package net.wensc.mitemod.child.trans;

import net.minecraft.EntityLiving;
import net.minecraft.EntityPlayer;
import net.minecraft.EntityZombie;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value = EntityLiving.class, priority = 2000)
public class EntityLivingBaseMixin {
    @Redirect(method = "onDeathUpdate", at = @At(value = "INVOKE", target = "Lnet/minecraft/EntityLiving;isChild()Z"))
    public boolean isChildOnDeathUpdate(EntityLiving entityLiving) {
        // 解决缩小后不掉落经验：此在某些mod注入了同一个注入点，某的小僵尸修改了这个属性导致注入失败，上面追加了priority
        if(entityLiving.isEntityPlayer()){
            return false;
        }
        return entityLiving.isChild();
    }

    @Redirect(method = "onDeath", at = @At(value = "INVOKE", target = "Lnet/minecraft/EntityLiving;isChild()Z"))
    public boolean isChildOnDeath(EntityLiving entityLiving) {
        // 解决缩小后不掉落物品问题
        if(entityLiving.isEntityPlayer()){
            return false;
        }
        return entityLiving.isChild();
    }
}
