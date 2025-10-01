package net.wensc.mitemod.child.trans;

import net.minecraft.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static net.wensc.mitemod.child.util.Constant.neakViewOffset;

@Mixin(EntityPlayer.class)
public abstract class EntityPlayerMixin extends EntityLiving {
    int watcher4;

    @Shadow
    public void setSizeProne() {}
    @Shadow
    public void setSizeNormal(){};

    public void setSizeChild()
    {
        this.setSize(0.3F, 0.9F);
    }

    public EntityPlayerMixin(World par1World) {
        super(par1World);
    }

    @Inject(method = "onLivingUpdate", at = @At("HEAD"))
    public void onLivingUpdate(CallbackInfo ci) {
        if (!this.worldObj.isRemote) {
            if(this.isChild()){
                this.setSizeProne();
            }
        }
        if(this.isChild()){
            this.jumpMovementFactor *= 0.5F;
        }
    }
    public boolean isChild() {
        return this.dataWatcher.getWatchableObjectByte(watcher4) == 1;
    }

    @Inject(method = "getEyePosY", at = @At("RETURN"), cancellable = true)
    public void getEyePosY(CallbackInfoReturnable<Double> cir) {
        if(this.isChild()) {
            cir.setReturnValue(cir.getReturnValueD() - neakViewOffset);
            cir.cancel();
        }
    }

    @Inject(method = "entityInit", at = @At("RETURN"))
    public void injectDataWatcher(CallbackInfo ci) {
        this.watcher4 = this.dataWatcher.addObject(this.dataWatcher.getNextAvailableId(), Byte.valueOf((byte)0));
    }

    @Inject(method = "readEntityFromNBT", at = @At("RETURN"))
    public void injectReadEntityFromNBT(NBTTagCompound par1NBTTagCompound, CallbackInfo ci) {
        if(par1NBTTagCompound.hasKey("isChild")) {
            this.dataWatcher.updateObject(watcher4, Byte.valueOf(par1NBTTagCompound.getByte("isChild")));
            if(this.isChild()){
                setSizeChild();
            } else {
                setSizeNormal();
            }
        }
    }

    @Inject(method = "writeEntityToNBT", at = @At("RETURN"))
    public void injectWriteEntityToNBT(NBTTagCompound par1NBTTagCompound, CallbackInfo ci) {
        par1NBTTagCompound.setByte("isChild", this.dataWatcher.getWatchableObjectByte(watcher4));
    }

    public void toggleChild(){
        byte var3 = this.dataWatcher.getWatchableObjectByte(watcher4);
        if(var3 == 1){
            this.dataWatcher.updateObject(watcher4, Byte.valueOf((byte)0));
            this.setSizeNormal();
        } else {
            if(this.ridingEntity == null) {
                this.dataWatcher.updateObject(watcher4, Byte.valueOf((byte) 1));
                this.setSizeChild();
            }
        }
    }
}
