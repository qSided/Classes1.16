package com.qsided.classesmod.events;

import com.qsided.classesmod.ClassesMod;
import com.qsided.classesmod.classes.ClassesSavedData;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.AxeItem;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.SwordItem;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.Hand;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class ClassEvents extends ClassesMod.GuiElement {
    public ClassEvents(ClassesMod instance) {
        super(instance, 2);
    }

    @SubscribeEvent
    public static void playerTickEvent(TickEvent.PlayerTickEvent event) {
        Entity entity = event.player;
        if (entity instanceof ServerPlayerEntity) {
            ServerPlayerEntity serverPlayer = (ServerPlayerEntity) entity;
            if (serverPlayer.getHeldItem(Hand.MAIN_HAND).getItem() instanceof AxeItem && ClassesSavedData.get(serverPlayer.server).getPlayerClass(serverPlayer).equals("LUMBERJACK")) {
                serverPlayer.addPotionEffect(new EffectInstance(Effects.HASTE, 60, 0));
            }
        }
        if (entity instanceof ServerPlayerEntity) {
            ServerPlayerEntity serverPlayer = (ServerPlayerEntity) entity;
            if (serverPlayer.getHeldItem(Hand.MAIN_HAND).getItem() instanceof PickaxeItem && ClassesSavedData.get(serverPlayer.server).getPlayerClass(serverPlayer).equals("MINER")) {
                serverPlayer.addPotionEffect(new EffectInstance(Effects.HASTE, 60, 0));
            }
        }
        if (entity instanceof ServerPlayerEntity) {
            ServerPlayerEntity serverPlayer = (ServerPlayerEntity) entity;
            if (ClassesSavedData.get(serverPlayer.server).getPlayerClass(serverPlayer).equals("WARRIOR")){
                if (serverPlayer.getHeldItem(Hand.MAIN_HAND).getItem() instanceof SwordItem || serverPlayer.getHeldItem(Hand.MAIN_HAND).getItem() instanceof AxeItem) {
                    serverPlayer.addPotionEffect(new EffectInstance(Effects.STRENGTH, 60, 0));
                }
            }
        }
    }
    @SubscribeEvent
    public static void playJoinedWorldEvent(PlayerEvent.PlayerLoggedInEvent event) {
        ServerPlayerEntity serverPlayer = (ServerPlayerEntity) event.getPlayer();
        if (ClassesSavedData.get(serverPlayer.server).getPlayerClass(serverPlayer).equals("WARRIOR")) {
            Float speed = serverPlayer.abilities.getWalkSpeed();
            serverPlayer.abilities.setWalkSpeed((float) (speed - 0.04));
            ClassesMod.LOGGER.info("Successfully set Warrior " + serverPlayer.getDisplayName() + "'s speed to " + "[" + speed + "]");
        }
        else if (ClassesSavedData.get(serverPlayer.server).getPlayerClass(serverPlayer).equals("ARCHER")) {
            Float speed = serverPlayer.abilities.getWalkSpeed();
            serverPlayer.abilities.setWalkSpeed((float) (speed + 0.04));
            ClassesMod.LOGGER.info("Successfully set Archer " + serverPlayer.getDisplayName() + "'s speed to " + "[" + speed + "]");
        }
    }
}
