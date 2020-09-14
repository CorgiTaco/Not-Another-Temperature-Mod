package corgitaco.notanothertempmod.playerimpacts.temperature;

import corgitaco.notanothertempmod.NotAnotherTemperatureMod;
import corgitaco.notanothertempmod.data.capabilities.PlayerImpacts;
import corgitaco.notanothertempmod.data.network.NetworkHandler;
import corgitaco.notanothertempmod.data.network.packet.PlayerTemperaturePacket;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.world.World;

public class TemperatureCommon {


    public static void tickPlayerTemperature(PlayerEntity playerEntity, World world) {
        PlayerImpacts PLAYER_DATA = (PlayerImpacts) playerEntity.getCapability(NotAnotherTemperatureMod.PLAYER_IMPACTS).orElseThrow(RuntimeException::new);

        if (world.getWorldInfo().getGameTime() % 50 == 0) {
            PLAYER_DATA.setPlayerTemperature(PLAYER_DATA.getPlayerTemperature() - 0.5);
            NotAnotherTemperatureMod.LOGGER.info(PLAYER_DATA.getPlayerTemperature());
        }
        PLAYER_DATA.setPlayerTemperature(PLAYER_DATA.getPlayerTemperature());

        NetworkHandler.sendTo((ServerPlayerEntity) playerEntity, new PlayerTemperaturePacket(PLAYER_DATA.getPlayerTemperature()));

    }
}
