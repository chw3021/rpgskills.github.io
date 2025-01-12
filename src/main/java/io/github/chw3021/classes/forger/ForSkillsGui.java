package io.github.chw3021.classes.forger;



import java.util.Arrays;
import java.io.File;
import java.math.BigDecimal;
import java.math.RoundingMode;

import org.bukkit.entity.Player;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import io.github.chw3021.classes.Proficiency;
import io.github.chw3021.classes.SkillsGui;
import io.github.chw3021.obtains.Obtained;
import net.md_5.bungee.api.ChatColor;

public class ForSkillsGui extends SkillsGui{




	public void ForSkillsinv(Player p)
	{
		String path = new File("").getAbsolutePath();
		Inventory skillsInv = Bukkit.createInventory(null, 36, "Forskills");
		

		ForSkillsData fsd = new ForSkillsData(ForSkillsData.loadData(path +"/plugins/RPGskills/ForSkillsData.data"));

		if(p.getLocale().equalsIgnoreCase("ko_kr")) {
			itemset("TNT발사기", Material.WOODEN_PICKAXE, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+fsd.TNTLauncher.getOrDefault(p.getUniqueId(),0),"",ChatColor.UNDERLINE+"✬[대지]","🖮🖰 손바꾸기","",ChatColor.BOLD+"4 X "+BigDecimal.valueOf(0.35*(1+fsd.TNTLauncher.getOrDefault(p.getUniqueId(),0)*0.0312)).setScale(2, RoundingMode.HALF_EVEN)+"D","Master LV.50"), 0, skillsInv);
			itemset("전자기관단총", Material.STONE_PICKAXE, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+fsd.RailSMG.getOrDefault(p.getUniqueId(),0),"",ChatColor.UNDERLINE+"✬[번개]","🖮🖰 우클릭","",ChatColor.BOLD+"10 X "+BigDecimal.valueOf(0.113*(1+fsd.RailSMG.get(p.getUniqueId())*0.042)).setScale(2, RoundingMode.HALF_EVEN) +"D","Master LV.50"), 1, skillsInv);
			itemset("충격파", Material.STONE_PICKAXE, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+fsd.DoubleBarrel.getOrDefault(p.getUniqueId(),0),"",ChatColor.UNDERLINE+"✬[번개]","🖮🖰 좌클릭","",ChatColor.BOLD+"2 X 0.135D", "Master LV.1"), 2, skillsInv);
			itemset("천둥포", Material.IRON_PICKAXE, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+fsd.LightningCannon.getOrDefault(p.getUniqueId(),0),"",ChatColor.UNDERLINE+"✬[번개]","🖮🖰 웅크리기 + 우클릭","",ChatColor.BOLD+"X "+BigDecimal.valueOf(0.9*(1+fsd.LightningCannon.getOrDefault(p.getUniqueId(),0)*0.06)).setScale(2, RoundingMode.HALF_EVEN)+"D","Master LV.50"), 3, skillsInv);
			itemset("점착미사일", Material.GOLDEN_PICKAXE, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+fsd.HoneyMissile.getOrDefault(p.getUniqueId(),0),"",ChatColor.UNDERLINE+"✬[대지]","🖮🖰 웅크리기 + 좌클릭", "점착된 적은 받는피해가 20회동안 증가합니다", "20회의 피격 또는 15초가 지나면 폭발합니다","",ChatColor.BOLD+"20 X "+BigDecimal.valueOf(0.085*(1+fsd.HoneyMissile.getOrDefault(p.getUniqueId(),0)*0.01)).setScale(2, RoundingMode.HALF_EVEN)+"D",ChatColor.BOLD+"X"+BigDecimal.valueOf(0.53*(1+fsd.HoneyMissile.getOrDefault(p.getUniqueId(),0)*0.042)*(Proficiency.getpro(p)>=1 ? 2:1)).setScale(4, RoundingMode.HALF_EVEN)+"D","Master LV.50"), 4, skillsInv);
			itemset("기관총", Material.DIAMOND_PICKAXE, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+fsd.MachineGun.getOrDefault(p.getUniqueId(),0),"",ChatColor.UNDERLINE+"✬[대지]","🖮🖰 손바꾸기 + 웅크리기","🖮🖰 마우스 스크롤로 발사체를 바꿀수있습니다","화살: 관통, 엔더드래곤 공격가능","탄알: 높은 피해량, 위더 공격 가능","",
					ChatColor.BOLD+"탄알: X "+BigDecimal.valueOf(0.0658*(1+fsd.MachineGun.getOrDefault(p.getUniqueId(),0)*0.064)).setScale(2, RoundingMode.HALF_EVEN)+"D"+","
							+ " 화살: "+BigDecimal.valueOf(0.0013*(1+fsd.MachineGun.getOrDefault(p.getUniqueId(),0)*0.0125)).setScale(4, RoundingMode.HALF_EVEN)+"D","Master LV.50"), 5, skillsInv);
			itemset("개발", Material.BOOK, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+fsd.Development.getOrDefault(p.getUniqueId(),0),"","공격력이 증가합니다","기관총 재장전시간이 감소합니다","",ChatColor.BOLD+"X "+BigDecimal.valueOf(1+fsd.Development.getOrDefault(p.getUniqueId(),0)*0.057).setScale(2, RoundingMode.HALF_EVEN)), 7, skillsInv);
			if(Proficiency.getpro(p)<1) {
				itemset("압축공기(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/29315"), 9, skillsInv);
				itemset("전자나선(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/29315"), 10, skillsInv);
				itemset("충격(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/29315"), 11, skillsInv);
				itemset("분광(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/29315"), 12, skillsInv);
				itemset("뇌관(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/29315"), 13, skillsInv);
				itemset("과열(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/29315"), 14, skillsInv);
				itemset("첨단기술(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/29315"), 16, skillsInv);
				itemset("용의숨결발사기(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/29315"), 17, skillsInv);
			}
			else if(Proficiency.getpro(p)<2 && Proficiency.getpro(p)>=1) {
				itemset("압축공기", Material.PISTON, 0, 1, Arrays.asList(ChatColor. UNDERLINE+"✬[대지]","🖮🖰 재입력시 압축공기를 발사해 위로 올라갑니다", "(피해량은 TNT발사기 레벨에 비례합니다)","",ChatColor.BOLD+"X "+BigDecimal.valueOf(0.42*(1+fsd.TNTLauncher.getOrDefault(p.getUniqueId(),0)*0.04)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 9, skillsInv);
				itemset("전자나선", Material.CYAN_DYE, 0, 1, Arrays.asList(ChatColor. UNDERLINE+"✬[번개]","🖮🖰 재입력시 전자나선을 사용합니다", "(피해량은 전자기관단총 레벨에 비례합니다)","",ChatColor.BOLD+"5 X"+demical(0.32*(1+fsd.RailSMG.get(p.getUniqueId())*0.092))+"D"), 10, skillsInv);
				itemset("충격", Material.CREEPER_HEAD, 0, 1, Arrays.asList("적을 기절시킵니다"), 11, skillsInv);
				itemset("분광기", Material.TINTED_GLASS, 0, 1, Arrays.asList(ChatColor. UNDERLINE+"✬[번개]","🖮🖰 재입력시 분광기를 사용해", "적들의 위치를 재정렬합니다", "(피해량은 천둥포 레벨에 비례합니다)","",ChatColor.BOLD+"X "+BigDecimal.valueOf(0.5*(1+fsd.LightningCannon.getOrDefault(p.getUniqueId(),0)*0.05)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 12, skillsInv);
				itemset("뇌관", Material.FIREWORK_STAR, 0, 1, Arrays.asList("🖮🖰 재입력시 미사일들을 즉시 폭발시킵니다","폭발 피해량이 증가합니다","뇌관을 이용해서 폭발시", "더높은 피해를 입힙니다","",ChatColor.BOLD+"X "+BigDecimal.valueOf(1.1*(1+fsd.HoneyMissile.getOrDefault(p.getUniqueId(),0)*0.0789)*(Proficiency.getpro(p))).setScale(2, RoundingMode.HALF_EVEN)+"D"), 13, skillsInv);
				itemset("과열", Material.REDSTONE_ORE, 0, 1, Arrays.asList("피해량과 연사력이 점차 상승합니다", "(최대 두배)"), 14, skillsInv);
				itemset("첨단기술", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("전체 피해량이 상승합니다","모든 스킬들이 대지,번개 피해를 추가로 입힙니다"), 16, skillsInv);
				itemset("용의숨결발사기", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"✬[대지]","🖮🖰 웅크리기 + num4","",ChatColor.BOLD+"30 X 1.05D"), 17, skillsInv);

				itemset("플라즈마류탄(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/155015"), 18, skillsInv);
				itemset("전자포(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/155015"), 19, skillsInv);
				itemset("광자방벽(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/155015"), 19, skillsInv);
				itemset("광자파도(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/155015"), 21, skillsInv);
				itemset("에너지순환(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/155015"), 23, skillsInv);
				itemset("창조(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/155015"), 25, skillsInv);
				itemset("영혼절단기(잠김)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("요구 숙련도: "+ Proficiency.getproexp(p) + "/155015"), 26, skillsInv);
			}
			else {
				itemset("압축공기", Material.PISTON, 0, 1, Arrays.asList(ChatColor. UNDERLINE+"✬[대지]","🖮🖰 재입력시 압축공기를 발사해 위로 올라갑니다", "(피해량은 TNT발사기 레벨에 비례합니다)","",ChatColor.BOLD+"X "+BigDecimal.valueOf(0.42*(1+fsd.TNTLauncher.getOrDefault(p.getUniqueId(),0)*0.04)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 9, skillsInv);
				itemset("전자나선", Material.CYAN_DYE, 0, 1, Arrays.asList(ChatColor. UNDERLINE+"✬[번개]","🖮🖰 재입력시 전자나선을 사용합니다", "(피해량은 전자기관단총 레벨에 비례합니다)","",ChatColor.BOLD+"5 X"+demical(0.32*(1+fsd.RailSMG.get(p.getUniqueId())*0.092))+"D"), 10, skillsInv);
				itemset("충격", Material.CREEPER_HEAD, 0, 1, Arrays.asList("적을 기절시킵니다"), 11, skillsInv);
				itemset("분광기", Material.TINTED_GLASS, 0, 1, Arrays.asList(ChatColor. UNDERLINE+"✬[번개]","🖮🖰 재입력시 분광기를 사용해", "적들의 위치를 재정렬합니다", "(피해량은 천둥포 레벨에 비례합니다)","",ChatColor.BOLD+"X "+BigDecimal.valueOf(0.5*(1+fsd.LightningCannon.getOrDefault(p.getUniqueId(),0)*0.05)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 12, skillsInv);
				itemset("뇌관", Material.FIREWORK_STAR, 0, 1, Arrays.asList("🖮🖰 재입력시 미사일들을 즉시 폭발시킵니다","폭발 피해량이 증가합니다","뇌관을 이용해서 폭발시", "더높은 피해를 입힙니다","",ChatColor.BOLD+"X "+BigDecimal.valueOf(1.1*(1+fsd.HoneyMissile.getOrDefault(p.getUniqueId(),0)*0.0789)*(Proficiency.getpro(p))).setScale(2, RoundingMode.HALF_EVEN)+"D"), 13, skillsInv);
				itemset("과열", Material.REDSTONE_ORE, 0, 1, Arrays.asList("피해량과 연사력이 점차 상승합니다", "(최대 두배)"), 14, skillsInv);
				itemset("첨단기술", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("전체 피해량이 상승합니다","모든 스킬들이 대지,번개 피해를 추가로 입힙니다"), 16, skillsInv);
				itemset("용의숨결발사기", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"✬[대지]","🖮🖰 웅크리기 + num4","",ChatColor.BOLD+"30 X 1.05D"), 17, skillsInv);

				itemset("플라즈마류탄", Material.CYAN_GLAZED_TERRACOTTA, 0, 1, Arrays.asList(ChatColor. UNDERLINE+"✬[번개]","🖮🖰 재입력시 플라즈마류탄을 발사합니다", "(피해량은 TNT발사기 레벨에 비례합니다)","",ChatColor.BOLD+"X "+BigDecimal.valueOf(1.42*(1+fsd.TNTLauncher.getOrDefault(p.getUniqueId(),0)*0.1)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 18, skillsInv);
				itemset("전자포", Material.WARPED_ROOTS, 0, 1, Arrays.asList(ChatColor. UNDERLINE+"✬[번개]","🖮🖰 재입력시 전자포을 사용합니다", "(피해량은 전자기관단총 레벨에 비례합니다)","",ChatColor.BOLD+"1 X "+BigDecimal.valueOf(1.66*(1+fsd.RailSMG.get(p.getUniqueId())*0.06)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 19, skillsInv);
				itemset("광자방벽", Material.CYAN_STAINED_GLASS, 0, 1, Arrays.asList("전방에서 받는 피해가 50% 감소합니다"), 20, skillsInv);
				itemset("광자파도", Material.SOUL_TORCH, 0, 1, Arrays.asList(ChatColor. UNDERLINE+"✬[번개]","🖮🖰 재입력시 광자파도를 사용합니다", "(피해량은 천둥포 레벨에 비례합니다)","",ChatColor.BOLD+"45 X "+BigDecimal.valueOf(0.5*(1+fsd.LightningCannon.getOrDefault(p.getUniqueId(),0)*0.03)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 21, skillsInv);
				itemset("에너지순환", Material.CYAN_STAINED_GLASS, 0, 1, Arrays.asList("다른 기술들도 과열을 5% 유발합니다"), 23, skillsInv);
				itemset("창조", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("공격력과 방어력이 증가합니다","용의숨결발사기 재사용대기시간이 감소합니다"), 25, skillsInv);
				itemset("영혼절단기", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"✬[번개]","🖮🖰 웅크리기 + num5",ChatColor.BOLD+"50 X 0.8D"), 26, skillsInv);
			}
			itemset("현재 숙련도", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(""+Proficiency.getproexp(p)), 27, skillsInv);
			itemset("스킬포인트", SKILLPOINT, 0, 1, Arrays.asList(ChatColor.AQUA+"SP."+fsd.SkillPoints.getOrDefault(p.getUniqueId(),0),"","클릭하면 스킬포인트가 초기화 됩니다"), 35, skillsInv);

		}
		else {
			itemset("TNTLauncher", Material.WOODEN_PICKAXE, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+fsd.TNTLauncher.getOrDefault(p.getUniqueId(),0),"",ChatColor.UNDERLINE+"✬[Earth]","🖮🖰 SwapHand","",ChatColor.BOLD+"4 X "+BigDecimal.valueOf(0.35*(1+fsd.TNTLauncher.getOrDefault(p.getUniqueId(),0)*0.0312)).setScale(2, RoundingMode.HALF_EVEN)+"D","Master LV.50"), 0, skillsInv);
			itemset("RailSMG", Material.STONE_PICKAXE, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+fsd.RailSMG.getOrDefault(p.getUniqueId(),0),"",ChatColor.UNDERLINE+"✬[Lightning]","🖮🖰 Rightclick","",ChatColor.BOLD+"10 X "+BigDecimal.valueOf(0.113*(1+fsd.RailSMG.get(p.getUniqueId())*0.042)).setScale(2, RoundingMode.HALF_EVEN)+"D","Master LV.50"), 1, skillsInv);
			itemset("Shockwave", Material.STONE_PICKAXE, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+fsd.DoubleBarrel.getOrDefault(p.getUniqueId(),0),"",ChatColor.UNDERLINE+"✬[Lightning]","🖮🖰 LeftClick","",ChatColor.BOLD+"2 X 0.135", "Master LV.1"), 2, skillsInv);
			itemset("LightningCannon", Material.IRON_PICKAXE, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+fsd.LightningCannon.getOrDefault(p.getUniqueId(),0),"",ChatColor.UNDERLINE+"✬[Lightning]","🖮🖰 Sneaking + Rightclick","",ChatColor.BOLD+"X "+BigDecimal.valueOf(0.9*(1+fsd.LightningCannon.getOrDefault(p.getUniqueId(),0)*0.06)).setScale(2, RoundingMode.HALF_EVEN)+"D","Master LV.50"), 3, skillsInv);
			itemset("HoneyMissile", Material.GOLDEN_PICKAXE, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+fsd.HoneyMissile.getOrDefault(p.getUniqueId(),0),"",ChatColor.UNDERLINE+"✬[Lightning]","🖮🖰 LeftClick + Sneaking", "Stick to hit enemies","Give additional damage for 20 hits", "Explode after 20 Hits or 15secs",ChatColor.BOLD+"20 X "+BigDecimal.valueOf(0.085*(1+fsd.HoneyMissile.getOrDefault(p.getUniqueId(),0)*0.01)).setScale(2, RoundingMode.HALF_EVEN)+"D",ChatColor.BOLD+"X"+BigDecimal.valueOf(0.53*(1+fsd.HoneyMissile.getOrDefault(p.getUniqueId(),0)*0.042)*(Proficiency.getpro(p)>=1 ? 2:1)).setScale(2, RoundingMode.HALF_EVEN)+"D","Master LV.50"), 4, skillsInv);
			itemset("MachineGun", Material.DIAMOND_PICKAXE, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+fsd.MachineGun.getOrDefault(p.getUniqueId(),0),"",ChatColor.UNDERLINE+"✬[Earth]","🖮🖰 SwapHand + Sneaking","🖮🖰 Change Projectile Using Mouse Scroll","Arrow: Piercing, Able to Hit EnderDragon","Bullet: More Damage, Able to Hit Wither","",ChatColor.BOLD+
					"Bullet: X "+BigDecimal.valueOf(0.0658*(1+fsd.MachineGun.getOrDefault(p.getUniqueId(),0)*0.064)).setScale(2, RoundingMode.HALF_EVEN)+"D"+
					", Arrow: "+BigDecimal.valueOf(0.0013*(1+fsd.MachineGun.getOrDefault(p.getUniqueId(),0)*0.0125)).setScale(4, RoundingMode.HALF_EVEN)+"D","Master LV.50"), 5, skillsInv);
			itemset("Development", Material.BOOK, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+fsd.Development.getOrDefault(p.getUniqueId(),0),"","Increases Weapon damage","Reduce MachineGun Reloading Duration","",ChatColor.BOLD+"X "+BigDecimal.valueOf(1+fsd.Development.getOrDefault(p.getUniqueId(),0)*0.057).setScale(2, RoundingMode.HALF_EVEN)), 7, skillsInv);
			if(Proficiency.getpro(p)<1) {
				itemset("Compressor(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 9, skillsInv);
				itemset("RailScrew(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 10, skillsInv);
				itemset("Impulse(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 11, skillsInv);
				itemset("Spectral(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 12, skillsInv);
				itemset("Detonator(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 13, skillsInv);
				itemset("OverHeat(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 14, skillsInv);
				itemset("High Tech(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 16, skillsInv);
				itemset("DragonBreather(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 17, skillsInv);
			}
			else if(Proficiency.getpro(p)<2 && Proficiency.getpro(p)>=1) {
				itemset("Compressor", Material.PISTON, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"✬[Earth]","🖮🖰 Use Compressor When Use Once More", "(Damage Affected By TNTLauncher)","",ChatColor.BOLD+"X "+BigDecimal.valueOf(0.42*(1+fsd.TNTLauncher.getOrDefault(p.getUniqueId(),0)*0.04)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 9, skillsInv);
				itemset("RailScrew", Material.CYAN_DYE, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"✬[Lightning]","🖮🖰 Use RailScrew When Use Once More","",ChatColor.BOLD+"5 X"+demical(0.32*(1+fsd.RailSMG.get(p.getUniqueId())*0.092))+"D"), 10, skillsInv);
				itemset("Impulse", Material.CREEPER_HEAD, 0, 1, Arrays.asList("Stuns Hit Enemies"), 11, skillsInv);
				itemset("Spectral", Material.TINTED_GLASS, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"✬[Lightning]","🖮🖰 Use Spectral When Use Once More", "(Damage Affected By LightningCannon)","",ChatColor.BOLD+"X "+BigDecimal.valueOf(0.5*(1+fsd.LightningCannon.getOrDefault(p.getUniqueId(),0)*0.05)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 12, skillsInv);
				itemset("Detonator", Material.FIREWORK_STAR, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"✬[Lightning]","🖮🖰 Explode Instantly When Use Once More","Increases Explode Damage","Higher Explode Damage By Detonator","",ChatColor.BOLD+"X "+BigDecimal.valueOf(1.1*(1+fsd.HoneyMissile.getOrDefault(p.getUniqueId(),0)*0.0789)*(Proficiency.getpro(p))).setScale(2, RoundingMode.HALF_EVEN)+"D"), 13, skillsInv);
				itemset("OverHeat", Material.REDSTONE_ORE, 0, 1, Arrays.asList("Damage & FireRate Increases When You Hold", "(Max X2)"), 14, skillsInv);
				itemset("High Tech", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("Increases Whole Skills Damage","Add Lightning, Earth Power to All skills"), 16, skillsInv);
				itemset("DragonBreather", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"✬[Earth]","🖮🖰 Sneaking + num4","",ChatColor.BOLD+"30 X 1.05D"), 17, skillsInv);

				itemset("PlazmaGrenade(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 18, skillsInv);
				itemset("RailCannon(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 19, skillsInv);
				itemset("PhotonBarrier(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 20, skillsInv);
				itemset("BeamSlash(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 21, skillsInv);
				itemset("EnergyCycle(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 23, skillsInv);
				itemset("Creation(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 25, skillsInv);
				itemset("SoulDivider(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 26, skillsInv);
			}
			else {
				itemset("Compressor", Material.PISTON, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"✬[Earth]","🖮🖰 Use Compressor When Use Once More", "(Damage Affected By TNTLauncher)","",ChatColor.BOLD+"X "+BigDecimal.valueOf(0.42*(1+fsd.TNTLauncher.getOrDefault(p.getUniqueId(),0)*0.04)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 9, skillsInv);
				itemset("RailScrew", Material.CYAN_DYE, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"✬[Lightning]","🖮🖰 Use RailScrew When Use Once More","",ChatColor.BOLD+"5 X"+demical(0.32*(1+fsd.RailSMG.get(p.getUniqueId())*0.092))+"D"), 10, skillsInv);
				itemset("Impulse", Material.CREEPER_HEAD, 0, 1, Arrays.asList("Stuns Hit Enemies"), 11, skillsInv);
				itemset("Spectral", Material.TINTED_GLASS, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"✬[Lightning]","🖮🖰 Use Spectral When Use Once More", "(Damage Affected By LightningCannon)","",ChatColor.BOLD+"X "+BigDecimal.valueOf(0.5*(1+fsd.LightningCannon.getOrDefault(p.getUniqueId(),0)*0.05)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 12, skillsInv);
				itemset("Detonator", Material.FIREWORK_STAR, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"✬[Lightning]","🖮🖰 Explode Instantly When Use Once More","Increases Explode Damage","Higher Explode Damage By Detonator","",ChatColor.BOLD+"X "+BigDecimal.valueOf(1.1*(1+fsd.HoneyMissile.getOrDefault(p.getUniqueId(),0)*0.0789)*(Proficiency.getpro(p))).setScale(2, RoundingMode.HALF_EVEN)+"D"), 13, skillsInv);
				itemset("OverHeat", Material.REDSTONE_ORE, 0, 1, Arrays.asList("Damage & FireRate Increases When You Hold", "(Max X2)"), 14, skillsInv);
				itemset("High Tech", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("Increases Whole Skills Damage","Add Lightning, Earth Power to All skills"), 16, skillsInv);
				itemset("DragonBreather", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"✬[Earth]","🖮🖰 Sneaking + num4","",ChatColor.BOLD+"30 X 1.05D"), 17, skillsInv);

				itemset("PlazmaGrenade", Material.CYAN_GLAZED_TERRACOTTA, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"✬[Lightning]","🖮🖰 Use PlazmaGrenade When Use Once More", "(Damage Affected By TNTLauncher)","",ChatColor.BOLD+"X "+BigDecimal.valueOf(1.42*(1+fsd.TNTLauncher.getOrDefault(p.getUniqueId(),0)*0.1)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 18, skillsInv);
				itemset("RailCannon", Material.WARPED_ROOTS, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"✬[Lightning]","🖮🖰 Use RailCannon When Use Once More", "(Damage Affected By RailSMG)","",ChatColor.BOLD+"1 X "+BigDecimal.valueOf(1.66*(1+fsd.RailSMG.get(p.getUniqueId())*0.06)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 19, skillsInv);
				itemset("PhotonBarrier", Material.CYAN_STAINED_GLASS, 0, 1, Arrays.asList("Reduce Damage 65% From Foward While Shooting"), 20, skillsInv);
				itemset("BeamWave", Material.SOUL_TORCH, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"✬[Lightning]","🖮🖰 Use BeamSlash When Use Once More", "(Damage Affected By LightningCannon)","",ChatColor.BOLD+"45 X "+BigDecimal.valueOf(0.5*(1+fsd.LightningCannon.getOrDefault(p.getUniqueId(),0)*0.03)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 21, skillsInv);
				itemset("EnergyCycle", Material.CYAN_STAINED_GLASS, 0, 1, Arrays.asList("Other Skills also trigger 5% of Overheat"), 23, skillsInv);
				itemset("Creation", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("Increases Damage & Armor","Decrease DragonBreather Cooldown"), 25, skillsInv);
				itemset("SoulDivider", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"✬[Lightning, Earth]","🖮🖰 Sneaking + num5","",ChatColor.BOLD+"50 X 0.8D"), 26, skillsInv);
			}
			itemset("Current Proficiency", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(""+Proficiency.getproexp(p)), 27, skillsInv);
			itemset("SkillPoints", SKILLPOINT, 0, 1, Arrays.asList(ChatColor.AQUA+"SP."+fsd.SkillPoints.getOrDefault(p.getUniqueId(),0),"","Click if you want to reset your skill's levels"), 35, skillsInv);

		}


		Obtained.itemset(p, skillsInv);
		p.openInventory(skillsInv);
	}


}