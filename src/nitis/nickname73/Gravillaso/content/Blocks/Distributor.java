package nitis.nickname73.Gravillaso.content.Blocks;

import arc.Core;
import arc.graphics.Color;
import arc.graphics.g2d.Draw;
import arc.graphics.g2d.Lines;
import arc.input.InputDevice;
import arc.input.InputProcessor;
import arc.input.KeyCode;
import arc.math.Mathf;
import arc.scene.ui.layout.Scl;
import arc.struct.*;
import mindustry.content.*;
import mindustry.entities.*;
import mindustry.gen.*;
import mindustry.graphics.*;
import mindustry.logic.*;
import mindustry.type.ItemStack;
import mindustry.ui.Fonts;
import mindustry.world.*;
import mindustry.world.blocks.logic.LogicBlock;
import mindustry.world.consumers.*;
import mindustry.world.meta.*;
import nitis.nickname73.Gravillaso.Extra.BuildingTarget;
import nitis.nickname73.Gravillaso.Extra.ItemTarget;

import static mindustry.Vars.*;

public class Distributor extends Block{
    public float range = 80f;
    public float workSpeed = 1f;
    public boolean deleteWhenDestroy = true;
    public int linksAmount = 10;

    public Distributor(String name){
        super(name);
        consumes.power(1f);
        configurable = true;
        update = true;
        flags = EnumSet.of(BlockFlag.allLogic);
    }

    @Override
    public void init(){
        if(linksAmount <= 0){
            linksAmount = 1;
        }
        super.init();
    }

    @Override
    public void drawPlace(int x, int y, int rotation, boolean valid){
        super.drawPlace(x, y, rotation, valid);
        Drawf.dashCircle(x * tilesize + offset, y * tilesize + offset, range, Pal.placing);
    }

    @Override
    public void setStats(){
        super.setStats();
        stats.add(Stat.shootRange, range / tilesize, StatUnit.blocks);
        stats.add(Stat.damage, linksAmount, StatUnit.blocks);
    }

    public class DistributorBuild extends Building implements Ranged{ /*  Тут блок   */
        public boolean inExist = false,outExist = false;
        public int linkAmount = 0;
        public Seq<BuildingTarget> selected = new Seq<BuildingTarget>();
        public Seq<ItemTarget> itemTargets = new Seq<ItemTarget>();
        public Seq<ItemTarget> outputTargets = new Seq<ItemTarget>();

        @Override
        public float range(){
            return range;
        }
        public float realRange(){
            return range/8;
        }

        public void drawConfigure(){
            Draw.color(Pal.reactorPurple);
            Drawf.dashCircle(x, y, range, team.color);
            Lines.stroke(1.0F);
            Lines.square(this.x, this.y, (float)(this.block.size * 8) / 2.0F + 1.0F);
            for (BuildingTarget target : selected){
                Draw.color(Pal.remove);
                if(target.getter){
                    Draw.color(Pal.accent);
                }
                if(target.sender){
                    Draw.color(Color.green);
                }
                if(target.getter && target.sender){
                    Draw.color(Pal.lightishGray);
                }
                Lines.square(target.x(), target.y(), (float)(target.block.size * 8) / 2.0F + 1.0F);
            }
            Draw.reset();
        }

        @Override
        public boolean onConfigureTileTapped(Building other){
            if(this == other){ //Нажал на себя
                deselect();
            }
            // Нажал не на себя
            if(this != other) if (other == null) {
                deselect();
            } else { // Если не пусто
                boolean del = false;
                for (BuildingTarget target : selected) {
                    if (target.target == other) { // Антивыбор
                        untagBuild(other);
                        del = true;
                    }
                }
                // Если ничего не убрали то добавляем + проверка на наполненость
                if (!del && linkAmount < linksAmount) {
                    if(inRange(other)){
                        tagBuild(other);
                    }
                }
            }
            return super.onConfigureTileTapped(other);
        }

        @Override
        public void drawSelect(){
            Drawf.dashCircle(x, y, range, team.color);
        }
        public boolean inRange(Tile tile){
            return Mathf.dst(x,y,tile.x,tile.y)/8f <= realRange();
        }
        public boolean inRange(Building building){
            return Mathf.dst(x,y,building.x,building.y)/8f <= realRange();
        }
        public boolean inRange(Unit unit){
            return Mathf.dst(x,y,unit.x,unit.y)/8f <= realRange();
        }
        @Override
        public void draw(){
            super.draw();
            var font = Fonts.outline;
            font.getData().setScale(0.3f / Scl.scl(1.f));
            font.setColor(Color.purple);
            font.draw(linkAmount + "/"+ linksAmount, x,y+ block.size * 8);
            font.getData().setScale(1f);
        }

        @Override
        public void update(){
            // Check if block destroy
            if(deleteWhenDestroy){
                for(BuildingTarget target : selected){
                    var build = world.build((int) (target.x()-0.5f), (int) (target.y()-0.5f));
                    for(BuildingTarget slot : selected){ //АААААААААААААААААААААААААААААААААААААА i do it
                        if(slot.target != world.build(slot.target.tileX(),slot.target.tileY())){
                            untagBuild(slot);
                        }
                    }
                }
            }
            //Translate from output to input
            inExist = false;
            outExist = false;
            for (BuildingTarget target : selected){
                if(target.getter){
                    inExist = true;
                }
                if(target.sender){
                    outExist = true;
                }
            }
            if(inExist){ // Create a list of inputs
                itemTargets.clear();
                for (BuildingTarget target:
                     selected) {
                    if(target.getter){
                        for (ItemStack itemStack:
                             target.consume.items) {
                            itemTargets.add(new ItemTarget(target.target,itemStack.item));
                        }
                    }
                }
            }
            if(outExist){ // Create a list of outputs
                outputTargets.clear();
                for (BuildingTarget target:
                        selected) {
                    if(target.sender){
                        outputTargets.add(new ItemTarget(target.target, target.output));
                    }
                }
            }
            //TODO: distribute from out to in;
        }
        public boolean tagBuild(Building building){
            return tagBuild(new BuildingTarget(building));
        }
        public boolean tagBuild(BuildingTarget buildingTarget){
            if(linkAmount >= linksAmount){
                return false;
            }else{
                selected.add(buildingTarget);
                linkAmount += 1;
                return true;
            }
        }
        public void untagBuild(Building building){
            untagBuild(new BuildingTarget(building));
        }
        public void untagBuild(BuildingTarget buildingTarget){
            selected.remove(buildingTarget);
            linkAmount -= 1;
        }
        public Seq<BuildingTarget> getSelected(){
            return selected;
        }

        @Override
        public BlockStatus status() {
            if(outExist && !inExist){
                return BlockStatus.noOutput;
            }
            if(!outExist && inExist){
                return BlockStatus.noInput;
            }
            if(!outExist && !inExist){
                return BlockStatus.noInput;
            }
            if(outExist && inExist){
                return BlockStatus.active;
            }
            return BlockStatus.active;
        }
    }
}
