package io.github.chw3021.monsters.raids;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.EndGateway;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
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

import io.github.chw3021.commons.party.Party;
import io.github.chw3021.commons.party.PartyCreateEvent;
import io.github.chw3021.commons.party.PartyJoinEvent;
import io.github.chw3021.commons.party.PartyLeaveEvent;
import io.github.chw3021.items.Elements;
import io.github.chw3021.monsters.Mobs;
import io.github.chw3021.rmain.RMain;

public class Summoned extends Mobs implements Serializable{
	
	/**
	 * 
	 */
	private static transient final long serialVersionUID = 5729675216993105537L;

	private static final Summoned instance = new Summoned ();
	public static Summoned getInstance()
	{
		return instance;
	}

	Double TC = 100d;
	Integer MINCOMBO = 5;
	Integer MAXCOMBO = 10;
	Integer COMBOPER = 12;
	public Integer SUMMONCOUNT = 2;
	
	Integer COMBOTIME = 50000;
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

	private Multimap<UUID, String> damaged = HashMultimap.create();

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
		else if(le.hasMetadata("soul")) {
			return ChatColor.WHITE;
		}
		else if(le.hasMetadata("crimson")) {
			return ChatColor.DARK_RED;
		}
		else if(le.hasMetadata("warped")) {
			return ChatColor.DARK_AQUA;
		}
		else if(le.hasMetadata("volcanic")) {
			return ChatColor.DARK_PURPLE;
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
		else if(meta.equals("soul")) {
			return ChatColor.WHITE;
		}
		else if(meta.equals("crimson")) {
			return ChatColor.DARK_RED;
		}
		else if(meta.equals("warped")) {
			return ChatColor.DARK_AQUA;
		}
		else if(meta.equals("volcanic")) {
			return ChatColor.DARK_PURPLE;
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
	                	double comt = (double)COMBOTIME*(1.2-combo.get(rn, meta)/(double)MAXCOMBO);
	        			if(combo.get(rn, meta)>=MAXCOMBO) {
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
	                	double comt = (double)COMBOTIME*(1.2-combo.get(rn, meta)/(double)MAXCOMBO);
	        			if(combo.get(rn, meta)>=MAXCOMBO) {
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
			
			double comt = (double)COMBOTIME*(1.2-combo.get(rn, meta)/(double)MAXCOMBO);
			if(combo.get(rn, meta)>=MAXCOMBO) {
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
			double comt = (double)COMBOTIME*(1.2-combo.get(rn, meta)/(double)MAXCOMBO);
			if(combo.get(rn, meta)>=MAXCOMBO) {
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
		else if(meta.equals("soul")) {
			return -2;
		}
		else if(meta.equals("crimson")) {
			return -3;
		}
		else if(meta.equals("warped")) {
			return -4;
		}
		else if(meta.equals("volcanic")) {
			return -5;
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
		else if(le.hasMetadata("soul")) {
			return -2;
		}
		else if(le.hasMetadata("crimson")) {
			return -3;
		}
		else if(le.hasMetadata("warped")) {
			return -4;
		}
		else if(le.hasMetadata("volcanic")) {
			return -5;
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
		else if(le.hasMetadata("soul")) {
			return "soul";
		}
		else if(le.hasMetadata("crimson")) {
			return "crimson";
		}
		else if(le.hasMetadata("warped")) {
			return "warped";
		}
		else if(le.hasMetadata("volcanic")) {
			return "volcanic";
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


    	if(p.hasPotionEffect(PotionEffectType.BAD_OMEN))
		{
			p.removePotionEffect(PotionEffectType.BAD_OMEN);
		}
		if(p.hasPotionEffect(PotionEffectType.BLINDNESS))
		{
			p.removePotionEffect(PotionEffectType.BLINDNESS);
		}
		if(p.hasPotionEffect(PotionEffectType.CONFUSION))
		{
			p.removePotionEffect(PotionEffectType.CONFUSION);
		}
		if(p.hasPotionEffect(PotionEffectType.HUNGER))
		{
			p.removePotionEffect(PotionEffectType.HUNGER);
		}
		if(p.hasPotionEffect(PotionEffectType.POISON))
		{
			p.removePotionEffect(PotionEffectType.POISON);
		}
		if(p.hasPotionEffect(PotionEffectType.SLOW))
		{
			p.removePotionEffect(PotionEffectType.SLOW);
		}
		if(p.hasPotionEffect(PotionEffectType.SLOW_DIGGING))
		{
			p.removePotionEffect(PotionEffectType.SLOW_DIGGING);
		}
		if(p.hasPotionEffect(PotionEffectType.UNLUCK))
		{
			p.removePotionEffect(PotionEffectType.UNLUCK);
		}
		if(p.hasPotionEffect(PotionEffectType.WEAKNESS))
		{
			p.removePotionEffect(PotionEffectType.WEAKNESS);
		}
		if(p.hasPotionEffect(PotionEffectType.WITHER))
		{
			p.removePotionEffect(PotionEffectType.WITHER);
		}
		p.setFireTicks(0);
    	
		p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 300, 5, false, false));
		p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 300, 5, false, false));
		p.addPotionEffect(new PotionEffect(PotionEffectType.WATER_BREATHING, 300, 5, false, false));
		p.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 300, 5, false, false));
		p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 300, 5, false, false));
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
		else if(meta.equals("soul")) {
			return 10;
		}
		else if(meta.equals("crimson")) {
			return 11;
		}
		else if(meta.equals("warped")) {
			return 12;
		}
		else if(meta.equals("volcanic")) {
			return 13;
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
		if(com <MINCOMBO) {
			return;
		}
		
		if(getherotype(rn) instanceof Player) {
			Player p = (Player) getherotype(rn);
			int count = com/COMBOPER;
        	Elements.give(Material.DIAMOND, count, p);
        	Elements.give(Material.GOLD_INGOT, count, p);
        	Elements.give(Material.LAPIS_LAZULI, count, p);
        	Elements.give(Material.EMERALD, count, p);
        	Elements.give(Material.NETHERITE_SCRAP, count, p);
        	Elements.give(Elements.getel(getelnum(meta), p), count, p);
			if(com==-1) {
				int exp = 2*getelexp(meta)*MAXCOMBO;
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
			else if(com>=MINCOMBO && com<MAXCOMBO){
				int exp = getelexp(meta)*MAXCOMBO/2/(2+MAXCOMBO-com);
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
			else if(com>=MAXCOMBO){
				int exp = getelexp(meta)*MAXCOMBO*(1+com/COMBOPER);
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
				int exp = 2*getelexp(meta)*MAXCOMBO;
				f.giveExp(exp);
				Bukkit.getServer().getPluginManager().callEvent(new PlayerExpChangeEvent(f,exp));
			}
			else if(com>=MINCOMBO && com<MAXCOMBO){
				int exp = getelexp(meta)*MAXCOMBO/2/(2+MAXCOMBO-com);
				f.giveExp(exp);
				Bukkit.getServer().getPluginManager().callEvent(new PlayerExpChangeEvent(f,exp));
			}
			else if(com>=MAXCOMBO) {
				int	exp = getelexp(meta)*MAXCOMBO*(1+com/COMBOPER);
				f.giveExp(exp);
				Bukkit.getServer().getPluginManager().callEvent(new PlayerExpChangeEvent(f,exp));
			}
			
			par.forEach(p ->{
				int count = com/COMBOPER/par.size();
	        	Elements.give(Material.DIAMOND, count, p);
	        	Elements.give(Material.GOLD_INGOT, count, p);
	        	Elements.give(Material.LAPIS_LAZULI, count, p);
	        	Elements.give(Material.EMERALD, count, p);
	        	Elements.give(Material.NETHERITE_SCRAP, count, p);
	        	Elements.give(Elements.getel(getelnum(meta), p), count, p);
				if(com>MAXCOMBO) {
					int exp = 2*getelexp(meta)*MAXCOMBO;
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
					int exp = getelexp(meta)*MAXCOMBO/2/(2+MAXCOMBO-com);;
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

			        			le.getWorld().spawnParticle(Particle.TOTEM, lel, 300,1,1,1);
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

		final Location sl = le.getLocation().clone().add(0, 0.5, 0);
		sl.getBlock().setType(Material.END_GATEWAY);
		EndGateway eg = (EndGateway) le.getWorld().getBlockState(sl);
		eg.setExactTeleport(true);
		eg.setExitLocation(null);
		eg.update();
		eg.setMetadata("OverworldRaidPortal", new FixedMetadataValue(RMain.getInstance(), rn));
		eg.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), rn));
		raidpor.put(rn, eg.getBlock());

		
		ArmorStand as = le.getWorld().spawn(sl, ArmorStand.class, a ->{
			a.setInvulnerable(true);
			a.setInvisible(true);
			a.setGravity(false);
			a.setCollidable(false);
			a.setMetadata("fake", new FixedMetadataValue(RMain.getInstance(), rn));
			a.setMetadata("rob", new FixedMetadataValue(RMain.getInstance(), rn));
			raidporstand.put(rn, a.getUniqueId());
			a.setCustomNameVisible(true);
		});
		
		if(getherotype(rn) instanceof Player) {
			Player p = (Player) getherotype(rn);
			if(p.getLocale().equalsIgnoreCase("ko_kr")) {
				as.setCustomName(ChatColor.DARK_RED +"우클릭(맨손)으로 입장이 가능합니다");
				p.sendTitle(ChatColor.RED +"고대의 포탈 소환", ChatColor.DARK_RED +"우클릭(맨손)으로 입장이 가능합니다", 10,35, 10);
				p.sendMessage(ChatColor.RED +"고대의 포탈 소환", ChatColor.DARK_RED +"우클릭(맨손)으로 입장이 가능합니다");
			}
			else {
				as.setCustomName(ChatColor.DARK_RED +"Enter by RightClick(Barehand)");
				p.sendTitle(ChatColor.RED +"Ancient Portal Summoned", ChatColor.DARK_RED +"Enter by RightClick(Barehand)", 10,35, 10);
				p.sendMessage(ChatColor.RED +"Ancient Portal Summoned", ChatColor.DARK_RED +"Enter by RightClick(Barehand)");
			}
		}
		else if(getherotype(rn) instanceof HashSet){
			HashSet<Player> par = (HashSet<Player>) getherotype(rn);
			par.forEach(p ->{
				if(p.getLocale().equalsIgnoreCase("ko_kr")) {
					as.setCustomName(ChatColor.DARK_RED +"우클릭(맨손)으로 입장이 가능합니다");
					p.sendTitle(ChatColor.RED +"고대의 포탈 소환", ChatColor.DARK_RED +"우클릭(맨손)으로 입장이 가능합니다", 10,35, 10);
					p.sendMessage(ChatColor.RED +"고대의 포탈 소환", ChatColor.DARK_RED +"우클릭(맨손)으로 입장이 가능합니다");
				}
				else {
					as.setCustomName(ChatColor.DARK_RED +"Enter by RightClick(Barehand)");
					p.sendTitle(ChatColor.RED +"Ancient Portal Summoned", ChatColor.DARK_RED +"Enter by RightClick(Barehand)", 10,35, 10);
					p.sendMessage(ChatColor.RED +"Ancient Portal Summoned", ChatColor.DARK_RED +"Enter by RightClick(Barehand)");
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
				}
				else {
					if(combo==MINCOMBO) {
						ComboMessage(rn,combo,false);
						return rn;
					}
					else if(combo % COMBOPER == 0 && combo>=COMBOPER && combo < MAXCOMBO) {
						ComboMessage(rn,combo,false);
						return rn;

					}
					else if(combo == MAXCOMBO) {
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
						else {
							
							if(combo==MINCOMBO) {
								
								ComboMessage(rn,combo,false);
								hs.add(rn);
							}
							else if(combo % COMBOPER == 0 && combo>=COMBOPER && combo < MAXCOMBO) {
								ComboMessage(rn,combo,false);
								hs.add(rn);
							}
							else if(combo == MAXCOMBO) {
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
		}
		else if(getherotype(rn) instanceof HashSet){

			@SuppressWarnings("unchecked")
			HashSet<Player> par = (HashSet<Player>) getherotype(rn);
			Player fp = Party.getOwner(rn);
    		
    		
			par.forEach(p ->{
				if(fp.getLocale().equalsIgnoreCase("ko_kr")) {
		    		fp.sendTitle(ChatColor.BOLD +(ChatColor.GOLD + "숨겨진 보물 발견!"), ChatColor.BOLD +"인벤토리에 지급되었습니다", 5, 60, 5);
				}
				else {
		    		fp.sendTitle(ChatColor.BOLD +(ChatColor.GOLD  + "Hidden Treasure!"), ChatColor.BOLD +"Given to Your Inventory", 5, 60, 5);
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
			if(factor == 1) {
				Reward(meta,rn,-1);
			}
			else {
				Reward(meta,rn,combo.get(rn, meta));
			}
			Random random=new Random();
	    	double ri = random.nextDouble()*TC;
	    	if(ri <= 50d * (combo.get(rn, meta)-MINCOMBO) / MAXCOMBO) {
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
			if(!p.isSneaking() && !p.isSprinting() && combo.containsRow(rn))
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
