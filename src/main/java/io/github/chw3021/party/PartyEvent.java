package io.github.chw3021.party;

import java.io.Serializable;
import java.util.UUID;
import java.util.stream.Collectors;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.event.player.PlayerExpChangeEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.event.server.PluginDisableEvent;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import io.github.chw3021.rmain.RMain;

public class PartyEvent implements Listener, Serializable {

	/**
	 * 
	 */
	private static transient final long serialVersionUID = -6724640892679279859L;
	@EventHandler
	public void Partydamcan(EntityDamageByEntityEvent d) 
	{
		if(d.getDamager() instanceof Player && d.getEntity() instanceof Player)
		{
			Player damager = (Player) d.getDamager();
			Player damagee = (Player) d.getEntity();
			if(PartyData.hasParty(damager)&&PartyData.hasParty(damagee)) {
				if(PartyData.getParty(damager).equals(PartyData.getParty(damagee)))
				{
					d.setDamage(0);
					d.setCancelled(true);
					return;
				}
			}
		}
		else if(d.getDamager() instanceof Projectile && d.getEntity() instanceof Player)
		{
			Projectile p = (Projectile) d.getDamager();
			if(p.getShooter() instanceof Player) {
				Player damager = (Player) p.getShooter();
				Player damagee = (Player) d.getEntity();
	
				if(PartyData.hasParty(damager)&&PartyData.hasParty(damagee)) {
					if(PartyData.getParty(damager).equals(PartyData.getParty(damagee)))
					{
						d.setDamage(0);
						d.setCancelled(true);
						return;
					}
				}
			}
		}
	}

	@EventHandler
	public void Partydamcan(EntityDamageEvent d) 
	{
		if(d.getEntity() instanceof Player && !d.isCancelled())
		{
			Player p = (Player) d.getEntity();
			if(PartyData.hasParty(p)) {
                Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
             		@Override
                	public void run() 
	                {	
             			p.getScoreboard().getObjective(PartyData.getParty(p)+"p").getScore(p.getName()).setScore((int)(p.getHealth()));
		            }
                }, 1/3);
			}
		}
	}

	@EventHandler
	public void Partydamcan(EntityRegainHealthEvent d) 
	{
		if(d.getEntity() instanceof Player)
		{
			Player p = (Player) d.getEntity();
			if(PartyData.hasParty(p)) {
				Player owner = PartyData.getOwner(PartyData.getParty(p).toString());
                Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
             		@Override
                	public void run() 
	                {	
             			p.getScoreboard().getObjective(PartyData.getParty(p)+"p").getScore(p.getName()).setScore((int)(p.getHealth()));
		            }
                }, 1/3);
			}
		}
	}

	@EventHandler
	public void Partydamcan(PlayerRespawnEvent d) 
	{
		if(d.getPlayer() instanceof Player)
		{
			Player p = d.getPlayer();
			if(PartyData.hasParty(p)) {
                Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
             		@Override
                	public void run() 
	                {	
             			p.getScoreboard().getObjective(PartyData.getParty(p)+"p").getScore(p.getName()).setScore((int)(p.getHealth()));
		            }
                }, 1/3);
			}
		}
	}
	
	@EventHandler
	public void Leave (PlayerQuitEvent e)
	{
		Player p = e.getPlayer();
		if(PartyData.hasParty(p)) 
		{
			Scoreboard lboard = p.getScoreboard();
			Scoreboard pboard = Partycom.pb.get(p);
			if(PartyData.getMembers(PartyData.getParty(p)).count()>1)
			{
				if(PartyData.isOwner(p)) {
					String par = PartyData.getParty(p);
					PartyData.removePartyMember(p, par);
					Player newowner = (Player) Bukkit.getPlayer(PartyData.getMembers(par).filter(o->(o!=p.getUniqueId())).findAny().get());
					PartyData.setNewOwner(newowner,par);
					newowner.sendMessage("You just took over owner of this party");
					p.sendMessage("You left your party");
					lboard.resetScores(p.getName());
					Team t = lboard.getEntryTeam(p.getName());
					t.removeEntry(p.getName());
					p.setScoreboard(pboard);
				}
				else
				{
					String par = PartyData.getParty(p);
					p.sendMessage("You left your party");
					lboard.resetScores(p.getName());
					Team t = lboard.getEntryTeam(p.getName());
					t.removeEntry(p.getName());
					p.setScoreboard(pboard);
					PartyData.removePartyMember(p, par);
				}
			}
			else {
				String par = PartyData.getParty(p);
				p.sendMessage("You left your party");
				lboard.resetScores(p.getName());
				Team t = lboard.getEntryTeam(p.getName());
				t.removeEntry(p.getName());
				p.setScoreboard(pboard);
				PartyData.removePartyMember(p, par);
				PartyData.removeParty(par);
			}
		}
	}
	@EventHandler
	public void Reset (PluginDisableEvent e)
	{
		e.getPlugin().getServer().getWorlds().forEach(wor -> wor.getPlayers().forEach(p -> {
		if(PartyData.hasParty(p))
			{
					PartyData.removeParty(PartyData.getParty(p));
			}
			
		}));		
	}
	@EventHandler
	public void Expup (PlayerExpChangeEvent e)
	{
		Player p = e.getPlayer();
		if(PartyData.hasParty(p)) {
			for(UUID mem : PartyData.getMembers(PartyData.getParty(p)).collect(Collectors.toSet()))
			{
				if(Bukkit.getPlayer(mem) != p)
				Bukkit.getPlayer(mem).giveExp(e.getAmount());
			}
		}
		
	}

}
