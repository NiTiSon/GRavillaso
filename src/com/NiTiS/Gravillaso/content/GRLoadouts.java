package com.NiTiS.Gravillaso.content;

import mindustry.Vars;
import mindustry.ctype.ContentList;
import mindustry.game.Schematic;
import mindustry.game.Schematics;

public class GRLoadouts implements ContentList {
    public static Schematic
    basicMolecular,
    basicColiseum;

    @Override
    public void load() {
        basicMolecular = Schematics.readBase64("bXNjaAF4nDWOQQ7CIBREf6EtRU2MS5ceoDtv4DGMC2x/DAmFhtIm3l7KRBIYIG/mDx3oJKn2ZmJqHiHynY4jL0O0c7LBE1HrzJvdQuL5knRJNhlv16kfgt/4GyKdZ8/rZJId+jFa5+j6iWbLF7OEfgqOh9WZmPnIOe1GZVU4igiIhNSQ5k+JCtx+yMxmaeAqW+RXJbKrQ0YxSBgkDDIjYndVMJfPFj1ahClMU0AUEAVLB7LbU4g0ammU0iivMVGjvN7JH+toI+A=");
        basicColiseum = Schematics.readBase64("bXNjaAF4nD2MXQrCMBCEJz8tNRXEN/EOffQEHkN8iDVIIE1Kmha8vZsumCV8+zMz6HFU0NFODs09ZXdD/3bLmP1cfIoA2mBfLiyQj6fCufhio1+nYUxxc9+UcZqjWydb/Di8sw8Bl0+2GzV2SaQKfnG7PDsKu9KHoKInGYqh//sKyY1mnYaQaNDVleKLYkmzDwTBkAzFIJ9Ay4a2GkTNYOz2rkqAA4FmwymGUwynmHr7Aa+GI0A=");

        Vars.schematics.add(basicMolecular);
        Vars.schematics.add(basicColiseum);
    }
}
