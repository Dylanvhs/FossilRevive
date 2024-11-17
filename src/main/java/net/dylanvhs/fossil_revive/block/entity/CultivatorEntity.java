package net.dylanvhs.fossil_revive.block.entity;


import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import net.dylanvhs.fossil_revive.block.ModBlockEntities;
import net.dylanvhs.fossil_revive.screens.CultivatorMenu;
import net.dylanvhs.fossil_revive.screens.CultivatorRecipe;
import net.dylanvhs.fossil_revive.screens.ModRecipeTypes;
import net.dylanvhs.fossil_revive.util.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.RegistryAccess;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Container;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.StackedContents;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.RecipeHolder;
import net.minecraft.world.inventory.StackedContentsCompatible;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class CultivatorEntity extends BlockEntity implements MenuProvider, StackedContentsCompatible {
    private final ItemStackHandler itemHandler = new ItemStackHandler(3);
    private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();
    protected final ContainerData data;

    private final RecipeType<CultivatorRecipe> recipeType;
    private int progress;
    private int maxProgress;

    int tickCount;

    public CultivatorEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.CULTIVATOR_BE.get(), pPos, pBlockState);
        this.recipeType = ModRecipeTypes.CULTIVATOR_TYPE.get();
        this.data = new ContainerData() {
            @Override
            public int get(int pIndex) {
                return switch (pIndex) {
                    case 0 -> CultivatorEntity.this.progress;
                    case 1 -> CultivatorEntity.this.maxProgress;
                    default -> 0;
                };
            }

            @Override
            public void set(int pIndex, int pValue) {
                switch (pIndex) {
                    case 0 -> CultivatorEntity.this.progress = pValue;
                    case 1 -> CultivatorEntity.this.maxProgress = pValue;
                }
            }

            @Override
            public int getCount() {
                return 2;
            }
        };
    }

    public void serverTick(Level pLevel, BlockPos pPos, BlockState pState) {
        ++tickCount;

        if (recipe == null) {
            this.progress = 0;
            return;
        }

        if (this.isRecipeAvailableToForge(pLevel.registryAccess(), recipe)) {

        }
    }

    public boolean isRecipeAvailableToForge(RegistryAccess access, StarlitFactoryRecipe recipe) {

        if (recipe != null) {
            ItemStack recipeStack = recipe.assemble(this, access);
            if (recipeStack.isEmpty()) {
                return false;
            } else {
                ItemStack result = containedItems.get(StarlitFactoryMenu.RESULT_SLOT);
                this.maxFactoryForgeTime = recipe.getForgeTime();
                if (result.isEmpty()) {
                    return true;
                } else if (!ItemStack.isSameItem(result, recipeStack)) {
                    return false;
                } else if (result.getCount() + recipeStack.getCount() <= this.getMaxStackSize() && result.getCount() + recipeStack.getCount() <= result.getMaxStackSize()) {
                    return true;
                } else {
                    return result.getCount() + recipeStack.getCount() <= recipeStack.getMaxStackSize();
                }
            }
        }
        return false;
    }

    public boolean finishForging(RegistryAccess access, StarlitFactoryRecipe recipe) {
        if (recipe != null && this.isRecipeAvailableToForge(access, recipe)) {
            ItemStack resultStack = containedItems.get(StarlitFactoryMenu.RESULT_SLOT);
            ItemStack formedResultItem = recipe.assemble(this, access);
            if (resultStack.isEmpty()) {
                this.setItem(StarlitFactoryMenu.RESULT_SLOT, formedResultItem.copy());
            } else if (resultStack.is(formedResultItem.getItem())) {
                resultStack.grow(formedResultItem.getCount());
            }

            for (ItemStack ingredientStack : containedItems) {
                if (ingredientStack != containedItems.get(StarlitFactoryMenu.RESULT_SLOT) && ingredientStack != containedItems.get(StarlitFactoryMenu.FUEL_SLOT)) {
                    ingredientStack.shrink(1);
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if(cap == ForgeCapabilities.ITEM_HANDLER) {
            return lazyItemHandler.cast();
        }
        return super.getCapability(cap, side);
    }

    @Override
    public void onLoad() {
        super.onLoad();
        lazyItemHandler = LazyOptional.of(() -> itemHandler);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        lazyItemHandler.invalidate();
    }

    public void drops() {
        SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
        for (int i = 0; i < itemHandler.getSlots(); i ++) {
            inventory.setItem(i, itemHandler.getStackInSlot(i));
        }
        Containers.dropContents(this.level, this.worldPosition, inventory);
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("block.fossil_revive.cultivator");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int pContainerId, Inventory pPlayerInventory, Player pPlayer) {
        return new CultivatorMenu(pContainerId, pPlayerInventory, this, this.data);
    }

    @Override
    protected void saveAdditional(CompoundTag pTag) {
        pTag.put("inventory", itemHandler.serializeNBT());
        pTag.putInt("cultivator.progress", progress);
        super.saveAdditional(pTag);
    }

    @Override
    public void load(CompoundTag pTag) {
        super.load(pTag);
        itemHandler.deserializeNBT(pTag.getCompound("inventory"));
        progress = pTag.getInt("cultivator.progress");
    }

    @Override
    public void fillStackedContents(StackedContents stackedContents) {
        for(int i = 0; i < this.itemHandler.getSlots(); i++) {
            stackedContents.accountStack(this.itemHandler.getStackInSlot(i));
        }
    }
}
