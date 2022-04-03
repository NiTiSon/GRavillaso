package nitis.gravillaso.world.modules;

import arc.util.io.Reads;
import arc.util.io.Writes;
import mindustry.world.modules.BlockModule;

public class GravityModule extends BlockModule {
    private int value = 0;
    public int maxValue = 160;
    public GravityModule(int value, int maxValue){
        this.value = value;
        this.maxValue = maxValue;
    }

    public static GravityModule create(Reads read) {
        return new GravityModule(read.i(), read.i());
    }
    public int getMaxValue() {
        return maxValue;
    }
    public int getValue() {
        return value;
    }
    public void setValue(int value) {
        this.value = Math.min(maxValue, value);
    }
    @Override
    public void write(Writes write){
        write.i(value);
        write.i(maxValue);
    }
    @Override
    public void read(Reads read){
        this.value = read.i();
        this.maxValue = read.i();
    }
    public float getRatio() {
        return (float)getValue() / (float)maxValue;
    }
}
