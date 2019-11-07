package oreeconomy;

import java.util.ArrayList;
import java.util.Collections;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BalancetopCommand implements CommandExecutor {

	OreEconomy plugin = OreEconomy.getMain();
	Messages msg = Messages.getInstance();
	Methods methods = Methods.getInstance();

	@Override
	public boolean onCommand(CommandSender sender, Command arg1, String arg2, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (player.hasPermission("oreconomy.baltop")) {
				ArrayList<String> baltop = new ArrayList<String>();
				for (String key : plugin.pdata.getKeys(false)) {
					baltop.add(plugin.pdata.getDouble(key + ".money") + ":" + plugin.pdata.getString(key + ".name"));
				}
				Collections.sort(baltop, new AlphanumComparator());
				if (baltop.size() >= 10) {
					msg.msg(player, baltop.get(9));
					msg.msg(player, baltop.get(8));
					msg.msg(player, baltop.get(7));
					msg.msg(player, baltop.get(6));
					msg.msg(player, baltop.get(5));
					msg.msg(player, baltop.get(4));
					msg.msg(player, baltop.get(3));
					msg.msg(player, baltop.get(2));
					msg.msg(player, baltop.get(1));
					msg.msg(player, baltop.get(0));
				}
				if (baltop.size() >= 9) {
					msg.msg(player, baltop.get(8));
					msg.msg(player, baltop.get(7));
					msg.msg(player, baltop.get(6));
					msg.msg(player, baltop.get(5));
					msg.msg(player, baltop.get(4));
					msg.msg(player, baltop.get(3));
					msg.msg(player, baltop.get(2));
					msg.msg(player, baltop.get(1));
					msg.msg(player, baltop.get(0));
				}
				if (baltop.size() >= 8) {
					msg.msg(player, baltop.get(7));
					msg.msg(player, baltop.get(6));
					msg.msg(player, baltop.get(5));
					msg.msg(player, baltop.get(4));
					msg.msg(player, baltop.get(3));
					msg.msg(player, baltop.get(2));
					msg.msg(player, baltop.get(1));
					msg.msg(player, baltop.get(0));
				}
				if (baltop.size() >= 7) {
					msg.msg(player, baltop.get(6));
					msg.msg(player, baltop.get(5));
					msg.msg(player, baltop.get(4));
					msg.msg(player, baltop.get(3));
					msg.msg(player, baltop.get(2));
					msg.msg(player, baltop.get(1));
					msg.msg(player, baltop.get(0));
				}
				if (baltop.size() >= 6) {
					msg.msg(player, baltop.get(5));
					msg.msg(player, baltop.get(4));
					msg.msg(player, baltop.get(3));
					msg.msg(player, baltop.get(2));
					msg.msg(player, baltop.get(1));
					msg.msg(player, baltop.get(0));
				}
				if (baltop.size() >= 5) {
					msg.msg(player, baltop.get(4));
					msg.msg(player, baltop.get(3));
					msg.msg(player, baltop.get(2));
					msg.msg(player, baltop.get(1));
					msg.msg(player, baltop.get(0));
				}
				if (baltop.size() >= 4) {
					msg.msg(player, baltop.get(3));
					msg.msg(player, baltop.get(2));
					msg.msg(player, baltop.get(1));
					msg.msg(player, baltop.get(0));
				}
				if (baltop.size() >= 3) {
					msg.msg(player, baltop.get(2));
					msg.msg(player, baltop.get(1));
					msg.msg(player, baltop.get(0));
				}
				if (baltop.size() >= 2) {
					msg.msg(player, baltop.get(1));
					msg.msg(player, baltop.get(0));
				}
				if (baltop.size() >= 1) {
					msg.msg(player, baltop.get(0));
				}

			}
		} else {
			msg.onlyplayer(sender);
		}
		return true;
	}
}
