package org.bernardo.o6sherosrpg.classes.templates;

import org.bernardo.o6sherosrpg.classes.APIs.ClassesManagerAPI;
import org.bernardo.o6sherosrpg.itens.APIs.MenuItensAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class ClassesTemplate implements Listener {

    private final String MENU_CLASSES = ChatColor.GRAY + "Menu de Classes";
    private final String MENU_NORMAIS = ChatColor.GRAY + "Menu de Classes - Normais";
    private final String MENU_HEROS = ChatColor.GRAY + "Menu de Classes - Heros";

    private final MenuItensAPI menuItensAPI = new MenuItensAPI();
    private final ClassesManagerAPI classesManagerAPI = new ClassesManagerAPI();

    public void abrirMenuPrincipal(Player player) {
        Inventory inv = Bukkit.createInventory(null, 3 * 9, MENU_CLASSES);

        inv.setItem(12, menuItensAPI.itemMenuClassesNormais());
        inv.setItem(14, menuItensAPI.itemMenuClassesHeros());

        player.openInventory(inv);
    }

    public void abrirMenuClassesNormais(Player player) {
        Inventory inv = Bukkit.createInventory(null, 3 * 9, MENU_NORMAIS);

        inv.setItem(10, menuItensAPI.itemArco(player));
        inv.setItem(12, menuItensAPI.itemEscudo(player));
        inv.setItem(14, menuItensAPI.itemEspada(player));
        inv.setItem(16, menuItensAPI.itemLanca(player));

        player.openInventory(inv);
    }

    public void abrirMenuClassesHeros(Player player) {
        Inventory inv = Bukkit.createInventory(null, 3 * 9, MENU_HEROS);

        inv.setItem(10, menuItensAPI.itemArqueiro(player));
        inv.setItem(12, menuItensAPI.itemEspadachim(player));
        inv.setItem(14, menuItensAPI.itemGuerreiro(player));
        inv.setItem(16, menuItensAPI.itemMago(player));

        player.openInventory(inv);
    }

    @EventHandler
    public void clickInventory(InventoryClickEvent e) {
        if (e.getView() != null && e.getView().getTitle() != null) {
            if (e.getView().getTitle().equalsIgnoreCase(MENU_CLASSES)) {
                e.setCancelled(true);

                ItemStack itemStack = e.getCurrentItem();
                if (itemStack == null || !itemStack.hasItemMeta()) {
                    return;
                }

                Player player = (Player) e.getWhoClicked();

                if (itemStack.equals(menuItensAPI.itemMenuClassesNormais())) {
                    player.closeInventory();

                    abrirMenuClassesNormais(player);
                } else if (itemStack.equals(menuItensAPI.itemMenuClassesHeros())) {
                    player.closeInventory();

                    abrirMenuClassesHeros(player);
                }

            } else if (e.getView().getTitle().equalsIgnoreCase(MENU_NORMAIS)) {
                e.setCancelled(true);

                ItemStack itemStack = e.getCurrentItem();
                if (itemStack == null || !itemStack.hasItemMeta()) {
                    return;
                }

                Player player = (Player) e.getWhoClicked();
                ClickType clickType = e.getClick();

                if (itemStack.equals(menuItensAPI.itemArqueiro(player))) {

                } else if (itemStack.equals(menuItensAPI.itemEspadachim(player))) {

                } else if (itemStack.equals(menuItensAPI.itemGuerreiro(player))) {

                } else if (itemStack.equals((menuItensAPI.itemMago(player)))) {

                }
            } else if (e.getView().getTitle().equalsIgnoreCase(MENU_HEROS)) {
                e.setCancelled(true);

                ItemStack itemStack = e.getCurrentItem();
                if (itemStack == null || !itemStack.hasItemMeta()) {
                    return;
                }

                Player player = (Player) e.getWhoClicked();

                if (itemStack.equals(menuItensAPI.itemArco(player))) {

                } else if (itemStack.equals(menuItensAPI.itemEscudo(player))) {

                } else if (itemStack.equals(menuItensAPI.itemEspada(player))) {

                } else if (itemStack.equals(menuItensAPI.itemLanca(player))) {

                }
            }
        }
    }

}
