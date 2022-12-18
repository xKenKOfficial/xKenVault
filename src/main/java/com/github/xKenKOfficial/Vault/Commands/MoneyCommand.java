package com.github.xKenKOfficial.Vault.Commands;

import com.github.xKenKOfficial.Vault.Basic.Main;
import com.github.xKenKOfficial.Vault.Users.User;
import com.github.xKenKOfficial.Vault.Utils.ChatUtil;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MoneyCommand implements CommandExecutor
{
    private static final String NO_PERMISSION = Main.getPlugin().getConfig().getString("no_permission");
    private static final String WRONG_ARGS = Main.getPlugin().getConfig().getString("wrong_args");
    private static final String STATUS_MONEY = Main.getPlugin().getConfig().getString("money_status");

    @Override
    public boolean onCommand(final CommandSender Sender, final Command c, final String l, final String[] args) {
        if(!Sender.hasPermission("xkenvault.player.money")) {
            Sender.sendMessage(ChatUtil.fixColor(NO_PERMISSION));
            return false;
        }
        if(Sender instanceof Player) {
            if(args.length < 1) {
                Sender.sendMessage(ChatUtil.fixColor(STATUS_MONEY.replace("{MONEY}", String.valueOf(User.getUserMoney((Player)Sender)))));
                return false;
            } else {
                Sender.sendMessage(ChatUtil.fixColor(WRONG_ARGS));
            }
        } else {
            Sender.sendMessage(ChatColor.DARK_RED + "Tej komendy nie mozna uzywac w konsoli!");
        }
        return false;
    }
}
