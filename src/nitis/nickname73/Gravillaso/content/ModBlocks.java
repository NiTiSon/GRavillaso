package nitis.nickname73.Gravillaso.content;

import arc.graphics.Color;
import arc.struct.ObjectMap;
import arc.struct.Seq;
import mindustry.content.*;
import mindustry.ctype.ContentList;
import mindustry.entities.bullet.*;
import mindustry.gen.Sounds;
import mindustry.graphics.CacheLayer;
import mindustry.type.Category;
import mindustry.type.ItemStack;
import mindustry.type.LiquidStack;
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
import mindustry.world.blocks.power.*;
import mindustry.world.blocks.production.AttributeCrafter;
import mindustry.world.blocks.production.GenericCrafter;
import mindustry.world.blocks.production.LiquidConverter;
import mindustry.world.blocks.production.SolidPump;
import mindustry.world.blocks.storage.CoreBlock;
import mindustry.world.blocks.units.RepairPoint;
import mindustry.world.blocks.units.UnitFactory;
import mindustry.world.draw.DrawMixer;
import mindustry.world.draw.DrawWeave;
import mindustry.world.meta.Attribute;
import mindustry.world.meta.BuildVisibility;
import nitis.nickname73.Gravillaso.content.Blocks.OverdriveDrill;

public class ModBlocks implements ContentList {

    public static final int gravitiumDefence = 1280 ,magneturnDefence = 2300;
    public static final Color baseHealColor = Color.valueOf("84f490"), phaseHealColor = Color.valueOf("B5ffbd");

    public static Block
            //Environment
            redSand,redSandWall,burningGround,burningGroundWall,flammableLiquidBlock,metalFloorDamagedOrange,
    //Defence
    hyperMender,colossalHealingDome,gravitiumWall,gravitiumWallLarge,magneturnWall,magneturnWallLarge,
    //Distribution
    magneturnConveyor,electroConveyor,colossalDriver,
    //Drills
    deepOilPump,overdriveDrill,
    //Power
    gasolineGenerator,batteryBig,multiConnector,sporeGenerator,solarArray,sunshine,
    //Production
    advancedPyratiteMixer,advancedBlastMixer,gasolineBarrel,cryofluidChamber,flammableLiquidChamber,phaseCaldron,molecularConverter,molecularReconstructor,magneturnSmelter,electroSmelter,molecularPhaseWeaver,plastaniumMolecularPress,
    //Turrets
    destiny,arhiepiscop,fierySpray,renunciation,phaseFuse,slt,voltum,sunrise,
    //Storages
    molecularCore,coliseumCore,
    //Units
    repairLaser,advancedNavalFactory,additiveReassembler, multiplicativeReassembler,
    //Ores
    oreQuartz,
    //PlanetUnlocks
    _end;

    @Override
    public void load() {

    }
}
