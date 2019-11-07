package oreeconomy;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class EconomyCommand implements CommandExecutor {

	Messages msg = Messages.getInstance();
	OreEconomy plugin = OreEconomy.getMain();
	Methods methods = Methods.getInstance();

	@Override
	public boolean onCommand(CommandSender sender, Command arg1, String arg2, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (player.hasPermission("oreconomy.eco")) {
				if (args.length == 0) {
					// TODO USAGE
				} else if (args.length == 1) {
					// TODO USAGE
				} else if (args.length == 2) {
					// TODO USAGE
				} else if (args.length == 3) {
					if (args[0].equalsIgnoreCase("set")) {
						if (Bukkit.getPlayer(args[1]).isOnline()) {
							Player target = Bukkit.getPlayer(args[1]);
							methods.setMoney(target, Integer.parseInt(args[2]));
							msg.msg(player, plugin.config.getString("message.eco.set").replace("%target%", target.getName()).replace("%amount%", "" + Integer.parseInt(args[2])));
						} else {
							// TODO ERROR
						}
					}
					if (args[0].equalsIgnoreCase("give")) {
						if (Bukkit.getPlayer(args[1]).isOnline()) {
							Player target = Bukkit.getPlayer(args[1]);
							methods.giveMoney(target, Integer.parseInt(args[2]));
							msg.msg(player, plugin.config.getString("message.eco.give").replace("%target%", target.getName()).replace("%amount%", "" + Integer.parseInt(args[2])));
						} else {
							// TODO ERROR
						}
					}
					if (args[0].equalsIgnoreCase("take")) {
						if (Bukkit.getPlayer(args[1]).isOnline()) {
							Player target = Bukkit.getPlayer(args[1]);
							methods.takeMoney(target, Integer.parseInt(args[2]));
							msg.msg(player, plugin.config.getString("message.eco.give").replace("%target%", target.getName()).replace("%amount%", "" + Integer.parseInt(args[2])));
						} else {
							// TODO ERROR
						}
					}
				}
			}
		} else {
			msg.onlyplayer(sender);
		}
		return true;
	}
}
