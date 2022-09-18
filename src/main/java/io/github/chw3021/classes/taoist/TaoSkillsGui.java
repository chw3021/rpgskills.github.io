package io.github.chw3021.classes.taoist;



import java.util.List;
import java.util.Arrays;
import java.io.File;
import java.math.BigDecimal;
import java.math.RoundingMode;

import org.bukkit.entity.Player;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import io.github.chw3021.classes.Proficiency;
import io.github.chw3021.obtains.Obtained;
import net.md_5.bungee.api.ChatColor;

public class TaoSkillsGui{
	

	public void itemset(String display, Material ID, int data, int stack, List<String> Lore, int loc, Inventory inv)
	{
		ItemStack item = new ItemStack(ID);
		ItemMeta items = item.getItemMeta();
		items.setDisplayName(display);
		items.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		items.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		items.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
		Lore.forEach(l -> {
			l=ChatColor.stripColor(l);
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
	
	public void TaoSkillsinv(Player p)
	{
	    String path = new File("").getAbsolutePath();
		TaoSkillsData tsd = new TaoSkillsData(TaoSkillsData.loadData(path +"/plugins/RPGskills/TaoSkillsData.data"));
		Inventory Taoskillsinv = Bukkit.createInventory(null, 54, "TaoSkills");
		Obtained.itemset(p, Taoskillsinv);


		if(p.getLocale().equalsIgnoreCase("ko_kr")) {
			itemset("기운", Material.CANDLE, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+tsd.Aura.getOrDefault(p.getUniqueId(), 0),"","웅크리기 + 아이템바꾸기(마우스휠)"
					,ChatColor.UNDERLINE+"[화염 계열, 대지 계열]","양의기운: 파티원들의 공격력(30%), 속도 증가","",ChatColor.UNDERLINE+"[서리 계열, 어둠 계열]","음의기운: ","파티원들의 방어력 30%증가","적 공격시 파티원 체력 회복", "Master Lv.1"), 0, Taoskillsinv);
			itemset("연상", Material.BLACK_CANDLE, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+tsd.Imagery.getOrDefault(p.getUniqueId(), 0),"","손바꾸기","",ChatColor.BOLD+" X (0.7D + "+BigDecimal.valueOf(tsd.Imagery.getOrDefault(p.getUniqueId(), 0)*0.9).setScale(2, RoundingMode.HALF_EVEN)+")", "Master Lv.50"), 1, Taoskillsinv);
			itemset("기공권", Material.YELLOW_CANDLE, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+tsd.Wave.getOrDefault(p.getUniqueId(), 0),"","우클릭","",ChatColor.BOLD+" X (0.67D + "+BigDecimal.valueOf(tsd.Wave.getOrDefault(p.getUniqueId(), 0)*0.64).setScale(2, RoundingMode.HALF_EVEN)+")" ,"Master LV.50"), 2, Taoskillsinv);
			itemset("증폭", Material.BROWN_CANDLE, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+tsd.Amplify.getOrDefault(p.getUniqueId(), 0),"","웅크리기 + 우클릭","기운의 범위를" + BigDecimal.valueOf(tsd.Amplify.getOrDefault(p.getUniqueId(), 0)*0.1).setScale(2, RoundingMode.HALF_EVEN) + "초간 증가시킵니다",
					"양의기운: 주변적을 공격합니다",ChatColor.BOLD+" X (0.2D + "+BigDecimal.valueOf(tsd.Amplify.getOrDefault(p.getUniqueId(), 0)*0.2).setScale(2, RoundingMode.HALF_EVEN)+")","",
					"음의기운: 파티원들을 즉시 회복합니다" ,"Master LV.50"), 3, Taoskillsinv);
			itemset("부적", Material.BLACK_BANNER, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+tsd.Charm.getOrDefault(p.getUniqueId(), 0),"","손바꾸기 + 웅크리기","",
					ChatColor.BOLD+"6 X (0.315D + "+BigDecimal.valueOf(tsd.Charm.getOrDefault(p.getUniqueId(), 0)*0.3455).setScale(2, RoundingMode.HALF_EVEN)+"), "+
					ChatColor.BOLD+" X (1.37D + "+BigDecimal.valueOf(tsd.Charm.getOrDefault(p.getUniqueId(), 0)*1.1).setScale(2, RoundingMode.HALF_EVEN)+"), ",
							"" ,"Master LV.50"), 4, Taoskillsinv);
			itemset("공중제비", Material.BLUE_BANNER, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+tsd.Flip.getOrDefault(p.getUniqueId(), 0),"","점프 + 좌클릭",
					"",ChatColor.BOLD+" X (0.3D + "+BigDecimal.valueOf(tsd.Flip.getOrDefault(p.getUniqueId(), 0)*0.34).setScale(2, RoundingMode.HALF_EVEN)+")" ,"Master LV.50"), 5, Taoskillsinv);
			itemset("잠해소비", Material.RED_CANDLE, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+tsd.CombustInside.getOrDefault(p.getUniqueId(), 0),"","웅크리기 + 근접공격",
					"",ChatColor.BOLD+" X (1.8D + "+BigDecimal.valueOf(tsd.CombustInside.getOrDefault(p.getUniqueId(), 0)*1.8).setScale(2, RoundingMode.HALF_EVEN)+")" ,"Master LV.50"), 6, Taoskillsinv);
			itemset("내공", Material.BOOK, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+tsd.Vibe.getOrDefault(p.getUniqueId(), 0),"","공격력이 증가합니다","",ChatColor.BOLD+" + "+BigDecimal.valueOf(tsd.Vibe.getOrDefault(p.getUniqueId(), 0)*0.6).setScale(2, RoundingMode.HALF_EVEN)), 7, Taoskillsinv);
			if(Proficiency.getpro(p)<1) {
				itemset("운기조식(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/29315"), 9, Taoskillsinv);
				itemset("기폭발(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/29315"), 10, Taoskillsinv);
				itemset("기공참(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/29315"), 11, Taoskillsinv);
				itemset("평온(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/29315"), 12, Taoskillsinv);
				itemset("대지의움켜쥠(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/29315"), 13, Taoskillsinv);
				itemset("순보(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/29315"), 14, Taoskillsinv);
				itemset("공명(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/29315"), 15, Taoskillsinv);
				itemset("통찰(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/29315"), 16, Taoskillsinv);
				itemset("명상(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/29315"), 17, Taoskillsinv);
			}
			else if(Proficiency.getpro(p)<2 && Proficiency.getpro(p)>=1) {
				itemset("운기조식", Material.SOUL_CAMPFIRE, 0, 1, Arrays.asList("기운의 버프 효과를 두배로 증가시킵니다"), 9, Taoskillsinv);
				itemset("기폭발", Material.FIREWORK_STAR, 0, 1, Arrays.asList("재입력시 기폭발을 사용합니다","",ChatColor.BOLD+" X (1.4D + "+BigDecimal.valueOf(tsd.Imagery.getOrDefault(p.getUniqueId(), 0)*1.5).setScale(2, RoundingMode.HALF_EVEN)+")","(피해량은 연상 레벨에 비례합니다)"), 10, Taoskillsinv);
				itemset("기공참", Material.DIAMOND_SWORD, 0, 1, Arrays.asList("재입력시 기공참를 사용합니다","",ChatColor.BOLD+"2 X (0.68D + "+BigDecimal.valueOf(tsd.Wave.getOrDefault(p.getUniqueId(), 0)*0.76).setScale(2, RoundingMode.HALF_EVEN)+")","(피해량은 기공권 레벨에 비례합니다)"), 11, Taoskillsinv);
				itemset("평온", Material.GREEN_CANDLE, 0, 1, Arrays.asList("재입력시 평온을 사용합니다",
						"양의기운: 주변적을 공격 및 제압합니다","",ChatColor.BOLD+" X (0.6D + "+BigDecimal.valueOf(tsd.Amplify.getOrDefault(p.getUniqueId(), 0)*0.8).setScale(2, RoundingMode.HALF_EVEN)+")",
						"음의기운: 파티원들을 무적상태로 만듭니다","공통: 해로운 효과들을 제거합니다","(피해량과 지속시간은 증폭 레벨에 비례합니다)"), 12, Taoskillsinv);
				itemset("대지의움켜쥠", Material.COARSE_DIRT, 0, 1, Arrays.asList("재입력시 기공참를 사용합니다","",ChatColor.BOLD+"10 X (0.115D + "+BigDecimal.valueOf(tsd.Charm.getOrDefault(p.getUniqueId(), 0)*0.2455).setScale(2, RoundingMode.HALF_EVEN)+")","(피해량은 부적 레벨에 비례합니다)"), 13, Taoskillsinv);
				itemset("순보", Material.NETHERITE_BOOTS, 0, 1, Arrays.asList("재입력시 기공참를 사용합니다","",ChatColor.BOLD+" X (0.9D + "+BigDecimal.valueOf(tsd.Flip.getOrDefault(p.getUniqueId(), 0)*0.9).setScale(2, RoundingMode.HALF_EVEN)+")","(피해량은 공중제비 레벨에 비례합니다)"), 14, Taoskillsinv);
				itemset("공명", Material.BELL, 0, 1, Arrays.asList("광역피해를 적용합니다"), 15, Taoskillsinv);
				itemset("통찰", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("공격력이 증가합니다"), 16, Taoskillsinv);
				itemset("명상", Material.WRITTEN_BOOK, 0, 1, Arrays.asList("웅크리기 + 아이템던지기","주변 파티원들을 치료합니다","",ChatColor.BOLD+"60 X 0.3D"), 17, Taoskillsinv);

				itemset("승천(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/155015"), 19, Taoskillsinv);
				itemset("기공장(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/155015"), 20, Taoskillsinv);
				itemset("만트라(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/155015"), 21, Taoskillsinv);
				itemset("기공파(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/155015"), 22, Taoskillsinv);
				itemset("초월(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/155015"), 25, Taoskillsinv);
				itemset("음양팔괘진(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/155015"), 26, Taoskillsinv);
			}
			else {
				itemset("운기조식", Material.SOUL_CAMPFIRE, 0, 1, Arrays.asList("기운의 버프 효과를 두배로 증가시킵니다"), 9, Taoskillsinv);
				itemset("기폭발", Material.FIREWORK_STAR, 0, 1, Arrays.asList("재입력시 기폭발을 사용합니다","",ChatColor.BOLD+" X (1.4D + "+BigDecimal.valueOf(tsd.Imagery.getOrDefault(p.getUniqueId(), 0)*1.5).setScale(2, RoundingMode.HALF_EVEN)+")","(피해량은 연상 레벨에 비례합니다)"), 10, Taoskillsinv);
				itemset("기공참", Material.DIAMOND_SWORD, 0, 1, Arrays.asList("재입력시 기공참를 사용합니다","",ChatColor.BOLD+"2 X (0.68D + "+BigDecimal.valueOf(tsd.Wave.getOrDefault(p.getUniqueId(), 0)*0.76).setScale(2, RoundingMode.HALF_EVEN)+")","(피해량은 기공권 레벨에 비례합니다)"), 11, Taoskillsinv);
				itemset("평온", Material.GREEN_CANDLE, 0, 1, Arrays.asList("재입력시 평온을 사용합니다",
						"양의기운: 주변적을 공격 및 제압합니다","",ChatColor.BOLD+" X (0.6D + "+BigDecimal.valueOf(tsd.Amplify.getOrDefault(p.getUniqueId(), 0)*0.8).setScale(2, RoundingMode.HALF_EVEN)+")",
						"음의기운: 파티원들을 무적상태로 만듭니다","공통: 해로운 효과들을 제거합니다","(피해량과 지속시간은 증폭 레벨에 비례합니다)"), 12, Taoskillsinv);
				itemset("대지의움켜쥠", Material.COARSE_DIRT, 0, 1, Arrays.asList("재입력시 기공참를 사용합니다","",ChatColor.BOLD+"10 X (0.115D + "+BigDecimal.valueOf(tsd.Charm.getOrDefault(p.getUniqueId(), 0)*0.2455).setScale(2, RoundingMode.HALF_EVEN)+")","(피해량은 부적 레벨에 비례합니다)"), 13, Taoskillsinv);
				itemset("순보", Material.NETHERITE_BOOTS, 0, 1, Arrays.asList("재입력시 기공참를 사용합니다","",ChatColor.BOLD+" X (0.9D + "+BigDecimal.valueOf(tsd.Flip.getOrDefault(p.getUniqueId(), 0)*0.9).setScale(2, RoundingMode.HALF_EVEN)+")","(피해량은 공중제비 레벨에 비례합니다)"), 14, Taoskillsinv);
				itemset("공명", Material.BELL, 0, 1, Arrays.asList("광역피해를 적용합니다"), 15, Taoskillsinv);
				itemset("통찰", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("공격력이 증가합니다"), 16, Taoskillsinv);
				itemset("명상", Material.WRITTEN_BOOK, 0, 1, Arrays.asList("웅크리기 + 아이템던지기","주변 파티원들을 치료합니다","",ChatColor.BOLD+"60 X 0.3D"), 17, Taoskillsinv);

				itemset("승천", Material.CUT_COPPER_SLAB, 0, 1, Arrays.asList("재입력시 승천을 사용합니다","",ChatColor.BOLD+" X (1.2D + "+BigDecimal.valueOf(tsd.Imagery.getOrDefault(p.getUniqueId(), 0)*1.3).setScale(2, RoundingMode.HALF_EVEN)+")","(피해량은 연상 레벨에 비례합니다)"), 19, Taoskillsinv);
				itemset("기공장", Material.BOW, 0, 1, Arrays.asList("재입력시 기공장을 사용합니다","",ChatColor.BOLD+" X (0.115D + "+BigDecimal.valueOf(tsd.Charm.getOrDefault(p.getUniqueId(), 0)*0.2455).setScale(2, RoundingMode.HALF_EVEN)+")","(피해량은 기공권 레벨에 비례합니다)"), 20, Taoskillsinv);
				itemset("만트라", Material.ACACIA_LEAVES, 0, 1, Arrays.asList("재입력시 만트라를 사용합니다","","기운 변경을 해도 5초동안 효과가 지속됩니다","",
						"5초간 파티원들의 허기를 채웁니다","",ChatColor.BOLD+" X (0.8D + "+BigDecimal.valueOf(tsd.Amplify.getOrDefault(p.getUniqueId(), 0)*0.9).setScale(2, RoundingMode.HALF_EVEN)+")","(피해량은 증폭 레벨에 비례합니다)"), 21, Taoskillsinv);
				itemset("기공파", Material.SOUL_CAMPFIRE, 0, 1, Arrays.asList("재입력시 기공파를 사용합니다","",ChatColor.BOLD+"12 X (0.1D + "+BigDecimal.valueOf(tsd.Charm.getOrDefault(p.getUniqueId(), 0)*0.12).setScale(2, RoundingMode.HALF_EVEN)+")","(피해량은 부적 레벨에 비례합니다)"), 22, Taoskillsinv);
				itemset("초월", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("공격력과 방어력이 증가합니다","명상의 재사용대기시간이 감소합니다","모든 개체들을 통과합니다", "투사체에 면역이 됩니다"), 25, Taoskillsinv);
				itemset("음양팔괘진", Material.WRITTEN_BOOK, 0, 1, Arrays.asList("달리기 + 아이템던지기","",ChatColor.BOLD+"8 X 1.5D, 3.5D, 12.5D"), 26, Taoskillsinv);
			}
			itemset("현재 숙련도", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(""+Proficiency.getproexp(p)), 27, Taoskillsinv);
			itemset("스킬포인트", Material.NETHER_STAR, 0, 1, Arrays.asList(ChatColor.AQUA+"SP."+tsd.SkillPoints.getOrDefault(p.getUniqueId(), 0),"","클릭하면 스킬포인트가 초기화 됩니다"), 35, Taoskillsinv);

		}
		else {
			itemset("Aura", Material.CANDLE, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+tsd.Aura.getOrDefault(p.getUniqueId(), 0),"","Sneaking + Item Change(MouseWheel)"
					,ChatColor.UNDERLINE+"[Fire, Earth]","Aura Of Positive: Increases Party Damage(30%), Speed","",ChatColor.UNDERLINE+"[Frost, Dark]","Aura Of Negative: ","Increases Party Armor 30%","Heal Party When You Attack", "Master Lv.1"), 0, Taoskillsinv);
			itemset("Imagery", Material.BLACK_CANDLE, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+tsd.Imagery.getOrDefault(p.getUniqueId(), 0),"","SwapHand","",ChatColor.BOLD+" X (0.7D + "+BigDecimal.valueOf(tsd.Imagery.getOrDefault(p.getUniqueId(), 0)*0.9).setScale(2, RoundingMode.HALF_EVEN)+")", "Master Lv.50"), 1, Taoskillsinv);
			itemset("Wave", Material.YELLOW_CANDLE, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+tsd.Wave.getOrDefault(p.getUniqueId(), 0),"","Rightclick","",ChatColor.BOLD+" X (0.67D + "+BigDecimal.valueOf(tsd.Wave.getOrDefault(p.getUniqueId(), 0)*0.64).setScale(2, RoundingMode.HALF_EVEN)+")" ,"Master LV.50"), 2, Taoskillsinv);
			itemset("Amplify", Material.BROWN_CANDLE, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+tsd.Amplify.getOrDefault(p.getUniqueId(), 0),"","Sneaking + Rightclick","Increases Aura Range For" + BigDecimal.valueOf(tsd.Amplify.getOrDefault(p.getUniqueId(), 0)*0.1).setScale(2, RoundingMode.HALF_EVEN) + "secs",
					"Aura Of Positive: Attack Near By Entity",ChatColor.BOLD+" X (0.2D + "+BigDecimal.valueOf(tsd.Amplify.getOrDefault(p.getUniqueId(), 0)*0.2).setScale(2, RoundingMode.HALF_EVEN)+")","","Aura Of Negetive: Heal Party Instantly" ,"Master LV.50"), 3, Taoskillsinv);
			itemset("Charm", Material.BLACK_BANNER, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+tsd.Charm.getOrDefault(p.getUniqueId(), 0),"","SwapHand + Sneaking","",
					ChatColor.BOLD+"6 X (0.315D + "+BigDecimal.valueOf(tsd.Charm.getOrDefault(p.getUniqueId(), 0)*0.3455).setScale(2, RoundingMode.HALF_EVEN)+"), "+
					ChatColor.BOLD+" X (1.37D + "+BigDecimal.valueOf(tsd.Charm.getOrDefault(p.getUniqueId(), 0)*1.1).setScale(2, RoundingMode.HALF_EVEN)+"), ",
							"" ,"Master LV.50"), 4, Taoskillsinv);
			itemset("Flip", Material.BLUE_BANNER, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+tsd.Flip.getOrDefault(p.getUniqueId(), 0),"","Jump + LeftClick",
					"",ChatColor.BOLD+" X (0.3D + "+BigDecimal.valueOf(tsd.Flip.getOrDefault(p.getUniqueId(), 0)*0.34).setScale(2, RoundingMode.HALF_EVEN)+")" ,"Master LV.50"), 5, Taoskillsinv);
			itemset("CombustInside", Material.RED_CANDLE, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+tsd.CombustInside.getOrDefault(p.getUniqueId(), 0),"","Sneaking + Hit",
					"",ChatColor.BOLD+" X (1.8D + "+BigDecimal.valueOf(tsd.CombustInside.getOrDefault(p.getUniqueId(), 0)*1.8).setScale(2, RoundingMode.HALF_EVEN)+")" ,"Master LV.50"), 6, Taoskillsinv);
			itemset("Vibe", Material.BOOK, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+tsd.Vibe.getOrDefault(p.getUniqueId(), 0),"","Increases damage","",ChatColor.BOLD+" + "+BigDecimal.valueOf(tsd.Vibe.getOrDefault(p.getUniqueId(), 0)*0.6).setScale(2, RoundingMode.HALF_EVEN)), 7, Taoskillsinv);
			if(Proficiency.getpro(p)<1) {
				itemset("MentalTraining(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 9, Taoskillsinv);
				itemset("Blast(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 10, Taoskillsinv);
				itemset("CharmSlash(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 11, Taoskillsinv);
				itemset("Serenity(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 12, Taoskillsinv);
				itemset("Grasp(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 13, Taoskillsinv);
				itemset("Shunpo(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 14, Taoskillsinv);
				itemset("Resonance(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 15, Taoskillsinv);
				itemset("Insight(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 16, Taoskillsinv);
				itemset("Meditation(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 17, Taoskillsinv);
			}
			else if(Proficiency.getpro(p)<2 && Proficiency.getpro(p)>=1) {
				itemset("MentalTraining", Material.SOUL_CAMPFIRE, 0, 1, Arrays.asList("Doubles Aura Buff Effect"), 9, Taoskillsinv);
				itemset("Blast", Material.FIREWORK_STAR, 0, 1, Arrays.asList("Use Blast When Use Once More","",ChatColor.BOLD+" X (1.4D + "+BigDecimal.valueOf(tsd.Imagery.getOrDefault(p.getUniqueId(), 0)*1.5).setScale(2, RoundingMode.HALF_EVEN)+")","(Damage Affected By Imagery)"), 10, Taoskillsinv);
				itemset("CharmSlash", Material.DIAMOND_SWORD, 0, 1, Arrays.asList("Use CharmSlash When Use Once More","",ChatColor.BOLD+"2 X (0.68D + "+BigDecimal.valueOf(tsd.Wave.getOrDefault(p.getUniqueId(), 0)*0.76).setScale(2, RoundingMode.HALF_EVEN)+")","(Damage Affected By Wave)"), 11, Taoskillsinv);
				itemset("Serenity", Material.GREEN_CANDLE, 0, 1, Arrays.asList("Use Serenity When Use Once More",
						"Aura Of Positive: Attack & Hold Near By Entity","",ChatColor.BOLD+" X (0.6D + "+BigDecimal.valueOf(tsd.Amplify.getOrDefault(p.getUniqueId(), 0)*0.8).setScale(2, RoundingMode.HALF_EVEN)+")",
						"Aura Of Negetive: Set Party Invulnerable","Both: Remove Negetive Effect","(Damage & Duration Affected By Amplify)"), 12, Taoskillsinv);
				itemset("Grasp", Material.COARSE_DIRT, 0, 1, Arrays.asList("Use Grasp When Use Once More","",ChatColor.BOLD+"10 X (0.115D + "+BigDecimal.valueOf(tsd.Charm.getOrDefault(p.getUniqueId(), 0)*0.2455).setScale(2, RoundingMode.HALF_EVEN)+")","(Damage Affected By Charm)"), 13, Taoskillsinv);
				itemset("Shunpo", Material.NETHERITE_BOOTS, 0, 1, Arrays.asList("Use Shunpo When Use Once More","",ChatColor.BOLD+" X (0.9D + "+BigDecimal.valueOf(tsd.Flip.getOrDefault(p.getUniqueId(), 0)*0.9).setScale(2, RoundingMode.HALF_EVEN)+")","(Damage Affected By Flip)"), 14, Taoskillsinv);
				itemset("Resonance", Material.BELL, 0, 1, Arrays.asList("Inflict splash damage"), 15, Taoskillsinv);
				itemset("Insight", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("Increases Whole Skills Damage"), 16, Taoskillsinv);
				itemset("Meditation", Material.WRITTEN_BOOK, 0, 1, Arrays.asList("Sneaking + ThrowItem","Heal Near Party","",ChatColor.BOLD+"60 X 0.3D"), 17, Taoskillsinv);

				itemset("Ascension(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 19, Taoskillsinv);
				itemset("SpiritStorm(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 20, Taoskillsinv);
				itemset("Mantra(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 21, Taoskillsinv);
				itemset("KamehameWave(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 22, Taoskillsinv);
				itemset("Transcendence(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 25, Taoskillsinv);
				itemset("Numinous(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 26, Taoskillsinv);
			}
			else {
				itemset("MentalTraining", Material.SOUL_CAMPFIRE, 0, 1, Arrays.asList("Doubles Aura Buff Effect"), 9, Taoskillsinv);
				itemset("Blast", Material.FIREWORK_STAR, 0, 1, Arrays.asList("Use Blast When Use Once More","",ChatColor.BOLD+" X (1.4D + "+BigDecimal.valueOf(tsd.Imagery.getOrDefault(p.getUniqueId(), 0)*1.5).setScale(2, RoundingMode.HALF_EVEN)+")","(Damage Affected By Imagery)"), 10, Taoskillsinv);
				itemset("CharmSlash", Material.DIAMOND_SWORD, 0, 1, Arrays.asList("Use CharmSlash When Use Once More","",ChatColor.BOLD+"2 X (0.68D + "+BigDecimal.valueOf(tsd.Wave.getOrDefault(p.getUniqueId(), 0)*0.76).setScale(2, RoundingMode.HALF_EVEN)+")","(Damage Affected By Wave)"), 11, Taoskillsinv);
				itemset("Serenity", Material.GREEN_CANDLE, 0, 1, Arrays.asList("Use Serenity When Use Once More",
						"Aura Of Positive: Attack & Hold Near By Entity","",ChatColor.BOLD+" X (0.6D + "+BigDecimal.valueOf(tsd.Amplify.getOrDefault(p.getUniqueId(), 0)*0.8).setScale(2, RoundingMode.HALF_EVEN)+")",
						"Aura Of Negetive: Set Party Invulnerable","Both: Remove Negetive Effect","(Damage & Duration Affected By Amplify)"), 12, Taoskillsinv);
				itemset("Grasp", Material.COARSE_DIRT, 0, 1, Arrays.asList("Use Grasp When Use Once More","",ChatColor.BOLD+"10 X (0.115D + "+BigDecimal.valueOf(tsd.Charm.getOrDefault(p.getUniqueId(), 0)*0.2455).setScale(2, RoundingMode.HALF_EVEN)+")","(Damage Affected By Charm)"), 13, Taoskillsinv);
				itemset("Shunpo", Material.NETHERITE_BOOTS, 0, 1, Arrays.asList("Use Shunpo When Use Once More","",ChatColor.BOLD+" X (0.9D + "+BigDecimal.valueOf(tsd.Flip.getOrDefault(p.getUniqueId(), 0)*0.9).setScale(2, RoundingMode.HALF_EVEN)+")","(Damage Affected By Flip)"), 14, Taoskillsinv);
				itemset("Resonance", Material.BELL, 0, 1, Arrays.asList("Inflict splash damage"), 15, Taoskillsinv);
				itemset("Insight", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("Increases Whole Skills Damage"), 16, Taoskillsinv);
				itemset("Meditation", Material.WRITTEN_BOOK, 0, 1, Arrays.asList("Sneaking + ThrowItem","Heal Near Party","",ChatColor.BOLD+"60 X 0.3D"), 17, Taoskillsinv);

				itemset("Ascension", Material.CUT_COPPER_SLAB, 0, 1, Arrays.asList("Use Ascension When Use Once More","",ChatColor.BOLD+" X (1.2D + "+BigDecimal.valueOf(tsd.Imagery.getOrDefault(p.getUniqueId(), 0)*1.3).setScale(2, RoundingMode.HALF_EVEN)+")","(Damage Affected By Imagery)"), 19, Taoskillsinv);
				itemset("SpiritStorm", Material.BOW, 0, 1, Arrays.asList("Use SpiritStorm When Use Once More","",ChatColor.BOLD+" X (0.115D + "+BigDecimal.valueOf(tsd.Charm.getOrDefault(p.getUniqueId(), 0)*0.2455).setScale(2, RoundingMode.HALF_EVEN)+")","(Damage Affected By Wave)"), 20, Taoskillsinv);
				itemset("Mantra", Material.ACACIA_LEAVES, 0, 1, Arrays.asList("Use Mantra When Use Once More","","Aura Effects Remain For Amplify Duration","Even if you Change Aura","",
						"Give Saturation Effect to Party","For 5 seconds","",ChatColor.BOLD+" X (0.8D + "+BigDecimal.valueOf(tsd.Amplify.getOrDefault(p.getUniqueId(), 0)*0.9).setScale(2, RoundingMode.HALF_EVEN)+")","(Damage Affected By Amplify)"), 21, Taoskillsinv);
				itemset("KamehameWave", Material.SOUL_CAMPFIRE, 0, 1, Arrays.asList("Use KamehameWave When Use Once More","",ChatColor.BOLD+"12 X (0.1D + "+BigDecimal.valueOf(tsd.Charm.getOrDefault(p.getUniqueId(), 0)*0.12).setScale(2, RoundingMode.HALF_EVEN)+")","(Damage Affected By Charm)"), 22, Taoskillsinv);
				itemset("Transcendence", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("Increases Damage & Armor","Decrease Meditation Cooldown","Able to Penetrate All Entities", "Be Immune To Projectile"), 25, Taoskillsinv);
				itemset("Numinous", Material.WRITTEN_BOOK, 0, 1, Arrays.asList("Sprinting + ThrowItem","",ChatColor.BOLD+"8 X 1.5D, 3.5D, 12.5D"), 26, Taoskillsinv);
			}
			itemset("Current Proficiency", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(""+Proficiency.getproexp(p)), 27, Taoskillsinv);
			itemset("SkillPoints", Material.NETHER_STAR, 0, 1, Arrays.asList(ChatColor.AQUA+"SP."+tsd.SkillPoints.getOrDefault(p.getUniqueId(), 0),"","Click if you want to reset your skill's levels"), 35, Taoskillsinv);

		}
		
		p.openInventory(Taoskillsinv);
	}


}
