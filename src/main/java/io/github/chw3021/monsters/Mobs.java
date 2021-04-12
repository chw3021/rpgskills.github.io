package io.github.chw3021.monsters;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.attribute.Attribute;
import org.bukkit.block.Biome;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.AnimalTamer;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.ElderGuardian;
import org.bukkit.entity.EnderDragon;
import org.bukkit.entity.Enderman;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityCategory;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Guardian;
import org.bukkit.entity.IronGolem;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Mob;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.Skeleton;
import org.bukkit.entity.SkeletonHorse;
import org.bukkit.entity.Snowman;
import org.bukkit.entity.Villager;
import org.bukkit.entity.Villager.Type;
import org.bukkit.entity.Witch;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.server.PluginDisableEvent;
import org.bukkit.event.world.ChunkLoadEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.jetbrains.annotations.Nullable;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

import io.github.chw3021.classes.ClassData;
import io.github.chw3021.hunter.HunSkillsData;
import io.github.chw3021.paladin.PalSkillsData;
import io.github.chw3021.party.PartyData;
import io.github.chw3021.rmain.RMain;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.ComponentBuilder;


public class Mobs implements Listener {

	static private HashMap<UUID, Integer> bosst = new HashMap<UUID, Integer>();
	private static HashMap<UUID, BossBar> bossbar = new HashMap<UUID, BossBar>();
	private static Multimap<UUID, UUID> bossbarplayer = ArrayListMultimap.create();
	private static HashMap<Integer, UUID> redboss = new HashMap<Integer, UUID>();
	private static HashMap<Integer, UUID> snowboss = new HashMap<Integer, UUID>();
	private static HashMap<Integer, UUID> stoneboss = new HashMap<Integer, UUID>();
	private static HashMap<Integer, UUID> iceboss = new HashMap<Integer, UUID>();

	@Nullable
	public static LivingEntity Mobspawn(LivingEntity le, @Nullable String name, Double health, @Nullable ItemStack head, @Nullable ItemStack chest, @Nullable ItemStack leg, @Nullable ItemStack boots, @Nullable ItemStack main, @Nullable ItemStack off, @Nullable EntityType type) {
		LivingEntity creature = (LivingEntity) le.getWorld().spawnEntity(le.getLocation(), type);
		creature.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), true));
		creature.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(health);
		creature.setHealth(health);
		creature.setCustomName(name);
		if(head != null) {
			creature.getEquipment().setHelmet(head);
		}
		if(chest != null) {
			creature.getEquipment().setChestplate(chest);
		}
		if(leg != null) {
			creature.getEquipment().setLeggings(leg);
		}
		if(boots != null) {
			creature.getEquipment().setBoots(boots);
		}
		if(main != null) {
			creature.getEquipment().setItemInMainHand(main);
		}
		if(off != null) {
			creature.getEquipment().setItemInOffHand(off);
		}
		le.remove();
		return creature;
	}
	
	@EventHandler
	public void Mobspawn(CreatureSpawnEvent ev) {
		if(!ev.getEntity().hasMetadata("rpgspawned") && !ev.getEntity().hasMetadata("untargetable") && !ev.getEntity().hasMetadata("fake") && !(ev.getEntity() instanceof Player) && ev.getEntity() instanceof LivingEntity && ev.getSpawnReason() != SpawnReason.CUSTOM&& ev.getSpawnReason() != SpawnReason.SHOULDER_ENTITY&& ev.getSpawnReason() != SpawnReason.TRAP) {

        	LivingEntity le = ev.getEntity();
        	if(ev.getEntityType() == EntityType.WITHER) {
        		ev.getEntity().setMaxHealth(500000);
        		ev.getEntity().setHealth(500000);
        	}
        	else if(ev.getEntityType() == EntityType.ENDER_DRAGON) {
        		ev.getEntity().setMaxHealth(200000);
        		ev.getEntity().setHealth(200000);
        	}
        	else {

            	if(le.getLocation().getBlock().getBiome().name().contains("MOUNTAINS") && !le.getLocation().getBlock().getBiome().name().contains("TAIGA")&& !le.getLocation().getBlock().getBiome().name().contains("SNOWY")) {
            		if(ev.getEntity().getCategory() == EntityCategory.UNDEAD ) {
    	        		Random random=new Random();
    	            	int ri = random.nextInt(5);
    	            	if(ri == 0) {
    	        			ItemStack head = new ItemStack(Material.STONE);
    	        			ItemStack chest = new ItemStack(Material.LEATHER_CHESTPLATE);
	        				LeatherArmorMeta chm = (LeatherArmorMeta) chest.getItemMeta();
	        				chm.setColor(Color.GRAY);
	        				chest.setItemMeta(chm);
    	        			ItemStack leg = new ItemStack(Material.LEATHER_LEGGINGS);
	        				LeatherArmorMeta lem = (LeatherArmorMeta) leg.getItemMeta();
	        				lem.setColor(Color.GRAY);
	        				leg.setItemMeta(lem);
    	        			ItemStack boots = new ItemStack(Material.LEATHER_BOOTS);
	        				LeatherArmorMeta bom = (LeatherArmorMeta) boots.getItemMeta();
	        				bom.setColor(Color.GRAY);
	        				boots.setItemMeta(bom);
    	            		LivingEntity newmob = Mobspawn(le, "WalkingStone", 1200.0, head, chest, leg, boots, new ItemStack(Material.STONE), new ItemStack(Material.STONE), EntityType.ZOMBIE);
    	            		newmob.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), true));
    	            	}
    	            	else if(ri == 1) {
    	        			ItemStack head = new ItemStack(Material.GRAVEL);
    	        			ItemStack chest = new ItemStack(Material.LEATHER_CHESTPLATE);
	        				LeatherArmorMeta chm = (LeatherArmorMeta) chest.getItemMeta();
	        				chm.setColor(Color.GRAY);
	        				chest.setItemMeta(chm);
    	        			ItemStack leg = new ItemStack(Material.LEATHER_LEGGINGS);
	        				LeatherArmorMeta lem = (LeatherArmorMeta) leg.getItemMeta();
	        				lem.setColor(Color.GRAY);
	        				leg.setItemMeta(lem);
    	        			ItemStack boots = new ItemStack(Material.LEATHER_BOOTS);
	        				LeatherArmorMeta bom = (LeatherArmorMeta) boots.getItemMeta();
	        				bom.setColor(Color.GRAY);
	        				boots.setItemMeta(bom);
    	            		ItemStack main = new ItemStack(Material.BOW);
    	            		main.addUnsafeEnchantment(Enchantment.ARROW_DAMAGE, 3);
    	            		LivingEntity newmob = Mobspawn(le, "GravelArcher", 700.0, head, chest, leg, boots, main, null, EntityType.STRAY);
    	            		newmob.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), true));
    	            	}
    	            	else if(ri == 2) {
    	        			ItemStack head = new ItemStack(Material.OAK_LEAVES);
    	        			ItemStack chest = new ItemStack(Material.LEATHER_CHESTPLATE);
	        				LeatherArmorMeta chm = (LeatherArmorMeta) chest.getItemMeta();
	        				chm.setColor(Color.OLIVE);
	        				chest.setItemMeta(chm);
    	        			ItemStack leg = new ItemStack(Material.LEATHER_LEGGINGS);
	        				LeatherArmorMeta lem = (LeatherArmorMeta) leg.getItemMeta();
	        				chm.setColor(Color.OLIVE);
	        				leg.setItemMeta(lem);
    	        			ItemStack boots = new ItemStack(Material.LEATHER_BOOTS);
	        				LeatherArmorMeta bom = (LeatherArmorMeta) boots.getItemMeta();
	        				chm.setColor(Color.OLIVE);
	        				boots.setItemMeta(bom);
    	            		ItemStack main = new ItemStack(Material.CROSSBOW);
    	            		main.addUnsafeEnchantment(Enchantment.QUICK_CHARGE, 2);
    	            		main.addUnsafeEnchantment(Enchantment.MULTISHOT, 1);
    	            		LivingEntity newmob = Mobspawn(le, "WoodenSniper", 600.0, head, leg, chest, boots, main,null, EntityType.PILLAGER);
    	            		newmob.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 999999, 1, false, false));
    	            		newmob.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 999999, 1, false, false));
    	            		newmob.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), true));
    	            	}
    	            	else if (ri == 3){
    		            	int bri = random.nextInt(2);
    		            	if(bri == 1 && !stoneboss.containsKey(1)) {
        	        			ItemStack head = new ItemStack(Material.LEATHER_HELMET);
    	        				LeatherArmorMeta hem = (LeatherArmorMeta) head.getItemMeta();
    	        				head.setItemMeta(hem);
        	        			ItemStack chest = new ItemStack(Material.LEATHER_CHESTPLATE);
    	        				LeatherArmorMeta chm = (LeatherArmorMeta) chest.getItemMeta();
    	        				chm.setColor(Color.RED);
    	        				chest.setItemMeta(chm);
    	        				chest.addUnsafeEnchantment(Enchantment.PROTECTION_FIRE, 5);
    	        				chest.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
    	        				chest.addUnsafeEnchantment(Enchantment.PROTECTION_PROJECTILE, 1);
        	        			ItemStack leg = new ItemStack(Material.LEATHER_LEGGINGS);
    	        				LeatherArmorMeta lem = (LeatherArmorMeta) leg.getItemMeta();
    	        				lem.setColor(Color.RED);
    	        				leg.setItemMeta(lem);
    	        				leg.addUnsafeEnchantment(Enchantment.PROTECTION_FIRE, 5);
    	        				leg.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
    	        				leg.addUnsafeEnchantment(Enchantment.PROTECTION_PROJECTILE, 1);
        	        			ItemStack boots = new ItemStack(Material.LEATHER_BOOTS);
    	        				LeatherArmorMeta bom = (LeatherArmorMeta) boots.getItemMeta();
    	        				bom.setColor(Color.WHITE);
    	        				boots.setItemMeta(bom);
    	        				boots.addUnsafeEnchantment(Enchantment.PROTECTION_FIRE, 5);
    	        				boots.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
    	        				boots.addUnsafeEnchantment(Enchantment.PROTECTION_PROJECTILE, 1);
    		            		ItemStack main = new ItemStack(Material.STONE);
    		            		main.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 3);
    		            		ItemStack off = new ItemStack(Material.STONE);
    		            		off.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 3);
    		            		IronGolem newmob = (IronGolem) Mobspawn(le, ChatColor.GRAY+"StoneGolem", 20000.0, null, null, null, boots, main, off, EntityType.IRON_GOLEM);
    		            		newmob.setGlowing(true);
    		            		newmob.getEquipment().setBootsDropChance(0);
    		            		newmob.getEquipment().setChestplateDropChance(0);
    		            		newmob.getEquipment().setHelmetDropChance(0);
    		            		newmob.getEquipment().setItemInMainHandDropChance(0);
    		            		newmob.getEquipment().setItemInOffHandDropChance(0);
    		            		newmob.getEquipment().setLeggingsDropChance(0);
    		            		newmob.setMetadata("stoneboss", new FixedMetadataValue(RMain.getInstance(), true));
    		            		newmob.setMetadata("boss", new FixedMetadataValue(RMain.getInstance(), true));
    		            		newmob.setPlayerCreated(false);
    		            		newmob.getServer().broadcastMessage(ChatColor.GRAY+"StoneGolem spawned at " + newmob.getLocation().toString());
    		            		BossBar	newbar = Bukkit.getServer().createBossBar(new NamespacedKey(RMain.getInstance(), "StoneGolem"),newmob.getName(), BarColor.WHITE, BarStyle.SEGMENTED_20, BarFlag.CREATE_FOG);
    		                    newbar.setVisible(true);
    		            		bossbar.put(newmob.getUniqueId(), newbar);
    		            		int task = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(RMain.getInstance(), new Runnable() {
    				                @Override
    				                public void run() 
    				                {
    										HashSet<Player> remove = new HashSet<Player>();
    				                		newbar.setProgress(newmob.getHealth() / newmob.getMaxHealth());
    				                		newbar.setTitle(newmob.getName());
    				                		newmob.getNearbyEntities(25, 25, 25).forEach(en -> {
    				                			if(en instanceof Player) {
    				                				Player p = (Player) en;
    				                				if(!bossbarplayer.containsValue(p)) {
    				                					bossbarplayer.put(newmob.getUniqueId(), p.getUniqueId());
        				                				newbar.addPlayer(p);
    				                				}
    				                			}
    				                		});
    				                		if(bossbarplayer.containsKey((LivingEntity)newmob)) {
    				                			bossbarplayer.get(newmob.getUniqueId()).forEach(p -> {
    				                				if(Bukkit.getPlayer(p).getLocation().distance(newmob.getLocation()) >25) {
    				                					newbar.removePlayer(Bukkit.getPlayer(p));
    				                					remove.add(Bukkit.getPlayer(p));
    				                				}
    				                			});
        				                		remove.forEach(p ->bossbarplayer.remove(newmob.getUniqueId(), p.getUniqueId()));
    				                		}
    				                }
    							}, 1, 5);
    		            		bosst.put(newmob.getUniqueId(), task);
    		            		stoneboss.put(1, (newmob.getUniqueId()));
    		            		newmob.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.3);
    		            		newmob.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE).setBaseValue(1);
    		            		newmob.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), true));
    		            		newmob.setRemoveWhenFarAway(false);
    		            	}
    		            	else {
    		            		ItemStack head = new ItemStack(Material.GRAVEL);
        	        			ItemStack chest = new ItemStack(Material.LEATHER_CHESTPLATE);
    	        				LeatherArmorMeta chm = (LeatherArmorMeta) chest.getItemMeta();
    	        				chm.setColor(Color.GRAY);
    	        				chest.setItemMeta(chm);
        	        			ItemStack leg = new ItemStack(Material.LEATHER_LEGGINGS);
    	        				LeatherArmorMeta lem = (LeatherArmorMeta) leg.getItemMeta();
    	        				lem.setColor(Color.GRAY);
    	        				leg.setItemMeta(lem);
        	        			ItemStack boots = new ItemStack(Material.LEATHER_BOOTS);
    	        				LeatherArmorMeta bom = (LeatherArmorMeta) boots.getItemMeta();
    	        				bom.setColor(Color.GRAY);
    	        				boots.setItemMeta(bom);
    		            		ItemStack main = new ItemStack(Material.GRAVEL);
    		            		main.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 3);
    		            		LivingEntity newmob = Mobspawn(le, "GravelMage", 600.0, head, chest, leg, boots, main, main, EntityType.SKELETON);
    		            		newmob.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.35);
    		            		newmob.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), true));
    		            	}
    	            	}
    	            	else {
    	            		LivingEntity newmob = Mobspawn(le, "Mountain" + le.getName(), 750.0, le.getEquipment().getHelmet(), le.getEquipment().getChestplate(), le.getEquipment().getLeggings(), le.getEquipment().getBoots(), le.getEquipment().getItemInMainHand(), le.getEquipment().getItemInOffHand(), le.getType());
    	            		newmob.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), true));
    	            	}
    	        	}
            		else if(ev.getEntity().getCategory() == EntityCategory.ARTHROPOD) {
	            		LivingEntity newmob = Mobspawn(le, "StoneSpider", 600.0,null, null, null, null, null, null, EntityType.SPIDER);
	            		newmob.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.25);
	            		newmob.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), true));
    	        	}
            		else if(ev.getEntity().getType() == EntityType.CREEPER) {
    	        		
	            		LivingEntity newmob = Mobspawn(le, "WoodCreeper", 700.0, null, null, null, null, null, null, EntityType.CREEPER);
	            		newmob.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.18);
	            		newmob.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), true));
    	            	
    	        	}
            		else if(ev.getEntity().getType() == EntityType.ENDERMAN) {
	            		Enderman newmob = (Enderman) Mobspawn(le, "WoodMan", 800.0, null, null, null, null, null, null, EntityType.ENDERMAN);
	            		
	            		newmob.setCarriedBlock(Material.ICE.createBlockData());
	            		newmob.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.18);
	            		newmob.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), true));
    	        	}
            		else {
    	            		LivingEntity newmob = Mobspawn(le, "Mountain" + le.getName(), 750.0, le.getEquipment().getHelmet(), le.getEquipment().getChestplate(), le.getEquipment().getLeggings(), le.getEquipment().getBoots(), le.getEquipment().getItemInMainHand(), le.getEquipment().getItemInOffHand(), le.getType());
    	            		newmob.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), true));
            		}
    	        }

            	else if(le.getLocation().getBlock().getBiome().name().contains("SNOWY") || le.getLocation().getBlock().getBiome().name().contains("ICE")|| le.getLocation().getBlock().getBiome().name().contains("FROZEN")) {
            		if(ev.getEntity().getCategory() == EntityCategory.UNDEAD ) {
    	        		Random random=new Random();
    	            	int ri = random.nextInt(5);
    	            	if(ri == 0) {
    	        			ItemStack head = new ItemStack(Material.ICE);
    	        			ItemStack chest = new ItemStack(Material.LEATHER_CHESTPLATE);
	        				LeatherArmorMeta chm = (LeatherArmorMeta) chest.getItemMeta();
	        				chm.setColor(Color.WHITE);
	        				chest.setItemMeta(chm);
    	        			ItemStack leg = new ItemStack(Material.LEATHER_LEGGINGS);
	        				LeatherArmorMeta lem = (LeatherArmorMeta) leg.getItemMeta();
	        				lem.setColor(Color.WHITE);
	        				leg.setItemMeta(lem);
    	        			ItemStack boots = new ItemStack(Material.LEATHER_BOOTS);
	        				LeatherArmorMeta bom = (LeatherArmorMeta) boots.getItemMeta();
	        				bom.setColor(Color.WHITE);
	        				boots.setItemMeta(bom);
    	            		LivingEntity newmob = Mobspawn(le, "Iceman", 3300.0, head, chest, leg, boots, new ItemStack(Material.SNOWBALL), new ItemStack(Material.SNOWBALL), EntityType.DROWNED);
    	            		newmob.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), true));
    	            	}
    	            	else if(ri == 1) {
    	        			ItemStack head = new ItemStack(Material.LEATHER_HELMET);
	        				LeatherArmorMeta hem = (LeatherArmorMeta) head.getItemMeta();
	        				hem.setColor(Color.WHITE);
	        				head.setItemMeta(hem);
    	        			ItemStack chest = new ItemStack(Material.LEATHER_CHESTPLATE);
	        				LeatherArmorMeta chm = (LeatherArmorMeta) chest.getItemMeta();
	        				chm.setColor(Color.WHITE);
	        				chest.setItemMeta(chm);
    	        			ItemStack leg = new ItemStack(Material.LEATHER_LEGGINGS);
	        				LeatherArmorMeta lem = (LeatherArmorMeta) leg.getItemMeta();
	        				lem.setColor(Color.WHITE);
	        				leg.setItemMeta(lem);
    	        			ItemStack boots = new ItemStack(Material.LEATHER_BOOTS);
	        				LeatherArmorMeta bom = (LeatherArmorMeta) boots.getItemMeta();
	        				bom.setColor(Color.WHITE);
	        				boots.setItemMeta(bom);
    	            		ItemStack main = new ItemStack(Material.BOW);
    	            		main.addUnsafeEnchantment(Enchantment.ARROW_KNOCKBACK, 1);
    	            		main.addUnsafeEnchantment(Enchantment.ARROW_DAMAGE, 3);
    	            		LivingEntity newmob = Mobspawn(le, "FrostSkull", 3000.0, head, chest, leg, boots, main, null, EntityType.STRAY);
    	            		newmob.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), true));
    	            	}
    	            	else if(ri == 2) {
    	        			ItemStack head = new ItemStack(Material.LEATHER_HELMET);
	        				LeatherArmorMeta hem = (LeatherArmorMeta) head.getItemMeta();
	        				hem.setColor(Color.NAVY);
	        				head.setItemMeta(hem);
    	        			ItemStack chest = new ItemStack(Material.LEATHER_CHESTPLATE);
	        				LeatherArmorMeta chm = (LeatherArmorMeta) chest.getItemMeta();
	        				chm.setColor(Color.NAVY);
	        				chest.setItemMeta(chm);
    	        			ItemStack leg = new ItemStack(Material.LEATHER_LEGGINGS);
	        				LeatherArmorMeta lem = (LeatherArmorMeta) leg.getItemMeta();
	        				lem.setColor(Color.NAVY);
	        				leg.setItemMeta(lem);
    	        			ItemStack boots = new ItemStack(Material.LEATHER_BOOTS);
	        				LeatherArmorMeta bom = (LeatherArmorMeta) boots.getItemMeta();
	        				bom.setColor(Color.NAVY);
	        				boots.setItemMeta(bom);
    	            		ItemStack main = new ItemStack(Material.DIAMOND_AXE);
    	            		main.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 2);
    	            		LivingEntity newmob = Mobspawn(le, "FrostHunter", 2000.0, head, null, null, boots, main,null, EntityType.STRAY);
    	            		newmob.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 999999, 1, false, false));
    	            		newmob.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 999999, 1, false, false));
    	            		newmob.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), true));
    	            	}
    	            	else if (ri == 3){
    		            	int bri = random.nextInt(2);
    		            	if(bri == 1 && !snowboss.containsKey(1)) {
        	        			ItemStack head = new ItemStack(Material.LEATHER_HELMET);
    	        				LeatherArmorMeta hem = (LeatherArmorMeta) head.getItemMeta();
    	        				head.setItemMeta(hem);
        	        			ItemStack chest = new ItemStack(Material.LEATHER_CHESTPLATE);
    	        				LeatherArmorMeta chm = (LeatherArmorMeta) chest.getItemMeta();
    	        				chm.setColor(Color.RED);
    	        				chest.setItemMeta(chm);
    	        				chest.addUnsafeEnchantment(Enchantment.PROTECTION_FIRE, 5);
    	        				chest.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
    	        				chest.addUnsafeEnchantment(Enchantment.PROTECTION_PROJECTILE, 1);
        	        			ItemStack leg = new ItemStack(Material.LEATHER_LEGGINGS);
    	        				LeatherArmorMeta lem = (LeatherArmorMeta) leg.getItemMeta();
    	        				lem.setColor(Color.RED);
    	        				leg.setItemMeta(lem);
    	        				leg.addUnsafeEnchantment(Enchantment.PROTECTION_FIRE, 5);
    	        				leg.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
    	        				leg.addUnsafeEnchantment(Enchantment.PROTECTION_PROJECTILE, 1);
        	        			ItemStack boots = new ItemStack(Material.LEATHER_BOOTS);
    	        				LeatherArmorMeta bom = (LeatherArmorMeta) boots.getItemMeta();
    	        				bom.setColor(Color.WHITE);
    	        				boots.setItemMeta(bom);
    	        				boots.addUnsafeEnchantment(Enchantment.PROTECTION_FIRE, 5);
    	        				boots.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
    	        				boots.addUnsafeEnchantment(Enchantment.PROTECTION_PROJECTILE, 1);
    		            		ItemStack main = new ItemStack(Material.STICK);
    		            		main.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 3);
    		            		ItemStack off = new ItemStack(Material.ICE);
    		            		off.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 3);
    		            		Witch newmob = (Witch) Mobspawn(le, ChatColor.BLUE+"FrostWitch", 25000.0, null, null, null, boots, main, off, EntityType.WITCH);
    		            		newmob.setGlowing(true);
    		            		newmob.getEquipment().setBootsDropChance(0);
    		            		newmob.getEquipment().setChestplateDropChance(0);
    		            		newmob.getEquipment().setHelmetDropChance(0);
    		            		newmob.getEquipment().setItemInMainHandDropChance(0);
    		            		newmob.getEquipment().setItemInOffHandDropChance(0);
    		            		newmob.getEquipment().setLeggingsDropChance(0);
    		            		newmob.setMetadata("snowboss", new FixedMetadataValue(RMain.getInstance(), true));
    		            		newmob.setMetadata("boss", new FixedMetadataValue(RMain.getInstance(), true));
    		            		newmob.setCanJoinRaid(false);
    		            		newmob.setPatrolTarget(null);
    		            		newmob.setPatrolLeader(false);
    		            		newmob.getServer().broadcastMessage(ChatColor.BLUE+"FrostWitch spawned at " + newmob.getLocation().toString());
    		            		BossBar	newbar = Bukkit.getServer().createBossBar(new NamespacedKey(RMain.getInstance(), "FrostWitch"),newmob.getName(), BarColor.WHITE, BarStyle.SEGMENTED_20, BarFlag.DARKEN_SKY);
    		                    newbar.setVisible(true);
    		            		bossbar.put(newmob.getUniqueId(), newbar);
    		            		int task = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(RMain.getInstance(), new Runnable() {
    				                @Override
    				                public void run() 
    				                {
										HashSet<Player> remove = new HashSet<Player>();
				                		newbar.setProgress(newmob.getHealth() / newmob.getMaxHealth());
				                		newbar.setTitle(newmob.getName());
				                		newmob.getNearbyEntities(25, 25, 25).forEach(en -> {
				                			if(en instanceof Player) {
				                				Player p = (Player) en;
				                				if(!bossbarplayer.containsValue(p)) {
				                					bossbarplayer.put(newmob.getUniqueId(), p.getUniqueId());
    				                				newbar.addPlayer(p);
				                				}
				                			}
				                		});
				                		if(bossbarplayer.containsKey((LivingEntity)newmob)) {
				                			bossbarplayer.get(newmob.getUniqueId()).forEach(p -> {
				                				if(Bukkit.getPlayer(p).getLocation().distance(newmob.getLocation()) >25) {
				                					newbar.removePlayer(Bukkit.getPlayer(p));
				                					remove.add(Bukkit.getPlayer(p));
				                				}
				                			});
    				                		remove.forEach(p ->bossbarplayer.remove(newmob.getUniqueId(), p.getUniqueId()));
				                		}
    				                }
    							}, 1, 5);
    		            		bosst.put(newmob.getUniqueId(), task);
    		            		snowboss.put(1, newmob.getUniqueId());
    		            		newmob.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.3);
    		            		newmob.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE).setBaseValue(0.9);
        	            		newmob.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 999999, 3, false, false));
    		            		newmob.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), true));
    		            		newmob.setRemoveWhenFarAway(false);
    		            	}
    		            	else {
    		            		ItemStack head = new ItemStack(Material.LEATHER_HELMET);
    	        				LeatherArmorMeta hem = (LeatherArmorMeta) head.getItemMeta();
    	        				hem.setColor(Color.WHITE);
    	        				head.setItemMeta(hem);
        	        			ItemStack chest = new ItemStack(Material.LEATHER_CHESTPLATE);
    	        				LeatherArmorMeta chm = (LeatherArmorMeta) chest.getItemMeta();
    	        				chm.setColor(Color.WHITE);
    	        				chest.setItemMeta(chm);
        	        			ItemStack leg = new ItemStack(Material.LEATHER_LEGGINGS);
    	        				LeatherArmorMeta lem = (LeatherArmorMeta) leg.getItemMeta();
    	        				lem.setColor(Color.WHITE);
    	        				leg.setItemMeta(lem);
        	        			ItemStack boots = new ItemStack(Material.LEATHER_BOOTS);
    	        				LeatherArmorMeta bom = (LeatherArmorMeta) boots.getItemMeta();
    	        				bom.setColor(Color.WHITE);
    	        				boots.setItemMeta(bom);
    		            		ItemStack main = new ItemStack(Material.BLUE_ICE);
    		            		main.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 3);
    		            		LivingEntity newmob = Mobspawn(le, "FrozenMage", 2800.0, null, chest, leg, boots, main, main, EntityType.STRAY);
    		            		newmob.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.2);
    		            		newmob.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), true));
    		            	}
    	            	}
    	            	else {
    	            		LivingEntity newmob = Mobspawn(le, "Snowy" + le.getName(), 3000.0, le.getEquipment().getHelmet(), le.getEquipment().getChestplate(), le.getEquipment().getLeggings(), le.getEquipment().getBoots(), le.getEquipment().getItemInMainHand(), le.getEquipment().getItemInOffHand(), le.getType());
    	            		newmob.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), true));
    	            	}
    	        	}
            		else if(ev.getEntity().getCategory() == EntityCategory.ARTHROPOD) {
	            		LivingEntity newmob = Mobspawn(le, "IceSpider", 2000.0,null, null, null, null, null, null, EntityType.SPIDER);
	            		newmob.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.25);
	            		newmob.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), true));
    	        	}
            		else if(ev.getEntity().getType() == EntityType.CREEPER) {
    	        		
	            		LivingEntity newmob = Mobspawn(le, "ColdCreeper", 2200.0, null, null, null, null, null, null, EntityType.CREEPER);
	            		newmob.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.18);
	            		newmob.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), true));
    	            	
    	        	}
            		else if(ev.getEntity().getType() == EntityType.ENDERMAN) {
	            		Enderman newmob = (Enderman) Mobspawn(le, "IceMan", 2500.0, null, null, null, null, null, null, EntityType.ENDERMAN);
	            		
	            		newmob.setCarriedBlock(Material.ICE.createBlockData());
	            		newmob.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.18);
	            		newmob.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), true));
    	        	}
            		else if(ev.getEntity().getType() == EntityType.GUARDIAN) {
            			Guardian newmob = (Guardian) Mobspawn(le, "IceGuardian", 2500.0, null, null, null, null, null, null, EntityType.GUARDIAN);
	            		newmob.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), true));
    	        	}
            		else if(ev.getEntity().getType() == EntityType.ELDER_GUARDIAN) {
		            	if(!iceboss.containsKey(1)) {
		            		ItemStack main = new ItemStack(Material.STICK);
		            		main.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 3);
		            		ItemStack off = new ItemStack(Material.ICE);
		            		off.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 3);
		            		ElderGuardian newmob = (ElderGuardian) Mobspawn(le, ChatColor.AQUA+"WhiteGuardian", 30000.0, null, null, null, null, main, off, EntityType.ELDER_GUARDIAN);
		            		newmob.setMetadata("iceboss", new FixedMetadataValue(RMain.getInstance(), true));
		            		newmob.setMetadata("boss", new FixedMetadataValue(RMain.getInstance(), true));
		            		newmob.setGlowing(true);
		            		newmob.getServer().broadcastMessage(ChatColor.AQUA+"WhiteGuardian spawned at " + newmob.getLocation().toString());
		            		BossBar	newbar = Bukkit.getServer().createBossBar(new NamespacedKey(RMain.getInstance(), "WhiteGuardian"),newmob.getName(), BarColor.WHITE, BarStyle.SEGMENTED_20, BarFlag.CREATE_FOG);
		                    newbar.setVisible(true);
		            		bossbar.put(newmob.getUniqueId(), newbar);
		            		int task = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(RMain.getInstance(), new Runnable() {
				                @Override
				                public void run() 
				                {
									HashSet<Player> remove = new HashSet<Player>();
			                		newbar.setProgress(newmob.getHealth() / newmob.getMaxHealth());
			                		newbar.setTitle(newmob.getName());
			                		newmob.getNearbyEntities(25, 25, 25).forEach(en -> {
			                			if(en instanceof Player) {
			                				Player p = (Player) en;
			                				if(!bossbarplayer.containsValue(p)) {
			                					bossbarplayer.put(newmob.getUniqueId(), p.getUniqueId());
				                				newbar.addPlayer(p);
			                				}
			                			}
			                		});
			                		if(bossbarplayer.containsKey((LivingEntity)newmob)) {
			                			bossbarplayer.get(newmob.getUniqueId()).forEach(p -> {
			                				if(Bukkit.getPlayer(p).getLocation().distance(newmob.getLocation()) >25) {
			                					newbar.removePlayer(Bukkit.getPlayer(p));
			                					remove.add(Bukkit.getPlayer(p));
			                				}
			                			});
				                		remove.forEach(p ->bossbarplayer.remove(newmob.getUniqueId(), p.getUniqueId()));
			                		}
				                }
							}, 1, 5);
		            		bosst.put(newmob.getUniqueId(), task);
		            		iceboss.put(1, newmob.getUniqueId());
		            		newmob.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE).setBaseValue(0.9);
    	            		newmob.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 999999, 3, false, false));
		            		newmob.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), true));
		            		newmob.setRemoveWhenFarAway(false);
		            	}
    	        	}
            		else {
    	            		LivingEntity newmob = Mobspawn(le, "Frost" + le.getName(), 3000.0, le.getEquipment().getHelmet(), le.getEquipment().getChestplate(), le.getEquipment().getLeggings(), le.getEquipment().getBoots(), le.getEquipment().getItemInMainHand(), le.getEquipment().getItemInOffHand(), le.getType());
    	            		newmob.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), true));
            		}
    	        }
        		
            	else if(le.getLocation().getBlock().getBiome().name().contains("BADLANDS")) {
            		if(ev.getEntity().getCategory() == EntityCategory.UNDEAD ) {
    	        		Random random=new Random();
    	            	int ri = random.nextInt(5);
    	            	if(ri == 0) {
    	        			ItemStack head = new ItemStack(Material.RED_TERRACOTTA);
    	        			ItemStack chest = new ItemStack(Material.LEATHER_CHESTPLATE);
	        				LeatherArmorMeta chm = (LeatherArmorMeta) chest.getItemMeta();
	        				chm.setColor(Color.RED);
	        				chest.setItemMeta(chm);
    	        			ItemStack leg = new ItemStack(Material.LEATHER_LEGGINGS);
	        				LeatherArmorMeta lem = (LeatherArmorMeta) leg.getItemMeta();
	        				lem.setColor(Color.RED);
	        				leg.setItemMeta(lem);
    	        			ItemStack boots = new ItemStack(Material.LEATHER_BOOTS);
	        				LeatherArmorMeta bom = (LeatherArmorMeta) boots.getItemMeta();
	        				bom.setColor(Color.RED);
	        				boots.setItemMeta(bom);
    	            		LivingEntity newmob = Mobspawn(le, "Redman", 10000.0, head, chest, leg, boots, new ItemStack(Material.CAMPFIRE), new ItemStack(Material.CAMPFIRE), EntityType.HUSK);
    	            		newmob.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 999999, 255, false, false));
    	            		newmob.setFireTicks(999999);
    	            		newmob.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), true));
    	            	}
    	            	else if(ri == 1) {
    	        			ItemStack head = new ItemStack(Material.LEATHER_HELMET);
	        				LeatherArmorMeta hem = (LeatherArmorMeta) head.getItemMeta();
	        				hem.setColor(Color.RED);
	        				head.setItemMeta(hem);
    	        			ItemStack chest = new ItemStack(Material.LEATHER_CHESTPLATE);
	        				LeatherArmorMeta chm = (LeatherArmorMeta) chest.getItemMeta();
	        				chm.setColor(Color.RED);
	        				chest.setItemMeta(chm);
    	        			ItemStack leg = new ItemStack(Material.LEATHER_LEGGINGS);
	        				LeatherArmorMeta lem = (LeatherArmorMeta) leg.getItemMeta();
	        				lem.setColor(Color.RED);
	        				leg.setItemMeta(lem);
    	        			ItemStack boots = new ItemStack(Material.LEATHER_BOOTS);
	        				LeatherArmorMeta bom = (LeatherArmorMeta) boots.getItemMeta();
	        				bom.setColor(Color.RED);
	        				boots.setItemMeta(bom);
    	            		ItemStack main = new ItemStack(Material.BOW);
    	            		main.addUnsafeEnchantment(Enchantment.ARROW_FIRE, 2);
    	            		main.addUnsafeEnchantment(Enchantment.ARROW_DAMAGE, 3);
    	            		LivingEntity newmob = Mobspawn(le, "BurnedSkull", 7000.0, null, chest, leg, boots, main, null, EntityType.SKELETON);
    	            		newmob.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 999999, 255, false, false));
    	            		newmob.setFireTicks(999999);
    	            		newmob.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), true));
    	            	}
    	            	else if(ri == 2) {
    	        			ItemStack head = new ItemStack(Material.LEATHER_HELMET);
	        				LeatherArmorMeta hem = (LeatherArmorMeta) head.getItemMeta();
	        				hem.setColor(Color.RED);
	        				head.setItemMeta(hem);
    	        			ItemStack chest = new ItemStack(Material.LEATHER_CHESTPLATE);
	        				LeatherArmorMeta chm = (LeatherArmorMeta) chest.getItemMeta();
	        				chm.setColor(Color.RED);
	        				chest.setItemMeta(chm);
    	        			ItemStack leg = new ItemStack(Material.LEATHER_LEGGINGS);
	        				LeatherArmorMeta lem = (LeatherArmorMeta) leg.getItemMeta();
	        				lem.setColor(Color.RED);
	        				leg.setItemMeta(lem);
    	        			ItemStack boots = new ItemStack(Material.LEATHER_BOOTS);
	        				LeatherArmorMeta bom = (LeatherArmorMeta) boots.getItemMeta();
	        				bom.setColor(Color.RED);
	        				boots.setItemMeta(bom);
    	            		ItemStack main = new ItemStack(Material.FIRE_CHARGE);
    	            		main.addUnsafeEnchantment(Enchantment.FIRE_ASPECT, 2);
    	            		main.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 5);
    	            		LivingEntity newmob = Mobspawn(le, "BurnedMage", 6800.0, null, chest, leg, boots, main,main, EntityType.SKELETON);
    	            		newmob.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 999999, 255, false, false));
    	            		newmob.setFireTicks(999999);
    	            		newmob.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), true));
    	            	}
    	            	else if (ri == 3){
    		            	int bri = random.nextInt(2);
    		            	if(bri == 1 && !redboss.containsKey(1)) {
        	        			ItemStack head = new ItemStack(Material.LEATHER_HELMET);
    	        				LeatherArmorMeta hem = (LeatherArmorMeta) head.getItemMeta();
    	        				hem.setDisplayName("RedKnight Helmet");
    	        				hem.setLocalizedName("RedKnight Helmet");
    	        				head.setItemMeta(hem);
        	        			ItemStack chest = new ItemStack(Material.LEATHER_CHESTPLATE);
    	        				LeatherArmorMeta chm = (LeatherArmorMeta) chest.getItemMeta();
    	        				chm.setColor(Color.RED);
    	        				chest.setItemMeta(chm);
    	        				chest.addUnsafeEnchantment(Enchantment.PROTECTION_FIRE, 5);
    	        				chest.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
    	        				chest.addUnsafeEnchantment(Enchantment.PROTECTION_PROJECTILE, 1);
        	        			ItemStack leg = new ItemStack(Material.LEATHER_LEGGINGS);
    	        				LeatherArmorMeta lem = (LeatherArmorMeta) leg.getItemMeta();
    	        				lem.setColor(Color.RED);
    	        				leg.setItemMeta(lem);
    	        				leg.addUnsafeEnchantment(Enchantment.PROTECTION_FIRE, 5);
    	        				leg.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
    	        				leg.addUnsafeEnchantment(Enchantment.PROTECTION_PROJECTILE, 1);
        	        			ItemStack boots = new ItemStack(Material.LEATHER_BOOTS);
    	        				LeatherArmorMeta bom = (LeatherArmorMeta) boots.getItemMeta();
    	        				bom.setColor(Color.RED);
    	        				boots.setItemMeta(bom);
    	        				boots.addUnsafeEnchantment(Enchantment.PROTECTION_FIRE, 5);
    	        				boots.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
    	        				boots.addUnsafeEnchantment(Enchantment.PROTECTION_PROJECTILE, 1);
    		            		ItemStack main = new ItemStack(Material.NETHERITE_AXE);
    		            		main.addUnsafeEnchantment(Enchantment.FIRE_ASPECT, 3);
    		            		main.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 3);
    		            		ItemStack off = new ItemStack(Material.SHIELD);
    		            		off.addUnsafeEnchantment(Enchantment.FIRE_ASPECT, 3);
    		            		off.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 3);
    		            		Skeleton newmob = (Skeleton) Mobspawn(le, ChatColor.RED+"RedKnight", 70000.0, head, chest, leg, boots, main, off, EntityType.SKELETON);
    		            		newmob.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 999999, 255, false, false));
    		            		newmob.setGlowing(true);
    		            		newmob.getEquipment().setBootsDropChance(0);
    		            		newmob.getEquipment().setChestplateDropChance(0);
    		            		newmob.getEquipment().setHelmetDropChance(0);
    		            		newmob.getEquipment().setItemInMainHandDropChance(0);
    		            		newmob.getEquipment().setItemInOffHandDropChance(0);
    		            		newmob.getEquipment().setLeggingsDropChance(0);
    		            		newmob.setMetadata("redboss", new FixedMetadataValue(RMain.getInstance(), true));
    		            		newmob.setMetadata("boss", new FixedMetadataValue(RMain.getInstance(), true));
    		            		newmob.getServer().broadcastMessage(ChatColor.RED+"RedKnight spawned at " + newmob.getLocation().toString());
    		            		BossBar	newbar = Bukkit.getServer().createBossBar(new NamespacedKey(RMain.getInstance(), "RedKnight"),newmob.getName(), BarColor.RED, BarStyle.SEGMENTED_20, BarFlag.CREATE_FOG);
    		                    newbar.setVisible(true);
    		            		bossbar.put(newmob.getUniqueId(), newbar);
    		            		final int task = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(RMain.getInstance(), new Runnable() {
    				                @Override
    				                public void run() 
    				                {
										HashSet<Player> remove = new HashSet<Player>();
				                		newbar.setProgress(newmob.getHealth() / newmob.getMaxHealth());
				                		newbar.setTitle(newmob.getName());
				                		newmob.getNearbyEntities(25, 25, 25).forEach(en -> {
				                			if(en instanceof Player) {
				                				Player p = (Player) en;
				                				if(!bossbarplayer.containsValue(p)) {
				                					bossbarplayer.put(newmob.getUniqueId(), p.getUniqueId());
    				                				newbar.addPlayer(p);
				                				}
				                			}
				                		});
				                		if(bossbarplayer.containsKey((LivingEntity)newmob)) {
				                			bossbarplayer.get(newmob.getUniqueId()).forEach(p -> {
				                				if(Bukkit.getPlayer(p).getLocation().distance(newmob.getLocation()) >25) {
				                					newbar.removePlayer(Bukkit.getPlayer(p));
				                					remove.add(Bukkit.getPlayer(p));
				                				}
				                			});
    				                		remove.forEach(p ->bossbarplayer.remove(newmob.getUniqueId(), p.getUniqueId()));
				                		}
    				                }
    							}, 1, 5);
    		            		bosst.put(newmob.getUniqueId(), task);
    		            		redboss.put(1, newmob.getUniqueId());
    		            		newmob.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.3);
    		            		newmob.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE).setBaseValue(0.9);
    		            		newmob.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), true));
    		            		newmob.setRemoveWhenFarAway(false);
    		            	}
    		            	else {
    		            		ItemStack head = new ItemStack(Material.LEATHER_HELMET);
    	        				LeatherArmorMeta hem = (LeatherArmorMeta) head.getItemMeta();
    	        				hem.setColor(Color.RED);
    	        				head.setItemMeta(hem);
        	        			ItemStack chest = new ItemStack(Material.LEATHER_CHESTPLATE);
    	        				LeatherArmorMeta chm = (LeatherArmorMeta) chest.getItemMeta();
    	        				chm.setColor(Color.RED);
    	        				chest.setItemMeta(chm);
        	        			ItemStack leg = new ItemStack(Material.LEATHER_LEGGINGS);
    	        				LeatherArmorMeta lem = (LeatherArmorMeta) leg.getItemMeta();
    	        				lem.setColor(Color.RED);
    	        				leg.setItemMeta(lem);
        	        			ItemStack boots = new ItemStack(Material.LEATHER_BOOTS);
    	        				LeatherArmorMeta bom = (LeatherArmorMeta) boots.getItemMeta();
    	        				bom.setColor(Color.RED);
    	        				boots.setItemMeta(bom);
    		            		ItemStack main = new ItemStack(Material.IRON_SWORD);
    		            		main.addUnsafeEnchantment(Enchantment.FIRE_ASPECT, 2);
    		            		main.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 3);
    		            		LivingEntity newmob = Mobspawn(le, "RedBruise", 7500.0, head, chest, leg, boots, main, main, EntityType.SKELETON);
    		            		newmob.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 999999, 255, false, false));
    		            		newmob.setFireTicks(999999);
    		            		newmob.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.3);
    		            		newmob.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), true));
    		            	}
    	            	}
    	            	else {
    	            		LivingEntity newmob = Mobspawn(le, "BadLands" + le.getName(), 7500.0, le.getEquipment().getHelmet(), le.getEquipment().getChestplate(), le.getEquipment().getLeggings(), le.getEquipment().getBoots(), le.getEquipment().getItemInMainHand(), le.getEquipment().getItemInOffHand(), le.getType());
    	            		newmob.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), true));
    	            	}
    	        	}
            		else if(ev.getEntity().getCategory() == EntityCategory.ARTHROPOD) {
    	            		LivingEntity newmob = Mobspawn(le, "BurningSpider", 6000.0,null, null, null, null, new ItemStack(Material.CAMPFIRE), new ItemStack(Material.CAMPFIRE), EntityType.SPIDER);
    	            		newmob.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 999999, 255, false, false));
    	            		newmob.setFireTicks(999999);
    	            		newmob.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.25);
    	            		newmob.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), true));
    	        	}
            		else if(ev.getEntity().getType() == EntityType.CREEPER) {
    	            		Creeper newmob = (Creeper) Mobspawn(le, "HotCreeper", 6000.0, null, null, null, null, new ItemStack(Material.CAMPFIRE), new ItemStack(Material.CAMPFIRE), EntityType.CREEPER);
    	            		newmob.setPowered(true);
    	            		newmob.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 999999, 255, false, false));
    	            		newmob.setFireTicks(999999);
    	            		newmob.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.18);
    	            		newmob.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), true));
    	        	}
            		else if(ev.getEntity().getType() == EntityType.ENDERMAN) {
    	            		Enderman newmob = (Enderman) Mobspawn(le, "FireMan", 8000.0, new ItemStack(Material.MAGMA_BLOCK), null, null, null, new ItemStack(Material.MAGMA_BLOCK), new ItemStack(Material.MAGMA_BLOCK), EntityType.ENDERMAN);
    	            		newmob.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 999999, 255, false, false));
    	            		newmob.setFireTicks(999999);
    	            		newmob.setCarriedBlock(Material.MAGMA_BLOCK.createBlockData());
    	            		newmob.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.18);
    	            		newmob.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), true));
    	        	}
            		else {
    	            		LivingEntity newmob = Mobspawn(le, "BadLands" + le.getName(), 7500.0, le.getEquipment().getHelmet(), le.getEquipment().getChestplate(), le.getEquipment().getLeggings(), le.getEquipment().getBoots(), le.getEquipment().getItemInMainHand(), le.getEquipment().getItemInOffHand(), le.getType());
    	            		newmob.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), true));
            		}
    	        }
        		
        	}
		}
	}

	
	
	@EventHandler
	public void BossBar(EntityDeathEvent d) 
	{		
		if(d.getEntity().hasMetadata("boss")) {
			LivingEntity le = d.getEntity();
			Bukkit.getServer().getScheduler().cancelTask(bosst.get(le.getUniqueId()));
			bosst.remove(le.getUniqueId());
            bossbar.get(le.getUniqueId()).removeAll();
            bossbarplayer.removeAll(le.getUniqueId());
            if(le.hasMetadata("redboss")) {
    			d.setDroppedExp(10000);
            	redboss.clear();
            }
            if(le.hasMetadata("snowboss")) {
    			d.setDroppedExp(2000);
            	snowboss.clear();
            }
            if(le.hasMetadata("iceboss")) {
            	iceboss.clear();
            }
            if(le.hasMetadata("stoneboss")) {
            	stoneboss.clear();
            }
		}
	}
	
	@EventHandler	
	public void bossbar(PlayerQuitEvent ev) 
	{
		Player p = ev.getPlayer();

		if(p.getServer().getOnlinePlayers().size()<=1) {

			List<World> worlds = Bukkit.getServer().getWorlds();
			worlds.forEach(w -> w.getEntities().forEach(b -> {
				if(b.hasMetadata("boss")) {
					LivingEntity le = (LivingEntity) b;
					Bukkit.getServer().getScheduler().cancelTask(bosst.get(le.getUniqueId()));
					bosst.remove(le.getUniqueId());
		            bossbar.get(le.getUniqueId()).removeAll();
		            bossbarplayer.removeAll(le.getUniqueId());
		            if(le.hasMetadata("redboss")) {
		            	redboss.clear();
		            }
		            if(le.hasMetadata("snowboss")) {
		            	snowboss.clear();
		            }
		            if(le.hasMetadata("iceboss")) {
		            	iceboss.clear();
		            }
		            if(le.hasMetadata("stoneboss")) {
		            	stoneboss.clear();
		            }
				}
			}));
		}
		
	}

	@EventHandler	
	public void bossbar(PluginDisableEvent ev) 
	{
		List<World> worlds = Bukkit.getServer().getWorlds();
		worlds.forEach(w -> w.getEntities().forEach(b -> {
			if(b.hasMetadata("boss")) {
				LivingEntity le = (LivingEntity) b;
				Bukkit.getServer().getScheduler().cancelTask(bosst.get(le.getUniqueId()));
				bosst.remove(le.getUniqueId());
	            bossbar.get(le.getUniqueId()).removeAll();
	            bossbarplayer.removeAll(le.getUniqueId());
	            if(le.hasMetadata("redboss")) {
	            	redboss.clear();
	            }
	            if(le.hasMetadata("snowboss")) {
	            	snowboss.clear();
	            }
	            if(le.hasMetadata("iceboss")) {
	            	iceboss.clear();
	            }
	            if(le.hasMetadata("stoneboss")) {
	            	stoneboss.clear();
	            }
			}
		}));
	}

}
