package com.sushio4.overrev.content.engine;

import com.simibubi.create.content.kinetics.base.HorizontalKineticBlock;
import com.simibubi.create.foundation.block.IBE;
import com.sushio4.overrev.registry.AllBlockEntities;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BooleanProperty;


public class DebugEngineBlock extends HorizontalKineticBlock implements IBE<DebugEngineBlockEntity>{
    public DebugEngineBlock(Properties properties) {
        super(properties);
    }

    @Override
    public Class<DebugEngineBlockEntity> getBlockEntityClass() {
        return DebugEngineBlockEntity.class;
    }

    @Override
    public BlockEntityType<DebugEngineBlockEntity> getBlockEntityType() {
        return AllBlockEntities.DEBUG_ENGINE_BE.get();
    }

    @Override
    public Direction.Axis getRotationAxis(BlockState state) {
        return state.getValue(HORIZONTAL_FACING).getAxis();
    }

    @Override
    public boolean hasShaftTowards(LevelReader world, BlockPos pos, BlockState state, Direction face) {
        return state.getValue(HORIZONTAL_FACING).getAxis() == face.getAxis();
    }
}
