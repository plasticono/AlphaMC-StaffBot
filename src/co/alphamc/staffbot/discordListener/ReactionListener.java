package co.alphamc.staffbot.discordListener;

import co.alphamc.staffbot.Main;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import net.dv8tion.jda.core.requests.RestAction;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import javax.annotation.Nonnull;


public class ReactionListener extends ListenerAdapter {

    public void onMessageReactionAdd​(@Nonnull MessageReactionAddEvent event){
        if(event.getChannel().getName().equalsIgnoreCase("auth-output")) {
            User user = event.getUser();
            RestAction<Message> restmessage = event.getChannel().getMessageById(event.getMessageId());
            Message message = restmessage.complete();
            if(user != message.getAuthor()) {
                String name = message.getContentDisplay().replaceAll(", please react to this message to link your account.", "");
                Player target = Bukkit.getPlayer(name);
                if (target != null) {
                    Main.getInstance().getAuthManager().getUserIds().put(target.getUniqueId(), user.getId());
                    target.sendMessage(ChatColor.GREEN + "Your account is now linked to: " + user.getName());
                }
            }

            event.getChannel().deleteMessageById(message.getId()).queue();
        }
    }

}
