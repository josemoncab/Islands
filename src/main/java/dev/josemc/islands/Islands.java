package dev.josemc.islands;

import dev.josemc.islands.files.FileManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Islands extends JavaPlugin {

    private static Islands instance;
    private FileManager fm;
    @Override
    public void onEnable() {
        instance = this;

        fm = new FileManager();
    }

    public static Islands get() {
        return instance;
    }

    public FileManager getFm() {
        return fm;
    }
}
