package z3roco01.endlessMusic.mixin;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.option.GameOptionsScreen;
import net.minecraft.client.gui.screen.option.SoundOptionsScreen;
import net.minecraft.client.option.GameOptions;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import z3roco01.endlessMusic.gui.widget.MusicFrequencySlider;

@Mixin(SoundOptionsScreen.class)
public abstract class SoundOptionsScreenMixin extends GameOptionsScreen {
    public SoundOptionsScreenMixin(Screen parent, GameOptions gameOptions, Text title) {
        super(parent, gameOptions, title);
    }

    @Inject(method = "init", at = @At("TAIL"))
    public void addOptions(CallbackInfo ci) {
        this.addDrawableChild(new MusicFrequencySlider(this.width/2-155, 0, 310, 20));
    }
}
