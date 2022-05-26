package nitis.gravillaso.world.blocks.defence;

import arc.Core;
import arc.func.Cons2;
import arc.graphics.Color;
import arc.graphics.g2d.Draw;
import arc.graphics.g2d.TextureRegion;
import arc.math.Mathf;
import arc.util.Tmp;
import mindustry.Vars;
import mindustry.game.Team;
import mindustry.gen.Building;
import mindustry.gen.Sounds;
import mindustry.graphics.Drawf;
import mindustry.logic.Ranged;
import mindustry.ui.Bar;
import mindustry.world.Block;
import mindustry.world.consumers.ConsumeType;
import mindustry.world.meta.BlockGroup;
import mindustry.world.meta.Env;
import nitis.gravillaso.content.GRPal;
import nitis.gravillaso.world.blocks.gravity.GravityConsumer;
import nitis.gravillaso.world.blocks.gravity.GravityProvider;

import static mindustry.Vars.indexer;
import static mindustry.Vars.player;

public class GravityProjector extends Block {
    public final int timerUse = timers++;
    public float phaseUseTime = 120f;

    public float radius = 320f;
    public float phaseRadius = radius * 1.25f;
    public float generatedGravity = 450f;
    public float phaseGeneratedGravity = generatedGravity * 1.25f;

    public TextureRegion topRegion;

    @Override
    public boolean outputsItems(){
        return false;
    }

    public GravityProjector(String name) {
        super(name);
        update = true;
        solid = true;
        group = BlockGroup.projectors;
        hasPower = true;
        hasItems = true;
        ambientSound = Sounds.grinding;
        ambientSoundVolume = 0.08f;
        consumesPower = true;

        envEnabled |= Env.space;
    }

    @Override
    public void init() {
        super.init();
        topRegion = Core.atlas.find(name+"-top", "clear");
    }

    @Override
    public void setBars(){
        super.setBars();
        bars.add("gravity", (GravityProjectorBuild build) -> new Bar("stat.gravity", GRPal.magneturnLight, () -> build.getGravity() / phaseGeneratedGravity));
    }

    @Override
    public void drawPlace(int x, int y, int rotation, boolean valid){
        super.drawPlace(x, y, rotation, valid);

        Draw.color(GRPal.magneturnLight);
        Drawf.dashCircle(x * Vars.tilesize, y * Vars.tilesize, radius, GRPal.magneturnLight);
        Draw.color();

        indexer.eachBlock(
                player.team(),
                x * Vars.tilesize + offset,
                y * Vars.tilesize + offset,
                radius,
                (build) -> build instanceof GravityConsumer,
                (build) -> Drawf.selected(build, Tmp.c1.set(GRPal.magneturn).a(Mathf.absin(4f, 1f))));
    }

    @Override
    protected TextureRegion[] icons() {
        super.icons();
        return new TextureRegion[] {Core.atlas.find(name), Core.atlas.find(name + "-top")};
    }

    public class GravityProjectorBuild extends Building implements Ranged, GravityProvider {
        public boolean phaseActive = false;
        @Override
        public float range() {
            return phaseActive ? phaseRadius : radius;
        }

        @Override
        public void updateTile() {
            super.updateTile();
            phaseActive = consumes.get(ConsumeType.item).valid(this);

            indexer.eachBlock(
                    this.team,
                    this.x(),
                    this.y(),
                    range(),
                    (build) -> true, //Anyway check in next function SKIP
                    (build) -> {
                        if (build instanceof GravityConsumer) {
                            ((GravityConsumer)build).connectGravityProvider(this);
                        }
                    }
                    );
            if(phaseActive && timer(timerUse, phaseUseTime) && efficiency() > 0){
                consume();
            }
        }

        @Override
        public void draw() {
            super.draw();
            float eff = getGravity() / phaseGeneratedGravity;
            if (eff > 1) {
                Draw.color(GRPal.magneturn, Color.white, eff - 1);
            } else {
                Draw.color(Color.darkGray, GRPal.magneturn, eff);
            }

            Draw.rect(topRegion, this.x, this.y);

            Draw.color();
        }

        @Override
        public float getGravity() {
            return (phaseActive ? phaseGeneratedGravity : generatedGravity) * this.power.status;
        }
        @Override
        public void drawSelect() {
            super.drawSelect();
            getPotentialConnections(this.team, (b,u) -> drawConnect(b));
        }

        public void drawConnect(Building build){
            Drawf.selected(build, Tmp.c1.set(GRPal.magneturn).a(Mathf.absin(4f, 1f)));
        }
        protected void getPotentialConnections(Team team, Cons2<Building, GravityConsumer> others) {
            indexer.eachBlock(
                    team,
                    this.x(),
                    this.y(),
                    range(),
                    (build) -> true,
                    (build) -> {
                        if (build instanceof GravityConsumer) {
                            others.get(build, (GravityConsumer) build);
                        }
                    }
                    );
        }
    }
}
