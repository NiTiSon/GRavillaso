package nitis.gravillaso.world.blocks.defence.turrets;

import arc.Core;
import arc.func.Func;
import arc.math.Mathf;
import arc.struct.Seq;
import arc.util.io.Reads;
import arc.util.io.Writes;
import mindustry.Vars;
import mindustry.entities.bullet.BulletType;
import mindustry.gen.Building;
import mindustry.graphics.Drawf;
import mindustry.graphics.Pal;
import mindustry.ui.Bar;
import mindustry.world.blocks.defense.turrets.PowerTurret;
import mindustry.world.meta.Stat;
import nitis.gravillaso.content.GRPal;
import nitis.gravillaso.world.blocks.gravity.GravityConsumer;
import nitis.gravillaso.world.blocks.gravity.GravityProvider;

public class GravityTurret extends PowerTurret {
    public float requiredGravity = 160f;
    public float minGravity = 20f;

    public float requiredGravityToAbsorbLasers = 0.66f;
    public GravityTurret(String name) {
        super(name);
        this.absorbLasers = true;
        this.consumesPower = true;
        update = true;
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
        super.setStats();
        stats.add(Stat.range, "%s~%s".formatted(range * getMinGravityModifier(), range));
    }

    @Override
    public void setBars() {
        super.setBars();
        bars.add("gravity-module", e -> {
            Func<Building,Float> rat = (building) -> {
                if (e instanceof GravityTurretBuild gravityTurret) {
                    return gravityTurret.calculateGravity();
                }
                return 0f;
            };
            return new Bar(
                    () -> Core.bundle.get("bar.gravity-module").replace("%d", Math.round(rat.get(e) *100) + "" ).replace("%%", "%"),
                    () -> GRPal.magneturnLight,
                    () -> rat.get(e)
            );
        });
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
            return range * calculateGravity();
        }

        public float getCurrentGravity() {
            return currentGravity;
        }
        public float speedMultiplier() {
            return calculateGravity();
        }

        private float calculateGravity() {
            return Math.min(Math.max(minGravity, currentGravity) / requiredGravity, 1f);
        }
        @Override
        public boolean absorbLasers() {
            return absorbLasers && calculateGravity() >= requiredGravityToAbsorbLasers;
        }

        @Override
        protected void bullet(BulletType type, float angle){
            float lifeScl = type.scaleVelocity ? Mathf.clamp(Mathf.dst(x + tr.x, y + tr.y, targetPos.x, targetPos.y) / type.range(), minRange / type.range(), range / type.range()) : 1f;
            BulletType otherType = type.copy();
            otherType.damage *= calculateGravity();
            otherType.create(this, team, x + tr.x, y + tr.y, angle, ( 1f + Mathf.range(velocityInaccuracy) ) * speedMultiplier(), lifeScl);
        }

        @Override
        public void write(Writes write) {
            super.write(write);

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
