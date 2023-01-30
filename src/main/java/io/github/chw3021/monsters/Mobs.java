package io.github.chw3021.monsters;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.ElderGuardian;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Guardian;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Mob;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.EntityPotionEffectEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.loot.LootTables;
import org.bukkit.loot.Lootable;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.potion.PotionEffectType;
import org.jetbrains.annotations.Nullable;

import io.github.chw3021.classes.ClassData;
import io.github.chw3021.classes.Proficiency;
import io.github.chw3021.commons.Holding;
import io.github.chw3021.commons.Pak;
import io.github.chw3021.rmain.RMain;

public class Mobs extends Pak {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3620485680544888652L;
	protected final String lang = RMain.getInstance().getConfig().getString("Language");

	final protected String trans(LivingEntity le) {
		if(lang.contains("kr")) {
			EntityType t = le.getType();
			if(t== EntityType.AXOLOTL) {
				return "¾ÆÈ¦·ÎÆ²";
			}
			else if(t== EntityType.ALLAY) {
				return "¾Ë·¹ÀÌ";
			}
			else if(t== EntityType.FROG) {
				return "°³±¸¸®";
			}
			else if(t== EntityType.TADPOLE) {
				return "¿ÃÃ¬ÀÌ";
			}
			else if(t== EntityType.WARDEN) {
				return "¿Íµç";
			}
			
			else if(t== EntityType.BAT) {
				return "¹ÚÁã";
			}
			else if(t== EntityType.BEE) {
				return "²Ü¹ú";
			}
			else if(t== EntityType.BLAZE) {
				return "ºí·¹ÀÌÁî";
			}
			else if(t== EntityType.CAT) {
				return "Ä¹¸¾ÃæµéÀº Á¦¹ß ÀÚ»ìÇØ¶ó";
			}
			else if(t== EntityType.CAVE_SPIDER) {
				return "µ¿±¼°Å¹Ì";
			}
			else if(t== EntityType.CHICKEN) {
				return "´ß";
			}
			else if(t== EntityType.COD) {
				return "¸Ó±¸";
			}
			else if(t== EntityType.COW) {
				return "¼Ò";
			}
			else if(t== EntityType.CREEPER) {
				return "Å©¸®ÆÛ";
			}
			else if(t== EntityType.DOLPHIN) {
				return "µ¹°í·¡";
			}
			else if(t== EntityType.DONKEY) {
				return "´ç³ª±Í";
			}
			else if(t== EntityType.DROWNED) {
				return "µå¶ó¿îµå";
			}
			else if(t== EntityType.ELDER_GUARDIAN) {
				return "¿¤´õ°¡µð¾ð";
			}
			else if(t== EntityType.ENDER_DRAGON) {
				return "¿£´õµå·¡°ï";
			}
			else if(t== EntityType.ENDERMAN) {
				return "¿£´õ¸Ç";
			}
			else if(t== EntityType.ENDERMITE) {
				return "¿£´õ¸¶ÀÌÆ®";
			}
			else if(t== EntityType.EVOKER) {
				return "¼ÒÈ¯»ç";
			}
			else if(t== EntityType.FOX) {
				return "¿©¿ì";
			}
			else if(t== EntityType.GHAST) {
				return "°¡½ºÆ®";
			}
			else if(t== EntityType.GLOW_SQUID) {
				return "¹ß±¤¿ÀÂ¡¾î";
			}
			else if(t== EntityType.GOAT) {
				return "¿°¼Ò";
			}
			else if(t== EntityType.GUARDIAN) {
				return "°¡µð¾ð";
			}
			else if(t== EntityType.HOGLIN) {
				return "È£±Û¸°";
			}
			else if(t== EntityType.HORSE) {
				return "¸»";
			}
			else if(t== EntityType.HUSK) {
				return "Çã½ºÅ©";
			}
			else if(t== EntityType.ILLUSIONER) {
				return "È¯¼ú»ç";
			}
			else if(t== EntityType.IRON_GOLEM) {
				return "Ã¶°ñ·½";
			}
			else if(t== EntityType.LLAMA) {
				return "¶ó¸¶";
			}
			else if(t== EntityType.MAGMA_CUBE) {
				return "¸¶±×¸¶Å¥ºê";
			}
			else if(t== EntityType.MULE) {
				return "³ë»õ";
			}
			else if(t== EntityType.MUSHROOM_COW) {
				return "¹«½Ã·ë";
			}
			else if(t== EntityType.OCELOT) {
				return "¿À¼¿·Ô";
			}
			else if(t== EntityType.PANDA) {
				return "ÆÇ´Ù";
			}
			else if(t== EntityType.PARROT) {
				return "¾Þ¹«»õ";
			}
			else if(t== EntityType.PHANTOM) {
				return "¸Á·É";
			}
			else if(t== EntityType.PIG) {
				return "¶×¶òÈÆ";
			}
			else if(t== EntityType.PIGLIN) {
				return "ÇÇ±Û¸°";
			}
			else if(t== EntityType.PIGLIN_BRUTE) {
				return "ÇÇ±Û¸°±«¼ö";
			}
			else if(t== EntityType.PILLAGER) {
				return "¾àÅ»ÀÚ";
			}
			else if(t== EntityType.POLAR_BEAR) {
				return "ºÏ±Ø°õ";
			}
			else if(t== EntityType.PUFFERFISH) {
				return "º¹¾î";
			}
			else if(t== EntityType.RABBIT) {
				return "Åä³¢";
			}
			else if(t== EntityType.RAVAGER) {
				return "ÆÄ±«¼ö";
			}
			else if(t== EntityType.SALMON) {
				return "¿¬¾î";
			}
			else if(t== EntityType.SHEEP) {
				return "¾ç";
			}
			else if(t== EntityType.SHULKER) {
				return "¼ÈÄ¿";
			}
			else if(t== EntityType.SILVERFISH) {
				return "Á»¹ú·¹";
			}
			else if(t== EntityType.SKELETON) {
				return "½ºÄÌ·¹Åæ";
			}
			else if(t== EntityType.SKELETON_HORSE) {
				return "½ºÄÌ·¹Åæ¸»";
			}
			else if(t== EntityType.SLIME) {
				return "½½¶óÀÓ";
			}
			else if(t== EntityType.SNOWMAN) {
				return "´«»ç¶÷";
			}
			else if(t== EntityType.SPIDER) {
				return "°Å¹Ì";
			}
			else if(t== EntityType.SQUID) {
				return "¿ÀÂ¡¾î";
			}
			else if(t== EntityType.STRAY) {
				return "½ºÆ®·¹ÀÌ";
			}
			else if(t== EntityType.STRIDER) {
				return "½ºÆ®¶óÀÌ´õ";
			}
			else if(t== EntityType.TRADER_LLAMA) {
				return "»óÀÎ¶ó¸¶";
			}
			else if(t== EntityType.TROPICAL_FISH) {
				return "¿­´ë¾î";
			}
			else if(t== EntityType.TURTLE) {
				return "°ÅºÏ";
			}
			else if(t== EntityType.VEX) {
				return "º¤½º";
			}
			else if(t== EntityType.VILLAGER) {
				return "ÁÖ¹Î";
			}
			else if(t== EntityType.VINDICATOR) {
				return "º¯¸íÀÚ";
			}
			else if(t== EntityType.WANDERING_TRADER) {
				return "¶°µ¹ÀÌ»óÀÎ";
			}
			else if(t== EntityType.WITCH) {
				return "¸¶³à";
			}
			else if(t== EntityType.WITHER) {
				return "À§´õ";
			}
			else if(t== EntityType.WITHER_SKELETON) {
				return "À§´õ½ºÄÌ·¹Åæ";
			}
			else if(t== EntityType.WOLF) {
				return "´Á´ë";
			}
			else if(t== EntityType.ZOGLIN) {
				return "Á¶±Û¸°";
			}
			else if(t== EntityType.ZOMBIE) {
				return "Á»ºñ";
			}
			else if(t== EntityType.ZOMBIE_HORSE) {
				return "Á»ºñ¸»";
			}
			else if(t== EntityType.ZOMBIE_VILLAGER) {
				return "Á»ºñÁÖ¹Î";
			}
			else if(t== EntityType.ZOMBIFIED_PIGLIN) {
				return "Á»ºñÈ­ÇÇ±Û¸°";
			}
			else {
				return le.getName();
			}
		}
		else {
			return le.getName();
		}
	}
	

	public void RaidMobPo(EntityPotionEffectEvent d) {
		if (d.getEntity() instanceof LivingEntity && d.getEntity().hasMetadata("raid")) {
			LivingEntity p = (LivingEntity) d.getEntity();
			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
				@Override
				public void run() {
					p.removePotionEffect(PotionEffectType.GLOWING);
				}
			}, 6);
		}
	}

	final protected void equipsum(LivingEntity creature, @Nullable ItemStack head,
			@Nullable ItemStack chest, @Nullable ItemStack leg, @Nullable ItemStack boots, @Nullable ItemStack main,
			@Nullable ItemStack off) {

		creature.getEquipment().setHelmet(head);
		creature.getEquipment().setChestplate(chest);
		creature.getEquipment().setLeggings(leg);
		creature.getEquipment().setBoots(boots);
		creature.getEquipment().setItemInMainHand(main);
		creature.getEquipment().setItemInOffHand(off);
		
		creature.getEquipment().setBootsDropChance(0);
		creature.getEquipment().setChestplateDropChance(0);
		creature.getEquipment().setHelmetDropChance(0);
		creature.getEquipment().setItemInMainHandDropChance(0);
		creature.getEquipment().setItemInOffHandDropChance(0);
		creature.getEquipment().setLeggingsDropChance(0);
	}

	final protected void equip(LivingEntity creature, @Nullable ItemStack head,
			@Nullable ItemStack chest, @Nullable ItemStack leg, @Nullable ItemStack boots, @Nullable ItemStack main,
			@Nullable ItemStack off) {

		if (head != null) {
			creature.getEquipment().setHelmet(head);
		}
		if (chest != null) {
			creature.getEquipment().setChestplate(chest);
		}
		if (leg != null) {
			creature.getEquipment().setLeggings(leg);
		}
		if (boots != null) {
			creature.getEquipment().setBoots(boots);
		}
		if (main != null) {
			creature.getEquipment().setItemInMainHand(main);
		}
		if (off != null) {
			creature.getEquipment().setItemInOffHand(off);
		}
		creature.getEquipment().setBootsDropChance(0);
		creature.getEquipment().setChestplateDropChance(0);
		creature.getEquipment().setHelmetDropChance(0);
		creature.getEquipment().setItemInMainHandDropChance(0);
		creature.getEquipment().setItemInOffHandDropChance(0);
		creature.getEquipment().setLeggingsDropChance(0);
	}

	final protected LivingEntity Mobspawn(LivingEntity le, @Nullable String name, Double health, @Nullable ItemStack head,
			@Nullable ItemStack chest, @Nullable ItemStack leg, @Nullable ItemStack boots, @Nullable ItemStack main,
			@Nullable ItemStack off, @Nullable EntityType type) {
		if(le.getType() == EntityType.ARMOR_STAND) {
			return le;
		}
		LivingEntity creature = (LivingEntity) le.getWorld().spawnEntity(le.getLocation(), type);
		creature.setRemoveWhenFarAway(true);
		creature.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), true));
		creature.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(health);
		creature.setHealth(health);
		creature.setCustomName(name);
		
		equip(creature,head,chest,leg,boots,main,off);
		le.remove();
		return creature;
	}

	final private LivingEntity SummonEffect(Location l, @Nullable EntityType type) {
		final LivingEntity creature = (LivingEntity) l.getWorld().spawnEntity(l.clone(), type);
		Holding.holding(null, creature, 20l);
		l.getWorld().playSound(l, Sound.BLOCK_RESPAWN_ANCHOR_CHARGE, 0.1f, 1.5f);
		for(int dou = 0; dou < 10; dou++) {
			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
                @Override
                public void run() 
                {
                	creature.teleport(creature.getLocation().add(creature.getLocation().getDirection().rotateAroundX(-Math.PI/2).normalize().multiply(0.1)));
                }
			}, dou*2);
		}
		return creature;
	}
	
	final protected LivingEntity Summon(Location l, @Nullable String name, Double health, @Nullable ItemStack head,
			@Nullable ItemStack chest, @Nullable ItemStack leg, @Nullable ItemStack boots, @Nullable ItemStack main,
			@Nullable ItemStack off, @Nullable EntityType type) {
		final LivingEntity creature = (LivingEntity) SummonEffect(l,type);
		creature.setMetadata("summoned", new FixedMetadataValue(RMain.getInstance(), true));
		creature.setMetadata("rmf", new FixedMetadataValue(RMain.getInstance(), true));
		creature.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), true));
		creature.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(health);
		creature.setHealth(health);
		creature.setCustomName(name);
		creature.setRemoveWhenFarAway(false);
		creature.getEquipment().setBootsDropChance(0);
		creature.getEquipment().setChestplateDropChance(0);
		creature.getEquipment().setHelmetDropChance(0);
		creature.getEquipment().setItemInMainHandDropChance(0);
		creature.getEquipment().setItemInOffHandDropChance(0);
		creature.getEquipment().setLeggingsDropChance(0);
		
		equipsum(creature,head,chest,leg,boots,main,off);
		
		Lootable cl = (Lootable) creature;
		cl.setLootTable(LootTables.EMPTY.getLootTable());
		return creature;
	}
	
	final protected LivingEntity MobspawnLoc(Location l, @Nullable String name, Double health, @Nullable ItemStack head,
			@Nullable ItemStack chest, @Nullable ItemStack leg, @Nullable ItemStack boots, @Nullable ItemStack main,
			@Nullable ItemStack off, @Nullable EntityType type) {
		final LivingEntity creature = (LivingEntity) l.getWorld().spawnEntity(l.clone().add(0, 20, 0), type);
		Holding.invur(creature, 40l);
		creature.setRemoveWhenFarAway(false);
		creature.setPersistent(true);
		creature.setMetadata("rmf", new FixedMetadataValue(RMain.getInstance(), true));
		creature.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), true));
		creature.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(health);
		creature.setHealth(health);
		creature.setCustomName(name);
		
		equipsum(creature,head,chest,leg,boots,main,off);
		
		Lootable cl = (Lootable) creature;
		cl.setLootTable(LootTables.EMPTY.getLootTable());
		return creature;
	}


	
	
	
}
