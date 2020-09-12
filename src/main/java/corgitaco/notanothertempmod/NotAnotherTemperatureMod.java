package corgitaco.notanothertempmod;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityViewRenderEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod("notanothertempmod")
public class NotAnotherTemperatureMod {
    public static final Logger LOGGER = LogManager.getLogger();

    public static final String MOD_ID = "notanothertempmod";

    public NotAnotherTemperatureMod() {}







    @Mod.EventBusSubscriber(modid = MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.FORGE)
    public static class NATMClientEvents {

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
        public void renderGameOverlayEventText(RenderGameOverlayEvent.Text event) {}
    }
}
