package com.qsided.classesmod.keybind;

import org.lwjgl.glfw.GLFW;

import com.qsided.classesmod.ClassesMod;
import com.qsided.classesmod.gui.GuiElement;
import com.qsided.classesmod.gui.OpenGUI;
import com.qsided.classesmod.network.KeyBindingPressedMessage;
import com.qsided.classesmod.network.PacketHandler;
import com.qsided.classesmod.playerClasses.ClassesSavedData;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;

@GuiElement.Tag
public class OpenClasses extends GuiElement {
	@OnlyIn(Dist.CLIENT)
	public KeyBinding keys;

	public OpenClasses(ClassesMod instance) {
		super(instance, 5);
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public void initElements() {
		keys = new KeyBinding("key.classes", GLFW.GLFW_KEY_RIGHT_ALT, "category.classes");
		ClientRegistry.registerKeyBinding(keys);
		MinecraftForge.EVENT_BUS.register(this);
	}

	// TODO We should not ever use Minecraft.getInstance(), it can cause resource
	// leaks - find another way
	@SubscribeEvent
	@OnlyIn(Dist.CLIENT)
	public void onKeyInput(InputEvent.KeyInputEvent event) {
		if (Minecraft.getInstance().currentScreen == null) {
			if (event.getKey() == keys.getKey().getKeyCode()) {
				if (event.getAction() == GLFW.GLFW_PRESS) {
					PacketHandler.HANDLER.sendToServer(new KeyBindingPressedMessage(0, 0));
					pressAction(Minecraft.getInstance().player, 0, 0);
				}
			}
		}
	}

	public static void pressAction(PlayerEntity entity, int type, int pressedms) {
		World world = entity.world;
		double x = entity.getPosX();
		double y = entity.getPosY();
		double z = entity.getPosZ();
		if (!world.isBlockLoaded(new BlockPos(x, y, z)))
			return;
		if (type == 0) {
			if (entity instanceof ServerPlayerEntity) {
				ServerPlayerEntity player = (ServerPlayerEntity) entity;
				if (ClassesSavedData.get(player.server).getPlayerClass(player).equals("NO_CLASS")) {
					OpenGUI.openGui(player);
				} // else {
					// if
					// (ClassesSavedData.get(player.server).getPlayerClass(player).equals("FIGHTER"))
					// {
					// if
					// (ClassesSavedData.get(player.server).getPlayerSubclass(player).equals("NO_SUBCLASS"))
					// {
					// OpenGUI.openGui2(dependencies);
					// }
					// }
			}
		}
	}
}
