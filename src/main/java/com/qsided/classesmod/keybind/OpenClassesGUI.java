package com.qsided.classesmod.keybind;

import com.qsided.classesmod.ClassesMod;
import com.qsided.classesmod.gui.OpenGUI;
import com.qsided.classesmod.playerClasses.ClassesSavedData;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.ServerPlayerEntity;
import org.lwjgl.glfw.GLFW;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

@ClassesMod.GuiElement.Tag
class OpenClasses extends ClassesMod.GuiElement {
    @OnlyIn(Dist.CLIENT)
    public KeyBinding keys;
    public OpenClasses(ClassesMod instance) {
        super(instance, 5);
        guiElements.addNetworkMessage(KeyBindingPressedMessage.class, KeyBindingPressedMessage::buffer, KeyBindingPressedMessage::new,
                KeyBindingPressedMessage::handler);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void initElements() {
        keys = new KeyBinding("key.classes", GLFW.GLFW_KEY_RIGHT_ALT, "category.classes");
        ClientRegistry.registerKeyBinding(keys);
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    public void onKeyInput(InputEvent.KeyInputEvent event) {
        if (Minecraft.getInstance().currentScreen == null) {
            if (event.getKey() == keys.getKey().getKeyCode()) {
                if (event.getAction() == GLFW.GLFW_PRESS) {
                    ClassesMod.PACKET_HANDLER.sendToServer(new KeyBindingPressedMessage(0, 0));
                    pressAction(Minecraft.getInstance().player, 0, 0);
                }
            }
        }
    }
    public static class KeyBindingPressedMessage {
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

        public static void handler(KeyBindingPressedMessage message, Supplier<NetworkEvent.Context> contextSupplier) {
            NetworkEvent.Context context = contextSupplier.get();
            context.enqueueWork(() -> {
                pressAction(context.getSender(), message.type, message.pressedms);
            });
            context.setPacketHandled(true);
        }
    }
    private static void pressAction(PlayerEntity entity, int type, int pressedms) {
        World world = entity.world;
        double x = entity.getPosX();
        double y = entity.getPosY();
        double z = entity.getPosZ();
        if (!world.isBlockLoaded(new BlockPos(x, y, z)))
            return;
        if (type == 0) {
            Map<String, Object> dependencies = new HashMap<>();
            dependencies.put("entity", entity);
            dependencies.put("x", x);
            dependencies.put("y", y);
            dependencies.put("z", z);
            dependencies.put("world", world);
            //---------------------------------------------------------------------------------------
            Entity entity1 = (Entity) dependencies.get("entity");
            //---------------------------------------------------------------------------------------
            if (entity1 instanceof ServerPlayerEntity) {
                ServerPlayerEntity player = (ServerPlayerEntity) entity1;
                if (ClassesSavedData.get(player.server).getPlayerClass(player).equals("NO_CLASS")) {
                    OpenGUI.openGui(dependencies);
                }// else {
                 //   if (ClassesSavedData.get(player.server).getPlayerClass(player).equals("FIGHTER")) {
                 //       if (ClassesSavedData.get(player.server).getPlayerSubclass(player).equals("NO_SUBCLASS")) {
                 //           OpenGUI.openGui2(dependencies);
                 //       }
                 //   }
                }
            }
        }
    }


