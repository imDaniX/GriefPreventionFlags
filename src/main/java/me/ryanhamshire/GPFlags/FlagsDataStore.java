package me.ryanhamshire.GPFlags;

import java.io.File;

/**
 * Data holder for flags
 */
//singleton class which manages all GriefPrevention data (except for config options)
@Deprecated
public interface FlagsDataStore {

	String dataLayerFolderPath = "plugins" + File.separator + "GPFlags" + File.separator;
	String configFilePath = dataLayerFolderPath + "config.yml";
	String messagesFilePath = dataLayerFolderPath + "messages.yml";
	String flagsFilePath = dataLayerFolderPath + "flags.yml";
	String flagsErrorFilePath = dataLayerFolderPath + "flagsError.yml";
}
