package com.qsided.classesmod.gui;

import java.io.IOException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import com.qsided.classesmod.ClassesMod;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;

public class GuiElement implements Comparable<GuiElement> {
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