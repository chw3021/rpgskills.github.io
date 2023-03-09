package io.github.chw3021.commons;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;
import org.jetbrains.annotations.NotNull;


public class SkillUseEvent extends PlayerEvent implements Cancellable{
	
	

    private static final HandlerList handlers = new HandlerList();
    private int num;
    private int tick;
    

	public SkillUseEvent(final Player p, final Double sec, final Integer itemnum) {
        super(p);
        this.num = itemnum;
        this.tick = (int) (sec*20);
	}
	
	public Integer getTick() {
		return tick;
	}

	public Integer getNum() {
		return num;
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
