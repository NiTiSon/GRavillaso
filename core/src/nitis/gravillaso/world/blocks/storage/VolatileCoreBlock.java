package nitis.gravillaso.world.blocks.storage;

import mindustry.game.Team;
import mindustry.gen.Unit;
import mindustry.world.Block;
import mindustry.world.Tile;
import mindustry.world.blocks.storage.CoreBlock;
import mindustry.world.meta.BlockGroup;

public class VolatileCoreBlock extends CoreBlock {
    public VolatileCoreBlock(String name) {
        super(name);
    }

    @Override
    public boolean canPlaceOn(Tile tile, Team team, int rotation){
        return canPlaceOn(tile, team);
    }
    @Override
    public boolean canBreak(Tile tile) {
        return true;
    }
    @Override
    public boolean canReplace(Block other) {

        return this.canReplace_Block(other) || (other instanceof VolatileCoreBlock && size > other.size);
    }
    protected boolean canReplace_Block(Block other) {
        if(other.alwaysReplace) return true;
        return other.replaceable && (other != this || rotate) && this.group != BlockGroup.none && other.group == this.group &&
                (size == other.size || (size >= other.size && ((subclass != null && subclass == other.subclass) || group.anyReplace)));
    }

    public class VolatileCoreBuild extends CoreBuild {
        @Override
        public boolean canControlSelect(Unit unit){
            return false;
        }
    }
}
