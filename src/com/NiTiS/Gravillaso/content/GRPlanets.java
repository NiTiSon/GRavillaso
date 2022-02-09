package com.NiTiS.Gravillaso.content;

import arc.graphics.Color;
import mindustry.content.Planets;
import mindustry.ctype.ContentList;
import mindustry.graphics.Pal;
import mindustry.graphics.g3d.HexMesh;
import mindustry.graphics.g3d.SunMesh;
import mindustry.type.Planet;
import nitis.nickname73.Gravillaso.maps.generators.GravilloPlanetGenerator;

public class GRPlanets implements ContentList {

    public static Planet gravillo, solonite;

    @Override
    public void load() {
        solonite = new Planet("solonite", Planets.sun, 2f){{
            bloom = true;
            accessible = false;

            orbitRadius = 1000f;

            meshLoader = () -> new SunMesh(
                    this, 4,
                    5, 0.3, 1.7, 1.2, 1,
                    1.1f,
                    Color.valueOf("5fcce4"),
                    Color.valueOf("60beeb"),
                    Color.valueOf("61b3f1"),
                    Color.valueOf("54f7f7"),
                    Color.valueOf("66edc9")
            );
        }};
        gravillo = new Planet("gravillo", solonite, 3, 0.85f){{
            generator = new GravilloPlanetGenerator();
            hasAtmosphere = true;
            meshLoader = () -> new HexMesh(this, 6);
            atmosphereColor = Color.valueOf("63353cFF");
            atmosphereRadIn = 0.019f;
            atmosphereRadOut = 0.29f;
            alwaysUnlocked = true;
            landCloudColor = Pal.spore.cpy().a(0.5f);
            orbitRadius = 4f;
            startSector = 20;
            alwaysUnlocked = false;
        }};
    }
}
