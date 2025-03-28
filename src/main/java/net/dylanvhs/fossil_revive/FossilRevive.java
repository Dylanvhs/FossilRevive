package net.dylanvhs.fossil_revive;

import com.mojang.logging.LogUtils;
import net.dylanvhs.fossil_revive.block.ModBlocks;
import net.dylanvhs.fossil_revive.block.ModBlockEntities;
import net.dylanvhs.fossil_revive.entity.ModEntities;
import net.dylanvhs.fossil_revive.entity.client.*;
import net.dylanvhs.fossil_revive.item.ModCreativeModeTabs;
import net.dylanvhs.fossil_revive.item.ModItems;
import net.dylanvhs.fossil_revive.screens.AnalyzerScreen;
import net.dylanvhs.fossil_revive.screens.CultivatorScreen;
import net.dylanvhs.fossil_revive.screens.ModMenuTypes;
import net.dylanvhs.fossil_revive.recipe.ModRecipeTypes;
import net.dylanvhs.fossil_revive.sounds.ModSounds;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

import java.util.Locale;

@Mod(FossilRevive.MOD_ID)
public class FossilRevive
{

    public static final String MOD_ID = "fossil_revive";

    private static final Logger LOGGER = LogUtils.getLogger();
    public FossilRevive()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModCreativeModeTabs.register(modEventBus);

        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);

        ModEntities.register(modEventBus);

        ModBlockEntities.register(modEventBus);
        ModMenuTypes.register(modEventBus);
        ModRecipeTypes.register(modEventBus);

        ModSounds.register(modEventBus);


        modEventBus.addListener(this::commonSetup);

    }
    public static ResourceLocation prefix(String name) {
        return new ResourceLocation(MOD_ID, name.toLowerCase(Locale.ROOT));
    }
    private void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(ModItems::initDispenser);
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {

    }


    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {

            EntityRenderers.register
                    (ModEntities.LIOPLEURODON.get(), LiopleurodonRenderer:: new);

            EntityRenderers.register
                    (ModEntities.DILOPHOSAURUS.get(), DilophosaurusRenderer:: new);

            EntityRenderers.register
                    (ModEntities.QUETZALCOATLUS.get(), QuetzalcoatlusRenderer:: new);

            EntityRenderers.register
                    (ModEntities.DODO.get(), DodoRenderer:: new);

            EntityRenderers.register
                    (ModEntities.XENACANTHUS.get(), XenacanthusRenderer:: new);

            EntityRenderers.register
                    (ModEntities.RANALOPHOSAURUS.get(), RanalophosaurusRenderer:: new);

            EntityRenderers.register
                    (ModEntities.MICRORAPTOR.get(), MicroraptorRenderer:: new);

            EntityRenderers.register
                    (ModEntities.SPINOSAURUS.get(), SpinosaurusRenderer:: new);



            MenuScreens.register(ModMenuTypes.ANALYZER_MENU.get(), AnalyzerScreen::new);
            MenuScreens.register(ModMenuTypes.CULTIVATOR_MENU.get(), CultivatorScreen::new);
        }
    }
}
