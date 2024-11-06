package io.github.chw3021.items;

import java.util.Arrays;
import java.util.HashMap;
import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;

public class Potions {

	public static HashMap<UUID, Integer> elpo = new HashMap<>();
	
	public static ItemStack get(int f, Player p) {

		ItemStack po = new ItemStack(Material.POTION);
		PotionMeta pom = (PotionMeta) po.getItemMeta();
		
		if(f==0) {
			pom.setColor(Color.GRAY);
			pom.setBasePotionType(PotionType.STRONG_HEALING);
			pom.addCustomEffect(new PotionEffect(PotionEffectType.HASTE, 2400, 2, true,true), false);
			pom.addCustomEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 2400, 2, true,true), false);
			pom.addCustomEffect(new PotionEffect(PotionEffectType.SATURATION, 10, 10, true,true), false);
			if(p.getLocale().equalsIgnoreCase("ko_kr")) {
				pom.setDisplayName(ChatColor.GRAY + "광부의 물약");
			}
			else {
				pom.setDisplayName(ChatColor.GRAY + "Potion Of Miner");
			}
		}
		else if(f==1) {
			pom.setColor(Color.WHITE);
			pom.setBasePotionType(PotionType.STRONG_HEALING);
			pom.addCustomEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 2400, 3, true,true), false);
			pom.addCustomEffect(new PotionEffect(PotionEffectType.JUMP_BOOST, 2400, 4, true,true), false);
			pom.addCustomEffect(new PotionEffect(PotionEffectType.SPEED, 2400, 4, true,true), false);
			pom.addCustomEffect(new PotionEffect(PotionEffectType.HEALTH_BOOST, 2400, 3, true,true), false);
			pom.addCustomEffect(new PotionEffect(PotionEffectType.REGENERATION, 200, 3, true,true), false);
			if(p.getLocale().equalsIgnoreCase("ko_kr")) {
				pom.setDisplayName(ChatColor.GREEN + "바람의 영약");
			}
			else {
				pom.setDisplayName(ChatColor.GREEN + "Wind Elixir");
			}
		}
		else if(f==2) {
			pom.setColor(Color.AQUA);
			pom.setBasePotionType(PotionType.STRONG_HEALING);
			pom.addCustomEffect(new PotionEffect(PotionEffectType.CONDUIT_POWER, 2400, 2, true,true), false);
			pom.addCustomEffect(new PotionEffect(PotionEffectType.DOLPHINS_GRACE, 2400, 2, true,true), false);
			pom.addCustomEffect(new PotionEffect(PotionEffectType.WATER_BREATHING, 2400, 2, true,true), false);
			pom.addCustomEffect(new PotionEffect(PotionEffectType.HEALTH_BOOST, 2400, 5, true,true), false);
			pom.addCustomEffect(new PotionEffect(PotionEffectType.REGENERATION, 200, 5, true,true), false);
			if(p.getLocale().equalsIgnoreCase("ko_kr")) {
				pom.setDisplayName(ChatColor.AQUA + "바다의 영약");
				pom.setLore(Arrays.asList("전달체의 힘이 있으면", "가디언의 반사피해를 무시합니다"));
			}
			else {
				pom.setDisplayName(ChatColor.AQUA + "Ocean Elixir");
				pom.setLore(Arrays.asList("Ignore Guardian's Reflective damage", "If you have Conduit Power"));
			}
		}
		else if(f==3) {
			pom.setColor(Color.NAVY);
			pom.setBasePotionType(PotionType.STRONG_HEALING);
			pom.addCustomEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 2400, 2, true,true), false);
			pom.addCustomEffect(new PotionEffect(PotionEffectType.RESISTANCE, 2400, 2, true,true), false);
			pom.addCustomEffect(new PotionEffect(PotionEffectType.HEALTH_BOOST, 2400, 6, true,true), false);
			pom.addCustomEffect(new PotionEffect(PotionEffectType.REGENERATION, 200, 6, true,true), false);
			if(p.getLocale().equalsIgnoreCase("ko_kr")) {
				pom.setDisplayName(ChatColor.DARK_BLUE + "어둠의 영약");
			}
			else {
				pom.setDisplayName(ChatColor.DARK_BLUE + "Dark Elixir");
			}
		}
		else if(f==4) {
			pom.setColor(Color.RED);
			pom.setBasePotionType(PotionType.STRONG_HEALING);
			pom.addCustomEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 2400, 6, true,true), false);
			pom.addCustomEffect(new PotionEffect(PotionEffectType.STRENGTH, 2400, 2, true,true), false);
			pom.addCustomEffect(new PotionEffect(PotionEffectType.HEALTH_BOOST, 2400, 5, true,true), false);
			pom.addCustomEffect(new PotionEffect(PotionEffectType.REGENERATION, 200, 5, true,true), false);
			if(p.getLocale().equalsIgnoreCase("ko_kr")) {
				pom.setDisplayName(ChatColor.RED + "타오르는 영약");
			}
			else {
				pom.setDisplayName(ChatColor.RED + "Burning Elixir");
			}
		}
		else if(f==5) {
			pom.setColor(Color.GREEN);
			pom.setBasePotionType(PotionType.STRONG_HEALING);
			pom.addCustomEffect(new PotionEffect(PotionEffectType.LUCK, 2400, 10, true,true), false);
			pom.addCustomEffect(new PotionEffect(PotionEffectType.HEALTH_BOOST, 2400, 5, true,true), false);
			pom.addCustomEffect(new PotionEffect(PotionEffectType.REGENERATION, 200, 5, true,true), false);
			if(p.getLocale().equalsIgnoreCase("ko_kr")) {
				pom.setDisplayName(ChatColor.GREEN + "행운의 영약");
			}
			else {
				pom.setDisplayName(ChatColor.GREEN + "Lucky Elixir");
			}
		}
		else if(f==6) {
			pom.setColor(Color.YELLOW);
			pom.setBasePotionType(PotionType.STRONG_HEALING);
			pom.addCustomEffect(new PotionEffect(PotionEffectType.REGENERATION, 2400, 3, true,true), false);
			pom.addCustomEffect(new PotionEffect(PotionEffectType.ABSORPTION, 2400, 10, true,true), false);
			pom.addCustomEffect(new PotionEffect(PotionEffectType.HEALTH_BOOST, 2400, 5, true,true), false);
			pom.addCustomEffect(new PotionEffect(PotionEffectType.REGENERATION, 200, 5, true,true), false);
			if(p.getLocale().equalsIgnoreCase("ko_kr")) {
				pom.setDisplayName(ChatColor.GREEN + "야생의 영약");
			}
			else {
				pom.setDisplayName(ChatColor.GREEN + "Wild Elixir");
			}
		}
		else if(f==7) {
			pom.setColor(Color.PURPLE);
			pom.setBasePotionType(PotionType.STRONG_HEALING);
			pom.addCustomEffect(new PotionEffect(PotionEffectType.REGENERATION, 24000, 4, true,true), false);
			pom.addCustomEffect(new PotionEffect(PotionEffectType.ABSORPTION, 24000, 20, true,true), false);
			pom.addCustomEffect(new PotionEffect(PotionEffectType.HEALTH_BOOST, 24000, 5, true,true), false);
			pom.addCustomEffect(new PotionEffect(PotionEffectType.STRENGTH, 24000, 2, true,true), false);
			pom.addCustomEffect(new PotionEffect(PotionEffectType.HASTE, 24000, 2, true,true), false);
			pom.addCustomEffect(new PotionEffect(PotionEffectType.SPEED, 24000, 2, true,true), false);
			pom.addCustomEffect(new PotionEffect(PotionEffectType.JUMP_BOOST, 24000, 2, true,true), false);
			pom.addCustomEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 24000, 2, true,true), false);
			pom.addCustomEffect(new PotionEffect(PotionEffectType.LUCK, 24000, 2, true,true), false);
			pom.addCustomEffect(new PotionEffect(PotionEffectType.RESISTANCE, 24000, 1, true,true), false);
			pom.addCustomEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 24000, 1, true,true), false);
			pom.addCustomEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 24000, 2, true,true), false);
			pom.addCustomEffect(new PotionEffect(PotionEffectType.SATURATION, 24000, 0, true,true), false);
			pom.addCustomEffect(new PotionEffect(PotionEffectType.LEVITATION, 40, 1, true,true), false);
			if(p.getLocale().equalsIgnoreCase("ko_kr")) {
				pom.setDisplayName(ChatColor.GREEN + "차원 여행자의 물약");
			}
			else {
				pom.setDisplayName(ChatColor.GREEN + "Potion of Dimension Travler");
			}
		}
		po.setItemMeta(pom);
		
		return po;
	}

	
}