package io.github.chw3021.commons;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;
import org.jetbrains.annotations.NotNull;

import io.github.chw3021.commons.Pak.SkillUse;
import io.github.chw3021.rmain.RMain;


public class SkillUseEvent extends PlayerEvent implements Cancellable{
	
	

    private static final HandlerList handlers = new HandlerList();
    private int num;
    private int tick;
    private String kname;
    private String ename;
    private HashMap<String, Long> hm;
    private boolean cancelled;


	public SkillUseEvent(final Player p, final Double sec, final Integer itemnum, final String kname, final String ename, final HashMap<String, Long> hm) {
        super(p);
        this.num = itemnum;
        this.tick = (int) (sec*20);
        this.kname = kname;
        this.ename = ename;
        this.hm = hm;
	}

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
        return this.cancelled;
	}

	@Override
	public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
	}
	
	public void resetCooldown() {
		Bukkit.getScheduler().runTaskLater(RMain.getInstance(), () -> {
		    hm.remove(player.getName());
		}, 1);
	}
}
