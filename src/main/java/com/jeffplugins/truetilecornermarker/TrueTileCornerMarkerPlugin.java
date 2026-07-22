package com.jeffplugins.truetilecornermarker;

import com.google.inject.Provides;
import javax.inject.Inject;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.overlay.OverlayManager;

@PluginDescriptor(
	name = "True Tile Corner Marker",
	description = "Dynamically marks the four corner tiles two tiles diagonally from the player's true tile",
	tags = {"tile", "marker", "overlay", "corner"}
)
public class TrueTileCornerMarkerPlugin extends Plugin
{
	@Inject
	private OverlayManager overlayManager;

	@Inject
	private TrueTileCornerMarkerOverlay overlay;

	@Provides
	TrueTileCornerMarkerConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(TrueTileCornerMarkerConfig.class);
	}

	@Override
	protected void startUp()
	{
		overlayManager.add(overlay);
	}

	@Override
	protected void shutDown()
	{
		overlayManager.remove(overlay);
	}
}
