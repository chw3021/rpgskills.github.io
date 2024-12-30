package io.github.chw3021.commons;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.Tag;
import org.bukkit.attribute.Attribute;
import org.bukkit.block.data.BlockData;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.AbstractArrow;
import org.bukkit.entity.Chicken;
import org.bukkit.entity.EnderDragon;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.TextDisplay;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityPotionEffectEvent;
import org.bukkit.event.entity.EntityToggleSwimEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerArmorStandManipulateEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.event.server.PluginEnableEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import io.github.chw3021.classes.ClassData;
import io.github.chw3021.items.Potions;
import io.github.chw3021.items.armors.ArmorSet;
import io.github.chw3021.party.Party;
import io.github.chw3021.rmain.RMain;
import net.md_5.bungee.api.ChatColor;

public class Pak extends CombatMode implements Listener{

	/**
	 * 
	 */

	public static HashMap<String, Double> player_damage = new HashMap<String, Double>();
	
	public static HashSet<Material> passables = new HashSet<>();

	public String path = new File("").getAbsolutePath();
	

	static public HashMap<UUID, Boolean> battlemod = new HashMap<UUID, Boolean>();

	static public HashMap<UUID, Double> windyd = new HashMap<UUID, Double>();
	static public HashMap<UUID, Double> earthd = new HashMap<UUID, Double>();
	static public HashMap<UUID, Double> frostd = new HashMap<UUID, Double>();
	static public HashMap<UUID, Double> waterd = new HashMap<UUID, Double>();
	static public HashMap<UUID, Double> darkd = new HashMap<UUID, Double>();
	static public HashMap<UUID, Double> lightningd = new HashMap<UUID, Double>();
	static public HashMap<UUID, Double> flamed = new HashMap<UUID, Double>();
	static public HashMap<UUID, Double> poisond = new HashMap<UUID, Double>();


	static public HashMap<UUID, Double> windyr = new HashMap<UUID, Double>();
	static public HashMap<UUID, Double> earthr = new HashMap<UUID, Double>();
	static public HashMap<UUID, Double> frostr = new HashMap<UUID, Double>();
	static public HashMap<UUID, Double> waterr = new HashMap<UUID, Double>();
	static public HashMap<UUID, Double> darkr = new HashMap<UUID, Double>();
	static public HashMap<UUID, Double> lightningr = new HashMap<UUID, Double>();
	static public HashMap<UUID, Double> flamer = new HashMap<UUID, Double>();
	static public HashMap<UUID, Double> poisonr = new HashMap<UUID, Double>();
	
	final public static NamespacedKey windydn = new NamespacedKey(RMain.getInstance(), "windyd");
	final public static NamespacedKey earthdn = new NamespacedKey(RMain.getInstance(), "earthd");
	final public static NamespacedKey frostdn = new NamespacedKey(RMain.getInstance(), "frostd");
	final public static NamespacedKey waterdn = new NamespacedKey(RMain.getInstance(), "waterd");
	final public static NamespacedKey darkdn = new NamespacedKey(RMain.getInstance(), "darkd");
	final public static NamespacedKey lightningdn = new NamespacedKey(RMain.getInstance(), "lightningd");
	final public static NamespacedKey flamedn = new NamespacedKey(RMain.getInstance(), "flamed");
	final public static NamespacedKey poisondn = new NamespacedKey(RMain.getInstance(), "poisond");

	
	final public static NamespacedKey windyrn = new NamespacedKey(RMain.getInstance(), "windyr");
	final public static NamespacedKey earthrn = new NamespacedKey(RMain.getInstance(), "earthr");
	final public static NamespacedKey frostrn = new NamespacedKey(RMain.getInstance(), "frostr");
	final public static NamespacedKey waterrn = new NamespacedKey(RMain.getInstance(), "waterr");
	final public static NamespacedKey darkrn = new NamespacedKey(RMain.getInstance(), "darkr");
	final public static NamespacedKey lightningrn = new NamespacedKey(RMain.getInstance(), "lightningr");
	final public static NamespacedKey flamern = new NamespacedKey(RMain.getInstance(), "flamer");
	final public static NamespacedKey poisonrn = new NamespacedKey(RMain.getInstance(), "poisonr");

    // 인스턴스를 직접 초기화하지 않음
    private static Pak instance;

    // private 생성자
    public Pak() {
        // 필요한 초기화 로직
    }

    // 싱글톤 인스턴스 반환
    public static Pak getInstance() {
        // null 체크 후 초기화
        if (instance == null) {
            instance = new Pak();
        }
        return instance;
    }
	
	public interface SkillUse {
	    public void skilluse();
	}
	 
	
	public void er(PluginEnableEvent ev) 
	{
   	 	Bukkit.getOnlinePlayers().forEach(p -> {
   	 		DamageGetter(p);
   	 	});
   	 	for(Material m : Material.values()) {
   	 		if(m.isBlock() && !m.isSolid()) {
   	 			passables.add(m);
   	 		}
   	 	}
	}


	public void classinv(InventoryClickEvent e)
	{
		if(ChatColor.stripColor(e.getView().getTitle()).equalsIgnoreCase("Classes"))
		{
			e.setCancelled(true);
			if(e.getCurrentItem()==null||e.getCurrentItem().getType() == null|| !e.getCurrentItem().hasItemMeta())
			{
				e.setCancelled(false);
			}
			else
			{	
           	 Player p = (Player) e.getWhoClicked();
           	 DamageGetter(p);
			}
			
		}
		if(e.getClickedInventory() != null) {
			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	             @Override
	             public void run() 
	             {
	            	 Player p = (Player) e.getWhoClicked();
	            	 DamageGetter(p);
	             }
			}, 2); 
		}
	}


	public void nepreventer(PlayerJoinEvent ev) 
	{
		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
            @Override
            public void run() 
            {
           	 Player p = (Player) ev.getPlayer();
           	 DamageGetter(p);
            }
	   }, 2); 
		
	}

	final private List<LivingEntity> rayTrace (Player p, AbstractArrow ar) {
		final Location il = p.getEyeLocation().clone();
		List<LivingEntity> st = new ArrayList<>();
		int plv = ar.getPierceLevel()+1;
		int count = 0;
		
		for(double d = 0.1; d <= ar.getVelocity().length()+25; d += 0.2) {
			Location pl = il.clone();
			pl.add(il.clone().getDirection().normalize().multiply(d));
			for(Entity e : pl.getWorld().getNearbyEntities(pl, 0.3, 0.3, 0.3)) {
				if (e instanceof Player)
				{
					Player p1 = (Player) e;
					if(Party.isInSameParty(p,p1))	{
						continue;
					}
				}
				if(e instanceof LivingEntity && e!=p&& !e.hasMetadata("fake") && !e.hasMetadata("portal")) {
					st.add((LivingEntity) e);
					if(++count >= plv) {
						return st;
					}
				}
			}
		}
		return st;
	}

	public void BarrierBreaker(ProjectileLaunchEvent ev) 
	{
		if(ev.getEntity().getShooter() instanceof Player && ev.getEntity() instanceof AbstractArrow) {
			Player p = (Player) ev.getEntity().getShooter();
			AbstractArrow ar = (AbstractArrow) ev.getEntity();
			
			
			List<LivingEntity> r = rayTrace(p,ar);
				
				if(r != null && !r.isEmpty()) {
					for(int i = 0; i<r.size(); i++) {
						EntityType etype = r.get(i).getType();
						if(etype == EntityType.ENDERMAN || etype == EntityType.BREEZE || etype == EntityType.WITHER) {
							Bukkit.getPluginManager().callEvent(new ProjectileHitEvent(ar, r.get(i)));
						}
					}
				}
			
		}
	}
	


	public void Swimer(EntityToggleSwimEvent ev) 
	{
		if(ev.getEntity() instanceof Player) {
			Player p = (Player) ev.getEntity();
			if(ClassData.pc.getOrDefault(p.getUniqueId(),-1) == 20|| ClassData.pc.getOrDefault(p.getUniqueId(),-1) == 21|| ClassData.pc.getOrDefault(p.getUniqueId(),-1) == 22 || ClassData.pc.getOrDefault(p.getUniqueId(),-1) == 19 || ArmorSet.setnum(p)==7) {
				p.addPotionEffect(new PotionEffect(PotionEffectType.CONDUIT_POWER, 2000, 3, false, false));
				p.addPotionEffect(new PotionEffect(PotionEffectType.DOLPHINS_GRACE, 2000, 3, false, false));
				p.addPotionEffect(new PotionEffect(PotionEffectType.WATER_BREATHING, 2000, 30, false, false));
			}
		}
	}
	
	final protected Location getTargetEntity(LivingEntity p, Integer in) {
		final Location pl = p.getEyeLocation().clone();
		for(int i =0; i<in; i++) {
			Location tl = pl.clone().add(pl.clone().getDirection().normalize().multiply(i));
			Boolean bool = tl.getWorld().getNearbyEntities(tl,1,1,1).stream().anyMatch(e ->{

					if (e instanceof Player && p instanceof Player)
					{

						Player p1 = (Player) e;
						if(Party.isInSameParty((Player) p,p1))	{
							return false;
						}
					}
					if ((!(e == p))&& e instanceof LivingEntity&& !(e.hasMetadata("fake"))&& !(e.hasMetadata("portal")))
					{
						return true;
					}
					return false;
			});
			if(!tl.getBlock().isPassable() || bool) {
				return pl.clone().add(pl.clone().getDirection().normalize().multiply(i));
			}
		}
		return pl.clone().add(pl.clone().getDirection().normalize().multiply(in));
	}
	
	protected final Location gettargetblock(LivingEntity p, Integer in) {
		if(p.rayTraceBlocks(in) == null) {

			try {
				final Location tl = p.getTargetBlock(passables, in).getLocation().clone();
				
				if(tl.getBlock().getType().isOccluding()) {
					return tl.clone().add(0, 0.75, 0);
				}
				return tl;
			}
			catch(IllegalStateException e) {
				final Location pl = p.getLocation().clone();
				for(int i =0; i<in; i++) {
					if(!pl.clone().add(pl.clone().getDirection().normalize().multiply(i)).getBlock().isPassable()) {
						return pl.clone().add(pl.clone().getDirection().normalize().multiply(i));
					}
				}
				return pl.clone().add(pl.clone().getDirection().normalize().multiply(in));
			}
		}
		else if(p.rayTraceBlocks(in).getHitEntity() != null) {
			final Location rl = p.rayTraceBlocks(in).getHitEntity().getLocation();
			if(rl.getBlock().getType().isOccluding()) {
				return rl.clone().add(0, 0.75, 0);
			}
			else {
				return rl;
			}
		}
		final Location rl = p.rayTraceBlocks(in).getHitPosition().toLocation(p.getWorld());
		if(rl.getBlock().getType().isOccluding()) {
			return rl.clone().add(0, 0.75, 0);
		}
		else {
			return rl;
		}
		

	}
	

	final static HashMap<Material, BlockData> bdHm = new HashMap<>();
	
	final protected BlockData getBd(Material m) {
		return bdHm.computeIfAbsent(m, Bukkit::createBlockData);
	}
	
	
	final protected void cleans(LivingEntity p) {


        	if(p.hasPotionEffect(PotionEffectType.HUNGER)) {
    			p.removePotionEffect(PotionEffectType.HUNGER);
        	}
    		if(p.hasPotionEffect(PotionEffectType.BLINDNESS))
    		{
    			p.removePotionEffect(PotionEffectType.BLINDNESS);
    		}
    		if(p.hasPotionEffect(PotionEffectType.NAUSEA))
    		{
    			p.removePotionEffect(PotionEffectType.NAUSEA);
    		}
    		if(p.hasPotionEffect(PotionEffectType.HUNGER))
    		{
    			p.removePotionEffect(PotionEffectType.HUNGER);
    		}
    		if(p.hasPotionEffect(PotionEffectType.POISON))
    		{
    			p.removePotionEffect(PotionEffectType.POISON);
    		}
    		if(p.hasPotionEffect(PotionEffectType.SLOWNESS))
    		{
    			p.removePotionEffect(PotionEffectType.SLOWNESS);
    		}
    		if(p.hasPotionEffect(PotionEffectType.MINING_FATIGUE))
    		{
    			p.removePotionEffect(PotionEffectType.MINING_FATIGUE);
    		}
    		if(p.hasPotionEffect(PotionEffectType.UNLUCK))
    		{
    			p.removePotionEffect(PotionEffectType.UNLUCK);
    		}
    		if(p.hasPotionEffect(PotionEffectType.WEAKNESS))
    		{
    			p.removePotionEffect(PotionEffectType.WEAKNESS);
    		}
    		if(p.hasPotionEffect(PotionEffectType.WITHER))
    		{
    			p.removePotionEffect(PotionEffectType.WITHER);
    		}
    		if(p.hasPotionEffect(PotionEffectType.DARKNESS))
    		{
    			p.removePotionEffect(PotionEffectType.DARKNESS);
    		}
    		if(p.hasPotionEffect(PotionEffectType.WIND_CHARGED))
    		{
    			p.removePotionEffect(PotionEffectType.WIND_CHARGED);
    		}
    		if(p.hasPotionEffect(PotionEffectType.OOZING))
    		{
    			p.removePotionEffect(PotionEffectType.OOZING);
    		}
    		if(p.hasPotionEffect(PotionEffectType.INFESTED))
    		{
    			p.removePotionEffect(PotionEffectType.INFESTED);
    		}
    		p.setFireTicks(0);
        	p.setFreezeTicks(0);
	}
	
	
	
	
	final private void effectdamage(Player p) {
		if (p.hasPotionEffect(PotionEffectType.STRENGTH))
		{
			player_damage.put(p.getName(),player_damage.get(p.getName())+p.getPotionEffect(PotionEffectType.STRENGTH).getAmplifier()*3);
		}
		if (p.hasPotionEffect(PotionEffectType.WEAKNESS))
		{
			player_damage.put(p.getName(),player_damage.get(p.getName())-p.getPotionEffect(PotionEffectType.WEAKNESS).getAmplifier()*3);
		}
	}
	
	final protected Double bbArrow(final AbstractArrow ar) {
		Double damage = ar.getVelocity().length()*ar.getDamage();
		
		return damage;
	}
	

	final public boolean DamageGetter(Player p) {
	
		final ItemStack mi = p.getInventory().getItemInMainHand();
		final ItemStack oi = p.getInventory().getItemInOffHand();
	
		final int classnum = ClassData.pc.getOrDefault(p.getUniqueId(),-1);
	
		
		if(classnum == 0) { //Swordman
	
			if(mi.getType().name().contains("SWORD")&& !oi.getType().name().contains("BANNER_PATTERN")&& !(oi.getType()==Material.TRIDENT)&& !(oi.getType()==Material.SHIELD))
			{
				player_damage.put(p.getName(), (p.getAttribute(Attribute.ATTACK_DAMAGE).getValue()) * (1 + mi.getEnchantmentLevel(Enchantment.SHARPNESS)*0.10) + p.getLevel()*0.125);
				effectdamage(p);
				
				return true;
			}
			else {
				player_damage.put(p.getName(),0d);
			}
		}
		
		else if(classnum == 1) {//berserker
	
			if(mi.getType().name().contains("SWORD")&&!oi.getType().name().contains("BANNER_PATTERN")&& !(oi.getType()==Material.TRIDENT)&& !(oi.getType()==Material.SHIELD))
			{
				player_damage.put(p.getName(), (p.getAttribute(Attribute.ATTACK_DAMAGE).getValue()) * (1 + mi.getEnchantmentLevel(Enchantment.SHARPNESS)*0.10) + p.getLevel()*0.125);
				effectdamage(p);
				
				return true;
			}
			else {
				player_damage.put(p.getName(),0d);
			}
		}
	
	
		
		else if(classnum == 2) {	//Hunter				
	
			if(mi.getType().name().contains("_AXE")&& !oi.getType().name().contains("SHIELD")&& !oi.getType().name().contains("BANNER_PATTERN")&& !(oi.getType()==Material.TRIDENT)&& !(oi.getType()==Material.SHIELD)) {
				player_damage.put(p.getName(), (p.getAttribute(Attribute.ATTACK_DAMAGE).getValue()) * (1 + mi.getEnchantmentLevel(Enchantment.SHARPNESS)*0.10) + p.getLevel()*0.125);
				effectdamage(p);
				
				return true;
			}
			else {
				player_damage.put(p.getName(), 0d);
			}
		
		}
		
		else if(classnum == 3) {	//Paladin					
	
			if(mi.getType().name().contains("_AXE")&& oi.getType().name().contains("SHIELD")&& !oi.getType().name().contains("BANNER_PATTERN")&& !(oi.getType()==Material.TRIDENT)) {
				player_damage.put(p.getName(), (p.getAttribute(Attribute.ATTACK_DAMAGE).getValue()) * (1 + mi.getEnchantmentLevel(Enchantment.SHARPNESS)*0.10) + p.getLevel()*0.125);
				effectdamage(p);
				return true;
			}
			else {
				player_damage.put(p.getName(), 0d);
			}
		
		}
		
	
		
		else if(classnum == 4) {//Sniper
	
			if(mi.getType() == Material.CROSSBOW&& !oi.getType().name().contains("BANNER_PATTERN")&& !(oi.getType()==Material.TRIDENT)&& !(oi.getType()==Material.SHIELD))
			{
				player_damage.put(p.getName(), (p.getAttribute(Attribute.ATTACK_DAMAGE).getValue()) * (1 + mi.getEnchantmentLevel(Enchantment.POWER)*0.10) + p.getLevel()*0.125);
				effectdamage(p);
				return true;
			}
			else {
				player_damage.put(p.getName(), 0d);
			}
		}
		
		else if(classnum == 5) {//Launcher
			if(mi.getType() == Material.BOW&& !oi.getType().name().contains("BANNER_PATTERN")&& !(oi.getType()==Material.TRIDENT)&& !(oi.getType()==Material.SHIELD))
			{

				player_damage.put(p.getName(), (p.getAttribute(Attribute.ATTACK_DAMAGE).getValue()) * (1 + mi.getEnchantmentLevel(Enchantment.POWER)*0.10) + p.getLevel()*0.125);
				effectdamage(p);
				
				return true;
			}
			else {
				player_damage.put(p.getName(), 0d);
			}
		}
	
		else if(classnum == 6) { //Archer
	
			if(mi.getType()==Material.BOW && !oi.getType().name().contains("BANNER_PATTERN")&& !(oi.getType()==Material.TRIDENT)&& !(oi.getType()==Material.SHIELD))
			{
				player_damage.put(p.getName(), (p.getAttribute(Attribute.ATTACK_DAMAGE).getValue()) * (1 + mi.getEnchantmentLevel(Enchantment.POWER)*0.10) + p.getLevel()*0.125);
				effectdamage(p);
					
					return true;
			}
			else {
				player_damage.put(p.getName(), 0d);
			}
		}
	
	
		else if(classnum == 61) { //Medic
	
			if(mi.getType() == Material.CROSSBOW&& !oi.getType().name().contains("BANNER_PATTERN")&& !(oi.getType()==Material.TRIDENT)&& !(oi.getType()==Material.SHIELD))
			{
				player_damage.put(p.getName(), (p.getAttribute(Attribute.ATTACK_DAMAGE).getValue()) * (1 + mi.getEnchantmentLevel(Enchantment.POWER)*0.10) + p.getLevel()*0.125);
				effectdamage(p);
				
				return true;
			}
			else {
				player_damage.put(p.getName(), 0d);
			}
		}
		
		else if(classnum == 7) { // Boxer
			if(mi.getType().name().contains("BANNER_PATTERN") && mi.hasItemMeta() && mi.getItemMeta().hasCustomModelData() && oi.getType().name().contains("BANNER_PATTERN") && oi.hasItemMeta() && oi.getItemMeta().hasCustomModelData()
					&& mi.getItemMeta().getCustomModelData() == oi.getItemMeta().getCustomModelData() && (oi.getType() == mi.getType()) &&
					((mi.getItemMeta().getItemName().contains("CopiedKnuckle")&&!oi.getItemMeta().getItemName().contains("CopiedKnuckle")) ||
							(!mi.getItemMeta().getItemName().contains("CopiedKnuckle")&&oi.getItemMeta().getItemName().contains("CopiedKnuckle"))) )
			{
				player_damage.put(p.getName(), (p.getAttribute(Attribute.ATTACK_DAMAGE).getValue()) * (1 + mi.getEnchantmentLevel(Enchantment.SHARPNESS)*0.10) + p.getLevel()*0.125);
				effectdamage(p);
				return true;
			}
			
			else {
				player_damage.put(p.getName(), 0d);
			}
		}
	
	
		else if(classnum == 8) { //Wrestler
			if(mi.getType().name().contains("BANNER_PATTERN") && mi.hasItemMeta() && mi.getItemMeta().hasCustomModelData() && oi.getType().name().contains("BANNER_PATTERN") && oi.hasItemMeta() && oi.getItemMeta().hasCustomModelData()
					&& mi.getItemMeta().getCustomModelData() == oi.getItemMeta().getCustomModelData() && (oi.getType() == mi.getType()) &&
							((mi.getItemMeta().getItemName().contains("CopiedKnuckle")&&!oi.getItemMeta().getItemName().contains("CopiedKnuckle")) ||
									(!mi.getItemMeta().getItemName().contains("CopiedKnuckle")&&oi.getItemMeta().getItemName().contains("CopiedKnuckle"))) )
			{
					player_damage.put(p.getName(), (p.getAttribute(Attribute.ATTACK_DAMAGE).getValue()) * (1 + mi.getEnchantmentLevel(Enchantment.SHARPNESS)*0.10) + p.getLevel()*0.125);
					effectdamage(p);
					return true;
			}
			else {
				player_damage.put(p.getName(), 0d);
			}
		}
		
		else if(classnum == 9) { //Tamer
			if(mi.getType().name().contains("BANNER_PATTERN") && mi.hasItemMeta() && mi.getItemMeta().hasCustomModelData() && oi.getType().name().contains("BANNER_PATTERN") && oi.hasItemMeta() && oi.getItemMeta().hasCustomModelData()
					&& mi.getItemMeta().getCustomModelData() == oi.getItemMeta().getCustomModelData() && (oi.getType() == mi.getType()) &&
							((mi.getItemMeta().getItemName().contains("CopiedKnuckle")&&!oi.getItemMeta().getItemName().contains("CopiedKnuckle")) ||
									(!mi.getItemMeta().getItemName().contains("CopiedKnuckle")&&oi.getItemMeta().getItemName().contains("CopiedKnuckle"))) )
			{
					player_damage.put(p.getName(), (p.getAttribute(Attribute.ATTACK_DAMAGE).getValue()) * (1 + mi.getEnchantmentLevel(Enchantment.SHARPNESS)*0.10) + p.getLevel()*0.125);
					effectdamage(p);
					
					return true;
			}
			else {
				player_damage.put(p.getName(), 0d);
			}
		}
		
	
		else if(classnum == 10) { //Taoist
			if(mi.getType().name().contains("BANNER_PATTERN") && mi.hasItemMeta() && mi.getItemMeta().hasCustomModelData() && oi.getType().name().contains("BANNER_PATTERN") && oi.hasItemMeta() && oi.getItemMeta().hasCustomModelData()
					&& mi.getItemMeta().getCustomModelData() == oi.getItemMeta().getCustomModelData() && (oi.getType() == mi.getType()) &&
							((mi.getItemMeta().getItemName().contains("CopiedKnuckle")&&!oi.getItemMeta().getItemName().contains("CopiedKnuckle")) ||
									(!mi.getItemMeta().getItemName().contains("CopiedKnuckle")&&oi.getItemMeta().getItemName().contains("CopiedKnuckle"))) )
			{
					player_damage.put(p.getName(), (p.getAttribute(Attribute.ATTACK_DAMAGE).getValue()) * (1 + mi.getEnchantmentLevel(Enchantment.SHARPNESS)*0.10) + p.getLevel()*0.125);
					effectdamage(p);
					
					return true;
			}
			else {
				player_damage.put(p.getName(), 0d);
			}
		}
		
		else if(classnum == 11) { //Illusionist
	
			if(mi.getType()==Material.BLAZE_ROD && mi.hasItemMeta() && mi.getItemMeta().hasCustomModelData()&& !oi.getType().name().contains("BANNER_PATTERN")&& !(oi.getType()==Material.TRIDENT)&& !(oi.getType()==Material.SHIELD))
			{
					player_damage.put(p.getName(), (p.getAttribute(Attribute.ATTACK_DAMAGE).getValue()) * (1 + mi.getEnchantmentLevel(Enchantment.SHARPNESS)*0.10) + p.getLevel()*0.125);
					effectdamage(p);
					
					return true;
			}
			else {
				player_damage.put(p.getName(),0d);
			}
		}
		
		else if(classnum == 12) {//Firemage
			if(mi.getType()==Material.BLAZE_ROD && mi.hasItemMeta() && mi.getItemMeta().hasCustomModelData()&& !oi.getType().name().contains("BANNER_PATTERN")&& !(oi.getType()==Material.TRIDENT)&& !(oi.getType()==Material.SHIELD))
			{
					player_damage.put(p.getName(), (p.getAttribute(Attribute.ATTACK_DAMAGE).getValue()) * (1 + mi.getEnchantmentLevel(Enchantment.SHARPNESS)*0.10) + p.getLevel()*0.125);
					effectdamage(p);
					
					return true;
			}
			else {
				player_damage.put(p.getName(), 0d);
			}
		}
	
	
		
		else if(classnum == 13) { //Witherist
			if(mi.getType().name().contains("HOE")&& !oi.getType().name().contains("BANNER_PATTERN")&& !(oi.getType()==Material.TRIDENT)&& !(oi.getType()==Material.SHIELD))
			{
				player_damage.put(p.getName(), (p.getAttribute(Attribute.ATTACK_DAMAGE).getValue()) * (1 + mi.getEnchantmentLevel(Enchantment.SHARPNESS)*0.10) + p.getLevel()*0.125);
				effectdamage(p);
				return true;
			}
			else {
				player_damage.put(p.getName(), 0d);
			}
		}
		
		
		else if(classnum == 14) { //WitchDoctor
	
			if(mi.getType().name().contains("HOE")&&!oi.getType().name().contains("BANNER_PATTERN")&& !(oi.getType()==Material.TRIDENT)&& !(oi.getType()==Material.SHIELD))
			{
				player_damage.put(p.getName(), (p.getAttribute(Attribute.ATTACK_DAMAGE).getValue()) * (1 + mi.getEnchantmentLevel(Enchantment.SHARPNESS)*0.10) + p.getLevel()*0.125);
				effectdamage(p);
				return true;
			}
			else {
				player_damage.put(p.getName(), 0d);
			}
		}
		
		else if(classnum == 15) {//Chemist
			if(mi.getType().name().contains("PICKAXE")&& !oi.getType().name().contains("BANNER_PATTERN")&& !(oi.getType()==Material.TRIDENT)&& !(oi.getType()==Material.SHIELD))
			{
					player_damage.put(p.getName(), (p.getAttribute(Attribute.ATTACK_DAMAGE).getValue()) * (1 + mi.getEnchantmentLevel(Enchantment.SHARPNESS)*0.10) + p.getLevel()*0.125);
					effectdamage(p);
					
					return true;
			}
			else {
				player_damage.put(p.getName(), 0d);
			}
			
		}
		
		else if(classnum == 16) {//Forger
			if(mi.getType().name().contains("PICKAXE")&& !oi.getType().name().contains("BANNER_PATTERN")&& !(oi.getType()==Material.TRIDENT)&& !(oi.getType()==Material.SHIELD))
			{
					player_damage.put(p.getName(), (p.getAttribute(Attribute.ATTACK_DAMAGE).getValue()) * (1 + mi.getEnchantmentLevel(Enchantment.SHARPNESS)*0.10) + p.getLevel()*0.125);
					effectdamage(p);
					return true;
			}
			else {
				player_damage.put(p.getName(), 0d);
			}
		}
		
		else if(classnum == 17) {//Engineer
			if(mi.getType().name().contains("PICKAXE")&& !oi.getType().name().contains("BANNER_PATTERN")&& !(oi.getType()==Material.TRIDENT)&& !(oi.getType()==Material.SHIELD))
			{
					player_damage.put(p.getName(), (p.getAttribute(Attribute.ATTACK_DAMAGE).getValue()) * (1 + mi.getEnchantmentLevel(Enchantment.SHARPNESS)*0.10) + p.getLevel()*0.125);
					effectdamage(p);
					
					return true;
			}
			else {
				player_damage.put(p.getName(), 0d);
			}
		}
	
		else if(classnum == 18) { // Cooker
			if(mi.getType().name().contains("SHOVEL")&& !oi.getType().name().contains("BANNER_PATTERN")&& !(oi.getType()==Material.TRIDENT)&& !(oi.getType()==Material.SHIELD))
			{
				player_damage.put(p.getName(), (p.getAttribute(Attribute.ATTACK_DAMAGE).getValue()) * (1 + mi.getEnchantmentLevel(Enchantment.SHARPNESS)*0.10) + p.getLevel()*0.125);
				effectdamage(p);
				return true;
			}
			else {
				player_damage.put(p.getName(), 0d);
			}
		}
	
		
	
		
		else if(classnum == 19) { //Nobility
	
			if(mi.getType()==Material.TRIDENT&& !oi.getType().name().contains("BANNER_PATTERN")&& !(oi.getType()==Material.TRIDENT)&& !(oi.getType()==Material.SHIELD))
			{
					player_damage.put(p.getName(), (p.getAttribute(Attribute.ATTACK_DAMAGE).getValue()) * (1 + mi.getEnchantmentLevel(Enchantment.SHARPNESS)*0.10) + p.getLevel()*0.125);
					effectdamage(p);
					
					return true;
			}
			else {
				player_damage.put(p.getName(), 0d);
			}
		}
		
	
		
		else if(classnum == 20) { //OceanKnight
	
	
			if(mi.getType()==Material.TRIDENT&& oi.getType()==Material.SHIELD&& !oi.getType().name().contains("BANNER_PATTERN")&& !(oi.getType()==Material.TRIDENT))
			{
					player_damage.put(p.getName(), (p.getAttribute(Attribute.ATTACK_DAMAGE).getValue()) * (1 + mi.getEnchantmentLevel(Enchantment.SHARPNESS)*0.10) + p.getLevel()*0.125);
					effectdamage(p);
					
					return true;
			}
			else if(mi.getType()==Material.SHIELD&&oi.getType()==Material.TRIDENT&& !oi.getType().name().contains("BANNER_PATTERN")&& !(mi.getType()==Material.TRIDENT))
			{
					player_damage.put(p.getName(), (p.getAttribute(Attribute.ATTACK_DAMAGE).getValue()) * (1 + oi.getEnchantmentLevel(Enchantment.SHARPNESS)*0.10) + p.getLevel()*0.125);
					effectdamage(p);
					
					return true;
			}
			else {
				player_damage.put(p.getName(), 0d);
			}
		}
		
		else if(classnum == 21) { //Frostman
	
				p.setFreezeTicks(0);
				if(mi.getType() == Material.PRISMARINE_SHARD&& mi.hasItemMeta() && mi.getItemMeta().hasCustomModelData()&& !oi.getType().name().contains("BANNER_PATTERN")&& !(oi.getType()==Material.TRIDENT)&& !(oi.getType()==Material.SHIELD))
				{
						player_damage.put(p.getName(), (p.getAttribute(Attribute.ATTACK_DAMAGE).getValue()) * (1 + mi.getEnchantmentLevel(Enchantment.SHARPNESS)*0.10) + p.getLevel()*0.125);
						effectdamage(p);
						return true;
				}
				else {
					player_damage.put(p.getName(), 0d);
				}
		}
		
		else if(classnum == 22) {//Angler
			if(mi.getType()==Material.FISHING_ROD && !oi.getType().name().contains("BANNER_PATTERN")&& !(oi.getType()==Material.TRIDENT)&& !(oi.getType()==Material.SHIELD))
			{
					player_damage.put(p.getName(), (p.getAttribute(Attribute.ATTACK_DAMAGE).getValue()) * (1 + mi.getEnchantmentLevel(Enchantment.SHARPNESS)*0.10) + p.getLevel()*0.125);
					effectdamage(p);
					
					return true;
			}
			else {
				player_damage.put(p.getName(), 0d);
			}
		}
		
		
	
	
		else if(classnum == 25) { // Broiler
			if(mi.getType().name().contains("SHOVEL")&& !oi.getType().name().contains("BANNER_PATTERN")&& !(oi.getType()==Material.TRIDENT)&& !(oi.getType()==Material.SHIELD))
			{
					player_damage.put(p.getName(), (p.getAttribute(Attribute.ATTACK_DAMAGE).getValue()) * (1 + mi.getEnchantmentLevel(Enchantment.SHARPNESS)*0.10) + p.getLevel()*0.125);
					effectdamage(p);
					
					return true;
			}
			else {
				player_damage.put(p.getName(), 0d);
			}
		}
		
		EldGetter(p.getInventory().getArmorContents(), mi, oi, p);
		ElrGetter(p.getInventory().getArmorContents(), p);
		
		return false;
		
	}
	
	final public ItemMeta putelm(ItemMeta im, Integer el, Double m, Player p) {
		ItemMeta rm = im.clone();
		PersistentDataContainer isp = rm.getPersistentDataContainer();
		if(el == 5) {
			isp.set(windydn, PersistentDataType.DOUBLE, isp.getOrDefault(windydn, PersistentDataType.DOUBLE,0d) + m);
			if (rm.hasLore()) {
				List<String> lore = rm.getLore();
				if(p.getLocale().equalsIgnoreCase("ko_kr")) {
					lore.removeIf(l -> l.contains("<[바람 계열 공격력]>"));
					lore.removeIf(l -> l.contains("<[Power Of Wind]>"));
					lore.add(ChatColor.GRAY + "<[바람 계열 공격력]> +" +  Math.round(isp.getOrDefault(windydn, PersistentDataType.DOUBLE,0d)*100.0) + "% | "
							+ChatColor.GREEN + "<[대지 계열 공격력]> +" +  Math.round(isp.getOrDefault(earthdn, PersistentDataType.DOUBLE,0d)*100.0) + "%");
					lore.add(ChatColor.BOLD + "모든 스킬에 바람피해가 추가로 적용됩니다");
				}
				else {
					lore.removeIf(l -> l.contains("<[바람 계열 공격력]>"));
					lore.removeIf(l -> l.contains("<[Power Of Wind]>"));
					lore.add(ChatColor.GRAY + "<[Power Of Wind]> +" +  Math.round(isp.getOrDefault(windydn, PersistentDataType.DOUBLE,0d)*100.0) + "% | " 
							+ ChatColor.GREEN + "<[Power Of Earth]> +" +  Math.round(isp.getOrDefault(earthdn, PersistentDataType.DOUBLE,0d)*100.0) + "%");
					lore.add(ChatColor.BOLD + "Additional Wind Damage applies to all skills.");
				}
				rm.setLore(lore);
			} else {
				List<String> lore = new ArrayList<>();
				if(p.getLocale().equalsIgnoreCase("ko_kr")) {
					lore.removeIf(l -> l.contains("<[바람 계열 공격력]>"));
					lore.removeIf(l -> l.contains("<[Power Of Wind]>"));
					lore.add(ChatColor.GRAY + "<[바람 계열 공격력]> +" +  Math.round(isp.getOrDefault(windydn, PersistentDataType.DOUBLE,0d)*100.0) + "% | "
							+ChatColor.GREEN + "<[대지 계열 공격력]> +" +  Math.round(isp.getOrDefault(earthdn, PersistentDataType.DOUBLE,0d)*100.0) + "%");
					lore.add(ChatColor.BOLD + "모든 스킬에 바람피해가 추가로 적용됩니다");
				}
				else {
					lore.removeIf(l -> l.contains("<[바람 계열 공격력]>"));
					lore.removeIf(l -> l.contains("<[Power Of Wind]>"));
					lore.add(ChatColor.GRAY + "<[Power Of Wind]> +" +  Math.round(isp.getOrDefault(windydn, PersistentDataType.DOUBLE,0d)*100.0) + "% | " 
							+ ChatColor.GREEN + "<[Power Of Earth]> +" +  Math.round(isp.getOrDefault(earthdn, PersistentDataType.DOUBLE,0d)*100.0) + "%");
					lore.add(ChatColor.BOLD + "Additional Wind Damage applies to all skills.");
				}
				rm.setLore(lore);
			}
			return rm;
		}
		else if(el == 14) {
			isp.set(earthdn, PersistentDataType.DOUBLE, isp.getOrDefault(earthdn, PersistentDataType.DOUBLE,0d) + m);
			if (rm.hasLore()) {
				List<String> lore = rm.getLore();
				if(p.getLocale().equalsIgnoreCase("ko_kr")) {
					lore.removeIf(l -> l.contains("<[대지 계열 공격력]>"));
					lore.removeIf(l -> l.contains("<[Power Of Earth]>"));
					lore.add(ChatColor.GRAY + "<[바람 계열 공격력]> +" +  Math.round(isp.getOrDefault(windydn, PersistentDataType.DOUBLE,0d)*100.0) + "% | "
							+ChatColor.GREEN + "<[대지 계열 공격력]> +" +  Math.round(isp.getOrDefault(earthdn, PersistentDataType.DOUBLE,0d)*100.0) + "%");
					lore.add(ChatColor.BOLD + "모든 스킬에 대지 피해가 추가로 적용됩니다");
				}
				else {
					lore.removeIf(l -> l.contains("<[대지 계열 공격력]>"));
					lore.removeIf(l -> l.contains("<[Power Of Earth]>"));
					lore.add(ChatColor.GRAY + "<[Power Of Wind]> +" +  Math.round(isp.getOrDefault(windydn, PersistentDataType.DOUBLE,0d)*100.0) + "% | " 
							+ ChatColor.GREEN + "<[Power Of Earth]> +" +  Math.round(isp.getOrDefault(earthdn, PersistentDataType.DOUBLE,0d)*100.0) + "%");
					lore.add(ChatColor.BOLD + "Additional Earth Damage applies to all skills.");
				}
				rm.setLore(lore);
			} else {
				List<String> lore = new ArrayList<>();
				if(p.getLocale().equalsIgnoreCase("ko_kr")) {
					lore.removeIf(l -> l.contains("<[대지 계열 공격력]>"));
					lore.removeIf(l -> l.contains("<[Power Of Earth]>"));
					lore.add(ChatColor.GRAY + "<[바람 계열 공격력]> +" +  Math.round(isp.getOrDefault(windydn, PersistentDataType.DOUBLE,0d)*100.0) + "% | "
							+ChatColor.GREEN + "<[대지 계열 공격력]> +" +  Math.round(isp.getOrDefault(earthdn, PersistentDataType.DOUBLE,0d)*100.0) + "%");
					lore.add(ChatColor.BOLD + "모든 스킬에 대지 피해가 추가로 적용됩니다");
				}
				else {
					lore.removeIf(l -> l.contains("<[대지 계열 공격력]>"));
					lore.removeIf(l -> l.contains("<[Power Of Earth]>"));
					lore.add(ChatColor.GRAY + "<[Power Of Wind]> +" +  Math.round(isp.getOrDefault(windydn, PersistentDataType.DOUBLE,0d)*100.0) + "% | " 
							+ ChatColor.GREEN + "<[Power Of Earth]> +" +  Math.round(isp.getOrDefault(earthdn, PersistentDataType.DOUBLE,0d)*100.0) + "%");
					lore.add(ChatColor.BOLD + "Additional Earth Damage applies to all skills.");
				}
				rm.setLore(lore);
			}
			return rm;
		}
		else if(el == 6) {
			isp.set(frostdn, PersistentDataType.DOUBLE, isp.getOrDefault(frostdn, PersistentDataType.DOUBLE,0d) + m);
			if (rm.hasLore()) {
				List<String> lore = rm.getLore();
				if(p.getLocale().equalsIgnoreCase("ko_kr")) {
					lore.removeIf(l -> l.contains("<[서리 계열 공격력]>"));
					lore.removeIf(l -> l.contains("<[Power Of Frost]>"));
					lore.add(ChatColor.AQUA + "<[서리 계열 공격력]> +" + Math.round(isp.getOrDefault(frostdn, PersistentDataType.DOUBLE,0d)*100.0) + "% | "
							+ChatColor.DARK_GREEN + "<[맹독 계열 공격력]> +" + Math.round(isp.getOrDefault(poisondn, PersistentDataType.DOUBLE,0d)*100.0) + "%");
					lore.add(ChatColor.BOLD + "모든 스킬에 서리 피해가 추가로 적용됩니다");
				}
				else {
					lore.removeIf(l -> l.contains("<[서리 계열 공격력]>"));
					lore.removeIf(l -> l.contains("<[Power Of Frost]>"));
					lore.add(ChatColor.AQUA + "<[Power Of Frost]> +" + Math.round(isp.getOrDefault(frostdn, PersistentDataType.DOUBLE,0d)*100.0) + "% | "
							+ChatColor.DARK_GREEN + "<[Power Of Poison]> +" + Math.round(isp.getOrDefault(poisondn, PersistentDataType.DOUBLE,0d)*100.0) + "%");
					lore.add(ChatColor.BOLD + "Additional Frost Damage applies to all skills.");
				}
				rm.setLore(lore);
			} else {
				List<String> lore = new ArrayList<>();
				if(p.getLocale().equalsIgnoreCase("ko_kr")) {
					lore.removeIf(l -> l.contains("<[서리 계열 공격력]>"));
					lore.removeIf(l -> l.contains("<[Power Of Frost]>"));
					lore.add(ChatColor.AQUA + "<[서리 계열 공격력]> +" + Math.round(isp.getOrDefault(frostdn, PersistentDataType.DOUBLE,0d)*100.0) + "% | "
							+ChatColor.DARK_GREEN + "<[맹독 계열 공격력]> +" + Math.round(isp.getOrDefault(poisondn, PersistentDataType.DOUBLE,0d)*100.0) + "%");
					lore.add(ChatColor.BOLD + "모든 스킬에 서리 피해가 추가로 적용됩니다");
				}
				else {
					lore.removeIf(l -> l.contains("<[서리 계열 공격력]>"));
					lore.removeIf(l -> l.contains("<[Power Of Frost]>"));
					lore.add(ChatColor.AQUA + "<[Power Of Frost]> +" + Math.round(isp.getOrDefault(frostdn, PersistentDataType.DOUBLE,0d)*100.0) + "% | "
							+ChatColor.DARK_GREEN + "<[Power Of Poison]> +" + Math.round(isp.getOrDefault(poisondn, PersistentDataType.DOUBLE,0d)*100.0) + "%");
					lore.add(ChatColor.BOLD + "Additional Frost Damage applies to all skills.");
				}
				rm.setLore(lore);
			}
			return rm;
		}
		else if(el == 7) {
			isp.set(waterdn, PersistentDataType.DOUBLE, isp.getOrDefault(waterdn, PersistentDataType.DOUBLE,0d) + m);
			if (rm.hasLore()) {
				List<String> lore = rm.getLore();
				if(p.getLocale().equalsIgnoreCase("ko_kr")) {
					lore.removeIf(l -> l.contains("<[물 계열 공격력]>"));
					lore.removeIf(l -> l.contains("<[Power Of Water]>"));
					lore.add(ChatColor.BLUE + "<[물 계열 공격력]> +" +  Math.round(isp.getOrDefault(waterdn, PersistentDataType.DOUBLE,0d)*100.0) + "% | "
							+ChatColor.RED + "<[화염 계열 공격력]> +" + Math.round(isp.getOrDefault(flamedn, PersistentDataType.DOUBLE,0d)*100.0) + "%");
					lore.add(ChatColor.BOLD + "모든 스킬에 물 피해가 추가로 적용됩니다");
				}
				else {
					lore.removeIf(l -> l.contains("<[물 계열 공격력]>"));
					lore.removeIf(l -> l.contains("<[Power Of Water]>"));
					lore.add(ChatColor.BLUE + "<[Power Of Water]> +" +  Math.round(isp.getOrDefault(waterdn, PersistentDataType.DOUBLE,0d)*100.0) + "% | "
							+ChatColor.RED + "<[Power Of Flame]> +" + Math.round(isp.getOrDefault(flamedn, PersistentDataType.DOUBLE,0d)*100.0) + "%");
					lore.add(ChatColor.BOLD + "Additional Water Damage applies to all skills.");
				}
				rm.setLore(lore);
			} else {
				List<String> lore = new ArrayList<>();
				if(p.getLocale().equalsIgnoreCase("ko_kr")) {
					lore.removeIf(l -> l.contains("<[물 계열 공격력]>"));
					lore.removeIf(l -> l.contains("<[Power Of Water]>"));
					lore.add(ChatColor.BLUE + "<[물 계열 공격력]> +" +  Math.round(isp.getOrDefault(waterdn, PersistentDataType.DOUBLE,0d)*100.0) + "% | "
							+ChatColor.RED + "<[화염 계열 공격력]> +" + Math.round(isp.getOrDefault(flamedn, PersistentDataType.DOUBLE,0d)*100.0) + "%");
					lore.add(ChatColor.BOLD + "모든 스킬에 물 피해가 추가로 적용됩니다");
				}
				else {
					lore.removeIf(l -> l.contains("<[물 계열 공격력]>"));
					lore.removeIf(l -> l.contains("<[Power Of Water]>"));
					lore.add(ChatColor.BLUE + "<[Power Of Water]> +" +  Math.round(isp.getOrDefault(waterdn, PersistentDataType.DOUBLE,0d)*100.0) + "% | "
							+ChatColor.RED + "<[Power Of Flame]> +" + Math.round(isp.getOrDefault(flamedn, PersistentDataType.DOUBLE,0d)*100.0) + "%");
					lore.add(ChatColor.BOLD + "Additional Water Damage applies to all skills.");
				}
				rm.setLore(lore);
			}
			return rm;
		}
		else if(el == 8) {
			isp.set(darkdn, PersistentDataType.DOUBLE, isp.getOrDefault(darkdn, PersistentDataType.DOUBLE,0d) + m);
			if (rm.hasLore()) {
				List<String> lore = rm.getLore();
				if(p.getLocale().equalsIgnoreCase("ko_kr")) {
					lore.removeIf(l -> l.contains("<[어둠 계열 공격력]>"));
					lore.removeIf(l -> l.contains("<[Power Of Darkness]>"));
					lore.add(ChatColor.DARK_GRAY + "<[어둠 계열 공격력]> +" +  Math.round(isp.getOrDefault(darkdn, PersistentDataType.DOUBLE,0d)*100.0) + "% | "
							+ChatColor.YELLOW + "<[번개 계열 공격력]> +" +  Math.round(isp.getOrDefault(lightningdn, PersistentDataType.DOUBLE,0d)*100.0) + "%");
					lore.add(ChatColor.BOLD + "모든 스킬에 어둠 피해가 추가로 적용됩니다");
				}
				else {
					lore.removeIf(l -> l.contains("<[어둠 계열 공격력]>"));
					lore.removeIf(l -> l.contains("<[Power Of Darkness]>"));
					lore.add(ChatColor.DARK_GRAY + "<[Power Of Darkness]> +" +  Math.round(isp.getOrDefault(darkdn, PersistentDataType.DOUBLE,0d)*100.0) + "% | "
							+ChatColor.YELLOW + "<[Power Of Lightning]> +" +  Math.round(isp.getOrDefault(lightningdn, PersistentDataType.DOUBLE,0d)*100.0) + "%");
					lore.add(ChatColor.BOLD + "Additional Darkness Damage applies to all skills.");
				}
				rm.setLore(lore);
			} else {
				List<String> lore = new ArrayList<>();
				if(p.getLocale().equalsIgnoreCase("ko_kr")) {
					lore.removeIf(l -> l.contains("<[어둠 계열 공격력]>"));
					lore.removeIf(l -> l.contains("<[Power Of Darkness]>"));
					lore.add(ChatColor.DARK_GRAY + "<[어둠 계열 공격력]> +" +  Math.round(isp.getOrDefault(darkdn, PersistentDataType.DOUBLE,0d)*100.0) + "% | "
							+ChatColor.YELLOW + "<[번개 계열 공격력]> +" +  Math.round(isp.getOrDefault(lightningdn, PersistentDataType.DOUBLE,0d)*100.0) + "%");
					lore.add(ChatColor.BOLD + "모든 스킬에 어둠 피해가 추가로 적용됩니다");
				}
				else {
					lore.removeIf(l -> l.contains("<[어둠 계열 공격력]>"));
					lore.removeIf(l -> l.contains("<[Power Of Darkness]>"));
					lore.add(ChatColor.DARK_GRAY + "<[Power Of Darkness]> +" +  Math.round(isp.getOrDefault(darkdn, PersistentDataType.DOUBLE,0d)*100.0) + "% | "
							+ChatColor.YELLOW + "<[Power Of Lightning]> +" +  Math.round(isp.getOrDefault(lightningdn, PersistentDataType.DOUBLE,0d)*100.0) + "%");
					lore.add(ChatColor.BOLD + "Additional Darkness Damage applies to all skills.");
				}
				rm.setLore(lore);
			}
			return rm;
		}
		else if(el == 9) {
			isp.set(lightningdn, PersistentDataType.DOUBLE, isp.getOrDefault(lightningdn, PersistentDataType.DOUBLE,0d) + m);
			if (rm.hasLore()) {
				List<String> lore = rm.getLore();
				if(p.getLocale().equalsIgnoreCase("ko_kr")) {
					lore.removeIf(l -> l.contains("<[번개 계열 공격력]>"));
					lore.removeIf(l -> l.contains("<[Power Of Lightning]>"));
					lore.add(ChatColor.DARK_GRAY + "<[어둠 계열 공격력]> +" +  Math.round(isp.getOrDefault(darkdn, PersistentDataType.DOUBLE,0d)*100.0) + "% | "
							+ChatColor.YELLOW + "<[번개 계열 공격력]> +" +  Math.round(isp.getOrDefault(lightningdn, PersistentDataType.DOUBLE,0d)*100.0) + "%");
					lore.add(ChatColor.BOLD + "모든 스킬에 번개 피해가 추가로 적용됩니다");
				}
				else {
					lore.removeIf(l -> l.contains("<[번개 계열 공격력]>"));
					lore.removeIf(l -> l.contains("<[Power Of Lightning]>"));
					lore.add(ChatColor.DARK_GRAY + "<[Power Of Darkness]> +" +  Math.round(isp.getOrDefault(darkdn, PersistentDataType.DOUBLE,0d)*100.0) + "% | "
							+ChatColor.YELLOW + "<[Power Of Lightning]> +" +  Math.round(isp.getOrDefault(lightningdn, PersistentDataType.DOUBLE,0d)*100.0) + "%");
					lore.add(ChatColor.BOLD + "Additional Lightning Damage applies to all skills.");
				}
				rm.setLore(lore);
			} else {
				List<String> lore = new ArrayList<>();
				if(p.getLocale().equalsIgnoreCase("ko_kr")) {
					lore.removeIf(l -> l.contains("<[번개 계열 공격력]>"));
					lore.removeIf(l -> l.contains("<[Power Of Lightning]>"));
					lore.add(ChatColor.DARK_GRAY + "<[어둠 계열 공격력]> +" +  Math.round(isp.getOrDefault(darkdn, PersistentDataType.DOUBLE,0d)*100.0) + "% | "
							+ChatColor.YELLOW + "<[번개 계열 공격력]> +" +  Math.round(isp.getOrDefault(lightningdn, PersistentDataType.DOUBLE,0d)*100.0) + "%");
					lore.add(ChatColor.BOLD + "모든 스킬에 번개 피해가 추가로 적용됩니다");
				}
				else {
					lore.removeIf(l -> l.contains("<[번개 계열 공격력]>"));
					lore.removeIf(l -> l.contains("<[Power Of Lightning]>"));
					lore.add(ChatColor.DARK_GRAY + "<[Power Of Darkness]> +" +  Math.round(isp.getOrDefault(darkdn, PersistentDataType.DOUBLE,0d)*100.0) + "% | "
							+ChatColor.YELLOW + "<[Power Of Lightning]> +" +  Math.round(isp.getOrDefault(lightningdn, PersistentDataType.DOUBLE,0d)*100.0) + "%");
					lore.add(ChatColor.BOLD + "Additional Lightning Damage applies to all skills.");
				}
				rm.setLore(lore);
			}
			return rm;
		}
		else if(el == 10) {
			isp.set(flamedn, PersistentDataType.DOUBLE, isp.getOrDefault(flamedn, PersistentDataType.DOUBLE,0d) + m);
			if (rm.hasLore()) {
				List<String> lore = rm.getLore();
				if(p.getLocale().equalsIgnoreCase("ko_kr")) {
					lore.removeIf(l -> l.contains("<[화염 계열 공격력]>"));
					lore.removeIf(l -> l.contains("<[Power Of Flame]>"));
					lore.add(ChatColor.BLUE + "<[물 계열 공격력]> +" +  Math.round(isp.getOrDefault(waterdn, PersistentDataType.DOUBLE,0d)*100.0) + "% | "
							+ChatColor.RED + "<[화염 계열 공격력]> +" + Math.round(isp.getOrDefault(flamedn, PersistentDataType.DOUBLE,0d)*100.0) + "%");
					lore.add(ChatColor.BOLD + "모든 스킬에 화염 피해가 추가로 적용됩니다");
				}
				else {
					lore.removeIf(l -> l.contains("<[화염 계열 공격력]>"));
					lore.removeIf(l -> l.contains("<[Power Of Flame]>"));
					lore.add(ChatColor.BLUE + "<[Power Of Water]> +" +  Math.round(isp.getOrDefault(waterdn, PersistentDataType.DOUBLE,0d)*100.0) + "% | "
							+ChatColor.RED + "<[Power Of Flame]> +" + Math.round(isp.getOrDefault(flamedn, PersistentDataType.DOUBLE,0d)*100.0) + "%");
					lore.add(ChatColor.BOLD + "Additional Flame Damage applies to all skills.");
				}
				rm.setLore(lore);
			} else {
				List<String> lore = new ArrayList<>();
				if(p.getLocale().equalsIgnoreCase("ko_kr")) {
					lore.removeIf(l -> l.contains("<[화염 계열 공격력]>"));
					lore.removeIf(l -> l.contains("<[Power Of Flame]>"));
					lore.add(ChatColor.BLUE + "<[물 계열 공격력]> +" +  Math.round(isp.getOrDefault(waterdn, PersistentDataType.DOUBLE,0d)*100.0) + "% | "
							+ChatColor.RED + "<[화염 계열 공격력]> +" + Math.round(isp.getOrDefault(flamedn, PersistentDataType.DOUBLE,0d)*100.0) + "%");
					lore.add(ChatColor.BOLD + "모든 스킬에 화염 피해가 추가로 적용됩니다");
				}
				else {
					lore.removeIf(l -> l.contains("<[바람 계열 공격력]>"));
					lore.removeIf(l -> l.contains("<[Power Of Flame]>"));
					lore.add(ChatColor.BLUE + "<[Power Of Water]> +" +  Math.round(isp.getOrDefault(waterdn, PersistentDataType.DOUBLE,0d)*100.0) + "% | "
							+ChatColor.RED + "<[Power Of Flame]> +" + Math.round(isp.getOrDefault(flamedn, PersistentDataType.DOUBLE,0d)*100.0) + "%");
					lore.add(ChatColor.BOLD + "Additional Flame Damage applies to all skills.");
				}
				rm.setLore(lore);
			}
			return rm;
		}
		else if(el == 11) {
			isp.set(poisondn, PersistentDataType.DOUBLE, isp.getOrDefault(poisondn, PersistentDataType.DOUBLE,0d) + m);
			if (rm.hasLore()) {
				List<String> lore = rm.getLore();
				if(p.getLocale().equalsIgnoreCase("ko_kr")) {
					lore.removeIf(l -> l.contains("<[맹독 계열 공격력]>"));
					lore.removeIf(l -> l.contains("<[Power Of Poison]>"));
					lore.add(ChatColor.AQUA + "<[서리 계열 공격력]> +" + Math.round(isp.getOrDefault(frostdn, PersistentDataType.DOUBLE,0d)*100.0) + "% | "
							+ChatColor.DARK_GREEN + "<[맹독 계열 공격력]> +" + Math.round(isp.getOrDefault(poisondn, PersistentDataType.DOUBLE,0d)*100.0) + "%");
					lore.add(ChatColor.BOLD + "모든 스킬에 맹독 피해가 추가로 적용됩니다");
				}
				else {
					lore.removeIf(l -> l.contains("<[맹독 계열 공격력]>"));
					lore.removeIf(l -> l.contains("<[Power Of Poison]>"));
					lore.add(ChatColor.AQUA + "<[Power Of Frost]> +" + Math.round(isp.getOrDefault(frostdn, PersistentDataType.DOUBLE,0d)*100.0) + "% | "
							+ChatColor.DARK_GREEN + "<[Power Of Poison]> +" + Math.round(isp.getOrDefault(poisondn, PersistentDataType.DOUBLE,0d)*100.0) + "%");
					lore.add(ChatColor.BOLD + "Additional Poison Damage applies to all skills.");
				}
				rm.setLore(lore);
			} else {
				List<String> lore = new ArrayList<>();
				if(p.getLocale().equalsIgnoreCase("ko_kr")) {
					lore.removeIf(l -> l.contains("<[맹독 계열 공격력]>"));
					lore.removeIf(l -> l.contains("<[Power Of Poison]>"));
					lore.add(ChatColor.AQUA + "<[서리 계열 공격력]> +" + Math.round(isp.getOrDefault(frostdn, PersistentDataType.DOUBLE,0d)*100.0) + "% | "
							+ChatColor.DARK_GREEN + "<[맹독 계열 공격력]> +" + Math.round(isp.getOrDefault(poisondn, PersistentDataType.DOUBLE,0d)*100.0) + "%");
					lore.add(ChatColor.BOLD + "모든 스킬에 맹독 피해가 추가로 적용됩니다");
				}
				else {
					lore.removeIf(l -> l.contains("<[맹독 계열 공격력]>"));
					lore.removeIf(l -> l.contains("<[Power Of Poison]>"));
					lore.add(ChatColor.AQUA + "<[Power Of Frost]> +" + Math.round(isp.getOrDefault(frostdn, PersistentDataType.DOUBLE,0d)*100.0) + "% | "
							+ChatColor.DARK_GREEN + "<[Power Of Poison]> +" + Math.round(isp.getOrDefault(poisondn, PersistentDataType.DOUBLE,0d)*100.0) + "%");
					lore.add(ChatColor.BOLD + "Additional Poison Damage applies to all skills.");
				}
				rm.setLore(lore);
			}
			return rm;
		}
		else {
			return rm;
		}
	}

	final public ItemMeta addelm(ItemMeta im, Integer el, Double m, Player p) {
		ItemMeta rm = im.clone();
		PersistentDataContainer isp = rm.getPersistentDataContainer();
		if(el== 5 || el == 0|| el == 2) {
			isp.set(windydn, PersistentDataType.DOUBLE, isp.getOrDefault(windydn, PersistentDataType.DOUBLE,0d) + m);
			if (rm.hasLore()) {
				List<String> lore = rm.getLore();
				if(p.getLocale().equalsIgnoreCase("ko_kr")) {
					lore.removeIf(l -> l.contains("<[바람 계열 공격력]>"));
					lore.removeIf(l -> l.contains("<[Power Of Wind]>"));
					lore.add(ChatColor.GRAY + "<[바람 계열 공격력]> +" +  Math.round(isp.getOrDefault(windydn, PersistentDataType.DOUBLE,0d)*100.0) + "% | "
							+ChatColor.GREEN + "<[대지 계열 공격력]> +" +  Math.round(isp.getOrDefault(earthdn, PersistentDataType.DOUBLE,0d)*100.0) + "%");
				}
				else {
					lore.removeIf(l -> l.contains("<[바람 계열 공격력]>"));
					lore.removeIf(l -> l.contains("<[Power Of Wind]>"));
					lore.add(ChatColor.GRAY + "<[Power Of Wind]> +" +  Math.round(isp.getOrDefault(windydn, PersistentDataType.DOUBLE,0d)*100.0) + "% | " 
							+ ChatColor.GREEN + "<[Power Of Earth]> +" +  Math.round(isp.getOrDefault(earthdn, PersistentDataType.DOUBLE,0d)*100.0) + "%");
				}
				rm.setLore(lore);
			} else {
				List<String> lore = new ArrayList<>();
				if(p.getLocale().equalsIgnoreCase("ko_kr")) {
					lore.removeIf(l -> l.contains("<[바람 계열 공격력]>"));
					lore.removeIf(l -> l.contains("<[Power Of Wind]>"));
					lore.add(ChatColor.GRAY + "<[바람 계열 공격력]> +" +  Math.round(isp.getOrDefault(windydn, PersistentDataType.DOUBLE,0d)*100.0) + "% | "
							+ChatColor.GREEN + "<[대지 계열 공격력]> +" +  Math.round(isp.getOrDefault(earthdn, PersistentDataType.DOUBLE,0d)*100.0) + "%");
				}
				else {
					lore.removeIf(l -> l.contains("<[바람 계열 공격력]>"));
					lore.removeIf(l -> l.contains("<[Power Of Wind]>"));
					lore.add(ChatColor.GRAY + "<[Power Of Wind]> +" +  Math.round(isp.getOrDefault(windydn, PersistentDataType.DOUBLE,0d)*100.0) + "% | " 
							+ ChatColor.GREEN + "<[Power Of Earth]> +" +  Math.round(isp.getOrDefault(earthdn, PersistentDataType.DOUBLE,0d)*100.0) + "%");
				}
				rm.setLore(lore);
			}
		}
		if(el == 14 || el == 1|| el == 2) {
			isp.set(earthdn, PersistentDataType.DOUBLE, isp.getOrDefault(earthdn, PersistentDataType.DOUBLE,0d) + m);
			if (rm.hasLore()) {
				List<String> lore = rm.getLore();
				if(p.getLocale().equalsIgnoreCase("ko_kr")) {
					lore.removeIf(l -> l.contains("<[바람 계열 공격력]>"));
					lore.removeIf(l -> l.contains("<[Power Of Wind]>"));
					lore.add(ChatColor.GRAY + "<[바람 계열 공격력]> +" +  Math.round(isp.getOrDefault(windydn, PersistentDataType.DOUBLE,0d)*100.0) + "% | "
							+ChatColor.GREEN + "<[대지 계열 공격력]> +" +  Math.round(isp.getOrDefault(earthdn, PersistentDataType.DOUBLE,0d)*100.0) + "%");
				}
				else {
					lore.removeIf(l -> l.contains("<[바람 계열 공격력]>"));
					lore.removeIf(l -> l.contains("<[Power Of Wind]>"));
					lore.add(ChatColor.GRAY + "<[Power Of Wind]> +" +  Math.round(isp.getOrDefault(windydn, PersistentDataType.DOUBLE,0d)*100.0) + "% | " 
							+ ChatColor.GREEN + "<[Power Of Earth]> +" +  Math.round(isp.getOrDefault(earthdn, PersistentDataType.DOUBLE,0d)*100.0) + "%");
				}
				rm.setLore(lore);
			} else {
				List<String> lore = new ArrayList<>();
				if(p.getLocale().equalsIgnoreCase("ko_kr")) {
					lore.removeIf(l -> l.contains("<[바람 계열 공격력]>"));
					lore.removeIf(l -> l.contains("<[Power Of Wind]>"));
					lore.add(ChatColor.GRAY + "<[바람 계열 공격력]> +" +  Math.round(isp.getOrDefault(windydn, PersistentDataType.DOUBLE,0d)*100.0) + "% | "
							+ChatColor.GREEN + "<[대지 계열 공격력]> +" +  Math.round(isp.getOrDefault(earthdn, PersistentDataType.DOUBLE,0d)*100.0) + "%");
				}
				else {
					lore.removeIf(l -> l.contains("<[바람 계열 공격력]>"));
					lore.removeIf(l -> l.contains("<[Power Of Wind]>"));
					lore.add(ChatColor.GRAY + "<[Power Of Wind]> +" +  Math.round(isp.getOrDefault(windydn, PersistentDataType.DOUBLE,0d)*100.0) + "% | " 
							+ ChatColor.GREEN + "<[Power Of Earth]> +" +  Math.round(isp.getOrDefault(earthdn, PersistentDataType.DOUBLE,0d)*100.0) + "%");
				}
				rm.setLore(lore);
			}
		}
		if(el== 6 || el == 0|| el == 2) {
			isp.set(frostdn, PersistentDataType.DOUBLE, isp.getOrDefault(frostdn, PersistentDataType.DOUBLE,0d) + m);
			if (rm.hasLore()) {
				List<String> lore = rm.getLore();
				if(p.getLocale().equalsIgnoreCase("ko_kr")) {
					lore.removeIf(l -> l.contains("<[서리 계열 공격력]>"));
					lore.removeIf(l -> l.contains("<[Power Of Frost]>"));
					lore.add(ChatColor.AQUA + "<[서리 계열 공격력]> +" + Math.round(isp.getOrDefault(frostdn, PersistentDataType.DOUBLE,0d)*100.0) + "% | "
							+ChatColor.DARK_GREEN + "<[맹독 계열 공격력]> +" + Math.round(isp.getOrDefault(poisondn, PersistentDataType.DOUBLE,0d)*100.0) + "%");
				}
				else {
					lore.removeIf(l -> l.contains("<[서리 계열 공격력]>"));
					lore.removeIf(l -> l.contains("<[Power Of Frost]>"));
					lore.add(ChatColor.AQUA + "<[Power Of Frost]> +" + Math.round(isp.getOrDefault(frostdn, PersistentDataType.DOUBLE,0d)*100.0) + "% | "
							+ChatColor.DARK_GREEN + "<[Power Of Poison]> +" + Math.round(isp.getOrDefault(poisondn, PersistentDataType.DOUBLE,0d)*100.0) + "%");
				}
				rm.setLore(lore);
			} else {
				List<String> lore = new ArrayList<>();
				if(p.getLocale().equalsIgnoreCase("ko_kr")) {
					lore.removeIf(l -> l.contains("<[서리 계열 공격력]>"));
					lore.removeIf(l -> l.contains("<[Power Of Frost]>"));
					lore.add(ChatColor.AQUA + "<[서리 계열 공격력]> +" + Math.round(isp.getOrDefault(frostdn, PersistentDataType.DOUBLE,0d)*100.0) + "% | "
							+ChatColor.DARK_GREEN + "<[맹독 계열 공격력]> +" + Math.round(isp.getOrDefault(poisondn, PersistentDataType.DOUBLE,0d)*100.0) + "%");
				}
				else {
					lore.removeIf(l -> l.contains("<[서리 계열 공격력]>"));
					lore.removeIf(l -> l.contains("<[Power Of Frost]>"));
					lore.add(ChatColor.AQUA + "<[Power Of Frost]> +" + Math.round(isp.getOrDefault(frostdn, PersistentDataType.DOUBLE,0d)*100.0) + "% | "
							+ChatColor.DARK_GREEN + "<[Power Of Poison]> +" + Math.round(isp.getOrDefault(poisondn, PersistentDataType.DOUBLE,0d)*100.0) + "%");
				}
				rm.setLore(lore);
			}
		}
		if(el== 7 || el == 0|| el == 2) {
			isp.set(waterdn, PersistentDataType.DOUBLE, isp.getOrDefault(waterdn, PersistentDataType.DOUBLE,0d) + m);
			if (rm.hasLore()) {
				List<String> lore = rm.getLore();
				if(p.getLocale().equalsIgnoreCase("ko_kr")) {
					lore.removeIf(l -> l.contains("<[물 계열 공격력]>"));
					lore.removeIf(l -> l.contains("<[Power Of Water]>"));
					lore.add(ChatColor.BLUE + "<[물 계열 공격력]> +" +  Math.round(isp.getOrDefault(waterdn, PersistentDataType.DOUBLE,0d)*100.0) + "% | "
							+ChatColor.RED + "<[화염 계열 공격력]> +" + Math.round(isp.getOrDefault(flamedn, PersistentDataType.DOUBLE,0d)*100.0) + "%");
				}
				else {
					lore.removeIf(l -> l.contains("<[물 계열 공격력]>"));
					lore.removeIf(l -> l.contains("<[Power Of Water]>"));
					lore.add(ChatColor.BLUE + "<[Power Of Water]> +" +  Math.round(isp.getOrDefault(waterdn, PersistentDataType.DOUBLE,0d)*100.0) + "% | "
							+ChatColor.RED + "<[Power Of Flame]> +" + Math.round(isp.getOrDefault(flamedn, PersistentDataType.DOUBLE,0d)*100.0) + "%");
				}
				rm.setLore(lore);
			} else {
				List<String> lore = new ArrayList<>();
				if(p.getLocale().equalsIgnoreCase("ko_kr")) {
					lore.removeIf(l -> l.contains("<[물 계열 공격력]>"));
					lore.removeIf(l -> l.contains("<[Power Of Water]>"));
					lore.add(ChatColor.BLUE + "<[물 계열 공격력]> +" +  Math.round(isp.getOrDefault(waterdn, PersistentDataType.DOUBLE,0d)*100.0) + "% | "
							+ChatColor.RED + "<[화염 계열 공격력]> +" + Math.round(isp.getOrDefault(flamedn, PersistentDataType.DOUBLE,0d)*100.0) + "%");
				}
				else {
					lore.removeIf(l -> l.contains("<[물 계열 공격력]>"));
					lore.removeIf(l -> l.contains("<[Power Of Water]>"));
					lore.add(ChatColor.BLUE + "<[Power Of Water]> +" +  Math.round(isp.getOrDefault(waterdn, PersistentDataType.DOUBLE,0d)*100.0) + "% | "
							+ChatColor.RED + "<[Power Of Flame]> +" + Math.round(isp.getOrDefault(flamedn, PersistentDataType.DOUBLE,0d)*100.0) + "%");
				}
				rm.setLore(lore);
			}
		}
		if(el== 8 || el == 0|| el == 2) {
			isp.set(darkdn, PersistentDataType.DOUBLE, isp.getOrDefault(darkdn, PersistentDataType.DOUBLE,0d) + m);
			if (rm.hasLore()) {
				List<String> lore = rm.getLore();
				if(p.getLocale().equalsIgnoreCase("ko_kr")) {
					lore.removeIf(l -> l.contains("<[어둠 계열 공격력]>"));
					lore.removeIf(l -> l.contains("<[Power Of Darkness]>"));
					lore.add(ChatColor.DARK_GRAY + "<[어둠 계열 공격력]> +" +  Math.round(isp.getOrDefault(darkdn, PersistentDataType.DOUBLE,0d)*100.0) + "% | "
							+ChatColor.YELLOW + "<[번개 계열 공격력]> +" +  Math.round(isp.getOrDefault(lightningdn, PersistentDataType.DOUBLE,0d)*100.0) + "%");
				}
				else {
					lore.removeIf(l -> l.contains("<[어둠 계열 공격력]>"));
					lore.removeIf(l -> l.contains("<[Power Of Darkness]>"));
					lore.add(ChatColor.DARK_GRAY + "<[Power Of Darkness]> +" +  Math.round(isp.getOrDefault(darkdn, PersistentDataType.DOUBLE,0d)*100.0) + "% | "
							+ChatColor.YELLOW + "<[Power Of Lightning]> +" +  Math.round(isp.getOrDefault(lightningdn, PersistentDataType.DOUBLE,0d)*100.0) + "%");
				}
				rm.setLore(lore);
			} else {
				List<String> lore = new ArrayList<>();
				if(p.getLocale().equalsIgnoreCase("ko_kr")) {
					lore.removeIf(l -> l.contains("<[어둠 계열 공격력]>"));
					lore.removeIf(l -> l.contains("<[Power Of Darkness]>"));
					lore.add(ChatColor.DARK_GRAY + "<[어둠 계열 공격력]> +" +  Math.round(isp.getOrDefault(darkdn, PersistentDataType.DOUBLE,0d)*100.0) + "% | "
							+ChatColor.YELLOW + "<[번개 계열 공격력]> +" +  Math.round(isp.getOrDefault(lightningdn, PersistentDataType.DOUBLE,0d)*100.0) + "%");
				}
				else {
					lore.removeIf(l -> l.contains("<[어둠 계열 공격력]>"));
					lore.removeIf(l -> l.contains("<[Power Of Darkness]>"));
					lore.add(ChatColor.DARK_GRAY + "<[Power Of Darkness]> +" +  Math.round(isp.getOrDefault(darkdn, PersistentDataType.DOUBLE,0d)*100.0) + "% | "
							+ChatColor.YELLOW + "<[Power Of Lightning]> +" +  Math.round(isp.getOrDefault(lightningdn, PersistentDataType.DOUBLE,0d)*100.0) + "%");
				}
				rm.setLore(lore);
			}
		}
		if(el== 9 || el == 1|| el == 2) {
			isp.set(lightningdn, PersistentDataType.DOUBLE, isp.getOrDefault(lightningdn, PersistentDataType.DOUBLE,0d) + m);
			if (rm.hasLore()) {
				List<String> lore = rm.getLore();
				if(p.getLocale().equalsIgnoreCase("ko_kr")) {
					lore.removeIf(l -> l.contains("<[어둠 계열 공격력]>"));
					lore.removeIf(l -> l.contains("<[Power Of Darkness]>"));
					lore.add(ChatColor.DARK_GRAY + "<[어둠 계열 공격력]> +" +  Math.round(isp.getOrDefault(darkdn, PersistentDataType.DOUBLE,0d)*100.0) + "% | "
							+ChatColor.YELLOW + "<[번개 계열 공격력]> +" +  Math.round(isp.getOrDefault(lightningdn, PersistentDataType.DOUBLE,0d)*100.0) + "%");
				}
				else {
					lore.removeIf(l -> l.contains("<[어둠 계열 공격력]>"));
					lore.removeIf(l -> l.contains("<[Power Of Darkness]>"));
					lore.add(ChatColor.DARK_GRAY + "<[Power Of Darkness]> +" +  Math.round(isp.getOrDefault(darkdn, PersistentDataType.DOUBLE,0d)*100.0) + "% | "
							+ChatColor.YELLOW + "<[Power Of Lightning]> +" +  Math.round(isp.getOrDefault(lightningdn, PersistentDataType.DOUBLE,0d)*100.0) + "%");
				}
				rm.setLore(lore);
			} else {
				List<String> lore = new ArrayList<>();
				if(p.getLocale().equalsIgnoreCase("ko_kr")) {
					lore.removeIf(l -> l.contains("<[어둠 계열 공격력]>"));
					lore.removeIf(l -> l.contains("<[Power Of Darkness]>"));
					lore.add(ChatColor.DARK_GRAY + "<[어둠 계열 공격력]> +" +  Math.round(isp.getOrDefault(darkdn, PersistentDataType.DOUBLE,0d)*100.0) + "% | "
							+ChatColor.YELLOW + "<[번개 계열 공격력]> +" +  Math.round(isp.getOrDefault(lightningdn, PersistentDataType.DOUBLE,0d)*100.0) + "%");
				}
				else {
					lore.removeIf(l -> l.contains("<[어둠 계열 공격력]>"));
					lore.removeIf(l -> l.contains("<[Power Of Darkness]>"));
					lore.add(ChatColor.DARK_GRAY + "<[Power Of Darkness]> +" +  Math.round(isp.getOrDefault(darkdn, PersistentDataType.DOUBLE,0d)*100.0) + "% | "
							+ChatColor.YELLOW + "<[Power Of Lightning]> +" +  Math.round(isp.getOrDefault(lightningdn, PersistentDataType.DOUBLE,0d)*100.0) + "%");
				}
				rm.setLore(lore);
			}
		}
		if(el== 10 || el == 1|| el == 2) {
			isp.set(flamedn, PersistentDataType.DOUBLE, isp.getOrDefault(flamedn, PersistentDataType.DOUBLE,0d) + m);
			if (rm.hasLore()) {
				List<String> lore = rm.getLore();
				if(p.getLocale().equalsIgnoreCase("ko_kr")) {
					lore.removeIf(l -> l.contains("<[물 계열 공격력]>"));
					lore.removeIf(l -> l.contains("<[Power Of Water]>"));
					lore.add(ChatColor.BLUE + "<[물 계열 공격력]> +" +  Math.round(isp.getOrDefault(waterdn, PersistentDataType.DOUBLE,0d)*100.0) + "% | "
							+ChatColor.RED + "<[화염 계열 공격력]> +" + Math.round(isp.getOrDefault(flamedn, PersistentDataType.DOUBLE,0d)*100.0) + "%");
				}
				else {
					lore.removeIf(l -> l.contains("<[물 계열 공격력]>"));
					lore.removeIf(l -> l.contains("<[Power Of Water]>"));
					lore.add(ChatColor.BLUE + "<[Power Of Water]> +" +  Math.round(isp.getOrDefault(waterdn, PersistentDataType.DOUBLE,0d)*100.0) + "% | "
							+ChatColor.RED + "<[Power Of Flame]> +" + Math.round(isp.getOrDefault(flamedn, PersistentDataType.DOUBLE,0d)*100.0) + "%");
				}
				rm.setLore(lore);
			} else {
				List<String> lore = new ArrayList<>();
				if(p.getLocale().equalsIgnoreCase("ko_kr")) {
					lore.removeIf(l -> l.contains("<[물 계열 공격력]>"));
					lore.removeIf(l -> l.contains("<[Power Of Water]>"));
					lore.add(ChatColor.BLUE + "<[물 계열 공격력]> +" +  Math.round(isp.getOrDefault(waterdn, PersistentDataType.DOUBLE,0d)*100.0) + "% | "
							+ChatColor.RED + "<[화염 계열 공격력]> +" + Math.round(isp.getOrDefault(flamedn, PersistentDataType.DOUBLE,0d)*100.0) + "%");
				}
				else {
					lore.removeIf(l -> l.contains("<[물 계열 공격력]>"));
					lore.removeIf(l -> l.contains("<[Power Of Water]>"));
					lore.add(ChatColor.BLUE + "<[Power Of Water]> +" +  Math.round(isp.getOrDefault(waterdn, PersistentDataType.DOUBLE,0d)*100.0) + "% | "
							+ChatColor.RED + "<[Power Of Flame]> +" + Math.round(isp.getOrDefault(flamedn, PersistentDataType.DOUBLE,0d)*100.0) + "%");
				}
				rm.setLore(lore);
			}
		}
		if(el== 11 || el == 1|| el == 2) {
			isp.set(poisondn, PersistentDataType.DOUBLE, isp.getOrDefault(poisondn, PersistentDataType.DOUBLE,0d) + m);
			if (rm.hasLore()) {
				List<String> lore = rm.getLore();
				if(p.getLocale().equalsIgnoreCase("ko_kr")) {
					lore.removeIf(l -> l.contains("<[서리 계열 공격력]>"));
					lore.removeIf(l -> l.contains("<[Power Of Frost]>"));
					lore.add(ChatColor.AQUA + "<[서리 계열 공격력]> +" + Math.round(isp.getOrDefault(frostdn, PersistentDataType.DOUBLE,0d)*100.0) + "% | "
							+ChatColor.DARK_GREEN + "<[맹독 계열 공격력]> +" + Math.round(isp.getOrDefault(poisondn, PersistentDataType.DOUBLE,0d)*100.0) + "%");
				}
				else {
					lore.removeIf(l -> l.contains("<[서리 계열 공격력]>"));
					lore.removeIf(l -> l.contains("<[Power Of Frost]>"));
					lore.add(ChatColor.AQUA + "<[Power Of Frost]> +" + Math.round(isp.getOrDefault(frostdn, PersistentDataType.DOUBLE,0d)*100.0) + "% | "
							+ChatColor.DARK_GREEN + "<[Power Of Poison]> +" + Math.round(isp.getOrDefault(poisondn, PersistentDataType.DOUBLE,0d)*100.0) + "%");
				}
				rm.setLore(lore);
			} else {
				List<String> lore = new ArrayList<>();
				if(p.getLocale().equalsIgnoreCase("ko_kr")) {
					lore.removeIf(l -> l.contains("<[서리 계열 공격력]>"));
					lore.removeIf(l -> l.contains("<[Power Of Frost]>"));
					lore.add(ChatColor.AQUA + "<[서리 계열 공격력]> +" + Math.round(isp.getOrDefault(frostdn, PersistentDataType.DOUBLE,0d)*100.0) + "% | "
							+ChatColor.DARK_GREEN + "<[맹독 계열 공격력]> +" + Math.round(isp.getOrDefault(poisondn, PersistentDataType.DOUBLE,0d)*100.0) + "%");
				}
				else {
					lore.removeIf(l -> l.contains("<[서리 계열 공격력]>"));
					lore.removeIf(l -> l.contains("<[Power Of Frost]>"));
					lore.add(ChatColor.AQUA + "<[Power Of Frost]> +" + Math.round(isp.getOrDefault(frostdn, PersistentDataType.DOUBLE,0d)*100.0) + "% | "
							+ChatColor.DARK_GREEN + "<[Power Of Poison]> +" + Math.round(isp.getOrDefault(poisondn, PersistentDataType.DOUBLE,0d)*100.0) + "%");
				}
				rm.setLore(lore);
			}
		}
		return rm;
	}

	final public ItemMeta addelmr(ItemMeta im, Integer el, Double m, Player p) {
		ItemMeta rm = im.clone();
		PersistentDataContainer isp = rm.getPersistentDataContainer();
		if(el== 5 || el == 0|| el == 2) {
			isp.set(windyrn, PersistentDataType.DOUBLE, isp.getOrDefault(windyrn, PersistentDataType.DOUBLE,0d) + m);
			if (rm.hasLore()) {
				List<String> lore = rm.getLore();
				if(p.getLocale().equalsIgnoreCase("ko_kr")) {
					lore.removeIf(l -> l.contains("<[바람 계열 저항력]>"));
					lore.removeIf(l -> l.contains("<[Resistance To Wind]>"));
					lore.add(ChatColor.GRAY + "<[바람 계열 저항력]> +" +  Math.round(isp.getOrDefault(windyrn, PersistentDataType.DOUBLE,0d)*100.0) + "% | " +
							ChatColor.GREEN + "<[대지 계열 저항력]> +" +  Math.round(isp.getOrDefault(earthrn, PersistentDataType.DOUBLE,0d)*100.0) + "%" );
				}
				else {
					lore.removeIf(l -> l.contains("<[바람 계열 저항력]>"));
					lore.removeIf(l -> l.contains("<[Resistance To Wind]>"));
					lore.add(ChatColor.GRAY + "<[Resistance To Wind]> +" +  Math.round(isp.getOrDefault(windyrn, PersistentDataType.DOUBLE,0d)*100.0) + "% | " +
							ChatColor.GREEN + "<[Resistance To Earth]> +" +  Math.round(isp.getOrDefault(earthrn, PersistentDataType.DOUBLE,0d)*100.0) + "%");
				}
				rm.setLore(lore);
			} else {
				List<String> lore = new ArrayList<>();
				if(p.getLocale().equalsIgnoreCase("ko_kr")) {
					lore.removeIf(l -> l.contains("<[바람 계열 저항력]>"));
					lore.removeIf(l -> l.contains("<[Resistance To Wind]>"));
					lore.add(ChatColor.GRAY + "<[바람 계열 저항력]> +" +  Math.round(isp.getOrDefault(windyrn, PersistentDataType.DOUBLE,0d)*100.0) + "% | " +
							ChatColor.GREEN + "<[대지 계열 저항력]> +" +  Math.round(isp.getOrDefault(earthrn, PersistentDataType.DOUBLE,0d)*100.0) + "%" );
				}
				else {
					lore.removeIf(l -> l.contains("<[바람 계열 저항력]>"));
					lore.removeIf(l -> l.contains("<[Resistance To Wind]>"));
					lore.add(ChatColor.GRAY + "<[Resistance To Wind]> +" +  Math.round(isp.getOrDefault(windyrn, PersistentDataType.DOUBLE,0d)*100.0) + "% | " +
							ChatColor.GREEN + "<[Resistance To Earth]> +" +  Math.round(isp.getOrDefault(earthrn, PersistentDataType.DOUBLE,0d)*100.0) + "%");
				}
				rm.setLore(lore);
			}
		}
		if(el == 14 || el == 1|| el == 2) {
			isp.set(earthrn, PersistentDataType.DOUBLE, isp.getOrDefault(earthrn, PersistentDataType.DOUBLE,0d) + m);
			if (rm.hasLore()) {
				List<String> lore = rm.getLore();
				if(p.getLocale().equalsIgnoreCase("ko_kr")) {
					lore.removeIf(l -> l.contains("<[대지 계열 저항력]>"));
					lore.removeIf(l -> l.contains("<[Resistance To Earth]>"));
					lore.add(ChatColor.GRAY + "<[바람 계열 저항력]> +" +  Math.round(isp.getOrDefault(windyrn, PersistentDataType.DOUBLE,0d)*100.0) + "% | " +
							ChatColor.GREEN + "<[대지 계열 저항력]> +" +  Math.round(isp.getOrDefault(earthrn, PersistentDataType.DOUBLE,0d)*100.0) + "%" );
				}
				else {
					lore.removeIf(l -> l.contains("<[대지 계열 저항력]>"));
					lore.removeIf(l -> l.contains("<[Resistance To Earth]>"));
					lore.add(ChatColor.GRAY + "<[Resistance To Wind]> +" +  Math.round(isp.getOrDefault(windyrn, PersistentDataType.DOUBLE,0d)*100.0) + "% | " +
							ChatColor.GREEN + "<[Resistance To Earth]> +" +  Math.round(isp.getOrDefault(earthrn, PersistentDataType.DOUBLE,0d)*100.0) + "%");
				}
				rm.setLore(lore);
			} else {
				List<String> lore = new ArrayList<>();
				if(p.getLocale().equalsIgnoreCase("ko_kr")) {
					lore.removeIf(l -> l.contains("<[대지 계열 저항력]>"));
					lore.removeIf(l -> l.contains("<[Resistance To Earth]>"));
					lore.add(ChatColor.GRAY + "<[바람 계열 저항력]> +" +  Math.round(isp.getOrDefault(windyrn, PersistentDataType.DOUBLE,0d)*100.0) + "% | " +
							ChatColor.GREEN + "<[대지 계열 저항력]> +" +  Math.round(isp.getOrDefault(earthrn, PersistentDataType.DOUBLE,0d)*100.0) + "%" );
				}
				else {
					lore.removeIf(l -> l.contains("<[대지 계열 저항력]>"));
					lore.removeIf(l -> l.contains("<[Resistance To Earth]>"));
					lore.add(ChatColor.GRAY + "<[Resistance To Wind]> +" +  Math.round(isp.getOrDefault(windyrn, PersistentDataType.DOUBLE,0d)*100.0) + "% | " +
							ChatColor.GREEN + "<[Resistance To Earth]> +" +  Math.round(isp.getOrDefault(earthrn, PersistentDataType.DOUBLE,0d)*100.0) + "%");
				}
				rm.setLore(lore);
			}
		}
		if(el== 6 || el == 0|| el == 2) {
			isp.set(frostrn, PersistentDataType.DOUBLE, isp.getOrDefault(frostrn, PersistentDataType.DOUBLE,0d) + m);
			if (rm.hasLore()) {
				List<String> lore = rm.getLore();
				if(p.getLocale().equalsIgnoreCase("ko_kr")) {
					lore.removeIf(l -> l.contains("<[서리 계열 저항력]>"));
					lore.removeIf(l -> l.contains("<[Resistance To Frost]>"));
					lore.add(ChatColor.AQUA + "<[서리 계열 저항력]> +" + Math.round(isp.getOrDefault(frostrn, PersistentDataType.DOUBLE,0d)*100.0) + "% | " 
							+ChatColor.DARK_GREEN + "<[맹독 계열 저항력]> +" + Math.round(isp.getOrDefault(poisonrn, PersistentDataType.DOUBLE,0d)*100.0) + "%");
				}
				else {
					lore.removeIf(l -> l.contains("<[서리 계열 저항력]>"));
					lore.removeIf(l -> l.contains("<[Resistance To Frost]>"));
					lore.add(ChatColor.AQUA + "<[Resistance To Frost]> +" + Math.round(isp.getOrDefault(frostrn, PersistentDataType.DOUBLE,0d)*100.0) + "% | " 
							+ChatColor.DARK_GREEN + "<[Resistance To Poison]> +" + Math.round(isp.getOrDefault(poisonrn, PersistentDataType.DOUBLE,0d)*100.0) + "%");
				}
				rm.setLore(lore);
			} else {
				List<String> lore = new ArrayList<>();
				if(p.getLocale().equalsIgnoreCase("ko_kr")) {
					lore.removeIf(l -> l.contains("<[서리 계열 저항력]>"));
					lore.removeIf(l -> l.contains("<[Resistance To Frost]>"));
					lore.add(ChatColor.AQUA + "<[서리 계열 저항력]> +" + Math.round(isp.getOrDefault(frostrn, PersistentDataType.DOUBLE,0d)*100.0) + "% | " 
							+ChatColor.DARK_GREEN + "<[맹독 계열 저항력]> +" + Math.round(isp.getOrDefault(poisonrn, PersistentDataType.DOUBLE,0d)*100.0) + "%");
				}
				else {
					lore.removeIf(l -> l.contains("<[서리 계열 저항력]>"));
					lore.removeIf(l -> l.contains("<[Resistance To Frost]>"));
					lore.add(ChatColor.AQUA + "<[Resistance To Frost]> +" + Math.round(isp.getOrDefault(frostrn, PersistentDataType.DOUBLE,0d)*100.0) + "% | " 
							+ChatColor.DARK_GREEN + "<[Resistance To Poison]> +" + Math.round(isp.getOrDefault(poisonrn, PersistentDataType.DOUBLE,0d)*100.0) + "%");
				}
				rm.setLore(lore);
			}
		}
		if(el== 7 || el == 0|| el == 2) {
			isp.set(waterrn, PersistentDataType.DOUBLE, isp.getOrDefault(waterrn, PersistentDataType.DOUBLE,0d) + m);
			if (rm.hasLore()) {
				List<String> lore = rm.getLore();
				if(p.getLocale().equalsIgnoreCase("ko_kr")) {
					lore.removeIf(l -> l.contains("<[물 계열 저항력]>"));
					lore.removeIf(l -> l.contains("<[Resistance To Water]>"));
					lore.add(ChatColor.BLUE + "<[물 계열 저항력]> +" +  Math.round(isp.getOrDefault(waterrn, PersistentDataType.DOUBLE,0d)*100.0) + "% | " 
							+ChatColor.RED + "<[화염 계열 저항력]> +" + Math.round(isp.getOrDefault(flamern, PersistentDataType.DOUBLE,0d)*100.0) + "%");
				}
				else {
					lore.removeIf(l -> l.contains("<[물 계열 저항력]>"));
					lore.removeIf(l -> l.contains("<[Resistance To Water]>"));
					lore.add(ChatColor.BLUE + "<[Resistance To Water]> +" +  Math.round(isp.getOrDefault(waterrn, PersistentDataType.DOUBLE,0d)*100.0) + "% | " 
							+ChatColor.RED + "<[화염 계열 저항력]> +" + Math.round(isp.getOrDefault(flamern, PersistentDataType.DOUBLE,0d)*100.0) + "%");
				}
				rm.setLore(lore);
			} else {
				List<String> lore = new ArrayList<>();
				if(p.getLocale().equalsIgnoreCase("ko_kr")) {
					lore.removeIf(l -> l.contains("<[물 계열 저항력]>"));
					lore.removeIf(l -> l.contains("<[Resistance To Water]>"));
					lore.add(ChatColor.BLUE + "<[물 계열 저항력]> +" +  Math.round(isp.getOrDefault(waterrn, PersistentDataType.DOUBLE,0d)*100.0) + "% | " 
							+ChatColor.RED + "<[화염 계열 저항력]> +" + Math.round(isp.getOrDefault(flamern, PersistentDataType.DOUBLE,0d)*100.0) + "%");
				}
				else {
					lore.removeIf(l -> l.contains("<[물 계열 저항력]>"));
					lore.removeIf(l -> l.contains("<[Resistance To Water]>"));
					lore.add(ChatColor.BLUE + "<[Resistance To Water]> +" +  Math.round(isp.getOrDefault(waterrn, PersistentDataType.DOUBLE,0d)*100.0) + "% | " 
							+ChatColor.RED + "<[화염 계열 저항력]> +" + Math.round(isp.getOrDefault(flamern, PersistentDataType.DOUBLE,0d)*100.0) + "%");
				}
				rm.setLore(lore);
			}
		}
		if(el== 8 || el == 0|| el == 2) {
			isp.set(darkrn, PersistentDataType.DOUBLE, isp.getOrDefault(darkrn, PersistentDataType.DOUBLE,0d) + m);
			if (rm.hasLore()) {
				List<String> lore = rm.getLore();
				if(p.getLocale().equalsIgnoreCase("ko_kr")) {
					lore.removeIf(l -> l.contains("<[어둠 계열 저항력]>"));
					lore.removeIf(l -> l.contains("<[Resistance To Darkness]>"));
					lore.add(ChatColor.DARK_GRAY + "<[어둠 계열 저항력]> +" +  Math.round(isp.getOrDefault(darkrn, PersistentDataType.DOUBLE,0d)*100.0) + "% | "
							+ChatColor.YELLOW + "<[번개 계열 저항력]> +" +  Math.round(isp.getOrDefault(lightningrn, PersistentDataType.DOUBLE,0d)*100.0) + "%");
				}
				else {
					lore.removeIf(l -> l.contains("<[어둠 계열 저항력]>"));
					lore.removeIf(l -> l.contains("<[Resistance To Darkness]>"));
					lore.add(ChatColor.DARK_GRAY + "<[Resistance To Darkness]> +" +  Math.round(isp.getOrDefault(darkrn, PersistentDataType.DOUBLE,0d)*100.0) + "% | "
							+ChatColor.YELLOW + "<[Resistance To Lightning]> +" +  Math.round(isp.getOrDefault(lightningrn, PersistentDataType.DOUBLE,0d)*100.0) + "%" );
				}
				rm.setLore(lore);
			} else {
				List<String> lore = new ArrayList<>();
				if(p.getLocale().equalsIgnoreCase("ko_kr")) {
					lore.removeIf(l -> l.contains("<[어둠 계열 저항력]>"));
					lore.removeIf(l -> l.contains("<[Resistance To Darkness]>"));
					lore.add(ChatColor.DARK_GRAY + "<[어둠 계열 저항력]> +" +  Math.round(isp.getOrDefault(darkrn, PersistentDataType.DOUBLE,0d)*100.0) + "% | "
							+ChatColor.YELLOW + "<[번개 계열 저항력]> +" +  Math.round(isp.getOrDefault(lightningrn, PersistentDataType.DOUBLE,0d)*100.0) + "%");
				}
				else {
					lore.removeIf(l -> l.contains("<[어둠 계열 저항력]>"));
					lore.removeIf(l -> l.contains("<[Resistance To Darkness]>"));
					lore.add(ChatColor.DARK_GRAY + "<[Resistance To Darkness]> +" +  Math.round(isp.getOrDefault(darkrn, PersistentDataType.DOUBLE,0d)*100.0) + "% | "
							+ChatColor.YELLOW + "<[Resistance To Lightning]> +" +  Math.round(isp.getOrDefault(lightningrn, PersistentDataType.DOUBLE,0d)*100.0) + "%" );
				}
				rm.setLore(lore);
			}
		}
		if(el== 9 || el == 1|| el == 2) {
			isp.set(lightningrn, PersistentDataType.DOUBLE, isp.getOrDefault(lightningrn, PersistentDataType.DOUBLE,0d) + m);
			if (rm.hasLore()) {
				List<String> lore = rm.getLore();
				if(p.getLocale().equalsIgnoreCase("ko_kr")) {
					lore.removeIf(l -> l.contains("<[어둠 계열 저항력]>"));
					lore.removeIf(l -> l.contains("<[Resistance To Darkness]>"));
					lore.add(ChatColor.DARK_GRAY + "<[어둠 계열 저항력]> +" +  Math.round(isp.getOrDefault(darkrn, PersistentDataType.DOUBLE,0d)*100.0) + "% | "
							+ChatColor.YELLOW + "<[번개 계열 저항력]> +" +  Math.round(isp.getOrDefault(lightningrn, PersistentDataType.DOUBLE,0d)*100.0) + "%");
				}
				else {
					lore.removeIf(l -> l.contains("<[어둠 계열 저항력]>"));
					lore.removeIf(l -> l.contains("<[Resistance To Darkness]>"));
					lore.add(ChatColor.DARK_GRAY + "<[Resistance To Darkness]> +" +  Math.round(isp.getOrDefault(darkrn, PersistentDataType.DOUBLE,0d)*100.0) + "% | "
							+ChatColor.YELLOW + "<[Resistance To Lightning]> +" +  Math.round(isp.getOrDefault(lightningrn, PersistentDataType.DOUBLE,0d)*100.0) + "%" );
				}
				rm.setLore(lore);
			} else {
				List<String> lore = new ArrayList<>();
				if(p.getLocale().equalsIgnoreCase("ko_kr")) {
					lore.removeIf(l -> l.contains("<[어둠 계열 저항력]>"));
					lore.removeIf(l -> l.contains("<[Resistance To Darkness]>"));
					lore.add(ChatColor.DARK_GRAY + "<[어둠 계열 저항력]> +" +  Math.round(isp.getOrDefault(darkrn, PersistentDataType.DOUBLE,0d)*100.0) + "% | "
							+ChatColor.YELLOW + "<[번개 계열 저항력]> +" +  Math.round(isp.getOrDefault(lightningrn, PersistentDataType.DOUBLE,0d)*100.0) + "%");
				}
				else {
					lore.removeIf(l -> l.contains("<[어둠 계열 저항력]>"));
					lore.removeIf(l -> l.contains("<[Resistance To Darkness]>"));
					lore.add(ChatColor.DARK_GRAY + "<[Resistance To Darkness]> +" +  Math.round(isp.getOrDefault(darkrn, PersistentDataType.DOUBLE,0d)*100.0) + "% | "
							+ChatColor.YELLOW + "<[Resistance To Lightning]> +" +  Math.round(isp.getOrDefault(lightningrn, PersistentDataType.DOUBLE,0d)*100.0) + "%" );
				}
				rm.setLore(lore);
			}
		}
		if(el== 10 || el == 1|| el == 2) {
			isp.set(flamern, PersistentDataType.DOUBLE, isp.getOrDefault(flamern, PersistentDataType.DOUBLE,0d) + m);
			if (rm.hasLore()) {
				List<String> lore = rm.getLore();
				if(p.getLocale().equalsIgnoreCase("ko_kr")) {
					lore.removeIf(l -> l.contains("<[물 계열 저항력]>"));
					lore.removeIf(l -> l.contains("<[Resistance To Water]>"));
					lore.add(ChatColor.BLUE + "<[물 계열 저항력]> +" +  Math.round(isp.getOrDefault(waterrn, PersistentDataType.DOUBLE,0d)*100.0) + "% | " 
							+ChatColor.RED + "<[화염 계열 저항력]> +" + Math.round(isp.getOrDefault(flamern, PersistentDataType.DOUBLE,0d)*100.0) + "%");
				}
				else {
					lore.removeIf(l -> l.contains("<[물 계열 저항력]>"));
					lore.removeIf(l -> l.contains("<[Resistance To Water]>"));
					lore.add(ChatColor.BLUE + "<[Resistance To Water]> +" +  Math.round(isp.getOrDefault(waterrn, PersistentDataType.DOUBLE,0d)*100.0) + "% | " 
							+ChatColor.RED + "<[화염 계열 저항력]> +" + Math.round(isp.getOrDefault(flamern, PersistentDataType.DOUBLE,0d)*100.0) + "%");
				}
				rm.setLore(lore);
			} else {
				List<String> lore = new ArrayList<>();
				if(p.getLocale().equalsIgnoreCase("ko_kr")) {
					lore.removeIf(l -> l.contains("<[물 계열 저항력]>"));
					lore.removeIf(l -> l.contains("<[Resistance To Water]>"));
					lore.add(ChatColor.BLUE + "<[물 계열 저항력]> +" +  Math.round(isp.getOrDefault(waterrn, PersistentDataType.DOUBLE,0d)*100.0) + "% | " 
							+ChatColor.RED + "<[화염 계열 저항력]> +" + Math.round(isp.getOrDefault(flamern, PersistentDataType.DOUBLE,0d)*100.0) + "%");
				}
				else {
					lore.removeIf(l -> l.contains("<[물 계열 저항력]>"));
					lore.removeIf(l -> l.contains("<[Resistance To Water]>"));
					lore.add(ChatColor.BLUE + "<[Resistance To Water]> +" +  Math.round(isp.getOrDefault(waterrn, PersistentDataType.DOUBLE,0d)*100.0) + "% | " 
							+ChatColor.RED + "<[화염 계열 저항력]> +" + Math.round(isp.getOrDefault(flamern, PersistentDataType.DOUBLE,0d)*100.0) + "%");
				}
				rm.setLore(lore);
			}
		}
		if(el== 11 || el == 1|| el == 2) {
			isp.set(poisonrn, PersistentDataType.DOUBLE, isp.getOrDefault(poisonrn, PersistentDataType.DOUBLE,0d) + m);
			if (rm.hasLore()) {
				List<String> lore = rm.getLore();
				if(p.getLocale().equalsIgnoreCase("ko_kr")) {
					lore.removeIf(l -> l.contains("<[서리 계열 저항력]>"));
					lore.removeIf(l -> l.contains("<[Resistance To Frost]>"));
					lore.add(ChatColor.AQUA + "<[서리 계열 저항력]> +" + Math.round(isp.getOrDefault(frostrn, PersistentDataType.DOUBLE,0d)*100.0) + "% | " 
							+ChatColor.DARK_GREEN + "<[맹독 계열 저항력]> +" + Math.round(isp.getOrDefault(poisonrn, PersistentDataType.DOUBLE,0d)*100.0) + "%");
				}
				else {
					lore.removeIf(l -> l.contains("<[서리 계열 저항력]>"));
					lore.removeIf(l -> l.contains("<[Resistance To Frost]>"));
					lore.add(ChatColor.AQUA + "<[Resistance To Frost]> +" + Math.round(isp.getOrDefault(frostrn, PersistentDataType.DOUBLE,0d)*100.0) + "% | " 
							+ChatColor.DARK_GREEN + "<[Resistance To Poison]> +" + Math.round(isp.getOrDefault(poisonrn, PersistentDataType.DOUBLE,0d)*100.0) + "%");
				}
				rm.setLore(lore);
			} else {
				List<String> lore = new ArrayList<>();
				if(p.getLocale().equalsIgnoreCase("ko_kr")) {
					lore.removeIf(l -> l.contains("<[서리 계열 저항력]>"));
					lore.removeIf(l -> l.contains("<[Resistance To Frost]>"));
					lore.add(ChatColor.AQUA + "<[서리 계열 저항력]> +" + Math.round(isp.getOrDefault(frostrn, PersistentDataType.DOUBLE,0d)*100.0) + "% | " 
							+ChatColor.DARK_GREEN + "<[맹독 계열 저항력]> +" + Math.round(isp.getOrDefault(poisonrn, PersistentDataType.DOUBLE,0d)*100.0) + "%");
				}
				else {
					lore.removeIf(l -> l.contains("<[서리 계열 저항력]>"));
					lore.removeIf(l -> l.contains("<[Resistance To Frost]>"));
					lore.add(ChatColor.AQUA + "<[Resistance To Frost]> +" + Math.round(isp.getOrDefault(frostrn, PersistentDataType.DOUBLE,0d)*100.0) + "% | " 
							+ChatColor.DARK_GREEN + "<[Resistance To Poison]> +" + Math.round(isp.getOrDefault(poisonrn, PersistentDataType.DOUBLE,0d)*100.0) + "%");
				}
				rm.setLore(lore);
			}
		}
		return rm;
	}
	
	final public void putelmr(LivingEntity p, Integer el, Double d) {
		PersistentDataContainer isp = p.getPersistentDataContainer();
		if(el == 5) {
			isp.set(windyrn, PersistentDataType.DOUBLE, d);
		}
		if(el == 14) {
			isp.set(earthrn, PersistentDataType.DOUBLE, d);
		}
		if(el == 6) {
			isp.set(frostrn, PersistentDataType.DOUBLE, d);
		}
		if(el == 7) {
			isp.set(waterrn, PersistentDataType.DOUBLE, d);
		}
		if(el == 8) {
			isp.set(darkrn, PersistentDataType.DOUBLE, d);
		}
		if(el == 9) {
			isp.set(lightningrn, PersistentDataType.DOUBLE, d);
		}
		if(el == 10) {
			isp.set(flamern, PersistentDataType.DOUBLE, d);
		}
		if(el == 11) {
			isp.set(poisonrn, PersistentDataType.DOUBLE, d);
		}
		
	}
	
	final public ItemMeta remelm(ItemMeta im) {
		ItemMeta rm = im.clone();
		PersistentDataContainer isp = rm.getPersistentDataContainer();
		isp.remove(windydn);
		isp.remove(earthdn);
		isp.remove(frostdn);
		isp.remove(waterdn);
		isp.remove(darkdn);
		isp.remove(lightningdn);
		isp.remove(flamedn);
		isp.remove(poisondn);
		return rm;
	}


	final public void remelmr(LivingEntity le) {
		PersistentDataContainer isp = le.getPersistentDataContainer();
		isp.remove(windyrn);
		isp.remove(earthrn);
		isp.remove(frostrn);
		isp.remove(waterrn);
		isp.remove(darkrn);
		isp.remove(lightningrn);
		isp.remove(flamern);
		isp.remove(poisonrn);
	}

	final private void ElrGetter(ItemStack[] ais, LivingEntity p) {
		windyr.remove(p.getUniqueId());
		earthr.remove(p.getUniqueId());
		frostr.remove(p.getUniqueId());
		waterr.remove(p.getUniqueId());
		darkr.remove(p.getUniqueId());
		lightningr.remove(p.getUniqueId());
		flamer.remove(p.getUniqueId());
		poisonr.remove(p.getUniqueId());
		
		for(ItemStack ai : ais) {
			if(ai == null || !ai.hasItemMeta()) {
				continue;
			}
			PersistentDataContainer aip = ai.getItemMeta().getPersistentDataContainer();
			if(aip.has(windyrn, PersistentDataType.DOUBLE)) {
				windyr.computeIfPresent(p.getUniqueId(), (k,v) -> v * (1+ aip.get(windyrn, PersistentDataType.DOUBLE)));
				windyr.putIfAbsent(p.getUniqueId(),aip.get(windyrn, PersistentDataType.DOUBLE));
			}
			if(aip.has(earthrn, PersistentDataType.DOUBLE)) {
				earthr.computeIfPresent(p.getUniqueId(), (k,v) -> v *(1+aip.get(earthrn, PersistentDataType.DOUBLE)));
				earthr.putIfAbsent(p.getUniqueId(),aip.get(earthrn, PersistentDataType.DOUBLE));
			}
			if(aip.has(frostrn, PersistentDataType.DOUBLE)) {
				frostr.computeIfPresent(p.getUniqueId(), (k,v) -> v * (1+ aip.get(frostrn, PersistentDataType.DOUBLE)));
				frostr.putIfAbsent(p.getUniqueId(),aip.get(frostrn, PersistentDataType.DOUBLE));
			}
			if(aip.has(waterrn, PersistentDataType.DOUBLE)) {
				waterr.computeIfPresent(p.getUniqueId(), (k,v) -> v *(1+ aip.get(waterrn, PersistentDataType.DOUBLE)));
				waterr.putIfAbsent(p.getUniqueId(),aip.get(waterrn, PersistentDataType.DOUBLE));
			}
			if(aip.has(darkrn, PersistentDataType.DOUBLE)) {
				darkr.computeIfPresent(p.getUniqueId(), (k,v) -> v *(1+ aip.get(darkrn, PersistentDataType.DOUBLE)));
				darkr.putIfAbsent(p.getUniqueId(),aip.get(darkrn, PersistentDataType.DOUBLE));
			}
			if(aip.has(lightningrn, PersistentDataType.DOUBLE)) {
				lightningr.computeIfPresent(p.getUniqueId(), (k,v) -> v*(1 + aip.get(lightningrn, PersistentDataType.DOUBLE)));
				lightningr.putIfAbsent(p.getUniqueId(),aip.get(lightningrn, PersistentDataType.DOUBLE));
			}
			if(aip.has(flamern, PersistentDataType.DOUBLE)) {
				flamer.computeIfPresent(p.getUniqueId(), (k,v) -> v *(1+ aip.get(flamern, PersistentDataType.DOUBLE)));
				flamer.putIfAbsent(p.getUniqueId(),aip.get(flamern, PersistentDataType.DOUBLE));
			}
			if(aip.has(poisonrn, PersistentDataType.DOUBLE)) {
				poisonr.computeIfPresent(p.getUniqueId(), (k,v) -> v*(1 + aip.get(poisonrn, PersistentDataType.DOUBLE)));
				poisonr.putIfAbsent(p.getUniqueId(),aip.get(poisonrn, PersistentDataType.DOUBLE));
			}
		}
		
		PersistentDataContainer aip = p.getPersistentDataContainer();
		if(aip.has(windyrn, PersistentDataType.DOUBLE)) {
			windyr.computeIfPresent(p.getUniqueId(), (k,v) -> v * (1+ aip.get(windyrn, PersistentDataType.DOUBLE)));
			windyr.putIfAbsent(p.getUniqueId(),aip.get(windyrn, PersistentDataType.DOUBLE));
		}
		if(aip.has(earthrn, PersistentDataType.DOUBLE)) {
			earthr.computeIfPresent(p.getUniqueId(), (k,v) -> v *(1+aip.get(earthrn, PersistentDataType.DOUBLE)));
			earthr.putIfAbsent(p.getUniqueId(),aip.get(earthrn, PersistentDataType.DOUBLE));
		}
		if(aip.has(frostrn, PersistentDataType.DOUBLE)) {
			frostr.computeIfPresent(p.getUniqueId(), (k,v) -> v * (1+ aip.get(frostrn, PersistentDataType.DOUBLE)));
			frostr.putIfAbsent(p.getUniqueId(),aip.get(frostrn, PersistentDataType.DOUBLE));
		}
		if(aip.has(waterrn, PersistentDataType.DOUBLE)) {
			waterr.computeIfPresent(p.getUniqueId(), (k,v) -> v *(1+ aip.get(waterrn, PersistentDataType.DOUBLE)));
			waterr.putIfAbsent(p.getUniqueId(),aip.get(waterrn, PersistentDataType.DOUBLE));
		}
		if(aip.has(darkrn, PersistentDataType.DOUBLE)) {
			darkr.computeIfPresent(p.getUniqueId(), (k,v) -> v *(1+ aip.get(darkrn, PersistentDataType.DOUBLE)));
			darkr.putIfAbsent(p.getUniqueId(),aip.get(darkrn, PersistentDataType.DOUBLE));
		}
		if(aip.has(lightningrn, PersistentDataType.DOUBLE)) {
			lightningr.computeIfPresent(p.getUniqueId(), (k,v) -> v*(1 + aip.get(lightningrn, PersistentDataType.DOUBLE)));
			lightningr.putIfAbsent(p.getUniqueId(),aip.get(lightningrn, PersistentDataType.DOUBLE));
		}
		if(aip.has(flamern, PersistentDataType.DOUBLE)) {
			flamer.computeIfPresent(p.getUniqueId(), (k,v) -> v *(1+ aip.get(flamern, PersistentDataType.DOUBLE)));
			flamer.putIfAbsent(p.getUniqueId(),aip.get(flamern, PersistentDataType.DOUBLE));
		}
		if(aip.has(poisonrn, PersistentDataType.DOUBLE)) {
			poisonr.computeIfPresent(p.getUniqueId(), (k,v) -> v*(1 + aip.get(poisonrn, PersistentDataType.DOUBLE)));
			poisonr.putIfAbsent(p.getUniqueId(),aip.get(poisonrn, PersistentDataType.DOUBLE));
		}
		return;
	}
	

	
	final private void EldGetter(ItemStack[] ais, ItemStack mi, ItemStack oi, LivingEntity p) {
		windyd.remove(p.getUniqueId());
		earthd.remove(p.getUniqueId());
		frostd.remove(p.getUniqueId());
		waterd.remove(p.getUniqueId());
		darkd.remove(p.getUniqueId());
		lightningd.remove(p.getUniqueId());
		flamed.remove(p.getUniqueId());
		poisond.remove(p.getUniqueId());
		
		for(ItemStack ai : ais) {
			if(ai == null || !ai.hasItemMeta()) {
				continue;
			}
			PersistentDataContainer aip = ai.getItemMeta().getPersistentDataContainer();
			if(aip.has(windydn, PersistentDataType.DOUBLE)) {
				windyd.computeIfPresent(p.getUniqueId(), (k,v) -> v * (1+ aip.get(windydn, PersistentDataType.DOUBLE)));
				windyd.putIfAbsent(p.getUniqueId(),aip.get(windydn, PersistentDataType.DOUBLE));
			}
			if(aip.has(earthdn, PersistentDataType.DOUBLE)) {
				earthd.computeIfPresent(p.getUniqueId(), (k,v) -> v *(1+aip.get(earthdn, PersistentDataType.DOUBLE)));
				earthd.putIfAbsent(p.getUniqueId(),aip.get(earthdn, PersistentDataType.DOUBLE));
			}
			if(aip.has(frostdn, PersistentDataType.DOUBLE)) {
				frostd.computeIfPresent(p.getUniqueId(), (k,v) -> v * (1+ aip.get(frostdn, PersistentDataType.DOUBLE)));
				frostd.putIfAbsent(p.getUniqueId(),aip.get(frostdn, PersistentDataType.DOUBLE));
			}
			if(aip.has(waterdn, PersistentDataType.DOUBLE)) {
				waterd.computeIfPresent(p.getUniqueId(), (k,v) -> v *(1+ aip.get(waterdn, PersistentDataType.DOUBLE)));
				waterd.putIfAbsent(p.getUniqueId(),aip.get(waterdn, PersistentDataType.DOUBLE));
			}
			if(aip.has(darkdn, PersistentDataType.DOUBLE)) {
				darkd.computeIfPresent(p.getUniqueId(), (k,v) -> v *(1+ aip.get(darkdn, PersistentDataType.DOUBLE)));
				darkd.putIfAbsent(p.getUniqueId(),aip.get(darkdn, PersistentDataType.DOUBLE));
			}
			if(aip.has(lightningdn, PersistentDataType.DOUBLE)) {
				lightningd.computeIfPresent(p.getUniqueId(), (k,v) -> v*(1 + aip.get(lightningdn, PersistentDataType.DOUBLE)));
				lightningd.putIfAbsent(p.getUniqueId(),aip.get(lightningdn, PersistentDataType.DOUBLE));
			}
			if(aip.has(flamedn, PersistentDataType.DOUBLE)) {
				flamed.computeIfPresent(p.getUniqueId(), (k,v) -> v *(1+ aip.get(flamedn, PersistentDataType.DOUBLE)));
				flamed.putIfAbsent(p.getUniqueId(),aip.get(flamedn, PersistentDataType.DOUBLE));
			}
			if(aip.has(poisondn, PersistentDataType.DOUBLE)) {
				poisond.computeIfPresent(p.getUniqueId(), (k,v) -> v*(1 + aip.get(poisondn, PersistentDataType.DOUBLE)));
				poisond.putIfAbsent(p.getUniqueId(),aip.get(poisondn, PersistentDataType.DOUBLE));
			}
		}
		
		if(mi != null && mi.hasItemMeta()) {
			PersistentDataContainer aip = mi.getItemMeta().getPersistentDataContainer();
			if(aip.has(windydn, PersistentDataType.DOUBLE)) {
				windyd.computeIfPresent(p.getUniqueId(), (k,v) -> v * (1+ aip.get(windydn, PersistentDataType.DOUBLE)));
				windyd.putIfAbsent(p.getUniqueId(),aip.get(windydn, PersistentDataType.DOUBLE));
			}
			if(aip.has(earthdn, PersistentDataType.DOUBLE)) {
				earthd.computeIfPresent(p.getUniqueId(), (k,v) -> v *(1+aip.get(earthdn, PersistentDataType.DOUBLE)));
				earthd.putIfAbsent(p.getUniqueId(),aip.get(earthdn, PersistentDataType.DOUBLE));
			}
			if(aip.has(frostdn, PersistentDataType.DOUBLE)) {
				frostd.computeIfPresent(p.getUniqueId(), (k,v) -> v * (1+ aip.get(frostdn, PersistentDataType.DOUBLE)));
				frostd.putIfAbsent(p.getUniqueId(),aip.get(frostdn, PersistentDataType.DOUBLE));
			}
			if(aip.has(waterdn, PersistentDataType.DOUBLE)) {
				waterd.computeIfPresent(p.getUniqueId(), (k,v) -> v *(1+ aip.get(waterdn, PersistentDataType.DOUBLE)));
				waterd.putIfAbsent(p.getUniqueId(),aip.get(waterdn, PersistentDataType.DOUBLE));
			}
			if(aip.has(darkdn, PersistentDataType.DOUBLE)) {
				darkd.computeIfPresent(p.getUniqueId(), (k,v) -> v *(1+ aip.get(darkdn, PersistentDataType.DOUBLE)));
				darkd.putIfAbsent(p.getUniqueId(),aip.get(darkdn, PersistentDataType.DOUBLE));
			}
			if(aip.has(lightningdn, PersistentDataType.DOUBLE)) {
				lightningd.computeIfPresent(p.getUniqueId(), (k,v) -> v*(1 + aip.get(lightningdn, PersistentDataType.DOUBLE)));
				lightningd.putIfAbsent(p.getUniqueId(),aip.get(lightningdn, PersistentDataType.DOUBLE));
			}
			if(aip.has(flamedn, PersistentDataType.DOUBLE)) {
				flamed.computeIfPresent(p.getUniqueId(), (k,v) -> v *(1+ aip.get(flamedn, PersistentDataType.DOUBLE)));
				flamed.putIfAbsent(p.getUniqueId(),aip.get(flamedn, PersistentDataType.DOUBLE));
			}
			if(aip.has(poisondn, PersistentDataType.DOUBLE)) {
				poisond.computeIfPresent(p.getUniqueId(), (k,v) -> v*(1 + aip.get(poisondn, PersistentDataType.DOUBLE)));
				poisond.putIfAbsent(p.getUniqueId(),aip.get(poisondn, PersistentDataType.DOUBLE));
			}
		}
		
		if(oi != null && oi.hasItemMeta()) {
			PersistentDataContainer aip = oi.getItemMeta().getPersistentDataContainer();
			if(aip.has(windydn, PersistentDataType.DOUBLE)) {
				windyd.computeIfPresent(p.getUniqueId(), (k,v) -> v * (1+ aip.get(windydn, PersistentDataType.DOUBLE)));
				windyd.putIfAbsent(p.getUniqueId(),aip.get(windydn, PersistentDataType.DOUBLE));
			}
			if(aip.has(earthdn, PersistentDataType.DOUBLE)) {
				earthd.computeIfPresent(p.getUniqueId(), (k,v) -> v *(1+aip.get(earthdn, PersistentDataType.DOUBLE)));
				earthd.putIfAbsent(p.getUniqueId(),aip.get(earthdn, PersistentDataType.DOUBLE));
			}
			if(aip.has(frostdn, PersistentDataType.DOUBLE)) {
				frostd.computeIfPresent(p.getUniqueId(), (k,v) -> v * (1+ aip.get(frostdn, PersistentDataType.DOUBLE)));
				frostd.putIfAbsent(p.getUniqueId(),aip.get(frostdn, PersistentDataType.DOUBLE));
			}
			if(aip.has(waterdn, PersistentDataType.DOUBLE)) {
				waterd.computeIfPresent(p.getUniqueId(), (k,v) -> v *(1+ aip.get(waterdn, PersistentDataType.DOUBLE)));
				waterd.putIfAbsent(p.getUniqueId(),aip.get(waterdn, PersistentDataType.DOUBLE));
			}
			if(aip.has(darkdn, PersistentDataType.DOUBLE)) {
				darkd.computeIfPresent(p.getUniqueId(), (k,v) -> v *(1+ aip.get(darkdn, PersistentDataType.DOUBLE)));
				darkd.putIfAbsent(p.getUniqueId(),aip.get(darkdn, PersistentDataType.DOUBLE));
			}
			if(aip.has(lightningdn, PersistentDataType.DOUBLE)) {
				lightningd.computeIfPresent(p.getUniqueId(), (k,v) -> v*(1 + aip.get(lightningdn, PersistentDataType.DOUBLE)));
				lightningd.putIfAbsent(p.getUniqueId(),aip.get(lightningdn, PersistentDataType.DOUBLE));
			}
			if(aip.has(flamedn, PersistentDataType.DOUBLE)) {
				flamed.computeIfPresent(p.getUniqueId(), (k,v) -> v *(1+ aip.get(flamedn, PersistentDataType.DOUBLE)));
				flamed.putIfAbsent(p.getUniqueId(),aip.get(flamedn, PersistentDataType.DOUBLE));
			}
			if(aip.has(poisondn, PersistentDataType.DOUBLE)) {
				poisond.computeIfPresent(p.getUniqueId(), (k,v) -> v*(1 + aip.get(poisondn, PersistentDataType.DOUBLE)));
				poisond.putIfAbsent(p.getUniqueId(),aip.get(poisondn, PersistentDataType.DOUBLE));
			}
		}
		PersistentDataContainer aip = p.getPersistentDataContainer();
		if(aip.has(windydn, PersistentDataType.DOUBLE)) {
			windyd.computeIfPresent(p.getUniqueId(), (k,v) -> v * (1+ aip.get(windydn, PersistentDataType.DOUBLE)));
			windyd.putIfAbsent(p.getUniqueId(),aip.get(windydn, PersistentDataType.DOUBLE));
		}
		if(aip.has(earthdn, PersistentDataType.DOUBLE)) {
			earthd.computeIfPresent(p.getUniqueId(), (k,v) -> v *(1+aip.get(earthdn, PersistentDataType.DOUBLE)));
			earthd.putIfAbsent(p.getUniqueId(),aip.get(earthdn, PersistentDataType.DOUBLE));
		}
		if(aip.has(frostdn, PersistentDataType.DOUBLE)) {
			frostd.computeIfPresent(p.getUniqueId(), (k,v) -> v * (1+ aip.get(frostdn, PersistentDataType.DOUBLE)));
			frostd.putIfAbsent(p.getUniqueId(),aip.get(frostdn, PersistentDataType.DOUBLE));
		}
		if(aip.has(waterdn, PersistentDataType.DOUBLE)) {
			waterd.computeIfPresent(p.getUniqueId(), (k,v) -> v *(1+ aip.get(waterdn, PersistentDataType.DOUBLE)));
			waterd.putIfAbsent(p.getUniqueId(),aip.get(waterdn, PersistentDataType.DOUBLE));
		}
		if(aip.has(darkdn, PersistentDataType.DOUBLE)) {
			darkd.computeIfPresent(p.getUniqueId(), (k,v) -> v *(1+ aip.get(darkdn, PersistentDataType.DOUBLE)));
			darkd.putIfAbsent(p.getUniqueId(),aip.get(darkdn, PersistentDataType.DOUBLE));
		}
		if(aip.has(lightningdn, PersistentDataType.DOUBLE)) {
			lightningd.computeIfPresent(p.getUniqueId(), (k,v) -> v*(1 + aip.get(lightningdn, PersistentDataType.DOUBLE)));
			lightningd.putIfAbsent(p.getUniqueId(),aip.get(lightningdn, PersistentDataType.DOUBLE));
		}
		if(aip.has(flamedn, PersistentDataType.DOUBLE)) {
			flamed.computeIfPresent(p.getUniqueId(), (k,v) -> v *(1+ aip.get(flamedn, PersistentDataType.DOUBLE)));
			flamed.putIfAbsent(p.getUniqueId(),aip.get(flamedn, PersistentDataType.DOUBLE));
		}
		if(aip.has(poisondn, PersistentDataType.DOUBLE)) {
			poisond.computeIfPresent(p.getUniqueId(), (k,v) -> v*(1 + aip.get(poisondn, PersistentDataType.DOUBLE)));
			poisond.putIfAbsent(p.getUniqueId(),aip.get(poisondn, PersistentDataType.DOUBLE));
		}
		return;
	}
	

	final private Double eldam(Double od, LivingEntity p, LivingEntity le, Integer el) {
		Double d = od;
		Integer elm = 0;
		Double mul = 1d;
		final ItemStack mi = p.getEquipment().getItemInMainHand();
		
		if(mi != null && mi.hasItemMeta() && mi.getItemMeta().hasCustomModelData()) {
			if(mi.getItemMeta().getCustomModelData()%1000 < 100) {
				elm = mi.getItemMeta().getCustomModelData()%1000;
			}
		}
		
		if(Potions.elpo.containsKey(p.getUniqueId())) {
			elm = Potions.elpo.get(p.getUniqueId());
		}
		
		if(el == 14 || elm == 14) {//earth
			mul = mul +(earthd.getOrDefault(p.getUniqueId(),0d))  - earthr.getOrDefault(le.getUniqueId(), 0d);
			if(elm ==14) 
			le.getWorld().spawnParticle(Particle.COMPOSTER, le.getEyeLocation(), 2, 0.5, 0.5, 0.5);
		}
		if(el == 5 || elm == 5) {//wind
			mul = mul +(windyd.getOrDefault(p.getUniqueId(),0d))  - windyr.getOrDefault(le.getUniqueId(), 0d);
			if(elm ==5) 
			le.getWorld().spawnParticle(Particle.SMALL_GUST, le.getEyeLocation(), 2, 0.5, 0.5, 0.5);
		}
		if(el == 6 || elm == 6) {//frost
			mul = mul +(frostd.getOrDefault(p.getUniqueId(),0d))  - frostr.getOrDefault(le.getUniqueId(), 0d);
			if(elm == 6) 
			le.getWorld().spawnParticle(Particle.SNOWFLAKE, le.getEyeLocation(), 2, 0.5, 0.5, 0.5);
		}
		if(el == 7 || elm == 7) {//water
			mul = mul +(waterd.getOrDefault(p.getUniqueId(),0d))  - waterr.getOrDefault(le.getUniqueId(), 0d);
			if(elm == 7) 
			le.getWorld().spawnParticle(Particle.SPLASH, le.getEyeLocation(), 2, 0.5, 0.5, 0.5);
		}
		if(el == 8 || elm == 8) {//dark
			mul = mul +(darkd.getOrDefault(p.getUniqueId(),0d))  - darkr.getOrDefault(le.getUniqueId(), 0d);
			if(elm == 8) 
			le.getWorld().spawnParticle(Particle.LANDING_OBSIDIAN_TEAR, le.getEyeLocation(), 2, 0.5, 0.5, 0.5);
		}
		if(el == 9 || elm == 9) {//lightning
			mul = mul +(lightningd.getOrDefault(p.getUniqueId(),0d))  - lightningr.getOrDefault(le.getUniqueId(), 0d);
			if(elm == 9) 
			le.getWorld().spawnParticle(Particle.WAX_ON, le.getEyeLocation(), 2, 0.25, 0.25, 0.25);
		}
		if(el == 10 || elm == 10) {//flame
			mul = mul +(flamed.getOrDefault(p.getUniqueId(),0d))  - flamer.getOrDefault(le.getUniqueId(), 0d);
			if(elm == 10) 
			le.getWorld().spawnParticle(Particle.FLAME, le.getEyeLocation(), 2, 0.5, 0.5, 0.5);
		}
		if(el == 11 || elm == 11) {//poison
			mul = mul +(poisond.getOrDefault(p.getUniqueId(),0d))  - poisonr.getOrDefault(le.getUniqueId(), 0d);
			if(elm == 11) 
			le.getWorld().spawnParticle(Particle.ITEM_SLIME, le.getEyeLocation(), 2, 0.5, 0.5, 0.5);
		}
		if(el == 16) {//forger
			mul = mul +(earthd.getOrDefault(p.getUniqueId(),0d))  - earthr.getOrDefault(le.getUniqueId(), 0d);
			mul = mul +(lightningd.getOrDefault(p.getUniqueId(),0d))  - lightningr.getOrDefault(le.getUniqueId(), 0d);
		}
		return d*mul;
	}
	
	
	final public void eldmes(Player p) {

		if(p.getLocale().equalsIgnoreCase("ko_kr")) {
			p.sendMessage("바람 계열 - 공격력: " +windyd.getOrDefault(p.getUniqueId(),0d) + " | 저항력: "+windyr.getOrDefault(p.getUniqueId(),0d));
			p.sendMessage("대지 계열 - 공격력:" +earthd.getOrDefault(p.getUniqueId(),0d)+ " | 저항력: "+earthr.getOrDefault(p.getUniqueId(),0d));
			p.sendMessage("서리 계열 - 공격력:" +frostd.getOrDefault(p.getUniqueId(),0d)+ " | 저항력: "+frostr.getOrDefault(p.getUniqueId(),0d));
			p.sendMessage("물 계열 - 공격력:" +waterd.getOrDefault(p.getUniqueId(),0d)+ " | 저항력: "+waterr.getOrDefault(p.getUniqueId(),0d));
			p.sendMessage("어둠 계열 - 공격력:" +darkd.getOrDefault(p.getUniqueId(),0d)+ " | 저항력: "+darkr.getOrDefault(p.getUniqueId(),0d));
			p.sendMessage("번개 계열 - 공격력:" +lightningd.getOrDefault(p.getUniqueId(),0d)+ " | 저항력: "+lightningr.getOrDefault(p.getUniqueId(),0d));
			p.sendMessage("화염 계열 - 공격력:" +flamed.getOrDefault(p.getUniqueId(),0d)+ " | 저항력: "+flamer.getOrDefault(p.getUniqueId(),0d));
			p.sendMessage("독 계열 - 공격력:" +poisond.getOrDefault(p.getUniqueId(),0d)+ " | 저항력: "+poisonr.getOrDefault(p.getUniqueId(),0d));
		}
		else {
			p.sendMessage("Wind - Power: " +windyd.getOrDefault(p.getUniqueId(),0d) + " | Resistance: "+windyr.getOrDefault(p.getUniqueId(),0d));
			p.sendMessage("Earth - Power:" +earthd.getOrDefault(p.getUniqueId(),0d)+ " | Resistance: "+earthr.getOrDefault(p.getUniqueId(),0d));
			p.sendMessage("Frost - Power:" +frostd.getOrDefault(p.getUniqueId(),0d)+ " | Resistance: "+frostr.getOrDefault(p.getUniqueId(),0d));
			p.sendMessage("Water - Power:" +waterd.getOrDefault(p.getUniqueId(),0d)+ " | Resistance: "+waterr.getOrDefault(p.getUniqueId(),0d));
			p.sendMessage("Dark - Power:" +darkd.getOrDefault(p.getUniqueId(),0d)+ " | Resistance: "+darkr.getOrDefault(p.getUniqueId(),0d));
			p.sendMessage("Lightning - Power:" +lightningd.getOrDefault(p.getUniqueId(),0d)+ " | Resistance: "+lightningr.getOrDefault(p.getUniqueId(),0d));
			p.sendMessage("Flame - Power:" +flamed.getOrDefault(p.getUniqueId(),0d)+ " | Resistance: "+flamer.getOrDefault(p.getUniqueId(),0d));
			p.sendMessage("Poison - Power:" +poisond.getOrDefault(p.getUniqueId(),0d)+ " | Resistance: "+poisonr.getOrDefault(p.getUniqueId(),0d));
		}
	}
	
	
	
	/**
	 * ar.getDamage()*dou1 + pd*dou2
	 */
	final public void arsn(final AbstractArrow ar, Player p, Double dou1, Double dou2) {
		Double pd = player_damage.get(p.getName());
    	ar.setDamage(ar.getDamage()*dou1 + pd*dou2);
    }
	/**
	 * pd*dou
	 */
	final public void ar1(final AbstractArrow ar, Player p, Double dou) {
		Double pd = player_damage.get(p.getName());
    	ar.setDamage(pd*dou);
    }

	/**
	 * dou1+ pd*dou2
	 */
	final public void ar2(final AbstractArrow ar, Player p, Double dou1, Double dou2) {
		Double pd = player_damage.get(p.getName());
    	ar.setDamage(dou1+ pd*dou2);
    }

	/**
	 * d.getDamage()+pd*do
	 */
	final public void dset0(EntityDamageByEntityEvent d, Player p, Double dou) {
		Double pd = player_damage.get(p.getName());
		d.setDamage(d.getDamage()+pd*dou);
    }

	/**
	 * d.getDamage()+pd*do
	 */
	final public void dset0(EntityDamageByEntityEvent d, Player p, Double dou, LivingEntity le, Integer el) {
		Double pd = player_damage.get(p.getName());
		final Double fd = eldam(d.getDamage()+pd*dou,p,le,el);
		d.setDamage(fd);
	}
	/**
	 * d.getDamage()*(dou1)*(1+(pd*dou2))
	 */
	final public void dset1(EntityDamageByEntityEvent d, Player p, Double dou1, Double dou2) {
		Double pd = player_damage.get(p.getName());
		d.setDamage(d.getDamage()*(dou1)*(1+(pd*dou2)));
    }
	
	final public void dseth(EntityDamageByEntityEvent d, Player p, Double dou1, Double dou2) {
		Double pd = player_damage.get(p.getName());
		d.setDamage(d.getDamage()*(dou1)*(1+(pd*dou2)));
    }
	

	/**
	 * d.getDamage()*(dou1)*(1+(pd*dou2))
	 */
	final public void dset1(EntityDamageByEntityEvent d, Player p, Double dou1, Double dou2, LivingEntity le, Integer el) {
		Double pd = player_damage.get(p.getName());
		final Double fd = eldam(d.getDamage()*(dou1)*(1+(pd*dou2)),p,le,el);
		d.setDamage(fd);
    }

	/**
	 * d.getDamage()*(dou1)
	 */
	final public void dset2(EntityDamageByEntityEvent d, Player p, Double dou1, LivingEntity le, Integer el) {
		final Double fd = eldam(d.getDamage()*(dou1),p,le,el);
		d.setDamage(fd);
    }

	/**
	 * .getDamage()*(dou1) + pd*dou2
	 */
	final public void dset3(EntityDamageByEntityEvent d, Player p, Double dou1, Double dou2) {
		Double pd = player_damage.get(p.getName());
		d.setDamage(d.getDamage()*(dou1) + pd*dou2);
    }
	
	
	
	/**
	 * d.getDamage()*(dou1)
	 */
	final public void mdset(EntityDamageByEntityEvent d, LivingEntity le, Double dou1, LivingEntity de, Integer el) {
		final Double fd = eldam(d.getDamage()*(dou1),le,de,el);
		d.setDamage(fd);
    }
	
	protected final void Enderdragonhit(EnderDragon le, Player p, Double d) {
		final Chicken ch = le.getWorld().spawn(le.getLocation().add(0, 1.25, 0), Chicken.class);
		ch.getAttribute(Attribute.MAX_HEALTH).setBaseValue(9999999);
		ch.setHealth(9999999);
		ch.setSilent(true);
		ch.setInvisible(true);
		ch.setAI(false);
		ch.setCollidable(true);
		ch.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), true));
		ch.setMetadata("rob"+p.getName(), new FixedMetadataValue(RMain.getInstance(), true));
		ch.setMetadata("enderdragondamager", new FixedMetadataValue(RMain.getInstance(), true));
		p.setCooldown(Material.YELLOW_TERRACOTTA,1);
		ch.damage(d*0.046, p);
		Double dam = 9999999-ch.getHealth();
		if(le.getHealth()-dam>0) {
			le.setHealth(le.getHealth()-dam);
		}
		else {
			le.setHealth(0);
		}
		le.playHurtAnimation(0);
		le.getWorld().playSound(le.getLocation(), Sound.ENTITY_ENDER_DRAGON_HURT, SoundCategory.HOSTILE, 1, 1);
		ch.remove();
	}


	/**
	 * pd*dou1 + dou2
	 */
	final public void atks(Double dou1, Double dou2,Player p, LivingEntity le) {
		Double pd = player_damage.get(p.getName());
		if(Tag.ENTITY_TYPES_SENSITIVE_TO_SMITE.isTagged(le.getType())) {
			pd += p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.SMITE)*2.5;
		}
		if(Tag.ENTITY_TYPES_SENSITIVE_TO_BANE_OF_ARTHROPODS.isTagged(le.getType())) {
			pd += p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.BANE_OF_ARTHROPODS)*2.5;
		}
		if(Tag.ENTITY_TYPES_SENSITIVE_TO_IMPALING.isTagged(le.getType())) {
			pd += p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.IMPALING)*2.5;
		}
		Double fd = pd*dou1 + dou2;
		if(le instanceof EnderDragon) {
			Enderdragonhit((EnderDragon) le, p ,fd);
		}
		if(!Holding.superholding.containsKey(le.getUniqueId())) {
			le.setInvulnerable(false);
		}
		p.setCooldown(Material.YELLOW_TERRACOTTA,1);
		le.damage(fd, p);
	}


	/**
	 * pd*dou1 + dou2
	 */
	final public void atks(Double dou1, Double dou2,Player p, LivingEntity le, Integer el) {
		Double pd = player_damage.get(p.getName());
		if(Tag.ENTITY_TYPES_SENSITIVE_TO_SMITE.isTagged(le.getType())) {
			pd += p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.SMITE)*2.5;
		}
		if(Tag.ENTITY_TYPES_SENSITIVE_TO_BANE_OF_ARTHROPODS.isTagged(le.getType())) {
			pd += p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.BANE_OF_ARTHROPODS)*2.5;
		}
		if(Tag.ENTITY_TYPES_SENSITIVE_TO_IMPALING.isTagged(le.getType())) {
			pd += p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.IMPALING)*2.5;
		}
		Double fd = eldam(pd*dou1 + dou2,p,le,el);
		if(le instanceof EnderDragon) {
			Enderdragonhit((EnderDragon) le, p ,fd);
		}
		if(!Holding.superholding.containsKey(le.getUniqueId())) {
			le.setInvulnerable(false);
		}
		p.setCooldown(Material.YELLOW_TERRACOTTA,1);
		le.damage(fd, p);
	}



	/**
	 * pd*dou1 * (1 + 0.1 *dou2)
	 */
	final public void atk0(Double dou1, Double dou2,Player p, LivingEntity le) {
		Double pd = player_damage.get(p.getName());
		if(Tag.ENTITY_TYPES_SENSITIVE_TO_SMITE.isTagged(le.getType())) {
			pd += p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.SMITE)*2.5;
		}
		if(Tag.ENTITY_TYPES_SENSITIVE_TO_BANE_OF_ARTHROPODS.isTagged(le.getType())) {
			pd += p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.BANE_OF_ARTHROPODS)*2.5;
		}
		if(Tag.ENTITY_TYPES_SENSITIVE_TO_IMPALING.isTagged(le.getType())) {
			pd += p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.IMPALING)*2.5;
		}
		Double fd = pd*dou1  * (1 + 0.1 *dou2);
		if(le instanceof EnderDragon) {
			Enderdragonhit((EnderDragon) le, p ,fd);
		}
		if(!Holding.superholding.containsKey(le.getUniqueId())) {
			le.setInvulnerable(false);
		}
		p.setCooldown(Material.YELLOW_TERRACOTTA,1);
		le.damage(fd, p);
	}


	/**
	 * pd*dou1 * (1 + 0.1 *dou2)
	 */
	final public void atk0(Double dou1, Double dou2,Player p, LivingEntity le, Integer el) {
		Double pd = player_damage.get(p.getName());
		if(Tag.ENTITY_TYPES_SENSITIVE_TO_SMITE.isTagged(le.getType())) {
			pd += p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.SMITE)*2.5;
		}
		if(Tag.ENTITY_TYPES_SENSITIVE_TO_BANE_OF_ARTHROPODS.isTagged(le.getType())) {
			pd += p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.BANE_OF_ARTHROPODS)*2.5;
		}
		if(Tag.ENTITY_TYPES_SENSITIVE_TO_IMPALING.isTagged(le.getType())) {
			pd += p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.IMPALING)*2.5;
		}
		Double fd = eldam(pd*dou1 * (1 + 0.1 *dou2),p,le,el);
		if(le instanceof EnderDragon) {
			Enderdragonhit((EnderDragon) le, p ,fd);
		}
		if(!Holding.superholding.containsKey(le.getUniqueId())) {
			le.setInvulnerable(false);
		}
		p.setCooldown(Material.YELLOW_TERRACOTTA,1);
		le.damage(fd, p);
	}


	/**
	 * pd*dou
	 */
	final public void atk1(Double dou, Player p, LivingEntity le) {
		Double pd = player_damage.get(p.getName());
		if(Tag.ENTITY_TYPES_SENSITIVE_TO_SMITE.isTagged(le.getType())) {
			pd += p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.SMITE)*2.5;
		}
		if(Tag.ENTITY_TYPES_SENSITIVE_TO_BANE_OF_ARTHROPODS.isTagged(le.getType())) {
			pd += p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.BANE_OF_ARTHROPODS)*2.5;
		}
		if(Tag.ENTITY_TYPES_SENSITIVE_TO_IMPALING.isTagged(le.getType())) {
			pd += p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.IMPALING)*2.5;
		}
		Double fd = pd*dou;
		if(le instanceof EnderDragon) {
			Enderdragonhit((EnderDragon) le, p ,fd);
		}
		if(!Holding.superholding.containsKey(le.getUniqueId())) {
			le.setInvulnerable(false);
		}
		p.setCooldown(Material.YELLOW_TERRACOTTA,1);
		le.damage(fd, p);
	}
	/**
	 * pd*dou
	 */
	final public void atk1(Double dou, Player p, LivingEntity le, Integer el) {
		Double pd = player_damage.get(p.getName());
		if(Tag.ENTITY_TYPES_SENSITIVE_TO_SMITE.isTagged(le.getType())) {
			pd += p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.SMITE)*2.5;
		}
		if(Tag.ENTITY_TYPES_SENSITIVE_TO_BANE_OF_ARTHROPODS.isTagged(le.getType())) {
			pd += p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.BANE_OF_ARTHROPODS)*2.5;
		}
		if(Tag.ENTITY_TYPES_SENSITIVE_TO_IMPALING.isTagged(le.getType())) {
			pd += p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.IMPALING)*2.5;
		}
		Double fd = eldam(pd*dou,p,le,el);
		if(le instanceof EnderDragon) {
			Enderdragonhit((EnderDragon) le, p ,fd);
		}
		if(!Holding.superholding.containsKey(le.getUniqueId())) {
			le.setInvulnerable(false);
		}
		p.setCooldown(Material.YELLOW_TERRACOTTA,1);
		le.damage(fd, p);
	}
	
	
	/**
	 * dou1 * (1+ pd*dou2)
	 */
	final public void atk2(Double dou1,Double dou2, Player p, LivingEntity le) {
		Double pd = player_damage.get(p.getName());
		if(Tag.ENTITY_TYPES_SENSITIVE_TO_SMITE.isTagged(le.getType())) {
			pd += p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.SMITE)*2.5;
		}
		if(Tag.ENTITY_TYPES_SENSITIVE_TO_BANE_OF_ARTHROPODS.isTagged(le.getType())) {
			pd += p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.BANE_OF_ARTHROPODS)*2.5;
		}
		if(Tag.ENTITY_TYPES_SENSITIVE_TO_IMPALING.isTagged(le.getType())) {
			pd += p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.IMPALING)*2.5;
		}
		Double fd = dou1 * (1+ pd*dou2);
		if(le instanceof EnderDragon) {
			Enderdragonhit((EnderDragon) le, p, fd);
		}
		if(!Holding.superholding.containsKey(le.getUniqueId())) {
			le.setInvulnerable(false);
		}
		p.setCooldown(Material.YELLOW_TERRACOTTA,1);
		le.damage(fd, p);
	}
	/**
	 * dou1 * (1+ pd*dou2)
	 */
	final public void atk2(Double dou1,Double dou2, Player p, LivingEntity le, Integer el) {
		Double pd = player_damage.get(p.getName());
		if(Tag.ENTITY_TYPES_SENSITIVE_TO_SMITE.isTagged(le.getType())) {
			pd += p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.SMITE)*2.5;
		}
		if(Tag.ENTITY_TYPES_SENSITIVE_TO_BANE_OF_ARTHROPODS.isTagged(le.getType())) {
			pd += p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.BANE_OF_ARTHROPODS)*2.5;
		}
		if(Tag.ENTITY_TYPES_SENSITIVE_TO_IMPALING.isTagged(le.getType())) {
			pd += p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.IMPALING)*2.5;
		}
		Double fd = eldam(dou1 * (1+ pd*dou2),p,le,el);
		if(le instanceof EnderDragon) {
			Enderdragonhit((EnderDragon) le, p, fd);
		}
		if(!Holding.superholding.containsKey(le.getUniqueId())) {
			le.setInvulnerable(false);
		}
		p.setCooldown(Material.YELLOW_TERRACOTTA,1);
		le.damage(fd, p);
	}

	/**
	 * pd*dou1 +  pd*dou2
	 */
	final public void atk3(Double dou1,Double dou2, Player p, LivingEntity le) {
		Double pd = player_damage.get(p.getName());
		Double fd = pd*dou1 +  pd*dou2;
		if(Tag.ENTITY_TYPES_SENSITIVE_TO_SMITE.isTagged(le.getType())) {
			fd += p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.SMITE)*2.5;
		}
		if(Tag.ENTITY_TYPES_SENSITIVE_TO_BANE_OF_ARTHROPODS.isTagged(le.getType())) {
			fd += p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.BANE_OF_ARTHROPODS)*2.5;
		}
		if(Tag.ENTITY_TYPES_SENSITIVE_TO_IMPALING.isTagged(le.getType())) {
			fd += p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.IMPALING)*2.5;
		}
		if(le instanceof EnderDragon) {
			Enderdragonhit((EnderDragon) le, p ,fd);
		}
		if(!Holding.superholding.containsKey(le.getUniqueId())) {
			le.setInvulnerable(false);
		}
		p.setCooldown(Material.YELLOW_TERRACOTTA,1);
		le.damage(fd , p);
	}
	/**
	 * pd*dou1 +  pd*dou2
	 */
	final public void atk3(Double dou1,Double dou2, Player p, LivingEntity le, Integer el) {
		Double pd = player_damage.get(p.getName());
		if(Tag.ENTITY_TYPES_SENSITIVE_TO_SMITE.isTagged(le.getType())) {
			pd += p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.SMITE)*2.5;
		}
		if(Tag.ENTITY_TYPES_SENSITIVE_TO_BANE_OF_ARTHROPODS.isTagged(le.getType())) {
			pd += p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.BANE_OF_ARTHROPODS)*2.5;
		}
		if(Tag.ENTITY_TYPES_SENSITIVE_TO_IMPALING.isTagged(le.getType())) {
			pd += p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.IMPALING)*2.5;
		}
		Double fd = eldam(pd*dou1 +  pd*dou2,p,le,el);
		if(le instanceof EnderDragon) {
			Enderdragonhit((EnderDragon) le, p ,fd);
		}
		if(!Holding.superholding.containsKey(le.getUniqueId())) {
			le.setInvulnerable(false);
		}
		p.setCooldown(Material.YELLOW_TERRACOTTA,1);
		le.damage(fd , p);
	}
	
	CommonEvents come = CommonEvents.getInstance();

	/**
	 * pd*dou1 + dou2
	 */
	final public void atkab0(Double dou, Double dou2,Player p, LivingEntity le) {
		if(le.isInvulnerable() || le.hasMetadata("fake")) {
			return;
		}
		Double pd = player_damage.get(p.getName());
		if(Tag.ENTITY_TYPES_SENSITIVE_TO_SMITE.isTagged(le.getType())) {
			pd += p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.SMITE)*2.5;
		}
		if(Tag.ENTITY_TYPES_SENSITIVE_TO_BANE_OF_ARTHROPODS.isTagged(le.getType())) {
			pd += p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.BANE_OF_ARTHROPODS)*2.5;
		}
		if(Tag.ENTITY_TYPES_SENSITIVE_TO_IMPALING.isTagged(le.getType())) {
			pd += p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.IMPALING)*2.5;
		}
		if(!Holding.superholding.containsKey(le.getUniqueId())) {
			le.setInvulnerable(false);
		}
		if(le.getHealth()-pd*dou - dou2>0) {
			le.setHealth(le.getHealth()-pd*dou- dou2);
		}
		else {
			le.setHealth(0);
		}
		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() 
		{
     	@Override
            public void run() 
			{	
     			come.damageind(p,le,player_damage.get(p.getName())*dou + dou2);
			}
        }, 1);
	}

	/**
	 * pd*dou
	 */
	final public void atkab1(Double dou, Player p, LivingEntity le) {
		if(le.isInvulnerable() || le.hasMetadata("fake")) {
			return;
		}
		Double pd = player_damage.get(p.getName());
		if(Tag.ENTITY_TYPES_SENSITIVE_TO_SMITE.isTagged(le.getType())) {
			pd += p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.SMITE)*2.5;
		}
		if(Tag.ENTITY_TYPES_SENSITIVE_TO_BANE_OF_ARTHROPODS.isTagged(le.getType())) {
			pd += p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.BANE_OF_ARTHROPODS)*2.5;
		}
		if(Tag.ENTITY_TYPES_SENSITIVE_TO_IMPALING.isTagged(le.getType())) {
			pd += p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.IMPALING)*2.5;
		}
		if(!Holding.superholding.containsKey(le.getUniqueId())) {
			le.setInvulnerable(false);
		}
		if(le.getHealth()-pd*dou>0) {
			le.setHealth(le.getHealth()-pd*dou);
		}
		else {
			le.setHealth(0);
		}
		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() 
		{
     	@Override
            public void run() 
			{	
     			come.damageind(p,le,player_damage.get(p.getName())*dou);
			}
        }, 1);
	}


	

	public void DamageGetter(EntityDamageByEntityEvent d) 
	{
		/*if(d.getDamager() instanceof Player && d.getEntity() instanceof LivingEntity && d.getDamage()>0) {
			Player p = (Player) d.getDamager();
			LivingEntity le = (LivingEntity) d.getEntity();
			if(Tag.ENTITY_TYPES_SENSITIVE_TO_SMITE.isTagged(le.getType())) {
				d.setDamage(d.getDamage()+p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.DAMAGE_UNDEAD)*2.5);
			}
			if(Tag.ENTITY_TYPES_SENSITIVE_TO_BANE_OF_ARTHROPODS.isTagged(le.getType())) {
				d.setDamage(d.getDamage()+p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.DAMAGE_ARTHROPODS)*2.5);
			}
			if(Tag.ENTITY_TYPES_SENSITIVE_TO_IMPALING.isTagged(le.getType())) {
				d.setDamage(d.getDamage()+p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.IMPALING)*2.5);
			}
		}*/
		if(d.getEntity() instanceof LivingEntity && d.getDamager() instanceof Projectile) {
			Projectile pr = (Projectile) d.getDamager();
			LivingEntity le = (LivingEntity) d.getEntity();
			if(pr.getShooter() instanceof Player) {
				Player p = (Player) pr.getShooter();
				Bukkit.getPluginManager().callEvent(new ProjectileHitEvent(pr, le));
				if(Tag.ENTITY_TYPES_SENSITIVE_TO_SMITE.isTagged(le.getType())) {
					d.setDamage(d.getDamage()+p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.SMITE)*2.5);
				}
				if(Tag.ENTITY_TYPES_SENSITIVE_TO_BANE_OF_ARTHROPODS.isTagged(le.getType())) {
					d.setDamage(d.getDamage()+p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.BANE_OF_ARTHROPODS)*2.5);
				}
				if(Tag.ENTITY_TYPES_SENSITIVE_TO_IMPALING.isTagged(le.getType())) {
					d.setDamage(d.getDamage()+p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.IMPALING)*2.5);
				}
			}
		}
	}


	public void Supporter(EntityDamageByEntityEvent d) 
	{
		if(d.getEntity() instanceof LivingEntity && d.getDamager() instanceof Projectile) {
			Projectile pr = (Projectile) d.getDamager();
			if(pr.getShooter() instanceof Player) {
				Player p = (Player) pr.getShooter();
				if(Party.hasParty(p)) {
					return;
				}
				if(ClassData.pc.getOrDefault(p.getUniqueId(),-1) == 10|| ClassData.pc.getOrDefault(p.getUniqueId(),-1) == 61|| ClassData.pc.getOrDefault(p.getUniqueId(),-1) == 3 || ClassData.pc.getOrDefault(p.getUniqueId(),-1) == 18 || ClassData.pc.getOrDefault(p.getUniqueId(),-1) == 22|| ClassData.pc.getOrDefault(p.getUniqueId(),-1) == 26) {
					d.setDamage(d.getDamage()*(1+p.getLevel()*0.006));
				}
			}
		}
		if(d.getEntity() instanceof LivingEntity && d.getDamager() instanceof Player) {
			Player p = (Player) d.getDamager();
			if(Party.hasParty(p)) {
				return;
			}
			if(ClassData.pc.getOrDefault(p.getUniqueId(),-1) == 10|| ClassData.pc.getOrDefault(p.getUniqueId(),-1) == 61|| ClassData.pc.getOrDefault(p.getUniqueId(),-1) == 3 || ClassData.pc.getOrDefault(p.getUniqueId(),-1) == 18 || ClassData.pc.getOrDefault(p.getUniqueId(),-1) == 22|| ClassData.pc.getOrDefault(p.getUniqueId(),-1) == 26) {
				d.setDamage(d.getDamage()*(1+p.getLevel()*0.006));
			}
		}
	}

	public void DamageGetter(EntityPotionEffectEvent ev) 
	{
		if(ev.getEntity() instanceof Player) {
	 	   Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	             @Override
	             public void run() 
	             {
	            	 Player p = (Player) ev.getEntity();
	            	 DamageGetter(p);
	     			
	             }
	 	   }, 2); 
		}
	}
	

	public void DamageGetter(PlayerRespawnEvent ev) 
	{
			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	             @Override
	             public void run() 
	             {
	            	 Player p = (Player) ev.getPlayer();
	            	 DamageGetter(p);
	             }
	 	   }, 1); 
	}

	
	public void DamageGetter(PlayerItemHeldEvent ev) 
	{
   	 Player p = (Player) ev.getPlayer();
			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	             @Override
	             public void run() 
	             {
	            	 DamageGetter(p);
	             }
	 	   },2); 
	}


	public void DamageGetter(PlayerSwapHandItemsEvent ev) 
	{
	 	   Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	             @Override
	             public void run() 
	             {
	            	 Player p = (Player) ev.getPlayer();
	            	 DamageGetter(p);
	     			
	             }
	 	   }, 2); 
	}
	
	public void DamageGetter(InventoryCloseEvent ev) 
	{
		if(!RMain.getInstance().isEnabled()) {
			return;
		}
		if(ev.getInventory() != null && ev.getPlayer() != null) {
			Player p = (Player) ev.getPlayer();
			DamageGetter(p);
		}
	}

	public void DamageGetter(PlayerArmorStandManipulateEvent ev) 
	{
		if(!ev.isCancelled()) {
			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	             @Override
	             public void run() 
	             {
	            	 Player p = (Player) ev.getPlayer();
	            	 DamageGetter(p);
	             }
	 	   }, 2); 
		}
	}
}