package z3roco01.endlessMusic.gui.widget;

import net.minecraft.client.gui.widget.SliderWidget;
import net.minecraft.text.Text;
import z3roco01.endlessMusic.EndlessMusic;

public class MusicFrequencySlider extends SliderWidget {
    public MusicFrequencySlider(int x, int y, int width, int height, Text text, double value) {
        super(x, y, width, height, text, value);
    }

    /**
     * turns the double value ( 0 to 1 ) into a seconds value ( 0 to 100 )
     */
    private int valueAsSeconds() {
        return (int)Math.round(value * 360.0);
    }

    @Override
    protected void updateMessage() {
        setMessage(Text.of("Seconds Between Tracks: " + valueAsSeconds()));
    }

    @Override
    protected void applyValue() {
        EndlessMusic.LOGGER.info(String.valueOf(valueAsSeconds()));
    }
}
