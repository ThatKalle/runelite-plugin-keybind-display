package com.keybinddisplay;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

import java.awt.*;

@ConfigGroup("keyvinddisplay")
public interface KeybindDisplayConfig extends Config {
	@ConfigItem(
			keyName = "xOffset",
			name = "X Offset",
			description = "X offset for keybind label"
	)
	default int xOffset() {
		return 5;
	}

	@ConfigItem(
			keyName = "yOffset",
			name = "Y Offset",
			description = "Y offset for keybind label"
	)
	default int yOffset() {
		return 5;
	}

	@ConfigItem(
			keyName = "fontSize",
			name = "Font Size",
			description = "Font size for the keybind text"
	)
	default int fontSize() {
		return 16;
	}

	@ConfigItem(
			keyName = "textColor",
			name = "Text Color",
			description = "Color of the keybind text"
	)
	default Color textColor() {
		return Color.WHITE;
	}
}