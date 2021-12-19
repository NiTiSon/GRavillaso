package nitis.nickname73.Gravillaso.content;

import mindustry.content.Fx;
import mindustry.content.UnitTypes;
import mindustry.ctype.ContentList;
import mindustry.entities.bullet.MissileBulletType;
import mindustry.gen.Sounds;
import mindustry.type.UnitType;

public class ModUnitTypes implements ContentList {

    public static UnitType release,update;
    public static UnitType coulomb,ampere,joule;
    @Override
    public void load() {
        release = new UnitType("release"){{
            onTitleScreen = false;
            hitSize = 8f;
            health = 320;
            fallSpeed = 0.025f;
            engineOffset = 9;
            engineSize = 3.2f;
            commandLimit = 6;
            flying = true;
            speed = 3.7f;
            drag = 0.1f;
            range = 30;
            buildSpeed = 1.35f;
            rotateSpeed = 8.6f;
            mineSpeed = 9.0f;
            itemCapacity = 90;
            rotateShooting = false;
            constructor = UnitTypes.alpha.constructor;
            weapons.add(new ModWeapon("south"){{
                rotate = true;
                rotateSpeed = 60f;
                x = 6.9f;
                y = -4.5f;
                inaccuracy = 2.5f;
                reload = 20;
                shots = 1;
                mirror = true;
                bullet = new MissileBulletType(){{
                    lifetime = 45;
                    speed = 3.5f;
                    damage = 27;
                    width = 6;
                    height = 11.7f;
                    hitSound = Sounds.explosion;
                    smokeEffect = Fx.shootSmallSmoke;
                    homingPower = 0.25f;
                    buildingDamageMultiplier = 0.1f;
                    status = ModStatuses.broken;
                }};
            }});
        }};
        update = new UnitType("update"){{
            onTitleScreen = false;
            hitSize = 8;
            health = 460;
            constructor = UnitTypes.alpha.constructor;
            speed = 0.45f;
            fallSpeed = 0.058f;
            accel =  5f;
            engineOffset = 9;
            engineSize = 3.2f;
            commandLimit = 7;
            flying = true;
            drag = 0.125f;
            range = 45f;
            buildSpeed = 2.25f;
            mineSpeed = 11;
            rotateSpeed = 9.5f;
            mineTier = 4;
            itemCapacity = 120;
        }};
    }
}
