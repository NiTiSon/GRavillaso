package nitis.nickname73.Gravillaso.Extra;

import mindustry.content.Items;
import mindustry.gen.Building;
import mindustry.type.Item;
import mindustry.type.ItemStack;
import mindustry.ui.dialogs.BaseDialog;
import mindustry.world.Block;
import mindustry.world.blocks.logic.LogicBlock;
import mindustry.world.blocks.production.GenericCrafter;
import mindustry.world.blocks.storage.StorageBlock;
import mindustry.world.consumers.ConsumeItems;

public class BuildingTarget extends DefaultTarget<Building>{
    public Building target;
    public Block block;
    public boolean getter = false;
    public boolean sender = false;
    public ConsumeItems consume;
    public Item output;

    public float x(){
        return target.x;
    }
    public float y(){
        return target.y;
    }

    public float blockSize(){
        return target.block.size;
    }
    public float realBlockSize(){
        return blockSize() * 8;
    }
    public BuildingTarget(Building building){
        target = building;
        block = target.block;
        if(target.block.outputsItems()){
            sender = true;
            if(target.block instanceof GenericCrafter){
                output = ((GenericCrafter) target.block).outputItem.item;
            }
        }
        if(target.shouldConsume()){
            getter = true;
            consume = target.block.consumes.getItem();
            //TODO: get consumers
        }
    }

    public Items[] getUsage(){
        return new Items[]{};
    }
}
