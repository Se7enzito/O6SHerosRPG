package org.bernardo.o6sherosrpg.levels.APIs;

import org.bernardo.o6sherosrpg.database.DatabaseAPI;
import org.bernardo.o6sherosrpg.itens.APIs.ItensClasseAPI;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class LevelManagerAPI implements Listener {

    private final ItensClasseAPI itensClasseAPI = new ItensClasseAPI();
    private final DatabaseAPI databaseAPI = new DatabaseAPI();
    private final XPLevelAPI xpLevelAPI = new XPLevelAPI();

    public void adicionarXpToPlayer(Player player, float xp) {
        String uuid = player.getUniqueId().toString();

        String classe = databaseAPI.getClasseAtivaUser(uuid);
        int levelPlayer = databaseAPI.getLevelClasseUser(uuid, classe);

        double xpNextLevel = xpLevelAPI.getXPToNextLevel(levelPlayer);
        double xpUser = databaseAPI.getXpClasseUser(uuid, classe);
        double xpTotal = xpUser + xp;

        if (xpTotal > xpNextLevel) {
            while (xpTotal > xpNextLevel) {
                xpTotal -= xpNextLevel;
                levelPlayer++;

                xpNextLevel = xpLevelAPI.getXPToNextLevel(levelPlayer);
            }

            databaseAPI.atualizarXpClasseUser(uuid, classe, xpTotal);
            databaseAPI.definirLevelClasseUser(uuid, classe, levelPlayer);
        } else if (xpTotal == xpNextLevel) {
            databaseAPI.atualizarXpClasseUser(uuid, classe, 0);
            databaseAPI.adicionarLevelClasseUser(uuid, classe);
        } else {
            databaseAPI.adicionarXpClasseUser(uuid, classe, xp);
        }
    }

    @EventHandler
    public void playerInteractEvent(PlayerInteractEvent e) {
        Player player = e.getPlayer();
    }

}
