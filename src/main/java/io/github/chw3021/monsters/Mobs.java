package io.github.chw3021.monsters;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Villager;
import org.bukkit.entity.Villager.Profession;
import org.bukkit.event.entity.EntityPotionEffectEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.loot.LootTables;
import org.bukkit.loot.Lootable;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.potion.PotionEffectType;
import org.jetbrains.annotations.Nullable;

import io.github.chw3021.commons.ConfigManager;
import io.github.chw3021.commons.Holding;
import io.github.chw3021.commons.Pak;
import io.github.chw3021.rmain.RMain;

public class Mobs extends Pak {

	protected final String lang = ConfigManager.getInstance(RMain.getInstance()).getCustomConfig().getString("Language");

	final protected String trans(LivingEntity le) {
		if(lang.contains("kr")) {
			EntityType t = le.getType();
			if(t== EntityType.AXOLOTL) {
				return "아홀로틀";
			}
			else if(t== EntityType.VILLAGER) {
				Villager vil = (Villager)le;
				Profession prof = vil.getProfession();
				if(prof == Profession.ARMORER) {
					return "갑옷제조인";
				}
				else if (prof == Profession.BUTCHER) {
					return "도살업자";
				} else if (prof == Profession.CARTOGRAPHER) {
					return "지도제작자";
				} else if (prof == Profession.CLERIC) {
					return "사제";
				} else if (prof == Profession.FARMER) {
					return "농부";
				} else if (prof == Profession.FISHERMAN) {
					return "어부";
				} else if (prof == Profession.FLETCHER) {
					return "화살제조인";
				} else if (prof == Profession.LEATHERWORKER) {
					return "가죽세공인";
				} else if (prof == Profession.LIBRARIAN) {
					return "사서";
				} else if (prof == Profession.MASON) {
					return "석공";
				} else if (prof == Profession.NITWIT) {
					return "멍청이";
				} else if (prof == Profession.NONE) {
					return "주민";
				} else if (prof == Profession.SHEPHERD) {
					return "양치기";
				} else if (prof == Profession.TOOLSMITH) {
					return "도구제조인";
				} else if (prof == Profession.WEAPONSMITH) {
					return "무기제조인";
				} else {
					return "주민";
				}
			}
			else if(t== EntityType.ALLAY) {
				return "알레이";
			}
			else if(t== EntityType.FROG) {
				return "개구리";
			}
			else if(t== EntityType.TADPOLE) {
				return "올챙이";
			}
			else if(t== EntityType.ARMADILLO) {
				return "아르마딜로";
			}
			else if(t== EntityType.BOGGED) {
				return "보그드";
			}
			else if(t== EntityType.CAMEL) {
				return "낙타";
			}
			else if(t== EntityType.BREEZE) {
				return "브리즈";
			}
			else if(t== EntityType.SNIFFER) {
				return "스니퍼";
			}

			else if(t== EntityType.BAT) {
				return "박쥐";
			}
			else if(t== EntityType.BEE) {
				return "꿀벌";
			}
			else if(t== EntityType.BLAZE) {
				return "블레이즈";
			}
			else if(t== EntityType.CAT) {
				return "고양이";
			}
			else if(t== EntityType.CAVE_SPIDER) {
				return "동굴거미";
			}
			else if(t== EntityType.CHICKEN) {
				return "닭";
			}
			else if(t== EntityType.COD) {
				return "대구";
			}
			else if(t== EntityType.COW) {
				return "소";
			}
			else if(t== EntityType.CREEPER) {
				return "크리퍼";
			}
			else if(t== EntityType.DOLPHIN) {
				return "돌고래";
			}
			else if(t== EntityType.DONKEY) {
				return "당나귀";
			}
			else if(t== EntityType.DROWNED) {
				return "드라운드";
			}
			else if(t== EntityType.ELDER_GUARDIAN) {
				return "엘더가디언";
			}
			else if(t== EntityType.ENDER_DRAGON) {
				return "엔더드래곤";
			}
			else if(t== EntityType.ENDERMAN) {
				return "엔더맨";
			}
			else if(t== EntityType.ENDERMITE) {
				return "엔더마이트";
			}
			else if(t== EntityType.EVOKER) {
				return "소환사";
			}
			else if(t== EntityType.FOX) {
				return "여우";
			}
			else if(t== EntityType.GHAST) {
				return "가스트";
			}
			else if(t== EntityType.GLOW_SQUID) {
				return "발광오징어";
			}
			else if(t== EntityType.GOAT) {
				return "염소";
			}
			else if(t== EntityType.GUARDIAN) {
				return "가디언";
			}
			else if(t== EntityType.HOGLIN) {
				return "호글린";
			}
			else if(t== EntityType.HORSE) {
				return "말";
			}
			else if(t== EntityType.HUSK) {
				return "허스크";
			}
			else if(t== EntityType.ILLUSIONER) {
				return "환술사";
			}
			else if(t== EntityType.IRON_GOLEM) {
				return "철골렘";
			}
			else if(t== EntityType.LLAMA) {
				return "라마";
			}
			else if(t== EntityType.MAGMA_CUBE) {
				return "마그마큐브";
			}
			else if(t== EntityType.MULE) {
				return "노새";
			}
			else if(t== EntityType.MOOSHROOM) {
				return "무시룸";
			}
			else if(t== EntityType.OCELOT) {
				return "오셀롯";
			}
			else if(t== EntityType.PANDA) {
				return "판다";
			}
			else if(t== EntityType.PARROT) {
				return "앵무새";
			}
			else if(t== EntityType.PHANTOM) {
				return "망령";
			}
			else if(t== EntityType.PIG) {
				return "돼지";
			}
			else if(t== EntityType.PIGLIN) {
				return "피글린";
			}
			else if(t== EntityType.PIGLIN_BRUTE) {
				return "난폭한피글린";
			}
			else if(t== EntityType.PILLAGER) {
				return "약탈자";
			}
			else if(t== EntityType.POLAR_BEAR) {
				return "북극곰";
			}
			else if(t== EntityType.PUFFERFISH) {
				return "복어";
			}
			else if(t== EntityType.RABBIT) {
				return "토끼";
			}
			else if(t== EntityType.RAVAGER) {
				return "파괴수";
			}
			else if(t== EntityType.SALMON) {
				return "연어";
			}
			else if(t== EntityType.SHEEP) {
				return "양";
			}
			else if(t== EntityType.SHULKER) {
				return "셜커";
			}
			else if(t== EntityType.SILVERFISH) {
				return "좀벌레";
			}
			else if(t== EntityType.SKELETON) {
				return "스켈레톤";
			}
			else if(t== EntityType.SKELETON_HORSE) {
				return "스켈레톤말";
			}
			else if(t== EntityType.SLIME) {
				return "슬라임";
			}
			else if(t== EntityType.SNOW_GOLEM) {
				return "눈사람";
			}
			else if(t== EntityType.SPIDER) {
				return "거미";
			}
			else if(t== EntityType.SQUID) {
				return "오징어";
			}
			else if(t== EntityType.STRAY) {
				return "스트레이";
			}
			else if(t== EntityType.STRIDER) {
				return "스트라이더";
			}
			else if(t== EntityType.TRADER_LLAMA) {
				return "상인라마";
			}
			else if(t== EntityType.TROPICAL_FISH) {
				return "열대어";
			}
			else if(t== EntityType.TURTLE) {
				return "거북";
			}
			else if(t== EntityType.VEX) {
				return "벡스";
			}
			else if(t== EntityType.VINDICATOR) {
				return "변명자";
			}
			else if(t== EntityType.WANDERING_TRADER) {
				return "떠돌이상인";
			}
			else if(t== EntityType.WITCH) {
				return "마녀";
			}
			else if(t== EntityType.WITHER) {
				return "위더";
			}
			else if(t== EntityType.WITHER_SKELETON) {
				return "위더스켈레톤";
			}
			else if(t== EntityType.WOLF) {
				return "늑대";
			}
			else if(t== EntityType.ZOGLIN) {
				return "조글린";
			}
			else if(t== EntityType.ZOMBIE) {
				return "좀비";
			}
			else if(t== EntityType.ZOMBIE_HORSE) {
				return "좀비말";
			}
			else if(t== EntityType.ZOMBIE_VILLAGER) {
				return "좀비주민";
			}
			else if(t== EntityType.ZOMBIFIED_PIGLIN) {
				return "좀비화피글린";
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
			le.setMetadata("portal", new FixedMetadataValue(RMain.getInstance(), true));
			return le;
		}
		LivingEntity creature = (LivingEntity) le.getWorld().spawnEntity(le.getLocation(), type);
		creature.setRemoveWhenFarAway(true);
		creature.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), true));
		creature.getAttribute(Attribute.MAX_HEALTH).setBaseValue(health);
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
		creature.getAttribute(Attribute.MAX_HEALTH).setBaseValue(health);
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
		cl.setLootTable(LootTables.UNEMPLOYED_GIFT.getLootTable());
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
		creature.getAttribute(Attribute.MAX_HEALTH).setBaseValue(health);
		creature.setHealth(health);
		creature.setCustomName(name);
		creature.getEquipment().setBootsDropChance(0);
		creature.getEquipment().setChestplateDropChance(0);
		creature.getEquipment().setHelmetDropChance(0);
		creature.getEquipment().setItemInMainHandDropChance(0);
		creature.getEquipment().setItemInOffHandDropChance(0);
		creature.getEquipment().setLeggingsDropChance(0);
		
		equipsum(creature,head,chest,leg,boots,main,off);
		
		Lootable cl = (Lootable) creature;
		cl.setLootTable(LootTables.UNEMPLOYED_GIFT.getLootTable());
		return creature;
	}
	
	final protected LivingEntity phaseChange(Location l, @Nullable String name, Double health, @Nullable ItemStack head,
			@Nullable ItemStack chest, @Nullable ItemStack leg, @Nullable ItemStack boots, @Nullable ItemStack main,
			@Nullable ItemStack off, @Nullable EntityType type) {
		final LivingEntity creature = (LivingEntity) SummonEffect(l,type);
		Holding.invur(creature, 20l);
		creature.setRemoveWhenFarAway(false);
		creature.setPersistent(true);
		creature.setMetadata("rmf", new FixedMetadataValue(RMain.getInstance(), true));
		creature.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), true));
		creature.getAttribute(Attribute.MAX_HEALTH).setBaseValue(health);
		creature.setHealth(health);
		creature.setCustomName(name);
		creature.getEquipment().setBootsDropChance(0);
		creature.getEquipment().setChestplateDropChance(0);
		creature.getEquipment().setHelmetDropChance(0);
		creature.getEquipment().setItemInMainHandDropChance(0);
		creature.getEquipment().setItemInOffHandDropChance(0);
		creature.getEquipment().setLeggingsDropChance(0);
		
		equipsum(creature,head,chest,leg,boots,main,off);
		
		Lootable cl = (Lootable) creature;
		cl.setLootTable(LootTables.UNEMPLOYED_GIFT.getLootTable());
		return creature;
	}


	
	
	
}