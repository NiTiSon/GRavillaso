package com.NiTiS.Gravillaso.type;

import com.NiTiS.Gravillaso.GRMod;
import mindustry.type.Weapon;

public class GRWeapon extends Weapon {

    public GRWeapon(String name){
        super(GRMod.fullName(name));
    }
}
