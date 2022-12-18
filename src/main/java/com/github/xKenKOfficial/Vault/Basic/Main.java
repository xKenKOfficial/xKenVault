package com.github.xKenKOfficial.Vault.Basic;

import com.github.xKenKOfficial.Vault.Apis.API;
import com.github.xKenKOfficial.Vault.Commands.MoneyCommand;
import com.github.xKenKOfficial.Vault.Commands.PayCommand;
import com.github.xKenKOfficial.Vault.Commands.SetMoneyCommand;
import com.github.xKenKOfficial.Vault.Listeners.PlayerJoin;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin
{
    private static Main plugin;

    @Override
    public void onEnable()
    {
        (plugin) = this;
        this.saveDefaultConfig();
        this.registerCommands();
        this.registerListeners();
        this.registerFiles();
        System.out.println("###########################################################");
        System.out.println(this.getName());
        System.out.println("Wersja: " + this.getDescription().getVersion());
        System.out.println("Wykryta wersja Bukkit: " + Bukkit.getBukkitVersion());
        System.out.println(this.isEnabled() ? "Aktywowany" : "Dezaktywowany");
        System.out.println("Jakiekolwiek edycje i naruszenie praw autorskich - ZABRONIONE!");
        System.out.println("###########################################################");
    }

    @Override
    public void onDisable()
    {
        this.saveDefaultConfig();
        this.saveFiles();
        System.out.println("###########################################################");
        System.out.println(this.getName());
        System.out.println("Wersja: " + this.getDescription().getVersion());
        System.out.println("Wykryta wersja Bukkit: " + Bukkit.getBukkitVersion());
        System.out.println(this.isEnabled() ? "Aktywowany" : "Dezaktywowany");
        System.out.println("Jakiekolwiek edycje i naruszenie praw autorskich - ZABRONIONE!");
        System.out.println("###########################################################");
    }

    private void registerCommands()
    {
        this.getLogger().info("Ladowanie komend z pluginu: " + this.getName());
        this.getCommand("money").setExecutor(new MoneyCommand());
        this.getCommand("pay").setExecutor(new PayCommand());
        this.getCommand("setmoney").setExecutor(new SetMoneyCommand());
    }

    private void registerListeners()
    {
        this.getLogger().info("Ladowanie eventow z pluginu: " + this.getName());
        Bukkit.getPluginManager().registerEvents((Listener)new PlayerJoin(), (Plugin)this);
    }

    private void registerFiles()
    {
        this.getLogger().info("Ladowanie plikow konfiguracyjnych z pluginu: " + this.getName());
        API.getDataFolder().setup((Plugin)this);
    }

    private void saveFiles()
    {
        this.getLogger().info("Zapisywanie plikow konfiguracyjnych z pluginu: " + this.getName());
        API.getDataFolder().saveData();
    }

    public static Main getPlugin()
    {
        return plugin;
    }
}
