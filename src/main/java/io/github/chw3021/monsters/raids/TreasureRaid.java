package io.github.chw3021.monsters.raids;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.attribute.Attribute;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.Skeleton;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.event.server.PluginDisableEvent;
import org.bukkit.event.server.PluginEnableEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

import io.github.chw3021.commons.Holding;
import io.github.chw3021.commons.party.Party;
import io.github.chw3021.items.Elements;
import io.github.chw3021.monsters.Mobs;
import io.github.chw3021.rmain.RMain;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.ComponentBuilder;


public class TreasureRaid extends Mobs implements Listener {

	/**
	 * 
	 */
	private transient static final long serialVersionUID = -4094063645512122453L;
	/**
	 * 
	 */
	private HashMap<UUID, Location> beforepl = new HashMap<UUID, Location>();
	private Multimap<String, UUID> raider = ArrayListMultimap.create();
	private static Multimap<String, UUID> heroes = ArrayListMultimap.create();
	private static HashMap<String, Location> raidloc = new HashMap<String, Location>();
	private HashMap<String, UUID> raidpor = new HashMap<String, UUID>();
	private HashMap<String, Integer> raidt = new HashMap<String, Integer>();
	private HashMap<String, BossBar> raidbar = new HashMap<String, BossBar>();
	private HashMap<String, Integer> raidbart = new HashMap<String, Integer>();
	
	
	private HashMap<String, Integer> lives = new HashMap<String, Integer>();

	private HashMap<String, Integer> timeout = new HashMap<String, Integer>();
	private HashMap<String, Integer> timet = new HashMap<String, Integer>();
	private HashMap<String, BossBar> timebar = new HashMap<String, BossBar>();

	Integer DelayTime =  5;
	
	
	private static final TreasureRaid instance = new TreasureRaid ();
	public static TreasureRaid getInstance()
	{
		return instance;
	}
	
	public static Collection<Player> getheroes(LivingEntity le)
	{
		if(le.hasMetadata("raid")) {
			String rn = le.getMetadata("raid").get(0).asString();
			Collection<Player> pc = new ArrayList<Player>();
			heroes.get(rn).forEach(pu -> pc.add(Bukkit.getPlayer(pu)));
			return pc;
		}
		else if(le.hasMetadata("raidvil")) {
			String rn = le.getMetadata("raidvil").get(0).asString();
			Collection<Player> pc = new ArrayList<Player>();
			heroes.get(rn).forEach(pu -> pc.add(Bukkit.getPlayer(pu)));
			return pc;
		}
		else{
			return null;
		}
	}

	public static Location getraidloc(LivingEntity le)
	{
		if(le.hasMetadata("raid")) {
			String rn = le.getMetadata("raid").get(0).asString();
			return raidloc.get(rn);
		}
		else{
			return null;
		}
	}
	
	private void RaidFinish(String rn, String title, String sub, Integer factor) {


		Bukkit.getWorld("TreasureRaid").getEntities().stream().filter(e -> e.hasMetadata("redknightcharge"+rn)).forEach(e -> e.remove());
		Bukkit.getWorld("TreasureRaid").getEntities().stream().filter(e -> e.hasMetadata("redknightmagma"+rn)).forEach(e -> e.remove());

		if(raidbart.containsKey(rn)) {
			Bukkit.getServer().getScheduler().cancelTask(raidbart.get(rn));
			raidbart.remove(rn);
		}
		if(raidt.containsKey(rn)) {
			Bukkit.getServer().getScheduler().cancelTask(raidt.get(rn));
			raidt.remove(rn);
		}
		if(timet.containsKey(rn)) {
			Bukkit.getServer().getScheduler().cancelTask(timet.get(rn));
			timet.remove(rn);
		}
		if(timebar.get(rn) != null) {
			timebar.get(rn).removeAll();
			timebar.get(rn).setVisible(false);
		}

		if(raidbar.get(rn) != null) {
			raidbar.get(rn).removeAll();
			raidbar.get(rn).setVisible(false);
		}
		if(raider.containsKey(rn)) {
			raider.get(rn).forEach(re -> {
				if(Bukkit.getEntity(re) != null) {
					Bukkit.getEntity(re).remove();
	    		}
			});
			raider.removeAll(rn);
		}
		
		Location spl = raidloc.get(rn);
		
		
		lives.remove(rn);
		timeout.remove(rn);
		if(raidpor.containsKey(rn)) {
			if(Bukkit.getEntity(raidpor.get(rn)) !=null) {
				Bukkit.getEntity(raidpor.get(rn)).remove();
			}
		}

    	heroes.get(rn).forEach(pu -> {
    		Player p = Bukkit.getPlayer(pu);
    		if(factor ==0) {
        		p.sendTitle(ChatColor.BOLD +(ChatColor.DARK_GRAY + title), ChatColor.BOLD +sub, 5, 60, 5);
        		p.playSound(spl, Sound.ENTITY_WITCH_AMBIENT, 1, 0);
        		p.playSound(spl, Sound.ENTITY_RAVAGER_DEATH, 1, 0);
            	p.spawnParticle(Particle.VILLAGER_ANGRY, spl, 1000,6,6,6);
    		}
    		else {
        		String reg = p.getLocale().equalsIgnoreCase("ko_kr") ? "승리":"Victory!";
        		p.sendTitle(ChatColor.BOLD +(ChatColor.GOLD + reg), null, 5, 60, 5);
    			final Location lel = p.getLocation().clone().add(2, 2, 0);
            	for(int i =0; i<20; i++) {
             	   Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
 		                @Override
 		                public void run() 
 		                {
 		        			p.getWorld().spawnParticle(Particle.TOTEM, lel, 300,1,1,1);
 		        			p.getWorld().spawnParticle(Particle.HEART, lel, 100,1,1,1);
 		        			p.getWorld().spawnParticle(Particle.COMPOSTER, lel, 300,1,1,1);
 		        			p.getWorld().spawnParticle(Particle.WAX_ON, lel, 300,1,1,1);
 		        			p.getWorld().spawnParticle(Particle.GLOW, lel, 100,1,1,1);
 		        			p.getWorld().spawnParticle(Particle.SOUL_FIRE_FLAME, lel, 100,1,1,1);
 		        			p.getWorld().playEffect(lel, Effect.FIREWORK_SHOOT, 1);
 		                }
             	   }, i*5); 
 			}
         	
            	Elements.give(Elements.getscroll(p), 1, p);
            	p.spawnParticle(Particle.COMPOSTER, spl, 1000,6,6,6);
            	p.spawnParticle(Particle.HEART, spl, 1000,6,6,6);
            	
    		}
            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
                @Override
                public void run() {
					p.teleport(beforepl.get(p.getUniqueId()));
					beforepl.remove(p.getUniqueId());
					raidbart.remove(p.getName());
                }
            }, 160); 
    	});
		heroes.removeAll(rn);
        
	}
	
	


	public void Escape(PlayerQuitEvent ev) 
	{
		Player p = ev.getPlayer();
		if(heroes.containsValue(p.getUniqueId())) {
	
			if(Party.hasParty(p)) {
				if(Party.isOwner(p) == true) {
					if(p.getLocale().equalsIgnoreCase("ko_kr")) {
						RaidFinish(p.getName(), "탈주","파티장이 떠났습니다",0);
					}
					else {
						RaidFinish(p.getName(), "Escaped","Party Owner Left",0);
					}
				}
				else if(Party.isOwner(p) == false){
	        		heroes.remove(Party.getOwner(Party.getParty(p)).getName(), p.getUniqueId());
	        		p.teleport(beforepl.get(p.getUniqueId()));
	    			beforepl.remove(p.getUniqueId());
	    			return;
				}
			}
			else {
				if(p.getLocale().equalsIgnoreCase("ko_kr")) {
					RaidFinish(p.getName(), "탈주","파티장이 떠났습니다",0);
				}
				else {
					RaidFinish(p.getName(), "Escaped","Party Owner Left",0);
				}
			}
			
		}
	}
	public void TreasureRaidStart(EntityDamageByEntityEvent d) 
	{	
		if(! (d.getDamager() instanceof Player)) {
			return;
		}
		Player p = (Player) d.getDamager();
		if(d.getEntity().hasMetadata("TreasureRaidPortal") && p.isSneaking()&& p.getInventory().getItemInMainHand().getType().isAir()) {

				d.setCancelled(true);
				Holding.ale(d.getEntity()).remove();
				if(heroes.containsValue(p.getUniqueId())|| raider.containsKey(p.getName()) || beforepl.containsKey(p.getUniqueId()) || p.hasCooldown(Material.RAIL)) {
					return;
				}
				if(p.getInventory().firstEmpty() == -1) {
					if(p.getLocale().equalsIgnoreCase("ko_kr")) {
		            	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder("인벤토리에 빈칸이 없습니다").create());
					}
					else {
		            	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder("You should empty inventory least one space").create());
					}
					return;
				}
	    		World rw = Bukkit.getServer().getWorld("TreasureRaid");
	    		int fix = p.getEntityId()*50-29999900;
	    		int fiz = p.getEntityId()*50-29999900;
	    		if(fix >= 29999900) {
	    			fix = fix - 29999900*2;
	    			fiz = 29999900*2 - fiz;
	    		}
	    		for(int in = 0; in<20; in++) {
		    		if(rw.getHighestBlockAt(fix, fiz).getType().name().contains("LEAVES")) {
		    			fix++;
		    		}
		    		else {
		    			break;
		    		}
	    		}
	        	p.setCooldown(Material.RAIL, 100);
	    		Location spl = rw.getHighestBlockAt(fix, fiz).getLocation().clone().add(0, 1, 0);
	    		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	                @Override
	                public void run() {

	    	    		if(Party.hasParty(p)) {
	    					if(Party.isOwner(p)) {
	    						Party.getMembers(Party.getParty(p)).forEach(pu -> {
	    			        		heroes.put(p.getName(), pu);
	    						});
	    					}
	    				}
	    				else {
	    	        		heroes.put(p.getName(), p.getUniqueId());
	    				}
    		    		if(Party.hasParty(p)) {
    						if(Party.isOwner(p)) {
    							Party.getMembers(Party.getParty(p)).forEach(pu -> {
    								final Location pl = Bukkit.getPlayer(pu).getLocation();
    								beforepl.put(pu, pl);
    	            				p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 20,1,false,false));
    								Bukkit.getPlayer(pu).teleport(spl.clone().add(0,0.5,0));
    								Holding.invur(Bukkit.getPlayer(pu), 40l);
    								if(p.getLocale().equalsIgnoreCase("ko_kr")) {
	    				        		Bukkit.getPlayer(pu).sendTitle(ChatColor.GOLD + "보물창고에 들어왔습니다", "몬스터들을 모두 섬멸하십시오", 5, 69, 5);
    								}
    								else {
	    				        		Bukkit.getPlayer(pu).sendTitle(ChatColor.GOLD + "Entered Treasure Room", "Sweep All Monsters", 5, 69, 5);
    								}
    				            
    							});
    						}
    						else {
    							if(p.getLocale().equalsIgnoreCase("ko_kr")) {
	    							p.sendMessage("파티장만 가능합니다");
    							}
    							else {
	    							p.sendMessage("You Should Be Owner");
    							}
    							return;
    						}
    					}
    					else {
    						final Location pl = p.getLocation();
    						beforepl.put(p.getUniqueId(), pl);
    						p.teleport(spl.clone().add(0,0.5,0));
    						Holding.invur(p, 40l);
							if(p.getLocale().equalsIgnoreCase("ko_kr")) {
				        		p.sendTitle(ChatColor.GOLD + "보물창고에 들어왔습니다", "몬스터들을 모두 섬멸하십시오", 5, 69, 5);
							}
							else {
				        		p.sendTitle(ChatColor.GOLD + "Entered Treasure Room", "Sweep All Monsters", 5, 69, 5);
							}
    		        		p.playSound(spl, Sound.EVENT_RAID_HORN, 1, 1);
    					}
	    		        	p.setCooldown(Material.RAIL, 100);
	    	        		ArmorStand portal = (ArmorStand) spl.getWorld().spawn(spl, ArmorStand.class);
	    	        		portal.setMetadata("portal", new FixedMetadataValue(RMain.getInstance(), true));
	    	        		portal.setMetadata("TreasureRaidExit", new FixedMetadataValue(RMain.getInstance(), true));
	    	        		portal.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), true));
	    	        		portal.setRemoveWhenFarAway(false);
	    	        		portal.setGlowing(true);
							if(p.getLocale().equalsIgnoreCase("ko_kr")) {
		    	        		portal.setCustomName(p.getName() + "파티의 출구 (웅크린상태에서 맨손으로 가격)");
							}
							else {
		    	        		portal.setCustomName(p.getName() + "'s Party Exit (Sneaking + Hit with Fist)");
							}
	    	        		portal.setCustomNameVisible(true);
	                		portal.setCollidable(false);
	    	        		raidloc.put(p.getName(), spl);
	    	        		raidpor.put(p.getName(), portal.getUniqueId());
	    	        		
	        	    		lives.put(p.getName(), 1);
	    	        		timeout.put(p.getName(), 60);
	    	                int rat = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	    		                @Override
	    		                public void run() {

	    		            		for(int i =0; i <20; i++) {

	    		                    	Random random=new Random();
	    		                    	double number = (random.nextDouble()+1.5) * 1.5 * (random.nextBoolean() ? -1 : 1);
	    		                    	double number2 = (random.nextDouble()+1.5) * 1.5 * (random.nextBoolean() ? -1 : 1);
	    		                    	Location esl = spl.clone().add(number, 2.5, number2);
	    		                    	ItemStack head = new ItemStack(Material.GOLD_BLOCK);
	    		        				ItemStack chest = new ItemStack(Material.LEATHER_CHESTPLATE);
	    		        				LeatherArmorMeta chm = (LeatherArmorMeta) chest.getItemMeta();
	    		        				chm.setColor(Color.YELLOW);
	    		        				chest.setItemMeta(chm);
	    		        				chest.addUnsafeEnchantment(Enchantment.PROTECTION_FIRE, 5);
	    		        				chest.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
	    		        				chest.addUnsafeEnchantment(Enchantment.PROTECTION_PROJECTILE, 1);
	    		        				ItemStack leg = new ItemStack(Material.LEATHER_LEGGINGS);
	    		        				LeatherArmorMeta lem = (LeatherArmorMeta) leg.getItemMeta();
	    		        				lem.setColor(Color.YELLOW);
	    		        				leg.setItemMeta(lem);
	    		        				leg.addUnsafeEnchantment(Enchantment.PROTECTION_FIRE, 5);
	    		        				leg.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
	    		        				leg.addUnsafeEnchantment(Enchantment.PROTECTION_PROJECTILE, 1);
	    		        				ItemStack boots = new ItemStack(Material.LEATHER_BOOTS);
	    		        				LeatherArmorMeta bom = (LeatherArmorMeta) boots.getItemMeta();
	    		        				bom.setColor(Color.YELLOW);
	    		        				boots.setItemMeta(bom);
	    		        				boots.addUnsafeEnchantment(Enchantment.PROTECTION_FIRE, 5);
	    		        				boots.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
	    		        				boots.addUnsafeEnchantment(Enchantment.PROTECTION_PROJECTILE, 1);
	    		        	    		ItemStack main = new ItemStack(Material.GOLD_INGOT);
	    		    	        		String reg = p.getLocale().equalsIgnoreCase("ko_kr") ? "탐욕꾼":"Greedy";
	    		        	    		Skeleton newmob = (Skeleton) MobspawnLoc(esl, ChatColor.GOLD+reg, 100.0, head, chest, leg, boots, main, main, EntityType.SKELETON);
	    		        	    		newmob.getEquipment().setBootsDropChance(0);
	    		        	    		newmob.getEquipment().setChestplateDropChance(0);
	    		        	    		newmob.getEquipment().setHelmetDropChance(0);
	    		        	    		newmob.getEquipment().setItemInMainHandDropChance(0);
	    		        	    		newmob.getEquipment().setItemInOffHandDropChance(0);
	    		        	    		newmob.getEquipment().setLeggingsDropChance(0);
	    		        	    		newmob.setConversionTime(-1);
	    		        	    		newmob.setMetadata("wave1", new FixedMetadataValue(RMain.getInstance(), true));
	    		        	    		newmob.setMetadata("treasureraid", new FixedMetadataValue(RMain.getInstance(), p.getName()));
	    		        	    		newmob.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(0);
	    		        	    		newmob.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.01);
	    		        	    		newmob.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), true));
	    		        	    		newmob.setRemoveWhenFarAway(false);
	    		        	    		raider.put(p.getName(), newmob.getUniqueId());
	    		            		}

	    		                	heroes.get(p.getName()).forEach(pu -> {
	    		                		Player pa = Bukkit.getPlayer(pu);
	    								if(pa.getLocale().equalsIgnoreCase("ko_kr")) {
		    		                		pa.sendTitle(ChatColor.GRAY + "작전 시작", null, 5, 69, 5);
		    				        		pa.playSound(spl, Sound.EVENT_RAID_HORN, 1, 1);
	    								}
	    								else {
		    		                		pa.sendTitle(ChatColor.GRAY + "Hunting Start", null, 5, 69, 5);
		    				        		pa.playSound(spl, Sound.EVENT_RAID_HORN, 1, 1);
	    								}
	    		                	});
	    		        			String w = p.getLocale().equalsIgnoreCase("ko_kr") ? "남은 몬스터수":"Monsters";
	    	        	    		BossBar	newbar = Bukkit.getServer().createBossBar(new NamespacedKey(RMain.getInstance(), p.getName()+"TreasureRaid"),w, BarColor.YELLOW, BarStyle.SEGMENTED_20);
	    	        	            newbar.setVisible(true);
	    	        	    		raidbar.put(p.getName(), newbar);
	    	        	    		int task = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(RMain.getInstance(), new Runnable() {
	    	        	                @Override
	    	        	                public void run() 
	    	        	                {
	    	        	            		raidbar.get(p.getName()).setProgress((double)raider.get(p.getName()).size() / 20d);
	    	        	            		raidbar.get(p.getName()).setTitle(w + raider.get(p.getName()).size() + "/" + 20);
	    	        	            		heroes.get(p.getName()).forEach(pu -> {
	    	    	            				Player p1 = (Player) Bukkit.getPlayer(pu);
	    	    	            				if(p1 != null) {
	    	    	            					raidbar.get(p.getName()).addPlayer(p1);
	    	    	            				}
	    	        	            		});
	    	        	                }
	    	        				}, 1, 1);
	    	        	    		raidbart.put(p.getName(), task);


	    		        			String t = p.getLocale().equalsIgnoreCase("ko_kr") ? "남은시간 - ":"TimeLeft - ";
	    	        	    		BossBar	timeb = Bukkit.getServer().createBossBar(new NamespacedKey(RMain.getInstance(), p.getName()+"TreasureRaidTime"),t + String.valueOf((int)timeout.get(p.getName())/20/60) + ":" + String.valueOf((int)(timeout.get(p.getName())/20)%60), BarColor.WHITE, BarStyle.SEGMENTED_6);
	    	        	    		timeb.setVisible(true);
	    	        	    		timebar.put(p.getName(), timeb);
	    	        	    		int timetask = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(RMain.getInstance(), new Runnable() {
	    	        	                @Override
	    	        	                public void run() 
	    	        	                {
	    	        	            		timebar.get(p.getName()).setProgress((double)timeout.get(p.getName())/420d);
	    	        	            		timebar.get(p.getName()).setTitle(t +"[" + String.valueOf((int)timeout.get(p.getName())/60) + ":" + String.valueOf((int)(timeout.get(p.getName()))%60)+"]");
	    	        	            		heroes.get(p.getName()).forEach(pu -> {
	    	    	            				Player p1 = (Player) Bukkit.getPlayer(pu);
	    	    	            				if(p1 != null) {
	    		    	            				timebar.get(p.getName()).addPlayer(p1);
	    	    	            				}
	    	        	            		});
	    	        	            		timeout.computeIfPresent(p.getName(), (k,v) -> v-1);
	    	        	            		if(timeout.get(p.getName()) <=0) {
	    	        	            			String rn = p.getName();

	    	    								if(p.getLocale().equalsIgnoreCase("ko_kr")) {
		    	        	            			RaidFinish(rn,"종료..", "시간초과",0);
	    	    								}
	    	    								else {
		    	        	            			RaidFinish(rn,"Close..", "TimeOut",0);
	    	    								}
	    	        	                    	
	    	        	                    	spl.getWorld().spawnParticle(Particle.VILLAGER_ANGRY, spl, 1000,6,6,6);
	    	        	            		}
	    	        	                }
	    	        				}, 1, 20);
	    	        	    		timet.put(p.getName(), timetask);
	    		                }
	    		            }, DelayTime); 
	    	                raidt.put(p.getName(), rat);
	                }
	    		},5);
	    		
	    		
	                return;
	    		
	    		
		}
	}
	
	public void Victory(EntityDeathEvent d) 
	{		
		if(d.getEntity().hasMetadata("wave1") && raider.containsValue(d.getEntity().getUniqueId())) {
			LivingEntity le = d.getEntity();
			String rn = le.getMetadata("treasureraid").get(0).asString();
			raider.remove(rn, le.getUniqueId());
			if(raider.get(rn).size()<=0){
	        	RaidFinish(rn, "", "",1);
			}
		}
	}

	
	
	public void Defeat(PlayerDeathEvent ev) 
	{
		Player dp = ev.getEntity();
		if(heroes.containsValue(dp.getUniqueId())) {
			String rn = heroes.keySet().stream().filter(k -> heroes.containsEntry(k, dp.getUniqueId())).findFirst().get();
			lives.computeIfPresent(rn, (k,v) -> v-1);
        	heroes.get(rn).forEach(pu -> {
        		Bukkit.getPlayer(pu).sendMessage(ChatColor.BOLD + String.valueOf(lives.getOrDefault(rn, 0)) + "lives Left");
        	});
        	if(lives.getOrDefault(rn, 0)<=0) {

    			if(Bukkit.getPlayer(rn).getLocale().equalsIgnoreCase("ko_kr")) {
    				RaidFinish(rn, "종료", "모든 목숨 소진",0);
    			}
    			else {
                	RaidFinish(rn, "Close", "All Lives Exhausted", 0);
    			}
        	}
		}
	}
	
	
	public void TreasureRaidExit(EntityDamageByEntityEvent d) 
	{	
		if(d.getEntity().hasMetadata("TreasureRaidExit")) {
			d.setCancelled(true);
			if(d.getDamager() instanceof Player) {
	
				Player p = (Player) d.getDamager();
				if(!heroes.containsValue(p.getUniqueId()) || !p.getInventory().getItemInMainHand().getType().isAir()) {
					return;
				}
				if(p.isSneaking() && !p.isSprinting()) {
					if(Party.hasParty(p)) {
						if(Party.isOwner(p)) {
			    			if(p.getLocale().equalsIgnoreCase("ko_kr")) {
								RaidFinish(p.getName(), "복귀","",0);
			    			}
			    			else {
								RaidFinish(p.getName(), "Return","",0);
			    			}
						}
						else {
			    			if(p.getLocale().equalsIgnoreCase("ko_kr")) {
								p.sendMessage("파티장만 가능합니다");
			    			}
			    			else {
								p.sendMessage("You Should Be Owner");
			    			}
							return;
						}
					}
					else {
		    			if(p.getLocale().equalsIgnoreCase("ko_kr")) {
							RaidFinish(p.getName(), "복귀","",0);
		    			}
		    			else {
							RaidFinish(p.getName(), "Return","",0);
		    			}
					}
					
				}
				return;
			}
		}
	}

	
	public void TreasureRaidExit(PlayerJoinEvent ev) 
	{
		Player p = ev.getPlayer();
		
		if(!heroes.containsValue(p.getUniqueId()) && p.getWorld().getName().contains("Raid")) {
			p.teleport(p.getBedSpawnLocation());
		}
	}

	
	public void TreasureRaidExit(PluginEnableEvent ev) 
	{
		List<World> worlds = Bukkit.getServer().getWorlds();
		worlds.forEach(w -> w.getPlayers().forEach(b -> {
				Player p = (Player) b;
				if(!heroes.containsValue(p.getUniqueId()) && p.getWorld().getName().contains("Raid")) {
					p.teleport(p.getBedSpawnLocation());
				}
		}));
	}
	
	public void TreasureRaidExit(PluginDisableEvent ev) 
	{
		List<World> worlds = Bukkit.getServer().getWorlds();
		worlds.forEach(w -> w.getPlayers().forEach(b -> {
				Player p = (Player) b;
				if(beforepl.containsKey(p.getUniqueId())) {
					p.teleport(beforepl.get(p.getUniqueId()));
				}
		}));
		raider.clear();
		heroes.clear();
	}

	
	
	public void RaiderHeoresDam(EntityDamageByEntityEvent d) 
	{	
		if(d.getEntity().getWorld().getName().contains("Raid")) {
			if(d.getEntity() instanceof Player &&  heroes.containsValue(d.getEntity().getUniqueId()) && d.getDamager().hasMetadata("raid")) {
				
				String rn1 = heroes.keySet().stream().filter(k -> heroes.containsEntry(k, d.getEntity().getUniqueId())).findFirst().get();
				String rn2 = d.getDamager().getMetadata("raid").get(0).asString();
				if(rn1 != rn2) {
					d.setCancelled(true);
				}
			}
			if(d.getDamager() instanceof Projectile &&  d.getEntity() instanceof Player &&  heroes.containsValue(d.getEntity().getUniqueId())) {

				Projectile pr = (Projectile) d.getDamager();
				if(pr.getShooter() instanceof LivingEntity ) {
					LivingEntity le = (LivingEntity) pr.getShooter();
					if(le.hasMetadata("raid")) {
						String rn1 = heroes.keySet().stream().filter(k -> heroes.containsEntry(k, d.getEntity().getUniqueId())).findFirst().get();
						String rn2 = le.getMetadata("raid").get(0).asString();
						if(rn1 != rn2) {
							d.setCancelled(true);
						}
					}
				}
			}
			if(d.getDamager() instanceof Player &&  heroes.containsValue(d.getDamager().getUniqueId()) && d.getEntity().hasMetadata("raid")) {
				
				String rn1 = heroes.keySet().stream().filter(k -> heroes.containsEntry(k, d.getDamager().getUniqueId())).findFirst().get();
				String rn2 = d.getEntity().getMetadata("raid").get(0).asString();
				if(rn1 != rn2) {
					d.setCancelled(true);
				}
			}
			if(d.getDamager() instanceof Projectile &&  d.getEntity().hasMetadata("raid")) {

				Projectile pr = (Projectile) d.getDamager();
				if(pr.getShooter() instanceof Player ) {
					Player p = (Player) pr.getShooter();
					if(heroes.containsValue(p.getUniqueId())) {
						String rn1 = heroes.keySet().stream().filter(k -> heroes.containsEntry(k, p.getUniqueId())).findFirst().get();
						String rn2 = d.getEntity().getMetadata("raid").get(0).asString();
						if(rn1 != rn2) {
							d.setCancelled(true);
						}
					}
				}
			}
			if(d.getDamager() instanceof Player &&  heroes.containsValue(d.getDamager().getUniqueId()) && d.getEntity().hasMetadata("raidvil")) {
				d.setCancelled(true);
			}
			if(d.getDamager() instanceof Projectile &&  d.getEntity().hasMetadata("raidvil")) {

				Projectile pr = (Projectile) d.getDamager();
				if(pr.getShooter() instanceof Player ) {
					Player p = (Player) pr.getShooter();
					if(heroes.containsValue(p.getUniqueId())) {
						d.setCancelled(true);
					}
					
				}
			}
		}
	}

	
	public void RaidBack(PlayerRespawnEvent ev) 
	{
		Player p = ev.getPlayer();
		if(heroes.containsValue(p.getUniqueId())) {

            Bukkit.getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
                @Override
                public void run() {
        			if(Party.hasParty(p)) {
        				if(Party.isOwner(p)) {
        					Holding.invur(p, 40l);
        					p.teleport(raidloc.get(p.getName()));
        				}
        				else {
        					Holding.invur(p, 40l);
        					p.teleport(raidloc.get(Party.getOwner(Party.getParty(p)).getName()));
        				}
        			}
        			else {
    					Holding.invur(p, 40l);
        				p.teleport(raidloc.get(p.getName()));
        			}
                }
            }, 25); 
		}
	}
	

	public void Teleport(PlayerTeleportEvent e)
	{
		Player p = e.getPlayer();
		if(e.getTo().getWorld().getName().contains("TreasureRaid")) {
			if(!heroes.containsValue(p.getUniqueId())) {
				e.setCancelled(true);
			}
		}
		if(e.getFrom().getWorld().getName().contains("TreasureRaid") && !e.getTo().getWorld().getName().contains("TreasureRaid") && !e.getTo().getWorld().getName().contains("FakeDimension")) {
			if(heroes.containsValue(p.getUniqueId())) {
				e.setCancelled(true);
			}
		}
	}
	public void BlockPlace(BlockPlaceEvent d) 
	{
		Player p = d.getPlayer();
		if(heroes.containsValue(p.getUniqueId())|| raider.containsKey(p.getName()) || beforepl.containsKey(p.getUniqueId())) {
			d.setCancelled(true);
		}
	}

	public void BlockBreak(BlockBreakEvent d) 
	{
		Player p = d.getPlayer();
		if(heroes.containsValue(p.getUniqueId())|| raider.containsKey(p.getName()) || beforepl.containsKey(p.getUniqueId())) {
			d.setCancelled(true);
		}
	}
}
