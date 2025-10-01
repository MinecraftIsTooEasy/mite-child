package net.wensc.mitemod.child.trans;

import net.minecraft.Connection;
import net.minecraft.Minecraft;
import net.minecraft.NetClientHandler;
import net.wensc.mitemod.child.packet.PacketToggleChild;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.Inject;

@Mixin(NetClientHandler.class)
public abstract class NetClientHandlerMixin extends Connection {
    @Shadow
    private Minecraft h;
    public void handleToggleChild(PacketToggleChild packetToggleChild) {
        this.unexpectedPacket(packetToggleChild);
    }

}
