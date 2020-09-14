package corgitaco.notanothertempmod.playerimpacts.temperature;

import corgitaco.notanothertempmod.NotAnotherTemperatureMod;
import corgitaco.notanothertempmod.data.capabilities.PlayerImpacts;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class TemperatureClient {
    public static double returnPlayerTemperature(Minecraft minecraft) {
        PlayerImpacts PLAYER_DATA2 = (PlayerImpacts) minecraft.player.getCapability(NotAnotherTemperatureMod.PLAYER_IMPACTS).orElseThrow(RuntimeException::new);
        return PLAYER_DATA2.getPlayerTemperature();
    }
}