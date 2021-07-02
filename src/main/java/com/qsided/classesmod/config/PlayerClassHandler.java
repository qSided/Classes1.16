package com.qsided.classesmod.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import com.qsided.classesmod.playerClasses.PlayerClass;
import com.qsided.classesmod.util.UtilityMethods;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.registries.ForgeRegistries;

public class PlayerClassHandler {

	public static ArrayList<PlayerClass> playerClasses = new ArrayList<PlayerClass>();

	public static void addPlayerClass(PlayerClass pc) {
		playerClasses.add(pc);

		System.out.println("Player Class Added: " + pc.className + ", " + pc.xpBoosters + ", " + pc.getClassItems());
	}

	public static ArrayList<PlayerClass> getClasses() {
		return playerClasses;
	}

	// Requirement: class names CAN NOT have symbols (spaces are okay)
	public static String encodeForClient() {
		String data = "";

		for (PlayerClass pClass : playerClasses) {

			String xpBoosters = "";
			String classItems = "";

			Iterator<Entry<String, Double>> it = pClass.xpBoosters.entrySet().iterator();

			while (it.hasNext()) {
				Map.Entry<String, Double> pair = (Map.Entry<String, Double>) it.next();
				xpBoosters += pair.getKey() + "#" + pair.getValue() + "`";
				it.remove();
			}

			for (ItemStack stack : pClass.classItems) {
				classItems += stack.getItem().getRegistryName().toString() + "#" + stack.getCount() + "`";
			}

			data += pClass.className + "&" + pClass.translationKey + "&" + xpBoosters + "&" + classItems + "~";

			// Example:
			// name&translationKey&boost1#5.0`boost2#5.0`boost3#5.0`&minecraft:dirt#4`minecraft:diamond_sword#1~...
			// In the example above, that is just one class, each class is separated by "~"
		}

		System.out.println("encoded and sending: " + data);
		return data;
	}

	public static void decode(String data) {
		System.out.println("decoding: " + data);

		// First of all, since we are resetting the class data, we will need to wipe
		// what is already saved on the client's side.
		PlayerClassHandler.playerClasses = new ArrayList<PlayerClass>();

		// Now that is empty, proceed to process data...
		String[] classes = data.split("~");

		for (int i = 0; i < classes.length; i++) {
			String[] splitData = classes[i].split("&");
			String name = splitData[0];
			String translationKey = splitData[1];
			String[] boostersArray = splitData[2].split("`");
			String[] itemsArray = splitData[3].split("`");

			HashMap<String, Double> xpBoosters = new HashMap<String, Double>();
			for (int xp = 0; xp < boostersArray.length; xp++) {
				String[] thisBoost = boostersArray[xp].split("#");
				xpBoosters.put(thisBoost[0], Double.parseDouble(thisBoost[1]));
			}

			ArrayList<ItemStack> classItems = new ArrayList<ItemStack>();
			for (int item = 0; item < itemsArray.length; item++) {
				String[] thisItem = itemsArray[item].split("#");
				if (UtilityMethods.doesItemExist(thisItem[0])) {
					Item actualItem = ForgeRegistries.ITEMS.getValue(UtilityMethods.getResourceLocation(thisItem[0]));
					ItemStack stack = new ItemStack(actualItem, Integer.parseInt(thisItem[1]));
					classItems.add(stack);
				} else {
					System.out.println("Failed to add item, apparently it does not exist? " + thisItem[0]);
				}
			}

			PlayerClass pc = new PlayerClass(name);
			pc.translationKey = translationKey;
			pc.xpBoosters = xpBoosters;
			pc.classItems = classItems;

			PlayerClassHandler.addPlayerClass(pc);
		}
	}

}