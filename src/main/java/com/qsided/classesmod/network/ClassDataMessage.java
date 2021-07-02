package com.qsided.classesmod.network;

import java.util.function.Supplier;

import com.qsided.classesmod.config.PlayerClassHandler;

import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;

public class ClassDataMessage {
	public final String data;

	public ClassDataMessage(PacketBuffer buf) {
		this.data = buf.readString();
	}

	public ClassDataMessage(String data) {
		this.data = data;
	}

	public void encode(PacketBuffer buf) {
		buf.writeString(data);
	}

	public static void handleClassData(ClassDataMessage message, Supplier<NetworkEvent.Context> ctx) {
		if (ctx.get().getDirection() == NetworkDirection.PLAY_TO_SERVER) {
			// Server sending to client
			// Nothing actually needs to be done here any more, because we send the data
			// straight away when the player joins the server
			// ctx.get().enqueueWork(() -> {}); // Left here just in case...
		} else if (ctx.get().getDirection() == NetworkDirection.PLAY_TO_CLIENT) {
			ctx.get().enqueueWork(() -> {
				// Client processing message
				PlayerClassHandler.decode(message.data);
			});
		}
		ctx.get().setPacketHandled(true);
	}
}
