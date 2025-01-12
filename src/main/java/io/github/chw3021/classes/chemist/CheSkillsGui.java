package io.github.chw3021.classes.chemist;



import java.io.File;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import io.github.chw3021.classes.Proficiency;
import io.github.chw3021.classes.SkillsGui;
import io.github.chw3021.obtains.Obtained;
import net.md_5.bungee.api.ChatColor;

public class CheSkillsGui extends SkillsGui{
	
	public void CheSkillsinv(Player p)
	{
        String path = new File("").getAbsolutePath();
		CheSkillsData csd = new CheSkillsData(CheSkillsData.loadData(path +"/plugins/RPGskills/CheSkillsData.data"));
		Inventory skillsInv = Bukkit.createInventory(null, 36, "Cheskills");
		
		
		Integer pro = Proficiency.getpro(p);

		if(p.getLocale().equalsIgnoreCase("ko_kr")) {
			itemset("네이팜" , Material.MAGMA_BLOCK, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+csd.Coagulation.getOrDefault(p.getUniqueId(),0),"",ChatColor.UNDERLINE+"✬[화염]","🖮🖰 손바꾸기","",ChatColor.BOLD+"10 X "+BigDecimal.valueOf(0.085*(1+csd.Coagulation.getOrDefault(p.getUniqueId(),0)*0.065)).setScale(2, RoundingMode.HALF_EVEN)+"D","Master LV.50"), 0, skillsInv);
			itemset("추출", Material.GLASS_BOTTLE, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+csd.Extraction.getOrDefault(p.getUniqueId(),0),"🖮🖰 웅크리기 + 좌클릭","추출된 몹의 종류에 따라 이로운 효과를 얻습니다","기본: 속도, 점프력","벌레: 야간투시" ,"언데드: 힘, 저항","수중: 돌고래의 우아함, 수중호흡","엔더: 은신","기타: 재생", "Master LV.1"), 1, skillsInv);
			itemset("돌진" , Material.POTION, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+csd.Charge.getOrDefault(p.getUniqueId(),0),"",ChatColor.UNDERLINE+"✬[대지]","🖮🖰 웅크리기 + 우클릭","돌진중에는 무적상태입니다","재사용시 일찍 종료할수 있습니다","재사용시 일찍 종료할수 있습니다","",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.79*(1+csd.Charge.getOrDefault(p.getUniqueId(),0)*0.078)).setScale(2, RoundingMode.HALF_EVEN)+"D","Master LV.50"), 2, skillsInv);
			itemset("산성구름" , Material.LINGERING_POTION, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+csd.AcidCloud.getOrDefault(p.getUniqueId(),0),"",ChatColor.UNDERLINE+"✬[맹독]","🖮🖰 우클릭", "활성화/비활성화 스킬", "산성 게이지 사용","",ChatColor.BOLD+""+(4+pro)+" X "+BigDecimal.valueOf(0.31*(1+csd.AcidCloud.getOrDefault(p.getUniqueId(),0)*0.04)).setScale(2, RoundingMode.HALF_EVEN)+"D","Master LV.50"), 3, skillsInv);
			itemset("화염병" , Material.SPLASH_POTION, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+csd.MolotovCocktail.getOrDefault(p.getUniqueId(),0),"",ChatColor.UNDERLINE+"✬[화염]","🖮🖰 좌클릭 + 점프","",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.569*(1+csd.MolotovCocktail.getOrDefault(p.getUniqueId(),0)*0.0435)).setScale(2, RoundingMode.HALF_EVEN)+"D","Master LV.50"), 4, skillsInv);
			itemset("슬라임볼" , Material.SLIME_BALL, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+csd.SlimeBall.getOrDefault(p.getUniqueId(),0),"",ChatColor.UNDERLINE+"✬[맹독]","🖮🖰 손바꾸기 + 웅크리기","",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.45*(1+csd.SlimeBall.getOrDefault(p.getUniqueId(),0)*0.035)).setScale(2, RoundingMode.HALF_EVEN)+"D","Master LV.50"), 5, skillsInv);
			itemset("유독성" , Material.BOOK, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+csd.Poisonous.getOrDefault(p.getUniqueId(),0),"","공격력이 증가합니다","독 효과에 면역이 됩니다","맹독 저항이 상승합니다","",ChatColor.BOLD+" X "+BigDecimal.valueOf(1+csd.Poisonous.getOrDefault(p.getUniqueId(),0)*0.0336).setScale(2, RoundingMode.HALF_EVEN)), 6, skillsInv);
			if(Proficiency.getpro(p)<1) {
				itemset("백린(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/29315"), 9, skillsInv);
				itemset("적응(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/29315"), 10, skillsInv);
				itemset("산성폭풍(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/29315"), 11, skillsInv);
				itemset("중독성(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/29315"), 12, skillsInv);
				itemset("접착제(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/29315"), 13, skillsInv);
				itemset("마그마볼(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/29315"), 14, skillsInv);
				itemset("맹독(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/29315"), 15, skillsInv);
				itemset("신경독(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/29315"), 17, skillsInv);
			}
			else if(Proficiency.getpro(p)<2 && Proficiency.getpro(p)>=1) {
				itemset("백린", Material.WHITE_GLAZED_TERRACOTTA, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"✬[화염]","🖮🖰 재입력시 백린탄을 투척합니다", "(피해량은 네이팜 레벨에 비례합니다)","",ChatColor.BOLD+"15 X "+BigDecimal.valueOf(0.29*(1+csd.Coagulation.getOrDefault(p.getUniqueId(),0)*0.06)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 9, skillsInv);
				itemset("적응", Material.NETHERITE_PICKAXE, 0, 1, Arrays.asList("효과가 추가되고 멀미효과가 제거됩니다", "기본: 화염저항 추가","벌레: 공격속도 추가" ,"언데드: 흡수 추가","수상: 전도체의 힘 추가","엔더맨: 행운 추가","기타: 포만감 추가"), 10, skillsInv);
				itemset("산성폭풍", Material.DRAGON_BREATH, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"✬[맹독]", "돌진 종료후 재사용시 2초동안 산성구름을 강화합니다","산성구름이 적들을 중앙으로 모으고","자신의 이동속도가 감소합니다", "(피해량은 돌진 레벨에 비례합니다)","",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.58*(1+csd.Charge.getOrDefault(p.getUniqueId(),0)*0.07)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 11, skillsInv);
				itemset("중독성", Material.SUSPICIOUS_STEW, 0, 1, Arrays.asList("잔류시간과 범위가 증가합니다"), 12, skillsInv);
				itemset("접착제", Material.LIME_GLAZED_TERRACOTTA, 0, 1, Arrays.asList("피격당한 적을 짧게 제압합니다"), 13, skillsInv);
				itemset("마그마볼", Material.MAGMA_CREAM, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"✬[화염]","🖮🖰 재입력시 마그마볼을 투척합니다", "(피해량은 슬라임볼 레벨에 비례합니다)","",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.9*(1+csd.SlimeBall.getOrDefault(p.getUniqueId(),0)*0.07)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 14, skillsInv);
				itemset("맹독", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("전체 피해량이 증가합니다","",ChatColor.GREEN+ "활성화된 물약 효과 종류수에 비례해", ChatColor.GREEN+ "받는피해가 감소합니다", "0.15 + 종류수("+ p.getActivePotionEffects().size() + ") X 0.05"), 15, skillsInv);
				itemset("신경독", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"✬[맹독]","🖮🖰 웅크리기 + num4","산성구름이 추가피해를 입힙니다", "",ChatColor.BOLD+" X 0.5D"), 17, skillsInv);
				
				itemset("환각제(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/155015"), 18, skillsInv);
				itemset("산성추출(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/155015"), 19, skillsInv);
				itemset("산성폭탄(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/155015"), 20, skillsInv);
				itemset("괴사(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/155015"), 21, skillsInv);
				itemset("화염병난사(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/155015"), 22, skillsInv);
				itemset("발광구(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/155015"), 23, skillsInv);
				itemset("생체강화(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/155015"), 24, skillsInv);
				itemset("아마겟돈(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/155015"), 26, skillsInv);
			}
			else {
				itemset("백린", Material.WHITE_GLAZED_TERRACOTTA, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"✬[화염]","🖮🖰 재입력시 백린탄을 투척합니다", "(피해량은 네이팜 레벨에 비례합니다)","",ChatColor.BOLD+"15 X "+BigDecimal.valueOf(0.29*(1+csd.Coagulation.getOrDefault(p.getUniqueId(),0)*0.06)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 9, skillsInv);
				itemset("적응", Material.NETHERITE_PICKAXE, 0, 1, Arrays.asList("효과가 추가되고 멀미효과가 제거됩니다", "기본: 화염저항 추가","벌레: 공격속도 추가" ,"언데드: 흡수 추가","수상: 전도체의 힘 추가","엔더맨: 행운 추가","기타: 포만감 추가"), 10, skillsInv);
				itemset("산성폭풍", Material.DRAGON_BREATH, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"✬[맹독]", "돌진 종료후 재사용시 2초동안 산성구름을 강화합니다","산성구름이 적들을 중앙으로 모으고","자신의 이동속도가 감소합니다", "(피해량은 돌진 레벨에 비례합니다)","",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.58*(1+csd.Charge.getOrDefault(p.getUniqueId(),0)*0.07)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 11, skillsInv);
				itemset("중독성", Material.SUSPICIOUS_STEW, 0, 1, Arrays.asList("잔류시간과 범위가 증가합니다"), 12, skillsInv);
				itemset("접착제", Material.LIME_GLAZED_TERRACOTTA, 0, 1, Arrays.asList("피격당한 적을 짧게 제압합니다"), 13, skillsInv);
				itemset("마그마볼", Material.MAGMA_CREAM, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"✬[화염]","🖮🖰 재입력시 마그마볼을 투척합니다", "(피해량은 슬라임볼 레벨에 비례합니다)","",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.9*(1+csd.SlimeBall.getOrDefault(p.getUniqueId(),0)*0.07)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 14, skillsInv);
				itemset("맹독", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("전체 피해량이 증가합니다","",ChatColor.GREEN+ "활성화된 물약 효과 종류수에 비례해", ChatColor.GREEN+ "받는피해가 감소합니다", "0.2 + 종류수("+ p.getActivePotionEffects().size() + ") X 0.05"), 15, skillsInv);
				itemset("신경독", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"✬[맹독]","🖮🖰 웅크리기 + num4","산성구름이 추가피해를 입힙니다", "",ChatColor.BOLD+" X 0.5D"), 17, skillsInv);
				
				itemset("환각제", Material.YELLOW_GLAZED_TERRACOTTA, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"✬[번개]","재입력시 환각제를 투척합니다","(피해량은 네이팜 레벨에 비례합니다)","",ChatColor.BOLD+"20 X "+BigDecimal.valueOf(0.24*(1+csd.Coagulation.getOrDefault(p.getUniqueId(),0)*0.07)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 18, skillsInv);
				itemset("산성추출", Material.BEEHIVE, 0, 1, Arrays.asList("추출당한 적을 짧게 제압합니다","산성 게이지를 5% 충전합니다"), 19, skillsInv);
				itemset("산성폭탄", Material.TNT, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"✬[맹독]","🖮🖰 재입력시 산성폭탄을 사용합니다", "(피해량은 돌진 레벨에 비례합니다)","",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.5*(1+csd.Charge.getOrDefault(p.getUniqueId(),0)*0.05)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 20, skillsInv);
				itemset("괴사", Material.LIME_SHULKER_BOX, 0, 1, Arrays.asList("범위가 증가합니다","방어력을 무시하는 추가 고정피해를 입힙니다","",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.2*(1+csd.AcidCloud.getOrDefault(p.getUniqueId(),0)*0.035)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 21, skillsInv);
				itemset("화염병난사", Material.SUNFLOWER, 0, 1, Arrays.asList("8방향으로 화염병을 투척합니다"), 22, skillsInv);
				itemset("발광구", Material.GLOW_LICHEN, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"✬[번개]","🖮🖰 재입력시 발광구를 투척합니다", "(피해량은 슬라임볼 레벨에 비례합니다)","",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.8*(1+csd.SlimeBall.getOrDefault(p.getUniqueId(),0)*0.08)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 23, skillsInv);
				itemset("생체강화", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("공격력과 방어력이 증가합니다","신경독 쿨타임이 감소합니다"), 24, skillsInv);
				itemset("아마겟돈", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"✬[맹독]","🖮🖰 웅크리기 + num5", "",ChatColor.BOLD+" X 19.0D + 10 X 0.6D"), 26, skillsInv);
			}
			itemset("현재 숙련도", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(""+Proficiency.getproexp(p)), 27, skillsInv);
			itemset("스킬포인트" , SKILLPOINT, 0, 1, Arrays.asList(ChatColor.AQUA+"SP."+csd.SkillPoints.getOrDefault(p.getUniqueId(),0),"","클릭하면 스킬포인트가 초기화 됩니다"), 35, skillsInv);
		
		}
		else {
			itemset("Napalm" , Material.MAGMA_BLOCK, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+csd.Coagulation.getOrDefault(p.getUniqueId(),0),"",ChatColor.UNDERLINE+"✬[Flame]","🖮🖰 SwapHand","",ChatColor.BOLD+"10 X "+BigDecimal.valueOf(0.085*(1+csd.Coagulation.getOrDefault(p.getUniqueId(),0)*0.065)).setScale(2, RoundingMode.HALF_EVEN)+"D","Master LV.50"), 0, skillsInv);
			itemset("Extraction", Material.GLASS_BOTTLE, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+csd.Extraction.getOrDefault(p.getUniqueId(),0),"Sneaking + LeftClick","Get Positive Effect","Depending on Extracted EntityCategory","Base: Speed, Jump","Arthropod: Nightvision" ,"Undead: Strength, Resistance","Water: WaterBreath, DolphinsGrace","Enderman: Invisible","Else: Regeneration", "Master LV.1"), 1, skillsInv);
			itemset("Charge" , Material.POTION, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+csd.Charge.getOrDefault(p.getUniqueId(),0),"",ChatColor.UNDERLINE+"✬[Earth]","🖮🖰 Sneaking + Rightclick","Set Invulnerable While Charging","Quit Earlier If You Use Once More","",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.79*(1+csd.Charge.getOrDefault(p.getUniqueId(),0)*0.078)).setScale(2, RoundingMode.HALF_EVEN)+"D","Master LV.50"), 2, skillsInv);
			itemset("AcidCloud" , Material.LINGERING_POTION, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+csd.AcidCloud.getOrDefault(p.getUniqueId(),0),"",ChatColor.UNDERLINE+"✬[Poison]","🖮🖰 Rightclick", "On/Off skill","Consume Acid Gauge","",ChatColor.BOLD+""+(4+pro)+" X "+BigDecimal.valueOf(0.31*(1+csd.AcidCloud.getOrDefault(p.getUniqueId(),0)*0.04)).setScale(2, RoundingMode.HALF_EVEN)+"D","Master LV.50"), 3, skillsInv);
			itemset("MolotovCocktail" , Material.SPLASH_POTION, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+csd.MolotovCocktail.getOrDefault(p.getUniqueId(),0),"",ChatColor.UNDERLINE+"✬[Flame]","🖮🖰 LeftClick + Jump","",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.569*(1+csd.MolotovCocktail.getOrDefault(p.getUniqueId(),0)*0.0435)).setScale(2, RoundingMode.HALF_EVEN)+"D","Master LV.50"), 4, skillsInv);
			itemset("SlimeBall" , Material.SLIME_BALL, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+csd.SlimeBall.getOrDefault(p.getUniqueId(),0),"",ChatColor.UNDERLINE+"✬[Poison]","🖮🖰 SwapHand + Sneaking","",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.45*(1+csd.SlimeBall.getOrDefault(p.getUniqueId(),0)*0.035)).setScale(2, RoundingMode.HALF_EVEN)+"D","Master LV.50"), 5, skillsInv);
			itemset("Poisonous" , Material.BOOK, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+csd.Poisonous.getOrDefault(p.getUniqueId(),0),"","Increases Damage","Be Immune to Poison Effect","Increases Posion Resistance","",ChatColor.BOLD+" X "+BigDecimal.valueOf(1+csd.Poisonous.getOrDefault(p.getUniqueId(),0)*0.0336).setScale(2, RoundingMode.HALF_EVEN)), 6, skillsInv);
			if(Proficiency.getpro(p)<1) {
				itemset("WP(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 9, skillsInv);
				itemset("Adaptation(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 10, skillsInv);
				itemset("AcidStorm(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 11, skillsInv);
				itemset("Addicting(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 12, skillsInv);
				itemset("Lime(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 13, skillsInv);
				itemset("MagmaBall(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 14, skillsInv);
				itemset("Venom(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 15, skillsInv);
				itemset("VX(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 17, skillsInv);
			}
			else if(Proficiency.getpro(p)<2 && Proficiency.getpro(p)>=1) {
				itemset("WP", Material.WHITE_GLAZED_TERRACOTTA, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"✬[Flame]","🖮🖰 Throw WP When Use Once More", "(Damage Affected By Napalm)","",ChatColor.BOLD+"15 X "+BigDecimal.valueOf(0.29*(1+csd.Coagulation.getOrDefault(p.getUniqueId(),0)*0.06)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 9, skillsInv);
				itemset("Adaptation", Material.NETHERITE_PICKAXE, 0, 1, Arrays.asList("Give More Effect & Remove Confusion", "Base: +FireResistance","Arthropod: +AttackSpeed" ,"Undead: +Absorbtion","Water: +Conduit Power","Enderman: +Luck","Else: +Saturation"), 10, skillsInv);
				itemset("AcidStorm", Material.DRAGON_BREATH, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"✬[Poison]","🖮🖰 Activate For 2s When Use Once More", "After Charge Quited", "AcidCloud Gathers Enemies to Middle of Cloud","Decreases Speed While Storm Activated", "(Damage Affected By Charge)","",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.58*(1+csd.Charge.getOrDefault(p.getUniqueId(),0)*0.07)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 11, skillsInv);
				itemset("Addicting", Material.SUSPICIOUS_STEW, 0, 1, Arrays.asList("Increases Duration & Range"), 12, skillsInv);
				itemset("Glue", Material.LIME_GLAZED_TERRACOTTA, 0, 1, Arrays.asList("Hold Hit Enemies Shortly"), 13, skillsInv);
				itemset("MagmaBall", Material.MAGMA_CREAM, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"✬[Flame]","🖮🖰 Throw Magma Ball When Use Once More", "(Damage Affected By SlimeBall)","",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.8*(1+csd.SlimeBall.getOrDefault(p.getUniqueId(),0)*0.08)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 14, skillsInv);
				itemset("Venom", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("Increases Whole Skills Damage", "Decreases received damage in", "Propotion to Number of PotionEffectTypes","0.2 + (Number of Types["+p.getActivePotionEffects().size() +"]X 0.05)"), 15, skillsInv);
				itemset("VX", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"✬[Poison]","🖮🖰 Sneaking + num4","Acid clouds will cause additional damage", "",ChatColor.BOLD+" X 0.5D"), 17, skillsInv);
				
				itemset("Hallucinogen(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 18, skillsInv);
				itemset("AcidExtraction(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 19, skillsInv);
				itemset("AcidBomb(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 20, skillsInv);
				itemset("Necrosis(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 21, skillsInv);
				itemset("CocktailFlower(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 22, skillsInv);
				itemset("GlowingBall(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 23, skillsInv);
				itemset("Bioreformation(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 24, skillsInv);
				itemset("Armageddon(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 26, skillsInv);
			}
			else {
				itemset("WP", Material.WHITE_GLAZED_TERRACOTTA, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"✬[Flame]","🖮🖰 Throw WP When Use Once More", "(Damage Affected By Napalm)","",ChatColor.BOLD+"15 X "+BigDecimal.valueOf(0.29*(1+csd.Coagulation.getOrDefault(p.getUniqueId(),0)*0.06)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 9, skillsInv);
				itemset("Adaptation", Material.NETHERITE_PICKAXE, 0, 1, Arrays.asList("Give More Effect & Remove Confusion", "Base: +FireResistance","Arthropod: +AttackSpeed" ,"Undead: +Absorbtion","Water: +Conduit Power","Enderman: +Luck","Else: +Saturation"), 10, skillsInv);
				itemset("AcidStorm", Material.DRAGON_BREATH, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"✬[Poison]","🖮🖰 Activate For 2s When Use Once More", "After Charge Quited", "AcidCloud Gathers Enemies to Middle of Cloud","Decreases Speed While Storm Activated", "(Damage Affected By Charge)","",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.58*(1+csd.Charge.getOrDefault(p.getUniqueId(),0)*0.07)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 11, skillsInv);
				itemset("Addicting", Material.SUSPICIOUS_STEW, 0, 1, Arrays.asList("Increases Duration & Range"), 12, skillsInv);
				itemset("Glue", Material.LIME_GLAZED_TERRACOTTA, 0, 1, Arrays.asList("Hold Hit Enemies Shortly"), 13, skillsInv);
				itemset("MagmaBall", Material.MAGMA_CREAM, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"✬[Flame]","🖮🖰 Throw Magma Ball When Use Once More", "(Damage Affected By SlimeBall)","",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.8*(1+csd.SlimeBall.getOrDefault(p.getUniqueId(),0)*0.08)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 14, skillsInv);
				itemset("Venom", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("Increases Whole Skills Damage", "Decreases received damage in", "Propotion to Number of PotionEffectTypes","0.2 + (Number of Types["+p.getActivePotionEffects().size() +"]X 0.05)"), 15, skillsInv);
				itemset("VX", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"✬[Poison]","🖮🖰 Sneaking + num4","Acid clouds will cause additional damage", "",ChatColor.BOLD+" X 0.5D"), 17, skillsInv);
				
				itemset("Hallucinogen", Material.YELLOW_GLAZED_TERRACOTTA, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"✬[Lightning]","🖮🖰 Throw Hallucinogen When Use Once More", "(Damage Affected By Napalm)","",ChatColor.BOLD+"20 X "+BigDecimal.valueOf(0.24*(1+csd.Coagulation.getOrDefault(p.getUniqueId(),0)*0.07)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 18, skillsInv);
				itemset("AcidExtraction", Material.BEEHIVE, 0, 1, Arrays.asList("Hold Hit Enemies Shortly","Recover 5% Acid Gauge"), 19, skillsInv);
				itemset("AcidBomb", Material.TNT, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"✬[Poison]","🖮🖰 Use AcidBomb When Use Once More", "(Damage Affected By Charge)","",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.5*(1+csd.Charge.getOrDefault(p.getUniqueId(),0)*0.05)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 20, skillsInv);
				itemset("Necrosis", Material.LIME_SHULKER_BOX, 0, 1, Arrays.asList("Increases Range","Extra Damage Ignoring Armor","",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.2*(1+csd.AcidCloud.getOrDefault(p.getUniqueId(),0)*0.035)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 21, skillsInv);
				itemset("CocktailFlower", Material.SUNFLOWER, 0, 1, Arrays.asList("Throw Cocktail 8 directions"), 22, skillsInv);
				itemset("GlowingBall", Material.GLOW_LICHEN, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"✬[Lightning]","🖮🖰 Throw Glowing Ball When Use Once More", "(Damage Affected By SlimeBall)","",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.8*(1+csd.SlimeBall.getOrDefault(p.getUniqueId(),0)*0.08)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 23, skillsInv);
				itemset("Bioreformation", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("Increases Damage & Armor","Decreases VX Cooldown"), 24, skillsInv);
				itemset("Armageddon", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"✬[Poison]","🖮🖰 Sneaking + num5", "",ChatColor.BOLD+" X 19.0D + 10 X 0.6D"), 26, skillsInv);
			}
			itemset("Current Proficiency", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(""+Proficiency.getproexp(p)), 27, skillsInv);
			itemset("SkillPoints" , SKILLPOINT, 0, 1, Arrays.asList(ChatColor.AQUA+"SP."+csd.SkillPoints.getOrDefault(p.getUniqueId(),0),"","Click if you want to reset your skill's levels"), 35, skillsInv);
		
		}
		
		Obtained.itemset(p, skillsInv);
		p.openInventory(skillsInv);
	}


}