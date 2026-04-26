package se.itssimple.insomniamatters;
import net.minecraftforge.eventbus.api.bus.BusGroup;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import se.itssimple.insomniamatters.util.Reference;
import net.minecraftforge.fml.common.Mod;

/**
 * The Forge version of the mod
 */
@Mod(Reference.MOD_ID)
public class ModForge {

    /**
     * Registering the mod with ModCommon
     * @param modLoadingContext The mod loading context from Forge
     */
	public ModForge(FMLJavaModLoadingContext modLoadingContext) {
        BusGroup modEventBus = modLoadingContext.getModBusGroup();

        FMLLoadCompleteEvent.getBus(modEventBus).addListener(this::loadComplete);
	}

    /**
     * Load completed!
     * @param event -
    */
    private void loadComplete(final FMLLoadCompleteEvent event) {
        ModCommon.init();
    }

}