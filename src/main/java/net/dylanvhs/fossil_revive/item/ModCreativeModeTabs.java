package net.dylanvhs.fossil_revive.item;

import net.dylanvhs.fossil_revive.FossilRevive;
import net.dylanvhs.fossil_revive.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, FossilRevive.MOD_ID);
    public static final RegistryObject<CreativeModeTab> FOSSIL_REVIVE_TAB = CREATIVE_MODE_TABS.register("ridiculous_weaponry_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.FOSSIL.get()))

                    .title(Component.translatable("creativetab.fossil_revive_tab"))
                    .displayItems((pParameters, pOutput) -> {
                        pOutput.accept(ModItems.FOSSIL.get());
                        pOutput.accept(ModItems.ROUGH_FOSSIL.get());
                        pOutput.accept(ModItems.PLANT_FOSSIL.get());

                        pOutput.accept(ModItems.DNA_BOTTLE.get());
                        pOutput.accept(ModItems.HYBRID_DNA_BOTTLE.get());

                        pOutput.accept(ModItems.AMARGASAURUS_DNA.get());
                        pOutput.accept(ModItems.CARNOTAURUS_DNA.get());
                        pOutput.accept(ModItems.DILOPHOSAURUS_DNA.get());
                        pOutput.accept(ModItems.DODO_DNA.get());
                        pOutput.accept(ModItems.KELENKEN_DNA.get());
                        pOutput.accept(ModItems.KELENTAURUS_DNA.get());
                        pOutput.accept(ModItems.LIOPLEURODON_DNA.get());
                        pOutput.accept(ModItems.OVIRAPTOR_DNA.get());
                        pOutput.accept(ModItems.SMILODON_DNA.get());
                        pOutput.accept(ModItems.TAPEJARA_DNA.get());
                        pOutput.accept(ModItems.TASMANIAN_TIGER_DNA.get());
                        pOutput.accept(ModItems.TROODON_DNA.get());

                        pOutput.accept(ModItems.LIOPLEURODON_SPAWN_EGG.get());

                        pOutput.accept(ModBlocks.ANALYZER.get());

                    })
                    .build());


    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
