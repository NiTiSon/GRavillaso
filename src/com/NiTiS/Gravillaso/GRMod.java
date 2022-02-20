package com.NiTiS.Gravillaso;

import arc.util.Log;
import com.NiTiS.Gravillaso.content.*;
import mindustry.Vars;
import mindustry.ctype.ContentList;
import mindustry.mod.Mod;
import mindustry.mod.Mods;

public class GRMod extends Mod {

    public static String fullName(String name){
        return "gravillaso-" + name;
    }

    private final static ContentList[] required = new ContentList[] {
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
    public void loadContent() {
        Mods.LoadedMod grmod = Vars.mods.getMod("gravillaso");
        Log.info("GRavillaso loading");
        int loaded = 0;
        for (ContentList list : required){
            try {
                list.load();
                Log.infoTag("GR", list.getClass().getSimpleName() + " loaded!");
                loaded++;
            }catch (Exception exception){
                Log.err(exception);
            }
        }
        Log.infoTag("GR", String.format("Loaded %s ContentList from %s", loaded, required.length));
    }
}
