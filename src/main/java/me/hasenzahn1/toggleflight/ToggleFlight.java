package me.hasenzahn1.toggleflight;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.ArrayList;
import java.util.UUID;

public final class ToggleFlight extends JavaPlugin {

    private ArrayList<UUID> enabledPlayers;
    public static ToggleFlight INSTANCE;
    public static String PREFIX = "[ToggleFlight]";

    @Override
    public void onEnable() {
        enabledPlayers = new ArrayList<>();
        INSTANCE = this;

        if (!new File(getDataFolder(), "config.yml").exists()) {
            saveDefaultConfig();
            reloadConfig();
        }

        PREFIX = getLang("prefix");

        getCommand("toggleFlight").setExecutor(new ToggleFlightCommand());

        getServer().getPluginManager().registerEvents(new JumpListener(), this);
    }

    @Override
    public void onDisable() {
        enabledPlayers.clear();
    }

    public ArrayList<UUID> getEnabledPlayers() {
        return enabledPlayers;
    }

    public static String getLang(String key, String... args) {
        // PlotsquaredLog.INSTANCE.saveResource("config.yml", true); //Replace config TODO: REMOVE
        String lang = ToggleFlight.INSTANCE.getConfig().getString(key, "&cUnknown language key &6" + key);
        for (int i = 0; i + 1 < args.length; i += 2) {
            lang = lang.replace("%" + args[i] + "%", args[i + 1]);
        }
        return ChatColor.translateAlternateColorCodes('&', lang);
    }


}
