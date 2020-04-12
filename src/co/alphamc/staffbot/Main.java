package co.alphamc.staffbot;

import co.alphamc.staffbot.command.CodeCommand;
import co.alphamc.staffbot.command.LinkCommand;
import co.alphamc.staffbot.discordListener.ReactionListener;
import co.alphamc.staffbot.util.ClassUtil;
import com.pzg.www.discord.object.Bot;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    private Bot bot;
    private static Main instance;
    private AuthManager authManager;


    @Override
    public void onEnable(){
        instance = this;
        bot = new Bot("Njk4Njk4NTE3MjI0NDg5MDYw.XpJqeQ.OU8vx1eeSCUn8vkkjKpBQN-VYwk", "!");

        this.getConfig().options().copyDefaults(true);
        this.saveConfig();

        this.authManager = new AuthManager();

        bot.getBot().addEventListener(new ReactionListener());


        getCommand("link").setExecutor(new LinkCommand());
        getCommand("code").setExecutor(new CodeCommand());

        try {
            registerListeners();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

    }

    private void registerListeners() throws IllegalAccessException, InstantiationException {
        PluginManager pm = getServer().getPluginManager();
        for (Class clazz : ClassUtil.getClassesInPackage(this, "co.alphamc.staffbot.listener")) {
            for (int i = 0; i < clazz.getInterfaces().length; i++) {
                if (clazz.getInterfaces()[i].equals(Listener.class)) {
                    pm.registerEvents((Listener) clazz.newInstance(), this);
                }
            }
        }
    }

    @Override
    public void onDisable(){

        authManager.saveIds();
        instance = null;
    }

    public Bot getBot() {
        return bot;
    }

    public static Main getInstance() {
        return instance;
    }

    public AuthManager getAuthManager() {
        return authManager;
    }
}
