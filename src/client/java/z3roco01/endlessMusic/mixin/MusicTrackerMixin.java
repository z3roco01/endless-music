package z3roco01.endlessMusic.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.sound.MusicTracker;
import net.minecraft.client.sound.SoundInstance;
import net.minecraft.sound.MusicSound;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import z3roco01.endlessMusic.EndlessMusic;

@Mixin(MusicTracker.class)
public abstract class MusicTrackerMixin {
    @Shadow @Final private MinecraftClient client;
    @Shadow @Nullable private SoundInstance current;
    @Shadow private int timeUntilNextSong;
    @Shadow public abstract void play(MusicSound type);

    @Inject(at = @At("HEAD"), method = "tick", cancellable = true)
    public void tick(CallbackInfo ci) {
        MusicSound musicSound = this.client.getMusicType();
        if (this.current != null) {
            if (!(musicSound.getSound().value()).id().equals(this.current.getId()) && musicSound.shouldReplaceCurrentMusic()) {
                this.client.getSoundManager().stop(this.current);
                this.timeUntilNextSong = EndlessMusic.getDelaySeconds();
            }

            if (!this.client.getSoundManager().isPlaying(this.current)) {
                this.current = null;
                this.timeUntilNextSong = EndlessMusic.getDelaySeconds();
            }
        }

        this.timeUntilNextSong = Math.min(this.timeUntilNextSong, musicSound.getMaxDelay() + EndlessMusic.getDelaySeconds());
        if (this.timeUntilNextSong-- <= 0 && this.current == null) {
            this.play(musicSound);
        }

        ci.cancel();
    }
}
