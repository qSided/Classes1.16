package com.qsided.classesmod.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.minecraftforge.fml.loading.FMLPaths;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonWrite {

    public static String fileName = "classes" + ".json";
    public static final String path = "classes/";

    public static File file = FMLPaths.CONFIGDIR.get().resolve(path + fileName).toFile();

    public static void main() {

        if (file.exists()) {
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.setPrettyPrinting().create();

            ClassesClass classOne = new ClassesClass();
            classOne.setName("Soldier");
            classOne.setBooster(5.0);

            ClassesClass classTwo = new ClassesClass();
            classTwo.setName("Freeman");
            classTwo.setBooster(5.0);

            String class1 = gson.toJson(classOne);
            String class2 = gson.toJson(classTwo);

            ClassesClass classesObj = new ClassesClass();
            List classes = new ArrayList();
            classes.add(class1);
            classes.add(class2);
            classesObj.setClasses(classes);


            try {
                FileWriter writer = new FileWriter(file);
                writer.write(String.valueOf(classes));
                writer.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
