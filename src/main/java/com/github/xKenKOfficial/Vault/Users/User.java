package com.github.xKenKOfficial.Vault.Users;

import com.github.xKenKOfficial.Vault.Files.UserFile;

import org.bukkit.entity.Player;

public class User
{
    private static UserFile userFile;

    public static String getUserUUUID(Player p)
    {
        final String userUUID = getDataFolder().getFile().getString("users." + p.getName() + ".uuid");
        return userUUID;
    }

    public static int getUserMoney(Player p)
    {
        final int money = getDataFolder().getFile().getInt("users." + p.getName() + ".money");
        return money;
    }

    private static UserFile getDataFolder()
    {
        return userFile;
    }

    static {
        userFile = UserFile.getInstance();
    }
}
