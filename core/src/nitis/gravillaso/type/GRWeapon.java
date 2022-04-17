package nitis.gravillaso.type;

import mindustry.type.Weapon;
import nitis.gravillaso.GRVars;

public class GRWeapon extends Weapon {

    public GRWeapon(String name){
        super(GRVars.fullName(name));
    }
}
