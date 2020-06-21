package me.ryanhamshire.GPFlags;

import org.bukkit.plugin.Plugin;

import java.io.File;

/**
 * Data holder for flags
 */
//singleton class which manages all GriefPrevention data (except for config options)
public class DataStore {

	private final static String dataLayerFolderPath = "plugins" + File.separator + "GPFlags";
	final static String configFilePath = dataLayerFolderPath + File.separator + "config.yml";
	final static String flagsFilePath = dataLayerFolderPath + File.separator + "flags.yml";
	final static String flagsErrorFilePath = dataLayerFolderPath + File.separator + "flagsError.yml";

	private final Plugin plugin;

	public DataStore(Plugin plugin) {
		this.plugin = plugin;
	}

}
