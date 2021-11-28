package nitis.nickname73.Gravillaso.content;

import arc.graphics.Color;
import mindustry.content.Planets;
import mindustry.ctype.ContentList;
import mindustry.graphics.Pal;
import mindustry.graphics.g3d.HexMesh;
import mindustry.maps.planet.SerpuloPlanetGenerator;
import mindustry.type.Planet;
import nitis.nickname73.Gravillaso.maps.generators.GravilloPlanetGenerator;

import javax.annotation.processing.SupportedAnnotationTypes;

public class ModPlanets implements ContentList {

    public static Planet gravillo;

    @Override
    public void load() {
        gravillo = new Planet("gravillo", Planets.sun, 3, 0.85f){{
            generator = new GravilloPlanetGenerator();
            hasAtmosphere = true;
            meshLoader = () -> new HexMesh(this, 6);
            atmosphereColor = Color.valueOf("63353cFF");
            atmosphereRadIn = 0.019f;
            atmosphereRadOut = 0.29f;
            alwaysUnlocked = true;
            landCloudColor = Pal.spore.cpy().a(0.5f);
            orbitRadius = 42.3f;
            startSector = 20;
            alwaysUnlocked = false;
        }};
    }
}
