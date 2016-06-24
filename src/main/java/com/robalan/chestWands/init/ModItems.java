package com.robalan.chestWands.init;

import com.robalan.chestWands.item.ItemCW;
import com.robalan.chestWands.item.ItemMovingWand;
import com.robalan.chestWands.reference.Reference;
import net.minecraftforge.fml.common.registry.GameRegistry;

@GameRegistry.ObjectHolder(Reference.MOD_ID)
public class ModItems {
    public static ItemCW movingWand;

    public static void init() {
        movingWand = new ItemMovingWand();
    }
}
