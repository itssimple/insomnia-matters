package se.itssimple.insomniamatters;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLLoadCompleteEvent;
import se.itssimple.insomniamatters.util.Reference;

/** The NeoForge version of the mod */
@Mod(Reference.MOD_ID)
public class ModNeoForge {
    /**
     * Default constructor
     * @param eventBus -
     */
    public ModNeoForge(IEventBus eventBus)
    {
        eventBus.addListener(this::loadComplete);
    }

    /**
     *
     * @param event -
     */
    private void loadComplete(final FMLLoadCompleteEvent event) { ModCommon.init(); }
}
