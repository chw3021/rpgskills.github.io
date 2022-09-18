package io.github.chw3021.commons.party;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;
import org.jetbrains.annotations.NotNull;


public class PartyCreateEvent extends PlayerEvent implements Cancellable{
	
	

    private static final HandlerList handlers = new HandlerList();
    private String partyname;
    

	public PartyCreateEvent(final Player p, final String pn) {
        super(p);
        this.partyname = pn;
	}

	public PartyCreateEvent(final Player p) {
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
