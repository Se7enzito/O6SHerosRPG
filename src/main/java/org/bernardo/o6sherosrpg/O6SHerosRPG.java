package org.bernardo.o6sherosrpg;

import org.bernardo.o6sherosrpg.classes.APIs.ClassesManagerAPI;
import org.bernardo.o6sherosrpg.classes.CommandClasses;
import org.bernardo.o6sherosrpg.classes.templates.ClassesTemplate;
import org.bernardo.o6sherosrpg.database.DatabaseSetup;
import org.bernardo.o6sherosrpg.levels.APIs.LevelManagerAPI;
import org.bernardo.o6sherosrpg.redis.RedisManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;
import java.util.logging.Logger;

public final class O6SHerosRPG extends JavaPlugin {

    public static O6SHerosRPG m;
    public static RedisManager redisManager;

    private final ConsoleCommandSender console = Bukkit.getConsoleSender();
    private final PluginManager pluginManager = Bukkit.getPluginManager();
    private final Logger logger = Bukkit.getLogger();
    private final long start_ms = System.currentTimeMillis();

    public static O6SHerosRPG getInstance() {
        return m;
    }

    @Override
    public void onEnable() {
        redisManager = new RedisManager();
        redisManager.connect();
        getLogger().info("Redis conectado.");

        final long loadms = System.currentTimeMillis() - start_ms;
        logger.log(Level.INFO, "[O6SMegaWalls] Loaded in %s" + loadms + "ms");

        getCommand("classes").setExecutor(new CommandClasses());

        pluginManager.registerEvents(new ClassesTemplate(), this);
        pluginManager.registerEvents(new ClassesManagerAPI(), this);
        pluginManager.registerEvents(new LevelManagerAPI(), this);

        console.sendMessage(ChatColor.GREEN + "O6SHerosRPG ligado com sucesso!");

        super.onEnable();
    }

    @Override
    public void onDisable() {
        HandlerList.unregisterAll();

        redisManager.close();
        getLogger().info("Redis desligado!");

        console.sendMessage(ChatColor.RED + "O6SHerosRPG desligado com sucesso!");

        super.onDisable();
    }

    @Override
    public void onLoad() {
        if (!getDataFolder().exists()) {
            getDataFolder().mkdirs();
        }

        DatabaseSetup.initializeDatabase();

        super.onLoad();
    }
}
