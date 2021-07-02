package com.qsided.classesmod.network;

import com.qsided.classesmod.ClassesMod;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

public class PacketHandler {

	public static int index = 0;

	private static final String PROTOCOL_VERSION = "1";
	public static final SimpleChannel HANDLER = NetworkRegistry.ChannelBuilder
			.named(new ResourceLocation(ClassesMod.MOD_ID, "main_channel"))
			.clientAcceptedVersions(PROTOCOL_VERSION::equals).serverAcceptedVersions(PROTOCOL_VERSION::equals)
			.networkProtocolVersion(() -> PROTOCOL_VERSION).simpleChannel();

	public static void register() {
		HANDLER.registerMessage(index++, KeyBindingPressedMessage.class, KeyBindingPressedMessage::buffer,
				KeyBindingPressedMessage::new, KeyBindingPressedMessage::handle);
		HANDLER.registerMessage(index++, ClassDataMessage.class, ClassDataMessage::encode, ClassDataMessage::new,
				ClassDataMessage::handleClassData);
		HANDLER.registerMessage(index++, ButtonPressedMessage.class, ButtonPressedMessage::buffer,
				ButtonPressedMessage::new, ButtonPressedMessage::handler);
		HANDLER.registerMessage(index++, GUISlotChangedMessage.class, GUISlotChangedMessage::buffer,
				GUISlotChangedMessage::new, GUISlotChangedMessage::handler);
	}

	public static void sendToClient(Object message, ServerPlayerEntity player) {
		HANDLER.sendTo(message, player.connection.getNetworkManager(), NetworkDirection.PLAY_TO_CLIENT);
	}

	public static void sendToServer(Object message) {
		HANDLER.sendToServer(message);
	}

}
