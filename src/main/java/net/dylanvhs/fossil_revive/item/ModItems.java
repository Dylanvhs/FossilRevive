package net.dylanvhs.fossil_revive.item;

import net.dylanvhs.fossil_revive.FossilRevive;
import net.dylanvhs.fossil_revive.entity.ModEntities;
import net.dylanvhs.fossil_revive.sounds.ModSounds;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.RecordItem;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, FossilRevive.MOD_ID);

public static final RegistryObject<Item> FOSSIL = ITEMS.register("fossil",
        () -> new Item(new Item.Properties()));
public static final RegistryObject<Item> PLANT_FOSSIL = ITEMS.register("plant_fossil",
        () -> new Item(new Item.Properties()));
public static final RegistryObject<Item> ROUGH_FOSSIL = ITEMS.register("rough_fossil",
        () -> new Item(new Item.Properties()));
public static final RegistryObject<Item> DNA_BOTTLE = ITEMS.register("dna_bottle",
        () -> new Item(new Item.Properties()));
public static final RegistryObject<Item> HYBRID_DNA_BOTTLE = ITEMS.register("hybrid_dna_bottle",
        () -> new Item(new Item.Properties()));
public static final RegistryObject<Item> AMARGASAURUS_DNA = ITEMS.register("amargasaurus_dna",
        () -> new Item(new Item.Properties()));
public static final RegistryObject<Item> CARNOTAURUS_DNA = ITEMS.register("carnotaurus_dna",
        () -> new Item(new Item.Properties()));
public static final RegistryObject<Item> DILOPHOSAURUS_DNA = ITEMS.register("dilophosaurus_dna",
        () -> new Item(new Item.Properties()));
public static final RegistryObject<Item> DODO_DNA = ITEMS.register("dodo_dna",
        () -> new Item(new Item.Properties()));
public static final RegistryObject<Item> KELENKEN_DNA = ITEMS.register("kelenken_dna",
        () -> new Item(new Item.Properties()));
public static final RegistryObject<Item> KELENTAURUS_DNA = ITEMS.register("kelentaurus_dna",
        () -> new Item(new Item.Properties()));
public static final RegistryObject<Item> LIOPLEURODON_DNA = ITEMS.register("liopleurodon_dna",
        () -> new Item(new Item.Properties()));
public static final RegistryObject<Item> OVIRAPTOR_DNA = ITEMS.register("oviraptor_dna",
        () -> new Item(new Item.Properties()));
public static final RegistryObject<Item> SMILODON_DNA = ITEMS.register("smilodon_dna",
        () -> new Item(new Item.Properties()));
public static final RegistryObject<Item> TAPEJARA_DNA = ITEMS.register("tapejara_dna",
        () -> new Item(new Item.Properties()));
public static final RegistryObject<Item> TASMANIAN_TIGER_DNA = ITEMS.register("tasmanian_tiger_dna",
        () -> new Item(new Item.Properties()));
public static final RegistryObject<Item> TROODON_DNA = ITEMS.register("troodon_dna",
        () -> new Item(new Item.Properties()));
public static final RegistryObject<Item> BONES_MUSIC_DISC = ITEMS.register("bones_music_disc",
        () -> new RecordItem(6, ModSounds.BONES, new Item.Properties().stacksTo(1), 4900));

    public static final RegistryObject<Item> LIOPLEURODON_SPAWN_EGG = ITEMS.register("liopleurodon_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntities.LIOPLEURODON, 0x7e9680, 0xc5d1c5, new Item.Properties()));
    public static final RegistryObject<Item> QUETZALCOATLUS_SPAWN_EGG = ITEMS.register("quetzalcoatlus_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntities.QUETZALCOATLUS, 0x7e4680, 0xc5d1c5, new Item.Properties()));




    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
