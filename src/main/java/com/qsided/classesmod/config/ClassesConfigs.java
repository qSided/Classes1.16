package com.qsided.classesmod.config;

import com.qsided.classesmod.ClassesMod;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import org.apache.commons.lang3.tuple.Pair;

@Mod.EventBusSubscriber(modid = ClassesMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClassesConfigs {

    public static class Common
    {
        //public final ForgeConfigSpec.ValueSpec lumberjackTool;

        public Common(ForgeConfigSpec.Builder builder) {
            builder.comment("Classes Configurations")
                    .push("classes");

            //lumberjackTool = builder
            //        .comment("What color should the last stand activation message be?")
            //        .translation("laststand.configgui.textColor")
            //        .worldRestart()
            //        .defineEnum("textColor", BLUE, BLACK);

            builder.pop();
        }
    }

    public static final ForgeConfigSpec COMMON_SPEC;
    public static final Common COMMON;
    static {
        final Pair<Common, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(Common::new);
        COMMON_SPEC = specPair.getRight();
        COMMON = specPair.getLeft();
    }

    @SubscribeEvent
    public static void onLoad(final ModConfig.Loading event) {

    }

    @SubscribeEvent
    public static void onFileChange(final ModConfig.Reloading event) {

    }
}
