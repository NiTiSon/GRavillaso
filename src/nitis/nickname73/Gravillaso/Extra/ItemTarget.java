package nitis.nickname73.Gravillaso.Extra;

import arc.struct.Seq;
import mindustry.gen.Building;
import mindustry.type.Item;
import mindustry.type.ItemStack;

public class ItemTarget extends DoubleTarget<Building, Item>{
    public ItemTarget(Building building,Item item){
        this.item = item;
        this.target = building;
    }
}
