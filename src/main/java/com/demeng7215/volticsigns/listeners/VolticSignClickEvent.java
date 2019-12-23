package com.demeng7215.volticsigns.listeners;

import com.demeng7215.volticsigns.VolticSigns;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class VolticSignClickEvent implements Listener {

	private VolticSigns i;

	public VolticSignClickEvent(VolticSigns i) {
		this.i = i;
	}

	@EventHandler
	public void onSignUse(PlayerInteractEvent e) {

		if (e.getClickedBlock() == null || !e.getClickedBlock().getType().name().contains("SIGN") ||
				e.getAction() != Action.RIGHT_CLICK_BLOCK) return;

		final String parsedLocation = e.getClickedBlock().getLocation().getWorld().getName() + "," +
				e.getClickedBlock().getLocation().getBlockX() + "," +
				e.getClickedBlock().getLocation().getBlockY() + "," +
				e.getClickedBlock().getLocation().getBlockZ();

		if (!i.getData().getKeys(false).contains(parsedLocation)) return;

		final String data = i.getData().getString(parsedLocation);

		final String boss = data.split(",")[0];
		final String location = data.split(",")[1] + " " + data.split(",")[2] + " " + data.split(",")[3];

		Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "boss spawn " + e.getClickedBlock().getLocation().getWorld().getName() + " " + location + " " + boss);
	}
}
