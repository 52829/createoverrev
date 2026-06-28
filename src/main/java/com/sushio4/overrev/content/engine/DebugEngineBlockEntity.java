package com.sushio4.overrev.content.engine;

import com.simibubi.create.content.kinetics.base.GeneratingKineticBlockEntity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class DebugEngineBlockEntity extends GeneratingKineticBlockEntity {
    private EngineSimulation engine = new EngineSimulation();
    private float targetRpm = 100;
    private int changeSpeedTimer = 5;
    private float throttle = 0.4f;
    
    public DebugEngineBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    @Override
    public void initialize() {
        super.initialize();

        updateGeneratedRotation();
    }

    @Override
    public void onSpeedChanged(float previousSpeed) {
        super.onSpeedChanged(previousSpeed);
        if(!level.isClientSide) {
            float networkSpeed = getSpeed();
            boolean matchesOwnSpeed = Math.abs(networkSpeed - targetRpm) < 0.5f;
            if(!matchesOwnSpeed && networkSpeed > targetRpm) {
                targetRpm = networkSpeed;
            } 
        }
    }

    @Override
    public void tick() {
        super.tick();
        if(!level.isClientSide) {
            if(overStressed) {
                targetRpm = 0;
            }    
            engine.tick(stress, targetRpm, throttle);
            targetRpm = engine.getRpm();

            changeSpeedTimer--;
            if(changeSpeedTimer > 0) return;

            if(Math.abs(speed - targetRpm) >= 1 || targetRpm == 0) {
                changeSpeedTimer = 10;
                updateGeneratedRotation();
                targetRpm = engine.getRpm();
            }
        }
    }

    @Override
    public void lazyTick() {
        super.lazyTick();

        int signal = level.getBestNeighborSignal(getBlockPos());
        throttle = (float)signal / 15.0f;
    }

    @Override
    public float getGeneratedSpeed() {
        return targetRpm;
    }

    @Override
    public float calculateAddedStressCapacity() {
        return engine.getTorque();
    }

    @Override
    public float calculateStressApplied() {
        if(targetRpm > 3) {
            return 0;
        }
        return 2;
    }
}