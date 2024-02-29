package me.hasenzahn1.toggleflight;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ToggleFlightCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ToggleFlight.PREFIX + ToggleFlight.getLang("hasToBePlayer"));
            return true;
        }
        Player applicator = ((Player) sender);
        if (args.length == 0) {
            if (!sender.hasPermission("toggleflight.command.toggleflight.self")) {
                sender.sendMessage(ToggleFlight.PREFIX + ToggleFlight.getLang("noPermission"));
                return true;
            }
        } else {
            if (!sender.hasPermission("toggleflight.command.toggleflight.other")) {
                sender.sendMessage(ToggleFlight.PREFIX + ToggleFlight.getLang("noPermission"));
                return true;
            }
            applicator = Bukkit.getPlayer(args[0]);
            if (applicator == null) {
                sender.sendMessage(ToggleFlight.PREFIX + ToggleFlight.getLang("noPlayer", "player", args[0]));
                return true;
            }
        }

        if (ToggleFlight.INSTANCE.getEnabledPlayers().contains(applicator.getUniqueId())) {
            ToggleFlight.INSTANCE.getEnabledPlayers().remove(applicator.getUniqueId());
        } else {
            ToggleFlight.INSTANCE.getEnabledPlayers().add(applicator.getUniqueId());
        }

        sender.sendMessage(ToggleFlight.PREFIX + ToggleFlight.getLang("success", "player", applicator.getDisplayName(), "state",
                ToggleFlight.getLang(ToggleFlight.INSTANCE.getEnabledPlayers().contains(applicator.getUniqueId()) ? "enabled" : "disabled")));

        return true;
    }
}
