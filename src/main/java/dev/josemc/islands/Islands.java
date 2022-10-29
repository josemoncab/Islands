package dev.josemc.islands;

import org.bukkit.plugin.java.JavaPlugin;

public class Islands extends JavaPlugin {

    private static Islands instance;
    @Override
    public void onEnable() {
        instance = this;
    }

    public static Islands get() {
        return instance;
    }

}
