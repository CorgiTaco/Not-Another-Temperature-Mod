package corgitaco.notanothertempmod.playerimpacts.temperature;

import corgitaco.notanothertempmod.capabilities.ITemperature;
import corgitaco.notanothertempmod.capabilities.PlayerTemperature;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;

public class TemperatureCommon {

    @CapabilityInject(ITemperature.class)
    public static Capability<ITemperature> PLAYER_TEMP = null;

    public static void tickPlayerTemperature(PlayerEntity playerEntity, World world) {
        final PlayerTemperature PLAYER_DATA = (PlayerTemperature) playerEntity.getCapability(PLAYER_TEMP).orElseThrow(RuntimeException::new);


        Biome biome = world.getBiome(playerEntity.getPosition());
        double biomeTemperature = biome.getTemperature(playerEntity.getPosition());

        double playerTemperature = PLAYER_DATA.getPlayerTemperature();

        if (world.getWorldInfo().getGameTime() % 50 == 0) {
            if (playerTemperature < biomeTemperature)
                playerTemperature = playerTemperature + 0.5;
            else if (playerTemperature > biomeTemperature)
                playerTemperature = playerTemperature - 0.5;

            PLAYER_DATA.setBiomeTemperature(biomeTemperature);
            PLAYER_DATA.setPlayerTemperature(playerTemperature);
        }
    }
}
