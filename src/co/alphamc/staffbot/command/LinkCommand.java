package co.alphamc.staffbot.command;

import co.alphamc.staffbot.BotUtil;
import co.alphamc.staffbot.Main;
import net.dv8tion.jda.core.entities.User;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class LinkCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String lbl, String[] args) {

        if (lbl.equalsIgnoreCase("link")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                String id = Main.getInstance().getAuthManager().getUserId(player.getUniqueId());
                if (id == null) {
                    player.sendMessage(ChatColor.YELLOW + "Please claim your account in discord.");
                    BotUtil.sendReactedChannelMessage(Main.getInstance().getBot().getBot().getTextChannelsByName("auth-output", true).get(0), player.getName() + ", please react to this message to link your account.");
                } else {
                User user = Main.getInstance().getBot().getBot().getUserById(id);
                if (user != null) {
                    player.sendMessage(ChatColor.RED + "You are already linked to: " + user.getName());
                }
            }

        }
    }
        return true;
}
}
