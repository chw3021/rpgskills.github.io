package io.github.chw3021.classes.oceanknight;



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

public class OceSkillsGui{
	



	public void itemset(String display, Material ID, int data, int stack, List<String> Lore, int loc, Inventory inv)
	{
		ItemStack item = new ItemStack(ID);
		ItemMeta items = item.getItemMeta();
		items.setDisplayName(display);
		items.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		items.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		items.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
		Lore.forEach(l -> {
			l=ChatColor.RESET+l;
		});
		items.setLore(Lore);
		item.setItemMeta(items);
		inv.setItem(loc, item);
	}
	
	public void itemset(String display, ItemStack is, int data, int stack, List<String> Lore, int loc, Inventory inv)
	{
		ItemStack item = is;
		ItemMeta items = item.getItemMeta();
		items.setDisplayName(display);
		items.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		items.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		items.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
		items.setLore(Lore);
		item.setItemMeta(items);
		inv.setItem(loc, item);
	}
	
	
	public void OceSkillsinv(Player p)
	{
		Inventory Oceskillsinv = Bukkit.createInventory(null, 54, "Oceskills");
	    String path = new File("").getAbsolutePath();
		OceSkillsData fsd = new OceSkillsData(OceSkillsData.loadData(path +"/plugins/RPGskills/OceSkillsData.data"));
		Obtained.itemset(p, Oceskillsinv);

		if(p.getLocale().equalsIgnoreCase("ko_kr")) {
			itemset("바다창술", Material.TRIDENT, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+fsd.WaterSpear.getOrDefault(p.getUniqueId(),0),"",ChatColor.UNDERLINE+"[물 계열]","막기 + 손바꾸기","",ChatColor.BOLD+"6 X "+BigDecimal.valueOf(0.353*(1+fsd.WaterSpear.getOrDefault(p.getUniqueId(),0)*0.038)).setScale(2, RoundingMode.HALF_EVEN)+"D", "Master Lv.50"), 0, Oceskillsinv);
			itemset("물의방벽", Material.SHIELD, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+fsd.WaterBarrier.getOrDefault(p.getUniqueId(),0),"",ChatColor.UNDERLINE+"[물 계열]","막기 + 웅크리기 + 손바꾸기", "물밖에 있을경우 물을 소환합니다", "보호막안에서는 모든피해를 막습니다","",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.65*(1+fsd.WaterBarrier.getOrDefault(p.getUniqueId(),0)*0.015)).setScale(2, RoundingMode.HALF_EVEN)+"D", "Master Lv.50"), 1, Oceskillsinv);
			itemset("투창", Material.TRIDENT, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+fsd.Javelin.getOrDefault(p.getUniqueId(),0),"",ChatColor.UNDERLINE+"[물 계열]","좌클릭 + 점프", "창을 다시 주우면 대기시간의", "절반을 되돌려받습니다","",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.635*(1+fsd.Javelin.getOrDefault(p.getUniqueId(),0)*0.0453)).setScale(2, RoundingMode.HALF_EVEN)+"D", "Master Lv.50"), 2, Oceskillsinv);
			itemset("역조", Material.TRIDENT, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+fsd.RipCurrent.getOrDefault(p.getUniqueId(),0),"","급류 도중 손바꾸기","가까운 적을 끌어옵니다", "Master LV.1"), 3, Oceskillsinv);
			itemset("꿰뚫기", Material.TRIDENT, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+fsd.OceanCharge.getOrDefault(p.getUniqueId(),0),"",ChatColor.UNDERLINE+"[물 계열]","웅크리기 + 근접공격","",ChatColor.BOLD+"3 X "+BigDecimal.valueOf(0.7*(1+fsd.OceanCharge.getOrDefault(p.getUniqueId(),0)*0.0453)).setScale(2, RoundingMode.HALF_EVEN)+"D", "Master Lv.50"), 4, Oceskillsinv);
			itemset("해풍참", Material.TRIDENT, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+fsd.WetSwing.getOrDefault(p.getUniqueId(),0),"",ChatColor.UNDERLINE+"[물 계열]","손바꾸기 + 웅크리기","",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.935*(1+fsd.WetSwing.getOrDefault(p.getUniqueId(),0)*0.0433)).setScale(2, RoundingMode.HALF_EVEN)+"D", "Master Lv.50"), 5, Oceskillsinv);
			itemset("물보라", Material.BOOK, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+fsd.Splash.getOrDefault(p.getUniqueId(),0),"","공격력이 증가합니다",ChatColor.BOLD+" X "+BigDecimal.valueOf(1.1*(1+fsd.Splash.getOrDefault(p.getUniqueId(),0)*0.03415)).setScale(2, RoundingMode.HALF_EVEN),"", 
					"공격 또는 낙하시 광역피해를 발생시킵니다", "낙하피해에 면역이 됩니다",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.1*(1+fsd.Splash.getOrDefault(p.getUniqueId(),0)*0.03415)).setScale(2, RoundingMode.HALF_EVEN)+"D",
					"","물 계열 저항력이 증가합니다", "삼지창에 급류 마법부여를 받습니다", "수영시 물에 이로운 효과들을 얻습니다"), 7, Oceskillsinv);
			if(Proficiency.getpro(p)<1) {
				itemset("회절(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/29315"), 9, Oceskillsinv);
				itemset("방패강타(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/29315"), 10, Oceskillsinv);
				itemset("낙조(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/29315"), 11, Oceskillsinv);
				itemset("고조(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/29315"), 12, Oceskillsinv);
				itemset("요동치는파도(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/29315"), 13, Oceskillsinv);
				itemset("역류(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/29315"), 14, Oceskillsinv);
				itemset("해상전투술(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/29315"), 16, Oceskillsinv);
				itemset("해신격(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/29315"), 17, Oceskillsinv);
			}
			else if(Proficiency.getpro(p)<2 && Proficiency.getpro(p)>=1) {
				itemset("회절", Material.PRISMARINE_BRICK_STAIRS, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[물 계열]","재입력시 회절을 사용합니다","(피해량은 바다창술 레벨에 비례합니다)","",ChatColor.BOLD+"4 X "+BigDecimal.valueOf(0.353*(1+fsd.WaterSpear.getOrDefault(p.getUniqueId(),0)*0.04)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 9, Oceskillsinv);
				itemset("방패강타", Material.DARK_PRISMARINE_SLAB, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[물 계열]","재입력시 방패강타를 사용합니다","(피해량은 물의방벽 레벨에 비례합니다)","",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.65*(1+fsd.WaterBarrier.getOrDefault(p.getUniqueId(),0)*0.045)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 10, Oceskillsinv);
				itemset("낙조", Material.HORN_CORAL, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[물 계열]","삼지창의 위치에 주변 적들을 모읍니다","(피해량은 투창 레벨에 비례합니다)","",ChatColor.BOLD+"6 X "+BigDecimal.valueOf(0.4*(1+fsd.Javelin.getOrDefault(p.getUniqueId(),0)*0.0353)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 11, Oceskillsinv);
				itemset("고조", Material.TRIDENT, 0, 1, Arrays.asList("여러 적들을 당기고 잠시 제압합니다"), 12, Oceskillsinv);
				itemset("요동치는파도", Material.DARK_PRISMARINE, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[물 계열]","꿰뚫기이후 재공격시 요동치는파도를 사용합니다","(피해량은 꿰뚫기 레벨에 비례합니다)","",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.7*(1+fsd.OceanCharge.getOrDefault(p.getUniqueId(),0)*0.07)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 13, Oceskillsinv);
				itemset("역류", Material.PRISMARINE_WALL, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[물 계열]","재입력시 역류를 사용합니다","(피해량은 해풍참 레벨에 비례합니다)","",ChatColor.BOLD+"2 X "+BigDecimal.valueOf(0.535*(1+fsd.WetSwing.getOrDefault(p.getUniqueId(),0)*0.0433)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 14, Oceskillsinv);
				itemset("해상전투술", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("공격력과 방어력이 증가합니다", "방패를 들고있는 도중 받는피해가 90% 감소합니다"), 16, Oceskillsinv);
				itemset("해신격", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[물 계열]","웅크리기 + 아이템던지기","",ChatColor.BOLD+"4 X 2.5D, 9.8D"), 17, Oceskillsinv);

				itemset("범람(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/155015"), 18, Oceskillsinv);
				itemset("급류(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/155015"), 20, Oceskillsinv);
				itemset("찌르기(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/155015"), 22, Oceskillsinv);
				itemset("가르기(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/155015"), 23, Oceskillsinv);
				itemset("용맹(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/155015"), 25, Oceskillsinv);
				itemset("바다의 분노(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/155015"), 26, Oceskillsinv);
			}
			else {
				itemset("회절", Material.PRISMARINE_BRICK_STAIRS, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[물 계열]","재입력시 회절을 사용합니다","(피해량은 바다창술 레벨에 비례합니다)","",ChatColor.BOLD+"4 X "+BigDecimal.valueOf(0.353*(1+fsd.WaterSpear.getOrDefault(p.getUniqueId(),0)*0.04)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 9, Oceskillsinv);
				itemset("방패강타", Material.DARK_PRISMARINE_SLAB, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[물 계열]","재입력시 방패강타를 사용합니다","(피해량은 물의방벽 레벨에 비례합니다)","",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.65*(1+fsd.WaterBarrier.getOrDefault(p.getUniqueId(),0)*0.045)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 10, Oceskillsinv);
				itemset("낙조", Material.HORN_CORAL, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[물 계열]","삼지창의 위치에 주변 적들을 모읍니다","(피해량은 투창 레벨에 비례합니다)","",ChatColor.BOLD+"6 X "+BigDecimal.valueOf(0.4*(1+fsd.Javelin.getOrDefault(p.getUniqueId(),0)*0.0353)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 11, Oceskillsinv);
				itemset("고조", Material.TRIDENT, 0, 1, Arrays.asList("여러 적들을 당기고 잠시 제압합니다"), 12, Oceskillsinv);
				itemset("요동치는파도", Material.DARK_PRISMARINE, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[물 계열]","꿰뚫기이후 재공격시 요동치는파도를 사용합니다","(피해량은 꿰뚫기 레벨에 비례합니다)","",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.7*(1+fsd.OceanCharge.getOrDefault(p.getUniqueId(),0)*0.07)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 13, Oceskillsinv);
				itemset("역류", Material.PRISMARINE_WALL, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[물 계열]","재입력시 역류를 사용합니다","(피해량은 해풍참 레벨에 비례합니다)","",ChatColor.BOLD+"2 X "+BigDecimal.valueOf(0.535*(1+fsd.WetSwing.getOrDefault(p.getUniqueId(),0)*0.0433)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 14, Oceskillsinv);
				itemset("해상전투술", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("공격력과 방어력이 증가합니다", "방패를 들고있는 도중 받는피해가 90% 감소합니다"), 16, Oceskillsinv);
				itemset("해신격", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[물 계열]","웅크리기 + 아이템던지기","",ChatColor.BOLD+"4 X 2.5D, 9.8D"), 17, Oceskillsinv);

				itemset("범람", Material.WATER_BUCKET, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[물 계열]","재입력시 범람을 사용합니다","(피해량은 바다창술 레벨에 비례합니다)","",ChatColor.BOLD+"30 X "+BigDecimal.valueOf(0.05*(1+fsd.WaterSpear.getOrDefault(p.getUniqueId(),0)*0.022)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 18, Oceskillsinv);
				itemset("급류", Material.TRIDENT, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[물 계열]","재입력시 삼지창위치로 이동합니다", "(피해량은 투창 레벨에 비례합니다)","",ChatColor.BOLD+" X "+BigDecimal.valueOf(1.1*(1+fsd.Javelin.getOrDefault(p.getUniqueId(),0)*0.0753)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 20, Oceskillsinv);
				itemset("찌르기", Material.TRIDENT, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[물 계열]","방패강타후 재공격시 찌르기를 사용합니다","(피해량은 꿰뚫기 레벨에 비례합니다)","",ChatColor.BOLD+" X "+BigDecimal.valueOf(1.1*(1+fsd.OceanCharge.getOrDefault(p.getUniqueId(),0)*0.0834)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 22, Oceskillsinv);
				itemset("가르기", Material.BUBBLE_CORAL, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[물 계열]","재입력시 가르기를 사용합니다","(피해량은 해풍참 레벨에 비례합니다)","",ChatColor.BOLD+" X "+BigDecimal.valueOf(1.235*(1+fsd.WetSwing.getOrDefault(p.getUniqueId(),0)*0.123)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 23, Oceskillsinv);
				itemset("용맹", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("공격력과 방어력이 증가합니다","해신격 대기시간이 감소합니다"), 25, Oceskillsinv);
				itemset("바다의 분노", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[물 계열]","달리기 + 아이템던지기","",ChatColor.BOLD+"20 X 1.2D, 12.5D"), 26, Oceskillsinv);
			}
			itemset("현재 숙련도", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(""+Proficiency.getproexp(p)), 27, Oceskillsinv);
			itemset("스킬포인트", Material.NETHER_STAR, 0, 1, Arrays.asList(ChatColor.AQUA+"SP."+fsd.SkillPoints.getOrDefault(p.getUniqueId(),0),"","클릭하면 스킬포인트가 초기화 됩니다"), 35, Oceskillsinv);
		
		}
		else {
			itemset("WaterSpear", Material.TRIDENT, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+fsd.WaterSpear.getOrDefault(p.getUniqueId(),0),"",ChatColor.UNDERLINE+"[Water]","Blocking + SwapHand","",ChatColor.BOLD+"6 X "+BigDecimal.valueOf(0.353*(1+fsd.WaterSpear.getOrDefault(p.getUniqueId(),0)*0.038)).setScale(2, RoundingMode.HALF_EVEN)+"D", "Master Lv.50"), 0, Oceskillsinv);
			itemset("WaterBarrier", Material.SHIELD, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+fsd.WaterBarrier.getOrDefault(p.getUniqueId(),0),"",ChatColor.UNDERLINE+"[Water]","Blocking + Sneaking + SwapHand", "Summon Water if you're not in Water", "Block All Damage When You're In Barrier","",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.65*(1+fsd.WaterBarrier.getOrDefault(p.getUniqueId(),0)*0.015)).setScale(2, RoundingMode.HALF_EVEN)+"D", "Master Lv.50"), 1, Oceskillsinv);
			itemset("Javelin", Material.TRIDENT, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+fsd.Javelin.getOrDefault(p.getUniqueId(),0),"",ChatColor.UNDERLINE+"[Water]","LeftClick + Jump", "Get Back Half of Cooldown", "When you pick up","",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.635*(1+fsd.Javelin.getOrDefault(p.getUniqueId(),0)*0.0453)).setScale(2, RoundingMode.HALF_EVEN)+"D", "Master Lv.50"), 2, Oceskillsinv);
			itemset("RipCurrent", Material.TRIDENT, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+fsd.RipCurrent.getOrDefault(p.getUniqueId(),0),"","SwapHand while RipTiding","Pull the Nearist Entity While Riptiding", "Master LV.1"), 3, Oceskillsinv);
			itemset("OceanCharge", Material.TRIDENT, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+fsd.OceanCharge.getOrDefault(p.getUniqueId(),0),"",ChatColor.UNDERLINE+"[Water]","Sneaking + Hit","",ChatColor.BOLD+"3 X "+BigDecimal.valueOf(0.7*(1+fsd.OceanCharge.getOrDefault(p.getUniqueId(),0)*0.0453)).setScale(2, RoundingMode.HALF_EVEN)+"D", "Master Lv.50"), 4, Oceskillsinv);
			itemset("WetSwing", Material.TRIDENT, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+fsd.WetSwing.getOrDefault(p.getUniqueId(),0),"",ChatColor.UNDERLINE+"[Water]","SwapHand + Sneaking","",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.935*(1+fsd.WetSwing.getOrDefault(p.getUniqueId(),0)*0.0433)).setScale(2, RoundingMode.HALF_EVEN)+"D", "Master Lv.50"), 5, Oceskillsinv);
			itemset("Splash", Material.BOOK, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+fsd.Splash.getOrDefault(p.getUniqueId(),0),"","Increases Damage",ChatColor.BOLD+" X "+BigDecimal.valueOf(1.1*(1+fsd.Splash.getOrDefault(p.getUniqueId(),0)*0.03415)).setScale(2, RoundingMode.HALF_EVEN),"", 
					"Inflicts Splash Damage When Attack Or Fall", "Immune to Falling Damage",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.1*(1+fsd.Splash.getOrDefault(p.getUniqueId(),0)*0.03415)).setScale(2, RoundingMode.HALF_EVEN)+"D",
					"", "Get Riptide Enchant if You Don't Have", "Get Water Ability When Swim"), 7, Oceskillsinv);
			if(Proficiency.getpro(p)<1) {
				itemset("Diffraction(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 9, Oceskillsinv);
				itemset("ShieldSmite(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 10, Oceskillsinv);
				itemset("Crisp(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 11, Oceskillsinv);
				itemset("Hightide(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 12, Oceskillsinv);
				itemset("Fluctuation(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 13, Oceskillsinv);
				itemset("Backwash(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 14, Oceskillsinv);
				itemset("AquaCombat(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 16, Oceskillsinv);
				itemset("Grand Waves(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 17, Oceskillsinv);
			}
			else if(Proficiency.getpro(p)<2 && Proficiency.getpro(p)>=1) {
				itemset("Diffraction", Material.PRISMARINE_BRICK_STAIRS, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Water]","Use Diffraction When You Use Once More","(Damage Affected By WaterSpear)","",ChatColor.BOLD+"4 X "+BigDecimal.valueOf(0.353*(1+fsd.WaterSpear.getOrDefault(p.getUniqueId(),0)*0.04)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 9, Oceskillsinv);
				itemset("ShieldSmite", Material.DARK_PRISMARINE_SLAB, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Water]","Use ShieldSmite When Use Once More","(Damage Affected By WaterBarrier)","",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.65*(1+fsd.WaterBarrier.getOrDefault(p.getUniqueId(),0)*0.045)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 10, Oceskillsinv);
				itemset("Crisp", Material.HORN_CORAL, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Water]","Pull Near By Enemies To Hit Pos","(Damage Affected By Javelin)","",ChatColor.BOLD+"6 X "+BigDecimal.valueOf(0.4*(1+fsd.Javelin.getOrDefault(p.getUniqueId(),0)*0.0353)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 11, Oceskillsinv);
				itemset("Hightide", Material.TRIDENT, 0, 1, Arrays.asList("Able to Pull Multiple Entities","Hold Enemies Shortly"), 12, Oceskillsinv);
				itemset("Fluctuation", Material.DARK_PRISMARINE, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Water]","Smite With Shield After OceanCharge","(Damage Affected By OceanCharge)","",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.7*(1+fsd.OceanCharge.getOrDefault(p.getUniqueId(),0)*0.07)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 13, Oceskillsinv);
				itemset("Backwash", Material.PRISMARINE_WALL, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Water]","Use Backwash When Use Once More","(Damage Affected By WetSwing)","",ChatColor.BOLD+"2 X "+BigDecimal.valueOf(0.535*(1+fsd.WetSwing.getOrDefault(p.getUniqueId(),0)*0.0433)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 14, Oceskillsinv);
				itemset("AquaCombat", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("Increases Whole Skills Damage & Armor", "Reduces 90% Damage While Raising Shield"), 16, Oceskillsinv);
				itemset("Grand Waves", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Water]","Sneaking + ThrowItem","",ChatColor.BOLD+"4 X 2.5D, 9.8D"), 17, Oceskillsinv);

				itemset("Flood(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 18, Oceskillsinv);
				itemset("Torrent(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 20, Oceskillsinv);
				itemset("Impale(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 22, Oceskillsinv);
				itemset("Cleave(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 23, Oceskillsinv);
				itemset("Prowess(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 25, Oceskillsinv);
				itemset("Wrath Of Sea(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 26, Oceskillsinv);
			}
			else {
				itemset("Diffraction", Material.PRISMARINE_BRICK_STAIRS, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Water]","Use Diffraction When You Use Once More","(Damage Affected By WaterSpear)","",ChatColor.BOLD+"4 X "+BigDecimal.valueOf(0.353*(1+fsd.WaterSpear.getOrDefault(p.getUniqueId(),0)*0.04)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 9, Oceskillsinv);
				itemset("ShieldSmite", Material.DARK_PRISMARINE_SLAB, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Water]","Use ShieldSmite When Use Once More","(Damage Affected By WaterBarrier)","",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.65*(1+fsd.WaterBarrier.getOrDefault(p.getUniqueId(),0)*0.045)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 10, Oceskillsinv);
				itemset("Crisp", Material.HORN_CORAL, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Water]","Pull Near By Enemies To Hit Pos","(Damage Affected By Javelin)","",ChatColor.BOLD+"6 X "+BigDecimal.valueOf(0.4*(1+fsd.Javelin.getOrDefault(p.getUniqueId(),0)*0.0353)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 11, Oceskillsinv);
				itemset("Hightide", Material.TRIDENT, 0, 1, Arrays.asList("Able to Pull Multiple Entities","Hold Enemies Shortly"), 12, Oceskillsinv);
				itemset("Fluctuation", Material.DARK_PRISMARINE, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Water]","Smite With Shield After OceanCharge","(Damage Affected By OceanCharge)","",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.7*(1+fsd.OceanCharge.getOrDefault(p.getUniqueId(),0)*0.07)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 13, Oceskillsinv);
				itemset("Backwash", Material.PRISMARINE_WALL, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Water]","Use Backwash When Use Once More","(Damage Affected By WetSwing)","",ChatColor.BOLD+"2 X "+BigDecimal.valueOf(0.535*(1+fsd.WetSwing.getOrDefault(p.getUniqueId(),0)*0.0433)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 14, Oceskillsinv);
				itemset("AquaCombat", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("Increases Whole Skills Damage & Armor", "Reduces 90% Damage While Raising Shield"), 16, Oceskillsinv);
				itemset("Grand Waves", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Water]","Sneaking + ThrowItem","",ChatColor.BOLD+"4 X 2.5D, 9.8D"), 17, Oceskillsinv);

				itemset("Flood", Material.WATER_BUCKET, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Water]","Use Flood When You Use Once More","(Damage Affected By WaterSpear)","",ChatColor.BOLD+"30 X "+BigDecimal.valueOf(0.05*(1+fsd.WaterSpear.getOrDefault(p.getUniqueId(),0)*0.022)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 18, Oceskillsinv);
				itemset("Torrent", Material.TRIDENT, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Water]","Jump To Trident When Use Once More", "(Damage Affected By Javelin)","",ChatColor.BOLD+" X "+BigDecimal.valueOf(1.1*(1+fsd.Javelin.getOrDefault(p.getUniqueId(),0)*0.0753)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 20, Oceskillsinv);
				itemset("Impale", Material.TRIDENT, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Water]","Impale After Fluctuation","(Damage Affected By OceanCharge)","",ChatColor.BOLD+" X "+BigDecimal.valueOf(1.1*(1+fsd.OceanCharge.getOrDefault(p.getUniqueId(),0)*0.0834)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 22, Oceskillsinv);
				itemset("Cleave", Material.BUBBLE_CORAL, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Water]","Use Cleave When Use Once More","(Damage Affected By WetSwing)","",ChatColor.BOLD+" X "+BigDecimal.valueOf(1.235*(1+fsd.WetSwing.getOrDefault(p.getUniqueId(),0)*0.123)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 23, Oceskillsinv);
				itemset("Prowess", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("Increases Damage & Armor","Decreases Waves Cooldown"), 25, Oceskillsinv);
				itemset("Wrath Of Sea", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Water]","Sprinting + ThrowItem","",ChatColor.BOLD+"20 X 1.2D, 12.5D"), 26, Oceskillsinv);
			}
			itemset("Current Proficiency", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(""+Proficiency.getproexp(p)), 27, Oceskillsinv);
			itemset("SkillPoints", Material.NETHER_STAR, 0, 1, Arrays.asList(ChatColor.AQUA+"SP."+fsd.SkillPoints.getOrDefault(p.getUniqueId(),0),"","Click if you want to reset your skill's levels"), 35, Oceskillsinv);
		
		}
		
		
		p.openInventory(Oceskillsinv);
	}


}
