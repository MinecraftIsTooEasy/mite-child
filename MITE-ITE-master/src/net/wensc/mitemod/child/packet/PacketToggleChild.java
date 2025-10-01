package net.wensc.mitemod.child.packet;

import net.minecraft.Connection;
import net.minecraft.Packet;
import net.minecraft.server.MinecraftServer;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class PacketToggleChild extends Packet {
    private boolean isChild;
    public PacketToggleChild() {
    }

    @Override
    public void readPacketData(DataInput dataInput) throws IOException {
        this.isChild = dataInput.readBoolean();
    }

    @Override
    public void writePacketData(DataOutput dataOutput) throws IOException {
        dataOutput.writeBoolean(this.isChild);
    }

    @Override
    public void processPacket(Connection connection) {
        connection.handleToggleChild(this);
    }

    @Override
    public int getPacketSize() {
        return 1;
    }
}
