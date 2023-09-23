package io.github.chw3021.monsters.snow;

import java.util.HashSet;
import java.util.Random;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Drowned;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Evoker;
import org.bukkit.entity.Player;
import org.bukkit.entity.Skeleton;
import org.bukkit.entity.Snowman;
import org.bukkit.entity.Stray;
import org.bukkit.entity.Witch;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.metadata.FixedMetadataValue;
import io.github.chw3021.commons.Holding;
import io.github.chw3021.monsters.raids.Summoned;
import io.github.chw3021.rmain.RMain;


public class SnowRaids extends Summoned {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8152354856668353552L;
	private static final SnowRaids instance = new SnowRaids ();
	public static SnowRaids getInstance()
	{
		return instance;
	}
	String META = "snowy";
	public void SnowCombo(EntityDeathEvent d) 
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
		double number = (random.nextDouble()+1.5) * 2 * (random.nextBoolean() ? -1 : 1);
		double number2 = (random.nextDouble()+1.5) * 2 * (random.nextBoolean() ? -1 : 1);
		Location esl = spl.clone().add(number, 2.5, number2);
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
		String reg = lang.contains("kr") ? "워킹아이스":"WalkingIce";
		Drowned newmob = (Drowned) Summon(esl, ChatColor.AQUA+reg + "<"+rn+">", 800.0, head, chest, leg, boots, new ItemStack(Material.SNOWBALL), new ItemStack(Material.SNOWBALL), EntityType.DROWNED);

		newmob.setMetadata("summoned", new FixedMetadataValue(RMain.getInstance(), rn));
		newmob.setMetadata(META, new FixedMetadataValue(RMain.getInstance(), true));
		newmob.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.3);
		newmob.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), true));
		newmob.setConversionTime(-1);
		
		
		addraider(rn,META,newmob);
	
	}
	final private void Mob2(Location spl, String rn) {
	
		Random random=new Random();
		double number = (random.nextDouble()+1.5) * 2 * (random.nextBoolean() ? -1 : 1);
		double number2 = (random.nextDouble()+1.5) * 2 * (random.nextBoolean() ? -1 : 1);
		Location esl = spl.clone().add(number, 2.5, number2);
		ItemStack head = new ItemStack(Material.FROSTED_ICE);
		head.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
		head.addUnsafeEnchantment(Enchantment.PROTECTION_PROJECTILE, 1);
		ItemStack chest = new ItemStack(Material.LEATHER_CHESTPLATE);
		LeatherArmorMeta chm = (LeatherArmorMeta) chest.getItemMeta();
		chm.setColor(Color.AQUA);
		chest.setItemMeta(chm);
		chest.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
		chest.addUnsafeEnchantment(Enchantment.PROTECTION_PROJECTILE, 1);
		ItemStack leg = new ItemStack(Material.LEATHER_LEGGINGS);
		LeatherArmorMeta lem = (LeatherArmorMeta) leg.getItemMeta();
		lem.setColor(Color.AQUA);
		leg.setItemMeta(lem);
		leg.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
		leg.addUnsafeEnchantment(Enchantment.PROTECTION_PROJECTILE, 1);
		ItemStack boots = new ItemStack(Material.LEATHER_BOOTS);
		LeatherArmorMeta bom = (LeatherArmorMeta) boots.getItemMeta();
		bom.setColor(Color.AQUA);
		boots.setItemMeta(bom);
		boots.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
		boots.addUnsafeEnchantment(Enchantment.PROTECTION_PROJECTILE, 1);
		ItemStack main = new ItemStack(Material.TRIDENT);
		main.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 3);
		ItemMeta mm = main.getItemMeta();
		mm.setCustomModelData(10006);
		main.setItemMeta(mm);
		ItemStack off = new ItemStack(Material.SHIELD);
		off.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 3);
		String reg = lang.equalsIgnoreCase("ko_kr") ? "얼음창기병":"IceAssault";
		Drowned newmob = (Drowned) Summon(esl, ChatColor.AQUA+reg + "<"+rn+">", 800.0, head, chest, leg, boots, main, off, EntityType.DROWNED);

		
		newmob.setMetadata("summoned", new FixedMetadataValue(RMain.getInstance(), rn));
		newmob.setMetadata(META, new FixedMetadataValue(RMain.getInstance(), true));
		newmob.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.3);
		newmob.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE).setBaseValue(1);
		newmob.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), true));
		newmob.setAdult();
		
		addraider(rn,META,newmob);
		
		
	}
	final private void Mob3(Location spl, String rn) {
		Random random=new Random();
		double number = (random.nextDouble()+1.5) * 2 * (random.nextBoolean() ? -1 : 1);
		double number2 = (random.nextDouble()+1.5) * 2 * (random.nextBoolean() ? -1 : 1);
		Location esl = spl.clone().add(number, 2.5, number2);
		ItemStack head = new ItemStack(Material.POWDER_SNOW);
		head.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
		head.addUnsafeEnchantment(Enchantment.PROTECTION_PROJECTILE, 1);
		ItemStack chest = new ItemStack(Material.LEATHER_CHESTPLATE);
		LeatherArmorMeta chm = (LeatherArmorMeta) chest.getItemMeta();
		chm.setColor(Color.WHITE);
		chest.setItemMeta(chm);
		chest.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
		chest.addUnsafeEnchantment(Enchantment.PROTECTION_PROJECTILE, 1);
		ItemStack leg = new ItemStack(Material.LEATHER_LEGGINGS);
		LeatherArmorMeta lem = (LeatherArmorMeta) leg.getItemMeta();
		lem.setColor(Color.WHITE);
		leg.setItemMeta(lem);
		leg.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
		leg.addUnsafeEnchantment(Enchantment.PROTECTION_PROJECTILE, 1);
		ItemStack boots = new ItemStack(Material.LEATHER_BOOTS);
		LeatherArmorMeta bom = (LeatherArmorMeta) boots.getItemMeta();
		bom.setColor(Color.WHITE);
		boots.setItemMeta(bom);
		boots.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
		boots.addUnsafeEnchantment(Enchantment.PROTECTION_PROJECTILE, 1);
		ItemStack main = new ItemStack(Material.NETHERITE_SWORD);
		main.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 3);
		ItemMeta mm = main.getItemMeta();
		mm.setCustomModelData(3006);
		main.setItemMeta(mm);
		String reg = lang.equalsIgnoreCase("ko_kr") ? "설산검사":"SnowBlader";
		Stray newmob = (Stray) Summon(esl, ChatColor.AQUA+reg + "<"+rn+">", 700.0, head, chest, leg, boots, main, null, EntityType.STRAY);

		
		newmob.setMetadata("summoned", new FixedMetadataValue(RMain.getInstance(), rn));
		newmob.setMetadata(META, new FixedMetadataValue(RMain.getInstance(), true));
		newmob.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.3);
		newmob.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE).setBaseValue(1);
		newmob.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), true));
		
		addraider(rn,META,newmob);
		
	}
	final private void Mob4(Location spl, String rn) {
	
		Random random=new Random();
		double number = (random.nextDouble()+1.5) * 2 * (random.nextBoolean() ? -1 : 1);
		double number2 = (random.nextDouble()+1.5) * 2 * (random.nextBoolean() ? -1 : 1);
		Location esl = spl.clone().add(number, 2.5, number2);
		ItemStack head = new ItemStack(Material.BLUE_ICE);
		head.addUnsafeEnchantment(Enchantment.PROTECTION_FIRE, 5);
		head.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
		head.addUnsafeEnchantment(Enchantment.PROTECTION_PROJECTILE, 1);
		ItemStack main = new ItemStack(Material.FROSTED_ICE);
		main.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 5);
		ItemStack off = new ItemStack(Material.ICE);
		main.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 5);
		String reg = lang.equalsIgnoreCase("ko_kr") ? "서리 주술사":"Frost Warlock";
		Evoker newmob = (Evoker) Summon(esl, reg, 650.0, head, null, null, null, main, off, EntityType.EVOKER);
		newmob.setCanJoinRaid(false);
		newmob.setPatrolLeader(false);
		newmob.setPatrolTarget(null);
		
		newmob.setMetadata("summoned", new FixedMetadataValue(RMain.getInstance(), rn));
		newmob.setMetadata(META, new FixedMetadataValue(RMain.getInstance(), true));
		newmob.setMetadata("mage", new FixedMetadataValue(RMain.getInstance(), true));
	
		newmob.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), true));
		
		
		
		
		addraider(rn,META,newmob);
		
	}
	final private void Mob5(Location spl, String rn) {
	
		Random random=new Random();
		double number = (random.nextDouble()+1.5) * 2 * (random.nextBoolean() ? -1 : 1);
		double number2 = (random.nextDouble()+1.5) * 2 * (random.nextBoolean() ? -1 : 1);
		Location esl = spl.clone().add(number, 2.5, number2);
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
		ItemStack main = new ItemStack(Material.NETHERITE_AXE);
		main.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 2);
		ItemMeta mm = main.getItemMeta();
		mm.setCustomModelData(4006);
		main.setItemMeta(mm);
		String reg = lang.contains("kr") ? "서리사냥꾼":"FrostHunter";
		Stray newmob = (Stray) Summon(esl, reg, 650.0, head, null, null, boots, main,null, EntityType.STRAY);

		newmob.setMetadata("summoned", new FixedMetadataValue(RMain.getInstance(),rn));
		newmob.setMetadata(META, new FixedMetadataValue(RMain.getInstance(), true));
		newmob.setMetadata("frosthunter", new FixedMetadataValue(RMain.getInstance(), true));
		newmob.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.26);
		newmob.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), true));
		
		newmob.setPersistent(true);
		
		
		addraider(rn,META,newmob);
	}
	final private void Mob6(Location spl, String rn) {
	
		Random random=new Random();
		double number = (random.nextDouble()+1.5) * 2 * (random.nextBoolean() ? -1 : 1);
		double number2 = (random.nextDouble()+1.5) * 2 * (random.nextBoolean() ? -1 : 1);
		Location esl = spl.clone().add(number, 2.5, number2);
		ItemStack head = new ItemStack(Material.JACK_O_LANTERN);
		
		String reg = lang.equalsIgnoreCase("ko_kr") ? "마녀의 눈사람":"Witch's Snowman";
		Snowman newmob = (Snowman) Summon(esl, ChatColor.AQUA+reg + "<"+rn+">", 550.0, head, null, null, null, null, null, EntityType.SNOWMAN);

		newmob.setMetadata("wsnowman", new FixedMetadataValue(RMain.getInstance(),rn));
		newmob.setMetadata("summoned", new FixedMetadataValue(RMain.getInstance(),rn));
		newmob.setMetadata(META, new FixedMetadataValue(RMain.getInstance(), true));
		newmob.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.29);
		newmob.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), true));
		
		newmob.setPersistent(true);
		
		
		addraider(rn,META,newmob);
		
	}
	final private void Mob7(Location spl, String rn) {
	
	
		Random random=new Random();
		double number = (random.nextDouble()+1.5) * 2 * (random.nextBoolean() ? -1 : 1);
		double number2 = (random.nextDouble()+1.5) * 2 * (random.nextBoolean() ? -1 : 1);
		Location esl = spl.clone().add(number, 2.5, number2);
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
		main.addUnsafeEnchantment(Enchantment.ARROW_DAMAGE, 3);
		String reg = lang.contains("kr") ? "서리궁수":"FrostArcher";
		
		Skeleton newmob = (Skeleton) Summon(esl, ChatColor.AQUA+reg + "<"+rn+">", 650.0, head, chest, leg, boots, main, null, EntityType.SKELETON);
		
		newmob.getEquipment().setBootsDropChance(0);
		newmob.getEquipment().setChestplateDropChance(0);
		newmob.getEquipment().setHelmetDropChance(0);
		newmob.getEquipment().setItemInMainHandDropChance(0);
		newmob.getEquipment().setItemInOffHandDropChance(0);
		newmob.getEquipment().setLeggingsDropChance(0);
		newmob.setMetadata("summoned", new FixedMetadataValue(RMain.getInstance(), rn));
		newmob.setMetadata(META, new FixedMetadataValue(RMain.getInstance(), true));
		newmob.setMetadata("arch", new FixedMetadataValue(RMain.getInstance(), true));
		newmob.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.4);
		newmob.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE).setBaseValue(1);
		newmob.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), true));
		
		
		
		
		addraider(rn,META,newmob);
		
	}
	@SuppressWarnings("unchecked")
	final private void Boss(Location spl, String rn) {
	
		Random random=new Random();
		double number = (random.nextDouble()+1.5) * 2 * (random.nextBoolean() ? -1 : 1);
		double number2 = (random.nextDouble()+1.5) * 2 * (random.nextBoolean() ? -1 : 1);
		Location esl = spl.clone().add(number, 2.5, number2);
	
		
		ItemStack main = new ItemStack(Material.BLAZE_ROD);
		ItemMeta mm = main.getItemMeta();
		mm.setCustomModelData(9006);
		main.setItemMeta(mm);
		main.addUnsafeEnchantment(Enchantment.KNOCKBACK, 3);
		main.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 3);
		
		String reg = lang.equalsIgnoreCase("ko_kr") ? "설산마녀":"SnowWitch";
		Witch newmob = (Witch) Summon(esl, ChatColor.AQUA+reg + "<"+rn+">", 3000.0, null, null, null, null, main, null, EntityType.WITCH);
		newmob.setGlowing(true);
		newmob.setCanJoinRaid(false);
		newmob.setPatrolTarget(null);
		newmob.setPatrolLeader(false);
		newmob.setMetadata("snowyboss", new FixedMetadataValue(RMain.getInstance(), true));
		newmob.setMetadata(META, new FixedMetadataValue(RMain.getInstance(), true));
		newmob.setMetadata("boss", new FixedMetadataValue(RMain.getInstance(), true));
		newmob.setMetadata("summoned", new FixedMetadataValue(RMain.getInstance(), rn));
		newmob.setMetadata("summonedboss", new FixedMetadataValue(RMain.getInstance(), rn));
		
		BossBar	newbar = Bukkit.getServer().createBossBar(new NamespacedKey(RMain.getInstance(), rn +"SnowWitch"),newmob.getName(), BarColor.WHITE, BarStyle.SEGMENTED_20, BarFlag.CREATE_FOG);
	    newbar.setVisible(true);
		raidbar.put(rn, META, newbar);
		newmob.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.36);
		newmob.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE).setBaseValue(1);
		newmob.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), true));
		
		addraider(rn,META,newmob);
		
	
		final Object ht = getherotype(rn);
		final double hp = Holding.ale(newmob).getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue();
	
		if(ht instanceof Player) {
			Player p = (Player) ht;
			int task = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(RMain.getInstance(), new Runnable() {
	            @Override
	            public void run() 
	            {
	
					if(Holding.ale(newmob)!=null) {
	                	raidbar.get(rn, META).setProgress(Holding.ale(newmob).getHealth()/hp);
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
	                	raidbar.get(rn, META).setProgress(Holding.ale(newmob).getHealth()/hp);
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