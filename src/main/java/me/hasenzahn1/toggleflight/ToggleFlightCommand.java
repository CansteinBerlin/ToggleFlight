package me.hasenzahn1.toggleflight;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ToggleFlightCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!sender.hasPermission("toggleflight.command.toggleflight")){
            sender.sendMessage(ToggleFlight.PREFIX + ToggleFlight.getLang("noPermission"));
            return true;
        }

        if(args.length != 1){
            sender.sendMessage(ToggleFlight.PREFIX + ToggleFlight.getLang("invalidCommand", "command", command.getName()));
            return true;
        }

        Player p = Bukkit.getPlayer(args[0]);
        if(p == null){
            sender.sendMessage(ToggleFlight.PREFIX + ToggleFlight.getLang("noPlayer", "player", args[0]));
            return true;
        }


        if(ToggleFlight.INSTANCE.getEnabledPlayers().contains(p.getUniqueId())){
            ToggleFlight.INSTANCE.getEnabledPlayers().remove(p.getUniqueId());
        }else{
            ToggleFlight.INSTANCE.getEnabledPlayers().add(p.getUniqueId());
        }

        sender.sendMessage(ToggleFlight.PREFIX + ToggleFlight.getLang("success", "player", p.getDisplayName(), "state",
                ToggleFlight.getLang(ToggleFlight.INSTANCE.getEnabledPlayers().contains(p.getUniqueId()) ? "enabled" : "disabled")));

        return true;
    }
}
