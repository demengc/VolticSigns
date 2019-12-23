package com.demeng7215.volticsigns;

import com.demeng7215.demlib.DemLib;
import com.demeng7215.demlib.api.Registerer;
import com.demeng7215.demlib.api.files.CustomConfig;
import com.demeng7215.demlib.api.messages.MessageUtils;
import com.demeng7215.volticsigns.listeners.VolticSignClickEvent;
import com.demeng7215.volticsigns.listeners.VolticSignPlaceEvent;
import lombok.Getter;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public final class VolticSigns extends JavaPlugin {

	@Getter
	public CustomConfig dataFile;

	@Override
	public void onEnable() {

		DemLib.setPlugin(this);
		MessageUtils.setPrefix("&8[&6VolticSigns&8] ");

		try {
			dataFile = new CustomConfig("data.yml");
		} catch (Exception e) {
			e.printStackTrace();
		}

		Registerer.registerListeners(new VolticSignPlaceEvent(this));
		Registerer.registerListeners(new VolticSignClickEvent(this));

		MessageUtils.console("&aVolticSigns has been successfully enabled.");
	}

	@Override
	public void onDisable() {
		MessageUtils.console("&cVolticSigns has been successfully disabled.");
	}

	public FileConfiguration getData() {
		return dataFile.getConfig();
	}
}
