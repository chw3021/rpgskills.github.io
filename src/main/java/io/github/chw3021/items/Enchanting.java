package io.github.chw3021.items;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentOffer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.enchantment.EnchantItemEvent;
import org.bukkit.event.enchantment.PrepareItemEnchantEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.EnchantingInventory;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.Table;

import io.github.chw3021.commons.Holding;
import io.github.chw3021.rmain.RMain;


public class Enchanting implements Listener {
	

	public Table<String, Integer, EnchantmentOffer> ench = HashBasedTable.create();
	private HashMap<String, Double> bonus =new HashMap<>(); 
	private Multimap<UUID, Integer> encht = HashMultimap.create();
	static private HashMap<UUID, ItemStack[]> pin =new HashMap<>(); 

	final public ItemStack enchlore(ItemStack is, Player p) {
		if(is == null) {
			return null;
		}
		ItemMeta im = is.getItemMeta();
		if (!im.hasLore()&&!im.hasCustomModelData()) {
			List<String> lore = new ArrayList<String>();
			if(p.getLocale().equalsIgnoreCase("ko_kr")) {
				lore.add(ChatColor.YELLOW + "원하는 옵션을 선택하면 마법부여가");
				lore.add(ChatColor.YELLOW + "5초 동안 진행되고 일정확률로 성공합니다");
				lore.add(ChatColor.YELLOW + "확률은 주변 책장의 개수에 비례합니다");
				lore.add("");
				lore.add(ChatColor.DARK_PURPLE + "무기의 경우 [찌르기, 살충, 강타]는 최대 10레벨");
				lore.add(ChatColor.DARK_PURPLE + "[무한 화살, 다중발사]는 1레벨, [빠른 장전]은 최대 3레벨");
				lore.add(ChatColor.DARK_PURPLE + "그 외 마법부여는 255레벨까지 가능합니다");
				lore.add("");
				lore.add(ChatColor.DARK_PURPLE + "활과 쇠뇌은 [힘], 그 외 무기들은 [날카로움]의 레벨에");
				lore.add(ChatColor.DARK_PURPLE + "비례하여 공격력이 곱연산으로 증가합니다");
				lore.add(ChatColor.DARK_PURPLE + "[찌르기, 살충, 강타]는 레벨에 비례하여");
				lore.add(ChatColor.DARK_PURPLE + "해당 유형 몬스터에게 추가 피해를 줍니다");
				lore.add("");
				lore.add(ChatColor.GREEN + "방어구의 경우 [~로부터 보호]꼴의 마법부여와");
				lore.add(ChatColor.GREEN + "[가시]는 최대 4레벨");
				lore.add(ChatColor.GREEN + "부츠에 부여되는 [이동 계열] 마법부여도 최대 4레벨");
				lore.add(ChatColor.GREEN + "투구에 부여되는 [수중 계열] 마법부여도 최대 4레벨");
				lore.add(ChatColor.GREEN + "그외 옵션은 255레벨까지 가능합니다");
			}
			else {
				lore.add(ChatColor.YELLOW + "Enchanting will proceed for 5 seconds");
				lore.add(ChatColor.YELLOW + "Since you choose option that you want");
				lore.add(ChatColor.YELLOW + "The probability of success is proportional");
				lore.add(ChatColor.YELLOW + "To the number of BookShelves");
				lore.add("");
				lore.add(ChatColor.DARK_PURPLE + "For weapons, [Impaling, Bane of Arthropods]");
				lore.add(ChatColor.DARK_PURPLE + "[Smite] is up to 10 levels");
				lore.add(ChatColor.DARK_PURPLE + "[Infinity, Multishot] is up to 1 level");
				lore.add(ChatColor.DARK_PURPLE + "[Quick Charge] is up to 3 level");
				lore.add(ChatColor.DARK_PURPLE + "Other Enchantments are available up to 255 levels");
				lore.add("");
				lore.add(ChatColor.DARK_PURPLE + "Damage of Bow and Crossbow increase with multiplication");
				lore.add(ChatColor.DARK_PURPLE + "In proportion to the level of [Power]");
				lore.add(ChatColor.DARK_PURPLE + "Damage of Other Weapons increase with multiplication");
				lore.add(ChatColor.DARK_PURPLE + "In proportion to the level of [Sharpness]");
				lore.add(ChatColor.DARK_PURPLE + "[Impaling, Bane of Arthropods, Smite] inflict");
				lore.add(ChatColor.DARK_PURPLE + "Additional Damage to Mobs of relevant Category");
				lore.add("");
				lore.add(ChatColor.GREEN + "For Armors, Enchantments in the form of [~ Protection]");
				lore.add(ChatColor.GREEN + "And [Thorns] is up to 4 level");
				lore.add(ChatColor.GREEN + "[Moving] Enchantments that given to the boots is up to 4 levels");
				lore.add(ChatColor.GREEN + "[Water] Enchantments that given to the helmet is up to 4 levels");
				lore.add(ChatColor.GREEN + "Other Enchantments are available up to 255 levels");
			}
			im.setLore(lore);
		}
		is.setItemMeta(im);
		return is;
	}
	@EventHandler
	public void LoreChange(InventoryClickEvent ev) 
	{
		if(ev.getCurrentItem() != null && !ev.getView().getTitle().contains("skills") && !ev.getView().getTitle().contains("Classes")) {
			for(ItemStack is : ev.getView().getBottomInventory()) {
				if(is == null) {
					continue;
				}
				Material m = is.getType();
				if(m == Material.ENCHANTING_TABLE) {
					Player p = (Player) ev.getView().getPlayer();
					enchlore(is, p);
				}
			}
		}
	}

	private Inventory prg(Inventory bi, Player p, Integer a, ItemStack di, EnchantmentOffer eo) {
		ItemStack is = new ItemStack(Material.LAPIS_BLOCK);
		ItemMeta im = is.getItemMeta();
		double chance = 100 * (256 - di.getEnchantmentLevel(eo.getEnchantment()))/257d * (0.36 + bonus.get(p.getUniqueId().toString() + di.toString())/50d );
		if(di.getEnchantmentLevel(eo.getEnchantment()) >=30){
			chance = chance* 0.8;
		}
		if(p.getLocale().equalsIgnoreCase("ko_kr")) {
			im.setDisplayName(ChatColor.LIGHT_PURPLE+"마법부여 시작..");
			List<String> lore = Arrays.asList(ChatColor.BLUE+"확률: " +  chance ,ChatColor.YELLOW+(ChatColor.UNDERLINE+"해당 마법부여 레벨이 30이상일 경우"), ChatColor.YELLOW+(ChatColor.UNDERLINE+"마법부여가 실패하면 레벨이 초기화 됩니다."), "완료전 창을 닫으면 취소됩니다","",ChatColor.BLUE+"최대 레벨은 256입니다");
			im.setLore(lore);
		}
		else {
			
			im.setDisplayName(ChatColor.LIGHT_PURPLE+"Start Enchanting...");

			List<String> lore = Arrays.asList(ChatColor.BLUE+"Chance: " +  chance,ChatColor.YELLOW+(ChatColor.UNDERLINE+"If the corresponding Enchantment level is 30 or higher"), ChatColor.YELLOW+(ChatColor.UNDERLINE+"Reset the level if the Enchanting fails."), "Cancel If you Close Before Complete","" ,ChatColor.BLUE+"Max 256Lv");
			im.setLore(lore);
		}
		is.setItemMeta(im);
		
		bi.setItem(a+9, is);
		bi.setItem(a+18, is);
		bi.setItem(a+27, is);
		return bi;
	}

	@EventHandler
	public void EnchResult(EnchantItemEvent d) 
	{
		EnchantingInventory et =  (EnchantingInventory) d.getInventory();
		Player p = d.getEnchanter();
		
		if(encht.containsKey(p.getUniqueId())) {
			d.setCancelled(true);
			return;
		}
		
		Inventory bi = d.getView().getBottomInventory();
		pin.put(p.getUniqueId(), bi.getContents());

		
		if(ench.containsRow(p.getUniqueId().toString() + d.getItem().toString())){
			
			final ItemStack is = d.getItem();
			if(et.getSecondary() == null || et.getSecondary().getAmount() < d.whichButton()+1) {
				d.setCancelled(true);
				return;
			}
			ItemStack lap = et.getSecondary().clone();
			lap.setAmount(et.getSecondary().getAmount()-d.whichButton()-1);
			final EnchantmentOffer eo = ench.get(p.getUniqueId().toString() + d.getItem().toString(), d.whichButton());

			Random random=new Random();
        	int ri = random.nextInt(100);

			
			
			bi.clear();
			AtomicInteger j = new AtomicInteger();
			for(int i = 0; i <9; i++) {
				int task =Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
	                @Override
	                public void run() 
	                {
        				p.playSound(p.getLocation(), Sound.BLOCK_ENCHANTMENT_TABLE_USE, 0.1f, 2);
        				Holding.invur(p, 10l);
	                	prg(bi,p,j.getAndIncrement(),is,eo);
	                }
				},i*5);
				encht.put(p.getUniqueId(), task);
			}
            
     	   	int task =Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(RMain.getInstance(), new Runnable() {
                @Override
                public void run() 
                {
        			et.setSecondary(lap);
        			double chance = 100 * (256 - is.getEnchantmentLevel(eo.getEnchantment()))/257d * (0.36 + bonus.get(p.getUniqueId().toString() + is.toString())/50d );
            		if(is.getEnchantmentLevel(eo.getEnchantment()) >=30){
            			chance = chance* 0.8;
            		}
                	if(ri <= chance) {

            			if(p.getLocale().equalsIgnoreCase("ko_kr")) {
            				p.sendTitle(ChatColor.GOLD+"마법부여 성공!", "", 15, 35, 15);
            			}
            			else {
            				p.sendTitle(ChatColor.GOLD+"Enchant Success!", "", 15, 35, 15);
            			}
            				is.addUnsafeEnchantment(eo.getEnchantment(), eo.getEnchantmentLevel()+is.getEnchantmentLevel(eo.getEnchantment()));
            				p.playSound(p.getLocation(), Sound.BLOCK_BEACON_ACTIVATE, 1, 1.8f);
            				p.playSound(p.getLocation(), Sound.BLOCK_ENCHANTMENT_TABLE_USE, 1, 1.11f);
            				for(int i = 0; i<=2; i++) {
            					ench.remove(p.getUniqueId().toString() + d.getItem().toString(), i);
            				}
            			
                	}
                	else {
                		if(is.getEnchantmentLevel(eo.getEnchantment()) >=30){
            				is.removeEnchantment(eo.getEnchantment());
                		}
        				p.playSound(p.getLocation(), Sound.BLOCK_BEACON_DEACTIVATE, 1, 1.8f);
        				p.playSound(p.getLocation(), Sound.BLOCK_ENCHANTMENT_TABLE_USE, 1, 0);
            			if(p.getLocale().equalsIgnoreCase("ko_kr")) {
            				p.sendTitle(ChatColor.DARK_GRAY+"마법부여 실패", "", 15, 35, 15);
            			}
            			else {
            				p.sendTitle(ChatColor.DARK_GRAY+"Enchanting Failed", "", 15, 35, 15);
            			}
        				for(int i = 0; i<=2; i++) {
        					ench.remove(p.getUniqueId().toString() + d.getItem().toString(), i);
        				}
                	}
                	p.closeInventory();
                }
            }, 50); 
			encht.put(p.getUniqueId(), task);
			d.setCancelled(true);
		}
	}

	@EventHandler
	public void Cancel(InventoryCloseEvent d) 
	{
		if(encht.containsKey(d.getPlayer().getUniqueId()) && d.getInventory().getType() == InventoryType.ENCHANTING) {
			encht.get(d.getPlayer().getUniqueId()).forEach(t ->{
				Bukkit.getScheduler().cancelTask(t);
			});
			encht.removeAll(d.getPlayer().getUniqueId());
			d.getView().getBottomInventory().setContents(pin.get(d.getPlayer().getUniqueId()));
		}
	}
	@EventHandler
	public void Cancel(InventoryClickEvent d) 
	{
		if(encht.containsKey(d.getWhoClicked().getUniqueId()) && d.getInventory().getType() == InventoryType.ENCHANTING) {
			d.setCancelled(true);
			return;
		}
	}
	
	@EventHandler
	public void Dice(PrepareItemEnchantEvent d) 
	{
		EnchantmentOffer[] eo = d.getOffers();
		HashMap<Integer, EnchantmentOffer> eh = new HashMap<>();
		Player p = d.getEnchanter();
		if(encht.containsKey(p.getUniqueId())) {
			d.setCancelled(true);
			return;
		}
		d.setCancelled(false);
		if(ench.containsRow(p.getUniqueId().toString() + d.getItem().toString())){
			if(bonus.getOrDefault(p.getUniqueId().toString() + d.getItem().toString(), (double) 0) != d.getEnchantmentBonus()) {
				for(int i = 0; i<=2; i++) {
					EnchantmentOffer eoa = ench.get(p.getUniqueId().toString() + d.getItem().toString(), i);
		    		if(eo[i] == null) {
						eoa.setCost((i+1)*(d.getEnchantmentBonus()/3+1));
		    		}
		    		else {
		    			eoa.setCost(eo[i].getCost());
		    		}
					eo[i] = eoa;
					bonus.put(p.getUniqueId().toString() + d.getItem().toString(), (double) d.getEnchantmentBonus());
				}
			}
			else {
				for(int i = 0; i<eo.length; i++) {
					eo[i] = ench.get(p.getUniqueId().toString() + d.getItem().toString(), i);
				}
			}
			return;
		}
		d.setCancelled(true);
		for(int i = 0; i<eo.length; i++) {
			Random random=new Random();
        	int ri = random.nextInt(100);
			if(d.getItem().getType() != Material.BOW && d.getItem().getType() != Material.CROSSBOW &&!d.getItem().getType().name().contains("CHESTPLATE") && !d.getItem().getType().name().contains("BOOTS") && !d.getItem().getType().name().contains("LEGGINGS") && !d.getItem().getType().name().contains("HELMET")) {
	    		
	    		if(eo[i] == null) {
		        	if(ri < 35) {
		        		EnchantmentOffer neo = new EnchantmentOffer(Enchantment.SHARPNESS, i+1, (i+1)*(d.getEnchantmentBonus()/3+1));
		    			eo[i] =neo;
		        	}
		        	else if(d.getItem().getEnchantmentLevel(Enchantment.BANE_OF_ARTHROPODS)+i+1<=10 &&ri < 60) {
		        		EnchantmentOffer neo = new EnchantmentOffer(Enchantment.BANE_OF_ARTHROPODS, i+1, (i+1)*(d.getEnchantmentBonus()/3+1));
		    			eo[i] =neo;
		        	}
		        	else if (d.getItem().getEnchantmentLevel(Enchantment.SMITE)+i+1<=10 &&ri <85){
		        		EnchantmentOffer neo = new EnchantmentOffer(Enchantment.SMITE, i+1, (i+1)*(d.getEnchantmentBonus()/3+1));
		    			eo[i] =neo;
		        	}
		        	else if (ri <90){
		        		EnchantmentOffer neo = new EnchantmentOffer(Enchantment.KNOCKBACK, i+1, (i+1)*(d.getEnchantmentBonus()/3+1));
		    			eo[i] =neo;
		        	}
		        	else if (ri <95){
		        		EnchantmentOffer neo = new EnchantmentOffer(Enchantment.FIRE_ASPECT, i+1, (i+1)*(d.getEnchantmentBonus()/3+1));
		    			eo[i] =neo;
		        	}
		        	else {
		        		EnchantmentOffer neo = new EnchantmentOffer(Enchantment.LOOTING, i+1, (i+1)*(d.getEnchantmentBonus()/3+1));
		    			eo[i] =neo;
		        	}
	    		}
	    		else {
		    		if(d.getItem().getType().name().contains("SWORD")){
			        	if(ri < 35) {
			        		eo[i].setEnchantment(Enchantment.SHARPNESS);
			        		eo[i].setEnchantmentLevel(i+1);
			        	}
			        	else if(d.getItem().getEnchantmentLevel(Enchantment.BANE_OF_ARTHROPODS)+i+1<=10 &&ri < 50) {
			        		eo[i].setEnchantment(Enchantment.BANE_OF_ARTHROPODS);
			        		eo[i].setEnchantmentLevel(i+1);
			        	}
			        	else if (d.getItem().getEnchantmentLevel(Enchantment.SMITE)+i+1<=10 &&ri <65){
			        		eo[i].setEnchantment(Enchantment.SMITE);
			        		eo[i].setEnchantmentLevel(i+1);
			        	}
			        	else if (ri <70){
			        		eo[i].setEnchantment(Enchantment.FIRE_ASPECT);
			        		eo[i].setEnchantmentLevel(i+1);
			        	}
			        	else if (ri <75){
			        		eo[i].setEnchantment(Enchantment.KNOCKBACK);
			        		eo[i].setEnchantmentLevel(i+1);
			        	}
			        	else if (ri <85){
			        		eo[i].setEnchantment(Enchantment.SWEEPING_EDGE);
			        		eo[i].setEnchantmentLevel(i+1);
			        	}
			        	else if (ri <90){
			        		eo[i].setEnchantment(Enchantment.LOOTING);
			        		eo[i].setEnchantmentLevel(i+1);
			        	}
			        	else {
			        		eo[i].setEnchantment(Enchantment.UNBREAKING);
			        		eo[i].setEnchantmentLevel(i+1);
			        	}
		    		}
		    		else if(d.getItem().getType().name().contains("AXE") || d.getItem().getType().name().contains("HOE") || d.getItem().getType().name().contains("SHOVEL") ){
			        	if(ri < 30) {
			        		eo[i].setEnchantment(Enchantment.SHARPNESS);
			        		eo[i].setEnchantmentLevel(i+1);
			        	}
			        	else if(d.getItem().getEnchantmentLevel(Enchantment.BANE_OF_ARTHROPODS)+i+1<=10 &&ri < 45) {
			        		eo[i].setEnchantment(Enchantment.BANE_OF_ARTHROPODS);
			        		eo[i].setEnchantmentLevel(i+1);
			        	}
			        	else if (d.getItem().getEnchantmentLevel(Enchantment.SMITE)+i+1<=10 &&ri <60){
			        		eo[i].setEnchantment(Enchantment.SMITE);
			        		eo[i].setEnchantmentLevel(i+1);
			        	}
			        	else if (ri <65){
			        		eo[i].setEnchantment(Enchantment.SILK_TOUCH);
			        		eo[i].setEnchantmentLevel(i+1);
			        	}
			        	else if (ri <73){
			        		eo[i].setEnchantment(Enchantment.FORTUNE);
			        		eo[i].setEnchantmentLevel(i+1);
			        	}
			        	else if (ri <82){
			        		eo[i].setEnchantment(Enchantment.EFFICIENCY);
			        		eo[i].setEnchantmentLevel(i+1);
			        	}
			        	else if (ri <88){
			        		eo[i].setEnchantment(Enchantment.LOOTING);
			        		eo[i].setEnchantmentLevel(i+1);
			        	}
			        	else {
			        		eo[i].setEnchantment(Enchantment.UNBREAKING);
			        		eo[i].setEnchantmentLevel(i+1);
			        	}
		    		}
		    		else if(d.getItem().getType() == Material.FISHING_ROD){
			        	if(ri < 30) {
			        		eo[i].setEnchantment(Enchantment.SHARPNESS);
			        		eo[i].setEnchantmentLevel(i+1);
			        	}
			        	else if(d.getItem().getEnchantmentLevel(Enchantment.BANE_OF_ARTHROPODS)+i+1<=10 &&ri < 45) {
			        		eo[i].setEnchantment(Enchantment.BANE_OF_ARTHROPODS);
			        		eo[i].setEnchantmentLevel(i+1);
			        	}
			        	else if (d.getItem().getEnchantmentLevel(Enchantment.SMITE)+i+1<=10 &&ri <60){
			        		eo[i].setEnchantment(Enchantment.SMITE);
			        		eo[i].setEnchantmentLevel(i+1);
			        	}
			        	else if (ri <65){
			        		eo[i].setEnchantment(Enchantment.LUCK_OF_THE_SEA);
			        		eo[i].setEnchantmentLevel(i+1);
			        	}
			        	else if (ri <73){
			        		eo[i].setEnchantment(Enchantment.LURE);
			        		eo[i].setEnchantmentLevel(i+1);
			        	}
			        	else if (ri <82){
			        		eo[i].setEnchantment(Enchantment.EFFICIENCY);
			        		eo[i].setEnchantmentLevel(i+1);
			        	}
			        	else if (ri <88){
			        		eo[i].setEnchantment(Enchantment.LOOTING);
			        		eo[i].setEnchantmentLevel(i+1);
			        	}
			        	else {
			        		eo[i].setEnchantment(Enchantment.UNBREAKING);
			        		eo[i].setEnchantmentLevel(i+1);
			        	}
		    		}
		    		else {
			        	if(ri < 30) {
			        		eo[i].setEnchantment(Enchantment.SHARPNESS);
			        		eo[i].setEnchantmentLevel(i+1);
			        	}
			        	else if(d.getItem().getEnchantmentLevel(Enchantment.BANE_OF_ARTHROPODS)+i+1<=10 &&ri < 45) {
			        		eo[i].setEnchantment(Enchantment.BANE_OF_ARTHROPODS);
			        		eo[i].setEnchantmentLevel(i+1);
			        	}
			        	else if (d.getItem().getEnchantmentLevel(Enchantment.SMITE)+i+1<=10 &&ri <60){
			        		eo[i].setEnchantment(Enchantment.SMITE);
			        		eo[i].setEnchantmentLevel(i+1);
			        	}
			        	else if (ri <65){
			        		eo[i].setEnchantment(Enchantment.FIRE_ASPECT);
			        		eo[i].setEnchantmentLevel(i+1);
			        	}
			        	else if (ri <73){
			        		eo[i].setEnchantment(Enchantment.FORTUNE);
			        		eo[i].setEnchantmentLevel(i+1);
			        	}
			        	else if (ri <82){
			        		eo[i].setEnchantment(Enchantment.EFFICIENCY);
			        		eo[i].setEnchantmentLevel(i+1);
			        	}
			        	else if (ri <88){
			        		eo[i].setEnchantment(Enchantment.LOOTING);
			        		eo[i].setEnchantmentLevel(i+1);
			        	}
			        	else {
			        		eo[i].setEnchantment(Enchantment.UNBREAKING);
			        		eo[i].setEnchantmentLevel(i+1);
			        	}
		    		}
	    		}
			}
			else if(d.getItem().getType().name().contains("CROSSBOW")) {
				if(d.getItem().getEnchantmentLevel(Enchantment.QUICK_CHARGE)+i+1>3) {
		        	if(ri < 40) {
		        		eo[i].setEnchantment(Enchantment.POWER);
		        		eo[i].setEnchantmentLevel(i+1);
		        	}
		        	else if(d.getItem().getEnchantmentLevel(Enchantment.MULTISHOT)+i+1<=1 && ri<55){
		        		eo[i].setEnchantment(Enchantment.MULTISHOT);
		        		eo[i].setEnchantmentLevel(i+1);
		        	}
		        	else if(ri<75){
		        		eo[i].setEnchantment(Enchantment.UNBREAKING);
		        		eo[i].setEnchantmentLevel(i+1);
		        	}
		        	else if (ri <80){
		        		eo[i].setEnchantment(Enchantment.LOOTING);
		        		eo[i].setEnchantmentLevel(i+1);
		        	}
		        	else {
		        		eo[i].setEnchantment(Enchantment.PIERCING);
		        		eo[i].setEnchantmentLevel(i+1);
		        	}
				}
				else {
		        	if(ri < 40) {
		        		eo[i].setEnchantment(Enchantment.POWER);
		        		eo[i].setEnchantmentLevel(i+1);
		        	}
		        	else if(d.getItem().getEnchantmentLevel(Enchantment.MULTISHOT)+i+1<=1 && ri<55){
		        		eo[i].setEnchantment(Enchantment.MULTISHOT);
		        		eo[i].setEnchantmentLevel(i+1);
		        	}
		        	else if(ri<60){
		        		eo[i].setEnchantment(Enchantment.UNBREAKING);
		        		eo[i].setEnchantmentLevel(i+1);
		        	}
		        	else if(ri<80){
		        		eo[i].setEnchantment(Enchantment.QUICK_CHARGE);
		        		eo[i].setEnchantmentLevel(i+1);
		        	}
		        	else if (ri <85){
		        		eo[i].setEnchantment(Enchantment.LOOTING);
		        		eo[i].setEnchantmentLevel(i+1);
		        	}
		        	else {
		        		eo[i].setEnchantment(Enchantment.PIERCING);
		        		eo[i].setEnchantmentLevel(i+1);
		        	}
				}
				
			}
			else if(d.getItem().getType().name().contains("BOW")) {
	        	if(ri < 40) {
	        		eo[i].setEnchantment(Enchantment.POWER);
	        		eo[i].setEnchantmentLevel(i+1);
	        	}
	        	else if(d.getItem().getEnchantmentLevel(Enchantment.INFINITY)+i+1<=1 && ri<55){
	        		eo[i].setEnchantment(Enchantment.INFINITY);
	        		eo[i].setEnchantmentLevel(i+1);
	        	}
	        	else if(ri<75){
	        		eo[i].setEnchantment(Enchantment.UNBREAKING);
	        		eo[i].setEnchantmentLevel(i+1);
	        	}
	        	else if (ri <80){
	        		eo[i].setEnchantment(Enchantment.LOOTING);
	        		eo[i].setEnchantmentLevel(i+1);
	        	}
	        	else if (ri <90){
	        		eo[i].setEnchantment(Enchantment.PUNCH);
	        		eo[i].setEnchantmentLevel(i+1);
	        	}
	        	else {
	        		eo[i].setEnchantment(Enchantment.FLAME);
	        		eo[i].setEnchantmentLevel(i+1);
	        	}
			}
			else if(d.getItem().getType().name().contains("TRIDENT")) {
	        	if(ri < 40) {
	        		eo[i].setEnchantment(Enchantment.SHARPNESS);
	        		eo[i].setEnchantmentLevel(i+1);
	        	}
	        	else if(d.getItem().getEnchantmentLevel(Enchantment.IMPALING)+i+1<=10 &&ri < 65) {
	        		eo[i].setEnchantment(Enchantment.IMPALING);
	        		eo[i].setEnchantmentLevel(i+1);
	        	}
	        	else if(ri<75){
	        		eo[i].setEnchantment(Enchantment.UNBREAKING);
	        		eo[i].setEnchantmentLevel(i+1);
	        	}
	        	else if (ri <80){
	        		eo[i].setEnchantment(Enchantment.LOOTING);
	        		eo[i].setEnchantmentLevel(i+1);
	        	}
	        	else if (ri <90){
	        		eo[i].setEnchantment(Enchantment.KNOCKBACK);
	        		eo[i].setEnchantmentLevel(i+1);
	        	}
	        	else {
	        		eo[i].setEnchantment(Enchantment.FIRE_ASPECT);
	        		eo[i].setEnchantmentLevel(i+1);
	        	}
			}
			else if(d.getItem().getType().name().contains("CHESTPLATE") || d.getItem().getType().name().contains("LEGGINGS")) {
	        	if(d.getItem().getEnchantmentLevel(Enchantment.PROTECTION)+i+1<=4 && ri<20){
	        		eo[i].setEnchantment(Enchantment.PROTECTION);
	        		eo[i].setEnchantmentLevel(i+1);
	        	}
	        	else if(d.getItem().getEnchantmentLevel(Enchantment.BLAST_PROTECTION)+i+1<=4 && ri<40){
	        		eo[i].setEnchantment(Enchantment.BLAST_PROTECTION);
	        		eo[i].setEnchantmentLevel(i+1);
	        	}
	        	else if(d.getItem().getEnchantmentLevel(Enchantment.FIRE_PROTECTION)+i+1<=4 && ri<60){
	        		eo[i].setEnchantment(Enchantment.FIRE_PROTECTION);
	        		eo[i].setEnchantmentLevel(i+1);
	        	}
	        	else if(d.getItem().getEnchantmentLevel(Enchantment.PROJECTILE_PROTECTION)+i+1<=4 && ri<80){
	        		eo[i].setEnchantment(Enchantment.PROJECTILE_PROTECTION);
	        		eo[i].setEnchantmentLevel(i+1);
	        	}
	        	else if(d.getItem().getEnchantmentLevel(Enchantment.THORNS)+i+1<=4 && ri<90){
	        		eo[i].setEnchantment(Enchantment.THORNS);
	        		eo[i].setEnchantmentLevel(i+1);
	        	}
	        	else {
	        		eo[i].setEnchantment(Enchantment.UNBREAKING);
	        		eo[i].setEnchantmentLevel(i+1);
	        	}
			}
			else if(d.getItem().getType().name().contains("BOOTS")) {
	        	if(d.getItem().getEnchantmentLevel(Enchantment.PROTECTION)+i+1<=4 && ri<15){
	        		eo[i].setEnchantment(Enchantment.PROTECTION);
	        		eo[i].setEnchantmentLevel(i+1);
	        	}
	        	else if(d.getItem().getEnchantmentLevel(Enchantment.BLAST_PROTECTION)+i+1<=4 && ri<30){
	        		eo[i].setEnchantment(Enchantment.BLAST_PROTECTION);
	        		eo[i].setEnchantmentLevel(i+1);
	        	}
	        	else if(d.getItem().getEnchantmentLevel(Enchantment.FIRE_PROTECTION)+i+1<=4 && ri<45){
	        		eo[i].setEnchantment(Enchantment.FIRE_PROTECTION);
	        		eo[i].setEnchantmentLevel(i+1);
	        	}
	        	else if(d.getItem().getEnchantmentLevel(Enchantment.PROJECTILE_PROTECTION)+i+1<=4 && ri<60){
	        		eo[i].setEnchantment(Enchantment.PROJECTILE_PROTECTION);
	        		eo[i].setEnchantmentLevel(i+1);
	        	}
	        	else if(d.getItem().getEnchantmentLevel(Enchantment.FEATHER_FALLING)+i+1<=4 && ri<70){
	        		eo[i].setEnchantment(Enchantment.FEATHER_FALLING);
	        		eo[i].setEnchantmentLevel(i+1);
	        	}
	        	else if(d.getItem().getEnchantmentLevel(Enchantment.DEPTH_STRIDER)+i+1<=4 && ri<75){
	        		eo[i].setEnchantment(Enchantment.DEPTH_STRIDER);
	        		eo[i].setEnchantmentLevel(i+1);
	        	}
	        	else if(d.getItem().getEnchantmentLevel(Enchantment.FROST_WALKER)+i+1<=4 && ri<80){
	        		eo[i].setEnchantment(Enchantment.FROST_WALKER);
	        		eo[i].setEnchantmentLevel(i+1);
	        	}
	        	else if(d.getItem().getEnchantmentLevel(Enchantment.SOUL_SPEED)+i+1<=4 && ri<85){
	        		eo[i].setEnchantment(Enchantment.SOUL_SPEED);
	        		eo[i].setEnchantmentLevel(i+1);
	        	}
	        	else if(d.getItem().getEnchantmentLevel(Enchantment.THORNS)+i+1<=4 && ri<90){
	        		eo[i].setEnchantment(Enchantment.THORNS);
	        		eo[i].setEnchantmentLevel(i+1);
	        	}
	        	else {
	        		eo[i].setEnchantment(Enchantment.UNBREAKING);
	        		eo[i].setEnchantmentLevel(i+1);
	        	}
			}
			else if(d.getItem().getType().name().contains("HELMET")) {
	        	if(d.getItem().getEnchantmentLevel(Enchantment.PROTECTION)+i+1<=4 && ri<16){
	        		eo[i].setEnchantment(Enchantment.PROTECTION);
	        		eo[i].setEnchantmentLevel(i+1);
	        	}
	        	else if(d.getItem().getEnchantmentLevel(Enchantment.BLAST_PROTECTION)+i+1<=4 && ri<32){
	        		eo[i].setEnchantment(Enchantment.BLAST_PROTECTION);
	        		eo[i].setEnchantmentLevel(i+1);
	        	}
	        	else if(d.getItem().getEnchantmentLevel(Enchantment.FIRE_PROTECTION)+i+1<=4 && ri<48){
	        		eo[i].setEnchantment(Enchantment.FIRE_PROTECTION);
	        		eo[i].setEnchantmentLevel(i+1);
	        	}
	        	else if(d.getItem().getEnchantmentLevel(Enchantment.PROJECTILE_PROTECTION)+i+1<=4 && ri<64){
	        		eo[i].setEnchantment(Enchantment.PROJECTILE_PROTECTION);
	        		eo[i].setEnchantmentLevel(i+1);
	        	}
	        	else if(d.getItem().getEnchantmentLevel(Enchantment.AQUA_AFFINITY)+i+1<=4 && ri<75){
	        		eo[i].setEnchantment(Enchantment.AQUA_AFFINITY);
	        		eo[i].setEnchantmentLevel(i+1);
	        	}
	        	else if(d.getItem().getEnchantmentLevel(Enchantment.RESPIRATION)+i+1<=4 && ri<83){
	        		eo[i].setEnchantment(Enchantment.RESPIRATION);
	        		eo[i].setEnchantmentLevel(i+1);
	        	}
	        	else if(d.getItem().getEnchantmentLevel(Enchantment.THORNS)+i+1<=4 && ri<90){
	        		eo[i].setEnchantment(Enchantment.THORNS);
	        		eo[i].setEnchantmentLevel(i+1);
	        	}
	        	else {
	        		eo[i].setEnchantment(Enchantment.UNBREAKING);
	        		eo[i].setEnchantmentLevel(i+1);
	        	}
			}
			eh.put(i, eo[i]);
		}
    	if(d.getInventory().getItem(1)!=null && d.getInventory().getItem(1).isSimilar(new ItemStack(Material.LAPIS_LAZULI))) {
    		d.setCancelled(false);
			if(!ench.containsRow(p.getUniqueId().toString() + d.getItem().toString())){
		    	for(int i =0; i<=2; i++) {
		    		ench.put(p.getUniqueId().toString() + d.getItem().toString(), i, eh.get(i));
		    	}
		    	bonus.put(p.getUniqueId().toString() + d.getItem().toString(), (double) d.getEnchantmentBonus());
			}
    		return;
    	}
	}

}