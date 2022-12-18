package com.github.xKenKOfficial.Vault.Commands;

import com.github.xKenKOfficial.Vault.Apis.API;
import com.github.xKenKOfficial.Vault.Basic.Main;
import com.github.xKenKOfficial.Vault.Utils.ChatUtil;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetMoneyCommand implements CommandExecutor
{
    private static final String NO_PERMISSION = Main.getPlugin().getConfig().getString("no_permission");
    private static final String OFFLINE_PLAYER = Main.getPlugin().getConfig().getString("offline_player");
    private static final String WRONG_ARGS = Main.getPlugin().getConfig().getString("wrong_args");
    private static final String COMMAND_USAGE = Main.getPlugin().getConfig().getString("setmoney_command_execute");
    private static final String ADMIN_MESSAGE = Main.getPlugin().getConfig().getString("setmoney_admin_message");
    private static final String PLAYER_MESSAGE = Main.getPlugin().getConfig().getString("setmoney_player_message");

    @Override
    public boolean onCommand(final CommandSender Sender, final Command c, final String l, final String[] args) {
        if(!Sender.hasPermission("xkenvault.admin.setmoney")) {
            Sender.sendMessage(ChatUtil.fixColor(NO_PERMISSION));
            return false;
        }
        if(Sender instanceof Player) {
            if(args.length < 1) {
                Sender.sendMessage(ChatUtil.fixColor(COMMAND_USAGE));
                return false;
            } else if(args.length == 2) {
                final Player target = Bukkit.getPlayer(args[0]);
                if(target == null) {
                    Sender.sendMessage(ChatUtil.fixColor(OFFLINE_PLAYER));
                    return false;
                }
                final int money = Integer.parseInt(args[1]);
                API.addMoney(target, money);
                Sender.sendMessage(ChatUtil.fixColor(ADMIN_MESSAGE.replace("{GRACZ}", target.getName()).replace("{MONEY}", String.valueOf(money))));
                target.sendMessage(ChatUtil.fixColor(PLAYER_MESSAGE.replace("{ADMIN}", Sender.getName()).replace("{MONEY}", String.valueOf(money))));
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
