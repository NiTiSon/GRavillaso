package nitis.nickname73.Gravillaso.content;

import arc.graphics.Color;
import arc.struct.ObjectMap;
import mindustry.content.*;
import mindustry.ctype.ContentList;
import mindustry.entities.bullet.*;
import mindustry.gen.Sounds;
import mindustry.type.Category;
import mindustry.type.ItemStack;
import mindustry.type.Liquid;
import mindustry.world.Block;
import mindustry.world.blocks.defense.MendProjector;
import mindustry.world.blocks.defense.Wall;
import mindustry.world.blocks.defense.turrets.ItemTurret;
import mindustry.world.blocks.defense.turrets.LiquidTurret;
import mindustry.world.blocks.defense.turrets.PowerTurret;
import mindustry.world.blocks.distribution.ArmoredConveyor;
import mindustry.world.blocks.distribution.MassDriver;
import mindustry.world.blocks.distribution.StackConveyor;
import mindustry.world.blocks.environment.Floor;
import mindustry.world.blocks.environment.OreBlock;
import mindustry.world.blocks.environment.StaticWall;
import mindustry.world.blocks.storage.CoreBlock;
import nitis.nickname73.Gravillaso.content.Bullets.PhaseBulletType;

public class ModBlocks implements ContentList {

    public static final int gravitiumDefence = 1280 ,magneturnDefence = 2300;
    public static final Color baseHealColor = Color.valueOf("84F490"), phaseHealColor = Color.valueOf("B5FFBD");

    public static Block
            //Environment
            redSand, redSandWall,
    //Defence
    hyperMender,colossalHealingDome,gravitiumWall,gravitiumWallLarge,magneturnWall,magneturnWallLarge,
    //Distribution
    magneturnConveyor,electroConveyor,colossalDriver,
    //Drills
    //Production
    //Turrets
    destiny,arhiepiscop,fierySpray,renunciation,phaseFuse,voltum,sunrise,saturn,
    //Storages
    molecularCore,coliseumCore,
    //Units
    //Ores
    oreQuartz;

    @Override
    public void load() {
        //region Environment
        redSand = new Floor("red-sand"){{
            wall = redSandWall;
            itemDrop = Items.sand;
            playerUnmineable = true;
        }};
        redSandWall = new StaticWall("red-sand-wall"){{
            breakable = false;
            variants = 2;
            inEditor = true;
            targetable = false;
        }};
        //endregion
        //region Defence
        hyperMender = new MendProjector("hyper-mender"){{
            requirements(Category.effect, ItemStack.with(Items.lead, 170, Items.silicon, 220, Items.thorium, 120, Items.silicon, 120, Items.phaseFabric, 50, ModItems.gravitium, 35));


            size = 3;
            health = 80 * size * size;

            baseColor = baseHealColor;
            phaseColor = phaseHealColor;

            range = 120;
            healPercent = 15;
            phaseBoost = 0.25f;
            phaseRangeBoost = 160;
            itemCapacity = 10;
            useTime = 540;

            consumes.item(Items.phaseFabric).boost();
            consumes.power(4.5f);
        }};
        colossalHealingDome = new MendProjector("colossal-healing-dome"){{
            requirements(Category.effect, ItemStack.with(Items.lead, 240, Items.silicon, 220, Items.thorium, 190, Items.phaseFabric, 220, ModItems.electroBrass, 90));
            size = 4;
            health = 120 * size * size;

            baseColor = baseHealColor;
            phaseColor = phaseHealColor;

            range = 1200;
            healPercent = 1;
            phaseBoost = 0.5f;
            phaseRangeBoost = 320;
            itemCapacity = 10;
            useTime = 120;

            consumes.item(Items.phaseFabric, 4).boost();
            consumes.power(9.5f);
        }};
        gravitiumWall = new Wall("gravitium-wall"){{
            requirements(Category.defense, ItemStack.with(ModItems.gravitium, 5,Items.plastanium,1));
            size = 1;
            health = gravitiumDefence * size * size;
            insulated = true;
            absorbLasers = true;
        }};
        gravitiumWallLarge = new Wall("gravitium-wall-large"){{
            requirements(Category.defense, ItemStack.with(ModItems.gravitium, 20,Items.plastanium,4));
            size = 2;
            health = gravitiumDefence * size * size;
            insulated = true;
            absorbLasers = true;
        }};
        magneturnWall = new Wall("magneturn-wall"){{
            requirements(Category.defense, ItemStack.with(ModItems.magneturn, 4,Items.surgeAlloy,1));
            size = 1;
            health = magneturnDefence * size * size;
        }};
        magneturnWallLarge = new Wall("magneturn-wall-large"){{
            requirements(Category.defense, ItemStack.with(ModItems.magneturn, 20,Items.surgeAlloy,4));
            size = 2;
            health = magneturnDefence * size * size;
        }};
        //endregion
        //region Distribution
        magneturnConveyor = new ArmoredConveyor("magneturn-conveyor"){{
            requirements(Category.distribution, ItemStack.with(Items.thorium,1,ModItems.magneturn,1));
            health = 240;
            displayedSpeed = 25.45f;
            speed = 0.22f;
        }};
        electroConveyor = new StackConveyor("electro-conveyor"){{
            requirements(Category.distribution, ItemStack.with(Items.thorium,1,Items.silicon,1,ModItems.electroBrass,1));
            speed = 0.1f;
            itemCapacity = 15;
            splitOut = true;
            recharge = 2;
            loadEffect = Fx.plasticburn;
            unloadEffect = Fx.plasticburn;
        }};
        colossalDriver = new MassDriver("colossal-driver"){{
            requirements(Category.distribution, ItemStack.with(Items.lead,225,Items.titanium,225,Items.thorium,75,ModItems.gravitium,25,Items.silicon,75));
            size = 4;
            health = 70 * size * size;
            itemCapacity = 240;
            range = 520;
            rotateSpeed = 0.03f;
            translation = 7f;
            minDistribute = 40;
            knockback = 4;
            reloadTime = 260;
            bulletSpeed = 12.5f;
            bulletLifetime = 600;
            consumes.power(4f);
        }};
        //endregion
        //region Drills

        //endregion
        //region Production

        //endregion
        //region Turrets
        destiny = new PowerTurret("destiny"){{
            requirements(Category.turret, ItemStack.with(Items.lead,150,Items.graphite,75,Items.silicon,70,ModItems.gravitium,25));
            health = 1340;
            size = 2;
            reloadTime = 20;
            inaccuracy = 2;
            range = 180;
            powerUse = 7;
            shootType = new LaserBulletType(45){{
                lifetime = 40;
                length = 220;
                width = 10;
                shootEffect = Fx.lightningShoot;
            }};
            shootSound = Sounds.laser;
            chargeEffects = 6;
            chargeMaxDelay = 20;
            chargeTime = 15;
            targetGround = true;
            targetAir = false;
            rotateSpeed = 3.5f;
            shots = 1;
            coolantMultiplier = 6;
        }};
        arhiepiscop = new PowerTurret("arhiepiscop"){{
            requirements(Category.turret, ItemStack.with(Items.graphite,220,Items.metaglass,230,Items.phaseFabric,120,Items.surgeAlloy,220));
            health = 1360;
            size = 3;
            reloadTime = 20f;
            inaccuracy = 0;
            range = 165;
            powerUse = 16;
            heatColor = Color.valueOf("5c5c02");
            shootType = new LightningBulletType(){{
                lightningColor = Color.valueOf("f2f2c4");
                lightningLength = 26;
                speed = 0f;
                lifetime = 45;
                buildingDamageMultiplier = 0.75f;
                status = ModStatuses.broken;
                damage = 59;
            }};
            shootSound = Sounds.spark;
            shootEffect = Fx.redgeneratespark;
            targetAir = false;
            targetGround = true;
            coolantUsage = 0.065f;
        }};
        fierySpray = new LiquidTurret("fiery-spray"){{
            requirements(Category.turret, ItemStack.with(Items.lead,150,Items.graphite,75,Items.silicon,70,ModItems.gravitium,25));
            size = 3;
            health = 1200;
            ammoTypes = ObjectMap.of(
                    ModLiquids.gasoline, new LiquidBulletType(){{
                        lifetime = 35;
                        speed = 6;
                        knockback = 1.5f;
                        puddleSize = 7;
                        orbSize = 4;
                        drag = 0.001f;
                        ammoMultiplier = 0.2f;
                        statusDuration = 240;
                        damage = 1.5f;
                        liquid = ModLiquids.gasoline;
            }},
                    ModLiquids.flammableLiquid, new LiquidBulletType(){{
                        lifetime = 35;
                        speed = 6;
                        knockback = 1.5f;
                        puddleSize = 9;
                        orbSize = 5;
                        drag = 0.001f;
                        ammoMultiplier = 0.3f;
                        statusDuration = 240;
                        damage = 1.2f;
                        liquid = ModLiquids.flammableLiquid;
                    }}
            );
            shootCone = 45;
            restitution = 0.04f;
            recoilAmount = 1;
            inaccuracy = 5;
            velocityInaccuracy = 0.15f;
            shots = 3;
            reloadTime = 1.5f;
        }};
        renunciation = new ItemTurret("renunciation"){{
            requirements(Category.turret, ItemStack.with(Items.graphite,220,Items.thorium,230,ModItems.gravitium,180,Items.surgeAlloy,120,ModItems.magneturn,120));
            size = 3;
            health = 1780;
            range = 280;
            inaccuracy = 9;
            rotateSpeed = 2.75f;
            xRand = 7;
            alternate = true;
            reloadTime = 90;
            shots = 3;
            ammoTypes = ObjectMap.of(
                    ModItems.gravitium, new MissileBulletType(){{
                        backColor = Color.valueOf("6ba1a2ff");
                        frontColor = Color.valueOf("b9eceeff");
                        splashDamageRadius = 32;
                        splashDamage = 9;
                        lifetime = 67;
                        speed = 5;
                        damage = 29;
                        width = 7;
                        height = 11;
                        hitSound = Sounds.explosion;
                        despawnEffect = Fx.explosion;
                        hitEffect = Fx.explosion;
                        shootEffect = Fx.shootSmall;
                        smokeEffect = Fx.shootSmallSmoke;
                        homingPower = 0.04f;
                        fragBullets = 7;
                        fragLifeMax = 2;
                        fragLifeMin = 0.75f;
                        fragVelocityMax = 3;
                        fragVelocityMin = 1.75f;
                        buildingDamageMultiplier = 0.35f;
                        fragBullet = new MissileBulletType(){{
                            backColor = Color.valueOf("6ba1a2ff");
                            frontColor = Color.valueOf("b9eceeff");
                            width = 6;
                            height = 9.7f;
                            damage = 8;
                            hitSound = Sounds.explosion;
                            smokeEffect = Fx.shootSmallSmoke;
                            homingPower = 0.08f;
                            buildingDamageMultiplier = 0.35f;
                        }};
                    }},
                    Items.surgeAlloy, new MissileBulletType(){{
                        splashDamageRadius = 49;
                        splashDamage = 22;
                        lifetime = 71;
                        speed = 4.2f;
                        damage = 63;
                        width = 6.1f;
                        height = 12;
                        hitSound = Sounds.explosion;
                        despawnEffect = Fx.explosion;
                        hitEffect = Fx.explosion;
                        shootEffect = Fx.shootSmall;
                        smokeEffect = Fx.shootSmallSmoke;
                        lightning = 6;
                        lightningCone = 240;
                        lightningDamage = 33;
                        lightningLength = 12;
                        lightningLengthRand = 12;
                        buildingDamageMultiplier = 0.35f;
                        status = StatusEffects.electrified;
                    }},
                    ModItems.magneturn, new MissileBulletType(){{
                        backColor = Color.valueOf("9171a7ff");
                        frontColor = Color.valueOf("ad8dc2ff");
                        splashDamageRadius = 62;
                        splashDamage = 29;
                        lifetime = 89;
                        speed = 5.8f;
                        width = 8.5f;
                        height = 16.7f;
                        hitSound = Sounds.explosion;
                        despawnEffect = Fx.explosion;
                        hitEffect = Fx.explosion;
                        shootEffect = Fx.shootSmall;
                        smokeEffect = Fx.shootSmallSmoke;
                        homingPower = 0.09f;
                        ammoMultiplier = 2;
                        fragBullets = 8;
                        fragLifeMax = 3;
                        fragLifeMin = 1.8f;
                        fragVelocityMax = 2;
                        fragVelocityMin = 1.15f;
                        buildingDamageMultiplier = 0.4f;
                        fragBullet = new MissileBulletType(){{
                            backColor = Color.valueOf("9171a7ff");
                            frontColor = Color.valueOf("ad8dc2ff");
                            damage = 12;
                            width = 8;
                            height = 11.7f;
                            hitSound = Sounds.explosion;
                            smokeEffect = Fx.shootSmallSmoke;
                            homingPower = 0.8f;
                            buildingDamageMultiplier = 0.4f;
                        }};
                        status = ModStatuses.broken;
                    }}
            );
            coolantUsage = 0.075f;
        }};
        phaseFuse = new ItemTurret("phase-fuse"){{
            requirements(Category.turret,ItemStack.with(Items.copper,220,Items.silicon,420,Items.plastanium,120,Items.thorium,65,ModItems.magneturn,70));
            size = 4;
            health = 2800;
            range = 120;
            shots = 4;
            shootShake = 5;
            recoilAmount = 6.4f;
            shootCone = 65;
            shootSound = Sounds.shotgun;
            rotateSpeed = 2.8f;
            reloadTime = 90;
            consumes.power(5.5f);
            ammoTypes = ObjectMap.of(
                   Items.phaseFabric, new ShrapnelBulletType(){{
                       length = 200;
                       damage = 220;
                       width = 17.5f;
                       serrationLenScl = 3;
                       serrationSpaceOffset = 60;
                       serrationFadeOffset = 0.25f;
                       serrations = 19;
                       serrationWidth = 10;
                       ammoMultiplier = 3;
                       lifetime = 40;
                       fromColor = Color.valueOf("f4ba6e");
                       toColor = Color.valueOf("d44e13");
                       shootEffect = Fx.thoriumShoot;
                       smokeEffect = Fx.sparkShoot;
                   }}
            );
        }};
        voltum = new PowerTurret("voltum"){{
            requirements(Category.turret, ItemStack.with(Items.copper,220,Items.titanium,140,Items.plastanium,90,ModItems.electroBrass,80));
            size = 3;
            health = 780;
            reloadTime = 14;
            inaccuracy = 3.5f;
            range = 150;
            shots = 3;
            shootType = new LightningBulletType(){{
                lightningColor = Color.valueOf("a4ded0");
                speed = 2;
                lifetime = 50;
                damage = 14;
                reloadMultiplier = 1.15f;
                shootEffect = Fx.lightningShoot;
            }};
            shootSound = Sounds.spark;
            heatColor = Color.red;
            powerUse = 9.5f;
            targetAir = true;
            targetGround = true;
        }};
        sunrise = new PowerTurret("sunrise"){{
            requirements(Category.turret, ItemStack.with(ModItems.magneturn,220,Items.phaseFabric,120,Items.surgeAlloy,220,ModItems.electroBrass,80));
            size = 4;
            health = 6260;
            reloadTime = 180;
            rotateSpeed = 2.2f;
            range = 205;
            powerUse = 35;
            heatColor = Color.valueOf("5c5c02");
            coolantMultiplier = 1.245f;
            shootSound = Sounds.spark;
            shootEffect = Fx.redgeneratespark;
            targetAir = true;
            targetGround = true;
            shootType = new LightningBulletType(){{
                lightningColor = Color.valueOf("f2f2c4");
                lightningLength = 32;
                speed = 0.001f;
                lightRadius = 2;
                lifetime = 60;
                damage = 434;
                buildingDamageMultiplier = 0.25f;
                status = StatusEffects.shocked;
            }};
        }};
        //endregion
        //region Storages
        molecularCore = new CoreBlock("molecular-core"){{
            requirements(Category.effect, ItemStack.with(Items.copper,12000,Items.lead,12000,ModItems.gravitium,6000,Items.thorium,8000,Items.silicon,10000,Items.surgeAlloy,3000));
            this.unitType = UnitTypes.gamma;
            this.health = 8600;
            this.itemCapacity = 19000;
            this.size = 6;
            //this.thrusterLength = 8.5F; WTF
            this.unitCapModifier = 34;
            this.researchCostMultiplier = 0.2F;
        }};
        coliseumCore = new CoreBlock("coliseum-core"){{
            requirements(Category.effect, ItemStack.with(Items.copper,18000,Items.lead,14000,ModItems.gravitium,9000,Items.thorium,8000,Items.silicon,14000,Items.surgeAlloy,7000));
            this.unitType = UnitTypes.gamma;
            this.health = 11000;
            this.itemCapacity = 25000;
            this.size = 7;
            this.unitCapModifier = 48;
            this.researchCostMultiplier = 0.2F;
        }};
        //endregion
        //region Ores
        oreQuartz = new OreBlock("quartz-ore"){{
            variants = 3;
            oreDefault = false;
            oreScale = 25.4209f;
            oreThreshold = 0.884f;
            itemDrop = ModItems.quartz;
        }};
        //endregion

        //DO Override origin blocks
    }
}
