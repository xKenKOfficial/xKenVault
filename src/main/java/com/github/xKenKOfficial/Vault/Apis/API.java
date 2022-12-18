package com.github.xKenKOfficial.Vault.Apis;

import com.github.xKenKOfficial.Vault.Basic.Main;
import com.github.xKenKOfficial.Vault.Files.UserFile;

import org.bukkit.entity.Player;

public class API
{
    private static UserFile userFile;

    public static void setUser(Player p)
    {
        Main.getPlugin().getLogger().info("Zaladowano uzytkownika " + p.getName() + " w pliku users.yml");
        getDataFolder().getFile().set("users." + p.getName() + ".uuid", (String)p.getUniqueId().toString());
        getDataFolder().getFile().set("users." + p.getName() + ".money", (int)0);
        getDataFolder().saveData();
    }

    public static void addMoney(Player p, int i)
    {
        getDataFolder().getFile().set("users." + p.getName() + ".money", getDataFolder().getFile().getInt("users." + p.getName() + ".money") + i);
        getDataFolder().saveData();
    }

    public static void removeMoney(Player p, int i)
    {
        getDataFolder().getFile().set("users." + p.getName() + ".money", getDataFolder().getFile().getInt("users." + p.getName() + ".money") - i);
        getDataFolder().saveData();
    }

    public static UserFile getDataFolder()
    {
        return userFile;
    }

    static {
        userFile = UserFile.getInstance();
    }
}
