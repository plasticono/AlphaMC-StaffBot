package co.alphamc.staffbot;

import net.dv8tion.jda.core.MessageBuilder;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;

import java.util.List;
import java.util.stream.Collectors;

public class BotUtil {

    public static  void sendPrivateMessage(User user, String content) {
        // openPrivateChannel provides a RestAction<PrivateChannel>
        // which means it supplies you with the resulting channel
        user.openPrivateChannel().queue((channel) ->
        {
            channel.sendMessage(content).queue();
        });
    }

    public static void sendChannelMessage(MessageChannel channel, String content){
        channel.sendMessage(content).queue();
    }
    public static void sendReactedChannelMessage(MessageChannel channel, String content){
        MessageBuilder messageBuilder = new MessageBuilder();
        messageBuilder.setContent(content);
        Message message = messageBuilder.build();
        channel.sendMessage(message).queue(message1 -> {
            message1.addReaction("✔").queue();
        });


    }
}
