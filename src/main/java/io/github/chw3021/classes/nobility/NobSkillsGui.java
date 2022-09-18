package io.github.chw3021.classes.nobility;



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

public class NobSkillsGui{
	


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
	
	
	public void NobSkillsinv(Player p)
	{
		Inventory Nobskillsinv = Bukkit.createInventory(null, 54, "Nobskills");
	    String path = new File("").getAbsolutePath();
		NobSkillsData fsd = new NobSkillsData(NobSkillsData.loadData(path +"/plugins/RPGskills/NobSkillsData.data"));
		Obtained.itemset(p, Nobskillsinv);
		

		if(p.getLocale().equalsIgnoreCase("ko_kr")) {
			itemset("����", Material.NAUTILUS_SHELL, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+fsd.Assault.getOrDefault(p.getUniqueId(),1),"",ChatColor.UNDERLINE+"[�� �迭]","��ũ���� + �չٲٱ�","",ChatColor.BOLD+"10 X "+BigDecimal.valueOf(0.35*(1+fsd.Assault.getOrDefault(p.getUniqueId(),1)*0.036)).setScale(2, RoundingMode.HALF_EVEN)+"D", "Master Lv.50"), 0, Nobskillsinv);
			itemset("������", Material.PRISMARINE_CRYSTALS, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+fsd.WaterWheel.getOrDefault(p.getUniqueId(),1),"",ChatColor.UNDERLINE+"[�� �迭]","�չٲٱ�","",ChatColor.BOLD+"15 X "+BigDecimal.valueOf(0.15*(1+fsd.WaterWheel.getOrDefault(p.getUniqueId(),1)*0.02)).setScale(2, RoundingMode.HALF_EVEN)+"D", "Master Lv.50"), 1, Nobskillsinv);
			itemset("��ǳ", Material.TRIDENT, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+fsd.Storm.getOrDefault(p.getUniqueId(),1),"",ChatColor.UNDERLINE+"[�� �迭]","��ũ���� + ��Ŭ��", "ǥ���� ������ ������ ��ǳ�� ����ŵ�ϴ�","",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.96*(1+fsd.Storm.getOrDefault(p.getUniqueId(),1)*0.0443)).setScale(2, RoundingMode.HALF_EVEN)+"D", "Master Lv.50"), 2, Nobskillsinv);
			itemset("�����������", Material.GUARDIAN_SPAWN_EGG, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+fsd.GuardianSupport.getOrDefault(p.getUniqueId(),1),"",ChatColor.UNDERLINE+"[�� �迭]","������ ����� �������� ������ϴ�","������ ������ �ݰ��� �մϴ�",ChatColor.BOLD+""+BigDecimal.valueOf(fsd.GuardianSupport.getOrDefault(p.getUniqueId(),1)*1).setScale(2, RoundingMode.HALF_EVEN)+" + 0.1D",
					"","������� ���ݷ��� �����ݴϴ�",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.56*(1+fsd.GuardianSupport.getOrDefault(p.getUniqueId(),1)*0.06)).setScale(2, RoundingMode.HALF_EVEN)+"D", "Master LV.20"), 3, Nobskillsinv);
			itemset("����", Material.TRIDENT, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+fsd.Transition.getOrDefault(p.getUniqueId(),1),"",ChatColor.UNDERLINE+"[�� �迭]","��ũ���� + ��ô","",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.7*(1+fsd.Transition.getOrDefault(p.getUniqueId(),1)*0.03)).setScale(2, RoundingMode.HALF_EVEN)+"D", "Master Lv.50"), 4, Nobskillsinv);
			itemset("����Ÿ��", Material.DOLPHIN_SPAWN_EGG, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+fsd.DolphinSurf.getOrDefault(p.getUniqueId(),1),"",ChatColor.UNDERLINE+"[�� �迭]","���� + ��Ŭ��", "��Ŭ���� �������� ������ �ֺ������� ���ظ� �ݴϴ�","",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.36*(1+fsd.DolphinSurf.getOrDefault(p.getUniqueId(),1)*0.03)).setScale(2, RoundingMode.HALF_EVEN)+"D", "Master Lv.50"), 5, Nobskillsinv);
			itemset("����â������", Material.HEART_OF_THE_SEA, 0, 1, Arrays.asList(ChatColor.AQUA+"�нú�","","����â�� ��ô�� �ش� ����â�� ��ſ��� ��ӵ˴ϴ�","", "[�Ǽ����� ��ũ���� + ��Ŭ��]�� ����â�� ��� �ҷ��ɴϴ�","" ,"�ѹ��� �������� ����â�� ������","�������� ���� ����â�� ��ӵ˴ϴ�","", "����â�� �ޱ������� �������°� �˴ϴ�","", "����â�� �ʹ� �ָ��ְų� ���㿡 ������","��� ���ƿɴϴ�","","����â�� ���� �����������¸�","���㿡 ������ �ʽ��ϴ�"), 6, Nobskillsinv);
			itemset("�ٴ���ǥ��", Material.BOOK, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+fsd.MarkOfSea.getOrDefault(p.getUniqueId(),1),"","���ݷ��� �����մϴ�","������� �ݻ絥������ �鿪�̵˴ϴ�", "������ ǥ���� ����ϴ�", "[��ǳ] ���� ���� ǥ���� ������ϴ�", "","����â�� ����,���� �����ο��� �մϴ�", "������ ���� �̷ο� ȿ������ ����ϴ�","",ChatColor.BOLD+" X "+BigDecimal.valueOf(1.15*(1+fsd.MarkOfSea.getOrDefault(p.getUniqueId(),1)*0.0636)).setScale(2, RoundingMode.HALF_EVEN)), 7, Nobskillsinv);
			if(Proficiency.getpro(p)<1) {
				itemset("��ġ���(���)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/29315"), 9, Nobskillsinv);
				itemset("�ع�(���)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/29315"), 10, Nobskillsinv);
				itemset("�źϿ��(���)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/29315"), 11, Nobskillsinv);
				itemset("���������(���)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/29315"), 12, Nobskillsinv);
				itemset("��Ȧ��Ʋ�д�(���)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/29315"), 13, Nobskillsinv);
				itemset("�߱���¡��(���)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/29315"), 14, Nobskillsinv);
				itemset("���(���)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/29315"), 16, Nobskillsinv);
				itemset("�ٴټҿ뵹��(���)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/29315"), 17, Nobskillsinv);
			}
			else if(Proficiency.getpro(p)<2 && Proficiency.getpro(p)>=1) {
				itemset("��ġ���", Material.TARGET, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[�� �迭]","���Է½� ������ �����մϴ�","(���ط��� ���� ������ ����մϴ�)","",ChatColor.BOLD+"6 X "+BigDecimal.valueOf(0.1*(1+fsd.Assault.getOrDefault(p.getUniqueId(),1)*0.015)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 9, Nobskillsinv);
				itemset("�ع�", Material.CONDUIT, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[�� �迭]","���Է½� ����â�� ���� �ع��մϴ�", "(���ط��� ������ ������ ����մϴ�)","",ChatColor.BOLD+"8 X "+BigDecimal.valueOf(0.16*(1+fsd.WaterWheel.getOrDefault(p.getUniqueId(),1)*0.05)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 10, Nobskillsinv);
				itemset("�źϿ��", Material.TURTLE_HELMET, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[�� �迭]","�źϿ���� ������ �۾��ϴ�", "(���ط��� ��ǳ ������ ����մϴ�)","",ChatColor.BOLD+"2 X "+BigDecimal.valueOf(0.3*(1+fsd.Storm.getOrDefault(p.getUniqueId(),1)*0.03)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 11, Nobskillsinv);
				itemset("���������", Material.ELDER_GUARDIAN_SPAWN_EGG, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[�� �迭]","�������� ������������� ��ü�մϴ�","",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.8*(1+fsd.GuardianSupport.getOrDefault(p.getUniqueId(),1)*0.06)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 12, Nobskillsinv);
				itemset("��Ȧ��Ʋ�д�", Material.AXOLOTL_BUCKET, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[�� �迭]","����â�� ��ġ�� ��Ȧ��Ʋ�д븦 �θ��ϴ�","����,�������� �켱������ �����մϴ�","(���ط��� ���� ������ ����մϴ�)","",ChatColor.BOLD+"5 X 6 X "+BigDecimal.valueOf(0.05*(1+fsd.Transition.getOrDefault(p.getUniqueId(),1)*0.016)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 13, Nobskillsinv);
				itemset("�߱���¡��", Material.GLOW_INK_SAC, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[�� �迭]","����� �߱���¡� ��ȯ�մϴ�","(���ط��� ����Ÿ�� ������ ����մϴ�)","",ChatColor.BOLD+"3 X "+BigDecimal.valueOf(0.36*(1+fsd.DolphinSurf.getOrDefault(p.getUniqueId(),1)*0.03)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 14, Nobskillsinv);
				itemset("���", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("���ݷ��� �����մϴ�", "[���� ���ұ�]�� ���°��� ȿ���� �����մϴ�", "����â�� �ӵ��� �����մϴ�"), 16, Nobskillsinv);
				itemset("�ٴټҿ뵹��", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[�� �迭]","��ũ���� + ��������ô","",ChatColor.BOLD+"10 X 0.46D + 14 X 0.8D"), 17, Nobskillsinv);

				itemset("�л����(���)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/155015"), 18, Nobskillsinv);
				itemset("�ٴ��Ǵ�(���)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/155015"), 19, Nobskillsinv);
				itemset("�������� (���)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/155015"), 20, Nobskillsinv);
				itemset("���������(���)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/155015"), 21, Nobskillsinv);
				itemset("ȣ��(���)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/155015"), 22, Nobskillsinv);
				itemset("��ġ��(���)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/155015"), 25, Nobskillsinv);
				itemset("�ٴ�����(���)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/155015"), 26, Nobskillsinv);
			}
			else {
				itemset("��ġ���", Material.TARGET, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[�� �迭]","���Է½� ������ �����մϴ�","(���ط��� ���� ������ ����մϴ�)","",ChatColor.BOLD+"6 X "+BigDecimal.valueOf(0.1*(1+fsd.Assault.getOrDefault(p.getUniqueId(),1)*0.015)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 9, Nobskillsinv);
				itemset("�ع�", Material.CONDUIT, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[�� �迭]","���Է½� ����â�� ���� �ع��մϴ�", "(���ط��� ������ ������ ����մϴ�)","",ChatColor.BOLD+"8 X "+BigDecimal.valueOf(0.16*(1+fsd.WaterWheel.getOrDefault(p.getUniqueId(),1)*0.05)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 10, Nobskillsinv);
				itemset("�źϿ��", Material.TURTLE_HELMET, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[�� �迭]","�źϿ���� ������ �۾��ϴ�", "(���ط��� ��ǳ ������ ����մϴ�)","",ChatColor.BOLD+"2 X "+BigDecimal.valueOf(0.3*(1+fsd.Storm.getOrDefault(p.getUniqueId(),1)*0.03)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 11, Nobskillsinv);
				itemset("���������", Material.ELDER_GUARDIAN_SPAWN_EGG, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[�� �迭]","�������� ������������� ��ü�մϴ�","",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.8*(1+fsd.GuardianSupport.getOrDefault(p.getUniqueId(),1)*0.06)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 12, Nobskillsinv);
				itemset("��Ȧ��Ʋ�д�", Material.AXOLOTL_BUCKET, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[�� �迭]","����â�� ��ġ�� ��Ȧ��Ʋ�д븦 �θ��ϴ�","����,�������� �켱������ �����մϴ�","(���ط��� ���� ������ ����մϴ�)","",ChatColor.BOLD+"5 X 6 X "+BigDecimal.valueOf(0.05*(1+fsd.Transition.getOrDefault(p.getUniqueId(),1)*0.016)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 13, Nobskillsinv);
				itemset("�߱���¡��", Material.GLOW_INK_SAC, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[�� �迭]","����� �߱���¡� ��ȯ�մϴ�","(���ط��� ����Ÿ�� ������ ����մϴ�)","",ChatColor.BOLD+"3 X "+BigDecimal.valueOf(0.36*(1+fsd.DolphinSurf.getOrDefault(p.getUniqueId(),1)*0.03)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 14, Nobskillsinv);
				itemset("���", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("���ݷ��� �����մϴ�", "[���� ���ұ�]�� ���°��� ȿ���� �����մϴ�", "����â�� �ӵ��� �����մϴ�"), 16, Nobskillsinv);
				itemset("�ٴټҿ뵹��", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[�� �迭]","��ũ���� + ��������ô","",ChatColor.BOLD+"10 X 0.46D + 20 X 0.5D"), 17, Nobskillsinv);

				itemset("�л����", Material.INK_SAC, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[�� �迭]","���Է½� �л������ ����մϴ�","(���ط��� ���� ������ ����մϴ�)","",ChatColor.BOLD+"6 X "+BigDecimal.valueOf(0.2*(1+fsd.Assault.getOrDefault(p.getUniqueId(),1)*0.015)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 18, Nobskillsinv);
				itemset("�ٴ��Ǵ�", Material.SEA_LANTERN, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[�� �迭]","���Է½� �ٴ��Ǵ��� �����մϴ�", "(���ط��� ������ ������ ����մϴ�)","",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.95*(1+fsd.WaterWheel.getOrDefault(p.getUniqueId(),1)*0.52)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 19, Nobskillsinv);
				itemset("��������", Material.HEART_OF_THE_SEA, 0, 1, Arrays.asList("��ǳ�� ���ð��� �������� �����մϴ�"), 20, Nobskillsinv);
				itemset("���������", Material.PRISMARINE, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[�� �迭]","[��ũ���� + �����ۺ���[���콺��]��","������� ���ָ� ����Ҽ� �ֽ��ϴ�", "(���ط��� ����������� ������ ����մϴ�)","",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.33*(1+fsd.GuardianSupport.getOrDefault(p.getUniqueId(),1)*0.056)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 21, Nobskillsinv);
				itemset("ȣ��", Material.POINTED_DRIPSTONE, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[�� �迭]","����â��ġ�� ȣ�츦 �θ��ϴ�","(���ط��� ���� ������ ����մϴ�)","",ChatColor.BOLD+"6 X "+BigDecimal.valueOf(0.2*(1+fsd.Assault.getOrDefault(p.getUniqueId(),1)*0.026)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 22, Nobskillsinv);
				itemset("��ġ��", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("���ݷ°� ������ �����մϴ�","�ٴټҿ뵹�� ������ð��� �����մϴ�"), 25, Nobskillsinv);
				itemset("�ٴٴ�����", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[�� �迭]","�޸��� + ��������ô","",ChatColor.BOLD+"200 X 0.2D"), 26, Nobskillsinv);
			}
			itemset("���� ���õ�", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(""+Proficiency.getproexp(p)), 27, Nobskillsinv);
			itemset("��ų����Ʈ", Material.NETHER_STAR, 0, 1, Arrays.asList(ChatColor.AQUA+"SP."+fsd.SkillPoints.getOrDefault(p.getUniqueId(),1),"","Ŭ���ϸ� ��ų����Ʈ�� �ʱ�ȭ �˴ϴ�"), 35, Nobskillsinv);
		
		}
		else {
			itemset("Assault", Material.NAUTILUS_SHELL, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+fsd.Assault.getOrDefault(p.getUniqueId(),1),"",ChatColor.UNDERLINE+"[Water]","Sneaking + SwapHand","",ChatColor.BOLD+"10 X "+BigDecimal.valueOf(0.35*(1+fsd.Assault.getOrDefault(p.getUniqueId(),1)*0.036)).setScale(2, RoundingMode.HALF_EVEN)+"D", "Master Lv.50"), 0, Nobskillsinv);
			itemset("WaterWheel", Material.PRISMARINE_CRYSTALS, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+fsd.WaterWheel.getOrDefault(p.getUniqueId(),1),"",ChatColor.UNDERLINE+"[Water]","SwapHand","",ChatColor.BOLD+"15 X "+BigDecimal.valueOf(0.15*(1+fsd.WaterWheel.getOrDefault(p.getUniqueId(),1)*0.02)).setScale(2, RoundingMode.HALF_EVEN)+"D", "Master Lv.50"), 1, Nobskillsinv);
			itemset("Storm", Material.TRIDENT, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+fsd.Storm.getOrDefault(p.getUniqueId(),1),"",ChatColor.UNDERLINE+"[Water]","Sneaking + LeftClick", "Call Storm to Marked Entites","",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.96*(1+fsd.Storm.getOrDefault(p.getUniqueId(),1)*0.0443)).setScale(2, RoundingMode.HALF_EVEN)+"D", "Master Lv.50"), 2, Nobskillsinv);
			itemset("GuardianSupport", Material.GUARDIAN_SPAWN_EGG, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+fsd.GuardianSupport.getOrDefault(p.getUniqueId(),1),"",ChatColor.UNDERLINE+"[Water]","Guardians will disappear", "When you change holding Item"
					,"Attack Enemies that hit you",ChatColor.BOLD+""+BigDecimal.valueOf(fsd.GuardianSupport.getOrDefault(p.getUniqueId(),1)*1).setScale(2, RoundingMode.HALF_EVEN)+" + 0.1D",
					"","Guardians Support your Attack",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.56*(1+fsd.GuardianSupport.getOrDefault(p.getUniqueId(),1)*0.06)).setScale(2, RoundingMode.HALF_EVEN)+"D", "Master LV.20"), 3, Nobskillsinv);
			itemset("Transition", Material.TRIDENT, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+fsd.Transition.getOrDefault(p.getUniqueId(),1),"",ChatColor.UNDERLINE+"[Water]","Sneaking + Throw","",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.7*(1+fsd.Transition.getOrDefault(p.getUniqueId(),1)*0.03)).setScale(2, RoundingMode.HALF_EVEN)+"D", "Master Lv.50"), 4, Nobskillsinv);
			itemset("DolphinSurf", Material.DOLPHIN_SPAWN_EGG, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+fsd.DolphinSurf.getOrDefault(p.getUniqueId(),1),"",ChatColor.UNDERLINE+"[Water]","Jump + LeftClick", "RightClick Will Quit Surfing", "Damage NearbyEntites When You Quit Surfing","",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.36*(1+fsd.DolphinSurf.getOrDefault(p.getUniqueId(),1)*0.03)).setScale(2, RoundingMode.HALF_EVEN)+"D", "Master Lv.50"), 5, Nobskillsinv);
			itemset("Owner", Material.HEART_OF_THE_SEA, 0, 1, Arrays.asList(ChatColor.AQUA+"Passive","","Trident will belong to you since you Throw it","", "You can call back earlier Trident", "Using [Sneaking + LeftClick with BareHands]","" ,"You should Throw One Trident at Once","", "Set Armor Max","Before You get back Trident","", "Trident Will Teleport To You If Trident is","Too Far Away From You or Located Void","","Prevent From Falling to Void While Trident Floating"), 6, Nobskillsinv);
			itemset("MarkOfSea", Material.BOOK, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+fsd.MarkOfSea.getOrDefault(p.getUniqueId(),1),"","Increases Damage","Immune to Guardian's Reflecting Damage", "Mark Attacked Enemies", "Remove Mark When Enemy Attacked By [Storm]", "","Give Loyalty & Channeling Enchant if You Don't Have", "Get Water Ability When Swim","",ChatColor.BOLD+" X "+BigDecimal.valueOf(1.15*(1+fsd.MarkOfSea.getOrDefault(p.getUniqueId(),1)*0.0636)).setScale(2, RoundingMode.HALF_EVEN)), 7, Nobskillsinv);
			if(Proficiency.getpro(p)<1) {
				itemset("HoldPosition(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 9, Nobskillsinv);
				itemset("Release(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 10, Nobskillsinv);
				itemset("AgentTurtle(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 11, Nobskillsinv);
				itemset("ElderGuardian(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 12, Nobskillsinv);
				itemset("AxolotlSquad(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 13, Nobskillsinv);
				itemset("GlowSquid(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 14, Nobskillsinv);
				itemset("Noble(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 16, Nobskillsinv);
				itemset("Whirlpool(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 17, Nobskillsinv);
			}
			else if(Proficiency.getpro(p)<2 && Proficiency.getpro(p)>=1) {
				itemset("HoldPosition", Material.TARGET, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Water]","Make Stop Assaulting", "When You Use Once More","(Damage Affected By Assault)","",ChatColor.BOLD+"6 X "+BigDecimal.valueOf(0.1*(1+fsd.Assault.getOrDefault(p.getUniqueId(),1)*0.015)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 9, Nobskillsinv);
				itemset("Release", Material.CONDUIT, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Water]","Release Trident Force When You Use Once More", "(Damage Affected By WaterWheel)","",ChatColor.BOLD+"8 X "+BigDecimal.valueOf(0.16*(1+fsd.WaterWheel.getOrDefault(p.getUniqueId(),1)*0.05)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 10, Nobskillsinv);
				itemset("AgentTurtle", Material.TURTLE_HELMET, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Water]","AgentTurtle will Attack Enemies", "(Damage Affected By Storm)","",ChatColor.BOLD+"2 X "+BigDecimal.valueOf(0.3*(1+fsd.Storm.getOrDefault(p.getUniqueId(),1)*0.03)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 11, Nobskillsinv);
				itemset("ElderGuardian", Material.ELDER_GUARDIAN_SPAWN_EGG, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Water]","Replace Guardians to an Elder Guardian","",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.8*(1+fsd.GuardianSupport.getOrDefault(p.getUniqueId(),1)*0.06)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 12, Nobskillsinv);
				itemset("AxolotlSquad", Material.AXOLOTL_BUCKET, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Water]","Summon AxolotlSquad to Hit Position","Target Boss & Leader First","(Damage Affected By Transition)","",ChatColor.BOLD+"5 X 6 X "+BigDecimal.valueOf(0.05*(1+fsd.Transition.getOrDefault(p.getUniqueId(),1)*0.016)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 13, Nobskillsinv);
				itemset("GlowSquid", Material.GLOW_INK_SAC, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Water]","Summon GlowSquids When Surfing end","(Damage Affected By DolphinSurf)","",ChatColor.BOLD+"3 X "+BigDecimal.valueOf(0.36*(1+fsd.DolphinSurf.getOrDefault(p.getUniqueId(),1)*0.03)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 14, Nobskillsinv);
				itemset("Noble", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("Increases Whole Skills Damage", "Remove Nuker's Armor Panalty", "Increases Trident Speed"), 16, Nobskillsinv);
				itemset("Whirlpool", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Water]","Sneaking + ThrowItem","",ChatColor.BOLD+"10 X 0.46D + 20 X 0.5D"), 17, Nobskillsinv);

				itemset("SprayAttack(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 18, Nobskillsinv);
				itemset("EyesOfSea(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 19, Nobskillsinv);
				itemset("Omnipotent (Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 20, Nobskillsinv);
				itemset("GuardianCurse(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 21, Nobskillsinv);
				itemset("Downpour(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 22, Nobskillsinv);
				itemset("Ruler(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 25, Nobskillsinv);
				itemset("OceanMarch(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 26, Nobskillsinv);
			}
			else {
				itemset("HoldPosition", Material.TARGET, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Water]","Make Stop Assaulting", "When You Use Once More","(Damage Affected By Assault)","",ChatColor.BOLD+"6 X "+BigDecimal.valueOf(0.1*(1+fsd.Assault.getOrDefault(p.getUniqueId(),1)*0.015)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 9, Nobskillsinv);
				itemset("Release", Material.CONDUIT, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Water]","Release Trident Force When You Use Once More", "(Damage Affected By WaterWheel)","",ChatColor.BOLD+"8 X "+BigDecimal.valueOf(0.16*(1+fsd.WaterWheel.getOrDefault(p.getUniqueId(),1)*0.05)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 10, Nobskillsinv);
				itemset("AgentTurtle", Material.TURTLE_HELMET, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Water]","AgentTurtle will Attack Enemies", "(Damage Affected By Storm)","",ChatColor.BOLD+"2 X "+BigDecimal.valueOf(0.3*(1+fsd.Storm.getOrDefault(p.getUniqueId(),1)*0.03)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 11, Nobskillsinv);
				itemset("ElderGuardian", Material.ELDER_GUARDIAN_SPAWN_EGG, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Water]","Replace Guardians to an Elder Guardian","",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.8*(1+fsd.GuardianSupport.getOrDefault(p.getUniqueId(),1)*0.06)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 12, Nobskillsinv);
				itemset("AxolotlSquad", Material.AXOLOTL_BUCKET, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Water]","Summon AxolotlSquad to Hit Position","Target Boss & Leader First","(Damage Affected By Transition)","",ChatColor.BOLD+"5 X 6 X "+BigDecimal.valueOf(0.05*(1+fsd.Transition.getOrDefault(p.getUniqueId(),1)*0.016)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 13, Nobskillsinv);
				itemset("GlowSquid", Material.GLOW_INK_SAC, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Water]","Summon GlowSquids When Surfing end","(Damage Affected By DolphinSurf)","",ChatColor.BOLD+"3 X "+BigDecimal.valueOf(0.36*(1+fsd.DolphinSurf.getOrDefault(p.getUniqueId(),1)*0.03)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 14, Nobskillsinv);
				itemset("Noble", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("Increases Whole Skills Damage", "Remove Nuker's Armor Panalty", "Increases Trident Speed"), 16, Nobskillsinv);
				itemset("Whirlpool", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Water]","Sneaking + ThrowItem","",ChatColor.BOLD+"10 X 1.46D"), 17, Nobskillsinv);

				itemset("SprayAttack", Material.INK_SAC, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Water]","Order SprayAttack When You Use Once More","(Damage Affected By Assault)","",ChatColor.BOLD+"6 X "+BigDecimal.valueOf(0.2*(1+fsd.Assault.getOrDefault(p.getUniqueId(),1)*0.015)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 18, Nobskillsinv);
				itemset("EyesOfSea", Material.SEA_LANTERN, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Water]","Rouse EyesOfSea When You Use Once More", "(Damage Affected By WaterWheel)","",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.95*(1+fsd.WaterWheel.getOrDefault(p.getUniqueId(),1)*0.52)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 19, Nobskillsinv);
				itemset("Omnipotent", Material.HEART_OF_THE_SEA, 0, 1, Arrays.asList("Halves Storm Cooldown"), 20, Nobskillsinv);
				itemset("GuardianCurse", Material.PRISMARINE, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Water]","Able To Use GuardianCurse","By [Sneaking + ChangeItem(MouseWheel)]", "(Damage Affected By GuardianSupport)","",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.33*(1+fsd.GuardianSupport.getOrDefault(p.getUniqueId(),1)*0.056)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 21, Nobskillsinv);
				itemset("Downpour", Material.POINTED_DRIPSTONE, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Water]","Summon Downpour to Hit Position","(Damage Affected By Transition)","",ChatColor.BOLD+"6 X "+BigDecimal.valueOf(0.2*(1+fsd.Assault.getOrDefault(p.getUniqueId(),1)*0.026)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 22, Nobskillsinv);
				itemset("Ruler", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("Increases Damage & Armor","Decrease Whirlpool Cooldown"), 25, Nobskillsinv);
				itemset("OceanMarch", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Water]","Sprinting + ThrowItem","",ChatColor.BOLD+"10 X 0.46D + 20 X 0.5D"), 26, Nobskillsinv);
			}
			itemset("Current Proficiency", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(""+Proficiency.getproexp(p)), 27, Nobskillsinv);
			itemset("SkillPoints", Material.NETHER_STAR, 0, 1, Arrays.asList(ChatColor.AQUA+"SP."+fsd.SkillPoints.getOrDefault(p.getUniqueId(),1),"","Click if you want to reset your skill's levels"), 35, Nobskillsinv);
		
		}
		
		p.openInventory(Nobskillsinv);
	}


}
