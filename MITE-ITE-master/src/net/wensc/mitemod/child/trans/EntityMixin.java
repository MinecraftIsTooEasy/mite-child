package net.wensc.mitemod.child.trans;

import net.minecraft.Entity;
import net.minecraft.EntityPlayer;
import net.xiaoyu233.fml.util.ReflectHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public class EntityMixin {
    @Inject(method = "isEntityInsideOpaqueBlock", at = @At("RETURN"), cancellable = true)
    public void isEntityInsideOpaqueBlock(CallbackInfoReturnable<Boolean> cir) {
        if(ReflectHelper.dyCast(Entity.class, this) instanceof EntityPlayer){
            if(((EntityPlayer) ReflectHelper.dyCast(Entity.class, this)).isChild()){
                cir.setReturnValue(false);
                cir.cancel();
            }
        }
    }
}
