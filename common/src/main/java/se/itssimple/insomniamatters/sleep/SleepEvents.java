package se.itssimple.insomniamatters.sleep;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;

import java.util.List;
import java.util.function.Consumer;

public class SleepEvents {
    private static final List<Consumer<ServerPlayer>> EVENTS = List.of(
        SleepEvents::teleportNearby,
        SleepEvents::applyNausea,
        SleepEvents::applyWeakness,
        SleepEvents::scrambleDirection
    );

    public static void fireRandomEvent(ServerPlayer player) {
        EVENTS.get(player.level().getRandom().nextInt(EVENTS.size())).accept(player);
    }

    private static void teleportNearby(ServerPlayer player) {
        var random = player.level().getRandom();
        double offsetX = (random.nextDouble() - 0.5) * 200.0;
        double offsetZ = (random.nextDouble() - 0.5) * 200.0;
        player.teleportTo(player.getX() + offsetX, player.getY(), player.getZ() + offsetZ);
        player.setOnGround(true);
    }

    private static void applyNausea(ServerPlayer player) {
        player.addEffect(new MobEffectInstance(MobEffects.NAUSEA, 200, 1, false, false));
    }

    private static void applyWeakness(ServerPlayer player) {
        player.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 600, 0, false, false));
    }

    private static void scrambleDirection(ServerPlayer player) {
        player.setYRot(player.level().getRandom().nextFloat() * 360.0f - 180.0f);
    }
}
