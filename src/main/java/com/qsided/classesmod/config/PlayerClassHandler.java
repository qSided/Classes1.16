package com.qsided.classesmod.config;

import com.qsided.classesmod.playerClasses.PlayerClass;

import harmonised.pmmo.skills.Skill;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

public class PlayerClassHandler {

	public static PlayerClass soldier, survivalist;

	// This method needs to be called AFTER items are registered (otherwise the
	// itemstacks will be null/air)
	public static void load() {
		soldier = new PlayerClass("Soldier");
		survivalist = new PlayerClass("Survivalist");

		soldier.addBooster(Skill.COMBAT, 5);
		soldier.addBooster(Skill.ARCHERY, 5);
		soldier.addBooster(Skill.ENDURANCE, 5);
		soldier.addClassItem(new ItemStack(Items.WOODEN_SWORD, 1));// ItemStack(Item, count)
		soldier.addClassItem(new ItemStack(Items.SHIELD, 1));

		survivalist.addBooster(Skill.FARMING, 5);
		survivalist.addBooster(Skill.FISHING, 5);
		survivalist.addBooster(Skill.COOKING, 5);
		survivalist.addClassItem(new ItemStack(Items.FISHING_ROD, 1));
		survivalist.addClassItem(new ItemStack(Items.APPLE, 12));

		// TODO if all this works when hardcoded, the next step will be putting it in to
		// a JSON.
	}

}