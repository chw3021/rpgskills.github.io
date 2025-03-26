package io.github.chw3021.monsters.wither;

import org.bukkit.event.entity.EntityDeathEvent;

import io.github.chw3021.monsters.raids.Summoned;


public class WitherRaidsPortal extends Summoned {

	
	private static final WitherRaidsPortal instance = new WitherRaidsPortal ();
	public static WitherRaidsPortal getInstance()
	{
		return instance;
		
	}
	String META = "wither";
	
	
	public void EnderCombo(EntityDeathEvent d) 
	{	
		final Object com = Combo(d,META);
	
		if(com == null) {
			return;
		}
	}
	
	
}