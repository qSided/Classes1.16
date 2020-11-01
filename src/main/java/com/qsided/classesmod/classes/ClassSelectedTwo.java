package com.qsided.classesmod.classes;

import com.qsided.classesmod.ClassesMod;
import harmonised.pmmo.config.Config;
import harmonised.pmmo.skills.Skill;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.attributes.ModifiableAttributeInstance;
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

public class ClassSelectedTwo extends ClassesMod.GuiElement {
    public ClassSelectedTwo(ClassesMod instance) {
        super(instance, 2);
    }

    static Map<Skill, Double> classXpBooster = new HashMap<>();
    static Map<Skill, Double> classXpBooster2 = new HashMap<>();
    static Map<Skill, Double> classXpBooster3 = new HashMap<>();
    static Map<Skill, Double> classXpBooster4 = new HashMap<>();
    static Map<Skill, Double> classXpBooster5 = new HashMap<>();
    static Map<Skill, Double> classXpBooster6 = new HashMap<>();
    static Map<Skill, Double> classXpBooster7 = new HashMap<>();
    static Map<Skill, Double> classXpBooster8 = new HashMap<>();
    static Map<Skill, Double> classXpBooster9 = new HashMap<>();
    static Map<Skill, Double> classXpBooster10 = new HashMap<>();
    static Map<Skill, Double> classXpBooster11 = new HashMap<>();

    public static void setClassP(Map<String, Object> dependencies1) {
        Entity entity = (Entity) dependencies1.get("entity");
        if (entity instanceof ServerPlayerEntity) {
            ServerPlayerEntity player = (ServerPlayerEntity) entity;
            ItemStack giveTunic = new ItemStack(LEATHER_CHESTPLATE, (int) (1));
            ItemStack giveBread = new ItemStack(BREAD, (int) (1));
            if (ClassesSavedData.get(player.server).getPlayerClass(player).equals("NO_CLASS")) {
                player.sendStatusMessage(new StringTextComponent(TextFormatting.GRAY + "You selected class: " + TextFormatting.GOLD + "Peasant"), false);
                AGILITY.addLevel(player.getUniqueID(), 5, "selectedClassP", false, true);
                COOKING.addLevel(player.getUniqueID(), 5, "selectedClassP", false, true);
                giveTunic.setCount((int) 1);
                ItemHandlerHelper.giveItemToPlayer((player), giveTunic);
                giveBread.setCount((int) 8);
                ItemHandlerHelper.giveItemToPlayer((player), giveBread);
                ClassesSavedData.get(player.server).setPlayerClass(player, "PEASANT");
                classXpBooster.put(WOODCUTTING, 2.5);
                Config.setPlayerXpBoost(player, "classes.peasant1", classXpBooster);
                classXpBooster2.put(MINING, 2.5);
                Config.setPlayerXpBoost(player, "classes.peasant2", classXpBooster2);
                classXpBooster3.put(EXCAVATION, 2.5);
                Config.setPlayerXpBoost(player, "classes.peasant3", classXpBooster3);
                classXpBooster4.put(SMITHING, 2.5);
                Config.setPlayerXpBoost(player, "classes.peasant4", classXpBooster4);
                classXpBooster5.put(AGILITY, 2.5);
                Config.setPlayerXpBoost(player, "classes.peasant5", classXpBooster5);
                classXpBooster6.put(ENDURANCE, 2.5);
                Config.setPlayerXpBoost(player, "classes.peasant6", classXpBooster6);
                classXpBooster7.put(CRAFTING, 2.5);
                Config.setPlayerXpBoost(player, "classes.peasant7", classXpBooster7);
                classXpBooster8.put(COOKING, 2.5);
                Config.setPlayerXpBoost(player, "classes.peasant8", classXpBooster8);
                classXpBooster9.put(COMBAT, 2.5);
                Config.setPlayerXpBoost(player, "classes.peasant9", classXpBooster9);
                classXpBooster10.put(BUILDING, 2.5);
                Config.setPlayerXpBoost(player, "classes.peasant10", classXpBooster10);
                classXpBooster11.put(SWIMMING, 2.5);
                Config.setPlayerXpBoost(player, "classes.peasant11", classXpBooster11);
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
            ItemStack giveTotem = new ItemStack(TOTEM_OF_UNDYING, (int) (1));
            ItemStack giveBread = new ItemStack(BREAD, (int) (1));
            ModifiableAttributeInstance armorToughnessAttribute = player.getAttribute(Attributes.ARMOR_TOUGHNESS);
            ModifiableAttributeInstance speedAttribute = player.getAttribute(Attributes.MOVEMENT_SPEED);
            if (ClassesSavedData.get(player.server).getPlayerClass(player).equals("NO_CLASS")) {
                player.sendStatusMessage(new StringTextComponent(TextFormatting.GRAY + "You selected class: " + TextFormatting.AQUA + "Mage"), false);
                MINING.addLevel(player.getUniqueID(), 15, "selectedClassM", false, true);
                giveTotem.setCount((int) 1);
                ItemHandlerHelper.giveItemToPlayer((player), giveTotem);
                giveBread.setCount((int) 8);
                ItemHandlerHelper.giveItemToPlayer((player), giveBread);
                ClassesSavedData.get(player.server).setPlayerClass(player, "MAGE");
                classXpBooster.put(MAGIC, 10.0);
                Config.setPlayerXpBoost(player, "classes.mage", classXpBooster);
                AttributeModifier mageModifier = new AttributeModifier("mageModifier", 2.0, AttributeModifier.Operation.ADDITION);
                AttributeModifier mageModifier2 = new AttributeModifier("mageModifier2", -0.04, AttributeModifier.Operation.ADDITION);
                armorToughnessAttribute.applyPersistentModifier(mageModifier);
                speedAttribute.applyPersistentModifier(mageModifier2);
            }
            else {
                player.sendStatusMessage(new StringTextComponent(TextFormatting.DARK_RED + "You have already selected a class."), false);
            }
            player.closeScreen();
        }
    }

    public static void setClassFi(Map<String, Object> dependencies1) {
        Entity entity = (Entity) dependencies1.get("entity");
        if (entity instanceof ServerPlayerEntity) {
            ServerPlayerEntity player = (ServerPlayerEntity) entity;
            ItemStack giveStoneSword = new ItemStack(STONE_SWORD, (int) (1));
            ItemStack giveShield = new ItemStack(SHIELD, (int) (1));
            if (ClassesSavedData.get(player.server).getPlayerClass(player).equals("NO_CLASS")) {
                player.sendStatusMessage(new StringTextComponent(TextFormatting.GRAY + "You selected class: " + TextFormatting.RED + "Fisherman"), false);
                Skill.COMBAT.addLevel(player.getUniqueID(), 10, "something", false, true);
                Skill.ENDURANCE.addLevel(player.getUniqueID(), 5, "something1", false, true);
                giveStoneSword.setCount((int) 1);
                ItemHandlerHelper.giveItemToPlayer((player), giveStoneSword);
                giveShield.setCount((int) 1);
                ItemHandlerHelper.giveItemToPlayer((player), giveShield);
                player.abilities.setWalkSpeed((float) (player.abilities.getWalkSpeed() - 0.03));
                ClassesSavedData.get(player.server).setPlayerClass(player, "FISHERMAN");
                classXpBooster.put(COMBAT, 10.0);
                Config.setPlayerXpBoost(player, "classes.fisherman", classXpBooster);
            }
            else {
                player.sendStatusMessage(new StringTextComponent(TextFormatting.DARK_RED + "You have already selected a class."), false);

            }
            player.closeScreen();
        }
    }

    public static void setClassC(Map<String, Object> dependencies1) {
        Entity entity = (Entity) dependencies1.get("entity");
        if (entity instanceof ServerPlayerEntity) {
            ServerPlayerEntity player = (ServerPlayerEntity) entity;
            ItemStack giveBow = new ItemStack(Items.BOW, (int) (1));
            ItemStack giveArrows = new ItemStack(Items.ARROW, (int) (1));
            if (ClassesSavedData.get(player.server).getPlayerClass(player).equals("NO_CLASS")) {
                player.sendStatusMessage(new StringTextComponent(TextFormatting.GRAY + "You selected class: " + TextFormatting.GOLD + "Craftsman"), false);
                Skill.ARCHERY.addLevel(player.getUniqueID(), 10, "selectedClassC", false, true);
                Skill.AGILITY.addLevel(player.getUniqueID(), 5, "selectedClassC", false, true);
                giveBow.setCount((int) 1);
                ItemHandlerHelper.giveItemToPlayer((player), giveBow);
                giveArrows.setCount((int) 16);
                ItemHandlerHelper.giveItemToPlayer((player), giveArrows);
                player.abilities.setWalkSpeed((float) (player.abilities.getWalkSpeed() + 0.03));
                ClassesSavedData.get(player.server).setPlayerClass(player, "CRAFTSMAN");
                classXpBooster.put(ARCHERY, 10.0);
                Config.setPlayerXpBoost(player, "classes.craftsman", classXpBooster);
            }
            else {
                player.sendStatusMessage(new StringTextComponent(TextFormatting.DARK_RED + "You have already selected a class."), false);
            }
            player.closeScreen();
        }
    }
    public static void setClassCo(Map<String, Object> dependencies1) {
        Entity entity = (Entity) dependencies1.get("entity");
        if (entity instanceof ServerPlayerEntity) {
            ServerPlayerEntity player = (ServerPlayerEntity) entity;
            ItemStack givePotatoes = new ItemStack(POTATO, (int) (1));
            ItemStack giveHoe = new ItemStack(STONE_HOE, (int) (1));
            if (ClassesSavedData.get(player.server).getPlayerClass(player).equals("NO_CLASS")) {
                player.sendStatusMessage(new StringTextComponent(TextFormatting.GRAY + "You selected class: " + TextFormatting.GREEN + "Collector"), false);
                Skill.FARMING.addLevel(player.getUniqueID(), 10, "selectedClassCo", false, true);
                Skill.COOKING.addLevel(player.getUniqueID(), 5, "selectedClassCo", false, true);
                giveHoe.setCount((int) 1);
                ItemHandlerHelper.giveItemToPlayer((player), giveHoe);
                givePotatoes.setCount((int) 12);
                ItemHandlerHelper.giveItemToPlayer((player), givePotatoes);
                ClassesSavedData.get(player.server).setPlayerClass(player, "COLLECTOR");
                classXpBooster.put(FARMING, 10.0);
                Config.setPlayerXpBoost(player, "classes.collector", classXpBooster);
            }
            else {
                player.sendStatusMessage(new StringTextComponent(TextFormatting.DARK_RED + "You have already selected a class."), false);
            }
            player.closeScreen();
        }
    }
    public static void setClassE(Map<String, Object> dependencies1) {
        Entity entity = (Entity) dependencies1.get("entity");
        if (entity instanceof ServerPlayerEntity) {
            ServerPlayerEntity player = (ServerPlayerEntity) entity;
            ItemStack giveIronIngots = new ItemStack(IRON_INGOT, (int) (1));
            ItemStack giveAnvil = new ItemStack(ANVIL, (int) (1));
            if (ClassesSavedData.get(player.server).getPlayerClass(player).equals("NO_CLASS")) {
                player.sendStatusMessage(new StringTextComponent(TextFormatting.GRAY + "You selected class: " + TextFormatting.WHITE + "Explorer"), false);
                Skill.SMITHING.addLevel(player.getUniqueID(), 10, "selectedClassE", false, true);
                Skill.CRAFTING.addLevel(player.getUniqueID(), 10, "selectedClassE", false, true);
                giveAnvil.setCount((int) 1);
                ItemHandlerHelper.giveItemToPlayer((player), giveAnvil);
                giveIronIngots.setCount((int) 12);
                ItemHandlerHelper.giveItemToPlayer((player), giveIronIngots);
                ClassesSavedData.get(player.server).setPlayerClass(player, "EXPLORER");
                classXpBooster.put(SMITHING, 10.0);
                Config.setPlayerXpBoost(player, "classes.explorer", classXpBooster);
            }
            else {
                player.sendStatusMessage(new StringTextComponent(TextFormatting.DARK_RED + "You have already selected a class."), false);
            }
            player.closeScreen();
        }
    }
}

