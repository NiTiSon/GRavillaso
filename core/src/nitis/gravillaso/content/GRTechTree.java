package nitis.gravillaso.content;

import arc.struct.Seq;
import mindustry.content.*;
import mindustry.ctype.ContentList;
import mindustry.ctype.UnlockableContent;
import mindustry.game.Objectives;
import mindustry.game.Objectives.Objective;
import mindustry.game.Objectives.Produce;
import mindustry.game.Objectives.Research;
import mindustry.game.Objectives.SectorComplete;
import mindustry.type.ItemStack;
import mindustry.type.SectorPreset;

public class GRTechTree implements ContentList {
    private static TechTree.TechNode context = null;
    @Override
    public void load(){
        margeNode(Blocks.illuminator, ()-> {
           node(GRBlocks.sunshine);
        });
        margeNode(Items.titanium, ()->{
            nodeProduce(GRItems.gravitium, ()->{
                nodeProduce(GRItems.magneturn, ()->{
                    nodeProduce(GRItems.electroBrass);
                });
            });
        });
        margeNode(Items.sand, ()->{
            nodeProduce(GRItems.quartz);
        });
        margeNode(Blocks.forceProjector, ()->{
            node(GRBlocks.colossalForceProjector);
            node(GRBlocks.gravityProjector);
        });
        margeNode(Blocks.navalFactory, () ->{
            node(GRBlocks.advancedNavalFactory, ()->{
                node(GRUnitTypes.coulomb, ()->{
                    node(GRUnitTypes.ampere, ()->{
                        node(GRUnitTypes.joule, ()->{
                            nodeProduce(GRUnitTypes.zap);
                        });
                    });
                });
                node(GRBlocks.additiveReassembler, ()->{
                   node(GRBlocks.multiplicativeReassembler);
                });
            });
        });
        Blocks.batteryLarge.node().remove();
        margeNode(Blocks.battery, () ->{
            node(GRBlocks.batteryBig, () ->{
                node(Blocks.batteryLarge);
            });
        });
        margeNode(Blocks.blastMixer, () ->{
            node(GRBlocks.advancedBlastMixer);
        });
        margeNode(Blocks.pyratiteMixer, () ->{
            node(GRBlocks.advancedPyratiteMixer);
        });
        margeNode(Blocks.cryofluidMixer, () ->{
            node(GRBlocks.gasolineBarrel);
            node(GRBlocks.cryofluidChamber);
            node(GRBlocks.flammableLiquidChamber);
        });
        margeNode(Blocks.kiln, () ->{
            node(GRBlocks.quartzKiln);
        });
        margeNode(Blocks.siliconSmelter, () -> {
            node(GRBlocks.molecularConverter, () ->{
                nodeSector(GRBlocks.molecularReconstructor, GRSectorPresets.logicalCenter);
            });
        });
        margeNode(Blocks.phaseWeaver, () ->{
            node(GRBlocks.phaseCaldron);
            node(GRBlocks.molecularPhaseWeaver);
        });
        margeNode(Blocks.plastaniumCompressor, () ->{
            node(GRBlocks.plastaniumMolecularPress);
        });
        margeNode(Blocks.surgeSmelter, () ->{
            node(GRBlocks.magneturnSmelter, () ->{
                nodeSector(GRBlocks.electroSmelter, GRSectorPresets.logicalCenter);
            });
        });
        margeNode(Blocks.oilExtractor, () ->{
            nodeSector(GRBlocks.deepOilPump,GRSectorPresets.logicalCenter);
        });
        margeNode(Blocks.differentialGenerator, () ->{
            node(GRBlocks.gasolineGenerator, Seq.with(new Produce(GRLiquids.gasoline)), ()->{
            });
        });
        margeNode(Blocks.powerNodeLarge, () ->{
            node(GRBlocks.multiConnector);
        });
        margeNode(Blocks.largeSolarPanel, () ->{
            node(GRBlocks.solarArray);
        });
        margeNode(Blocks.blastDrill, () ->{
            nodeSector(GRBlocks.overdriveDrill,GRSectorPresets.logicalCenter, Seq.with(new Research(Blocks.coreNucleus), new Research(GRBlocks.colossalDriver)));
        });
        margeNode(Liquids.oil, ()->{
            nodeProduce(GRLiquids.gasoline);
        });
        margeNode(Blocks.fuse, () ->{
            node(GRBlocks.voltum);
            node(GRBlocks.phaseFuse);
        });
        margeNode(Blocks.titaniumWall, () ->{
            node(GRBlocks.gravitiumWall, () ->{
                node(GRBlocks.gravitiumWallLarge);
                node(GRBlocks.magneturnWall, () ->{
                    node(GRBlocks.magneturnWallLarge);
                });
            });
        });
        margeNode(Blocks.spectre, () ->{
            //nodeSector(GRBlocks.saturn, GRSectorPresets.causticGorge); TODO: Saturn
        });
        margeNode(Blocks.swarmer, () ->{
            node(GRBlocks.renunciation);
        });
        margeNode(Blocks.tsunami, () ->{
            node(GRBlocks.fierySpray);
        });
        margeNode(Blocks.armoredConveyor, () ->{
            node(GRBlocks.magneturnConveyor);
        });
        margeNode(Blocks.plastaniumConveyor, () ->{
            node(GRBlocks.electroConveyor);
        });
        margeNode(Blocks.massDriver, () ->{
            node(GRBlocks.colossalDriver);
        });
        margeNode(Blocks.lancer, () ->{
            node(GRBlocks.destiny, () ->{
                node(GRBlocks.arhiepiscop, () ->{
                    node(GRBlocks.sunrise);
                });
                node(GRBlocks.slt);
            });
        });
        margeNode(Blocks.coreNucleus, () ->{
            node(GRBlocks.molecularCore, () ->{
                node(GRBlocks.coliseumCore);
            });
        });
        margeNode(Blocks.mendProjector, ()->{
            nodeSector(GRBlocks.hyperMender,SectorPresets.overgrowth, ()->{
                nodeSector(GRBlocks.colossalHealingDome, SectorPresets.desolateRift);
            });
        });
        margeNode(SectorPresets.coastline, ()->{
            nodeSector(GRSectorPresets.logicalCenter, SectorPresets.coastline);
            node(GRSectorPresets.driedFields, Seq.with(new Research(GRBlocks.phaseFuse), new Research(GRBlocks.colossalHealingDome), new SectorComplete(SectorPresets.coastline)), ()->{

            });
        });
        margeNode(Blocks.coreShard, () ->{
            nodeSector(GRPlanets.gravillo, SectorPresets.desolateRift, Seq.with(new Research(Blocks.interplanetaryAccelerator)) , () ->{
                nodeSector(GRSectorPresets.causticGorge, SectorPresets.desolateRift, Seq.with(new Research(GRBlocks.colossalDriver), new Research(GRBlocks.molecularCore)), ()->{
                });
            });
        });
        margeNode(SectorPresets.desolateRift, ()->{
            nodeSector(GRSectorPresets.frozenRiver, SectorPresets.desolateRift, Seq.with(new Research(GRBlocks.molecularCore), new Research(GRBlocks.gravityProjector), new Research(GRBlocks.slt)));
        });
        margeNode(Blocks.repairTurret, () ->{
            node(GRBlocks.repairLaser);
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
        TechTree.TechNode node = new TechTree.TechNode(context, content, requirements);
        if(objectives != null) node.objectives = objectives;

        TechTree.TechNode prev = context;
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
    private static void nodeSector(UnlockableContent content, SectorPreset sector,Seq<Objectives.Objective> objectives){
        node(content, objectives.and( new SectorComplete(sector)), () ->{});
    }
    private static void nodeSector(UnlockableContent content){
        nodeSector(content, () -> {});
    }
}