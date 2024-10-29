package io.github.chw3021.obtains;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.HeightMap;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Registry;
import org.bukkit.World;
import org.bukkit.World.Environment;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Axolotl;
import org.bukkit.entity.Drowned;
import org.bukkit.entity.ElderGuardian;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Husk;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Piglin;
import org.bukkit.entity.Player;
import org.bukkit.entity.Skeleton;
import org.bukkit.entity.Snowman;
import org.bukkit.entity.Turtle;
import org.bukkit.entity.Villager;
import org.bukkit.entity.WanderingTrader;
import org.bukkit.entity.Axolotl.Variant;
import org.bukkit.entity.Villager.Profession;
import org.bukkit.entity.Villager.Type;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.server.PluginEnableEvent;
import org.bukkit.event.world.AsyncStructureGenerateEvent;
import org.bukkit.event.world.LootGenerateEvent;
import org.bukkit.event.world.WorldLoadEvent;
import org.bukkit.event.world.WorldUnloadEvent;
import org.bukkit.generator.structure.GeneratedStructure;
import org.bukkit.generator.structure.Structure;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.loot.LootTable;
import org.bukkit.loot.LootTables;
import org.bukkit.loot.Lootable;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;
import org.jetbrains.annotations.Nullable;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Table;

import io.github.chw3021.commons.ConfigManager;
import io.github.chw3021.rmain.RMain;

public class NPCLoc implements Serializable, Listener{


	
	/**
	 * 
	 */
	private transient static final long serialVersionUID = 4534915477449837108L;
	/**
	 * 
	 */
	//public HashMultimap<UUID, HashMap<Location, String>> Locs = HashMultimap.create();
	public Table<UUID, Location, ItemStack[]> Locs = HashBasedTable.create();
	static public HashSet<Location> NPCLocs = new HashSet<Location>();
	static public HashSet<Location> ChestLocs = new HashSet<Location>();
	
    // Can be used for saving
    public NPCLoc(Table<UUID, Location, ItemStack[]> Locs) {
    	this.Locs = Locs;
    	}
    // Can be used for loading
    public NPCLoc(NPCLoc loadedData) {
    	this.Locs = loadedData.Locs;
    	}
 
	public NPCLoc saveData(String filePath) {
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
    public static NPCLoc loadData(String filePath) {
        try {
            BukkitObjectInputStream in = new BukkitObjectInputStream(new GZIPInputStream(new FileInputStream(filePath)));
            NPCLoc data = (NPCLoc) in.readObject();
            in.close();
            return data;
        } catch (ClassNotFoundException | IOException |NullPointerException e) {
            // TODO Auto-generated catch block


            String path = new File("").getAbsolutePath();
            NPCLoc data = new NPCLoc(HashBasedTable.create()).saveData(path +"/plugins/RPGskills/NPCLoc.data");
            Bukkit.getServer().getLogger().log(Level.INFO, "Data Saved");
            return data;
        }
    }
    
    public static NPCLoc getLocsdata(){
        String path = new File("").getAbsolutePath();
        NPCLoc data = new NPCLoc(NPCLoc.loadData(path +"/plugins/RPGskills/NPCLoc.data"));
		return data;
	}
	final public static void saver(Player p, Location sl, ItemStack[] is) {

		Table<UUID, Location, ItemStack[]> Locs = getLocsdata().Locs;
		Locs.put(p.getUniqueId(), sl, is);
        String path = new File("").getAbsolutePath();
		new NPCLoc(Locs).saveData(path +"/plugins/RPGskills/NPCLoc.data");
	}
	

	
	@EventHandler	
	public void spawning(LootGenerateEvent ev) 
	{
		final World w = ev.getWorld();
		if(w.hasMetadata("fake")||w.hasMetadata("rpgraidworld") || !w.canGenerateStructures() || disabledWorlds.contains(w.getName())) {
			return;
		}
		String structureKey = ev.getLootTable().getKey().getKey();
		if(ev.getInventoryHolder() == null) {
			if(ev.getEntity() instanceof ElderGuardian && !ev.getEntity().hasMetadata("rpgspawned")) {

				Location l = ev.getEntity().getLocation();
				ev.setCancelled(true);
				Block b = l.getBlock();
				b.setType(Material.CHEST);
				b.setMetadata("structureChest", new FixedMetadataValue(RMain.getInstance(),l.toString()));
				Chest cstate = (Chest) b.getState();
				cstate.setLootTable(ev.getLootTable());
				cstate.setCustomName("ElderGuaridan Chest");
				ChestLocs.add(l);
			}
		}
		
		InventoryHolder ih = ev.getInventoryHolder();
		
		Location l = ih.getInventory().getLocation();
		ev.setCancelled(true);

		if(ChestLocs.add(l)) {
			Spawn(l,structureKey);
		}
		
		l.getBlock().setMetadata("structureChest", new FixedMetadataValue(RMain.getInstance(),l.toString()));
		
		if(structureKey.contains("trial")) {
			return;
		}

		Table<UUID, Location, ItemStack[]> data = getLocsdata().Locs;

		if(structureKey.contains("buried_treasure")) {
			return;
		}
		List<HumanEntity> viewers = new ArrayList<>(ih.getInventory().getViewers());
		for (HumanEntity he : viewers) {
		    Player p = (Player) he;
		    p.closeInventory();
		    
		    if (!data.contains(p.getUniqueId(), l)) {
		        saver(p, l, ih.getInventory().getContents());
		    	data = getLocsdata().Locs;
		    }
		    
		    Inventory inv = Bukkit.createInventory(p, 54, p.getName());
		    inv.setContents(data.get(p.getUniqueId(), l));
		    p.openInventory(inv);
		}
		
	}

	@EventHandler	
	public void Close(InventoryCloseEvent d) 
	{
		Inventory ci = d.getInventory();
		Player p = (Player) d.getPlayer();
		if(ci.getLocation().getBlock() != null && ci.getLocation().getBlock().hasMetadata("structureChest")) {
			saver(p,ci.getLocation(),ci.getContents());
		}
	}

	@EventHandler	
	public void Break(BlockBreakEvent d) 
	{
		if(d.getBlock().getState() instanceof Chest) {
			Chest c = (Chest) d.getBlock().getState();
			if(c.hasMetadata("structureChest")) {
				d.setCancelled(true);
			}
		}
	}
	
	@EventHandler	
	public void despawning(WorldUnloadEvent ev) 
	{
		World w = ev.getWorld();
		if(w.hasMetadata("fake")||w.hasMetadata("rpgraidworld") || !w.canGenerateStructures() || !w.canGenerateStructures()) {
			return;
		}
		
		/*if(NPCLocs.containsKey(w.getUID())) {
			NPCLocs.get(w.getUID()).forEach(l -> {
				for(Entity e : l.getChunk().getEntities()) {
					e.remove();
				};
			});
		}*/
		npcloc.keySet().forEach(uid ->{
			Entity entity = Bukkit.getEntity(uid);
			if (entity != null) {
			    entity.remove();
			}
		});
	}
	
	
	public static HashMap<UUID,Location> npcloc = new HashMap<UUID,Location>();
	private static HashMap<Location,String> npcsloc = new HashMap<Location,String> ();
	public static HashMap<Location,Location> pil = new HashMap<Location,Location> ();


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
		final World w = l.getWorld();
		HashSet<Location> lhs = new HashSet<>();
		Location tl = w.getHighestBlockAt(l.clone(), HeightMap.OCEAN_FLOOR).getLocation().add(0, 1, 0);
		
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
				Location al = w.getHighestBlockAt(tl.clone().add(50, 0, 50), HeightMap.OCEAN_FLOOR).getLocation().add(0, 1, 0);
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
		return NPCLocs.add(tl) ? tl : null;
	}
	
	final private Villager vnpc(World w, Location l, String meta, String name,  @Nullable  Type vt,  @Nullable  Profession pr) {
	
		if(npcsloc.containsKey(l) && npcsloc.get(l).equalsIgnoreCase(meta)) {
			return null;
		}
		else {
			
			Location tl = lfd(l,meta);
			if(tl == null) {
				return null;
			}
			npcsloc.put(l, meta);
			final Villager v = (Villager) w.spawn(tl, Villager.class, ve ->{
				npcloc.put(ve.getUniqueId(),l);
				ve.setPersistent(false);
				ve.setGlowing(true);
				ve.setCustomName(name);
				ve.setCustomNameVisible(true);
				ve.setMetadata(meta, new FixedMetadataValue(RMain.getInstance(), true));
				ve.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), true));
				ve.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
				ve.setMetadata("obnpc", new FixedMetadataValue(RMain.getInstance(), true));
				ve.setPersistent(true);
				((Lootable) ve).setLootTable(null);
			});
			v.setAI(false);
			v.setInvulnerable(false);
			v.setCollidable(false);
			v.getEquipment().setBootsDropChance(0);
			v.getEquipment().setChestplateDropChance(0);
			v.getEquipment().setHelmetDropChance(0);
			v.getEquipment().setItemInMainHandDropChance(0);
			v.getEquipment().setItemInOffHandDropChance(0);
			v.getEquipment().setLeggingsDropChance(0);
			v.setRemoveWhenFarAway(false);


			v.setAdult();
			if(vt != null) {
				v.setVillagerType(vt);
			}
			if(pr != null) {
				v.setProfession(pr);
			}
			return v;
			
		}
	}

	final private LivingEntity mnpc(World w, Location l, EntityType type, String meta, @Nullable String name) {

		if(npcsloc.containsKey(l) && npcsloc.get(l).equalsIgnoreCase(meta)) {
			return null;
		}
		else {
			Location tl = lfd(l,meta);
			if(tl == null) {
				return null;
			}
			npcsloc.put(l, meta);
			final LivingEntity v = (LivingEntity) w.spawn(tl, type.getEntityClass(), ve ->{
				npcloc.put(ve.getUniqueId(),l);
				ve.setPersistent(false);
				ve.setGlowing(true);
				ve.setCustomName(name);
				ve.setCustomNameVisible(true);
				ve.setMetadata(meta, new FixedMetadataValue(RMain.getInstance(), true));
				ve.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), true));
				ve.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
				ve.setMetadata("obnpc", new FixedMetadataValue(RMain.getInstance(), true));
				ve.setPersistent(true);
				((Lootable) ve).setLootTable(null);
			});
			v.setAI(false);
			v.setInvulnerable(false);
			v.setCollidable(false);
			v.getEquipment().setBootsDropChance(0);
			v.getEquipment().setChestplateDropChance(0);
			v.getEquipment().setHelmetDropChance(0);
			v.getEquipment().setItemInMainHandDropChance(0);
			v.getEquipment().setItemInOffHandDropChance(0);
			v.getEquipment().setLeggingsDropChance(0);
			v.setRemoveWhenFarAway(false);

			
			return v;
		}
	}

	final private LivingEntity mnpc(World w, Location l, EntityType type, String meta, @Nullable String name, @Nullable ItemStack head, @Nullable ItemStack chest, @Nullable ItemStack leg, @Nullable ItemStack boots,@Nullable ItemStack main,@Nullable ItemStack off) {

		if(npcsloc.containsKey(l) && npcsloc.get(l).equalsIgnoreCase(meta)) {
			return null;
		}
		else {
			Location tl = lfd(l,meta);
			if(tl == null) {
				return null;
			}
			npcsloc.put(l, meta);
			final LivingEntity v = (LivingEntity) w.spawn(tl, type.getEntityClass(), ve ->{
				npcloc.put(ve.getUniqueId(),l);
				ve.setPersistent(false);
				ve.setGlowing(true);
				ve.setCustomName(name);
				ve.setCustomNameVisible(true);
				ve.setMetadata(meta, new FixedMetadataValue(RMain.getInstance(), true));
				ve.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), true));
				ve.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), true));
				ve.setMetadata("obnpc", new FixedMetadataValue(RMain.getInstance(), true));
				ve.setPersistent(true);
				((Lootable) ve).setLootTable(null);
			});
			v.setAI(false);
			v.setInvulnerable(false);
			v.setCollidable(false);
			v.getEquipment().setBootsDropChance(0);
			v.getEquipment().setChestplateDropChance(0);
			v.getEquipment().setHelmetDropChance(0);
			v.getEquipment().setItemInMainHandDropChance(0);
			v.getEquipment().setItemInOffHandDropChance(0);
			v.getEquipment().setLeggingsDropChance(0);
			v.setRemoveWhenFarAway(false);


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
			return v;
		}
	}


	protected final String lang = ConfigManager.getInstance(RMain.getInstance()).getCustomConfig().getString("Language");
	List<String> disabledWorlds = ConfigManager.getInstance(RMain.getInstance()).getCustomConfig().getStringList("Worlds");
	
	
	final public void Spawn(final Location lel, String ns) {

		final World w = lel.getWorld();
		
    	if(ns.contains("abandoned_mineshaft")) {
			String reg = lang.contains("kr") ? "길잃은 광부":"Stray Miner";
    		Villager v = vnpc(w,lel, "mineshaft",reg, Type.DESERT, Profession.ARMORER);
    		if(v != null) {
        		v.getEquipment().setHelmet(new ItemStack(Material.GOLDEN_HELMET));
        		v.setVillagerLevel(5);
        		v.setBreed(false);
    		}
    		
    	}
    	else if(ns.contains("buried_treasure")) {
			ItemStack head = new ItemStack(Material.CHEST);
			head.addUnsafeEnchantment(Enchantment.BINDING_CURSE, 1);
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
    	else if(ns.contains("pillager_outpost")) {
			String reg = lang.contains("kr") ? "약탈당한 상인":"Looted Trader";
    		WanderingTrader v = (WanderingTrader) mnpc(w,lel, EntityType.WANDERING_TRADER, "pillageroutpost", reg);
    		if(v != null) {
        		v.setAdult();
        		v.setAgeLock(true);
        		v.setBreed(false);
        		v.setDespawnDelay(-1);
    		}
    	}
    	else if(ns.contains("stronghold")) {

			String reg = lang.contains("kr") ? "요새 청소부":"Stronghold Cleaner";
    		Villager v = vnpc(w,lel, "stronghold",reg, Type.SNOW, Profession.BUTCHER);
    		if(v != null) {
        		v.setVillagerLevel(5);
    		}
    	}
    	else if(ns.contains("ruined_portal")) {

    			ItemStack head = new ItemStack(Material.CRYING_OBSIDIAN);
    			head.addUnsafeEnchantment(Enchantment.BINDING_CURSE, 1);
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
		

    	else if(ns.contains("woodland_mansion")) {
        		
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

    	else if(ns.contains("ancient_city")) {

			String reg = lang.contains("kr") ? "성직자":"Priest";
    		Villager v = vnpc(w,lel, "ancient",reg, Type.SWAMP, Profession.CLERIC);
    		if(v != null) {
        		v.setVillagerLevel(5);
    		}
    		
    	}
    	else if(ns.contains("igloo_chest")) {
    		
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
    			Snowman v = (Snowman) mnpc(w,lel, EntityType.SNOW_GOLEM, "igloo", reg,head,chest,leg,boots,null,null);
        		if(v != null) {
            		v.setDerp(true);
        		}
    	}

    	else if(ns.contains("desert_pyramid")) {
    		
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
    	else if(ns.contains("jungle_temple")) {

			String reg = lang.contains("kr") ? "고고학자":"Archaeologist";
    		Villager v = vnpc(w,lel, "junglepyramid" ,reg, Type.JUNGLE, Profession.LIBRARIAN);
    		if(v != null) {
        		v.setVillagerLevel(5);
    		}
    	}
    	else if(ns.contains("underwater_ruin_small")) {
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
    	else if(ns.contains("shipwreck")) {
        		
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
    	else if(ns.contains("underwater_ruin_big")) {
    		
        		lel.getWorld().getNearbyEntities(lel, 100, 100, 100).forEach(e -> {
        			if(!e.hasMetadata("rpgspawned") && !e.hasMetadata("fake") && (e.getType() == EntityType.GUARDIAN)) {
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
    	else if(ns.contains("nether_bridge")) {
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
    	else if(ns.contains("bastion_treasure")) {
    			ItemStack head = new ItemStack(Material.IRON_HELMET);
    			ItemStack chest = new ItemStack(Material.IRON_CHESTPLATE);
    			ItemStack leg = new ItemStack(Material.IRON_LEGGINGS);;
    			ItemStack boots = new ItemStack(Material.IRON_BOOTS);

    			String reg = lang.contains("kr") ? "데스나이트":"Death Knight";
    			mnpc(w,lel, EntityType.WITHER_SKELETON, "bastionremnant", reg,head,chest,leg,boots,new ItemStack(Material.NETHERITE_SWORD),new ItemStack(Material.SHIELD));
    		
    	}
    	else if(ns.contains("buried_treasure")) {
    			ItemStack head = new ItemStack(Material.DRAGON_HEAD);
    			ItemStack chest = new ItemStack(Material.LEATHER_CHESTPLATE);
    			LeatherArmorMeta chm = (LeatherArmorMeta) chest.getItemMeta();
    			chm.setColor(Color.PURPLE);
    			chest.setItemMeta(chm);
    			ItemStack leg = new ItemStack(Material.LEATHER_LEGGINGS);
    			LeatherArmorMeta lem = (LeatherArmorMeta) leg.getItemMeta();
    			lem.setColor(Color.PURPLE);
    			leg.setItemMeta(lem);
    			ItemStack boots = new ItemStack(Material.LEATHER_BOOTS);
    			LeatherArmorMeta bom = (LeatherArmorMeta) boots.getItemMeta();
    			bom.setColor(Color.PURPLE);
    			boots.setItemMeta(bom);

    			String reg = lang.contains("kr") ? "드래곤 사냥꾼":"Dragon Hunter";
    			mnpc(w,lel, EntityType.DROWNED, "endcity", reg,head,chest,leg,boots,new ItemStack(Material.CROSSBOW),new ItemStack(Material.RED_BED));
    		
    	}
		

		w.getNearbyEntities(lel.clone(),4,4,4).forEach(e -> {
			if(!(e instanceof Player) && !e.hasMetadata("untargetable") && !e.hasMetadata("obnpc")) {
				e.remove();
			}
		});
    	return;
	}
	
}