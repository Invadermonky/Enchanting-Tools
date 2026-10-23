package com.sirsquidly.enchanter_tools.common;

import com.sirsquidly.enchanter_tools.config.ConfigParser;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.ItemStack;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

public class RuneType {
    private final String name;
    private String configStr;
    private ItemStack mimicItem;
    private float levelRequirementMult;
    private float costMult;
    private Set<Enchantment> enchantmentWhitelist;
    private Set<Enchantment> enchantmentBlacklist;

    public RuneType(String name, String configStr) {
        this.name = name;
        this.configStr = configStr;
        this.setDefaultValues();
    }

    public RuneType(String name) {
        this(name, "");
    }

    public void setDefaultValues()
    {
        this.mimicItem = ItemStack.EMPTY;
        this.levelRequirementMult = 1.0f;
        this.costMult = 1.0f;
        this.enchantmentWhitelist = new LinkedHashSet<>();
        this.enchantmentBlacklist = new LinkedHashSet<>();
    }

    public void init()
    {
        ConfigParser.parseAndLoadRuneConfig(this);
    }

    public String getName() {
        return name;
    }

    public String getRegistryName() {
        return this.name + "_lapis_rune";
    }

    public String getConfigStr() {
        return configStr;
    }

    public void setConfigStr(String configStr) {
        this.configStr = configStr;
    }

    public ItemStack getMimicItem() {
        //Copying the mimic stack ensures other methods don't accidentally change the value
        return mimicItem.copy();
    }

    public void setMimicItem(ItemStack mimicItem) {
        this.mimicItem = mimicItem;
    }

    public float getLevelRequirementMult() {
        return levelRequirementMult;
    }

    public void setLevelRequirementMult(float levelRequirementMult) {
        this.levelRequirementMult = levelRequirementMult;
    }

    public float getCostMult() {
        return costMult;
    }

    public void setCostMult(float costMult) {
        this.costMult = costMult;
    }

    public Set<Enchantment> getEnchantmentWhitelist() {
        return enchantmentWhitelist;
    }

    public void setEnchantmentWhitelist(Set<Enchantment> enchantmentWhitelist) {
        this.enchantmentWhitelist = enchantmentWhitelist;
    }

    public Set<Enchantment> getEnchantmentBlacklist() {
        return enchantmentBlacklist;
    }

    public void setEnchantmentBlacklist(Set<Enchantment> enchantmentBlacklist) {
        this.enchantmentBlacklist = enchantmentBlacklist;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof RuneType))
            return false;
        RuneType runeType = (RuneType) object;
        return Objects.equals(getName(), runeType.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getName());
    }
}
