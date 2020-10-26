package com.qsided.classesmod.classes;


import com.qsided.classesmod.ClassesMod;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;
import net.minecraft.world.storage.WorldSavedData;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

import static net.minecraft.world.World.OVERWORLD;

public class ClassesSavedData extends WorldSavedData {
    private static String NAME = ClassesMod.MOD_ID;
    Map<UUID, String> classesMap = new HashMap<>();

    public ClassesSavedData() {
        super(NAME);
    }

    @Override
    public void read(CompoundNBT inData) {
        if(inData.contains("classes")) {
            CompoundNBT classesNBT = inData.getCompound("classes");

            for(String key : classesNBT.keySet()) {
                classesMap.put(UUID.fromString(key), classesNBT.getString(key));
            }
        }
    }

    @Override
    public CompoundNBT write(CompoundNBT outData) {
        
            for (Map.Entry<UUID, String> entry : classesMap.entrySet()) {
                outData.putString(entry.getKey().toString(), entry.getValue());
            }

        return outData;
    }

    public static ClassesSavedData get(MinecraftServer server) {
        return server.getWorld(OVERWORLD).getSavedData().getOrCreate(ClassesSavedData::new, NAME);
    }

    public String getPlayerClass(ServerPlayerEntity player) {
        return classesMap.getOrDefault(player.getUniqueID(), "NO_CLASS");
    }

    public void setPlayerClass(ServerPlayerEntity player, String newClass) {
        classesMap.put(player.getUniqueID(), newClass);
        this.markDirty();
    }
}