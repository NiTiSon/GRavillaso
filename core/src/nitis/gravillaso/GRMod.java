package nitis.gravillaso;

import arc.util.Log;
import mindustry.Vars;
import mindustry.ctype.ContentList;
import mma.MMAMod;

public class GRMod extends MMAMod {

    public GRMod(){
        super();
        GRVars.load();
    }
    @Deprecated
    public static String fullName(String name){
        return GRVars.fullName(name);
    }

    @Override
    public void loadContent() {
        Log.info("GRavillaso loading");
        GRVars.modInfo = Vars.mods.getMod(this.getClass());
        int loaded = 0;
        for (ContentList list : GRVars.REQUIRED){
            try {
                list.load();
                Log.infoTag(GRVars.LOGTAG, list.getClass().getSimpleName() + " loaded!");
                loaded++;
            }catch (Exception exception){
                GRVars.logErr(exception);
            }
        }
        Log.infoTag(GRVars.LOGTAG, String.format("Loaded %s ContentList from %s", loaded, GRVars.REQUIRED.length));
    }
}
