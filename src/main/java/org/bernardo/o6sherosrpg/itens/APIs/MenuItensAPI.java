package org.bernardo.o6sherosrpg.itens.APIs;

import org.bernardo.o6sherosrpg.classes.APIs.ClassesTypes;
import org.bernardo.o6sherosrpg.database.DatabaseAPI;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.*;

public class MenuItensAPI {

    private final DatabaseAPI databaseAPI = new DatabaseAPI();

    private ItemStack itemClasseDesbloqueado(ClassesTypes classesTypes) {
        ItemStack itemStack = new ItemStack(Material.RED_WOOL);
        ItemMeta meta = itemStack.getItemMeta();

        String classeName = classesTypes.getDisplayName();

        assert meta != null;
        meta.setDisplayName(ChatColor.RED + "Classe " + classeName);
        meta.setLore(Collections.singletonList(ChatColor.GRAY + "Você já desbloqueou a classe " + classeName + "."));

        itemStack.setItemMeta(meta);

        return itemStack;
    }

    private ItemStack itemClasseBloqueado(ClassesTypes classesTypes) {
        ItemStack itemStack = new ItemStack(Material.GREEN_WOOL);
        ItemMeta meta = itemStack.getItemMeta();

        String classeName = classesTypes.getDisplayName();

        assert meta != null;
        meta.setDisplayName(ChatColor.YELLOW + "Classe " + classeName);
        meta.setLore(Arrays.asList(ChatColor.GREEN + "CLIQUE ESQUERDO " + ChatColor.GRAY + "para desbloquear esta classe.", "", ChatColor.GRAY + "Clique " +
                "para desbloquear a classe " + classeName));

        itemStack.setItemMeta(meta);

        return itemStack;
    }

    public Map<ClassesTypes, ItemStack> getClassesItensMenu(Player player) {
        Map<ClassesTypes, ItemStack> map = new HashMap<>();
        List<String> classesExist = Arrays.asList("ARCO", "ESCUDO", "ESPADA", "LANCA", "ARQUEIRO", "ESPADACHIM", "GUERREIRO", "MAGO");
        List<String> classesPLayer = databaseAPI.getClassesUser(player.getUniqueId().toString());
        classesExist.removeAll(classesPLayer);

        for (String classe : classesExist) {
            ClassesTypes classesTypes = ClassesTypes.valueOf(classe);

            map.put(classesTypes, itemClasseBloqueado(classesTypes));
        }

        for (String classe : classesPLayer) {
            ClassesTypes classesTypes = ClassesTypes.valueOf(classe);

            map.put(classesTypes, itemClasseDesbloqueado(classesTypes));
        }

        return map;
    }

    // Template Classes

    public ItemStack itemMenuClassesNormais() {
        ItemStack itemStack = new ItemStack(Material.IRON_SWORD);
        ItemMeta meta = itemStack.getItemMeta();

        assert meta != null;
        meta.setDisplayName(ChatColor.YELLOW + "Classes Normais");
        meta.setLore(Arrays.asList(ChatColor.GRAY + "Clique para ter acesso a todas as classes normais!", "", ChatColor.GRAY + "Lista das classes normais:",
                ChatColor.YELLOW + "   - " + ChatColor.GRAY + "Arqueiro", ChatColor.YELLOW + "   - " + ChatColor.GRAY + "Espadachim", ChatColor.YELLOW + "   " +
                        "- " + ChatColor.GRAY + "Guerreiro", ChatColor.YELLOW + "   - " + ChatColor.GRAY + "Mago"));

        itemStack.setItemMeta(meta);

        return itemStack;
    }

    public ItemStack itemMenuClassesHeros() {
        ItemStack itemStack = new ItemStack(Material.DIAMOND_SWORD);
        ItemMeta meta = itemStack.getItemMeta();

        assert meta != null;
        meta.setDisplayName(ChatColor.YELLOW + "Classes Héroi");
        meta.setLore(Arrays.asList(ChatColor.GRAY + "Clique para ter acesso a todas as classes héroi!", "", ChatColor.GRAY + "Lista das classes héroi:",
                ChatColor.YELLOW + "   - " + ChatColor.GRAY + "Arco", ChatColor.YELLOW + "   - " + ChatColor.GRAY + "Escudo", ChatColor.YELLOW + "   " +
                        "- " + ChatColor.GRAY + "Espada", ChatColor.YELLOW + "   - " + ChatColor.GRAY + "Lança"));

        itemStack.setItemMeta(meta);

        return itemStack;
    }

    private List<String> getLoreItem(String uuid, String classeName) {
        List<String> lore = Arrays.asList(ChatColor.GREEN + "CLIQUE ESQUERDO " + ChatColor.GRAY + "para definir classe como ativa", ChatColor.GREEN + "CLIQUE" +
                " DIREITO " + ChatColor.GRAY + "para mais informações", "");

        if (databaseAPI.getClassesUser(uuid).contains(classeName)) {
            if (databaseAPI.getClasseAtivaUser(uuid).equals(classeName)) {
                lore.add(ChatColor.GRAY + "Classe Ativa: " + ChatColor.GREEN + "Sim");
            } else {
                lore.add(ChatColor.GRAY + "Classe Ativa: " + ChatColor.RED + "Não");
            }

            lore.add(ChatColor.GRAY + "Level: " + ChatColor.GREEN + databaseAPI.getLevelClasseUser(uuid, classeName));
        } else {
            lore.add(ChatColor.RED + "Atualmente a classe está bloqueada.");
            lore.add(ChatColor.GRAY + "Utilize um ativador de classe para desbloquear.");
        }

        return lore;
    }

    public ItemStack itemArco(Player player) {
        ItemStack itemStack = new ItemStack(Material.BOW);
        ItemMeta meta = itemStack.getItemMeta();

        String uuid = player.getUniqueId().toString();
        String classeName = ClassesTypes.ARCO.getDisplayName();

        assert meta != null;
        meta.setDisplayName(ChatColor.YELLOW + "Héroi de Arco");
        meta.setLore(getLoreItem(uuid, classeName));

        itemStack.setItemMeta(meta);

        return itemStack;
    }

    public ItemStack itemEscudo(Player player) {
        ItemStack itemStack = new ItemStack(Material.SHIELD);
        ItemMeta meta = itemStack.getItemMeta();

        String uuid = player.getUniqueId().toString();
        String classeName = ClassesTypes.ESCUDO.getDisplayName();

        assert meta != null;
        meta.setDisplayName(ChatColor.YELLOW + "Héroi de Escudo");
        meta.setLore(getLoreItem(uuid, classeName));


        itemStack.setItemMeta(meta);

        return itemStack;
    }

    public ItemStack itemEspada(Player player) {
        ItemStack itemStack = new ItemStack(Material.DIAMOND_SWORD);
        ItemMeta meta = itemStack.getItemMeta();

        String uuid = player.getUniqueId().toString();
        String classeName = ClassesTypes.ESPADA.getDisplayName();

        assert meta != null;
        meta.setDisplayName(ChatColor.YELLOW + "Héroi de Espada");
        meta.setLore(getLoreItem(uuid, classeName));

        itemStack.setItemMeta(meta);

        return itemStack;
    }

    public ItemStack itemLanca(Player player) {
        ItemStack itemStack = new ItemStack(Material.BLAZE_ROD);
        ItemMeta meta = itemStack.getItemMeta();

        String uuid = player.getUniqueId().toString();
        String classeName = ClassesTypes.LANCA.getDisplayName();

        assert meta != null;
        meta.setDisplayName(ChatColor.YELLOW + "Héroi de Lança");
        meta.setLore(getLoreItem(uuid, classeName));

        itemStack.setItemMeta(meta);

        return itemStack;
    }

    public ItemStack itemArqueiro(Player player) {
        ItemStack itemStack = new ItemStack(Material.BOW);
        ItemMeta meta = itemStack.getItemMeta();

        String uuid = player.getUniqueId().toString();
        String classeName = ClassesTypes.ARQUEIRO.getDisplayName();

        assert meta != null;
        meta.setDisplayName(ChatColor.YELLOW + "Classe Arqueiro");
        meta.setLore(getLoreItem(uuid, classeName));

        itemStack.setItemMeta(meta);

        return itemStack;
    }

    public ItemStack itemEspadachim(Player player) {
        ItemStack itemStack = new ItemStack(Material.IRON_SWORD);
        ItemMeta meta = itemStack.getItemMeta();

        String uuid = player.getUniqueId().toString();
        String classeName = ClassesTypes.ESPADACHIM.getDisplayName();

        assert meta != null;
        meta.setDisplayName(ChatColor.YELLOW + "Classe Espadachim");
        meta.setLore(getLoreItem(uuid, classeName));

        itemStack.setItemMeta(meta);

        return itemStack;
    }

    public ItemStack itemGuerreiro(Player player) {
        ItemStack itemStack = new ItemStack(Material.IRON_AXE);
        ItemMeta meta = itemStack.getItemMeta();

        String uuid = player.getUniqueId().toString();
        String classeName = ClassesTypes.GUERREIRO.getDisplayName();

        assert meta != null;
        meta.setDisplayName(ChatColor.YELLOW + "Classe Guerreiro");
        meta.setLore(getLoreItem(uuid, classeName));

        itemStack.setItemMeta(meta);

        return itemStack;
    }

    public ItemStack itemMago(Player player) {
        ItemStack itemStack = new ItemStack(Material.STICK);
        ItemMeta meta = itemStack.getItemMeta();

        String uuid = player.getUniqueId().toString();
        String classeName = ClassesTypes.MAGO.getDisplayName();

        assert meta != null;
        meta.setDisplayName(ChatColor.YELLOW + "Classe Mago");
        meta.setLore(getLoreItem(uuid, classeName));

        itemStack.setItemMeta(meta);

        return itemStack;
    }

}
