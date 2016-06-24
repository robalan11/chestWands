package com.robalan.chestWands.init;

import com.robalan.chestWands.block.BlockChestCW;
import com.robalan.chestWands.block.BlockChest;

public class ModBlocks {

    public static BlockChestCW chest;

    public static void init() {
        chest = new BlockChest();

        //initTileEntities();
    }
}
