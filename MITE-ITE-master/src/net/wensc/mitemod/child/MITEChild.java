package net.wensc.mitemod.child;


import net.wensc.mitemod.child.events.EventListeners;
import net.wensc.mitemod.child.trans.TransMarker;
import net.xiaoyu233.fml.AbstractMod;

import net.xiaoyu233.fml.classloading.Mod;
import net.xiaoyu233.fml.config.InjectionConfig;
import org.spongepowered.asm.mixin.MixinEnvironment;

@Mod
public class MITEChild extends AbstractMod {

    public MITEChild() {
    }

    public void preInit() {
    }


    @Override
    public InjectionConfig getInjectionConfig() {
        return InjectionConfig.Builder.of("mite-child", TransMarker.class.getPackage(), MixinEnvironment.Phase.INIT).setRequired().build();
    }


    public void postInit() {
        super.postInit();
        EventListeners.registerAllEvents();
    }


    public String modId() {
        return "mite-child";
    }

    public int modVerNum() {
        return 1;
    }

    public String modVerStr() {
        return "0.0.1";
    }
}
