package com.keybinddisplay;

import com.google.inject.Provides;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.ChatMessageType;
import net.runelite.api.Client;
import net.runelite.api.GameState;
import net.runelite.api.events.GameStateChanged;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.overlay.OverlayManager;

@Slf4j
@PluginDescriptor(
	name = "Keybind Display"
)
public class KeybindDisplayPlugin extends Plugin
{
	@Inject
	private Client client;

	@Inject
	private KeybindDisplayConfig config;

	@Inject
	private OverlayManager overlayManager;

	@Inject
	private KeybindDisplayOverlay overlay;

	@Override
	protected void startUp() throws Exception
	{
		log.info("Keybind Display started!");
		overlayManager.add(overlay);
	}

	@Override
	protected void shutDown() throws Exception
	{
		log.info("Keybind Display stopped!");
		overlayManager.remove(overlay);
	}

	@Provides
	KeybindDisplayConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(KeybindDisplayConfig.class);
	}
}
