package net.wensc.mitemod.child.trans;

import net.minecraft.Entity;
import net.minecraft.EntityArrow;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(EntityArrow.class)
public class EntityArrowMixin {

    @Shadow
    public Entity shootingEntity;

    // 这个偏移量本质上是给箭加了一个前移 / 出生点前推"的效果，本意是把箭的生成位置从射手身体里往外挪一点，让它不要再出生在射手自己的包围盒内。对正常体型玩家（宽约 0.6、高约 1.8，包围盒容差大），这 1 格偏差通常仍落在命中范围内；但对极小 hitbox 的缩小玩家，这条线就完全从包围盒外侧擦过，performVsEntities 的线段与它永不相交 → 判定未命中。
    @Redirect(
        method = "<init>(Lnet/minecraft/World;Lnet/minecraft/EntityLiving;Lnet/minecraft/EntityLiving;FFLnet/minecraft/ItemArrow;Z)V",
        at = @At(value = "INVOKE", target = "Lnet/minecraft/EntityArrow;setLocationAndAngles(DDDFF)V")
    )
    private void redirectSetLocationAndAngles(EntityArrow instance, double x, double y, double z, float yaw, float pitch) {
        Entity shooter = this.shootingEntity;
        if (shooter != null) {
            instance.setLocationAndAngles(shooter.posX, y, shooter.posZ, yaw, pitch);
        } else {
            // 兜底：理论上不会走到这里（shootingEntity 已先赋值）
            instance.setLocationAndAngles(x, y, z, yaw, pitch);
        }
    }
}
