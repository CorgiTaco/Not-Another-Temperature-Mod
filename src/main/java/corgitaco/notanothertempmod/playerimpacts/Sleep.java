package corgitaco.notanothertempmod.playerimpacts;

import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class Sleep {

    @OnlyIn(Dist.CLIENT)
    public static double getPlayerSleepiness(Minecraft minecraft) {
        return 0;
    }
}
