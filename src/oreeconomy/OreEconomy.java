package oreeconomy;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class OreEconomy extends JavaPlugin {

	private static OreEconomy main;

	File configurationConfig;
	public FileConfiguration config;
	File playerData;
	public FileConfiguration pdata;

	String prefix = "";

	public static OreEconomy getMain() {
		return main;
	}

	public Methods methods;

	public void onEnable() {
		main = this;
		configurationConfig = new File(getDataFolder(), "config.yml");
		config = YamlConfiguration.loadConfiguration(configurationConfig);
		playerData = new File(getDataFolder(), "playerData.yml");
		pdata = YamlConfiguration.loadConfiguration(playerData);
		loadConfig();
		Bukkit.getPluginManager().registerEvents(new PlayerListener(), this);
		getCommand("balance").setExecutor(new BalanceCommand());
		getCommand("balancetop").setExecutor(new BalancetopCommand());
		getCommand("eco").setExecutor(new EconomyCommand());
		getCommand("oreconomy").setExecutor(new OreconomyCommand());
		getCommand("pay").setExecutor(new PayCommand());
		methods = Methods.getInstance();
	}

	public void savec() {
		try {
			config.save(configurationConfig);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void savepd() {
		try {
			pdata.save(playerData);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void loadConfig() {

		config.addDefault("message.error.pay.money", "&4You don't have that much money in the bank!");
		config.addDefault("message.error.pay.amount", "&4Amount has to be an integer (args[1]).");
		config.addDefault("message.error.pay.player", "%target% is not online.");
		config.addDefault("message.pay.usage", "&8Usage: &7/pay <playerName>");
		config.addDefault("message.payed", "&b%player% payed you %am%.");
		config.addDefault("message.pay", "&bYou payed %target% a sum of %am%.");
		config.addDefault("message.bought", "You bought %amount% %item% for %money%.");
		config.addDefault("message.sold", "You sold %amount% %item% for %money%.");
		config.addDefault("message.eco.set", "&bSet %target%'s balance to %amount%.");
		config.addDefault("message.eco.take", "&bTook %amount% from %target%.");
		config.addDefault("message.eco.give", "&bGave %target% %amount%.");
		config.addDefault("message.balance.other", "&6%target% has $%bal% in the bank.");
		config.addDefault("message.balance.self", "&6You have $%bal% in the bank.");
		config.addDefault("sign.atm.detectorline", "[ATM]");
		config.addDefault("sign.exc.detectorline", "[EXCH]");
		config.addDefault("worth.GOLD_NUGGET", 1);
		config.addDefault("worth.GOLD_INGOT", 2);
		config.addDefault("worth.DIAMOND", 3);
		config.addDefault("worth.EMERALD", 4);
		config.addDefault("start-money", 10);
		config.addDefault("message.noperm", "&4Error: You don't have permission to use this function.");
		config.addDefault("message.onlyplayer", "&4Error: Only players may use this function.");
		config.addDefault("prefix", "");
		config.options().copyDefaults(true);
		pdata.options().copyDefaults(true);
		savepd();
		savec();
		prefix = config.getString("prefix");

	}

}
