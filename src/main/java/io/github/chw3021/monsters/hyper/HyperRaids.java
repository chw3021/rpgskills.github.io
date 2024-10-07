package io.github.chw3021.monsters.hyper;

import java.util.HashSet;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Evoker;
import org.bukkit.entity.Husk;
import org.bukkit.entity.Illusioner;
import org.bukkit.entity.Player;
import org.bukkit.entity.Skeleton;
import org.bukkit.entity.WitherSkeleton;
import org.bukkit.entity.Zombie;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;
import io.github.chw3021.commons.Holding;
import io.github.chw3021.monsters.raids.Summoned;
import io.github.chw3021.rmain.RMain;


public class HyperRaids extends Summoned {

	/**
	 * 
	 */
	private static transient final long serialVersionUID = 6534716276049737277L;
	
	
	private static final HyperRaids instance = new HyperRaids ();
	public static HyperRaids getInstance()
	{
		return instance;
	}

	String META = "hyper";

	

	public void HyperCombo(EntityDeathEvent d) 
	{	
		final Object com = Combo(d,META);

		if(com == null) {
			return;
		}

		final Location l = d.getEntity().getLocation().clone();
		
		if(com instanceof String) {
			String rn = (String) com;
			MobSpawn(l, rn);
		}
		else if(com instanceof HashSet) {
			@SuppressWarnings("unchecked")
			HashSet<String> hs = (HashSet<String>) com;
			hs.forEach(rn -> MobSpawn(l,rn));
		}
	}
	
	final protected void Boss(String rn, String meta, Location esl) {
		super.Boss(rn, meta, esl);
		if(meta.equals(META)) {
			Boss(esl,rn);
		}
	}
	
	
	final private void MobSpawn(final Location spl, final String rn) {

        for(int i = 0; i<SUMMONCOUNT; i++) {
			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
                @Override
                public void run() 
                {
                	if(combot.contains(rn, META)) {
            			Mob1(spl,rn);
            			Mob2(spl,rn);
            			Mob3(spl,rn);
            			Mob4(spl,rn);
            			Mob5(spl,rn);
            			Mob6(spl,rn);
            			Mob7(spl,rn);
                	}
                }
    	   }, i*20);
        }
		
	}

	final private void Mob1(Location spl, String rn) {


    	Random random=new Random();
    	double number = (random.nextDouble()+1.5) * 2.5 * (random.nextBoolean() ? -1 : 1);
    	double number2 = (random.nextDouble()+1.5) * 2.5 * (random.nextBoolean() ? -1 : 1);
    	Location esl = spl.clone().add(number, 0, number2);

    	
    	ItemStack head = new ItemStack(Material.SKELETON_SKULL);
		ItemStack chest = new ItemStack(Material.IRON_CHESTPLATE);
		ItemStack leg = new ItemStack(Material.IRON_LEGGINGS);
		ItemStack boots = new ItemStack(Material.IRON_BOOTS);
		ItemStack main = new ItemStack(Material.BONE);
		ItemStack off = new ItemStack(Material.BONE);
		String reg = lang.equalsIgnoreCase("ko_kr") ? "변이체B":"MutantB";
		Husk newmob = (Husk) Summon(esl, ChatColor.LIGHT_PURPLE+reg + "<"+rn+">", 10000.0, head, chest, leg, boots, main, off, EntityType.HUSK);
		newmob.setAdult();
		newmob.setConversionTime(-1);
		newmob.setMetadata("summoned", new FixedMetadataValue(RMain.getInstance(), rn));
		newmob.setMetadata(META, new FixedMetadataValue(RMain.getInstance(), true));
		newmob.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.3);
		newmob.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE).setBaseValue(1);
		newmob.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), true));
		addraider(rn,META,newmob);

	}


	final private void Mob2(Location spl, String rn) {


    	Random random=new Random();
    	double number = (random.nextDouble()+1.5) * 2.5 * (random.nextBoolean() ? -1 : 1);
    	double number2 = (random.nextDouble()+1.5) * 2.5 * (random.nextBoolean() ? -1 : 1);
    	Location esl = spl.clone().add(number, 0, number2);

    	ItemStack head = new ItemStack(Material.CRYING_OBSIDIAN);
		ItemStack chest = new ItemStack(Material.NETHERITE_CHESTPLATE);
		ItemStack leg = new ItemStack(Material.NETHERITE_LEGGINGS);
		ItemStack boots = new ItemStack(Material.NETHERITE_BOOTS);
		ItemStack main = new ItemStack(Material.NETHERITE_HOE);
		ItemStack off = new ItemStack(Material.TOTEM_OF_UNDYING);
		String reg = lang.equalsIgnoreCase("ko_kr") ? "변이체C":"MutantC";
		Husk newmob = (Husk) Summon(esl, ChatColor.LIGHT_PURPLE+reg + "<"+rn+">", 10000.0, head, chest, leg, boots, main, off, EntityType.HUSK);
		newmob.setAdult();
		newmob.setConversionTime(-1);
		newmob.setMetadata("summoned", new FixedMetadataValue(RMain.getInstance(), rn));
		newmob.setMetadata(META, new FixedMetadataValue(RMain.getInstance(), true));
		newmob.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.3);
		newmob.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(5);
		newmob.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE).setBaseValue(1);
		newmob.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), true));
		addraider(rn,META,newmob);
		
		
	}

	final private void Mob3(Location spl, String rn) {


    	Random random=new Random();
    	double number = (random.nextDouble()+1.5) * 2.5 * (random.nextBoolean() ? -1 : 1);
    	double number2 = (random.nextDouble()+1.5) * 2.5 * (random.nextBoolean() ? -1 : 1);
    	Location esl = spl.clone().add(number, 0, number2);

		ItemStack head = new ItemStack(Material.LIME_STAINED_GLASS);
		head.addUnsafeEnchantment(Enchantment.PROTECTION, 3);
		ItemStack chest = new ItemStack(Material.NETHERITE_CHESTPLATE);
		chest.addUnsafeEnchantment(Enchantment.PROTECTION, 3);
		ItemStack leg = new ItemStack(Material.NETHERITE_LEGGINGS);
		leg.addUnsafeEnchantment(Enchantment.PROTECTION, 3);
		ItemStack boots = new ItemStack(Material.NETHERITE_BOOTS);
		boots.addUnsafeEnchantment(Enchantment.DEPTH_STRIDER, 3);
		ItemStack main = new ItemStack(Material.BOW);
		ItemMeta mm = main.getItemMeta();
		mm.setCustomModelData(2009);
		main.setItemMeta(mm);
		ItemStack off = new ItemStack(Material.BEACON);
		String reg = lang.equalsIgnoreCase("ko_kr") ? "강화형광선궁수":"ReinforcedRayArcher";
		Skeleton newmob = (Skeleton) Summon(esl, getelcolor(META) + reg, 10000.0, head, chest, leg, boots, main, off, EntityType.SKELETON);
		newmob.setConversionTime(-1);
		newmob.setMetadata("summoned", new FixedMetadataValue(RMain.getInstance(), rn));
		newmob.setMetadata(META, new FixedMetadataValue(RMain.getInstance(), true));
		newmob.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.3);
		newmob.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), true));
		newmob.setMetadata("RayArcher", new FixedMetadataValue(RMain.getInstance(), true));
		addraider(rn,META,newmob);
		
	}

	final private void Mob4(Location spl, String rn) {


    	Random random=new Random();
    	double number = (random.nextDouble()+1.5) * 2.5 * (random.nextBoolean() ? -1 : 1);
    	double number2 = (random.nextDouble()+1.5) * 2.5 * (random.nextBoolean() ? -1 : 1);
    	Location esl = spl.clone().add(number, 0, number2);

		ItemStack head = new ItemStack(Material.BLACK_STAINED_GLASS);
		head.addUnsafeEnchantment(Enchantment.PROTECTION, 3);
		ItemStack chest = new ItemStack(Material.NETHERITE_CHESTPLATE);
		chest.addUnsafeEnchantment(Enchantment.PROTECTION, 3);
		ItemStack leg = new ItemStack(Material.NETHERITE_LEGGINGS);
		leg.addUnsafeEnchantment(Enchantment.PROTECTION, 3);
		ItemStack boots = new ItemStack(Material.NETHERITE_BOOTS);
		boots.addUnsafeEnchantment(Enchantment.DEPTH_STRIDER, 3);
		ItemStack main = new ItemStack(Material.NETHERITE_SWORD);
		ItemMeta mm = main.getItemMeta();
		mm.setCustomModelData(3009);
		main.setItemMeta(mm);
		String reg = lang.equalsIgnoreCase("ko_kr") ? "광선검사":"RayKnight";
		Skeleton newmob = (Skeleton) Summon(esl, getelcolor(META) + reg, 10000.0, head, chest, leg, boots, main, main, EntityType.SKELETON);
		newmob.setConversionTime(-1);
		
		newmob.setMetadata("summoned", new FixedMetadataValue(RMain.getInstance(), rn));
		
		newmob.setMetadata(META, new FixedMetadataValue(RMain.getInstance(), true));
		newmob.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.3);
		newmob.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE).setBaseValue(1);
		newmob.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), true));
		addraider(rn,META,newmob);
		
	}



	final private void Mob5(Location spl, String rn) {


    	Random random=new Random();
    	double number = (random.nextDouble()+1.5) * 2.5 * (random.nextBoolean() ? -1 : 1);
    	double number2 = (random.nextDouble()+1.5) * 2.5 * (random.nextBoolean() ? -1 : 1);
    	Location esl = spl.clone().add(number, 0, number2);
    	
    	ItemStack head = new ItemStack(Material.GOLD_BLOCK);
		ItemStack chest = new ItemStack(Material.GOLDEN_CHESTPLATE);
		ItemStack leg = new ItemStack(Material.GOLDEN_LEGGINGS);
		ItemStack boots = new ItemStack(Material.GOLDEN_BOOTS);
		ItemStack main = new ItemStack(Material.GOLDEN_HOE);
		ItemStack off = new ItemStack(Material.ENCHANTED_GOLDEN_APPLE);
		String reg = lang.equalsIgnoreCase("ko_kr") ? "복제된마법사":"ClonedMage";
		Evoker newmob = (Evoker) Summon(esl, ChatColor.LIGHT_PURPLE+reg + "<"+rn+">", 10000.0, head, chest, leg, boots, main, off, EntityType.EVOKER);

		newmob.setPatrolLeader(false);
		newmob.setPatrolTarget(null);
		newmob.setCanJoinRaid(false);
		newmob.setMetadata(META, new FixedMetadataValue(RMain.getInstance(), true));
		newmob.setMetadata("summoned", new FixedMetadataValue(RMain.getInstance(), rn));
		newmob.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.3);
		newmob.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE).setBaseValue(1);
		newmob.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), true));
		addraider(rn,META,newmob);
	}

	final private void Mob6(Location spl, String rn) {


    	Random random=new Random();
    	double number = (random.nextDouble()+1.5) * 2.5 * (random.nextBoolean() ? -1 : 1);
    	double number2 = (random.nextDouble()+1.5) * 2.5 * (random.nextBoolean() ? -1 : 1);
    	Location esl = spl.clone().add(number, 0, number2);

    	ItemStack head = new ItemStack(Material.IRON_BLOCK);
		ItemStack chest1 = new ItemStack(Material.IRON_CHESTPLATE);
		ItemStack leg = new ItemStack(Material.IRON_LEGGINGS);
		ItemStack boots = new ItemStack(Material.IRON_BOOTS);
		ItemStack main = new ItemStack(Material.IRON_BLOCK);
		ItemStack off = new ItemStack(Material.IRON_BLOCK);
		String reg = lang.equalsIgnoreCase("ko_kr") ? "변이체A":"MutantA";
		Zombie newmob = (Zombie) Summon(esl, ChatColor.LIGHT_PURPLE+reg + "<"+rn+">", 10000.0, head, chest1, leg, boots, main, off, EntityType.ZOMBIE);
		newmob.setAdult();
		newmob.setConversionTime(-1);
		newmob.setMetadata("summoned", new FixedMetadataValue(RMain.getInstance(), rn));
		newmob.setMetadata(META, new FixedMetadataValue(RMain.getInstance(), true));
		newmob.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.3);
		newmob.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE).setBaseValue(1);
		newmob.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), true));
		addraider(rn,META,newmob);
		
	}

	final private void Mob7(Location spl, String rn) {


    	Random random=new Random();
    	double number = (random.nextDouble()+1.5) * 2.5 * (random.nextBoolean() ? -1 : 1);
    	double number2 = (random.nextDouble()+1.5) * 2.5 * (random.nextBoolean() ? -1 : 1);
    	Location esl = spl.clone().add(number, 0, number2);

    	ItemStack head = new ItemStack(Material.DIAMOND_BLOCK);
		ItemStack chest = new ItemStack(Material.DIAMOND_CHESTPLATE);
		ItemStack leg = new ItemStack(Material.DIAMOND_LEGGINGS);
		ItemStack boots = new ItemStack(Material.DIAMOND_BOOTS);
		ItemStack main = new ItemStack(Material.NETHERITE_SWORD);
		ItemMeta mm = main.getItemMeta();
		mm.setCustomModelData(3009);
		main.setItemMeta(mm);
		String reg = lang.equalsIgnoreCase("ko_kr") ? "거신":"Colossus";
		WitherSkeleton newmob = (WitherSkeleton) Summon(esl, ChatColor.LIGHT_PURPLE+reg + "<"+rn+">", 10000.0, head, chest, leg, boots, main, main, EntityType.WITHER_SKELETON);
		newmob.setMetadata("summoned", new FixedMetadataValue(RMain.getInstance(), rn));
		
		newmob.setMetadata(META, new FixedMetadataValue(RMain.getInstance(), true));
		newmob.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.3);
		newmob.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), true));
		
		newmob.setAI(true);
		newmob.setAware(true);
		addraider(rn,META,newmob);
		
	}


	@SuppressWarnings("unchecked")
	final private void Boss(Location spl, String rn) {

    	Random random=new Random();
    	double number = (random.nextDouble()+1.5) * 2.5 * (random.nextBoolean() ? -1 : 1);
    	double number2 = (random.nextDouble()+1.5) * 2.5 * (random.nextBoolean() ? -1 : 1);
    	Location esl = spl.clone().add(number, 0, number2);
		ItemStack main = new ItemStack(Material.BOW);
		ItemMeta mm = main.getItemMeta();
		mm.setCustomModelData(2009);
		main.setItemMeta(mm);

		String reg = lang.equalsIgnoreCase("ko_kr") ? "닥터B":"Dr.B";
		Illusioner newmob = (Illusioner) Summon(esl.clone(), ChatColor.DARK_PURPLE+reg + "<"+rn+">", 40000.0, new ItemStack(Material.BLACK_STAINED_GLASS), null, null, null, main, null, EntityType.ILLUSIONER);
		newmob.setCanJoinRaid(false);
		newmob.setPatrolTarget(null);
		newmob.setPatrolLeader(false);
		newmob.setGlowing(true);
		newmob.getEquipment().setBootsDropChance(0);
		newmob.getEquipment().setChestplateDropChance(0);
		newmob.getEquipment().setHelmetDropChance(0);
		newmob.getEquipment().setItemInMainHandDropChance(0);
		newmob.getEquipment().setItemInOffHandDropChance(0);
		newmob.getEquipment().setLeggingsDropChance(0);
		newmob.setMetadata("hyperboss", new FixedMetadataValue(RMain.getInstance(), true));
		newmob.setMetadata(META, new FixedMetadataValue(RMain.getInstance(), true));
		
		newmob.setMetadata("boss", new FixedMetadataValue(RMain.getInstance(), true));
		newmob.setMetadata("summoned", new FixedMetadataValue(RMain.getInstance(), rn));
		newmob.setMetadata("summonedboss", new FixedMetadataValue(RMain.getInstance(), rn));
		newmob.setLootTable(null);
		BossBar	newbar = Bukkit.getServer().createBossBar(new NamespacedKey(RMain.getInstance(), rn +"DrB"),newmob.getName(), BarColor.PURPLE, BarStyle.SEGMENTED_20, BarFlag.CREATE_FOG);
        newbar.setVisible(true);
		raidbar.put(rn, META, newbar);
		newmob.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.36);
		newmob.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE).setBaseValue(1);
		newmob.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), true));
		newmob.setRemoveWhenFarAway(false);
		addraider(rn,META,newmob);

		final Object ht = getherotype(rn);

		if(ht instanceof Player) {
			Player p = (Player) ht;
    		int task = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(RMain.getInstance(), new Runnable() {
                @Override
                public void run() 
                {

					if(Holding.ale(newmob)!=null) {
	                	raidbar.get(rn, META).setProgress(Holding.ale(newmob).getHealth()/40000d);
	                	raidbar.get(rn, META).setTitle(Holding.ale(newmob).getName());
        				raidbar.get(rn, META).addPlayer(p);
					}
                }
			}, 0, 1);
    		raidbart.put(rn, META, task);
		}
		else if(getherotype(rn) instanceof HashSet){
			HashSet<Player> par = (HashSet<Player>) ht;
    		int task = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(RMain.getInstance(), new Runnable() {
                @Override
                public void run() 
                {

					if(Holding.ale(newmob)!=null) {
	                	raidbar.get(rn, META).setProgress(Holding.ale(newmob).getHealth()/40000d);
	                	raidbar.get(rn, META).setTitle(Holding.ale(newmob).getName());
	            		par.forEach(p -> {
            				raidbar.get(rn, META).addPlayer(p);
	            		});
					}
                }
			}, 0, 1);
    		raidbart.put(rn, META, task);
		}
		
	}
}