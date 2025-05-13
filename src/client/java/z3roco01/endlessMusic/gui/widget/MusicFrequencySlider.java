package z3roco01.endlessMusic.gui.widget;

import net.minecraft.client.gui.widget.SliderWidget;
import net.minecraft.text.Text;
import z3roco01.endlessMusic.EndlessMusic;

public class MusicFrequencySlider extends SliderWidget {
    private static final Text PREFIX = Text.translatable("endless_music.options.slider.text");

    public MusicFrequencySlider(int x, int y, int width, int height) {
        super(x, y, width, height, PREFIX.copy().append(": " + EndlessMusic.config.get().delay),
                secondsAsValue(EndlessMusic.config.get().delay));
    }

    /**
     * turns the double value ( 0 to 1 ) into a seconds value ( 0 to 100 )
     */
    private int valueAsSeconds() {
        return (int)Math.round(value * 360.0);
    }

    /**
     * divides the passed seconds by the same amount its multiplied by
     */
    private static double secondsAsValue(int seconds) {
        return seconds/360.0;
    }

    @Override
    protected void updateMessage() {
        setMessage(PREFIX.copy().append(Text.of(": " + valueAsSeconds())));
    }

    @Override
    protected void applyValue() {
        EndlessMusic.config.get().delay = valueAsSeconds();
        EndlessMusic.config.save();
    }
}
