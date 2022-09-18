package io.github.chw3021.classes.tamer;



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

public class TamSkillsGui{
	

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
	
	public void TamSkillsinv(Player p)
	{
        String path = new File("").getAbsolutePath();
		TamSkillsData tsd = new TamSkillsData(TamSkillsData.loadData(path +"/plugins/RPGskills/TamSkillsData.data"));
		Inventory Tamskillsinv = Bukkit.createInventory(null, 54, "TamSkills");
		Obtained.itemset(p, Tamskillsinv);
		

		if(p.getLocale().equalsIgnoreCase("ko_kr")) {
			itemset("���߰���", Material.ARROW, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+tsd.PressTheAttack.getOrDefault(p.getUniqueId(), 0),"","�չٲٱ�(ȭ��߻�) �Ǵ� ��������","ȭ���� ���ݷ��� ������ ���ݷ� ������ 5%�Դϴ�","","�����̵�� �ش����� ������ϴ�","ũ������ź�� ��� �����մϴ�", "Master LV.1"), 0, Tamskillsinv);
			itemset("�����̵�", Material.CAVE_SPIDER_SPAWN_EGG, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+tsd.Spidey.getOrDefault(p.getUniqueId(), 0),"",ChatColor.UNDERLINE+"[���� �迭]","��ũ���� + ��Ŭ��","",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.2*(1+tsd.Spidey.getOrDefault(p.getUniqueId(), 0)*0.02)*(1+tsd.Taming.getOrDefault(p.getUniqueId(), 0)*0.058)).setScale(2, RoundingMode.HALF_EVEN)+"D", "Master LV.50"), 1, Tamskillsinv);
			itemset("�ݷ�����", Material.WOLF_SPAWN_EGG, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+tsd.Pets.getOrDefault(p.getUniqueId(), 0),"",ChatColor.UNDERLINE+"[���� �迭]","�չٲٱ� + ��ũ����"
					,"","����: "+ChatColor.BOLD+" X "+BigDecimal.valueOf(5*(1+tsd.Pets.getOrDefault(p.getUniqueId(), 0)*0.02)*(1+tsd.Taming.getOrDefault(p.getUniqueId(), 0)*0.015)).setScale(2, RoundingMode.HALF_EVEN)+"D"
					,"","�����: "+ChatColor.BOLD+" X "+BigDecimal.valueOf(3*(1+tsd.Pets.getOrDefault(p.getUniqueId(), 0)*0.02)*(1+tsd.Taming.getOrDefault(p.getUniqueId(), 0)*0.01)).setScale(2, RoundingMode.HALF_EVEN)+"D"
					,"","�޹���: "+ChatColor.BOLD+" X "+BigDecimal.valueOf(3*(1+tsd.Taming.getOrDefault(p.getUniqueId(), 0)*0.01)).setScale(2, RoundingMode.HALF_EVEN)+"D",
					"Master LV.50"), 2, Tamskillsinv);
			itemset("����", Material.BEEHIVE, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+tsd.BeeHive.getOrDefault(p.getUniqueId(), 0),"",ChatColor.UNDERLINE+"[�͵� �迭]","���� + ��Ŭ��","",ChatColor.BOLD+" X "+BigDecimal.valueOf(3*(1+tsd.BeeHive.getOrDefault(p.getUniqueId(), 0)*0.03)*(1+tsd.Taming.getOrDefault(p.getUniqueId(), 0)*0.023)).setScale(2, RoundingMode.HALF_EVEN)+"D", "Master LV.50"), 3, Tamskillsinv);
			itemset("ũ������ź", Material.CREEPER_HEAD, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+tsd.CreepBomb.getOrDefault(p.getUniqueId(), 0),"",ChatColor.UNDERLINE+"[�͵� �迭]","��ũ���� + ��Ŭ��","",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.08*(1+tsd.CreepBomb.getOrDefault(p.getUniqueId(), 0)*0.07)*(1+tsd.Taming.getOrDefault(p.getUniqueId(), 0)*0.058)).setScale(2, RoundingMode.HALF_EVEN)+"D", "Master LV.50"), 4, Tamskillsinv);
			itemset("�Ǵ�", Material.PANDA_SPAWN_EGG, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+tsd.PandaSweep.getOrDefault(p.getUniqueId(), 0),"",ChatColor.UNDERLINE+"[���� �迭]","���� + ��Ŭ��","�ֺ����� ���������� �����մϴ�",""+ChatColor.BOLD+" X "+BigDecimal.valueOf(8*(1+tsd.PandaSweep.getOrDefault(p.getUniqueId(), 0)*0.04)*(1+tsd.Taming.getOrDefault(p.getUniqueId(), 0)*0.02)).setScale(2, RoundingMode.HALF_EVEN)+"D", "Master LV.50"), 5, Tamskillsinv);
			itemset("����", Material.BOOK, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+tsd.Taming.getOrDefault(p.getUniqueId(), 0),"","�������� ü��, ���ݷ�, �ӵ��� �����մϴ�","",
					ChatColor.GOLD+"��ũ���� + �����۹ٲٱ� ��(���콺�� ������)", ChatColor.GOLD+"�������� ������ ���߰����� �����մϴ�","", ChatColor.AQUA+"��ũ���� + �����۹ٲٱ� �Ʒ�(���콺�� �Ʒ�����)", ChatColor.AQUA+"�������� �ǵ��������ϴ�"), 6, Tamskillsinv);
			if(Proficiency.getpro(p)<1) {
				itemset("Ȱ��(���)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/29315"), 9, Tamskillsinv);
				itemset("�Ź��ٹ߻�(���)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/29315"), 10, Tamskillsinv);
				itemset("�������(���)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/29315"), 11, Tamskillsinv);
				itemset("ȥ��(���)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/29315"), 12, Tamskillsinv);
				itemset("�ŷ�(���)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/29315"), 13, Tamskillsinv);
				itemset("�Ǵ��۾���(���)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/29315"), 14, Tamskillsinv);
				itemset("����(���)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/29315"), 15, Tamskillsinv);
				itemset("ö��/�񷽰�Ÿ(���)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/29315"), 17, Tamskillsinv);
			}
			else if(Proficiency.getpro(p)<2 && Proficiency.getpro(p)>=1) {
				itemset("Ȱ��", Material.APPLE, 0, 1, Arrays.asList("ȭ�� ���߽� ü���� ȸ���մϴ�"), 9, Tamskillsinv);
				itemset("�Ź��ٹ߻�", Material.COBWEB, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[�͵� �迭]","���Է½� �Ź��ٹ߻縦 ����մϴ�","(���ط��� �����̵� ������ ���մϴ�)","",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.6*(1+tsd.Spidey.getOrDefault(p.getUniqueId(), 0)*0.04)*(1+tsd.Taming.getOrDefault(p.getUniqueId(), 0)*0.058)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 10, Tamskillsinv);
				itemset("�������", Material.SLIME_BLOCK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[���� �迭]","�ݷ��������� �����Ҷ� ����","�������� ��������� �մϴ�","���� ���߰��ݴ���� �ִٸ�","�ش� ��ǥ���� �����մϴ�",
						"(���ط��� �ݷ����� ������ ���մϴ�)","",ChatColor.BOLD+"5 X "+BigDecimal.valueOf(0.3*(1+tsd.Pets.getOrDefault(p.getUniqueId(), 0)*0.03)*(1+tsd.Taming.getOrDefault(p.getUniqueId(), 0)*0.058)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 11, Tamskillsinv);
				itemset("ȥ��", Material.BEE_NEST, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[�͵� �迭]","����� ������ �ֺ����� �����մϴ�","(���ط��� ���� ������ ���մϴ�)",""
						,ChatColor.BOLD+"6 X 5 X "+BigDecimal.valueOf(0.2*(1+tsd.BeeHive.getOrDefault(p.getUniqueId(), 0)*0.01)*(1+tsd.Taming.getOrDefault(p.getUniqueId(), 0)*0.058)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 12, Tamskillsinv);
				itemset("�ŷ�", Material.CREEPER_HEAD, 0, 1, Arrays.asList("�ֺ������� ������ϴ�"), 13, Tamskillsinv);
				itemset("�Ǵ��۾���", Material.ANVIL, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[���� �迭]","�Ǵٰ� �����Ҷ� ����","�Ǵٰ� �۾�������� �մϴ�","���� ���߰��ݴ���� �ִٸ�","�ش� ��ǥ���� �̵��մϴ�",""
						+ "(���ط��� �Ǵ� ������ ���մϴ�)","",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.6*(1+tsd.PandaSweep.getOrDefault(p.getUniqueId(), 0)*0.03)*(1+tsd.Taming.getOrDefault(p.getUniqueId(), 0)*0.058)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 14, Tamskillsinv);
				itemset("����", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("Increases Whole Skills Damage","","�ݷ������� ����� �������� �߰��˴ϴ�"
						,"","����: "+ChatColor.BOLD+" X "+BigDecimal.valueOf(4*(1+tsd.Pets.getOrDefault(p.getUniqueId(), 0)*0.03)*(1+tsd.Taming.getOrDefault(p.getUniqueId(), 0)*0.02)).setScale(2, RoundingMode.HALF_EVEN)+"D"
						,"","������: "+ChatColor.BOLD+" X "+BigDecimal.valueOf(4*(1+tsd.Pets.getOrDefault(p.getUniqueId(), 0)*0.03)*(1+tsd.Taming.getOrDefault(p.getUniqueId(), 0)*0.02)).setScale(2, RoundingMode.HALF_EVEN)+"D"
						,"","��� �������� ��� ���ؿ��� �鿪�̵˴ϴ�","����� �����ص� �������ִ�", "�������� ������� �ʽ��ϴ�"), 15, Tamskillsinv);
				itemset("ö��/�񷽰�Ÿ", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(
						ChatColor.UNDERLINE+"[���� �迭]","��ũ���� + �����۴�����","",ChatColor.BOLD+" X "+BigDecimal.valueOf(20*(1+tsd.Taming.getOrDefault(p.getUniqueId(), 0)*0.08)).setScale(2, RoundingMode.HALF_EVEN)+"D"
						,"","�񷽰�Ÿ�� ö���� �����Ҷ��� ��� �����մϴ�",""
						,ChatColor.BOLD+" X "+BigDecimal.valueOf(10*(1+tsd.Taming.getOrDefault(p.getUniqueId(), 0)*0.058)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 17, Tamskillsinv);

				itemset("����ȭ��(���)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/155015"), 18, Tamskillsinv);
				itemset("������(���)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/155015"), 20, Tamskillsinv);
				itemset("��Ÿ��(���)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/155015"), 21, Tamskillsinv);
				itemset("ũ���۱���(���)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/155015"), 22, Tamskillsinv);
				itemset("�߱�����(���)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/155015"), 23, Tamskillsinv);
				itemset("����(���)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/155015"), 24, Tamskillsinv);
				itemset("��鿩�� �巡��(���)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("�䱸 ���õ�: "+ Proficiency.getproexp(p) + "/155015"), 26, Tamskillsinv);
			}
			else {
				itemset("Ȱ��", Material.APPLE, 0, 1, Arrays.asList("ȭ�� ���߽� ü���� ȸ���մϴ�"), 9, Tamskillsinv);
				itemset("�Ź��ٹ߻�", Material.COBWEB, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[�͵� �迭]","���Է½� �Ź��ٹ߻縦 ����մϴ�","(���ط��� �����̵� ������ ���մϴ�)","",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.6*(1+tsd.Spidey.getOrDefault(p.getUniqueId(), 0)*0.04)*(1+tsd.Taming.getOrDefault(p.getUniqueId(), 0)*0.058)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 10, Tamskillsinv);
				itemset("�������", Material.SLIME_BLOCK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[���� �迭]","�ݷ��������� �����Ҷ� ����","�������� ��������� �մϴ�","���� ���߰��ݴ���� �ִٸ�","�ش� ��ǥ���� �����մϴ�",
						"(���ط��� �ݷ����� ������ ���մϴ�)","",ChatColor.BOLD+"5 X "+BigDecimal.valueOf(0.3*(1+tsd.Pets.getOrDefault(p.getUniqueId(), 0)*0.03)*(1+tsd.Taming.getOrDefault(p.getUniqueId(), 0)*0.058)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 11, Tamskillsinv);
				itemset("ȥ��", Material.BEE_NEST, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[�͵� �迭]","����� ������ �ֺ����� �����մϴ�","(���ط��� ���� ������ ���մϴ�)",""
						,ChatColor.BOLD+"6 X 5 X "+BigDecimal.valueOf(0.2*(1+tsd.BeeHive.getOrDefault(p.getUniqueId(), 0)*0.01)*(1+tsd.Taming.getOrDefault(p.getUniqueId(), 0)*0.058)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 12, Tamskillsinv);
				itemset("�ŷ�", Material.CREEPER_HEAD, 0, 1, Arrays.asList("�ֺ������� ������ϴ�"), 13, Tamskillsinv);
				itemset("�Ǵ��۾���", Material.ANVIL, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[���� �迭]","�Ǵٰ� �����Ҷ� ����","�Ǵٰ� �۾�������� �մϴ�","���� ���߰��ݴ���� �ִٸ�","�ش� ��ǥ���� �̵��մϴ�",""
						+ "(���ط��� �Ǵ� ������ ���մϴ�)","",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.6*(1+tsd.PandaSweep.getOrDefault(p.getUniqueId(), 0)*0.03)*(1+tsd.Taming.getOrDefault(p.getUniqueId(), 0)*0.058)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 14, Tamskillsinv);
				itemset("����", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("���ݷ��� �����մϴ�","","�ݷ������� ����� �������� �߰��˴ϴ�"
						,"","����: "+ChatColor.BOLD+" X "+BigDecimal.valueOf(4*(1+tsd.Pets.getOrDefault(p.getUniqueId(), 0)*0.03)*(1+tsd.Taming.getOrDefault(p.getUniqueId(), 0)*0.02)).setScale(2, RoundingMode.HALF_EVEN)+"D"
						,"","������: "+ChatColor.BOLD+" X "+BigDecimal.valueOf(4*(1+tsd.Pets.getOrDefault(p.getUniqueId(), 0)*0.03)*(1+tsd.Taming.getOrDefault(p.getUniqueId(), 0)*0.02)).setScale(2, RoundingMode.HALF_EVEN)+"D"
						,"","��� �������� ��� ���ؿ��� �鿪�̵˴ϴ�","����� �����ص� �������ִ�", "�������� ������� �ʽ��ϴ�"), 15, Tamskillsinv);
				itemset("ö��/�񷽰�Ÿ", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(
						ChatColor.UNDERLINE+"[���� �迭]","��ũ���� + �����۴�����","",ChatColor.BOLD+" X "+BigDecimal.valueOf(20*(1+tsd.Taming.getOrDefault(p.getUniqueId(), 0)*0.08)).setScale(2, RoundingMode.HALF_EVEN)+"D"
						,"","�񷽰�Ÿ�� ö���� �����Ҷ��� ��� �����մϴ�",""
						,ChatColor.BOLD+" X "+BigDecimal.valueOf(10*(1+tsd.Taming.getOrDefault(p.getUniqueId(), 0)*0.058)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 17, Tamskillsinv);

				itemset("����ȭ��", Material.TIPPED_ARROW, 0, 1, Arrays.asList("���� ������ŵ�ϴ�"), 18, Tamskillsinv);
				itemset("������", Material.SHEARS, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[���� �迭]","���Է½� �������� ����������� �մϴ�","(���ط��� �ݷ����� ������ ���մϴ�)"
						,"",ChatColor.BOLD+"5 X "+BigDecimal.valueOf(0.5*(1+tsd.Pets.getOrDefault(p.getUniqueId(), 0)*0.035)*(1+tsd.Taming.getOrDefault(p.getUniqueId(), 0)*0.058)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 19, Tamskillsinv);
				itemset("��Ÿ��", Material.CHICKEN_SPAWN_EGG, 0, 1, Arrays.asList("���Է½� ���� ��ȯ�Ͽ� ���� ���ƿ����ϴ�"), 21, Tamskillsinv);
				itemset("ũ���۱���", Material.CREEPER_BANNER_PATTERN, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[�͵� �迭]","������ ������ �����˴ϴ�","(���ط��� ũ������ź ������ ���մϴ�)"
						,"",ChatColor.BOLD+"10 X "+BigDecimal.valueOf(0.08*(1+tsd.CreepBomb.getOrDefault(p.getUniqueId(), 0)*0.07)*(1+tsd.Taming.getOrDefault(p.getUniqueId(), 0)*0.058)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 22, Tamskillsinv);
				itemset("�߱�����", Material.COBBLESTONE, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[���� �迭]","���Է½� �Ǵٰ� �߱����⸦ �մϴ�","(���ط��� �Ǵ� ������ ���մϴ�)"
						,"",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.6*(1+tsd.PandaSweep.getOrDefault(p.getUniqueId(), 0)*0.03)*(1+tsd.Taming.getOrDefault(p.getUniqueId(), 0)*0.058)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 23, Tamskillsinv);
				itemset("����", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("���ݷ°� ������ �����մϴ�","ö�� ���� ���ð��� �����մϴ�"), 24, Tamskillsinv);
				itemset("��鿩�� �巡��", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(
						ChatColor.UNDERLINE+"[���� �迭]",ChatColor.BOLD+"150 X "+BigDecimal.valueOf(0.1*(1+tsd.Taming.get(p.getUniqueId())*0.058)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 26, Tamskillsinv);
			}
			itemset("���� ���õ�", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(""+Proficiency.getproexp(p)), 27, Tamskillsinv);
			itemset("��ų����Ʈ", Material.NETHER_STAR, 0, 1, Arrays.asList(ChatColor.AQUA+"SP."+tsd.SkillPoints.getOrDefault(p.getUniqueId(), 0),"","Ŭ���ϸ� ��ų����Ʈ�� �ʱ�ȭ �˴ϴ�"), 35, Tamskillsinv);

		}
		else {
			itemset("PressTheAttack", Material.ARROW, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+tsd.PressTheAttack.getOrDefault(p.getUniqueId(), 0),"","SwapHand(Arrow) or MeleeAtack","The arrow's attack power is the same", "As 5% of The sum of animals' attack power","","Spidey Will Hold Hit Enemy","CreepBomb will Explode Instantly", "Master LV.1"), 0, Tamskillsinv);
			itemset("Spidey", Material.CAVE_SPIDER_SPAWN_EGG, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+tsd.Spidey.getOrDefault(p.getUniqueId(), 0),"",ChatColor.UNDERLINE+"[Earth]","Sneaking + Rightclick","",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.2*(1+tsd.Spidey.getOrDefault(p.getUniqueId(), 0)*0.02)*(1+tsd.Taming.getOrDefault(p.getUniqueId(), 0)*0.058)).setScale(2, RoundingMode.HALF_EVEN)+"D", "Master LV.50"), 1, Tamskillsinv);
			itemset("Pets", Material.WOLF_SPAWN_EGG, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+tsd.Pets.getOrDefault(p.getUniqueId(), 0),"",ChatColor.UNDERLINE+"[Earth]","SwapHand + Sneaking"
					,"","Wolf: "+ChatColor.BOLD+" X "+BigDecimal.valueOf(5*(1+tsd.Pets.getOrDefault(p.getUniqueId(), 0)*0.02)*(1+tsd.Taming.getOrDefault(p.getUniqueId(), 0)*0.015)).setScale(2, RoundingMode.HALF_EVEN)+"D"
					,"","Cat: "+ChatColor.BOLD+" X "+BigDecimal.valueOf(3*(1+tsd.Pets.getOrDefault(p.getUniqueId(), 0)*0.02)*(1+tsd.Taming.getOrDefault(p.getUniqueId(), 0)*0.01)).setScale(2, RoundingMode.HALF_EVEN)+"D"
					,"","Parrot: "+ChatColor.BOLD+" X "+BigDecimal.valueOf(3*(1+tsd.Taming.getOrDefault(p.getUniqueId(), 0)*0.01)).setScale(2, RoundingMode.HALF_EVEN)+"D",
					"Master LV.50"), 2, Tamskillsinv);
			itemset("BeeHive", Material.BEEHIVE, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+tsd.BeeHive.getOrDefault(p.getUniqueId(), 0),"",ChatColor.UNDERLINE+"[Poison]","Jump + RightClick","",ChatColor.BOLD+" X "+BigDecimal.valueOf(3*(1+tsd.BeeHive.getOrDefault(p.getUniqueId(), 0)*0.03)*(1+tsd.Taming.getOrDefault(p.getUniqueId(), 0)*0.023)).setScale(2, RoundingMode.HALF_EVEN)+"D", "Master LV.50"), 3, Tamskillsinv);
			itemset("CreepBomb", Material.CREEPER_HEAD, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+tsd.CreepBomb.getOrDefault(p.getUniqueId(), 0),"",ChatColor.UNDERLINE+"[Poison]","Sneaking + LeftClick","",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.08*(1+tsd.CreepBomb.getOrDefault(p.getUniqueId(), 0)*0.07)*(1+tsd.Taming.getOrDefault(p.getUniqueId(), 0)*0.058)).setScale(2, RoundingMode.HALF_EVEN)+"D", "Master LV.50"), 4, Tamskillsinv);
			itemset("Panda", Material.PANDA_SPAWN_EGG, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+tsd.PandaSweep.getOrDefault(p.getUniqueId(), 0),"",ChatColor.UNDERLINE+"[Earth]","Jump + LeftClick","Taunt Near By Enemies Continuously",""+ChatColor.BOLD+" X "+BigDecimal.valueOf(8*(1+tsd.PandaSweep.getOrDefault(p.getUniqueId(), 0)*0.04)*(1+tsd.Taming.getOrDefault(p.getUniqueId(), 0)*0.02)).setScale(2, RoundingMode.HALF_EVEN)+"D", "Master LV.50"), 5, Tamskillsinv);
			itemset("Taming", Material.BOOK, 0, 1, Arrays.asList(ChatColor.AQUA+"LV."+tsd.Taming.getOrDefault(p.getUniqueId(), 0),"","Increases Creatures' HP, Damage, Speed","",
					ChatColor.GOLD+"Sneaking + ItemChangeUp(MouseWheelUp)", ChatColor.GOLD+"To Assemble Animals And Remove Target","", ChatColor.AQUA+"Sneaking + ItemChangeDown(MouseWheelDown)", ChatColor.AQUA+"To Return Animals"), 6, Tamskillsinv);
			if(Proficiency.getpro(p)<1) {
				itemset("Vitality(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 9, Tamskillsinv);
				itemset("WebShoot(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 10, Tamskillsinv);
				itemset("Leap(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 11, Tamskillsinv);
				itemset("Disruption(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 12, Tamskillsinv);
				itemset("Allure(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 13, Tamskillsinv);
				itemset("PandaSweep(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 14, Tamskillsinv);
				itemset("AnimalTraining(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 15, Tamskillsinv);
				itemset("IronGolem/GolemSmash(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/29315"), 17, Tamskillsinv);
			}
			else if(Proficiency.getpro(p)<2 && Proficiency.getpro(p)>=1) {
				itemset("Vitality", Material.APPLE, 0, 1, Arrays.asList("Get Heal When Hit"), 9, Tamskillsinv);
				itemset("WebSpread", Material.COBWEB, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Poison]","Spidey will Spread Webs When Use Once More","(Damage Affected By Spidey)","",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.6*(1+tsd.Spidey.getOrDefault(p.getUniqueId(), 0)*0.04)*(1+tsd.Taming.getOrDefault(p.getUniqueId(), 0)*0.058)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 10, Tamskillsinv);
				itemset("Leap", Material.SLIME_BLOCK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Earth]","Pets will Leap When Use [Pets]","While Pets Are Existing","If They Have Target, Leap To Target",
						"(Damage Affected By Pets)","",ChatColor.BOLD+"5 X "+BigDecimal.valueOf(0.3*(1+tsd.Pets.getOrDefault(p.getUniqueId(), 0)*0.03)*(1+tsd.Taming.getOrDefault(p.getUniqueId(), 0)*0.058)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 11, Tamskillsinv);
				itemset("Disruption", Material.BEE_NEST, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Poison]","Bees will Attack Near By Enemies","When Use Once More","(Damage Affected By BeeHive)",""
						,ChatColor.BOLD+"6 X 5 X "+BigDecimal.valueOf(0.2*(1+tsd.BeeHive.getOrDefault(p.getUniqueId(), 0)*0.01)*(1+tsd.Taming.getOrDefault(p.getUniqueId(), 0)*0.058)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 12, Tamskillsinv);
				itemset("Allure", Material.CREEPER_HEAD, 0, 1, Arrays.asList("Pull near By enemies"), 13, Tamskillsinv);
				itemset("PandaSweep", Material.ANVIL, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Earth]","Pandy will Sweep When Use [Panda]","While Panda is Existing","If Panda Have Target, Sweep To Target",""
						+ "(Damage Affected By Panda)","",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.6*(1+tsd.PandaSweep.getOrDefault(p.getUniqueId(), 0)*0.03)*(1+tsd.Taming.getOrDefault(p.getUniqueId(), 0)*0.058)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 14, Tamskillsinv);
				itemset("AnimalTraining", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("Increases Whole Skills Damage","","Add Fox & Ocelot To Pets"
						,"","Fox: "+ChatColor.BOLD+" X "+BigDecimal.valueOf(4*(1+tsd.Pets.getOrDefault(p.getUniqueId(), 0)*0.03)*(1+tsd.Taming.getOrDefault(p.getUniqueId(), 0)*0.02)).setScale(2, RoundingMode.HALF_EVEN)+"D"
						,"","Ocelot: "+ChatColor.BOLD+" X "+BigDecimal.valueOf(4*(1+tsd.Pets.getOrDefault(p.getUniqueId(), 0)*0.03)*(1+tsd.Taming.getOrDefault(p.getUniqueId(), 0)*0.02)).setScale(2, RoundingMode.HALF_EVEN)+"D"
						,"","Set All Animals' Armor Max","Animals Are Immune to All Negetive Effect","Animals Will Not Disappear","If You Use Skills Once More"), 15, Tamskillsinv);
				itemset("IronGolem/GolemSmash", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Earth]","Sneaking + ThrowItem","",ChatColor.BOLD+" X "+BigDecimal.valueOf(20*(1+tsd.Taming.getOrDefault(p.getUniqueId(), 0)*0.08)).setScale(2, RoundingMode.HALF_EVEN)+"D"
						,"", "You Can Use GolemSmash When IronGolem Existing"
						,"",ChatColor.BOLD+" X "+BigDecimal.valueOf(10*(1+tsd.Taming.getOrDefault(p.getUniqueId(), 0)*0.058)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 17, Tamskillsinv);

				itemset("StunArrow(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 18, Tamskillsinv);
				itemset("Scratch(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 20, Tamskillsinv);
				itemset("Chicken(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 21, Tamskillsinv);
				itemset("CreepCloud(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 22, Tamskillsinv);
				itemset("Stomp(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 23, Tamskillsinv);
				itemset("Communication(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 24, Tamskillsinv);
				itemset("Tamed Dragon(Locked)", Material.STRUCTURE_VOID, 0, 1, Arrays.asList("Required Proficiency: "+ Proficiency.getproexp(p) + "/155015"), 26, Tamskillsinv);
			}
			else {
				itemset("Vitality", Material.APPLE, 0, 1, Arrays.asList("Get Heal When Hit"), 9, Tamskillsinv);
				itemset("WebSpread", Material.COBWEB, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Poison]","Spidey will Spread Webs When Use Once More","(Damage Affected By Spidey)","",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.6*(1+tsd.Spidey.getOrDefault(p.getUniqueId(), 0)*0.04)*(1+tsd.Taming.getOrDefault(p.getUniqueId(), 0)*0.058)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 10, Tamskillsinv);
				itemset("Leap", Material.SLIME_BLOCK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Earth]","Pets will Leap When Use [Pets]","While Pets Are Existing","If They Have Target, Leap To Target",
						"(Damage Affected By Pets)","",ChatColor.BOLD+"5 X "+BigDecimal.valueOf(0.3*(1+tsd.Pets.getOrDefault(p.getUniqueId(), 0)*0.03)*(1+tsd.Taming.getOrDefault(p.getUniqueId(), 0)*0.058)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 11, Tamskillsinv);
				itemset("Disruption", Material.BEE_NEST, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Poison]","Bees will Attack Near By Enemies","When Use Once More","(Damage Affected By BeeHive)",""
						,ChatColor.BOLD+"6 X 5 X "+BigDecimal.valueOf(0.2*(1+tsd.BeeHive.getOrDefault(p.getUniqueId(), 0)*0.01)*(1+tsd.Taming.getOrDefault(p.getUniqueId(), 0)*0.058)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 12, Tamskillsinv);
				itemset("Allure", Material.CREEPER_HEAD, 0, 1, Arrays.asList("Pull near By enemies"), 13, Tamskillsinv);
				itemset("PandaSweep", Material.ANVIL, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Earth]","Pandy will Sweep When Use [Panda]","While Panda is Existing","If Panda Have Target, Sweep To Target",""
						+ "(Damage Affected By Panda)","",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.6*(1+tsd.PandaSweep.getOrDefault(p.getUniqueId(), 0)*0.03)*(1+tsd.Taming.getOrDefault(p.getUniqueId(), 0)*0.058)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 14, Tamskillsinv);
				itemset("AnimalTraining", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("Increases Whole Skills Damage","","Add Fox & Ocelot To Pets"
						,"","Fox: "+ChatColor.BOLD+" X "+BigDecimal.valueOf(4*(1+tsd.Pets.getOrDefault(p.getUniqueId(), 0)*0.03)*(1+tsd.Taming.getOrDefault(p.getUniqueId(), 0)*0.02)).setScale(2, RoundingMode.HALF_EVEN)+"D"
						,"","Ocelot: "+ChatColor.BOLD+" X "+BigDecimal.valueOf(4*(1+tsd.Pets.getOrDefault(p.getUniqueId(), 0)*0.03)*(1+tsd.Taming.getOrDefault(p.getUniqueId(), 0)*0.02)).setScale(2, RoundingMode.HALF_EVEN)+"D"
						,"","Set All Animals' Armor Max","Animals Are Immune to All Negetive Effect","Animals Will Not Disappear","If You Use Skills Once More"), 15, Tamskillsinv);
				itemset("IronGolem/GolemSmash", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Earth]","Sneaking + ThrowItem","",ChatColor.BOLD+" X "+BigDecimal.valueOf(20*(1+tsd.Taming.getOrDefault(p.getUniqueId(), 0)*0.08)).setScale(2, RoundingMode.HALF_EVEN)+"D"
						,"", "You Can Use GolemSmash When IronGolem Existing"
						,"",ChatColor.BOLD+" X "+BigDecimal.valueOf(10*(1+tsd.Taming.getOrDefault(p.getUniqueId(), 0)*0.058)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 17, Tamskillsinv);

				itemset("StunArrow", Material.TIPPED_ARROW, 0, 1, Arrays.asList("Stun Hit Enemy"), 18, Tamskillsinv);
				itemset("Scratch", Material.SHEARS, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Earth]","Pets will Scratch Attack When Use Once More","(Damage Affected By Pets)"
						,"",ChatColor.BOLD+"5 X "+BigDecimal.valueOf(0.5*(1+tsd.Pets.getOrDefault(p.getUniqueId(), 0)*0.035)*(1+tsd.Taming.getOrDefault(p.getUniqueId(), 0)*0.058)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 19, Tamskillsinv);
				itemset("Chicken", Material.CHICKEN_SPAWN_EGG, 0, 1, Arrays.asList("Summon Chickens That Carry You"), 21, Tamskillsinv);
				itemset("CreepCloud", Material.CREEPER_BANNER_PATTERN, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Poison]","Summon Cloud After Explosion","(Damage Affected By CreepBomb)"
						,"",ChatColor.BOLD+"10 X "+BigDecimal.valueOf(0.08*(1+tsd.CreepBomb.getOrDefault(p.getUniqueId(), 0)*0.07)*(1+tsd.Taming.getOrDefault(p.getUniqueId(), 0)*0.058)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 22, Tamskillsinv);
				itemset("Stomp", Material.COBBLESTONE, 0, 1, Arrays.asList(ChatColor.UNDERLINE+"[Earth]","Panda will Stomp When Use Once More","(Damage Affected By Panda)"
						,"",ChatColor.BOLD+" X "+BigDecimal.valueOf(0.6*(1+tsd.PandaSweep.getOrDefault(p.getUniqueId(), 0)*0.03)*(1+tsd.Taming.getOrDefault(p.getUniqueId(), 0)*0.058)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 23, Tamskillsinv);
				itemset("Communication", Material.ENCHANTED_BOOK, 0, 1, Arrays.asList("Increases Damage & Armor","Decrease IronGolem Cooldown"), 24, Tamskillsinv);
				itemset("Tamed Dragon", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(
						ChatColor.UNDERLINE+"[Earth]",ChatColor.BOLD+"150 X "+BigDecimal.valueOf(0.1*(1+tsd.Taming.get(p.getUniqueId())*0.058)).setScale(2, RoundingMode.HALF_EVEN)+"D"), 26, Tamskillsinv);
			}
			itemset("Current Proficiency", Material.WRITTEN_BOOK, 0, 1, Arrays.asList(""+Proficiency.getproexp(p)), 27, Tamskillsinv);
			itemset("SkillPoints", Material.NETHER_STAR, 0, 1, Arrays.asList(ChatColor.AQUA+"SP."+tsd.SkillPoints.getOrDefault(p.getUniqueId(), 0),"","Click if you want to reset your skill's levels"), 35, Tamskillsinv);

		}
		
		p.openInventory(Tamskillsinv);
	}


}
