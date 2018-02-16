package org.reverendracing.tzbot;

import de.btobastian.javacord.DiscordApi;
import de.btobastian.javacord.DiscordApiBuilder;
import de.btobastian.sdcf4j.CommandHandler;
import de.btobastian.sdcf4j.handler.JavacordHandler;


public class tzbot {
    private static final String botToken = "NDA5NzA0MjM1NjUxODI1NjY0.DVieYg.5GjZJJgWgO3HLCND7M49e29qmMc";

    public static void main(String[] args) {

        DiscordApi api = new DiscordApiBuilder().setToken(botToken).login().join();

        CommandHandler handler = new JavacordHandler(api);
        handler.registerCommand(new tzbotExecutor());

    }


}
