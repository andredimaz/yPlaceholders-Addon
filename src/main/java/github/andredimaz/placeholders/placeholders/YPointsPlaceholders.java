package github.andredimaz.placeholders.placeholders;

import com.ystoreplugins.ypoints.api.yPointsAPI;
import github.andredimaz.placeholders.Main;
import org.bukkit.OfflinePlayer;

public class YPointsPlaceholders extends BasePlaceholders {
    private final yPointsAPI api = new yPointsAPI();

    public YPointsPlaceholders(Main plugin) {
        super(plugin);
    }

    @Override
    public String getIdentifier() {
        return "cash";
    }

    @Override
    protected String getBalanceIdentifier() {
        return "ypoints";
    }

    @Override
    protected double getBalance(OfflinePlayer player) {
        return api.getBalance(player.getName());
    }

    @Override
    protected int getTopSize() {
        return plugin.getYPointsTopSize();
    }

    @Override
    protected String getStyle() {
        return plugin.getYPointsStyle();
    }
}
