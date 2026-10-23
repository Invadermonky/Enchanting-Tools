package com.sirsquidly.enchanter_tools.common.items;

import com.sirsquidly.enchanter_tools.common.RuneType;
import com.sirsquidly.enchanter_tools.config.Config;
import com.sirsquidly.enchanter_tools.config.Config.configItem.configLapisRune.TooltipType;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Set;

public class ItemLapisRune extends Item
{
    private final RuneType runeType;

    public ItemLapisRune(RuneType spearMaterialIn)
    {
        this.runeType = spearMaterialIn;
        this.setCreativeTab(CreativeTabs.MISC);
    }

    public RuneType getRuneType() { return runeType; }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
        if(Config.item.lapisRune.enableTooltip)
        {
            tooltip.add(TextFormatting.GRAY + I18n.format("description.enchanter_tools.enchantment_table_ingredient.label"));
            tooltip.add("");
            tooltip.add(TextFormatting.GRAY + I18n.format("description.enchanter_tools.enchantment_table_ingredient.applies"));
            tooltip.add(TextFormatting.BLUE + I18n.format("description.enchanter_tools.enchantment_table_ingredient.books"));
        }

        TooltipType configType = Config.item.lapisRune.enchantmentTooltips;
        if(configType == TooltipType.ENABLED || (configType == TooltipType.SHIFT && GuiScreen.isShiftKeyDown()))
        {
            Set<Enchantment> whitelist =  this.getRuneType().getEnchantmentWhitelist();
            Set<Enchantment> blacklist =  this.getRuneType().getEnchantmentBlacklist();
            if(!whitelist.isEmpty())
            {
                tooltip.add(I18n.format("description.enchanter_tools.lapis_rune.whitelist"));
                whitelist.forEach(ench -> tooltip.add(TextFormatting.BLUE + "  " + I18n.format(ench.getName())));
            }

            if(!blacklist.isEmpty())
            {
                tooltip.add(I18n.format("description.enchanter_tools.lapis_rune.blacklist"));
                blacklist.forEach(ench -> tooltip.add(TextFormatting.RED + "  " + I18n.format(ench.getName())));
            }
        }
    }
}