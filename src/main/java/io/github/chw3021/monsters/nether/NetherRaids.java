package io.github.chw3021.monsters.nether;

import java.util.HashSet;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Blaze;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Ghast;
import org.bukkit.entity.Hoglin;
import org.bukkit.entity.MagmaCube;
import org.bukkit.entity.PigZombie;
import org.bukkit.entity.PiglinBrute;
import org.bukkit.entity.WitherSkeleton;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ArmorMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.trim.ArmorTrim;
import org.bukkit.inventory.meta.trim.TrimMaterial;
import org.bukkit.inventory.meta.trim.TrimPattern;
import org.bukkit.metadata.FixedMetadataValue;
import io.github.chw3021.monsters.raids.Summoned;
import io.github.chw3021.rmain.RMain;


public class NetherRaids extends Summoned {

	/**
	 * 
	 */
	private static transient final long serialVersionUID = 490515503598935525L;
	/**
	 * 
	 */
	
	private static final NetherRaids instance = new NetherRaids ();
	public static NetherRaids getInstance()
	{
		return instance;
		
	}
	String META = "nether";
	
	
	public void NetherCombo(EntityDeathEvent d) 
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
	/*final protected void Boss(String rn, String meta, Location esl) {
		super.Boss(rn, meta, esl);
		if(meta.equals(META)) {
			Boss(esl,rn);
		}
	}*/
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
		ItemStack pe = new ItemStack(Material.NETHER_QUARTZ_ORE);
		pe.addUnsafeEnchantment(Enchantment.BINDING_CURSE, 1);
		return pe;
	}
	final private ItemStack mobchest() {
		ItemStack pe = new ItemStack(Material.NETHERITE_CHESTPLATE);
		ArmorMeta arm = (ArmorMeta) pe.getItemMeta();
		ArmorTrim t1 = new ArmorTrim(TrimMaterial.QUARTZ, TrimPattern.RIB);
		arm.setTrim(t1);
		pe.setItemMeta(arm);
		return pe;
	}
	final private ItemStack mobleg() {
		ItemStack pe = mobleg();
		ArmorMeta arm = (ArmorMeta) pe.getItemMeta();
		ArmorTrim t1 = new ArmorTrim(TrimMaterial.QUARTZ, TrimPattern.RIB);
		arm.setTrim(t1);
		pe.setItemMeta(arm);
		return pe;
	}
	final private ItemStack mobboots() {
		ItemStack pe = new ItemStack(Material.NETHERITE_BOOTS);
		ArmorMeta arm = (ArmorMeta) pe.getItemMeta();
		ArmorTrim t1 = new ArmorTrim(TrimMaterial.QUARTZ, TrimPattern.RIB);
		arm.setTrim(t1);
		pe.setItemMeta(arm);
		return pe;
	}
	
	final private void Mob1(Location spl, String rn) {
    	Random random=new Random();
    	double number = (random.nextDouble()+1.5) * 2.5 * (random.nextBoolean() ? -1 : 1);
    	double number2 = (random.nextDouble()+1.5) * 2.5 * (random.nextBoolean() ? -1 : 1);
    	Location esl = spl.clone().add(number, 2.5, number2);
    	ItemStack head = mobhead();
		ItemStack chest = mobchest();
		ItemStack leg = mobleg();
		ItemStack boots = mobboots();
		ItemStack main = new ItemStack(Material.BOW);
		
		String reg = lang.equalsIgnoreCase("ko_kr") ? "네더사수":"NetherShooter";
		WitherSkeleton newmob = (WitherSkeleton) Summon(esl, ChatColor.GRAY+reg + "<"+rn+">", 45000.0, head, chest, leg, boots, main, null, EntityType.WITHER_SKELETON);
		
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
    	ItemStack head = mobhead();
		ItemStack chest = mobchest();
		ItemStack leg = mobleg();
		ItemStack boots = mobboots();
		ItemStack main = new ItemStack(Material.NETHERITE_SWORD);

		String reg = lang.equalsIgnoreCase("ko_kr") ? "거대마그마큐브":"GiantMagmaCube";
		MagmaCube newmob = (MagmaCube) Summon(esl, ChatColor.GRAY+reg + "<"+rn+">", 48000.0, head, chest, leg, boots, main, null, EntityType.MAGMA_CUBE);
		
		
		newmob.setMetadata("summoned", new FixedMetadataValue(RMain.getInstance(), rn));
		newmob.setSize(6);
		
		newmob.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(48000.0);
		newmob.setHealth(48000);
		
		newmob.setMetadata(META, new FixedMetadataValue(RMain.getInstance(), true));
		newmob.getAttribute(Attribute.GENERIC_JUMP_STRENGTH).setBaseValue(10);
		newmob.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE).setBaseValue(0.9);
		newmob.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), true));
		newmob.setMetadata("GiantSlime", new FixedMetadataValue(RMain.getInstance(), true));
		
		
		
		
		addraider(rn,META,newmob);
		
		
	}
	final private void Mob3(Location spl, String rn) {
    	Random random=new Random();
    	double number = (random.nextDouble()+1.5) * 2.5 * (random.nextBoolean() ? -1 : 1);
    	double number2 = (random.nextDouble()+1.5) * 2.5 * (random.nextBoolean() ? -1 : 1);
    	Location esl = spl.clone().add(number, 2.5, number2);
    	ItemStack head = mobhead();
		ItemStack chest = mobchest();
		ItemStack leg = mobleg();
		ItemStack boots = mobboots();
		ItemStack main = new ItemStack(Material.ENCHANTED_BOOK);
		
		String reg = lang.equalsIgnoreCase("ko_kr") ? "네더마법사":"NetherMage";
		Blaze newmob = (Blaze) Summon(esl, ChatColor.GRAY+reg + "<"+rn+">", 35000.0, head, chest, leg, boots, main, null, EntityType.BLAZE);

		newmob.setMetadata("summoned", new FixedMetadataValue(RMain.getInstance(), rn));
		
		newmob.setMetadata(META, new FixedMetadataValue(RMain.getInstance(), true));
		newmob.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.3);
		newmob.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), true));
		
		

		addraider(rn,META,newmob);
		
	}
	final private void Mob4(Location spl, String rn) {
    	Random random=new Random();
    	double number = (random.nextDouble()+1.5) * 2.5 * (random.nextBoolean() ? -1 : 1);
    	double number2 = (random.nextDouble()+1.5) * 2.5 * (random.nextBoolean() ? -1 : 1);
    	Location esl = spl.clone().add(number, 2.5, number2);
    	ItemStack head = mobhead();
		ItemStack chest = mobchest();
		ItemStack leg = mobleg();
		ItemStack boots = mobboots();
		ItemStack main = new ItemStack(Material.ENCHANTED_BOOK);
		
		String reg = lang.equalsIgnoreCase("ko_kr") ? "강화호글린":"EnhancedHoglin";
		Hoglin newmob = (Hoglin) Summon(esl, ChatColor.GRAY+reg + "<"+rn+">", 50000.0, head, chest, leg, boots, main, null, EntityType.HOGLIN);
		newmob.setAdult();
		newmob.setIsAbleToBeHunted(false);
		newmob.setImmuneToZombification(true);
		newmob.setConversionTime(-1);
		newmob.setMetadata("summoned", new FixedMetadataValue(RMain.getInstance(),rn));
		
		newmob.setMetadata(META, new FixedMetadataValue(RMain.getInstance(), true));
		newmob.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), true));
		
		
		
		addraider(rn,META,newmob);
		
	}
	final private void Mob5(Location spl, String rn) {
    	Random random=new Random();
    	double number = (random.nextDouble()+1.5) * 2.5 * (random.nextBoolean() ? -1 : 1);
    	double number2 = (random.nextDouble()+1.5) * 2.5 * (random.nextBoolean() ? -1 : 1);
    	Location esl = spl.clone().add(number, 2.5, number2);
    	
    	ItemStack head = mobhead();
		ItemStack chest = mobchest();
		ItemStack leg = mobleg();
		ItemStack boots = mobboots();

		ItemStack main = new ItemStack(Material.MACE);
		ItemStack off = new ItemStack(Material.SHIELD);
		
		String reg = lang.equalsIgnoreCase("ko_kr") ? "네더기사":"NetherKnight";
		PiglinBrute newmob = (PiglinBrute) Summon(esl, ChatColor.GRAY+reg + "<"+rn+">", 50000.0, head, chest, leg, boots, main, off, EntityType.PIGLIN_BRUTE);
		newmob.setConversionTime(-1);
		newmob.setAdult();
		newmob.setImmuneToZombification(true);
		
		newmob.setMetadata("summoned", new FixedMetadataValue(RMain.getInstance(), rn));
		
		newmob.setMetadata(META, new FixedMetadataValue(RMain.getInstance(), true));

		newmob.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE).setBaseValue(1);
		newmob.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.35);
		newmob.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), true));
		
		
		
		addraider(rn,META,newmob);
	}
	final private void Mob6(Location spl, String rn) {
    	Random random=new Random();
    	double number = (random.nextDouble()+1.5) * 2.5 * (random.nextBoolean() ? -1 : 1);
    	double number2 = (random.nextDouble()+1.5) * 2.5 * (random.nextBoolean() ? -1 : 1);
    	Location esl = spl.clone().add(number, 2.5, number2);

    	ItemStack head = mobhead();
		ItemStack chest = mobchest();
		ItemStack leg = mobleg();
		ItemStack boots = mobboots();
		
		ItemStack main = new ItemStack(Material.NETHERITE_HOE);
		
		String reg = lang.contains("kr") ? "강화가스트":"EnhancedGhast";
		Ghast newmob = (Ghast) Summon(esl, reg, 47500.0, head, chest, leg, boots, main,
				null, EntityType.GHAST);
		
		newmob.setCharging(true);
		
		newmob.setMetadata("summoned", new FixedMetadataValue(RMain.getInstance(), rn));
		
		newmob.setMetadata(META, new FixedMetadataValue(RMain.getInstance(), true));
		newmob.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), true));
		
		
		
		addraider(rn,META,newmob);
		
	}
	final private void Mob7(Location spl, String rn) {

    	Random random=new Random();
    	double number = (random.nextDouble()+1.5) * 2.5 * (random.nextBoolean() ? -1 : 1);
    	double number2 = (random.nextDouble()+1.5) * 2.5 * (random.nextBoolean() ? -1 : 1);
    	Location esl = spl.clone().add(number, 2.5, number2);
    	ItemStack head = mobhead();
		ItemStack chest = mobchest();
		ItemStack leg = mobleg();
		ItemStack boots = mobboots();
		
		ItemStack main = new ItemStack(Material.GLOBE_BANNER_PATTERN);
		ItemMeta offm = main.getItemMeta();
		offm.setCustomModelData(1060);
		main.setItemMeta(offm);
		main.addUnsafeEnchantment(Enchantment.SHARPNESS, 3);
		main.addUnsafeEnchantment(Enchantment.FIRE_ASPECT, 3);
		
		String reg = lang.equalsIgnoreCase("ko_kr") ? "네더격투가":"NetherFighter";
		PigZombie newmob = (PigZombie) Summon(esl, ChatColor.GRAY+reg + "<"+rn+">", 47500.0, head, chest, leg, boots, main, main, EntityType.ZOMBIFIED_PIGLIN);
		newmob.setConversionTime(-1);
		newmob.setAdult();
		newmob.setAnger(999999);
		newmob.setAngry(true);
		newmob.setMetadata("summoned", new FixedMetadataValue(RMain.getInstance(),rn));
		
		newmob.setMetadata(META, new FixedMetadataValue(RMain.getInstance(), true));
		newmob.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), true));
		
		
		addraider(rn,META,newmob);
		
	}
	
	
}