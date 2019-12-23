package com.demeng7215.volticsigns.listeners;

import com.demeng7215.demlib.api.messages.MessageUtils;
import com.demeng7215.volticsigns.VolticSigns;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;

import java.io.IOException;

public class VolticSignPlaceEvent implements Listener {

	private VolticSigns i;

	public VolticSignPlaceEvent(VolticSigns i) {
		this.i = i;
	}

	@EventHandler(priority = EventPriority.MONITOR)
	public void onSignPlace(SignChangeEvent e) {

		if (e.getPlayer().hasPermission("vsigns.create")) {

			if (emptyLine(e.getLine(0)) || !e.getLine(0).equalsIgnoreCase("[CMD]") ||
					emptyLine(e.getLine(1)) || emptyLine(e.getLine(2))) return;

			String[] coords = e.getLine(2).split(",");

			if (coords.length != 3) return;

			for (String coord : coords) if (!isNumeric(coord)) return;

			final String parsedLocation = e.getBlock().getLocation().getWorld().getName() + "," +
					e.getBlock().getLocation().getBlockX() + "," +
					e.getBlock().getLocation().getBlockY() + "," +
					e.getBlock().getLocation().getBlockZ();

			final String bossName = e.getLine(1);

			final String parsedData = bossName + "," + e.getLine(2);

			i.getData().set(parsedLocation, parsedData);

			try {
				i.getDataFile().saveConfig();
			} catch (IOException ex) {
				ex.printStackTrace();
			}

			e.setLine(0, MessageUtils.colorize("&2&l——————————————————"));
			e.setLine(1, MessageUtils.colorize("&c" + bossName));
			e.setLine(2, MessageUtils.colorize("&2&l——————————————————"));
			e.setLine(3, "");
		}
	}

	private boolean emptyLine(String line) {
		return line == null || line.equals("");
	}

	public static boolean isNumeric(String string) {
		try {
			int i = Integer.parseInt(string);
		} catch (NumberFormatException nfe) {
			return false;
		}
		return true;
	}
}
