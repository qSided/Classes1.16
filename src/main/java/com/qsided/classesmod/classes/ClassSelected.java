package com.qsided.classesmod.classes;

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

    public static Item cfItem1 = WOODEN_AXE;
    public static Item cfItem2 = WOODEN_PICKAXE;
    public static Item csItem1 = WOODEN_SWORD;
    public static Item csItem2 = SHIELD;
    public static Item csvItem1 = FISHING_ROD;
    public static Item csvItem2 = APPLE;
    public static Item caItem1 = SMITHING_TABLE;
    public static Item caItem2 = OAK_LOG;
    public static Item cadItem1 = HAY_BLOCK;
    public static Item cadItem2 = WOODEN_SWORD;
    public static Item cmItem1 = ENDER_PEARL;
    public static Item cmItem2 = EXPERIENCE_BOTTLE;

    public static TranslationTextComponent translation1 = new TranslationTextComponent("class.selected.true");
    public static String alreadySelected = translation1.getString();

    static Map<String, Double> classXpBooster = new HashMap<>();

    public static void setClassSoldier(Map<String, Object> dependencies1) {

        Entity entity = (Entity) dependencies1.get("entity");
        if (entity instanceof ServerPlayerEntity)
        {
            ServerPlayerEntity player = (ServerPlayerEntity) entity;
            ItemStack giveSword = new ItemStack(csItem1, (int) (1));
            ItemStack giveShield = new ItemStack(csItem2, (int) (1));
            //-------------------------------------------------------------------------
            if (ClassesSavedData.get(player.server).getPlayerClass(player).equals("NO_CLASS"))
            {
                ClassesSavedData.get(player.server).setPlayerClass(player,
                        "SOLDIER");
                player.sendStatusMessage
                        (new StringTextComponent(
                                TextFormatting.GRAY + "You selected class: " +
                                        TextFormatting.RED + "Soldier"),
                                false);
                //-------------------------------------------------------------------------
                classXpBooster.put(COMBAT.toString(), 5.0);
                classXpBooster.put(ARCHERY.toString(), 5.0);
                classXpBooster.put(ENDURANCE.toString(), 5.0);
                Config.setPlayerXpBoost(player,
                        "classes.soldier",
                        classXpBooster);
                //-------------------------------------------------------------------------
                giveSword.setCount((int) 1);
                ItemHandlerHelper.giveItemToPlayer((player), giveSword);

                giveShield.setCount((int) 1);
                ItemHandlerHelper.giveItemToPlayer((player), giveShield);
            }
            else {
                player.sendStatusMessage(
                        new StringTextComponent(
                                TextFormatting.DARK_RED + alreadySelected),
                                false);
            }
            player.closeScreen();
        }
    }

    public static void setClassSurvivalist(Map<String, Object> dependencies1) {

        Entity entity = (Entity) dependencies1.get("entity");
        if (entity instanceof ServerPlayerEntity)
        {
            ServerPlayerEntity player = (ServerPlayerEntity) entity;
            ItemStack giveFishingRod = new ItemStack(csvItem1, (int) (1));
            ItemStack giveApples = new ItemStack(csvItem2, (int) (1));
            //-------------------------------------------------------------------------
            if (ClassesSavedData.get(player.server).getPlayerClass(player).equals("NO_CLASS"))
            {
                ClassesSavedData.get(player.server).setPlayerClass(player,
                        "SURVIVALIST");
                player.sendStatusMessage
                        (new StringTextComponent(
                                TextFormatting.GRAY + "You selected class: " +
                                        TextFormatting.GOLD + "Survivalist"),
                                false);
                //-------------------------------------------------------------------------
                classXpBooster.put(FARMING.toString(), 5.0);
                classXpBooster.put(FISHING.toString(), 5.0);
                classXpBooster.put(COOKING.toString(), 5.0);
                Config.setPlayerXpBoost(player,
                        "classes.survivalist",
                        classXpBooster);
                //-------------------------------------------------------------------------
                giveFishingRod.setCount((int) 1);
                ItemHandlerHelper.giveItemToPlayer((player), giveFishingRod);

                giveApples.setCount((int) 12);
                ItemHandlerHelper.giveItemToPlayer((player), giveApples);
            }
            else {
                player.sendStatusMessage
                        (new StringTextComponent(
                                TextFormatting.DARK_RED + alreadySelected),
                                false);
            }
            player.closeScreen();
        }
    }

    public static void setClassArtisan(Map<String, Object> dependencies1)
    {
        Entity entity = (Entity) dependencies1.get("entity");
        if (entity instanceof ServerPlayerEntity)
        {
            ServerPlayerEntity player = (ServerPlayerEntity) entity;
            ItemStack giveSmithingTable = new ItemStack(caItem1, (int) (1));
            ItemStack giveWood = new ItemStack(caItem2, (int) (1));
            //-------------------------------------------------------------------------
            if (ClassesSavedData.get(player.server).getPlayerClass(player).equals("NO_CLASS"))
            {
                ClassesSavedData.get(player.server).setPlayerClass(player,
                        "ARTISAN");
                player.sendStatusMessage(new StringTextComponent(
                        TextFormatting.GRAY + "You selected class: " +
                                TextFormatting.YELLOW + "Artisan"),
                        false);
                //-------------------------------------------------------------------------
                classXpBooster.put(BUILDING.toString(), 5.0);
                classXpBooster.put(SMITHING.toString(), 5.0);
                classXpBooster.put(CRAFTING.toString(), 5.0);
                Config.setPlayerXpBoost(player,
                        "classes.artisan",
                        classXpBooster);
                //-------------------------------------------------------------------------
                giveSmithingTable.setCount((int) 1);
                ItemHandlerHelper.giveItemToPlayer((player), giveSmithingTable);

                giveWood.setCount((int) 12);
                ItemHandlerHelper.giveItemToPlayer((player), giveWood);
            }
            else {
                player.sendStatusMessage(new StringTextComponent(
                        TextFormatting.DARK_RED + alreadySelected),
                        false);
            }
            player.closeScreen();
        }
    }

    public static void setClassFreeman(Map<String, Object> dependencies1)
    {
        Entity entity = (Entity) dependencies1.get("entity");
        if (entity instanceof ServerPlayerEntity)
        {
            ServerPlayerEntity player = (ServerPlayerEntity) entity;
            ItemStack giveItem1 = new ItemStack(cfItem1, (int) (1));
            ItemStack giveItem2 = new ItemStack(cfItem2, (int) (1));
            //-------------------------------------------------------------------------
            if (ClassesSavedData.get(player.server).getPlayerClass(player).equals("NO_CLASS"))
            {
                ClassesSavedData.get(player.server).setPlayerClass(player,
                        "FREEMAN");
                player.sendStatusMessage(new StringTextComponent(
                        TextFormatting.GRAY + "You selected class: " +
                                TextFormatting.AQUA + "Freeman"),
                        false);
                //-------------------------------------------------------------------------
                classXpBooster.put(EXCAVATION.toString(), 5.0);
                classXpBooster.put(WOODCUTTING.toString(), 5.0);
                classXpBooster.put(MINING.toString(), 5.0);
                Config.setPlayerXpBoost(player,
                        "classes.freeman",
                        classXpBooster);
                //-------------------------------------------------------------------------
                giveItem1.setCount((int) 1);
                ItemHandlerHelper.giveItemToPlayer((player), giveItem1);

                giveItem2.setCount((int) 1);
                ItemHandlerHelper.giveItemToPlayer((player), giveItem2);
            }
            else {
                player.sendStatusMessage(new StringTextComponent(
                        TextFormatting.DARK_RED + alreadySelected),
                        false);
            }
            player.closeScreen();
        }
    }
    public static void setClassAdventurer(Map<String, Object> dependencies1)
    {
        Entity entity = (Entity) dependencies1.get("entity");
        if (entity instanceof ServerPlayerEntity)
        {
            ServerPlayerEntity player = (ServerPlayerEntity) entity;
            ItemStack giveHay = new ItemStack(cadItem1, (int) (1));
            ItemStack giveSword = new ItemStack(cadItem2, (int) (1));
            //-------------------------------------------------------------------------
            if (ClassesSavedData.get(player.server).getPlayerClass(player).equals("NO_CLASS"))
            {
                ClassesSavedData.get(player.server).setPlayerClass(player,
                        "ADVENTURER");
                player.sendStatusMessage(new StringTextComponent(
                        TextFormatting.GRAY + "You selected class: " +
                                TextFormatting.GREEN + "Adventurer"),
                        false);
                //-------------------------------------------------------------------------
                classXpBooster.put(AGILITY.toString(), 5.0);
                classXpBooster.put(SWIMMING.toString(), 5.0);
                classXpBooster.put(FLYING.toString(), 5.0);
                Config.setPlayerXpBoost(player,
                        "classes.adventurer",
                        classXpBooster);
                //-------------------------------------------------------------------------
                giveHay.setCount((int) 5);
                ItemHandlerHelper.giveItemToPlayer((player), giveHay);

                giveSword.setCount((int) 1);
                ItemHandlerHelper.giveItemToPlayer((player), giveSword);            }
            else {
                player.sendStatusMessage(new StringTextComponent(
                        TextFormatting.DARK_RED + alreadySelected),
                        false);
            }
            player.closeScreen();
        }
    }
    public static void setClassMage(Map<String, Object> dependencies1)
    {
        Entity entity = (Entity) dependencies1.get("entity");
        if (entity instanceof ServerPlayerEntity)
        {
            ServerPlayerEntity player = (ServerPlayerEntity) entity;
            ItemStack giveEPearls = new ItemStack(cmItem1, (int) (1));
            ItemStack giveBottleXp = new ItemStack(cmItem2, (int) (1));
            //-------------------------------------------------------------------------
            if (ClassesSavedData.get(player.server).getPlayerClass(player).equals("NO_CLASS"))
            {
                ClassesSavedData.get(player.server).setPlayerClass(player,
                        "MAGE");
                player.sendStatusMessage(new StringTextComponent(
                        TextFormatting.GRAY + "You selected class: " +
                                TextFormatting.BLUE + "Mage"),
                        false);
                //-------------------------------------------------------------------------
                classXpBooster.put(ALCHEMY.toString(), 5.0);
                classXpBooster.put(MAGIC.toString(), 5.0);
                classXpBooster.put(COMBAT.toString(), 5.0);
                Config.setPlayerXpBoost(player,
                        "classes.mage",
                        classXpBooster);
                //-------------------------------------------------------------------------
                giveEPearls.setCount((int) 4);
                ItemHandlerHelper.giveItemToPlayer((player), giveEPearls);

                giveBottleXp.setCount((int) 12);
                ItemHandlerHelper.giveItemToPlayer((player), giveBottleXp);
            }
            else {
                player.sendStatusMessage(new StringTextComponent(
                        TextFormatting.DARK_RED + alreadySelected),
                        false);
            }
            player.closeScreen();
        }
    }
}

