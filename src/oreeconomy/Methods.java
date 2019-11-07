package oreeconomy;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Methods {

	OreEconomy plugin = OreEconomy.getMain();
	private static Methods instance = new Methods();

	public static Methods getInstance() {
		return instance;
	}

	public void giveOre(Player player, String mat, int am) {
		ItemStack is = new ItemStack(Material.getMaterial(mat));
		is.setAmount(am);
		player.getInventory().addItem(is);
	}

	public double getMoney(Player player) {
		double money = plugin.pdata.getDouble(player.getUniqueId().toString() + ".money");
		return money;
	}

	public void setMoney(Player player, double money) {
		plugin.pdata.set(player.getUniqueId().toString() + ".money", money);
		plugin.savepd();
	}

	public void giveMoney(Player player, double money) {
		money = money + getMoney(player);
		plugin.pdata.set(player.getUniqueId().toString() + ".money", money);
		plugin.savepd();
	}

	public void takeMoney(Player player, double money) {
		double mon = getMoney(player) - money;
		plugin.pdata.set(player.getUniqueId().toString() + ".money", mon);
		plugin.savepd();
	}

	public void payMoney(Player player, Player target, double amount) {
		double tm = getMoney(target) - amount;
		double pm = getMoney(player) + amount;
		plugin.pdata.set(player.getUniqueId().toString() + ".money", pm);
		plugin.pdata.set(target.getUniqueId().toString() + ".money", tm);
		plugin.savepd();
	}

	public boolean isInteger(String s) {
		try {
			Integer.parseInt(s);
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}
}
