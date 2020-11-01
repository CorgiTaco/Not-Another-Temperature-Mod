package corgitaco.notanothertempmod.playerimpacts;

import corgitaco.notanothertempmod.NotAnotherTemperatureMod;
import corgitaco.notanothertempmod.data.PlayerData;
import corgitaco.notanothertempmod.util.MathUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class Temperature {


    public static void tickPlayerTemperature(PlayerEntity player, World world) {
        calculatePlayerTemperature(player, world, NotAnotherTemperatureMod.playerImpacts);
    }

    public static void calculatePlayerTemperature(PlayerEntity player, World world, PlayerData playerImpacts) {
        double modifiedTemperature = playerImpacts.getPlayerTemperature();
        BlockPos.Mutable playerPosition = new BlockPos.Mutable().setPos(player.getPosition());
        Biome biome = world.getBiome(playerPosition);

        if (world.getWorldInfo().getGameTime() % 10 == 0)
            modifiedTemperature = modifiedTemperature - 10;


        playerImpacts.setPlayerTemperature(modifiedTemperature);

    }

    public static double calculatedWorldTemperature(PlayerEntity player, World world, PlayerData playerImpacts) {
        double worldTemperatureFahrenheit;
        BlockPos.Mutable playerPosition = new BlockPos.Mutable().setPos(player.getPosition());
        Biome biome = world.getBiome(playerPosition);

        //We do this to ensure temperature never goes negative from the world. < 0.1499999... is where water freezes or in our case, 0.9555999. 32 Degrees is where water freezes so we must find a way to calculate math to make 0.955599(or 0.96) = 32.
        double biomeTemperature = MathUtil.blendBiomeTemperatures(world, playerPosition);
        worldTemperatureFahrenheit = (biomeTemperature * 4) * 10;


        return MathUtil.roundToHundredthPlace(MathUtil.isUsingCelsius(worldTemperatureFahrenheit, false));
    }

    @OnlyIn(Dist.CLIENT)
    public static double returnPlayerTemperature(Minecraft minecraft) {
        return NotAnotherTemperatureMod.playerImpacts.getPlayerTemperature();
    }
}
