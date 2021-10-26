package nitis.nickname73.Gravillaso.Extra;

import arc.graphics.Color;
import arc.struct.Seq;
import mindustry.core.World;
import mindustry.gen.Building;
import mindustry.ui.dialogs.BaseDialog;
import mindustry.world.blocks.logic.LogicBlock;

public class NiTiSON {

    public static String parseToString(Integer... numbers){
        String text = "";
        for (Integer num:
             numbers) {
            text += num +">";
        }
        return trimEnd(text,">");
    }

    public static Integer[] parseToIntArray(String array){
        String[] lines = array.split(">");
        Integer[] numbers = new Integer[lines.length];
        int index = 0;
        for (String line:
             lines) {
            try{numbers[index] = Integer.parseInt(line);}
            catch (Exception e){
                numbers[index] = 0;
            }
            index += 1;
        }
        return numbers;
    }

    public static Integer[] parseToIntArray(Seq<BuildingTarget> targets){
        Integer[] numbers = new Integer[targets.size * 2];
        int index = 0;
        for (BuildingTarget target:
                targets) {
            numbers[index] = target.target.tileX();
            numbers[index + 1] = target.target.tileY();
            index += 2;
        }
        return numbers;
    }

    public static Seq<BuildingTarget> parseToBuildingTargetArray(String array, World world){
        return parseToBuildingTargetArray(world, parseToIntArray(array));
    }

    public static Seq<BuildingTarget> parseToBuildingTargetArray(World world, Integer... array){
        Seq<BuildingTarget> targets = new Seq<BuildingTarget>();

        Integer[] xs = new Integer[array.length / 2];
        Integer[] ys = new Integer[array.length / 2];
        Integer constructIndex = 0;

        for(int i = 0; i < array.length; i++){
            if(i % 2 == 1){ // 1,3,5 numbers Xs
                xs[constructIndex] = array[i];
            }else{ // 2,4,6 numbers Ys
                ys[constructIndex] = array[i];
                targets.add(new BuildingTarget( world.tile( xs[constructIndex], ys[constructIndex] ).build ));
                constructIndex += 1;
            }
        }
        return targets;
    }

    public static String trimEnd( String s,  String suffix) {
        if (s.endsWith(suffix)) {
            return s.substring(0, s.length() - suffix.length());
        }
        return s;
    }
}
