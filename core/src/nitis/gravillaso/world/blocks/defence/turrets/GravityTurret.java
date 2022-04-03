package nitis.gravillaso.world.blocks.defence.turrets;

import arc.Core;
import arc.func.Func;
import arc.math.Mathf;
import arc.util.io.Reads;
import arc.util.io.Writes;
import mindustry.content.Planets;
import mindustry.entities.bullet.BulletType;
import mindustry.game.Team;
import mindustry.gen.Building;
import mindustry.graphics.Drawf;
import mindustry.ui.Bar;
import mindustry.world.Tile;
import mindustry.world.blocks.defense.turrets.PowerTurret;
import nitis.gravillaso.GRVars;
import nitis.gravillaso.content.GRPal;
import nitis.gravillaso.world.blocks.gravity.GravityModuleHolder;
import nitis.gravillaso.world.blocks.gravity.GravityUsager;
import nitis.gravillaso.world.modules.GravityModule;

public class GravityTurret extends PowerTurret {
    public int maxGravity = 1_600;
    public float minSpeedModifier = 0.25f;
    public GravityTurret(String name) {
        super(name);
        this.absorbLasers = true;
        this.consumesPower = true;
        update = true;
    }

    @Override
    public void setStats(){
        super.setStats();
    }

    @Override
    public void setBars() {
        super.setBars();
        bars.add("gravity-module", e -> {
            Func<Building,Float> rat = (building) -> {
                float ratio = 0;
                if (building instanceof GravityModuleHolder grModule) {
                    ratio = grModule.getGravityModule().getRatio();
                }
                return ratio;
            };
            return new Bar(
                    () -> Core.bundle.get("bar.gravity-module").formatted(rat.get(e) * 100),
                    () -> GRPal.magneturnLight,
                    () -> rat.get(e)
            );
        });
    }

    public class GravityTurretBuild extends PowerTurretBuild implements GravityModuleHolder, GravityUsager {
        private GravityModule gravity;
        private int _i = 0;
        private int allBonus;
        @Override
        public GravityModule getGravityModule() {
            return gravity;
        }
        @Override
        public void applyBonus(int bonus) {
            _i++;
            allBonus += bonus;
        }
        @Override
        public BulletType useAmmo(){
            return shootType;
        }

        @Override
        public void drawSelect(){
            Drawf.dashCircle(x, y, range(), team.color);
        }

        @Override
        public void update() {
            gravity.setValue(allBonus + GRVars.getGravity(Planets.serpulo));
            if (_i > 0) {
                _i = 0;
                allBonus = 0;
            }
            super.update();
        }
        @Override
        public float range() {
            return range * getGravityModule().getRatio();
        }

        public float speedMultiplier() {
            return gravity.getRatio();
        }

        @Override
        public Building init(Tile tile, Team team, boolean shouldAdd, int rotation) {
            Building building = super.init(tile, team, shouldAdd, rotation);
            this.gravity = new GravityModule(0, maxGravity);
            return building;
        }
        @Override
        public boolean absorbLasers() {
            return absorbLasers && gravity.getRatio() >= 0.25f; //When gravity exists true
        }

        @Override
        protected void bullet(BulletType type, float angle){
            float lifeScl = type.scaleVelocity ? Mathf.clamp(Mathf.dst(x + tr.x, y + tr.y, targetPos.x, targetPos.y) / type.range(), minRange / type.range(), range / type.range()) : 1f;

            type.create(this, team, x + tr.x, y + tr.y, angle, ( 1f + Mathf.range(velocityInaccuracy) ) * speedMultiplier(), lifeScl);
        }

        @Override
        public void write(Writes write) {
            super.write(write);
            gravity.write(write);
        }

        @Override
        public void read(Reads read, byte revision) {
            super.read(read, revision);
            if (revision >= 1){
                gravity = GravityModule.create(read);
            }
        }
        @Override
        public byte version(){
            return 1;
        }
    }
}
