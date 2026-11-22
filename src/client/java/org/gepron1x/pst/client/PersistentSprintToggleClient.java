package org.gepron1x.pst.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientLevelEvents;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientLifecycleEvents;
import net.minecraft.client.Options;

public class PersistentSprintToggleClient implements ClientModInitializer {

	public static PstConfig pstConfig;
	@Override
	public void onInitializeClient() {
		pstConfig = ConfigService.loadConfig();

		ClientLifecycleEvents.CLIENT_STARTED.register((client) -> {
			Options options = client.options;
			if(!options.toggleSprint().get()) return;
			if(pstConfig.isSprintingEnabled()) {
				if(!options.keySprint.isDown()) {
					options.keySprint.setDown(true);
				}
			}
		});

		ClientLevelEvents.AFTER_CLIENT_LEVEL_CHANGE.register((client, level) -> {
			ConfigService.saveConfig(pstConfig);
		});

		ClientLifecycleEvents.CLIENT_STOPPING.register((client) -> {
			ConfigService.saveConfig(pstConfig);
		});
	}
}