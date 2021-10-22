package nitis.nickname73.Gravillaso.content;

import arc.graphics.Color;
import mindustry.content.StatusEffects;
import mindustry.ctype.ContentList;
import mindustry.type.Liquid;

public class ModLiquids implements ContentList {

    public static Liquid gasoline,flammableLiquid,phaseLiquid;

    @Override
    public void load() {
        gasoline = new Liquid("gasoline"){{
           color = Color.valueOf("dbd0af");
           barColor = Color.valueOf("dbd0af");
           temperature = 0.25f;
           flammability = 2.1f;
           heatCapacity = 0.2f;
           viscosity = 0.5f;
           explosiveness = 1.9f;
           effect = StatusEffects.tarred;
        }};
        flammableLiquid = new Liquid("flammable-liquid"){{
            color = Color.valueOf("d6a787");
            barColor = Color.valueOf("d6a787");
            temperature = 1.65f;
            flammability = 3.8f;
            heatCapacity = 0.8f;
            viscosity = 0.7f;
            explosiveness = 0.4f;
            effect = StatusEffects.burning;
        }};
        phaseLiquid = new Liquid("phase-liquid"){{
            color = Color.valueOf("ffd59eff");
            barColor = Color.valueOf("ffd59eff");
            temperature = 0.4f;
            flammability = 0;
            heatCapacity = 2;
            viscosity = 0.007f;
            explosiveness = 0;
            effect = StatusEffects.overclock;
        }};
    }
}
