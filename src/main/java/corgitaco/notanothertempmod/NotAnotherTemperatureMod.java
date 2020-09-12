package corgitaco.notanothertempmod;

import corgitaco.notanothertempmod.capabilities.PlayerTemperatureCapability;
import corgitaco.notanothertempmod.playerimpacts.temperature.TemperatureClient;
import corgitaco.notanothertempmod.playerimpacts.temperature.TemperatureCommon;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityViewRenderEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod("notanothertempmod")
public class NotAnotherTemperatureMod {
    public static final Logger LOGGER = LogManager.getLogger();

    public static final String MOD_ID = "notanothertempmod";

    public NotAnotherTemperatureMod() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::commonSetup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientSetup);

    }

    public void commonSetup(FMLCommonSetupEvent event) {
        PlayerTemperatureCapability.register();
    }

    public void clientSetup(FMLClientSetupEvent event) {}

    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
    public static class NATMEvents {

        @SubscribeEvent
        public static void playerTickEvent(TickEvent.PlayerTickEvent event) {
            PlayerEntity player = event.player;
            World world = player.world;
            TemperatureCommon.tickPlayerTemperature(player, world);
        }

        @SubscribeEvent
        public static void worldTickEvent(TickEvent.WorldTickEvent event) {}

        @SubscribeEvent
        public static void entityTickEvent(LivingEvent.LivingUpdateEvent event) {}
    }


    @Mod.EventBusSubscriber(modid = MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.FORGE)
    public static class NATMClientEvents {

        Minecraft mc = Minecraft.getInstance();

        @SubscribeEvent
        public static void renderTickEvent(TickEvent.RenderTickEvent event) {}

        @SubscribeEvent
        public static void clientTickEvent(TickEvent.ClientTickEvent event) {}

        @SubscribeEvent
        public static void renderFogEvent(EntityViewRenderEvent.FogDensity event) {}

        @SubscribeEvent
        public void renderGameOverlayEventPre(RenderGameOverlayEvent.Pre event) {}

        @SubscribeEvent
        public void renderGameOverlayEventPost(RenderGameOverlayEvent.Post event) {}

        @SubscribeEvent
        public void renderGameOverlayEventText(RenderGameOverlayEvent.Text event) {
            if (!mc.gameSettings.showDebugInfo)
               event.getLeft().add("Player Temperature" + TemperatureClient.returnPlayerTemperature());
        }
    }
}
