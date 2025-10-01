package net.wensc.mitemod.child.trans;

import net.minecraft.EntityPlayer;
import net.minecraft.PlayerAbilities;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerAbilities.class)
public class PlayerCapabilitiesMixin {
    @Shadow
    public EntityPlayer player;
    @Inject(method = "getWalkSpeed", at = @At("RETURN"), cancellable = true)
    public void getWalkSpeed(CallbackInfoReturnable<Float> cir) {
        if (player.isChild()) {
            cir.setReturnValue(cir.getReturnValue() * 0.5F);
            cir.cancel();
        }
    }
}
