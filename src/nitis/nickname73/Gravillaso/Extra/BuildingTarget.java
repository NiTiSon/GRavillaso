package nitis.nickname73.Gravillaso.Extra;

import mindustry.content.Items;
import mindustry.gen.Building;
import mindustry.type.Item;
import mindustry.type.ItemStack;
import mindustry.world.Block;
import mindustry.world.blocks.logic.LogicBlock;
import mindustry.world.blocks.production.GenericCrafter;
import mindustry.world.consumers.ConsumeItems;

public class BuildingTarget extends DefaultTarget<Building>{
    public Building target;
    public int id;
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
    public ItemStack[] getStorage(){
        return null; //TODO
    }

    public BuildingTarget(Building building){
        target = building;
        block = target.block;
        id = target.id;
        if(block.outputsItems()){
            sender = true;
            if(block instanceof GenericCrafter){
                output = ((GenericCrafter) block).outputItem.item;
            }
        }
        if(block.acceptsItems){
            getter = true;
            consume = block.consumes.items();
        }
    }

    public Items[] getUsage(){
        return new Items[]{};
    }
}
