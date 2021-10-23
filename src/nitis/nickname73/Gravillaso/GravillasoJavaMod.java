package nitis.nickname73.Gravillaso;

import arc.*;
import arc.graphics.Color;
import arc.util.*;
import mindustry.Vars;
import mindustry.game.EventType.*;
import mindustry.gen.Icon;
import mindustry.mod.*;
import mindustry.mod.Mods.*;
import mindustry.ui.dialogs.BaseDialog;
import nitis.nickname73.Gravillaso.content.*;

import java.awt.*;

import static mindustry.Vars.loadSettings;
import static mindustry.Vars.mods;

public class GravillasoJavaMod extends Mod{

    public static final boolean isBeta = true;
    public static final boolean isDev = false;
    public static final String githubLink = "https://github.com/NickName73/Gravillaso";

    public GravillasoJavaMod(){
        if(isDev){
            Core.settings.put("gravillaso-beta-show", true);
        }
        Events.on(ClientLoadEvent.class, e -> {
            Time.runTask(10f, () -> {
                if(isBeta & Core.settings.getBool("gravillaso-beta-show",true)){
                    BaseDialog dialog = new BaseDialog("Gravillaso Beta");
                    dialog.cont.add(Core.bundle.format("warning").replace('.','!'), Color.red).fontScale(1.15f).row();
                    dialog.cont.add(Core.bundle.format("@gravillaso-beta")).fontScale(1f).row();
                    dialog.cont.add().size(20f).row();
                    dialog.cont.add(Core.bundle.format("@gravillaso-beta.description")).row();
                    dialog.cont.add().size(20f).row();
                    dialog.cont.button(Core.bundle.format("ok"), () ->{
                        dialog.hide();
                    }).size(140f,60f).center().row();
                    dialog.cont.button(Core.bundle.format("@gravillaso-hide"), () ->{
                        dialog.hide();
                        Core.settings.put("gravillaso-beta-show",false);
                    }).size(140f,60f).row();
                    dialog.cont.button(Icon.github, () ->{
                        Core.app.openURI(githubLink);
                    }).size(140f,60f);
                    dialog.show();
                }
            });
        });
    }

    @Override
    public void loadContent(){
        try{
            LoadedMod mod = mods.locateMod("gravillaso");
            mod.meta.displayName = "[#51158b]G[#4d2a97]r[#493fa3]a[#4554af]v[#4169bb]i[#3d7ec7]l[#3993d3]l[#35a8df]a[#31bdeb]s[#2dd2f7]o [#e5e5e5]Java";
            mod.meta.author = "[#ffea96]NickName73[]\nCREAsTIVE\n[maroon]Spriters:[]\nRadmir Yakupov\nCat fat\nVeniamin Katyrev\n[salmon]Additional Content from:[]\nTheSkyFather/Minerals";
        }catch (Exception e){
            Log.err("Meta...");
        }
        Log.info("Gravillaso Mod Loading");
        try {
            ModSounds.load();
        }catch (Exception e ){
            Log.err("Gravillaso Sounds loading failed");
            Log.err(e);
        }
        try {
            new ModLiquids().load();
        }catch (Exception e){
            Log.err("Gravillaso Liquid loading failed");
            Log.err(e);
        }
        try {
            new ModItems().load();
        }catch (Exception e){
            Log.err("Gravillaso Items loading failed");
            Log.err(e);
        }
        try {
            new ModStatuses().load();
        }catch (Exception e){
            Log.err("Gravillaso Statuses loading failed");
            Log.err(e);
        }
        try {
            new ModBlocks().load();
        }catch (Exception e){
            Log.err("Gravillaso Blocks loading failed");
            Log.err(e);
        }
        try {
            new ModPlanets().load();
        }catch (Exception e){
            Log.err("Gravillaso Planets loading failed");
            Log.err(e);
        }
        try {
            new ModSectorPresets().load();
        }catch (Exception e){
            Log.err("Gravillaso Sectors loading failed");
            Log.err(e);
        }
        try {
            new ModTechTree().load();
        }catch (Exception e){
            Log.err("Gravillaso TechTree loading failed");
            Log.err(e);
        }
    }

}
