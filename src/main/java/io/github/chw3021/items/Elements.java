package io.github.chw3021.items;

import java.util.Arrays;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.CraftingInventory;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;

import io.github.chw3021.rmain.RMain;
import net.md_5.bungee.api.ChatColor;

public class Elements implements Listener {


	public static ItemStack give(Material m, Integer count, Player p) {

		final ItemStack is = new ItemStack(m);
		is.setAmount(count);
    	p.getInventory().addItem(is);
    	//final String in = m.name();
		//String gived = p.getLocale().equalsIgnoreCase("ko_kr") ? in + " " + count + "개를 획득했습니다":"You've Got " + count +" "+in;
		//p.sendMessage(ChatColor.GOLD + gived);
		
    	if(p.getInventory().firstEmpty() == -1 && is != null && is.getAmount() >0) {
    		p.getWorld().dropItem(p.getLocation(), is);
    		String reg = p.getLocale().equalsIgnoreCase("ko_kr") ? "인벤토리가 꽉찼습니다":"Your Inventory Is Fulled!";
    		p.sendMessage(ChatColor.RED + reg);
    	}
    	return is;
	}
	
	public static ItemStack give(ItemStack is, Integer count, Player p) {

		is.setAmount(count);
    	p.getInventory().addItem(is);
    	final String in = is.getItemMeta().getDisplayName();
		String gived = p.getLocale().equalsIgnoreCase("ko_kr") ? in + " " + count + "개를 획득했습니다":"You've Got " + count +" "+in;
		p.sendMessage(ChatColor.GOLD + gived);
    	if(p.getInventory().firstEmpty() == -1 && is != null && is.getAmount() >0) {
    		p.getWorld().dropItem(p.getLocation(), is);
    		String reg = p.getLocale().equalsIgnoreCase("ko_kr") ? "인벤토리가 꽉찼습니다":"Your Inventory Is Fulled!";
    		p.sendMessage(ChatColor.RED + reg);
    	}
    	return is;
	}

	
	
	public static ItemStack getpor(int i, Player p) {
		ItemStack ii = new ItemStack(Material.RED_STAINED_GLASS);
		ItemMeta imeta = ii.getItemMeta();
		if(p.getLocale().equalsIgnoreCase("ko_kr")) {
			imeta.setLore(Arrays.asList(ChatColor.BOLD +"한번 설치하면 회수할수 없습니다","우클릭시 침공작전이 시작됩니다", "작전 시작시 해당 블럭은 사라집니다"));
			if(i == 4) {
				ii.setType(Material.GREEN_STAINED_GLASS);
				imeta.setDisplayName(ChatColor.BLUE +"엘더크리퍼 침공작전 순간이동기");
				imeta.setLocalizedName(ChatColor.BLUE +"ElderCreeper Raid Teleporter");
			}
			else if(i == 5) {
				ii.setType(Material.LIGHT_GRAY_STAINED_GLASS);
				imeta.setDisplayName(ChatColor.BLUE +"스톤골렘 침공작전 순간이동기");
				imeta.setLocalizedName(ChatColor.BLUE +"StoneGolem Raid Teleporter");
			}
			else if(i == 6) {
				ii.setType(Material.WHITE_STAINED_GLASS);
				imeta.setDisplayName(ChatColor.BLUE +"설산마녀 침공작전 순간이동기");
				imeta.setLocalizedName(ChatColor.BLUE +"SnowWitch Raid Teleporter");
			}
			else if(i == 7) {
				ii.setType(Material.BLUE_STAINED_GLASS);
				imeta.setDisplayName(ChatColor.BLUE +"엘더가디언 침공작전 순간이동기");
				imeta.setLocalizedName(ChatColor.BLUE +"ElderGuardian Raid Teleporter");
			}
			else if(i == 8) {
				ii.setType(Material.BROWN_STAINED_GLASS);
				imeta.setDisplayName(ChatColor.BLUE +"흑마법사 침공작전 순간이동기");
				imeta.setLocalizedName(ChatColor.BLUE +"DarkMage Raid Teleporter");
			}
			else if(i == 9) {
				ii.setType(Material.MAGENTA_STAINED_GLASS);
				imeta.setDisplayName(ChatColor.BLUE +"닥터B 침공작전 순간이동기");
				imeta.setLocalizedName(ChatColor.BLUE +"DrB Raid Teleporter");
			}
			else if(i == 10) {
				ii.setType(Material.RED_STAINED_GLASS);
				imeta.setDisplayName(ChatColor.BLUE +"붉은기사 침공작전 순간이동기");
				imeta.setLocalizedName(ChatColor.BLUE +"RedKnight Raid Teleporter");
			}
			else if(i == 12) {
				ii.setType(Material.YELLOW_STAINED_GLASS);
				imeta.setDisplayName(ChatColor.BLUE +"잊혀진 사제 침공작전 순간이동기");
				imeta.setLocalizedName(ChatColor.BLUE +"LostTemplar Raid Teleporter");//wild
			}
			else if(i == -2) {
				ii.setType(Material.ORANGE_STAINED_GLASS);
				imeta.setDisplayName(ChatColor.BLUE +"수확자 침공작전 순간이동기");
				imeta.setLocalizedName(ChatColor.BLUE +"Harvester Raid Teleporter");//soul
			}
			else if(i == -3) {
				ii.setType(Material.MAGENTA_STAINED_GLASS);
				imeta.setDisplayName(ChatColor.BLUE +"학살자 침공작전 순간이동기");
				imeta.setLocalizedName(ChatColor.BLUE +"Slaughter Raid Teleporter");//crimson
			}
			else if(i == -4) {
				ii.setType(Material.CYAN_STAINED_GLASS);
				imeta.setDisplayName(ChatColor.BLUE +"거대팬텀 침공작전 순간이동기");
				imeta.setLocalizedName(ChatColor.BLUE +"GiantPhantom Raid Teleporter");//warp
			}
			else if(i == -5) {
				ii.setType(Material.PINK_STAINED_GLASS);
				imeta.setDisplayName(ChatColor.BLUE +"피글린요리사 침공작전 순간이동기");
				imeta.setLocalizedName(ChatColor.BLUE +"PiglinCook Raid Teleporter");//volcanic
			}
			else if(i == -6) {
				ii.setType(Material.GRAY_STAINED_GLASS);
				imeta.setDisplayName(ChatColor.BLUE +"대량엔더맨 침공작전 순간이동기");
				imeta.setLocalizedName(ChatColor.BLUE +"MassEndermans Raid Teleporter");//ender
			}
			else if(i == -7) {
				ii.setType(Material.PURPLE_STAINED_GLASS);
				imeta.setDisplayName(ChatColor.BLUE +"차원 훼방꾼 침공작전 순간이동기");
				imeta.setLocalizedName(ChatColor.BLUE +"Disrupter Raid Teleporter");//void
			}
			else if(i == -8) {
				ii.setType(Material.BLACK_STAINED_GLASS);
				imeta.setDisplayName(ChatColor.BLUE +"위더 침공작전 순간이동기");
				imeta.setLocalizedName(ChatColor.BLUE +"Wither Raid Teleporter");
			}
			ii.setItemMeta(imeta);
			
	    }
		else {
			imeta.setLore(Arrays.asList(ChatColor.BOLD +"You Can't Retreive Block Since You Place","Using For Enter To Raid By RightClick", "Block will Disappear When You Enter Raid"));
			if(i == 4) {
				ii.setType(Material.GREEN_STAINED_GLASS);
				imeta.setDisplayName(ChatColor.BLUE +"ElderCreeper Raid Teleporter");
				imeta.setLocalizedName(ChatColor.BLUE +"ElderCreeper Raid Teleporter");
			}
			else if(i == 5) {
				ii.setType(Material.LIGHT_GRAY_STAINED_GLASS);
				imeta.setDisplayName(ChatColor.BLUE +"StoneGolem Raid Teleporter");
				imeta.setLocalizedName(ChatColor.BLUE +"StoneGolem Raid Teleporter");
			}
			else if(i == 6) {
				ii.setType(Material.WHITE_STAINED_GLASS);
				imeta.setDisplayName(ChatColor.BLUE +"SnowWitch Raid Teleporter");
				imeta.setLocalizedName(ChatColor.BLUE +"SnowWitch Raid Teleporter");
			}
			else if(i == 7) {
				ii.setType(Material.BLUE_STAINED_GLASS);
				imeta.setDisplayName(ChatColor.BLUE +"ElderGuardian Raid Teleporter");
				imeta.setLocalizedName(ChatColor.BLUE +"ElderGuardian Raid Teleporter");
			}
			else if(i == 8) {
				ii.setType(Material.BROWN_STAINED_GLASS);
				imeta.setDisplayName(ChatColor.BLUE +"DarkMage Raid Teleporter");
				imeta.setLocalizedName(ChatColor.BLUE +"DarkMage Raid Teleporter");
			}
			else if(i == 9) {
				ii.setType(Material.MAGENTA_STAINED_GLASS);
				imeta.setDisplayName(ChatColor.BLUE +"Dr.B Raid Teleporter");
				imeta.setLocalizedName(ChatColor.BLUE +"DrB Raid Teleporter");
			}
			else if(i == 10) {
				ii.setType(Material.RED_STAINED_GLASS);
				imeta.setDisplayName(ChatColor.BLUE +"RedKnight Raid Teleporter");
				imeta.setLocalizedName(ChatColor.BLUE +"RedKnight Raid Teleporter");
			}
			else if(i == 12) {
				ii.setType(Material.YELLOW_STAINED_GLASS);
				imeta.setDisplayName(ChatColor.BLUE +"LostTemplar Raid Teleporter");
				imeta.setLocalizedName(ChatColor.BLUE +"LostTemplar Raid Teleporter");//wild
			}
			else if(i == -2) {
				ii.setType(Material.ORANGE_STAINED_GLASS);
				imeta.setDisplayName(ChatColor.BLUE +"Harvester Raid Teleporter");
				imeta.setLocalizedName(ChatColor.BLUE +"Harvester Raid Teleporter");//soul
			}
			else if(i == -3) {
				ii.setType(Material.MAGENTA_STAINED_GLASS);
				imeta.setDisplayName(ChatColor.BLUE +"Slaughter Raid Teleporter");
				imeta.setLocalizedName(ChatColor.BLUE +"Slaughter Raid Teleporter");//crimson
			}
			else if(i == -4) {
				ii.setType(Material.CYAN_STAINED_GLASS);
				imeta.setDisplayName(ChatColor.BLUE +"GiantPhantom Raid Teleporter");
				imeta.setLocalizedName(ChatColor.BLUE +"GiantPhantom Raid Teleporter");//warp
			}
			else if(i == -5) {
				ii.setType(Material.PINK_STAINED_GLASS);
				imeta.setDisplayName(ChatColor.BLUE +"PiglinCook Raid Teleporter");
				imeta.setLocalizedName(ChatColor.BLUE +"PiglinCook Raid Teleporter");//volcanic
			}
			else if(i == -6) {
				ii.setType(Material.GRAY_STAINED_GLASS);
				imeta.setDisplayName(ChatColor.BLUE +"MassEndermans Raid Teleporter");
				imeta.setLocalizedName(ChatColor.BLUE +"MassEndermans Raid Teleporter");//ender
			}
			else if(i == -7) {
				ii.setType(Material.PURPLE_STAINED_GLASS);
				imeta.setDisplayName(ChatColor.BLUE +"Disrupter Raid Teleporter");
				imeta.setLocalizedName(ChatColor.BLUE +"Disrupter Raid Teleporter");//void
			}
			else if(i == -8) {
				ii.setType(Material.BLACK_STAINED_GLASS);
				imeta.setDisplayName(ChatColor.BLUE +"Wither Raid Teleporter");
				imeta.setLocalizedName(ChatColor.BLUE +"Wither Raid Teleporter");
			}
			ii.setItemMeta(imeta);
			
		}
		return ii;
	}
	

	public static ItemStack getel(int i, Player p) {
		ItemStack ii = new ItemStack(Material.GRASS);
		ItemMeta imeta = ii.getItemMeta();
		imeta.setCustomModelData(100*(i>0?1:-1) + i);
		if(p.getLocale().equalsIgnoreCase("ko_kr")) {
			imeta.setLore(Arrays.asList("64개를 조합시 무기를 강화할수 있는 핵을 획득합니다"));
			if(i == 5) {
				List<String> lore = imeta.getLore();
				lore.addAll(Arrays.asList("",ChatColor.AQUA + "[지상 원소핵 강화 순서, 획득 가능한 군계]",ChatColor.AQUA + "네더라이트-대지(평원)-바람(산)-서리(얼음)-바다",ChatColor.AQUA + "어둠(숲)-개조(사막)-작열(악지)-맹독(늪)-야생(정글)" ));
				lore.addAll(Arrays.asList("",ChatColor.LIGHT_PURPLE + "바람의 핵으로 [원소기운의 무기-1단계]를 강화시", ChatColor.LIGHT_PURPLE + "[원소기운의 무기-2단계]를 획득합니다"));
				imeta.setLore(lore);
				ii.setType(Material.DIORITE);
				imeta.setDisplayName(ChatColor.BLUE +"바람의 파편");
				imeta.setLocalizedName(ChatColor.BLUE +"바람의 파편");
			}
			else if(i == 14) {
				ii.setType(Material.WHITE_TULIP);
				List<String> lore = imeta.getLore();
				lore.addAll(Arrays.asList("",ChatColor.AQUA + "[지상 원소핵 강화 순서, 획득 가능한 군계]",ChatColor.AQUA + "네더라이트-대지(평원)-바람(산)-서리(얼음)-바다",ChatColor.AQUA + "어둠(숲)-개조(사막)-작열(악지)-맹독(늪)-야생(정글)" ));
				lore.addAll(Arrays.asList("",ChatColor.LIGHT_PURPLE + "바람의 핵으로 [네더라이트 무기]를 강화시", ChatColor.LIGHT_PURPLE + "[원소기운의 무기-1단계]를 획득합니다"));
				imeta.setLore(lore);
				imeta.setDisplayName(ChatColor.BLUE +"대지의 파편");
				imeta.setLocalizedName(ChatColor.BLUE +"대지의 파편");
			}
			else if(i == 6) {
				ii.setType(Material.BLUE_ICE);
				List<String> lore = imeta.getLore();
				lore.addAll(Arrays.asList("",ChatColor.AQUA + "[지상 원소핵 강화 순서, 획득 가능한 군계]",ChatColor.AQUA + "네더라이트-대지(평원)-바람(산)-서리(얼음)-바다",ChatColor.AQUA + "어둠(숲)-개조(사막)-작열(악지)-맹독(늪)-야생(정글)" ));
				lore.addAll(Arrays.asList("",ChatColor.LIGHT_PURPLE + "서리의 핵으로 [원소기운의 무기-2단계]를 강화시", ChatColor.LIGHT_PURPLE + "[원소기운의 무기-3단계]를 획득합니다"));
				imeta.setLore(lore);
				imeta.setDisplayName(ChatColor.BLUE +"서리의 파편");
				imeta.setLocalizedName(ChatColor.BLUE +"서리의 파편");
			}
			else if(i == 7) {
				ii.setType(Material.PRISMARINE_CRYSTALS);
				List<String> lore = imeta.getLore();
				lore.addAll(Arrays.asList("",ChatColor.AQUA + "[지상 원소핵 강화 순서, 획득 가능한 군계]",ChatColor.AQUA + "네더라이트-대지(평원)-바람(산)-서리(얼음)-바다",ChatColor.AQUA + "어둠(숲)-개조(사막)-작열(악지)-맹독(늪)-야생(정글)" ));
				lore.addAll(Arrays.asList("",ChatColor.LIGHT_PURPLE + "바다의 핵으로 [원소기운의 무기-3단계]를 강화시", ChatColor.LIGHT_PURPLE + "[원소기운의 무기-4단계]를 획득합니다"));
				imeta.setLore(lore);
				imeta.setDisplayName(ChatColor.BLUE +"바다의 파편");
				imeta.setLocalizedName(ChatColor.BLUE +"바다의 파편");
			}
			else if(i == 8) {
				ii.setType(Material.STRIPPED_DARK_OAK_WOOD);
				List<String> lore = imeta.getLore();
				lore.addAll(Arrays.asList("",ChatColor.AQUA + "[지상 원소핵 강화 순서, 획득 가능한 군계]",ChatColor.AQUA + "네더라이트-대지(평원)-바람(산)-서리(얼음)-바다",ChatColor.AQUA + "어둠(숲)-개조(사막)-작열(악지)-맹독(늪)-야생(정글)" ));
				lore.addAll(Arrays.asList("",ChatColor.LIGHT_PURPLE + "어둠의 핵으로 [원소기운의 무기-4단계]를 강화시", ChatColor.LIGHT_PURPLE + "[원소기운의 무기-5단계]를 획득합니다"));
				imeta.setLore(lore);
				imeta.setDisplayName(ChatColor.BLUE +"어둠의 파편");
				imeta.setLocalizedName(ChatColor.BLUE +"어둠의 파편");
			}
			else if(i == 9) {
				ii.setType(Material.FIREWORK_STAR);
				List<String> lore = imeta.getLore();
				lore.addAll(Arrays.asList("",ChatColor.AQUA + "[지상 원소핵 강화 순서, 획득 가능한 군계]",ChatColor.AQUA + "네더라이트-대지(평원)-바람(산)-서리(얼음)-바다",ChatColor.AQUA + "어둠(숲)-개조(사막)-작열(악지)-맹독(늪)-야생(정글)" ));
				lore.addAll(Arrays.asList("",ChatColor.LIGHT_PURPLE + "하이퍼 엔진으로 [원소기운의 무기-5단계]를 강화시", ChatColor.LIGHT_PURPLE + "[원소기운의 무기-6단계]를 획득합니다"));
				imeta.setLore(lore);
				imeta.setDisplayName(ChatColor.BLUE +"실험체 부품");
				imeta.setLocalizedName(ChatColor.BLUE +"실험체 부품");
			}
			else if(i == 10) {
				ii.setType(Material.RED_SAND);
				List<String> lore = imeta.getLore();
				lore.addAll(Arrays.asList("",ChatColor.AQUA + "[지상 원소핵 강화 순서, 획득 가능한 군계]",ChatColor.AQUA + "네더라이트-대지(평원)-바람(산)-서리(얼음)-바다",ChatColor.AQUA + "어둠(숲)-개조(사막)-작열(악지)-맹독(늪)-야생(정글)" ));
				lore.addAll(Arrays.asList("",ChatColor.LIGHT_PURPLE + "타오르는 심장으로 [원소기운의 무기-6단계]를 강화시", ChatColor.LIGHT_PURPLE + "[원소기운의 무기-7단계]를 획득합니다"));
				imeta.setLore(lore);
				imeta.setDisplayName(ChatColor.BLUE +"적색 파편");
				imeta.setLocalizedName(ChatColor.BLUE +"적색 파편");
			}
			else if(i == 11) {
				ii.setType(Material.GREEN_CONCRETE_POWDER);
				List<String> lore = imeta.getLore();
				lore.addAll(Arrays.asList("",ChatColor.AQUA + "[지상 원소핵 강화 순서, 획득 가능한 군계]",ChatColor.AQUA + "네더라이트-대지(평원)-바람(산)-서리(얼음)-바다",ChatColor.AQUA + "어둠(숲)-개조(사막)-작열(악지)-맹독(늪)-야생(정글)" ));
				lore.addAll(Arrays.asList("",ChatColor.LIGHT_PURPLE + "맹독의 핵으로 [원소기운의 무기-7단계]를 강화시", ChatColor.LIGHT_PURPLE + "[원소기운의 무기-8단계]를 획득합니다"));
				imeta.setLore(lore);
				imeta.setDisplayName(ChatColor.BLUE +"맹독의 파편");
				imeta.setLocalizedName(ChatColor.BLUE +"맹독의 파편");
			}
			else if(i == 12) {
				ii.setType(Material.JUNGLE_SAPLING);
				List<String> lore = imeta.getLore();
				lore.addAll(Arrays.asList("",ChatColor.AQUA + "[지상 원소핵 강화 순서, 획득 가능한 군계]",ChatColor.AQUA + "네더라이트-대지(평원)-바람(산)-서리(얼음)-바다",ChatColor.AQUA + "어둠(숲)-개조(사막)-작열(악지)-맹독(늪)-야생(정글)" ));
				lore.addAll(Arrays.asList("",ChatColor.LIGHT_PURPLE + "야생의 핵으로 [원소기운의 무기-8단계]를 강화시", ChatColor.LIGHT_PURPLE + "[자연의 무기]를 획득합니다"));
				lore.addAll(Arrays.asList("",ChatColor.GOLD + "자연의 무기를 [강력한 원소의 기운]으로 강화시", ChatColor.GOLD + "해당 속성의 무기를 제작할수 있습니다", ChatColor.GOLD + "[강력한 원소의 기운]은 보스 침공에서 획득가능"));
				imeta.setDisplayName(ChatColor.BLUE +"야생의 파편");
				imeta.setLocalizedName(ChatColor.BLUE +"야생의 파편");
			}
			else if(i == -2) {
				ii.setType(Material.GHAST_TEAR);
				imeta.setLore(Arrays.asList("영혼의 핵을 제작하는데 사용됩니다", "영혼의 핵은 무기를 강화할때 사용됩니다"
						,"",ChatColor.LIGHT_PURPLE +"네더지역의 4가지 핵을 조합하여 얻는", ChatColor.LIGHT_PURPLE +"융합된 네더의 핵을 통해",  ChatColor.LIGHT_PURPLE +"헬멧,갑옷 강화가 가능합니다"));
				imeta.setDisplayName(ChatColor.BLUE +"영혼의 파편");
				imeta.setLocalizedName(ChatColor.BLUE +"영혼의 파편");
			}
			else if(i == -3) {
				ii.setType(Material.CRIMSON_FUNGUS);
				imeta.setLore(Arrays.asList("진홍빛 핵을 제작하는데 사용됩니다", "진홍빛 핵은 무기를 강화할때 사용됩니다"
						,"",ChatColor.LIGHT_PURPLE +"네더지역의 4가지 핵을 조합하여 얻는", ChatColor.LIGHT_PURPLE +"융합된 네더의 핵을 통해",  ChatColor.LIGHT_PURPLE +"헬멧,갑옷 강화가 가능합니다"));
				imeta.setDisplayName(ChatColor.BLUE +"진홍빛 파편");
				imeta.setLocalizedName(ChatColor.BLUE +"진홍빛 파편");
			}
			else if(i == -4) {
				ii.setType(Material.WARPED_FUNGUS);
				imeta.setLore(Arrays.asList("뒤틀린 핵을 제작하는데 사용됩니다", "뒤틀린 핵은 무기를 강화할때 사용됩니다"
						,"",ChatColor.LIGHT_PURPLE +"네더지역의 4가지 핵을 조합하여 얻는", ChatColor.LIGHT_PURPLE +"융합된 네더의 핵을 통해",  ChatColor.LIGHT_PURPLE +"헬멧,갑옷 강화가 가능합니다"));
				imeta.setDisplayName(ChatColor.BLUE +"뒤틀린 파편");
				imeta.setLocalizedName(ChatColor.BLUE +"뒤틀린 파편");
			}
			else if(i == -5) {
				ii.setType(Material.BASALT);
				imeta.setLore(Arrays.asList("화산의 핵을 제작하는데 사용됩니다", "화산의 핵은 무기를 강화할때 사용됩니다"
						,"",ChatColor.LIGHT_PURPLE +"네더지역의 4가지 핵을 조합하여 얻는", ChatColor.LIGHT_PURPLE +"융합된 네더의 핵을 통해",  ChatColor.LIGHT_PURPLE +"헬멧,갑옷 강화가 가능합니다"));
				imeta.setDisplayName(ChatColor.BLUE +"화산의 파편");
				imeta.setLocalizedName(ChatColor.BLUE +"화산의 파편");
			}
			else if(i == -6) {
				ii.setType(Material.END_STONE);
				imeta.setLore(Arrays.asList("엔더 핵을 제작하는데 사용됩니다", "엔더 핵은 무기를 강화할때 사용됩니다","",ChatColor.LIGHT_PURPLE +"엔더지역의 2가지 핵을 조합하여 얻는", ChatColor.LIGHT_PURPLE +"융합된 엔더의 핵을 통해",  ChatColor.LIGHT_PURPLE +"레깅스,부츠 강화가 가능합니다"));
				imeta.setDisplayName(ChatColor.BLUE +"엔더 파편");
				imeta.setLocalizedName(ChatColor.BLUE +"엔더 파편");
			}
			else if(i == -7) {
				ii.setType(Material.STRUCTURE_VOID);
				imeta.setLore(Arrays.asList("공허의 핵을 제작하는데 사용됩니다", "공허의 핵은 무기를 강화할때 사용됩니다","",ChatColor.LIGHT_PURPLE +"엔더지역의 2가지 핵을 조합하여 얻는", ChatColor.LIGHT_PURPLE +"융합된 엔더의 핵을 통해",  ChatColor.LIGHT_PURPLE +"레깅스,부츠 강화가 가능합니다"));
				imeta.setDisplayName(ChatColor.BLUE +"공허의 파편");
				imeta.setLocalizedName(ChatColor.BLUE +"공허의 파편");
			}
			else if(i == -8) {
				ii.setType(Material.NETHER_STAR);
				imeta.setLore(Arrays.asList("영웅의 상징을 제작하는데 사용됩니다", "영웅의 상징은 무기를 강화할때 사용됩니다"));
				imeta.setDisplayName(ChatColor.BLUE +"서사의 파편");
				imeta.setLocalizedName(ChatColor.BLUE +"서사의 파편");
			}
			ii.setItemMeta(imeta);
			
		}
		else {
			imeta.setLore(Arrays.asList("Used For Crafting Core(64Fragments = 1Core)", "Core is Used For Smithing Weapon"));
			if(i == 5) {
				ii.setType(Material.DIORITE);
				List<String> lore = imeta.getLore();
				lore.addAll(Arrays.asList("",ChatColor.AQUA + "[Order of Smithing & Obtainable Biome]",ChatColor.AQUA + "Netherite-Earth(Plain)-Wind(Mountain,Hill)",ChatColor.AQUA + "Frost(Frozen)-Ocean-Dark(Forest)-Hyper(Desert)",ChatColor.AQUA + "Burning(Badlands)-Poison(Swamp)-Wild(Jungle)"));
				lore.addAll(Arrays.asList("",ChatColor.LIGHT_PURPLE + "If You Smith [Aura Weapon - Lv.1] with [Core Of Wind]", ChatColor.LIGHT_PURPLE + "You'll get [Aura Weapon - Lv.2]"));
				imeta.setLore(lore);
				imeta.setDisplayName(ChatColor.BLUE +"Fragments of Wind");
				imeta.setLocalizedName(ChatColor.BLUE +"Fragments of Wind");
			}
			else if(i == 14) {
				ii.setType(Material.WHITE_TULIP);
				List<String> lore = imeta.getLore();
				lore.addAll(Arrays.asList("",ChatColor.AQUA + "[Order of Smithing & Obtainable Biome]",ChatColor.AQUA + "Netherite-Earth(Plain)-Wind(Mountain,Hill)",ChatColor.AQUA + "Frost(Frozen)-Ocean-Dark(Forest)-Hyper(Desert)",ChatColor.AQUA + "Burning(Badlands)-Poison(Swamp)-Wild(Jungle)"));
				lore.addAll(Arrays.asList("",ChatColor.LIGHT_PURPLE + "If You Smith Netherite Weapon with [Core Of Earth]", ChatColor.LIGHT_PURPLE + "You'll get [Aura Weapon - Lv.1]"));
				imeta.setLore(lore);
				imeta.setDisplayName(ChatColor.BLUE +"Fragments of Earth");
				imeta.setLocalizedName(ChatColor.BLUE +"Fragments of Earth");
			}
			else if(i == 6) {
				ii.setType(Material.BLUE_ICE);
				List<String> lore = imeta.getLore();
				lore.addAll(Arrays.asList("",ChatColor.AQUA + "[Order of Smithing & Obtainable Biome]",ChatColor.AQUA + "Netherite-Earth(Plain)-Wind(Mountain,Hill)",ChatColor.AQUA + "Frost(Frozen)-Ocean-Dark(Forest)-Hyper(Desert)",ChatColor.AQUA + "Burning(Badlands)-Poison(Swamp)-Wild(Jungle)"));
				lore.addAll(Arrays.asList("",ChatColor.LIGHT_PURPLE + "If You Smith [Aura Weapon - Lv.2] with [Core Of Frost]", ChatColor.LIGHT_PURPLE + "You'll get [Aura Weapon - Lv.3]"));
				imeta.setLore(lore);
				imeta.setDisplayName(ChatColor.BLUE +"Fragments of Frost");
				imeta.setLocalizedName(ChatColor.BLUE +"Fragments of Frost");
			}
			else if(i == 7) {
				ii.setType(Material.PRISMARINE_CRYSTALS);
				List<String> lore = imeta.getLore();
				lore.addAll(Arrays.asList("",ChatColor.AQUA + "[Order of Smithing & Obtainable Biome]",ChatColor.AQUA + "Netherite-Earth(Plain)-Wind(Mountain,Hill)",ChatColor.AQUA + "Frost(Frozen)-Ocean-Dark(Forest)-Hyper(Desert)",ChatColor.AQUA + "Burning(Badlands)-Poison(Swamp)-Wild(Jungle)"));
				lore.addAll(Arrays.asList("",ChatColor.LIGHT_PURPLE + "If You Smith [Aura Weapon - Lv.3] with [Core Of Ocean]", ChatColor.LIGHT_PURPLE + "You'll get [Aura Weapon - Lv.4]"));
				imeta.setLore(lore);
				imeta.setDisplayName(ChatColor.BLUE +"Fragments of Ocean");
				imeta.setLocalizedName(ChatColor.BLUE +"Fragments of Ocean");
			}
			else if(i == 8) {
				ii.setType(Material.STRIPPED_DARK_OAK_WOOD);
				List<String> lore = imeta.getLore();
				lore.addAll(Arrays.asList("",ChatColor.AQUA + "[Order of Smithing & Obtainable Biome]",ChatColor.AQUA + "Netherite-Earth(Plain)-Wind(Mountain,Hill)",ChatColor.AQUA + "Frost(Frozen)-Ocean-Dark(Forest)-Hyper(Desert)",ChatColor.AQUA + "Burning(Badlands)-Poison(Swamp)-Wild(Jungle)"));
				lore.addAll(Arrays.asList("",ChatColor.LIGHT_PURPLE + "If You Smith [Aura Weapon - Lv.4] with [Core Of Darkness]", ChatColor.LIGHT_PURPLE + "You'll get [Aura Weapon - Lv.5]"));
				imeta.setLore(lore);
				imeta.setDisplayName(ChatColor.BLUE +"Fragments of Darkness");
				imeta.setLocalizedName(ChatColor.BLUE +"Fragments of Darkness");
			}
			else if(i == 9) {
				ii.setType(Material.FIREWORK_STAR);
				List<String> lore = imeta.getLore();
				lore.addAll(Arrays.asList("",ChatColor.AQUA + "[Order of Smithing & Obtainable Biome]",ChatColor.AQUA + "Netherite-Earth(Plain)-Wind(Mountain,Hill)",ChatColor.AQUA + "Frost(Frozen)-Ocean-Dark(Forest)-Hyper(Desert)",ChatColor.AQUA + "Burning(Badlands)-Poison(Swamp)-Wild(Jungle)"));
				lore.addAll(Arrays.asList("",ChatColor.LIGHT_PURPLE + "If You Smith [Aura Weapon - Lv.5] with [Hyper Engine]", ChatColor.LIGHT_PURPLE + "You'll get [Aura Weapon - Lv.6]"));
				imeta.setLore(lore);
				imeta.setDisplayName(ChatColor.BLUE +"Part of Specimen");
				imeta.setLocalizedName(ChatColor.BLUE +"Part of Specimen");
			}
			else if(i == 10) {
				ii.setType(Material.GUNPOWDER);
				List<String> lore = imeta.getLore();
				lore.addAll(Arrays.asList("",ChatColor.AQUA + "[Order of Smithing & Obtainable Biome]",ChatColor.AQUA + "Netherite-Earth(Plain)-Wind(Mountain,Hill)",ChatColor.AQUA + "Frost(Frozen)-Ocean-Dark(Forest)-Hyper(Desert)",ChatColor.AQUA + "Burning(Badlands)-Poison(Swamp)-Wild(Jungle)"));
				lore.addAll(Arrays.asList("",ChatColor.LIGHT_PURPLE + "If You Smith [Aura Weapon - Lv.6] with [Burning Heart]", ChatColor.LIGHT_PURPLE + "You'll get [Aura Weapon - Lv.7]"));
				imeta.setLore(lore);
				imeta.setDisplayName(ChatColor.BLUE +"Red Fragments");
				imeta.setLocalizedName(ChatColor.BLUE +"Red Fragments");
			}
			else if(i == 11) {
				ii.setType(Material.GREEN_CONCRETE_POWDER);
				List<String> lore = imeta.getLore();
				lore.addAll(Arrays.asList("",ChatColor.AQUA + "[Order of Smithing & Obtainable Biome]",ChatColor.AQUA + "Netherite-Earth(Plain)-Wind(Mountain,Hill)",ChatColor.AQUA + "Frost(Frozen)-Ocean-Dark(Forest)-Hyper(Desert)",ChatColor.AQUA + "Burning(Badlands)-Poison(Swamp)-Wild(Jungle)"));
				lore.addAll(Arrays.asList("",ChatColor.LIGHT_PURPLE + "If You Smith [Aura Weapon - Lv.7] with [Core Of Poison]", ChatColor.LIGHT_PURPLE + "You'll get [Aura Weapon - Lv.8]"));
				imeta.setLore(lore);
				imeta.setDisplayName(ChatColor.BLUE +"Fragments of Poison");
				imeta.setLocalizedName(ChatColor.BLUE +"Fragments of Poison");
			}
			else if(i == 12) {
				ii.setType(Material.JUNGLE_SAPLING);
				List<String> lore = imeta.getLore();
				lore.addAll(Arrays.asList("",ChatColor.AQUA + "[Order of Smithing & Obtainable Biome]",ChatColor.AQUA + "Netherite-Earth(Plain)-Wind(Mountain,Hill)",ChatColor.AQUA + "Frost(Frozen)-Ocean-Dark(Forest)-Hyper(Desert)",ChatColor.AQUA + "Burning(Badlands)-Poison(Swamp)-Wild(Jungle)"));
				lore.addAll(Arrays.asList("",ChatColor.LIGHT_PURPLE + "If You Smith [Aura Weapon - Lv.8] with [Core Of Wild]", ChatColor.LIGHT_PURPLE + "You'll get [Natural Weapon]"));
				lore.addAll(Arrays.asList("",ChatColor.GOLD + "If You Smith [Natural Weapon] with [Strong Core]", ChatColor.GOLD + "You'll get [Element Weapon]", ChatColor.GOLD + "You Can Get [Strong Core] From Boss Raid"));
				imeta.setDisplayName(ChatColor.BLUE +"Fragments of Wild");
				imeta.setLocalizedName(ChatColor.BLUE +"Fragments of Wild");
			}
			else if(i == -2) {
				ii.setType(Material.GHAST_TEAR);
				imeta.setLore(Arrays.asList("Used For Crafting Condensate Soul", "Soul Core is Used For Smithing Weapon"));
				imeta.setDisplayName(ChatColor.BLUE +"Fragments of Soul");
				imeta.setLocalizedName(ChatColor.BLUE +"Fragments of Soul");
			}
			else if(i == -3) {
				ii.setType(Material.CRIMSON_FUNGUS);
				imeta.setLore(Arrays.asList("Used For Crafting Crimson Core", "Crimson Core is Used For Smithing Weapon"));
				imeta.setDisplayName(ChatColor.BLUE +"Crimson Fragments");
				imeta.setLocalizedName(ChatColor.BLUE +"Crimson Fragments");
			}
			else if(i == -4) {
				ii.setType(Material.WARPED_FUNGUS);
				imeta.setLore(Arrays.asList("Used For Crafting Warped Core", "Warped Core is Used For Smithing Weapon"));
				imeta.setDisplayName(ChatColor.BLUE +"Warped Fragments");
				imeta.setLocalizedName(ChatColor.BLUE +"Warped Fragments");
			}
			else if(i == -5) {
				ii.setType(Material.BASALT);
				imeta.setLore(Arrays.asList("Used For Crafting Volcanic Core", "Volcanic Core is Used For Smithing Weapon"));
				imeta.setDisplayName(ChatColor.BLUE +"Volcanic Fragments");
				imeta.setLocalizedName(ChatColor.BLUE +"Volcanic Fragments");
			}
			else if(i == -6) {
				ii.setType(Material.END_STONE);
				imeta.setLore(Arrays.asList("Used For Crafting Ender Core", "Ender Core is Used For Smithing Weapon"));
				imeta.setDisplayName(ChatColor.BLUE +"Ender Fragments");
				imeta.setLocalizedName(ChatColor.BLUE +"Ender Fragments");
			}
			else if(i == -7) {
				ii.setType(Material.STRUCTURE_VOID);
				imeta.setLore(Arrays.asList("Used For Crafting Void Core", "Void Core is Used For Smithing Weapon"));
				imeta.setDisplayName(ChatColor.BLUE +"Void Fragments");
				imeta.setLocalizedName(ChatColor.BLUE +"Void Fragments");
			}
			else if(i == -8) {
				ii.setType(Material.NETHER_STAR);
				imeta.setLore(Arrays.asList("Used For Crafting Symbol Of Hero", "Symbol Of Hero is Used For Smithing Weapon"));
				imeta.setDisplayName(ChatColor.BLUE +"Epic Fragments");
				imeta.setLocalizedName(ChatColor.BLUE +"Epic Fragments");
			}
			ii.setItemMeta(imeta);
			
		}
		return ii;
	}


	public static ItemStack getelcore(int i, Player p) {
		ItemStack ii = new ItemStack(Material.GRASS);
		ItemMeta imeta = ii.getItemMeta();
		imeta.setCustomModelData(i);
		if(p.getLocale().equalsIgnoreCase("ko_kr")) {
			imeta.setLore(Arrays.asList("무기를 강화할때 사용합니다"));
			if(i == 5) {
				ii.setType(Material.GREEN_GLAZED_TERRACOTTA);
				List<String> lore = imeta.getLore();
				lore.addAll(Arrays.asList("",ChatColor.LIGHT_PURPLE + "[원소기운의 무기-1단계]를 강화시", ChatColor.LIGHT_PURPLE + "[원소기운의 무기-2단계]를 획득합니다"));
				imeta.setLore(lore);
				imeta.setDisplayName(ChatColor.LIGHT_PURPLE +"바람의 핵");
				imeta.setLocalizedName(ChatColor.LIGHT_PURPLE +"바람의 핵");
			}
			else if(i == 14) {
				List<String> lore = imeta.getLore();
				lore.addAll(Arrays.asList("",ChatColor.LIGHT_PURPLE + "[네더라이트 무기]를 강화시", ChatColor.LIGHT_PURPLE + "[원소기운의 무기-1단계]를 획득합니다"));
				imeta.setLore(lore);
				ii.setType(Material.LIGHT_GRAY_GLAZED_TERRACOTTA);
				imeta.setDisplayName(ChatColor.LIGHT_PURPLE +"대지의 핵");
				imeta.setLocalizedName(ChatColor.LIGHT_PURPLE +"대지의 핵");
			}
			else if(i == 6) {
				List<String> lore = imeta.getLore();
				lore.addAll(Arrays.asList("",ChatColor.LIGHT_PURPLE + "[원소기운의 무기-2단계]를 강화시", ChatColor.LIGHT_PURPLE + "[원소기운의 무기-3단계]를 획득합니다"));
				imeta.setLore(lore);
				ii.setType(Material.LIGHT_BLUE_GLAZED_TERRACOTTA);
				imeta.setDisplayName(ChatColor.LIGHT_PURPLE +"서리의 핵");
				imeta.setLocalizedName(ChatColor.LIGHT_PURPLE +"서리의 핵");
			}
			else if(i == 7) {
				List<String> lore = imeta.getLore();
				lore.addAll(Arrays.asList("",ChatColor.LIGHT_PURPLE + "[원소기운의 무기-3단계]를 강화시", ChatColor.LIGHT_PURPLE + "[원소기운의 무기-4단계]를 획득합니다"));
				imeta.setLore(lore);
				ii.setType(Material.BLUE_GLAZED_TERRACOTTA);
				imeta.setDisplayName(ChatColor.LIGHT_PURPLE +"바다의 핵");
				imeta.setLocalizedName(ChatColor.LIGHT_PURPLE +"바다의 핵");
			}
			else if(i == 8) {
				List<String> lore = imeta.getLore();
				lore.addAll(Arrays.asList("",ChatColor.LIGHT_PURPLE + "[원소기운의 무기-4단계]를 강화시", ChatColor.LIGHT_PURPLE + "[원소기운의 무기-5단계]를 획득합니다"));
				imeta.setLore(lore);
				ii.setType(Material.BROWN_GLAZED_TERRACOTTA);
				imeta.setDisplayName(ChatColor.LIGHT_PURPLE +"어둠의 핵");
				imeta.setLocalizedName(ChatColor.LIGHT_PURPLE +"어둠의 핵");
			}
			else if(i == 9) {
				ii.setType(Material.WHITE_GLAZED_TERRACOTTA);
				List<String> lore = imeta.getLore();
				lore.addAll(Arrays.asList("",ChatColor.LIGHT_PURPLE + "[원소기운의 무기-5단계]를 강화시", ChatColor.LIGHT_PURPLE + "[원소기운의 무기-6단계]를 획득합니다"));
				imeta.setLore(lore);
				imeta.setDisplayName(ChatColor.LIGHT_PURPLE +"하이퍼 엔진");
				imeta.setLocalizedName(ChatColor.LIGHT_PURPLE +"하이퍼 엔진");
			}
			else if(i == 10) {
				ii.setType(Material.RED_GLAZED_TERRACOTTA);
				List<String> lore = imeta.getLore();
				lore.addAll(Arrays.asList("",ChatColor.LIGHT_PURPLE + "[원소기운의 무기-6단계]를 강화시", ChatColor.LIGHT_PURPLE + "[원소기운의 무기-7단계]를 획득합니다"));
				imeta.setLore(lore);
				imeta.setDisplayName(ChatColor.LIGHT_PURPLE +"작열하는 심장");
				imeta.setLocalizedName(ChatColor.LIGHT_PURPLE +"작열하는 심장");
			}
			else if(i == 11) {
				List<String> lore = imeta.getLore();
				lore.addAll(Arrays.asList("",ChatColor.LIGHT_PURPLE + "[원소기운의 무기-7단계]를 강화시", ChatColor.LIGHT_PURPLE + "[원소기운의 무기-8단계]를 획득합니다"));
				imeta.setLore(lore);
				ii.setType(Material.LIME_GLAZED_TERRACOTTA);
				imeta.setDisplayName(ChatColor.LIGHT_PURPLE +"맹독의 핵");
				imeta.setLocalizedName(ChatColor.LIGHT_PURPLE +"맹독의 핵");
			}
			else if(i == 12) {
				ii.setType(Material.ORANGE_GLAZED_TERRACOTTA);
				List<String> lore = imeta.getLore();
				lore.addAll(Arrays.asList("",ChatColor.LIGHT_PURPLE + "[원소기운의 무기-8단계]를 강화시", ChatColor.LIGHT_PURPLE + "[자연의 무기]를 획득합니다"));
				lore.addAll(Arrays.asList("",ChatColor.GOLD + "자연의 무기를 [강력한 원소의 기운]으로 강화시", ChatColor.GOLD + "해당 속성의 무기를 제작할수 있습니다", ChatColor.GOLD + "[강력한 원소의 기운]은 보스 침 에서 획득",ChatColor.GOLD +"또는 고고학자에게 구매 가능합니다",ChatColor.GOLD +"고고학자는 정글피라미드에서 찾을수 있습니다"));
				imeta.setLore(lore);
				imeta.setDisplayName(ChatColor.LIGHT_PURPLE +"야생의 핵");
				imeta.setLocalizedName(ChatColor.LIGHT_PURPLE +"야생의 핵");
			}
			else if(i == -2) {
				ii.setType(Material.SOUL_SOIL);
				imeta.setLore(Arrays.asList("무기 강화, 융합된 네더의 핵 제작에 사용됩니다","",ChatColor.GOLD +"융합된 네더의 핵은 네더지역에서 얻은",ChatColor.GOLD +"네가지 핵들을 조합하면 획득가능합니다",ChatColor.GOLD +"융합된 네더의 핵으로 헬멧 또는 갑옷 강화시",ChatColor.GOLD +"모든 능력치가 상승합니다(방어구당 한번만 가능)","",
						ChatColor.GRAY +"무기강화시 네더지역 핵은",ChatColor.GRAY +"한가지만 부여 가능합니다", ChatColor.GRAY +"+50% 일반공격력, +15 행운", ChatColor.GRAY +"물,바람,어둠,서리 계열 공격력 +25%", "행운은 궁극기를 제외한 일반기술들의", "재사용 대기시간 감소에 영향을 줍니다"
						,"", ChatColor.AQUA +"제작대에서 [진홍빛 핵]으로 교환가능"));
				imeta.setDisplayName(ChatColor.LIGHT_PURPLE +"영혼 응축물");
				imeta.setLocalizedName(ChatColor.LIGHT_PURPLE +"영혼 응축물");
			}
			else if(i == -3) {
				ii.setType(Material.CRIMSON_HYPHAE);
				imeta.setLore(Arrays.asList("무기 강화, 융합된 네더의 핵 제작에 사용됩니다","",ChatColor.GOLD +"융합된 네더의 핵은 네더지역에서 얻은",ChatColor.GOLD +"네가지 핵들을 조합하면 획득가능합니다",ChatColor.GOLD +"융합된 네더의 핵으로 헬멧 또는 갑옷 강화시",ChatColor.GOLD +"모든 능력치가 상승합니다(방어구당 한번만 가능)","",
						ChatColor.GRAY +"무기강화시 네더지역 핵은",ChatColor.GRAY +"한가지만 부여 가능합니다", ChatColor.GRAY +"+50% 일반공격력, +15 행운", ChatColor.GRAY +"불,대지,번개,독 계열 공격력 +25%", "행운은 궁극기를 제외한 일반기술들의", "재사용 대기시간 감소에 영향을 줍니다"
						,"", ChatColor.AQUA +"제작대에서 [뒤틀린 핵]으로 교환가능"));
				imeta.setDisplayName(ChatColor.LIGHT_PURPLE +"진홍빛 핵");
				imeta.setLocalizedName(ChatColor.LIGHT_PURPLE +"진홍빛 핵");
			}
			else if(i == -4) {
				ii.setType(Material.WARPED_HYPHAE);
				imeta.setLore(Arrays.asList("무기 강화, 융합된 네더의 핵 제작에 사용됩니다","",ChatColor.GOLD +"융합된 네더의 핵은 네더지역에서 얻은",ChatColor.GOLD +"네가지 핵들을 조합하면 획득가능합니다",ChatColor.GOLD +"융합된 네더의 핵으로 헬멧 또는 갑옷 강화시",ChatColor.GOLD +"모든 능력치가 상승합니다(방어구당 한번만 가능)","",
						ChatColor.GRAY +"무기강화시 네더지역 핵은",ChatColor.GRAY +"한가지만 부여 가능합니다", ChatColor.GRAY +"+25% 일반공격력, +50 행운", ChatColor.GRAY +"물,바람,어둠,서리 계열 공격력 +25%", "행운은 궁극기를 제외한 일반기술들의", "재사용 대기시간 감소에 영향을 줍니다"
						,"", ChatColor.AQUA +"제작대에서 [화산의 핵]으로 교환가능"));
				imeta.setDisplayName(ChatColor.LIGHT_PURPLE +"뒤틀린 핵");
				imeta.setLocalizedName(ChatColor.LIGHT_PURPLE +"뒤틀린 핵");
			}
			else if(i == -5) {
				ii.setType(Material.GILDED_BLACKSTONE);
				imeta.setLore(Arrays.asList("무기 강화, 융합된 네더의 핵 제작에 사용됩니다","",ChatColor.GOLD +"융합된 네더의 핵은 네더지역에서 얻은",ChatColor.GOLD +"네가지 핵들을 조합하면 획득가능합니다",ChatColor.GOLD +"융합된 네더의 핵으로 헬멧 또는 갑옷 강화시",ChatColor.GOLD +"모든 능력치가 상승합니다(방어구당 한번만 가능)","",
						ChatColor.GRAY +"무기강화시 네더지역 핵은",ChatColor.GRAY +"한가지만 부여 가능합니다", ChatColor.GRAY +"+25% 일반공격력, +50 행운", ChatColor.GRAY +"불,대지,번개,독 계열 공격력 +25%", "행운은 궁극기를 제외한 일반기술들의", "재사용 대기시간 감소에 영향을 줍니다"
						,"", ChatColor.AQUA +"제작대에서 [영혼 응축물]으로 교환가능"));
				imeta.setDisplayName(ChatColor.LIGHT_PURPLE +"화산의 핵");
				imeta.setLocalizedName(ChatColor.LIGHT_PURPLE +"화산의 핵");
			}
			else if(i == -6) {
				ii.setType(Material.END_STONE_BRICKS);
				imeta.setLore(Arrays.asList("무기 강화, 융합된 엔더의 핵 제작에 사용됩니다","",ChatColor.GOLD +"융합된 엔더의 핵은 엔더지역에서 얻은",ChatColor.GOLD +"두가지 핵들을 조합하면 획득가능합니다",ChatColor.GOLD +"융합된 엔더의 핵으로 레깅스 또는 부츠 강화시",ChatColor.GOLD +"모든 능력치가 상승합니다(방어구당 한번만 가능)","",
						ChatColor.GRAY +"무기강화시 엔더지역 핵은",ChatColor.GRAY +"한가지만 부여 가능합니다", ChatColor.GRAY +"+80% 일반공격력, +30% 행운", ChatColor.GRAY +"모든 원소 계열 공격력 +35%", "행운은 궁극기를 제외한 일반기술들의", "재사용 대기시간 감소에 영향을 줍니다"
						,"", ChatColor.AQUA +"제작대에서 [공허의 핵]으로 교환가능"));
				imeta.setDisplayName(ChatColor.LIGHT_PURPLE +"엔더 핵");
				imeta.setLocalizedName(ChatColor.LIGHT_PURPLE +"엔더 핵");
			}
			else if(i == -7) {
				ii.setType(Material.PURPUR_PILLAR);
				imeta.setLore(Arrays.asList("무기 강화, 융합된 엔더의 핵 제작에 사용됩니다","",ChatColor.GOLD +"융합된 엔더의 핵은 엔더지역에서 얻은",ChatColor.GOLD +"두가지 핵들을 조합하면 획득가능합니다",ChatColor.GOLD +"융합된 엔더의 핵으로 레깅스 또는 부츠 강화시",ChatColor.GOLD +"모든 능력치가 상승합니다(방어구당 한번만 가능)","",
						ChatColor.GRAY +"무기강화시 엔더지역 핵은",ChatColor.GRAY +"한가지만 부여 가능합니다", ChatColor.GRAY +"+40% 일반공격력, +60% 행운", ChatColor.GRAY +"모든 원소 계열 공격력 +35%", "행운은 궁극기를 제외한 일반기술들의", "재사용 대기시간 감소에 영향을 줍니다"
						,"", ChatColor.AQUA +"제작대에서 [엔더 핵]으로 교환가능"));
				imeta.setDisplayName(ChatColor.LIGHT_PURPLE +"공허의 핵");
				imeta.setLocalizedName(ChatColor.LIGHT_PURPLE +"공허의 핵");
			}
			else if(i == -8) {
				ii.setType(Material.CRYING_OBSIDIAN);
				imeta.setLore(Arrays.asList("무기 강화시 사용됩니다", "+110% 일반공격력, +80% 행운", "모든 원소 계열 공격력 +55%", "행운은 궁극기를 제외한 일반기술들의", "재사용 대기시간 감소에 영향을 줍니다"));
				imeta.setDisplayName(ChatColor.LIGHT_PURPLE +"영웅의 상징");
				imeta.setLocalizedName(ChatColor.LIGHT_PURPLE +"영웅의 상징");
			}
			imeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
			ii.setItemMeta(imeta);
			
		}
		else {
			imeta.setLore(Arrays.asList("Used For Smithing Weapon"));
			if(i == 5) {
				ii.setType(Material.GREEN_GLAZED_TERRACOTTA);
				List<String> lore = imeta.getLore();
				lore.addAll(Arrays.asList("",ChatColor.LIGHT_PURPLE + "If You Smith [Aura Weapon - Lv.1]", ChatColor.LIGHT_PURPLE + "You'll get [Aura Weapon - Lv.2]"));
				imeta.setLore(lore);
				imeta.setDisplayName(ChatColor.LIGHT_PURPLE +"Core of Wind");
				imeta.setLocalizedName(ChatColor.LIGHT_PURPLE +"Core of Wind");
			}
			else if(i == 6) {
				List<String> lore = imeta.getLore();
				lore.addAll(Arrays.asList("",ChatColor.LIGHT_PURPLE + "If You Smith [Aura Weapon - Lv.2]", ChatColor.LIGHT_PURPLE + "You'll get [Aura Weapon - Lv.3]"));
				imeta.setLore(lore);
				ii.setType(Material.LIGHT_BLUE_GLAZED_TERRACOTTA);
				imeta.setDisplayName(ChatColor.LIGHT_PURPLE +"Core of Frost");
				imeta.setLocalizedName(ChatColor.LIGHT_PURPLE +"Core of Frost");
			}
			else if(i == 7) {
				List<String> lore = imeta.getLore();
				lore.addAll(Arrays.asList("",ChatColor.LIGHT_PURPLE + "If You Smith [Aura Weapon - Lv.3]", ChatColor.LIGHT_PURPLE + "You'll get [Aura Weapon - Lv.4]"));
				imeta.setLore(lore);
				ii.setType(Material.BLUE_GLAZED_TERRACOTTA);
				imeta.setDisplayName(ChatColor.LIGHT_PURPLE +"Core of Ocean");
				imeta.setLocalizedName(ChatColor.LIGHT_PURPLE +"Core of Ocean");
			}
			else if(i == 8) {
				List<String> lore = imeta.getLore();
				lore.addAll(Arrays.asList("",ChatColor.LIGHT_PURPLE + "If You Smith [Aura Weapon - Lv.4]", ChatColor.LIGHT_PURPLE + "You'll get [Aura Weapon - Lv.5]"));
				imeta.setLore(lore);
				ii.setType(Material.BROWN_GLAZED_TERRACOTTA);
				imeta.setDisplayName(ChatColor.LIGHT_PURPLE +"Core of Darkness");
				imeta.setLocalizedName(ChatColor.LIGHT_PURPLE +"Core of Darkness");
			}
			else if(i == 9) {
				List<String> lore = imeta.getLore();
				lore.addAll(Arrays.asList("",ChatColor.LIGHT_PURPLE + "If You Smith [Aura Weapon - Lv.5]", ChatColor.LIGHT_PURPLE + "You'll get [Aura Weapon - Lv.6]"));
				imeta.setLore(lore);
				ii.setType(Material.WHITE_GLAZED_TERRACOTTA);
				imeta.setDisplayName(ChatColor.LIGHT_PURPLE +"Hyper Engine");
				imeta.setLocalizedName(ChatColor.LIGHT_PURPLE +"Hyper Engine");
			}
			else if(i == 10) {
				ii.setType(Material.RED_GLAZED_TERRACOTTA);
				List<String> lore = imeta.getLore();
				lore.addAll(Arrays.asList("",ChatColor.LIGHT_PURPLE + "If You Smith [Aura Weapon - Lv.6]", ChatColor.LIGHT_PURPLE + "You'll get [Aura Weapon - Lv.7]"));
				imeta.setLore(lore);
				imeta.setDisplayName(ChatColor.LIGHT_PURPLE +"Burning Heart");
				imeta.setLocalizedName(ChatColor.LIGHT_PURPLE +"Burning Heart");
			}
			else if(i == 11) {
				ii.setType(Material.LIME_GLAZED_TERRACOTTA);
				List<String> lore = imeta.getLore();
				lore.addAll(Arrays.asList("",ChatColor.LIGHT_PURPLE + "If You Smith [Aura Weapon - Lv.7]", ChatColor.LIGHT_PURPLE + "You'll get [Aura Weapon - Lv.8]"));
				imeta.setLore(lore);
				imeta.setDisplayName(ChatColor.LIGHT_PURPLE +"Core of Poison");
				imeta.setLocalizedName(ChatColor.LIGHT_PURPLE +"Core of Poison");
			}
			else if(i == 14) {
				List<String> lore = imeta.getLore();
				lore.addAll(Arrays.asList("",ChatColor.LIGHT_PURPLE + "If You Smith Netherite Weapon", ChatColor.LIGHT_PURPLE + "You'll get [Aura Weapon - Lv.1]"));
				imeta.setLore(lore);
				ii.setType(Material.LIGHT_GRAY_GLAZED_TERRACOTTA);
				imeta.setDisplayName(ChatColor.LIGHT_PURPLE +"Core of Earth");
				imeta.setLocalizedName(ChatColor.LIGHT_PURPLE +"Core of Earth");
			}
			else if(i == 12) {
				ii.setType(Material.ORANGE_GLAZED_TERRACOTTA);
				List<String> lore = imeta.getLore();
				lore.addAll(Arrays.asList("",ChatColor.LIGHT_PURPLE + "If You Smith [Aura Weapon - Lv.8] with [Core Of Wild]", ChatColor.LIGHT_PURPLE + "You'll get [Natural Weapon]"));
				lore.addAll(Arrays.asList("",ChatColor.GOLD + "If You Smith [Natural Weapon] with [Strong Core]", ChatColor.GOLD + "You'll get [Element Weapon]", ChatColor.GOLD + "You Can Get [Strong Core] From Boss Raid"
						,ChatColor.GOLD +"Or Buy From Archaeologist",ChatColor.GOLD +"Archaeologist locates on Jungle Pyramid"));
				imeta.setLore(lore);
				imeta.setDisplayName(ChatColor.LIGHT_PURPLE +"Wild Core");
				imeta.setLocalizedName(ChatColor.LIGHT_PURPLE +"Wild Core");
			}
			else if(i == -2) {
				ii.setType(Material.SOUL_SOIL);
				imeta.setLore(Arrays.asList("Used for Smithing Weapon", "And Crafting Converged NetherCore","",ChatColor.GOLD +"You Can Craft Converged NetherCore",ChatColor.GOLD +"With 4 Cores From Nether Region",ChatColor.GOLD +"Which is Used For Smithing [ChestPlate or Helmet]",ChatColor.GOLD +"That increase All abilities(Unstackable)"
						,"", ChatColor.GRAY +"Only Single Nether Region Core", ChatColor.GRAY +"Can be Applied To [Weapon]",ChatColor.GRAY +"+50% Damage, +15 Luck",ChatColor.GRAY +"+25% Power Of Water,Wind,Dark,Frost", "Luck Affect To Decreasing Skill's Cooldown", "Except Ult"
						,"", ChatColor.AQUA +"You Can Change To [Crimson Core]", ChatColor.AQUA +"On Crafting Table"));
				imeta.setDisplayName(ChatColor.LIGHT_PURPLE +"Condensate Soul");
				imeta.setLocalizedName(ChatColor.LIGHT_PURPLE +"Condensate Soul");
			}
			else if(i == -3) {
				ii.setType(Material.CRIMSON_HYPHAE);
				imeta.setLore(Arrays.asList("Used for Smithing Weapon", "Crafting Converged NetherCore","",ChatColor.GOLD +"You Can Craft Converged NetherCore",ChatColor.GOLD +"With 4 Cores From Nether Region",ChatColor.GOLD +"Smithing ChestPlate or Helmet With This",ChatColor.GOLD +"Will increase All abilities(Unstackable)"
						,"", ChatColor.GRAY +"Only Single Nether Region Core", ChatColor.GRAY +"Can be Applied To Weapon",ChatColor.GRAY +"+50% Damage, +15 Luck",ChatColor.GRAY +"+25% Power Of Fire,Earth,Lightning,Poison", "Luck Affect To Decreasing Skill's Cooldown", "Except Ult"
						,"", ChatColor.AQUA +"You Can Change To [Warped Core]", ChatColor.AQUA +"On Crafting Table"));
				imeta.setDisplayName(ChatColor.LIGHT_PURPLE +"Crimson Core");
				imeta.setLocalizedName(ChatColor.LIGHT_PURPLE +"Crimson Core");
			}
			else if(i == -4) {
				ii.setType(Material.WARPED_HYPHAE);
				imeta.setLore(Arrays.asList("Used for Smithing Weapon", "Crafting Converged NetherCore","",ChatColor.GOLD +"You Can Craft Converged NetherCore",ChatColor.GOLD +"With 4 Cores From Nether Region",ChatColor.GOLD +"Smithing ChestPlate or Helmet With This",ChatColor.GOLD +"Will increase All abilities(Unstackable)"
						,"", ChatColor.GRAY +"Only Single Nether Region Core", ChatColor.GRAY +"Can be Applied To Weapon",ChatColor.GRAY +"+25% Damage, +50 Luck",ChatColor.GRAY +"+25% Power Of Water,Wind,Dark,Frost", "Luck Affect To Decreasing Skill's Cooldown", "Except Ult"
						,"", ChatColor.AQUA +"You Can Change To [Volcanic Core]", ChatColor.AQUA +"On Crafting Table"));
				imeta.setDisplayName(ChatColor.LIGHT_PURPLE +"Warped Core");
				imeta.setLocalizedName(ChatColor.LIGHT_PURPLE +"Warped Core");
			}
			else if(i == -5) {
				ii.setType(Material.GILDED_BLACKSTONE);
				imeta.setLore(Arrays.asList("Used for Smithing Weapon", "Crafting Converged NetherCore","",ChatColor.GOLD +"You Can Craft Converged NetherCore",ChatColor.GOLD +"With 4 Cores From Nether Region",ChatColor.GOLD +"Smithing ChestPlate or Helmet With This",ChatColor.GOLD +"Will increase All abilities(Unstackable)"
						,"", ChatColor.GRAY +"Only Single Nether Region Core", ChatColor.GRAY +"Can be Applied To Weapon",ChatColor.GRAY +"+25% Damage, +50 Luck",ChatColor.GRAY +"+25% Power Of Fire,Earth,Lightning,Poison", "Luck Affect To Decreasing Skill's Cooldown", "Except Ult"
						,"", ChatColor.AQUA +"You Can Change To [Condensate Soul]", ChatColor.AQUA +"On Crafting Table"));
				imeta.setDisplayName(ChatColor.LIGHT_PURPLE +"Volcanic Core");
				imeta.setLocalizedName(ChatColor.LIGHT_PURPLE +"Volcanic Core");
			}
			else if(i == -6) {
				ii.setType(Material.END_STONE_BRICKS);
				imeta.setLore(Arrays.asList("Used for Smithing Weapon", "Crafting Converged EnderCore","",ChatColor.GOLD +"You Can Craft Converged EnderCore",ChatColor.GOLD +"With 2 Cores From Ender Region",ChatColor.GOLD +"Smithing Leggings or Boots With This",ChatColor.GOLD +"Will increase All abilities(Unstackable)"
						,"", ChatColor.GRAY +"Only Single Ender Region Core", ChatColor.GRAY +"Can be Applied To Weapon",ChatColor.GRAY +"+80% Damage, +30% Luck",ChatColor.GRAY +"+35% Power Of All Elements", "Luck Affect To Decreasing Skill's Cooldown", "Except Ult"
						,"", ChatColor.AQUA +"You Can Change To [Void Core]", ChatColor.AQUA +"On Crafting Table"));
				imeta.setDisplayName(ChatColor.LIGHT_PURPLE +"Ender Core");
				imeta.setLocalizedName(ChatColor.LIGHT_PURPLE +"Ender Core");
			}
			else if(i == -7) {
				ii.setType(Material.PURPUR_PILLAR);
				imeta.setLore(Arrays.asList("Used for Smithing Weapon", "Crafting Converged EnderCore","",ChatColor.GOLD +"You Can Craft Converged EnderCore",ChatColor.GOLD +"With 2 Cores From Ender Region",ChatColor.GOLD +"Smithing Leggings or Boots With This",ChatColor.GOLD +"Will increase All abilities(Unstackable)"
						,"", ChatColor.GRAY +"Only Single Ender Region Core", ChatColor.GRAY +"Can be Applied To Weapon",ChatColor.GRAY +"+40% Damage, +60% Luck",ChatColor.GRAY +"+35% Power Of All Elements", "Luck Affect To Decreasing Skill's Cooldown", "Except Ult"
						,"", ChatColor.AQUA +"You Can Change To [Ender Core]", ChatColor.AQUA +"On Crafting Table"));
				imeta.setDisplayName(ChatColor.LIGHT_PURPLE +"Void Core");
				imeta.setLocalizedName(ChatColor.LIGHT_PURPLE +"Void Core");
			}
			else if(i == -8) {
				ii.setType(Material.CRYING_OBSIDIAN);
				imeta.setLore(Arrays.asList("Used for Smithing Weapon", "Increase Damage 110%, Luck 80%","+55% Power Of All Elements", "Luck Affect To Decreasing Skill's Cooldown", "Except Ult"));
				imeta.setDisplayName(ChatColor.LIGHT_PURPLE +"Symbol Of Hero");
				imeta.setLocalizedName(ChatColor.LIGHT_PURPLE +"Symbol Of Hero");
			}
			imeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
			ii.setItemMeta(imeta);
		}
		
		return ii;
	}
	

	public static ItemStack getstel(int i, Player p) {
		ItemStack ii = new ItemStack(Material.GRASS);
		ItemMeta imeta = ii.getItemMeta();
		imeta.setCustomModelData(200*(i>0?1:-1) + i);
		imeta.setLore(Arrays.asList("고고학자에게 구매 가능합니다","고고학자는 정글피라미드에서 찾을수 있습니다"));
		if(p.getLocale().equalsIgnoreCase("ko_kr")) {
			if(i == 5) {
				ii.setType(Material.GREEN_GLAZED_TERRACOTTA);
				List<String> lore = imeta.getLore();
				lore.addAll(Arrays.asList("",ChatColor.GOLD + "[자연의 무기]를 강화시", ChatColor.GOLD + "[바람의 무기]를 획득합니다"));
				lore.addAll(Arrays.asList("",ChatColor.GOLD + "[고대의 방어구]를 강화시", ChatColor.GOLD + "[바람의 방어구]를 획득합니다"));
				lore.addAll(Arrays.asList("",ChatColor.RED + "[고대의 방어구]는 네더 보스 몬스터드랍", ChatColor.RED + "또는 사신 npc에게 구매가능합니다"));
				imeta.setLore(lore);
				imeta.setDisplayName(ChatColor.GOLD +"폭풍의 핵");
				imeta.setLocalizedName(ChatColor.GOLD +"폭풍의 핵");
			}
			else if(i == 14) {
				List<String> lore = imeta.getLore();
				lore.addAll(Arrays.asList("",ChatColor.GOLD + "[자연의 무기]를 강화시", ChatColor.GOLD + "[대지의 무기]를 획득합니다"));
				lore.addAll(Arrays.asList("",ChatColor.GOLD + "[고대의 방어구]를 강화시", ChatColor.GOLD + "[대지의 방어구]를 획득합니다"));
				lore.addAll(Arrays.asList("",ChatColor.RED + "[고대의 방어구]는 네더 보스 몬스터드랍", ChatColor.RED + "또는 사신 npc에게 구매가능합니다"));
				imeta.setLore(lore);
				ii.setType(Material.LIGHT_GRAY_GLAZED_TERRACOTTA);
				imeta.setDisplayName(ChatColor.GOLD +"광야의 핵");
				imeta.setLocalizedName(ChatColor.GOLD +"광야의 핵");
			}
			else if(i == 6) {
				List<String> lore = imeta.getLore();
				lore.addAll(Arrays.asList("",ChatColor.GOLD + "[자연의 무기]를 강화시", ChatColor.GOLD + "[서리의 무기]를 획득합니다"));
				lore.addAll(Arrays.asList("",ChatColor.GOLD + "[고대의 방어구]를 강화시", ChatColor.GOLD + "[서리의 방어구]를 획득합니다"));
				lore.addAll(Arrays.asList("",ChatColor.RED + "[고대의 방어구]는 네더 보스 몬스터드랍", ChatColor.RED + "또는 사신 npc에게 구매가능합니다"));
				imeta.setLore(lore);
				ii.setType(Material.LIGHT_BLUE_GLAZED_TERRACOTTA);
				imeta.setDisplayName(ChatColor.GOLD +"만년서리의 핵");
				imeta.setLocalizedName(ChatColor.GOLD +"만년서리의 핵");
			}
			else if(i == 7) {
				List<String> lore = imeta.getLore();
				lore.addAll(Arrays.asList("",ChatColor.GOLD + "[자연의 무기]를 강화시", ChatColor.GOLD + "[바다의 무기]를 획득합니다"));
				lore.addAll(Arrays.asList("",ChatColor.GOLD + "[고대의 방어구]를 강화시", ChatColor.GOLD + "[바다의 방어구]를 획득합니다"));
				lore.addAll(Arrays.asList("",ChatColor.RED + "[고대의 방어구]는 네더 보스 몬스터드랍", ChatColor.RED + "또는 사신 npc에게 구매가능합니다"));
				imeta.setLore(lore);
				ii.setType(Material.BLUE_GLAZED_TERRACOTTA);
				imeta.setDisplayName(ChatColor.GOLD +"대양의 핵");
				imeta.setLocalizedName(ChatColor.GOLD +"대양의 핵");
			}
			else if(i == 8) {
				List<String> lore = imeta.getLore();
				lore.addAll(Arrays.asList("",ChatColor.GOLD + "[자연의 무기]를 강화시", ChatColor.GOLD + "[어둠의 무기]를 획득합니다"));
				lore.addAll(Arrays.asList("",ChatColor.GOLD + "[고대의 방어구]를 강화시", ChatColor.GOLD + "[어둠의 방어구]를 획득합니다"));
				lore.addAll(Arrays.asList("",ChatColor.RED + "[고대의 방어구]는 네더 보스 몬스터드랍", ChatColor.RED + "또는 사신 npc에게 구매가능합니다"));
				imeta.setLore(lore);
				ii.setType(Material.BROWN_GLAZED_TERRACOTTA);
				imeta.setDisplayName(ChatColor.GOLD +"밤의 핵");
				imeta.setLocalizedName(ChatColor.GOLD +"밤의 핵");
			}
			else if(i == 9) {
				ii.setType(Material.WHITE_GLAZED_TERRACOTTA);
				List<String> lore = imeta.getLore();
				lore.addAll(Arrays.asList("",ChatColor.GOLD + "[자연의 무기]를 강화시", ChatColor.GOLD + "[개조된 무기]를 획득합니다"));
				lore.addAll(Arrays.asList("",ChatColor.GOLD + "[고대의 방어구]를 강화시", ChatColor.GOLD + "[개조된 방어구]를 획득합니다"));
				lore.addAll(Arrays.asList("",ChatColor.RED + "[고대의 방어구]는 네더 보스 몬스터드랍", ChatColor.RED + "또는 사신 npc에게 구매가능합니다"));
				imeta.setLore(lore);
				imeta.setDisplayName(ChatColor.GOLD +"강화형 하이퍼 엔진");
				imeta.setLocalizedName(ChatColor.GOLD +"강화형 하이퍼 엔진");
			}
			else if(i == 10) {
				ii.setType(Material.RED_GLAZED_TERRACOTTA);
				List<String> lore = imeta.getLore();
				lore.addAll(Arrays.asList("",ChatColor.GOLD + "[자연의 무기]를 강화시", ChatColor.GOLD + "[작열하는 무기]를 획득합니다"));
				lore.addAll(Arrays.asList("",ChatColor.GOLD + "[고대의 방어구]를 강화시", ChatColor.GOLD + "[작열하는 방어구]를 획득합니다"));
				lore.addAll(Arrays.asList("",ChatColor.RED + "[고대의 방어구]는 네더 보스 몬스터드랍", ChatColor.RED + "또는 사신 npc에게 구매가능합니다"));
				imeta.setLore(lore);
				imeta.setDisplayName(ChatColor.GOLD +"태양의 심장");
				imeta.setLocalizedName(ChatColor.GOLD +"태양의 심장");
			}
			else if(i == 11) {
				List<String> lore = imeta.getLore();
				lore.addAll(Arrays.asList("",ChatColor.GOLD + "[자연의 무기]를 강화시", ChatColor.GOLD + "[맹독의 무기]를 획득합니다"));
				lore.addAll(Arrays.asList("",ChatColor.GOLD + "[고대의 방어구]를 강화시", ChatColor.GOLD + "[맹독의 방어구]를 획득합니다"));
				lore.addAll(Arrays.asList("",ChatColor.RED + "[고대의 방어구]는 네더 보스 몬스터드랍", ChatColor.RED + "또는 사신 npc에게 구매가능합니다"));
				imeta.setLore(lore);
				ii.setType(Material.LIME_GLAZED_TERRACOTTA);
				imeta.setDisplayName(ChatColor.GOLD +"강력한 맹독의 핵");
				imeta.setLocalizedName(ChatColor.GOLD +"강력한 맹독의 핵");
			}
			else if(i == 12) {
				ii.setType(Material.BLACK_GLAZED_TERRACOTTA);
				imeta.setLore(Arrays.asList("고고학자에게 강력한 원소핵을 구매할 수 있습니다","고고학자는 정글피라미드에서 찾을수 있습니다"));
				imeta.setDisplayName(ChatColor.GOLD +"강력한 원소의 기운");
				imeta.setLocalizedName(ChatColor.GOLD +"강력한 원소의 기운");
			}
			else if(i == -2 || i== -3 || i == -4 || i == -5) {
				ii.setType(Material.ANCIENT_DEBRIS);
				imeta.setLore(Arrays.asList("투구, 갑옷 강화에 사용됩니다(방어구당 한번만 가능)","",
						ChatColor.GRAY +"+15% 공격력, +10 행운", "", "행운은 궁극기를 제외한 일반기술들의", "재사용 대기시간 감소에 영향을 줍니다"));
				imeta.setDisplayName(ChatColor.GOLD +"융합된 네더의 핵");
				imeta.setLocalizedName(ChatColor.GOLD +"융합된 네더의 핵");
			}
			else if(i == -6 || i ==7) {
				ii.setType(Material.END_CRYSTAL);
				imeta.setLore(Arrays.asList("레깅스, 부츠 강화에 사용됩니다(방어구당 한번만 가능)","",
						ChatColor.GRAY +"+20% 공격력, +15 행운, +20% 속도", "", "행운은 궁극기를 제외한 일반기술들의", "재사용 대기시간 감소에 영향을 줍니다"));
				imeta.setDisplayName(ChatColor.GOLD +"융합된 엔더 핵");
				imeta.setLocalizedName(ChatColor.GOLD +"융합된 엔더 핵");
			}
			imeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
			ii.setItemMeta(imeta);
			
		}
		else {
			imeta.setLore(Arrays.asList("You Can Buy From [Archaeologist]","(Archaeologist Locates on the Jungle Pyramid)"));
			if(i == 5) {
				ii.setType(Material.GREEN_GLAZED_TERRACOTTA);
				List<String> lore = imeta.getLore();
				lore.addAll(Arrays.asList("",ChatColor.GOLD + "If You Smith [Natural Weapon]", ChatColor.GOLD + "You'll get [Wind Weapon]"));
				lore.addAll(Arrays.asList("",ChatColor.GOLD + "If You Smith [Ancient Armor]", ChatColor.GOLD + "You'll get [Wind Armor]"));
				lore.addAll(Arrays.asList("",ChatColor.RED + "You Can Get [Ancient Armor] From Nether Boss", ChatColor.RED + "Or Reaper's Shop"));
				imeta.setLore(lore);
				imeta.setDisplayName(ChatColor.GOLD +"Core of Storm");
				imeta.setLocalizedName(ChatColor.GOLD +"Core of Storm");
			}
			else if(i == 14) {
				List<String> lore = imeta.getLore();
				lore.addAll(Arrays.asList("",ChatColor.GOLD + "If You Smith [Natural Weapon]", ChatColor.GOLD + "You'll get [Earth Weapon]"));
				lore.addAll(Arrays.asList("",ChatColor.GOLD + "If You Smith [Ancient Armor]", ChatColor.GOLD + "You'll get [Earth Armor]"));
				lore.addAll(Arrays.asList("",ChatColor.RED + "You Can Get [Ancient Armor] From Nether Boss", ChatColor.RED + "Or Reaper's Shop"));
				imeta.setLore(lore);
				ii.setType(Material.LIGHT_GRAY_GLAZED_TERRACOTTA);
				imeta.setDisplayName(ChatColor.GOLD +"Core of Wilderness");
				imeta.setLocalizedName(ChatColor.GOLD +"Core of Wilderness");
			}
			else if(i == 6) {
				List<String> lore = imeta.getLore();
				lore.addAll(Arrays.asList("",ChatColor.GOLD + "If You Smith [Natural Weapon]", ChatColor.GOLD + "You'll get [Frost Weapon]"));
				lore.addAll(Arrays.asList("",ChatColor.GOLD + "If You Smith [Ancient Armor]", ChatColor.GOLD + "You'll get [Frost Armor]"));
				lore.addAll(Arrays.asList("",ChatColor.RED + "You Can Get [Ancient Armor] From Nether Boss", ChatColor.RED + "Or Reaper's Shop"));
				imeta.setLore(lore);
				ii.setType(Material.LIGHT_BLUE_GLAZED_TERRACOTTA);
				imeta.setDisplayName(ChatColor.GOLD +"Core of Everfrost");
				imeta.setLocalizedName(ChatColor.GOLD +"Core of Everfrost");
			}
			else if(i == 7) {
				List<String> lore = imeta.getLore();
				lore.addAll(Arrays.asList("",ChatColor.GOLD + "If You Smith [Natural Weapon]", ChatColor.GOLD + "You'll get [Ocean Weapon]"));
				lore.addAll(Arrays.asList("",ChatColor.GOLD + "If You Smith [Ancient Armor]", ChatColor.GOLD + "You'll get [Ocean Armor]"));
				lore.addAll(Arrays.asList("",ChatColor.RED + "You Can Get [Ancient Armor] From Nether Boss", ChatColor.RED + "Or Reaper's Shop"));
				imeta.setLore(lore);
				ii.setType(Material.BLUE_GLAZED_TERRACOTTA);
				imeta.setDisplayName(ChatColor.GOLD +"Core of Pacific");
				imeta.setLocalizedName(ChatColor.GOLD +"Core of Pacific");
			}
			else if(i == 8) {
				List<String> lore = imeta.getLore();
				lore.addAll(Arrays.asList("",ChatColor.GOLD + "If You Smith [Natural Weapon]", ChatColor.GOLD + "You'll get [Dark Weapon]"));
				lore.addAll(Arrays.asList("",ChatColor.GOLD + "If You Smith [Ancient Armor]", ChatColor.GOLD + "You'll get [Dark Armor]"));
				lore.addAll(Arrays.asList("",ChatColor.RED + "You Can Get [Ancient Armor] From Nether Boss", ChatColor.RED + "Or Reaper's Shop"));
				imeta.setLore(lore);
				ii.setType(Material.BROWN_GLAZED_TERRACOTTA);
				imeta.setDisplayName(ChatColor.GOLD +"Core of Night");
				imeta.setLocalizedName(ChatColor.GOLD +"Core of Night");
			}
			else if(i == 9) {
				List<String> lore = imeta.getLore();
				lore.addAll(Arrays.asList("",ChatColor.GOLD + "If You Smith [Natural Weapon]", ChatColor.GOLD + "You'll get [Hyper Weapon]"));
				lore.addAll(Arrays.asList("",ChatColor.GOLD + "If You Smith [Ancient Armor]", ChatColor.GOLD + "You'll get [Hyper Armor]"));
				lore.addAll(Arrays.asList("",ChatColor.RED + "You Can Get [Ancient Armor] From Nether Boss", ChatColor.RED + "Or Reaper's Shop"));
				imeta.setLore(lore);
				ii.setType(Material.WHITE_GLAZED_TERRACOTTA);
				imeta.setDisplayName(ChatColor.GOLD +"Enhanced Hyper Engine");
				imeta.setLocalizedName(ChatColor.GOLD +"Enhanced Hyper Engine");
			}
			else if(i == 10) {
				ii.setType(Material.RED_GLAZED_TERRACOTTA);
				List<String> lore = imeta.getLore();
				lore.addAll(Arrays.asList("",ChatColor.GOLD + "If You Smith [Natural Weapon]", ChatColor.GOLD + "You'll get [Burning Weapon]"));
				lore.addAll(Arrays.asList("",ChatColor.GOLD + "If You Smith [Ancient Armor]", ChatColor.GOLD + "You'll get [Burning Armor]"));
				lore.addAll(Arrays.asList("",ChatColor.RED + "You Can Get [Ancient Armor] From Nether Boss", ChatColor.RED + "Or Reaper's Shop"));
				imeta.setLore(lore);
				imeta.setDisplayName(ChatColor.GOLD +"Solar Heart");
				imeta.setLocalizedName(ChatColor.GOLD +"Solar Heart");
			}
			else if(i == 11) {
				ii.setType(Material.LIME_GLAZED_TERRACOTTA);
				List<String> lore = imeta.getLore();
				lore.addAll(Arrays.asList("",ChatColor.GOLD + "If You Smith [Natural Weapon]", ChatColor.GOLD + "You'll get [Poison Weapon]"));
				lore.addAll(Arrays.asList("",ChatColor.GOLD + "If You Smith [Ancient Armor]", ChatColor.GOLD + "You'll get [Poison Armor]"));
				lore.addAll(Arrays.asList("",ChatColor.RED + "You Can Get [Ancient Armor] From Nether Boss", ChatColor.RED + "Or Reaper's Shop"));
				imeta.setLore(lore);
				imeta.setDisplayName(ChatColor.GOLD +"Core of Venom");
				imeta.setLocalizedName(ChatColor.GOLD +"Core of Venom");
			}
			else if(i == 12) {
				ii.setType(Material.BLACK_GLAZED_TERRACOTTA);
				imeta.setLore(Arrays.asList("You can buy Strong Core from [Archaeologist]","(Archaeologist Locates on the Jungle Pyramid)"));
				imeta.setDisplayName(ChatColor.GOLD +"강력한 원소의 기운");
				imeta.setLocalizedName(ChatColor.GOLD +"강력한 원소의 기운");
			}
			else if(i == -2 || i == -3 || 3==-4||i==-5) {
				ii.setType(Material.ANCIENT_DEBRIS);
				imeta.setLore(Arrays.asList("Used For Smithing [ChestPlate, Helmet](Unstackable)","",
						ChatColor.GRAY +"+15% Damage, +10 Luck", "", "Luck Affect To Decreasing Skill's Cooldown", "Except Ult"));
				imeta.setDisplayName(ChatColor.GOLD +"Converged NetherCore");
				imeta.setLocalizedName(ChatColor.GOLD +"Converged NetherCore");
			}
			else if(i == -6 || i ==-7) {
				ii.setType(Material.END_CRYSTAL);
				imeta.setLore(Arrays.asList("Used For Smithing [Leggings, Boots](Unstackable)","",
						ChatColor.GRAY +"+20% Damage, +15 Luck, +20% Speed", "", "Luck Affect To Decreasing Skill's Cooldown", "Except Ult"));
				imeta.setDisplayName(ChatColor.GOLD +"Converged EnderCore");
				imeta.setLocalizedName(ChatColor.GOLD +"Converged EnderCore");
			}
			imeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
			ii.setItemMeta(imeta);
		}
		ii.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
		
		return ii;
	}



	public static ItemStack getscroll(Player p) {
		ItemStack ii = new ItemStack(Material.MOJANG_BANNER_PATTERN);
		ItemMeta imeta = ii.getItemMeta();
		ii.addUnsafeEnchantment(Enchantment.PROTECTION_FIRE, 1);
		imeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		imeta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
		if(p.getLocale().equalsIgnoreCase("ko_kr")) {
			imeta.setLore(Arrays.asList(ChatColor.BOLD +"우클릭시 스킬포인트 1을 획득합니다"));
			imeta.setDisplayName(ChatColor.GOLD +"신비로운 두루마리");
			imeta.setLocalizedName(ChatColor.GOLD +"Mysterious scroll");
		}
		else {
			imeta.setLore(Arrays.asList(ChatColor.BOLD +"Get 1 SkillPoint By RightClick"));
			imeta.setDisplayName(ChatColor.GOLD +"Mysterious scroll");
			imeta.setLocalizedName(ChatColor.GOLD +"Mysterious scroll");
		}
		ii.setItemMeta(imeta);
		return ii;
	}

	private void bedcheck(ItemStack bc, Block b, Player p) {
		if((p.getInventory().getItemInMainHand().hasItemMeta() && p.getInventory().getItemInMainHand().getItemMeta().hasLocalizedName())){
			final String ln = p.getInventory().getItemInMainHand().getItemMeta().getLocalizedName();
			if(ln.contains("Teleporter") || ln.contains("순간이동기")){
				ItemMeta bm =  p.getInventory().getItemInMainHand().getItemMeta();
				String ll = bm.getLocalizedName().split(" ")[0];
				b.setMetadata(ChatColor.stripColor(ll) +"RaidPortal", new FixedMetadataValue(RMain.getInstance(), true));
			}
		}
	}
	
	@EventHandler
	public void PlaceCancel(BlockPlaceEvent d) 
	{
		bedcheck(d.getPlayer().getInventory().getItemInMainHand(), d.getBlock(), d.getPlayer());
		if(d.getBlockPlaced().getDrops().stream().anyMatch(b -> b.hasItemMeta()&&b.getItemMeta().hasCustomModelData())){
			d.setCancelled(true);
		}
		if(d.getPlayer().getInventory().getItemInMainHand().hasItemMeta() && d.getPlayer().getInventory().getItemInMainHand().getItemMeta().hasCustomModelData()){
			d.setCancelled(true);
		}
	}

	private int eck(CraftingInventory inv, Integer cmdt) 
	{
		if(inv.getItem(5) != null && inv.getItem(5).getAmount() >=64 && inv.getItem(5).hasItemMeta() && inv.getItem(5).getItemMeta().hasCustomModelData() && inv.getItem(5).getItemMeta().getCustomModelData() == cmdt){
			return 5;
		}
		else if(inv.getItem(1) != null && inv.getItem(1).getAmount() >=64 && inv.getItem(1).hasItemMeta() && inv.getItem(1).getItemMeta().hasCustomModelData() && inv.getItem(1).getItemMeta().getCustomModelData() == cmdt){
			return 1;
		}
		else if(inv.getItem(2) != null && inv.getItem(2).getAmount() >=64 && inv.getItem(2).hasItemMeta() && inv.getItem(2).getItemMeta().hasCustomModelData() && inv.getItem(2).getItemMeta().getCustomModelData() == cmdt){
			return 2;
		}
		else if(inv.getItem(3) != null && inv.getItem(3).getAmount() >=64 && inv.getItem(3).hasItemMeta() && inv.getItem(3).getItemMeta().hasCustomModelData() && inv.getItem(3).getItemMeta().getCustomModelData() == cmdt){
			return 3;
		}
		else if(inv.getItem(4) != null && inv.getItem(4).getAmount() >=64 && inv.getItem(4).hasItemMeta() && inv.getItem(4).getItemMeta().hasCustomModelData() && inv.getItem(4).getItemMeta().getCustomModelData() == cmdt){
			return 4;
		}
		else if(inv.getItem(6) != null && inv.getItem(6).getAmount() >=64 && inv.getItem(6).hasItemMeta() && inv.getItem(6).getItemMeta().hasCustomModelData() && inv.getItem(6).getItemMeta().getCustomModelData() == cmdt){
			return 6;
		}
		else if(inv.getItem(7) != null && inv.getItem(7).getAmount() >=64 && inv.getItem(7).hasItemMeta() && inv.getItem(7).getItemMeta().hasCustomModelData() && inv.getItem(7).getItemMeta().getCustomModelData() == cmdt){
			return 7;
		}
		else if(inv.getItem(8) != null && inv.getItem(8).getAmount() >=64 && inv.getItem(8).hasItemMeta() && inv.getItem(8).getItemMeta().hasCustomModelData() && inv.getItem(8).getItemMeta().getCustomModelData() == cmdt){
			return 8;
		}
		else if(inv.getItem(9) != null && inv.getItem(9).getAmount() >=64 && inv.getItem(9).hasItemMeta() && inv.getItem(9).getItemMeta().hasCustomModelData() && inv.getItem(9).getItemMeta().getCustomModelData() == cmdt){
			return 9;
		}
		else {
			return -1;
		}
	}

	@EventHandler
	public void PICE(PrepareItemCraftEvent d) 
	{
		Player p = (Player) d.getView().getPlayer();
			if(eck(d.getInventory(), 114)>0) {
				d.getInventory().setResult(getelcore(14, p));
			}
			else if(eck(d.getInventory(), 105)>0) {
				d.getInventory().setResult(getelcore(5, p));
			}
			else if(eck(d.getInventory(), 106)>0) {
				d.getInventory().setResult(getelcore(6,p));
			}
			else if(eck(d.getInventory(), 107)>0) {
				d.getInventory().setResult(getelcore(7,p));
			}
			else if(eck(d.getInventory(), 108)>0) {
				d.getInventory().setResult(getelcore(8,p));
			}
			else if(eck(d.getInventory(), 109)>0) {
				d.getInventory().setResult(getelcore(9,p));
			}
			else if(eck(d.getInventory(), 110)>0) {
				d.getInventory().setResult(getelcore(10,p));
			}
			else if(eck(d.getInventory(), 111)>0) {
				d.getInventory().setResult(getelcore(11,p));
			}
			else if(eck(d.getInventory(), 112)>0) {
				d.getInventory().setResult(getelcore(12,p));
			}
			else if(eck(d.getInventory(), -102)>0) {
				d.getInventory().setResult(getelcore(-2,p));
			}
			else if(eck(d.getInventory(), -103)>0) {
				d.getInventory().setResult(getelcore(-3,p));
			}
			else if(eck(d.getInventory(), -104)>0) {
				d.getInventory().setResult(getelcore(-4,p));
			}
			else if(eck(d.getInventory(), -105)>0) {
				d.getInventory().setResult(getelcore(-5,p));
			}
			else if(eck(d.getInventory(), -106)>0) {
				d.getInventory().setResult(getelcore(-6,p));
			}
			else if(eck(d.getInventory(), -107)>0) {
				d.getInventory().setResult(getelcore(-7,p));
			}
			else if(eck(d.getInventory(), -108)>0) {
				d.getInventory().setResult(getelcore(-8,p));
			}

	}


	@EventHandler
	public void ICE(InventoryClickEvent d) 
	{
		if(d.getClickedInventory() == null)
		{
			return;
		}
		if(d.getClickedInventory().getType() == InventoryType.WORKBENCH || d.getClickedInventory().getType() == InventoryType.CRAFTING) {
			CraftingInventory ci = (CraftingInventory) d.getClickedInventory();
			Player p = (Player) d.getWhoClicked();
				if(eck(ci, 114)>0) {
					if(d.getCurrentItem().hasItemMeta() && d.getCurrentItem().getItemMeta().hasCustomModelData() && d.getCurrentItem().getItemMeta().getCustomModelData() == 14) {
						d.setCancelled(true);
						if(p.getInventory().firstEmpty() != -1) {
						p.getInventory().addItem(getelcore(14, p));
						ci.clear(eck(ci, 114));
						ci.setResult(null);
						}
					}
				}
				else if(eck(ci, 105)>0) {
					if(d.getCurrentItem().hasItemMeta() && d.getCurrentItem().getItemMeta().hasCustomModelData() && d.getCurrentItem().getItemMeta().getCustomModelData() == 5) {
						d.setCancelled(true);
						if(p.getInventory().firstEmpty() != -1) {
						p.getInventory().addItem(getelcore(5, p));
						ci.clear(eck(ci, 105));
						ci.setResult(null);
						}
					}
				}
				else if(eck(ci, 106)>0) {
					if(d.getCurrentItem().hasItemMeta() && d.getCurrentItem().getItemMeta().hasCustomModelData() && d.getCurrentItem().getItemMeta().getCustomModelData() == 6) {
						d.setCancelled(true);
						if(p.getInventory().firstEmpty() != -1) {
						p.getInventory().addItem(getelcore(6, p));
						ci.clear(eck(ci, 106));
						ci.setResult(null);
						}
					}
				}
				else if(eck(ci, 107)>0) {
					if(d.getCurrentItem().hasItemMeta() && d.getCurrentItem().getItemMeta().hasCustomModelData() && d.getCurrentItem().getItemMeta().getCustomModelData() == 7) {
						d.setCancelled(true);
						if(p.getInventory().firstEmpty() != -1) {
						p.getInventory().addItem(getelcore(7, p));
						ci.clear(eck(ci, 107));
						ci.setResult(null);
						}
					}
				}
				else if(eck(ci, 108)>0) {
					if(d.getCurrentItem().hasItemMeta() && d.getCurrentItem().getItemMeta().hasCustomModelData() && d.getCurrentItem().getItemMeta().getCustomModelData() == 8) {
						d.setCancelled(true);
						if(p.getInventory().firstEmpty() != -1) {
						p.getInventory().addItem(getelcore(8, p));
						ci.clear(eck(ci, 108));
						ci.setResult(null);
						}
					}
				}
				else if(eck(ci, 109)>0) {
					if(d.getCurrentItem().hasItemMeta() && d.getCurrentItem().getItemMeta().hasCustomModelData() && d.getCurrentItem().getItemMeta().getCustomModelData() == 9) {
						d.setCancelled(true);
						if(p.getInventory().firstEmpty() != -1) {
						p.getInventory().addItem(getelcore(9, p));
						ci.clear(eck(ci, 109));
						ci.setResult(null);
						}
					}
				}
				else if(eck(ci, 110)>0) {
					if(d.getCurrentItem().hasItemMeta() && d.getCurrentItem().getItemMeta().hasCustomModelData() && d.getCurrentItem().getItemMeta().getCustomModelData() == 10) {
						d.setCancelled(true);
						if(p.getInventory().firstEmpty() != -1) {
						p.getInventory().addItem(getelcore(10, p));
						ci.clear(eck(ci, 110));
						ci.setResult(null);
						}
					}
				}
				else if(eck(ci, 111)>0) {
					if(d.getCurrentItem().hasItemMeta() && d.getCurrentItem().getItemMeta().hasCustomModelData() && d.getCurrentItem().getItemMeta().getCustomModelData() == 11) {
						d.setCancelled(true);
						if(p.getInventory().firstEmpty() != -1) {
						p.getInventory().addItem(getelcore(11, p));
						ci.clear(eck(ci, 111));
						ci.setResult(null);
						}
					}
				}
				else if(eck(ci, 112)>0) {
					if(d.getCurrentItem().hasItemMeta() && d.getCurrentItem().getItemMeta().hasCustomModelData() && d.getCurrentItem().getItemMeta().getCustomModelData() == 12) {
						d.setCancelled(true);
						if(p.getInventory().firstEmpty() != -1) {
						p.getInventory().addItem(getelcore(12, p));
						ci.clear(eck(ci, 112));
						ci.setResult(null);
						}
					}
				}
				else if(eck(ci, -102)>0) {
					if(d.getCurrentItem().hasItemMeta() && d.getCurrentItem().getItemMeta().hasCustomModelData() && d.getCurrentItem().getItemMeta().getCustomModelData() == -2) {
						d.setCancelled(true);
						if(p.getInventory().firstEmpty() != -1) {
						p.getInventory().addItem(getelcore(-2, p));
						ci.clear(eck(ci, -102));
						ci.setResult(null);
						}
					}
				}
				else if(eck(ci, -103)>0) {
					if(d.getCurrentItem().hasItemMeta() && d.getCurrentItem().getItemMeta().hasCustomModelData() && d.getCurrentItem().getItemMeta().getCustomModelData() == -3) {
						d.setCancelled(true);
						if(p.getInventory().firstEmpty() != -1) {
						p.getInventory().addItem(getelcore(-3, p));
						ci.clear(eck(ci, -103));
						ci.setResult(null);
						}
					}
				}
				else if(eck(ci, -104)>0) {
					if(d.getCurrentItem().hasItemMeta() && d.getCurrentItem().getItemMeta().hasCustomModelData() && d.getCurrentItem().getItemMeta().getCustomModelData() == -4) {
						d.setCancelled(true);
						if(p.getInventory().firstEmpty() != -1) {
						p.getInventory().addItem(getelcore(-4, p));
						ci.clear(eck(ci, -104));
						ci.setResult(null);
						}
					}
				}
				else if(eck(ci, -105)>0) {
					if(d.getCurrentItem().hasItemMeta() && d.getCurrentItem().getItemMeta().hasCustomModelData() && d.getCurrentItem().getItemMeta().getCustomModelData() == -5) {
						d.setCancelled(true);
						if(p.getInventory().firstEmpty() != -1) {
						p.getInventory().addItem(getelcore(-2, p));
						ci.clear(eck(ci, -105));
						ci.setResult(null);
						}
					}
				}
				else if(eck(ci, -106)>0) {
					if(d.getCurrentItem().hasItemMeta() && d.getCurrentItem().getItemMeta().hasCustomModelData() && d.getCurrentItem().getItemMeta().getCustomModelData() == -6) {
						d.setCancelled(true);
						if(p.getInventory().firstEmpty() != -1) {
						p.getInventory().addItem(getelcore(-6, p));
						ci.clear(eck(ci, -106));
						ci.setResult(null);
						}
					}
				}
				else if(eck(ci, -107)>0) {
					if(d.getCurrentItem().hasItemMeta() && d.getCurrentItem().getItemMeta().hasCustomModelData() && d.getCurrentItem().getItemMeta().getCustomModelData() == -7) {
						d.setCancelled(true);
						if(p.getInventory().firstEmpty() != -1) {
						p.getInventory().addItem(getelcore(-7, p));
						ci.clear(eck(ci, -107));
						ci.setResult(null);
						}
					}
				}
				else if(eck(ci, -108)>0) {
					if(d.getCurrentItem().hasItemMeta() && d.getCurrentItem().getItemMeta().hasCustomModelData() && d.getCurrentItem().getItemMeta().getCustomModelData() == -8) {
						d.setCancelled(true);
						if(p.getInventory().firstEmpty() != -1) {
						p.getInventory().addItem(getelcore(-8, p));
						ci.clear(eck(ci, -108));
						ci.setResult(null);
						}
					}
				}
		}
		

		if(ChatColor.stripColor(d.getView().getTitle()).equalsIgnoreCase("Elements"))
		{
			d.setCancelled(true);
			if(d.getCurrentItem()==null||d.getCurrentItem().getType() == null|| !d.getCurrentItem().hasItemMeta())
			{
				d.setCancelled(false);
			}
			else
			{
				Player p = (Player) d.getWhoClicked();
				ItemStack ci = d.getCurrentItem();
				if(d.getClick().equals(ClickType.LEFT) && d.getClickedInventory() == d.getView().getTopInventory()) {
					d.setCancelled(true);
					p.getInventory().addItem(ci);
				}
				else if(d.getClick().equals(ClickType.SHIFT_LEFT) && d.getClickedInventory() == d.getView().getTopInventory()) {
					d.setCancelled(true);
					ci.setAmount(64);
					p.getInventory().addItem(ci);
				}
				
			}
		}
	}
	

	public static void itemset(ItemStack is,int loc, Inventory inv)
	{
		ItemStack item = is;
		ItemMeta items = item.getItemMeta();
		items.setDisplayName(is.getItemMeta().getDisplayName());
		items.removeItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		items.removeItemFlags(ItemFlag.HIDE_ENCHANTS);
		items.setLore(is.getItemMeta().getLore());
		item.setItemMeta(items);
		inv.setItem(loc, item);
	}
	
	public static void ElementsInv(Player p)
	{
		Inventory ElementsInv = Bukkit.createInventory(null, 54, "Elements");
		itemset(getel(14,p), 0, ElementsInv);
		itemset(getel(5,p), 1, ElementsInv);
		itemset(getel(6,p), 2, ElementsInv);
		itemset(getel(7,p), 3, ElementsInv);
		itemset(getel(8,p), 4, ElementsInv);
		itemset(getel(9,p), 5, ElementsInv);
		itemset(getel(10,p), 6, ElementsInv);
		itemset(getel(11,p), 7, ElementsInv);
		itemset(getel(12,p), 8, ElementsInv);
		itemset(getel(-2,p), 9, ElementsInv);
		itemset(getel(-3,p), 10, ElementsInv);
		itemset(getel(-4,p), 11, ElementsInv);
		itemset(getel(-5,p), 12, ElementsInv);
		itemset(getel(-6,p), 13, ElementsInv);
		itemset(getel(-7,p), 14, ElementsInv);
		itemset(getel(-8,p), 15, ElementsInv);

		itemset(getelcore(14,p), 18, ElementsInv);
		itemset(getelcore(5,p), 19, ElementsInv);
		itemset(getelcore(6,p), 20, ElementsInv);
		itemset(getelcore(7,p), 21, ElementsInv);
		itemset(getelcore(8,p), 22, ElementsInv);
		itemset(getelcore(9,p), 23, ElementsInv);
		itemset(getelcore(10,p), 24, ElementsInv);
		itemset(getelcore(11,p), 25, ElementsInv);
		itemset(getelcore(12,p), 26, ElementsInv);
		itemset(getelcore(-2,p), 27, ElementsInv);
		itemset(getelcore(-3,p), 28, ElementsInv);
		itemset(getelcore(-4,p), 29, ElementsInv);
		itemset(getelcore(-5,p), 30, ElementsInv);
		itemset(getelcore(-6,p), 31, ElementsInv);
		itemset(getelcore(-7,p), 32, ElementsInv);
		itemset(getelcore(-8,p), 33, ElementsInv);

		itemset(getstel(14,p), 37, ElementsInv);
		itemset(getstel(5,p), 38, ElementsInv);
		itemset(getstel(6,p), 39, ElementsInv);
		itemset(getstel(7,p), 40, ElementsInv);
		itemset(getstel(8,p), 41, ElementsInv);
		itemset(getstel(9,p), 42, ElementsInv);
		itemset(getstel(10,p), 43, ElementsInv);
		itemset(getstel(11,p), 44, ElementsInv);
		itemset(getstel(-2,p), 45, ElementsInv);
		itemset(getstel(-6,p), 46, ElementsInv);
		
		p.openInventory(ElementsInv);
	}

	
	public static void BedInv(Player p)
	{
		Inventory ElementsInv = Bukkit.createInventory(null, 36, "Beds");
		itemset(getpor(5, p), 0, ElementsInv);
		itemset(getpor(6, p), 1, ElementsInv);
		itemset(getpor(7, p), 2, ElementsInv);
		itemset(getpor(8,p), 3, ElementsInv);
		itemset(getpor(9,p), 4, ElementsInv);
		itemset(getpor(10,p), 5, ElementsInv);
		itemset(getpor(12,p), 6, ElementsInv);
		itemset(getpor(-2,p), 9, ElementsInv);
		itemset(getpor(-3,p), 10, ElementsInv);
		itemset(getpor(-4,p), 11, ElementsInv);
		itemset(getpor(-5,p), 12, ElementsInv);
		itemset(getpor(-6,p), 13, ElementsInv);
		itemset(getpor(-7,p), 14, ElementsInv);
		itemset(getpor(-8,p), 15, ElementsInv);
		
		
		p.openInventory(ElementsInv);
	}

	
	
}
