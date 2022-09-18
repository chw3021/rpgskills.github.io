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
import org.bukkit.StructureType;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Phantom;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerExpChangeEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.server.PluginDisableEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Merchant;
import org.bukkit.inventory.MerchantRecipe;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

import io.github.chw3021.commons.party.Party;
import io.github.chw3021.items.Elements;
import io.github.chw3021.items.Potions;
import io.github.chw3021.items.armors.Boots;
import io.github.chw3021.items.armors.Helmet;
import io.github.chw3021.monsters.Mobs;
import io.github.chw3021.obtains.NPCsSpawn;
import io.github.chw3021.obtains.Obtained;
import io.github.chw3021.obtains.TrophyLoc;
import io.github.chw3021.rmain.RMain;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.ComponentBuilder;


public class ShipwreckQuest extends Mobs implements Listener  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4390525757690769363L;
	private HashMap<UUID, Integer> asked = new HashMap<UUID, Integer>();
	private HashMap<UUID, Location> quested = new HashMap<UUID, Location>();
	private HashMap<UUID, Location> startloc = new HashMap<UUID, Location>();
	
	private HashMap<UUID, Integer> qt = new HashMap<UUID, Integer>();
	private static Multimap<String, UUID> qmobs = ArrayListMultimap.create();
	private HashMap<String, Integer> qmobskill = new HashMap<String, Integer>();

	private HashMap<String, BossBar> qbar = new HashMap<String, BossBar>();
	private HashMap<String, Integer> qbart = new HashMap<String, Integer>();
	
	
	private ItemStack map = null;
	

	private static final ShipwreckQuest instance = new ShipwreckQuest ();
	public static ShipwreckQuest getInstance()
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
    			p.sendMessage("����Ʈ�� ��ҵǾ����ϴ�(�Ÿ�)");
		    }
    		else {
    			p.sendMessage("Quest Canceled!(Too Far Away)");
    		}
		}
		else if (factor == 2) {
    		if(p.getLocale().equalsIgnoreCase("ko_kr")) {
    			p.sendMessage("����Ʈ�� ��ҵǾ����ϴ�(���)");
		    }
    		else {
    			p.sendMessage("Quest Canceled!(Death)");
    		}
		}
		else if (factor == 3) {
    		if(p.getLocale().equalsIgnoreCase("ko_kr")) {
    			p.sendMessage("����Ʈ�� ��ҵǾ����ϴ�(����)");
		    }
    		else {
    			p.sendMessage("Quest Canceled!(Given Up)");
    		}
		}
		else if (factor == 4) {
    		p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 1, 1);
    		if(p.getLocale().equalsIgnoreCase("ko_kr")) {
    			p.sendTitle(ChatColor.BOLD + (ChatColor.GOLD + "����Ʈ �Ϸ�!"),ChatColor.BOLD + (ChatColor.GOLD + "���ļ� ����ǰ�� ȹ���߽��ϴ�!"),15,35,15);
            	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + "�ͻ��� ������ �ŷ��� �� �� �ֽ��ϴ�").create());
		    }
    		else {
    			p.sendTitle(ChatColor.GOLD + "Complete Quest!",ChatColor.GOLD + "You Just Obtained Shipwreck Trophy!",15,35,15);
            	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + "Able To Trade With Drowned Sailor").create());
    		}
    		Obtained.saver(p, 5, 1);
    		TrophyLoc.saver(p, quested.get(p.getUniqueId()));
        	Elements.give(Material.LAPIS_LAZULI, 5, p);
        	Elements.give(Material.EMERALD, 5, p);
			p.giveExp(150);
			Bukkit.getServer().getPluginManager().callEvent(new PlayerExpChangeEvent(p,150));
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
    	double number = (random.nextDouble()+1.5) * 3 * (random.nextBoolean() ? -1 : 1);
    	double number2 = (random.nextDouble()+1.5) * 3 * (random.nextBoolean() ? -1 : 1);
    	Location esl = l.clone().add(number, -18, number2);
    	
    	p.playSound(p.getLocation(), Sound.BLOCK_RESPAWN_ANCHOR_SET_SPAWN, 0.1f, 2f);

		String reg = p.getLocale().equalsIgnoreCase("ko_kr") ? "���ļ� ���� ("+p.getName()+")":"Ship Phantom ("+p.getName()+")";
		Phantom newmob = (Phantom) MobspawnLoc(esl, reg, p.getLevel()*45.0, null, null, null, null, null, null, EntityType.PHANTOM);
		newmob.setSize(10);
		newmob.addPotionEffect(new PotionEffect(PotionEffectType.CONDUIT_POWER, 999999, 4, false, false));
		newmob.addPotionEffect(new PotionEffect(PotionEffectType.WATER_BREATHING, 999999, 4, false, false));
		newmob.addPotionEffect(new PotionEffect(PotionEffectType.DOLPHINS_GRACE, 999999, 4, false, false));
		newmob.getEquipment().setBootsDropChance(0);
		newmob.getEquipment().setChestplateDropChance(0);
		newmob.getEquipment().setHelmetDropChance(0);
		newmob.getEquipment().setItemInMainHandDropChance(0);
		newmob.getEquipment().setItemInOffHandDropChance(0);
		newmob.getEquipment().setLeggingsDropChance(0);
		newmob.setMetadata("quest", new FixedMetadataValue(RMain.getInstance(),true));
		newmob.setMetadata("shipwreckquest", new FixedMetadataValue(RMain.getInstance(),p.getName()));
		newmob.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), true));
		newmob.setRemoveWhenFarAway(true);
		newmob.setTarget(p);
		newmob.setLootTable(null);
		qmobs.put(p.getName(), newmob.getUniqueId());
		
	}

	
	public void QuestStart(PlayerInteractEntityEvent d)
	{	
		if(d.getRightClicked().hasMetadata("obnpc") && d.getRightClicked().hasMetadata("shipwreck")) {
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
				if(TrophyLoc.getLocsdata().Locs.containsEntry(p.getUniqueId(), NPCsSpawn.npcloc.get(le.getUniqueId()))) {
	        		if(p.getLocale().equalsIgnoreCase("ko_kr")) {
		            	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + le.getCustomName() + ": ������!").create());
	        		}
	        		else {
		            	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + le.getCustomName() + ": Thanks!").create());
	        		}
	        		List<MerchantRecipe> mrl = new ArrayList<MerchantRecipe>();

	        		ArrayList<ItemStack> poia = new ArrayList<>();
	        		poia.add(new ItemStack(Material.EMERALD,1));
	        		ArrayList<ItemStack> aa = new ArrayList<>();
	        		aa.add(new ItemStack(Material.EMERALD,5));
	        		aa.add(new ItemStack(Material.GOLD_INGOT,5));
	        		
	        		MerchantRecipe mr1 = new MerchantRecipe(Potions.get(2, p), 1,64,true);
	        		mr1.setIngredients(poia);
	        		mrl.add(mr1);
	        		
					ItemStack enchbook = new ItemStack(Material.ENCHANTED_BOOK);
					EnchantmentStorageMeta enchmeta = (EnchantmentStorageMeta) enchbook.getItemMeta();
					enchmeta.addStoredEnchant(Enchantment.DEPTH_STRIDER, 10, true);
	        		if(p.getLocale().equalsIgnoreCase("ko_kr")) {
	        			enchmeta.setDisplayName("������ 10LV");
				    }
	        		else {
	        			enchmeta.setDisplayName("Depth Strider 10LV");
	        		}
	        		enchbook.setItemMeta(enchmeta);
					
	        		MerchantRecipe mr3 = new MerchantRecipe(Helmet.get(2, p), 1,64,true);
	        		mr3.setIngredients(aa);
	        		mrl.add(mr3);

	        		MerchantRecipe mr5 = new MerchantRecipe(Boots.get(2, p), 1,64,true);
	        		mr5.setIngredients(aa);
	        		mrl.add(mr5);

	        		if(map == null) {
		        		map = Bukkit.createExplorerMap(p.getWorld(), p.getLocation(),  StructureType.BURIED_TREASURE,200,true);
		        		ItemMeta mapm = map.getItemMeta();
		        		

		        		if(p.getLocale().equalsIgnoreCase("ko_kr")) {
		        			mapm.setDisplayName(ChatColor.ITALIC+"���� ����");
					    }
		        		else {
		        			mapm.setDisplayName(ChatColor.ITALIC+"Treasure Map");
		        		}
		        		map.setItemMeta(mapm);
		        		MerchantRecipe mr4 = new MerchantRecipe(map, 1,64,true);
		        		mr4.setIngredients(aa);
		        		mrl.add(mr4);
		        		
	        		}
	        		else {
		        		MerchantRecipe mr4 = new MerchantRecipe(map, 1,64,true);
		        		mr4.setIngredients(aa);
		        		mrl.add(mr4);
	        		}
	        		Merchant mi = Bukkit.createMerchant(le.getCustomName());
	        		mi.setRecipes(mrl);
	        		p.openMerchant(mi, true);
	        		
	        		p.setCooldown(Material.RAIL, 3);
	        		p.setCooldown(Material.RAIL, 3);
					return;
				}

				if(Party.hasParty(p)) {
	        		if(p.getLocale().equalsIgnoreCase("ko_kr")) {
		            	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + "��Ƽ�� ���� ���·δ� ������ �Ұ����մϴ�").create());
	        		}
	        		else {
		            	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + "You should leave Your Party").create());
	        		}
					return;
				}
				if(p.getInventory().firstEmpty() == -1) {
	        		if(p.getLocale().equalsIgnoreCase("ko_kr")) {
		            	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + "�κ��丮�� ��ĭ�� �ּ� ��ĭ�� �־�� �մϴ�").create());
	        		}
	        		else {
		            	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + "You should empty inventory least one space").create());
	        		}
					return;
				}
				
            	if(asked.containsKey(p.getUniqueId())) {
            		if(qt.containsKey(p.getUniqueId())) {
            			Bukkit.getScheduler().cancelTask(qt.get(p.getUniqueId()));
            			qt.remove(p.getUniqueId());
            		}
            		asked.remove(p.getUniqueId());
	        		if(p.getLocale().equalsIgnoreCase("ko_kr")) {
	                	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + le.getCustomName() + ": �����մϴ�. �װ� ������ ���͵��� �������� ���� ���׿�..").create());
	                	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + le.getCustomName() + ": (���, ����� �Ǵ� �ʹ� �ָ� ���� ����Ʈ�� ��ҵ˴ϴ�.)").create());
				    }
	        		else {
	                	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + le.getCustomName() + ": Thanks So Much. Even after I die, phantoms continue to bully me..").create());
	                	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + le.getCustomName() + ": (Quest Will be Canceled If You Die, Quit or go far away)").create());
	        		}
	        		quested.put(p.getUniqueId(), NPCsSpawn.npcloc.get(le.getUniqueId()));
	        		startloc.put(p.getUniqueId(), le.getLocation().clone());

    	    		BossBar	newbar = Bukkit.getServer().createBossBar(new NamespacedKey(RMain.getInstance(), p.getName()+"shipwreckquest"),"Killed Monsters: " + qmobskill.get(p.getName())  + "/" + 20, BarColor.BLUE, BarStyle.SEGMENTED_20, BarFlag.CREATE_FOG);
    	            newbar.setVisible(true);
    	            qbar.put(p.getName(), newbar);
    	    		int btask = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(RMain.getInstance(), new Runnable() {
    	                @Override
    	                public void run() 
    	                {
    	            		qbar.get(p.getName()).setProgress((double)qmobskill.getOrDefault(p.getName(),0) / 20d);
    		        		if(p.getLocale().equalsIgnoreCase("ko_kr")) {
        	            		qbar.get(p.getName()).setTitle("óġ ���� ��: " + qmobskill.getOrDefault(p.getName(),0) + "/" + 20);
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
	                	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + le.getCustomName() + ": ����.. ��ġ�ڴ�!..").create());
	                	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + "(��Ŭ���� ����)").create());
				    }
	        		else {
	                	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + le.getCustomName() + ": They're Driving Me Crazy!..").create());
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
		if(d.getEntity().hasMetadata("shipwreckquest")) {
			Player p = Bukkit.getPlayerExact(d.getEntity().getMetadata("shipwreckquest").get(0).asString());
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
