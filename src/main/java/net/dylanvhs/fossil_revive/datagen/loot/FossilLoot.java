package net.dylanvhs.fossil_revive.datagen.loot;

import java.util.function.BiConsumer;

import net.dylanvhs.fossil_revive.item.ModItems;
import net.dylanvhs.fossil_revive.loot.ModBuiltInLootTables;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;


public class FossilLoot implements LootTableSubProvider {
    public void generate(BiConsumer<ResourceLocation, LootTable.Builder> p_278066_) {
        p_278066_.accept(ModBuiltInLootTables.FOSSIL_LOOT, LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).add(LootItem.lootTableItem(ModItems.CRUMBlED_FOSSIL.get())).add(LootItem.lootTableItem(ModItems.PLANT_FOSSIL.get())).add(LootItem.lootTableItem(Items.BONE)).add(LootItem.lootTableItem(Items.STICK)).add(LootItem.lootTableItem(Items.BONE_MEAL)).add(LootItem.lootTableItem(Items.EMERALD).setWeight(2)).add(LootItem.lootTableItem(ModItems.CRUMBlED_FOSSIL.get()).setWeight(2)).add(LootItem.lootTableItem(Items.CHARCOAL).setWeight(2)).add(LootItem.lootTableItem(Items.COAL).setWeight(2))));
    }
}
