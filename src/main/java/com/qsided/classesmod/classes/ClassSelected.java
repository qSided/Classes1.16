package com.qsided.classesmod.classes;

import com.qsided.classesmod.ClassesMod;
import harmonised.pmmo.config.Config;
import harmonised.pmmo.skills.Skill;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.items.ItemHandlerHelper;

import java.util.HashMap;
import java.util.Map;

import static harmonised.pmmo.skills.Skill.*;
import static net.minecraft.item.Items.*;

public class ClassSelected extends ClassesMod.GuiElement {
    public ClassSelected(ClassesMod instance) {
        super(instance, 2);
    }

    static Map<Skill, Double> classXpBooster = new HashMap<>();
    static Map<Skill, Double> classXpBooster1 = new HashMap<>();
    static Map<Skill, Double> classXpBooster2 = new HashMap<>();
    static Map<Skill, Double> classXpBooster3 = new HashMap<>();
    static Map<Skill, Double> classXpBooster4 = new HashMap<>();
    static Map<Skill, Double> classXpBooster5 = new HashMap<>();
    static Map<Skill, Double> classXpBooster6 = new HashMap<>();
    static Map<Skill, Double> classXpBooster7 = new HashMap<>();
    static Map<Skill, Double> classXpBooster8 = new HashMap<>();
    static Map<Skill, Double> classXpBooster9 = new HashMap<>();

    public static void setClassFighter(Map<String, Object> dependencies1)
    {
        Entity entity = (Entity) dependencies1.get("entity");
        if (entity instanceof ServerPlayerEntity)
        {
            ServerPlayerEntity player = (ServerPlayerEntity) entity;
            ItemStack giveAxe = new ItemStack(STONE_AXE, (int) (1));
            ItemStack giveLogs = new ItemStack(OAK_LOG, (int) (1));
            //-------------------------------------------------------------------------
            if (ClassesSavedData.get(player.server).getPlayerClass(player).equals("NO_CLASS"))
            {
                ClassesSavedData.get(player.server).setPlayerClass(player,
                        "FIGHTER");
                player.sendStatusMessage
                        (new StringTextComponent(
                                TextFormatting.GRAY + "You selected class: " +
                                        TextFormatting.RED + "Fighter"),
                                false);
                //-------------------------------------------------------------------------
                classXpBooster.put(COMBAT, 5.0);
                Config.setPlayerXpBoost(player,
                        "classes.fighter",
                        classXpBooster);

                classXpBooster2.put(ENDURANCE, 5.0);
                Config.setPlayerXpBoost(player,
                        "classes.fighter2",
                        classXpBooster2);
                //-------------------------------------------------------------------------
                giveAxe.setCount((int) 1);
                ItemHandlerHelper.giveItemToPlayer((player), giveAxe);

                giveLogs.setCount((int) 12);
                ItemHandlerHelper.giveItemToPlayer((player), giveLogs);
            }
            else {
                player.sendStatusMessage
                        (new StringTextComponent(
                                TextFormatting.DARK_RED + "You have already selected a class."),
                                false);
            }
            player.closeScreen();
        }
    }

    public static void setClassSurvivor(Map<String, Object> dependencies1)
    {
        Entity entity = (Entity) dependencies1.get("entity");
        if (entity instanceof ServerPlayerEntity)
        {
            ServerPlayerEntity player = (ServerPlayerEntity) entity;
            ItemStack givePickaxe = new ItemStack(Items.STONE_PICKAXE, (int) (1));
            ItemStack giveTorches = new ItemStack(Items.TORCH, (int) (1));
            //-------------------------------------------------------------------------
            if (ClassesSavedData.get(player.server).getPlayerClass(player).equals("NO_CLASS"))
            {
                ClassesSavedData.get(player.server).setPlayerClass(player, "SURVIVOR");
                player.sendStatusMessage
                        (new StringTextComponent(
                                TextFormatting.GRAY + "You selected class: " +
                                        TextFormatting.AQUA + "Survivor"),
                                false);
                //-------------------------------------------------------------------------
                classXpBooster.put(COMBAT, 5.0);
                Config.setPlayerXpBoost(player,
                        "classes.survivor",
                        classXpBooster);

                classXpBooster2.put(COMBAT, 5.0);
                Config.setPlayerXpBoost(player,
                        "classes.survivor2",
                        classXpBooster2);
                //-------------------------------------------------------------------------
                givePickaxe.setCount((int) 1);
                ItemHandlerHelper.giveItemToPlayer((player), givePickaxe);

                giveTorches.setCount((int) 16);
                ItemHandlerHelper.giveItemToPlayer((player), giveTorches);
            }
            else {
                player.sendStatusMessage
                        (new StringTextComponent(
                                TextFormatting.DARK_RED + "You have already selected a class."),
                                false);
            }
            player.closeScreen();
        }
    }

    public static void setClassGatherer(Map<String, Object> dependencies1)
    {
        Entity entity = (Entity) dependencies1.get("entity");
        if (entity instanceof ServerPlayerEntity)
        {
            ServerPlayerEntity player = (ServerPlayerEntity) entity;
            ItemStack giveStoneSword = new ItemStack(STONE_SWORD, (int) (1));
            ItemStack giveShield = new ItemStack(SHIELD, (int) (1));
            //-------------------------------------------------------------------------
            if (ClassesSavedData.get(player.server).getPlayerClass(player).equals("NO_CLASS"))
            {
                player.abilities.setWalkSpeed((float) (player.abilities.getWalkSpeed() - 0.03));
                ClassesSavedData.get(player.server).setPlayerClass(player, "WARRIOR");
                player.sendStatusMessage(new StringTextComponent(
                        TextFormatting.GRAY + "You selected class: " +
                                TextFormatting.RED + "Warrior"),
                        false);
                //-------------------------------------------------------------------------
                COMBAT.addLevel(player.getUniqueID(),
                        10,
                        "Selected class: Gatherer",
                        false,
                        true);

                ENDURANCE.addLevel(player.getUniqueID(),
                        5,
                        "Selected class: Gatherer",
                        false,
                        true);
                //-------------------------------------------------------------------------
                giveStoneSword.setCount((int) 1);
                ItemHandlerHelper.giveItemToPlayer((player), giveStoneSword);

                giveShield.setCount((int) 1);
                ItemHandlerHelper.giveItemToPlayer((player), giveShield);
                //-------------------------------------------------------------------------
                classXpBooster.put(COMBAT, 10.0);
                Config.setPlayerXpBoost(player,
                        "classes.gatherer",
                        classXpBooster);
            }
            else {
                player.sendStatusMessage(new StringTextComponent(
                        TextFormatting.DARK_RED + "You have already selected a class."),
                        false);
            }
            player.closeScreen();
        }
    }

    public static void setClassWorker(Map<String, Object> dependencies1)
    {
        Entity entity = (Entity) dependencies1.get("entity");
        if (entity instanceof ServerPlayerEntity)
        {
            ServerPlayerEntity player = (ServerPlayerEntity) entity;
            ItemStack giveBow = new ItemStack(Items.BOW, (int) (1));
            ItemStack giveArrows = new ItemStack(Items.ARROW, (int) (1));
            //-------------------------------------------------------------------------
            if (ClassesSavedData.get(player.server).getPlayerClass(player).equals("NO_CLASS"))
            {
                player.abilities.setWalkSpeed((float) (player.abilities.getWalkSpeed() + 0.03));
                ClassesSavedData.get(player.server).setPlayerClass(player, "ARCHER");
                player.sendStatusMessage(new StringTextComponent(
                        TextFormatting.GRAY + "You selected class: " +
                                TextFormatting.YELLOW + "Archer"),
                        false);
                //-------------------------------------------------------------------------
                ARCHERY.addLevel(player.getUniqueID(),
                        10,
                        "something",
                        false,
                        true);

                AGILITY.addLevel(player.getUniqueID(),
                        5,
                        "something1",
                        false,
                        true);
                //-------------------------------------------------------------------------
                giveBow.setCount((int) 1);
                ItemHandlerHelper.giveItemToPlayer((player), giveBow);

                giveArrows.setCount((int) 16);
                ItemHandlerHelper.giveItemToPlayer((player), giveArrows);
                //-------------------------------------------------------------------------
                classXpBooster.put(ARCHERY, 10.0);
                Config.setPlayerXpBoost(player,
                        "classes.archer",
                        classXpBooster);
            }
            else {
                player.sendStatusMessage(new StringTextComponent(
                        TextFormatting.DARK_RED + "You have already selected a class."),
                        false);
            }
            player.closeScreen();
        }
    }
    public static void setClassExplorer(Map<String, Object> dependencies1)
    {
        Entity entity = (Entity) dependencies1.get("entity");
        if (entity instanceof ServerPlayerEntity)
        {
            ServerPlayerEntity player = (ServerPlayerEntity) entity;
            ItemStack givePotatoes = new ItemStack(POTATO, (int) (1));
            ItemStack giveHoe = new ItemStack(STONE_HOE, (int) (1));
            //-------------------------------------------------------------------------
            if (ClassesSavedData.get(player.server).getPlayerClass(player).equals("NO_CLASS"))
            {
                ClassesSavedData.get(player.server).setPlayerClass(player, "FARMER");
                player.sendStatusMessage(new StringTextComponent(
                        TextFormatting.GRAY + "You selected class: " +
                                TextFormatting.GREEN + "Farmer"),
                        false);
                //-------------------------------------------------------------------------
                FARMING.addLevel(player.getUniqueID(),
                        10,
                        "something",
                        false,
                        true);

                COOKING.addLevel(player.getUniqueID(),
                        5,
                        "something1",
                        false,
                        true);
                //-------------------------------------------------------------------------
                giveHoe.setCount((int) 1);
                ItemHandlerHelper.giveItemToPlayer((player), giveHoe);

                givePotatoes.setCount((int) 12);
                ItemHandlerHelper.giveItemToPlayer((player), givePotatoes);
                //-------------------------------------------------------------------------
                classXpBooster.put(FARMING, 10.0);
                Config.setPlayerXpBoost(player,
                        "classes.farmer",
                        classXpBooster);
            }
            else {
                player.sendStatusMessage(new StringTextComponent(
                        TextFormatting.DARK_RED + "You have already selected a class."),
                        false);
            }
            player.closeScreen();
        }
    }
    public static void setClassMagician(Map<String, Object> dependencies1)
    {
        Entity entity = (Entity) dependencies1.get("entity");
        if (entity instanceof ServerPlayerEntity)
        {
            ServerPlayerEntity player = (ServerPlayerEntity) entity;
            ItemStack giveIronIngots = new ItemStack(IRON_INGOT, (int) (1));
            ItemStack giveAnvil = new ItemStack(ANVIL, (int) (1));
            //-------------------------------------------------------------------------
            if (ClassesSavedData.get(player.server).getPlayerClass(player).equals("NO_CLASS"))
            {
                ClassesSavedData.get(player.server).setPlayerClass(player, "MAGICIAN");
                player.sendStatusMessage(new StringTextComponent(
                        TextFormatting.GRAY + "You selected class: " +
                                TextFormatting.AQUA + "Magician"),
                        false);
                //-------------------------------------------------------------------------
                SMITHING.addLevel(player.getUniqueID(),
                        10,
                        "Selected class: Magician",
                        false,
                        true);

                CRAFTING.addLevel(player.getUniqueID(),
                        10,
                        "Selected class: Magician",
                        false,
                        true);
                //-------------------------------------------------------------------------
                giveAnvil.setCount((int) 1);
                ItemHandlerHelper.giveItemToPlayer((player), giveAnvil);

                giveIronIngots.setCount((int) 12);
                ItemHandlerHelper.giveItemToPlayer((player), giveIronIngots);
                //-------------------------------------------------------------------------
                classXpBooster.put(SMITHING, 10.0);
                Config.setPlayerXpBoost(player,
                        "classes.magician",
                        classXpBooster);
            }
            else {
                player.sendStatusMessage(new StringTextComponent(
                        TextFormatting.DARK_RED + "You have already selected a class."),
                        false);
            }
            player.closeScreen();
        }
    }
}

