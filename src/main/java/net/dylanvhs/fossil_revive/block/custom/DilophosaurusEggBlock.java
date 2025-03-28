package net.dylanvhs.fossil_revive.block.custom;

import net.dylanvhs.fossil_revive.block.ModBlocks;
import net.dylanvhs.fossil_revive.entity.ModEntities;
import net.dylanvhs.fossil_revive.entity.custom.DilophosaurusEntity;
import net.dylanvhs.fossil_revive.item.ModItems;
import net.minecraft.advancements.Criterion;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Turtle;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LevelEvent;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class DilophosaurusEggBlock extends Block implements SimpleWaterloggedBlock {
    protected static final VoxelShape SHAPE = Block.box(5, 0, 5, 11, 9, 11);
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    public static final IntegerProperty HATCH = BlockStateProperties.HATCH;

    public DilophosaurusEggBlock(BlockBehaviour.Properties settings) {
        super(settings);
        this.registerDefaultState(this.stateDefinition.any().setValue(WATERLOGGED, false).setValue(HATCH, 0));
    }

    @Override
    public ItemStack getCloneItemStack(BlockState state, HitResult target, BlockGetter level, BlockPos pos, Player player) {
        return new ItemStack(ModItems.DILOPHOSAURUS_EGG.get());

    }

    public void randomTick(BlockState pState, ServerLevel pLevel, BlockPos pPos, RandomSource pRandom) {
        if (this.shouldUpdateHatchLevel(pLevel) && onFertileMoss(pLevel, pPos)) {
            int i = pState.getValue(HATCH);
            if (i < 2) {
                pLevel.playSound((Player) null, pPos, SoundEvents.TURTLE_EGG_CRACK, SoundSource.BLOCKS, 0.7F, 0.9F + pRandom.nextFloat() * 0.2F);
                pLevel.setBlock(pPos, pState.setValue(HATCH, Integer.valueOf(i + 1)), 2);
            } else {
                pLevel.playSound((Player) null, pPos, SoundEvents.TURTLE_EGG_HATCH, SoundSource.BLOCKS, 0.7F, 0.9F + pRandom.nextFloat() * 0.2F);
                pLevel.removeBlock(pPos, false);
                pLevel.levelEvent(2001, pPos, Block.getId(pState));
                DilophosaurusEntity dilophosaurus = ModEntities.DILOPHOSAURUS.get().create(pLevel);
                if (dilophosaurus != null) {
                    dilophosaurus.setAge(-24000);
                    dilophosaurus.moveTo(pPos.getX() + 0.3, pPos.getY(), pPos.getZ() + 0.3, 0.0f, 0.0f);
                    pLevel.addFreshEntity(dilophosaurus);
                }
            }
        }
    }

    private boolean shouldUpdateHatchLevel(Level pLevel) {
        float f = pLevel.getTimeOfDay(1.0F);
        if ((double)f < 0.80D && (double)f > 0.50D) {
            return true;
        } else {
            return pLevel.random.nextInt(500) == 0;
        }
    }
    public static boolean onFertileMoss(BlockGetter pLevel, BlockPos pPos) {
        return isFertileMoss(pLevel, pPos.below());
    }

    public static boolean isFertileMoss(BlockGetter pReader, BlockPos pPos) {
        return pReader.getBlockState(pPos).is(ModBlocks.FERTILE_MOSS.get());
    }


    @Override
    public boolean canSurvive(BlockState blockState, LevelReader levelReader, BlockPos blockPos) {
        return !levelReader.isEmptyBlock(blockPos.below());
    }

    @Override
    public VoxelShape getShape(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, CollisionContext collisionContext) {
        return SHAPE;
    }

    @Override
    public BlockState updateShape(BlockState blockState, Direction direction, BlockState blockState2, LevelAccessor levelAccessor, BlockPos blockPos, BlockPos blockPos2) {
        if (blockState.getValue(WATERLOGGED)) levelAccessor.scheduleTick(blockPos, Fluids.WATER, Fluids.WATER.getTickDelay(levelAccessor));
        return super.updateShape(blockState, direction, blockState2, levelAccessor, blockPos, blockPos2);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext blockPlaceContext) {
        return this.defaultBlockState().setValue(WATERLOGGED, blockPlaceContext.getLevel().getFluidState(blockPlaceContext.getClickedPos()).getType() == Fluids.WATER);
    }

    @Override
    public FluidState getFluidState(BlockState state) {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(HATCH, WATERLOGGED);
    }




}
