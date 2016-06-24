package com.robalan.chestWands.item;

import com.robalan.chestWands.utility.LogHelper;
import net.minecraft.block.Block;
import net.minecraft.block.BlockChest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import sun.rmi.runtime.Log;

public class ItemChestWand extends ItemCW {

    public ItemChestWand(String name) {
        super(name);
    }

    public boolean clickChest(World world, BlockPos pos) {
        if (world.isRemote) return false; // TODO: Figure out why this seems backwards...

        Block block = world.getBlockState(pos).getBlock();
        if (block instanceof BlockChest) {
            LogHelper.info(String.format("It's a chest! Let's do something!"));
            return true;
        }
        else {
            return false;
        }
    }
}
