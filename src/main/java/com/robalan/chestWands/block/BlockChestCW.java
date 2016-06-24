package com.robalan.chestWands.block;

import assets.chestwands.creativetab.CreativeTabCW;
import com.robalan.chestWands.reference.Reference;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockChestCW extends BlockContainer {
    public BlockChestCW(String name, Material material) {
        super(material);
        super.setUnlocalizedName(name);
        registerBlock(this, name);
        this.setCreativeTab(CreativeTabCW.CHESTWANDS_TAB);
    }

    public Block registerBlock(Block block, String name) {
        setRegistryName(Reference.RESOURCE_PREFIX + name);
        GameRegistry.register(block);
        GameRegistry.register(new ItemBlock(block), new ResourceLocation(Reference.RESOURCE_PREFIX + name));

        return block;
    }

    public static void registerRender(Block block) {
        Item item = Item.getItemFromBlock(block);
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return null;
    }
}
