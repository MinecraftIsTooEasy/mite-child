package net.wensc.mitemod.child.trans;

import net.minecraft.Connection;
import net.minecraft.PlayerConnection;
import net.minecraft.ServerPlayer;
import net.wensc.mitemod.child.packet.PacketToggleChild;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(PlayerConnection.class)
public abstract class NetServerHandlerMixin extends Connection {
    @Shadow
    public ServerPlayer playerEntity;
    public void handleToggleChild(PacketToggleChild packetToggleChild) {
        playerEntity.toggleChild();
    }
}
