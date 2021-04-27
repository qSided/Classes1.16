package com.qsided.classesmod.util;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;

import com.qsided.classesmod.ClassesMod;

import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;

public class UtilityMethods {

	public static boolean doesItemExist(String toCheck) {
		String[] splitLocation = toCheck.split(":");
		if (splitLocation.length == 2) {
			ResourceLocation resourceLocation = getResourceLocation(toCheck);
			Item item = ForgeRegistries.ITEMS.getValue(resourceLocation);

			if (item == null || item == Items.AIR) {
				LogManager.getLogger(ClassesMod.MOD_ID).log(Level.WARN,
						"Can't find item '" + toCheck + "'! Make sure it exists and is not a typo.");
				return false;
			}
		}
		return true;
	}
	
	public static ResourceLocation getResourceLocation(String key) {
		String[] splitLocation = key.split(":");
		return new ResourceLocation(splitLocation[0], splitLocation[1]);
	}

}
