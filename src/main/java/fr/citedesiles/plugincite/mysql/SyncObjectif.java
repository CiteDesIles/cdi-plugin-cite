package fr.citedesiles.plugincite.mysql;

import fr.citedesiles.plugincite.PluginCite;
import fr.citedesiles.plugincite.objects.CDIObjectif;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.concurrent.CompletableFuture;

public class SyncObjectif {
    public static void getFromDB() {
        CompletableFuture.runAsync(() -> {
            PluginCite.instance().getLogger().info("[CDIOBJECTIF] Loading objectifs from database...");
            try {
               Connection connection = DatabaseManager.MAIN_DB.getDatabaseAccess().getConnection();
               ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM OBJECTIF");
               while (resultSet.next()) {
                    String teamName = resultSet.getString("team");
                    String objectif = resultSet.getString("objectif");
                    int value = resultSet.getInt("value");
                   CDIObjectif cdiObjectif = new CDIObjectif(teamName, objectif, value);
                   PluginCite.instance().objectifManager().addObjectif(cdiObjectif);
               }
               PluginCite.instance().getLogger().info("[CDIOBJECTIF] Objectifs loaded from database !");

           } catch (Exception e) {
               e.printStackTrace();
           }
        });
    }
}
