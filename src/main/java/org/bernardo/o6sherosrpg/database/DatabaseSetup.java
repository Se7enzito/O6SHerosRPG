package org.bernardo.o6sherosrpg.database;

import org.bukkit.ChatColor;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseSetup {

    public static void initializeDatabase() {
        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement()) {

            String createTableClassesPlayer = "CREATE TABLE IF NOT EXISTS user_classes (" +
                    "user_id TEXT PRIMARY KEY NOT NULL," +
                    "tipo TEXT NOT NULL," +
                    "level INTEGER DEFAULT 1," +
                    "xp REAL DEFAULT 0.0," +
                    "ativo INTEGER DEFAULT 1" +
                    ");";
            statement.executeUpdate(createTableClassesPlayer);

            System.out.println(ChatColor.GREEN + "Banco de dados criado com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao criar a estrutura do banco de dados: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
