package com.qsided.classesmod;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.qsided.classesmod.config.ClassesClass;
import com.qsided.classesmod.config.ClassesConfigs;
import com.qsided.classesmod.config.JsonWrite;
import com.qsided.classesmod.events.ClassEvents;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;
import net.minecraftforge.fml.network.NetworkEvent;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;
import net.minecraftforge.forgespi.language.ModFileScanData;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;

@Mod("classes")
public class ClassesMod {

    public static File file = FMLPaths.CONFIGDIR.get().resolve("classes.json").toFile();

    private static final String PROTOCOL_VERSION = "1";
    public static final SimpleChannel PACKET_HANDLER = NetworkRegistry.newSimpleChannel(new ResourceLocation("classes", "main"),
            () -> PROTOCOL_VERSION, PROTOCOL_VERSION::equals, PROTOCOL_VERSION::equals);

    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ID = "classes";
    public static ClassesMod instance;
    public final List<GuiElement> guiElements = new ArrayList<>();

    private int messageID = 0;
    public <T> void addNetworkMessage(Class<T> messageType, BiConsumer<T, PacketBuffer> encoder, Function<PacketBuffer, T> decoder,
                                      BiConsumer<T, Supplier<NetworkEvent.Context>> messageConsumer) {
        ClassesMod.PACKET_HANDLER.registerMessage(messageID, messageType, encoder, decoder, messageConsumer);
        messageID++;
    }

    public List<GuiElement> getGuiElements() {
        return guiElements;
    }

    public static class GuiElement implements Comparable<GuiElement> {
        @Retention(RetentionPolicy.RUNTIME)
        public @interface Tag {
        }
        protected final ClassesMod guiElements;
        protected final int sortid;
        public GuiElement(ClassesMod elements, int sortid) {
            this.guiElements = elements;
            this.sortid = sortid;
        }

        public void initElements() {
        }

        public void init(FMLCommonSetupEvent event) throws IOException {
        }

        public void serverLoad(FMLServerStartingEvent event) {
        }

        @OnlyIn(Dist.CLIENT)
        public void clientLoad(FMLClientSetupEvent event) {
        }

        @Override
        public int compareTo(GuiElement other) {
            return this.sortid - other.sortid;
        }
    }


    public ClassesMod() {

        final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(this::setup);

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, ClassesConfigs.COMMON_SPEC, "classes-common.toml");
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);
        instance = this;

        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.register(ClassEvents.class);


        try {
            ModFileScanData modFileInfo = ModList.get().getModFileById("classes").getFile().getScanResult();
            Set<ModFileScanData.AnnotationData> annotations = modFileInfo.getAnnotations();
            for (ModFileScanData.AnnotationData annotationData : annotations) {
                if (annotationData.getAnnotationType().getClassName().equals(GuiElement.Tag.class.getName())) {
                    Class<?> clazz = Class.forName(annotationData.getClassType().getClassName());
                    if (clazz.getSuperclass() == GuiElement.class)
                        guiElements.add((GuiElement) clazz.getConstructor(this.getClass()).newInstance(this));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Collections.sort(guiElements);
        guiElements.forEach(GuiElement::initElements);
    }


    private void setup(final FMLCommonSetupEvent event)
    {
        //JsonWrite.main();
    }

    private void doClientStuff(final FMLClientSetupEvent event)
    {

    }
}
