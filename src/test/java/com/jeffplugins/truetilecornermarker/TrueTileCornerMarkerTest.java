package com.jeffplugins.truetilecornermarker;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class TrueTileCornerMarkerTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(TrueTileCornerMarkerPlugin.class);
		RuneLite.main(args);
	}
}