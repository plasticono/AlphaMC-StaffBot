package co.alphamc.staffbot.listener;

import co.alphamc.staffbot.BotUtil;
import co.alphamc.staffbot.Main;
import net.dv8tion.jda.core.entities.User;
import net.minecraft.util.com.google.common.primitives.Ints;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.Random;

public class JoinLeaveListener implements Listener {


    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        //check profile for stuff

        if(Main.getInstance().getAuthManager().getUserId(e.getPlayer().getUniqueId()) != null){
            e.getPlayer().sendMessage(ChatColor.RED + "Please enter the code sent to your discord DMs by using /code [code]");
            Main.getInstance().getAuthManager().getPending().add(e.getPlayer().getUniqueId());
            User user = Main.getInstance().getBot().getBot().getUserById(Main.getInstance().getAuthManager().getUserId(e.getPlayer().getUniqueId()));
            //TODO: generate random code

            Random r = new Random();

            String code = "";

            code = code + r.nextInt(9);
            code = code + r.nextInt(9);
            code = code + r.nextInt(9);
            code = code + r.nextInt(9);

            BotUtil.sendPrivateMessage(user, "Your code is " + code);
            Main.getInstance().getAuthManager().getCodes().put(e.getPlayer().getUniqueId(), Ints.tryParse(code));


        }
    }

    @EventHandler
    public void onMove(PlayerMoveEvent e){
        if(Main.getInstance().getAuthManager().getPending().contains(e.getPlayer().getUniqueId())) {
            if (e.getTo().getBlockX() != e.getFrom().getBlockX() || e.getTo().getBlockZ() != e.getFrom().getBlockZ()) {
                e.getPlayer().teleport(e.getFrom());
            }
        }
    }

}
