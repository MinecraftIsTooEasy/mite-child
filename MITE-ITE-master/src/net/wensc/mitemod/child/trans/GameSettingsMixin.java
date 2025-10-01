package net.wensc.mitemod.child.trans;

import net.minecraft.ats;
import net.minecraft.aul;
import org.lwjgl.input.Keyboard;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Arrays;

@Mixin(aul.class)
public class GameSettingsMixin {

    public ats keyBindChild = new ats("key.child", Keyboard.KEY_C);
    @Shadow
    public ats[] W;

    @Shadow
    public ats S;

    @Inject(method = "<init>(Lnet/minecraft/Minecraft;Ljava/io/File;)V", at =  @At("RETURN"))
    public void init(CallbackInfo ci) {
        W = Arrays.copyOf(W, W.length + 1);
        W[W.length - 1] = keyBindChild;
        System.out.println(W);
    }

}
