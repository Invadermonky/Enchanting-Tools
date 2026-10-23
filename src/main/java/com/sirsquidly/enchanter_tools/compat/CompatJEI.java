package com.sirsquidly.enchanter_tools.compat;

import com.sirsquidly.enchanter_tools.init.EnchanterToolsBlocks;
import com.sirsquidly.enchanter_tools.init.EnchanterToolsItems;
import mezz.jei.api.*;
import mezz.jei.api.ingredients.VanillaTypes;
import net.minecraft.item.ItemStack;

import java.util.List;
import java.util.stream.Collectors;

@JEIPlugin
public class CompatJEI implements IModPlugin
{
    @Override
    public void register(IModRegistry registry)
    {
        IJeiHelpers helpers = registry.getJeiHelpers();

        addInformation(registry);
    }

    /** Attaches JEI Descriptions to the items which it is helpful for. */
    public void addInformation(IModRegistry registry)
    {
        registry.addIngredientInfo(new ItemStack(EnchanterToolsBlocks.ARCANE_BRAZIER), VanillaTypes.ITEM, "jei.enchanter_tools.arcane_brazier.desc");
        registry.addIngredientInfo(new ItemStack(EnchanterToolsItems.ENCHANTED_EIGHT_BALL), VanillaTypes.ITEM, "jei.enchanter_tools.enchanted_eight_ball.desc");
        registry.addIngredientInfo(new ItemStack(EnchanterToolsItems.ENCHANTED_INKWELL), VanillaTypes.ITEM, "jei.enchanter_tools.enchanted_inkwell.desc");
        registry.addIngredientInfo(new ItemStack(EnchanterToolsItems.EXTRACTING_BOOK), VanillaTypes.ITEM, "jei.enchanter_tools.extracting_book.desc");
        /* Holy shit I expected this to be WAY worse! */
        List<ItemStack> runeStacks = EnchanterToolsItems.LAPIS_RUNES.stream().map(ItemStack::new).collect(Collectors.toList());
        registry.addIngredientInfo(runeStacks, VanillaTypes.ITEM, "jei.enchanter_tools.lapis_rune.desc");
        registry.addIngredientInfo(new ItemStack(EnchanterToolsBlocks.PONDERING_ORB), VanillaTypes.ITEM, "jei.enchanter_tools.pondering_orb.desc");
    }
}