package me.truekenny.MyAntiPillar;


import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public class MyAntiPillar extends JavaPlugin {
    private Logger log = Logger.getLogger("Minecraft");
    /**
     * Экземпляр конфигурации
     */
    public FileConfiguration config;

    public int minimumQuantity;

    public void onEnable() {
        defaultConfig();

        PluginManager pm = getServer().getPluginManager();
        PlayerListener playerListener = new PlayerListener(this);
        pm.registerEvents(playerListener, this);


        log("MyAntiPillar has been enabled! +");
    }

    public void onDisable() {
        log("MyAntiPillar has been disabled.");
    }

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public void log(String text, String color) {
        this.log.info(color + text + ANSI_RESET);
    }

    public void log(String text) {
        log(text, ANSI_GREEN);
    }

    public void defaultConfig() {
        config = getConfig();

        config.addDefault("minimumQuantity", 7);
        // config.addDefault("reason", "You can not put this block here, try to strengthen the sides of the base unit");

        config.options().copyDefaults(true);
        saveConfig();

        minimumQuantity = config.getInt("minimumQuantity");
    }

}
