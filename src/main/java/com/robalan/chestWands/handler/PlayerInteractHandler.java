package com.robalan.chestWands.handler;

import com.robalan.chestWands.item.ItemChestWand;
import com.robalan.chestWands.utility.LogHelper;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class PlayerInteractHandler {

    @SubscribeEvent
    public void onPlayerInteract(PlayerInteractEvent.RightClickBlock event) {
        World world = event.getWorld();
        if (world.isRemote) return;
        ItemStack stack = event.getItemStack();
        BlockPos pos = event.getPos();
        Block block = world.getBlockState(pos).getBlock();
        if (stack != null && stack.getItem() instanceof ItemChestWand && (block == Blocks.chest || stack.getTagCompound().hasKey("Chest"))) {
            LogHelper.info("Chest not opening");
            event.setUseBlock(Event.Result.DENY);
        }
    }
}
