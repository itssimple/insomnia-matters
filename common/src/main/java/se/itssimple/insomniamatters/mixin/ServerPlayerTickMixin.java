package se.itssimple.insomniamatters.mixin;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.ServerStatsCounter;
import net.minecraft.stats.Stats;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import se.itssimple.insomniamatters.ModCommon;
import se.itssimple.insomniamatters.data.Constants;
import se.itssimple.insomniamatters.sleep.SleepManager;

@Mixin(ServerPlayer.class)
public class ServerPlayerTickMixin {
    @Inject(method = "tick", at = @At(value = "TAIL"), remap = false)
    private void tick(CallbackInfo ci) {
        ServerPlayer player = (ServerPlayer)(Object)this;
        Level level = player.level();

        if (level.isClientSide()) return;

        if (SleepManager.isSleeping(player.getUUID())) {
            SleepManager.tickSleeping(player);
            return;
        }

        ServerStatsCounter stats = player.getStats();
        RandomSource random = level.getRandom();
        int value = Mth.clamp(stats.getValue(Stats.CUSTOM.get(Stats.TIME_SINCE_REST)), 1, Integer.MAX_VALUE);

        if(value % 20 == 0)
        {
            //Constants.LOG.info("Tick {}, configured value {}", value, ModCommon.DAYS_AWAKE_BEFORE_EVENTS.getValue());
        }

        if (random.nextInt(value) >= ModCommon.DAYS_AWAKE_BEFORE_EVENTS.getValue()) {
            SleepManager.startSleep(player);
        }
    }
}
