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
			itemset("���", Material.CANDLE, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+tsd.Aura.getOrDefault(p.getUniqueId(), 0),"","��ũ���� + �����۹ٲٱ�(���콺��)"
					,ChatColor.UNDERLINE+"[ȭ�� �迭, ���� �迭]","���Ǳ��: ��Ƽ������ ���ݷ�(30%), �ӵ� ����","",ChatColor.UNDERLINE+"[���� �迭, ��� �迭]","���Ǳ��: ","��Ƽ������ ���� 30%����","�� ���ݽ� ��Ƽ�� ü�� ȸ��", "Master Lv.1"), 0, Taoskillsinv);
			itemset("����", Material.BLACK_CANDLE, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+tsd.Imagery.getOrDefault(p.getUniqueId(), 0),"","�չٲٱ�","",ChatColor.BOLD+" X (0.7D + "+BigDecimal.valueOf(tsd.Imagery.getOrDefault(p.getUniqueId(), 0)*0.9).setScale(2, RoundingMode.HALF_EVEN)+")", "Master Lv.50"), 1, Taoskillsinv);
			itemset("�����", Material.YELLOW_CANDLE, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+tsd.Wave.getOrDefault(p.getUniqueId(), 0),"","��Ŭ��","",ChatColor.BOLD+" X (0.67D + "+BigDecimal.valueOf(tsd.Wave.getOrDefault(p.getUniqueId(), 0)*0.64).setScale(2, RoundingMode.HALF_EVEN)+")" ,"Master LV.50"), 2, Taoskillsinv);
			itemset("����", Material.BROWN_CANDLE, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+tsd.Amplify.getOrDefault(p.getUniqueId(), 0),"","��ũ���� + ��Ŭ��","����� ������" + BigDecimal.valueOf(tsd.Amplify.getOrDefault(p.getUniqueId(), 0)*0.1).setScale(2, RoundingMode.HALF_EVEN) + "�ʰ� ������ŵ�ϴ�",
					"���Ǳ��: �ֺ����� �����մϴ�",ChatColor.BOLD+" X (0.2D + "+BigDecimal.valueOf(tsd.Amplify.getOrDefault(p.getUniqueId(), 0)*0.2).setScale(2, RoundingMode.HALF_EVEN)+")","",
					"���Ǳ��: ��Ƽ������ ��� ȸ���մϴ�" ,"Master LV.50"), 3, Taoskillsinv);
			itemset("����", Material.BLACK_BANNER, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+tsd.Charm.getOrDefault(p.getUniqueId(), 0),"","�չٲٱ� + ��ũ����","",
					ChatColor.BOLD+"6 X (0.315D + "+BigDecimal.valueOf(tsd.Charm.getOrDefault(p.getUniqueId(), 0)*0.3455).setScale(2, RoundingMode.HALF_EVEN)+"), "+
					ChatColor.BOLD+" X (1.37D + "+BigDecimal.valueOf(tsd.Charm.getOrDefault(p.getUniqueId(), 0)*1.1).setScale(2, RoundingMode.HALF_EVEN)+"), ",
							"" ,"Master LV.50"), 4, Taoskillsinv);
			itemset("��������", Material.BLUE_BANNER, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+tsd.Flip.getOrDefault(p.getUniqueId(), 0),"","���� + ��Ŭ��",
					"",ChatColor.BOLD+" X (0.3D + "+BigDecimal.valueOf(tsd.Flip.getOrDefault(p.getUniqueId(), 0)*0.34).setScale(2, RoundingMode.HALF_EVEN)+")" ,"Master LV.50"), 5, Taoskillsinv);
			itemset("���ؼҺ�", Material.RED_CANDLE, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+tsd.CombustInside.getOrDefault(p.getUniqueId(), 0),"","��ũ���� + ��������",
					"",ChatColor.BOLD+" X (1.8D + "+BigDecimal.valueOf(tsd.CombustInside.getOrDefault(p.getUniqueId(), 0)*1.8).setScale(2, RoundingMode.HALF_EVEN)+")" ,"Master LV.50"), 6, Taoskillsinv);
			itemset("����", Material.BOOK, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+tsd.Vibe.getOrDefault(p.getUniqueId(), 0),"","���ݷ��� �����մϴ�","",ChatColor.BOLD+" + "+BigDecimal.valueOf(tsd.Vibe.getOrDefault(p.getUniqueId(), 0)*0.6).setScale(2, RoundingMode.HALF_EVEN)), 7, Taoskillsinv);
			if(Proficiency.getpro(p)<1) {
				itemset("�������(���)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/29315"), 9, Taoskillsinv);
				itemset("������(���)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/29315"), 10, Taoskillsinv);
				itemset("�����(���)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/29315"), 11, Taoskillsinv);
				itemset("���(���)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/29315"), 12, Taoskillsinv);
				itemset("�����ǿ�����(���)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/29315"), 13, Taoskillsinv);
				itemset("����(���)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/29315"), 14, Taoskillsinv);
				itemset("����(���)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/29315"), 15, Taoskillsinv);
				itemset("����(���)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/29315"), 16, Taoskillsinv);
				itemset("���(���)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/29315"), 17, Taoskillsinv);
			}
			else if(Proficiency.getpro(p)<2 && Proficiency.getpro(p)>=1) {
				itemset("�������", Material.SOUL_CAMPFIRE, 0, 1, Arrays.asList("����� ���� ȿ���� �ι�� ������ŵ�ϴ�"), 9, Taoskillsinv);
				itemset("������", Material.FIREWORK_STAR, 0, 1, Arrays.asList("���Է½� �������� ����մϴ�","",ChatColor.BOLD+" X (1.4D + "+BigDecimal.valueOf(tsd.Imagery.getOrDefault(p.getUniqueId(), 0)*1.5).setScale(2, RoundingMode.HALF_EVEN)+")","(���ط��� ���� ������ ����մϴ�)"), 10, Taoskillsinv);
				itemset("�����", Material.DIAMOND_SWORD, 0, 1, Arrays.asList("���Է½� ������� ����մϴ�","",ChatColor.BOLD+"2 X (0.68D + "+BigDecimal.valueOf(tsd.Wave.getOrDefault(p.getUniqueId(), 0)*0.76).setScale(2, RoundingMode.HALF_EVEN)+")","(���ط��� ����� ������ ����մϴ�)"), 11, Taoskillsinv);
				itemset("���", Material.GREEN_CANDLE, 0, 1, Arrays.asList("���Է½� ����� ����մϴ�",
						"���Ǳ��: �ֺ����� ���� �� �����մϴ�","",ChatColor.BOLD+" X (0.6D + "+BigDecimal.valueOf(tsd.Amplify.getOrDefault(p.getUniqueId(), 0)*0.8).setScale(2, RoundingMode.HALF_EVEN)+")",
						"���Ǳ��: ��Ƽ������ �������·� ����ϴ�","����: �طο� ȿ������ �����մϴ�","(���ط��� ���ӽð��� ���� ������ ����մϴ�)"), 12, Taoskillsinv);
				itemset("�����ǿ�����", Material.COARSE_DIRT, 0, 1, Arrays.asList("���Է½� ������� ����մϴ�","",ChatColor.BOLD+"10 X (0.115D + "+BigDecimal.valueOf(tsd.Charm.getOrDefault(p.getUniqueId(), 0)*0.2455).setScale(2, RoundingMode.HALF_EVEN)+")","(���ط��� ���� ������ ����մϴ�)"), 13, Taoskillsinv);
				itemset("����", Material.NETHERITE_BOOTS, 0, 1, Arrays.asList("���Է½� ������� ����մϴ�","",ChatColor.BOLD+" X (0.9D + "+BigDecimal.valueOf(tsd.Flip.getOrDefault(p.getUniqueId(), 0)*0.9).setScale(2, RoundingMode.HALF_EVEN)+")","(���ط��� �������� ������ ����մϴ�)"), 14, Taoskillsinv);
				itemset("����", Material.BELL, 0, 1, Arrays.asList("�������ظ� �����մϴ�"), 15, Taoskillsinv);
				itemset("����", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("���ݷ��� �����մϴ�"), 16, Taoskillsinv);
				itemset("���", Material.WRITTEN_BOOK, 0, 1, Arrays.asList("��ũ���� + �����۴�����","�ֺ� ��Ƽ������ ġ���մϴ�","",ChatColor.BOLD+"60 X 0.3D"), 17, Taoskillsinv);

				itemset("��õ(���)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/155015"), 19, Taoskillsinv);
				itemset("�����(���)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/155015"), 20, Taoskillsinv);
				itemset("��Ʈ��(���)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/155015"), 21, Taoskillsinv);
				itemset("�����(���)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/155015"), 22, Taoskillsinv);
				itemset("�ʿ�(���)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/155015"), 25, Taoskillsinv);
				itemset("�����ȱ���(���)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/155015"), 26, Taoskillsinv);
			}
			else {
				itemset("�������", Material.SOUL_CAMPFIRE, 0, 1, Arrays.asList("����� ���� ȿ���� �ι�� ������ŵ�ϴ�"), 9, Taoskillsinv);
				itemset("������", Material.FIREWORK_STAR, 0, 1, Arrays.asList("���Է½� �������� ����մϴ�","",ChatColor.BOLD+" X (1.4D + "+BigDecimal.valueOf(tsd.Imagery.getOrDefault(p.getUniqueId(), 0)*1.5).setScale(2, RoundingMode.HALF_EVEN)+")","(���ط��� ���� ������ ����մϴ�)"), 10, Taoskillsinv);
				itemset("�����", Material.DIAMOND_SWORD, 0, 1, Arrays.asList("���Է½� ������� ����մϴ�","",ChatColor.BOLD+"2 X (0.68D + "+BigDecimal.valueOf(tsd.Wave.getOrDefault(p.getUniqueId(), 0)*0.76).setScale(2, RoundingMode.HALF_EVEN)+")","(���ط��� ����� ������ ����մϴ�)"), 11, Taoskillsinv);
				itemset("���", Material.GREEN_CANDLE, 0, 1, Arrays.asList("���Է½� ����� ����մϴ�",
						"���Ǳ��: �ֺ����� ���� �� �����մϴ�","",ChatColor.BOLD+" X (0.6D + "+BigDecimal.valueOf(tsd.Amplify.getOrDefault(p.getUniqueId(), 0)*0.8).setScale(2, RoundingMode.HALF_EVEN)+")",
						"���Ǳ��: ��Ƽ������ �������·� ����ϴ�","����: �طο� ȿ������ �����մϴ�","(���ط��� ���ӽð��� ���� ������ ����մϴ�)"), 12, Taoskillsinv);
				itemset("�����ǿ�����", Material.COARSE_DIRT, 0, 1, Arrays.asList("���Է½� ������� ����մϴ�","",ChatColor.BOLD+"10 X (0.115D + "+BigDecimal.valueOf(tsd.Charm.getOrDefault(p.getUniqueId(), 0)*0.2455).setScale(2, RoundingMode.HALF_EVEN)+")","(���ط��� ���� ������ ����մϴ�)"), 13, Taoskillsinv);
				itemset("����", Material.NETHERITE_BOOTS, 0, 1, Arrays.asList("���Է½� ������� ����մϴ�","",ChatColor.BOLD+" X (0.9D + "+BigDecimal.valueOf(tsd.Flip.getOrDefault(p.getUniqueId(), 0)*0.9).setScale(2, RoundingMode.HALF_EVEN)+")","(���ط��� �������� ������ ����մϴ�)"), 14, Taoskillsinv);
				itemset("����", Material.BELL, 0, 1, Arrays.asList("�������ظ� �����մϴ�"), 15, Taoskillsinv);
				itemset("����", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("���ݷ��� �����մϴ�"), 16, Taoskillsinv);
				itemset("���", Material.WRITTEN_BOOK, 0, 1, Arrays.asList("��ũ���� + �����۴�����","�ֺ� ��Ƽ������ ġ���մϴ�","",ChatColor.BOLD+"60 X 0.3D"), 17, Taoskillsinv);

				itemset("��õ", Material.CUT_COPPER_SLAB, 0, 1, Arrays.asList("���Է½� ��õ�� ����մϴ�","",ChatColor.BOLD+" X (1.2D + "+BigDecimal.valueOf(tsd.Imagery.getOrDefault(p.getUniqueId(), 0)*1.3).setScale(2, RoundingMode.HALF_EVEN)+")","(���ط��� ���� ������ ����մϴ�)"), 19, Taoskillsinv);
				itemset("�����", Material.BOW, 0, 1, Arrays.asList("���Է½� ������� ����մϴ�","",ChatColor.BOLD+" X (0.115D + "+BigDecimal.valueOf(tsd.Charm.getOrDefault(p.getUniqueId(), 0)*0.2455).setScale(2, RoundingMode.HALF_EVEN)+")","(���ط��� ����� ������ ����մϴ�)"), 20, Taoskillsinv);
				itemset("��Ʈ��", Material.ACACIA_LEAVES, 0, 1, Arrays.asList("���Է½� ��Ʈ�� ����մϴ�","","��� ������ �ص� 5�ʵ��� ȿ���� ���ӵ˴ϴ�","",
						"5�ʰ� ��Ƽ������ ��⸦ ä��ϴ�","",ChatColor.BOLD+" X (0.8D + "+BigDecimal.valueOf(tsd.Amplify.getOrDefault(p.getUniqueId(), 0)*0.9).setScale(2, RoundingMode.HALF_EVEN)+")","(���ط��� ���� ������ ����մϴ�)"), 21, Taoskillsinv);
				itemset("�����", Material.SOUL_CAMPFIRE, 0, 1, Arrays.asList("���Է½� ����ĸ� ����մϴ�","",ChatColor.BOLD+"12 X (0.1D + "+BigDecimal.valueOf(tsd.Charm.getOrDefault(p.getUniqueId(), 0)*0.12).setScale(2, RoundingMode.HALF_EVEN)+")","(���ط��� ���� ������ ����մϴ�)"), 22, Taoskillsinv);
				itemset("�ʿ�", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("���ݷ°� ������ �����մϴ�","����� ������ð��� �����մϴ�","��� ��ü���� ����մϴ�", "����ü�� �鿪�� �˴ϴ�"), 25, Taoskillsinv);
				itemset("�����ȱ���", Material.WRITTEN_BOOK, 0, 1, Arrays.asList("�޸��� + �����۴�����","",ChatColor.BOLD+"8 X 1.5D, 3.5D, 12.5D"), 26, Taoskillsinv);
			}
			itemset("���� ���õ�", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(""+Proficiency.getproexp(p)), 27, Taoskillsinv);
			itemset("��ų����Ʈ", Material.NETHER_STAR, 0, 1, Arrays.asList(ChatColor.AQUA+"SP."+tsd.SkillPoints.getOrDefault(p.getUniqueId(), 0),"","Ŭ���ϸ� ��ų����Ʈ�� �ʱ�ȭ �˴ϴ�"), 35, Taoskillsinv);

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
