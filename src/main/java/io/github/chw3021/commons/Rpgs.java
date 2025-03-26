package io.github.chw3021.commons;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashSet;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.Registry;
import org.bukkit.World;
import org.bukkit.attribute.Attribute;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.ElderGuardian;
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
import io.github.chw3021.classes.Classgui;
import io.github.chw3021.classes.Proficiency;
import io.github.chw3021.classes.SkillsGui;
import io.github.chw3021.items.Elements;
import io.github.chw3021.items.armors.Armors;
import io.github.chw3021.items.armors.Boots;
import io.github.chw3021.items.armors.Chestplate;
import io.github.chw3021.items.armors.Helmet;
import io.github.chw3021.items.armors.Leggings;
import io.github.chw3021.items.weapons.Weapons;
import io.github.chw3021.monsters.raids.EndercoreRaids;
import io.github.chw3021.monsters.raids.NethercoreRaids;
import io.github.chw3021.monsters.raids.OverworldRaids;
import io.github.chw3021.monsters.raids.Summoned;
import io.github.chw3021.monsters.raids.WitherRaids;
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
	WitherRaids wtr = WitherRaids.getInstance();

	EndercoreRaids ecr = EndercoreRaids.getInstance();
	NethercoreRaids ncr = NethercoreRaids.getInstance();

	OverworldRaids owr = OverworldRaids.getInstance();
	private void bossTest(String args, Player p) {

		Integer input = Integer.parseInt(args);
		String rn = getheroname(p);
		if(input<0 && input >-6) {
			NethercoreRaids.beforepl.put(p.getUniqueId(), p.getLocation());
			NethercoreRaids.heroes.put(rn, p.getUniqueId());
			NethercoreRaids.raidloc.put(rn, p.getLocation());
			ncr.bossnum.put(rn, input);
			ncr.difen.put(rn, 0);
			ncr.language.put(rn, p.getLocale());
			ncr.bossgen(p.getLocation(), p, rn, input, ncr.BOSSHP);
			
		}
		else if(input <= -6&& input >-8) {
			EndercoreRaids.beforepl.put(p.getUniqueId(), p.getLocation());
			EndercoreRaids.heroes.put(rn, p.getUniqueId());
			EndercoreRaids.raidloc.put(rn, p.getLocation());
			ecr.bossnum.put(rn, input);
			ecr.difen.put(rn, 0);
			ecr.language.put(rn, p.getLocale());
			ecr.bossgen(p.getLocation(), p, rn, input, ecr.BOSSHP);
			
		}
		else if(input == -8) {
			WitherRaids.beforepl.put(p.getUniqueId(), p.getLocation());
			WitherRaids.heroes.put(rn, p.getUniqueId());
			WitherRaids.raidloc.put(rn, p.getLocation());
			wtr.difen.put(rn, 0);
			wtr.language.put(rn, p.getLocale());
			wtr.bossgen(p.getLocation(), p, rn, input, wtr.BOSSHP);
			
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

    public static void teleportToDimension(Player player, String dimension) {
        World targetWorld = Bukkit.getWorld(dimension);
        if (targetWorld == null) {
            player.sendMessage("§c해당 차원은 존재하지 않습니다: " + dimension);
            return;
        }

        // 이동할 위치 설정 (월드 스폰 지점 또는 안전한 기본 위치)
        Location targetLocation = targetWorld.getSpawnLocation();
        targetLocation.setY(targetWorld.getHighestBlockYAt(targetLocation)); // 안전한 높이 계산

        // 플레이어 이동
        player.teleport(targetLocation);
        player.sendMessage("§a" + dimension + " 차원으로 이동했습니다!");
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
			p.sendMessage(ChatColor.GREEN +"/rpg element (/rpg el) -> 현재 원소 공격력, 저항력을 표시합니다");
			p.sendMessage(ChatColor.GREEN +"/rpg escape (/rpg es) -> 현재 진행중인 전투를 종료합니다");
			p.sendMessage(ChatColor.GREEN +"/rpg skill (/rpg s) -> 스킬창을 엽니다");
			p.sendMessage(ChatColor.GREEN +"/rpg class (/rpg c) -> 직업 선택창을 엽니다");
			p.sendMessage(ChatColor.GREEN +"/rpg rank (/rpg r) -> 자신의 직업의 숙련도 10순위를 표시합니다");
			p.sendMessage(ChatColor.GREEN +"/rpg graph (/rpg g) -> 적들에게 입힌 피해량을 표시합니다");
			p.sendMessage(ChatColor.GREEN +"/rpg gclear (/rpg gc) -> 기록된 피해량들을 모두 지웁니다");
			p.sendMessage(ChatColor.YELLOW +"/smith -> 첫번째 슬롯에 있는 장비를 두번째 슬롯에 있는 재료로 강화합니다");
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
			p.sendMessage(ChatColor.YELLOW +"/smith -> Smith Equipment in first slot by using ingredient in second slot");
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
	
	private void opCommands(String[] args, Player p) {

		if(args[0].equalsIgnoreCase("exp") && p.isOp()&& !args[1].isEmpty())
		{
			p.giveExp(Integer.parseInt(args[1]));
			Bukkit.getServer().getPluginManager().callEvent(new PlayerExpChangeEvent(p,Integer.parseInt(args[1])));
		}
		else if(args[0].equalsIgnoreCase("pro") && p.isOp()&& !args[1].isEmpty())
		{
			Bukkit.getServer().getPluginManager().callEvent(new PlayerExpChangeEvent(p,Integer.parseInt(args[1])));
		}
		else if((args[0].equalsIgnoreCase("elements")||args[0].equalsIgnoreCase("elm")) && p.isOp())
		{
			Elements.ElementsInv(p);
		}
		else if((args[0].equalsIgnoreCase("dimen")||args[0].equalsIgnoreCase("dim"))&& !args[1].isEmpty() && p.isOp())
		{
			teleportToDimension(p, args[1]);
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
			}
			else if(!args[2].isEmpty()) {
				p.getEquipment().getItemInMainHand().addUnsafeEnchantment(Registry.ENCHANTMENT.get(NamespacedKey.fromString(args[1])), Integer.parseInt(args[2]));
			}
		}
		else if((args[0].equalsIgnoreCase("elg")) && p.isOp())
		{
			p.getWorld().spawn(p.getLocation(), ElderGuardian.class);
		}
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String alias, String [] args)         
    {
		if(!(sender instanceof Player) &&alias.equals("rpg"))
		{
		}
		if(sender instanceof Player && alias.equals("rpg"))
		{
			Player p = (Player)sender;
			
			if(args.length==0 || args[0].equals("help") || args[0].equals("?"))
			{
				help(p);
			}

			else if(args.length > 0)
			{
				opCommands(args, p);
				
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
				else if(args[0].equalsIgnoreCase("class") || args[0].equalsIgnoreCase("c"))
				{
					Classgui classgui = new Classgui();
					classgui.open(p);
				}
				else if(args[0].equalsIgnoreCase("rank") || args[0].equalsIgnoreCase("r"))
				{
					Proficiency.prorank(p);
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
					SkillsGui.openSkillsGui(p);
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