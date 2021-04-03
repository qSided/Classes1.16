package com.qsided.classesmod.config;

import com.google.gson.JsonObject;

public class JSONUtil {

	public static void loadJSON() {

	}

	public static void saveJSON() {
		JsonObject obj = new JsonObject();
		JsonObject classes = new JsonObject();

		JsonObject class1 = new JsonObject();
		JsonObject class1Skills = new JsonObject();
		JsonObject class2 = new JsonObject();
		JsonObject class2Skills = new JsonObject();
		JsonObject class3 = new JsonObject();
		JsonObject class3Skills = new JsonObject();
		JsonObject class4 = new JsonObject();
		JsonObject class4Skills = new JsonObject();
		JsonObject class5 = new JsonObject();
		JsonObject class5Skills = new JsonObject();
		JsonObject class6 = new JsonObject();
		JsonObject class6Skills = new JsonObject();

		class1Skills.addProperty("COMBAT", 5);
		class1Skills.addProperty("ARCHERY", 5);
		class1Skills.addProperty("ENDURANCE", 5);

		class1.addProperty("Class Name", "Fighter");
		class1.add("Skills", class1Skills);

		//TODO items
		
		classes.add("Class 1", class1);

		obj.add("classes", classes);

		System.out.println(obj);
	}

}
