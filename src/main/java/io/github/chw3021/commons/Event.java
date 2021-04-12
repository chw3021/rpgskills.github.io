package io.github.chw3021.commons;

import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.boss.DragonBattle.RespawnPhase;
import org.bukkit.entity.EnderDragon;
import org.bukkit.entity.EnderDragon.Phase;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EnderDragonChangePhaseEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerGameModeChangeEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.event.server.PluginDisableEvent;
import org.bukkit.event.server.PluginEnableEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.RenderType;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.util.Vector;

import io.github.chw3021.classes.ClassData;
import io.github.chw3021.party.PartyData;
import io.github.chw3021.rmain.RMain;


public class Event implements Listener {
	
	static public HashMap<UUID, String> damaged = new HashMap<UUID, String>();
	private String path = new File("").getAbsolutePath();
	private HashMap<UUID, Integer> playerclass;

	@EventHandler
	public void classinv(InventoryClickEvent e)
	{
		if(ChatColor.stripColor(e.getView().getTitle()).equalsIgnoreCase("Classes"))
		{
			e.setCancelled(true);
			if(e.getCurrentItem()==null||e.getCurrentItem().getType() == null|| !e.getCurrentItem().hasItemMeta())
			{
				e.setCancelled(false);
			}
			else
			{	
				ClassData cdata = new ClassData(ClassData.loadData(path +"/plugins/RPGskills/ClassData.data"));
				playerclass = cdata.playerclass;
			}
			
		}
	}


	private static final ScoreboardManager manager = Bukkit.getScoreboardManager();

	@EventHandler
	public void Villdamcan(EntityDamageByEntityEvent d) 
	{
		if(d.getDamager() instanceof Player && d.getEntity().getType() == EntityType.VILLAGER && !d.getEntity().hasMetadata("rpgspawned") && !d.getEntity().hasMetadata("fake"))
		{
			d.setCancelled(true);
			return;
		}
		else if(d.getDamager() instanceof Player && d.getEntity().getType() == EntityType.VILLAGER && !d.getEntity().hasMetadata("rpgspawned") && !d.getEntity().hasMetadata("fake"))
		{
			Projectile p = (Projectile) d.getDamager();
			if(p.getShooter() instanceof Player) {
				d.setCancelled(true);
				return;
			}
		}
	}
	
	@EventHandler
	public void WallDamagecan(EntityDamageEvent d) 
	{
		if(d.getEntity() instanceof LivingEntity && (d.getCause() == DamageCause.FLY_INTO_WALL))
		{
			LivingEntity le = (LivingEntity) d.getEntity();
			Vector lev = le.getVelocity().clone().normalize().multiply(-1);
			Location letl = le.getLocation().clone().add(lev.normalize().multiply(2.1));
			try {
				if(letl.getBlock().isPassable()) {
					le.teleport(letl);
					d.setCancelled(true);
				}
			}
			catch(IllegalArgumentException e) {
				if(le.getNearbyEntities(10, 10, 10).stream().filter(lel -> lel instanceof LivingEntity).findFirst().isPresent()) {
					le.teleport(le.getNearbyEntities(11, 11, 11).stream().filter(lel -> lel instanceof LivingEntity).findFirst().get());
					d.setCancelled(true);
				}
				else {
					le.teleport(le.getLocation().add(0,1,0));
				}
			}
		}
		else if(d.getEntity() instanceof LivingEntity && (d.getCause() == DamageCause.SUFFOCATION))
		{
			LivingEntity le = (LivingEntity) d.getEntity();
			if(le.getNearbyEntities(10, 10, 10).stream().filter(lel -> lel instanceof LivingEntity).findFirst().isPresent()) {
				le.teleport(le.getNearbyEntities(11, 11, 11).stream().filter(lel -> lel instanceof LivingEntity).findFirst().get());
				d.setCancelled(true);
			}
			else {
				le.teleport(le.getLocation().add(0,1,0));
				d.setCancelled(true);
			}
		}
	}
	
	
	@EventHandler
	public void Damagegetter(EntityDamageByEntityEvent d) 
	{
		if(d.getEntity().hasMetadata("fake"))
		{
			d.setCancelled(true);
		}
		if(d.getDamager() instanceof Player && d.getEntity() instanceof LivingEntity && !d.isCancelled() && !d.getEntity().hasMetadata("untargetable") && d.getDamage()!=0) {
			Player p = (Player) d.getDamager();
			LivingEntity le = (LivingEntity) d.getEntity();
			le.setMaximumNoDamageTicks(0);
			le.setNoDamageTicks(0);
			le.setRemoveWhenFarAway(false);
			if(!damaged.containsKey(le.getUniqueId())) {
				damaged.put(le.getUniqueId(), le.getName());
				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	                @Override
	                public void run() 
	                {
	    				le.setCustomName((le.getName()) + (ChatColor.UNDERLINE + " ("+ String.valueOf(Math.round(le.getHealth()*10)/10.0) + "/" + String.valueOf(Math.round(le.getMaxHealth()*10)/10.0) + ")"));
	    				le.setCustomNameVisible(true);
	                }
	            }, 1/5); 
				le.setMetadata("damaged", new FixedMetadataValue(RMain.getInstance(),true));
			}
			else {
				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	                @Override
	                public void run() 
	                {
	    				le.setCustomName(damaged.get(le.getUniqueId()) + (ChatColor.UNDERLINE + " ("+ String.valueOf(Math.round(le.getHealth()*10)/10.0) + "/" + String.valueOf(Math.round(le.getMaxHealth()*10)/10.0) + ")"));
	    				le.setCustomNameVisible(true);
	                }
	            }, 1/5); 
				le.setMetadata("damaged", new FixedMetadataValue(RMain.getInstance(),true));
			}
			Location el = le.getLocation().add(0, 0.1, 0);
			if(!el.getBlock().isPassable()) {
				el.add(0, 1.1, 0);
			}
			Vector dinv = p.getEyeLocation().toVector().subtract(el.toVector()).normalize();
			Vector dinvv = dinv.clone();
			if(le.getLocation().getY()<p.getEyeLocation().getY()) {
				dinvv.rotateAroundAxis(dinv.clone().rotateAroundY(Math.PI/2), -Math.PI/6).normalize();
			}
			else {
				dinvv.rotateAroundAxis(dinv.clone().rotateAroundY(Math.PI/2), -Math.PI/45).normalize();					
			}
			
			Snowball din = (Snowball) le.getWorld().spawnEntity(el.clone().add(dinvv.clone().multiply(0.385)), EntityType.SNOWBALL);
			din.setSilent(true);
			if(playerclass.containsKey(p.getUniqueId())) {
				switch (playerclass.get(p.getUniqueId())){
				case 0:
					din.setItem(new ItemStack(Material.IRON_SWORD));
					break;
				case 1:
					din.setItem(new ItemStack(Material.CRIMSON_ROOTS));
					break;
				case 2:
					din.setItem(new ItemStack(Material.IRON_AXE));
					break;
				case 3:
					din.setItem(new ItemStack(Material.SHIELD));
					break;
				case 4:
					din.setItem(new ItemStack(Material.CROSSBOW));
					break;
				case 5:
					din.setItem(new ItemStack(Material.FIREWORK_ROCKET));
					break;
				case 6:
					din.setItem(new ItemStack(Material.BOW));
					break;
				case 61:
					ItemStack ta = new ItemStack(Material.TIPPED_ARROW);
					PotionMeta meta = (PotionMeta) ta.getItemMeta();
					meta.setColor(Color.RED);
					ta.setItemMeta(meta);
					din.setItem(ta);
					break;
				case 7:
					din.setItem(new ItemStack(Material.IRON_HELMET));
					break;
				case 8:
					din.setItem(new ItemStack(Material.GOLDEN_CHESTPLATE));
					break;
				case 9:
					din.setItem(new ItemStack(Material.LEAD));
					break;
				case 10:
					din.setItem(new ItemStack(Material.SOUL_CAMPFIRE));
					break;
				case 11:
					din.setItem(new ItemStack(Material.JACK_O_LANTERN));
					break;
				case 12:
					din.setItem(new ItemStack(Material.FIRE_CHARGE));
					break;
				case 13:
					din.setItem(new ItemStack(Material.WITHER_ROSE));
					break;
				case 14:
					din.setItem(new ItemStack(Material.TOTEM_OF_UNDYING));
					break;
				case 15:
					din.setItem(new ItemStack(Material.POTION));
					break;
				case 16:
					din.setItem(new ItemStack(Material.BEACON));
					break;
				case 17:
					din.setItem(new ItemStack(Material.IRON_PICKAXE));
					break;
				case 18:
					din.setItem(new ItemStack(Material.COOKED_BEEF));
					break;
				case 19:
					din.setItem(new ItemStack(Material.HEART_OF_THE_SEA));	
					break;
				case 20:	
					din.setItem(new ItemStack(Material.TRIDENT));
					break;				
				case 21:	
					din.setItem(new ItemStack(Material.PACKED_ICE));
					break;	
				}
			}
			else {
				din.setItem(new ItemStack(Material.PLAYER_HEAD));				
			}
			din.setBounce(false);
			din.setGravity(false);
			din.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(),true));
			din.setMetadata("din of "+p.getName(), new FixedMetadataValue(RMain.getInstance(),true));
			if(d.getFinalDamage()<0.1) {
				din.setCustomName(ChatColor.AQUA + String.valueOf(Math.round(d.getFinalDamage()*1000)/1000.000) + " ["+ p.getName()+"]");				
			}
			else if(d.getFinalDamage()>=999999) {
				din.setCustomName(ChatColor.RED + "!999999!" + " ["+ p.getName()+"]");	
				din.setGlowing(true);
				
			}
			else {
				din.setCustomName(ChatColor.AQUA + String.valueOf(Math.round(d.getFinalDamage()*10)/10.0) + " ["+ p.getName()+"]");				
			}
			din.setCustomNameVisible(true);
			din.setVelocity((el.clone().add(dinvv.clone().multiply(0.15))).toVector().subtract(le.getLocation().toVector()).normalize().multiply(0.12));
			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() 
			{
         	@Override
	                public void run() 
         				{	
         					din.remove();
			            }
            }, 30);
		}
		if(d.getDamager() instanceof Projectile && d.getEntity() instanceof LivingEntity&& !d.isCancelled()&& !d.getEntity().hasMetadata("untargetable") && d.getDamage()!=0) {
			Projectile pr = (Projectile) d.getDamager();
			if(pr.getShooter() instanceof Player && !(pr instanceof Snowball)) {
				Player p = (Player) pr.getShooter();
				LivingEntity le = (LivingEntity) d.getEntity();
				le.setMaximumNoDamageTicks(0);
				le.setNoDamageTicks(0);
				le.setRemoveWhenFarAway(false);
				if(!damaged.containsKey(le.getUniqueId())) {
					damaged.put(le.getUniqueId(), le.getName());
					Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		                @Override
		                public void run() 
		                {
		    				le.setCustomName((le.getName()) + (ChatColor.UNDERLINE + " ("+ String.valueOf(Math.round(le.getHealth()*10)/10.0) + "/" + String.valueOf(Math.round(le.getMaxHealth()*10)/10.0) + ")"));
		    				le.setCustomNameVisible(true);
		                }
		            }, 1/5); 
					le.setMetadata("damaged", new FixedMetadataValue(RMain.getInstance(),true));
				}
				else {
					Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
		                @Override
		                public void run() 
		                {
		    				le.setCustomName(damaged.get(le.getUniqueId()) + (ChatColor.UNDERLINE + " ("+ String.valueOf(Math.round(le.getHealth()*10)/10.0) + "/" + String.valueOf(Math.round(le.getMaxHealth()*10)/10.0) + ")"));
		    				le.setCustomNameVisible(true);
		                }
		            }, 1/5); 
					le.setMetadata("damaged", new FixedMetadataValue(RMain.getInstance(),true));
				}
				Location el = le.getLocation().add(0, 0.1, 0);
				if(!el.getBlock().isPassable()) {
					el.add(0, 1.1, 0);
				}
				Vector dinv = p.getEyeLocation().toVector().subtract(el.toVector()).normalize();
				Vector dinvv = dinv.clone();
				if(le.getLocation().getY()<p.getEyeLocation().getY()) {
					dinvv.rotateAroundAxis(dinv.clone().rotateAroundY(Math.PI/2), -Math.PI/6).normalize();
				}
				else {
					dinvv.rotateAroundAxis(dinv.clone().rotateAroundY(Math.PI/2), -Math.PI/45).normalize();					
				}
				
				Snowball din = (Snowball) le.getWorld().spawnEntity(el.clone().add(dinvv.clone().multiply(0.385)), EntityType.SNOWBALL);
				din.setSilent(true);
				if(playerclass.containsKey(p.getUniqueId())) {
					switch (playerclass.get(p.getUniqueId())){
					case 0:
						din.setItem(new ItemStack(Material.IRON_SWORD));
						break;
					case 1:
						din.setItem(new ItemStack(Material.CRIMSON_ROOTS));
						break;
					case 2:
						din.setItem(new ItemStack(Material.IRON_AXE));
						break;
					case 3:
						din.setItem(new ItemStack(Material.SHIELD));
						break;
					case 4:
						din.setItem(new ItemStack(Material.CROSSBOW));
						break;
					case 5:
						din.setItem(new ItemStack(Material.FIREWORK_ROCKET));
						break;
					case 6:
						din.setItem(new ItemStack(Material.BOW));
						break;
					case 61:
						ItemStack ta = new ItemStack(Material.TIPPED_ARROW);
						PotionMeta meta = (PotionMeta) ta.getItemMeta();
						meta.setColor(Color.RED);
						ta.setItemMeta(meta);
						din.setItem(ta);
						break;
					case 7:
						din.setItem(new ItemStack(Material.IRON_HELMET));
						break;
					case 8:
						din.setItem(new ItemStack(Material.GOLDEN_CHESTPLATE));
						break;
					case 9:
						din.setItem(new ItemStack(Material.LEAD));
						break;
					case 10:
						din.setItem(new ItemStack(Material.SOUL_CAMPFIRE));
						break;
					case 11:
						din.setItem(new ItemStack(Material.JACK_O_LANTERN));
						break;
					case 12:
						din.setItem(new ItemStack(Material.FIRE_CHARGE));
						break;
					case 13:
						din.setItem(new ItemStack(Material.WITHER_ROSE));
						break;
					case 14:
						din.setItem(new ItemStack(Material.TOTEM_OF_UNDYING));
						break;
					case 15:
						din.setItem(new ItemStack(Material.POTION));
						break;
					case 16:
						din.setItem(new ItemStack(Material.BEACON));
						break;
					case 17:
						din.setItem(new ItemStack(Material.IRON_PICKAXE));
						break;
					case 18:
						din.setItem(new ItemStack(Material.COOKED_BEEF));
						break;
					case 19:
						din.setItem(new ItemStack(Material.HEART_OF_THE_SEA));	
						break;
					case 20:	
						din.setItem(new ItemStack(Material.TRIDENT));
						break;		
					case 21:	
						din.setItem(new ItemStack(Material.PACKED_ICE));
						break;		
					}
				}
				else {
					din.setItem(new ItemStack(Material.PLAYER_HEAD));				
				}
				din.setBounce(false);
				din.setGravity(false);
				din.setMetadata("din of "+p.getName(), new FixedMetadataValue(RMain.getInstance(),true));
				din.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(),true));
				if(d.getFinalDamage()<0.1) {
					din.setCustomName(ChatColor.AQUA + String.valueOf(Math.round(d.getFinalDamage()*1000)/1000.000) + " ["+ p.getName()+"]");				
				}
				else if(d.getFinalDamage()>=999999) {
					din.setCustomName(ChatColor.RED + "!999999!" + " ["+ p.getName()+"]");	
					din.setGlowing(true);
					
				}
				else {
					din.setCustomName(ChatColor.AQUA + String.valueOf(Math.round(d.getFinalDamage()*10)/10.0) + " ["+ p.getName()+"]");				
				}
				din.setCustomNameVisible(true);
				din.setVelocity((el.clone().add(dinvv.clone().multiply(0.15))).toVector().subtract(le.getLocation().toVector()).normalize().multiply(0.12));
				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() 
				{
	         	@Override
		                public void run() 
	         				{	
	         					din.remove();
				            }
	            }, 30);
			}
			
		}
	}

	@EventHandler
	public void Enderdragon(EntityDamageByEntityEvent d) 
	{		
        if (!(d.getEntity() instanceof EnderDragon)) {
            return;
        }
        EnderDragon ed = (EnderDragon) d.getEntity();
		if(ed.getHealth()<=d.getDamage()) {
			ed.setAI(true);
			ed.setCustomName(null);
			ed.setCustomNameVisible(false);
	        ed.getDragonBattle().getBossBar().setTitle(ed.getName());
		}
	}

	@EventHandler
	public void Enderdragon(CreatureSpawnEvent ev) {
        if(ev.getEntityType() == EntityType.ENDER_DRAGON) {

        	ev.getEntity().setCustomName(null);
        	ev.getEntity().setCustomNameVisible(false);
            
        }
	}
	
	@EventHandler
	public void Enderdragon(EnderDragonChangePhaseEvent d) 
	{	
        EnderDragon ed = (EnderDragon) d.getEntity();
        d.getEntity().setCustomName(null);
        d.getEntity().setCustomNameVisible(false);
        if(ed.getDragonBattle().getRespawnPhase() == RespawnPhase.END) {
			ed.setCustomName(null);
			ed.setCustomNameVisible(false);
        }
		if(d.getNewPhase() == Phase.DYING) {
			ed.setAI(true);
			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
                @Override
                public void run() 
                {
        			ed.setAI(true);
        			ed.setCustomName(null);
        			ed.setCustomNameVisible(false);
                }
            }, 1); 
		}
		if(d.getCurrentPhase() == Phase.DYING) {
			ed.setAI(true);
			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
                @Override
                public void run() 
                {
        			ed.setAI(true);
        			ed.setCustomName(null);
        			ed.setCustomNameVisible(false);
                }
            }, 1); 
		}
		if(ed.getPhase() == Phase.DYING) {
			ed.setAI(true);
			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
                @Override
                public void run() 
                {
        			ed.setAI(true);
        			ed.setCustomName(null);
        			ed.setCustomNameVisible(false);
                }
            }, 1); 	
		}
	}
	
	@EventHandler	
	public void deleter(PluginDisableEvent ev) 
	{
		ClassData cdata = new ClassData(ClassData.loadData(path +"/plugins/RPGskills/ClassData.data"));
		playerclass = cdata.playerclass;
		Bukkit.getBossBars().forEachRemaining(b -> b.removeAll());
		List<World> worlds = Bukkit.getServer().getWorlds();

		worlds.forEach(w -> w.getEntities().forEach(b -> {
			b.setCustomName(damaged.get(b.getUniqueId()));
			if(b.hasMetadata("rpgspawned")) {
				b.remove();
			}
		}));
		worlds.forEach(w -> w.getEntities().stream().filter(b -> b.hasMetadata("untargetable")).forEach(b -> b.remove()));
		worlds.forEach(w -> w.getEntities().stream().filter(b -> (b.hasMetadata("fake") && !(b instanceof Player))).forEach(b -> b.remove()));
		Bukkit.getServer().getOnlinePlayers().forEach(p -> p.getScoreboard().getObjectives().forEach(o -> o.unregister()));
	}

	@EventHandler	
	public void er(PluginEnableEvent ev) 
	{
		ClassData cdata = new ClassData(ClassData.loadData(path +"/plugins/RPGskills/ClassData.data"));
		playerclass = cdata.playerclass;
		Bukkit.getServer().getOnlinePlayers().forEach(p->{

			Scoreboard board = manager.getNewScoreboard();
			Objective objective = board.registerNewObjective("Hearts" ,"health", "[Health]", RenderType.HEARTS);
			objective.setDisplaySlot(DisplaySlot.BELOW_NAME);
			p.setScoreboard(board);
			p.setHealth(p.getMaxHealth());
		});
	}
	
	@EventHandler	
	public void nepreventer(PlayerJoinEvent ev) 
	{
		Player p = ev.getPlayer();

		ClassData cdata = new ClassData(ClassData.loadData(path +"/plugins/RPGskills/ClassData.data"));
		playerclass = cdata.playerclass;
		Scoreboard board = manager.getNewScoreboard();
		Objective objective = board.registerNewObjective("Hearts" ,"health", "[Health]", RenderType.HEARTS);
		objective.setDisplaySlot(DisplaySlot.BELOW_NAME);
		p.setScoreboard(board);
		p.setHealth(p.getMaxHealth());
		if(!playerclass.containsKey(p.getUniqueId())) {
			playerclass.put(p.getUniqueId(), -1);
		}
		if(p.getGameMode() == GameMode.CREATIVE) {
			p.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(),true));
		}
		if(p.getGameMode() != GameMode.CREATIVE) {
			if(p.isInvulnerable()) {
				p.setInvulnerable(false);
			}
		}
		
	}
	@EventHandler	
	public void nepreventer(PlayerQuitEvent ev) 
	{
		Player p = ev.getPlayer();

		p.getScoreboard().getObjectives().forEach(o -> o.unregister());
		p.getWorld().getEntities().forEach(e -> {
			if(e.hasMetadata("din of "+p.getName())) {
				e.remove();
			}
		});
		if(p.getServer().getOnlinePlayers().size()<=1) {

			List<World> worlds = Bukkit.getServer().getWorlds();
			worlds.forEach(w -> w.getEntities().forEach(b -> {
				b.setCustomName(damaged.get(b.getUniqueId()));
				if(b.hasMetadata("rpgspawned")) {
					b.remove();
				}
			}));
		}
		
	}
	@EventHandler	
	public void dinremove(PlayerDeathEvent ev) 
	{
		Player p = ev.getEntity();

		p.getWorld().getEntities().forEach(e -> {
			if(e.hasMetadata("din of "+p.getName())) {
				e.remove();
			}
		});
	}
	@EventHandler	
	public void dinremove(PlayerRespawnEvent ev) 
	{
		Player p = ev.getPlayer();

		p.getWorld().getEntities().forEach(e -> {
			if(e.hasMetadata("din of "+p.getName())) {
				e.remove();
			}
		});
		if(p.getGameMode() != GameMode.CREATIVE) {
			if(p.isInvulnerable()) {
				p.setInvulnerable(false);
			}
		}
	}
	@EventHandler	
	public void nepreventer(PlayerGameModeChangeEvent ev) 
	{
		Player p = ev.getPlayer();
		if(ev.getNewGameMode() == GameMode.CREATIVE) {
			p.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(),true));
		}
		else {
			p.removeMetadata("fake", RMain.getInstance());
		}
	}
	@EventHandler
	public void Bullet(ProjectileHitEvent ev) 
	{
		
		if(ev.getEntity().getShooter() instanceof Player)
		{
			Player p = (Player)ev.getEntity().getShooter();
				if(ev.getHitEntity()!=null) {
					Entity e =ev.getHitEntity();
					{
                		if (e instanceof Player) 
						{
							Player p1 = (Player) e;
							if(PartyData.hasParty(p) && PartyData.hasParty(p1))	{
							if(PartyData.getParty(p) == PartyData.getParty(p1))
								{
								return;
								}
							}
						}
                		if ((!(e == p))&& e instanceof LivingEntity&& !(e.hasMetadata("fake"))) 
						{
							LivingEntity le = (LivingEntity)e;
							le.setMaximumNoDamageTicks(0);
							le.setNoDamageTicks(0);
							le.setRemoveWhenFarAway(false);
						}
					}
				}
					
		}
	}
	

	@EventHandler	
	public void classShow(PlayerJoinEvent ev) 
	{
		ClassData cdata = new ClassData(ClassData.loadData(path +"/plugins/RPGskills/ClassData.data"));
		playerclass = cdata.playerclass;
		Player p = ev.getPlayer();

		if(playerclass.containsKey(p.getUniqueId())) {
			switch(playerclass.get(p.getUniqueId())) {
			case 0:{
				p.addScoreboardTag(ChatColor.AQUA + "SwordMan");
				p.setDisplayName(ChatColor.AQUA + "[SwordMan]" + p.getDisplayName());
			    p.setPlayerListName(ChatColor.AQUA + "[SwordMan]" + p.getPlayerListName());			
			    break;
			}
			case 1:{
				p.addScoreboardTag(ChatColor.DARK_RED + "Berserker");
				p.setDisplayName(ChatColor.DARK_RED + "[Berserker]" + p.getDisplayName());
			    p.setPlayerListName(ChatColor.DARK_RED + "[Berserker]" + p.getPlayerListName());		
			    break;
			}
			case 2:{
				p.addScoreboardTag(ChatColor.GREEN + "Hunter");
				p.setDisplayName(ChatColor.GREEN + "[Hunter]" + p.getDisplayName());
			    p.setPlayerListName(ChatColor.GREEN + "[Hunter]" + p.getPlayerListName());		
			    break;
			}
			case 3:{
				p.addScoreboardTag(ChatColor.WHITE + "Paladin");
				p.setDisplayName(ChatColor.WHITE + "[Paladin]" + p.getDisplayName());
			    p.setPlayerListName(ChatColor.WHITE + "[Paladin]" + p.getPlayerListName());		
			    break;
			}
			case 4:{
				p.addScoreboardTag(ChatColor.getByChar("336600") + "Sniper");
				p.setDisplayName(ChatColor.getByChar("336600") + "[Sniper]" + p.getDisplayName());
			    p.setPlayerListName(ChatColor.getByChar("336600") + "[Sniper]" + p.getPlayerListName());		
			    break;
			}
			case 5:{
				p.addScoreboardTag(ChatColor.LIGHT_PURPLE + "Launcher");
				p.setDisplayName(ChatColor.LIGHT_PURPLE + "[Launcher]" + p.getDisplayName());
			    p.setPlayerListName(ChatColor.LIGHT_PURPLE + "[Launcher]" + p.getPlayerListName());		
			    break;
			}
			case 6:{
				p.addScoreboardTag(ChatColor.BLUE + "Archer");
				p.setDisplayName(ChatColor.BLUE + "[Archer]" + p.getDisplayName());
			    p.setPlayerListName(ChatColor.BLUE + "[Archer]" + p.getPlayerListName());		
			    break;
			}
			case 61:{
				p.addScoreboardTag(ChatColor.LIGHT_PURPLE + "Medic");
				p.setDisplayName(ChatColor.LIGHT_PURPLE + "[Medic]" + p.getDisplayName());
			    p.setPlayerListName(ChatColor.LIGHT_PURPLE + "[Medic]" + p.getPlayerListName());		
			    break;
			}
			case 7:{
				p.addScoreboardTag(ChatColor.GRAY + "Boxer");
				p.setDisplayName(ChatColor.GRAY + "[Boxer]" + p.getDisplayName());
			    p.setPlayerListName(ChatColor.GRAY + "[Boxer]" + p.getPlayerListName());		
			    break;
			}
			case 8:{
				p.addScoreboardTag(ChatColor.GOLD + "Wrestler");
				p.setDisplayName(ChatColor.GOLD + "[Wrestler]" + p.getDisplayName());
			    p.setPlayerListName(ChatColor.GOLD + "[Wrestler]" + p.getPlayerListName());		
			    break;
			}
			case 9:{
				p.addScoreboardTag(ChatColor.DARK_GRAY + "Tamer");
				p.setDisplayName(ChatColor.DARK_GRAY + "[Tamer]" + p.getDisplayName());
			    p.setPlayerListName(ChatColor.DARK_GRAY + "[Tamer]" + p.getPlayerListName());		
			    break;
			}
			case 10:{
				p.addScoreboardTag(ChatColor.YELLOW + "Taoist");
				p.setDisplayName(ChatColor.YELLOW + "[Taoist]" + p.getDisplayName());
			    p.setPlayerListName(ChatColor.YELLOW + "[Taoist]" + p.getPlayerListName());			
			    break;
			}
			case 11:{
				p.addScoreboardTag(ChatColor.DARK_PURPLE + "Illusionist");
				p.setDisplayName(ChatColor.DARK_PURPLE + "[Illusionist]" + p.getDisplayName());
			    p.setPlayerListName(ChatColor.DARK_PURPLE + "[Illusionist]" + p.getPlayerListName());		
			    break;
			}
			case 12:{
				p.addScoreboardTag(ChatColor.RED + "FireMage");
				p.setDisplayName(ChatColor.RED + "[FireMage]" + p.getDisplayName());
			    p.setPlayerListName(ChatColor.RED + "[FireMage]" + p.getPlayerListName());		
			    break;
			}
			case 13:{
				p.addScoreboardTag(ChatColor.BLACK + "Witherist");
				p.setDisplayName(ChatColor.BLACK + "[Witherist]" + p.getDisplayName());
			    p.setPlayerListName(ChatColor.BLACK+ "[Witherist]" + p.getPlayerListName());		
			    break;
			}
			case 14:{
				p.addScoreboardTag(ChatColor.DARK_GREEN + "WitchDoctor");
				p.setDisplayName(ChatColor.DARK_GREEN + "[WitchDorcor]" + p.getDisplayName());
			    p.setPlayerListName(ChatColor.DARK_GREEN+ "[WitchDoctor]" + p.getPlayerListName());		
			    break;
			}
			case 15:{
				p.addScoreboardTag(ChatColor.LIGHT_PURPLE + "Chemist");
				p.setDisplayName(ChatColor.LIGHT_PURPLE + "[Chemist]" + p.getDisplayName());
			    p.setPlayerListName(ChatColor.LIGHT_PURPLE+ "[Chemist]" + p.getPlayerListName());		
			    break;
			}
			case 16:{
				p.addScoreboardTag(ChatColor.BLUE + "Forger");
				p.setDisplayName(ChatColor.BLUE+ "[Forger]" + p.getDisplayName());
			    p.setPlayerListName(ChatColor.BLUE+ "[Forger]" + p.getPlayerListName());		
			    break;
			}
			case 17:{
				p.addScoreboardTag(ChatColor.AQUA + "Engineer");
				p.setDisplayName(ChatColor.AQUA  + "[Engineer]" + p.getDisplayName());
			    p.setPlayerListName(ChatColor.AQUA + "[Engineer]" + p.getPlayerListName());		
			    break;
			}
			case 18:{
				p.addScoreboardTag(ChatColor.GOLD + "Cook");
				p.setDisplayName(ChatColor.GOLD + "[Cook]" + p.getDisplayName());
			    p.setPlayerListName(ChatColor.GOLD+ "[Cook]" + p.getPlayerListName());		
			    break;
			}
			case 19:{
				p.addScoreboardTag(ChatColor.BLUE+ "Nobility");
				p.setDisplayName(ChatColor.BLUE + "[Nobility]" + p.getDisplayName());
			    p.setPlayerListName(ChatColor.BLUE+ "[Nobility]" + p.getPlayerListName());		
			    break;
			}
			case 20:{
				p.addScoreboardTag(ChatColor.DARK_AQUA + "OceanKnight");
				p.setDisplayName(ChatColor.DARK_AQUA + "[OceanKnight]" + p.getDisplayName());
			    p.setPlayerListName(ChatColor.DARK_AQUA + "[OceanKnight]" + p.getPlayerListName());		
			    break;
			}
			case 21:{
				p.addScoreboardTag(ChatColor.AQUA + "Frostman");
				p.setDisplayName(ChatColor.AQUA + "[Frostman]" + p.getDisplayName());
			    p.setPlayerListName(ChatColor.AQUA + "[Frostman]" + p.getPlayerListName());			
			    break;
			}
			    
				
				
			}
		}
		
	}
}
