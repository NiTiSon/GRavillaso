package nitis.nickname73.Gravillaso.content;

import arc.Core;
import arc.audio.Sound;
import arc.struct.IntMap;
import arc.struct.ObjectIntMap;
import mindustry.gen.Sounds;

public class ModSounds {
    private static IntMap idToSound = new IntMap();
    private static ObjectIntMap soundToId = new ObjectIntMap();
    public static Sound magneturnWork = new Sound();

    public static int getSoundId(Sound sound) {
        return soundToId.get(sound, -1);
    }

    public static Sound getSound(int id) {
        return (Sound)idToSound.get(id, () -> {
            return Sounds.none;
        });
    }

    public static void load(){
        /*Core.assets.load("sounds/magneturnWork.ogg", Sound.class).loaded = (a) -> {
            magneturnWork = a;
            soundToId.put(a, 0);
            idToSound.put(0, a);
        };*/
    }
}
