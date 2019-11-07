package oreeconomy;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PayCommand implements CommandExecutor {

	OreEconomy plugin = OreEconomy.getMain();
	Messages msg = Messages.getInstance();
	Methods methods = Methods.getInstance();

	@Override
	public boolean onCommand(CommandSender sender, Command arg1, String arg2, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (player.hasPermission("oreconomy.pay")) {
				if (args.length == 0) {
					msg.msg(player, plugin.config.getString("message.pay.usage"));
				} else if (args.length == 1) {
					msg.msg(player, plugin.config.getString("message.pay.usage"));
				} else if (args.length == 2) {
					if (Bukkit.getPlayer(args[0]).isOnline()) {
						Player target = Bukkit.getPlayer(args[0]);
						if (methods.isInteger(args[1])) {
							int amount = Integer.parseInt(args[1]);
							if (methods.getMoney(player) >= amount) {
								methods.payMoney(player, target, amount);
								msg.msg(player, plugin.config.getString("message.pay").replace("%target%", target.getName()).replace("%am%", "" + amount));
								msg.msg(target, plugin.config.getString("message.payed").replace("%player%", player.getName()).replace("%am%", "" + amount));
							} else {
								msg.msg(player, plugin.config.getString("message.error.pay.money"));
							}
						} else {
							msg.msg(player, plugin.config.getString("message.error.pay.amount"));
						}
					} else {
						msg.msg(player, plugin.config.getString("message.error.pay.player").replace("%target%", args[0]));
					}
				}
			}
		} else {
			msg.onlyplayer(sender);
		}
		return false;
	}
}
