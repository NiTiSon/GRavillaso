package nitis.gravillaso.world.blocks.defence.turrets;

import arc.Core;
import arc.func.Func;
import arc.graphics.g2d.Draw;
import arc.graphics.g2d.TextureRegion;
import arc.math.Mathf;
import arc.struct.Seq;
import arc.util.io.Reads;
import arc.util.io.Writes;
import mindustry.Vars;
import mindustry.entities.Units;
import mindustry.entities.bullet.BulletType;
import mindustry.gen.Building;
import mindustry.graphics.Drawf;
import mindustry.graphics.Pal;
import mindustry.ui.Bar;
import mindustry.world.blocks.defense.turrets.PowerTurret;
import mindustry.world.consumers.ConsumeLiquidBase;
import mindustry.world.consumers.ConsumeType;
import mindustry.world.meta.Stat;
import mindustry.world.meta.StatUnit;
import mindustry.world.meta.StatValues;
import nitis.gravillaso.content.GRPal;
import nitis.gravillaso.world.blocks.gravity.GravityConsumer;
import nitis.gravillaso.world.blocks.gravity.GravityProvider;

public class GravityTurret extends PowerTurret {
    public float requiredGravity = 160f;
    public float minGravity = 20f;
    /**Shoot position at Y */
    public float shootY = 0f;
    public TextureRegion gravityRegion;
    public float requiredGravityToAbsorbLasers = 120f;
    public GravityTurret(String name) {
        super(name);
        this.absorbLasers = true;
        this.consumesPower = true;
        update = true;
    }

    @Override
    public void load() {
        super.load();
        gravityRegion = Core.atlas.find(this.name + "-gravity", "error");
    }

    @Override
    public void drawPlace(int x, int y, int rotation, boolean valid) {
        super.drawPlace(x, y, rotation, valid);

        Drawf.dashCircle(x * Vars.tilesize, y * Vars.tilesize, range, GRPal.magneturnLight);
        Drawf.dashCircle(x * Vars.tilesize, y * Vars.tilesize, range * getMinGravityModifier(), Pal.accent);
    }
    public float getMinGravityModifier() {
        return Math.min(minGravity / requiredGravity, 1f);
    }
    @Override
    public void setStats() {
        stats.add(Stat.size, "@x@", size, size);

        if(synthetic()){
            stats.add(Stat.health, health, StatUnit.none);
        }

        if(canBeBuilt() && requirements.length > 0){
            stats.add(Stat.buildTime, buildCost / 60, StatUnit.seconds);
            stats.add(Stat.buildCost, StatValues.items(false, requirements));
        }

        if(instantTransfer){
            stats.add(Stat.maxConsecutive, 2, StatUnit.none);
        }

        consumes.display(stats);

        //Note: Power stats are added by the consumers.
        if(hasLiquids) stats.add(Stat.liquidCapacity, liquidCapacity, StatUnit.liquidUnits);
        if(hasItems && itemCapacity > 0) stats.add(Stat.itemCapacity, itemCapacity, StatUnit.items);

        stats.add(Stat.range, String.format("%s~%s", Math.round(range * getMinGravityModifier()), Math.round(range)), StatUnit.blocks);

        if(acceptCoolant){
            stats.add(Stat.booster, StatValues.boosters(reloadTime, consumes.<ConsumeLiquidBase>get(ConsumeType.liquid).amount, coolantMultiplier, true, l -> consumes.liquidfilters.get(l.id)));
        }

        stats.add(Stat.inaccuracy, (int)inaccuracy, StatUnit.degrees);
        stats.add(Stat.reload, 60f / (reloadTime) * (alternate ? 1 : shots), StatUnit.perSecond);
        stats.add(Stat.targetsAir, targetAir);
        stats.add(Stat.targetsGround, targetGround);
        if(ammoPerShot != 1) stats.add(Stat.ammoUse, ammoPerShot, StatUnit.perShot);
    }

    @Override
    public void setBars() {
        super.setBars();
        bars.add("gravity-module", build -> {
            Func<Building,Float> rat = (building) -> {
                if (build instanceof GravityTurretBuild) {
                    return ((GravityTurretBuild)build).gravityRatio();
                }
                return 0f;
            };
            return new Bar(
                    () -> Core.bundle.get("bar.gravity-module").replace("%d", Math.round(rat.get(build) *100) + "" ).replace("%%", "%"),
                    () -> GRPal.magneturnLight,
                    () -> rat.get(build)
            );
        });
    }
    @Override
    public TextureRegion[] icons() {
        return new TextureRegion[]{baseRegion, region};
    }

    public class GravityTurretBuild extends PowerTurretBuild implements GravityConsumer {
        private final Seq<GravityProvider> gravityProviders = new Seq<>();
        private float currentGravity = 0f;
        @Override
        public BulletType useAmmo(){
            return shootType;
        }

        @Override
        public void drawSelect() {
            Drawf.dashCircle(x, y, range(), team.color);
        }

        @Override
        public void updateTile() {
            super.updateTile();
            currentGravity = 0;
            for (GravityProvider provider: gravityProviders) {
                currentGravity += provider.getGravity();
            }
            gravityProviders.clear();
        }

        @Override
        public float range() {
            return range * gravityRatio();
        }

        @Override
        public float gravity() {
            return currentGravity;
        }

        @Override
        public float maxGravity() {
            return requiredGravity;
        }

        @Override
        public float minGravity() {
            return minGravity;
        }

        public float speedMultiplier() {
            return gravityRatio();
        }
        @Override
        public boolean absorbLasers() {
            return absorbLasers && currentGravity >= requiredGravityToAbsorbLasers;
        }

        @Override
        protected void bullet(BulletType type, float angle){
            float lifeScl = type.scaleVelocity ? Mathf.clamp(Mathf.dst(x + tr.x, y + tr.y, targetPos.x, targetPos.y) / type.range(), minRange / type.range(), range / type.range()) : 1f;
            BulletType otherType = type.copy();
            otherType.damage *= gravityRatio();
            tr.trns(rotation, shootLength + shootY, Mathf.range(xRand));
            otherType.create(
                    this,
                    team,
                    x + tr.x,
                    y + tr.y,
                    angle,
                    ( 1f + Mathf.range(velocityInaccuracy) ) * speedMultiplier(),
                    lifeScl
            );
        }
        @Override
        protected void findTarget(){
            float _range = range();
            if(targetAir && !targetGround){
                target = Units.bestEnemy(team, x, y, _range, e -> !e.dead() && !e.isGrounded(), unitSort);
            }else{
                target = Units.bestTarget(team, x, y, _range, e -> !e.dead() && (e.isGrounded() || targetAir) && (!e.isGrounded() || targetGround), b -> targetGround, unitSort);

                if(target == null && canHeal()){
                    target = Units.findAllyTile(team, x, y, _range, b -> b.damaged() && b != this);
                }
            }
        }
        @Override
        public void write(Writes write) {
            super.write(write);

        }

        @Override
        public void draw() {
            super.draw();
            Draw.alpha(gravityRatio());
            Draw.rect(gravityRegion, x + tr2.x, y + tr2.y, rotation - 90);
            Draw.alpha(1f);
        }

        @Override
        public void read(Reads read, byte revision) {
            super.read(read, (byte) 1);
            if (revision == 2) {
                return;
            }
            if (revision == 1) {
                read.i();
                read.i();
            }
        }
        @Override
        public byte version(){
            return 2;
        }

        @Override
        public void connectGravityProvider(GravityProvider provider) {
            gravityProviders.add(provider);
        }
    }
}
