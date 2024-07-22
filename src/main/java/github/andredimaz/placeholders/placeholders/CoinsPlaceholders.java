package github.andredimaz.placeholders.placeholders;

import github.andredimaz.placeholders.Main;
import org.bukkit.OfflinePlayer;

public class CoinsPlaceholders extends BasePlaceholders {
    public CoinsPlaceholders(Main plugin) {
        super(plugin);
    }

    @Override
    public String getIdentifier() {
        return "coins";
    }

    @Override
    protected String getBalanceIdentifier() {
        return "vault";
    }

    @Override
    protected double getBalance(OfflinePlayer player) {
        return plugin.getEconomy().getBalance(player);
    }

    @Override
    protected int getTopSize() {
        return plugin.getVaultTopSize();
    }

    @Override
    protected String getStyle() {
        return plugin.getVaultStyle();
    }
}
