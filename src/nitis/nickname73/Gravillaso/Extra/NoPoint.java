package nitis.nickname73.Gravillaso.Extra;

import mindustry.core.World;
import mindustry.gen.Building;
import mindustry.world.Tile;

public class NoPoint extends Point<Integer>{
    public NoPoint(int x, int y){
        this.X = x;
        this.Y = y;
    }
    public Tile getTile(World world) {
        return world.tile(this.X,this.Y);
    }
    public Building getBuild(World world) {
        return world.buildWorld(this.X * 8,this.Y * 8);
    }
    @Override
    public String toString() {
        return "TilePoint{"+this.X+":"+this.Y+"}";
    }
}
