package net.dylanvhs.fossil_revive.item;

import net.dylanvhs.fossil_revive.FossilRevive;
import net.dylanvhs.fossil_revive.block.ModBlocks;
import net.dylanvhs.fossil_revive.entity.ModEntities;
import net.dylanvhs.fossil_revive.item.custom.ChiselItem;
import net.dylanvhs.fossil_revive.item.custom.ItemModFishBucket;
import net.dylanvhs.fossil_revive.sounds.ModSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.core.BlockSource;
import net.minecraft.core.dispenser.DefaultDispenseItemBehavior;
import net.minecraft.core.dispenser.DispenseItemBehavior;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, FossilRevive.MOD_ID);

    public static final RegistryObject<Item> FOSSIL = ITEMS.register("fossil",
        () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> PLANT_FOSSIL = ITEMS.register("plant_fossil",
        () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> CRUMBlED_FOSSIL = ITEMS.register("crumbled_fossil",
        () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> DNA_BOTTLE = ITEMS.register("dna_bottle",
        () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> HYBRID_DNA_BOTTLE = ITEMS.register("hybrid_dna_bottle",
        () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> SYRINGE = ITEMS.register("syringe",
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
    public static final RegistryObject<Item> QUETZALCOATLUS_DNA = ITEMS.register("quetzalcoatlus_dna",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> MICRORAPTOR_DNA = ITEMS.register("microraptor_dna",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> SMILODON_EMBRYO = ITEMS.register("smilodon_embryo",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> LIOPLEURODON_EMBRYO = ITEMS.register("liopleurodon_embryo",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> TASMANIAN_TIGER_EMBRYO = ITEMS.register("tasmanian_tiger_embryo",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> HORSETAIL_SEEDS = ITEMS.register("horsetail_seeds",
            () -> new ItemNameBlockItem(ModBlocks.HORSETAIL_CROP.get(), new Item.Properties()));
    public static final RegistryObject<Item> MONTSECHIA_SEEDS = ITEMS.register("montsechia_seeds",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> OSMUNDA_SEEDS = ITEMS.register("osmunda_seeds",
            () -> new ItemNameBlockItem(ModBlocks.OSMUNDA_CROP.get(), new Item.Properties()));
    public static final RegistryObject<Item> PINKSPARK_SEEDS = ITEMS.register("pinkspark_seeds",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> SPIRALFLOWER_SEEDS = ITEMS.register("spiralflower_seeds",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> DIRTY_CLOTH = ITEMS.register("dirty_cloth",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> CHISEL = ITEMS.register("chisel",
            () -> new ChiselItem(new Item.Properties().durability(64)));

    public static final RegistryObject<Item> DILOPHOSAURUS_EGG =
            ITEMS.register("dilophosaurus_egg", () -> new BlockItem(ModBlocks.DILOPHOSAURUS_EGG.get(), (new Item.Properties()).stacksTo(4)));
    public static final RegistryObject<Item> DODO_EGG =
            ITEMS.register("dodo_egg", () -> new BlockItem(ModBlocks.DODO_EGG.get(), (new Item.Properties()).stacksTo(4)));


    public static final RegistryObject<Item> RAW_XENACANTHUS = ITEMS.register("raw_xenacanthus",
            () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(2).saturationMod(0.4F).build())));
    public static final RegistryObject<Item> COOKED_XENACANTHUS = ITEMS.register("cooked_xenacanthus",
            () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(6).saturationMod(0.8F).build())));
    public static final RegistryObject<Item> XENACANTHUS_BUCKET = ITEMS.register("xenacanthus_bucket",
            () -> new ItemModFishBucket(ModEntities.XENACANTHUS, Fluids.WATER, new Item.Properties()));


    public static final RegistryObject<Item> BONES_MUSIC_DISC = ITEMS.register("bones_music_disc",
            () -> new RecordItem(6, ModSounds.BONES, new Item.Properties().stacksTo(1).rarity(Rarity.RARE), 4900));
    public static final RegistryObject<Item> WHEN_YOURE_THE_LAST_MUSIC_DISC = ITEMS.register("when_youre_the_last_music_disc",
            () -> new RecordItem(7, ModSounds.WHEN_YOURE_THE_LAST, new Item.Properties().stacksTo(1).rarity(Rarity.RARE), 4900));


    public static final RegistryObject<Item> DODO_SPAWN_EGG = ITEMS.register("dodo_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntities.DODO, 0x4279a1, 0xe9e9e9, new Item.Properties()));
    public static final RegistryObject<Item> DILOPHOSAURUS_SPAWN_EGG = ITEMS.register("dilophosaurus_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntities.DILOPHOSAURUS, 0x2f992b, 0x1f3a78, new Item.Properties()));
    public static final RegistryObject<Item> LIOPLEURODON_SPAWN_EGG = ITEMS.register("liopleurodon_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntities.LIOPLEURODON, 0x3257b2, 0x151057, new Item.Properties()));
    public static final RegistryObject<Item> QUETZALCOATLUS_SPAWN_EGG = ITEMS.register("quetzalcoatlus_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntities.QUETZALCOATLUS, 0x3f4053, 0xffbb00, new Item.Properties()));
    public static final RegistryObject<Item> MICRORAPTOR_SPAWN_EGG = ITEMS.register("microraptor_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntities.MICRORAPTOR, 0x3e4580, 0xc391c5, new Item.Properties()));
    public static final RegistryObject<Item> XENACANTHUS_SPAWN_EGG = ITEMS.register("xenacanthus_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntities.XENACANTHUS, 0x978077, 0xd17f6e, new Item.Properties()));
    public static final RegistryObject<Item> RANALOPHOSAURUS_SPAWN_EGG = ITEMS.register("ranalophosaurus_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntities.RANALOPHOSAURUS, 0x8e4730, 0x5c31c5, new Item.Properties()));

    public static void initDispenser() {
        DispenseItemBehavior bucketDispenseBehavior = new DefaultDispenseItemBehavior() {
            private final DefaultDispenseItemBehavior defaultDispenseItemBehavior = new DefaultDispenseItemBehavior();

            public @NotNull ItemStack execute(BlockSource blockSource, ItemStack stack) {
                DispensibleContainerItem dispensiblecontaineritem = (DispensibleContainerItem) stack.getItem();
                BlockPos blockpos = blockSource.getPos().relative(blockSource.getBlockState().getValue(DispenserBlock.FACING));
                Level level = blockSource.getLevel();
                if (dispensiblecontaineritem.emptyContents(null, level, blockpos, null)) {
                    dispensiblecontaineritem.checkExtraContent(null, level, stack, blockpos);
                    return new ItemStack(Items.BUCKET);
                } else {
                    return this.defaultDispenseItemBehavior.dispense(blockSource, stack);
                }
            }
        };
        DispenserBlock.registerBehavior(XENACANTHUS_BUCKET.get(), bucketDispenseBehavior);
    }


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
