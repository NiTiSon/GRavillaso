package nitis.nickname73.Gravillaso.content;

import mindustry.content.UnitTypes;
import mindustry.ctype.ContentList;
import mindustry.type.UnitType;

public class ModUnitTypes implements ContentList {

    UnitType update;
    @Override
    public void load() {
        update = new UnitType("update"){{
            hitSize = 8.2f;
            health = 120;
            constructor = UnitTypes.alpha.constructor;
        }};
    }
}
