package corgitaco.notanothertempmod.playerimpacts.temperature;

import corgitaco.notanothertempmod.NotAnotherTemperatureMod;
import corgitaco.notanothertempmod.data.capabilities.PlayerImpacts;
import corgitaco.notanothertempmod.data.network.NetworkHandler;
import corgitaco.notanothertempmod.data.network.packet.PlayerTemperaturePacket;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;

public class TemperatureCommon {


    public static void tickPlayerTemperature(PlayerEntity player, World world) {
        PlayerImpacts PLAYER_DATA = (PlayerImpacts) player.getCapability(NotAnotherTemperatureMod.PLAYER_IMPACTS).orElseThrow(RuntimeException::new);
        calculatePlayerTemperature(player, world, PLAYER_DATA);
        NetworkHandler.sendTo((ServerPlayerEntity) player, new PlayerTemperaturePacket(PLAYER_DATA.getPlayerTemperature()));
    }

    public static void calculatePlayerTemperature(PlayerEntity player, World world, PlayerImpacts playerImpacts) {
        double modifiedTemperature = playerImpacts.getPlayerTemperature();
        BlockPos.Mutable playerPosition = new BlockPos.Mutable().setPos(player.getPosition());
        Biome biome = world.getBiome(playerPosition);

//        if (player.isInWater()) {
//            modifiedTemperature -= 0.5;
//        }

        modifiedTemperature = calculatedWorldTemperature(player, world, playerImpacts);

        playerImpacts.setPlayerTemperature(modifiedTemperature);

    }

    public static double calculatedWorldTemperature(PlayerEntity player, World world, PlayerImpacts playerImpacts) {
        double worldTemperatureFahrenheit;
        BlockPos.Mutable playerPosition = new BlockPos.Mutable().setPos(player.getPosition());
        Biome biome = world.getBiome(playerPosition);

        //We do this to ensure temperature never goes negative from the world. < 0.1499999... is where water freezes or in our case, 0.9555999. 32 Degrees is where water freezes so we must find a way to calculate math to make 0.955599(or 0.96) = 32.
        double biomeTemperature = blendBiomeTemperatures(world, playerPosition);
        worldTemperatureFahrenheit = (biomeTemperature * 4) * 10;
        return worldTemperatureFahrenheit;
    }


    public static double blendBiomeTemperatures(World world, BlockPos pos) {
        final int x = pos.getX();
        final int z = pos.getZ();

        int blendingRange = 16;
        double accumulatedTemperature = 0;

        BlockPos.Mutable mutable = new BlockPos.Mutable().setPos(pos);
        for (int sampleX = x - blendingRange; sampleX <= x + blendingRange; ++sampleX) {
            mutable.setX(sampleX);

            for (int sampleZ = z - blendingRange; sampleZ <= z + blendingRange; ++sampleZ) {
                mutable.setZ(sampleZ);
                Biome biomeFromBlending = world.getBiome(mutable);
                double temperatureBlend = biomeFromBlending.getTemperature(mutable) + 0.5;

                accumulatedTemperature += temperatureBlend * temperatureBlend;
            }
        }
        float blendingSmoothing = 16 * 16;
        return (Math.sqrt(accumulatedTemperature / blendingSmoothing) / 2) - 3.125;
    }
}
