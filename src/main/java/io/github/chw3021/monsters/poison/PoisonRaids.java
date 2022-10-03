package io.github.chw3021.monsters.poison;

import java.util.Arrays;
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
import org.bukkit.entity.Illusioner;
import org.bukkit.entity.Player;
import org.bukkit.entity.Skeleton;
import org.bukkit.entity.Vindicator;
import org.bukkit.entity.Witch;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;
import io.github.chw3021.commons.Holding;
import io.github.chw3021.monsters.raids.Summoned;
import io.github.chw3021.rmain.RMain;


public class PoisonRaids extends Summoned {

	/**
	 * 
	 */
	private static transient final long serialVersionUID = 490515503598935525L;
	/**
	 * 
	 */
	
	private static final PoisonRaids instance = new PoisonRaids ();
	public static PoisonRaids getInstance()
	{
		return instance;
		
	}
	String META = "poison";
	
	
	public void PoisonCombo(EntityDeathEvent d) 
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
	
	final private ItemStack mobhead() {
		ItemStack pe = new ItemStack(Material.NETHERITE_HELMET);
		ItemMeta im = pe.getItemMeta();
		im.setLore(Arrays.asList("Poison Mob"));
		pe.setItemMeta(im);
		return pe;
	}
	final private ItemStack mobchest() {
		ItemStack pe = new ItemStack(Material.NETHERITE_CHESTPLATE);
		ItemMeta im = pe.getItemMeta();
		im.setLore(Arrays.asList("Poison Mob"));
		pe.setItemMeta(im);
		return pe;
	}
	final private ItemStack mobboots() {
		ItemStack pe = new ItemStack(Material.NETHERITE_BOOTS);
		ItemMeta im = pe.getItemMeta();
		im.setLore(Arrays.asList("Poison Mob"));
		pe.setItemMeta(im);
		return pe;
	}
	final private ItemStack bosshead() {
		ItemStack pe = new ItemStack(Material.NETHERITE_HELMET);
		ItemMeta im = pe.getItemMeta();
		im.setLore(Arrays.asList("Poison Boss"));
		pe.setItemMeta(im);
		return pe;
	}
	final private ItemStack bosschest() {
		ItemStack pe = new ItemStack(Material.NETHERITE_CHESTPLATE);
		ItemMeta im = pe.getItemMeta();
		im.setLore(Arrays.asList("Poison Boss"));
		pe.setItemMeta(im);
		return pe;
	}
	final private ItemStack bossboots() {
		ItemStack pe = new ItemStack(Material.NETHERITE_BOOTS);
		ItemMeta im = pe.getItemMeta();
		im.setLore(Arrays.asList("Poison Boss"));
		pe.setItemMeta(im);
		return pe;
	}
	
	final private void Mob1(Location spl, String rn) {
    	Random random=new Random();
    	double number = (random.nextDouble()+1.5) * 2.5 * (random.nextBoolean() ? -1 : 1);
    	double number2 = (random.nextDouble()+1.5) * 2.5 * (random.nextBoolean() ? -1 : 1);
    	Location esl = spl.clone().add(number, 2.5, number2);
    	ItemStack head = mobhead();
		ItemStack chest = mobchest();
		ItemStack leg = new ItemStack(Material.NETHERITE_LEGGINGS);
		ItemStack boots = mobboots();
		ItemStack main = new ItemStack(Material.BOW);
		ItemMeta mm = main.getItemMeta();
		mm.setCustomModelData(2011);
		main.setItemMeta(mm);
		ItemStack off = new ItemStack(Material.OBSIDIAN);
		String reg = lang.equalsIgnoreCase("ko_kr") ? "강화형소총수":"VenomRifleman";
		Skeleton newmob = (Skeleton) Summon(esl, ChatColor.DARK_GREEN+reg + "<"+rn+">", 26000.0, head, chest, leg, boots, main, off, EntityType.SKELETON);
		
		newmob.setMetadata("summoned", new FixedMetadataValue(RMain.getInstance(), rn));
		
		newmob.setMetadata(META, new FixedMetadataValue(RMain.getInstance(), true));
		newmob.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.3);
		newmob.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), true));
		newmob.setMetadata("Rifleman", new FixedMetadataValue(RMain.getInstance(), true));
		
		
		newmob.setLootTable(null);
		addraider(rn,META,newmob);
	
	}
	final private void Mob2(Location spl, String rn) {
    	Random random=new Random();
    	double number = (random.nextDouble()+1.5) * 2.5 * (random.nextBoolean() ? -1 : 1);
    	double number2 = (random.nextDouble()+1.5) * 2.5 * (random.nextBoolean() ? -1 : 1);
    	Location esl = spl.clone().add(number, 2.5, number2);
    	ItemStack head = mobhead();
		ItemStack chest = mobchest();
		ItemStack leg = new ItemStack(Material.NETHERITE_LEGGINGS);
		ItemStack boots = mobboots();
		ItemStack main = new ItemStack(Material.NETHERITE_SWORD);
		ItemMeta offm = main.getItemMeta();
		offm.setCustomModelData(3011);
		main.setItemMeta(offm);
		main.addUnsafeEnchantment(Enchantment.FIRE_ASPECT, 3);

		ItemStack off = new ItemStack(Material.SHIELD);

		String reg = lang.equalsIgnoreCase("ko_kr") ? "강화형전사":"VenomSoldier";
		Skeleton newmob = (Skeleton) Summon(esl, ChatColor.DARK_GREEN+reg + "<"+rn+">", 26500.0, head, chest, leg, boots, main, off, EntityType.SKELETON);
		
		
		newmob.setMetadata("summoned", new FixedMetadataValue(RMain.getInstance(), rn));
		
		
		newmob.setMetadata(META, new FixedMetadataValue(RMain.getInstance(), true));
		newmob.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.3);
		newmob.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE).setBaseValue(1);
		newmob.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), true));
		
		
		newmob.setLootTable(null);
		
		addraider(rn,META,newmob);
		
		
	}
	final private void Mob3(Location spl, String rn) {
    	Random random=new Random();
    	double number = (random.nextDouble()+1.5) * 2.5 * (random.nextBoolean() ? -1 : 1);
    	double number2 = (random.nextDouble()+1.5) * 2.5 * (random.nextBoolean() ? -1 : 1);
    	Location esl = spl.clone().add(number, 2.5, number2);
    	ItemStack head = mobhead();
		ItemStack chest = mobchest();
		ItemStack leg = new ItemStack(Material.NETHERITE_LEGGINGS);
		ItemStack boots = mobboots();
		ItemStack main = new ItemStack(Material.FLINT_AND_STEEL);
		ItemStack off = new ItemStack(Material.TORCH);
		
		String reg = lang.equalsIgnoreCase("ko_kr") ? "방화범":"Arsonist";
		Evoker newmob = (Evoker) Summon(esl, ChatColor.DARK_GREEN+reg + "<"+rn+">", 27000.0, head, chest, leg, boots, main, off, EntityType.EVOKER);

		newmob.setCanJoinRaid(false);
		newmob.setPatrolLeader(false);
		newmob.setPatrolTarget(null);
		newmob.setMetadata("summoned", new FixedMetadataValue(RMain.getInstance(), rn));
		newmob.setMetadata("Arsonist", new FixedMetadataValue(RMain.getInstance(), rn));
		
		newmob.setMetadata(META, new FixedMetadataValue(RMain.getInstance(), true));
		newmob.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.3);
		newmob.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), true));
		
		
		newmob.setLootTable(null);
		addraider(rn,META,newmob);
		
	}
	final private void Mob4(Location spl, String rn) {
    	Random random=new Random();
    	double number = (random.nextDouble()+1.5) * 2.5 * (random.nextBoolean() ? -1 : 1);
    	double number2 = (random.nextDouble()+1.5) * 2.5 * (random.nextBoolean() ? -1 : 1);
    	Location esl = spl.clone().add(number, 2.5, number2);
		
		String reg = lang.equalsIgnoreCase("ko_kr") ? "맹독폭탄투척병":"PoisonBomber";
		Witch newmob = (Witch) Summon(esl, ChatColor.DARK_GREEN+reg + "<"+rn+">", 26000.0, null, null, null, null, null, null, EntityType.WITCH);
		newmob.setCanJoinRaid(false);
		newmob.setPatrolLeader(false);
		newmob.setPatrolTarget(null);
		newmob.setMetadata("summoned", new FixedMetadataValue(RMain.getInstance(),rn));
		
		newmob.setMetadata(META, new FixedMetadataValue(RMain.getInstance(), true));
		newmob.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), true));
		newmob.setMetadata("PoisonGrenadier", new FixedMetadataValue(RMain.getInstance(), true));
		
		
		newmob.setLootTable(null);
		addraider(rn,META,newmob);
		
	}
	final private void Mob5(Location spl, String rn) {
    	Random random=new Random();
    	double number = (random.nextDouble()+1.5) * 2.5 * (random.nextBoolean() ? -1 : 1);
    	double number2 = (random.nextDouble()+1.5) * 2.5 * (random.nextBoolean() ? -1 : 1);
    	Location esl = spl.clone().add(number, 2.5, number2);
    	
    	ItemStack head = mobhead();
		ItemStack chest = mobchest();
		ItemStack leg = new ItemStack(Material.NETHERITE_LEGGINGS);
		ItemStack boots = mobboots();
		
		ItemStack main = new ItemStack(Material.BOW);
		ItemMeta offm = main.getItemMeta();
		offm.setCustomModelData(2011);
		main.setItemMeta(offm);
		main.addUnsafeEnchantment(Enchantment.FIRE_ASPECT, 3);
		ItemStack off = new ItemStack(Material.DEEPSLATE_IRON_ORE);
		
		String reg = lang.equalsIgnoreCase("ko_kr") ? "기관총난사범":"MachineGunman";
		Skeleton newmob = (Skeleton) Summon(esl, ChatColor.DARK_GREEN+reg + "<"+rn+">", 25500.0, head, chest, leg, boots, main, off, EntityType.SKELETON);
		newmob.setConversionTime(-1);
		
		newmob.setMetadata("summoned", new FixedMetadataValue(RMain.getInstance(), rn));
		newmob.setMetadata("MachineGunman", new FixedMetadataValue(RMain.getInstance(), rn));
		
		newmob.setMetadata(META, new FixedMetadataValue(RMain.getInstance(), true));
		
		newmob.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.35);
		newmob.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), true));
		
		newmob.setLootTable(null);
		
		addraider(rn,META,newmob);
	}
	final private void Mob6(Location spl, String rn) {
    	Random random=new Random();
    	double number = (random.nextDouble()+1.5) * 2.5 * (random.nextBoolean() ? -1 : 1);
    	double number2 = (random.nextDouble()+1.5) * 2.5 * (random.nextBoolean() ? -1 : 1);
    	Location esl = spl.clone().add(number, 2.5, number2);

    	ItemStack head = mobhead();
		
		String reg = lang.contains("kr") ? "독가스살포병":"GasSprayer";
		Illusioner newmob = (Illusioner) Summon(esl, reg, 26500.0, head, null, null, null, null,
				null, EntityType.ILLUSIONER);
		newmob.setCanJoinRaid(false);
		newmob.setPatrolLeader(false);
		newmob.setPatrolTarget(null);
		
		newmob.setMetadata("summoned", new FixedMetadataValue(RMain.getInstance(), rn));
		newmob.setMetadata("GasSprayer", new FixedMetadataValue(RMain.getInstance(), rn));
		
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
    	Location esl = spl.clone().add(number, 2.5, number2);
    	ItemStack head = bosshead();
    	
		ItemStack main = new ItemStack(Material.NETHERITE_AXE);
		
		String reg = lang.equalsIgnoreCase("ko_kr") ? "강화형돌격병":"VenomCharger";
		Vindicator newmob = (Vindicator) Summon(esl, ChatColor.DARK_GREEN+reg + "<"+rn+">", 27000.0, head, null, null, null, main, main, EntityType.VINDICATOR);
		newmob.setCanJoinRaid(false);
		newmob.setPatrolLeader(false);
		newmob.setPatrolTarget(null);
		newmob.setMetadata("summoned", new FixedMetadataValue(RMain.getInstance(),rn));
		
		newmob.setMetadata(META, new FixedMetadataValue(RMain.getInstance(), true));
		newmob.setMetadata("VenomSuicider", new FixedMetadataValue(RMain.getInstance(), true));
		newmob.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), true));
		
		newmob.setLootTable(null);
		addraider(rn,META,newmob);
		
	}
	@SuppressWarnings("unchecked")
	final private void Boss(Location spl, String rn) {

    	Random random=new Random();
    	double number = (random.nextDouble()+1.5) * 1 * (random.nextBoolean() ? -1 : 1);
    	double number2 = (random.nextDouble()+1.5) * 1 * (random.nextBoolean() ? -1 : 1);
    	Location esl = spl.clone().add(number, 1.1, number2);

    	ItemStack head =  bosshead();
		ItemStack chest = bosschest();
		ItemStack leg = new ItemStack(Material.NETHERITE_LEGGINGS);
		ItemStack boots = bossboots();
		ItemStack main = new ItemStack(Material.BOW);
		ItemMeta offm = main.getItemMeta();
		offm.setCustomModelData(2011);
		main.setItemMeta(offm);
		main.addUnsafeEnchantment(Enchantment.FIRE_ASPECT, 3);
		ItemStack off = new ItemStack(Material.BREWING_STAND);

		
		String reg = lang.equalsIgnoreCase("ko_kr") ? "테러리스트":"Terrorist";
		Skeleton newmob = (Skeleton) Summon(esl, ChatColor.DARK_GREEN + reg, 100000.0, head,
				chest, leg, boots, main, off, EntityType.SKELETON);

		newmob.setConversionTime(-1);
		newmob.setLootTable(null);
		newmob.setGlowing(true);
		newmob.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.36);
		newmob.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE).setBaseValue(1);
		newmob.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(4);
		
		addraider(rn,META,newmob);
		newmob.setMetadata(META+"boss", new FixedMetadataValue(RMain.getInstance(), true));
		newmob.setMetadata("boss", new FixedMetadataValue(RMain.getInstance(), true));
		newmob.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), true));
		newmob.setMetadata(META, new FixedMetadataValue(RMain.getInstance(), true));
		newmob.setMetadata("summoned", new FixedMetadataValue(RMain.getInstance(), rn));
		newmob.setMetadata("summonedboss", new FixedMetadataValue(RMain.getInstance(), rn));
		newmob.setRemoveWhenFarAway(false);
		
		BossBar	newbar = Bukkit.getServer().createBossBar(new NamespacedKey(RMain.getInstance(), rn +"Terrorist"),newmob.getName(), BarColor.GREEN, BarStyle.SEGMENTED_20, BarFlag.CREATE_FOG);
        newbar.setVisible(true);
		raidbar.put(rn, META, newbar);
		newmob.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.36);
		newmob.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE).setBaseValue(1);
		newmob.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), true));
		
		addraider(rn,META,newmob);
		
	
		final Object ht = getherotype(rn);
	
		if(ht instanceof Player) {
			Player p = (Player) ht;
			int task = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(RMain.getInstance(), new Runnable() {
	            @Override
	            public void run() 
	            {
	
					if(Holding.ale(newmob)!=null) {
	                	raidbar.get(rn, META).setProgress(Holding.ale(newmob).getHealth()/100000d);
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
	                	raidbar.get(rn, META).setProgress(Holding.ale(newmob).getHealth()/25000d);
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
