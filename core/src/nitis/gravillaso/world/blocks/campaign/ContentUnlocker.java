package nitis.gravillaso.world.blocks.campaign;

import arc.Events;
import arc.scene.ui.layout.Table;
import mindustry.ctype.UnlockableContent;
import mindustry.game.EventType;
import mindustry.world.blocks.campaign.Accelerator;
import mindustry.world.meta.BuildVisibility;

import static mindustry.Vars.state;

public class ContentUnlocker extends Accelerator {
    public UnlockableContent unlockableContent;
    public ContentUnlocker(String name) {
        super(name);
        super.buildVisibility = BuildVisibility.campaignOnly;
    }

    public class ContentUnlockerBuild extends  Accelerator.AcceleratorBuild {
        @Override
        public void buildConfiguration(Table table){
            deselect();


            if(block.buildVisibility == BuildVisibility.campaignOnly){
                if(!state.isCampaign() || !consValid()) return;
            }else{ if(!consValid()) return; }

            unlockableContent.unlock(); //Unlock planet

            Events.fire(EventType.Trigger.acceleratorUse);
        }
    }
}
