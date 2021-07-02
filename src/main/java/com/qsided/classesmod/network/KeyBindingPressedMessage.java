package com.qsided.classesmod.network;

import java.util.function.Supplier;

import com.qsided.classesmod.keybind.OpenClasses;

import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

public class KeyBindingPressedMessage {
	int type, pressedms;

	public KeyBindingPressedMessage(int type, int pressedms) {
		this.type = type;
		this.pressedms = pressedms;
	}

	public KeyBindingPressedMessage(PacketBuffer buffer) {
		this.type = buffer.readInt();
		this.pressedms = buffer.readInt();
	}

	public static void buffer(KeyBindingPressedMessage message, PacketBuffer buffer) {
		buffer.writeInt(message.type);
		buffer.writeInt(message.pressedms);
	}

	public static void handle(KeyBindingPressedMessage message, Supplier<NetworkEvent.Context> contextSupplier) {
		NetworkEvent.Context context = contextSupplier.get();
		context.enqueueWork(() -> {
			OpenClasses.pressAction(context.getSender(), message.type, message.pressedms);
		});
		context.setPacketHandled(true);
	}
}
