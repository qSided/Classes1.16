package com.qsided.classesmod.subclasses.soldier;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.qsided.classesmod.ClassesMod;
import com.qsided.classesmod.classes.ClassesSavedData;
import com.qsided.classesmod.config.ClassesClass;
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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

import static harmonised.pmmo.skills.Skill.*;
import static net.minecraft.item.Items.*;

public class SoldierSubclassSelected extends ClassesMod.GuiElement {

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

    private static final ClassesClass classObj = gson.fromJson(br, ClassesClass.class);


    public SoldierSubclassSelected(ClassesMod instance) {
        super(instance, 2);
    }
    public static Item csvItem1 = FISHING_ROD;
    public static Item csvItem2 = APPLE;
    public static Item caItem1 = SMITHING_TABLE;
    public static Item caItem2 = OAK_LOG;

    public static TranslationTextComponent translation1 = new TranslationTextComponent("class.selected.true");
    public static String alreadySelected = translation1.getString();

    static Map<String, Double> subclassXpBooster = new HashMap<>();

    public static void setSubclassArcher(Map<String, Object> dependencies1) {

        Entity entity = (Entity) dependencies1.get("entity");
        if (entity instanceof ServerPlayerEntity)
        {
            ServerPlayerEntity player = (ServerPlayerEntity) entity;
            //-------------------------------------------------------------------------
            if (ClassesSavedData.get(player.server).getPlayerSubclass(player).equals("NO_SUBCLASS"))
            {
                ClassesSavedData.get(player.server).setPlayerSubclass(player,
                        "ARCHER");
                player.sendStatusMessage
                        (new StringTextComponent(
                                TextFormatting.GRAY + "You selected subclass: " +
                                        TextFormatting.YELLOW + "Archer"),
                                false);
                //-------------------------------------------------------------------------
                subclassXpBooster.put(ARCHERY.toString(), 5.0);
                Config.setPlayerXpBoost(player,
                        "subclass.archer",
                        subclassXpBooster);
                //-------------------------------------------------------------------------
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

    public static void setSubclassGuardian(Map<String, Object> dependencies1) {

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
                subclassXpBooster.put(ENDURANCE.toString(), 5.0);
                Config.setPlayerXpBoost(player,
                        "subclass.guardian",
                        subclassXpBooster);
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

    public static void setSubclassWarrior(Map<String, Object> dependencies1)
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
                subclassXpBooster.put(COMBAT.toString(), 5.0);
                Config.setPlayerXpBoost(player,
                        "subclass.warrior",
                        subclassXpBooster);
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
}

