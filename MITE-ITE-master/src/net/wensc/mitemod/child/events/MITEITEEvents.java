package net.wensc.mitemod.child.events;

import com.google.common.eventbus.Subscribe;
import net.wensc.mitemod.child.packet.PacketToggleChild;
import net.xiaoyu233.fml.reload.event.LanguageResourceReloadEvent;
import net.xiaoyu233.fml.reload.event.PacketRegisterEvent;
import net.xiaoyu233.mitemod.miteite.network.*;

public class MITEITEEvents {
    @Subscribe
    public void onPacketRegister(PacketRegisterEvent event){
        event.register(196, false, true, PacketToggleChild.class);
    }
}
