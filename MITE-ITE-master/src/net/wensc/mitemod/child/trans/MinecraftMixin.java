package net.wensc.mitemod.child.trans;

import net.minecraft.*;
import net.wensc.mitemod.child.packet.PacketToggleChild;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public class MinecraftMixin {
    @Shadow
    public aul u;

    @Shadow
    public ClientPlayer h;

    @Inject(method = "k", at = @At(value = "INVOKE", target = "Lnet/minecraft/Minecraft;inDevMode()Z", shift =  At.Shift.BEFORE, ordinal = 2))
    public void onTick(CallbackInfo ci) {
        if (this.u.keyBindChild.c()) {
            this.h.toggleChild();
            this.h.sendPacket((new PacketToggleChild()));
        }
    }
}
