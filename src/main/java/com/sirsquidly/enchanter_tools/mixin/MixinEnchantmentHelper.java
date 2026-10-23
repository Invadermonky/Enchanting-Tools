package com.sirsquidly.enchanter_tools.mixin;

import net.minecraft.enchantment.EnchantmentHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

/**
 * An ENTIRE MIXIN to make a Vanilla Method NOT try preforming math with a Power of 0.
 * Wow this sucks.
 * */
@Mixin(EnchantmentHelper.class)
public class MixinEnchantmentHelper
{
    @ModifyVariable(method = "calcItemStackEnchantability", at = @At("HEAD"), ordinal = 1)
    private static int enchantertools$preventNegativePower(int power)
    {
        return Math.max(power, 0);
    }
}
