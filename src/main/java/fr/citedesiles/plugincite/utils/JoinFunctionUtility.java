package fr.citedesiles.plugincite.utils;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import fr.citedesiles.plugincite.PluginCite;
import org.bukkit.entity.Player;

public class JoinFunctionUtility {
    public static void connect(String serveurName, Player player) {
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("Connect");
        out.writeUTF(serveurName);
        player.sendPluginMessage(PluginCite.instance(), "BungeeCord", out.toByteArray());
    }
}
