package z3roco01.endlessMusic.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.sound.MusicInstance;
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

    @Shadow public abstract void play(MusicInstance music);

    @Inject(at = @At("HEAD"), method = "tick", cancellable = true)
    public void tick(CallbackInfo ci) {
        MusicSound musicSound = this.client.getMusicInstance().music();


        if (this.current != null && musicSound != null) {
            if (!(musicSound.getSound().value()).id().equals(this.current.getId()) && musicSound.shouldReplaceCurrentMusic()) {
                this.client.getSoundManager().stop(this.current);
                this.timeUntilNextSong = EndlessMusic.getDelayTicks();
            }

            if (!this.client.getSoundManager().isPlaying(this.current)) {
                this.current = null;
                this.timeUntilNextSong = EndlessMusic.getDelayTicks();
            }
        }

        this.timeUntilNextSong = Math.min(this.timeUntilNextSong, (musicSound == null ? 0 : musicSound.getMaxDelay()) + EndlessMusic.getDelayTicks());
        if (this.timeUntilNextSong-- <= 0 && this.current == null && musicSound != null) {
            this.play(new MusicInstance(musicSound));
        }

        ci.cancel();
    }
}
