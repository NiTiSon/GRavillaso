package nitis.gravillaso.world.blocks.storage;

import mindustry.game.Team;
import mindustry.world.Tile;
import mindustry.world.blocks.storage.CoreBlock;

public class VolatileCoreBlock extends CoreBlock {
    public VolatileCoreBlock(String name) {
        super(name);
    }

    @Override
    public boolean canPlaceOn(Tile tile, Team team, int rotation){
        return canPlaceOn(tile, team);
    }

    public class VolatileCoreBuild extends CoreBuild {

    }
}
