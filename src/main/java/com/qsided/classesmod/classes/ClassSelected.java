package com.qsided.classesmod.classes;

import com.qsided.classesmod.ClassesMod;
import harmonised.pmmo.config.Config;
import harmonised.pmmo.skills.Skill;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.items.ItemHandlerHelper;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static harmonised.pmmo.skills.Skill.*;
import static net.minecraft.item.Items.*;

public class ClassSelected extends ClassesMod.GuiElement {
    public ClassSelected(ClassesMod instance) {
        super(instance, 2);
    }

    static Map<Skill, Double> classXpBooster = new HashMap<>();

    public static void setClassLJ(Map<String, Object> dependencies1) {
        Entity entity = (Entity) dependencies1.get("entity");
        if (entity instanceof ServerPlayerEntity) {
            ServerPlayerEntity player = (ServerPlayerEntity) entity;
            ItemStack giveAxe = new ItemStack(STONE_AXE, (int) (1));
            ItemStack giveLogs = new ItemStack(OAK_LOG, (int) (1));
            if (ClassesSavedData.get(player.server).getPlayerClass(player).equals("NO_CLASS")) {
                player.sendStatusMessage(new StringTextComponent(TextFormatting.GRAY + "You selected class: " + TextFormatting.GOLD + "Lumberjack"), false);
                Skill.WOODCUTTING.addLevel(player.getUniqueID(), 10, "selectedClassLJ", false, true);
                Skill.CRAFTING.addLevel(player.getUniqueID(), 5, "selectedClassLJ", false, true);
                giveAxe.setCount((int) 1);
                ItemHandlerHelper.giveItemToPlayer((player), giveAxe);
                giveLogs.setCount((int) 12);
                ItemHandlerHelper.giveItemToPlayer((player), giveLogs);
                ClassesSavedData.get(player.server).setPlayerClass(player, "LUMBERJACK");
                classXpBooster.put(WOODCUTTING, 10.0);
                Config.setPlayerXpBoost(player, "classes.lumberjack", classXpBooster);
            }
            else {
                player.sendStatusMessage(new StringTextComponent(TextFormatting.DARK_RED + "You have already selected a class."), false);
            }
            player.closeScreen();
        }
    }

    public static void setClassM(Map<String, Object> dependencies1) {
        Entity entity = (Entity) dependencies1.get("entity");
        if (entity instanceof ServerPlayerEntity) {
            ServerPlayerEntity player = (ServerPlayerEntity) entity;
            ItemStack givePickaxe = new ItemStack(Items.STONE_PICKAXE, (int) (1));
            ItemStack giveTorches = new ItemStack(Items.TORCH, (int) (1));
            if (ClassesSavedData.get(player.server).getPlayerClass(player).equals("NO_CLASS")) {
                player.sendStatusMessage(new StringTextComponent(TextFormatting.GRAY + "You selected class: " + TextFormatting.AQUA + "Miner"), false);
                MINING.addLevel(player.getUniqueID(), 10, "something", false, true);
                Skill.EXCAVATION.addLevel(player.getUniqueID(), 5, "something1", false, true);
                givePickaxe.setCount((int) 1);
                ItemHandlerHelper.giveItemToPlayer((player), givePickaxe);
                giveTorches.setCount((int) 16);
                ItemHandlerHelper.giveItemToPlayer((player), giveTorches);
                ClassesSavedData.get(player.server).setPlayerClass(player, "MINER");
                classXpBooster.put(MINING, 10.0);
                Config.setPlayerXpBoost(player, "classes.miner", classXpBooster);
            }
            else {
                player.sendStatusMessage(new StringTextComponent(TextFormatting.DARK_RED + "You have already selected a class."), false);
            }
            player.closeScreen();
        }
    }

    public static void setClassW(Map<String, Object> dependencies1) {
        Entity entity = (Entity) dependencies1.get("entity");
        if (entity instanceof ServerPlayerEntity) {
            ServerPlayerEntity player = (ServerPlayerEntity) entity;
            ItemStack giveStoneSword = new ItemStack(STONE_SWORD, (int) (1));
            ItemStack giveShield = new ItemStack(SHIELD, (int) (1));
            if (ClassesSavedData.get(player.server).getPlayerClass(player).equals("NO_CLASS")) {
                player.sendStatusMessage(new StringTextComponent(TextFormatting.GRAY + "You selected class: " + TextFormatting.RED + "Warrior"), false);
                Skill.COMBAT.addLevel(player.getUniqueID(), 10, "something", false, true);
                Skill.ENDURANCE.addLevel(player.getUniqueID(), 5, "something1", false, true);
                giveStoneSword.setCount((int) 1);
                ItemHandlerHelper.giveItemToPlayer((player), giveStoneSword);
                giveShield.setCount((int) 1);
                ItemHandlerHelper.giveItemToPlayer((player), giveShield);
                ClassesSavedData.get(player.server).setPlayerClass(player, "WARRIOR");
                classXpBooster.put(COMBAT, 10.0);
                Config.setPlayerXpBoost(player, "classes.warrior", classXpBooster);
            }
            else {
                player.sendStatusMessage(new StringTextComponent(TextFormatting.DARK_RED + "You have already selected a class."), false);

            }
            player.closeScreen();
        }
    }

    public static void setClassA(Map<String, Object> dependencies1) {
        Entity entity = (Entity) dependencies1.get("entity");
        if (entity instanceof ServerPlayerEntity) {
            ServerPlayerEntity player = (ServerPlayerEntity) entity;
            ItemStack giveBow = new ItemStack(Items.BOW, (int) (1));
            ItemStack giveArrows = new ItemStack(Items.ARROW, (int) (1));
            if (ClassesSavedData.get(player.server).getPlayerClass(player).equals("NO_CLASS")) {
                player.sendStatusMessage(new StringTextComponent(TextFormatting.GRAY + "You selected class: " + TextFormatting.YELLOW + "Archer"), false);
                Skill.ARCHERY.addLevel(player.getUniqueID(), 10, "something", false, true);
                Skill.AGILITY.addLevel(player.getUniqueID(), 5, "something1", false, true);
                giveBow.setCount((int) 1);
                ItemHandlerHelper.giveItemToPlayer((player), giveBow);
                giveArrows.setCount((int) 16);
                ItemHandlerHelper.giveItemToPlayer((player), giveArrows);
                ClassesSavedData.get(player.server).setPlayerClass(player, "ARCHER");
                classXpBooster.put(ARCHERY, 10.0);
                Config.setPlayerXpBoost(player, "classes.archer", classXpBooster);
            }
            else {
                player.sendStatusMessage(new StringTextComponent(TextFormatting.DARK_RED + "You have already selected a class."), false);
            }
            player.closeScreen();
        }
    }
    public static void setClassF(Map<String, Object> dependencies1) {
        Entity entity = (Entity) dependencies1.get("entity");
        if (entity instanceof ServerPlayerEntity) {
            ServerPlayerEntity player = (ServerPlayerEntity) entity;
            ItemStack givePotatoes = new ItemStack(POTATO, (int) (1));
            ItemStack giveHoe = new ItemStack(STONE_HOE, (int) (1));
            if (ClassesSavedData.get(player.server).getPlayerClass(player).equals("NO_CLASS")) {
                player.sendStatusMessage(new StringTextComponent(TextFormatting.GRAY + "You selected class: " + TextFormatting.GREEN + "Farmer"), false);
                Skill.FARMING.addLevel(player.getUniqueID(), 10, "something", false, true);
                Skill.COOKING.addLevel(player.getUniqueID(), 5, "something1", false, true);
                giveHoe.setCount((int) 1);
                ItemHandlerHelper.giveItemToPlayer((player), giveHoe);
                givePotatoes.setCount((int) 12);
                ItemHandlerHelper.giveItemToPlayer((player), givePotatoes);
                ClassesSavedData.get(player.server).setPlayerClass(player, "FARMER");
                classXpBooster.put(FARMING, 10.0);
                Config.setPlayerXpBoost(player, "classes.farmer", classXpBooster);
            }
            else {
                player.sendStatusMessage(new StringTextComponent(TextFormatting.DARK_RED + "You have already selected a class."), false);
            }
            player.closeScreen();
        }
    }
    public static void setClassB(Map<String, Object> dependencies1) {
        Entity entity = (Entity) dependencies1.get("entity");
        if (entity instanceof ServerPlayerEntity) {
            ServerPlayerEntity player = (ServerPlayerEntity) entity;
            ItemStack giveIronIngots = new ItemStack(IRON_INGOT, (int) (1));
            ItemStack giveAnvil = new ItemStack(ANVIL, (int) (1));
            if (ClassesSavedData.get(player.server).getPlayerClass(player).equals("NO_CLASS")) {
                player.sendStatusMessage(new StringTextComponent(TextFormatting.GRAY + "You selected class: " + TextFormatting.WHITE + "Blacksmith"), false);
                Skill.SMITHING.addLevel(player.getUniqueID(), 10, "something", false, true);
                Skill.CRAFTING.addLevel(player.getUniqueID(), 10, "something1", false, true);
                giveAnvil.setCount((int) 1);
                ItemHandlerHelper.giveItemToPlayer((player), giveAnvil);
                giveIronIngots.setCount((int) 12);
                ItemHandlerHelper.giveItemToPlayer((player), giveIronIngots);
                ClassesSavedData.get(player.server).setPlayerClass(player, "BLACKSMITH");
                classXpBooster.put(SMITHING, 10.0);
                Config.setPlayerXpBoost(player, "classes.smithing", classXpBooster);
            }
            else {
                player.sendStatusMessage(new StringTextComponent(TextFormatting.DARK_RED + "You have already selected a class."), false);
            }
            player.closeScreen();
        }
    }
}

