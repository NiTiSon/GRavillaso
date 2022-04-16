package nitis.gravillaso.content;

import mindustry.world.meta.Stat;
import nitis.gravillaso.GRMod;

import java.lang.reflect.Constructor;

public class GRStat {
    static {
        Constructor<?>[] constructors = Stat.class.getConstructors();
        GRMod.log(constructors.length);
        for (Constructor<?> constr : constructors) {
            GRMod.log(constr);
        }
    }
}
