package io.github.chw3021.commons;



import java.io.Serializable;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityDropItemEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.entity.EntityPotionEffectEvent;
import org.bukkit.event.entity.EntityResurrectEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.EntityTargetEvent;
import org.bukkit.event.entity.EntityToggleSwimEvent;
import org.bukkit.event.entity.ExplosionPrimeEvent;
import org.bukkit.event.entity.FireworkExplodeEvent;
import org.bukkit.event.entity.HorseJumpEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.event.inventory.PrepareSmithingEvent;
import org.bukkit.event.player.PlayerArmorStandManipulateEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLocaleChangeEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerPickupArrowEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.event.player.PlayerRiptideEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.event.server.PluginDisableEvent;
import org.bukkit.event.server.PluginEnableEvent;
import org.bukkit.event.vehicle.VehicleExitEvent;
import io.github.chw3021.classes.angler.Angskills;
import io.github.chw3021.classes.archer.Archskills;
import io.github.chw3021.classes.berserker.Berskills;
import io.github.chw3021.classes.boxer.Boxskills;
import io.github.chw3021.classes.broiler.Broskills;
import io.github.chw3021.classes.chemist.Cheskills;
import io.github.chw3021.classes.cook.Cookskills;
import io.github.chw3021.classes.engineer.Engskills;
import io.github.chw3021.classes.firemage.Fireskills;
import io.github.chw3021.classes.forger.Forskills;
import io.github.chw3021.classes.frostman.Frostskills;
import io.github.chw3021.classes.hunter.Hunskills;
import io.github.chw3021.classes.illusionist.Illskills;
import io.github.chw3021.classes.launcher.Launskills;
import io.github.chw3021.classes.medic.Medskills;
import io.github.chw3021.classes.nobility.Nobskills;
import io.github.chw3021.classes.oceanknight.Oceskills;
import io.github.chw3021.classes.paladin.Palskills;
import io.github.chw3021.classes.sniper.Snipskills;
import io.github.chw3021.classes.swordman.Swordskills;
import io.github.chw3021.classes.tamer.Tamskills;
import io.github.chw3021.classes.taoist.Taoskills;
import io.github.chw3021.classes.witchdoctor.Wdcskills;
import io.github.chw3021.classes.witherist.Witskills;
import io.github.chw3021.items.weapons.Weapons;
import io.github.chw3021.monsters.raids.Summoned;

public class SkillUsing implements Listener, Serializable {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -3182008353111479606L;
	
	Pak pak = new Pak();
	Weapons w = new Weapons();


	@EventHandler
	public void skilluse(SkillUseEvent e) {

		CombatMode.getInstance().modeAlert(e);
	}
	
	@EventHandler
	public void Localechange(PlayerLocaleChangeEvent e)
	{
		CommonEvents.getInstance().classinv(e);
	}

	@EventHandler
	public void INvClick(InventoryClickEvent e)
	{
		pak.classinv(e);
		w.AttributeChange(e);
		
		CombatMode.getInstance().classinv(e);
		
		Angskills.getInstance().classinv(e);
		Archskills.getInstance().classinv(e);
		Berskills.getInstance().classinv(e);
		Boxskills.getInstance().classinv(e);
		Broskills.getInstance().classinv(e);
		Cheskills.getInstance().classinv(e);
		Cookskills.getInstance().classinv(e);
		Engskills.getInstance().classinv(e);
		Fireskills.getInstance().classinv(e);
		Forskills.getInstance().classinv(e);
		Frostskills.getInstance().classinv(e);
		Hunskills.getInstance().classinv(e);
		Illskills.getInstance().classinv(e);
		Launskills.getInstance().classinv(e);
		Medskills.getInstance().classinv(e);
		Oceskills.getInstance().classinv(e);
		Nobskills.getInstance().classinv(e);
		Palskills.getInstance().classinv(e);
		Snipskills.getInstance().classinv(e);
		Swordskills.getInstance().classinv(e);
		Tamskills.getInstance().classinv(e);
		Taoskills.getInstance().classinv(e);
		Wdcskills.getInstance().classinv(e);
		Witskills.getInstance().classinv(e);
	}

	@EventHandler	
	public void Join(PlayerJoinEvent ev) 
	{
		pak.nepreventer(ev);
		
		CommonEvents.getInstance().join(ev);
		
		Angskills.getInstance().nepreventer(ev);
		Archskills.getInstance().nepreventer(ev);
		Berskills.getInstance().nepreventer(ev);
		Boxskills.getInstance().nepreventer(ev);
		Broskills.getInstance().nepreventer(ev);
		Cheskills.getInstance().nepreventer(ev);
		Cookskills.getInstance().nepreventer(ev);
		Engskills.getInstance().nepreventer(ev);
		Fireskills.getInstance().nepreventer(ev);
		Forskills.getInstance().nepreventer(ev);
		Frostskills.getInstance().nepreventer(ev);
		Hunskills.getInstance().nepreventer(ev);
		Illskills.getInstance().nepreventer(ev);
		Launskills.getInstance().nepreventer(ev);
		Medskills.getInstance().nepreventer(ev);
		Oceskills.getInstance().nepreventer(ev);
		Nobskills.getInstance().nepreventer(ev);
		Palskills.getInstance().nepreventer(ev);
		Snipskills.getInstance().nepreventer(ev);
		Swordskills.getInstance().nepreventer(ev);
		Tamskills.getInstance().nepreventer(ev);
		Taoskills.getInstance().nepreventer(ev);
		Wdcskills.getInstance().nepreventer(ev);
		Witskills.getInstance().nepreventer(ev);
		
	}

	@EventHandler	
	public void er(PluginEnableEvent ev) 
	{
		pak.er(ev);
		
		CommonEvents.getInstance().er(ev);
		
		
		Angskills.getInstance().er(ev);
		Archskills.getInstance().er(ev);
		Berskills.getInstance().er(ev);
		Boxskills.getInstance().er(ev);
		Broskills.getInstance().er(ev);
		Cheskills.getInstance().er(ev);
		Cookskills.getInstance().er(ev);
		Engskills.getInstance().er(ev);
		Fireskills.getInstance().er(ev);
		Forskills.getInstance().er(ev);
		Frostskills.getInstance().er(ev);
		Hunskills.getInstance().er(ev);
		Illskills.getInstance().er(ev);
		Launskills.getInstance().er(ev);
		Medskills.getInstance().er(ev);
		Oceskills.getInstance().er(ev);
		Nobskills.getInstance().er(ev);
		Palskills.getInstance().er(ev);
		Snipskills.getInstance().er(ev);
		Swordskills.getInstance().er(ev);
		Tamskills.getInstance().er(ev);
		Taoskills.getInstance().er(ev);
		Wdcskills.getInstance().er(ev);
		Witskills.getInstance().er(ev);
	}
	
	
	
	
	@EventHandler
	public void Quit(PlayerQuitEvent ev) 
	{
		CombatMode.getInstance().nepreventer(ev);
		
		CommonEvents.getInstance().nepreventer(ev);
		
		
		Cheskills.getInstance().Cloud(ev);
		
		
	
		Medskills.getInstance().Stretcher(ev);
		
		Oceskills.getInstance().Shield(ev);
	
		Nobskills.getInstance().delete(ev);
	
		Tamskills.getInstance().delete(ev);
	
		Taoskills.getInstance().Aura(ev);
	
		Wdcskills.getInstance().delete(ev);
		
		Witskills.getInstance().Hover(ev);
	}

	@EventHandler
	public void Off(PluginDisableEvent ev) 
	{
		CombatMode.getInstance().deleter(ev);
		
		CommonEvents.getInstance().deleter(ev);
		
	}

	@EventHandler
	public void Wheel(PlayerItemHeldEvent ev) 
	{
		pak.DamageGetter(ev);
		
		Angskills.getInstance().ULT(ev);
		Angskills.getInstance().ULT2(ev);
		
		Broskills.getInstance().OneOnly(ev);
		
		Cheskills.getInstance().Cloud(ev);
		
		Forskills.getInstance().MachineGun(ev);

		Hunskills.getInstance().SuperJump(ev);

		Launskills.getInstance().ArrowChange(ev);

		Medskills.getInstance().Hideout(ev);

		Oceskills.getInstance().Shield(ev);

		Nobskills.getInstance().GuardianCurse(ev);
		Nobskills.getInstance().GuardianSupport(ev);

		Snipskills.getInstance().Camouflage(ev);
		
		Taoskills.getInstance().Aura(ev);
		
		Tamskills.getInstance().Recall(ev);
	}

	@EventHandler
	public void Swap(PlayerSwapHandItemsEvent ev) 
	{
		pak.DamageGetter(ev);
		
		Angskills.getInstance().CoralLiquor(ev);
		Angskills.getInstance().Ddash(ev);
		Angskills.getInstance().DrunkenDance(ev);
		Angskills.getInstance().DSmash(ev);
		Angskills.getInstance().Spout(ev);
		
		Archskills.getInstance().RapidFire(ev);
		Archskills.getInstance().Retrieve(ev);
		Archskills.getInstance().SpinShots(ev);
		

		Berskills.getInstance().Conversion(ev);
		Berskills.getInstance().Spray(ev);
		Berskills.getInstance().Squirt(ev);
		Berskills.getInstance().Rave(ev);

		Boxskills.getInstance().Adrenaline(ev);
		Boxskills.getInstance().DempseyRoll(ev);
		Boxskills.getInstance().DempseyRoll1(ev);
		Boxskills.getInstance().DempseyRoll2(ev);

		Broskills.getInstance().ChainHook(ev);
		Broskills.getInstance().ChainHookmove(ev);
		Broskills.getInstance().TimeBomb(ev);

		Cheskills.getInstance().GlowingBall(ev);
		Cheskills.getInstance().Hallucinogen(ev);
		Cheskills.getInstance().MagmaBall(ev);
		Cheskills.getInstance().Napalm(ev);
		Cheskills.getInstance().SlimeBall(ev);
		Cheskills.getInstance().WP(ev);

		Cookskills.getInstance().DessertRain(ev);
		Cookskills.getInstance().GrilledDishes(ev);

		Engskills.getInstance().Dispenser(ev);
		Engskills.getInstance().EnergyBall(ev);
		Engskills.getInstance().Factory(ev);
		Engskills.getInstance().Orbital(ev);
		Engskills.getInstance().Graviton(ev);
		Engskills.getInstance().Observer(ev);

		Fireskills.getInstance().AliveFlame(ev);
		Fireskills.getInstance().Breath(ev);
		Fireskills.getInstance().FireStrike(ev);
		Fireskills.getInstance().LavaBoom(ev);
		Fireskills.getInstance().LavaShower(ev);
		Fireskills.getInstance().SunLightSpear(ev);

		Forskills.getInstance().Compressor(ev);
		Forskills.getInstance().MachineGun(ev);
		Forskills.getInstance().PlazmaGrenade(ev);
		Forskills.getInstance().TNTLauncher(ev);

		Frostskills.getInstance().Avalanche(ev);
		Frostskills.getInstance().FrozenCrystal(ev);
		Frostskills.getInstance().FrozenCrystalbreak(ev);
		Frostskills.getInstance().ExtremeCold(ev);
		Frostskills.getInstance().Hailstones(ev);

		Hunskills.getInstance().Climb(ev);
		Hunskills.getInstance().Dodge(ev);

		Illskills.getInstance().Change(ev);
		Illskills.getInstance().FakeDoll(ev);
		Illskills.getInstance().JackoLantern(ev);
		Illskills.getInstance().Jugglling(ev);
		Illskills.getInstance().Magnify(ev);

		Launskills.getInstance().ArrowFountain(ev);
		Launskills.getInstance().ArrowRain(ev);
		Launskills.getInstance().Galaxy(ev);
		Launskills.getInstance().GiantArrow(ev);
		Launskills.getInstance().Meteor(ev);
		Launskills.getInstance().swcancle(ev);
		Launskills.getInstance().Nebula(ev);

		Medskills.getInstance().Barrier(ev);
		Medskills.getInstance().Decontamination(ev);
		Medskills.getInstance().HealingPump(ev);
		Medskills.getInstance().MassTreatment(ev);
		Medskills.getInstance().SupplyCart(ev);
		Medskills.getInstance().SupportFire(ev);
		Medskills.getInstance().swcancle(ev);

		Oceskills.getInstance().Backwash(ev);
		Oceskills.getInstance().Cleave(ev);
		Oceskills.getInstance().Diffraction(ev);
		Oceskills.getInstance().Flood(ev);
		Oceskills.getInstance().RipCurrent(ev);
		Oceskills.getInstance().TridentDrive(ev);
		Oceskills.getInstance().WaterBarrier(ev);
		Oceskills.getInstance().WaterSpear(ev);
		Oceskills.getInstance().WetSwing(ev);

		Nobskills.getInstance().Assault(ev);
		Nobskills.getInstance().HoldPosition(ev);
		Nobskills.getInstance().SprayAttack(ev);
		Nobskills.getInstance().EyesOfSea(ev);
		Nobskills.getInstance().Release(ev);
		Nobskills.getInstance().WaterWheel(ev);

		Palskills.getInstance().Aria(ev);
		Palskills.getInstance().Doom(ev);
		Palskills.getInstance().Encourage(ev);
		Palskills.getInstance().HolyPile(ev);
		Palskills.getInstance().HolySmash(ev);
		Palskills.getInstance().Illumination(ev);
		Palskills.getInstance().Pray(ev);
		Palskills.getInstance().Restraint(ev);
		Palskills.getInstance().Sanctuary(ev);
		Palskills.getInstance().Thrust(ev);

		Snipskills.getInstance().ArmourPiercingArrow(ev);
		Snipskills.getInstance().DangerClose(ev);
		Snipskills.getInstance().Destroyer(ev);
		Snipskills.getInstance().FlashBomb(ev);
		Snipskills.getInstance().ShockArrow(ev);
		Snipskills.getInstance().SmokeShell(ev);
		Snipskills.getInstance().swcancle(ev);

		Swordskills.getInstance().Spike(ev);
		Swordskills.getInstance().CriticalDraw(ev);
		Swordskills.getInstance().SoulFlourish(ev);
		Swordskills.getInstance().SwordDance(ev);
		Swordskills.getInstance().SwordDrive(ev);

		Tamskills.getInstance().Pets(ev);
		Tamskills.getInstance().PressTheAttack(ev);
		Tamskills.getInstance().Scratch(ev);

		Taoskills.getInstance().Ascension(ev);
		Taoskills.getInstance().Blast(ev);
		Taoskills.getInstance().Charm(ev);
		Taoskills.getInstance().EarthlyGrasp(ev);
		Taoskills.getInstance().Imagery(ev);
		Taoskills.getInstance().KarmaBreath(ev);

		Wdcskills.getInstance().Fangs(ev);
		Wdcskills.getInstance().FangsRush(ev);
		Wdcskills.getInstance().ZoglinCharge(ev);
		Wdcskills.getInstance().AstralProjection(ev);

		Witskills.getInstance().Demolition(ev);
		Witskills.getInstance().PurifierBeam(ev);
		Witskills.getInstance().Roses(ev);
		Witskills.getInstance().WhiteQuarts(ev);
		Witskills.getInstance().WitherBola(ev);
		Witskills.getInstance().WitherSkull(ev);
	}

	@EventHandler
	public void ClickEntity(PlayerArmorStandManipulateEvent ev) 
	{
		CommonEvents.getInstance().namingAndBarRemove(ev);
		
	}


	@EventHandler
	public void ClickEntity(PlayerInteractAtEntityEvent ev) 
	{
		CommonEvents.getInstance().namingAndBarRemove(ev);
	}
	@EventHandler
	public void ClickEntity(PlayerInteractEntityEvent ev) 
	{
		CommonEvents.getInstance().namingAndBarRemove(ev);
		Holding.getInstance().holded(ev);
		


		Medskills.getInstance().Stretcher(ev);
	}
	@EventHandler
	public void Click(PlayerInteractEvent ev) 
	{
		Angskills.getInstance().Whipping(ev);
		Angskills.getInstance().CoralRoots(ev);

		Archskills.getInstance().Indure(ev);
		Archskills.getInstance().SpreadingArrows(ev);

		Berskills.getInstance().Burstout(ev);
		Berskills.getInstance().CrimsonAdvance(ev);
		Berskills.getInstance().Flurry(ev);
		Berskills.getInstance().Smite(ev);
		Berskills.getInstance().Onslaught(ev);
		Berskills.getInstance().Inhale(ev);
		Berskills.getInstance().Swipe(ev);
		Berskills.getInstance().Scratch(ev);
		Berskills.getInstance().Merciless(ev);
		Berskills.getInstance().CrimpsonSlash(ev);

		Boxskills.getInstance().BodyBlow(ev);
		Boxskills.getInstance().EarthQuaker(ev);
		Boxskills.getInstance().Parrying(ev);
		Boxskills.getInstance().SkyCrasher(ev);
		Boxskills.getInstance().Straight(ev);
		Boxskills.getInstance().UnderHook(ev);
		Boxskills.getInstance().FistStorm(ev);
		Boxskills.getInstance().JabRush(ev);
		Boxskills.getInstance().FlickerJab(ev);
		Boxskills.getInstance().oneInchPunch(ev);

		Broskills.getInstance().CactusTrap(ev);
		Broskills.getInstance().Crack(ev);
		Broskills.getInstance().DustEyes(ev);

		Cheskills.getInstance().AcidCloud(ev);
		Cheskills.getInstance().Extraction(ev);
		Cheskills.getInstance().Charge(ev);
		Cheskills.getInstance().AcidBomb(ev);
		Cheskills.getInstance().AcidStorm(ev);
		Cheskills.getInstance().MolotovCocktail(ev);

		Cookskills.getInstance().BerrySalad(ev);
		Cookskills.getInstance().MushSpa(ev);
		Cookskills.getInstance().MelonWall(ev);

		Engskills.getInstance().Anti_gravity(ev);
		Engskills.getInstance().Electrostatic(ev);
		Engskills.getInstance().EMP(ev);
		Engskills.getInstance().Jetpack(ev);
		Engskills.getInstance().GravityShift(ev);
		Engskills.getInstance().Propellant(ev);
		Engskills.getInstance().ThunderCaller(ev);
		Engskills.getInstance().X_ray(ev);

		Fireskills.getInstance().Eruption(ev);
		Fireskills.getInstance().Fireball(ev);
		Fireskills.getInstance().FlowingLava(ev);
		Fireskills.getInstance().MagmaBlock(ev);
		Fireskills.getInstance().Ring(ev);
		Fireskills.getInstance().VolcanicStorm(ev);
		Fireskills.getInstance().SunClutch(ev);

		Forskills.getInstance().BeamWave(ev);
		Forskills.getInstance().Detonator(ev);
		Forskills.getInstance().HoneyMissile(ev);
		Forskills.getInstance().LightningCannon(ev);
		Forskills.getInstance().RailScrew(ev);
		Forskills.getInstance().RailSMG(ev);
		Forskills.getInstance().Spectral(ev);

		Frostskills.getInstance().IceBlades(ev);
		Frostskills.getInstance().Icefall(ev);
		Frostskills.getInstance().IceSpikes(ev);
		Frostskills.getInstance().GlacialDrift(ev);
		Frostskills.getInstance().IcicleShot(ev);
		Frostskills.getInstance().PolarVortex(ev);
		Frostskills.getInstance().SnowBall(ev);
		Frostskills.getInstance().SnowBreeze(ev);

		Hunskills.getInstance().HuntingStart(ev);
		Hunskills.getInstance().HuntingStart2(ev);
		Hunskills.getInstance().Webthrow(ev);
		Hunskills.getInstance().webRetrieving(ev);
		Hunskills.getInstance().swing(ev);

		Illskills.getInstance().Distortion(ev);
		Illskills.getInstance().Encore(ev);
		Illskills.getInstance().MindControl(ev);
		Illskills.getInstance().Paradox(ev);
		Illskills.getInstance().Penetration(ev);
		Illskills.getInstance().Shuffle(ev);
		Illskills.getInstance().Trick(ev);

		Launskills.getInstance().Discharge(ev);
		Launskills.getInstance().RocketHit(ev);
		Launskills.getInstance().ChargingShot(ev);

		Medskills.getInstance().AED(ev);
		Medskills.getInstance().FineNeedles(ev);
		Medskills.getInstance().RemedyingRocket(ev);
		Medskills.getInstance().UltrasonicNebulizer(ev);
		Medskills.getInstance().Stretcher(ev);

		Oceskills.getInstance().Javelin(ev);
		Oceskills.getInstance().Torrent(ev);

		Nobskills.getInstance().Dolphinleft(ev);
		Nobskills.getInstance().DolphinSurf(ev);
		Nobskills.getInstance().Owner(ev);
		Nobskills.getInstance().Storm(ev);

		Palskills.getInstance().Asperges(ev);
		Palskills.getInstance().Griffon(ev);
		Palskills.getInstance().Judgement(ev);
		Palskills.getInstance().Punish(ev);
		Palskills.getInstance().Protection(ev);

		Snipskills.getInstance().Rope(ev);
		Snipskills.getInstance().Flare(ev);

		Swordskills.getInstance().DividingAir(ev);
		Swordskills.getInstance().FallenLeaves(ev);
		Swordskills.getInstance().FlashyRush(ev);
		Swordskills.getInstance().RisingBlades(ev);
		Swordskills.getInstance().strike(ev);
		Swordskills.getInstance().strike2(ev);
		Swordskills.getInstance().strike3(ev);

		Tamskills.getInstance().BeeHive(ev);
		Tamskills.getInstance().Carry(ev);
		Tamskills.getInstance().CreepBomb(ev);
		Tamskills.getInstance().Disruption(ev);
		Tamskills.getInstance().Panda(ev);
		Tamskills.getInstance().Spidey(ev);
		Tamskills.getInstance().Stomp(ev);
		Tamskills.getInstance().WebSpread(ev);

		Taoskills.getInstance().Amplify(ev);
		Taoskills.getInstance().CharmSlash(ev);
		Taoskills.getInstance().Flip(ev);
		Taoskills.getInstance().Mantra(ev);
		Taoskills.getInstance().Serenity(ev);
		Taoskills.getInstance().Shunpo(ev);
		Taoskills.getInstance().SpiritStorm(ev);
		Taoskills.getInstance().Wave(ev);

		Wdcskills.getInstance().Harvest(ev);
		Wdcskills.getInstance().Incantation(ev);
		Wdcskills.getInstance().PhantomSwoop(ev);
		Wdcskills.getInstance().Sacrifice(ev);
		Wdcskills.getInstance().VengefulSpirit(ev);
		Wdcskills.getInstance().Wraith(ev);
		Wdcskills.getInstance().Bosou(ev);
		Wdcskills.getInstance().ForbiddenHex(ev);

		Witskills.getInstance().BlackSpin(ev);
		Witskills.getInstance().CrystalCage(ev);
		Witskills.getInstance().Curse(ev);
		Witskills.getInstance().Hover(ev);
		Witskills.getInstance().ReapingHook(ev);
	}
	
	
	@EventHandler
	public void Attack(EntityDamageByEntityEvent d) 
	{
		pak.DamageGetter(d);
		pak.Supporter(d);
		
		CommonEvents.getInstance().Thorndamcan(d);
		if(d.getCause() == DamageCause.THORNS) {
			return;
		}
		CommonEvents.getInstance().Villdamcan(d);
		CommonEvents.getInstance().leftClickTrigger(d);
		
		
		
		Angskills.getInstance().Technic(d);
		
		Archskills.getInstance().HookAndShot(d);
		Archskills.getInstance().Archery(d);
		

		Berskills.getInstance().Lunacy(d);

		Boxskills.getInstance().Counter(d);
		Boxskills.getInstance().CounterStack(d);
		Boxskills.getInstance().Training(d);
		Boxskills.getInstance().Weaving(d);
		Boxskills.getInstance().Parrying(d);

		Broskills.getInstance().CactusTrap(d);
		Broskills.getInstance().ChainHook(d);
		Broskills.getInstance().GlassBreak(d);
		Broskills.getInstance().OneOnly(d);

		Cheskills.getInstance().Poisonous(d);

		Cookskills.getInstance().Cookery(d);
		Cookskills.getInstance().Saturation(d);

		Engskills.getInstance().CombatSuit(d);
		Engskills.getInstance().Magnetic(d);
		Engskills.getInstance().X_ray(d);
		
		Fireskills.getInstance().HotBody(d);
		Fireskills.getInstance().HotBodyre(d);
		Fireskills.getInstance().MagmaBlock(d);
		Fireskills.getInstance().Spread(d);

		Forskills.getInstance().ArmorDecrease(d);
		Forskills.getInstance().Barrier(d);
		Forskills.getInstance().Damagegetter(d);
		Forskills.getInstance().HESH(d);
		Forskills.getInstance().Shockwave(d);

		Frostskills.getInstance().Crack(d);
		Frostskills.getInstance().Frostbite(d);
		Frostskills.getInstance().GlacialDrift(d);

		Hunskills.getInstance().ArmorDecrease(d);
		Hunskills.getInstance().Huntingdamage(d);
		Hunskills.getInstance().Daze(d);
		Hunskills.getInstance().Ult(d);
		Hunskills.getInstance().SkullCrusher(d);
		Hunskills.getInstance().Backattack(d);
		Hunskills.getInstance().Damagegetter(d);
		Hunskills.getInstance().Atrocity(d);

		Illskills.getInstance().ArmorDecrease(d);
		Illskills.getInstance().Damagegetter(d);
		Illskills.getInstance().FakeDimensionDoll(d);
		Illskills.getInstance().Switch(d);
		Illskills.getInstance().Magnify(d);
		Illskills.getInstance().Hypnosis(d);

		Launskills.getInstance().ArrowChange(d);
		Launskills.getInstance().Damagegetter(d);
		Launskills.getInstance().Fireworkdischarge(d);
		Launskills.getInstance().Meteor(d);

		Medskills.getInstance().Damagegetter(d);
		Medskills.getInstance().Grogi(d);
		Medskills.getInstance().RemedyingRocket(d);

		Oceskills.getInstance().Impale(d);
		Oceskills.getInstance().ShieldSmite(d);
		Oceskills.getInstance().Splash(d);
		Oceskills.getInstance().TripleHit(d);

		Nobskills.getInstance().Damagegetter(d);
		Nobskills.getInstance().GuardianSupport(d);
		Nobskills.getInstance().GuardianSupport2(d);
		Nobskills.getInstance().MarkOfSea(d);
		Nobskills.getInstance().ArmorDecrease(d);
		Nobskills.getInstance().ULT1(d);

		Palskills.getInstance().Faith(d);
		Palskills.getInstance().Protection(d);
		Palskills.getInstance().Punish(d);

		Snipskills.getInstance().AirStrike(d);
		Snipskills.getInstance().ArmorDecrease(d);
		Snipskills.getInstance().Damagegetter(d);
		Snipskills.getInstance().Fireworkairstrike(d);
		Snipskills.getInstance().HeadShot(d);
		Snipskills.getInstance().DangerClose(d);

		Swordskills.getInstance().Swoop(d);
		Swordskills.getInstance().Dualbladed(d);

		Tamskills.getInstance().ArmorDecrease(d);
		Tamskills.getInstance().DamageCancel(d);
		Tamskills.getInstance().Damagegetter(d);

		Taoskills.getInstance().CombustInside(d);
		Taoskills.getInstance().Aura(d);
		Taoskills.getInstance().KiVibe(d);
		
		Witskills.getInstance().ReapingHook(d);
		Witskills.getInstance().WitherScythe(d);
		Witskills.getInstance().Witherize(d);
		Witskills.getInstance().Witherize2(d);

		Wdcskills.getInstance().Fangs(d);
		Wdcskills.getInstance().ult2(d);
		Wdcskills.getInstance().Legba(d);

	}

	@EventHandler
	public void Attacked(EntityDamageEvent d) 
	{
		CommonEvents.getInstance().WallDamagecan(d);
		
		
		
		Angskills.getInstance().Drink(d);
		
		Archskills.getInstance().Indure(d);
		
		Berskills.getInstance().Undying(d);

		Boxskills.getInstance().Adrenaline(d);

		Cheskills.getInstance().Poisonous(d);

		Engskills.getInstance().Graviton(d);
		Engskills.getInstance().Jetpack(d);

		Fireskills.getInstance().HotBody(d);
		
		Frostskills.getInstance().CoolBody(d);

		Hunskills.getInstance().Dodge(d);

		Medskills.getInstance().Grogi(d);
		Medskills.getInstance().Grogi2(d);

		Oceskills.getInstance().Splash(d);
		Oceskills.getInstance().WaterBarrier(d);

		Palskills.getInstance().Protection(d);
		
		Swordskills.getInstance().guard1(d);

		Tamskills.getInstance().ArmorIncrease(d);

		Taoskills.getInstance().Aura(d);

		Wdcskills.getInstance().Legba2(d);
		Wdcskills.getInstance().Resurrect(d);
		Wdcskills.getInstance().ULT(d);

		Witskills.getInstance().Witherized(d);
	}
	
	@EventHandler
	public void Throw(PlayerDropItemEvent ev)        
    {
		CombatMode.getInstance().modeChange(ev);
		
		Angskills.getInstance().ThrowCancel(ev);
		
		Archskills.getInstance().ULT(ev);
		Archskills.getInstance().ULT2(ev);
		Archskills.getInstance().ThrowCancel(ev);

		Berskills.getInstance().ULT(ev);
		Berskills.getInstance().ULT2(ev);
		Berskills.getInstance().ThrowCancel(ev);

		Boxskills.getInstance().ULT(ev);
		Boxskills.getInstance().ULT2(ev);
		Boxskills.getInstance().ThrowCancel(ev);

		Broskills.getInstance().ULT(ev);

		Cheskills.getInstance().ULT(ev);
		Cheskills.getInstance().ULT2(ev);
		Cheskills.getInstance().ThrowCancel(ev);

		Cookskills.getInstance().ULT(ev);

		Engskills.getInstance().ULT(ev);
		Engskills.getInstance().ULT2(ev);
		Engskills.getInstance().ThrowCancel(ev);

		Fireskills.getInstance().ULT(ev);
		Fireskills.getInstance().ULT2(ev);
		Fireskills.getInstance().ThrowCancel(ev);

		Forskills.getInstance().ULT(ev);
		Forskills.getInstance().ULT2(ev);
		Forskills.getInstance().ThrowCancel(ev);

		Frostskills.getInstance().ULT(ev);
		Frostskills.getInstance().ULT2(ev);
		Frostskills.getInstance().ThrowCancel(ev);

		Hunskills.getInstance().ULT(ev);
		Hunskills.getInstance().ULT2(ev);
		Hunskills.getInstance().ThrowCancel(ev);

		Illskills.getInstance().ULT(ev);
		Illskills.getInstance().ULT2(ev);
		Illskills.getInstance().ThrowCancel(ev);

		Launskills.getInstance().ULT(ev);
		Launskills.getInstance().ULT2(ev);
		Launskills.getInstance().ThrowCancel(ev);

		Medskills.getInstance().ULT(ev);
		Medskills.getInstance().ULT2(ev);
		Medskills.getInstance().ThrowCancel(ev);

		Oceskills.getInstance().ULT(ev);
		Oceskills.getInstance().ULT2(ev);
		Oceskills.getInstance().ThrowCancel(ev);

		Nobskills.getInstance().ULT(ev);
		Nobskills.getInstance().ULT2(ev);
		Nobskills.getInstance().ThrowCancel(ev);

		Palskills.getInstance().ULT(ev);
		Palskills.getInstance().ULT2(ev);
		Palskills.getInstance().ThrowCancel(ev);

		Snipskills.getInstance().ULT(ev);
		Snipskills.getInstance().ULT2(ev);
		Snipskills.getInstance().ThrowCancel(ev);

		Swordskills.getInstance().ULT(ev);
		Swordskills.getInstance().ULT2(ev);
		Swordskills.getInstance().ThrowCancel(ev);

		Tamskills.getInstance().ULT(ev);
		Tamskills.getInstance().Smash(ev);
		Tamskills.getInstance().ULT2(ev);
		Tamskills.getInstance().ThrowCancel(ev);

		Taoskills.getInstance().ULT(ev);
		Taoskills.getInstance().ULT2(ev);
		Taoskills.getInstance().ThrowCancel(ev);

		Wdcskills.getInstance().ULT(ev);
		Wdcskills.getInstance().ULT2(ev);
		Wdcskills.getInstance().ThrowCancel(ev);

		Witskills.getInstance().ULT(ev);
		Witskills.getInstance().ULT2(ev);
		Witskills.getInstance().ThrowCancel(ev);
		
		CombatMode.getInstance().modeChange(ev);
		Summoned.getInstance().ThrowCancel(ev);
    }


	@EventHandler
	public void Launch(ProjectileLaunchEvent e) 
	{
		Angskills.getInstance().Bait(e);

		Nobskills.getInstance().Owner(e);
		Nobskills.getInstance().Transition(e);
	}



	@EventHandler
	public void Bow(EntityShootBowEvent e) 
	{
		Archskills.getInstance().TripleShot(e);
		Archskills.getInstance().MultiShot(e);
		
		Launskills.getInstance().Explosion(e);

		Medskills.getInstance().RemedyingRocket(e);

		Snipskills.getInstance().Remodeling(e);
		
	}
	
	@EventHandler
	public void PrHit(ProjectileHitEvent e) 
	{
		/*if(e.getEntity().hasMetadata("dragonhunt") && !(e.getHitEntity() instanceof EnderDragon) && !(e.getHitEntity() instanceof EnderDragonPart)) {
			e.setCancelled(true);
			return;
		}*/
		
		CommonEvents.getInstance().Bullet(e);
		Holding.getInstance().holded(e);
		
		
		Archskills.getInstance().EnderWitherHunter(e);
		Archskills.getInstance().XXX(e);
		
		Broskills.getInstance().Crack(e);

		Cheskills.getInstance().Throw(e);

		Cookskills.getInstance().BerrySalad(e);

		Engskills.getInstance().EMP(e);

		Fireskills.getInstance().Fireball(e);
		
		Forskills.getInstance().PlazmaGrenade(e);
		Forskills.getInstance().MachineGun(e);

		Frostskills.getInstance().SnowBall(e);

		Hunskills.getInstance().Webthrow(e);

		Launskills.getInstance().Comet(e);
		Launskills.getInstance().EnderArrow(e);
		Launskills.getInstance().EnderWitherHunter(e);
		Launskills.getInstance().Explosion(e);
		Launskills.getInstance().ULT(e);
		Launskills.getInstance().ChargingShot(e);

		Medskills.getInstance().ArrowClinic(e);
		Medskills.getInstance().Throw(e);

		Oceskills.getInstance().Crisp(e);

		Nobskills.getInstance().Transition(e);

		Snipskills.getInstance().APA(e);
		Snipskills.getInstance().ShockArrow(e);
		Snipskills.getInstance().WitherHunter(e);

		Tamskills.getInstance().PressTheAttack(e);

		Taoskills.getInstance().Flip(e);

		Witskills.getInstance().WitherSkull(e);
		Witskills.getInstance().WhiteQuarts(e);
		Witskills.getInstance().WitherBola(e);
	}

	@EventHandler
	public void PotionEffectApply(EntityPotionEffectEvent d) 
	{
		pak.DamageGetter(d);
		
		Frostskills.getInstance().Frostbite(d);

		Hunskills.getInstance().Hunger(d);

		Tamskills.getInstance().ArmorIncrease(d);
		
	}

	@EventHandler
	public void Sneak(PlayerToggleSneakEvent e) 
	{
		Boxskills.getInstance().Rest(e);

		Hunskills.getInstance().SuperJump(e);

		Medskills.getInstance().Hideout(e);

		Snipskills.getInstance().Camouflage(e);
		
		Wdcskills.getInstance().AstralProjection(e);
	}

	@EventHandler
	public void FrExplode(FireworkExplodeEvent f) 
	{
		Forskills.getInstance().HESH(f);

		Launskills.getInstance().Fireworkdischarge(f);

		Medskills.getInstance().RemedyingRocket(f);

		Snipskills.getInstance().Destroyer(f);
		Snipskills.getInstance().FireworkExplode(f);
	}

	@EventHandler
	public void PrimeExplode(ExplosionPrimeEvent d) 
	{
		Cheskills.getInstance().Nuke(d);

		Tamskills.getInstance().Creepey(d);
	}
		

	@EventHandler
	public void Explode(EntityExplodeEvent ev) 
	{
		Tamskills.getInstance().TamedDragon(ev);
		
		Fireskills.getInstance().Fireball(ev);

		Witskills.getInstance().WitherSkull(ev);
		
	}

	@EventHandler
	public void DropItem(EntityDropItemEvent ev) 
	{
		Fireskills.getInstance().MagmaBlock(ev);
		
		Frostskills.getInstance().GlacialDrift(ev);
		
		Illskills.getInstance().Magnify(ev);

		Launskills.getInstance().Meteor(ev);

		Nobskills.getInstance().Owner(ev);
		Nobskills.getInstance().ULT1(ev);

		Snipskills.getInstance().DangerClose(ev);
		
	}

	@EventHandler
	public void BlockChanger(EntityChangeBlockEvent ev) 
	{
		Fireskills.getInstance().MagmaBlock(ev);
		
		Frostskills.getInstance().GlacialDrift(ev);
		
		Illskills.getInstance().Magnify(ev);

		Launskills.getInstance().Meteor(ev);
		
		Nobskills.getInstance().ULT1(ev);

		Snipskills.getInstance().DangerClose(ev);
	
		Witskills.getInstance().Roses(ev);
		
		Tamskills.getInstance().TamedDragon(ev);
	}

	@EventHandler
	public void Death(PlayerDeathEvent ev)
	{
		CommonEvents.getInstance().delete(ev);
		
		
		Broskills.getInstance().OneOnly(ev);
		
		Cheskills.getInstance().Cloud(ev);
		
		Medskills.getInstance().Stretcher(ev);

		Nobskills.getInstance().Owner(ev);
		Nobskills.getInstance().delete(ev);

		Tamskills.getInstance().delete(ev);

		Taoskills.getInstance().Aura(ev);

		Wdcskills.getInstance().delete(ev);
		
		Witskills.getInstance().Hover(ev);
	}


	

	@EventHandler
	public void Respawn(PlayerRespawnEvent ev) 
	{
		pak.DamageGetter(ev);
		
		CommonEvents.getInstance().dinremove(ev);
		
		
		Nobskills.getInstance().Owner(ev);
		
	}
	
	@EventHandler
	public void PickupArrow(PlayerPickupArrowEvent ev) 
	{
		Oceskills.getInstance().Javelin(ev);
		
		Nobskills.getInstance().Owner(ev);
		
	}

	@EventHandler
	public void PickupItem(EntityPickupItemEvent ev) 
	{
		w.AttributeChange(ev);
		
		Nobskills.getInstance().Owner(ev);
		
		Berskills.getInstance().Vampire(ev);
	}
	
	@EventHandler
	public void Equipment(PlayerItemDamageEvent e) 
	{
		Angskills.getInstance().Equipment(e);
		Archskills.getInstance().Equipment(e);
		Berskills.getInstance().Equipment(e);
		Boxskills.getInstance().Equipment(e);
		Broskills.getInstance().Equipment(e);
		Cheskills.getInstance().Equipment(e);
		Cookskills.getInstance().Equipment(e);
		Engskills.getInstance().Equipment(e);
		Fireskills.getInstance().Equipment(e);
		Forskills.getInstance().Equipment(e);
		Frostskills.getInstance().Equipment(e);
		Hunskills.getInstance().Equipment(e);
		Illskills.getInstance().Equipment(e);
		Launskills.getInstance().Equipment(e);
		Medskills.getInstance().Equipment(e);
		Oceskills.getInstance().Equipment(e);
		Nobskills.getInstance().Equipment(e);
		Palskills.getInstance().Equipment(e);
		Snipskills.getInstance().Equipment(e);
		Swordskills.getInstance().Equipment(e);
		Tamskills.getInstance().Equipment(e);
		Taoskills.getInstance().Equipment(e);
		Wdcskills.getInstance().Equipment(e);
		Witskills.getInstance().Equipment(e);
	}


	@EventHandler
	public void HorseJump(HorseJumpEvent ev) 
	{
		Palskills.getInstance().Griffon(ev);
	}


	
	@EventHandler
	public void Exit(VehicleExitEvent ev) 
	{
		Medskills.getInstance().Stretcher(ev);
		
		Palskills.getInstance().Griffon(ev);
		
	}

	@EventHandler
	public void entitydeath(EntityDeathEvent d) 
	{
		Tamskills.getInstance().delete(d);

		Wdcskills.getInstance().delete(d);
		Wdcskills.getInstance().Zombies(d);
		Wdcskills.getInstance().Incantation(d);
	}
	

	@EventHandler
	public void Target(EntityTargetEvent d) 
	{
		Tamskills.getInstance().Target(d);
	}

	@EventHandler
	public void Tp(PlayerTeleportEvent d) 
	{
		CommonEvents.getInstance().delete(d);
		CommonEvents.getInstance().Teleport(d);
		
		Holding.getInstance().holded(d);
		
		Tamskills.getInstance().delete(d);

		Wdcskills.getInstance().delete(d);
	}

	@EventHandler
	public void Move(PlayerMoveEvent ev) {
		
		Hunskills.getInstance().Climb(ev);
		Holding.getInstance().holded(ev);
		//CommonEvents.getInstance().ChangeBiome(ev);
	}

	@EventHandler
	public void Resr(EntityResurrectEvent d) 
	{
		Wdcskills.getInstance().Resurrect(d);
	}
	
	
	@EventHandler
	public void Inhancer(PlayerItemConsumeEvent d) 
	{
		Medskills.getInstance().Inhancer(d);
	}

	@EventHandler
	public void Fishing(PlayerFishEvent d) 
	{
		Angskills.getInstance().Fishing(d);
	}
	@EventHandler
	public void Riptide(PlayerRiptideEvent e) 
	{
		Oceskills.getInstance().Shield(e);
	}

	@EventHandler
	public void Swimer(EntityToggleSwimEvent ev) 
	{
		pak.Swimer(ev);
	}
	@EventHandler
	public void av(PrepareAnvilEvent ev) 
	{
		w.AttributeChange(ev);
	}
	@EventHandler
	public void sm(PrepareSmithingEvent ev) 
	{
		w.AttributeChange(ev);
	}
	@EventHandler
	public void Close(InventoryCloseEvent ev) 
	{
		pak.DamageGetter(ev);
	}
	@EventHandler
	public void arm(PlayerArmorStandManipulateEvent ev) 
	{
		pak.DamageGetter(ev);
	}
}



