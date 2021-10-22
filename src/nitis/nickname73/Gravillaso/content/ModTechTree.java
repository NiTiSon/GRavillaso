package nitis.nickname73.Gravillaso.content;

import arc.Core;
import arc.struct.ObjectMap;
import arc.struct.Seq;
import arc.util.Nullable;
import mindustry.content.*;
import mindustry.ctype.ContentList;
import mindustry.ctype.UnlockableContent;
import mindustry.game.Objectives;
import mindustry.game.Objectives.*;
import mindustry.mod.Mod;
import mindustry.type.ItemStack;
import mindustry.type.Liquid;
import mindustry.type.SectorPreset;
import mindustry.world.blocks.defense.turrets.Turret;

import static mindustry.content.TechTree.*;

public class ModTechTree implements ContentList {
    static TechTree.TechNode context = null;

    @Override
    public void load(){
        margeNode(Items.titanium, ()->{
            nodeProduce(ModItems.gravitium, ()->{
                nodeProduce(ModItems.magneturn, ()->{
                    nodeProduce(ModItems.electroBrass);
                });
            });
        });
        margeNode(Liquids.oil, ()->{
            nodeProduce(ModLiquids.gasoline);
        });
        margeNode(Blocks.fuse, () ->{
            node(ModBlocks.voltum);
            node(ModBlocks.phaseFuse);
        });
        margeNode(Blocks.titaniumWall, () ->{
            node(ModBlocks.gravitiumWall, () ->{
                node(ModBlocks.gravitiumWallLarge);
                node(ModBlocks.magneturnWall, () ->{
                    node(ModBlocks.magneturnWallLarge);
                });
            });
        });
        margeNode(Blocks.swarmer, () ->{
            node(ModBlocks.renunciation);
        });
        margeNode(Blocks.tsunami, () ->{
            node(ModBlocks.fierySpray);
        });
        margeNode(Blocks.armoredConveyor, () ->{
            node(ModBlocks.magneturnConveyor);
        });
        margeNode(Blocks.plastaniumConveyor, () ->{
            node(ModBlocks.electroConveyor);
        });
        margeNode(Blocks.massDriver, () ->{
            node(ModBlocks.colossalDriver);
        });
        margeNode(Blocks.lancer, () ->{
            node(ModBlocks.destiny, () ->{
                node(ModBlocks.arhiepiscop, () ->{
                    node(ModBlocks.sunrise);
                });
            });
        });
        margeNode(Blocks.coreNucleus, () ->{
            node(ModBlocks.molecularCore, () ->{
               node(ModBlocks.coliseumCore);
            });
        });
        margeNode(Blocks.mendProjector, ()->{
            nodeSector(ModBlocks.hyperMender,SectorPresets.overgrowth, ()->{
                nodeSector(ModBlocks.colossalHealingDome, SectorPresets.desolateRift);
            });
        });
        margeNode(SectorPresets.coastline, ()->{
            nodeSector(ModSectorPresets.logicalCenter, SectorPresets.coastline);
            node(ModSectorPresets.driedFields, Seq.with(new Research(ModBlocks.phaseFuse), new Research(ModBlocks.colossalHealingDome), new SectorComplete(SectorPresets.coastline)), ()->{

            });
        });
        margeNode(Blocks.coreShard, () ->{
            nodeSector(ModPlanets.gravillo, SectorPresets.desolateRift, Seq.with(new Research(Blocks.interplanetaryAccelerator)) , () ->{
                //node(ModPlanets.sunCenter); In 2.4 or V8
                nodeSector(ModSectorPresets.causticGorge,SectorPresets.desolateRift, Seq.with(new Research(ModBlocks.colossalDriver), new Research(ModBlocks.molecularCore)), ()->{
                });
            });
        });
    }









    /*
    Methods
     */
    private static void margeNode(UnlockableContent parent, Runnable children){
        context = TechTree.all.find(t -> t.content == parent);
        children.run();
    }

    private static void node(UnlockableContent content, ItemStack[] requirements, Seq<Objective> objectives, Runnable children){
        TechNode node = new TechNode(context, content, requirements);
        if(objectives != null) node.objectives = objectives;

        TechNode prev = context;
        context = node;
        children.run();
        context = prev;
    }

    private static void node(UnlockableContent content, ItemStack[] requirements, Runnable children){
        node(content, requirements, null, children);
    }

    private static void node(UnlockableContent content, Seq<Objectives.Objective> objectives, Runnable children){
        node(content, content.researchRequirements(), objectives, children);
    }

    private static void node(UnlockableContent content, Runnable children){
        node(content, content.researchRequirements(), children);
    }

    private static void node(UnlockableContent block){
        node(block, () -> {});
    }

    private static void nodeProduce(UnlockableContent content, Seq<Objectives.Objective> objectives, Runnable children){
        node(content, content.researchRequirements(), objectives.and(new Produce(content)), children);
    }

    private static void nodeProduce(UnlockableContent content, Runnable children){
        nodeProduce(content, Seq.with(), children);
    }

    private static void nodeProduce(UnlockableContent content){
        nodeProduce(content, Seq.with(), () -> {});
    }
    private static void nodeSector(UnlockableContent content, SectorPreset zone, Runnable children){
        node(content, Seq.with(new SectorComplete(zone)), children);
    }
    private static void nodeSector(UnlockableContent content, SectorPreset zone){
        node(content, content.researchRequirements(), Seq.with(new SectorComplete(zone)), ()->{});
    }

    private static void nodeSector(UnlockableContent content, Runnable children){
        node(content, Seq.with(new SectorComplete(SectorPresets.groundZero)), children);
    }
    private static void nodeSector(UnlockableContent content, SectorPreset sector,Seq<Objectives.Objective> objectives, Runnable runnable){
        node(content, objectives.and( new SectorComplete(sector)),runnable);
    }
    private static void nodeSector(UnlockableContent content){
        nodeSector(content, () -> {});
    }
}
