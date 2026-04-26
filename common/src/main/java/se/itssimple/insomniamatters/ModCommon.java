package se.itssimple.insomniamatters;

import se.itssimple.obsidianweave.data.ConfigEntry;
import se.itssimple.obsidianweave.data.ConfigHolder;
import se.itssimple.insomniamatters.data.Constants;
import se.itssimple.insomniamatters.util.Reference;

/**
 * ModCommon contains all the configuration info for the mod
 */
public class ModCommon {
	/** Default constructor */
	public ModCommon() {}

	/** The config holder where the mod accesses info */
	public static ConfigHolder CONFIG;

	/** The entry for the "days awake before events" entry, which sets how many days awake you've had to be, before this mod starts doing stuff */
	public static ConfigEntry<Double> DAYS_AWAKE_BEFORE_EVENTS;

	/**
	 * The init method for the ModCommon class, telling us which mod and version we've loaded
	 */
	public static void init() {
		Constants.LOG.info("Loading {} (ID: {}), version {}", Reference.NAME, Reference.MOD_ID, Reference.VERSION);
		load();
	}

	/**
	 * This is where we load the config.
	 */
	private static void load() {
		CONFIG = se.itssimple.obsidianweave.ModCommon.registerConfig(Reference.MOD_ID, builder -> {
			DAYS_AWAKE_BEFORE_EVENTS = builder.define("days_awake_before_events", 500.0 /*2.5 * 24000*/);
		});
	}
}