package co.alphamc.staffbot;

import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class AuthManager {

    private List<UUID> pending;
    private HashMap<UUID, String> userIds;
    private HashMap<UUID, Integer> codes;

    public AuthManager() {
        pending = new ArrayList<>();
        userIds = new HashMap<>();
        codes = new HashMap<>();
        loadIds();
    }

    private void loadIds() {
        for (String path : Main.getInstance().getConfig().getConfigurationSection("discordId").getKeys(false)) {
            userIds.put(UUID.fromString(path), (String) Main.getInstance().getConfig().get("discordId." + path));
            Bukkit.broadcastMessage(path);
            Bukkit.broadcastMessage((String) Main.getInstance().getConfig().get("discordId." + path));
        }
    }

    public void saveIds() {
        for (UUID uuid : userIds.keySet()) {
            Main.getInstance().getConfig().set("discordId." + uuid.toString(), userIds.get(uuid));
        }
        Main.getInstance().saveConfig();
    }

    public String getUserId(UUID uuid) {
        return userIds.get(uuid);
    }

    public List<UUID> getPending() {
        return pending;
    }

    public HashMap<UUID, String> getUserIds() {
        return userIds;
    }

    public HashMap<UUID, Integer> getCodes() {
        return codes;
    }
}
