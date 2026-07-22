package com.jeffplugins.truetilecornermarker;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Polygon;
import javax.inject.Inject;
import net.runelite.api.Client;
import net.runelite.api.Perspective;
import net.runelite.api.Player;
import net.runelite.api.coords.LocalPoint;
import net.runelite.api.coords.WorldPoint;
import net.runelite.client.ui.overlay.Overlay;
import net.runelite.client.ui.overlay.OverlayLayer;
import net.runelite.client.ui.overlay.OverlayPosition;

public class TrueTileCornerMarkerOverlay extends Overlay
{
	// Offsets from the player's true tile: two tiles out on each diagonal,
	// skipping the ring that is only one tile diagonally adjacent.
	// Matches:
	//   X . . . X
	//   . . . . .
	//   . . P . .
	//   . . . . .
	//   X . . . X
	private static final int[][] CORNER_OFFSETS = {
		{-2, -2},
		{-2,  2},
		{ 2, -2},
		{ 2,  2}
	};

	private final Client client;
	private final TrueTileCornerMarkerConfig config;

	@Inject
	private TrueTileCornerMarkerOverlay(Client client, TrueTileCornerMarkerConfig config)
	{
		this.client = client;
		this.config = config;
		setPosition(OverlayPosition.DYNAMIC);
		setLayer(OverlayLayer.ABOVE_SCENE);
	}

	@Override
	public Dimension render(Graphics2D graphics)
	{
		Player player = client.getLocalPlayer();
		if (player == null)
		{
			return null;
		}

		// getWorldLocation() is already the true tile - it does not smooth/interpolate
		// like the rendered LocalPoint does mid-walk.
		WorldPoint playerLocation = player.getWorldLocation();

		Color base = config.markerColor();
		Color fill = new Color(base.getRed(), base.getGreen(), base.getBlue(), config.fillOpacity());
		Color border = new Color(base.getRed(), base.getGreen(), base.getBlue(), 255);

		graphics.setStroke(new BasicStroke(config.borderWidth()));

		for (int[] offset : CORNER_OFFSETS)
		{
			WorldPoint corner = new WorldPoint(
				playerLocation.getX() + offset[0],
				playerLocation.getY() + offset[1],
				playerLocation.getPlane());

			LocalPoint localPoint = LocalPoint.fromWorld(client, corner);
			if (localPoint == null)
			{
				// Tile isn't loaded in the current scene, skip it.
				continue;
			}

			Polygon poly = Perspective.getCanvasTilePoly(client, localPoint);
			if (poly == null)
			{
				continue;
			}

			graphics.setColor(fill);
			graphics.fillPolygon(poly);

			graphics.setColor(border);
			graphics.drawPolygon(poly);
		}

		return null;
	}
}
