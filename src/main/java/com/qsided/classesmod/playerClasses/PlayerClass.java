package com.qsided.classesmod.playerClasses;

import java.util.ArrayList;
import java.util.HashMap;

import harmonised.pmmo.config.Config;
import harmonised.pmmo.skills.Skill;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.ItemHandlerHelper;

public class PlayerClass {

	public String className, translationKey;

	public static HashMap<String, Double> classXpBoosters = new HashMap<String, Double>();

	private ArrayList<ItemStack> classItems = new ArrayList<ItemStack>();

	public PlayerClass(String className) {
		this.className = className;
		this.translationKey = "classes." + className;
	}

	public void addBooster(Skill skill, double value) {
		classXpBoosters.put(skill.toString(), value);
	}

	public void applyBoosters(ServerPlayerEntity player) {
		Config.setPlayerXpBoost(player, translationKey, classXpBoosters);

	}

	public void addClassItem(ItemStack itemStack) {
		classItems.add(itemStack);
	}

	public ArrayList<ItemStack> getClassItems() {
		return classItems;
	}

	public void giveItems(ServerPlayerEntity player) {
		for (ItemStack stack : classItems) {
			ItemHandlerHelper.giveItemToPlayer(player, stack);
		}
	}

}
