package me.hasenzahn1.toggleflight;

import com.destroystokyo.paper.event.player.PlayerJumpEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class JumpListener implements Listener {

    @EventHandler
    public void onPlayerJump(PlayerJumpEvent event){
        if(!ToggleFlight.INSTANCE.getEnabledPlayers().contains(event.getPlayer().getUniqueId())) return;
        event.getPlayer().setFlying(true);
    }

}
