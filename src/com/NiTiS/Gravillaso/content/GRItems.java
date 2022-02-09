package com.NiTiS.Gravillaso.content;

import arc.graphics.Color;
import mindustry.ctype.ContentList;
import mindustry.type.Item;

public class GRItems implements ContentList {

    public static Item
    gravitium, magneturn, quartz, electroBrass;

    @Override
    public void load() {
        quartz = new Item("quartz"){{
            cost = 1;
            color = Color.valueOf("ebf6f7");
        }};
        gravitium = new Item("gravitium"){{
            radioactivity = 0.09f;
            cost = 2;
            charge = 0.04f;
            hardness = 5;
            color = Color.valueOf("6497ac");
        }};
        magneturn = new Item("magneturn"){{
            cost = 4;
            color = Color.valueOf("3d0a78");
        }};
        electroBrass = new Item("electro-brass"){{
            cost = 3;
            hardness = 7;
            charge = 0.85f;
            color = Color.valueOf("f9ffb4");
        }};
    }
}
