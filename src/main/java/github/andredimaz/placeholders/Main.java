package github.andredimaz.placeholders;

import github.andredimaz.placeholders.comandos.ReloadCommand;
import github.andredimaz.placeholders.placeholders.CoinsPlaceholders;
import github.andredimaz.placeholders.placeholders.YPointsPlaceholders;
import net.milkbowl.vault.economy.Economy;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import ru.tehkode.permissions.PermissionManager;
import ru.tehkode.permissions.bukkit.PermissionsEx;

import java.util.List;
import java.util.logging.Logger;

public class Main extends JavaPlugin {

    private static final Logger log = Logger.getLogger("Minecraft");
    private static Economy econ = null;
    private String vaultStyle;
    private int vaultTopSize;
    private String ypointsStyle;
    private int ypointsTopSize;
    private List<String> formatSuffixes;
    private String topNullMessage;
    private LuckPerms luckPerms = null;
    private PermissionManager permissionsEx = null;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        loadConfig();

        if (!setupEconomy()) {
            log.severe(String.format("[%s] - Vault não foi encontrado", getDescription().getName()));
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        if (Bukkit.getPluginManager().getPlugin("LuckPerms") != null) {
            luckPerms = LuckPermsProvider.get();
        }

        if (Bukkit.getPluginManager().getPlugin("PermissionsEx") != null) {
            permissionsEx = PermissionsEx.getPermissionManager();
        }

        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            new CoinsPlaceholders(this).register();
            new YPointsPlaceholders(this).register();
        }

        this.getCommand("yreload").setExecutor(new ReloadCommand(this));
    }

    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }

        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }

    public Economy getEconomy() {
        return econ;
    }

    public String getVaultStyle() {
        return vaultStyle;
    }

    public int getVaultTopSize() {
        return vaultTopSize;
    }

    public String getYPointsStyle() {
        return ypointsStyle;
    }

    public int getYPointsTopSize() {
        return ypointsTopSize;
    }

    public List<String> getFormatSuffixes() {
        return formatSuffixes;
    }

    public String getTopNullMessage() {
        return topNullMessage;
    }

    public LuckPerms getLuckPerms() {
        return luckPerms;
    }

    public PermissionManager getPermissionsEx() {
        return permissionsEx;
    }

    public void loadConfig() {
        vaultStyle = getConfig().getString("vault.estilo", "&7{pos} &8▪ {tag}{jogador} &8▶ &2$&f{valor}");
        vaultTopSize = getConfig().getInt("vault.tamanho", 10);
        ypointsStyle = getConfig().getString("ypoints.estilo", "&7{pos} &8▪ {tag}{jogador} &8▶ &6✪{valor}");
        ypointsTopSize = getConfig().getInt("ypoints.tamanho", 10);
        formatSuffixes = getConfig().getStringList("formatador");
        topNullMessage = getConfig().getString("top-nulo", "§7Ninguém");
    }
}
