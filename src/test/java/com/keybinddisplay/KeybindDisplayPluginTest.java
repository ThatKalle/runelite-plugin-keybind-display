package com.keybinddisplay;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class KeybindDisplayPluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(KeybindDisplayPlugin.class);
		RuneLite.main(args);
	}
}