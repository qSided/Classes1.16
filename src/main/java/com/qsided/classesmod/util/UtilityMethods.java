package com.qsided.classesmod.util;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;

import com.qsided.classesmod.ClassesMod;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.registries.ForgeRegistries;

public class UtilityMethods {

	public static void handleButtonAction(PlayerEntity entity, int buttonID, int x, int y, int z) {
		World world = entity.world;
		if (!world.isBlockLoaded(new BlockPos(x, y, z)))
			return;
//        if (buttonID == 0) {
//            {
//                Map<String, Object> dependencies1 = new HashMap<>();
//                dependencies1.put("entity", entity);
//                ClassSelected.setClassSoldier(dependencies1);
//            }
//        }
//        if (buttonID == 1) {
//            {
//                Map<String, Object> dependencies1 = new HashMap<>();
//                dependencies1.put("entity", entity);
//                ClassSelected.setClassFreeman(dependencies1);
//            }
//        }
//        if (buttonID == 2) {
//            {
//                Map<String, Object> dependencies1 = new HashMap<>();
//                dependencies1.put("entity", entity);
//                ClassSelected.setClassSurvivalist(dependencies1);
//            }
//        }
//        if (buttonID == 3) {
//            {
//                Map<String, Object> dependencies1 = new HashMap<>();
//                dependencies1.put("entity", entity);
//                ClassSelected.setClassArtisan(dependencies1);
//            }
//        }
//        if (buttonID == 4) {
//            {
//                Map<String, Object> dependencies1 = new HashMap<>();
//                dependencies1.put("entity", entity);
//                ClassSelected.setClassAdventurer(dependencies1);
//            }
//        }
//        if (buttonID == 5) {
//            {
//                Map<String, Object> dependencies1 = new HashMap<>();
//                dependencies1.put("entity", entity);
//                ClassSelected.setClassMage(dependencies1);
//            }
//        }
	}

	public static void handleSlotAction(PlayerEntity entity, int slotID, int changeType, int meta, int x, int y,
			int z) {
		World world = entity.world;
		if (!world.isBlockLoaded(new BlockPos(x, y, z)))
			return;
	}

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
