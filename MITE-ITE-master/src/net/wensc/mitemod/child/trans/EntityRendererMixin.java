package net.wensc.mitemod.child.trans;

import net.minecraft.EntityLiving;
import net.minecraft.EntityRenderer;
import net.minecraft.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import static net.wensc.mitemod.child.util.Constant.neakViewOffset;

@Mixin(EntityRenderer.class)
public class EntityRendererMixin {
    @Shadow
    private Minecraft q;
    @ModifyVariable(method = "g(F)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/ClientPlayer;isSneaking()Z", shift = At.Shift.BEFORE), ordinal = 1)
    private float modifyFloatVariable(float var3) {
        if(this.q.h.isChild()){
            var3 -= -neakViewOffset;
            return var3;
        }
        return var3;
    }
}
