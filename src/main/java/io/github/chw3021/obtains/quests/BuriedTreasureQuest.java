package io.github.chw3021.obtains.quests;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Random;
import java.util.UUID;
import java.util.logging.Level;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Sound;
import org.bukkit.block.Chest;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Drowned;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Vex;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.PlayerExpChangeEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.server.PluginDisableEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Multimap;
import com.google.common.collect.Table;

import io.github.chw3021.commons.party.Party;
import io.github.chw3021.items.Elements;
import io.github.chw3021.items.Potions;
import io.github.chw3021.monsters.Mobs;
import io.github.chw3021.obtains.NPCsSpawn;
import io.github.chw3021.obtains.Obtained;
import io.github.chw3021.obtains.TrophyLoc;
import io.github.chw3021.rmain.RMain;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.ComponentBuilder;


public class BuriedTreasureQuest extends Mobs implements Serializable  {
	
	


	/**
	 * 
	 */
	private static transient final long serialVersionUID = 2096901131179551084L;

	public final Table<UUID, Location, ItemStack[]> chestlocinv;

	private HashMap<UUID, Integer> asked = new HashMap<UUID, Integer>();
	private HashMap<UUID, Location> quested = new HashMap<UUID, Location>();
	private HashMap<UUID, Location> startloc = new HashMap<UUID, Location>();
	
	private HashMap<UUID, Integer> qt = new HashMap<UUID, Integer>();
	private static Multimap<String, UUID> qmobs = ArrayListMultimap.create();
	private HashMap<String, Integer> qmobskill = new HashMap<String, Integer>();

	private HashMap<String, BossBar> qbar = new HashMap<String, BossBar>();
	private HashMap<String, Integer> qbart = new HashMap<String, Integer>();
	

	private static final BuriedTreasureQuest instance = new BuriedTreasureQuest (HashBasedTable.create());
	public static BuriedTreasureQuest getInstance()
	{
		return instance;
	}


    public BuriedTreasureQuest(Table<UUID, Location, ItemStack[]> chestlocinv) {
    	this.chestlocinv = chestlocinv;
    	}
    // Can be used for loading
    public BuriedTreasureQuest(BuriedTreasureQuest loadedData) {
    	this.chestlocinv = loadedData.chestlocinv;
   	}
 

	public BuriedTreasureQuest saveData(String filePath) {
        try {
            BukkitObjectOutputStream out = new BukkitObjectOutputStream(new GZIPOutputStream(new FileOutputStream(filePath)));
            out.writeObject(this);
            out.close();
            return this;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return this;
        }
    }
    public static BuriedTreasureQuest loadData(String filePath) {
        try {
            BukkitObjectInputStream in = new BukkitObjectInputStream(new GZIPInputStream(new FileInputStream(filePath)));
            BuriedTreasureQuest data = (BuriedTreasureQuest) in.readObject();
            in.close();
            return data;
        } catch (ClassNotFoundException | IOException | NullPointerException e ) {
            // TODO Auto-generated catch block


            String path = new File("").getAbsolutePath();
            BuriedTreasureQuest data = new BuriedTreasureQuest(HashBasedTable.create()).saveData(path +"/plugins/RPGskills/BuriedTreasureQuest.data");
            Bukkit.getServer().getLogger().log(Level.INFO, "Data Saved");
            e.printStackTrace();
            return data;
        }
    }
    
    final private BuriedTreasureQuest getdata(){
        String path = new File("").getAbsolutePath();
        BuriedTreasureQuest d = new BuriedTreasureQuest(BuriedTreasureQuest.loadData(path +"/plugins/RPGskills/BuriedTreasureQuest.data")) ;
		return d;
	}
    
    final private void save(Player p, Location l, Inventory inv) {
        String path = new File("").getAbsolutePath();
        Table<UUID, Location, ItemStack[]> chestlocinv = getdata().chestlocinv;
        chestlocinv.put(p.getUniqueId(), l, inv.getContents());
		new BuriedTreasureQuest(chestlocinv).saveData(path +"/plugins/RPGskills/BuriedTreasureQuest.data");
        return;
    }


    
    final private Boolean check(Player p) {
        Table<UUID, Location, ItemStack[]> chestlocinv = getdata().chestlocinv;
        return chestlocinv.contains(p, quested.get(p.getUniqueId()));
    }

    final private ItemStack[] getinv(Player p, Location l) {
        Table<UUID, Location, ItemStack[]> chestlocinv = getdata().chestlocinv;
		return chestlocinv.get(p.getUniqueId(), l);
    }
	
	public void Open(InventoryOpenEvent d)
	{	
		if(d.getInventory().getHolder() instanceof Chest) {
			Chest c = (Chest) d.getInventory().getHolder();
			Player p = (Player) d.getPlayer();
			if(c.getWorld().getNearbyEntities(c.getLocation(),2,5,2).stream().anyMatch(e -> e.hasMetadata("treasure"))) {
				LivingEntity le = (LivingEntity) c.getWorld().getNearbyEntities(c.getLocation(),2,5,2).stream().filter(e -> e.hasMetadata("treasure")).findFirst().get();
				if(TrophyLoc.getLocsdata().Locs.containsEntry(p.getUniqueId(), NPCsSpawn.npcloc.get(le.getUniqueId()))) {

					Location l = NPCsSpawn.npcloc.get(le.getUniqueId());
					Inventory ci = Bukkit.createInventory(p, c.getInventory().getSize(), p.getName() + "'s Buried Treasure");
					if(check(p)) {
						ci.setContents(getinv(p,l));
						p.openInventory(ci);
					}
					else {
						ci.setContents(c.getInventory().getContents());
						for(int i =0; i<ci.getSize(); i++) {
							if(ci.getItem(i).getType().name().contains("SALMON")) {
								ci.setItem(i, Potions.get(2, p));
							}
							else if(ci.getItem(i).getType().name().contains("COD")) {
								ci.setItem(i, new ItemStack(Material.LAPIS_LAZULI));
							}
							else if(ci.getItem(i).getType().name().contains("IRON")) {
								ci.setItem(i, Elements.getscroll(p));
							}
						}
						p.openInventory(ci);
						save(p,l,ci);
					}
					
				}
				else {
	        		if(p.getLocale().equalsIgnoreCase("ko_kr")) {
		            	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + "넌 아직 자격이 없다.").create());
	        		}
	        		else {
		            	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + "You don't deserve it yet").create());
	        		}
				}
				d.setCancelled(true);
			}
		}
	}
	
	public void Close(InventoryCloseEvent d) 
	{
		Inventory ci = d.getInventory();
		Player p = (Player) d.getPlayer();
		if(d.getView().getTitle().equals(p.getName() + "'s Buried Treasure")) {
			if(p.getLocation().getWorld().getNearbyEntities(p.getLocation(),3,5,3).stream().anyMatch(e -> e.hasMetadata("treasure"))) {
				LivingEntity le = (LivingEntity) p.getLocation().getWorld().getNearbyEntities(p.getLocation(),3,5,3).stream().filter(e -> e.hasMetadata("treasure")).findFirst().get();
				if(check(p)) {
					Location l = NPCsSpawn.npcloc.get(le.getUniqueId());
					save(p,l,ci);
				}
			}
		}
	}

	public void Place(BlockPlaceEvent d) 
	{
		if(d.getBlock().getState() instanceof Chest) {
			Chest c = (Chest) d.getBlock().getState();
			Player p = (Player) d.getPlayer();
			if(c.getWorld().getNearbyEntities(c.getLocation(),10,10,10).stream().anyMatch(e -> e.hasMetadata("treasure"))) {
				d.setCancelled(true);
        		if(p.getLocale().equalsIgnoreCase("ko_kr")) {
	            	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + "보물 근처에는 창고를 설치 할 수 없습니다").create());
        		}
        		else {
	            	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + "You can't Place Chest Near By Treasure").create());
        		}
			}
		}
	}

	public void Break(BlockBreakEvent d) 
	{
		if(d.getBlock().getState() instanceof Chest) {
			Chest c = (Chest) d.getBlock().getState();
			if(c.getWorld().getNearbyEntities(c.getLocation(),2,5,2).stream().anyMatch(e -> e.hasMetadata("treasure"))) {
				d.setCancelled(true);
			}
		}
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
    			p.sendTitle(ChatColor.BOLD + (ChatColor.GOLD + "퀘스트 완료!"),ChatColor.BOLD + (ChatColor.GOLD + "파묻힌 보물 전리품을 획득했습니다!"),15,35,15);
            	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + "보물상자를 열수 있습니다").create());
		    }
    		else {
    			p.sendTitle(ChatColor.GOLD + "Complete Quest!",ChatColor.GOLD + "You Just Obtained Buried Treasure Trophy!",15,35,15);
            	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + "Able To Open Treasure Chest").create());
    		}
    		Obtained.saver(p, 1, 1);
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
		ItemStack head = new ItemStack(Material.CHEST);
		ItemStack chest = new ItemStack(Material.LEATHER_CHESTPLATE);
		LeatherArmorMeta chm = (LeatherArmorMeta) chest.getItemMeta();
		chm.setColor(Color.TEAL);
		chest.setItemMeta(chm);
		ItemStack leg = new ItemStack(Material.LEATHER_LEGGINGS);
		LeatherArmorMeta lem = (LeatherArmorMeta) leg.getItemMeta();
		lem.setColor(Color.TEAL);
		leg.setItemMeta(lem);
		ItemStack boots = new ItemStack(Material.LEATHER_BOOTS);
		LeatherArmorMeta bom = (LeatherArmorMeta) boots.getItemMeta();
		bom.setColor(Color.TEAL);
		boots.setItemMeta(bom);
		ItemStack main = new ItemStack(Material.SOUL_LANTERN);

    	Random random=new Random();
    	double number = (random.nextDouble()+1.5) * 3 * (random.nextBoolean() ? -1 : 1);
    	double number2 = (random.nextDouble()+1.5) * 3 * (random.nextBoolean() ? -1 : 1);
    	Location esl = l.clone().add(number, -18, number2);
    	int factor = random.nextInt(2);
    	
    	p.playSound(p.getLocation(), Sound.BLOCK_RESPAWN_ANCHOR_SET_SPAWN, 0.1f, 2f);
    	
		if(factor ==0) {
			String reg = p.getLocale().equalsIgnoreCase("ko_kr") ? "보물망령 ("+p.getName()+")":"TreasureVex ("+p.getName()+")";
    		Vex newmob = (Vex) MobspawnLoc(esl, reg, p.getLevel()*30.0, head, chest, leg, boots, main, main, EntityType.VEX);
    		newmob.setCharging(true);
    		newmob.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 999999, 255, false, false));
    		newmob.getEquipment().setBootsDropChance(0);
    		newmob.getEquipment().setChestplateDropChance(0);
    		newmob.getEquipment().setHelmetDropChance(0);
    		newmob.getEquipment().setItemInMainHandDropChance(0);
    		newmob.getEquipment().setItemInOffHandDropChance(0);
    		newmob.getEquipment().setLeggingsDropChance(0);
    		newmob.setMetadata("quest", new FixedMetadataValue(RMain.getInstance(),true));
    		newmob.setMetadata("treasurequest", new FixedMetadataValue(RMain.getInstance(),p.getName()));
    		newmob.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), true));
    		newmob.setRemoveWhenFarAway(true);
    		newmob.setTarget(p);
    		newmob.setLootTable(null);
    		qmobs.put(p.getName(), newmob.getUniqueId());
		}
		else {
			String reg = p.getLocale().equalsIgnoreCase("ko_kr") ? "미믹 ("+p.getName()+")":"Mimic ("+p.getName()+")";
			Drowned newmob = (Drowned) MobspawnLoc(esl, reg, p.getLevel()*30.0, head, chest, leg, boots, main, main, EntityType.DROWNED);
    		newmob.setConversionTime(-1);
    		newmob.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 999999, 255, false, false));
    		newmob.getEquipment().setBootsDropChance(0);
    		newmob.getEquipment().setChestplateDropChance(0);
    		newmob.getEquipment().setHelmetDropChance(0);
    		newmob.getEquipment().setItemInMainHandDropChance(0);
    		newmob.getEquipment().setItemInOffHandDropChance(0);
    		newmob.getEquipment().setLeggingsDropChance(0);
    		newmob.setMetadata("quest", new FixedMetadataValue(RMain.getInstance(),true));
    		newmob.setMetadata("treasurequest", new FixedMetadataValue(RMain.getInstance(),p.getName()));
    		newmob.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), true));
    		newmob.setRemoveWhenFarAway(true);
    		newmob.setTarget(p);
    		newmob.setLootTable(null);
    		qmobs.put(p.getName(), newmob.getUniqueId());
		}
		
	}

	
	public void QuestStart(PlayerInteractEntityEvent d)
	{	
		if(d.getRightClicked().hasMetadata("obnpc") && d.getRightClicked().hasMetadata("treasure")) {
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
				if(!quested.isEmpty()) {
	        		if(p.getLocale().equalsIgnoreCase("ko_kr")) {
		            	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + "이미 다른 플레이어가 퀘스트를 진행중입니다.(퀘스트를 도와줄수 있습니다) ").create());
	        		}
	        		else {
		            	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + "You Should Wait(Or Assist) until Test End. ").create());
	        		}
					return;
				}
				if(TrophyLoc.getLocsdata().Locs.containsEntry(p.getUniqueId(), NPCsSpawn.npcloc.get(le.getUniqueId()))) {
	        		if(p.getLocale().equalsIgnoreCase("ko_kr")) {
		            	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + le.getCustomName() + ": 자격을 갖추었군.").create());
	        		}
	        		else {
		            	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + le.getCustomName() + ": You've been proven.").create());
	        		}
	        		p.setCooldown(Material.RAIL, 3);
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
	                	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + le.getCustomName() + ": 시험은 시작되었다.").create());
	                	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + le.getCustomName() + ": (사망, 종료시 또는 너무 멀리 가면 퀘스트가 취소됩니다.)").create());
				    }
	        		else {
	                	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + le.getCustomName() + ": Overcome the ordeal.").create());
	                	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + le.getCustomName() + ": (Quest Will be Canceled If You Die, Quit or go far away)").create());
	        		}
	        		quested.put(p.getUniqueId(), NPCsSpawn.npcloc.get(le.getUniqueId()));
	        		startloc.put(p.getUniqueId(), le.getLocation().clone());

    	    		BossBar	newbar = Bukkit.getServer().createBossBar(new NamespacedKey(RMain.getInstance(), p.getName()+"treasurequest"),"Killed Monsters: " + qmobskill.get(p.getName())  + "/" + 20, BarColor.BLUE, BarStyle.SEGMENTED_20, BarFlag.CREATE_FOG);
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
	                	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + le.getCustomName() + ": 새로운 도전자인가...").create());
	                	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + le.getCustomName() + ": 자격을 시험하겠다..").create());
	                	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + "(우클릭시 수락)").create());
				    }
	        		else {
	                	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + le.getCustomName() + ": New Challenger..").create());
	                	p.spigot().sendMessage(ChatMessageType.CHAT, new ComponentBuilder(ChatColor.BOLD + le.getCustomName() + ": Prove yourself..").create());
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
		if(d.getEntity().hasMetadata("treasurequest")) {
			Player p = Bukkit.getPlayerExact(d.getEntity().getMetadata("treasurequest").get(0).asString());
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
