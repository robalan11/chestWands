package com.robalan.chestWands.init;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.ShapedOreRecipe;

public class Recipes {
    public static void init() {
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.movingWand), " is", " si", "s  ", 's', "stickWood", 'i', "ingotIron"));
    }
}
