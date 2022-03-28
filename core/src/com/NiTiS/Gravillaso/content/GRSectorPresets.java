package com.NiTiS.Gravillaso.content;

import mindustry.content.Planets;
import mindustry.ctype.ContentList;
import mindustry.type.SectorPreset;

public class GRSectorPresets implements ContentList {

    public static SectorPreset driedFields, logicalCenter, causticGorge;

    @Override
    public void load() {
        driedFields = new SectorPreset("dried-fields", Planets.serpulo, 154){{
            captureWave = 45;
            difficulty = 9;
            startWaveTimeMultiplier = 2.5f;
        }};
        logicalCenter = new SectorPreset("logical-center", Planets.serpulo, 224){{
            captureWave = 50;
            difficulty = 7;
            startWaveTimeMultiplier = 3.5f;
        }};
        causticGorge = new SectorPreset("caustic-gorge", GRPlanets.gravillo, 47){{
            captureWave = 50;
            difficulty = 9;
        }};
    }
}