package org.bernardo.o6sherosrpg.classes.APIs;

import org.bernardo.o6sherosrpg.database.DatabaseAPI;
import org.bernardo.o6sherosrpg.itens.APIs.ItensClasseAPI;
import org.bernardo.o6sherosrpg.itens.APIs.MenuItensAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClassesManagerAPI implements Listener {

    public final List<String> classesHeros = Arrays.asList("ARCO", "ESCUDO", "ESPADA", "LANCA");
    public final List<String> classesNormais = Arrays.asList("ARQUEIRO", "ESPADACHIM", "GUERREIRO", "MAGO");
    private final String MENU_ATIVAR = ChatColor.GRAY + "Menu Ativar Classe";
    private final Map<Player, ItemStack> playerItemStackMap = new HashMap<>();
    private final ItensClasseAPI itensClasseAPI = new ItensClasseAPI();
    private final MenuItensAPI menuItensAPI = new MenuItensAPI();
    private final DatabaseAPI databaseAPI = new DatabaseAPI();

    public String getRaridadeClasse(String classe) {
        if (classesHeros.contains(classe)) {
            return "Herói";
        } else {
            return "Classe";
        }
    }

    public int getQuantidadeClasse(Player player, String tipo) {
        List<String> classesUser = databaseAPI.getClassesUser(player.getUniqueId().toString());
        List<String> listaComparacao;

        if (tipo.equalsIgnoreCase("heros")) {
            listaComparacao = classesHeros;
        } else if (tipo.equalsIgnoreCase("normais")) {
            listaComparacao = classesNormais;
        } else {
            return 0;
        }

        int contador = 0;
        for (String classe : classesUser) {
            if (listaComparacao.contains(classe)) {
                contador++;
            }
        }

        return contador;
    }

    public void ativarClassePlayer(Player player, ClassesTypes classesTypes) {
        boolean retorno = databaseAPI.definirClasseAtivaUser(player.getUniqueId().toString(), classesTypes.getDisplayName());

        if (retorno) {
            player.sendMessage(ChatColor.GREEN + "A classe " + classesTypes.getDisplayName() + " foi ativada com sucesso!");
        } else {
            player.sendMessage(ChatColor.RED + "Não foi possível ativar a classe " + classesTypes.getDisplayName() + " aconteceu algum erro!");
        }
    }

    public void adicionarClassePlayer(Player player, ClassesTypes classe) {
        String classeString = classe.getDisplayName();

        Bukkit.broadcastMessage("");
        Bukkit.broadcastMessage(ChatColor.GOLD + "O player " + ChatColor.YELLOW + player.getName() + " acaba de ativar a classe " + ChatColor.BOLD + classeString);
        Bukkit.broadcastMessage("");

        databaseAPI.addClasseUser(player.getUniqueId().toString(), classeString);

        player.sendMessage(ChatColor.GREEN + "Parabéns por ativar a classe " + ChatColor.GOLD + classeString + ChatColor.GREEN + "!!");
    }

    private void abrirMenuAtivador(Player player, String tipo) {
        Inventory inv = Bukkit.createInventory(null, 3 * 9, MENU_ATIVAR);

        Map<ClassesTypes, ItemStack> classesTypesItemStackMap = menuItensAPI.getClassesItensMenu(player);
        List<String> classesAtivas;

        if (tipo.equalsIgnoreCase("normal")) {
            classesAtivas = classesNormais;
        } else {
            classesAtivas = classesHeros;
        }

        int contador = 10;

        for (String classe : classesAtivas) {
            inv.setItem(contador, classesTypesItemStackMap.get(ClassesTypes.valueOf(classe)));

            contador += 2;
        }

        player.openInventory(inv);
    }

    @EventHandler
    public void playerInteractEvent(PlayerInteractEvent e) {
        Player player = e.getPlayer();
        ItemStack itemStack = e.getItem();
        Action action = e.getAction();

        if (action.equals(Action.RIGHT_CLICK_AIR) || action.equals(Action.RIGHT_CLICK_BLOCK)) {
            if (!(playerItemStackMap.containsKey(player))) {
                playerItemStackMap.put(player, itemStack);

                if (itemStack.equals(itensClasseAPI.itemAtivadorClasseNormal())) {
                    abrirMenuAtivador(player, "normal");
                } else if (itemStack.equals(itensClasseAPI.itemAtivadorClasseHeroi())) {
                    abrirMenuAtivador(player, "heroi");
                }
            }
        }
    }

    private void removerItemInventario(Player player, ItemStack itemStack) {
        Inventory inv = player.getInventory();

        for (int i = 0; i < inv.getSize(); i++) {
            ItemStack slotItem = inv.getItem(i);

            if (slotItem != null && slotItem.isSimilar(itemStack)) {
                if (slotItem.getAmount() > 1) {
                    slotItem.setAmount(slotItem.getAmount() - 1);
                } else {
                    inv.setItem(i, null);
                }

                break;
            }
        }

        player.updateInventory();
    }

    @EventHandler
    public void clickInventory(InventoryClickEvent e) {
        if (e.getView() != null && e.getView().getTitle() != null) {
            if (e.getView().getTitle().equalsIgnoreCase(MENU_ATIVAR)) {
                e.setCancelled(true);

                ItemStack itemStack = e.getCurrentItem();
                if (itemStack == null || !itemStack.hasItemMeta()) {
                    return;
                }

                Player player = (Player) e.getWhoClicked();

                if (!(itemStack.hasItemMeta()) || !(itemStack.getItemMeta().hasDisplayName())) {
                    return;
                }
                String display = itemStack.getItemMeta().getDisplayName().split(" ")[1];
                String displayUpper = display.toUpperCase();

                player.closeInventory();

                if (itemStack.getType().equals(Material.GREEN_WOOL)) {
                    if (Arrays.toString(ClassesTypes.values()).contains(displayUpper)) {
                        removerItemInventario(player, playerItemStackMap.get(player));

                        adicionarClassePlayer(player, ClassesTypes.valueOf(displayUpper));
                        ativarClassePlayer(player, ClassesTypes.valueOf(displayUpper));
                    } else {
                        player.sendMessage(ChatColor.RED + "Entre em um contato com um staff, está classe não foi encontrada.");
                    }
                }

                playerItemStackMap.remove(player);
            }
        }
    }

    @EventHandler
    public void closeInventory(InventoryCloseEvent e) {
        if (e.getView() != null && e.getView().getTitle() != null) {
            if (e.getView().getTitle().equalsIgnoreCase(MENU_ATIVAR)) {
                Player player = (Player) e.getPlayer();

                if (playerItemStackMap.containsKey(player)) {
                    playerItemStackMap.remove(player);
                }
            }
        }
    }

}
