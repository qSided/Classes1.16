package com.qsided.classesmod.classes;

import harmonised.pmmo.util.NBTHelper;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;
import net.minecraft.world.storage.WorldSavedData;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ClassesSavedData extends WorldSavedData {
    private static final String NAME = "classes";
    Map<UUID, String> classesMap = new HashMap<>();
    Map<UUID, String> subclassesMap = new HashMap<>();
    Map<UUID, Boolean> joinedMap = new HashMap<>();
    Map<UUID, Long> claimedMap = new HashMap<>();

    public ClassesSavedData() {
        super(NAME);
    }

    @Override
    public void read(CompoundNBT inData) {
        if(inData.contains("classes")) {
            CompoundNBT classesNBT = inData.getCompound("classes");
            for (String key : classesNBT.keySet()) {
                classesMap.put(UUID.fromString(key), classesNBT.getString(key));
            }
        }

        if(inData.contains("hasJoinedBefore")) {
            CompoundNBT classesNBT = inData.getCompound("hasJoinedBefore");
            for (String key : classesNBT.keySet()) {
                joinedMap.put(UUID.fromString(key), classesNBT.getBoolean(key));
            }
        }

        if(inData.contains("dailyRewardClaimedTime")) {
            CompoundNBT classesNBT = inData.getCompound("dailyRewardClaimedTime");
            for (String key : classesNBT.keySet()) {
                claimedMap.put(UUID.fromString(key), classesNBT.getLong(key));
            }
        }

        if(inData.contains("subclasses")) {
            CompoundNBT classesNBT = inData.getCompound("subclasses");
            for (String key : classesNBT.keySet()) {
                subclassesMap.put(UUID.fromString(key), classesNBT.getString(key));
            }
        }
    }

    @Override
    public CompoundNBT write(CompoundNBT outData) {
        outData = new CompoundNBT();

        CompoundNBT newNBT = new CompoundNBT();
        for (Map.Entry<UUID, String> entry : classesMap.entrySet()) {
            newNBT.putString(entry.getKey().toString(), entry.getValue());
        }

        CompoundNBT newNBT2 = new CompoundNBT();
        for (Map.Entry<UUID, Boolean> entry2 : joinedMap.entrySet()) {
            newNBT2.putBoolean(entry2.getKey().toString(), entry2.getValue());
        }

        CompoundNBT newNBT3 = new CompoundNBT();
        for (Map.Entry<UUID, Long> entry3 : claimedMap.entrySet()) {
            newNBT3.putInt(entry3.getKey().toString(), Math.toIntExact(entry3.getValue()));
        }

        CompoundNBT newNBT4 = new CompoundNBT();
        for (Map.Entry<UUID, String> entry4 : subclassesMap.entrySet()) {
            newNBT4.putString(entry4.getKey().toString(), entry4.getValue());
        }

        outData.put("classes", newNBT);
        outData.put("hasJoinedBefore", newNBT2);
        outData.put("dailyRewardClaimedTime", newNBT3);
        outData.put("subclasses", newNBT4);
        return outData;
    }

    public static ClassesSavedData get(MinecraftServer server) {
        return server.getWorld(World.OVERWORLD).getSavedData().getOrCreate(ClassesSavedData::new, NAME);
    }

    // Set/get player class
    public String getPlayerClass(ServerPlayerEntity player) {
        return classesMap.getOrDefault(player.getUniqueID(), "NO_CLASS");
    }
    public void setPlayerClass(ServerPlayerEntity player, String newClass) {
        classesMap.put(player.getUniqueID(), newClass);
        this.markDirty();
    }

    // Set/get player subclass
    public String getPlayerSubclass(ServerPlayerEntity player) {
        return subclassesMap.getOrDefault(player.getUniqueID(), "NO_SUBCLASS");
    }
    public void setPlayerSubclass(ServerPlayerEntity player, String newSubclass) {
        subclassesMap.put(player.getUniqueID(), newSubclass);
        this.markDirty();
    }

    // Set/get player if the player has joined before
    public Boolean getPlayerJoined(ServerPlayerEntity player) {
        return joinedMap.getOrDefault(player.getUniqueID(), false);
    }
    public void setPlayerJoined(ServerPlayerEntity player, Boolean hasJoined) {
        joinedMap.put(player.getUniqueID(), hasJoined);
        this.markDirty();
    }

    // Set/get player last daily reward claim time
    public Long getPlayerClaimed(ServerPlayerEntity player) {
        return claimedMap.getOrDefault(player.getUniqueID(), 720L);
    }
    public void setPlayerClaimed(ServerPlayerEntity player, Long claimedAt) {
        claimedMap.put(player.getUniqueID(), claimedAt);
        this.markDirty();
    }
}