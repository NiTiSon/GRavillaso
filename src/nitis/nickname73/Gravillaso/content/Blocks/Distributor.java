package nitis.nickname73.Gravillaso.content.Blocks;

import arc.Core;
import arc.graphics.Color;
import arc.graphics.g2d.Draw;
import arc.graphics.g2d.Font;
import arc.graphics.g2d.Lines;
import arc.graphics.g2d.TextureRegion;
import arc.math.Mathf;
import arc.scene.ui.layout.Scl;
import arc.struct.*;
import arc.util.Structs;
import arc.util.io.Reads;
import arc.util.io.Writes;
import mindustry.gen.*;
import mindustry.graphics.*;
import mindustry.io.TypeIO;
import mindustry.logic.*;
import mindustry.type.ItemStack;
import mindustry.ui.Bar;
import mindustry.ui.Fonts;
import mindustry.ui.dialogs.BaseDialog;
import mindustry.world.*;
import mindustry.world.blocks.logic.LogicBlock;
import mindustry.world.meta.*;
import nitis.nickname73.Gravillaso.Extra.BuildingTarget;
import nitis.nickname73.Gravillaso.Extra.ItemTarget;
import nitis.nickname73.Gravillaso.Extra.NiTiSON;

import java.io.*;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.InflaterInputStream;

import static mindustry.Vars.*;

public class Distributor extends Block{
    public TextureRegion topRegion = Core.atlas.find(this.name +"-top");
    public float range = 80f;
    @Deprecated
    public float workSpeed = 1f;
    public boolean deleteWhenDestroy = true;
    //Add rotator on top
    public boolean absurdRotator = false;
    public int linksAmount = 10;

    public Distributor(String name){
        super(name);
        solid = true;
        consumes.power(1f);
        configurable = true;
        saveConfig = true;
        update = true;
        noUpdateDisabled = true;
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

    public class DistributorBuild extends Building implements Ranged{
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
            if(this == other) { //Click
                deselect();
            }
            //Miss click
            if(this != other) if (other == null) {
                deselect();
            } else { // Not null
                boolean del = false;
                for (BuildingTarget target : selected) {
                    if (target.target == other) { // Unselect
                        untagBuild(target);
                        del = true;
                    }
                }
                // Select
                if (!del && linkAmount < linksAmount) {
                    if(inRange(other)){
                        tagBuild(other);
                    }
                }
            }
            return super.onConfigureTileTapped(other);
        }

        int r = 0;
        @Override
        public void drawSelect(){
            Drawf.dashCircle(x, y, range, team.color);
            if(absurdRotator){ Drawf.spinSprite(region,x,y,r); }
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
            //Draw.rect(topRegion, this.x, this.y, this.block.rotate ? this.rotdeg() : 0.0F);
            Font font = Fonts.outline;
            font.getData().setScale(0.3f / Scl.scl(1.f));
            font.setColor(Color.purple);
            font.draw(linkAmount + "/"+ linksAmount, x,y);
            font.getData().setScale(1f);
        }

        @Override
        public void update(){
            if(absurdRotator) {if(r > 360) { r = 0; } else{ r++; }}
            // Check if block destroy
            if(deleteWhenDestroy){
                for(BuildingTarget target : selected){
                    Building build = world.build((int) (target.x()-0.5f), (int) (target.y()-0.5f));
                    for(BuildingTarget slot : selected){
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
        public void setSelected(Seq<BuildingTarget> targets, boolean clear){
            if(clear) { selected.clear(); selected = targets; }
            else{
                for (BuildingTarget target:
                     targets) {
                    selected.add(target);
                }
            }
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
        @Override
        public void write(Writes write){
            super.write(write);
            write.str("d>");
            write.str(NiTiSON.parseToString(NiTiSON.parseToIntArray(selected)));
            write.str("d<");
        }
        @Override
        public void read(Reads read, byte revision){
            super.read(read, revision);
            read.str(); // d>
            String numbers = read.str();
            read.str(); // d<
            BaseDialog dialog = new BaseDialog("Save File: "+numbers);
            for (Integer number:
                 NiTiSON.parseToIntArray(numbers)) {
                dialog.cont.add(number+" Int").row();
            }
            for (BuildingTarget target:
                    NiTiSON.parseToBuildingTargetArray(numbers, world)){
                dialog.cont.add(target.block.name).row();
            }
            dialog.addCloseButton();
            dialog.show();
        }
    }
}
