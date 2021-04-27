package com.qsided.classesmod.config;

import java.util.ArrayList;

import com.qsided.classesmod.playerClasses.PlayerClass;

public class PlayerClassHandler {

	public static ArrayList<PlayerClass> playerClasses = new ArrayList<PlayerClass>();

	public static void addPlayerClass(PlayerClass pc) {
		playerClasses.add(pc);

		System.out
				.println("Player Class Added: " + pc.className + ", " + pc.classXpBoosters + ", " + pc.getClassItems());
	}

	public static ArrayList<PlayerClass> getClasses() {
		return playerClasses;
	}

}