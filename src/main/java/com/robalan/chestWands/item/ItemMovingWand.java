package com.robalan.chestWands.item;

import com.robalan.chestWands.utility.LogHelper;
import net.minecraft.block.Block;
import net.minecraft.block.BlockChest;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemMovingWand extends ItemChestWand {

    public ItemMovingWand() {
        super("movingWand");
    }

    @Override
    public EnumActionResult onItemUseFirst(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ, EnumHand hand) {
        if (super.clickChest(world, pos) && (!stack.hasTagCompound() || !stack.getTagCompound().hasKey("Chest"))) {
            pickupChest(stack, player, world, pos);
        }
        else if (stack.hasTagCompound() && stack.getTagCompound().hasKey("Chest")) {
            placeChest(stack, player, world, pos, side);
        }
        return EnumActionResult.PASS;
    }

    protected boolean pickupChest(ItemStack stack, EntityPlayer player, World world, BlockPos pos) {
        LogHelper.info("Picking up a chest.");
        IBlockState chestState = world.getBlockState(pos);
        Block storedChest = chestState.getBlock();
        int chestMeta = storedChest.getMetaFromState(chestState);
        TileEntity chestTE = world.getTileEntity(pos);
        NBTTagCompound nbtChest = new NBTTagCompound();
        NBTTagCompound nbtTarget = new NBTTagCompound();

        chestTE.writeToNBT(nbtChest);

        nbtTarget.setString("Block", storedChest.getRegistryName().toString());
        nbtTarget.setInteger("Meta", chestMeta);
        nbtTarget.setTag("NBT", nbtChest);

        stack.getTagCompound().setTag("Chest", nbtTarget);

        try {
            world.removeTileEntity(pos);
            world.setBlockToAir(pos);
        }
        catch ( Exception e ) {
            e.printStackTrace();
        }

        //world.playSound(player, pos, SoundEvents.block_wood_break, SoundCategory.BLOCKS, 5.0f, 1.0f); TODO: Figure out how to play a sound
        stack.getTagCompound().setBoolean("isFull", true);
        return true;
    }

    protected boolean placeChest(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumFacing side) {
        LogHelper.info("Putting down a chest.");
        NBTTagCompound nbtChest = stack.getTagCompound().getCompoundTag("Chest");

        Block toPlace = Block.getBlockFromName(nbtChest.getString("Block"));
        int blockMeta = nbtChest.getInteger("Meta");
        NBTTagCompound nbtContainer = nbtChest.getCompoundTag("NBT");

        Block targetBlock = world.getBlockState(pos).getBlock();

        BlockPos targetPos = pos;
        if (!targetBlock.isReplaceable(world, pos)) {
            targetPos = pos.offset(side);
        }

        if (!world.isAirBlock(targetPos) && !world.getBlockState(targetPos).getBlock().isReplaceable(world, pos)) return false;
        if (!toPlace.canPlaceBlockAt(world, targetPos)) return false;

        nbtContainer.setInteger("x", targetPos.getX());
        nbtContainer.setInteger("y", targetPos.getY());
        nbtContainer.setInteger("z", targetPos.getZ());

        world.setBlockState(targetPos, toPlace.getStateFromMeta(blockMeta).withProperty(BlockChest.FACING, player.getHorizontalFacing().getOpposite()));
        world.getTileEntity(targetPos).readFromNBT(nbtContainer);

        stack.getTagCompound().removeTag("Chest");

        stack.getTagCompound().setBoolean("isFull", false);
        return true;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public boolean hasEffect(ItemStack stack) {
        return stack.getTagCompound().getBoolean("isFull");
    }

    @Override
    public void onCreated(ItemStack stack, World world, EntityPlayer player) {
        stack.setTagCompound(new NBTTagCompound());
        stack.getTagCompound().setBoolean("isFull", false);
    }
}
