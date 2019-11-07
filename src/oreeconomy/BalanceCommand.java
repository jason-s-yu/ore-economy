package oreeconomy;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BalanceCommand implements CommandExecutor {

	OreEconomy plugin = OreEconomy.getMain();
	Messages msg = Messages.getInstance();
	Methods methods = Methods.getInstance();

	@Override
	public boolean onCommand(CommandSender sender, Command arg1, String arg2, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (player.hasPermission("oreconomy.bal")) {
				if (args.length == 0) {
					double bal = methods.getMoney(player);
					msg.msg(player, plugin.config.getString("message.balance.self").replace("%bal%", "" + bal));
				} else if (args.length == 1) {
					if (player.hasPermission("oreconomy.bal.other")) {
						if (Bukkit.getPlayer(args[0]) != null) {
							Player target = Bukkit.getPlayer(args[0]);
							double bal = methods.getMoney(target);
							msg.msg(player, plugin.config.getString("message.balance.other").replace("%bal%", "" + bal).replace("%target%", target.getName()));
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
