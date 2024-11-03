package io.github.chw3021.monsters;

import org.bukkit.entity.Entity;
import org.bukkit.event.HandlerList;
import org.bukkit.event.entity.EntityEvent;
import org.jetbrains.annotations.NotNull;

public class MonsterEvent extends EntityEvent {

    private static final HandlerList handlers = new HandlerList();
    
	public MonsterEvent(Entity what) {
		super(what);
		// TODO Auto-generated constructor stub
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

}
