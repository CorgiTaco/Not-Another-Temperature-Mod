package corgitaco.notanothertempmod.playerimpacts.sleep;

import corgitaco.notanothertempmod.NotAnotherTemperatureMod;
import corgitaco.notanothertempmod.data.capabilities.PlayerImpacts;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SleepClient {

    public static double returnPlayerSleepiness(Minecraft minecraft) {
        PlayerImpacts PLAYER_DATA2 = (PlayerImpacts) minecraft.player.getCapability(NotAnotherTemperatureMod.PLAYER_IMPACTS).orElseThrow(RuntimeException::new);
        return PLAYER_DATA2.getPlayerSleepiness();
    }
}
