package z3roco01.endlessMusic.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import z3roco01.endlessMusic.EndlessMusic;

@Config(name = EndlessMusic.MOD_ID + "Delay")
public class DelayConfig implements ConfigData {
    @ConfigEntry.Gui.Tooltip
    public int delay = 10;

    @ConfigEntry.Gui.Excluded
    InnerData excluded = new InnerData();

    static class InnerData {
        int delay = 10;
    }
}
