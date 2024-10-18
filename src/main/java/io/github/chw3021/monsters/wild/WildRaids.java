package io.github.chw3021.monsters.wild;

import java.util.HashSet;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Drowned;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Evoker;
import org.bukkit.entity.Husk;
import org.bukkit.entity.Skeleton;
import org.bukkit.entity.Vindicator;
import org.bukkit.entity.Witch;
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


public class WildRaids extends Summoned {

	/**
	 * 
	 */
	private static transient final long serialVersionUID = 490515503598935525L;
	/**
	 * 
	 */
	
	private static final WildRaids instance = new WildRaids ();
	public static WildRaids getInstance()
	{
		return instance;
		
	}
	String META = "wild";
	
	
	public void WildCombo(EntityDeathEvent d) 
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
		ItemStack pe = new ItemStack(Material.MOSS_BLOCK);
		pe.addUnsafeEnchantment(Enchantment.BANE_OF_ARTHROPODS, 1);
		return pe;
	}
	final private ItemStack mobchest() {
		ItemStack pe = new ItemStack(Material.NETHERITE_CHESTPLATE);
		ArmorMeta arm = (ArmorMeta) pe.getItemMeta();
		ArmorTrim t1 = new ArmorTrim(TrimMaterial.EMERALD, TrimPattern.SILENCE);
		arm.setTrim(t1);
		pe.setItemMeta(arm);
		return pe;
	}
	final private ItemStack mobleg() {
		ItemStack pe = new ItemStack(Material.NETHERITE_LEGGINGS);
		ArmorMeta arm = (ArmorMeta) pe.getItemMeta();
		ArmorTrim t1 = new ArmorTrim(TrimMaterial.EMERALD, TrimPattern.SILENCE);
		arm.setTrim(t1);
		pe.setItemMeta(arm);
		return pe;
	}
	final private ItemStack mobboots() {
		ItemStack pe = new ItemStack(Material.NETHERITE_BOOTS);
		ArmorMeta arm = (ArmorMeta) pe.getItemMeta();
		ArmorTrim t1 = new ArmorTrim(TrimMaterial.EMERALD, TrimPattern.SILENCE);
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
		String reg = lang.equalsIgnoreCase("ko_kr") ? "고대의사수":"AncientShooter";
		Skeleton newmob = (Skeleton) Summon(esl, ChatColor.GRAY+reg + "<"+rn+">", 35000.0, head, chest, leg, boots, null, null, EntityType.SKELETON);
		
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

		ItemStack off = new ItemStack(Material.SHIELD);

		String reg = lang.equalsIgnoreCase("ko_kr") ? "고대의전사":"AncientWarrior";
		Skeleton newmob = (Skeleton) Summon(esl, ChatColor.GRAY+reg + "<"+rn+">", 40000.0, head, chest, leg, boots, main, off, EntityType.SKELETON);
		
		
		newmob.setMetadata("summoned", new FixedMetadataValue(RMain.getInstance(), rn));
		newmob.setMetadata("jumper", new FixedMetadataValue(RMain.getInstance(), rn));
		
		
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
    	ItemStack head = mobhead();
		ItemStack chest = mobchest();
		ItemStack leg = mobleg();
		ItemStack boots = mobboots();
		ItemStack main = new ItemStack(Material.BOOK);
		
		String reg = lang.equalsIgnoreCase("ko_kr") ? "고대의마법사":"AncientMage";
		Evoker newmob = (Evoker) Summon(esl, ChatColor.GRAY+reg + "<"+rn+">", 35000.0, head, chest, leg, boots, main, null, EntityType.EVOKER);

		newmob.setCanJoinRaid(false);
		newmob.setPatrolLeader(false);
		newmob.setPatrolTarget(null);
		newmob.setMetadata("summoned", new FixedMetadataValue(RMain.getInstance(), rn));
		newmob.setMetadata("vexLocked", new FixedMetadataValue(RMain.getInstance(), rn));
		
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
		
		String reg = lang.equalsIgnoreCase("ko_kr") ? "고대의과학자":"AncientScientist";
		Witch newmob = (Witch) Summon(esl, ChatColor.GRAY+reg + "<"+rn+">", 38000.0, mobhead(), null, null, null, null, null, EntityType.WITCH);
		newmob.setCanJoinRaid(false);
		newmob.setPatrolLeader(false);
		newmob.setPatrolTarget(null);
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

		ItemStack main = new ItemStack(Material.TRIDENT);
		main.addUnsafeEnchantment(Enchantment.RIPTIDE, 10);
		ItemStack off = new ItemStack(Material.SHIELD);
		
		String reg = lang.equalsIgnoreCase("ko_kr") ? "고대의바다전사":"AncientMarine";
		Drowned newmob = (Drowned) Summon(esl, ChatColor.GRAY+reg + "<"+rn+">", 42000.0, head, chest, leg, boots, main, off, EntityType.DROWNED);
		newmob.setConversionTime(-1);
		
		newmob.setMetadata("summoned", new FixedMetadataValue(RMain.getInstance(), rn));
		newmob.setMetadata("riptider", new FixedMetadataValue(RMain.getInstance(), true));
		
		newmob.setMetadata(META, new FixedMetadataValue(RMain.getInstance(), true));
		
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
		
		String reg = lang.contains("kr") ? "고대의복수자":"AncientAvenger";
		Husk newmob = (Husk) Summon(esl, reg, 40000.0, head, chest, leg, boots, main,
				null, EntityType.HUSK);
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
    	Location esl = spl.clone().add(number, 2.5, number2);
    	ItemStack head = mobhead();
    	
		ItemStack main = new ItemStack(Material.GLOBE_BANNER_PATTERN);
		ItemMeta offm = main.getItemMeta();
		offm.setCustomModelData(1060);
		main.setItemMeta(offm);
		main.addUnsafeEnchantment(Enchantment.FIRE_ASPECT, 3);
		
		String reg = lang.equalsIgnoreCase("ko_kr") ? "고대의격투가":"AncientFighter";
		Vindicator newmob = (Vindicator) Summon(esl, ChatColor.GRAY+reg + "<"+rn+">", 45000.0, head, null, null, null, main, main, EntityType.VINDICATOR);
		newmob.setCanJoinRaid(false);
		newmob.setPatrolLeader(false);
		newmob.setPatrolTarget(null);
		newmob.setMetadata("summoned", new FixedMetadataValue(RMain.getInstance(),rn));
		
		newmob.setMetadata(META, new FixedMetadataValue(RMain.getInstance(), true));
		newmob.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), true));
		
		
		addraider(rn,META,newmob);
		
	}
	
	
}