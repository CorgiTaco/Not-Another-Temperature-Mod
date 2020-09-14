package corgitaco.notanothertempmod.util;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;

public class MathUtil {

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


    public static double isUsingCelsius(double fahrenheitValue, boolean isUsingCelsius) {
        double returnValue;
        if (isUsingCelsius)
            returnValue = (5.0 / 9.0) * (fahrenheitValue - 32);
        else
            returnValue = fahrenheitValue;

        return returnValue;
    }

    public static double roundToHundredthPlace(double valueToRound) {
        return Math.round(valueToRound * 100) / 100.0;
    }
}
