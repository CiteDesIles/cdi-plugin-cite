package fr.citedesiles.plugincite.mysql;

import fr.citedesiles.plugincite.PluginCite;

public enum DatabaseManager {

    MAIN_DB(new DatabaseCredentials(
            PluginCite.instance().configManager().getString("database.host"),
            PluginCite.instance().configManager().getString("database.name"),
            PluginCite.instance().configManager().getString("database.user"),
            PluginCite.instance().configManager().getString("database.password"),
            PluginCite.instance().configManager().getInt("database.port")
    ));

    private DatabaseAccess databaseAccess;

    DatabaseManager(DatabaseCredentials credentials) {
        this.databaseAccess = new DatabaseAccess(credentials);
    }

    public DatabaseAccess getDatabaseAccess() {
        return databaseAccess;
    }

    public static void initAllDataBaseConnections() {
        for(DatabaseManager databaseManager : values()) {
            databaseManager.getDatabaseAccess().initPool();
        }
    }

    public static void closeAllDataBaseConnections() {
        for(DatabaseManager databaseManager : values()) {
            databaseManager.getDatabaseAccess().closePool();
        }
    }
}
