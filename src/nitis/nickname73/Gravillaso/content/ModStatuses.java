package nitis.nickname73.Gravillaso.content;

import arc.math.Mathf;
import mindustry.content.Fx;
import mindustry.content.StatusEffects;
import mindustry.ctype.ContentList;
import mindustry.type.StatusEffect;

public class ModStatuses implements ContentList {

    public static StatusEffect broken,pressure;

    @Override
    public void load() {
        broken = new StatusEffect("broken"){{
            healthMultiplier = 0.8f;
            transitionDamage = 15f;
            opposite(StatusEffects.shielded);
            affinity(StatusEffects.slow, ((unit, result, time) -> {
                unit.damagePierce(transitionDamage);
                result.set(broken, Math.min(time + result.time, 300f));
            }));
            affinity(StatusEffects.unmoving, ((unit, result, time) -> {
                unit.damagePierce(transitionDamage);
                result.set(broken, Math.min(time + result.time, 300f));
            }));
        }};
        pressure = new StatusEffect("pressure"){{
           speedMultiplier = 0.75f;
           damageMultiplier = 0.75f;
           reloadMultiplier = 0.85f;
           opposite(StatusEffects.overdrive,StatusEffects.overclock);
        }};
    }
}
