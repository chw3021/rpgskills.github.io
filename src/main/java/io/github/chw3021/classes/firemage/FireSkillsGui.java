package io.github.chw3021.classes.firemage;



import java.io.File;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import io.github.chw3021.classes.Proficiency;
import io.github.chw3021.obtains.Obtained;
import net.md_5.bungee.api.ChatColor;

public class FireSkillsGui{
	



	public void itemset(String display, Material ID, int data, int stack, List<String> Lore, int loc, Inventory inv)
	{
		ItemStack item = new ItemStack(ID,stack);
		ItemMeta items = item.getItemMeta();
		items.setDisplayName(display);
		items.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		items.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		items.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
		items.setLore(Lore);
		item.setItemMeta(items);
		inv.setItem(loc, item);
	}
	
	public void FIreSkillsinv(Player p)
	{
		Inventory FIreskillsinv = Bukkit.createInventory(null, 54, "FIreskills");
		
		Obtained.itemset(p, FIreskillsinv);

	    String path = new File("").getAbsolutePath();
		FireSkillsData fsd = new FireSkillsData(FireSkillsData.loadData(path +"/plugins/RPGskills/FireSkillsData.data"));
		
		if(p.getLocale().equalsIgnoreCase("ko_kr")) {
			itemset("분화구", Material.CRYING_OBSIDIAN, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+fsd.FlowingLava.getOrDefault(p.getUniqueId(),0),"",ChatColor.UNDERLINE+"[화염 계열]","우클릭","",ChatColor.BOLD+"8 X "+BigDecimal.valueOf(0.032*(1+fsd.FlowingLava.getOrDefault(p.getUniqueId(),0)*0.04)).setScale(2, RoundingMode.HALF_EVEN).setScale(2, RoundingMode.HALF_EVEN)+"D", "Master Lv.50"), 0, FIreskillsinv);
			itemset("불의고리", Material.FIRE_CORAL_FAN, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+fsd.Ring.getOrDefault(p.getUniqueId(),0),"",ChatColor.UNDERLINE+"[화염 계열]","웅크리기 + 우클릭","",ChatColor.BOLD+"15 X "+BigDecimal.valueOf(0.2*(1+fsd.Ring.getOrDefault(p.getUniqueId(),0)*0.045)).setScale(2, RoundingMode.HALF_EVEN).setScale(2, RoundingMode.HALF_EVEN)+"D", "Master Lv.50"), 1, FIreskillsinv);
			itemset("화염구", Material.FIRE_CHARGE, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+fsd.Fireball.getOrDefault(p.getUniqueId(),0),"",ChatColor.UNDERLINE+"[화염 계열]","좌클릭 + 점프","",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.25*(1+fsd.Fireball.getOrDefault(p.getUniqueId(),0)*0.04)).setScale(2, RoundingMode.HALF_EVEN)+"D", "Master Lv.50"), 2, FIreskillsinv);
			itemset("확산", Material.BLAZE_ROD, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+fsd.Spread.getOrDefault(p.getUniqueId(),0),"",ChatColor.UNDERLINE+"[화염 계열]","웅크리기 + 근접공격","",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.5*(1+fsd.Spread.getOrDefault(p.getUniqueId(),0)*0.08)).setScale(2, RoundingMode.HALF_EVEN)+"D", "Master Lv.50"), 3, FIreskillsinv);
			itemset("화염의숨결", Material.CAMPFIRE, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+fsd.Breath.getOrDefault(p.getUniqueId(),0),"",ChatColor.UNDERLINE+"[화염 계열]","손바꾸기","시전중 방어력이 최대치가 됩니다","",ChatColor.BOLD+"3 X "+BigDecimal.valueOf(0.4*(1+fsd.Breath.getOrDefault(p.getUniqueId(),0)*0.05)).setScale(2, RoundingMode.HALF_EVEN)+"D", "Master Lv.50"), 4, FIreskillsinv);
			itemset("살아있는불꽃", Material.FIRE_CHARGE, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+fsd.AliveFlame.getOrDefault(p.getUniqueId(),0),"",ChatColor.UNDERLINE+"[화염 계열]","손바꾸기 + 웅크리기","",ChatColor.BOLD+"20 X "+BigDecimal.valueOf(0.07*(1+fsd.AliveFlame.getOrDefault(p.getUniqueId(),0)*0.016)).setScale(2, RoundingMode.HALF_EVEN)+"D", "Master Lv.50"), 5, FIreskillsinv);
			itemset("열기", Material.BOOK, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+fsd.HotBody.getOrDefault(p.getUniqueId(),0),"","공격력이 증가합니다",ChatColor.BOLD+" X "+BigDecimal.valueOf(1+fsd.HotBody.getOrDefault(p.getUniqueId(),0)*0.044).setScale(2, RoundingMode.HALF_EVEN),"", "적을 태웁니다", "화염에 면역이 되고 화염계열 저항이 증가합니다", "피해량의 5%를 반사합니다(플레이어는 1%)","","스킬사용후 3초안에 다른스킬을 사용하면","3초동안 열기가 활성화됩니다","열기 활성화 중에는 받는피해가 감소하며","주변 적에게 피해를 줍니다","최대 6중첩",ChatColor.BOLD+" X 0.03D X 중첩수"), 7, FIreskillsinv);
			if(Proficiency.getpro(p)<1) {
				itemset("흐르는용암(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/29315"), 9, FIreskillsinv);
				itemset("태양의움켜쥠(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/29315"), 10, FIreskillsinv);
				itemset("쌍구(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/29315"), 11, FIreskillsinv);
				itemset("연화(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/29315"), 12, FIreskillsinv);
				itemset("용암소나기(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/29315"), 13, FIreskillsinv);
				itemset("화염격(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/29315"), 14, FIreskillsinv);
				itemset("타오르는심장(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/29315"), 16, FIreskillsinv);
				itemset("불사조의 날갯짓(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/29315"), 17, FIreskillsinv);
			}
			else if(Proficiency.getpro(p)<2 && Proficiency.getpro(p)>=1) {
				itemset("흐르는용암", Material.LAVA_BUCKET, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[화염 계열]","재사용시 흐르는용암을 사용합니다", "(피해량은 분화구 레벨에 비례합니다)","",ChatColor.BOLD+"8 X "+BigDecimal.valueOf(0.02*(1+fsd.FlowingLava.getOrDefault(p.getUniqueId(),0)*0.035)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 9, FIreskillsinv);
				itemset("태양의움켜쥠", Material.SUNFLOWER, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[화염 계열]","재사용시 태양의움켜쥠을 사용합니다","(피해량은 불의고리 레벨에 비례합니다)","",ChatColor.BOLD+"3 X "+BigDecimal.valueOf(0.6*(1+fsd.Ring.getOrDefault(p.getUniqueId(),0)*0.057)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 10, FIreskillsinv);
				itemset("쌍구", Material.FIRE_CHARGE, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[화염 계열]","한번에 두개의 화염구를 발사합니다"), 11, FIreskillsinv);
				itemset("연화", Material.FIRE_CORAL_BLOCK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[화염 계열]","화염이 퍼진 적이 주변의", "또다른 적들에게 화염을 퍼뜨립니다"), 12, FIreskillsinv);
				itemset("용암소나기", Material.RED_GLAZED_TERRACOTTA, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[화염 계열]","재사용시 용암소나기를 사용합니다", "(피해량은 화염의숨결 레벨에 비례합니다)","",ChatColor.BOLD+"10 X "+BigDecimal.valueOf(0.3*(1+fsd.Breath.getOrDefault(p.getUniqueId(),0)*0.05)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 13, FIreskillsinv);
				itemset("화염격", Material.BLAZE_POWDER, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[화염 계열]","재사용시 화염격을 사용합니다", "(피해량은 살아있는불꽃 레벨에 비례합니다)","",ChatColor.BOLD+"4 X "+BigDecimal.valueOf(0.36*(1+fsd.AliveFlame.getOrDefault(p.getUniqueId(),0)*0.034)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 14, FIreskillsinv);
				itemset("타오르는심장", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("공격력과 방어력이 증가합니다"), 16, FIreskillsinv);
				itemset("불사조의 날갯짓", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[화염 계열]","웅크리기 + 아이템던지기","",ChatColor.BOLD+"5 X 3.5D"), 17, FIreskillsinv);
				
				itemset("화산폭풍(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/155015"), 19, FIreskillsinv);
				itemset("마그마결정체(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/155015"), 20, FIreskillsinv);
				itemset("용암폭발(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/155015"), 22, FIreskillsinv);
				itemset("햇빛의창(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/155015"), 23, FIreskillsinv);
				itemset("태양의힘(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/155015"), 25, FIreskillsinv);
				itemset("두번째 태양(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/155015"), 26, FIreskillsinv);
			}
			else {
				itemset("흐르는용암", Material.LAVA_BUCKET, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[화염 계열]","재사용시 흐르는용암을 사용합니다", "(피해량은 분화구 레벨에 비례합니다)","",ChatColor.BOLD+"8 X "+BigDecimal.valueOf(0.02*(1+fsd.FlowingLava.getOrDefault(p.getUniqueId(),0)*0.035)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 9, FIreskillsinv);
				itemset("태양의움켜쥠", Material.SUNFLOWER, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[화염 계열]","재사용시 태양의움켜쥠을 사용합니다","(피해량은 불의고리 레벨에 비례합니다)","",ChatColor.BOLD+"3 X "+BigDecimal.valueOf(0.6*(1+fsd.Ring.getOrDefault(p.getUniqueId(),0)*0.057)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 10, FIreskillsinv);
				itemset("쌍구", Material.FIRE_CHARGE, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[화염 계열]","한번에 두개의 화염구를 발사합니다"), 11, FIreskillsinv);
				itemset("연화", Material.FIRE_CORAL_BLOCK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[화염 계열]","화염이 퍼진 적이 주변의", "또다른 적들에게 화염을 퍼뜨립니다"), 12, FIreskillsinv);

				itemset("용암소나기", Material.RED_GLAZED_TERRACOTTA, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[화염 계열]","재사용시 용암소나기를 사용합니다", "(피해량은 화염의숨결 레벨에 비례합니다)","",ChatColor.BOLD+"10 X "+BigDecimal.valueOf(0.3*(1+fsd.Breath.getOrDefault(p.getUniqueId(),0)*0.05)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 13, FIreskillsinv);
				itemset("화염격", Material.BLAZE_POWDER, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[화염 계열]","재사용시 화염격을 사용합니다", "(피해량은 살아있는불꽃 레벨에 비례합니다)","",ChatColor.BOLD+"4 X "+BigDecimal.valueOf(0.36*(1+fsd.AliveFlame.getOrDefault(p.getUniqueId(),0)*0.034)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 14, FIreskillsinv);
				itemset("타오르는심장", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("공격력과 방어력이 증가합니다"), 16, FIreskillsinv);
				itemset("불사조의 날갯짓", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[화염 계열]","웅크리기 + 아이템던지기","",ChatColor.BOLD+"5 X 3.5D"), 17, FIreskillsinv);
				
				itemset("화산폭풍", Material.GILDED_BLACKSTONE, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[화염 계열]","재사용시 화산폭풍을 불러옵니다", "(피해량은 불의고리 레벨에 비례합니다)","",ChatColor.BOLD+"15 X "+BigDecimal.valueOf(0.25*(1+fsd.Ring.getOrDefault(p.getUniqueId(),0)*0.047)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 19, FIreskillsinv);
				itemset("마그마결정체", Material.MAGMA_BLOCK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[화염 계열]","재사용시 마그마결정체을 발사합니다", "(피해량은 화염구 레벨에 비례합니다)","",ChatColor.BOLD+"8 X "+BigDecimal.valueOf(0.39*(1+fsd.Fireball.getOrDefault(p.getUniqueId(),0)*0.054)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 20, FIreskillsinv);
				itemset("용암폭발", Material.MAGMA_BLOCK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[화염 계열]","재사용시 용암폭발을 사용합니다","(피해량은 화염의숨결 레벨에 비례합니다)","",ChatColor.BOLD+"6 X "+BigDecimal.valueOf(0.24*(1+fsd.Breath.getOrDefault(p.getUniqueId(),0)*0.05)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 22, FIreskillsinv);
				itemset("햇빛의창", Material.SHROOMLIGHT, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[화염 계열]","재사용시 햇빛의창을 사용합니다", "(피해량은 살아있는불꽃 레벨에 비례합니다)","",ChatColor.BOLD+"X "+BigDecimal.valueOf(1.4*(1+fsd.AliveFlame.getOrDefault(p.getUniqueId(),0)*0.05)).setScale(2, RoundingMode.HALF_EVEN)+"D, 6 X "+BigDecimal.valueOf(0.153*(1+fsd.AliveFlame.getOrDefault(p.getUniqueId(),0)*0.015)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 23, FIreskillsinv);
				itemset("태양의힘", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("공격력과 방어력이 증가합니다","불사조의 날갯짓 재사용 대기시간이 감소합니다"), 25, FIreskillsinv);
				itemset("두번째 태양", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[화염 계열]","달리기 + 아이템던지기","",ChatColor.BOLD+"10 X 2.5D"), 26, FIreskillsinv);
			}
			itemset("현재 숙련도", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(""+Proficiency.getproexp(p)), 27, FIreskillsinv);
			itemset("스킬포인트", Material.NETHER_STAR, 0, 1, Arrays.asList(ChatColor.AQUA+"SP."+fsd.SkillPoints.getOrDefault(p.getUniqueId(),0),"","클릭하면 스킬포인트가 초기화 됩니다"), 35, FIreskillsinv);
		
		}
		else {
			itemset("Eruption", Material.CRYING_OBSIDIAN, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+fsd.FlowingLava.getOrDefault(p.getUniqueId(),0),"",ChatColor.UNDERLINE+"[Flame]","RightClick","",ChatColor.BOLD+"8 X "+BigDecimal.valueOf(0.032*(1+fsd.FlowingLava.getOrDefault(p.getUniqueId(),0)*0.04)).setScale(2, RoundingMode.HALF_EVEN)+"D", "Master Lv.50"), 0, FIreskillsinv);
			itemset("Ring", Material.FIRE_CORAL_FAN, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+fsd.Ring.getOrDefault(p.getUniqueId(),0),"",ChatColor.UNDERLINE+"[Flame]","Sneaking + RightClick","",ChatColor.BOLD+"15 X "+BigDecimal.valueOf(0.2*(1+fsd.Ring.getOrDefault(p.getUniqueId(),0)*0.045)).setScale(2, RoundingMode.HALF_EVEN)+"D", "Master Lv.50"), 1, FIreskillsinv);
			itemset("Fireball", Material.FIRE_CHARGE, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+fsd.Fireball.getOrDefault(p.getUniqueId(),0),"",ChatColor.UNDERLINE+"[Flame]","LeftClick + Jump","",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.25*(1+fsd.Fireball.getOrDefault(p.getUniqueId(),0)*0.04)).setScale(2, RoundingMode.HALF_EVEN)+"D", "Master Lv.50"), 2, FIreskillsinv);
			itemset("Spread", Material.BLAZE_ROD, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+fsd.Spread.getOrDefault(p.getUniqueId(),0),"",ChatColor.UNDERLINE+"[Flame]","Sneaking + Hit","",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.5*(1+fsd.Spread.getOrDefault(p.getUniqueId(),0)*0.08)).setScale(2, RoundingMode.HALF_EVEN)+"D", "Master Lv.50"), 3, FIreskillsinv);
			itemset("Breath", Material.CAMPFIRE, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+fsd.Breath.getOrDefault(p.getUniqueId(),0),"",ChatColor.UNDERLINE+"[Flame]","SwapHand","Maximise Armor While casting","",ChatColor.BOLD+"3 X "+BigDecimal.valueOf(0.4*(1+fsd.Breath.getOrDefault(p.getUniqueId(),0)*0.05)).setScale(2, RoundingMode.HALF_EVEN)+"D", "Master Lv.50"), 4, FIreskillsinv);
			itemset("AliveFlame", Material.FIRE_CHARGE, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+fsd.AliveFlame.getOrDefault(p.getUniqueId(),0),"","SwapHand + Sneaking","",ChatColor.BOLD+"20 X "+BigDecimal.valueOf(0.07*(1+fsd.AliveFlame.getOrDefault(p.getUniqueId(),0)*0.016)).setScale(2, RoundingMode.HALF_EVEN)+"D", "Master Lv.50"), 5, FIreskillsinv);
			itemset("HotBody", Material.BOOK, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+fsd.HotBody.getOrDefault(p.getUniqueId(),0),"","Increases Damage",ChatColor.BOLD+" X "+BigDecimal.valueOf(1+fsd.HotBody.getOrDefault(p.getUniqueId(),0)*0.044),"", "Burn enime which attacked by you or attack you", "Get Fire Resistance", "Reflect 5% Damage (1% to Player)","","If you use another skill within","3 seconds after using the skill","Hotbody will be activated for 3s","During Hotbody activation, Increases Armor","and Damages your surroundings", "Max 6 stacks",ChatColor.BOLD+" X 0.03D X stacks"), 7, FIreskillsinv);
			if(Proficiency.getpro(p)<1) {
				itemset("FlowingLava(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 9, FIreskillsinv);
				itemset("SunClutch(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 10, FIreskillsinv);
				itemset("DoubleBall(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 11, FIreskillsinv);
				itemset("FlameChain(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 12, FIreskillsinv);
				itemset("LavaBoom(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 13, FIreskillsinv);
				itemset("FireStrike(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 14, FIreskillsinv);
				itemset("BurningHeart(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 16, FIreskillsinv);
				itemset("Phoenix Flap(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 17, FIreskillsinv);
			}
			else if(Proficiency.getpro(p)<2 && Proficiency.getpro(p)>=1) {
				itemset("FlowingLava", Material.LAVA_BUCKET, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Flame]","Use FlowingLava When Use Once More", "(Damage Affected By Eruption)","",ChatColor.BOLD+"8 X "+BigDecimal.valueOf(0.02*(1+fsd.FlowingLava.getOrDefault(p.getUniqueId(),0)*0.035)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 9, FIreskillsinv);
				itemset("SunClutch", Material.SUNFLOWER, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Flame]","Use SunClutch When Use Once More","(Damage Affected By Ring)","",ChatColor.BOLD+"3 X "+BigDecimal.valueOf(0.6*(1+fsd.Ring.getOrDefault(p.getUniqueId(),0)*0.057)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 10, FIreskillsinv);
				itemset("DoubleBall", Material.FIRE_CHARGE, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Flame]","Shot Two FireBall"), 11, FIreskillsinv);
				itemset("FlameChain", Material.FIRE_CORAL_BLOCK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Flame]","Hit Enemy Spread FireBall Once More"), 12, FIreskillsinv);
				itemset("LavaShower", Material.RED_GLAZED_TERRACOTTA, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Flame]","Call LavaShower When Use Once More", "(Damage Affected By Breath)","",ChatColor.BOLD+"10 X "+BigDecimal.valueOf(0.3*(1+fsd.Breath.getOrDefault(p.getUniqueId(),0)*0.05)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 13, FIreskillsinv);
				itemset("FireStrike", Material.BLAZE_POWDER, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Flame]","Use FireStrike When Use Once More", "(Damage Affected By AliveFlame)","",ChatColor.BOLD+"4 X "+BigDecimal.valueOf(0.36*(1+fsd.AliveFlame.getOrDefault(p.getUniqueId(),0)*0.034)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 14, FIreskillsinv);
				itemset("BurningHeart", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("Increases Whole Skills Damage & Armor","Increases Hotbody's Armor Increasement"), 16, FIreskillsinv);
				itemset("Phoenix Flap", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Flame]","Sneaking + ThrowItem",ChatColor.BOLD+"5 X 3.5D"), 17, FIreskillsinv);
				
				itemset("VolcanicStorm(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 19, FIreskillsinv);
				itemset("MagmaBlock(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 20, FIreskillsinv);
				itemset("LavaBoom(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 22, FIreskillsinv);
				itemset("SunLightSpear(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 23, FIreskillsinv);
				itemset("SolarForce(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 25, FIreskillsinv);
				itemset("New SunRise(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 26, FIreskillsinv);
			}
			else {
				itemset("FlowingLava", Material.LAVA_BUCKET, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Flame]","Use FlowingLava When Use Once More", "(Damage Affected By Eruption)","",ChatColor.BOLD+"8 X "+BigDecimal.valueOf(0.02*(1+fsd.FlowingLava.getOrDefault(p.getUniqueId(),0)*0.035)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 9, FIreskillsinv);
				itemset("SunClutch", Material.SUNFLOWER, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Flame]","Use SunClutch When Use Once More","(Damage Affected By Ring)","",ChatColor.BOLD+"3 X "+BigDecimal.valueOf(0.6*(1+fsd.Ring.getOrDefault(p.getUniqueId(),0)*0.057)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 10, FIreskillsinv);
				itemset("DoubleBall", Material.FIRE_CHARGE, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Flame]","Shot Two FireBall"), 11, FIreskillsinv);
				itemset("FlameChain", Material.FIRE_CORAL_BLOCK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Flame]","Hit Enemy Spread FireBall Once More"), 12, FIreskillsinv);
				itemset("LavaShower", Material.RED_GLAZED_TERRACOTTA, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Flame]","Call LavaShower When Use Once More", "(Damage Affected By Breath)","",ChatColor.BOLD+"10 X "+BigDecimal.valueOf(0.3*(1+fsd.Breath.getOrDefault(p.getUniqueId(),0)*0.05)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 13, FIreskillsinv);
				itemset("FireStrike", Material.BLAZE_POWDER, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Flame]","Use FireStrike When Use Once More", "(Damage Affected By AliveFlame)","",ChatColor.BOLD+"4 X "+BigDecimal.valueOf(0.36*(1+fsd.AliveFlame.getOrDefault(p.getUniqueId(),0)*0.034)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 14, FIreskillsinv);
				itemset("BurningHeart", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("Increases Whole Skills Damage & Armor","Increases Hotbody's Armor Increasement"), 16, FIreskillsinv);
				itemset("Phoenix Flap", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Flame]","Sneaking + ThrowItem",ChatColor.BOLD+"5 X 3.5D"), 17, FIreskillsinv);
				
				itemset("VolcanicStorm", Material.GILDED_BLACKSTONE, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Flame]","Call VolcanicStorm When Use Once More", "(Damage Affected By Ring)","",ChatColor.BOLD+"15 X "+BigDecimal.valueOf(0.25*(1+fsd.Ring.getOrDefault(p.getUniqueId(),0)*0.047)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 19, FIreskillsinv);
				itemset("MagmaBlock", Material.MAGMA_BLOCK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Flame]","Shot MagmaBlock When Use Once More", "(Damage Affected By FireBall)","",ChatColor.BOLD+"8 X "+BigDecimal.valueOf(0.39*(1+fsd.Fireball.getOrDefault(p.getUniqueId(),0)*0.054)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 20, FIreskillsinv);
				itemset("LavaBoom", Material.MAGMA_BLOCK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Flame]","Use LavaBoom When Use Once More","(Damage Affected By Breath)","",ChatColor.BOLD+"6 X "+BigDecimal.valueOf(0.24*(1+fsd.Breath.getOrDefault(p.getUniqueId(),0)*0.05)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 22, FIreskillsinv);
				itemset("SunLightSpear", Material.SHROOMLIGHT, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Flame]","Use SunLightSpear When Use Once More", "(Damage Affected By AliveFlame)","",ChatColor.BOLD+"X "+BigDecimal.valueOf(1.4*(1+fsd.AliveFlame.getOrDefault(p.getUniqueId(),0)*0.05)).setScale(2, RoundingMode.HALF_EVEN)+"D, 6 X "+BigDecimal.valueOf(0.153*(1+fsd.AliveFlame.getOrDefault(p.getUniqueId(),0)*0.015)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 23, FIreskillsinv);
				itemset("SolarForce", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("Increases Damage & Armor","Decreases Phoenix Flap Cooldown"), 25, FIreskillsinv);
				itemset("New SunRise", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Flame]","Sprinting + ThrowItem","",ChatColor.BOLD+"10 X 2.5D"), 26, FIreskillsinv);
			}
			itemset("Current Proficiency", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(""+Proficiency.getproexp(p)), 27, FIreskillsinv);
			itemset("SkillPoints", Material.NETHER_STAR, 0, 1, Arrays.asList(ChatColor.AQUA+"SP."+fsd.SkillPoints.getOrDefault(p.getUniqueId(),0),"","Click if you want to reset your skill's levels"), 35, FIreskillsinv);
		
		}
		
		p.openInventory(FIreskillsinv);
	}


}
