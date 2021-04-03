package com.qsided.classesmod.playerClasses;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.qsided.classesmod.ClassesMod;
import harmonised.pmmo.config.Config;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.fml.loading.FMLPaths;
import net.minecraftforge.items.ItemHandlerHelper;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

import static harmonised.pmmo.skills.Skill.*;
import static net.minecraft.item.Items.*;

public class ClassSelected extends ClassesMod.GuiElement {

	public static String fileName = "classes" + ".json";
	public static final String path = "classes/";

	public static File file = FMLPaths.CONFIGDIR.get().resolve(path + fileName).toFile();

	public static GsonBuilder builder = new GsonBuilder();
	public static Gson gson = builder.setPrettyPrinting().create();

	public static BufferedReader br;

	static {
		try {
			br = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public ClassSelected(ClassesMod instance) {
		super(instance, 2);
	}

	public static String selectedClass = new TranslationTextComponent("class.selected.true").getString();

	public static void setPlayerClass(PlayerClass playerClass, Entity entity) {

		if (entity instanceof ServerPlayerEntity) {
			ServerPlayerEntity player = (ServerPlayerEntity) entity;

			String currentClass = ClassesSavedData.get(player.server).getPlayerClass(player);

			if (currentClass.equals("NO_CLASS")) {

				ClassesSavedData.get(player.server).setPlayerClass(player, playerClass.className);

				playerClass.applyBoosters(player);
				playerClass.giveItems(player);

				player.sendStatusMessage(new StringTextComponent(
						TextFormatting.GRAY + "You selected class: " + TextFormatting.RED + playerClass.className),
						false);
			} else {
				player.sendStatusMessage(new StringTextComponent(TextFormatting.DARK_RED + selectedClass), false);
			}
			player.closeScreen();

		}
	}
}
