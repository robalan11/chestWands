package com.robalan.chestWands.proxy;

import com.robalan.chestWands.block.BlockChestCW;
import com.robalan.chestWands.init.ModBlocks;
import com.robalan.chestWands.init.ModItems;
import com.robalan.chestWands.item.ItemCW;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends CommonProxy {

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event){
        super.preInit(event);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        super.init(event);
        ItemCW.registerRender(ModItems.movingWand);
        BlockChestCW.registerRender(ModBlocks.chest);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event){
        super.postInit(event);
    }
}
