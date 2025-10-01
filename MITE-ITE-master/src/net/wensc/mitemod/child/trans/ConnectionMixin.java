package net.wensc.mitemod.child.trans;

import net.minecraft.Connection;
import net.minecraft.Packet;
import net.minecraft.Packet3Chat;
import net.wensc.mitemod.child.packet.PacketToggleChild;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(Connection.class)
public class ConnectionMixin {
    @Shadow
    public void unexpectedPacket(Packet par1Packet) {}
    public void handleToggleChild(PacketToggleChild packetToggleChild) {
        this.unexpectedPacket(packetToggleChild);
    }
}
