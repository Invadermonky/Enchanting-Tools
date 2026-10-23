package com.sirsquidly.enchanter_tools.init;

import com.sirsquidly.enchanter_tools.common.RuneType;
import com.sirsquidly.enchanter_tools.common.items.*;
import com.sirsquidly.enchanter_tools.config.Config;
import com.sirsquidly.enchanter_tools.config.ConfigParser;
import com.sirsquidly.enchanter_tools.enchanterTools;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber(modid = enchanterTools.MOD_ID)
public class EnchanterToolsItems
{
    public static final List<Item> itemList = new ArrayList<Item>();

    public static Item EXTRACTING_BOOK = new ItemExtractingBook().setCreativeTab(CreativeTabs.TOOLS);
    public static Item ENCHANTED_INKWELL = new ItemEnchantedInkwell().setCreativeTab(CreativeTabs.TOOLS);
    public static Item ENCHANTED_EIGHT_BALL = new ItemEnchanted8Ball().setCreativeTab(CreativeTabs.MISC);
    public static Item OBSIDIAN_BURNISHER = new ItemObsidianBurnisher().setCreativeTab(CreativeTabs.TOOLS);

    public static List<ItemLapisRune> LAPIS_RUNES = new ArrayList<>();

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event)
    {
        if (Config.item.extractingBook.enable) itemReadyForRegister(EXTRACTING_BOOK, "extracting_book");
        if (Config.item.enchantedEightBall.enable) itemReadyForRegister(ENCHANTED_EIGHT_BALL, "enchanted_eight_ball");
        if (Config.item.inkwell.enable) itemReadyForRegister(ENCHANTED_INKWELL, "enchanted_inkwell");
        if (Config.item.obsidianBurnisher.enable) itemReadyForRegister(OBSIDIAN_BURNISHER, "obsidian_burnisher");

        for(RuneType type : ConfigParser.parseLapisRuneConfigs())
        {
            LAPIS_RUNES.add((ItemLapisRune) itemReadyForRegister(new ItemLapisRune(type), type.getRegistryName()));
        }

        for (Item items : itemList) event.getRegistry().register(items);
    }

    public static Item itemReadyForRegister(Item item, String name)
    {
        if (name != null)
        {
            item.setTranslationKey(enchanterTools.MOD_ID + "." + name);
            item.setRegistryName(name);
        }

        itemList.add(item);

        return item;
    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public static void onModelRegister(ModelRegistryEvent event)
    { for (Item items : itemList) enchanterTools.proxy.registerItemRenderer(items, 0, "inventory"); }
}