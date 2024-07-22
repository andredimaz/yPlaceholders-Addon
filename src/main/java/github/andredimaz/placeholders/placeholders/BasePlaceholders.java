package github.andredimaz.placeholders.placeholders;

import github.andredimaz.placeholders.Main;
import me.clip.placeholderapi.PlaceholderAPI;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import net.luckperms.api.model.user.User;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import ru.tehkode.permissions.PermissionUser;

import java.util.*;

public abstract class BasePlaceholders extends PlaceholderExpansion {
    protected final Main plugin;

    public BasePlaceholders(Main plugin) {
        this.plugin = plugin;
    }

    protected abstract String getBalanceIdentifier();

    protected abstract double getBalance(OfflinePlayer player);

    protected abstract int getTopSize();

    protected abstract String getStyle();

    @Override
    public String getAuthor() {
        return plugin.getDescription().getAuthors().toString();
    }

    @Override
    public String getVersion() {
        return plugin.getDescription().getVersion();
    }

    @Override
    public boolean persist() {
        return true;
    }

    @Override
    public String onRequest(OfflinePlayer player, String identifier) {
        if (identifier.startsWith("top_")) {
            try {
                int position = Integer.parseInt(identifier.substring(4));
                return getTopRichInfo(player, position);
            } catch (NumberFormatException e) {
                return null;
            }
        }
        return null;
    }

    private String getTopRichInfo(OfflinePlayer player, int position) {
        Map<String, Double> balances = new HashMap<>();

        for (OfflinePlayer offlinePlayer : Bukkit.getOfflinePlayers()) {
            double balance = getBalance(offlinePlayer);
            balances.put(offlinePlayer.getName(), balance);
        }

        List<Map.Entry<String, Double>> sortedBalances = new ArrayList<>(balances.entrySet());
        sortedBalances.sort((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()));

        int topSize = getTopSize();
        if (position > 0 && position <= topSize) {
            if (position > sortedBalances.size()) {
                return plugin.getTopNullMessage().replace("{pos}", position + "º");
            }
            Map.Entry<String, Double> entry = sortedBalances.get(position - 1);
            String style = getStyle();
            style = style
                    .replace("{pos}", position + "º")
                    .replace("{jogador}", entry.getKey())
                    .replace("{valor}", formatValue(entry.getValue()))
                    .replace("{tag}", getTag(Bukkit.getOfflinePlayer(entry.getKey())));

            style = PlaceholderAPI.setPlaceholders(player, style);

            return style;
        }

        return null;
    }

    private String formatValue(double value) {
        List<String> suffixes = plugin.getFormatSuffixes();
        int base = 1000;
        int exponent = 0;

        while (value >= base && exponent < suffixes.size() - 1) {
            value /= base;
            exponent++;
        }

        String formattedValue = String.format("%.2f", value);
        if (formattedValue.endsWith(".00")) {
            formattedValue = formattedValue.substring(0, formattedValue.length() - 3);
        }

        return formattedValue + suffixes.get(exponent);
    }

    private String getTag(OfflinePlayer player) {
        if (plugin.getLuckPerms() != null) {
            User user = plugin.getLuckPerms().getUserManager().getUser(player.getUniqueId());
            if (user != null) {
                return user.getCachedData().getMetaData().getPrefix();
            }
        }

        if (plugin.getPermissionsEx() != null) {
            PermissionUser user = plugin.getPermissionsEx().getUser(player.getUniqueId());
            if (user != null) {
                return user.getPrefix();
            }
        }

        return "§r";
    }
}
