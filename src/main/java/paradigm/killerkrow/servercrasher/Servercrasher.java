package paradigm.killerkrow.servercrasher;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import paradigm.killerkrow.ModNetworkHelper;

public class Servercrasher implements ModInitializer {


    @Override
    public void onInitialize() {
        UseBlockCallback.EVENT.register((player, world, hand, hitResult) -> {
            // Throw an exception to instantly crash the game
            throw new RuntimeException("Forced crash triggered by right-clicking a block!");
        });
        PlayerBlockBreakEvents.AFTER.register((world, player, pos, state, blockEntity) -> {
            if (!world.isClient()) {
                // Send the packet to trigger client-side credits
                ModNetworkHelper.sendCreditsPacket((net.minecraft.server.network.ServerPlayerEntity) player);
            }
        });
    }
}