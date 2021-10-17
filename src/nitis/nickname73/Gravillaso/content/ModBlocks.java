package nitis.nickname73.Gravillaso.content;

import arc.graphics.Color;
import mindustry.content.Blocks;
import mindustry.content.Items;
import mindustry.ctype.ContentList;
import mindustry.type.Category;
import mindustry.type.ItemStack;
import mindustry.world.Block;
import mindustry.world.blocks.defense.MendProjector;
import mindustry.world.blocks.defense.Wall;
import mindustry.world.blocks.environment.Floor;
import mindustry.world.blocks.environment.OreBlock;
import mindustry.world.blocks.environment.StaticWall;
import nitis.nickname73.Gravillaso.content.ModItems;

public class ModBlocks implements ContentList {

    public static final int gravitiumDefence = 1280 ,magneturnDefence = 2300;
    public static final Color baseHealColor = Color.valueOf("84F490"), phaseHealColor = Color.valueOf("B5FFBD");

    public static Block
            //Environment
            redSand, redSandWall,
    //Defence
    hyperMender,colossalHealingDome,gravitiumWall,gravitiumWallLarge,magneturnWall,magneturnWallLarge,
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
