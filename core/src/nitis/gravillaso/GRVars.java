package nitis.gravillaso;

import arc.util.Log;
import mindustry.ctype.ContentList;
import mma.ModVars;
import nitis.gravillaso.content.*;

public class GRVars extends ModVars {
    public final static String MODID = "gravillaso";
    public final static GRVars instance;
    public final static ContentList[] REQUIRED = new ContentList[] {
            new GRItems(),
            new GRStatusEffects(),
            new GRUnitTypes(),
            new GRLiquids(),
            new GRBlocks(),
            new GRPlanets(),
            new GRSectorPresets(),
            new GRTechTree(),
            new GRLoadouts(),
    };
    public static void load(){

    }
    @Override
    protected void onLoad(Runnable runnable) {

    }
    @Override
    protected void showException(Throwable ex) {
        Log.err(ex);
    }

    @Override
    public ContentList[] getContentList() {
        return REQUIRED;
    }

    @Override
    public String getFullName(String name) {
        return MODID + "-" + name;
    }

    static {
        instance = new GRVars();
    }
}
