package io.github.chw3021.obtains.quests;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.StructureType;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Evoker;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.entity.Vindicator;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerExpChangeEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.server.PluginDisableEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Merchant;
import org.bukkit.inventory.MerchantRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;

import io.github.chw3021.items.Elements;
import io.github.chw3021.items.Potions;
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


public class WoodlandMansionQuest extends Mobs implements Quest {

	private HashMap<UUID, Integer> asked = new HashMap<UUID, Integer>();
	private HashMap<UUID, Location> quested = new HashMap<UUID, Location>();
	private HashMap<UUID, Location> startloc = new HashMap<UUID, Location>();
	private HashMap<UUID, Integer> clearable = new HashMap<UUID, Integer>();

	private HashMap<UUID, Integer> qt = new HashMap<UUID, Integer>();
	private Integer st = 0;

	private ItemStack map = null;

	private static Integer count = 0;
	

	private static final WoodlandMansionQuest instance = new WoodlandMansionQuest ();
	public static WoodlandMansionQuest getInstance()
	{
		return instance;
	}
	
	
	private Location BlockFinder(Location l) {
		Location tl = l.clone();
		HashSet<Location> lhs = new HashSet<>();
		for(int ix = -40; ix<40; ix++) {
			for(int iy = -30; iy<30; iy++) {
				for(int iz = -40; iz<40; iz++) {
					lhs.add(tl.clone().add(ix, iy, iz));
				}
			}
		}
		for(Location dl : lhs) {
			if(dl.getBlock().getType() == Material.RED_CARPET || dl.getBlock().getType() == Material.WHITE_CARPET) {
				tl = dl;
				break;
			}
		}
		return tl;
	}
	
	final private void MobsSpawn(Location l) {

		count++;
		if(count>20) {
    		Bukkit.getScheduler().cancelTask(st);
			return;
		}
    	Random random=new Random();
    	double number = (random.nextDouble()+1.5) * 6 * (random.nextBoolean() ? -1 : 1);
    	double number2 = (random.nextDouble()+1.5) * 6 * (random.nextBoolean() ? -1 : 1);
    	Location esl = BlockFinder(l.clone().add(number, -25, number2)).clone().add(0, -19.5, 0);

		String reg = lang.contains("kr") ? "변명자":"Vindicator";
	    	Vindicator newmob = (Vindicator) MobspawnLoc(esl, reg, 6000.0, null, null, null, null, null, null, EntityType.VINDICATOR);
			newmob.setCanJoinRaid(false);
			newmob.setPatrolLeader(false);
			newmob.setPatrolTarget(null);
			newmob.getEquipment().setBootsDropChance(0);
			newmob.getEquipment().setChestplateDropChance(0);
			newmob.getEquipment().setHelmetDropChance(0);
			newmob.getEquipment().setItemInMainHandDropChance(0);
			newmob.getEquipment().setItemInOffHandDropChance(0);
			newmob.getEquipment().setLeggingsDropChance(0);
    		newmob.setMetadata("quest", new FixedMetadataValue(RMain.getInstance(),true));
			newmob.setMetadata("mansionquest", new FixedMetadataValue(RMain.getInstance(),true));
			newmob.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), true));
			newmob.setRemoveWhenFarAway(true);
			newmob.setLootTable(null);

			String reg1 = lang.contains("kr") ? "소환사":"Evoker";
			Evoker newmob1 = (Evoker) MobspawnLoc(esl, reg1, 5000.0, null, null, null, null, null, null, EntityType.EVOKER);
			newmob1.setCanJoinRaid(false);
			newmob1.setPatrolLeader(false);
			newmob1.setPatrolTarget(null);
			newmob1.getEquipment().setBootsDropChance(0);
			newmob1.getEquipment().setChestplateDropChance(0);
			newmob1.getEquipment().setHelmetDropChance(0);
			newmob1.getEquipment().setItemInMainHandDropChance(0);
			newmob1.getEquipment().setItemInOffHandDropChance(0);
			newmob1.getEquipment().setLeggingsDropChance(0);
    		newmob1.setMetadata("quest", new FixedMetadataValue(RMain.getInstance(),true));
			newmob1.setMetadata("mansionquest", new FixedMetadataValue(RMain.getInstance(),true));
			newmob1.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), true));
			newmob1.setRemoveWhenFarAway(true);
			newmob1.setLootTable(null);
	}
	
	private void QuestEnd(Player p, Integer factor) {
		if(qt.containsKey(p.getUniqueId())) {
			Bukkit.getScheduler().cancelTask(qt.get(p.getUniqueId()));
			qt.remove(p.getUniqueId());
		}
		if(clearable.containsKey(p.getUniqueId())) {
			clearable.remove(p.getUniqueId());
		}
		if(asked.containsKey(p.getUniqueId())) {
			asked.remove(p.getUniqueId());
		}
		if(startloc.containsKey(p.getUniqueId())) {
			startloc.remove(p.getUniqueId());
		}
		if(st != 0) {
    		Bukkit.getScheduler().cancelTask(st);
		}

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
    			p.sendTitle(ChatColor.GOLD + "퀘스트 완료!",ChatColor.GOLD + "삼림 대저택 전리품을 획득했습니다!",15,35,15);
            	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + "보안관과 거래를 할 수 있습니다").create());
		    }
    		else {
    			p.sendTitle(ChatColor.GOLD + "Complete Quest!",ChatColor.GOLD + "You Just Obtained Woodland Mansion Trophy!",15,35,15);
            	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + "Able To Trade With Sheriff").create());
    		}
    		Obtained.saver(p, 4, 1);
    		TrophyLoc.saver(p, quested.get(p.getUniqueId()));
        	Elements.give(Material.LAPIS_LAZULI, 5, p);
        	Elements.give(Material.EMERALD, 5, p);
        	Elements.give(Elements.getel(8, p),5, p);
			p.giveExp(100);
			Bukkit.getServer().getPluginManager().callEvent(new PlayerExpChangeEvent(p,100));
		}
		if(quested.containsKey(p.getUniqueId())) {
			quested.remove(p.getUniqueId());
		}
	}


	
	public void QuestStart(PlayerInteractEntityEvent d) 
	{	
		if(d.getRightClicked().hasMetadata("obnpc") && d.getRightClicked().hasMetadata("mansion")) {
			d.setCancelled(true);
				Villager le = (Villager)d.getRightClicked();
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
		            	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + le.getCustomName() + ": 고맙네.").create());
	        		}
	        		else {
		            	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + le.getCustomName() + ": Thank you.").create());
	        		}
	        		List<MerchantRecipe> mrl = new ArrayList<MerchantRecipe>();

	        		ArrayList<ItemStack> helia = new ArrayList<>();
	        		helia.add(new ItemStack(Material.EMERALD,17));
	        		helia.add(new ItemStack(Material.GOLD_INGOT,17));
	        		ArrayList<ItemStack> r = new ArrayList<>();
	        		r.add(new ItemStack(Material.EMERALD,6));
	        		r.add(new ItemStack(Material.LAPIS_LAZULI,6));
	        		ArrayList<ItemStack> poia = new ArrayList<>();
	        		poia.add(new ItemStack(Material.EMERALD,5));
	        		poia.add(new ItemStack(Material.LAPIS_LAZULI,5));
	        		ArrayList<ItemStack> poi = new ArrayList<>();
	        		poi.add(new ItemStack(Material.EMERALD,1));
	        		
	        		ArrayList<ItemStack> aa = new ArrayList<>();
	        		aa.add(new ItemStack(Material.EMERALD,3));
	        		
	        		MerchantRecipe mr1 = new MerchantRecipe(Potions.get(2, p), 1,64,true);
	        		mr1.setIngredients(poi);
	        		mrl.add(mr1);
	        		
	        		MerchantRecipe mr2 = new MerchantRecipe(Elements.getel(8, p), 1,64,true);
	        		mr2.setIngredients(poia);
	        		mrl.add(mr2);
	        		
	        		MerchantRecipe che = new MerchantRecipe(Chestplate.get(5, p), 1,64,true);
	        		che.setIngredients(helia);
	        		mrl.add(che);
	        		MerchantRecipe leg = new MerchantRecipe(Leggings.get(5, p), 1,64,true);
	        		leg.setIngredients(helia);
	        		mrl.add(leg);
	        		MerchantRecipe bt = new MerchantRecipe(Boots.get(5, p), 1,64,true);
	        		bt.setIngredients(helia);
	        		mrl.add(bt);
	        		MerchantRecipe hel = new MerchantRecipe(Helmet.get(5, p), 1,64,true);
	        		hel.setIngredients(helia);
	        		mrl.add(hel);
	        		
	        		MerchantRecipe mr5 = new MerchantRecipe(Elements.getpor(8,p), 1,64,true);
	        		mr5.setIngredients(r);
	        		mrl.add(mr5);

	        		if(map == null) {
		        		map = Bukkit.createExplorerMap(p.getWorld(), p.getLocation(),  StructureType.SWAMP_HUT,200,true);
		        		ItemMeta mapm = map.getItemMeta();
		        		

		        		if(p.getLocale().equalsIgnoreCase("ko_kr")) {
		        			mapm.setDisplayName(ChatColor.ITALIC+"늪지 오두막 지도");
					    }
		        		else {
		        			mapm.setDisplayName(ChatColor.ITALIC+"Swamp Hut Map");
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
					return;
				}
				
            	if(asked.containsKey(p.getUniqueId())) {
            		if(qt.containsKey(p.getUniqueId())) {
            			Bukkit.getScheduler().cancelTask(qt.get(p.getUniqueId()));
            			qt.remove(p.getUniqueId());
            		}
            		asked.remove(p.getUniqueId());
	        		if(p.getLocale().equalsIgnoreCase("ko_kr")) {
	                	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + le.getCustomName() + ": 고맙네. 대저택안에 있는 녀석들한테서 작전지도를 구해다 줄 수 있나?").create());
	                	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + le.getCustomName() + ": (사망, 종료, 우클릭시 또는 너무 멀리 가면 퀘스트가 취소됩니다.)").create());
				    }
	        		else {
	                	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + le.getCustomName() + ": Thanks. Could You Bring me the Operation Map From The guys in the mansion?").create());
	                	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + le.getCustomName() + ": (Quest Will be Canceled If You Die, Quit, RightClick or go far away)").create());
	        		}
	        		quested.put(p.getUniqueId(), NPCLoc.npcloc.get(le.getUniqueId()));
	        		startloc.put(p.getUniqueId(), le.getLocation().clone());

	        		
    	    		int task = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(RMain.getInstance(), new Runnable() {
    	                @Override
    	                public void run() 
    	                {
    	                	Location sl = startloc.get(p.getUniqueId());
    	                	if(p.getWorld() != sl.getWorld() || p.getLocation().distance(sl) >200) {
    	                		QuestEnd(p,0);
    	                	}
    	                	for(Entity e : sl.getWorld().getNearbyEntities(sl.clone(), 3,3,3)) {
    	                		if(e == p && clearable.containsKey(p.getUniqueId())) {
    	                			QuestEnd(p,4);
    	                		}
    	                	}
    	                	
    	                }
    				}, 0, 20);
    	    		qt.put(p.getUniqueId(), task);
    	    		
    	    		if(st != 0) {
        	    		Bukkit.getScheduler().cancelTask(st);
    	    		}
    	    		
    	    		int t1 = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(RMain.getInstance(), new Runnable() {
    	                @Override
    	                public void run() 
    	                {
    	                	Location sl = startloc.get(p.getUniqueId());
    	                	MobsSpawn(sl);
    	                }
    				}, 0, 20);
    	    		st = t1;
            	}
            	else {
    	        	p.setCooldown(Material.RAIL, 10);

	        		if(p.getLocale().equalsIgnoreCase("ko_kr")) {
	                	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + le.getCustomName() + ": 일손이 부족해..").create());
	                	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + "(우클릭시 수락)").create());
				    }
	        		else {
	                	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + le.getCustomName() + ": We are shorthanded..").create());
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
		if(d.getEntity().hasMetadata("mansionquest")) {
			LivingEntity le = d.getEntity();
			count--;
			if(le.getKiller() != null) {
				Player p = le.getKiller();
				if(quested.containsKey(p.getUniqueId()) && !clearable.containsKey(p.getUniqueId())) {
		    		Random random=new Random();
		        	int ri = random.nextInt(100);
		        	if(ri <= 20) {
		        		p.playSound(p.getLocation(), Sound.ENTITY_ITEM_PICKUP, 1, 1);
		        		if(p.getLocale().equalsIgnoreCase("ko_kr")) {
			        		p.sendTitle(ChatColor.BOLD + "작전 지도", ChatColor.BOLD + "(1/1)", 10, 20, 10);
					    }
		        		else {
			        		p.sendTitle(ChatColor.BOLD + "Operation Map", ChatColor.BOLD + "(1/1)", 10, 20, 10);
		        		}
		        		clearable.put(p.getUniqueId(), 1);
		        	}
				}
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