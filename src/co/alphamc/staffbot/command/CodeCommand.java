package co.alphamc.staffbot.command;

import co.alphamc.staffbot.Main;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CodeCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String lbl, String[] args) {

        if (lbl.equalsIgnoreCase("code")) {
            if(sender instanceof Player) {
                Player player = (Player) sender;
                if (args.length < 1) {
                    sender.sendMessage(ChatColor.RED + "Usage: /code (code)");
                } else {
                    String codeStr = args[0];
                    try {
                        int code = Integer.parseInt(codeStr);
                        if(Main.getInstance().getAuthManager().getCodes().get(player.getUniqueId()) != null){
                            if(Main.getInstance().getAuthManager().getCodes().get(player.getUniqueId()) == code){
                                //auth
                                player.sendMessage(ChatColor.GREEN + "Authenticated.");
                                Main.getInstance().getAuthManager().getCodes().remove(player.getUniqueId());
                                Main.getInstance().getAuthManager().getPending().remove(player.getUniqueId());
                            }else {
                                //deauth
                                player.sendMessage(ChatColor.RED + "You have entered the wrong code! If this was an error, please contact management.");
                            }
                        }
                    } catch (NumberFormatException e) {
                        sender.sendMessage(ChatColor.RED + "You have entered an invalid code!");
                    }
                }
            }else{
                return false;
            }
        }

        return true;
    }
}
