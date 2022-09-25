package io.github.chw3021.monsters.red;

import java.util.Arrays;
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
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Evoker;
import org.bukkit.entity.Husk;
import org.bukkit.entity.Player;
import org.bukkit.entity.Ravager;
import org.bukkit.entity.Skeleton;
import org.bukkit.entity.Vindicator;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import io.github.chw3021.commons.Holding;
import io.github.chw3021.monsters.raids.Summoned;
import io.github.chw3021.rmain.RMain;


public class RedRaids extends Summoned {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6536664363548624595L;
	
	private static final RedRaids instance = new RedRaids ();
	public static RedRaids getInstance()
	{
		return instance;
		
	}

	String META = "red";
	
	public void RedCombo(EntityDeathEvent d) 
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
    	Location esl = spl.clone().add(number, 2.5, number2);
    	ItemStack head = new ItemStack(Material.LEATHER_HELMET);
		LeatherArmorMeta hem = (LeatherArmorMeta) head.getItemMeta();
		hem.setColor(Color.RED);
		head.setItemMeta(hem);
		head.addUnsafeEnchantment(Enchantment.PROTECTION_FIRE, 5);
		head.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
		head.addUnsafeEnchantment(Enchantment.PROTECTION_PROJECTILE, 1);
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
		ItemStack main = new ItemStack(Material.IRON_SWORD);
		String reg = lang.equalsIgnoreCase("ko_kr") ? "ºÓÀº¿ë»ç":"RedWarrior";
		Skeleton newmob = (Skeleton) Summon(esl, ChatColor.RED+reg + "<"+rn+">", 15000.0, head, chest, leg, boots, main, null, EntityType.SKELETON);
		newmob.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 999999, 255, false, false));
		newmob.getEquipment().setBootsDropChance(0);
		newmob.getEquipment().setChestplateDropChance(0);
		newmob.getEquipment().setHelmetDropChance(0);
		newmob.getEquipment().setItemInMainHandDropChance(0);
		newmob.getEquipment().setItemInOffHandDropChance(0);
		newmob.getEquipment().setLeggingsDropChance(0);
		newmob.setMetadata("summoned", new FixedMetadataValue(RMain.getInstance(), rn));
		newmob.setMetadata(META, new FixedMetadataValue(RMain.getInstance(), true));
		newmob.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.3);
		newmob.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), true));
		
		
		addraider(rn,META,newmob);
	
	}
	final private void Mob2(Location spl, String rn) {
	
		Random random=new Random();
    	double number = (random.nextDouble()+1.5) * 2.5 * (random.nextBoolean() ? -1 : 1);
    	double number2 = (random.nextDouble()+1.5) * 2.5 * (random.nextBoolean() ? -1 : 1);
    	Location esl = spl.clone().add(number, 2.5, number2);
    	ItemStack head = new ItemStack(Material.LEATHER_HELMET);
		LeatherArmorMeta hem = (LeatherArmorMeta) head.getItemMeta();
		hem.setColor(Color.RED);
		head.setItemMeta(hem);
		head.addUnsafeEnchantment(Enchantment.PROTECTION_FIRE, 5);
		head.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
		head.addUnsafeEnchantment(Enchantment.PROTECTION_PROJECTILE, 1);
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
		ItemStack main = new ItemStack(Material.NETHERITE_SWORD);
		main.addUnsafeEnchantment(Enchantment.FIRE_ASPECT, 3);
		main.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 3);
		ItemStack off = new ItemStack(Material.SHIELD);
		main.addUnsafeEnchantment(Enchantment.FIRE_ASPECT, 3);
		main.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 3);
		String reg = lang.equalsIgnoreCase("ko_kr") ? "ºÓÀº´ÜÀå":"RedWarlord";
		Skeleton newmob = (Skeleton) Summon(esl, ChatColor.RED+reg + "<"+rn+">", 20000.0, head, chest, leg, boots, main, off, EntityType.SKELETON);
		newmob.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 999999, 255, false, false));
		
		newmob.getEquipment().setBootsDropChance(0);
		newmob.getEquipment().setChestplateDropChance(0);
		newmob.getEquipment().setHelmetDropChance(0);
		newmob.getEquipment().setItemInMainHandDropChance(0);
		newmob.getEquipment().setItemInOffHandDropChance(0);
		newmob.getEquipment().setLeggingsDropChance(0);
		newmob.setMetadata("summoned", new FixedMetadataValue(RMain.getInstance(), rn));
		newmob.setMetadata(META, new FixedMetadataValue(RMain.getInstance(), true));
		newmob.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.3);
		newmob.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE).setBaseValue(1);
		newmob.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), true));
		

		
		
		addraider(rn,META,newmob);
		
		
	}
	final private void Mob3(Location spl, String rn) {
		Random random=new Random();
    	double number = (random.nextDouble()+1.5) * 2.5 * (random.nextBoolean() ? -1 : 1);
    	double number2 = (random.nextDouble()+1.5) * 2.5 * (random.nextBoolean() ? -1 : 1);
    	Location esl = spl.clone().add(number, 2.5, number2);
    	ItemStack head = new ItemStack(Material.RED_SANDSTONE);
		head.addUnsafeEnchantment(Enchantment.PROTECTION_FIRE, 5);
		head.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
		head.addUnsafeEnchantment(Enchantment.PROTECTION_PROJECTILE, 1);
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
		ItemStack main = new ItemStack(Material.FIRE_CHARGE);
		main.addUnsafeEnchantment(Enchantment.FIRE_ASPECT, 3);
		main.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 5);
		ItemStack off = new ItemStack(Material.NETHERITE_AXE);
		ItemMeta offm = off.getItemMeta();
		offm.setCustomModelData(4010);
		off.setItemMeta(offm);
		String reg = lang.equalsIgnoreCase("ko_kr") ? "ºÓÀº ÇÏ¼öÀÎ":"Red Hunter";
		Vindicator newmob = (Vindicator) Summon(esl, ChatColor.RED+reg + "<"+rn+">", 17000.0, head, chest, leg, boots, main, off, EntityType.VINDICATOR);
		newmob.setCanJoinRaid(false);
		newmob.setPatrolLeader(false);
		newmob.setPatrolTarget(null);
		newmob.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 999999, 255, false, false));
		newmob.getEquipment().setBootsDropChance(0);
		newmob.getEquipment().setChestplateDropChance(0);
		newmob.getEquipment().setHelmetDropChance(0);
		newmob.getEquipment().setItemInMainHandDropChance(0);
		newmob.getEquipment().setItemInOffHandDropChance(0);
		newmob.getEquipment().setLeggingsDropChance(0);
		newmob.setMetadata("summoned", new FixedMetadataValue(RMain.getInstance(),rn));
		newmob.setMetadata(META, new FixedMetadataValue(RMain.getInstance(), true));
		newmob.setMetadata("mage", new FixedMetadataValue(RMain.getInstance(), true));
		newmob.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), true));
		

		
		addraider(rn,META,newmob);
		
	}
	final private void Mob4(Location spl, String rn) {

    	Random random=new Random();
    	double number = (random.nextDouble()+1.5) * 2 * (random.nextBoolean() ? -1 : 1);
    	double number2 = (random.nextDouble()+1.5) * 2 * (random.nextBoolean() ? -1 : 1);
    	Location esl = spl.clone().add(number, 2.5, number2);
    	ItemStack head = new ItemStack(Material.CHISELED_RED_SANDSTONE);
		head.addUnsafeEnchantment(Enchantment.PROTECTION_FIRE, 5);
		head.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
		head.addUnsafeEnchantment(Enchantment.PROTECTION_PROJECTILE, 1);
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
		ItemStack main = new ItemStack(Material.FIRE_CHARGE);
		main.addUnsafeEnchantment(Enchantment.FIRE_ASPECT, 3);
		main.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 5);
		ItemStack off = new ItemStack(Material.ENCHANTED_BOOK);
		main.addUnsafeEnchantment(Enchantment.FIRE_ASPECT, 3);
		main.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 5);
		String reg = lang.equalsIgnoreCase("ko_kr") ? "ºÓÀº ÁÖ¼ú»ç":"RedWarlock";
		Evoker newmob = (Evoker) Summon(esl, ChatColor.RED+reg + "<"+rn+">", 19000.0, head, chest, leg, boots, main, off, EntityType.EVOKER);
		newmob.setCanJoinRaid(false);
		newmob.setPatrolLeader(false);
		newmob.setPatrolTarget(null);
		newmob.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 999999, 255, false, false));
		
		newmob.getEquipment().setBootsDropChance(0);
		newmob.getEquipment().setChestplateDropChance(0);
		newmob.getEquipment().setHelmetDropChance(0);
		newmob.getEquipment().setItemInMainHandDropChance(0);
		newmob.getEquipment().setItemInOffHandDropChance(0);
		newmob.getEquipment().setLeggingsDropChance(0);
		newmob.setMetadata("summoned", new FixedMetadataValue(RMain.getInstance(), rn));
		newmob.setMetadata(META, new FixedMetadataValue(RMain.getInstance(), true));
		newmob.setMetadata("mage", new FixedMetadataValue(RMain.getInstance(), true));

		newmob.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE).setBaseValue(1);
		newmob.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), true));
		
		
		
		addraider(rn,META,newmob);
		
	}
	final private void Mob5(Location spl, String rn) {

    	Random random=new Random();
    	double number = (random.nextDouble()+1.5) * 2.5 * (random.nextBoolean() ? -1 : 1);
    	double number2 = (random.nextDouble()+1.5) * 2.5 * (random.nextBoolean() ? -1 : 1);
    	Location esl = spl.clone().add(number, 2.5, number2);
    	ItemStack head = new ItemStack(Material.RED_TERRACOTTA);
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
		ItemStack main = new ItemStack(Material.FIRE_CHARGE);
		main.addUnsafeEnchantment(Enchantment.FIRE_ASPECT, 3);
		main.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 5);
		String reg = lang.equalsIgnoreCase("ko_kr") ? "Å×¶óÄÚÅ¸Á»ºñ":"WalkingTerracotta";
		Husk newmob = (Husk) Summon(esl, ChatColor.RED+reg + "<"+rn+">", 20000.0, head, chest, leg, boots, main, main, EntityType.HUSK);
		newmob.setAdult();
		newmob.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 999999, 255, false, false));
		newmob.getEquipment().setBootsDropChance(0);
		newmob.getEquipment().setChestplateDropChance(0);
		newmob.getEquipment().setHelmetDropChance(0);
		newmob.getEquipment().setItemInMainHandDropChance(0);
		newmob.getEquipment().setItemInOffHandDropChance(0);
		newmob.getEquipment().setLeggingsDropChance(0);
		newmob.setMetadata("summoned", new FixedMetadataValue(RMain.getInstance(),rn));
		newmob.setMetadata(META, new FixedMetadataValue(RMain.getInstance(), true));
		newmob.setMetadata("headthrower", new FixedMetadataValue(RMain.getInstance(), true));
		newmob.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.26);
		newmob.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), true));
		
		newmob.setConversionTime(-1);
		
		
		
		newmob.getAttribute(Attribute.ZOMBIE_SPAWN_REINFORCEMENTS).setBaseValue(0);
		addraider(rn,META,newmob);
	}
	final private void Mob6(Location spl, String rn) {

    	Random random=new Random();
    	double number = (random.nextDouble()+1.5) * 2.5 * (random.nextBoolean() ? -1 : 1);
    	double number2 = (random.nextDouble()+1.5) * 2.5 * (random.nextBoolean() ? -1 : 1);
    	Location esl = spl.clone().add(number, 2.5, number2);
    	ItemStack head = new ItemStack(Material.RED_TERRACOTTA);
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
		ItemStack main = new ItemStack(Material.FIRE_CHARGE);
		main.addUnsafeEnchantment(Enchantment.FIRE_ASPECT, 3);
		main.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 5);
		String reg = lang.equalsIgnoreCase("ko_kr") ? "¿ë¾Ï±«¼ö":"Lavager";
		Ravager newmob = (Ravager) Summon(esl, ChatColor.RED+reg + "<"+rn+">", 21000.0, head, chest, leg, boots, main, main, EntityType.RAVAGER);
		newmob.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 999999, 255, false, false));
		newmob.getEquipment().setBootsDropChance(0);
		newmob.getEquipment().setChestplateDropChance(0);
		newmob.getEquipment().setHelmetDropChance(0);
		newmob.getEquipment().setItemInMainHandDropChance(0);
		newmob.getEquipment().setItemInOffHandDropChance(0);
		newmob.getEquipment().setLeggingsDropChance(0);
		newmob.setCanJoinRaid(false);
		newmob.setPatrolTarget(null);
		newmob.setPatrolLeader(false);
		newmob.setMetadata("summoned", new FixedMetadataValue(RMain.getInstance(),rn));
		newmob.setMetadata(META, new FixedMetadataValue(RMain.getInstance(), true));
		newmob.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.29);
		newmob.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), true));
		
		
		
		
		addraider(rn,META,newmob);
		
	}
	final private void Mob7(Location spl, String rn) {


    	Random random=new Random();
    	double number = (random.nextDouble()+1.5) * 2.5 * (random.nextBoolean() ? -1 : 1);
    	double number2 = (random.nextDouble()+1.5) * 2.5 * (random.nextBoolean() ? -1 : 1);
    	Location esl = spl.clone().add(number, 2.5, number2);
    	ItemStack head = new ItemStack(Material.LEATHER_HELMET);
		LeatherArmorMeta hem = (LeatherArmorMeta) head.getItemMeta();
		hem.setColor(Color.RED);
		head.setItemMeta(hem);
		head.addUnsafeEnchantment(Enchantment.PROTECTION_FIRE, 5);
		head.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
		head.addUnsafeEnchantment(Enchantment.PROTECTION_PROJECTILE, 1);
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
		ItemStack main = new ItemStack(Material.BOW);
		main.addUnsafeEnchantment(Enchantment.ARROW_DAMAGE, 2);
		main.addUnsafeEnchantment(Enchantment.ARROW_KNOCKBACK, 2);
		main.addUnsafeEnchantment(Enchantment.ARROW_FIRE, 5);
		ItemStack off = new ItemStack(Material.BOW);
		main.addUnsafeEnchantment(Enchantment.ARROW_DAMAGE, 2);
		main.addUnsafeEnchantment(Enchantment.ARROW_KNOCKBACK, 2);
		main.addUnsafeEnchantment(Enchantment.ARROW_FIRE, 5);
		String reg = lang.equalsIgnoreCase("ko_kr") ? "±×À»¸°±Ã¼ö":"BurnedArcher";
		Skeleton newmob = (Skeleton) Summon(esl, ChatColor.RED+reg + "<"+rn+">", 16000.0, head, chest, leg, boots, main, off, EntityType.SKELETON);
		newmob.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 999999, 255, false, false));
		
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
    	double number = (random.nextDouble()+1.5) * 2.5 * (random.nextBoolean() ? -1 : 1);
    	double number2 = (random.nextDouble()+1.5) * 2.5 * (random.nextBoolean() ? -1 : 1);
    	Location esl = spl.clone().add(number, 2.5, number2);

		
		ItemStack head = new ItemStack(Material.LEATHER_HELMET);
		LeatherArmorMeta hem = (LeatherArmorMeta) head.getItemMeta();
		hem.setLore(Arrays.asList("RedKnight Helmet"));
		hem.setCustomModelData(100);
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
		boots.addUnsafeEnchantment(Enchantment.DEPTH_STRIDER,5);
		ItemStack main = new ItemStack(Material.NETHERITE_AXE);
		ItemMeta offm = main.getItemMeta();
		offm.setCustomModelData(4010);
		main.setItemMeta(offm);
		main.addUnsafeEnchantment(Enchantment.FIRE_ASPECT, 3);
		ItemStack off = new ItemStack(Material.SHIELD);
		off.addUnsafeEnchantment(Enchantment.FIRE_ASPECT, 3);
		String reg = lang.equalsIgnoreCase("ko_kr") ? "ºÓÀº±â»ç":"RedKnight";
		Skeleton newmob = (Skeleton) Summon(esl, ChatColor.RED+reg+ "<"+rn+">", 60000.0, head, chest, leg, boots, main, off, EntityType.SKELETON);
		newmob.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 999999, 255, false, false));
		newmob.setGlowing(true);
		newmob.getEquipment().setBootsDropChance(0);
		newmob.getEquipment().setChestplateDropChance(0);
		newmob.getEquipment().setHelmetDropChance(0);
		newmob.getEquipment().setItemInMainHandDropChance(0);
		newmob.getEquipment().setItemInOffHandDropChance(0);
		newmob.getEquipment().setLeggingsDropChance(0);
		newmob.setMetadata("redboss", new FixedMetadataValue(RMain.getInstance(), true));
		newmob.setMetadata(META, new FixedMetadataValue(RMain.getInstance(), true));
		newmob.setMetadata("boss", new FixedMetadataValue(RMain.getInstance(), true));
		newmob.setMetadata("summoned", new FixedMetadataValue(RMain.getInstance(), rn));
		newmob.setMetadata("summonedboss", new FixedMetadataValue(RMain.getInstance(), rn));
		
		BossBar	newbar = Bukkit.getServer().createBossBar(new NamespacedKey(RMain.getInstance(), rn +"RedKnight"),newmob.getName(), BarColor.RED, BarStyle.SEGMENTED_20, BarFlag.CREATE_FOG);
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
	                	raidbar.get(rn, META).setProgress(Holding.ale(newmob).getHealth()/60000d);
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
	                	raidbar.get(rn, META).setProgress(Holding.ale(newmob).getHealth()/60000d);
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
