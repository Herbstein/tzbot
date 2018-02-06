package org.reverendracing.tzbot;

import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.Javacord;
import de.btobastian.javacord.listener.message.MessageCreateListener;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class tzbot {
    private static final String botToken = "NDA5NzA0MjM1NjUxODI1NjY0.DVieYg.5GjZJJgWgO3HLCND7M49e29qmMc";

    public static void main(String[] args) {
        DiscordAPI api = Javacord.getApi(botToken, true);
        api.connectBlocking();

        api.registerListener((MessageCreateListener) (discordAPI, message) -> {
            if (message.getContent().startsWith("!tzd")) {
                String info = message.getContent().substring(5);

                ZoneId est = ZoneId.of("America/New_York");
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                LocalDateTime localDateAndTime = LocalDateTime.parse(info, formatter);
                ZonedDateTime dateAndTimeEST = ZonedDateTime.of(localDateAndTime, est);

                message.reply(dateAndTimeEST.toLocalDateTime().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME) + " GMT+1");
            } else if (message.getContent().startsWith("!tzt")) {
                String info = message.getContent().substring(5);

                Integer hour = Integer.parseInt(info.substring(0, 2));

                int rollover = 0;

                Integer minute;
                if (info.charAt(2) == ':') {
                    minute = Integer.parseInt(info.substring(3, 5));
                    rollover = 1;
                } else {
                    minute = Integer.parseInt(info.substring(2, 4));
                }

                String timeString = info.substring(5 + rollover, 7 + rollover);

                Time time = null;
                if (timeString.equalsIgnoreCase("am")) {
                    time = Time.AM;
                } else if (timeString.equalsIgnoreCase("pm")) {
                    time = Time.PM;
                }

                Instant instant = Instant.parse(hour.toString() + ":" + minute + " " + (time != null ? time.toString() : ""));

                //message.reply();

            }
        });
    }

    private enum Time {
        AM, PM
    }
}
