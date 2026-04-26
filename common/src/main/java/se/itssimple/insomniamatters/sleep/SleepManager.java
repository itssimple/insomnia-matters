package se.itssimple.insomniamatters.sleep;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class SleepManager {
    private static final int DARKNESS_DURATION = 100;
    private static final int MOVEMENT_LOCK_DURATION = 80;
    static final int EVENT_TICK = 40;
    private static final int TOTAL_SLEEP_TICKS = 80;

    private static final Map<UUID, Long> SLEEPING_PLAYERS = new HashMap<>();

    public static boolean isSleeping(UUID playerId) {
        return SLEEPING_PLAYERS.containsKey(playerId);
    }

    public static void startSleep(ServerPlayer player) {
        if (isSleeping(player.getUUID())) return;
        SLEEPING_PLAYERS.put(player.getUUID(), player.level().getGameTime());
        player.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, DARKNESS_DURATION, 127, false, false));
        player.addEffect(new MobEffectInstance(MobEffects.SLOWNESS, MOVEMENT_LOCK_DURATION, 127, false, false));
    }

    public static void tickSleeping(ServerPlayer player) {
        Long startTick = SLEEPING_PLAYERS.get(player.getUUID());
        if (startTick == null) return;

        long elapsed = player.level().getGameTime() - startTick;

        var movement = player.getDeltaMovement();
        player.setDeltaMovement(0.0, movement.y(), 0.0);

        if (elapsed == EVENT_TICK) {
            SleepEvents.fireRandomEvent(player);
        }

        if (elapsed >= TOTAL_SLEEP_TICKS) {
            SLEEPING_PLAYERS.remove(player.getUUID());
            player.startSleeping(player.getBlockPosBelowThatAffectsMyMovement());
        }
    }
}
