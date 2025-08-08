package org.bernardo.o6sherosrpg.itens.APIs;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class ItensClasseAPI {

    // Itens Padrões

    public ItemStack itemLevelUp() {
        ItemStack itemStack = new ItemStack(Material.PAPER);
        ItemMeta meta = itemStack.getItemMeta();

        assert meta != null;
        meta.setDisplayName(ChatColor.YELLOW + "Item Level Up +1");
        meta.setLore(Arrays.asList(ChatColor.GREEN + "CLIQUE DIREITO " + ChatColor.GRAY + "para utilizar este item.", "", ChatColor.GRAY + "Utilize este item" +
                " para aumentar o seu nível de classe!"));

        itemStack.setItemMeta(meta);

        return itemStack;
    }

    public ItemStack itemXp(double quantidade) {
        ItemStack itemStack = new ItemStack(Material.NETHER_STAR);
        ItemMeta meta = itemStack.getItemMeta();

        assert meta != null;
        meta.setDisplayName(ChatColor.YELLOW + "+" + quantidade + " XP");
        meta.setLore(Arrays.asList(ChatColor.GREEN + "CLIQUE DIREITO " + ChatColor.GRAY + "para utilizar este item", "", ChatColor.GRAY + "Ao utilizar este " +
                "item são adicionados " + quantidade + " de xp na sua conta!"));

        itemStack.setItemMeta(meta);

        return itemStack;
    }

    public ItemStack itemAtivadorClasseNormal() {
        ItemStack itemStack = new ItemStack(Material.NETHER_STAR);
        ItemMeta meta = itemStack.getItemMeta();

        assert meta != null;
        meta.setDisplayName(ChatColor.YELLOW + "Ativador de Classe Normal");
        meta.setLore(Arrays.asList(ChatColor.GREEN + "CLIQUE DIREITO " + ChatColor.GRAY + "para ativar este item.", "", ChatColor.GRAY + "Utilize este item " +
                "para conseguir ativar uma nova classe normal."));

        itemStack.setItemMeta(meta);

        return itemStack;
    }

    public ItemStack itemAtivadorClasseHeroi() {
        ItemStack itemStack = new ItemStack(Material.NETHER_STAR);
        ItemMeta meta = itemStack.getItemMeta();

        assert meta != null;
        meta.setDisplayName(ChatColor.YELLOW + "Ativador de Classe Héroi");
        meta.setLore(Arrays.asList(ChatColor.GREEN + "CLIQUE DIREITO " + ChatColor.GRAY + "para ativar este item.", "", ChatColor.GRAY + "Utilize este item " +
                "para conseguir ativar uma nova classe héroi."));

        itemStack.setItemMeta(meta);

        return itemStack;
    }

    // Itens Especiais

}
