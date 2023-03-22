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
    private String kname;
    private String ename;
    

	public SkillUseEvent(final Player p, final Double sec, final Integer itemnum, final String kname, final String ename) {
        super(p);
        this.num = itemnum;
        this.tick = (int) (sec*20);
        this.kname = kname;
        this.ename = ename;
	}
	
	public Integer getTick() {
		return tick;
	}

	public Integer getNum() {
		return num;
	}

	public String getKname() {
		return kname;
	}
	public String getEname() {
		return ename;
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
