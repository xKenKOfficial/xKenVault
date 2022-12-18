package com.github.xKenKOfficial.Vault.Commands;

import com.github.xKenKOfficial.Vault.Apis.API;
import com.github.xKenKOfficial.Vault.Basic.Main;
import com.github.xKenKOfficial.Vault.Users.User;
import com.github.xKenKOfficial.Vault.Utils.ChatUtil;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PayCommand implements CommandExecutor
{
    private static final String NO_PERMISSION = Main.getPlugin().getConfig().getString("no_permission");
    private static final String OFFLINE_PLAYER = Main.getPlugin().getConfig().getString("offline_player");
    private static final String WRONG_ARGS = Main.getPlugin().getConfig().getString("wrong_args");
    private static final String COMMAND_USAGE = Main.getPlugin().getConfig().getString("pay_command_usage");
    private static final String PLAYER_PAY_MONEY_TO_PLAYER = Main.getPlugin().getConfig().getString("pay_player_send_money_to_player");
    private static final String PLAYER_RECIPIENT_MONEY = Main.getPlugin().getConfig().getString("pay_player_recipient_of_money");
    private static final String PLAYER_NOT_HAS_MONEY = Main.getPlugin().getConfig().getString("pay_player_doesnt_have_that_much_money");
    private static final String PLAYER_IS_TARGET_PLAYER = Main.getPlugin().getConfig().getString("pay_player_is_target_player");
    private static final String MONEY_ERROR = Main.getPlugin().getConfig().getString("pay_money_error");

    @Override
    public boolean onCommand(final CommandSender Sender, final Command c, final String l, final String[] args) {
        if(!Sender.hasPermission("xkenvault.player.pay")) {
            Sender.sendMessage(ChatUtil.fixColor(NO_PERMISSION));
            return false;
        }
        if(Sender instanceof Player) {
            if(args.length < 1) {
                Sender.sendMessage(ChatUtil.fixColor(COMMAND_USAGE));
                return false;
            } else if(args.length == 2) {
                final Player target = Bukkit.getPlayer(args[0]);
                final Integer money = Integer.parseInt(args[1]);
                if(target == null) {
                    Sender.sendMessage(ChatUtil.fixColor(OFFLINE_PLAYER));
                    return false;
                } else if((Player)Sender == target) {
                    Sender.sendMessage(ChatUtil.fixColor(PLAYER_IS_TARGET_PLAYER));
                    return false;
                }
                if(money == null) {
                    Sender.sendMessage(ChatUtil.fixColor(MONEY_ERROR));
                    return false;
                }
                if(money <= User.getUserMoney((Player)Sender)) {
                    API.addMoney(target, money);
                    API.removeMoney((Player)Sender, money);
                    Sender.sendMessage(ChatUtil.fixColor(PLAYER_PAY_MONEY_TO_PLAYER.replace("{GRACZ}", target.getName()).replace("{MONEY}", String.valueOf(money))));
                    target.sendMessage(ChatUtil.fixColor(PLAYER_RECIPIENT_MONEY.replace("{GRACZ}", Sender.getName()).replace("{MONEY}", String.valueOf(money))));
                    return false;
                } else if(money >= 0) {
                    Sender.sendMessage(ChatUtil.fixColor(PLAYER_NOT_HAS_MONEY));
                    return false;
                } else {
                    Sender.sendMessage(ChatUtil.fixColor(PLAYER_NOT_HAS_MONEY));
                }
            } else {
                Sender.sendMessage(ChatUtil.fixColor(WRONG_ARGS));
            }
        } else {
            Sender.sendMessage(ChatColor.DARK_RED + "Tej komendy nie mozna uzywac w konsoli!");
        }
        return false;
    }
}
