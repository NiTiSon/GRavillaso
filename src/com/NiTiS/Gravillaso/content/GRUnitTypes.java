package com.NiTiS.Gravillaso.content;

import arc.Core;
import arc.graphics.Color;
import com.NiTiS.Gravillaso.type.GRWeapon;
import mindustry.content.Fx;
import mindustry.content.UnitTypes;
import mindustry.ctype.ContentList;
import mindustry.entities.abilities.MoveLightningAbility;
import mindustry.entities.abilities.UnitSpawnAbility;
import mindustry.entities.bullet.*;
import mindustry.gen.Sounds;
import mindustry.type.UnitType;
import mindustry.content.StatusEffects;
import mindustry.type.Weapon;
import mindustry.type.ammo.ItemAmmoType;
import mindustry.type.ammo.PowerAmmoType;
import mindustry.world.meta.BlockFlag;

public class GRUnitTypes implements ContentList {

    public static UnitType release, update;
    public static UnitType zap;
    public static UnitType coulomb, ampere, joule;

    @Override
    public void load() {
        release = new UnitType("release") {{
            onTitleScreen = false;
            hitSize = 6.8f;
            health = 320;
            fallSpeed = 0.025f;
            engineOffset = 9;
            engineSize = 3.2f;
            commandLimit = 6;
            flying = true;
            speed = 3.7f;
            drag = 0.1f;
            range = 30;
            accel = 0.35f;
            buildSpeed = 1.35f;
            rotateSpeed = 8.6f;
            mineSpeed = 9.0f;
            itemCapacity = 90;
            rotateShooting = false;
            constructor = UnitTypes.gamma.constructor;
            weapons.add(new GRWeapon("south") {{
                rotate = true;
                rotateSpeed = 60f;
                x = 6.9f;
                y = -4.5f;
                inaccuracy = 2.5f;
                reload = 20;
                shots = 1;
                mirror = true;
                shootSound = Sounds.shoot;
                bullet = new MissileBulletType() {{
                    lifetime = 45;
                    speed = 3.5f;
                    damage = 27;
                    width = 6;
                    height = 11.7f;
                    hitSound = Sounds.explosion;
                    smokeEffect = Fx.shootSmallSmoke;
                    homingPower = 0.25f;
                    buildingDamageMultiplier = 0.1f;
                    status = GRStatusEffects.broken;
                }};
            }});
        }};
        update = new UnitType("update") {{
            onTitleScreen = false;
            hitSize = 7.2f;
            health = 460;
            constructor = UnitTypes.gamma.constructor;
            speed = 4.5f;
            fallSpeed = 0.058f;
            accel = 0.25f;
            engineOffset = 9;
            engineSize = 3.2f;
            commandLimit = 8;
            flying = true;
            drag = 0.125f;
            range = 45f;
            buildSpeed = 2.25f;
            mineSpeed = 11;
            rotateSpeed = 9.5f;
            mineTier = 4;
            itemCapacity = 120;
            weapons.add(
                    new GRWeapon("south") {{
                        rotate = true;
                        rotateSpeed = 40;
                        flipSprite = false;
                        x = 9;
                        inaccuracy = 4;
                        y = -5.5f;
                        reload = 10;
                        mirror = true;
                        shootSound = Sounds.shoot;
                        bullet = new BasicBulletType() {{
                            buildingDamageMultiplier = 0.25f;
                            lifetime = 40;
                            speed = 8;
                            damage = 22;
                            width = 9;
                            height = 9;
                            hitSound = Sounds.explosion;
                            despawnEffect = Fx.explosion;
                            hitEffect = Fx.explosion;
                            shootEffect = Fx.lightningShoot;
                            smokeEffect = Fx.shootSmallSmoke;
                            homingPower = 0.1f;
                        }};

                    }},
                    new GRWeapon("violence") {{
                        rotate = false;
                        rotateSpeed = 60;
                        flipSprite = false;
                        x = 0;
                        y = 2;
                        inaccuracy = 0;
                        reload = 120;
                        shots = 1;
                        mirror = false;
                        shootSound = Sounds.bigshot;
                        bullet = new BasicBulletType() {{
                            buildingDamageMultiplier = 0.25f;
                            lifetime = 40;
                            speed = 2.25f;
                            damage = 24;
                            width = 9;
                            height = 9;
                            hitSound = Sounds.explosion;
                            despawnEffect = Fx.explosion;
                            hitEffect = Fx.explosion;
                            shootEffect = Fx.lightningShoot;
                            smokeEffect = Fx.shootSmallSmoke;
                            homingPower = 0.1f;
                            fragCone = 360;
                            fragBullet = new LightningBulletType() {{
                                damage = 42;
                                buildingDamageMultiplier = 0.75f;
                                status = StatusEffects.shocked;
                                lightningLength = 14;
                                lightningLengthRand = 20;
                                lightColor = Color.valueOf("#1995b0");
                            }};
                        }};
                    }}
            );
        }};
        zap = new UnitType("zap"){{
            constructor = UnitTypes.horizon.constructor;
            health = 95;
            speed = 3.125f;
            accel = 0.08f;
            drag = 0.016f;
            flying = true;
            hitSize = 5f;
            targetAir = false;
            engineOffset = 7.8f;
            range = 140f;
            faceTarget = false;
            armor = 0;
            //do not rush core, attack closest
            playerTargetFlags = new BlockFlag[]{null};
            targetFlags = new BlockFlag[]{BlockFlag.factory, BlockFlag.turret, null};
            commandLimit = 5;
            circleTarget = true;
            engineOffset = -0.2f;
            abilities.add(new MoveLightningAbility(14, 12, 0.05f, 0f, 2.5f, 3f, Color.valueOf("747FFF")){{
                bulletSpread = 90f;
                bulletAngle = 90f;
            }});
            weapons.add(new Weapon(){{
                minShootVelocity = 0.75f;
                x = 3f;
                shootY = 0f;
                reload = 12f;
                shootCone = 180f;
                ejectEffect = Fx.none;
                inaccuracy = 2f;
                ignoreRotation = true;
                shootSound = Sounds.none;
                bullet = new BombBulletType(14f, 35f){{
                    width = 7f;
                    height = 9f;
                    hitEffect = Fx.explosion;
                    shootEffect = Fx.none;
                    smokeEffect = Fx.none;

                    status = GRStatusEffects.broken;
                    statusDuration = 60f * 15;
                }};
            }});
        }};
        coulomb = new UnitType("coulomb") {{
            constructor = UnitTypes.risso.constructor;
            hitSize = 9.5f;
            accel = 0.44f;
            commandLimit = 8;
            flying = false;
            health = 320;
            armor = 3;
            speed = 1.35f;
            drag = 0.1f;
            range = 24;
            buildSpeed = 0;
            mineSpeed = 0;
            rotateSpeed = 5.25f;
            itemCapacity = 30;
            trailLength = 20;
            weapons.add(new GRWeapon("phis"){{
                rotate = true;
                rotateSpeed = 20;
                flipSprite = false;
                x = 0;
                y = -4;
                inaccuracy = 0;
                reload = 44;
                shots = 2;
                mirror = false;
                shootSound = Sounds.spark;
                bullet = new LightningBulletType(){{
                    lifetime = 28;
                    speed = 6.7f;
                    damage = 13;
                    shootEffect = Fx.redgeneratespark;
                    status = StatusEffects.shocked;
                }};
            }});
        }};
        ampere = new UnitType("ampere") {{
            constructor = UnitTypes.minke.constructor;
            hitSize = 12.0f;
            accel = 0.35f;
            commandLimit = 8;
            flying = false;
            health = 720;
            armor = 6;
            speed = 1.15f;
            drag = 0.175f;
            range = 24;
            buildSpeed = 0;
            mineSpeed = 0;
            itemCapacity = 40;

            trailLength = 40;
            trailScl = 1.9f;
            ammoType = new PowerAmmoType(700);
            ammoCapacity = 20;
            weapons.add(
                    new GRWeapon("phase"){{
                        rotate = true;
                        rotateSpeed = 6;
                        flipSprite = false;
                        x = 6.4f;
                        y = 2;
                        inaccuracy = 0;
                        reload = 75;
                        shots = 2;
                        mirror = true;
                        shootSound = Sounds.spark;
                        bullet = new LightningBulletType(){{
                           shootEffect = Fx.redgeneratespark;
                           lifetime = 14;
                           speed = 5.3f;
                            damage = 7;
                           status = StatusEffects.shocked;
                        }};
                    }},
                    new GRWeapon("sofe"){{
                        x = 0;
                        y = -5;
                        rotateSpeed = 3.5f;
                        reload = 120;
                        rotate = true;
                        shots = 1;
                        shootSound = Sounds.laser;
                        mirror = false;
                        cooldownTime = 20f;
                        bullet = new LaserBulletType(30){{

                            lifetime = 20;
                            length = 180;
                            width = 5;
                            shootEffect = Fx.lightningShoot;
                            status = StatusEffects.shocked;
                        }};
                    }}
            );
        }};
        joule = new UnitType("joule"){{
            constructor = UnitTypes.minke.constructor;
            health = 1120;
            ammoType = new PowerAmmoType(1500);
            ammoCapacity = 30;

            rotateShooting = false;
            clipSize = 175;
            abilities.add(new UnitSpawnAbility(zap, 60 * 25f, 0, -0.8f){{

            }});

        }};

    }
}