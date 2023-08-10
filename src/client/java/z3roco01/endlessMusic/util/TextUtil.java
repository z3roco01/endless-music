package z3roco01.endlessMusic.util;

import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import z3roco01.endlessMusic.EndlessMusic;

public class TextUtil {
    public static Text mkTransText(String id) {
        return Text.translatable("gui." + EndlessMusic.MOD_ID + "." + id);
    }
}
