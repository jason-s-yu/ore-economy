package oreeconomy;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class OreconomyCommand implements CommandExecutor {

	Messages msg = Messages.getInstance();
	OreEconomy plugin = OreEconomy.getMain();

	@Override
	public boolean onCommand(CommandSender sender, Command arg1, String arg2, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (player.hasPermission("oreconomy.reload")) {
				plugin.getServer().getPluginManager().disablePlugin(plugin);
				plugin.getServer().getPluginManager().enablePlugin(plugin);
			}
		} else {
			msg.onlyplayer(sender);
		}
		return false;
	}
}
