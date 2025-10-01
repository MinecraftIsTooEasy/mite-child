package net.wensc.mitemod.child.trans;

import net.minecraft.bex;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(bex.class)
public class ClientPlayerMixin {

    @Shadow
    protected boolean pushOutOfBlocks(double par1, double par3, double par5) {
        return false;
    }

    public boolean pushOutOfBlocksPublic(double par1, double par3, double par5){
        return pushOutOfBlocks(par1, par3, par5);
    }
    @Redirect(method = "onLivingUpdate", at = @At(value = "INVOKE", target = "Lnet/minecraft/bex;pushOutOfBlocks(DDD)Z"))
    public boolean pushOutOfBlocks(bex instance, double x, double y, double z) {
        if (instance.isChild()) {
            return false;
        } else {
            return instance.pushOutOfBlocksPublic(x, y, z);
        }
    }
}
