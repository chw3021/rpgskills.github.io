package io.github.chw3021.commons;

import java.io.File;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.EnderDragonPart;
import org.bukkit.entity.Enderman;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;

import io.github.chw3021.party.PartyData;
import io.github.chw3021.rmain.RMain;

public class Holding {
	
	static public HashMap<LivingEntity, Long> holded = new HashMap<LivingEntity, Long>();
	static public HashMap<LivingEntity, Long> slowed = new HashMap<LivingEntity, Long>();
	static public HashMap<LivingEntity, Double> orspeed = new HashMap<LivingEntity, Double>();
	static public HashMap<Player, Long> superholding = new HashMap<Player, Long>();
	
	private static final Holding instance = new Holding ();
	public static Holding getInstance()
	{
		return instance;
	}
	
	public void attack(Player p, Entity e, Double amount) {

		if ((!(e == p))&& e instanceof LivingEntity && !(e.hasMetadata("fake"))) 
		{
			if (e instanceof Player) 
			{
			    String path = new File("").getAbsolutePath();
				Player p1 = (Player) e;
				try {
				if(PartyData.hasParty(p) && PartyData.hasParty(p1))	{
				if(PartyData.getParty(p).equalsIgnoreCase(PartyData.getParty(p1)))
					{
						return;
					}
				}}
				catch(NullPointerException ne) {
					return;
				}
			}
			LivingEntity le = (LivingEntity)e;
			le.damage(0, p);
			le.damage(amount, p);
		}
		else if (e instanceof EnderDragonPart) {
			EnderDragonPart edp = (EnderDragonPart) e;
			edp.damage(0, p);
			edp.damage(amount, p);
		}
	}
	
	static public void superholding(Player p, LivingEntity le, Long tick) {
		if(le.getType()!=EntityType.PLAYER && !le.isDead())
		{
			if(holded.containsKey(le) && superholding.containsKey(p)) {
				holded.computeIfPresent(le, (k, v) -> v+1);
				superholding.computeIfPresent(p, (k, v) -> v+1);
				le.setAI(false);
				if(le.getType()==EntityType.CREEPER) {
					Creeper c = (Creeper)le;
					c.setMaxFuseTicks(tick.intValue() + c.getMaxFuseTicks());
				}
				p.setInvulnerable(true);
			}
			else if(holded.containsKey(le) && !superholding.containsKey(p)) {
				holded.computeIfPresent(le, (k, v) -> v+1);
				superholding.put(p, (long) 0);
				le.setAI(false);
				if(le.getType()==EntityType.CREEPER) {
					Creeper c = (Creeper)le;
					c.setMaxFuseTicks(tick.intValue() + c.getMaxFuseTicks());
				}
				p.setInvulnerable(true);				
			}
			else if(!holded.containsKey(le) && superholding.containsKey(p)) {
				holded.put(le, (long) 0);
				superholding.computeIfPresent(p, (k, v) -> v+1);
				le.setAI(false);
				if(le.getType()==EntityType.CREEPER) {
					Creeper c = (Creeper)le;
					c.setMaxFuseTicks(tick.intValue() + c.getMaxFuseTicks());
				}
				p.setInvulnerable(true);				
			}
			else if(!holded.containsKey(le) && !superholding.containsKey(p)) {
				holded.put(le, (long) 0);
				superholding.put(p, (long) 0);
				le.setAI(false);
				if(le.getType()==EntityType.CREEPER) {
					Creeper c = (Creeper)le;
					c.setMaxFuseTicks(tick.intValue() + c.getMaxFuseTicks());
				}
				p.setInvulnerable(true);
			}
			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
                @Override
                public void run() 
                {
                	holded.computeIfPresent(le, (k, v) -> v-1);
                	superholding.computeIfPresent(p, (k, v) -> v-1);
					if(superholding.get(p)<0) {
						p.setInvulnerable(false);
						superholding.remove(p);
					}
                	if(holded.get(le)<0) {
					le.setAI(true);
					holded.remove(le);
                	}
                }
    	   }, tick); 
		}
		else if(le.getType()==EntityType.PLAYER&& !le.isDead()){
			if (le instanceof Player) 
			{
			    String path = new File("").getAbsolutePath();
				PartyData party = new PartyData(PartyData.loadData(path +"/plugins/RPGskills/PartyData.data"));
				Player p1 = (Player) le;
				try {
				if(PartyData.hasParty(p) && PartyData.hasParty(p1))	{
				if(PartyData.getParty(p).equalsIgnoreCase(PartyData.getParty(p1)))
					{
						return;
					}
				}}
				catch(NullPointerException ne) {
					return;
				}
			}

			if(holded.containsKey(le) && superholding.containsKey(p)) {
				holded.computeIfPresent(le, (k, v) -> v+1);
				superholding.computeIfPresent(p, (k, v) -> v+1);
                le.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0);
				p.setInvulnerable(true);
			}
			else if(holded.containsKey(le) && !superholding.containsKey(p)) {
				holded.computeIfPresent(le, (k, v) -> v+1);
				superholding.put(p, (long) 0);
                le.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0);
				p.setInvulnerable(true);				
			}
			else if(!holded.containsKey(le) && superholding.containsKey(p)) {
				holded.put(le, (long) 0);
				superholding.computeIfPresent(p, (k, v) -> v+1);
                le.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0);
				p.setInvulnerable(true);				
			}
			else if(!holded.containsKey(le) && !superholding.containsKey(p)) {
				holded.put(le, (long) 0);
				superholding.put(p, (long) 0);
                le.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0);
				p.setInvulnerable(true);
			}
			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
                @Override
                public void run() 
                {
                	holded.computeIfPresent(le, (k, v) -> v-1);
                	superholding.computeIfPresent(p, (k, v) -> v-1);
					if(superholding.get(p)<0) {
						p.setInvulnerable(false);
						superholding.remove(p);
					}
                	if(holded.get(le)<0) {
     					le.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.1);
					holded.remove(le);
                	}
                }
    	   }, tick); 
		
		}
	
	}
	
	static public void holding(@Nullable Player p, LivingEntity le, Long tick) {
			if(le.getType()!=EntityType.PLAYER&& !le.isDead())
			{
				if(holded.containsKey(le)) {
					holded.computeIfPresent(le, (k, v) -> v+1);
					le.setAI(false);
					if(le.getType()==EntityType.CREEPER) {
						Creeper c = (Creeper)le;
						c.setMaxFuseTicks(tick.intValue() + c.getMaxFuseTicks());
					}
				}
				else {
					holded.put(le, (long) 0);
					le.setAI(false);
					if(le.getType()==EntityType.CREEPER) {
						Creeper c = (Creeper)le;
						c.setMaxFuseTicks(tick.intValue() + c.getMaxFuseTicks());
					}
				}
				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	                @Override
	                public void run() 
	                {
	                	holded.computeIfPresent(le, (k, v) -> v-1);
	                	if(holded.get(le)<0) {
						le.setAI(true);
						holded.remove(le);
	                	}
	                }
        	   }, tick); 
			}
			else if(le.getType()==EntityType.PLAYER&& !le.isDead()){
				if (le instanceof Player) 
				{
				    String path = new File("").getAbsolutePath();
					PartyData party = new PartyData(PartyData.loadData(path +"/plugins/RPGskills/PartyData.data"));
					Player p1 = (Player) le;
					try {
					if(PartyData.hasParty(p) && PartyData.hasParty(p1))	{
					if(PartyData.getParty(p).equalsIgnoreCase(PartyData.getParty(p1)))
						{
							return;
						}
					}}
					catch(NullPointerException ne) {
						return;
					}
				}
			
				if(holded.containsKey(le)) {
					holded.computeIfPresent(le, (k, v) -> v+1);
	                le.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0);
				}
				else {
					holded.put(le, (long) 0);
	                le.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0);
				}
				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	                @Override
	                public void run() 
	                {
	                	holded.computeIfPresent(le, (k, v) -> v-1);
	                	if(holded.get(le)<0) {
	     					le.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.1);
						holded.remove(le);
	                	}
	                }
        	   }, tick); 
			}
		
	}
	

	
	static public void slow(@Nullable Player p, LivingEntity le, Long tick, Double amount) {
			if(!le.isDead())
			{
				if (le instanceof Player) 
				{
					Player p1 = (Player) le;
					try {
					if(PartyData.hasParty(p) && PartyData.hasParty(p1))	{
					if(PartyData.getParty(p).equalsIgnoreCase(PartyData.getParty(p1)))
						{
							return;
						}
					}}
					catch(NullPointerException ne) {
						return;
					}
				}
				if(slowed.containsKey(le)) {
					slowed.computeIfPresent(le, (k, v) -> v+1);
					le.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(orspeed.get(le)*(100-amount)/100);
					le.setVelocity(le.getVelocity().zero());
				}
				else {
					orspeed.put(le, le.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).getBaseValue());
					slowed.put(le, (long) 0);
					le.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(orspeed.get(le)*(100-amount)/100);
					le.setVelocity(le.getVelocity().zero());
				}
				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	                @Override
	                public void run() 
	                {
	                	slowed.computeIfPresent(le, (k, v) -> v-1);
	                	if(slowed.get(le)<0) {
	    					le.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(orspeed.get(le));
	    					slowed.remove(le);
	    					orspeed.remove(le);
	                	}
	                }
        	   }, tick); 
			}
		
	}
	

	static public void invur(@Nullable Player p, Long tick) {
		if(superholding.containsKey(p)) {
			superholding.computeIfPresent(p, (k, v) -> v+1);
			p.setInvulnerable(true);
		}
		else if(!superholding.containsKey(p)) {
			superholding.put(p, (long) 0);
			p.setInvulnerable(true);				
		}
		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
            @Override
            public void run() 
            {
            	superholding.computeIfPresent(p, (k, v) -> v-1);
				if(superholding.get(p)<0) {
					p.setInvulnerable(false);
					superholding.remove(p);
				}
            }
	   }, tick); 
		
	}
}
