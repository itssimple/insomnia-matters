package se.itssimple.insomniamatters;

import net.fabricmc.api.ModInitializer;

/** The Fabric version for the mod */
public class ModFabric implements ModInitializer {

	/** Register the entity load event to fix the goal loading */
	public ModFabric()
	{
	}

	/** Initializer of stuff */
	@Override
	public void onInitialize() {
		ModCommon.init();
	}
}