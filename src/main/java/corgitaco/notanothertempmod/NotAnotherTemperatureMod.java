package corgitaco.notanothertempmod;

import corgitaco.notanothertempmod.data.PlayerData;
import corgitaco.notanothertempmod.playerimpacts.Hydration;
import corgitaco.notanothertempmod.playerimpacts.Sleep;
import corgitaco.notanothertempmod.playerimpacts.Temperature;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.ServerPlayerEntity;
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

//    @CapabilityInject(IPlayerImpacts.class)
//    public static Capability<IPlayerImpacts> PLAYER_IMPACTS = null;

    public NotAnotherTemperatureMod() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::commonSetup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientSetup);
    }

    public static final PlayerData playerImpacts = new PlayerData();

    public void commonSetup(FMLCommonSetupEvent event) {
        LOGGER.debug("NATM: Common setup event starting...");
//        PlayerImpactCapability.register();
//        NetworkHandler.init();
        LOGGER.info("NATM: Common setup event completed!");
    }


    public void clientSetup(FMLClientSetupEvent event) {}

    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
    public static class NATMEvents {

        @SubscribeEvent
        public static void playerTickEvent(TickEvent.PlayerTickEvent event) {
            if (event.side.isServer()) {
                ServerPlayerEntity player = (ServerPlayerEntity) event.player;
                World world = player.world;
                if (event.phase == TickEvent.Phase.START) {
                    Temperature.tickPlayerTemperature(player, world);
                }
            }
        }

        @SubscribeEvent
        public static void worldTickEvent(TickEvent.WorldTickEvent event) {
        }

        @SubscribeEvent
        public static void entityTickEvent(LivingEvent.LivingUpdateEvent event) {
        }
    }


    @Mod.EventBusSubscriber(modid = MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.FORGE)
    public static class NATMClientEvents {

        public static final Minecraft mc = Minecraft.getInstance();

        @SubscribeEvent
        public static void renderTickEvent(TickEvent.RenderTickEvent event) {
        }

        @SubscribeEvent
        public static void clientTickEvent(TickEvent.ClientTickEvent event) {
        }

        @SubscribeEvent
        public static void renderFogEvent(EntityViewRenderEvent.FogDensity event) {
        }

        @SubscribeEvent
        public void renderGameOverlayEventPre(RenderGameOverlayEvent.Pre event) {
        }

        @SubscribeEvent
        public static void renderGameOverlayEventPost(RenderGameOverlayEvent.Post event) {
        }

        @SubscribeEvent
        public static void renderGameOverlayEventText(RenderGameOverlayEvent.Text event) {
            if (!mc.gameSettings.showDebugInfo) {
                event.getLeft().add("Player Temperature: " + Temperature.returnPlayerTemperature(mc));
                event.getLeft().add("World Temperature: " + Temperature.returnWorldTemperature(mc));
                event.getLeft().add("Player Hydration: " + Hydration.getHydration(mc));
                event.getLeft().add("Player Sleepiness: " + Sleep.getPlayerSleepiness(mc));
            }
        }
    }
}
