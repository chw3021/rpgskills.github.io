package io.github.chw3021.party;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandSendEvent;
import org.bukkit.event.player.PlayerExpChangeEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.event.server.PluginDisableEvent;
import org.bukkit.scoreboard.Criteria;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.RenderType;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;
import org.bukkit.scoreboard.Team.Option;
import org.bukkit.scoreboard.Team.OptionStatus;

import com.google.common.collect.HashMultimap;

import io.github.chw3021.monsters.raids.Summoned;
import io.github.chw3021.rmain.RMain;
import net.md_5.bungee.api.ChatColor;

public class Party implements CommandExecutor, Serializable, Listener{

	/**
	 * 
	 */
	private static transient final long serialVersionUID = 3384762156083972776L;
	
	public static HashMap<UUID, Boolean> Owner = new HashMap<>();
	public static HashMultimap<String, UUID> Party = HashMultimap.create();
	
	
	static private HashMap<UUID, Scoreboard> pb = new HashMap<>();
	
	static public HashMap<String, String> password = new HashMap<String, String>();
	static public HashMap<UUID, String> unlockable = new HashMap<UUID, String>();
	static public HashMap<UUID, Integer> unlockablet = new HashMap<UUID, Integer>();

	static public HashMap<String,UUID> join = new HashMap<>();
	static public HashMap<String,Integer> joint = new HashMap<>();

	static public HashMap<UUID,String> invite = new HashMap<>();
	static public HashMap<UUID,Integer> invitet = new HashMap<>();

    @EventHandler
 	public void command(PlayerCommandSendEvent ev)
 	{
        if(ev.getCommands().contains("party")) {
        }
 	}
    
	final private void addNewParty(Player p, String name)
	{
		if(Summoned.combo.containsRow(p.getName()))
		{
			if(p.getLocale().equalsIgnoreCase("ko_kr")) {
				p.sendMessage("�����߿��� ��Ƽ������ �Ұ��� �մϴ�");
			}
			else {
				p.sendMessage("You Can't Create Party While Fightning");
			}
			return;
		}
		PartyCreateEvent pe = new PartyCreateEvent(p, name);
		Bukkit.getPluginManager().callEvent(pe);
		
		if(!pe.isCancelled()) {
			Party.put(name, p.getUniqueId());
			Owner.put(p.getUniqueId(), true);

			if(p.getLocale().equalsIgnoreCase("ko_kr")) {
				p.sendMessage(ChatColor.GOLD+ "��Ƽ [" + name + "] �� �����Ǿ����ϴ�");
			}
			else {
				p.sendMessage(ChatColor.GOLD+ "Party [" + name + "] created");
			}
	        Scoreboard nboard = Bukkit.getScoreboardManager().getNewScoreboard();
	        Scoreboard board = p.getScoreboard();
	        pb.put(p.getUniqueId(), board);
			Objective heart = nboard.registerNewObjective(name ,Criteria.HEALTH, "[Health]", RenderType.HEARTS);
			heart.setDisplaySlot(DisplaySlot.BELOW_NAME);
	        Objective po = nboard.registerNewObjective(name+"p" ,Criteria.DUMMY, ChatColor.GOLD + "Party: [" + name+"]");
			if(p.getLocale().equalsIgnoreCase("ko_kr")) {
				heart.setDisplayName(ChatColor.GREEN + "[�����]");
				po.setDisplayName(ChatColor.GOLD + "��Ƽ: [" + name+"]");
			}
	        po.setDisplaySlot(DisplaySlot.SIDEBAR);
	        
			Team t = nboard.registerNewTeam(name);
			p.setScoreboard(nboard);
			t.setOption(Option.NAME_TAG_VISIBILITY, OptionStatus.FOR_OWN_TEAM);
			t.addEntry(p.getName());
			t.setCanSeeFriendlyInvisibles(true);
			t.setAllowFriendlyFire(true);
			t.setPrefix(ChatColor.LIGHT_PURPLE + "["+name + "]");
			po.getScore(p.getName()).setScore((int) p.getHealth());
		}
	}
	
	final private void setNewOwner(Player p1, Player p2, String name)
	{
		Owner.put(p1.getUniqueId(), false);
		Owner.put(p2.getUniqueId(), true);
	}
	
	final private void addNewPartyMember(Player p, String name)
	{
		Bukkit.getScheduler().runTask(RMain.getInstance(), () -> {

			if(Summoned.combo.containsRow(name))
			{
				if(p.getLocale().equalsIgnoreCase("ko_kr")) {
					p.sendMessage("�����߿��� ��Ƽ�� �߰��� �Ұ��� �մϴ�");
				}
				else {
					p.sendMessage("You Can't Add member while fighting");
				}
				return;
			}
			
			if(Party.get(name).size()>8) {
				if(p.getLocale().equalsIgnoreCase("ko_kr")) {
					p.sendMessage("��Ƽ���� �ִ� 8����� �����մϴ�");
				}
				else {
					p.sendMessage("Party members should be less than 8 people.");
				}
				return;
			}
			
			PartyJoinEvent pe = new PartyJoinEvent(p, name);
			
			Bukkit.getPluginManager().callEvent(pe);
			
			if(!pe.isCancelled()) {

				Party.put(name, p.getUniqueId());
				Owner.put(p.getUniqueId(), false);

				Scoreboard oboard = getOwner(name).getScoreboard();
				Scoreboard pboard = p.getScoreboard();
				Team t = oboard.getTeam(name);
		        pb.put(p.getUniqueId(), pboard);
				t.addEntry(p.getName());
				p.setScoreboard(oboard);
				oboard.getObjective(name+"p").getScore(p.getName()).setScore((int) p.getHealth());

				getPlayerMembers(name).forEach(m -> {
					if(m.getLocale().equalsIgnoreCase("ko_kr")) {
						m.sendMessage(ChatColor.GOLD + p.getDisplayName() +  "�� [" + name +"] ��Ƽ�� �����߽��ϴ�");
					}
					else {
						m.sendMessage(ChatColor.GOLD + p.getDisplayName() +"joined party [" + name+"]");
					}
				});
			}

		});
	}
	
	final private void removePartyMember(final Player p, String name)
	{
		Bukkit.getPluginManager().callEvent(new PartyLeaveEvent(p, name));
		
		Party.remove(name, p.getUniqueId());
		Owner.remove(p.getUniqueId());
        if(password.containsKey(name)) {
        	password.remove(name);
        }
        
		Scoreboard lboard = p.getScoreboard();
		lboard.resetScores(p.getName());
		Scoreboard pboard = pb.get(p.getUniqueId());
		
		Team t = lboard.getEntryTeam(p.getName());
		t.removeEntry(p.getName());
		p.setScoreboard(pboard);

		getPlayerMembers(name).forEach(m -> {
			if(m.getLocale().equalsIgnoreCase("ko_kr")) {
				m.sendMessage(ChatColor.GOLD + p.getDisplayName() +  "�� [" + name +"] ��Ƽ���� �������ϴ�");
			}
			else {
				m.sendMessage(ChatColor.GOLD + p.getDisplayName() +"left party [" + name+"]");
			}
		});
	}
	
	final private Stream<UUID> getOwners()
	{
		return Owner.keySet().stream().filter(k -> Owner.get(k) == true);
	}
	
	final private void removeParty(String name)
	{
		getPlayerMembers(name).forEach(p -> {
			if(p.getLocale().equalsIgnoreCase("ko_kr")) {
				p.sendMessage(ChatColor.RED + "��Ƽ ["+ name + "] �� ��ü�Ǿ����ϴ�");
			}
			else {
				p.sendMessage(ChatColor.RED + "Party ["+ name + "] has been disbanded");
			}
			Scoreboard lboard = p.getScoreboard();
			lboard.resetScores(p.getName());
			Scoreboard pboard = pb.get(p.getUniqueId());
			
			Team t = lboard.getEntryTeam(p.getName());
			t.removeEntry(p.getName());
			p.setScoreboard(pboard);
		});
        Party.get(name).forEach(pu -> Owner.remove(pu));
        Party.removeAll(name);
        if(password.containsKey(name)) {
        	password.remove(name);
        }
        
	}
	
	final private boolean isnameable(String name)
	{
		HashSet<String> sh = new HashSet<>();
		for(OfflinePlayer p : Bukkit.getOfflinePlayers()) {
			sh.add(p.getName());
		}
		return sh.contains(name);
	}
	
	public static boolean isPartyexist(String name)
	{
		return Party.containsKey(name);
	}
	
	public static String getParty(Player p)
	{
		return Party.keySet().stream().filter(x -> Party.containsEntry(x, p.getUniqueId())).findFirst().get();
	}

	public static List<Player> getPlayerMembers(String name)
	{
		List<Player> pl = new ArrayList<>();
		Party.get(name).forEach(pu -> {
			if(Bukkit.getPlayer(pu) != null) {
				pl.add(Bukkit.getPlayer(pu));
			}
		});
		return pl;
	}
	
	public static Stream<UUID> getMembers(String name)
	{
		return Party.get(name).stream();
	}

	public static Player getOwner(String name)
	{
		return Bukkit.getPlayer(Party.get(name).stream().filter(pu -> Owner.get(pu) == true).findFirst().get());
	}

	
	public static boolean isInSameParty(Player p1, Player p2)
	{
		if(p1 == null || p2 == null) {
			return false;
		}
		if(p1 == p2) {
			return true;
		}
		if(Party.containsValue(p1.getUniqueId()) && Party.containsValue(p2.getUniqueId())) {
			return Party.keySet().stream().anyMatch(x -> Party.containsEntry(x, p1.getUniqueId()) && Party.containsEntry(x, p2.getUniqueId()));
		}
		else {
			return false;
		}
	}
	
	public static boolean hasParty(Player p)
	{
		if(p == null) {
			return false;
		}
		
		
		return Party.containsValue(p.getUniqueId());
	}
	
	public static boolean isOwner(Player p)
	{
		return Owner.getOrDefault(p.getUniqueId(),false) == true;
	}

	final private void unlock(Player p, String party) {
		if(p.getLocale().equalsIgnoreCase("ko_kr")) {
			p.sendMessage("�ش� ��Ƽ�� ��й�ȣ�� 10�ʾȿ� �Է����ּ���");
		}
		else {
			p.sendMessage("Please Enter the password in 10 seconds To join.");
		}
		if(unlockablet.containsKey(p.getUniqueId())) {
			Bukkit.getScheduler().cancelTask(unlockablet.get(p.getUniqueId()));
			unlockablet.remove(p.getUniqueId());
		}
		unlockable.put(p.getUniqueId(), party);
		int task =Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
            @Override
            public void run() 
            {
            	unlockable.remove(p.getUniqueId());
    			unlockablet.remove(p.getUniqueId());
            }
		}, 200); 
		unlockablet.put(p.getUniqueId(), task);
	}

	final private void tolock(AsyncPlayerChatEvent e) {
		Player p = e.getPlayer();
		if(unlockable.containsKey(p.getUniqueId())) {
			e.setCancelled(true);
			String sbs = unlockable.get(p.getUniqueId());
			String chat = e.getMessage();
			if(password.get(sbs).equals(chat)) {
				Scoreboard oboard = getOwner(sbs).getScoreboard();
				Scoreboard pboard = p.getScoreboard();
				Team t = oboard.getTeam(sbs);
		        pb.put(p.getUniqueId(), pboard);
				t.addEntry(p.getName());
				p.setScoreboard(oboard);
				addNewPartyMember(p, sbs);
				if(p.getLocale().equalsIgnoreCase("ko_kr")) {
					p.sendMessage(sbs +" ��Ƽ�� �����߽��ϴ�");
				}
				else {
					p.sendMessage("joined party " + sbs);
				}
				oboard.getObjective(sbs+"p").getScore(p.getName()).setScore((int) p.getHealth());
			}
			else {
				if(p.getLocale().equalsIgnoreCase("ko_kr")) {
					p.sendMessage("��й�ȣ�� Ʋ�Ƚ��ϴ�");
				}
				else {
					p.sendMessage("Wrong Password");
				}
			}
			if(unlockablet.containsKey(p.getUniqueId())) {
				Bukkit.getScheduler().cancelTask(unlockablet.get(p.getUniqueId()));
				unlockablet.remove(p.getUniqueId());
			}
        	unlockable.remove(p.getUniqueId());
		}
	}
	
	final private void joinreq(Player p, String party) {
		if(join.containsKey(party)) {
			if(p.getLocale().equalsIgnoreCase("ko_kr")) {
				p.sendMessage(ChatColor.RED + "��û�� ���ؼ� ����ϼž��մϴ�");
			}
			else {
				p.sendMessage(ChatColor.LIGHT_PURPLE + "You still have to wait to apply");
			}
			return;
		}
		Player owner = getOwner(party);
		if(owner.getLocale().equalsIgnoreCase("ko_kr")) {
			owner.sendMessage(ChatColor.LIGHT_PURPLE + p.getDisplayName() + "�� ��Ƽ ���� ��û�� ���½��ϴ�.");
			owner.sendMessage(ChatColor.LIGHT_PURPLE + "[����: a] [����: d] 5�ʾȿ� ���Է½� �ڵ����� �����˴ϴ�");
		}
		else {
			owner.sendMessage(ChatColor.LIGHT_PURPLE + p.getDisplayName() + "wants to join your party");
			owner.sendMessage(ChatColor.LIGHT_PURPLE + "[Accept: a] [Deny: d]");
			owner.sendMessage(ChatColor.LIGHT_PURPLE + "Deny Automatically If you don't enter in 5 seconds");
		}
		if(joint.containsKey(party)) {
			Bukkit.getScheduler().cancelTask(joint.get(party));
			joint.remove(party);
		}
		join.put(party, p.getUniqueId());
		int task =Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
            @Override
            public void run() 
            {
            	join.remove(party);
    			joint.remove(party);
            }
		}, 100); 
		joint.put(party, task);
	}

	final private void tojoin(AsyncPlayerChatEvent e) {
		Player owp = e.getPlayer();
		if(hasParty(owp) && isOwner(owp)) {
			String par = getParty(owp);
			if(join.containsKey(par)) {
				
				Player p = Bukkit.getPlayer(join.get(par));
				e.setCancelled(true);
				String chat = e.getMessage();
				if(chat.equals("a")) {
					Scoreboard oboard = getOwner(par).getScoreboard();
					Scoreboard pboard = p.getScoreboard();
					Team t = oboard.getTeam(par);
			        pb.put(p.getUniqueId(), pboard);
					t.addEntry(p.getName());
					p.setScoreboard(oboard);
					addNewPartyMember(p, par);
					if(p.getLocale().equalsIgnoreCase("ko_kr")) {
						p.sendMessage(ChatColor.GOLD + "[" + par +"] ��Ƽ�� �����߽��ϴ�");
					}
					else {
						p.sendMessage(ChatColor.GOLD +"joined party [" + par+"]");
					}
					oboard.getObjective(par+"p").getScore(p.getName()).setScore((int) p.getHealth());
				}
				else {
					if(p.getLocale().equalsIgnoreCase("ko_kr")) {
						p.sendMessage(ChatColor.GRAY +"��û�� �����Ǿ����ϴ�");
					}
					else {
						p.sendMessage(ChatColor.GRAY +"Your application has been declined");
					}
				}
				if(joint.containsKey(par)) {
					Bukkit.getScheduler().cancelTask(joint.get(par));
					joint.remove(par);
				}
	        	join.remove(par);
			}
		}
	}
	
	final private void invitereq(Player p, Player ow) {
		String party = getParty(ow);
		if(invite.containsKey(p.getUniqueId())) {
			if(p.getLocale().equalsIgnoreCase("ko_kr")) {
				p.sendMessage(ChatColor.RED + "�ʴ븦 ���ؼ� ����ϼž��մϴ�");
			}
			else {
				p.sendMessage(ChatColor.LIGHT_PURPLE + "You still have to wait to invite");
			}
			return;
		}
		if(p.getLocale().equalsIgnoreCase("ko_kr")) {
			p.sendMessage(ChatColor.LIGHT_PURPLE + "[" + party + "] ��Ƽ�� ���� �ʴ�޾ҽ��ϴ�.");
			p.sendMessage(ChatColor.LIGHT_PURPLE + "[����: a] [����: d] 5�ʾȿ� ���Է½� �ڵ����� �����˴ϴ�");
		}
		else {
			p.sendMessage(ChatColor.LIGHT_PURPLE + "You're just invited from Party [" + party + "]");
			p.sendMessage(ChatColor.LIGHT_PURPLE + "[Accept: a] [Deny: d]");
			p.sendMessage(ChatColor.LIGHT_PURPLE + "Deny Automatically If you don't enter in 5 seconds");
		}
		if(invitet.containsKey(p.getUniqueId())) {
			Bukkit.getScheduler().cancelTask(invitet.get(p.getUniqueId()));
			invitet.remove(p.getUniqueId());
		}
		invite.put(p.getUniqueId(), party);
		int task =Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
            @Override
            public void run() 
            {
            	invite.remove(p.getUniqueId());
    			invitet.remove(p.getUniqueId());
            }
		}, 100); 
		invitet.put(p.getUniqueId(), task);
	}

	final private void toinvite(AsyncPlayerChatEvent e) {
		Player p = e.getPlayer();
			if(invite.containsKey(p.getUniqueId())) {
				
			String par  = invite.get(p.getUniqueId());
			Player owner = getOwner(par);
			
			e.setCancelled(true);
			String chat = e.getMessage();
			if(chat.equals("a")) {
				Scoreboard oboard = getOwner(par).getScoreboard();
				Scoreboard pboard = p.getScoreboard();
				Team t = oboard.getTeam(par);
		        pb.put(p.getUniqueId(), pboard);
				t.addEntry(p.getName());
				p.setScoreboard(oboard);
				addNewPartyMember(p, par);
				if(owner.getLocale().equalsIgnoreCase("ko_kr")) {
					owner.sendMessage(ChatColor.GRAY +"�ʴ밡 �����Ǿ����ϴ�");
				}
				else {
					owner.sendMessage(ChatColor.GRAY +"Your invitation has been accepted");
				}
				oboard.getObjective(par+"p").getScore(p.getName()).setScore((int) p.getHealth());
			}
			else {
				if(owner.getLocale().equalsIgnoreCase("ko_kr")) {
					owner.sendMessage(ChatColor.GRAY +"�ʴ밡 �����Ǿ����ϴ�");
				}
				else {
					owner.sendMessage(ChatColor.GRAY +"Your invitation has been declined");
				}
			}
			if(invitet.containsKey(p.getUniqueId())) {
				Bukkit.getScheduler().cancelTask(invitet.get(p.getUniqueId()));
				invitet.remove(p.getUniqueId());
			}
        	invite.remove(p.getUniqueId());
		}
	}
	
	@EventHandler
	public void Chat(AsyncPlayerChatEvent e)
	{
		tolock(e);
		tojoin(e);
		toinvite(e);
	}
	
	
	final private void help(Player p) {

		if(p.getLocale().equalsIgnoreCase("ko_kr")) {
			p.sendMessage(ChatColor.GREEN +"[Made By chw3021]");
			p.sendMessage(ChatColor.LIGHT_PURPLE +"---��Ƽ ��ɾ�---");
			p.sendMessage(ChatColor.LIGHT_PURPLE +"/party create <��Ƽ��> (/party c): ��Ƽ�� �����ϰ� ��Ƽ���� �˴ϴ�");
			p.sendMessage(ChatColor.LIGHT_PURPLE +"/party join <��Ƽ��> (/party j): �ش� ��Ƽ�� �����մϴ�");
			p.sendMessage(ChatColor.LIGHT_PURPLE +"/party password <��й�ȣ> (/party p): ��Ƽ�� �ش� ��й�ȣ�� ��ż�, ��й�ȣ�� �Է��ؾ� ��Ƽ�� �����Ҽ� �ְ��մϴ�");
			p.sendMessage(ChatColor.LIGHT_PURPLE +"/party invite <��Ƽ��> (/party i): (��Ƽ�常 ����) �ش� �÷��̾ �ʴ��մϴ�");
			p.sendMessage(ChatColor.LIGHT_PURPLE +"/party kick <�÷��̾� �̸�> (/party k): (��Ƽ�常 ����) �ش� �÷��̾ �����մϴ�");
			p.sendMessage(ChatColor.LIGHT_PURPLE +"/party disband (/party d): (��Ƽ�常 ����) ��Ƽ�� ��ü�մϴ�");
			p.sendMessage(ChatColor.LIGHT_PURPLE +"/party leave (/party lv): ��Ƽ�� �����ϴ�");
			p.sendMessage(ChatColor.LIGHT_PURPLE +"/party assemble (/party a): (��Ƽ�常 ����) ��Ƽ������ �ڽ��� ��ġ�� �̵���ŵ�ϴ�");
			p.sendMessage(ChatColor.LIGHT_PURPLE +"/party list (/party li): ���� �����Ǿ��ִ� ��Ƽ���� ����� ǥ���մϴ�");
		}
		else {
			p.sendMessage(ChatColor.GREEN +"[Made By chw3021]");
			p.sendMessage(ChatColor.LIGHT_PURPLE +"---Party Commands---");
			p.sendMessage(ChatColor.LIGHT_PURPLE +"/party create <partyname> (/party c): Create Party and be owner");
			p.sendMessage(ChatColor.LIGHT_PURPLE +"/party join <partyname> (/party j): Join to the party");
			p.sendMessage(ChatColor.LIGHT_PURPLE +"/party password <password> (/party p): Lock the Party With the Password. Player Should Enter the Password To Join This Party");
			p.sendMessage(ChatColor.LIGHT_PURPLE +"/party invite <playername> (/party i): Invite player to your party(Only Owner can use)");
			p.sendMessage(ChatColor.LIGHT_PURPLE +"/party kick <playername> (/party k): Kick the Player(Only Owner can use)");
			p.sendMessage(ChatColor.LIGHT_PURPLE +"/party disband (/party d): Disband the Party(Only Owner can use)");
			p.sendMessage(ChatColor.LIGHT_PURPLE +"/party leave (/party lv): Leave Party");
			p.sendMessage(ChatColor.LIGHT_PURPLE +"/party assemble (/party a): Teleport All members(Only Owner can use)");
			p.sendMessage(ChatColor.LIGHT_PURPLE +"/party list (/party li): Show Current Existing Parties & Owners");
		}
	}
	


	final private void leave(final Player p) {

		if(hasParty(p)) 
		{
			if(getMembers(getParty(p)).count()>1)
			{
				if(isOwner(p)) {
					String par = getParty(p);
					Player newowner = (Player) Bukkit.getPlayer(getMembers(par).filter(o->(o!=p.getUniqueId())).findAny().get());
					setNewOwner(p,newowner,par);
					if(newowner.getLocale().equalsIgnoreCase("ko_kr")) {
						newowner.sendMessage("��Ƽ���� ���ӹ޾ҽ��ϴ�");
					}
					else {
						newowner.sendMessage("You just took over owner of this party");
					}
					if(p.getLocale().equalsIgnoreCase("ko_kr")) {
						p.sendMessage("��Ƽ�� �������ϴ�");
					}
					else {
						p.sendMessage("You left your party");
					}
					removePartyMember(p, par);
				}
				else
				{
					String par = getParty(p);
					if(p.getLocale().equalsIgnoreCase("ko_kr")) {
						p.sendMessage("��Ƽ�� �������ϴ�");
					}
					else {
						p.sendMessage("You left your party");
					}
					removePartyMember(p, par);
				}
			}
			else {
				String par = getParty(p);
				if(p.getLocale().equalsIgnoreCase("ko_kr")) {
					p.sendMessage("��Ƽ�� �������ϴ�");
				}
				else {
					p.sendMessage("You left your party");
				}
				removeParty(par);
			}
		}
		else {
			if(p.getLocale().equalsIgnoreCase("ko_kr")) {
				p.sendMessage("��Ƽ�� �������� �ʽ��ϴ�");
			}
			else {
				p.sendMessage("You aren't in any party");
			}
		}
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String alias, String[] args)         
    {
		if(sender instanceof Player && alias.equals("party"))
		{
			if(args.length==0 || args[0].equals("help"))
			{
				Player p = (Player)sender;
				
				help(p);
				
				if(hasParty(p)) {
					if(p.getLocale().equalsIgnoreCase("ko_kr")) {
						p.sendMessage(ChatColor.LIGHT_PURPLE +"���� �����ִ� ��Ƽ: " + getParty(p));
					}
					else {
						p.sendMessage(ChatColor.LIGHT_PURPLE +"You belong to party: " + getParty(p));
					}
				}
			}
			else if(args.length > 0)
			{
				try{				
				if(args[0].equals("create") || args[0].equals("c"))
				{
					Player p = (Player)sender;
					String sbs = args[1];
					if(!hasParty(p))
						if(isPartyexist(sbs)) {
							if(p.getLocale().equalsIgnoreCase("ko_kr")) {
								p.sendMessage(ChatColor.LIGHT_PURPLE +"�̹� ������� ��Ƽ���Դϴ�");
							}
							else {
								p.sendMessage(ChatColor.LIGHT_PURPLE +"Already existing party");
							}
						}

						else if(isnameable(sbs)) {
							if(p.getLocale().equalsIgnoreCase("ko_kr")) {
								p.sendMessage(ChatColor.LIGHT_PURPLE +"�÷��̾��� �̸��� ��Ƽ������ ����Ҽ� �����ϴ�");
							}
							else {
								p.sendMessage(ChatColor.LIGHT_PURPLE +"Party's Name Cannot be Player's Name");
							}
						}
						else {
							addNewParty(p, sbs);
						}
					else
					{
						if(p.getLocale().equalsIgnoreCase("ko_kr")) {
							p.sendMessage(ChatColor.LIGHT_PURPLE +"�̹� ��Ƽ�� �����ֽ��ϴ�");
						}
						else {
							p.sendMessage(ChatColor.LIGHT_PURPLE +"You're already in a party");
						}
					}
				}
				else if(args[0].equals("password") || args[0].equals("p"))
				{
					Player p = (Player) sender;
					String sbs = args[1];
					
					if(hasParty(p)&& isOwner(p) && sbs != null) 
					{
						password.put(getParty(p), sbs);
						if(p.getLocale().equalsIgnoreCase("ko_kr")) {
							p.sendMessage(ChatColor.LIGHT_PURPLE +"��й�ȣ ���� �Ϸ�: " +args[1]);
						}
						else {
							p.sendMessage(ChatColor.LIGHT_PURPLE +"Password Set: " +args[1]);
						}
					}
					else {
						if(p.getLocale().equalsIgnoreCase("ko_kr")) {
							p.sendMessage(ChatColor.LIGHT_PURPLE +"��Ƽ�常 �����մϴ�");
						}
						else {
							p.sendMessage(ChatColor.LIGHT_PURPLE +"You should be owner of a party");
						}
					}
				}
				else if(args[0].equals("join") || args[0].equals("j"))
				{
					Player p = (Player) sender;
					String sbs = args[1];
					
					if(isPartyexist(sbs)) 
					{
						if(hasParty(p))
						{
							if(p.getLocale().equalsIgnoreCase("ko_kr")) {
								p.sendMessage(ChatColor.LIGHT_PURPLE +"�̹� ��Ƽ�� �����ֽ��ϴ�");
							}
							else {
								p.sendMessage(ChatColor.LIGHT_PURPLE +"You're already in a party");
							}
						}
						else {
							if(password.containsKey(sbs)) {
								unlock(p,sbs);
							}
							else {
								joinreq(p,sbs);
							}
						}
					}
					else {
						if(p.getLocale().equalsIgnoreCase("ko_kr")) {
							p.sendMessage(ChatColor.LIGHT_PURPLE +"�������� �ʴ� ��Ƽ�Դϴ�");
						}
						else {
							p.sendMessage(ChatColor.LIGHT_PURPLE +"invalid party");
						}
					}
				}
				else if(args[0].equals("invite") || args[0].equals("i"))
				{
					Player p = (Player)sender;
					String sbs = args[1];
					
					if(hasParty(p) && isOwner(p)) 
					{
						if(hasParty(Bukkit.getPlayer(sbs)))
						{
							if(p.getLocale().equalsIgnoreCase("ko_kr")) {
								p.sendMessage(ChatColor.LIGHT_PURPLE +"�̹� ��Ƽ�� �����ִ� �÷��̾� �Դϴ�");
							}
							else {
								p.sendMessage(ChatColor.LIGHT_PURPLE +"The player is already in a party");
							}
						}
						else {
							invitereq(Bukkit.getPlayer(sbs),p);
							}
					}
					else {
						if(p.getLocale().equalsIgnoreCase("ko_kr")) {
							p.sendMessage(ChatColor.LIGHT_PURPLE +"��Ƽ�常 �����մϴ�");
						}
						else {
							p.sendMessage(ChatColor.LIGHT_PURPLE +"You should be owner of a party");
						}
					}
						
				}
				else if(args[0].equals("kick") || args[0].equals("k"))
				{
					Player p = (Player)sender;
					String sbs = args[1];
					
					if(isOwner(p) && hasParty(p)) {
						if(Bukkit.getPlayer(sbs)!=null && hasParty(Bukkit.getPlayer(sbs))) {
							if(p.getLocale().equalsIgnoreCase("ko_kr")) {
								p.sendMessage(ChatColor.LIGHT_PURPLE +sbs + " �� ���� ���߽��ϴ�");
							}
							else {
								p.sendMessage(ChatColor.LIGHT_PURPLE +sbs + " kicked from your party");
							}
							if(Bukkit.getPlayer(sbs).getLocale().equalsIgnoreCase("ko_kr")) {
								Bukkit.getPlayer(sbs).sendMessage(ChatColor.LIGHT_PURPLE +getParty(p)+" ��Ƽ���� ���� ���߽��ϴ�"  );
							}
							else {
								Bukkit.getPlayer(sbs).sendMessage(ChatColor.LIGHT_PURPLE +"You just kicked from party " + getParty(p));
							}
							removePartyMember(Bukkit.getPlayer(sbs), getParty(p));
						}
						else {
							if(p.getLocale().equalsIgnoreCase("ko_kr")) {
								p.sendMessage(ChatColor.LIGHT_PURPLE +"�ش� �÷��̾�� ����Ƽ�� �����ϴ�");
							}
							else {
								p.sendMessage(ChatColor.LIGHT_PURPLE +"The player isn't in the party");
							}
						}
					}
					else {
						if(p.getLocale().equalsIgnoreCase("ko_kr")) {
							p.sendMessage(ChatColor.LIGHT_PURPLE +"��Ƽ�常 �����մϴ�");
						}
						else {
							p.sendMessage(ChatColor.LIGHT_PURPLE +"You should be owner of a party");
						}
					}
				}
				else if(args[0].equals("disband") || args[0].equals("d"))
				{
					Player p = (Player)sender;
					if(isOwner(p) && hasParty(p)) {
						removeParty(getParty(p));
					}
				}
				else if(args[0].equals("leave") || args[0].equals("lv"))
				{					
					Player p = (Player)sender;
					leave(p);
				}
				else if(args[0].equals("list") || args[0].equals("li"))
				{					
					Player p = (Player)sender;
					
					getOwners().forEach(o -> {
						String party =  getParty(Bukkit.getPlayer(o));
						if(p.getLocale().equalsIgnoreCase("ko_kr")) {
							p.sendMessage(ChatColor.LIGHT_PURPLE +"��Ƽ��: "+ party +" | ��Ƽ��: " + Bukkit.getPlayer(o).getDisplayName() + " | ��Ƽ����: " + (getMembers(party).toArray().length));
						}
						else {
							p.sendMessage(ChatColor.LIGHT_PURPLE +"Party Name: "+ getParty(Bukkit.getPlayer(o)) +" | Owner: " + Bukkit.getPlayer(o).getDisplayName() + " | Number of Members: " + (getMembers(party).toArray().length));
						}
					});
				}
				else if(args[0].equals("assemble") || args[0].equals("a"))
				{
					Player p = (Player)sender;
					
					if(isOwner(p) && hasParty(p)) 
					{
						for(UUID mem : getMembers(getParty(p)).collect(Collectors.toSet()))
						{
							Bukkit.getPlayer(mem).teleport(p.getLocation());
						}
					}
					else {
						if(p.getLocale().equalsIgnoreCase("ko_kr")) {
							p.sendMessage(ChatColor.LIGHT_PURPLE +"��Ƽ�常 �����մϴ�");
						}
						else {
							p.sendMessage(ChatColor.LIGHT_PURPLE +"You should be owner of a party");
						}
					}
				}
				else {
					Player p = (Player)sender;
					p.sendMessage(ChatColor.LIGHT_PURPLE +"invalid command");
					help(p);
				}
	            return true;
	            }
				catch (NullPointerException | NoSuchMethodError e)
				{
					Player p = (Player)sender;
					p.sendMessage("invalid command");
					help(p);
				}
			}
		}
		else
		{
			sender.sendMessage("invaild sender");
		}
		return false;
	}

	@EventHandler
	public void Partydamcan(EntityDamageByEntityEvent d) 
	{
		if(d.getDamager() instanceof Player && d.getEntity() instanceof Player)
		{
			Player damager = (Player) d.getDamager();
			Player damagee = (Player) d.getEntity();
			if(hasParty(damager)&&hasParty(damagee)) {
				if(getParty(damager).equals(getParty(damagee)))
				{
					d.setDamage(0);
					d.setCancelled(true);
					return;
				}
			}
		}
		else if(d.getDamager() instanceof Projectile && d.getEntity() instanceof Player)
		{
			Projectile p = (Projectile) d.getDamager();
			if(p.getShooter() instanceof Player) {
				Player damager = (Player) p.getShooter();
				Player damagee = (Player) d.getEntity();
	
				if(hasParty(damager)&&hasParty(damagee)) {
					if(getParty(damager).equals(getParty(damagee)))
					{
						d.setDamage(0);
						d.setCancelled(true);
						return;
					}
				}
			}
		}
	}

	@EventHandler
	public void Partydamcan(EntityDamageEvent d) 
	{
		if(d.getEntity() instanceof Player && !d.isCancelled())
		{
			Player p = (Player) d.getEntity();
			if(hasParty(p)) {
                Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
             		@Override
                	public void run() 
	                {
             			p.getScoreboard().getObjective(getParty(p)+"p").getScore(p.getName()).setScore((int)(p.getHealth()));
		            }
                }, 1);
			}
		}
	}

	@EventHandler
	public void Partydamcan(EntityRegainHealthEvent d)
	{
		if(d.getEntity() instanceof Player)
		{
			Player p = (Player) d.getEntity();
			if(hasParty(p)) {
                Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
             		@Override
                	public void run() 
	                {	
             			p.getScoreboard().getObjective(getParty(p)+"p").getScore(p.getName()).setScore((int)(p.getHealth()));
		            }
                }, 1);
			}
		}
	}

	@EventHandler
	public void Partydamcan(PlayerRespawnEvent d)
	{
		if(d.getPlayer() instanceof Player)
		{
			Player p = d.getPlayer();
			if(hasParty(p)) {
                Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
             		@Override
                	public void run() 
	                {	
             			p.getScoreboard().getObjective(getParty(p)+"p").getScore(p.getName()).setScore((int)(p.getHealth()));
		            }
                }, 1);
			}
		}
	}
	
	@EventHandler
	public void Leave (PlayerQuitEvent e)
	{
		final Player p = e.getPlayer();
		leave(p);

	}
	@EventHandler
	public void Reset (PluginDisableEvent e)
	{
		e.getPlugin().getServer().getWorlds().forEach(wor -> wor.getPlayers().forEach(p -> {
		if(hasParty(p))
			{
				removeParty(getParty(p));
			}
			
		}));
	}
	@EventHandler
	public void Expup (PlayerExpChangeEvent e)
	{
		Player p = e.getPlayer();
		
		if(hasParty(p) && !p.hasCooldown(Material.BEE_NEST)) {
			getMembers(getParty(p)).forEach(mem -> {
				Player mp =Bukkit.getPlayer(mem);
				if(mp != p) {
					mp.setCooldown(Material.BEE_NEST,2);
					mp.giveExp(e.getAmount());
					Bukkit.getServer().getPluginManager().callEvent(new PlayerExpChangeEvent(mp,e.getAmount()));
				}
			});
		}
		
	}

}
