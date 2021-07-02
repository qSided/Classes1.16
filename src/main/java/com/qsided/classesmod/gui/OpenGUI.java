package com.qsided.classesmod.gui;

import io.netty.buffer.Unpooled;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.fml.network.NetworkHooks;

@GuiElement.Tag
public class OpenGUI {
	public static void openGui(Entity entity) {
		Entity _ent = entity;
		if (_ent instanceof ServerPlayerEntity) {
			BlockPos _bpos = new BlockPos((int) entity.getPosX(), (int) entity.getPosY(), (int) entity.getPosZ());
			NetworkHooks.openGui((ServerPlayerEntity) _ent, new INamedContainerProvider() {
				@Override
				public ITextComponent getDisplayName() {
					return new StringTextComponent("ClassSelector");
				}

				@Override
				public Container createMenu(int id, PlayerInventory inventory, PlayerEntity player) {
					return new ClassSelectorGui.GuiContainerMod(id, inventory,
							new PacketBuffer(Unpooled.buffer()).writeBlockPos(_bpos));
				}
			}, _bpos);
		}
	}

//	public static void openGui2(Map<String, Object> dependencies) {
//		Entity entity = (Entity) dependencies.get("entity");
//		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x")
//				: (double) dependencies.get("x");
//		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y")
//				: (double) dependencies.get("y");
//		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z")
//				: (double) dependencies.get("z");
//		IWorld world = (IWorld) dependencies.get("world");
//		{
//			Entity _ent = entity;
//			if (_ent instanceof ServerPlayerEntity) {
//				BlockPos _bpos = new BlockPos((int) x, (int) y, (int) z);
//				NetworkHooks.openGui((ServerPlayerEntity) _ent, new INamedContainerProvider() {
//					@Override
//					public ITextComponent getDisplayName() {
//						return new StringTextComponent("FighterSubclassSelector");
//					}
//
//					@Override
//					public Container createMenu(int id, PlayerInventory inventory, PlayerEntity player) {
//						return new ClassSelectorGui.GuiContainerMod(id, inventory,
//								new PacketBuffer(Unpooled.buffer()).writeBlockPos(_bpos));
//					}
//				}, _bpos);
//			}
//		}
//	}
}
