//package io.github.chw3021.monsters.wild;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.HashSet;
//import java.util.UUID;
//import java.util.concurrent.atomic.AtomicInteger;
//
//import org.bukkit.Bukkit;
//import org.bukkit.Color;
//import org.bukkit.FireworkEffect;
//import org.bukkit.Location;
//import org.bukkit.Material;
//import org.bukkit.Particle;
//import org.bukkit.Sound;
//import org.bukkit.FireworkEffect.Type;
//import org.bukkit.entity.AreaEffectCloud;
//import org.bukkit.entity.Creeper;
//import org.bukkit.entity.Entity;
//import org.bukkit.entity.EntityType;
//import org.bukkit.entity.Evoker;
//import org.bukkit.entity.Firework;
//import org.bukkit.entity.Illusioner;
//import org.bukkit.entity.Item;
//import org.bukkit.entity.Skeleton;
//import org.bukkit.entity.Snowball;
//import org.bukkit.entity.LivingEntity;
//import org.bukkit.entity.Player;
//import org.bukkit.entity.ThrownPotion;
//import org.bukkit.entity.Witch;
//import org.bukkit.entity.Zombie;
//import org.bukkit.event.entity.EntityDamageByEntityEvent;
//import org.bukkit.event.entity.EntityShootBowEvent;
//import org.bukkit.event.entity.EntitySpellCastEvent;
//import org.bukkit.event.entity.EntityTargetEvent;
//import org.bukkit.event.entity.ProjectileHitEvent;
//import org.bukkit.event.entity.ProjectileLaunchEvent;
//import org.bukkit.event.entity.SlimeSplitEvent;
//import org.bukkit.inventory.ItemStack;
//import org.bukkit.inventory.meta.FireworkMeta;
//import org.bukkit.inventory.meta.ItemMeta;
//import org.bukkit.inventory.meta.PotionMeta;
//import org.bukkit.metadata.FixedMetadataValue;
//import org.bukkit.potion.PotionEffect;
//import org.bukkit.potion.PotionEffectType;
//import org.bukkit.util.Vector;
//
//import com.google.common.collect.ArrayListMultimap;
//import com.google.common.collect.Multimap;
//
//import io.github.chw3021.commons.Holding;
//import io.github.chw3021.monsters.raids.OverworldRaids;
//import io.github.chw3021.monsters.raids.Summoned;
//import io.github.chw3021.rmain.RMain;
//
//
//
//public class WildSkills extends Summoned{
//
//	/**
//	 * 
//	 */
//	private static transient final long serialVersionUID = -2543380479388196924L;
//	/**
//	 * 
//	 */
//	
//	Holding hold = Holding.getInstance();
//	private HashMap<UUID, Long> rb3cooldown = new HashMap<UUID, Long>();
//	private HashMap<UUID, Long> rb4cooldown = new HashMap<UUID, Long>();
//	private HashMap<UUID, Long> rb8cooldown = new HashMap<UUID, Long>();
//	private HashMap<UUID, Integer> throwable = new HashMap<UUID, Integer>();
//	static public Multimap<String, Integer> ordt = ArrayListMultimap.create();
//	
//	private HashMap<UUID, Boolean> npable = new HashMap<UUID, Boolean>();
//
//	
//	private static final WildSkills instance = new WildSkills ();
//	public static WildSkills getInstance()
//	{
//		return instance;
//	}
//	
//	public void slimesplit(SlimeSplitEvent d) 
//	{
//		if(d.getEntity().hasMetadata("GiantSlime")) {
//			d.setCancelled(true);
//		}
//	}
//
//	
//	
//}