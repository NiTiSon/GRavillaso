package nitis.nickname73.Gravillaso.content;

import mindustry.type.Weapon;
import nitis.nickname73.Gravillaso.GravillasoJavaMod;

public class ModWeapon extends Weapon {
    public  ModWeapon(String name){
        super(GravillasoJavaMod.fullName(name));
    }
}
