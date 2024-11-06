package io.github.chw3021.obtains.quests;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Sound;
import org.bukkit.attribute.Attribute;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Vex;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerExpChangeEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.server.PluginDisableEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Merchant;
import org.bukkit.inventory.MerchantRecipe;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

import io.github.chw3021.items.Elements;
import io.github.chw3021.items.Potions;
import io.github.chw3021.items.armors.Armors;
import io.github.chw3021.items.armors.Boots;
import io.github.chw3021.items.armors.Chestplate;
import io.github.chw3021.items.armors.Helmet;
import io.github.chw3021.items.armors.Leggings;
import io.github.chw3021.monsters.Mobs;
import io.github.chw3021.obtains.NPCLoc;
import io.github.chw3021.obtains.Obtained;
import io.github.chw3021.obtains.TrophyLoc;
import io.github.chw3021.party.Party;
import io.github.chw3021.rmain.RMain;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.ComponentBuilder;


public class RuinedPortalQuest extends Mobs  implements Quest {
	
	private HashMap<UUID, Integer> asked = new HashMap<UUID, Integer>();
	private HashMap<UUID, Location> quested = new HashMap<UUID, Location>();
	private HashMap<UUID, Location> startloc = new HashMap<UUID, Location>();
	
	private HashMap<UUID, Integer> qt = new HashMap<UUID, Integer>();
	private static Multimap<String, UUID> qmobs = ArrayListMultimap.create();
	private HashMap<String, Integer> qmobskill = new HashMap<String, Integer>();

	private HashMap<String, BossBar> qbar = new HashMap<String, BossBar>();
	private HashMap<String, Integer> qbart = new HashMap<String, Integer>();
	
	final String meta = "ruinedportalquest";

	private static final RuinedPortalQuest instance = new RuinedPortalQuest ();
	public static RuinedPortalQuest getInstance()
	{
		return instance;
	}

	
	private void QuestEnd(Player p, Integer factor) {
		if(qt.containsKey(p.getUniqueId())) {
			Bukkit.getScheduler().cancelTask(qt.get(p.getUniqueId()));
			qt.remove(p.getUniqueId());
		}
		if(asked.containsKey(p.getUniqueId())) {
			asked.remove(p.getUniqueId());
		}
		if(startloc.containsKey(p.getUniqueId())) {
			startloc.remove(p.getUniqueId());
		}

		if(qbart.containsKey(p.getName())) {
			Bukkit.getServer().getScheduler().cancelTask(qbart.get(p.getName()));
			qbart.remove(p.getName());
		}
		if(qbar.get(p.getName()) != null) {
			qbar.get(p.getName()).removeAll();
			qbar.get(p.getName()).setVisible(false);
		}
		if(qmobs.containsKey(p.getName())) {
			qmobs.get(p.getName()).forEach(re -> {
				if(Bukkit.getEntity(re) != null) {
					Bukkit.getEntity(re).remove();
	    		}
			});
			qmobs.removeAll(p.getName());
		}
		qmobskill.remove(p.getName());
		
		if(factor ==0) {
    		if(p.getLocale().equalsIgnoreCase("ko_kr")) {
    			p.sendMessage("퀘스트가 취소되었습니다(거리)");
		    }
    		else {
    			p.sendMessage("Quest Canceled!(Too Far Away)");
    		}
		}
		else if (factor == 2) {
    		if(p.getLocale().equalsIgnoreCase("ko_kr")) {
    			p.sendMessage("퀘스트가 취소되었습니다(사망)");
		    }
    		else {
    			p.sendMessage("Quest Canceled!(Death)");
    		}
		}
		else if (factor == 3) {
    		if(p.getLocale().equalsIgnoreCase("ko_kr")) {
    			p.sendMessage("퀘스트가 취소되었습니다(포기)");
		    }
    		else {
    			p.sendMessage("Quest Canceled!(Given Up)");
    		}
		}
		else if (factor == 4) {
    		p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 1, 1);
    		if(p.getLocale().equalsIgnoreCase("ko_kr")) {
    			p.sendTitle(ChatColor.BOLD + (ChatColor.GOLD + "퀘스트 완료!"),ChatColor.BOLD + (ChatColor.GOLD + "무너진 차원문 전리품을 획득했습니다!"),15,35,15);
            	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + "사신과 거래를 할 수 있습니다").create());
		    }
    		else {
    			p.sendTitle(ChatColor.GOLD + "Complete Quest!",ChatColor.GOLD + "You Just Obtained Ruined Portal Trophy!",15,35,15);
            	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + "Able To Trade With Reaper").create());
    		}
    		Obtained.saver(p, 13, 1);
    		TrophyLoc.saver(p, quested.get(p.getUniqueId()));
        	Elements.give(Material.LAPIS_LAZULI, 15, p);
        	Elements.give(Material.EMERALD, 15, p);
			p.giveExp(200);
			Bukkit.getServer().getPluginManager().callEvent(new PlayerExpChangeEvent(p,200));
		}
		if(quested.containsKey(p.getUniqueId())) {
			quested.remove(p.getUniqueId());
		}
	}
	


	final private void MobsSpawn(Player p, Location l) {

		if(qmobs.containsKey(p.getName())) {
			if(qmobs.get(p.getName()).size()>25) {
				return;
			}
		}
    	Random random=new Random();
    	double number = (random.nextDouble()+1.5) * 2 * (random.nextBoolean() ? -1 : 1);
    	double number2 = (random.nextDouble()+1.5) * 2 * (random.nextBoolean() ? -1 : 1);
    	Location esl = l.clone().add(number, 5, number2);
    	
    	p.playSound(p.getLocation(), Sound.BLOCK_RESPAWN_ANCHOR_SET_SPAWN, 0.1f, 2f);

    	ItemStack head = new ItemStack(Material.CRYING_OBSIDIAN);
    	head.addUnsafeEnchantment(Enchantment.PROJECTILE_PROTECTION, 1);
    	
    	ItemStack main = new ItemStack(Material.NETHERITE_SWORD);
    	head.addUnsafeEnchantment(Enchantment.KNOCKBACK, 1);

    	ItemStack off = new ItemStack(Material.SHIELD);
    	head.addUnsafeEnchantment(Enchantment.KNOCKBACK, 1);
    	
		String reg = p.getLocale().equalsIgnoreCase("ko_kr") ? "차원문 혼령 ("+p.getName()+")":"Portal Spirit ("+p.getName()+")";
		Vex newmob = (Vex) MobspawnLoc(esl, reg, p.getLevel()*250.0, head, null, null, null, main, off, EntityType.VEX);
		newmob.setCharging(true);
		newmob.getAttribute(Attribute.KNOCKBACK_RESISTANCE).setBaseValue(1);
		newmob.getAttribute(Attribute.SCALE).setBaseValue(2.5);
		newmob.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, 999999, 4, false, false));
		newmob.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 999999, 4, false, false));
		newmob.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 999999, 4, false, false));
		newmob.getEquipment().setBootsDropChance(0);
		newmob.getEquipment().setChestplateDropChance(0);
		newmob.getEquipment().setHelmetDropChance(0);
		newmob.getEquipment().setItemInMainHandDropChance(0);
		newmob.getEquipment().setItemInOffHandDropChance(0);
		newmob.getEquipment().setLeggingsDropChance(0);
		newmob.setMetadata("quest", new FixedMetadataValue(RMain.getInstance(),true));
		newmob.setMetadata(meta, new FixedMetadataValue(RMain.getInstance(),p.getName()));
		newmob.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), true));
		newmob.setRemoveWhenFarAway(true);
		newmob.setTarget(p);
		newmob.setLootTable(null);
		qmobs.put(p.getName(), newmob.getUniqueId());
		
	}

	private void openMerchant(Player p, LivingEntity le) {

		List<MerchantRecipe> mrl = new ArrayList<MerchantRecipe>();

		ArrayList<ItemStack> armorIngredients = new ArrayList<>();
		ItemStack stel = Elements.getstel(12, p);
		stel.setAmount(64);
		armorIngredients.add(stel);
		armorIngredients.add(stel);
		
		ArrayList<ItemStack> poia = new ArrayList<>();
		poia.add(new ItemStack(Material.EMERALD,15));
		poia.add(new ItemStack(Material.GOLD_INGOT,15));
		
		ArrayList<ItemStack> aa = new ArrayList<>();
		aa.add(new ItemStack(Material.EMERALD,64));
		aa.add(new ItemStack(Material.GOLD_INGOT,64));
		
		MerchantRecipe mr1 = new MerchantRecipe(Potions.get(7, p), 1,64,true);
		mr1.setIngredients(poia);
		mrl.add(mr1);
		
		MerchantRecipe mr2 = new MerchantRecipe(Helmet.get(7, p), 1,64,true);
		mr2.setIngredients(aa);
		mrl.add(mr2);
		
		MerchantRecipe mr3 = new MerchantRecipe(Chestplate.get(7, p), 1,64,true);
		mr3.setIngredients(aa);
		mrl.add(mr3);

		MerchantRecipe mr4 = new MerchantRecipe(Leggings.get(7, p), 1,64,true);
		mr4.setIngredients(aa);
		mrl.add(mr4);
		
		MerchantRecipe mr5 = new MerchantRecipe(Boots.get(7, p), 1,64,true);
		mr5.setIngredients(aa);
		mrl.add(mr5);

		MerchantRecipe mr6 = new MerchantRecipe(Armors.acArmor(0, p), 1,64,true);
		mr6.setIngredients(armorIngredients);
		mrl.add(mr6);

		MerchantRecipe mr7 = new MerchantRecipe(Armors.acArmor(1, p), 1,64,true);
		mr7.setIngredients(armorIngredients);
		mrl.add(mr7);

		MerchantRecipe mr8 = new MerchantRecipe(Armors.acArmor(2, p), 1,64,true);
		mr8.setIngredients(armorIngredients);
		mrl.add(mr8);
		
		MerchantRecipe mr9 = new MerchantRecipe(Armors.acArmor(3, p), 1,64,true);
		mr9.setIngredients(armorIngredients);
		mrl.add(mr9);
		
		Merchant mi = Bukkit.createMerchant(le.getCustomName());
		mi.setRecipes(mrl);
		p.openMerchant(mi, true);
		
		p.setCooldown(Material.RAIL, 3);
		p.setCooldown(Material.RAIL, 3);
	}
	
	public void QuestStart(PlayerInteractEntityEvent d)
	{	
		if(d.getRightClicked().hasMetadata("obnpc") && d.getRightClicked().hasMetadata("ruinedportal")) {
			d.setCancelled(true);
				LivingEntity le = (LivingEntity)d.getRightClicked();
				Player p = (Player) d.getPlayer();
				if(p.hasCooldown(Material.RAIL)) {
					return;
				}
        		p.setCooldown(Material.RAIL, 10);
				if(quested.containsKey(p.getUniqueId())) {
		    		QuestEnd(p,3);
					return;
				}
				if(TrophyLoc.getLocsdata().Locs.containsEntry(p.getUniqueId(), NPCLoc.npcloc.get(le.getUniqueId()))) {
	        		if(p.getLocale().equalsIgnoreCase("ko_kr")) {
		            	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + le.getCustomName() + ": 넌 자격이 있다.").create());
	        		}
	        		else {
		            	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + le.getCustomName() + ": You're worthy").create());
	        		}
					return;
				}

				if(Party.hasParty(p)) {
	        		if(p.getLocale().equalsIgnoreCase("ko_kr")) {
		            	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + "파티에 속한 상태로는 진행이 불가능합니다").create());
	        		}
	        		else {
		            	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + "You should leave Your Party").create());
	        		}
					return;
				}
				if(p.getInventory().firstEmpty() == -1) {
	        		if(p.getLocale().equalsIgnoreCase("ko_kr")) {
		            	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + "인벤토리에 빈칸이 최소 한칸은 있어야 합니다").create());
	        		}
	        		else {
		            	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + "You should empty inventory least one space").create());
	        		}
	        		openMerchant(p, le);
					return;
				}
				
            	if(asked.containsKey(p.getUniqueId())) {
            		if(qt.containsKey(p.getUniqueId())) {
            			Bukkit.getScheduler().cancelTask(qt.get(p.getUniqueId()));
            			qt.remove(p.getUniqueId());
            		}
            		asked.remove(p.getUniqueId());
	        		if(p.getLocale().equalsIgnoreCase("ko_kr")) {
	                	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + le.getCustomName() + ": 시험은 시작되었다..").create());
	                	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + le.getCustomName() + ": (사망, 종료시 또는 너무 멀리 가면 퀘스트가 취소됩니다.)").create());
				    }
	        		else {
	                	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + le.getCustomName() + ": Test is just begun..").create());
	                	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + le.getCustomName() + ": (Quest Will be Canceled If You Die, Quit or go far away)").create());
	        		}
	        		quested.put(p.getUniqueId(), NPCLoc.npcloc.get(le.getUniqueId()));
	        		startloc.put(p.getUniqueId(), le.getLocation().clone());

    	    		BossBar	newbar = Bukkit.getServer().createBossBar(new NamespacedKey(RMain.getInstance(), p.getName()+"ruinedportalquest"),"Killed Monsters: " + qmobskill.get(p.getName())  + "/" + 20, BarColor.PURPLE, BarStyle.SEGMENTED_20, BarFlag.DARKEN_SKY);
    	            newbar.setVisible(true);
    	            qbar.put(p.getName(), newbar);
    	    		int btask = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(RMain.getInstance(), new Runnable() {
    	                @Override
    	                public void run() 
    	                {
    	            		qbar.get(p.getName()).setProgress((double)qmobskill.getOrDefault(p.getName(),0) / 20d);
    		        		if(p.getLocale().equalsIgnoreCase("ko_kr")) {
        	            		qbar.get(p.getName()).setTitle("처치 몬스터 수: " + qmobskill.getOrDefault(p.getName(),0) + "/" + 20);
    					    }
    		        		else {
        	            		qbar.get(p.getName()).setTitle("Killed Monsters: " + qmobskill.getOrDefault(p.getName(),0)  + "/" + 20);
    		        		}
    	            		qbar.get(p.getName()).addPlayer(p);
    	                }
    				}, 0, 5);
    	    		qbart.put(p.getName(), btask);
    	    		
    	    		int task = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(RMain.getInstance(), new Runnable() {
    	                @Override
    	                public void run() 
    	                {
    	                	Location sl = startloc.get(p.getUniqueId());
    	                	if(p.getWorld() != sl.getWorld() || p.getLocation().distance(sl) >60) {
    	                		QuestEnd(p,0);
    	                	}
    	                	MobsSpawn(p,sl);
    	                	
    	                }
    				}, 0, 20);
    	    		qt.put(p.getUniqueId(), task);
            	}
            	else {
    	        	p.setCooldown(Material.RAIL, 10);

	        		if(p.getLocale().equalsIgnoreCase("ko_kr")) {
	                	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + le.getCustomName() + ": 새로운 도전자인가..").create());
	                	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + "(우클릭시 수락)").create());
				    }
	        		else {
	                	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + le.getCustomName() + ": New challenger..").create());
	                	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + "(RightClick To Accept)").create());
	        		}
                	asked.put(p.getUniqueId(), 1);
                	
                	int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
    	                @Override
    	                public void run() 
    	                {
    	                	asked.remove(p.getUniqueId());
    	                }
    				}, 100);
    	    		qt.put(p.getUniqueId(), task);
            	}
            	
			
		}
	}

	public void QuestClear(EntityDeathEvent d) 
	{
		if(d.getEntity().hasMetadata(meta)) {
			Player p = Bukkit.getPlayerExact(d.getEntity().getMetadata(meta).get(0).asString());
			qmobskill.computeIfPresent(p.getName(), (k,v) -> v+1);
			qmobskill.putIfAbsent(p.getName(), 1);
			if(qmobskill.getOrDefault(p.getName(), 0) >= 20) {
				QuestEnd(p,4);
			}
		}
	}

	
	public void GiveUp(PlayerQuitEvent ev) 
	{
		Player p = ev.getPlayer();
		if(quested.containsKey(p.getUniqueId())) {
    		QuestEnd(p,1);
		}
	}

	
	public void Failed(PlayerDeathEvent ev) 
	{
		Player p = ev.getEntity();
		if(quested.containsKey(p.getUniqueId())) {
    		QuestEnd(p,2);
		}
	}
	
	
	public void Reset(PluginDisableEvent ev) 
	{
		Bukkit.getOnlinePlayers().forEach(p -> {

			if(quested.containsKey(p.getUniqueId())) {
	    		QuestEnd(p,3);
			}
		});
	}



	
}