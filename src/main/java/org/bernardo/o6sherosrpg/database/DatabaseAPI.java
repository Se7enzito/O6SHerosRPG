package org.bernardo.o6sherosrpg.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DatabaseAPI {

    public String getClasseAtivaUser(String uuid) {
        String sql = "SELECT tipo FROM user_classes WHERE user_id = ? AND ativo = 1;";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, uuid);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString("tipo");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return "";
    }

    public boolean definirClasseAtivaUser(String uuid, String tipo) {
        String sqlAtivar = "UPDATE user_classes SET ativo = 1 WHERE user_id = ? AND tipo = ?";
        String sqlDesativar = "UPDATE user_classes SET ativo = 0 WHERE user_id = ? AND tipo = ?";

        String tipoAtivo = getClasseAtivaUser(uuid);

        if (tipoAtivo.equals(tipo) || tipoAtivo.isEmpty()) {
            return false;
        }

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement smAtivar = connection.prepareStatement(sqlAtivar);
             PreparedStatement smDesativar = connection.prepareStatement(sqlDesativar)) {
            smAtivar.setString(1, uuid);
            smAtivar.setString(2, tipo);
            smAtivar.executeUpdate();

            smDesativar.setString(1, uuid);
            smDesativar.setString(2, tipo);
            smDesativar.executeUpdate();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public int getLevelClasseUser(String uuid, String tipo) {
        String sql = "SELECT level FROM user_classes WHERE user_id = ? AND tipo = ?;";

        if (!(getClassesUser(uuid).contains(tipo))) {
            return 0;
        }

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, uuid);
            statement.setString(2, tipo);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    public void adicionarLevelClasseUser(String uuid, String tipo) {
        String sql = "UPDATE user_classes SET level = level + 1 WHERE user_id = ? AND tipo = ?;";

        if (!(getClassesUser(uuid).contains(tipo))) {
            return;
        }

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, uuid);
            statement.setString(2, tipo);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void definirLevelClasseUser(String uuid, String tipo, int level) {
        String sql = "UPDATE user_classes SET level = ? WHERE user_id = ? AND tipo = ?;";

        if (!(getClassesUser(uuid).contains(tipo))) {
            return;
        }

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, level);
            statement.setString(2, uuid);
            statement.setString(3, tipo);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public double getXpClasseUser(String uuid, String tipo) {
        String sql = "SELECT xp FROM user_classes WHERE user_id = ? AND tipo = ?;";

        if (!(getClassesUser(uuid).contains(tipo))) {
            return 0;
        }

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, uuid);
            statement.setString(2, tipo);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("xp");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    public void adicionarXpClasseUser(String uuid, String tipo, double xp) {
        String sql = "UPDATE user_classes SET xp = xp + ? WHERE user_id = ? AND tipo = ?;";

        if (!(getClassesUser(uuid).contains(tipo))) {
            return;
        }

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setDouble(1, xp);
            statement.setString(2, uuid);
            statement.setString(3, tipo);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void atualizarXpClasseUser(String uuid, String tipo, double xp) {
        String sql = "UPDATE user_classes SET xp = ? WHERE user_id = ? AND tipo = ?;";

        if (!(getClassesUser(uuid).contains(tipo))) {
            return;
        }

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setDouble(1, xp);
            statement.setString(2, uuid);
            statement.setString(3, tipo);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addClasseUser(String uuid, String tipo) {
        String sql = "INSERT INTO user_classes (user_id, tipo) VALUES (?, ?);";

        if (getClassesUser(uuid).contains(tipo)) {
            return;
        }

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, uuid);
            statement.setString(2, tipo);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<String> getClassesUser(String uuid) {
        List<String> list = new ArrayList<>();
        String sql = "SELECT tipo FROM user_classes WHERE user_id = ?;";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, uuid);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                list.add(resultSet.getString("tipo"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

}
