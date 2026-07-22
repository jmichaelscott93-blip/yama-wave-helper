package com.jeffplugins.truetilecornermarker;

import java.awt.Color;
import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

@ConfigGroup("truetilecornermarker")
public interface TrueTileCornerMarkerConfig extends Config
{
	@ConfigItem(
		keyName = "markerColor",
		name = "Marker color",
		description = "Color used to highlight the four corner tiles",
		position = 1
	)
	default Color markerColor()
	{
		return Color.RED;
	}

	@ConfigItem(
		keyName = "borderWidth",
		name = "Border width",
		description = "Width of the tile outline",
		position = 2
	)
	default int borderWidth()
	{
		return 2;
	}

	@ConfigItem(
		keyName = "fillOpacity",
		name = "Fill opacity",
		description = "Alpha (0-255) for the tile fill color",
		position = 3
	)
	default int fillOpacity()
	{
		return 50;
	}
}
