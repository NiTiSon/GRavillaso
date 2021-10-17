package nitis.nickname73.Gravillaso.content;

import arc.graphics.Color;
import mindustry.ctype.ContentList;
import mindustry.type.Item;

public class ModItems implements ContentList {

    public static final Color gravitiumColor = new Color(100, 151, 172);
    public static final Color magneturnColor = new Color(61, 10, 120);
    public static final Color quartzColor = new Color(235, 246, 247);
    public static final Color electroBrassColor = new Color(249, 255, 180);

    public static Item
        gravitium, magneturn, quartz, electroBrass;

    @Override
    public void load() {
        gravitium = new Item("gravitium"){{
           radioactivity = 0.09f;
           cost = 2;
           charge = 0.04f;
           hardness = 5;
           color = gravitiumColor;
        }};
        magneturn = new Item("magneturn"){{
           cost = 4;
           color = magneturnColor;
        }};
        quartz = new Item("quartz"){{
           cost = 1;
           color = quartzColor;
        }};
        electroBrass = new Item("electro-brass"){{
           cost = 3;
           hardness = 7;
           charge = 0.85f;
           color = electroBrassColor;
        }};
    }
}
