package io.github.chw3021.monsters.raids;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.attribute.Attribute;
import org.bukkit.block.Block;
import org.bukkit.block.EndGateway;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.TextDisplay;
import org.bukkit.entity.Display.Billboard;
import org.bukkit.entity.TextDisplay.TextAlignment;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityTargetEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerExpChangeEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.server.PluginDisableEvent;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.Table;

import io.github.chw3021.commons.CommonEvents;
import io.github.chw3021.commons.Holding;
import io.github.chw3021.items.Elements;
import io.github.chw3021.monsters.Mobs;
import io.github.chw3021.party.Party;
import io.github.chw3021.party.PartyCreateEvent;
import io.github.chw3021.party.PartyJoinEvent;
import io.github.chw3021.party.PartyLeaveEvent;
import io.github.chw3021.rmain.RMain;

public class Summoned extends Mobs{
	

	private static final Summoned instance = new Summoned ();
	public static Summoned getInstance()
	{
		return instance;
	}

	Double TC = 100d;
	Integer MINCOMBO = 5;
	Integer BOSSCOMBO = 20;
	Integer MAXCOMBO = 200;
	Integer COMBOPER = 12;
	public Integer SUMMONCOUNT = 2;
	
	Integer COMBOTIME = 400;
	Integer BOSSTIME = 6000;
	
	
	static protected Table<String, String, HashSet<UUID>> raider = HashBasedTable.create();
	static protected Table<String, String, BossBar> raidbar = HashBasedTable.create();
	static protected Table<String, String, Integer> raidbart = HashBasedTable.create();

	static private Table<String, String, BossBar> combobar = HashBasedTable.create();
	static private Table<String, String, Integer> combobart = HashBasedTable.create();
	
	static public Table<String, String, Integer> combo = HashBasedTable.create();
	static public Table<String, String, Integer> combot = HashBasedTable.create();
	
	static private HashMap<String, UUID> raidporstand = new HashMap<String, UUID>();
	static private HashMap<String, Block> raidpor = new HashMap<String, Block>();
	
	static public Multimap<String, Integer> ordt = ArrayListMultimap.create();
	static public HashMap<UUID, Boolean> ordealable = new HashMap<UUID, Boolean>();
	public static HashMap<UUID, Boolean> ordeal = new HashMap<UUID, Boolean>();

	private Multimap<UUID, String> damaged = HashMultimap.create();
	
	public boolean checkAndApplyCharge(LivingEntity p, EntityDamageByEntityEvent d) {
	    // 체력 조건 확인 및 적용
		if(!p.hasMetadata("raid")) {
			return false;
		}
	    if ((p.getHealth() - d.getDamage() <= p.getAttribute(Attribute.MAX_HEALTH).getValue() * 0.2) 
	            && !ordealable.containsKey(p.getUniqueId()) 
	            && p.hasMetadata("ruined")) {
	        p.setHealth(p.getAttribute(Attribute.MAX_HEALTH).getValue() * 0.2);
	        d.setCancelled(true);
	        ordealable.put(p.getUniqueId(), true);
	        return true;
	    }
	    return false;
	}

	protected final void addraider(String rn, String meta, LivingEntity le)
	{
		if(raider.contains(rn, meta)) {
			raider.get(rn, meta).add(le.getUniqueId());
		}
		else{
			HashSet<UUID> hs = new HashSet<>();
			hs.add(le.getUniqueId());
			raider.put(rn, meta, hs);
		}
	}
	
	protected final String gethero(Entity entity)
	{
		if(entity.hasMetadata("summoned")) {
			String rn = entity.getMetadata("summoned").get(0).asString();
			return rn;
		}
		if(entity.hasMetadata("raid")) {
			String rn = entity.getMetadata("raid").get(0).asString();
			return rn;
		}
		else{
			return null;
		}
	}

	protected final Object getherotype(String rn)
	{
		if(Bukkit.getPlayer(rn) != null) {
			return Bukkit.getPlayer(rn);
		}
		else if (Party.isPartyexist(rn)) {
			HashSet<Player> par = new HashSet<>();
			Party.getMembers(rn).forEach(pu -> par.add(Bukkit.getPlayer(pu)));
			return par;
		}
		return null;
	}
	
	protected final String getheroname(Player p) {
		if(Party.hasParty(p)) {
			return Party.getParty(p);
		}
		else {
			return p.getName();
		}
	}

	final protected ChatColor getelcolor(LivingEntity le) {
		if(le.hasMetadata("plain")) {
			return ChatColor.GREEN;
		}
		else if(le.hasMetadata("mountains")) {
			return ChatColor.GRAY;
		}
		else if(le.hasMetadata("snowy")) {
			return ChatColor.AQUA;
		}
		else if(le.hasMetadata("ocean")) {
			return ChatColor.BLUE;
		}
		else if(le.hasMetadata("dark")) {
			return ChatColor.DARK_GRAY;
		}
		else if(le.hasMetadata("hyper")) {
			return ChatColor.LIGHT_PURPLE;
		}
		else if(le.hasMetadata("red")) {
			return ChatColor.RED;
		}
		else if(le.hasMetadata("poison")) {
			return ChatColor.DARK_GREEN;
		}
		else if(le.hasMetadata("wild")) {
			return ChatColor.YELLOW;
		}
		else if(le.hasMetadata("nether")) {
			return ChatColor.DARK_RED;
		}
		else if(le.hasMetadata("ender")) {
			return ChatColor.GOLD;
		}
		else if(le.hasMetadata("void")) {
			return ChatColor.STRIKETHROUGH;
		}
		else {
			return ChatColor.BLACK;
		}
	}

	final protected ChatColor getelcolor(String meta) {
		if(meta.equals("plain")) {
			return ChatColor.GREEN;
		}
		else if(meta.equals("mountains")) {
			return ChatColor.GRAY;
		}
		else if(meta.equals("snowy")) {
			return ChatColor.AQUA;
		}
		else if(meta.equals("ocean")) {
			return ChatColor.BLUE;
		}
		else if(meta.equals("dark")) {
			return ChatColor.DARK_GRAY;
		}
		else if(meta.equals("hyper")) {
			return ChatColor.LIGHT_PURPLE;
		}
		else if(meta.equals("red")) {
			return ChatColor.RED;
		}
		else if(meta.equals("poison")) {
			return ChatColor.DARK_GREEN;
		}
		else if(meta.equals("wild")) {
			return ChatColor.YELLOW;
		}
		else if(meta.equals("nether")) {
			return ChatColor.DARK_RED;
		}
		else if(meta.equals("ender")) {
			return ChatColor.GOLD;
		}
		else if(meta.equals("void")) {
			return ChatColor.STRIKETHROUGH;
		}
		else {
			return ChatColor.BLACK;
		}
	}
	
	
	final private void combobar(String rn, String meta) {

		if(!combobar.contains(rn, meta)) {
			BossBar	newbar = Bukkit.getServer().createBossBar(new NamespacedKey(RMain.getInstance(), rn + meta +"Combo"),(getelcolor(meta)+"[" + combo.get(rn, meta) +" COMBO]")+ChatColor.UNDERLINE, BarColor.PINK, BarStyle.SOLID, BarFlag.DARKEN_SKY);
	        newbar.setVisible(true);
	        newbar.setProgress(1d);
			combobar.put(rn,meta, newbar);
			
			final Object ht = getherotype(rn);
			if(ht instanceof Player) {
				Player p = (Player) ht;

				
	    		int task1 = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(RMain.getInstance(), new Runnable() {
	                @Override
	                public void run() 
	                {
	                	final BossBar rb = combobar.get(rn,meta);
	                	double comt = (double)COMBOTIME*(1.2-combo.get(rn, meta)/(double)BOSSCOMBO);
	        			if(combo.get(rn, meta)>=BOSSCOMBO) {
	        				comt = 40d;
	        			}
	                	
	                	final double prg = rb.getProgress() - 1d/comt;
	                	if(prg>=0&&prg<=1) {
		                	rb.setProgress(prg);
	                	}
	                	rb.setTitle((getelcolor(meta)+"<" + combo.get(rn, meta) +" COMBO!>")+ChatColor.UNDERLINE);
	    				rb.addPlayer(p);
	                }
				}, 1, 1);
	    		combobart.put(rn,meta, task1);
			}
			else if(getherotype(rn) instanceof HashSet){
				@SuppressWarnings("unchecked")
				HashSet<Player> par = (HashSet<Player>) ht;
	    		int task1 = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(RMain.getInstance(), new Runnable() {
	                @Override
	                public void run() 
	                {

	                	final BossBar rb = combobar.get(rn,meta);
	                	double comt = (double)COMBOTIME*(1.2-combo.get(rn, meta)/(double)BOSSCOMBO);
	        			if(combo.get(rn, meta)>=BOSSCOMBO) {
	        				comt = 40d;
	        			}
	                	final double prg = rb.getProgress() - 1d/comt;
	                	if(prg>=0&&prg<=1) {
		                	rb.setProgress(prg);
	                	}
	                	rb.setTitle((getelcolor(meta)+"[" + combo.get(rn, meta) +" COMBO!]")+ChatColor.UNDERLINE);
	            		par.forEach(p -> {
	        				combobar.get(rn,meta).addPlayer(p);
	            		});
	                }
				}, 1, 1);
	    		combobart.put(rn,meta, task1);
			}
		}
		else {
        	final BossBar rb = combobar.get(rn,meta);
        	rb.setProgress(1d);
		}
	}
	
	
	
	final private Integer comin(String rn, String meta) {
		
		if(raidbart.contains(rn,meta)){
			return 0;	
		}
		
		if(combot.contains(rn, meta)) {
			Bukkit.getScheduler().cancelTask(combot.get(rn, meta));
			combot.remove(rn, meta);
		}
		
		if(combo.contains(rn, meta)) {
			final int com = combo.get(rn, meta)+1;
			combo.put(rn, meta, com);
			
			double comt = (double)COMBOTIME*(1.2-combo.get(rn, meta)/(double)BOSSCOMBO);
			if(combo.get(rn, meta)>=BOSSCOMBO) {
				comt = 40d;
			}
			combobar(rn,meta);
			
			int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
				@Override
				public void run() {
					RaidFinish(rn, "","",0,meta);
				}
			},(long) (comt));
			combot.put(rn, meta, task);
			
			
			return com;
		}
		else {
			combo.put(rn, meta, 1);
			double comt = (double)COMBOTIME*(1.2-combo.get(rn, meta)/(double)BOSSCOMBO);
			if(combo.get(rn, meta)>=BOSSCOMBO) {
				comt = 40d;
			}
			combobar(rn,meta);
			int task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
				@Override
				public void run() {
					RaidFinish(rn, "","",0,meta);
				}
			},(long) (comt));
			combot.put(rn, meta, task);

			return 1;
		}
	}

	final private void timebar(String rn, String meta) {

		if(combobart.contains(rn,meta)) {
			Bukkit.getScheduler().cancelTask(combobart.get(rn, meta));
			combobart.remove(rn, meta);
		}
		if(combobar.contains(rn, meta)) {
        	final BossBar rb = combobar.get(rn,meta);
        	rb.setProgress(1d);
			final Object ht = getherotype(rn);
			if(ht instanceof Player) {
				Player p = (Player) ht;

				
	    		int task1 = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(RMain.getInstance(), new Runnable() {
	                @Override
	                public void run() 
	                {
	                	final double prg = rb.getProgress() - 1d/BOSSTIME;
	                	rb.setTitle((getelcolor(meta)+"TIMELEFT")+ChatColor.UNDERLINE);
	    				rb.addPlayer(p);
	                	if(prg>=0&&prg<=1) {
		                	rb.setProgress(prg);
	                	}
	                	else if (prg <0) {
	                		RaidFinish(rn,"","",0,meta);
	                	}
	                }
				}, 0, 1);
	    		combobart.put(rn,meta, task1);
			}
			else if(getherotype(rn) instanceof HashSet){
				@SuppressWarnings("unchecked")
				HashSet<Player> par = (HashSet<Player>) ht;
	    		int task1 = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(RMain.getInstance(), new Runnable() {
	                @Override
	                public void run() 
	                {
	                	final double prg = rb.getProgress() - 1d/BOSSTIME;
	                	rb.setTitle((getelcolor(meta)+"TIMELEFT")+ChatColor.UNDERLINE);
	            		par.forEach(p -> {
	        				combobar.get(rn,meta).addPlayer(p);
	            		});
	                	if(prg>=0&&prg<=1) {
		                	rb.setProgress(prg);
	                	}
	                	else if (prg <0) {
	                		RaidFinish(rn,"","",0,meta);
	                	}
	                }
				}, 0, 1);
	    		combobart.put(rn,meta, task1);
			}
		}
		else {
        	final BossBar rb = combobar.get(rn,meta);
        	rb.setProgress(1d);
		}
	}
	protected void Boss(String rn, String meta, Location esl) {

		timebar(rn,meta);
		if(combot.contains(rn,meta)) {
			Bukkit.getScheduler().cancelTask(combot.get(rn, meta));
			combot.remove(rn, meta);
		}
		
		if(raider.contains(rn,meta)) {
			raider.get(rn,meta).forEach(re -> {
				if(Bukkit.getEntity(re) != null) {
					Bukkit.getEntity(re).remove();
	    		}
			});
			raider.remove(rn, meta);
		}
        
	}
	
	@SuppressWarnings("unchecked")
	protected void bossBar(String rn, String META, LivingEntity newmob) {

		final Object ht = getherotype(rn);
		
		if(ht instanceof Player) {
			Player p = (Player) ht;
			int task = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(RMain.getInstance(), new Runnable() {
	            @Override
	            public void run() 
	            {
	
					if(Holding.ale(newmob)!=null) {
	                	raidbar.get(rn, META).setProgress((double)Holding.ale(newmob).getHealth()/Holding.ale(newmob).getAttribute(Attribute.MAX_HEALTH).getValue());
	                	raidbar.get(rn, META).setTitle(Holding.ale(newmob).getName());
	    				raidbar.get(rn, META).addPlayer(p);
					}
	            }
			}, 0, 1);
			raidbart.put(rn, META, task);
		}
		else if(getherotype(rn) instanceof HashSet){
			HashSet<Player> par = (HashSet<Player>) ht;
			int task = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(RMain.getInstance(), new Runnable() {
	            @Override
	            public void run() 
	            {
					if(Holding.ale(newmob)!=null) {
	                	raidbar.get(rn, META).setProgress((double)Holding.ale(newmob).getHealth()/Holding.ale(newmob).getAttribute(Attribute.MAX_HEALTH).getValue());
	                	raidbar.get(rn, META).setTitle(Holding.ale(newmob).getName());
	            		par.forEach(p -> {
	        				raidbar.get(rn, META).addPlayer(p);
	            		});
					}
	            }
			}, 0, 1);
			raidbart.put(rn, META, task);
		}
		
	}
	
	final protected Integer getelnum(String meta) {
		if(meta.equals("plain")) {
			return 14;
		}
		else if(meta.equals("mountains")) {
			return 5;
		}
		else if(meta.equals("snowy")) {
			return 6;
		}
		else if(meta.equals("ocean")) {
			return 7;
		}
		else if(meta.equals("dark")) {
			return 8;
		}
		else if(meta.equals("hyper")) {
			return 9;
		}
		else if(meta.equals("red")) {
			return 10;
		}
		else if(meta.equals("poison")) {
			return 11;
		}
		else if(meta.equals("wild")) {
			return 12;
		}
		else if(meta.equals("nether")) {
			Random ran = new Random();
			return ran.nextInt(-5, -1);
		}
		else if(meta.equals("ender")) {
			return -6;
		}
		else if(meta.equals("void")) {
			return -7;
		}
		else {
			return 0;
		}
	}
	
	final protected Integer getelnum(LivingEntity le) {
		if(le.hasMetadata("plain")) {
			return 14;
		}
		else if(le.hasMetadata("mountains")) {
			return 5;
		}
		else if(le.hasMetadata("snowy")) {
			return 6;
		}
		else if(le.hasMetadata("ocean")) {
			return 7;
		}
		else if(le.hasMetadata("dark")) {
			return 8;
		}
		else if(le.hasMetadata("hyper")) {
			return 9;
		}
		else if(le.hasMetadata("red")) {
			return 10;
		}
		else if(le.hasMetadata("poison")) {
			return 11;
		}
		else if(le.hasMetadata("wild")) {
			return 12;
		}
		else if(le.hasMetadata("nether")) {
			Random ran = new Random();
			return ran.nextInt(-5, -1);
		}
		else if(le.hasMetadata("ender")) {
			return -6;
		}
		else if(le.hasMetadata("void")) {
			return -7;
		}
		else {
			return 0;
		}
	}
	final protected String getmeta(LivingEntity le) {
		if(le.hasMetadata("plain")) {
			return "plain";
		}
		else if(le.hasMetadata("mountains")) {
			return "mountains";
		}
		else if(le.hasMetadata("snowy")) {
			return "snowy";
		}
		else if(le.hasMetadata("ocean")) {
			return "ocean";
		}
		else if(le.hasMetadata("dark")) {
			return "dark";
		}
		else if(le.hasMetadata("hyper")) {
			return "hyper";
		}
		else if(le.hasMetadata("red")) {
			return "red";
		}
		else if(le.hasMetadata("poison")) {
			return "poison";
		}
		else if(le.hasMetadata("wild")) {
			return "wild";
		}
		else if(le.hasMetadata("nether")) {
			return "nether";
		}
		else if(le.hasMetadata("ender")) {
			return "ender";
		}
		else if(le.hasMetadata("void")) {
			return "void";
		}
		else {
			return null;
		}
	}
	
	final private void Effect(Player p) {

		cleans(p);
    	
		p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 300, 5, false, false));
		p.addPotionEffect(new PotionEffect(PotionEffectType.RESISTANCE, 300, 5, false, false));
		p.addPotionEffect(new PotionEffect(PotionEffectType.WATER_BREATHING, 300, 5, false, false));
		p.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 300, 5, false, false));
		p.addPotionEffect(new PotionEffect(PotionEffectType.STRENGTH, 300, 5, false, false));
		p.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 300, 5, false, false));
		p.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, 300, 5, false, false));
    	p.setSaturation(1f);
		if(p.getLocale().equalsIgnoreCase("ko_kr")) {
    		p.sendMessage("15초간 이로운상태들을 획득합니다");
		}
		else {
    		p.sendMessage("Get Positive Effects 15 seconds");
		}
	}

	final protected Integer getelexp(String meta) {
		if(meta.equals("plain")) {
			return 1;
		}
		else if(meta.equals("mountains")) {
			return 2;
		}
		else if(meta.equals("snowy")) {
			return 3;
		}
		else if(meta.equals("ocean")) {
			return 4;
		}
		else if(meta.equals("dark")) {
			return 5;
		}
		else if(meta.equals("hyper")) {
			return 6;
		}
		else if(meta.equals("red")) {
			return 7;
		}
		else if(meta.equals("poison")) {
			return 8;
		}
		else if(meta.equals("wild")) {
			return 9;
		}
		else if(meta.equals("nether")) {
			return 12;
		}
		else if(meta.equals("ender")) {
			return 14;
		}
		else if(meta.equals("void")) {
			return 15;
		}
		else {
			return 0;
		}
	}
	@SuppressWarnings("unchecked")
	final protected void Reward(String meta, String rn, int com) {
		if(com <MINCOMBO && com!=-1) {
			return;
		}
		
		if(getherotype(rn) instanceof Player) {
			Player p = (Player) getherotype(rn);
			int count = com/COMBOPER;
			if(meta.equals("nether")) {
				
			}
			else if(meta.equals("ender")) {
				
			}
			else {
	        	Elements.give(Elements.getel(getelnum(meta), p), count, p);
			}
			if(com==-1) {
				int exp = 2*getelexp(meta)*BOSSCOMBO;
	        	p.giveExp(exp);
				Bukkit.getServer().getPluginManager().callEvent(new PlayerExpChangeEvent(p,exp));
				if(p.getLocale().equalsIgnoreCase("ko_kr")) {
					p.sendMessage(ChatColor.GOLD + "" + exp + " 경험치를 획득했습니다");
		        	Effect(p);
					p.sendMessage(ChatColor.GOLD + "보스 처치 보상이 지급되었습니다");
				}
				else {
					p.sendMessage(ChatColor.GOLD + "You've got " + exp + " experience point");
		        	Effect(p);
					p.sendMessage(ChatColor.GOLD + "You've just got Boss Reward");
				}
			}
			else if(com>=MINCOMBO && com<BOSSCOMBO){
				int exp = getelexp(meta)*BOSSCOMBO/2/(2+BOSSCOMBO-com);
	        	p.giveExp(exp);
				Bukkit.getServer().getPluginManager().callEvent(new PlayerExpChangeEvent(p,exp));
				if(p.getLocale().equalsIgnoreCase("ko_kr")) {
					p.sendMessage(ChatColor.GOLD + "" + exp + " 경험치를 획득했습니다");
					p.sendMessage(ChatColor.GOLD + "" + com + " 콤보 보상이 지급되었습니다");
				}
				else {
					p.sendMessage(ChatColor.GOLD + "You've got " + exp + " experience point");
					p.sendMessage(ChatColor.GOLD + "You've just got " + com + " Combo Reward");
				}
			}
			else if(com>=BOSSCOMBO){
				int exp = getelexp(meta)*BOSSCOMBO*(1+com/COMBOPER);
	        	p.giveExp(exp);
				Bukkit.getServer().getPluginManager().callEvent(new PlayerExpChangeEvent(p,exp));
				if(p.getLocale().equalsIgnoreCase("ko_kr")) {
					p.sendMessage(ChatColor.GOLD + "" + exp + " 경험치를 획득했습니다");
					p.sendMessage(ChatColor.GOLD + "" + com + " 콤보 보상이 지급되었습니다");
				}
				else {
					p.sendMessage(ChatColor.GOLD + "You've got " + exp + " experience point");
					p.sendMessage(ChatColor.GOLD + "You've just got " + com + " Combo Reward");
				}
			}
		}
		else if(getherotype(rn) instanceof HashSet){
			HashSet<Player> par = (HashSet<Player>) getherotype(rn);
			Player f =par.stream().findFirst().get();
			
			for(Player p : par){
				if(p.getLevel() < f.getLevel()) {
					f = p;
				}
			}

			if(com==-1) {
				int exp = 2*getelexp(meta)*BOSSCOMBO;
				f.giveExp(exp);
				Bukkit.getServer().getPluginManager().callEvent(new PlayerExpChangeEvent(f,exp));
			}
			else if(com>=MINCOMBO && com<BOSSCOMBO){
				int exp = getelexp(meta)*BOSSCOMBO/2/(2+BOSSCOMBO-com);
				f.giveExp(exp);
				Bukkit.getServer().getPluginManager().callEvent(new PlayerExpChangeEvent(f,exp));
			}
			else if(com>=BOSSCOMBO) {
				int	exp = getelexp(meta)*BOSSCOMBO*(1+com/COMBOPER);
				f.giveExp(exp);
				Bukkit.getServer().getPluginManager().callEvent(new PlayerExpChangeEvent(f,exp));
			}
			
			par.forEach(p ->{
				int count = com/COMBOPER/par.size();
	        	Elements.give(Elements.getel(getelnum(meta), p), count, p);
				if(com>BOSSCOMBO) {
					int exp = 2*getelexp(meta)*BOSSCOMBO;
					if(p.getLocale().equalsIgnoreCase("ko_kr")) {
						p.sendMessage(ChatColor.GOLD + "" + exp + " 경험치를 획득했습니다");
			        	Effect(p);
						p.sendMessage(ChatColor.GOLD + "보스 처치 보상이 지급되었습니다");
					}
					else {
						p.sendMessage(ChatColor.GOLD + "You've got " + exp + " experience point");
			        	Effect(p);
						p.sendMessage(ChatColor.GOLD + "You've just got Boss Reward");
					}
				}
				else if(com>=MINCOMBO){
					int exp = getelexp(meta)*BOSSCOMBO/2/(2+BOSSCOMBO-com);;
					if(p.getLocale().equalsIgnoreCase("ko_kr")) {
						p.sendMessage(ChatColor.GOLD + "" + exp + " 경험치를 획득했습니다");
						p.sendMessage(ChatColor.GOLD + "" + com + " 콤보 보상이 지급되었습니다");
					}
					else {
						p.sendMessage(ChatColor.GOLD + "You've got " + exp + " experience point");
						p.sendMessage(ChatColor.GOLD + "You've just got " + com + " Combo Reward");
					}
				}
			});
		}
	}
	
	
	
	public void Victory(EntityDeathEvent d) 
	{		
		if(d.getEntity().hasMetadata("summonedboss")) {
			final LivingEntity le = d.getEntity();
			final Location lel = le.getLocation().clone().add(0, 1.5, 0);
			String rn = le.getMetadata("summoned").get(0).asString();
			String meta = getmeta(le);
			if(raider.get(rn, meta).contains(le.getUniqueId())) {
    			le.getWorld().playSound(lel, Sound.ENTITY_WITHER_DEATH, 0.6f, 0);
    			le.getWorld().playSound(lel, Sound.ENTITY_GUARDIAN_HURT, 0.1f, 0);
    			le.getWorld().playSound(lel, Sound.ENTITY_WITHER_HURT, 0.1f, 0);
    			le.getWorld().spawnParticle(Particle.END_ROD, lel, 300,1,1,1,5);
    			le.getWorld().spawnParticle(Particle.PORTAL, lel, 300,1,1,1,5);

	        	for(int i =0; i<30; i++) {
	            	   Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
			                @Override
			                public void run() 
			                {

			        			le.getWorld().spawnParticle(Particle.TOTEM_OF_UNDYING, lel, 300,1,1,1);
			        			le.getWorld().spawnParticle(Particle.CRIMSON_SPORE, lel, 100,1,1,1);
			        			le.getWorld().spawnParticle(Particle.REVERSE_PORTAL, lel, 300,1,1,1);
			        			le.getWorld().spawnParticle(Particle.SOUL_FIRE_FLAME, lel, 300,1,1,1);
			        			le.getWorld().spawnParticle(Particle.FLAME, lel, 100,1,1,1);
			        			le.getWorld().spawnParticle(Particle.SOUL, lel, 100,1,1,1);
			                }
	            	   }, i*3); 
				}
	        	
				RaidFinish(rn, "","",1,getmeta(le));
			}
		}
	}


	@SuppressWarnings("unchecked")
	private final void AncientPortal(String rn, Integer combo, LivingEntity le) {

		if(raidporstand.containsKey(rn)) {
			if(Bukkit.getEntity(raidporstand.get(rn)) != null) {
				Bukkit.getEntity(raidporstand.get(rn)).remove();
			}
			raidporstand.remove(rn);
		}
		if(raidpor.containsKey(rn)) {
			raidpor.get(rn).setType(Material.VOID_AIR);
			raidpor.remove(rn);
		}
		Location sl = le.getLocation().clone().add(0, 1, 0);
		if(!sl.getBlock().isEmpty()) {
			sl = CommonEvents.getInstance().BlankFinder(sl).clone().add(1, 0, 1);
		}
		sl.getBlock().setType(Material.END_GATEWAY);
		EndGateway eg = (EndGateway) le.getWorld().getBlockState(sl);
		eg.setExactTeleport(false);
		eg.setExitLocation(null);
		eg.setAge(0);
		eg.update();
		eg.setMetadata("OverworldRaidPortal", new FixedMetadataValue(RMain.getInstance(), rn));
		eg.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), rn));
		raidpor.put(rn, eg.getBlock());

		
		TextDisplay as = le.getWorld().spawn(sl.clone().add(0, 2.5, 0), TextDisplay.class, a ->{
			a.setInvulnerable(true);
			a.setGravity(false);
			a.setAlignment(TextAlignment.CENTER);
			a.setGlowing(true);
			a.setBackgroundColor(Color.WHITE);
			a.setBillboard(Billboard.CENTER);
			a.setSeeThrough(true);
			a.setViewRange(15f);
			a.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), rn));
			a.setMetadata("rob", new FixedMetadataValue(RMain.getInstance(), rn));
			raidporstand.put(rn, a.getUniqueId());
			a.setCustomNameVisible(false);
		});
		
		if(getherotype(rn) instanceof Player) {
			Player p = (Player) getherotype(rn);
			Holding.invur(p, 100l);
			p.teleport(CommonEvents.getInstance().BlankFinder(sl.clone().add(2, 0, 2)));
			if(p.getLocale().equalsIgnoreCase("ko_kr")) {
				as.setText(ChatColor.DARK_RED +"우클릭(맨손)으로 입장이 가능합니다");
				p.sendTitle(ChatColor.RED +"고대의 포탈 소환", ChatColor.DARK_RED +"우클릭(맨손)으로 입장이 가능합니다", 10,35, 10);
				p.sendMessage(ChatColor.RED +"고대의 포탈 소환", ChatColor.DARK_RED +"우클릭(맨손)으로 입장이 가능합니다");
			}
			else {
				as.setText(ChatColor.DARK_RED +"Enter by RightClick(Barehand)");
				p.sendTitle(ChatColor.RED +"Ancient Portal Summoned", ChatColor.DARK_RED +"Enter by RightClick(Barehand)", 10,35, 10);
				p.sendMessage(ChatColor.RED +"Ancient Portal Summoned", ChatColor.DARK_RED +"Enter by RightClick(Barehand)");
			}
		}
		else if(getherotype(rn) instanceof HashSet){
			HashSet<Player> par = (HashSet<Player>) getherotype(rn);
			par.forEach(p ->{
				Holding.invur(p, 100l);
				Location lel = le.getLocation().clone().add(2, 1, 2);
				if(!lel.getBlock().isEmpty()) {
					lel = CommonEvents.getInstance().BlankFinder(lel);
				}
				p.teleport(lel);
				if(p.getLocale().equalsIgnoreCase("ko_kr")) {
					as.setText(ChatColor.DARK_RED +"우클릭(맨손)으로 입장이 가능합니다");
					p.sendTitle(ChatColor.RED +"고대의 포탈 소환", ChatColor.DARK_RED +"우클릭(맨손)으로 입장이 가능합니다", 10,35, 10);
					p.sendMessage(ChatColor.RED +"고대의 포탈 소환", ChatColor.DARK_RED +"우클릭(맨손)으로 입장이 가능합니다");
				}
				else {
					as.setText(ChatColor.DARK_RED +"Enter by RightClick(Barehand)");
					p.sendTitle(ChatColor.RED +"Ancient Portal Summoned", ChatColor.DARK_RED +"Enter by RightClick(Barehand)", 10,35, 10);
					p.sendMessage(ChatColor.RED +"Ancient Portal Summoned", ChatColor.DARK_RED +"Enter by RightClick(Barehand)");
				}
			});
		}
	
	}

	@SuppressWarnings("unchecked")
	private final void NethercorePortal(String rn, Integer combo, LivingEntity le) {

		if(raidporstand.containsKey(rn)) {
			if(Bukkit.getEntity(raidporstand.get(rn)) != null) {
				Bukkit.getEntity(raidporstand.get(rn)).remove();
			}
			raidporstand.remove(rn);
		}
		if(raidpor.containsKey(rn)) {
			raidpor.get(rn).setType(Material.VOID_AIR);
			raidpor.remove(rn);
		}
		Location sl = le.getLocation().clone().add(0, 1, 0);
		if(!sl.getBlock().isEmpty()) {
			sl = CommonEvents.getInstance().BlankFinder(sl).clone().add(1, 0, 1);
		}
		sl.getBlock().setType(Material.END_GATEWAY);
		EndGateway eg = (EndGateway) le.getWorld().getBlockState(sl);
		eg.setExactTeleport(false);
		eg.setExitLocation(null);
		eg.setAge(10000);
		eg.update();
		eg.setMetadata("NethercoreRaidPortal", new FixedMetadataValue(RMain.getInstance(), rn));
		eg.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), rn));
		raidpor.put(rn, eg.getBlock());


		TextDisplay as = le.getWorld().spawn(sl.clone().add(0, 2.5, 0), TextDisplay.class, a ->{
			a.setInvulnerable(true);
			a.setGravity(false);
			a.setAlignment(TextAlignment.CENTER);
			a.setGlowing(true);
			a.setBackgroundColor(Color.WHITE);
			a.setBillboard(Billboard.CENTER);
			a.setSeeThrough(true);
			a.setViewRange(15f);
			a.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), rn));
			a.setMetadata("rob", new FixedMetadataValue(RMain.getInstance(), rn));
			raidporstand.put(rn, a.getUniqueId());
			a.setCustomNameVisible(false);
		});
		
		if(getherotype(rn) instanceof Player) {
			Player p = (Player) getherotype(rn);
			Holding.invur(p, 100l);
			p.teleport(CommonEvents.getInstance().BlankFinder(sl.clone().add(2, 0, 2)));
			if(p.getLocale().equalsIgnoreCase("ko_kr")) {
				as.setText(ChatColor.DARK_RED +"우클릭(맨손)으로 입장이 가능합니다");
				p.sendTitle(ChatColor.RED +"네더 코어 포탈 소환", ChatColor.DARK_RED +"우클릭(맨손)으로 입장이 가능합니다", 10,35, 10);
				p.sendMessage(ChatColor.RED +"네더 코어 포탈 소환", ChatColor.DARK_RED +"우클릭(맨손)으로 입장이 가능합니다");
			}
			else {
				as.setText(ChatColor.DARK_RED +"Enter by RightClick(Barehand)");
				p.sendTitle(ChatColor.RED +"Nether Core Portal Summoned", ChatColor.DARK_RED +"Enter by RightClick(Barehand)", 10,35, 10);
				p.sendMessage(ChatColor.RED +"Nether Core Portal Summoned", ChatColor.DARK_RED +"Enter by RightClick(Barehand)");
			}
		}
		else if(getherotype(rn) instanceof HashSet){
			HashSet<Player> par = (HashSet<Player>) getherotype(rn);
			par.forEach(p ->{
				Holding.invur(p, 100l);
				Location lel = le.getLocation().clone().add(2, 1, 2);
				if(!lel.getBlock().isEmpty()) {
					lel = CommonEvents.getInstance().BlankFinder(lel);
				}
				if(p.getLocale().equalsIgnoreCase("ko_kr")) {
					as.setText(ChatColor.DARK_RED +"우클릭(맨손)으로 입장이 가능합니다");
					p.sendTitle(ChatColor.RED +"네더 코어 포탈 소환", ChatColor.DARK_RED +"우클릭(맨손)으로 입장이 가능합니다", 10,35, 10);
					p.sendMessage(ChatColor.RED +"네더 코어 포탈 소환", ChatColor.DARK_RED +"우클릭(맨손)으로 입장이 가능합니다");
				}
				else {
					as.setText(ChatColor.DARK_RED +"Enter by RightClick(Barehand)");
					p.sendTitle(ChatColor.RED +"Nether Core Portal Summoned", ChatColor.DARK_RED +"Enter by RightClick(Barehand)", 10,35, 10);
					p.sendMessage(ChatColor.RED +"Nether Core Portal Summoned", ChatColor.DARK_RED +"Enter by RightClick(Barehand)");
				}
			});
		}
	
	}

	@SuppressWarnings("unchecked")
	private final void EndercorePortal(String rn, Integer combo, LivingEntity le) {

		if(raidporstand.containsKey(rn)) {
			if(Bukkit.getEntity(raidporstand.get(rn)) != null) {
				Bukkit.getEntity(raidporstand.get(rn)).remove();
			}
			raidporstand.remove(rn);
		}
		if(raidpor.containsKey(rn)) {
			raidpor.get(rn).setType(Material.VOID_AIR);
			raidpor.remove(rn);
		}
		Location sl = le.getLocation().clone().add(0, 1, 0);
		if(!sl.getBlock().isEmpty()) {
			sl = CommonEvents.getInstance().BlankFinder(sl).clone().add(1, 0, 1);
		}
		sl.getBlock().setType(Material.END_GATEWAY);
		EndGateway eg = (EndGateway) le.getWorld().getBlockState(sl);
		eg.setExactTeleport(false);
		eg.setExitLocation(null);
		eg.setAge(50000);
		eg.update();
		eg.setMetadata("EndercoreRaidPortal", new FixedMetadataValue(RMain.getInstance(), rn));
		eg.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), rn));
		raidpor.put(rn, eg.getBlock());


		TextDisplay as = le.getWorld().spawn(sl.clone().add(0, 2.5, 0), TextDisplay.class, a ->{
			a.setInvulnerable(true);
			a.setGravity(false);
			a.setAlignment(TextAlignment.CENTER);
			a.setGlowing(true);
			a.setBackgroundColor(Color.WHITE);
			a.setBillboard(Billboard.CENTER);
			a.setSeeThrough(true);
			a.setViewRange(15f);
			a.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), rn));
			a.setMetadata("rob", new FixedMetadataValue(RMain.getInstance(), rn));
			raidporstand.put(rn, a.getUniqueId());
			a.setCustomNameVisible(false);
		});
		
		if(getherotype(rn) instanceof Player) {
			Player p = (Player) getherotype(rn);
			Holding.invur(p, 100l);
			p.teleport(CommonEvents.getInstance().BlankFinder(sl.clone().add(2, 0, 2)));
			if(p.getLocale().equalsIgnoreCase("ko_kr")) {
				as.setText(ChatColor.DARK_RED +"우클릭(맨손)으로 입장이 가능합니다");
				p.sendTitle(ChatColor.RED +"엔더 코어 포탈 소환", ChatColor.DARK_RED +"우클릭(맨손)으로 입장이 가능합니다", 10,35, 10);
				p.sendMessage(ChatColor.RED +"엔더 코어 포탈 소환", ChatColor.DARK_RED +"우클릭(맨손)으로 입장이 가능합니다");
			}
			else {
				as.setText(ChatColor.DARK_RED +"Enter by RightClick(Barehand)");
				p.sendTitle(ChatColor.RED +"Ender Core Portal Summoned", ChatColor.DARK_RED +"Enter by RightClick(Barehand)", 10,35, 10);
				p.sendMessage(ChatColor.RED +"Ender Core Portal Summoned", ChatColor.DARK_RED +"Enter by RightClick(Barehand)");
			}
		}
		else if(getherotype(rn) instanceof HashSet){
			HashSet<Player> par = (HashSet<Player>) getherotype(rn);
			par.forEach(p ->{
				Holding.invur(p, 100l);
				Location lel = le.getLocation().clone().add(2, 1, 2);
				if(!lel.getBlock().isEmpty()) {
					lel = CommonEvents.getInstance().BlankFinder(lel);
				}
				if(p.getLocale().equalsIgnoreCase("ko_kr")) {
					as.setText(ChatColor.DARK_RED +"우클릭(맨손)으로 입장이 가능합니다");
					p.sendTitle(ChatColor.RED +"엔더 코어 포탈 소환", ChatColor.DARK_RED +"우클릭(맨손)으로 입장이 가능합니다", 10,35, 10);
					p.sendMessage(ChatColor.RED +"엔더 코어 포탈 소환", ChatColor.DARK_RED +"우클릭(맨손)으로 입장이 가능합니다");
				}
				else {
					as.setText(ChatColor.DARK_RED +"Enter by RightClick(Barehand)");
					p.sendTitle(ChatColor.RED +"Ender Core Portal Summoned", ChatColor.DARK_RED +"Enter by RightClick(Barehand)", 10,35, 10);
					p.sendMessage(ChatColor.RED +"Ender Core Portal Summoned", ChatColor.DARK_RED +"Enter by RightClick(Barehand)");
				}
			});
		}
	
	}
	


	@SuppressWarnings("unchecked")
	private final void WitherPortal(String rn, Integer combo, LivingEntity le) {

		if(raidporstand.containsKey(rn)) {
			if(Bukkit.getEntity(raidporstand.get(rn)) != null) {
				Bukkit.getEntity(raidporstand.get(rn)).remove();
			}
			raidporstand.remove(rn);
		}
		if(raidpor.containsKey(rn)) {
			raidpor.get(rn).setType(Material.VOID_AIR);
			raidpor.remove(rn);
		}
		Location sl = le.getLocation().clone().add(0, 1, 0);
		if(!sl.getBlock().isEmpty()) {
			sl = CommonEvents.getInstance().BlankFinder(sl).clone().add(1, 0, 1);
		}
		sl.getBlock().setType(Material.END_GATEWAY);
		EndGateway eg = (EndGateway) le.getWorld().getBlockState(sl);
		eg.setExactTeleport(false);
		eg.setExitLocation(null);
		eg.setAge(50000);
		eg.update();
		eg.setMetadata("WitherRaidPortal", new FixedMetadataValue(RMain.getInstance(), rn));
		eg.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), rn));
		raidpor.put(rn, eg.getBlock());


		TextDisplay as = le.getWorld().spawn(sl.clone().add(0, 2.5, 0), TextDisplay.class, a ->{
			a.setInvulnerable(true);
			a.setGravity(false);
			a.setAlignment(TextAlignment.CENTER);
			a.setGlowing(true);
			a.setBackgroundColor(Color.WHITE);
			a.setBillboard(Billboard.CENTER);
			a.setSeeThrough(true);
			a.setViewRange(15f);
			a.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), rn));
			a.setMetadata("rob", new FixedMetadataValue(RMain.getInstance(), rn));
			raidporstand.put(rn, a.getUniqueId());
			a.setCustomNameVisible(false);
		});
		
		if(getherotype(rn) instanceof Player) {
			Player p = (Player) getherotype(rn);
			Holding.invur(p, 100l);
			p.teleport(CommonEvents.getInstance().BlankFinder(sl.clone().add(2, 0, 2)));
			if(p.getLocale().equalsIgnoreCase("ko_kr")) {
				as.setText(ChatColor.DARK_RED +"우클릭(맨손)으로 입장이 가능합니다");
				p.sendTitle(ChatColor.RED +"위더 포탈 소환", ChatColor.DARK_RED +"우클릭(맨손)으로 입장이 가능합니다", 10,35, 10);
				p.sendMessage(ChatColor.RED +"위더 포탈 소환", ChatColor.DARK_RED +"우클릭(맨손)으로 입장이 가능합니다");
			}
			else {
				as.setText(ChatColor.DARK_RED +"Enter by RightClick(Barehand)");
				p.sendTitle(ChatColor.RED +"WitherRaidPortal Summoned", ChatColor.DARK_RED +"Enter by RightClick(Barehand)", 10,35, 10);
				p.sendMessage(ChatColor.RED +"WitherRaidPortal Summoned", ChatColor.DARK_RED +"Enter by RightClick(Barehand)");
			}
		}
		else if(getherotype(rn) instanceof HashSet){
			HashSet<Player> par = (HashSet<Player>) getherotype(rn);
			par.forEach(p ->{
				Holding.invur(p, 100l);
				Location lel = le.getLocation().clone().add(2, 1, 2);
				if(!lel.getBlock().isEmpty()) {
					lel = CommonEvents.getInstance().BlankFinder(lel);
				}
				if(p.getLocale().equalsIgnoreCase("ko_kr")) {
					as.setText(ChatColor.DARK_RED +"우클릭(맨손)으로 입장이 가능합니다");
					p.sendTitle(ChatColor.RED +"위더 포탈 소환", ChatColor.DARK_RED +"우클릭(맨손)으로 입장이 가능합니다", 10,35, 10);
					p.sendMessage(ChatColor.RED +"위더 포탈 소환", ChatColor.DARK_RED +"우클릭(맨손)으로 입장이 가능합니다");
				}
				else {
					as.setText(ChatColor.DARK_RED +"Enter by RightClick(Barehand)");
					p.sendTitle(ChatColor.RED +"WitherRaidPortal Summoned", ChatColor.DARK_RED +"Enter by RightClick(Barehand)", 10,35, 10);
					p.sendMessage(ChatColor.RED +"WitherRaidPortal Summoned", ChatColor.DARK_RED +"Enter by RightClick(Barehand)");
				}
			});
		}
	
	}
	
	
	@SuppressWarnings("unchecked")
	private final void ComboMessage(String rn, Integer combo, Boolean boss) {

		if(boss) {
			if(getherotype(rn) instanceof Player) {
				Player p = (Player) getherotype(rn);
				if(p.getLocale().equalsIgnoreCase("ko_kr")) {
					p.sendMessage(ChatColor.RED +""+ combo + "콤보 달성!", ChatColor.DARK_RED +"보스몹이 소환됩니다");
				}
				else {
					p.sendMessage(ChatColor.RED +""+ combo + "Combo!", ChatColor.DARK_RED +"Boss Monster will be Summoned");
				}
			}
			else if(getherotype(rn) instanceof HashSet){
				HashSet<Player> par = (HashSet<Player>) getherotype(rn);
				par.forEach(p ->{
					if(p.getLocale().equalsIgnoreCase("ko_kr")) {
						p.sendMessage(ChatColor.RED +""+ combo + "콤보 달성!", ChatColor.DARK_RED +"보스몹이 소환됩니다");
					}
					else {
						p.sendMessage(ChatColor.RED +""+ combo + "Combo!", ChatColor.DARK_RED +"Boss Monster will be Summoned");
					}
				});
			}
		}
		else {
			if(getherotype(rn) instanceof Player) {
				Player p = (Player) getherotype(rn);
				if(p.getLocale().equalsIgnoreCase("ko_kr")) {
					p.sendMessage(ChatColor.RED +""+ combo + "콤보 달성!", ChatColor.DARK_RED +"몬스터들이 소환됩니다");
				}
				else {
					p.sendMessage(ChatColor.RED +""+ combo + "Combo!", ChatColor.DARK_RED +"Monster will be Summoned");
				}
			}
			else if(getherotype(rn) instanceof HashSet){
				HashSet<Player> par = (HashSet<Player>) getherotype(rn);
				par.forEach(p ->{
					if(p.getLocale().equalsIgnoreCase("ko_kr")) {
						p.sendMessage(ChatColor.RED +""+ combo + "콤보 달성!", ChatColor.DARK_RED +"몬스터들이 소환됩니다");
					}
					else {
						p.sendMessage(ChatColor.RED +""+ combo + "Combo!", ChatColor.DARK_RED +"Monster will be Summoned");
					}
				});
			}
		}
	}
	
	public final Object Combo(EntityDeathEvent d, String meta) 
	{		
		final LivingEntity le = d.getEntity();

		if(le.hasMetadata(meta) && !le.hasMetadata("raid")) {
			if(le.hasMetadata("summoned") && !le.hasMetadata("boss")) {
				final String rn = gethero(le);
				int combo = comin(rn,meta);

				if(meta.equals("wild")) {

					if(combo==MINCOMBO) {
						AncientPortal(rn,combo,le);
						return rn;
					}
					else if(combo % COMBOPER == 0 && combo>=COMBOPER) {
						ComboMessage(rn,combo,false);
						return rn;
					}
					else if(combo >= MAXCOMBO) {
                		RaidFinish(rn,"","",0,meta);
					}
				}
				else if(meta.equals("nether")) {

					if(combo==MINCOMBO) {
						NethercorePortal(rn,combo,le);
						return rn;
					}
					else if(combo % COMBOPER == 0 && combo>=COMBOPER) {
						ComboMessage(rn,combo,false);
						return rn;
					}
					else if(combo >= MAXCOMBO) {
                		RaidFinish(rn,"","",0,meta);
					}
				}
				else {
					if(combo==MINCOMBO) {
						ComboMessage(rn,combo,false);
						return rn;
					}
					else if(combo % COMBOPER == 0 && combo>=COMBOPER && combo < BOSSCOMBO) {
						ComboMessage(rn,combo,false);
						return rn;

					}
					else if(combo == BOSSCOMBO) {
						ComboMessage(rn,combo,true);
						Boss(rn,meta, le.getLocation().clone());
					}
					return null;
				}
			}
			else {
				if(damaged.containsKey(le.getUniqueId())) {
					HashSet<String> hs = new HashSet<>();
					damaged.get(le.getUniqueId()).forEach(rn -> {
						int combo = comin(rn,meta);

						if(meta.equals("enderMimic")) {

							if(combo==1) {
								EndercorePortal(rn, combo, le);
								hs.add(rn);
							}
						}
						if(meta.equals("wither")) {

							if(combo==1) {
								WitherPortal(rn, combo, le);
								hs.add(rn);
							}
						}
						
						if(meta.equals("wild")) {

							if(combo==MINCOMBO) {
								AncientPortal(rn,combo,le);
								hs.add(rn);
							}
							else if(combo % COMBOPER == 0 && combo>=COMBOPER) {
								ComboMessage(rn,combo,false);
								hs.add(rn);
							}
							return;
						}
						else if(meta.equals("nether")) {

							if(combo==MINCOMBO) {
								NethercorePortal(rn,combo,le);
								hs.add(rn);
							}
							else if(combo % COMBOPER == 0 && combo>=COMBOPER) {
								ComboMessage(rn,combo,false);
								hs.add(rn);
							}
							return;
						}
						else {
							
							if(combo==MINCOMBO) {
								
								ComboMessage(rn,combo,false);
								hs.add(rn);
							}
							else if(combo % COMBOPER == 0 && combo>=COMBOPER && combo < BOSSCOMBO) {
								ComboMessage(rn,combo,false);
								hs.add(rn);
							}
							else if(combo == BOSSCOMBO) {
								ComboMessage(rn,combo,true);
								Boss(rn,meta, le.getLocation().clone());
							}
						}
					});
					damaged.removeAll(le.getUniqueId());
					return hs;
				}
				return null;
			}
		}
		return null;
	}
	
	public void DamagerSave(EntityDamageByEntityEvent d) 
	{	
		if(!d.getEntity().hasMetadata("summoned") && d.getEntity() instanceof LivingEntity && !d.isCancelled()) {
			final LivingEntity le = (LivingEntity) d.getEntity();
			
			if(d.getDamager() instanceof Player) {

				final Player p = (Player) d.getDamager();
				damaged.put(le.getUniqueId(), getheroname(p));
			}
			if(d.getDamager() instanceof Projectile) {

				Projectile pr = (Projectile) d.getDamager();
				if(pr.getShooter() instanceof Player ) {
					final Player p = (Player) pr.getShooter();
					damaged.put(le.getUniqueId(), getheroname(p));
				}
			}
		}
	}


	final private void TreasureRaid(String rn, String meta) {
		
		if(getherotype(rn) instanceof Player) {
			Player fp = (Player) getherotype(rn);
			
			if(fp.getLocale().equalsIgnoreCase("ko_kr")) {
	    		fp.sendTitle(ChatColor.BOLD +(ChatColor.GOLD + "숨겨진 보물 발견!"), ChatColor.BOLD +"인벤토리에 지급되었습니다", 5, 60, 5);
			}
			else {
	    		fp.sendTitle(ChatColor.BOLD +(ChatColor.GOLD  + "Hidden Treasure!"), ChatColor.BOLD +"Given to Your Inventory", 5, 60, 5);
			}
			Elements.give(Elements.getscroll(fp), 1, fp);
        	Elements.give(Material.DIAMOND, 5, fp);
        	Elements.give(Material.GOLD_INGOT, 5, fp);
        	Elements.give(Material.LAPIS_LAZULI, 5, fp);
        	Elements.give(Material.EMERALD, 5, fp);
        	Elements.give(Material.NETHERITE_SCRAP, 5, fp);
		}
		else if(getherotype(rn) instanceof HashSet){

			@SuppressWarnings("unchecked")
			HashSet<Player> par = (HashSet<Player>) getherotype(rn);
			Player fp = Party.getOwner(rn);
    		
    		
			par.forEach(p ->{
				Elements.give(Elements.getscroll(p), 1, p);
	        	Elements.give(Material.DIAMOND, 5, p);
	        	Elements.give(Material.GOLD_INGOT, 5, p);
	        	Elements.give(Material.LAPIS_LAZULI, 5, p);
	        	Elements.give(Material.EMERALD, 5, p);
	        	Elements.give(Material.NETHERITE_SCRAP, 5, p);
				if(fp.getLocale().equalsIgnoreCase("ko_kr")) {
		    		p.sendTitle(ChatColor.BOLD +(ChatColor.GOLD + "숨겨진 보물 발견!"), ChatColor.BOLD +"인벤토리에 지급되었습니다", 5, 60, 5);
				}
				else {
		    		p.sendTitle(ChatColor.BOLD +(ChatColor.GOLD  + "Hidden Treasure!"), ChatColor.BOLD +"Given to Your Inventory", 5, 60, 5);
				}
			});
		}

		RaidFinish(rn,"","",0,meta);
	}

	final protected void RaidFinish(String rn, String title, String sub, Integer factor, String meta) {

		if(combot.contains(rn,meta)) {
			Bukkit.getScheduler().cancelTask(combot.get(rn, meta));
			combot.remove(rn, meta);
		}
		if(raidbart.contains(rn,meta)) {
			Bukkit.getScheduler().cancelTask(raidbart.get(rn, meta));
			raidbart.remove(rn, meta);
		}
		if(raidbar.contains(rn,meta)) {
			raidbar.get(rn, meta).removeAll();
			raidbar.get(rn, meta).setVisible(false);
			raidbar.get(rn, meta).removeFlag(BarFlag.CREATE_FOG);
			raidbar.remove(rn, meta);
		}
		if(raider.contains(rn,meta)) {
			if(getherotype(rn) instanceof Player) {
				Player p = (Player) getherotype(rn);
				if(factor == 1) {
	            	
					if(p.getLocale().equalsIgnoreCase("ko_kr")) {
			    		p.sendTitle(ChatColor.BOLD +(ChatColor.GOLD + "승리!"), ChatColor.BOLD +"보스몹을 물리쳤습니다", 5, 60, 5);
			    		p.sendMessage("15초간 이로운상태들을 획득합니다");
					}
					else {
			    		p.sendTitle(ChatColor.BOLD +(ChatColor.GOLD  + "Victory!"), ChatColor.BOLD +"Defeated Boss Monster", 5, 60, 5);
			    		p.sendMessage("Get Positive Effects 15 seconds");
					}
				}
				else {
		    		p.sendTitle(ChatColor.BOLD +(ChatColor.DARK_GRAY + title), ChatColor.BOLD +sub, 5, 60, 5);
				}
			}
			else if(getherotype(rn) instanceof HashSet){
				@SuppressWarnings("unchecked")
				HashSet<Player> par = (HashSet<Player>) getherotype(rn);
				par.forEach(p ->{
					if(factor == 1) {
		            	
						if(p.getLocale().equalsIgnoreCase("ko_kr")) {
				    		p.sendTitle(ChatColor.BOLD +(ChatColor.GOLD + "승리!"), ChatColor.BOLD +"보스몹을 물리쳤습니다", 5, 60, 5);
				    		p.sendMessage("15초간 이로운상태들을 획득합니다");
						}
						else {
				    		p.sendTitle(ChatColor.BOLD +(ChatColor.GOLD  + "Victory!"), ChatColor.BOLD +"Defeated Boss Monster", 5, 60, 5);
				    		p.sendMessage("Get Positive Effects 15 seconds");
						}
					}
					else {
			    		p.sendTitle(ChatColor.BOLD +(ChatColor.DARK_GRAY + title), ChatColor.BOLD +sub, 5, 60, 5);
					}
				});
			}
			HashSet<UUID> rem = new HashSet<>();
			raider.get(rn,meta).forEach(re -> {
				if(Bukkit.getEntity(re) != null && Bukkit.getEntity(re).hasMetadata(meta)) {
					LivingEntity le = (LivingEntity) Bukkit.getEntity(re);
					le.remove();
					le.setHealth(0);
					rem.add(re);
	    		}
			});
			rem.forEach(re -> raider.get(rn, meta).remove(re));
			if(raider.get(rn, meta).isEmpty()) {
				raider.remove(rn, meta);
			}
		}
		
		if(combo.contains(rn,meta)) {
			if(factor.equals(1) || factor == 1) {
				Reward(meta,rn,-1);
			}
			else {
				Reward(meta,rn,combo.get(rn, meta));
			}
			Random random=new Random();
	    	double ri = random.nextDouble()*TC;
	    	if(ri <= 50d * (combo.get(rn, meta)-MINCOMBO) / BOSSCOMBO) {
				combo.remove(rn, meta);
	    		TreasureRaid(rn,meta);
	    		return;
	    	}
			combo.remove(rn, meta);
		}
		if(raidporstand.containsKey(rn)) {
			if(Bukkit.getEntity(raidporstand.get(rn)) != null) {
				Bukkit.getEntity(raidporstand.get(rn)).remove();
			}
			raidporstand.remove(rn);
		}
		if(raidpor.containsKey(rn)) {
			raidpor.get(rn).setType(Material.VOID_AIR);
			raidpor.remove(rn);
		}
	
		if(combobart.contains(rn,meta)) {
			Bukkit.getScheduler().cancelTask(combobart.get(rn, meta));
			combobart.remove(rn, meta);
		}
		if(combobar.contains(rn,meta)) {
			combobar.get(rn, meta).removeAll();
			combobar.get(rn, meta).setVisible(false);
			combobar.get(rn, meta).removeFlag(BarFlag.DARKEN_SKY);
			combobar.remove(rn, meta);
		}
	    
	}

	@SuppressWarnings("unchecked")
	protected void RaidFinish(String rn, String title, String sub, Integer factor) {

		if(raidporstand.containsKey(rn)) {
			if(Bukkit.getEntity(raidporstand.get(rn)) != null) {
				Bukkit.getEntity(raidporstand.get(rn)).remove();
			}
			raidporstand.remove(rn);
		}
		if(raidpor.containsKey(rn)) {
			raidpor.get(rn).setType(Material.VOID_AIR);
			raidpor.remove(rn);
		}
		HashSet<String> metaset = new HashSet<>();
		if(combot.containsRow(rn)) {
			combot.row(rn).keySet().forEach(meta -> {
				Bukkit.getScheduler().cancelTask(combot.get(rn, meta));
				metaset.add(meta);
			});
		}
		if(combo.containsRow(rn)) {
			combo.row(rn).keySet().forEach(meta -> {
				if(factor == 1) {

					Reward(meta,rn,-1);
				}
				else {
					Reward(meta,rn,combo.get(rn, meta));
				}
				metaset.add(meta);
			});
		}

		if(combobar.containsRow(rn)) {
			combobar.row(rn).keySet().forEach(meta -> {
				combobar.get(rn, meta).removeAll();
				combobar.get(rn, meta).setVisible(false);
				combobar.get(rn, meta).removeFlag(BarFlag.DARKEN_SKY);
				metaset.add(meta);
			});
		}
		if(combobart.containsRow(rn)) {
			combobart.row(rn).keySet().forEach(meta -> {
				Bukkit.getScheduler().cancelTask(combobart.get(rn, meta));
				metaset.add(meta);
			});
		}

		if(raidbar.containsRow(rn)) {
			raidbar.row(rn).keySet().forEach(meta -> {
				raidbar.get(rn, meta).removeAll();
				raidbar.get(rn, meta).setVisible(false);
				raidbar.get(rn, meta).removeFlag(BarFlag.CREATE_FOG);
				metaset.add(meta);
			});
		}
		if(raidbart.containsRow(rn)) {
			raidbart.row(rn).keySet().forEach(meta -> {
				Bukkit.getScheduler().cancelTask(raidbart.get(rn, meta));
				metaset.add(meta);
			});
		}

		metaset.forEach(meta -> {
			combot.remove(rn, meta);
			combo.remove(rn, meta);
			combobar.remove(rn, meta);
			combobart.remove(rn, meta);
			raidbar.remove(rn, meta);
			raidbart.remove(rn, meta);
		});
		

		if(raider.containsRow(rn)) {
			if(getherotype(rn) instanceof Player) {
				Player p = (Player) getherotype(rn);
				if(factor == 1) {
					if(p.getLocale().equalsIgnoreCase("ko_kr")) {
			    		p.sendTitle(ChatColor.BOLD +(ChatColor.GOLD + "승리!"), ChatColor.BOLD +"보스몹을 물리쳤습니다", 5, 60, 5);
					}
					else {
			    		p.sendTitle(ChatColor.BOLD +(ChatColor.GOLD  + "Victory!"), ChatColor.BOLD +"Defeated Boss Monster", 5, 60, 5);
					}
				}
				else {
		    		p.sendTitle(ChatColor.BOLD +(ChatColor.DARK_GRAY + title), ChatColor.BOLD +sub, 5, 60, 5);
				}
			}
			else if(getherotype(rn) instanceof HashSet){
				HashSet<Player> par = (HashSet<Player>) getherotype(rn);
				par.forEach(p ->{
					if(factor == 1) {
						if(p.getLocale().equalsIgnoreCase("ko_kr")) {
				    		p.sendTitle(ChatColor.BOLD +(ChatColor.GOLD + "승리!"), ChatColor.BOLD +"보스몹을 물리쳤습니다", 5, 60, 5);
						}
						else {
				    		p.sendTitle(ChatColor.BOLD +(ChatColor.GOLD  + "Victory!"), ChatColor.BOLD +"Defeated Boss Monster", 5, 60, 5);
						}
					}
					else {
			    		p.sendTitle(ChatColor.BOLD +(ChatColor.DARK_GRAY + title), ChatColor.BOLD +sub, 5, 60, 5);
					}
				});
			}
			metaset.forEach(meta -> {
				if(!raider.containsColumn(meta)) {
					return;
				}
				
				HashSet<UUID> rem = new HashSet<>();
				raider.get(rn,meta).forEach(re -> {
					if(Bukkit.getEntity(re) != null && Bukkit.getEntity(re).hasMetadata(meta)) {
						LivingEntity le = (LivingEntity) Bukkit.getEntity(re);
						le.remove();
						le.setHealth(0);
						rem.add(re);
		    		}
				});
				rem.forEach(re -> raider.get(rn, meta).remove(re));
				if(raider.get(rn, meta).isEmpty()) {
					raider.remove(rn, meta);
				}
			});
		}
        
	}

	public void ThrowCancel(PlayerDropItemEvent ev)        
    {
		Player p = (Player)ev.getPlayer();
		String rn = getheroname(p);
			if(!p.isSneaking() && !p.isSprinting() && (combo.containsRow(rn) || p.getWorld().hasMetadata("rpgraidworld")))
			{
				ev.setCancelled(true);
				if(p.getLocale().equalsIgnoreCase("ko_kr")) {
					p.sendMessage("전투중에는 아이템을 버릴수 없습니다");
					p.sendMessage("/rpg escape 또는 /rpg es 명령어로 전투를 종료할수 있습니다.");
				}
				else {
					p.sendMessage("You Can't Drop Items While Fightning");
					p.sendMessage("You Can escape from fighting by" +ChatColor.BLUE+ "/rpg escape(es) "  +ChatColor.RESET+  "command");
				}
			}
	
    }
	public void Party(PartyCreateEvent ev) 
	{
		Player p = ev.getPlayer();
		String rn = getheroname(p);
		if(combo.containsRow(rn))
		{
			ev.setCancelled(true);
			if(p.getLocale().equalsIgnoreCase("ko_kr")) {
				p.sendMessage("전투중에는 파티생성이 불가능 합니다");
				p.sendMessage("/rpg escape 또는 /rpg es 명령어로 전투를 종료할수 있습니다.");
			}
			else {
				p.sendMessage("You Can't Create Party While Fightning");
				p.sendMessage("You Can escape from fighting by" +ChatColor.BLUE+ "/rpg escape(es) "  +ChatColor.RESET+  "command");
			}
		}
	}
	public void Party(PartyJoinEvent ev) 
	{
		Player p = ev.getPlayer();
		String rn = getheroname(p);
		if(combo.containsRow(rn))
		{
			ev.setCancelled(true);
			if(p.getLocale().equalsIgnoreCase("ko_kr")) {
				p.sendMessage("전투중에는 파티원 추가가 불가능 합니다");
				p.sendMessage("/rpg escape 또는 /rpg es 명령어로 전투를 종료할수 있습니다.");
			}
			else {
				p.sendMessage("You Can't Add member while fighting");
				p.sendMessage("You Can escape from fighting by" +ChatColor.BLUE+ "/rpg escape(es)"  +ChatColor.RESET+  "command");
			}
		}
	}
	
	public void Escape(PartyLeaveEvent ev) 
	{
		Player p = ev.getPlayer();
		
		if(p.getLocale().equalsIgnoreCase("ko_kr")) {
			RaidFinish(ev.getpartyname(), "탈주","몬스터들이 떠났습니다",0);
		}
		else {
			RaidFinish(ev.getpartyname(), "Escaped","Monsters Left",0);
		}
	}

	public void deleter(PluginDisableEvent ev) 
	{
		raidpor.values().forEach(b -> {
			b.setType(Material.VOID_AIR);
		});
	}
	
	public void Defeat(PlayerQuitEvent ev) 
	{
		Player p = ev.getPlayer();
		
		if(p.getLocale().equalsIgnoreCase("ko_kr")) {
			RaidFinish(getheroname(p), "탈주","몬스터들이 떠났습니다",0);
		}
		else {
			RaidFinish(getheroname(p), "Escaped","Monsters Left",0);
		}
	}


	public void Defeat(PlayerDeathEvent ev) 
	{
		Player p = ev.getEntity();
		if(p.getLocale().equalsIgnoreCase("ko_kr")) {
			RaidFinish(getheroname(p), "사망","몬스터들이 떠났습니다",0);
		}
		else {
			RaidFinish(getheroname(p), "Death","Monsters Left",0);
		}
	}
/*
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String alias, String [] args)         
    {
		return false;
		if(!(sender instanceof Player) &&alias.equals("rpg"))
		{
			Player p = (Player)sender;
			if(args[0].equalsIgnoreCase("escape")  || args[0].equalsIgnoreCase("es"))
			{
				RaidFinish(getheroname(p), "탈주","몬스터들이 떠났습니다",0);
			}
		}
    }
*/
	public void SummonedTarget(EntityTargetEvent d) 
	{	
		if(d.getEntity().hasMetadata("summoned")) {

			final LivingEntity le = (LivingEntity) d.getEntity();
			String rn2 = gethero(le);
			if(d.getTarget() instanceof Player) {
				Player p = (Player) d.getTarget();
				String rn1 = getheroname(p);
				if(rn1 != rn2) {
					d.setCancelled(true);
				}
			}
			if(d.getTarget() instanceof Projectile) {

				Projectile pr = (Projectile) d.getTarget();
				if(pr.getShooter() instanceof Player ) {
					Player p = (Player) pr.getShooter();
					String rn1 = getheroname(p);
					if(rn1 != rn2) {
						d.setCancelled(true);
					}
				}
			}
		}
		
	}
	public void SummonedDamage(EntityDamageByEntityEvent d) 
	{	
		if(d.getEntity() instanceof Player) {

			final Player p = (Player) d.getEntity();
			if(d.getDamager().hasMetadata("summoned")) {
				
				String rn1 = getheroname(p);
				String rn2 = gethero(d.getDamager());
				if(rn1 != rn2) {
					d.setCancelled(true);
				}
			}
			if(d.getDamager() instanceof Projectile) {

				Projectile pr = (Projectile) d.getDamager();
				if(pr.getShooter() instanceof LivingEntity ) {
					LivingEntity le = (LivingEntity) pr.getShooter();
					if(le.hasMetadata("summoned")) {
						String rn = getheroname(p);
						String rn2 = gethero(d.getDamager());
						if(rn != rn2) {
							d.setCancelled(true);
						}
					}
				}
			}
		}

		if(d.getEntity().hasMetadata("summoned")) {

			final LivingEntity le = (LivingEntity) d.getEntity();
			if(d.getDamager() instanceof Player) {
				Player p = (Player) d.getDamager();
				String rn1 = getheroname(p);
				String rn2 = gethero(le);
				if(rn1 != rn2) {
					d.setCancelled(true);
				}
				if(getherotype(rn1) instanceof HashSet){
					@SuppressWarnings("unchecked")
					HashSet<Player> par = (HashSet<Player>) getherotype(rn1);
					d.setDamage(d.getDamage()*(1 - par.size()*0.1));
				}
				
			}
			
			else if(d.getDamager().hasMetadata("summoned")) {
				LivingEntity a = (LivingEntity) d.getDamager();
				String rn1 = gethero(a);
				String rn2 = gethero(le);
				if(rn1.equals(rn2)) {
					d.setCancelled(true);
				}
			}
			
			else if(d.getDamager() instanceof Projectile) {

				Projectile pr = (Projectile) d.getDamager();
				if(pr.getShooter() instanceof Player ) {
					Player p = (Player) pr.getShooter();
					String rn1 = getheroname(p);
					String rn2 = gethero(le);
					if(rn1 != rn2) {
						d.setCancelled(true);
					}
					if(getherotype(rn1) instanceof HashSet){
						@SuppressWarnings("unchecked")
						HashSet<Player> par = (HashSet<Player>) getherotype(rn1);
						d.setDamage(d.getDamage()*(1 - par.size()*0.1));
					}
				}
				else if(d.getDamager().hasMetadata("summoned")) {
					LivingEntity a = (LivingEntity) d.getDamager();
					String rn1 = gethero(a);
					String rn2 = gethero(le);
					if(rn1.equals(rn2)) {
						d.setCancelled(true);
					}
				}
			}
		}
		
	}


	
}