package com.github.xKenKOfficial.Vault.Files;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;

public class UserFile
{
    static UserFile instance;
    Plugin p;
    FileConfiguration data;
    public static File rfile;

    public static UserFile getInstance()
    {
        return UserFile.instance;
    }

    public void setup(final Plugin p)
    {
        if (!p.getDataFolder().exists()) {
            p.getDataFolder().mkdir();
        }
        final File path = new File(p.getDataFolder() + File.separator + "/Users");
        UserFile.rfile = new File(path, String.valueOf(String.valueOf(File.separatorChar) + "users.yml"));
        if (!UserFile.rfile.exists()) {
            try {
                path.mkdirs();
                UserFile.rfile.createNewFile();
            }
            catch (IOException e) {
                Bukkit.getServer().getLogger().severe("Nie udalo sie zaladowac pliku users.yml");
            }
        }
        this.data = (FileConfiguration) YamlConfiguration.loadConfiguration(UserFile.rfile);
    }

    public FileConfiguration getFile()
    {
        return this.data;
    }

    public void saveData()
    {
        try {
            this.data.save(UserFile.rfile);
        }
        catch (IOException e) {
            Bukkit.getServer().getLogger().severe("Nie udalo sie zapisac pliku users.yml");
        }
    }

    public void reloadData()
    {
        this.data = (FileConfiguration)YamlConfiguration.loadConfiguration(UserFile.rfile);
    }

    static {
        UserFile.instance = new UserFile();
    }
}
