package nitis.gravillaso.type;

import nitis.gravillaso.GRMod;
import mindustry.type.Weapon;

public class GRWeapon extends Weapon {

    public GRWeapon(String name){
        super(GRMod.fullName(name));
    }
}
