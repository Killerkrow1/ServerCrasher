package paradigm.killerkrow;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.client.gui.screen.CreditsScreen;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;

public class ModNetworkHelper {
    public static final Identifier SHOW_CREDITS_PACKET_ID = new Identifier("servercrasher", "show_credits");

    public static void sendCreditsPacket(ServerPlayerEntity player) {
        PacketByteBuf buf = PacketByteBufs.create();
        ServerPlayNetworking.send(player, SHOW_CREDITS_PACKET_ID, buf);
    }

    @Environment(EnvType.CLIENT)
    public static void registerClientReceiver() {
        ClientPlayNetworking.registerGlobalReceiver(SHOW_CREDITS_PACKET_ID, (client, handler, buf, responseSender) -> {
            client.execute(() -> {
                client.setScreen(new CreditsScreen(false, () -> {
                    client.setScreen(null);
                }));
            });
        });
    }
}