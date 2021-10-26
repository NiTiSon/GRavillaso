package nitis.nickname73.Gravillaso.content.Blocks;

import arc.graphics.Color;
import arc.graphics.g2d.Font;
import arc.scene.ui.layout.Scl;
import arc.struct.EnumSet;
import mindustry.gen.Building;
import mindustry.ui.Fonts;
import mindustry.world.Block;
import mindustry.world.meta.BlockFlag;
import mindustry.world.meta.Stat;

@Deprecated
public class DPSDisplay extends Block {
    public DPSDisplay(String name) {
        super(name);
        update = true;
        flags = EnumSet.of(BlockFlag.allLogic);
    }

    @Override
    public void setStats(){
        this.stats.remove(Stat.health);
    }

    public class DPSDisplayBuild extends Building {
        public float lastDamage = 0f;
        @Override
        public void damagePierce(float amount, boolean withEffect) {
            lastDamage = amount;
            this.damage(amount, withEffect);
        }

        @Override
        public void updateTile(){
            super.update();
            Font font = Fonts.outline;
            font.getData().setScale(0.3f / Scl.scl(1.f));
            font.setColor(Color.purple);
            font.draw(""+lastDamage+"", x,y+ block.size * 8);
            font.getData().setScale(1f);
        }
    }
}
