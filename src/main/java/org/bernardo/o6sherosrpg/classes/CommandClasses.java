package org.bernardo.o6sherosrpg.classes;

import org.bernardo.o6sherosrpg.classes.templates.ClassesTemplate;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.List;

public class CommandClasses implements CommandExecutor, TabCompleter {

    private final ClassesTemplate classesTemplate = new ClassesTemplate();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String lbl, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Apenas players podem utilizar este comando!");

            return false;
        }

        Player player = (Player) sender;

        if (args.length == 0) {
            classesTemplate.abrirMenuPrincipal(player);
        } else {
            player.sendMessage(ChatColor.RED + "Uso do comando: /classes");
        }

        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String lbl, String[] args) {
        return null;
    }
}
