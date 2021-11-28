package nitis.nickname73.Gravillaso.content.Blocks;

import arc.math.Mathf;
import arc.util.Time;
import mindustry.entities.bullet.BulletType;
import mindustry.entities.bullet.MissileBulletType;
import mindustry.world.blocks.defense.turrets.PowerTurret;

public class SLT extends PowerTurret {

    public BulletType backSidesShoot = new MissileBulletType();
    public float backSidesDegress = 135;
    public float backSideReloadTime = 25;

    public SLT(String name) {
        super(name);
    }

    public class SLTBuild extends PowerTurretBuild{
        public float backSideReload = 0;
        @Override
        protected void bullet(BulletType type, float angle){
            float lifeScl = type.scaleVelocity ? Mathf.clamp(Mathf.dst(x + tr.x, y + tr.y, targetPos.x, targetPos.y) / type.range(), minRange / type.range(), range / type.range()) : 1f;

            type.create(this, team, x + tr.x, y + tr.y, angle, 1f + Mathf.range(velocityInaccuracy), lifeScl);
        }

        @Override
        protected void updateShooting() {
            reload += delta() * peekAmmo().reloadMultiplier * baseReloadSpeed();
            backSideReload += delta() * backSidesShoot.reloadMultiplier * baseReloadSpeed();

            if(reload >= reloadTime && !charging){
                BulletType type = peekAmmo();

                shoot(type);

                reload %= reloadTime;
            }
            if(backSideReload >= backSideReloadTime){
                backShoot(backSidesShoot);

                backSideReload %= backSideReloadTime;
            }
        }
        protected void backShoot(BulletType type){
            if(dead) return;
            recoil = recoilAmount;
            tr.trns(rotation - backSidesDegress, shootLength);
            bullet(type, rotation - 10 + Mathf.range(inaccuracy + type.inaccuracy));
            tr.trns(rotation + backSidesDegress, shootLength);
            bullet(type, rotation + 10 + Mathf.range(inaccuracy + type.inaccuracy));
        }
        @Override
        protected void shoot(BulletType type){
            if(chargeTime > 0){
                useAmmo();

                tr.trns(rotation, shootLength);
                chargeBeginEffect.at(x + tr.x, y + tr.y, rotation);
                chargeSound.at(x + tr.x, y + tr.y, 1);

                for(int i = 0; i < chargeEffects; i++){
                    Time.run(Mathf.random(chargeMaxDelay), () -> {
                        if(dead) return;
                        tr.trns(rotation, shootLength);
                        chargeEffect.at(x + tr.x, y + tr.y, rotation);
                    });
                }

                charging = true;

                Time.run(chargeTime, () -> {
                    if(dead) return;
                    tr.trns(rotation, shootLength);
                    recoil = recoilAmount;
                    heat = 1f;
                    bullet(type, rotation + Mathf.range(inaccuracy + type.inaccuracy));
                    effects();
                    charging = false;
                });

                //when burst spacing is enabled, use the burst pattern
            }else if(burstSpacing > 0.0001f){
                for(int i = 0; i < shots; i++){
                    int ii = i;
                    Time.run(burstSpacing * i, () -> {
                        if(dead || !hasAmmo()) return;

                        recoil = recoilAmount;

                        tr.trns(rotation, shootLength, Mathf.range(xRand));
                        bullet(type, rotation + Mathf.range(inaccuracy + type.inaccuracy) + (ii - (int)(shots / 2f)) * spread);
                        effects();
                        useAmmo();
                        recoil = recoilAmount;
                        heat = 1f;
                    });
                }

            }else{
                //otherwise, use the normal shot pattern(s)

                if(alternate){
                    float i = (shotCounter % shots) - (shots-1)/2f;

                    tr.trns(rotation - 90, spread * i + Mathf.range(xRand), shootLength);
                    bullet(type, rotation + Mathf.range(inaccuracy + type.inaccuracy));
                }else{
                    tr.trns(rotation, shootLength, Mathf.range(xRand));

                    for(int i = 0; i < shots; i++){
                        bullet(type, rotation + Mathf.range(inaccuracy + type.inaccuracy) + (i - (int)(shots / 2f)) * spread);
                    }
                }

                shotCounter++;

                recoil = recoilAmount;
                heat = 1f;
                effects();
                useAmmo();
            }
        }
    }
}
