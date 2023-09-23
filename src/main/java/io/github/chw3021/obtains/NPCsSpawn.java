package io.github.chw3021.obtains;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.HeightMap;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.StructureType;
import org.bukkit.World;
import org.bukkit.entity.Axolotl;
import org.bukkit.entity.Axolotl.Variant;
import org.bukkit.entity.Drowned;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Husk;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Piglin;
import org.bukkit.entity.Player;
import org.bukkit.entity.Skeleton;
import org.bukkit.entity.Snowman;
import org.bukkit.entity.Turtle;
import org.bukkit.entity.Villager;
import org.bukkit.entity.Villager.Profession;
import org.bukkit.entity.Villager.Type;
import org.bukkit.entity.WanderingTrader;
import org.bukkit.event.Listener;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.loot.LootTables;
import org.bukkit.loot.Lootable;
import org.bukkit.metadata.FixedMetadataValue;
import org.jetbrains.annotations.Nullable;

import io.github.chw3021.monsters.Mobs;
import io.github.chw3021.rmain.RMain;


public class NPCsSpawn extends Mobs implements Serializable, Listener {
	
	/**
	 * 
	 */
	private transient static final long serialVersionUID = -1035108652441484461L;
	public static HashMap<UUID,Location> npcloc = new HashMap<UUID,Location>();
	private static HashMap<Location,String> npcsloc = new HashMap<Location,String> ();
	public static HashMap<Location,Location> pil = new HashMap<Location,Location> ();
	

	private static final NPCsSpawn instance = new NPCsSpawn ();
	public static NPCsSpawn getInstance()
	{
		return instance;
	}


	final private Location clf(Location l) {
		Location tl = l.clone();

		HashSet<Location> lhs = new HashSet<>();
		for(int iy = -20; iy<50; iy++) {
			for(int ix = -50; ix<50; ix++) {
				for(int iz = -50; iz<50; iz++) {
					lhs.add(tl.clone().add(ix, iy, iz));
				}
			}
		}
		if(lhs.stream().anyMatch(dl -> dl.getBlock().getType() == Material.CHEST)){
			tl = lhs.stream().filter(dl -> dl.getBlock().getType() == Material.CHEST).findAny().get();
		}
		else {
			tl.add(0, 1, 0).getBlock().setType(Material.CHEST);
		}
		return tl;
	}
	
	final private Location lfd(Location l, String s) {
		HashSet<Location> lhs = new HashSet<>();
		Location tl = l.getWorld().getHighestBlockAt(l.clone(), HeightMap.OCEAN_FLOOR).getLocation().add(0, 1, 0);
		
		for(int ix = -20; ix<20; ix++) {
			for(int iz = -20; iz<20; iz++) {
				for(int iy = -100; iy<80; iy++) {
					lhs.add(tl.clone().add(ix, iy, iz));
				}
			}
		}
		for(Location dl : lhs) {
			if(s.equalsIgnoreCase("mineshaft")) {
				dl.add(0, -30, 0);
				if(dl.getBlock().getType() == Material.RAIL || dl.getBlock().getType() == Material.COBWEB) {
					tl = dl;
					break;
				}
			}
			else if(s.equalsIgnoreCase("treasure")) {
				if(dl.getBlock().getType() == Material.CHEST && !dl.clone().add(0, -1, 0).getBlock().getType().name().contains("PLANKS")) {
					tl = dl.clone().add(0, 2, 0);
					break;
				}
			}
			else if(s.equalsIgnoreCase("junglepyramid")) {
				if(dl.getBlock().getType() == Material.REDSTONE_WIRE) {
					tl = dl;
					break;
				}
			}
			else if(s.equalsIgnoreCase("igloo")) {
				if(dl.getBlock().getType() == Material.WHITE_CARPET) {
					tl = dl;
					break;
				}
			}
			else if(s.equalsIgnoreCase("monument")) {
				if(dl.getBlock().getType() == Material.PRISMARINE_BRICKS) {
					tl = dl;
					break;
				}
			}
			else if(s.equalsIgnoreCase("shipwreck")) {
				if(dl.getBlock().getType().name().contains("TRAPDOOR")) {
					tl = dl;
					break;
				}
			}
			else if(s.equalsIgnoreCase("pillageroutpost")) {
				pil.put(l, clf(tl));
				Location al = l.getWorld().getHighestBlockAt(tl.clone().add(50, 0, 50), HeightMap.OCEAN_FLOOR).getLocation().add(0, 1, 0);
				tl = al;
				break;
			}
			else if(s.equalsIgnoreCase("stronghold")) {
				dl.add(0, -30, 0);
				if(dl.getBlock().getType() == Material.BOOKSHELF || dl.getBlock().getType() == Material.IRON_DOOR) {
					tl = dl;
					break;
				}
			}
			else if(s.equalsIgnoreCase("ruinedportal")) {
				if(dl.getBlock().getType() == Material.GOLD_BLOCK|| dl.getBlock().getType() == Material.CRYING_OBSIDIAN || dl.getBlock().getType() == Material.CRACKED_STONE_BRICKS) {
					tl = dl.clone().add(0, 0.5, 0);
					break;
				}
			}
			else if(s.equalsIgnoreCase("netherfortress")) {
				dl.add(0, -80, 0);
				if(dl.getBlock().getType() == Material.NETHER_BRICKS) {
					tl = dl;
					break;
				}
			}
			else if(s.equalsIgnoreCase("bastionremnant")) {
				dl.add(0, -80, 0);
				if(dl.getBlock().getType() == Material.POLISHED_BLACKSTONE_BRICK_STAIRS) {
					tl = dl;
					break;
				}
			}
			else if(s.equalsIgnoreCase("endcity")) {
				if(dl.getBlock().getType() == Material.PURPUR_STAIRS) {
					tl = dl;
					break;
				}
			}
		}
		tl.clone().getWorld().getNearbyEntities(tl.clone(),4,4,4).forEach(e -> {
			if(!(e instanceof Player) && !e.hasMetadata("untargetable")) {
				e.remove();
			}
		});
		System.out.println(tl);
		return tl;
	}
	
	final private Villager vnpc(World w, Location l, String meta, String name,  @Nullable  Type vt,  @Nullable  Profession pr) {
	
		if(npcsloc.containsKey(l) && npcsloc.get(l).equalsIgnoreCase(meta)) {
			return null;
		}
		else {
			npcsloc.put(l, meta);
			
			Location tl = lfd(l,meta);
			w.getNearbyEntities(tl.clone(),4,4,4).forEach(e -> {
				if(!(e instanceof Player) && !e.hasMetadata("untargetable")) {
					e.remove();
				}
			});
			final Villager v = w.spawn(tl, Villager.class);
			npcloc.put(v.getUniqueId(),l);
			v.setAI(false);
			v.setGlowing(true);
			v.setBreed(false);
			v.setInvulnerable(true);
			v.setAgeLock(true);
			v.setCollidable(false);
			v.setCustomName(name);
			v.setCustomNameVisible(true);
			v.setAdult();
			if(vt != null) {
				v.setVillagerType(vt);
			}
			if(pr != null) {
				v.setProfession(pr);
			}
			v.setMetadata(meta, new FixedMetadataValue(RMain.getInstance(), true));
			v.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), true));
			v.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
			v.setMetadata("obnpc", new FixedMetadataValue(RMain.getInstance(), true));
			v.setPersistent(true);
			v.getEquipment().setBootsDropChance(0);
			v.getEquipment().setChestplateDropChance(0);
			v.getEquipment().setHelmetDropChance(0);
			v.getEquipment().setItemInMainHandDropChance(0);
			v.getEquipment().setItemInOffHandDropChance(0);
			v.getEquipment().setLeggingsDropChance(0);
			v.setRemoveWhenFarAway(false);
			v.setLootTable(null);
			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	            @Override
	            public void run() 
	            {
	    			w.getNearbyEntities(tl.clone(),4,4,4).forEach(e -> {
	    				if(e!=v&& !(e instanceof Player) && !e.hasMetadata("untargetable")) {
	    					e.remove();
	    				}
	    			});
	            }
	        }, 2); 
			return v;
			
		}
	}

	final private LivingEntity mnpc(World w, Location l, EntityType type, String meta, @Nullable String name) {

		if(npcsloc.containsKey(l) && npcsloc.get(l).equalsIgnoreCase(meta)) {
			return null;
		}
		else {
			npcsloc.put(l, meta);
			Location tl = lfd(l,meta);
			w.getNearbyEntities(tl.clone(),4,4,4).forEach(e -> {
				if(!(e instanceof Player) && !e.hasMetadata("untargetable")) {
					e.remove();
				}
			});
			final LivingEntity v = (LivingEntity) w.spawnEntity(tl, type);
			npcloc.put(v.getUniqueId(),l);
			v.setAI(false);
			v.setGlowing(true);
			v.setInvulnerable(true);
			v.setCollidable(false);
			v.setCustomName(name);
			v.setCustomNameVisible(true);
			v.setMetadata(meta, new FixedMetadataValue(RMain.getInstance(), true));
			v.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), true));
			v.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
			v.setMetadata("obnpc", new FixedMetadataValue(RMain.getInstance(), true));
			v.setPersistent(true);
			v.getEquipment().setBootsDropChance(0);
			v.getEquipment().setChestplateDropChance(0);
			v.getEquipment().setHelmetDropChance(0);
			v.getEquipment().setItemInMainHandDropChance(0);
			v.getEquipment().setItemInOffHandDropChance(0);
			v.getEquipment().setLeggingsDropChance(0);
			v.setRemoveWhenFarAway(false);
			((Lootable) v).setLootTable(null);

			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	            @Override
	            public void run() 
	            {
	    			w.getNearbyEntities(tl.clone(),4,4,4).forEach(e -> {
	    				if(e!=v&& !(e instanceof Player) && !e.hasMetadata("untargetable")) {
	    					e.remove();
	    				}
	    			});
	            }
	        }, 2); 
			
			return v;
		}
	}

	final private LivingEntity mnpc(World w, Location l, EntityType type, String meta, @Nullable String name, @Nullable ItemStack head, @Nullable ItemStack chest, @Nullable ItemStack leg, @Nullable ItemStack boots,@Nullable ItemStack main,@Nullable ItemStack off) {

		if(npcsloc.containsKey(l) && npcsloc.get(l).equalsIgnoreCase(meta)) {
			return null;
		}
		else {
			npcsloc.put(l, meta);
			Location tl = lfd(l,meta);
			w.getNearbyEntities(tl.clone(),4,4,4).forEach(e -> {
				if(!(e instanceof Player) && !e.hasMetadata("untargetable")) {
					e.remove();
				}
			});
			final LivingEntity v = (LivingEntity) w.spawnEntity(tl, type);
			npcloc.put(v.getUniqueId(),l);
			v.setPersistent(false);
			v.setAI(false);
			v.setGlowing(true);
			v.setInvulnerable(true);
			v.setCollidable(false);
			v.setCustomName(name);
			v.setCustomNameVisible(true);
			v.setMetadata(meta, new FixedMetadataValue(RMain.getInstance(), true));
			v.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), true));
			v.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
			v.setMetadata("obnpc", new FixedMetadataValue(RMain.getInstance(), true));
			v.setPersistent(true);
			EntityEquipment ee = v.getEquipment();
			if(head != null) {
				ee.setHelmet(head);
			}
			if(chest != null) {
				ee.setChestplate(chest);
			}
			if(leg != null) {
				ee.setLeggings(leg);
			}
			if(boots != null) {
				ee.setBoots(boots);
			}
			if(main != null) {
				ee.setItemInMainHand(main);
			}
			if(off != null) {
				ee.setItemInOffHand(off);
			}
			v.getEquipment().setBootsDropChance(0);
			v.getEquipment().setChestplateDropChance(0);
			v.getEquipment().setHelmetDropChance(0);
			v.getEquipment().setItemInMainHandDropChance(0);
			v.getEquipment().setItemInOffHandDropChance(0);
			v.getEquipment().setLeggingsDropChance(0);
			v.setRemoveWhenFarAway(false);
			((Lootable) v).setLootTable(LootTables.EMPTY.getLootTable());


			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	            @Override
	            public void run() 
	            {
	    			w.getNearbyEntities(tl.clone(),4,4,4).forEach(e -> {
	    				if(e!=v&& !(e instanceof Player) && !e.hasMetadata("untargetable")) {
	    					e.remove();
	    				}
	    			});
	            }
	        }, 2); 
			return v;
		}
	}


	final public void Spawn(final Location lel, StructureType st) {

		final World w = lel.getWorld();
		

    	if(st == StructureType.MINESHAFT) {
			String reg = lang.contains("kr") ? "길잃은 광부":"Stray Miner";
    		Villager v = vnpc(w,lel, "mineshaft",reg, Type.DESERT, Profession.ARMORER);
    		if(v != null) {
        		v.getEquipment().setHelmet(new ItemStack(Material.GOLDEN_HELMET));
        		v.setVillagerLevel(5);
        		v.setBreed(false);
    		}
    		
    	}
    	if(st == StructureType.BURIED_TREASURE) {
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

			String reg = lang.contains("kr") ? "보물 수호령":"Treasure Ghost";
    		Skeleton v = (Skeleton) mnpc(w,lel, EntityType.SKELETON, "treasure", reg,head,chest,leg,null,new ItemStack(Material.SOUL_LANTERN),new ItemStack(Material.SOUL_LANTERN));
    		if(v != null) {
        		v.setConversionTime(-1);
    		}
    		
    	}
    	if(st == StructureType.PILLAGER_OUTPOST) {
			String reg = lang.contains("kr") ? "약탈당한 상인":"Looted Trader";
    		WanderingTrader v = (WanderingTrader) mnpc(w,lel, EntityType.WANDERING_TRADER, "pillageroutpost", reg);
    		if(v != null) {
        		v.setAdult();
        		v.setAgeLock(true);
        		v.setBreed(false);
        		v.setDespawnDelay(-1);
    		}
    	}
    	if(st == StructureType.STRONGHOLD) {

			String reg = lang.contains("kr") ? "요새 청소부":"Stronghold Cleaner";
    		Villager v = vnpc(w,lel, "stronghold",reg, Type.SNOW, Profession.BUTCHER);
    		if(v != null) {
        		v.setVillagerLevel(5);
    		}
    	}
    	if(st == StructureType.RUINED_PORTAL) {

    			ItemStack head = new ItemStack(Material.CRYING_OBSIDIAN);
    			ItemStack chest = new ItemStack(Material.LEATHER_CHESTPLATE);
    			LeatherArmorMeta chm = (LeatherArmorMeta) chest.getItemMeta();
    			chm.setColor(Color.BLACK);
    			chest.setItemMeta(chm);
    			ItemStack leg = new ItemStack(Material.LEATHER_LEGGINGS);
    			LeatherArmorMeta lem = (LeatherArmorMeta) leg.getItemMeta();
    			lem.setColor(Color.BLACK);
    			leg.setItemMeta(lem);
    			ItemStack boots = new ItemStack(Material.LEATHER_BOOTS);
    			LeatherArmorMeta bom = (LeatherArmorMeta) boots.getItemMeta();
    			bom.setColor(Color.TEAL);
    			boots.setItemMeta(bom);

    			String reg = lang.contains("kr") ? "사신":"Reaper";
    			mnpc(w,lel, EntityType.STRAY, "ruinedportal", reg,head,chest,leg,null,new ItemStack(Material.TOTEM_OF_UNDYING),new ItemStack(Material.NETHERITE_HOE));
        }
		

    	if(st == StructureType.WOODLAND_MANSION) {
        		
			lel.getWorld().getNearbyEntities(lel, 150, 60, 150).forEach(e -> {
    			if(!e.hasMetadata("rpgspawned") && !e.hasMetadata("fake") && (e.getType() == EntityType.EVOKER||e.getType() == EntityType.VINDICATOR||e.getType() == EntityType.WITCH)) {
    				e.remove();
    			}
    		});
			String reg = lang.contains("kr") ? "보안관":"Sheriff";
    		Villager v = vnpc(w,lel, "mansion",reg, Type.SWAMP, Profession.CARTOGRAPHER);
    		if(v != null) {
        		v.getEquipment().setHelmet(new ItemStack(Material.NETHERITE_HELMET));
        		v.getEquipment().setItemInMainHand(new ItemStack(Material.CROSSBOW));
        		v.getEquipment().setItemInOffHand(new ItemStack(Material.SPYGLASS));
        		v.setVillagerLevel(5);
        		v.setBreed(false);
    		}
    	}

    	if(st == StructureType.SWAMP_HUT) {

			String reg = lang.contains("kr") ? "성직자":"Priest";
    		Villager v = vnpc(w,lel, "swamphut",reg, Type.SWAMP, Profession.CLERIC);
    		if(v != null) {
        		v.setVillagerLevel(5);
    		}
    		
    	}
    	if(st == StructureType.IGLOO) {
    		
    			ItemStack head = new ItemStack(Material.SNOW_BLOCK);
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

    			String reg = lang.contains("kr") ? "이글루 눈사람":"Igloo Snowman";
    			Snowman v = (Snowman) mnpc(w,lel, EntityType.SNOWMAN, "igloo", reg,head,chest,leg,boots,null,null);
        		if(v != null) {
            		v.setDerp(true);
        		}
    	}

    	if(st == StructureType.DESERT_PYRAMID) {
    		
    			ItemStack head = new ItemStack(Material.OBSERVER);
    			ItemStack chest = new ItemStack(Material.IRON_CHESTPLATE);
    			ItemStack leg = new ItemStack(Material.LEATHER_LEGGINGS);
    			LeatherArmorMeta lem = (LeatherArmorMeta) leg.getItemMeta();
    			lem.setColor(Color.BLACK);
    			leg.setItemMeta(lem);
    			ItemStack boots = new ItemStack(Material.LEATHER_BOOTS);
    			LeatherArmorMeta bom = (LeatherArmorMeta) boots.getItemMeta();
    			bom.setColor(Color.BLACK);
    			boots.setItemMeta(bom);

    			String reg = lang.contains("kr") ? "탈출한 실험체":"Escaped Specimen";
        		Husk v = (Husk) mnpc(w,lel, EntityType.HUSK, "desertpyramid", reg,head,chest,leg,boots,null,null);
        		if(v != null) {
            		v.setBaby();
            		v.setConversionTime(-1);
        		}
    		
    	}
    	if(st == StructureType.JUNGLE_PYRAMID) {

			String reg = lang.contains("kr") ? "고고학자":"Archaeologist";
    		Villager v = vnpc(w,lel, "junglepyramid" ,reg, Type.JUNGLE, Profession.LIBRARIAN);
    		if(v != null) {
        		v.setVillagerLevel(5);
    		}
    	}
    	if(st == StructureType.OCEAN_RUIN) {
    		lel.getWorld().getNearbyEntities(lel, 100, 100, 100).forEach(e -> {
        			if(!e.hasMetadata("rpgspawned") && !e.hasMetadata("fake") && (e.getType() == EntityType.DROWNED)) {

        				e.remove();
        			}
        		});
    			String reg = lang.contains("kr") ? "아홀로틀 요원":"Agent Axolotl";
    			Axolotl v = (Axolotl) mnpc(w,lel, EntityType.AXOLOTL, "oceanruin", reg,null,null,null,null, new ItemStack(Material.CLOCK),null);
        		if(v != null) {
            		v.setAdult();
            		v.setAgeLock(true);
            		v.setVariant(Variant.BLUE);
            		v.setBreed(false);
        		}
    		
    	}
    	if(st == StructureType.SHIPWRECK) {
        		
    			ItemStack head = new ItemStack(Material.DEAD_BRAIN_CORAL_BLOCK);
    			ItemStack chest = new ItemStack(Material.LEATHER_CHESTPLATE);
    			LeatherArmorMeta chm = (LeatherArmorMeta) chest.getItemMeta();
    			chm.setColor(Color.RED);
    			chest.setItemMeta(chm);
    			ItemStack leg = new ItemStack(Material.LEATHER_LEGGINGS);
    			LeatherArmorMeta lem = (LeatherArmorMeta) leg.getItemMeta();
    			lem.setColor(Color.BLACK);
    			leg.setItemMeta(lem);
    			ItemStack boots = new ItemStack(Material.LEATHER_BOOTS);
    			LeatherArmorMeta bom = (LeatherArmorMeta) boots.getItemMeta();
    			bom.setColor(Color.OLIVE);
    			boots.setItemMeta(bom);
    			String reg = lang.contains("kr") ? "익사한 선원":"Drowned Sailor";
        		Drowned v = (Drowned) mnpc(w,lel, EntityType.DROWNED, "shipwreck", reg ,head,chest,leg,boots,null,null);
        		if(v != null) {
            		v.setAdult();
            		v.setConversionTime(-1);
        		}
    	}
    	if(st == StructureType.OCEAN_MONUMENT) {
    		
        		lel.getWorld().getNearbyEntities(lel, 100, 100, 100).forEach(e -> {
        			if(!e.hasMetadata("rpgspawned") && !e.hasMetadata("fake") && (e.getType() == EntityType.GUARDIAN||e.getType() == EntityType.ELDER_GUARDIAN)) {
        				e.remove();
        			}
        		});
    			String reg = lang.contains("kr") ? "거북 특공대원":"Commando Turtle";
        		Turtle v = (Turtle) mnpc(w,lel, EntityType.TURTLE, "oceanmonument", reg ,null,null,null,null,null,null);
        		if(v != null) {
            		v.setAdult();
            		v.setAgeLock(true);
            		v.setBreed(false);
        		}
    		
    	}
    	if(st == StructureType.NETHER_FORTRESS) {
    			ItemStack head = new ItemStack(Material.IRON_HELMET);
    			ItemStack chest = new ItemStack(Material.IRON_CHESTPLATE);
    			ItemStack leg = new ItemStack(Material.IRON_LEGGINGS);;
    			ItemStack boots = new ItemStack(Material.IRON_BOOTS);

    			String reg = lang.contains("kr") ? "피글린 기사":"Piglin Knight";
    			Piglin v = (Piglin) mnpc(w,lel, EntityType.PIGLIN, "netherfortress", reg,head,chest,leg,boots,new ItemStack(Material.DIAMOND_AXE),new ItemStack(Material.SHIELD));
        		if(v != null) {
            		v.setImmuneToZombification(true);
        		}
    	}
    	if(st == StructureType.BASTION_REMNANT) {
    			ItemStack head = new ItemStack(Material.IRON_HELMET);
    			ItemStack chest = new ItemStack(Material.IRON_CHESTPLATE);
    			ItemStack leg = new ItemStack(Material.IRON_LEGGINGS);;
    			ItemStack boots = new ItemStack(Material.IRON_BOOTS);

    			String reg = lang.contains("kr") ? "데스나이트":"Death Knight";
    			mnpc(w,lel, EntityType.WITHER_SKELETON, "bastionremnant", reg,head,chest,leg,boots,new ItemStack(Material.NETHERITE_SWORD),new ItemStack(Material.SHIELD));
    		
    	}
    	if(st == StructureType.END_CITY) {
    			ItemStack head = new ItemStack(Material.DRAGON_HEAD);
    			ItemStack chest = new ItemStack(Material.LEATHER_CHESTPLATE);
    			LeatherArmorMeta chm = (LeatherArmorMeta) chest.getItemMeta();
    			chm.setColor(Color.BLACK);
    			chest.setItemMeta(chm);
    			ItemStack leg = new ItemStack(Material.LEATHER_LEGGINGS);
    			LeatherArmorMeta lem = (LeatherArmorMeta) leg.getItemMeta();
    			lem.setColor(Color.BLACK);
    			leg.setItemMeta(lem);
    			ItemStack boots = new ItemStack(Material.LEATHER_BOOTS);
    			LeatherArmorMeta bom = (LeatherArmorMeta) boots.getItemMeta();
    			bom.setColor(Color.BLACK);
    			boots.setItemMeta(bom);

    			String reg = lang.contains("kr") ? "드래곤 나이트":"Dragon Knight";
    			mnpc(w,lel, EntityType.STRAY, "endcity", reg,head,chest,leg,boots,new ItemStack(Material.DRAGON_BREATH),new ItemStack(Material.NETHERITE_SWORD));
    		
    	}
		
		
    	return;
	}


}