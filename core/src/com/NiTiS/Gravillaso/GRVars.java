package com.NiTiS.Gravillaso;

import arc.util.Log;
import com.NiTiS.Gravillaso.content.*;
import mindustry.ctype.ContentList;
import mma.ModVars;

public class GRVars extends ModVars {
    public final static String MODID = "gravillaso";
    public final static String LOGTAG = "GR";
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
    @Override
    protected void onLoad(Runnable runnable) {

    }

    @Override
    protected void showException(Throwable ex) {
        Log.errTag(LOGTAG, ex.getMessage());
    }

    @Override
    public ContentList[] getContentList() {
        return REQUIRED;
    }

    @Override
    public String getFullName(String name) {
        return MODID + "-" + name;
    }
}
