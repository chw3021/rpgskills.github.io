package io.github.chw3021.commons;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.metadata.FixedMetadataValue;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import io.github.chw3021.commons.party.Party;
import io.github.chw3021.rmain.RMain;

public class Holding implements Listener{
	
	static public HashMap<UUID, Long> holded = new HashMap<UUID, Long>();
	static public HashMap<UUID, Long> slowed = new HashMap<UUID, Long>();
	static public HashMap<UUID, Double> orspeed = new HashMap<UUID, Double>();
	static public HashMap<UUID, Long> superholding = new HashMap<UUID, Long>();
	static public HashMap<UUID, Integer> lasttask = new HashMap<UUID, Integer>();
	static public HashMap<UUID, Long> untouchable = new HashMap<UUID, Long>();
	

	static public HashMap<UUID, Integer> flyt = new HashMap<UUID,Integer>();
	
	private static final Holding instance = new Holding ();
	public static Holding getInstance()
	{
		return instance;
	}

	static public Entity ale(Entity le) {
		Entity ale = (Entity) Bukkit.getEntity(le.getUniqueId());
		if(ale == null) {
			return le;
		}
		return ale;
	}

	static public LivingEntity ale(LivingEntity le) {
		LivingEntity ale = (LivingEntity) Bukkit.getEntity(le.getUniqueId());
		if(ale == null) {
			return le;
		}
		return ale;
	}
	
	static public void superholding(@NotNull Player p, final LivingEntity le, Long tick) {
		if(le.hasMetadata("fake") || untouchable.containsKey(le.getUniqueId())) {
			return;
		}
		if(ale(le).getType()!=EntityType.PLAYER && ale(le).getType()!=EntityType.VILLAGER && !ale(le).isDead())
		{
			if(holded.containsKey(le.getUniqueId()) && superholding.containsKey(p.getUniqueId())) {
				holded.computeIfPresent(le.getUniqueId(), (k, v) -> v+1);
				superholding.computeIfPresent(p.getUniqueId(), (k, v) -> v+1);
				ale(le).setAI(false);
				if(ale(le).getType()==EntityType.CREEPER) {
					Creeper c = (Creeper)ale(le);
					c.setFuseTicks(0);
				}
				ale(le).setVelocity(ale(le).getVelocity().multiply(0));
				p.setInvulnerable(true);
			}
			else if(holded.containsKey(le.getUniqueId()) && !superholding.containsKey(p.getUniqueId())) {
				holded.computeIfPresent(le.getUniqueId(), (k, v) -> v+1);
				superholding.put(p.getUniqueId(), (long) 0);
				ale(le).setAI(false);
				if(ale(le).getType()==EntityType.CREEPER) {
					Creeper c = (Creeper)ale(le);
					c.setFuseTicks(0);
				}
				ale(le).setVelocity(ale(le).getVelocity().multiply(0));
				p.setInvulnerable(true);				
			}
			else if(!holded.containsKey(le.getUniqueId()) && superholding.containsKey(p.getUniqueId())) {
				holded.put(le.getUniqueId(), (long) 0);
				superholding.computeIfPresent(p.getUniqueId(), (k, v) -> v+1);
				ale(le).setAI(false);
				if(ale(le).getType()==EntityType.CREEPER) {
					Creeper c = (Creeper)ale(le);
					c.setFuseTicks(0);
				}
				ale(le).setVelocity(ale(le).getVelocity().multiply(0));
				p.setInvulnerable(true);				
			}
			else if(!holded.containsKey(le.getUniqueId()) && !superholding.containsKey(p.getUniqueId())) {
				holded.put(le.getUniqueId(), (long) 0);
				superholding.put(p.getUniqueId(), (long) 0);
				ale(le).setAI(false);
				if(ale(le).getType()==EntityType.CREEPER) {
					Creeper c = (Creeper)ale(le);
					c.setFuseTicks(0);
				}
				ale(le).setVelocity(ale(le).getVelocity().multiply(0));
				p.setInvulnerable(true);
			}
			int task =Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
                @Override
                public void run() 
                {
                	holded.computeIfPresent(le.getUniqueId(), (k, v) -> v-1);
                	if(holded.getOrDefault(le.getUniqueId(),0l)<0) {
                		ale(le).setAI(true);
                		holded.remove(le.getUniqueId());
                	}
                }
			}, tick); 
			lasttask.put(le.getUniqueId(), task);
			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
                @Override
                public void run() 
                {
                	superholding.computeIfPresent(p.getUniqueId(), (k, v) -> v-1);
					if(superholding.get(p.getUniqueId())<0) {
						p.setInvulnerable(false);
						superholding.remove(p.getUniqueId());
					}
                }
			}, tick); 
		}
		else if(le.getType()==EntityType.PLAYER&& !le.isDead()){
			if (le instanceof Player) 
			{
				Player p1 = (Player) le;
				if(p!=null && Party.hasParty(p) && Party.hasParty(p1))	{
				if(Party.getParty(p).equalsIgnoreCase(Party.getParty(p1)))
					{
						return;
					}
				}
			}

			if(holded.containsKey(le.getUniqueId()) && superholding.containsKey(p.getUniqueId())) {
				holded.computeIfPresent(le.getUniqueId(), (k, v) -> v+1);
				superholding.computeIfPresent(p.getUniqueId(), (k, v) -> v+1);
				p.setInvulnerable(true);
			}
			else if(holded.containsKey(le.getUniqueId()) && !superholding.containsKey(p.getUniqueId())) {
				holded.computeIfPresent(le.getUniqueId(), (k, v) -> v+1);
				superholding.put(p.getUniqueId(), (long) 0);
				p.setInvulnerable(true);				
			}
			else if(!holded.containsKey(le.getUniqueId()) && superholding.containsKey(p.getUniqueId())) {
				holded.put(le.getUniqueId(), (long) 0);
				superholding.computeIfPresent(p.getUniqueId(), (k, v) -> v+1);
				p.setInvulnerable(true);				
			}
			else if(!holded.containsKey(le.getUniqueId()) && !superholding.containsKey(p.getUniqueId())) {
				holded.put(le.getUniqueId(), (long) 0);
				superholding.put(p.getUniqueId(), (long) 0);
				p.setInvulnerable(true);
			}
			int task =Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
                @Override
                public void run() 
                {
                	holded.computeIfPresent(le.getUniqueId(), (k, v) -> v-1);
                	if(holded.getOrDefault(le.getUniqueId(),0l)<0) {
					holded.remove(le.getUniqueId());
                	}
                }
			}, tick); 
			lasttask.put(le.getUniqueId(), task);
			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
                @Override
                public void run() 
                {
                	superholding.computeIfPresent(p.getUniqueId(), (k, v) -> v-1);
					if(superholding.get(p.getUniqueId())<0) {
						p.setInvulnerable(false);
						superholding.remove(p.getUniqueId());
					}
                }
    	   }, tick); 
		
		}
	
	}
	
	static public void holding(@Nullable Player p, final LivingEntity le, Long tick) {
		if(le.hasMetadata("fake") || untouchable.containsKey(le.getUniqueId())) {
			return;
		}
			if(ale(le).getType()!=EntityType.PLAYER && ale(le).getType()!=EntityType.VILLAGER && !ale(le).isDead())
			{
				if(holded.containsKey(le.getUniqueId())) {
					holded.computeIfPresent(le.getUniqueId(), (k, v) -> v+1);
					ale(le).setAI(false);
					ale(le).setVelocity(ale(le).getVelocity().multiply(0));
					if(le.getType()==EntityType.CREEPER) {
						Creeper c = (Creeper)ale(le);
						c.setFuseTicks(0);
					}
				}
				else {
					holded.put(le.getUniqueId(), (long) 0);
					ale(le).setAI(false);
					ale(le).setVelocity(ale(le).getVelocity().multiply(0));
					if(ale(le).getType()==EntityType.CREEPER) {
						Creeper c = (Creeper)ale(le);
						c.setFuseTicks(0);
					}
				}
				int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	                @Override
	                public void run() 
	                {
	                	holded.computeIfPresent(le.getUniqueId(), (k, v) -> v-1);
	                	if(holded.getOrDefault(le.getUniqueId(),0l)<0) {
	                		ale(le).setAI(true);
	                		holded.remove(le.getUniqueId());
	                	}
	                }
				}, tick); 
				lasttask.put(le.getUniqueId(), task);
			}
			else if(ale(le).getType()==EntityType.PLAYER&& !ale(le).isDead()){
				if (le instanceof Player) 
				{
					Player p1 = (Player) le;
					if(p!=null && Party.hasParty(p) && Party.hasParty(p1))	{
					if(Party.getParty(p).equalsIgnoreCase(Party.getParty(p1)))
						{
							return;
						}
					}
				}
			
				if(holded.containsKey(le.getUniqueId())) {
					holded.computeIfPresent(le.getUniqueId(), (k, v) -> v+1);
				}
				else {
					holded.put(le.getUniqueId(), (long) 0);
				}
				int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	                @Override
	                public void run() 
	                {
	                	holded.computeIfPresent(le.getUniqueId(), (k, v) -> v-1);
	                	if(holded.getOrDefault(le.getUniqueId(),0l)<0) {
						holded.remove(le.getUniqueId());
	                	}
	                }
				}, tick); 
				lasttask.put(le.getUniqueId(), task);
			}
		
	}
	

	static public void invur(final LivingEntity le, Long tick) {
		if(superholding.containsKey(le.getUniqueId())) {
			superholding.computeIfPresent(le.getUniqueId(), (k, v) -> v+1);
			ale(le).setInvulnerable(true);
		}
		else if(!superholding.containsKey(le.getUniqueId())) {
			superholding.put(le.getUniqueId(), (long) 0);
			ale(le).setInvulnerable(true);				
		}
		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
            @Override
            public void run() 
            {
            	superholding.computeIfPresent(le.getUniqueId(), (k, v) -> v-1);
				if(superholding.getOrDefault(le.getUniqueId(),0l)<0) {
					superholding.remove(le.getUniqueId());
					ale(le).setInvulnerable(false);
				}
            }
	   }, tick); 
	}


	static public void untouchable(@NotNull LivingEntity le, Long tick) {
		if(untouchable.containsKey(le.getUniqueId())) {
			untouchable.computeIfPresent(le.getUniqueId(), (k, v) -> v+1);
			ale(le).setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
		}
		else if(!untouchable.containsKey(le.getUniqueId())) {
			untouchable.put(le.getUniqueId(), (long) 0);
			ale(le).setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
		}
		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
            @Override
            public void run() 
            {
            	untouchable.computeIfPresent(le.getUniqueId(), (k, v) -> v-1);
				if(untouchable.getOrDefault(le.getUniqueId(),0l)<0) {
	            	ale(le).removeMetadata("fake", RMain.getInstance());
					untouchable.remove(le.getUniqueId());
				}
            }
	   }, tick); 
	}

	static public void reset(@NotNull LivingEntity le) {
		if(untouchable.containsKey(le.getUniqueId())) {
        	ale(le).removeMetadata("fake", RMain.getInstance());
			untouchable.remove(le.getUniqueId());
		}
	}

	static public void fly(@NotNull Player p, Long tick) {
        if(flyt.containsKey(p.getUniqueId())) {
        	Bukkit.getScheduler().cancelTask(flyt.get(p.getUniqueId()));
        }
        p.setAllowFlight(true);
        p.setFlying(true);
		int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
            @Override
            public void run() 
            {
            	if(p.getGameMode() != GameMode.CREATIVE && p.getGameMode() != GameMode.SPECTATOR) {
					p.setAllowFlight(false);
	                p.setFlying(false);
            	}
            }
        }, tick); 
		flyt.put(p.getUniqueId(), task);
	}
	
	
	

	static public void unhold(@NotNull LivingEntity le) {
		if(ale(le).getType()!=EntityType.PLAYER && ale(le).getType()!=EntityType.VILLAGER && !ale(le).isDead())
		{
        	holded.computeIfPresent(le.getUniqueId(), (k, v) -> v-1);
        	if(holded.getOrDefault(le.getUniqueId(),(long) -1)<0) {
        		ale(le).setAI(true);
				holded.remove(le.getUniqueId());
        	}
        	Bukkit.getServer().getScheduler().cancelTask(lasttask.getOrDefault(le.getUniqueId(),0));
		}
		else if(ale(le).getType()==EntityType.PLAYER&& !ale(le).isDead()){
        	holded.computeIfPresent(le.getUniqueId(), (k, v) -> v-1);
        	if(holded.getOrDefault(le.getUniqueId(),0l)<0) {
				holded.remove(le.getUniqueId());
        	}
        	Bukkit.getServer().getScheduler().cancelTask(lasttask.getOrDefault(le.getUniqueId(),0));
		
		}
	
	}
	public void holded(ProjectileHitEvent ev) {
		if(ev.getHitEntity() == null) {
			return;
		}
		if(!superholding.containsKey(ev.getHitEntity().getUniqueId())) {
			ev.getHitEntity().setInvulnerable(false);
		}
	}

	public void holded(PlayerInteractEntityEvent ev) {
		if(ev.getRightClicked() == null) {
			return;
		}
		if(!superholding.containsKey(ev.getRightClicked().getUniqueId())) {
			ev.getRightClicked().setInvulnerable(false);
		}
		if(!holded.containsKey(ev.getRightClicked().getUniqueId()) && ev.getRightClicked() instanceof LivingEntity && !ev.getRightClicked().hasMetadata("fake")) {
			((LivingEntity)ale(ev.getRightClicked())).setAI(true);
		}
	}

	public void holded(EntityDamageByEntityEvent ev) {
		if(ev.getEntity() == null) {
			return;
		}
		if(!superholding.containsKey(ev.getEntity().getUniqueId())) {
			ev.getEntity().setInvulnerable(false);
		}
		if(!holded.containsKey(ev.getEntity().getUniqueId()) && ev.getEntity() instanceof LivingEntity && !ev.getEntity().hasMetadata("fake")) {
			((LivingEntity)ale(ev.getEntity())).setAI(true);
		}
	}
	
	public void holded(PlayerTeleportEvent ev) {
		ev.getTo().getWorld().getNearbyEntities(ev.getTo(), 60, 60, 60).forEach(e -> {
			if(!superholding.containsKey(e.getUniqueId()) && !e.hasMetadata("fake")) {
				ale(e).setInvulnerable(false);
			}
			if(!holded.containsKey(e.getUniqueId()) && e instanceof LivingEntity && !e.hasMetadata("fake")) {
				((LivingEntity)ale(e)).setAI(true);
			}
			

			if(!untouchable.containsKey(e.getUniqueId()) && e.hasMetadata("boss")) {
            	ale(e).removeMetadata("fake", RMain.getInstance());
			}
		});
	}
	
	
	public void holded(PlayerMoveEvent ev) {
		if(holded.containsKey(ev.getPlayer().getUniqueId())) {
			ev.setCancelled(true);
		}
	}
	
	
	
}
