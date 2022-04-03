package nitis.gravillaso.entity.bullet;

import mindustry.entities.bullet.BasicBulletType;
import nitis.gravillaso.GRVars;
import nitis.gravillaso.content.GRStatusEffects;

public class GraviBullet extends BasicBulletType {
    public GraviBullet(float damage) {
        this.damage = damage;
        this.status = GRStatusEffects.pressure;
        this.homingRange = 15f;
        this.homingPower = 0.7f;
        this.sprite = GRVars.fullName("gravi-bullet");
    }
}
