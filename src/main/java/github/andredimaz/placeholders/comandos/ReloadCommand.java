package github.andredimaz.placeholders.comandos;

import github.andredimaz.placeholders.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public class ReloadCommand implements CommandExecutor {

    private final Main plugin;

    public ReloadCommand(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender.hasPermission("plugin.reload")) {
            plugin.reloadConfig();
            plugin.loadConfig();
            sender.sendMessage("§aConfiguração recarregada com sucesso!");
        } else {
            sender.sendMessage("§cVocê não tem permissão para executar este comando.");
        }
        return true;
    }
}
