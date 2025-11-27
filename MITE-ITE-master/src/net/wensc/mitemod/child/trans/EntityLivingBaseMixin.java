package net.wensc.mitemod.child.trans;

import net.minecraft.EntityLiving;
import net.minecraft.EntityPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(EntityLiving.class)
public class EntityLivingBaseMixin {
    @Redirect(method = "onDeathUpdate", at = @At(value = "INVOKE", target = "Lnet/minecraft/EntityLiving;isChild()Z"))
    public boolean isChild(EntityLiving entityLiving) {
        if(entityLiving instanceof EntityPlayer){
            return false;
        }
        return entityLiving.isChild();
    }
}
