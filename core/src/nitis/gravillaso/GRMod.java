package nitis.gravillaso;

import arc.Core;
import arc.Events;
import arc.graphics.Color;
import arc.util.Log;
import arc.util.Time;
import mindustry.Vars;
import mindustry.ctype.ContentList;
import mindustry.game.EventType;
import mindustry.gen.Icon;
import mindustry.ui.dialogs.BaseDialog;
import mma.MMAMod;

public class GRMod extends MMAMod {
    private static final boolean isBeta = true;
    private static final String GITHUB_LINK = "https://github.com/NickName73/Gravillaso";
    private static final String GR_TAG_COLORIZED = "NiTiS-[purple]GR[]";

    public GRMod(){
        super();
        GRVars.load();
        Events.on(EventType.ClientLoadEvent.class, e -> {
            Time.runTask(10f, () -> {
                if(isBeta & Core.settings.getBool("gravillaso-beta-show",true)){
                    BaseDialog dialog = new BaseDialog("Gravillaso Beta");
                    dialog.cont.add(Core.bundle.format("warning").replace('.','!'), Color.red).fontScale(1.15f).row();
                    dialog.cont.add(Core.bundle.format("@gravillaso-beta")).fontScale(1f).row();
                    dialog.cont.add().size(20f).row();
                    dialog.cont.add(Core.bundle.format("@gravillaso-beta.description")).row();
                    dialog.cont.add().size(20f).row();
                    dialog.cont.button(Core.bundle.format("ok"), dialog::hide)
                            .size(140f,60f).center().row();
                    dialog.cont.button(Core.bundle.format("@gravillaso-hide"), () ->{
                        dialog.hide();
                        Core.settings.put("gravillaso-beta-show",false);
                    }).size(140f,60f).row();
                    dialog.cont.button(Icon.github, () ->{
                        Core.app.openURI(GITHUB_LINK);
                    }).size(140f,60f);
                    dialog.show();
                }
            });
        });
    }
    @Override
    public void loadContent() {
        Log.info("GRavillaso loading");
        GRVars.modInfo = Vars.mods.getMod(this.getClass());
        int loaded = 0;
        for (ContentList list : GRVars.REQUIRED){
            try {
                list.load();
                Log.infoTag(GR_TAG_COLORIZED, list.getClass().getSimpleName() + " loaded!");
                loaded++;
            }catch (Exception ignore) {}
        }
        Log.infoTag(GR_TAG_COLORIZED, String.format("Loaded %s ContentList from %s", loaded, GRVars.REQUIRED.length));
    }
    public static void log(Object obj) {
        log(obj.toString());
    }
    public static void log(String text) {
        Log.infoTag(GR_TAG_COLORIZED, text);
    }
}
