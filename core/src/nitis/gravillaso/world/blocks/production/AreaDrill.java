package nitis.gravillaso.world.blocks.production;

import mindustry.world.blocks.production.Drill;

/**Mine ore inside area */
public class AreaDrill extends Drill {
    protected int areaSize = 3;
    public AreaDrill(String name) {
        super(name);
    }
}
