package com.sushio4.overrev.registry;

import com.simibubi.create.foundation.data.CreateRegistrate;
import com.sushio4.overrev.CreateOverrev;
import com.sushio4.overrev.content.engine.DebugEngineBlock;
import com.tterrag.registrate.util.entry.BlockEntry;

public class AllBlocks {
    private static final CreateRegistrate REGISTRATE = CreateOverrev.registrate();

    public static final BlockEntry<DebugEngineBlock> DEBUG_ENGINE = 
        REGISTRATE.block("debug_engine", DebugEngineBlock::new).item().build().register();

    // load class
    public static void register() {

    }
}
