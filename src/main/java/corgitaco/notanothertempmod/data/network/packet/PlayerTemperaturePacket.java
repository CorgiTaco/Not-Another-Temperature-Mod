package corgitaco.notanothertempmod.data.network.packet;

import corgitaco.notanothertempmod.NotAnotherTemperatureMod;
import corgitaco.notanothertempmod.data.capabilities.PlayerImpacts;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class PlayerTemperaturePacket {
    private final double playerTemp;

    public PlayerTemperaturePacket(double playerTemp) {
        this.playerTemp = playerTemp;
    }

    public static void writeToPacket(PlayerTemperaturePacket packet, PacketBuffer buf) {
        buf.writeDouble(packet.playerTemp);
    }

    public static PlayerTemperaturePacket readFromPacket(PacketBuffer buf) {
        return new PlayerTemperaturePacket(buf.readDouble());
    }

    public static void handle(PlayerTemperaturePacket message, Supplier<NetworkEvent.Context> ctx) {
        if (ctx.get().getDirection().getReceptionSide().isClient()) {
            ctx.get().enqueueWork(() -> {
                PlayerEntity player = Minecraft.getInstance().player;
                PlayerImpacts PLAYER_DATA2 = (PlayerImpacts) player.getCapability(NotAnotherTemperatureMod.PLAYER_IMPACTS).orElseThrow(RuntimeException::new);
                PLAYER_DATA2.setPlayerTemperature(message.playerTemp);
            });
        }
        ctx.get().setPacketHandled(true);
    }
}
