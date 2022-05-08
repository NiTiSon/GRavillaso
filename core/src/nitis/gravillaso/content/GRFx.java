package nitis.gravillaso.content;

import arc.graphics.*;
import arc.graphics.g2d.*;
import mindustry.entities.*;

import static arc.graphics.g2d.Draw.*;
import static arc.math.Angles.*;

public class GRFx {
    public static final Effect
    phaseBurn = new Effect(40, e -> {
        randLenVectors(e.id, 5, 3f + e.fin() * 5f, (x, y) -> {
            color(GRPal.phaseColor, Color.gray, e.fin());
            Fill.circle(e.x + x, e.y + y, e.fout());
        });
    });
}
