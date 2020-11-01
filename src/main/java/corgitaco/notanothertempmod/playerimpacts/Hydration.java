package corgitaco.notanothertempmod.playerimpacts;

import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class Hydration {

    @OnlyIn(Dist.CLIENT)
    public static double getHydration(Minecraft minecraft) {
        return 0;
    }
}