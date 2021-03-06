package com.qsided.classesmod.gui;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

import org.lwjgl.opengl.GL11;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.qsided.classesmod.ClassesMod;
import com.qsided.classesmod.config.PlayerClassHandler;
import com.qsided.classesmod.network.ButtonPressedMessage;
import com.qsided.classesmod.network.PacketHandler;
import com.qsided.classesmod.playerClasses.ClassSelected;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.inventory.container.Slot;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DeferredWorkQueue;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.network.IContainerFactory;
import net.minecraftforge.fml.network.NetworkEvent;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

@GuiElement.Tag
public class ClassSelectorGui extends GuiElement {

	private static ContainerType<GuiContainerMod> containerType = null;

	public ClassSelectorGui(ClassesMod instance) {
		super(instance, 1);
		containerType = new ContainerType<>(new GuiContainerModFactory());
		FMLJavaModLoadingContext.get().getModEventBus().register(this);
	}

	@OnlyIn(Dist.CLIENT)
	public void initElements() {
		DeferredWorkQueue.runLater(() -> ScreenManager.registerFactory(containerType, GuiWindow::new));
	}

	@SubscribeEvent
	public void registerContainer(RegistryEvent.Register<ContainerType<?>> event) {
		event.getRegistry().register(containerType.setRegistryName("class_selector"));
	}

	public static class GuiContainerModFactory implements IContainerFactory {
		public GuiContainerMod create(int id, PlayerInventory inv, PacketBuffer extraData) {
			return new GuiContainerMod(id, inv, extraData);
		}
	}

	public static class GuiContainerMod extends Container implements Supplier<Map<Integer, Slot>> {
		private World world;
		private PlayerEntity entity;
		private int x, y, z;
		private IItemHandler internal;
		private Map<Integer, Slot> customSlots = new HashMap<>();
		private boolean bound = false;

		public GuiContainerMod(int id, PlayerInventory inv, PacketBuffer extraData) {
			super(containerType, id);
			this.entity = inv.player;
			this.world = inv.player.world;
			this.internal = new ItemStackHandler(0);
			BlockPos pos = null;
			if (extraData != null) {
				pos = extraData.readBlockPos();
				this.x = pos.getX();
				this.y = pos.getY();
				this.z = pos.getZ();
			}
		}

		public Map<Integer, Slot> get() {
			return customSlots;
		}

		@Override
		public boolean canInteractWith(PlayerEntity player) {
			return true;
		}
	}

	@OnlyIn(Dist.CLIENT)
	public static class GuiWindow extends ContainerScreen<GuiContainerMod> {
		private World world;
		private int x, y, z;
		private PlayerEntity entityPlayer;

		public GuiWindow(GuiContainerMod container, PlayerInventory inventory, ITextComponent text) {
			super(container, inventory, text);
			this.world = container.world;
			this.x = container.x;
			this.y = container.y;
			this.z = container.z;
			this.entityPlayer = container.entity;
			this.xSize = 400;
			this.ySize = 200;
		}

		@Override
		public boolean isPauseScreen() {
			return true;
		}

		@Override
		public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
			this.renderBackground(matrixStack);
			super.render(matrixStack, mouseX, mouseY, partialTicks);
			this.renderHoveredToolTip(mouseX, mouseY);
		}

		@Override
		protected void drawGuiContainerBackgroundLayer(MatrixStack matrixStack, float partialTicks, int mouseX,
				int mouseY) {
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		}

		private void renderHoveredToolTip(int mouseX, int mouseY) {
		}

		@Override
		public boolean keyPressed(int key, int b, int c) {
			if (key == 256) {
				entityPlayer.closeScreen();
				return true;
			}
			return super.keyPressed(key, b, c);
		}

		@Override
		public void tick() {
			super.tick();
		}

		@Override
		protected void drawGuiContainerForegroundLayer(MatrixStack matrixStack, int mouseX, int mouseY) {

			TranslationTextComponent translation = new TranslationTextComponent("gui.classes.title");

			TranslationTextComponent translation2 = new TranslationTextComponent("class.soldier.archery");
			TranslationTextComponent translation3 = new TranslationTextComponent("class.soldier.combat");
			TranslationTextComponent translation4 = new TranslationTextComponent("class.soldier.endurance");

			TranslationTextComponent translation5 = new TranslationTextComponent("class.freeman.excavation");
			TranslationTextComponent translation6 = new TranslationTextComponent("class.freeman.mining");
			TranslationTextComponent translation7 = new TranslationTextComponent("class.freeman.woodcutting");

			TranslationTextComponent translation8 = new TranslationTextComponent("class.survivalist.cooking");
			TranslationTextComponent translation9 = new TranslationTextComponent("class.survivalist.farming");
			TranslationTextComponent translation10 = new TranslationTextComponent("class.survivalist.fishing");

			TranslationTextComponent translation11 = new TranslationTextComponent("class.artisan.building");
			TranslationTextComponent translation12 = new TranslationTextComponent("class.artisan.crafting");
			TranslationTextComponent translation13 = new TranslationTextComponent("class.artisan.smithing");

			TranslationTextComponent translation14 = new TranslationTextComponent("class.adventurer.agility");
			TranslationTextComponent translation15 = new TranslationTextComponent("class.adventurer.swimming");
			TranslationTextComponent translation16 = new TranslationTextComponent("class.adventurer.flying");

			TranslationTextComponent translation17 = new TranslationTextComponent("class.mage.alchemy");
			TranslationTextComponent translation18 = new TranslationTextComponent("class.mage.combat");
			TranslationTextComponent translation19 = new TranslationTextComponent("class.mage.magic");

			String title = translation.getString();

			String archery = translation2.getString();
			String combat = translation3.getString();
			String endurance = translation4.getString();
			String excavation = translation5.getString();
			String mining = translation6.getString();
			String woodcutting = translation7.getString();
			String cooking = translation8.getString();
			String farming = translation9.getString();
			String fishing = translation10.getString();
			String building = translation11.getString();
			String crafting = translation12.getString();
			String smithing = translation13.getString();
			String agility = translation14.getString();
			String swimming = translation15.getString();
			String flying = translation16.getString();
			String alchemy = translation17.getString();
			String combat2 = translation18.getString();
			String magic = translation19.getString();

			// TODO change so that items are not hardcoded, pull the data from
			// PlayerClassHandler.getPlayerClasses()

			String freemanItem1 = "Item 1";
			String freemanItem2 = "Item 2";
			String soldierItem1 = "Item 1";
			String soldierItem2 = "Item 2";
			String survivalistItem1 = "Item 1";
			String survivalistItem2 = "Item 2";
			String artisanItem1 = "Item 1";
			String artisanItem2 = "Item 2";
			String adventurerItem1 = "Item 1";
			String adventurerItem2 = "Item 2";
			String mageItem1 = "Item 1";
			String mageItem2 = "Item 2";

			this.font.drawStringWithShadow(matrixStack, title,
					(float) (getXSize() * 0.5) - (this.font.getStringWidth(title) / 2), (float) (getYSize() * 0.08),
					-6684775);

			// Soldier
			this.font.drawStringWithShadow(matrixStack, "+1 " + soldierItem1,
					(float) (getXSize() * 0.175) - (this.font.getStringWidth("+1 " + soldierItem1) / 2), 54, -52429);
			this.font.drawStringWithShadow(matrixStack, "+1 " + soldierItem2,
					(float) (getXSize() * 0.175) - (this.font.getStringWidth("+1 " + soldierItem2) / 2), 64, -52429);
			this.font.drawStringWithShadow(matrixStack, "+5% " + archery,
					(float) (getXSize() * 0.175) - (this.font.getStringWidth("+5% " + archery) / 2), 74, -52429);
			this.font.drawStringWithShadow(matrixStack, "+5% " + combat,
					(float) (getXSize() * 0.175) - (this.font.getStringWidth("+5% " + combat) / 2), 84, -52429);
			this.font.drawStringWithShadow(matrixStack, "+5% " + endurance,
					(float) (getXSize() * 0.175) - (this.font.getStringWidth("+5% " + endurance) / 2), 94, -52429);

			// Freeman
			this.font.drawStringWithShadow(matrixStack, "+1 " + freemanItem1,
					(float) (getXSize() * 0.5) - (this.font.getStringWidth("+1 " + freemanItem1) / 2), 54, -10027009);
			this.font.drawStringWithShadow(matrixStack, "+1 " + freemanItem2,
					(float) (getXSize() * 0.5) - (this.font.getStringWidth("+1 " + freemanItem2) / 2), 64, -10027009);
			this.font.drawStringWithShadow(matrixStack, "+5% " + excavation,
					(float) (getXSize() * 0.5) - (this.font.getStringWidth("+5% " + excavation) / 2), 74, -10027009);
			this.font.drawStringWithShadow(matrixStack, "+5% " + mining,
					(float) (getXSize() * 0.5) - (this.font.getStringWidth("+5% " + mining) / 2), 84, -10027009);
			this.font.drawStringWithShadow(matrixStack, "+5% " + woodcutting,
					(float) (getXSize() * 0.5) - (this.font.getStringWidth("+5% " + woodcutting) / 2), 94, -10027009);

			// Survivalist
			this.font.drawStringWithShadow(matrixStack, "+1 " + survivalistItem1,
					(float) (getXSize() * 0.825) - (this.font.getStringWidth("+1 " + survivalistItem1) / 2), 54,
					-26368);
			this.font.drawStringWithShadow(matrixStack, "+12 " + survivalistItem2,
					(float) (getXSize() * 0.825) - (this.font.getStringWidth("+12 " + survivalistItem2) / 2), 64,
					-26368);
			this.font.drawStringWithShadow(matrixStack, "+5% " + cooking,
					(float) (getXSize() * 0.825) - (this.font.getStringWidth("+5% " + cooking) / 2), 74, -26368);
			this.font.drawStringWithShadow(matrixStack, "+5% " + farming,
					(float) (getXSize() * 0.825) - (this.font.getStringWidth("+5% " + farming) / 2), 84, -26368);
			this.font.drawStringWithShadow(matrixStack, "+5% " + fishing,
					(float) (getXSize() * 0.825) - (this.font.getStringWidth("+5% " + fishing) / 2), 94, -26368);

			// Artisan
			this.font.drawStringWithShadow(matrixStack, "+1 " + artisanItem1,
					(float) (getXSize() * 0.175) - (this.font.getStringWidth("+1 " + artisanItem1) / 2), 134, -205);
			this.font.drawStringWithShadow(matrixStack, "+12 " + artisanItem2,
					(float) (getXSize() * 0.175) - (this.font.getStringWidth("+12 " + artisanItem2) / 2), 144, -205);
			this.font.drawStringWithShadow(matrixStack, "+5% " + building,
					(float) (getXSize() * 0.175) - (this.font.getStringWidth("+5% " + building) / 2), 154, -205);
			this.font.drawStringWithShadow(matrixStack, "+5% " + crafting,
					(float) (getXSize() * 0.175) - (this.font.getStringWidth("+5% " + crafting) / 2), 164, -205);
			this.font.drawStringWithShadow(matrixStack, "+5% " + smithing,
					(float) (getXSize() * 0.175) - (this.font.getStringWidth("+5% " + smithing) / 2), 174, -205);

			// Adventurer
			this.font.drawStringWithShadow(matrixStack, "+5 " + adventurerItem1,
					(float) (getXSize() * 0.5) - (this.font.getStringWidth("+5 " + adventurerItem1) / 2), 134,
					-16715008);
			this.font.drawStringWithShadow(matrixStack, "+1 " + adventurerItem2,
					(float) (getXSize() * 0.5) - (this.font.getStringWidth("+1 " + adventurerItem2) / 2), 144,
					-16715008);
			this.font.drawStringWithShadow(matrixStack, "+5% " + agility,
					(float) (getXSize() * 0.5) - (this.font.getStringWidth("+5% " + agility) / 2), 154, -16715008);
			this.font.drawStringWithShadow(matrixStack, "+5% " + swimming,
					(float) (getXSize() * 0.5) - (this.font.getStringWidth("+5% " + swimming) / 2), 164, -16715008);
			this.font.drawStringWithShadow(matrixStack, "+5% " + flying,
					(float) (getXSize() * 0.5) - (this.font.getStringWidth("+5% " + flying) / 2), 174, -16715008);

			// Mage
			this.font.drawStringWithShadow(matrixStack, "+4 " + mageItem1,
					(float) (getXSize() * 0.825) - (this.font.getStringWidth("+4 " + mageItem1) / 2), 134, 3685372);
			this.font.drawStringWithShadow(matrixStack, "+12 " + mageItem2,
					(float) (getXSize() * 0.825) - (this.font.getStringWidth("+12 " + mageItem2) / 2), 144, 3685372);
			this.font.drawStringWithShadow(matrixStack, "+5% " + alchemy,
					(float) (getXSize() * 0.825) - (this.font.getStringWidth("+5% " + alchemy) / 2), 154, 3685372);
			this.font.drawStringWithShadow(matrixStack, "+5% " + combat2,
					(float) (getXSize() * 0.825) - (this.font.getStringWidth("+5% " + combat2) / 2), 164, 3685372);
			this.font.drawStringWithShadow(matrixStack, "+5% " + magic,
					(float) (getXSize() * 0.825) - (this.font.getStringWidth("+5% " + magic) / 2), 174, 3685372);
		}

		@Override
		public void init(Minecraft minecraft, int width, int height) {
			super.init(minecraft, width, height);
			minecraft.keyboardListener.enableRepeatEvents(true);

			TranslationTextComponent translation20 = new TranslationTextComponent("class.soldier");
			TranslationTextComponent translation21 = new TranslationTextComponent("class.freeman");
			TranslationTextComponent translation22 = new TranslationTextComponent("class.survivalist");
			TranslationTextComponent translation23 = new TranslationTextComponent("class.artisan");
			TranslationTextComponent translation24 = new TranslationTextComponent("class.adventurer");
			TranslationTextComponent translation25 = new TranslationTextComponent("class.mage");

			String soldier = translation20.getString();
			String freeman = translation21.getString();
			String survivalist = translation22.getString();
			String artisan = translation23.getString();
			String adventurer = translation24.getString();
			String mage = translation25.getString();

			this.addButton(new Button((getGuiLeft() + (int) (float) (getXSize() * 0.175) - (100 / 2)), this.guiTop + 32,
					100, 20, new StringTextComponent(TextFormatting.BOLD + soldier), e -> {
						PacketHandler.HANDLER.sendToServer(new ButtonPressedMessage(0, x, y, z));
						handleButtonAction(entityPlayer, 0, x, y, z);
					}));
			this.addButton(new Button((getGuiLeft() + (int) (float) (getXSize() * 0.5) - (100 / 2)), this.guiTop + 32,
					100, 20, new StringTextComponent(TextFormatting.BOLD + freeman), e -> {
						PacketHandler.HANDLER.sendToServer(new ButtonPressedMessage(1, x, y, z));
						handleButtonAction(entityPlayer, 1, x, y, z);
					}));
			this.addButton(new Button((getGuiLeft() + (int) (float) (getXSize() * 0.825) - (100 / 2)), this.guiTop + 32,
					100, 20, new StringTextComponent(TextFormatting.BOLD + survivalist), e -> {
						PacketHandler.HANDLER.sendToServer(new ButtonPressedMessage(2, x, y, z));
						handleButtonAction(entityPlayer, 2, x, y, z);
					}));
			this.addButton(new Button((getGuiLeft() + (int) (float) (getXSize() * 0.175) - (100 / 2)),
					this.guiTop + 112, 100, 20, new StringTextComponent(TextFormatting.BOLD + artisan), e -> {
						PacketHandler.HANDLER.sendToServer(new ButtonPressedMessage(3, x, y, z));
						handleButtonAction(entityPlayer, 3, x, y, z);
					}));
			this.addButton(new Button((getGuiLeft() + (int) (float) (getXSize() * 0.5) - (100 / 2)), this.guiTop + 112,
					100, 20, new StringTextComponent(TextFormatting.BOLD + adventurer), e -> {
						PacketHandler.HANDLER.sendToServer(new ButtonPressedMessage(4, x, y, z));
						handleButtonAction(entityPlayer, 4, x, y, z);
					}));
			this.addButton(new Button((getGuiLeft() + (int) (float) (getXSize() * 0.825) - (100 / 2)),
					this.guiTop + 112, 100, 20, new StringTextComponent(TextFormatting.BOLD + mage), e -> {
						PacketHandler.HANDLER.sendToServer(new ButtonPressedMessage(5, x, y, z));
						handleButtonAction(entityPlayer, 5, x, y, z);
					}));
		}
	}

	public static class GUISlotChangedMessage {
		int slotID, x, y, z, changeType, meta;

		public GUISlotChangedMessage(int slotID, int x, int y, int z, int changeType, int meta) {
			this.slotID = slotID;
			this.x = x;
			this.y = y;
			this.z = z;
			this.changeType = changeType;
			this.meta = meta;
		}

		public GUISlotChangedMessage(PacketBuffer buffer) {
			this.slotID = buffer.readInt();
			this.x = buffer.readInt();
			this.y = buffer.readInt();
			this.z = buffer.readInt();
			this.changeType = buffer.readInt();
			this.meta = buffer.readInt();
		}

		public static void buffer(GUISlotChangedMessage message, PacketBuffer buffer) {
			buffer.writeInt(message.slotID);
			buffer.writeInt(message.x);
			buffer.writeInt(message.y);
			buffer.writeInt(message.z);
			buffer.writeInt(message.changeType);
			buffer.writeInt(message.meta);
		}

		public static void handler(GUISlotChangedMessage message, Supplier<NetworkEvent.Context> contextSupplier) {
			NetworkEvent.Context context = contextSupplier.get();
			context.enqueueWork(() -> {
				PlayerEntity entity = context.getSender();
				int slotID = message.slotID;
				int changeType = message.changeType;
				int meta = message.meta;
				int x = message.x;
				int y = message.y;
				int z = message.z;
				handleSlotAction(entity, slotID, changeType, meta, x, y, z);
			});
			context.setPacketHandled(true);
		}
	}

	// TODO the rest and check whether we really need block pos
	private static void handleButtonAction(PlayerEntity entity, int buttonID, int x, int y, int z) {
		World world = entity.world;
		if (!world.isBlockLoaded(new BlockPos(x, y, z)))
			return;
		if (buttonID == 0) {
			{
				ClassSelected.setPlayerClass(PlayerClassHandler.playerClasses.get(0), entity);
			}
		}
		if (buttonID == 1) {
			{
				Map<String, Object> dependencies1 = new HashMap<>();
				dependencies1.put("entity", entity);
				ClassSelected.setPlayerClass(PlayerClassHandler.playerClasses.get(1), entity);
			}
		}
		if (buttonID == 2) {
			{
				ClassSelected.setPlayerClass(PlayerClassHandler.playerClasses.get(2), entity);
			}
		}
		if (buttonID == 3) {
			{
				Map<String, Object> dependencies1 = new HashMap<>();
				dependencies1.put("entity", entity);
				ClassSelected.setPlayerClass(PlayerClassHandler.playerClasses.get(3), entity);
			}
		}
		if (buttonID == 4) {
			{
				Map<String, Object> dependencies1 = new HashMap<>();
				dependencies1.put("entity", entity);
				ClassSelected.setPlayerClass(PlayerClassHandler.playerClasses.get(4), entity);
			}
		}
		if (buttonID == 5) {
			{
				Map<String, Object> dependencies1 = new HashMap<>();
				dependencies1.put("entity", entity);
				ClassSelected.setPlayerClass(PlayerClassHandler.playerClasses.get(5), entity);
			}
		}
	}

	// TODO is this even needed??
	private static void handleSlotAction(PlayerEntity entity, int slotID, int changeType, int meta, int x, int y,
			int z) {
		World world = entity.world;
		if (!world.isBlockLoaded(new BlockPos(x, y, z)))
			return;
	}
}
