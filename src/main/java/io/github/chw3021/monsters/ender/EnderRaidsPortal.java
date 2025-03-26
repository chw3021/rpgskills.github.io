package io.github.chw3021.monsters.ender;

import org.bukkit.event.entity.EntityDeathEvent;

import io.github.chw3021.monsters.raids.Summoned;


public class EnderRaidsPortal extends Summoned {

	
	private static final EnderRaidsPortal instance = new EnderRaidsPortal ();
	public static EnderRaidsPortal getInstance()
	{
		return instance;
		
	}
	String META = "enderMimicPortalTrigger";
	
	
	public void EnderCombo(EntityDeathEvent d) 
	{	
		final Object com = Combo(d,META);
	
		if(com == null) {
			return;
		}
	}
	
	
}