package com.sushio4.overrev.registry;

import com.simibubi.create.foundation.data.CreateRegistrate;
import com.sushio4.overrev.CreateOverrev;
import com.sushio4.overrev.content.engine.DebugEngineBlockEntity;
import com.sushio4.overrev.registry.AllBlocks;
import com.tterrag.registrate.util.entry.BlockEntityEntry;

public class AllBlockEntities {
    private static final CreateRegistrate REGISTRATE = CreateOverrev.registrate();

    public static final BlockEntityEntry<DebugEngineBlockEntity> DEBUG_ENGINE_BE = 
        REGISTRATE.blockEntity("debug_engine", DebugEngineBlockEntity::new)
            .validBlocks(AllBlocks.DEBUG_ENGINE)
            .register();

    // load class
    public static void register() {

    }
}
