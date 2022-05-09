package nitis.gravillaso.world.blocks.gravity;

import arc.Core;
import arc.func.Func;
import arc.struct.Seq;
import mindustry.gen.Building;
import mindustry.ui.Bar;
import mindustry.world.blocks.production.GenericCrafter;
import nitis.gravillaso.content.GRPal;
import nitis.gravillaso.type.ConvertRecipe;

public class GASaC extends GenericCrafter {
    protected ConvertRecipe[] recipes;
    public float requiredGravity = 7200f;
    public float requiredGravityForProduction = requiredGravity * 0.5f;
    public float minGravity = 0f;

    public GASaC(String name) {
        super(name);
        consumesPower = true;
        update = true;
        solid = true;
    }

    @Override
    public void setBars() {
        super.setBars();
        bars.add("gravity-module", build -> {
            Func<Building,Float> rat = (building) -> { //TODO: Move to GRBars
                if (build instanceof GravityConsumer) {
                    return ((GravityConsumer)build).gravityRatio();
                }
                return 0f;
            };
            return new Bar(
                    () -> Core.bundle.get("bar.gravity-module").replace("%d", Math.round(rat.get(build) *100) + "" ).replace("%%", "%"),
                    () -> GRPal.magneturnLight,
                    () -> rat.get(build)
            );
        });
    }

    public class GASaCBuild extends GenericCrafterBuild implements GravityConsumer {
        private final Seq<GravityProvider> gravityProviders = new Seq<>();
        private float currentGravity = 0f;
        @Override
        public void connectGravityProvider(GravityProvider provider) {
            gravityProviders.add(provider);
        }

        @Override
        public void updateTile() {
            super.updateTile();
            currentGravity = 0;
            for (GravityProvider provider: gravityProviders) {
                currentGravity += provider.getGravity();
            }
            gravityProviders.clear();
        }

        @Override
        public float maxGravity() {
            return requiredGravity;
        }

        @Override
        public float minGravity() {
            return minGravity;
        }

        @Override
        public float gravity() {
            return currentGravity;
        }
    }
}
