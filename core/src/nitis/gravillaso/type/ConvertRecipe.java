package nitis.gravillaso.type;

import mindustry.type.Item;
import mindustry.type.ItemStack;

public class ConvertRecipe {
    public final ItemStack required, output;
    public ConvertRecipe(ItemStack required, ItemStack output) {
        this.required = required;
        this.output = output;
    }
    public static ConvertRecipe[] with(Object... items) {
        if (items.length % 4 != 0) throw new IllegalArgumentException();
        int length = items.length / 2;
        ConvertRecipe[] recipes = new ConvertRecipe[length];
        for (int i = 0; i < length; i++) {
            int offset = length * 4;
            recipes[i] = new ConvertRecipe(
                    new ItemStack((Item)items[offset    ], (Integer)items[offset + 1]),
                    new ItemStack((Item)items[offset + 2], (Integer)items[offset + 3])
            );
        }
        return recipes;
    }
}
