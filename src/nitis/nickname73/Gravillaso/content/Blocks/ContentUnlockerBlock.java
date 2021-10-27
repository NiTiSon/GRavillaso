package nitis.nickname73.Gravillaso.content.Blocks;

import arc.Core;
import arc.Events;
import arc.scene.ui.layout.Table;
import mindustry.content.Blocks;
import mindustry.ctype.UnlockableContent;
import mindustry.game.EventType;
import mindustry.world.blocks.campaign.Accelerator;
import mindustry.world.meta.BuildVisibility;
import nitis.nickname73.Gravillaso.content.ModPlanets;

import static mindustry.Vars.state;
import static mindustry.Vars.ui;

public class ContentUnlockerBlock extends Accelerator {
    public UnlockableContent unlockableContent;
    public ContentUnlockerBlock(String name) {
        super(name);
    }

    public class ContentUnlockerBlockBuild extends  Accelerator.AcceleratorBuild{
        @Override
        public void buildConfiguration(Table table){
            deselect();


            if(block.buildVisibility == BuildVisibility.campaignOnly){
                if(!state.isCampaign() || !consValid()) return;
            }else{ if(!consValid()) return; }

            ModPlanets.gravillo.node().content.unlock(); //Unlock planet

            Events.fire(EventType.Trigger.acceleratorUse);
        }
    }
}
