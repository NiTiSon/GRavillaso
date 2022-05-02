package nitis.gravillaso.world.blocks.gravity;

public interface GravityConsumer {
    void connectGravityProvider(GravityProvider provider);

    /** Get max gravity*/
    float maxGravity();
    /** Get minimal gravity (0 by default)*/
    default float minGravity() {
        return 0f;
    }
    /** Get current gravity*/
    float gravity();
    /** Calculate gravity*/
    default float gravityRatio() {
        return Math.min(Math.max(minGravity(), gravity()) / maxGravity(), 1f);
    }
}
