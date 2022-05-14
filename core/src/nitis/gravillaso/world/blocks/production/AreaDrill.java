package nitis.gravillaso.world.blocks.production;

import mindustry.Vars;
import mindustry.graphics.Drawf;

import mindustry.graphics.Pal;
import mindustry.world.blocks.production.Drill;
/**Mine ore inside area */
public class AreaDrill extends Drill {
    protected int areaSize = 3;

    @Override
    public void drawPlace(int x, int y, int rotation, boolean valid) {
        super.drawPlace(x, y, rotation, valid);
        Drawf.square(x, y, Vars.tilesize * (areaSize + size) , rotation * 90, Pal.place);
    }

    public AreaDrill(String name) {
        super(name);
    }

    public class AreaDrillBuild extends DrillBuild {

    }
}
