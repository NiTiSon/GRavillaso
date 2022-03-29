package com.NiTiS.Gravillaso;

import arc.util.Log;
import mindustry.ctype.ContentList;
import mma.MMAMod;

public class GRMod extends MMAMod {

    @Deprecated
    public static String fullName(String name){
        return GRVars.fullName(name);
    }

    @Override
    public void loadContent() {
        Log.info("GRavillaso loading");
        int loaded = 0;
        for (ContentList list : GRVars.REQUIRED){
            try {
                list.load();
                Log.infoTag(GRVars.LOGTAG, list.getClass().getSimpleName() + " loaded!");
                loaded++;
            }catch (Exception exception){
                Log.err(exception);
            }
        }
        Log.infoTag(GRVars.LOGTAG, String.format("Loaded %s ContentList from %s", loaded, GRVars.REQUIRED.length));
    }
}
