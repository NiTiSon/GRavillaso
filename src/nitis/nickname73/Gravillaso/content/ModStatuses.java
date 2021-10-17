package nitis.nickname73.Gravillaso.content;

import mindustry.ctype.ContentList;
import mindustry.type.StatusEffect;

public class ModStatuses implements ContentList {

    public static StatusEffect broken,pressure;

    @Override
    public void load() {
        broken = new StatusEffect("broken"){{
            healthMultiplier = 0.8f;
        }};
        pressure = new StatusEffect("pressure"){{
           speedMultiplier = 0.75f;
           damageMultiplier = 0.75f;
           reloadMultiplier = 0.85f;
        }};
    }
}
