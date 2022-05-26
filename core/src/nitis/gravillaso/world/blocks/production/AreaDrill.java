package nitis.gravillaso.world.blocks.production;

import mindustry.Vars;
import mindustry.graphics.Drawf;

import mindustry.graphics.Pal;
import mindustry.world.blocks.production.Drill;
import nitis.gravillaso.content.GRPal;

/**Mine ore inside area */
public class AreaDrill extends Drill {
    protected int areaSize = 3;

    @Override
    public void drawPlace(int x, int y, int rotation, boolean valid) {
        super.drawPlace(x, y, rotation, valid);

        Drawf.dashCircle(x * Vars.tilesize, y * Vars.tilesize, 40, GRPal.magneturnLight);
        Drawf.dashCircle(x * Vars.tilesize, y * Vars.tilesize, 44, Pal.accent);
    }

    public AreaDrill(String name) {
        super(name);
    }

    public class AreaDrillBuild extends DrillBuild {

    }
}
