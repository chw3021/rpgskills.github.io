package io.github.chw3021.monsters;



import java.io.Serializable;
import java.util.List;
import java.util.Random;

import org.bukkit.World;
import org.bukkit.World.Environment;
import org.bukkit.attribute.Attribute;
import org.bukkit.block.Biome;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.MagmaCube;
import org.bukkit.entity.Player;
import org.bukkit.entity.Slime;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityDropItemEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.entity.EntityPotionEffectEvent;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.EntitySpellCastEvent;
import org.bukkit.event.entity.EntityTargetEvent;
import org.bukkit.event.entity.EntityTransformEvent;
import org.bukkit.event.entity.EntityTransformEvent.TransformReason;
import org.bukkit.event.entity.ExplosionPrimeEvent;
import org.bukkit.event.entity.FireworkExplodeEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.entity.SlimeSplitEvent;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.event.server.PluginDisableEvent;
import org.bukkit.event.server.PluginEnableEvent;
import org.bukkit.metadata.FixedMetadataValue;

import io.github.chw3021.commons.ConfigManager;
import io.github.chw3021.monsters.dark.DarkMobsSpawn;
import io.github.chw3021.monsters.dark.DarkRaids;
import io.github.chw3021.monsters.dark.DarkSkills;
import io.github.chw3021.monsters.hyper.HyperMobsSpawn;
import io.github.chw3021.monsters.hyper.HyperRaids;
import io.github.chw3021.monsters.hyper.HyperSkills;
import io.github.chw3021.monsters.mountains.MountainsMobsSpawn;
import io.github.chw3021.monsters.mountains.MountainsRaids;
import io.github.chw3021.monsters.mountains.MountainsSkills;
import io.github.chw3021.monsters.nether.NetherMobsSpawn;
import io.github.chw3021.monsters.nether.NetherRaids;
import io.github.chw3021.monsters.ocean.OceanMobsSpawn;
import io.github.chw3021.monsters.ocean.OceanRaids;
import io.github.chw3021.monsters.ocean.OceanSkills;
import io.github.chw3021.monsters.plain.PlainMobsSpawn;
import io.github.chw3021.monsters.plain.PlainRaids;
import io.github.chw3021.monsters.plain.PlainSkills;
import io.github.chw3021.monsters.poison.PoisonMobsSpawn;
import io.github.chw3021.monsters.poison.PoisonRaids;
import io.github.chw3021.monsters.poison.PoisonSkills;
import io.github.chw3021.monsters.raids.BloodNight;
import io.github.chw3021.monsters.raids.Summoned;
import io.github.chw3021.monsters.red.RedMobsSpawn;
import io.github.chw3021.monsters.red.RedRaids;
import io.github.chw3021.monsters.red.RedSkills;
import io.github.chw3021.monsters.snow.SnowMobsSpawn;
import io.github.chw3021.monsters.snow.SnowRaids;
import io.github.chw3021.monsters.snow.SnowSkills;
import io.github.chw3021.monsters.wild.WildMobsSpawn;
import io.github.chw3021.monsters.wild.WildRaids;
import io.github.chw3021.party.PartyLeaveEvent;
import io.github.chw3021.rmain.RMain;

public class MobsSkillsEvents extends Mobs implements Listener, Serializable  {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -3182008353111479606L;
	private final String lang = ConfigManager.getInstance(RMain.getInstance()).getCustomConfig().getString("Language");
	private final List<String> disabledWorlds = ConfigManager.getInstance(RMain.getInstance()).getCustomConfig().getStringList("Worlds");
	Mobs mobs = new Mobs();                 


	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void Mobspawn(CreatureSpawnEvent ev) {
		if (ev.getEntityType() == EntityType.ARMOR_STAND) {
			return;
		}
		
		final World world = ev.getEntity().getWorld();
		final String worldName = world.getName();
		if ((world.hasMetadata("rpgraidworld") || disabledWorlds.contains(worldName)) && ev.getSpawnReason() != SpawnReason.CUSTOM && ev.getSpawnReason() != SpawnReason.MOUNT
				&& !(ev.getEntity() instanceof Player) && !ev.getEntity().hasMetadata("rpgspawned")) {
			ev.setCancelled(true);
			return;
		}
		if (!ev.getEntity().hasMetadata("rpgspawned") && !ev.getEntity().hasMetadata("untargetable")
				&& !ev.getEntity().hasMetadata("fake") && ev.getEntity() instanceof LivingEntity && ev.getSpawnReason() != SpawnReason.CUSTOM
				&& ev.getSpawnReason() != SpawnReason.RAID&& ev.getSpawnReason() != SpawnReason.SHOULDER_ENTITY
				&& ev.getSpawnReason() != SpawnReason.ENDER_PEARL) {

			final LivingEntity le = ev.getEntity();
			if(ev.getSpawnReason() == SpawnReason.SLIME_SPLIT) {
				if(le instanceof Slime || le instanceof MagmaCube) {
					le.setCustomName(le.getName().split("\\s\\(")[0]);
					return;
				}
			}
			if(ev.getSpawnReason() == SpawnReason.PIGLIN_ZOMBIFIED) {
				return;
			}
			le.setCustomName(null);
			if (ev.getEntityType() == EntityType.WITHER) {
				ev.getEntity().setMaxHealth(900000);
				ev.getEntity().setHealth(900000);
				ev.getEntity().setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), true));
				ev.getEntity().setMetadata("nether", new FixedMetadataValue(RMain.getInstance(), true));
				ev.getEntity().setMetadata("wither", new FixedMetadataValue(RMain.getInstance(), true));
			} else if (ev.getEntityType() == EntityType.WARDEN) {
				ev.getEntity().setMaxHealth(9999999);
				ev.getEntity().setHealth(9999999);
				ev.getEntity().setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), true));
				ev.getEntity().setMetadata("ender", new FixedMetadataValue(RMain.getInstance(), true));
				ev.getEntity().setMetadata("nether", new FixedMetadataValue(RMain.getInstance(), true));
				ev.getEntity().setMetadata("wither", new FixedMetadataValue(RMain.getInstance(), true));
				ev.getEntity().setMetadata("void", new FixedMetadataValue(RMain.getInstance(), true));
			}else if (ev.getEntityType() == EntityType.ENDER_DRAGON) {
				ev.getEntity().setMaxHealth(200000);
				ev.getEntity().setHealth(200000);
				ev.getEntity().setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), true));
				ev.getEntity().setMetadata("ender", new FixedMetadataValue(RMain.getInstance(), true));
				ev.getEntity().setMetadata("void", new FixedMetadataValue(RMain.getInstance(), true));
			} else if (ev.getEntityType() == EntityType.GIANT) {
				le.setAI(false);
				le.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE).setBaseValue(1);
				le.setCustomName("SandBag");
				ev.getEntity().setMaxHealth(9999999);
				ev.getEntity().setHealth(9999999);
				ev.getEntity().setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), true));
				ev.getEntity().setMetadata("sandbag", new FixedMetadataValue(RMain.getInstance(), true));
			} else {
				
				
            	final Biome b = le.getLocation().getBlock().getBiome();


    			Random random = new Random();
    			int ri = random.nextInt(200);
    			if (ri == 0) {
					String reg = lang.contains("kr") ? "보물돼지":"TreasurePig";
					LivingEntity newmob = mobs.Mobspawn(le, reg, 1d, null,	null, null,	null, null,	null, EntityType.PIG);
					newmob.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), true));
					newmob.setMetadata("treasurepig", new FixedMetadataValue(RMain.getInstance(), true));
    			} 

				PlainMobsSpawn.getInstance().Spawn(le,b);
				MountainsMobsSpawn.getInstance().Spawn(le,b);
				SnowMobsSpawn.getInstance().Spawn(le,b);
				OceanMobsSpawn.getInstance().Spawn(le,b);
				DarkMobsSpawn.getInstance().Spawn(le,b);
				HyperMobsSpawn.getInstance().Spawn(le,b);
				RedMobsSpawn.getInstance().Spawn(le,b);
				PoisonMobsSpawn.getInstance().Spawn(le,b);
				WildMobsSpawn.getInstance().Spawn(le,b);
				
				NetherMobsSpawn.getInstance().Spawn(le, b);
				
				
				if (b == Biome.THE_END
						&& le.getLocation().getWorld().getEnvironment() == Environment.THE_END) {
					LivingEntity newmob = mobs.Mobspawn(le, trans(le), 80000.0, le.getEquipment().getHelmet(),
							le.getEquipment().getChestplate(), le.getEquipment().getLeggings(),
							le.getEquipment().getBoots(), le.getEquipment().getItemInMainHand(),
							le.getEquipment().getItemInOffHand(), le.getType());
					newmob.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), true));
					newmob.setMetadata("ender", new FixedMetadataValue(RMain.getInstance(), true));
				} else if (b != Biome.THE_END
						&& le.getLocation().getWorld().getEnvironment() == Environment.THE_END) {
					String reg = lang.contains("kr") ? "공허":"Void";
					LivingEntity newmob = mobs.Mobspawn(le, reg + trans(le), 80000.0, le.getEquipment().getHelmet(),
							le.getEquipment().getChestplate(), le.getEquipment().getLeggings(),
							le.getEquipment().getBoots(), le.getEquipment().getItemInMainHand(),
							le.getEquipment().getItemInOffHand(), le.getType());
					newmob.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), true));
					newmob.setMetadata("void", new FixedMetadataValue(RMain.getInstance(), true));
				}

			}
		}
	}


	@EventHandler
	public void VilClickCan(PlayerInteractAtEntityEvent ev) {
		if (ev.getRightClicked().hasMetadata("raid") || ev.getRightClicked().hasMetadata("raidvil")) {
			ev.setCancelled(true);
		}
	}


	@EventHandler
	public void Transform(EntityTransformEvent ev) {
		Entity tfe = ev.getTransformedEntity();
		if(tfe.hasMetadata("rpgspawned") && ev.getTransformReason() == TransformReason.PIGLIN_ZOMBIFIED) {
			LivingEntity le = (LivingEntity) ev.getTransformedEntity();
			LivingEntity nle = (LivingEntity) ev.getEntity();

			String reg = le.getCustomName();
			if(reg.contains("피글린")) {
				reg = reg.substring(0, reg.indexOf("피글린"));
			}
			else if(reg.contains("호글린")) {
				reg = reg.substring(0, reg.indexOf("호글린"));
			}
			else if(reg.contains("Piglin")) {
				reg = reg.substring(0, reg.indexOf("Piglin"));
			}
			else if(reg.contains("Hoglin")) {
				reg = reg.substring(0, reg.indexOf("Hoglin"));
			}
			
			LivingEntity newmob = Mobspawn(nle, reg + trans(nle), le.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue(),
					le.getEquipment().getHelmet(), le.getEquipment().getChestplate(),
					le.getEquipment().getLeggings(), le.getEquipment().getBoots(),
					le.getEquipment().getItemInMainHand(), le.getEquipment().getItemInOffHand(),
					nle.getType());
			if(!tfe.getMetadata("rpgspawned").get(0).asString().equals("true")) {
				newmob.setMetadata("rpgspawned", new FixedMetadataValue(RMain.getInstance(), tfe.getMetadata("rpgspawned").get(0).asString()));
				newmob.setMetadata("unmodified", new FixedMetadataValue(RMain.getInstance(), true));
			}
		}
	}

	@EventHandler
	public void SlimeSplit(SlimeSplitEvent ev) {
		PoisonSkills.getInstance().slimesplit(ev);
	}



	@EventHandler	
	public void Join(PlayerJoinEvent ev) 
	{

	}

	@EventHandler	
	public void er(PluginEnableEvent ev) 
	{
	}
	

	@EventHandler
	public void Click(PlayerInteractEvent d) 
	{	
	}

	@EventHandler
	public void Attacked(EntityDamageEvent d) 
	{

		HyperSkills.getInstance().JetPack(d);
		RedSkills.getInstance().BackStab(d);
	}
	@EventHandler
	public void Attack(EntityDamageByEntityEvent d) 
	{
		Summoned.getInstance().SummonedDamage(d);;

		PlainRaids.getInstance().DamagerSave(d);
		HyperRaids.getInstance().DamagerSave(d);
		RedRaids.getInstance().DamagerSave(d);
		SnowRaids.getInstance().DamagerSave(d);
		MountainsRaids.getInstance().DamagerSave(d);
		DarkRaids.getInstance().DamagerSave(d);
		OceanRaids.getInstance().DamagerSave(d);
		PoisonRaids.getInstance().DamagerSave(d);
		WildRaids.getInstance().DamagerSave(d);

		MountainsSkills.getInstance().GolemSmash(d);
		MountainsSkills.getInstance().IronBody(d);
		MountainsSkills.getInstance().Drop(d);
		MountainsSkills.getInstance().EarthQuake(d);
		MountainsSkills.getInstance().EarthBlock(d);
		MountainsSkills.getInstance().mantle(d);
		MountainsSkills.getInstance().mCounter(d);
		

		PlainSkills.getInstance().Burrow(d);
		PlainSkills.getInstance().StoneThrow(d);

		RedSkills.getInstance().Ordeal(d);
		RedSkills.getInstance().Daze(d);
		RedSkills.getInstance().Ring(d);
		RedSkills.getInstance().Fire(d);
		RedSkills.getInstance().Eruption(d);
		RedSkills.getInstance().BackStab(d);
		RedSkills.getInstance().Charge(d);
		
		DarkSkills.getInstance().darkness(d);
		DarkSkills.getInstance().witherskullthrow(d);
		DarkSkills.getInstance().reapingHook(d);
		DarkSkills.getInstance().ReapingHook(d);
		DarkSkills.getInstance().cage(d);
		DarkSkills.getInstance().darkcircle(d);
		DarkSkills.getInstance().nightMare(d);
		DarkSkills.getInstance().nightCounter(d);
		
		

		SnowSkills.getInstance().Freeze(d);
		SnowSkills.getInstance().Breeze(d);
		SnowSkills.getInstance().Cyclone(d);
		SnowSkills.getInstance().SnowBlock(d);
		SnowSkills.getInstance().Mirror(d);
		SnowSkills.getInstance().mirrored(d);
		SnowSkills.getInstance().Icefall(d);
		SnowSkills.getInstance().Meteor(d);

		OceanSkills.getInstance().Mimic(d);
		OceanSkills.getInstance().Spikes(d);
		OceanSkills.getInstance().markHit(d);
		OceanSkills.getInstance().Riptide(d);
		OceanSkills.getInstance().Shoot(d);
		OceanSkills.getInstance().Tridents(d);
		OceanSkills.getInstance().Curse(d);
		
		HyperSkills.getInstance().SecurityRobot(d);
		HyperSkills.getInstance().Scraching(d);
		HyperSkills.getInstance().Charging(d);
		HyperSkills.getInstance().Throwing(d);
		HyperSkills.getInstance().EnergyBall(d);
		HyperSkills.getInstance().GravityShift(d);
		HyperSkills.getInstance().JetPack(d);
		HyperSkills.getInstance().Ordeal(d);

		PoisonSkills.getInstance().drug(d);
		PoisonSkills.getInstance().poisoness(d);
		PoisonSkills.getInstance().motovthrow(d);
		PoisonSkills.getInstance().charge(d);
		PoisonSkills.getInstance().napalm(d);
		PoisonSkills.getInstance().Ordeal(d);
		PoisonSkills.getInstance().multiShot(d);
		PoisonSkills.getInstance().aiming(d);
		PoisonSkills.getInstance().playerSuccess(d);

	}


	@EventHandler
	public void Regain(EntityRegainHealthEvent d)
	{
		MountainsSkills.getInstance().Smash(d);
	}
	
	@EventHandler
	public void PartyLeave(PartyLeaveEvent ev)
	{
		Summoned.getInstance().Escape(ev);
	}
	
	@EventHandler
	public void Death(PlayerDeathEvent ev)
	{
		Summoned.getInstance().Defeat(ev);
	}


	@EventHandler
	public void entitydeath(EntityDeathEvent d) 
	{
		
		Summoned.getInstance().Victory(d);
		

		PlainRaids.getInstance().PlainCombo(d);
		MountainsRaids.getInstance().MountainsCombo(d);
		OceanRaids.getInstance().OceanCombo(d);
		SnowRaids.getInstance().SnowCombo(d);
		DarkRaids.getInstance().DarkCombo(d);
		HyperRaids.getInstance().HyperCombo(d);
		RedRaids.getInstance().RedCombo(d);
		PoisonRaids.getInstance().PoisonCombo(d);
		WildRaids.getInstance().WildCombo(d);
		
		NetherRaids.getInstance().NetherCombo(d);
		
	
		RedSkills.getInstance().Fireball(d);
	}


	@EventHandler
	public void Tp(PlayerTeleportEvent d) 
	{
		
	}


	@EventHandler
	public void Respawn(PlayerRespawnEvent ev) 
	{
	}


	@EventHandler
	public void Quit(PlayerQuitEvent ev) 
	{
		
		Summoned.getInstance().Defeat(ev);
		
		
	}


	@EventHandler
	public void Off(PluginDisableEvent ev) 
	{
		Summoned.getInstance().deleter(ev);
	}


	@EventHandler
	public void Cast(EntitySpellCastEvent d) 
	{
		SnowSkills.getInstance().Breeze(d);
		
		RedSkills.getInstance().MageFB(d);

		DarkSkills.getInstance().Spell(d);
		
		HyperSkills.getInstance().RayCannon(d);
		
		PoisonSkills.getInstance().spell(d);
	}
	


	@EventHandler
	public void Launch(ProjectileLaunchEvent e) 
	{
		SnowSkills.getInstance().Potion(e);
		
		OceanSkills.getInstance().shulker(e);
		OceanSkills.getInstance().riptider(e);
		
		HyperSkills.getInstance().Granadier(e);
		HyperSkills.getInstance().RayBow(e);
		

		DarkSkills.getInstance().darkwitch(e);

		PoisonSkills.getInstance().launch(e);
	}
	@EventHandler
	public void Hit(ProjectileHitEvent e) 
	{
		PlainSkills.getInstance().StoneThrow(e);
		
		RedSkills.getInstance().HeadThrow(e);
		
		SnowSkills.getInstance().Potion(e);
		
		HyperSkills.getInstance().Granadier(e);

		DarkSkills.getInstance().witherskullthrow(e);
		
		MountainsSkills.getInstance().EarthQuake(e);

		PoisonSkills.getInstance().hit(e);
	}

	@EventHandler
	public void Bow(EntityShootBowEvent e) 
	{
		SnowSkills.getInstance().IceBow(e);

		HyperSkills.getInstance().RayBow(e);

		DarkSkills.getInstance().darkBow(e);

		PoisonSkills.getInstance().bowshoot(e);
		
		RedSkills.getInstance().Sweep(e);
	}
		

	@EventHandler
	public void Explode(EntityExplodeEvent ev) 
	{
		RedSkills.getInstance().Fireball(ev);
		DarkSkills.getInstance().witherskullthrow(ev);
	}

	@EventHandler
	public void ExplodePrime(ExplosionPrimeEvent d) 
	{
		PlainSkills.getInstance().Creeperboom(d);
		
		RedSkills.getInstance().HotCreep(d);
		
		DarkSkills.getInstance().darkcreep(d);
	}
	
	
	@EventHandler
	public void Fireworkex(FireworkExplodeEvent ev) 
	{
	}
	
	@EventHandler
	public void DropItem(EntityDropItemEvent ev) 
	{
		
		SnowSkills.getInstance().SnowBlock(ev);
		
		MountainsSkills.getInstance().EarthBlock(ev);
		
	}

	@EventHandler
	public void PickupItem(EntityPickupItemEvent ev) 
	{
		RedSkills.getInstance().Ordeal(ev);
		
	}

	@EventHandler
	public void BlockChanger(EntityChangeBlockEvent ev) 
	{
		SnowSkills.getInstance().SnowBlock(ev);

		MountainsSkills.getInstance().EarthBlock(ev);
	}


	@EventHandler
	public void Effect(EntityPotionEffectEvent d) 
	{

		SnowSkills.getInstance().Breeze(d);
	}

	@EventHandler
	public void Target(EntityTargetEvent d) 
	{

		Summoned.getInstance().SummonedTarget(d);
		
		RedSkills.getInstance().HeadThrow(d);
		RedSkills.getInstance().Jump(d);
		RedSkills.getInstance().Breath(d);

		SnowSkills.getInstance().Hunting(d);

		HyperSkills.getInstance().CreepJumper(d);
		HyperSkills.getInstance().Stalker(d);

		DarkSkills.getInstance().Fly(d);
		
		PoisonSkills.getInstance().poisoncreep(d);
		
		BloodNight.getInstance().SummonedTarget(d);
	}
	
	@EventHandler
	public void BlockPlace(BlockPlaceEvent d) 
	{
		if(d.getBlock().getWorld().getName().contains("FakeDimension") || d.getBlock().getWorld().getName().contains("Raid")) {
			d.setCancelled(true);
		}
	}

	@EventHandler
	public void BlockBreak(BlockBreakEvent d) 
	{
		if(d.getBlock().getWorld().getName().contains("FakeDimension") || d.getBlock().getWorld().getName().contains("Raid")) {
			d.setCancelled(true);
		}
	}
}