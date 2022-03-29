package gravillaso.tools;

import nitis.gravillaso.GRVars;
import mindustry.ctype.Content;
import mma.tools.ModImagePacker;

public class GRImagePacker extends ModImagePacker {
    @Override
    protected void start() throws Exception {
        new GRVars();
        super.start();
    }

    @Override
    protected void preCreatingContent() {
        super.preCreatingContent();
    }

    @Override
    protected void runGenerators() {
        new GRGenerators();
    }

    @Override
    protected void checkContent(Content content) {
        super.checkContent(content);
    }

    public static void main(String[] args) throws Exception {
        new GRImagePacker();
    }
}
