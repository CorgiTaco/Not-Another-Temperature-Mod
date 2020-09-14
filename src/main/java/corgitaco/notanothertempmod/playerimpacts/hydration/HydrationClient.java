package corgitaco.notanothertempmod.playerimpacts.hydration;

import corgitaco.notanothertempmod.NotAnotherTemperatureMod;
import corgitaco.notanothertempmod.data.capabilities.PlayerImpacts;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class HydrationClient {
    public static double returnHydrationTemperature(Minecraft minecraft) {
        PlayerImpacts PLAYER_DATA2 = (PlayerImpacts) minecraft.player.getCapability(NotAnotherTemperatureMod.PLAYER_IMPACTS).orElseThrow(RuntimeException::new);
        return PLAYER_DATA2.getPlayerHydration();
    }
}