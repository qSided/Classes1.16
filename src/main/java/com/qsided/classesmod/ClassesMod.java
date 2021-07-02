package com.qsided.classesmod;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qsided.classesmod.config.ClassesConfigs;
import com.qsided.classesmod.config.ModJSONUtil;
import com.qsided.classesmod.events.ClassEvents;
import com.qsided.classesmod.gui.GuiElement;
import com.qsided.classesmod.network.PacketHandler;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;
import net.minecraftforge.forgespi.language.ModFileScanData;

@Mod("classes")
public class ClassesMod {

	public static File file = FMLPaths.CONFIGDIR.get().resolve("classes.json").toFile();

	public static final Logger LOGGER = LogManager.getLogger();
	public static final String MOD_ID = "classes";
	public static ClassesMod instance;
	public final List<GuiElement> guiElements = new ArrayList<>();

	public List<GuiElement> getGuiElements() {
		return guiElements;
	}

	public ClassesMod() {

		if (!ModJSONUtil.file.exists()) {
			try {
				ModJSONUtil.file.createNewFile();
				ModJSONUtil.generateClassesJSON();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, ClassesConfigs.COMMON_SPEC,
				"classes-common.toml");
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);
		instance = this;

		MinecraftForge.EVENT_BUS.register(this);
		MinecraftForge.EVENT_BUS.register(ClassEvents.class);

		PacketHandler.register();

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

	// Load stuff on server and client
	private void setup(final FMLCommonSetupEvent event) {
		ModJSONUtil.loadClassesJSON(); // We must load this and it must be done after item registry.
	}

	// Load stuff on JUST the client
	private void doClientStuff(final FMLClientSetupEvent event) {
	}

	// TODO Send classes config from server to client when client joins (so all the
	// numbers are correct)
}
