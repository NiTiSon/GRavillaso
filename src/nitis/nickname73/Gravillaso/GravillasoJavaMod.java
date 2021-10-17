package nitis.nickname73.Gravillaso;

import arc.*;
import arc.util.*;
import mindustry.core.UI;
import mindustry.game.EventType.*;
import mindustry.mod.*;
import mindustry.ui.Menus;
import mindustry.ui.dialogs.*;
import nitis.nickname73.Gravillaso.content.*;

public class GravillasoJavaMod extends Mod{
    public GravillasoJavaMod(){
        Events.on(ClientLoadEvent.class, e -> {
            Time.runTask(10f, () -> {

            });
        });
    }

    @Override
    public void loadContent(){
        Log.info("Gravillaso Mod Loading");
        try {
            new ModItems().load();
            new ModBlocks().load();
            new ModStatuses().load();
            new ModPlanets().load();
            new ModSectorPresets().load();
            new ModTechTree().load();
        }catch (Exception e){
            Log.err(e);
        }
    }

}
