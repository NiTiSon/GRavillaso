package com.NiTiS.Gravillaso.content;

import arc.graphics.Color;
import arc.struct.ObjectMap;
import arc.struct.Seq;
import com.NiTiS.Gravillaso.world.blocks.production.OverdriveDrill;
import mindustry.content.*;
import mindustry.ctype.ContentList;
import mindustry.entities.bullet.*;
import mindustry.gen.Sounds;
import mindustry.graphics.CacheLayer;
import mindustry.type.Category;
import mindustry.type.ItemStack;
import mindustry.type.LiquidStack;
import mindustry.type.UnitType;
import mindustry.world.Block;
import mindustry.world.blocks.Attributes;
import mindustry.world.blocks.defense.ForceProjector;
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
import mindustry.world.blocks.power.*;
import mindustry.world.blocks.production.AttributeCrafter;
import mindustry.world.blocks.production.GenericCrafter;
import mindustry.world.blocks.production.LiquidConverter;
import mindustry.world.blocks.production.SolidPump;
import mindustry.world.blocks.storage.CoreBlock;
import mindustry.world.blocks.units.Reconstructor;
import mindustry.world.blocks.units.RepairPoint;
import mindustry.world.blocks.units.UnitFactory;
import mindustry.world.draw.DrawMixer;
import mindustry.world.draw.DrawWeave;
import mindustry.world.meta.Attribute;
import mindustry.world.meta.BuildVisibility;

import static mindustry.type.ItemStack.with;

public class GRBlocks implements ContentList {
    public static final int gravitiumDefence = 1280 ,magneturnDefence = 2300;
    public static final Color baseHealColor = Color.valueOf("84f490"), phaseHealColor = Color.valueOf("B5ffbd");

    public static Block
    //Environment
    oreQuartz,redSand,redSandWall,burningGround,burningGroundWall,flammableLiquidBlock,metalFloorDamagedOrange,
    //Production
    advancedPyratiteMixer,advancedBlastMixer,gasolineBarrel,cryofluidChamber,flammableLiquidChamber,phaseCaldron,molecularConverter,molecularReconstructor,magneturnSmelter,electroSmelter,molecularPhaseWeaver,plastaniumMolecularPress,
    //Defence
    gravitiumWall,gravitiumWallLarge,magneturnWall,magneturnWallLarge,hyperMender,colossalHealingDome,colossalForceProjector,
    //Distribution
    magneturnConveyor,electroConveyor,colossalDriver,
    //Power
    multiConnector,batteryBig,gasolineGenerator,sporeGenerator,solarArray,
    //Drills
    deepOilPump,overdriveDrill,
    //Storages
    molecularCore,coliseumCore,
    //Turrets
    destiny,arhiepiscop,fierySpray,renunciation,phaseFuse,voltum,sunrise,
    //Units
    repairLaser,advancedNavalFactory,additiveReassembler, multiplicativeReassembler,
    //Light
    sunshine,
    //PlanetUnlocks
    _end;
    @Deprecated
    public static Block slt;

    @Override
    public void load() {
        //region Environment
        oreQuartz = new OreBlock("quartz-ore"){{
            variants = 3;
            oreDefault = false;
            oreScale = 25.4209f;
            oreThreshold = 0.884f;
            itemDrop = GRItems.quartz;
        }};
        redSand = new Floor("red-sand"){{
            wall = redSandWall;
            attributes.set(Attribute.heat, 0.05f);
            attributes.set(Attribute.water, -0.05f);
            itemDrop = Items.sand;
            playerUnmineable = true;
        }};
        redSandWall = new StaticWall("red-sand-wall"){{
            breakable = false;
            variants = 2;
            inEditor = true;
            targetable = false;
        }};
        burningGround = new Floor("burning-ground"){{
            variants = 4;
            damageTaken = 2.75f;
            speedMultiplier = 0.88f;
            walkEffect = Fx.smeltsmoke;
            status = StatusEffects.melting;
            statusDuration = 247f;
            wall = burningGroundWall;
            attributes.set(Attribute.heat, 2.75f);
            attributes.set(Attribute.water, -3);
            attributes.set(Attribute.oil, 0.25f);
        }};
        burningGroundWall = new StaticWall("burning-ground-wall"){{
            variants = 2;
            inEditor = true;
            breakable = false;
            targetable = false;
        }};
        flammableLiquidBlock = new Floor("flammable-liquid-block"){{
            variants = 0;
            speedMultiplier = 0.45f;
            damageTaken = 0.9f;
            drownTime = 360f;
            walkEffect = Fx.melting;
            status = StatusEffects.burning;
            statusDuration = 240;
            cacheLayer = CacheLayer.tar;
            liquidDrop = GRLiquids.flammableLiquid;
            liquidMultiplier = 0.2f;
            isLiquid = true;
            albedo = 0.5f;
        }};
        metalFloorDamagedOrange = new Floor("metal-floor-damaged-orange"){{
            variants = 3;
        }};
        //endregion
        //region Production
        advancedPyratiteMixer = new GenericCrafter("advanced-pyratite-mixer"){{
            requirements(Category.crafting, ItemStack.with(Items.metaglass,70,Items.titanium,130,Items.silicon,50,Items.phaseFabric,15));
            size = 3;
            health = 80 * size * size;
            consumes.items(ItemStack.with(Items.coal,2,Items.lead,3,Items.sand,5));
            consumes.power(0.8f);
            outputItem = new ItemStack(Items.pyratite,3);
        }};
        advancedBlastMixer = new GenericCrafter("advanced-blast-mixer"){{
            requirements(Category.crafting, ItemStack.with(Items.thorium,110,Items.silicon,50,Items.plastanium,60,Items.surgeAlloy,30));
            size = 3;
            health = 80 * size * size;
            consumes.items(ItemStack.with(Items.pyratite,2,Items.sporePod,4));
            consumes.power(1.2f);
            outputItem = new ItemStack(Items.blastCompound,3);
        }};
        gasolineBarrel = new LiquidConverter("gasoline-barrel"){{
            requirements(Category.crafting, ItemStack.with(Items.copper,90,Items.plastanium,55,Items.graphite,110,Items.silicon,35,Items.metaglass,70));
            size = 3;
            health = 90 * size * size;
            hasItems = false;
            craftTime = 360;
            liquidCapacity = 40;
            drawer = new DrawMixer();
            consumes.power(7.5f);
            consumes.liquid(Liquids.oil,0.35f);
            outputLiquid = new LiquidStack(GRLiquids.gasoline,0.2f);
            updateEffect = Fx.magmasmoke;
        }};
        cryofluidChamber = new LiquidConverter("cryofluid-chamber"){{
            requirements(Category.crafting, ItemStack.with(Items.copper,70,GRItems.magneturn,40,Items.graphite,120,Items.silicon,35,Items.metaglass,70));
            drawer = new DrawMixer();
            size = 2;
            craftTime = 140;
            health = 70 * size * size;
            liquidCapacity = 30;
            consumes.power(8.5f);
            consumes.item(Items.titanium,1);
            consumes.liquid(Liquids.water,0.6f);
            outputLiquid = new LiquidStack(Liquids.cryofluid,27);
        }};
        flammableLiquidChamber = new LiquidConverter("flammable-liquid-chamber"){{
            requirements(Category.crafting, ItemStack.with(Items.copper,50,Items.lead,20,Items.thorium,40,Items.silicon,60));
            size = 2;
            health = 70 * size * size;
            craftTime = 140;
            drawer = new DrawMixer();
            liquidCapacity = 30;
            itemCapacity = 15;
            consumes.power(7.5f);
            consumes.item(Items.pyratite,1);
            consumes.liquid(Liquids.water,0.2f);
            outputLiquid = new LiquidStack(GRLiquids.flammableLiquid,4f);
        }};
        phaseCaldron = new LiquidConverter("phase-caldron"){{
            requirements(Category.crafting, ItemStack.with(Items.titanium,75,Items.metaglass,20,Items.thorium,60,Items.silicon,160));
            size = 2;
            health = 60 * size * size;
            drawer = new DrawMixer();
            craftTime = 90;
            liquidCapacity = 20;
            itemCapacity = 15;
            updateEffect = Fx.magmasmoke;
            consumes.power(6.5f);
            consumes.item(Items.phaseFabric,1);
            consumes.liquid(Liquids.cryofluid,0.15f);
            outputLiquid = new LiquidStack(GRLiquids.phaseLiquid,0.15f);
        }};
        molecularConverter = new GenericCrafter("molecular-converter"){{
            requirements(Category.crafting, ItemStack.with(Items.lead,175,Items.titanium,120,Items.silicon,80));
            size = 3;
            health = 70 * size * size;
            craftTime = 72;
            itemCapacity = 20;
            updateEffect = Fx.smeltsmoke;
            ambientSound = Sounds.cutter;
            ambientSoundVolume = 0.25f;
            consumes.power(2.4f);
            consumes.items(ItemStack.with(Items.titanium,1,Items.lead,1,Items.silicon,2));
            outputItem = new ItemStack(GRItems.gravitium,3);
        }};
        molecularReconstructor = new GenericCrafter("molecular-reconstructor"){{
            requirements(Category.crafting, ItemStack.with(Items.lead,240,Items.titanium,180,Items.silicon,120,Items.surgeAlloy,45));
            size = 4;
            health = 80 * size * size;
            craftTime = 145;
            itemCapacity = 32;
            updateEffect = Fx.smeltsmoke;
            ambientSound = Sounds.cutter;
            ambientSoundVolume = 0.35f;
            consumes.power(6.5f);
            consumes.items(ItemStack.with(Items.titanium,3,Items.lead,3,Items.silicon,4));
            consumes.liquid(Liquids.water,0.3f);
            outputItem = new ItemStack(GRItems.gravitium,12);
        }};
        magneturnSmelter = new AttributeCrafter("magneturn-smelter"){{
            requirements(Category.crafting, ItemStack.with(GRItems.gravitium,150,Items.silicon,175,Items.titanium,120,Items.surgeAlloy,55));
            size = 3;
            health = 90 * size * size;
            attribute = Attribute.heat;
            baseEfficiency = 1f;
            craftTime = 140;
            liquidCapacity = 30;
            updateEffect = Fx.smeltsmoke;
            //ambientSound = ModSounds.magneturnWork;
            consumes.power(7.5f);
            consumes.items(ItemStack.with(GRItems.gravitium,1,Items.surgeAlloy,3));
            consumes.liquid(Liquids.cryofluid,0.1f);
            outputItem = new ItemStack(GRItems.magneturn,2);
        }};
        electroSmelter = new GenericCrafter("electro-smelter"){{
            requirements(Category.crafting, ItemStack.with(Items.copper,400,Items.titanium,225,Items.plastanium,80,Items.surgeAlloy,120,GRItems.magneturn,45));
            size = 3;
            health = 120 * size * size;
            craftTime = 600;
            itemCapacity = 30;
            updateEffect = Fx.smeltsmoke;
            ambientSound = Sounds.smelter;
            ambientSoundVolume = 0.35f;
            consumes.power(14.5f);
            consumes.items(ItemStack.with(Items.copper,3,Items.lead,2,Items.silicon,1,Items.surgeAlloy,2));
            consumes.liquid(Liquids.cryofluid,0.15f);
            outputItem = new ItemStack(GRItems.electroBrass,3);
        }};
        molecularPhaseWeaver = new GenericCrafter("molecular-phase-weaver"){{
            requirements(Category.crafting, ItemStack.with(Items.silicon,220,Items.lead,140,Items.thorium,95,GRItems.electroBrass,70));
            size = 3;
            health = 90 * size * size;
            drawer = new DrawWeave();
            itemCapacity = 35;
            craftTime = 120;
            craftEffect = Fx.smeltsmoke;
            consumes.power(8.5f);
            consumes.items(ItemStack.with(Items.thorium,5,Items.sand,12));
            consumes.liquid(Liquids.cryofluid,0.08f);
            outputItem = new ItemStack(Items.phaseFabric,3);
        }};
        plastaniumMolecularPress = new GenericCrafter("plastanium-molecular-press"){{
            requirements(Category.crafting, ItemStack.with(Items.lead,225,Items.graphite,95,GRItems.magneturn,60,Items.silicon,120));
            size = 3;
            health = 70 * size * size;
            craftTime = 90;
            liquidCapacity = 40;
            itemCapacity = 25;
            updateEffect = Fx.plasticburn;
            consumes.power(4f);
            consumes.item(Items.titanium,4);
            consumes.liquid(Liquids.oil,0.4f);
            outputItem = new ItemStack(Items.plastanium,3);
        }};
        //endregion
        //region Defence
        gravitiumWall = new Wall("gravitium-wall"){{
            requirements(Category.defense, ItemStack.with(GRItems.gravitium, 5,Items.plastanium,1));
            size = 1;
            health = gravitiumDefence * size * size;
            insulated = true;
            absorbLasers = true;
        }};
        gravitiumWallLarge = new Wall("gravitium-wall-large"){{
            requirements(Category.defense, ItemStack.with(GRItems.gravitium, 20, Items.plastanium, 4));
            size = 2;
            health = gravitiumDefence * size * size;
            insulated = true;
            absorbLasers = true;
        }};
        magneturnWall = new Wall("magneturn-wall"){{
            requirements(Category.defense, ItemStack.with(GRItems.magneturn, 4, Items.surgeAlloy, 1));
            size = 1;
            health = magneturnDefence * size * size;
        }};
        magneturnWallLarge = new Wall("magneturn-wall-large"){{
            requirements(Category.defense, ItemStack.with(GRItems.magneturn, 20, Items.surgeAlloy, 4));
            size = 2;
            health = magneturnDefence * size * size;
        }};
        hyperMender = new MendProjector("hyper-mender"){{
            requirements(Category.effect, ItemStack.with(Items.lead, 170, Items.silicon, 220, Items.thorium, 120, Items.silicon, 120, Items.phaseFabric, 50, GRItems.gravitium, 35));


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
            requirements(Category.effect, ItemStack.with(Items.lead, 240, Items.silicon, 220, Items.thorium, 190, Items.phaseFabric, 220, GRItems.electroBrass, 90));
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
        //endregion
        //region Distribution
        magneturnConveyor = new ArmoredConveyor("magneturn-conveyor"){{
            requirements(Category.distribution, ItemStack.with(Items.thorium, 1, GRItems.magneturn, 1));
            health = 240;
            displayedSpeed = 25.45f;
            speed = 0.22f;
        }};
        electroConveyor = new StackConveyor("electro-conveyor"){{
            requirements(Category.distribution, ItemStack.with(Items.thorium, 1, Items.silicon, 1, GRItems.electroBrass, 1));
            speed = 0.1f;
            itemCapacity = 15;
            splitOut = true;
            recharge = 2;
            loadEffect = Fx.plasticburn;
            unloadEffect = Fx.plasticburn;
        }};
        colossalDriver = new MassDriver("colossal-driver"){{
            requirements(Category.distribution, ItemStack.with(Items.lead, 225, Items.titanium, 225, Items.thorium, 75, GRItems.gravitium, 25, Items.silicon, 75));
            size = 4;
            health = 70 * size * size;
            itemCapacity = 240;
            range = 520;
            rotateSpeed = 0.1f;
            translation = 7f;
            minDistribute = 40;
            knockback = 4;
            reloadTime = 260;
            bulletSpeed = 12.5f;
            bulletLifetime = 600;
            consumes.power(4f);
        }};
        colossalForceProjector = new ForceProjector("colossal-force-projector"){{
            requirements(Category.effect, with(Items.lead, 650, Items.plastanium, 450, GRItems.electroBrass, 320, Items.phaseFabric, 120));
            size = 5;
            phaseRadiusBoost = 120f;
            radius = 160f;
            shieldHealth = 2500f;
            cooldownNormal = 1f;
            cooldownLiquid = 0.85f;
            cooldownBrokenBase = 0.65f;
            itemCapacity = 45;
            consumes.item(Items.phaseFabric).boost();
            consumes.power(25f);
        }};
        //endregion
        //region Power
        multiConnector = new PowerNode("multi-connector"){{
            requirements(Category.power, ItemStack.with(Items.lead, 35, Items.titanium, 30, Items.silicon, 100, GRItems.gravitium, 15));
            size = 3;
            health = 40 * size * size;
            maxNodes = 35;
            laserRange = 22.5f;
        }};
        batteryBig = new Battery("battery-big"){{
            requirements(Category.power, ItemStack.with(Items.copper,15,Items.lead,30,Items.silicon,5));
            fullLightColor = Color.valueOf("fb9567");
            emptyLightColor = Color.valueOf("f8c266");
            consumes.powerBuffered(22000);
            size = 2;
            health = 450;
        }};
        gasolineGenerator = new SingleTypeGenerator("gasoline-generator"){{
            requirements(Category.power,ItemStack.with(Items.copper, 90, Items.lead, 70, Items.metaglass, 100, Items.silicon, 25));
            size = 3;
            health = 70 * size * size;
            itemCapacity = 20;
            liquidCapacity = 20;
            itemDuration = 90;
            powerProduction = 26;
            consumes.item(Items.blastCompound,1);
            consumes.liquid(GRLiquids.gasoline,0.12f);
        }};
        sporeGenerator = new ThermalGenerator("spore-generator"){{
            requirements(Category.power, ItemStack.with(Items.lead, 65, Items.plastanium, 40, Items.graphite, 20, Items.silicon, 90));
            size = 2;
            placeableLiquid = true;
            powerProduction = 2.5f;
            generateEffect = Fx.fuelburn;
            attribute = Attribute.spores;
            ambientSound = Sounds.respawn;
            ambientSoundVolume = 0.4f;
        }};
        solarArray = new SolarGenerator("solar-array"){{
            requirements(Category.power, ItemStack.with(Items.lead, 450, Items.titanium, 320, Items.plastanium, 180, Items.phaseFabric, 80));
            size = 5;
            health = 90 * size * size;
            powerProduction = 15;
        }};
        //endregion
        //region Drills
        deepOilPump = new SolidPump("deep-oil-pump"){{
            requirements(Category.production, ItemStack.with(Items.lead, 180, Items.titanium, 25,Items.graphite, 120, Items.silicon, 65, Items.plastanium, 90));
            size = 3;
            health = 90 * size * size;
            consumes.power(7.5f);
            liquidCapacity = 72;
            pumpAmount = 0.4f;
            result = Liquids.oil;
            rotateSpeed = 1.8f;
            baseEfficiency = 0.75f;
            attribute = Attribute.spores;
            updateEffect = Fx.pulverize;
        }};
        overdriveDrill = new OverdriveDrill("overdrive-drill"){{
            requirements(Category.production, ItemStack.with(Items.titanium, 90, Items.thorium, 75, Items.plastanium, 55, GRItems.gravitium, 55, Items.silicon, 120));
            size = 4;
            tier = 4;
            health = 90 * size * size;
            drawRim = true;
            drillTime = 187;
            itemCapacity = 20;
            liquidCapacity = 20;
            liquidBoostIntensity = 2;
            warmupSpeed = 0.01f;
            rotateSpeed = 7;
            consumes.power(3.5f);
            consumes.liquid(Liquids.water,0.18f).boost();
        }};
        //endregion
        //region Storages
        molecularCore = new CoreBlock("molecular-core"){{
            requirements(Category.effect, ItemStack.with(Items.copper,12000,Items.lead,12000,GRItems.gravitium,6000,Items.thorium,8000,Items.silicon,10000,Items.surgeAlloy,3000));
            this.unitType = GRUnitTypes.release;
            this.health = 8600;
            this.itemCapacity = 19000;
            this.size = 6;
            //this.thrusterLength = 8.5F; WTF
            this.unitCapModifier = 34;
            this.researchCostMultiplier = 0.2F;
        }};
        coliseumCore = new CoreBlock("coliseum-core"){{
            requirements(Category.effect, ItemStack.with(Items.copper,18000,Items.lead,14000,GRItems.gravitium,9000,Items.thorium,8000,Items.silicon,14000,Items.surgeAlloy,7000));
            this.unitType = GRUnitTypes.update;
            this.health = 11000;
            this.itemCapacity = 25000;
            this.size = 7;
            this.unitCapModifier = 48;
            this.researchCostMultiplier = 0.2F;
        }};
        //endregion
        //region Turrets
        destiny = new PowerTurret("destiny"){{
            requirements(Category.turret, ItemStack.with(Items.lead,150,Items.graphite,75,Items.silicon,70,GRItems.gravitium,25));
            health = 1340;
            size = 2;
            reloadTime = 20;
            inaccuracy = 2;
            range = 180;
            powerUse = 7;
            shootType = new LaserBulletType(75){{
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
                status = GRStatusEffects.broken;
                damage = 59;
            }};
            shootSound = Sounds.spark;
            shootEffect = Fx.redgeneratespark;
            targetAir = false;
            targetGround = true;
            coolantUsage = 0.065f;
        }};
        fierySpray = new LiquidTurret("fiery-spray"){{
            requirements(Category.turret, ItemStack.with(Items.lead,150,Items.graphite,75,Items.silicon,70, GRItems.gravitium,25));
            size = 3;
            health = 1200;
            ammoTypes = ObjectMap.of(
                    GRLiquids.gasoline, new LiquidBulletType(){{
                        lifetime = 35;
                        speed = 6;
                        knockback = 1.5f;
                        puddleSize = 7;
                        orbSize = 4;
                        drag = 0.001f;
                        ammoMultiplier = 0.2f;
                        statusDuration = 240;
                        damage = 1.5f;
                        liquid = GRLiquids.gasoline;
                    }},
                    GRLiquids.flammableLiquid, new LiquidBulletType(){{
                        lifetime = 35;
                        speed = 6;
                        knockback = 1.5f;
                        puddleSize = 9;
                        orbSize = 5;
                        drag = 0.001f;
                        ammoMultiplier = 0.3f;
                        statusDuration = 240;
                        damage = 1.2f;
                        liquid = GRLiquids.flammableLiquid;
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
            requirements(Category.turret, ItemStack.with(Items.graphite,220,Items.thorium,230,GRItems.gravitium,180,Items.surgeAlloy,120,GRItems.magneturn,120));
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
                    GRItems.gravitium, new MissileBulletType(){{
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
                    GRItems.magneturn, new MissileBulletType(){{
                        backColor = Color.valueOf("9171a7ff");
                        frontColor = Color.valueOf("ad8dc2ff");
                        splashDamageRadius = 62;
                        splashDamage = 29;
                        damage = 40;
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
                            damage = 24;
                            width = 8;
                            height = 11.7f;
                            hitSound = Sounds.explosion;
                            smokeEffect = Fx.shootSmallSmoke;
                            homingPower = 0.8f;
                            buildingDamageMultiplier = 0.4f;
                        }};
                        status = GRStatusEffects.broken;
                    }}
            );
            coolantUsage = 0.075f;
        }};
        phaseFuse = new ItemTurret("phase-fuse"){{
            requirements(Category.turret,ItemStack.with(Items.copper,220,Items.silicon,420,Items.plastanium,120,Items.thorium,65,GRItems.magneturn,70));
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
            requirements(Category.turret, ItemStack.with(Items.copper,220,Items.titanium,140,Items.plastanium,90,GRItems.electroBrass,80));
            size = 3;
            health = 780;
            reloadTime = 12;
            inaccuracy = 3.5f;
            range = 175;
            shots = 3;
            shootType = new LightningBulletType(){{
                lightningColor = Color.valueOf("a4ded0");
                speed = 2;
                lifetime = 50;
                damage = 26;
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
            requirements(Category.turret, ItemStack.with(GRItems.magneturn,220,Items.phaseFabric,120,Items.surgeAlloy,220,GRItems.electroBrass,80));
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
        //region Units
        repairLaser = new RepairPoint("repair-laser"){{
            requirements(Category.units, ItemStack.with(Items.thorium,120,Items.silicon,160,Items.plastanium,75,GRItems.gravitium,45));
            size = 3;
            health = 70 * size * size;
            powerUse = 2.5f;
            repairRadius = 275;
            repairSpeed = 3.5f;
        }};
        advancedNavalFactory = new UnitFactory("advanced-naval-factory"){{
            requirements(Category.units, ItemStack.with(Items.copper,200, Items.lead, 160, Items.silicon, 80));
            plans = Seq.with(
                    new UnitPlan(GRUnitTypes.coulomb, 60f * 75f, ItemStack.with(Items.silicon, 25, Items.metaglass, 30, GRItems.gravitium, 15))
            );
            size = 3;
            consumes.power(1.5f);
            floating = true;
        }};
        additiveReassembler = new Reconstructor("additive-reassembler"){{
            requirements(Category.units, ItemStack.with(Items.lead, 220, Items.titanium, 140, Items.thorium, 60, Items.silicon, 120));
            upgrades.addAll(
                new UnitType[]{GRUnitTypes.coulomb, GRUnitTypes.ampere}
            );
            consumes.items(with(Items.silicon, 50, Items.graphite, 20, Items.titanium, 30));
            consumes.power(4);
            constructTime = 60 * 15;
            size = 3;
        }};
        multiplicativeReassembler = new Reconstructor("multiplicative-reassembler"){{
            requirements(Category.units, ItemStack.with(Items.lead, 700, Items.silicon, 450, Items.titanium, 350, Items.thorium, 650, GRItems.gravitium, 210));
            upgrades.addAll(
                    new UnitType[]{GRUnitTypes.ampere, GRUnitTypes.joule}
            );
            consumes.power(7.5f);
            consumes.items(with(Items.silicon, 140, Items.titanium, 120, GRItems.gravitium, 20));
            constructTime = 60 * 40;
            size = 5;
        }};
        //endregion
        //region Light
        sunshine = new LightBlock("sunshine"){{
            requirements(Category.effect, ItemStack.with(GRItems.gravitium,15,Items.graphite,30,Items.silicon,20));
            size = 2;
            radius = 180;
            health = 60 * size * size;
            brightness = 0.9f;
            consumes.power(0.166667f);
        }};
        //endregion
    }
}
