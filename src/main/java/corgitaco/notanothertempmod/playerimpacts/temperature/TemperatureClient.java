package corgitaco.notanothertempmod.playerimpacts.temperature;

import corgitaco.notanothertempmod.capabilities.PlayerTemperature;
import corgitaco.notanothertempmod.capabilities.PlayerTemperatureProvider;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class TemperatureClient {

    public static double returnPlayerTemperature() {
        PlayerTemperature playerTemperature = PlayerTemperatureProvider.PLAYER_TEMP.getDefaultInstance();
        if (playerTemperature != null)
            return playerTemperature.getPlayerTemperature();
        else
            return 0;
    }
}