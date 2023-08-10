package z3roco01.endlessMusic;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import net.fabricmc.api.ClientModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import z3roco01.endlessMusic.config.DelayConfig;

public class EndlessMusic implements ClientModInitializer {
	public static final String MOD_ID = "endless_music";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	public static DelayConfig config = null;
	@Override
	public void onInitializeClient() {
		LOGGER.info("Starting init !");

		AutoConfig.register(DelayConfig.class, GsonConfigSerializer::new);
		config = AutoConfig.getConfigHolder(DelayConfig.class).getConfig();

		LOGGER.info("Finished init !");
	}
}
