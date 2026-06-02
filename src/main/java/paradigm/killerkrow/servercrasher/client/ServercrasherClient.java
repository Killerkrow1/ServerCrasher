package paradigm.killerkrow.servercrasher.client;

import net.fabricmc.api.ClientModInitializer;
import paradigm.killerkrow.ModNetworkHelper;

public class ServercrasherClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        ModNetworkHelper.registerClientReceiver();
    }
}
