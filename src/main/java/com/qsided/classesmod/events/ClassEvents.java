package com.qsided.classesmod.events;

import com.qsided.classesmod.ClassesMod;
import com.qsided.classesmod.classes.ClassesSavedData;
import com.qsided.classesmod.config.ClassesConfigs;
import com.qsided.classesmod.gui.OpenGUI;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.*;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.Hand;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.items.ItemHandlerHelper;

import java.util.HashMap;
import java.util.Map;

import static net.minecraft.item.Items.*;

public class ClassEvents extends ClassesMod.GuiElement {
    public ClassEvents(ClassesMod instance) {
        super(instance, 2);
    }

    @SubscribeEvent
    public static void playerTickEvent(TickEvent.PlayerTickEvent event) {
        Entity entity = event.player;
        if (entity instanceof ServerPlayerEntity) {
            ServerPlayerEntity player = (ServerPlayerEntity) entity;
            if (
                    player.getHeldItem(Hand.MAIN_HAND).getItem() instanceof AxeItem ||
                            player.getHeldItem(Hand.MAIN_HAND).getItem() instanceof PickaxeItem &&
                                    ClassesSavedData.get(player.server)
                                            .getPlayerClass(player).equals("FREEMA")) {
                player.addPotionEffect(new EffectInstance(Effects.HASTE, 60, 0));
            }
        }
        if (entity instanceof ServerPlayerEntity) {
            ServerPlayerEntity player = (ServerPlayerEntity) entity;
            if (ClassesSavedData.get(player.server).getPlayerClass(player).equals("SOLDIE")){
                if (
                        player.getHeldItem(Hand.MAIN_HAND).getItem() instanceof SwordItem ||
                        player.getHeldItem(Hand.MAIN_HAND).getItem() instanceof AxeItem) {

                    player.addPotionEffect(new EffectInstance(Effects.STRENGTH, 60, 0));
                }
            }
        }
        if (entity instanceof ServerPlayerEntity) {
            ServerPlayerEntity player = (ServerPlayerEntity) entity;
            if (ClassesSavedData.get(player.server).getPlayerClass(player).equals("MAGICIA")) {
                if (((EnchantmentHelper.getEnchantmentLevel(Enchantments.UNBREAKING, ((ServerPlayerEntity) entity).getHeldItemMainhand())) <= 1)) {
                    if (player.getHeldItem(Hand.MAIN_HAND).getItem() instanceof PickaxeItem
                            || player.getHeldItem(Hand.MAIN_HAND).getItem() instanceof AxeItem
                            || player.getHeldItem(Hand.MAIN_HAND).getItem() instanceof SwordItem
                            || player.getHeldItem(Hand.MAIN_HAND).getItem() instanceof HoeItem
                            || player.getHeldItem(Hand.MAIN_HAND).getItem() instanceof ShovelItem) {
                        player.getHeldItem(Hand.MAIN_HAND).addEnchantment(Enchantments.UNBREAKING, (int) 1);
                    }
                }
            }
        }
    }


    //Opens gui on first join
    @SubscribeEvent
    public static void playJoinedWorldEvent(PlayerEvent.PlayerLoggedInEvent event) {
        ServerPlayerEntity player = (ServerPlayerEntity) event.getPlayer();
        if (ClassesConfigs.COMMON.openOnFirstJoin.get()) {
            if (!ClassesSavedData.get(player.server).getPlayerJoined(player)) {
                Map<String, Object> dependencies = new HashMap<>();
                dependencies.put("entity", player);
                dependencies.put("x", player.getPosX());
                dependencies.put("y", player.getPosY());
                dependencies.put("z", player.getPosZ());
                dependencies.put("world", player.getEntityWorld());
                OpenGUI.openGui(dependencies);
                ClassesSavedData.get(player.server).setPlayerJoined(player, true);
            }
        }


        //Update legacy
        if (ClassesSavedData.get(player.server).getPlayerClass(player).equals("MINER")) {
            ClassesSavedData.get(player.server).setPlayerClass(player, "WORKER");
            player.sendStatusMessage(new StringTextComponent
                            (TextFormatting.GRAY + "You're class has been updated to: "
                                    + TextFormatting.GOLD + "Worker"),
                    false);
        }
        if (ClassesSavedData.get(player.server).getPlayerClass(player).equals("LUMBERJACK")) {
            ClassesSavedData.get(player.server).setPlayerClass(player, "WORKER");
            player.sendStatusMessage(new StringTextComponent
                            (TextFormatting.GRAY + "You're class has been updated to: "
                                    + TextFormatting.GOLD + "Worker"),
                    false);
        }
        if (ClassesSavedData.get(player.server).getPlayerClass(player).equals("WARRIOR")) {
            ClassesSavedData.get(player.server).setPlayerClass(player, "FIGHTER");
            player.sendStatusMessage(new StringTextComponent
                            (TextFormatting.GRAY + "You're class has been updated to: "
                                    + TextFormatting.RED + "Fighter"),
                    false);
        }
        if (ClassesSavedData.get(player.server).getPlayerClass(player).equals("ARCHER")) {
            ClassesSavedData.get(player.server).setPlayerClass(player, "FIGHTER");
            player.sendStatusMessage(new StringTextComponent
                            (TextFormatting.GRAY + "You're class has been updated to: "
                                    + TextFormatting.RED + "Fighter"),
                            false);
        }
        if (ClassesSavedData.get(player.server).getPlayerClass(player).equals("BLACKSMITH")) {
            ClassesSavedData.get(player.server).setPlayerClass(player, "ARTISAN");
            player.sendStatusMessage(new StringTextComponent
                            (TextFormatting.GRAY + "You're class has been updated to: "
                                    + TextFormatting.BLUE + "Artisan"), false);
        }
        if (ClassesSavedData.get(player.server).getPlayerClass(player).equals("FARMER")) {
            ClassesSavedData.get(player.server).setPlayerClass(player, "SURVIVOR");
            player.sendStatusMessage(new StringTextComponent
                            (TextFormatting.GRAY + "You're class has been updated to: "
                                    + TextFormatting.GREEN + "Survivor"), false);
        }

        //Update legacy 2.0
        if (ClassesSavedData.get(player.server).getPlayerClass(player).equals("FIGHTER")) {
            ClassesSavedData.get(player.server).setPlayerClass(player, "SOLDIER");
            player.sendStatusMessage(new StringTextComponent
                            (TextFormatting.GRAY + "You're class has been updated to: "
                                    + TextFormatting.RED + "Soldier"),
                    false);
        }
        if (ClassesSavedData.get(player.server).getPlayerClass(player).equals("WORKER")) {
            ClassesSavedData.get(player.server).setPlayerClass(player, "FREEMAN");
            player.sendStatusMessage(new StringTextComponent
                            (TextFormatting.GRAY + "You're class has been updated to: "
                                    + TextFormatting.AQUA + "Freeman"),
                    false);
        }
        if (ClassesSavedData.get(player.server).getPlayerClass(player).equals("SURVIVOR")) {
            ClassesSavedData.get(player.server).setPlayerClass(player, "SURVIVALIST");
            player.sendStatusMessage(new StringTextComponent
                            (TextFormatting.GRAY + "You're class has been updated to: "
                                    + TextFormatting.GOLD + "Survivalist"),
                    false);
        }
        if (ClassesSavedData.get(player.server).getPlayerClass(player).equals("EXPLORER")) {
            ClassesSavedData.get(player.server).setPlayerClass(player, "ADVENTURER");
            player.sendStatusMessage(new StringTextComponent
                            (TextFormatting.GRAY + "You're class has been updated to: "
                                    + TextFormatting.GREEN + "Adventurer"),
                    false);
        }
        if (ClassesSavedData.get(player.server).getPlayerClass(player).equals("MAGICIAN")) {
            ClassesSavedData.get(player.server).setPlayerClass(player, "MAGE");
            player.sendStatusMessage(new StringTextComponent
                            (TextFormatting.GRAY + "You're class has been updated to: "
                                    + TextFormatting.BLUE + "Mage"),
                    false);



        //Daily reward
        if (ClassesConfigs.COMMON.doDailyRewards.get()) {
            Long currentTime = System.currentTimeMillis()/1000/60;
            if (currentTime - ClassesSavedData.get(player.server).getPlayerClaimed(player) >= 720L) {

                //Fighter
                ItemStack giveGoldenApple = new ItemStack(GOLDEN_APPLE, (int) (1));
                if (ClassesSavedData.get(player.server).getPlayerClass(player).equals("SOLDIER")) {
                    giveGoldenApple.setCount((int) 1);
                    ItemHandlerHelper.giveItemToPlayer((player), giveGoldenApple);
                    player.sendStatusMessage(new StringTextComponent(TextFormatting.DARK_GRAY +
                            "You claimed your daily reward of " + TextFormatting.RED + "1 Golden Apple" + TextFormatting.DARK_GRAY +
                            " by logging in today!"), false);
                    ClassesSavedData.get(player.server).setPlayerClaimed(player, System.currentTimeMillis()/1000/60);
                }

                //Survivor
                ItemStack giveHayBales = new ItemStack(IRON_ORE, (int) (1));
                if (ClassesSavedData.get(player.server).getPlayerClass(player).equals("SURVIVALIST")) {
                    giveHayBales.setCount((int) 6);
                    ItemHandlerHelper.giveItemToPlayer((player), giveHayBales);
                    player.sendStatusMessage(new StringTextComponent(TextFormatting.DARK_GRAY +
                            "You claimed your daily reward of " + TextFormatting.GREEN + "6 Hay Bales" + TextFormatting.DARK_GRAY +
                            " by logging in today!"), false);
                    ClassesSavedData.get(player.server).setPlayerClaimed(player, System.currentTimeMillis()/1000/60);
                }

                //Artisan
                ItemStack giveAnvil = new ItemStack(ANVIL, (int) (1));
                if (ClassesSavedData.get(player.server).getPlayerClass(player).equals("ARTISAN")) {
                    giveAnvil.setCount((int) 1);
                    ItemHandlerHelper.giveItemToPlayer((player), giveAnvil);
                    player.sendStatusMessage(new StringTextComponent(TextFormatting.DARK_GRAY +
                            "You claimed your daily reward of " + TextFormatting.YELLOW + "1 Anvil" + TextFormatting.DARK_GRAY +
                            " by logging in today!"), false);
                    ClassesSavedData.get(player.server).setPlayerClaimed(player, System.currentTimeMillis()/1000/60);
                }

                //Worker
                ItemStack giveIronOre = new ItemStack(IRON_ORE, (int) (1));
                if (ClassesSavedData.get(player.server).getPlayerClass(player).equals("FREEMAN")) {
                    giveIronOre.setCount((int) 12);
                    ItemHandlerHelper.giveItemToPlayer((player), giveIronOre);
                    player.sendStatusMessage(new StringTextComponent(TextFormatting.DARK_GRAY +
                            "You claimed your daily reward of " + TextFormatting.GOLD + "12 Iron Ore" + TextFormatting.DARK_GRAY +
                            " by logging in today!"), false);
                    ClassesSavedData.get(player.server).setPlayerClaimed(player, System.currentTimeMillis()/1000/60);
                }

                //Explorer
                if (ClassesSavedData.get(player.server).getPlayerClass(player).equals("ADVENTURER")) {
                    giveHayBales.setCount((int) 16);
                    ItemHandlerHelper.giveItemToPlayer((player), giveHayBales);
                    player.sendStatusMessage(new StringTextComponent(TextFormatting.DARK_GRAY +
                            "You claimed your daily reward of " + TextFormatting.AQUA + "1 Diamond" + TextFormatting.DARK_GRAY +
                            " by logging in today!"), false);
                    ClassesSavedData.get(player.server).setPlayerClaimed(player, System.currentTimeMillis()/1000/60);
                }

                //Magician
                ItemStack giveXpBottles = new ItemStack(EXPERIENCE_BOTTLE, (int) (1));
                if (ClassesSavedData.get(player.server).getPlayerClass(player).equals("MAGE")) {
                    giveXpBottles.setCount((int) 8);
                    ItemHandlerHelper.giveItemToPlayer((player), giveXpBottles);
                    player.sendStatusMessage(new StringTextComponent(TextFormatting.DARK_GRAY +
                            "You claimed your daily reward of " + TextFormatting.DARK_BLUE + "8 Xp Bottles" + TextFormatting.DARK_GRAY +
                            " by logging in today!"), false);
                    ClassesSavedData.get(player.server).setPlayerClaimed(player, System.currentTimeMillis()/1000/60);
                }
            }
            else {
                player.sendStatusMessage(new StringTextComponent(TextFormatting.DARK_RED +
                        "You have already claimed your daily login reward!"), false);
                ClassesMod.LOGGER.info(System.currentTimeMillis());
                }
            }
        }
    }
}
