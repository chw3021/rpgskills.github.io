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
				return "��Ȧ��Ʋ";
			}
			else if(t== EntityType.ALLAY) {
				return "�˷���";
			}
			else if(t== EntityType.FROG) {
				return "������";
			}
			else if(t== EntityType.TADPOLE) {
				return "��ì��";
			}
			else if(t== EntityType.WARDEN) {
				return "�͵�";
			}
			
			else if(t== EntityType.BAT) {
				return "����";
			}
			else if(t== EntityType.BEE) {
				return "�ܹ�";
			}
			else if(t== EntityType.BLAZE) {
				return "������";
			}
			else if(t== EntityType.CAT) {
				return "Ĺ������� ���� �ڻ��ض�";
			}
			else if(t== EntityType.CAVE_SPIDER) {
				return "�����Ź�";
			}
			else if(t== EntityType.CHICKEN) {
				return "��";
			}
			else if(t== EntityType.COD) {
				return "�ӱ�";
			}
			else if(t== EntityType.COW) {
				return "��";
			}
			else if(t== EntityType.CREEPER) {
				return "ũ����";
			}
			else if(t== EntityType.DOLPHIN) {
				return "����";
			}
			else if(t== EntityType.DONKEY) {
				return "�糪��";
			}
			else if(t== EntityType.DROWNED) {
				return "�����";
			}
			else if(t== EntityType.ELDER_GUARDIAN) {
				return "���������";
			}
			else if(t== EntityType.ENDER_DRAGON) {
				return "�����巡��";
			}
			else if(t== EntityType.ENDERMAN) {
				return "������";
			}
			else if(t== EntityType.ENDERMITE) {
				return "��������Ʈ";
			}
			else if(t== EntityType.EVOKER) {
				return "��ȯ��";
			}
			else if(t== EntityType.FOX) {
				return "����";
			}
			else if(t== EntityType.GHAST) {
				return "����Ʈ";
			}
			else if(t== EntityType.GLOW_SQUID) {
				return "�߱���¡��";
			}
			else if(t== EntityType.GOAT) {
				return "����";
			}
			else if(t== EntityType.GUARDIAN) {
				return "�����";
			}
			else if(t== EntityType.HOGLIN) {
				return "ȣ�۸�";
			}
			else if(t== EntityType.HORSE) {
				return "��";
			}
			else if(t== EntityType.HUSK) {
				return "�㽺ũ";
			}
			else if(t== EntityType.ILLUSIONER) {
				return "ȯ����";
			}
			else if(t== EntityType.IRON_GOLEM) {
				return "ö��";
			}
			else if(t== EntityType.LLAMA) {
				return "��";
			}
			else if(t== EntityType.MAGMA_CUBE) {
				return "���׸�ť��";
			}
			else if(t== EntityType.MULE) {
				return "���";
			}
			else if(t== EntityType.MUSHROOM_COW) {
				return "���÷�";
			}
			else if(t== EntityType.OCELOT) {
				return "������";
			}
			else if(t== EntityType.PANDA) {
				return "�Ǵ�";
			}
			else if(t== EntityType.PARROT) {
				return "�޹���";
			}
			else if(t== EntityType.PHANTOM) {
				return "����";
			}
			else if(t== EntityType.PIG) {
				return "�׶���";
			}
			else if(t== EntityType.PIGLIN) {
				return "�Ǳ۸�";
			}
			else if(t== EntityType.PIGLIN_BRUTE) {
				return "�Ǳ۸�����";
			}
			else if(t== EntityType.PILLAGER) {
				return "��Ż��";
			}
			else if(t== EntityType.POLAR_BEAR) {
				return "�ϱذ�";
			}
			else if(t== EntityType.PUFFERFISH) {
				return "����";
			}
			else if(t== EntityType.RABBIT) {
				return "�䳢";
			}
			else if(t== EntityType.RAVAGER) {
				return "�ı���";
			}
			else if(t== EntityType.SALMON) {
				return "����";
			}
			else if(t== EntityType.SHEEP) {
				return "��";
			}
			else if(t== EntityType.SHULKER) {
				return "��Ŀ";
			}
			else if(t== EntityType.SILVERFISH) {
				return "������";
			}
			else if(t== EntityType.SKELETON) {
				return "���̷���";
			}
			else if(t== EntityType.SKELETON_HORSE) {
				return "���̷��渻";
			}
			else if(t== EntityType.SLIME) {
				return "������";
			}
			else if(t== EntityType.SNOWMAN) {
				return "�����";
			}
			else if(t== EntityType.SPIDER) {
				return "�Ź�";
			}
			else if(t== EntityType.SQUID) {
				return "��¡��";
			}
			else if(t== EntityType.STRAY) {
				return "��Ʈ����";
			}
			else if(t== EntityType.STRIDER) {
				return "��Ʈ���̴�";
			}
			else if(t== EntityType.TRADER_LLAMA) {
				return "���ζ�";
			}
			else if(t== EntityType.TROPICAL_FISH) {
				return "�����";
			}
			else if(t== EntityType.TURTLE) {
				return "�ź�";
			}
			else if(t== EntityType.VEX) {
				return "����";
			}
			else if(t== EntityType.VILLAGER) {
				return "�ֹ�";
			}
			else if(t== EntityType.VINDICATOR) {
				return "������";
			}
			else if(t== EntityType.WANDERING_TRADER) {
				return "�����̻���";
			}
			else if(t== EntityType.WITCH) {
				return "����";
			}
			else if(t== EntityType.WITHER) {
				return "����";
			}
			else if(t== EntityType.WITHER_SKELETON) {
				return "�������̷���";
			}
			else if(t== EntityType.WOLF) {
				return "����";
			}
			else if(t== EntityType.ZOGLIN) {
				return "���۸�";
			}
			else if(t== EntityType.ZOMBIE) {
				return "����";
			}
			else if(t== EntityType.ZOMBIE_HORSE) {
				return "����";
			}
			else if(t== EntityType.ZOMBIE_VILLAGER) {
				return "�����ֹ�";
			}
			else if(t== EntityType.ZOMBIFIED_PIGLIN) {
				return "����ȭ�Ǳ۸�";
			}
			else {
				return le.getName();
			}
		}
		else {
			return le.getName();
		}
	}
	
	final private void dammul(LivingEntity le, LivingEntity de, EntityDamageByEntityEvent d) {
		if(!le.isValid()) {
			d.setCancelled(true);
			return;
		}
		if (le.hasMetadata("quest")) {
			d.setDamage(d.getDamage() * 2);
		}
		if (!le.hasMetadata("raid")) {
			if (le.hasMetadata("plain")) {
				mdset(d, le, 1.0, de, 14);
			}
			if (le.hasMetadata("mountains")) {
				mdset(d, le, 1.35, de, 5);
			}
			if (le.hasMetadata("frost")) {
				mdset(d, le, 1.7, de, 6);
			}
			if (le.hasMetadata("ocean")) {
				mdset(d, le, 2.1, de, 7);
			}
			if (le.hasMetadata("dark")) {
				mdset(d, le, 2.5, de, 8);
			}
			if (le.hasMetadata("hyper")) {
				mdset(d, le, 2.9, de, 9);
			}
			if (le.hasMetadata("red")) {
				mdset(d, le, 3.3, de, 10);
			}
			if (le.hasMetadata("poison")) {
				mdset(d, le, 3.8, de, 11);
			}
			if (le.hasMetadata("wild")) {
				mdset(d, le, 4.0, de, 14);
				mdset(d, le, 1.0, de, 5);
				mdset(d, le, 1.0, de, 9);
			}
		}
		else if (le.hasMetadata("raid")) {
			if (le.hasMetadata("plain")) {
				mdset(d, le, 4.5, de, 14);
			}
			if (le.hasMetadata("mountains")) {
				mdset(d, le, 4.5, de, 5);
			}
			if (le.hasMetadata("frost")) {
				mdset(d, le, 4.5, de, 6);
			}
			if (le.hasMetadata("ocean")) {
				mdset(d, le, 4.5, de, 7);
			}
			if (le.hasMetadata("dark")) {
				mdset(d, le, 4.5, de, 8);
			}
			if (le.hasMetadata("hyper")) {
				mdset(d, le, 4.5, de, 9);
			}
			if (le.hasMetadata("red")) {
				mdset(d, le, 4.5, de, 10);
			}
			if (le.hasMetadata("poison")) {
				mdset(d, le, 4.5, de, 11);
			}
			if (le.hasMetadata("wild")) {
				mdset(d, le, 4.5, de, 14);
				mdset(d, le, 1.0, de, 5);
				mdset(d, le, 1.0, de, 9);
			}
		}
		if (le.hasMetadata("summoned")) {
			d.setDamage(d.getDamage() * 0.9);
		}
		if (le.hasMetadata("leader")) {
			d.setDamage(d.getDamage() * 1.5);
		}
		if (le.hasMetadata("nether")) {
			mdset(d, le, 5.13, de, 10);
			mdset(d, le, 1.0, de, 8);
		}
		if (le.hasMetadata("ender")) {
			mdset(d, le, 6.2, de, 6);
			mdset(d, le, 1.0, de, 8);
		}
		if (le.hasMetadata("void")) {
			mdset(d, le, 7.3, de, 5);
			mdset(d, le, 1.0, de, 14);
		}
		if (le.hasMetadata("boss")) {
			d.setDamage(d.getDamage() * 2.5);
		}
	}
	
	public void MobDam(EntityDamageByEntityEvent d) {
		if (d.getDamager() instanceof LivingEntity && d.getEntity() instanceof LivingEntity) {
			final LivingEntity le = (LivingEntity)d.getDamager();
			final LivingEntity de = (LivingEntity)d.getEntity();
			dammul(le,de,d);
			
		}
		if (d.getDamager() instanceof Projectile && d.getEntity() instanceof LivingEntity) {
			final Projectile pr = (Projectile) d.getDamager();
			if (pr.getShooter() instanceof LivingEntity) {
				final LivingEntity le = (LivingEntity) pr.getShooter();
				final LivingEntity de = (LivingEntity)d.getEntity();
				dammul(le,de,d);
			}
		}

	}

	public void PDam(EntityDamageByEntityEvent d) {
		if (d.getDamager() instanceof Player && d.getEntity() instanceof LivingEntity) {
			Player p = (Player) d.getDamager();
			if (p.getCooldown(Material.GLISTERING_MELON_SLICE) <= 0) {
				d.setDamage(d.getDamage() * (1 + Proficiency.getpro(p)*0.25));
			}
		}
		if (d.getDamager() instanceof Projectile && d.getEntity() instanceof LivingEntity) {
			Projectile pr = (Projectile) d.getDamager();
			if (pr.getShooter() instanceof Player) {
				Player p = (Player) pr.getShooter();
				if (p.getCooldown(Material.GLISTERING_MELON_SLICE) <= 0) {
					d.setDamage(d.getDamage() * (1 + Proficiency.getpro(p)*0.25));
				}
			}
		}
		if (d.getCause() == DamageCause.THORNS && d.getEntity() instanceof Player && (d.getDamager() instanceof Guardian||d.getDamager() instanceof ElderGuardian) ) {
			if(d.getDamager().hasMetadata("fake")) {
				d.setCancelled(true);
			}
			Player p = (Player) d.getEntity();
			if(ClassData.pc.get(p.getUniqueId()) == 19 || p.hasPotionEffect(PotionEffectType.CONDUIT_POWER)) {
				d.setCancelled(true);
			}
		}

	}

	public void PlayerArmor(EntityDamageEvent d) {
		if (d.getEntity() instanceof Player) {
			Player p = (Player) d.getEntity();
			if (Proficiency.getpro(p) >= 2) {
				d.setDamage(d.getDamage() * 0.75);
			}
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
