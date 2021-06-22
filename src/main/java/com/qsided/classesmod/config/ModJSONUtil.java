package com.qsided.classesmod.config;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.qsided.classesmod.ClassesMod;
import com.qsided.classesmod.playerClasses.PlayerClass;
import com.qsided.classesmod.util.UtilityMethods;

import harmonised.pmmo.skills.Skill;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.loading.FMLPaths;
import net.minecraftforge.registries.ForgeRegistries;

public class ModJSONUtil {

	private static String fileName = "player_classes" + ".json";

	public static File file = FMLPaths.CONFIGDIR.get().resolve(fileName).toFile();

	public static void loadClassesJSON() {
		try {
			FileReader reader = new FileReader(file);

			JsonArray jsonArray = JsonParser.parseReader(reader).getAsJsonObject().get("classes").getAsJsonArray();

			System.out.println("overhere");

			for (int i = 0; i < jsonArray.size(); i++) {
				JsonObject entry = jsonArray.get(i).getAsJsonObject();
				JsonObject entrySkills = entry.get("Skills").getAsJsonObject();
				JsonObject entryItems = entry.get("Items").getAsJsonObject();

				String name = entry.get("Name").toString();
				Iterator<String> skillsIt = entrySkills.keySet().iterator();
				Iterator<String> itemsIt = entryItems.keySet().iterator();

				PlayerClass pc = new PlayerClass(name);

				while (skillsIt.hasNext()) {
					String skillStr = skillsIt.next();
					if (Skill.getSkills().containsKey(skillStr)) {
						pc.addBooster(skillStr, entrySkills.get(skillStr).getAsDouble());
					}
					skillsIt.remove();
				}

				while (itemsIt.hasNext()) {
					String itemToAdd = itemsIt.next();
					if (UtilityMethods.doesItemExist(itemToAdd)) {
						pc.addClassItem(new ItemStack(
								ForgeRegistries.ITEMS.getValue(UtilityMethods.getResourceLocation(itemToAdd)),
								entryItems.get(itemToAdd).getAsInt()));
					}
					itemsIt.remove();
				}

				PlayerClassHandler.addPlayerClass(pc);
			}

			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// TODO This line will be used to get and register the itemstack for the
		// PlayerClass class
//		String itemToAdd = "minecraft:dirt";
//		if (UtilityMethods.doesItemExist(itemToAdd)) {
//			ForgeRegistries.ITEMS.getValue(UtilityMethods.getResourceLocation(itemToAdd));
//		}

	}

	// This class is used to generate the *DEFAULT* classes if a config does not
	// exist
	public static void generateClassesJSON() {
		JsonObject obj = new JsonObject();
		JsonArray classArray = new JsonArray();

		HashMap<String, Double> class1Skills = new HashMap<String, Double>();
		HashMap<String, Integer> class1Items = new HashMap<String, Integer>();
		class1Skills.put(Skill.COMBAT.toString(), 5.0);
		class1Skills.put(Skill.ARCHERY.toString(), 5.0);
		class1Skills.put(Skill.ENDURANCE.toString(), 5.0);
		class1Items.put("minecraft:wooden_sword", 1);
		class1Items.put("minecraft:shield", 1);

		HashMap<String, Double> class2Skills = new HashMap<String, Double>();
		HashMap<String, Integer> class2Items = new HashMap<String, Integer>();
		class2Skills.put(Skill.EXCAVATION.toString(), 5.0);
		class2Skills.put(Skill.MINING.toString(), 5.0);
		class2Skills.put(Skill.WOODCUTTING.toString(), 5.0);
		class2Items.put("minecraft:wooden_axe", 1);
		class2Items.put("minecraft:wooden_pickaxe", 1);

		HashMap<String, Double> class3Skills = new HashMap<String, Double>();
		HashMap<String, Integer> class3Items = new HashMap<String, Integer>();
		class3Skills.put(Skill.FARMING.toString(), 5.0);
		class3Skills.put(Skill.FISHING.toString(), 5.0);
		class3Skills.put(Skill.COOKING.toString(), 5.0);
		class3Items.put("minecraft:fishing_rod", 1);
		class3Items.put("minecraft:apple", 12);

		HashMap<String, Double> class4Skills = new HashMap<String, Double>();
		HashMap<String, Integer> class4Items = new HashMap<String, Integer>();
		class4Skills.put(Skill.BUILDING.toString(), 5.0);
		class4Skills.put(Skill.CRAFTING.toString(), 5.0);
		class4Skills.put(Skill.SMITHING.toString(), 5.0);
		class4Items.put("minecraft:smithing_table", 1);
		class4Items.put("minecraft:oak_log", 12);

		HashMap<String, Double> class5Skills = new HashMap<String, Double>();
		HashMap<String, Integer> class5Items = new HashMap<String, Integer>();
		class5Skills.put(Skill.AGILITY.toString(), 5.0);
		class5Skills.put(Skill.SWIMMING.toString(), 5.0);
		class5Skills.put(Skill.FLYING.toString(), 5.0);
		class5Items.put("minecraft:hay_block", 5);
		class5Items.put("minecraft:wooden_sword", 1);

		HashMap<String, Double> class6Skills = new HashMap<String, Double>();
		HashMap<String, Integer> class6Items = new HashMap<String, Integer>();
		class6Skills.put(Skill.ALCHEMY.toString(), 5.0);
		class6Skills.put(Skill.COMBAT.toString(), 5.0);
		class6Skills.put(Skill.MAGIC.toString(), 5.0);
		class6Items.put("minecraft:ender_pearl", 1);
		class6Items.put("minecraft:experience_bottle", 14);

		classArray.add(genClass("Soldier", class1Skills, class1Items));
		classArray.add(genClass("Freeman", class2Skills, class2Items));
		classArray.add(genClass("Survivalist", class3Skills, class3Items));
		classArray.add(genClass("Artisan", class4Skills, class4Items));
		classArray.add(genClass("Adventurer", class5Skills, class5Items));
		classArray.add(genClass("Magician", class6Skills, class6Items));

		obj.add("classes", classArray);

		try {
			FileWriter writer = new FileWriter(file);

			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			JsonElement parsedJson = JsonParser.parseString(obj.getAsJsonObject().toString());

			gson.toJson(parsedJson, writer);
			writer.close();
		} catch (IOException e) {
			LogManager.getLogger(ClassesMod.MOD_ID).log(Level.WARN, "Failed to write JSON: '" + obj.getAsString());
			e.printStackTrace();
		}

	}

	// Generates the Json
	private static JsonObject genClass(String name, HashMap<String, Double> skills, HashMap<String, Integer> items) {
		JsonObject theClass = new JsonObject();
		JsonObject classSkills = new JsonObject();
		JsonObject classItems = new JsonObject();

		Iterator<Entry<String, Integer>> itemIt = items.entrySet().iterator();
		while (itemIt.hasNext()) {
			Map.Entry<String, Integer> pair = (Map.Entry<String, Integer>) itemIt.next();
			classItems.addProperty(pair.getKey(), pair.getValue());
			itemIt.remove();
		}

		Iterator<Entry<String, Double>> skillsIt = skills.entrySet().iterator();
		while (skillsIt.hasNext()) {
			Map.Entry<String, Double> pair = (Map.Entry<String, Double>) skillsIt.next();
			classSkills.addProperty(pair.getKey(), pair.getValue());
			skillsIt.remove();
		}

		theClass.addProperty("Name", name);
		theClass.add("Skills", classSkills);
		theClass.add("Items", classItems);

		return theClass;
	}

}
