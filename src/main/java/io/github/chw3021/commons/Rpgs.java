package io.github.chw3021.commons;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashSet;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Registry;
import org.bukkit.attribute.Attribute;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerExpChangeEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.google.common.collect.HashBasedTable;
import io.github.chw3021.classes.ClassData;
import io.github.chw3021.classes.Classgui;
import io.github.chw3021.classes.Proficiency;
import io.github.chw3021.classes.angler.AngSkillsGui;
import io.github.chw3021.classes.archer.ArchSkillsGui;
import io.github.chw3021.classes.berserker.BerSkillsGui;
import io.github.chw3021.classes.boxer.BoxSkillsGui;
import io.github.chw3021.classes.broiler.BroSkillsGui;
import io.github.chw3021.classes.chemist.CheSkillsGui;
import io.github.chw3021.classes.cook.CookSkillsGui;
import io.github.chw3021.classes.engineer.EngSkillsGui;
import io.github.chw3021.classes.firemage.FireSkillsGui;
import io.github.chw3021.classes.forger.ForSkillsGui;
import io.github.chw3021.classes.frostman.FrostSkillsGui;
import io.github.chw3021.classes.hunter.HunSkillsGui;
import io.github.chw3021.classes.illusionist.IllSkillsGui;
import io.github.chw3021.classes.launcher.LaunSkillsGui;
import io.github.chw3021.classes.medic.MedSkillsGui;
import io.github.chw3021.classes.nobility.NobSkillsGui;
import io.github.chw3021.classes.oceanknight.OceSkillsGui;
import io.github.chw3021.classes.paladin.PalSkillsGui;
import io.github.chw3021.classes.sniper.SnipSkillsGui;
import io.github.chw3021.classes.swordman.SwordSkillsGui;
import io.github.chw3021.classes.tamer.TamSkillsGui;
import io.github.chw3021.classes.taoist.TaoSkillsGui;
import io.github.chw3021.classes.witchdoctor.WdcSkillsGui;
import io.github.chw3021.classes.witherist.WitSkillsGui;
import io.github.chw3021.classes.wreltler.WreSkillsGui;
import io.github.chw3021.items.Elements;
import io.github.chw3021.items.armors.Armors;
import io.github.chw3021.items.armors.Boots;
import io.github.chw3021.items.armors.Chestplate;
import io.github.chw3021.items.armors.Helmet;
import io.github.chw3021.items.armors.Leggings;
import io.github.chw3021.items.weapons.Weapons;
import io.github.chw3021.monsters.raids.NethercoreRaids;
import io.github.chw3021.monsters.raids.OverworldRaids;
import io.github.chw3021.monsters.raids.Summoned;
import net.md_5.bungee.api.ChatColor;

public class Rpgs extends Summoned implements CommandExecutor, Listener {
	

	private static HashBasedTable<UUID, String, Double> damagedsdam = HashBasedTable.create();
	private static HashBasedTable<UUID, String, Integer> damagedscount = HashBasedTable.create();
	

	final private Integer getkillcount(Player p, String len) {
		if(damagedscount.contains(p.getUniqueId(), len)) {
			return damagedscount.get(p.getUniqueId(), len);
		}
		else {
			return 0;
		}
	}
	
	@EventHandler
	public void Damagegetter(EntityDeathEvent d) 
	{
		LivingEntity le = (LivingEntity) d.getEntity();
		String len = ChatColor.stripColor(le.getName().split("\\s\\(")[0]);
		if(!damagedsdam.containsColumn(len)) {
			return;
		}
		damagedsdam.column(len).forEach((k,v)->{
			if(damagedscount.containsColumn(len)) {
				damagedscount.put(k, len, damagedscount.get(k, len)+1);
			}
			else {
				damagedscount.put(k, len, 1);
			}
		});
	}

	NethercoreRaids ncr = NethercoreRaids.getInstance();
	
	OverworldRaids owr = OverworldRaids.getInstance();
	private void bossTest(String args, Player p) {

		Integer input = Integer.parseInt(args);
		String rn = getheroname(p);
		if(input<0) {
			NethercoreRaids.beforepl.put(p.getUniqueId(), p.getLocation());
			NethercoreRaids.heroes.put(rn, p.getUniqueId());
			NethercoreRaids.raidloc.put(rn, p.getLocation());
			ncr.bossnum.put(rn, input);
			ncr.difen.put(rn, 0);
			ncr.language.put(rn, p.getLocale());
			ncr.bossgen(p.getLocation(), p, rn, input, ncr.BOSSHP);
			
		}
		else {
			OverworldRaids.beforepl.put(p.getUniqueId(), p.getLocation());
			OverworldRaids.heroes.put(rn, p.getUniqueId());
			OverworldRaids.raidloc.put(rn, p.getLocation());
			owr.language.put(rn, p.getLocale());
			owr.difen.put(rn, 0);
			owr.bossgen(p.getLocation(), p, p.getName(), input, owr.BOSSHP);
		}
	}
	
	@EventHandler
	public void Damagegetter(EntityDamageByEntityEvent d) 
	{
		
		if(d.getEntity().hasMetadata("fake") || d.getEntity().hasMetadata("portal") || d.getEntity().isInvulnerable()) {
			return;
		}
		if(d.getDamager() instanceof Player && d.getEntity() instanceof LivingEntity) {
			Player p = (Player) d.getDamager();
			LivingEntity le = (LivingEntity) d.getEntity();
			String len = ChatColor.stripColor(le.getName().split("\\s\\(")[0]);
			if(le instanceof Player) {
				Player hp = (Player) le;
				len = hp.getDisplayName();
				if(io.github.chw3021.party.Party.isInSameParty(p, hp)) {
					return;
				}
			}

			if(damagedsdam.containsRow(p.getUniqueId())) {
				if(damagedsdam.contains(p.getUniqueId(), len)) {
					damagedsdam.put(p.getUniqueId(), len, damagedsdam.get(p.getUniqueId(), len)+d.getDamage());
				}
				else {
					if(damagedsdam.row(p.getUniqueId()).size()>10) {
						String re = damagedsdam.row(p.getUniqueId()).keySet().stream().findFirst().get();
						damagedsdam.remove(p.getUniqueId(),re);
					}
					damagedsdam.put(p.getUniqueId(), len, d.getDamage());
				}
			}
			else {
				damagedsdam.put(p.getUniqueId(), len, d.getDamage());
			}
		}

		if(d.getDamager() instanceof Projectile && d.getEntity() instanceof LivingEntity) {
			Projectile pr = (Projectile) d.getDamager();
			if(pr.getShooter() instanceof Player && !(pr instanceof Snowball)) {
				Player p = (Player) pr.getShooter();
				LivingEntity le = (LivingEntity) d.getEntity();
				String len = ChatColor.stripColor(le.getName().split("\\s\\(")[0]);
				if(le instanceof Player) {
					Player hp = (Player) le;
					len = hp.getDisplayName();
					if(io.github.chw3021.party.Party.isInSameParty(p, hp)) {
						return;
					}
				}


				if(damagedsdam.containsRow(p.getUniqueId())) {
					if(damagedsdam.contains(p.getUniqueId(), len)) {
						damagedsdam.put(p.getUniqueId(), len, damagedsdam.get(p.getUniqueId(), len)+d.getDamage());
					}
					else {
						if(damagedsdam.row(p.getUniqueId()).size()>10) {
							String re = damagedsdam.row(p.getUniqueId()).keySet().stream().findFirst().get();
							damagedsdam.remove(p.getUniqueId(),re);
						}
						damagedsdam.put(p.getUniqueId(), len, d.getDamage());
					}
				}
				else {
					damagedsdam.put(p.getUniqueId(), len, d.getDamage());
				}
			}
			
		}
	}
	
	final private void help(Player p) {

		if(p.getLocale().equalsIgnoreCase("ko_kr")) {
			p.sendMessage(ChatColor.GREEN +"[Made By chw3021]");
			p.sendMessage(ChatColor.GREEN +"---Rpg 명령어---");
			p.sendMessage(ChatColor.GREEN +"/rpg dam (/rpg d, /rpg damage) -> 현재 데미지를 표시합니다");
			p.sendMessage(ChatColor.GREEN +"/rpg element (/rpg el) -> 현재 원소 계열 공격력, 저항력을 표시합니다");
			p.sendMessage(ChatColor.GREEN +"/rpg escape (/rpg es) -> 현재 진행중인 전투를 종료합니다");
			p.sendMessage(ChatColor.GREEN +"/rpg skill (/rpg s) -> 스킬창을 엽니다");
			p.sendMessage(ChatColor.GREEN +"/rpg class (/rpg c) -> 직업 선택창을 엽니다");
			p.sendMessage(ChatColor.GREEN +"/rpg rank (/rpg r) -> 자신의 직업의 숙련도 10순위를 표시합니다");
			p.sendMessage(ChatColor.GREEN +"/rpg graph (/rpg g) -> 적들에게 입힌 피해량을 표시합니다");
			p.sendMessage(ChatColor.GREEN +"/rpg gclear (/rpg gc) -> 기록된 피해량들을 모두 지웁니다");
			if(p.isOp())
			{
				p.sendMessage(ChatColor.RED +"/rpg exp [amounts]-> give player amounts of exp");
				p.sendMessage(ChatColor.RED +"/rpg pro [amounts]-> give player amounts of proficiency");
				p.sendMessage(ChatColor.RED +"/rpg enchant(/rpg ench) [enchantment] [level] -> enchant item in main hand");
				p.sendMessage(ChatColor.RED +"/rpg enchant(/rpg ench) clear -> remove enchantment of item in main hand");
				p.sendMessage(ChatColor.RED +"/rpg elements(/rpg elm) -> open Elements Inventory");
			}
			p.sendMessage(ChatColor.GREEN +" ");
			p.sendMessage(ChatColor.LIGHT_PURPLE +"---파티 명령어---");
			p.sendMessage(ChatColor.LIGHT_PURPLE +"/party create <파티명>: 파티를 생성하고 파티장이 됩니다");
			p.sendMessage(ChatColor.LIGHT_PURPLE +"/party join <파티명>: 해당 파티에 참가합니다");
			p.sendMessage(ChatColor.LIGHT_PURPLE +"/party password <비밀번호>: 파티를 해당 비밀번호로 잠궈서, 비밀번호를 입력해야 파티를 참가할수 있게합니다");
			p.sendMessage(ChatColor.LIGHT_PURPLE +"/party invite <파티명>: (파티장만 가능) 해당 플레이어를 초대합니다");
			p.sendMessage(ChatColor.LIGHT_PURPLE +"/party kick <플레이어 이름>: (파티장만 가능) 해당 플레이어를 강퇴합니다");
			p.sendMessage(ChatColor.LIGHT_PURPLE +"/party leave: 파티를 떠납니다");
			p.sendMessage(ChatColor.LIGHT_PURPLE +"/party assemble: (파티장만 가능) 파티원들을 자신의 위치로 이동시킵니다");
			p.sendMessage(ChatColor.LIGHT_PURPLE +"/party list: 현재 생성되어있는 파티들의 목록을 표시합니다");
		}
		else {
			p.sendMessage(ChatColor.GREEN +"[Made By chw3021]");
			p.sendMessage(ChatColor.GREEN +"---Rpg Commands---");
			p.sendMessage(ChatColor.GREEN +"/rpg dam (/rpg d, /rpg damage) -> show player's current attack damage");
			p.sendMessage(ChatColor.GREEN +"/rpg element (/rpg el) -> show player's element power & resistance");
			p.sendMessage(ChatColor.GREEN +"/rpg skill (/rpg s) -> show player's skills gui");
			p.sendMessage(ChatColor.GREEN +"/rpg escape (/rpg es) -> end current fighting");
			p.sendMessage(ChatColor.GREEN +"/rpg class (/rpg c) -> show player's classes gui");
			p.sendMessage(ChatColor.GREEN +"/rpg rank (/rpg r) -> show Top 10 ranking of proficiency of your class");
			p.sendMessage(ChatColor.GREEN +"/rpg graph (/rpg g) -> show the damage you've done to your enemies");
			p.sendMessage(ChatColor.GREEN +"/rpg gclear (/rpg gc) -> clear all recorded damage");
			if(p.isOp())
			{
				p.sendMessage(ChatColor.RED +"/rpg exp [amounts]-> give player amounts of exp");
				p.sendMessage(ChatColor.RED +"/rpg pro [amounts]-> give player amounts of proficiency");
				p.sendMessage(ChatColor.RED +"/rpg enchant(/rpg ench) [enchantment] [level] -> enchant item in main hand");
				p.sendMessage(ChatColor.RED +"/rpg enchant(/rpg ench) clear -> remove enchantment of item in main hand");
				p.sendMessage(ChatColor.RED +"/rpg elements(/rpg elm) -> open Elements Inventory");
			}
			p.sendMessage(ChatColor.GREEN +" ");
			p.sendMessage(ChatColor.LIGHT_PURPLE +"---Party Commands---");
			p.sendMessage(ChatColor.LIGHT_PURPLE +"/party create <partyname>: Create Party and be owner");
			p.sendMessage(ChatColor.LIGHT_PURPLE +"/party join <partyname>: Join to the party");
			p.sendMessage(ChatColor.LIGHT_PURPLE +"/party password <password>: Lock the Party With the Password. Player Should Enter the Password To Join This Party");
			p.sendMessage(ChatColor.LIGHT_PURPLE +"/party invite <playername>: Invite player to your party(Only Owner can use)");
			p.sendMessage(ChatColor.LIGHT_PURPLE +"/party kick <playername>: Kick the Player(Only Owner can use)");
			p.sendMessage(ChatColor.LIGHT_PURPLE +"/party leave: Leave Party");
			p.sendMessage(ChatColor.LIGHT_PURPLE +"/party assemble: Teleport All members(Only Owner can use)");
			p.sendMessage(ChatColor.LIGHT_PURPLE +"/party list: Show Current Existing Parties & Owners");
		}
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String alias, String [] args)         
    {
		if(!(sender instanceof Player) &&alias.equals("rpg"))
		{
		}
		if (sender instanceof ConsoleCommandSender && alias.equals("rpg")) { 
	        if (args.length > 0 && args[0].equalsIgnoreCase("jjj")) {
	            Bukkit.getWorlds().forEach(w -> {
	                Bukkit.unloadWorld(w, false);
	            });
	            sender.sendMessage("모든 월드를 언로드했습니다.");
	            return true; // 명령어 처리 성공
	        }
	    }
		if(sender instanceof Player && alias.equals("rpg"))
		{
			Player p = (Player)sender;
			
			if(args.length==0 || args[0].equals("help") || args[0].equals("?"))
			{
				help(p);
			}

			if(args[0].equalsIgnoreCase("water") && p.isOp())
			{
				HashSet<Location> lhs = new HashSet<>();
				final Location rl = p.getLocation().clone();
				for(int ix = -10; ix<10; ix++) {
					for(int iy = -10; iy<10; iy++) {
						for(int iz = -10; iz<10; iz++) {
							if(rl.clone().add(ix, iy, iz).getBlock().getType().name().contains("WATER"))
							lhs.add(rl.clone().add(ix, iy, iz));
						}
					}
				}
				lhs.forEach(l -> l.getBlock().setType(Material.VOID_AIR));
				
			}
			else if(args.length > 0)
			{
				if(args[0].equalsIgnoreCase("bm")  || args[0].equalsIgnoreCase("bi"))
				{
					p.sendRawMessage(p.getLocation().getBlock().getBiome().toString());
				}

				else if(args[0].equalsIgnoreCase("escape")||args[0].equalsIgnoreCase("es"))
				{
					String rn = getheroname(p);
					if(p.getLocale().equalsIgnoreCase("ko_kr")) {
						RaidFinish(rn, "탈주","몬스터들이 떠났습니다",0);
						owr.OverworldRaidFinish(rn, "탈주","몬스터들이 떠났습니다",0);
						ncr.NethercoreRaidFinish(rn, "탈주","몬스터들이 떠났습니다",0);
					}
					else {
						RaidFinish(rn, "Escaped","Monsters Left",0);
						owr.OverworldRaidFinish(rn, "Escaped","Monsters Left",0);
						ncr.NethercoreRaidFinish(rn, "Escaped","Monsters Left",0);
					}
				}

				else if(args[0].equalsIgnoreCase("element")||args[0].equalsIgnoreCase("el"))
				{
					Pak pak = Pak.getInstance();
					pak.eldmes(p);
				}
				else if(args[0].equalsIgnoreCase("exp") && p.isOp()&& !args[1].isEmpty())
				{
					p.giveExp(Integer.parseInt(args[1]));
					Bukkit.getServer().getPluginManager().callEvent(new PlayerExpChangeEvent(p,Integer.parseInt(args[1])));
				}
				else if(args[0].equalsIgnoreCase("class") || args[0].equalsIgnoreCase("c"))
				{
					Classgui classgui = new Classgui();
					classgui.open(p);
				}
				else if(args[0].equalsIgnoreCase("rank") || args[0].equalsIgnoreCase("r"))
				{
					Proficiency.prorank(p);
				}
				else if(args[0].equalsIgnoreCase("pro") && p.isOp()&& !args[1].isEmpty())
				{
					Bukkit.getServer().getPluginManager().callEvent(new PlayerExpChangeEvent(p,Integer.parseInt(args[1])));
				}
				else if((args[0].equalsIgnoreCase("elements")||args[0].equalsIgnoreCase("elm")) && p.isOp())
				{
					Elements.ElementsInv(p);
				}
				else if((args[0].equalsIgnoreCase("armor")||args[0].equalsIgnoreCase("ar")) && p.isOp())
				{
					Elements.give(Boots.get(Integer.parseInt(args[1]), p), 1, p);
					Elements.give(Helmet.get(Integer.parseInt(args[1]), p), 1, p);
					Elements.give(Leggings.get(Integer.parseInt(args[1]), p), 1, p);
					Elements.give(Chestplate.get(Integer.parseInt(args[1]), p), 1, p);
				}
				else if((args[0].equalsIgnoreCase("scroll")||args[0].equalsIgnoreCase("scroll")) && p.isOp())
				{
					Elements.give(Elements.getscroll(p), 1, p);
				}
				else if((args[0].equalsIgnoreCase("weapons")||args[0].equalsIgnoreCase("w")) && p.isOp())
				{
					Weapons w = new Weapons();
					w.winv(p);
				}
				else if((args[0].equalsIgnoreCase("elweapon")||args[0].equalsIgnoreCase("elw")) && p.isOp()&& !args[1].isEmpty())
				{
					Weapons w = new Weapons();
					w.giveElWeapon(Integer.parseInt(args[1]), p );
				}
				else if((args[0].equalsIgnoreCase("elarmors")||args[0].equalsIgnoreCase("elar")) && p.isOp()&& !args[1].isEmpty())
				{
					Armors ar = new Armors();
					ar.giveElArmors(Integer.parseInt(args[1]), p);
				}
				else if(args[0].equalsIgnoreCase("bosstest") && p.isOp()&& !args[1].isEmpty())
				{
					bossTest(args[1], p);
				}
				else if(args[0].equalsIgnoreCase("god") && p.isOp()&& !args[1].isEmpty())
				{
					p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 999999, Integer.parseInt(args[1]), false, false));
					p.addPotionEffect(new PotionEffect(PotionEffectType.RESISTANCE, 999999, Integer.parseInt(args[1]), false, false));
					p.addPotionEffect(new PotionEffect(PotionEffectType.WATER_BREATHING, 999999, Integer.parseInt(args[1]), false, false));
					p.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 999999, Integer.parseInt(args[1]), false, false));
					p.addPotionEffect(new PotionEffect(PotionEffectType.STRENGTH, 999999, Integer.parseInt(args[1]), false, false));
					p.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 999999, Integer.parseInt(args[1]), false, false));
					p.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, 999999, Integer.parseInt(args[1]), false, false));
				}
				else if(args[0].equalsIgnoreCase("speed") && p.isOp()&& !args[1].isEmpty())
				{
					p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 999999, Integer.parseInt(args[1]), false, false));
					p.addPotionEffect(new PotionEffect(PotionEffectType.HASTE, 999999, Integer.parseInt(args[1]), false, false));
					p.addPotionEffect(new PotionEffect(PotionEffectType.DOLPHINS_GRACE, 999999, Integer.parseInt(args[1]), false, false));
					p.addPotionEffect(new PotionEffect(PotionEffectType.CONDUIT_POWER, 999999, Integer.parseInt(args[1]), false, false));
				}
				else if((args[0].equalsIgnoreCase("enchant")||args[0].equalsIgnoreCase("ench")) && p.isOp() && !args[1].isEmpty())
				{
					if(args[1].equalsIgnoreCase("clear")) {
						p.getEquipment().getItemInMainHand().getEnchantments().keySet().forEach(en -> {
							p.getEquipment().getItemInMainHand().removeEnchantment(en);
						});
						return true;
					}
					else if(!args[2].isEmpty()) {
						p.getEquipment().getItemInMainHand().addUnsafeEnchantment(Registry.ENCHANTMENT.get(NamespacedKey.fromString(args[1])), Integer.parseInt(args[2]));
					}
				}
				else if(args[0].equalsIgnoreCase("dam") || args[0].equalsIgnoreCase("d") || args[0].equalsIgnoreCase("damage"))
				{
					p.sendMessage(String.valueOf(Pak.player_damage.get(p.getName())) );
				}
				else if(args[0].equalsIgnoreCase("graph") || args[0].equalsIgnoreCase("g"))
				{
					p.sendMessage(ChatColor.AQUA+"■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");
					if(damagedsdam.containsRow(p.getUniqueId())) {
						damagedsdam.row(p.getUniqueId()).forEach((k,v) ->{
							p.sendMessage(ChatColor.AQUA+"=================================");
							p.sendMessage(k+ ": " +ChatColor.AQUA+ ""+ BigDecimal.valueOf(v).setScale(2, RoundingMode.HALF_EVEN) +"  |   X"+getkillcount(p,k));
						});
					}
					p.sendMessage(ChatColor.AQUA+"=================================");
					p.sendMessage(ChatColor.AQUA+"■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");
				}
				else if(args[0].equalsIgnoreCase("gclear") || args[0].equalsIgnoreCase("gc"))
				{
					if(damagedsdam.containsRow(p.getUniqueId())) {
						HashSet<String> re = new HashSet<>();
						damagedsdam.row(p.getUniqueId()).keySet().forEach(k->{
							re.add(k);
						});
						re.forEach(k -> damagedsdam.remove(p.getUniqueId(), k));
					}
					if(damagedscount.containsRow(p.getUniqueId())) {
						HashSet<String> re = new HashSet<>();
						damagedscount.row(p.getUniqueId()).keySet().forEach(k->{
							re.add(k);
						});
						re.forEach(k -> damagedscount.remove(p.getUniqueId(), k));
					}
				}
				else if(args[0].equalsIgnoreCase("ad") || args[0].equalsIgnoreCase("atd") || args[0].equalsIgnoreCase("atdamage"))
				{
					p.sendMessage(String.valueOf(p.getAttribute(Attribute.ATTACK_DAMAGE).getValue()) );
				}
				else if(args[0].equalsIgnoreCase("skill") || args[0].equalsIgnoreCase("s"))
				{
					if(ClassData.pc.get(p.getUniqueId()) == 0)
					{
						SwordSkillsGui ssg = new SwordSkillsGui();
						ssg.SwordSkillsinv(p);
						ssg.SwordSkillsinv(p);
					}
					else if(ClassData.pc.get(p.getUniqueId()) == 1)
					{
						BerSkillsGui bsg = new BerSkillsGui();
						bsg.Berskillsinv(p);
						bsg.Berskillsinv(p);
					}
					else if(ClassData.pc.get(p.getUniqueId()) == 2)
					{
						HunSkillsGui hsg = new HunSkillsGui();
						hsg.Hunskillsinv(p);
						hsg.Hunskillsinv(p);
					}
					else if(ClassData.pc.get(p.getUniqueId()) == 3)
					{
						PalSkillsGui psg = new PalSkillsGui();
						psg.PalSkillsinv(p);
						psg.PalSkillsinv(p);
					}
					else if(ClassData.pc.get(p.getUniqueId()) == 4)
					{
						SnipSkillsGui l = new SnipSkillsGui();
						l.Snipskillsinv(p);
						l.Snipskillsinv(p);
					}
					else if(ClassData.pc.get(p.getUniqueId()) == 5)
					{
						LaunSkillsGui l = new LaunSkillsGui();
						l.Launskillsinv(p);
						l.Launskillsinv(p);
					}
					else if(ClassData.pc.get(p.getUniqueId()) == 6)
					{
						ArchSkillsGui l = new ArchSkillsGui();
						l.Archskillsinv(p);
						l.Archskillsinv(p);
					}
					else if(ClassData.pc.get(p.getUniqueId()) == 61)
					{
						MedSkillsGui l = new MedSkillsGui();
						l.Medskillsinv(p);
						l.Medskillsinv(p);
					}
					else if(ClassData.pc.get(p.getUniqueId()) == 7)
					{
						BoxSkillsGui l = new BoxSkillsGui();
						l.BoxSkillsinv(p);
						l.BoxSkillsinv(p);
					}
					else if(ClassData.pc.get(p.getUniqueId()) == 8)
					{
						WreSkillsGui wrg = new WreSkillsGui();
						wrg.WreSkillsinv(p);
						wrg.WreSkillsinv(p);
					}
					else if(ClassData.pc.get(p.getUniqueId()) == 9)
					{
						TamSkillsGui tsg = new TamSkillsGui();
						tsg.TamSkillsinv(p);
						tsg.TamSkillsinv(p);
					}
					else if(ClassData.pc.get(p.getUniqueId()) == 10)
					{
						TaoSkillsGui tag = new TaoSkillsGui();
						tag.TaoSkillsinv(p);
						tag.TaoSkillsinv(p);
					}
					else if(ClassData.pc.get(p.getUniqueId()) == 11)
					{
						IllSkillsGui ilg = new IllSkillsGui();
						ilg.IllSkillsinv(p);
						ilg.IllSkillsinv(p);
					}
					else if(ClassData.pc.get(p.getUniqueId()) == 12)
					{
						FireSkillsGui fsg = new FireSkillsGui();
						fsg.FIreSkillsinv(p);
						fsg.FIreSkillsinv(p);
					}
					else if(ClassData.pc.get(p.getUniqueId()) == 13)
					{
						WitSkillsGui wsg = new WitSkillsGui();
						wsg.WitSkillsinv(p);
						wsg.WitSkillsinv(p);
					}
					else if(ClassData.pc.get(p.getUniqueId()) == 14)
					{
						WdcSkillsGui wdg = new WdcSkillsGui();
						wdg.WdcSkillsinv(p);
						wdg.WdcSkillsinv(p);
					}
					else if(ClassData.pc.get(p.getUniqueId()) == 15)
					{
						CheSkillsGui chg = new CheSkillsGui();
						chg.CheSkillsinv(p);
						chg.CheSkillsinv(p);
					}
					else if(ClassData.pc.get(p.getUniqueId()) == 16)
					{
						ForSkillsGui fgg = new ForSkillsGui();
						fgg.ForSkillsinv(p);
						fgg.ForSkillsinv(p);
					}
					else if(ClassData.pc.get(p.getUniqueId()) == 17)
					{
						EngSkillsGui egg = new EngSkillsGui();
						egg.EngSkillsinv(p);
						egg.EngSkillsinv(p);
					}
					else if(ClassData.pc.get(p.getUniqueId()) == 18)
					{
						CookSkillsGui csg = new CookSkillsGui();
						csg.CookSkillsinv(p);
						csg.CookSkillsinv(p);
					}
					else if(ClassData.pc.get(p.getUniqueId()) == 19)
					{
						NobSkillsGui nsg = new NobSkillsGui();
						nsg.NobSkillsinv(p);
						nsg.NobSkillsinv(p);
					}
					else if(ClassData.pc.get(p.getUniqueId()) == 20)
					{
						OceSkillsGui osg = new OceSkillsGui();
						osg.OceSkillsinv(p);
						osg.OceSkillsinv(p);
					}
					else if(ClassData.pc.get(p.getUniqueId()) == 21)
					{
						FrostSkillsGui fsg = new FrostSkillsGui();
						fsg.FrostSkillsinv(p);
						fsg.FrostSkillsinv(p);
					}
					else if(ClassData.pc.get(p.getUniqueId()) == 22)
					{
						AngSkillsGui fsg = new AngSkillsGui();
						fsg.AngSkillsinv(p);
						fsg.AngSkillsinv(p);
					}
					else if(ClassData.pc.get(p.getUniqueId()) == 25)
					{
						BroSkillsGui bsg = new BroSkillsGui();
						bsg.BroSkillsinv(p);
						bsg.BroSkillsinv(p);
					}
				}
				else
				{
					sender.sendMessage("invaild command");
					help(p);
				}
			}
		}
		else
		{
			sender.sendMessage("invaild command");
		}
		return false;
	}

}