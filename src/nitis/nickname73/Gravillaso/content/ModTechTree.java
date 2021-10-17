package nitis.nickname73.Gravillaso.content;

import arc.Core;
import arc.struct.ObjectMap;
import arc.struct.Seq;
import arc.util.Nullable;
import mindustry.content.Blocks;
import mindustry.content.Items;
import mindustry.content.SectorPresets;
import mindustry.content.TechTree;
import mindustry.ctype.ContentList;
import mindustry.ctype.UnlockableContent;
import mindustry.game.Objectives;
import mindustry.game.Objectives.*;
import mindustry.type.ItemStack;
import mindustry.type.SectorPreset;

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
        margeNode(Blocks.mendProjector, ()->{
            nodeSector(ModBlocks.hyperMender,SectorPresets.overgrowth, ()->{
                nodeSector(ModBlocks.colossalHealingDome, SectorPresets.desolateRift);
            });
        });
        margeNode(SectorPresets.coastline, ()->{
            nodeSector(ModSectorPresets.logicalCenter, SectorPresets.coastline);
        });
    }
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

    private static void nodeSector(UnlockableContent content){
        nodeSector(content, () -> {});
    }
}
