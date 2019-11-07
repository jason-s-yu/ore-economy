package oreeconomy;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerListener implements Listener {

	private static PlayerListener instance = new PlayerListener();

	OreEconomy plugin = OreEconomy.getMain();
	Messages msg = Messages.getInstance();
	Methods methods = Methods.getInstance();

	public static PlayerListener getInstance() {
		return instance;
	}

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		if (plugin.pdata.get(player.getUniqueId().toString()) == null) {
			plugin.pdata.set(player.getUniqueId().toString() + ".money", plugin.config.getDouble("start-money"));

			plugin.savepd();
		}
		plugin.pdata.set(player.getUniqueId().toString() + ".name", player.getName());
		plugin.savepd();
	}

	@EventHandler
	public void onSignChange(SignChangeEvent event) {
		Player player = event.getPlayer();
		if (player.hasPermission("oreconomy.atm")) {
			if (event.getLine(0).equalsIgnoreCase(plugin.config.getString("sign.atm.detectorline"))) {
				event.setLine(0, ChatColor.BLUE + "[ATM]");
				event.setLine(1, event.getLine(1));
				String item = event.getLine(1);
				int multiplier = Integer.parseInt(event.getLine(2));
				event.setLine(3, "$" + plugin.config.getInt("worth." + item) * multiplier);
			}
			if (event.getLine(0).equalsIgnoreCase(plugin.config.getString("sign.exc.detectorline"))) {
				event.setLine(0, ChatColor.BLUE + "[EXCH]");
				event.setLine(1, event.getLine(1));
				String item = event.getLine(1);
				int multiplier = Integer.parseInt(event.getLine(2));
				event.setLine(3, "$" + plugin.config.getInt("worth." + item) * multiplier);
			}
		}
	}
	@EventHandler
	public void onInteract(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
			if (event.getClickedBlock().getType() == Material.SIGN || event.getClickedBlock().getType() == Material.SIGN_POST || event.getClickedBlock().getType() == Material.WALL_SIGN) {
				Sign sign = (Sign) event.getClickedBlock().getState();
				if (ChatColor.stripColor(sign.getLine(0)).equalsIgnoreCase("[EXCH]")) {
					event.setCancelled(true);
					String mat = sign.getLine(1);
					int am = Integer.parseInt(sign.getLine(2));
					int cost = Integer.parseInt(sign.getLine(3).replace("$", ""));
					if (player.getItemInHand().getType() == Material.getMaterial(mat)) {
						ItemStack is = player.getItemInHand();
						int stack = is.getAmount();
						stack = stack - am;
						if (stack == 0) {
							player.getInventory().setItemInHand(new ItemStack(Material.AIR));
							methods.giveMoney(player, cost);
							msg.msg(player, plugin.config.getString("message.sold").replace("%amount%", "" + am).replace("%item%", mat).replace("%money%", "" + cost));
							player.updateInventory();
						} else if (stack > 0) {
							is.setAmount(stack);
							player.getInventory().setItemInHand(is);
							methods.giveMoney(player, cost);
							msg.msg(player, plugin.config.getString("message.sold").replace("%amount%", "" + am).replace("%item%", mat).replace("%money%", "" + cost));
							player.updateInventory();
						} else if (stack < 0) {
							// TODO ERROR MESSAGE
						}
					}
				} else if (ChatColor.stripColor(sign.getLine(0)).equalsIgnoreCase("[ATM]")) {
					event.setCancelled(true);
					String mat = sign.getLine(1);
					int am = Integer.parseInt(sign.getLine(2));
					int cost = Integer.parseInt(sign.getLine(3).replace("$", ""));
					if (methods.getMoney(player) >= cost) {
						methods.takeMoney(player, cost);
						methods.giveOre(player, mat, am);
						msg.msg(player, plugin.config.getString("message.bought").replace("%amount%", "" + am).replace("%item%", mat).replace("%money%", "" + cost));
						player.updateInventory();
					}

				}
			}
		}
	}
}