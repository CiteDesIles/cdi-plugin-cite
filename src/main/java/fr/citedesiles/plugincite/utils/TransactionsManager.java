package fr.citedesiles.plugincite.utils;

import fr.citedesiles.plugincite.PluginCite;
import fr.citedesiles.plugincite.objects.CDITeam;
import fr.citedesiles.plugincite.postgresql.DatabaseManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TransactionsManager {

    public static boolean addMoney(CDITeam cdiTeam, int amount) {
        try (Connection connection = DatabaseManager.MAIN_DB.getDatabaseAccess().getConnection()) {
            connection.setAutoCommit(false);
            PreparedStatement statement = connection.prepareStatement("UPDATE team SET golds = golds + ? WHERE name = ?");
            statement.setInt(1, amount);
            statement.setString(2, cdiTeam.getName());
            statement.executeUpdate();
            connection.commit();
            return true;
        } catch (SQLException e) {
            PluginCite.instance().getLogger().severe("Error while adding money to team " + cdiTeam.getName());
            return false;
        }
    }

    public static boolean removeMoney(CDITeam cdiTeam, int amount) {
        try (Connection connection = DatabaseManager.MAIN_DB.getDatabaseAccess().getConnection()) {
            connection.setAutoCommit(false);
            PreparedStatement statement = connection.prepareStatement("UPDATE team SET golds = golds - ? WHERE name = ?");
            statement.setInt(1, amount);
            statement.setString(2, cdiTeam.getName());
            statement.executeUpdate();
            connection.commit();
            return true;
        } catch (SQLException e) {
            PluginCite.instance().getLogger().severe("Error while removing money from team " + cdiTeam.getName());
            return false;
        }
    }

    public static boolean setMoney(CDITeam cdiTeam, int amount) {
        try (Connection connection = DatabaseManager.MAIN_DB.getDatabaseAccess().getConnection()) {
            connection.setAutoCommit(false);
            PreparedStatement statement = connection.prepareStatement("UPDATE team SET golds = ? WHERE name = ?");
            statement.setInt(1, amount);
            statement.setString(2, cdiTeam.getName());
            statement.executeUpdate();
            connection.commit();
            return true;
        } catch (SQLException e) {
            PluginCite.instance().getLogger().severe("Error while setting money for team " + cdiTeam.getName());
            return false;
        }
    }

    public static boolean addSupportPoints(CDITeam cdiTeam, int amount) {
        try (Connection connection = DatabaseManager.MAIN_DB.getDatabaseAccess().getConnection()) {
            connection.setAutoCommit(false);
            PreparedStatement statement = connection.prepareStatement("UPDATE team SET supportpoints = team.supportpoints + ? WHERE name = ?");
            statement.setInt(1, amount);
            statement.setString(2, cdiTeam.getName());
            statement.executeUpdate();
            connection.commit();
            return true;
        } catch (SQLException e) {
            PluginCite.instance().getLogger().severe("Error while adding support points to team " + cdiTeam.getName());
            return false;
        }
    }

    public static boolean removeSupportPoints(CDITeam cdiTeam, int amount) {
        try (Connection connection = DatabaseManager.MAIN_DB.getDatabaseAccess().getConnection()) {
            connection.setAutoCommit(false);
            PreparedStatement statement = connection.prepareStatement("UPDATE team SET supportpoints = team.supportpoints - ? WHERE name = ?");
            statement.setInt(1, amount);
            statement.setString(2, cdiTeam.getName());
            statement.executeUpdate();
            connection.commit();
            return true;
        } catch (SQLException e) {
            PluginCite.instance().getLogger().severe("Error while removing support points from team " + cdiTeam.getName());
            return false;
        }
    }

    public static boolean setSupportPoints(CDITeam cdiTeam, int amount) {
        try (Connection connection = DatabaseManager.MAIN_DB.getDatabaseAccess().getConnection()) {
            connection.setAutoCommit(false);
            PreparedStatement statement = connection.prepareStatement("UPDATE team SET supportpoints = ? WHERE name = ?");
            statement.setInt(1, amount);
            statement.setString(2, cdiTeam.getName());
            statement.executeUpdate();
            connection.commit();
            return true;
        } catch (SQLException e) {
            PluginCite.instance().getLogger().severe("Error while setting support points for team " + cdiTeam.getName());
            return false;
        }
    }
}