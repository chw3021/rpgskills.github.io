package io.github.chw3021.party;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;
import org.jetbrains.annotations.NotNull;


public class PartyJoinEvent extends PlayerEvent implements Cancellable{
	
	

    private static final HandlerList handlers = new HandlerList();
    private String partyname;
    

	public PartyJoinEvent(final Player p, final String pn) {
        super(p);
        this.partyname = pn;
	}

	public PartyJoinEvent(final Player p) {
        super(p);
	}
	
	public String getpartyname() {
		return partyname;
	}
	
    @NotNull
    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    @NotNull
    public static HandlerList getHandlerList() {
        return handlers;
    }

	@Override
	public boolean isCancelled() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setCancelled(boolean cancel) {
		// TODO Auto-generated method stub
		
	}
}
