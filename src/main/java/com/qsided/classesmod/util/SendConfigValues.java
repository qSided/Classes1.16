package com.qsided.classesmod.util;

import com.qsided.classesmod.config.PlayerClassHandler;
import java.util.HashMap;

public class SendConfigValues {

    HashMap expboost = PlayerClassHandler.getClasses().get(0).xpBoosters;
    String name = PlayerClassHandler.getClasses().get(0).className;
    String items = PlayerClassHandler.getClasses().get(0).getClassItems().iterator().toString();

    public String encode() {
        return expboost + "," + name + "," + items;
    }

    public String[] decode(String data) {
        return data.split(",");
    }
}
