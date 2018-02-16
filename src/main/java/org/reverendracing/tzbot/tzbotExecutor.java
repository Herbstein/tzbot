package org.reverendracing.tzbot;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

import de.btobastian.sdcf4j.Command;
import de.btobastian.sdcf4j.CommandExecutor;

import org.reverendracing.tzbot.util.TimeData;

public class tzbotExecutor implements CommandExecutor {

    @Command(aliases = "!info", description = "Shows some information about the bot.", usage = "!info")
    public String onInfoCommand(String[] args) {

        return "Bot Usage: !tz <time 12 hr or 24hr> <GMT timezone offset. If blank, defaults to GMT-5 (EST)>.\n"
                + "Example Usage: !tz **4:00pm** **GMT-5**\n"
                + "If it's <time> in <timezone> right now, what time is it in GMT?";
    }

    @Command(aliases = "!tz", description = "Convert from entered time and timezone to GMT/UTC", usage = "!tz <time 12 hr or 24hr> <GMT timezone offset. If blank, defaults to GMT-5 (EST)>")
    public String onTimeConversion(String[] args) {

        TimeData timeData;

        if (args.length == 2) {
            String[] timeSplit = args[0].split(":");
            timeData = new TimeData(timeSplit[0], timeSplit[1].substring(0, 2),
                    timeSplit[1].substring(2), args[1]);
        } else if (args.length == 1) {
            String[] timeSplit = args[0].split(":");
            timeData = new TimeData(timeSplit[0], timeSplit[1].substring(0, 2),
                    timeSplit[1].substring(2));
        } else {
            return "Yeah. No clue. Try again";
        }

        Integer hour = Integer.parseInt(timeData.getHour());
        Integer minute = Integer.parseInt(timeData.getMinute());
        if (timeData.getAmPm() != null || !timeData.getAmPm().equals("")) {
            if (timeData.getAmPm().equalsIgnoreCase("am") && hour < 12) {
                hour += 12;
            } else if (timeData.getAmPm().equalsIgnoreCase("pm") && hour < 12) {
                hour += 12;
            }
        }
        ZoneId offset = getOffset(timeData);

        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.of(hour, minute);
        ZonedDateTime inputOffsetDateTime =
                ZonedDateTime.of(LocalDateTime.of(date, time), offset);
        ZonedDateTime utcDateTime = inputOffsetDateTime.withZoneSameInstant(ZoneOffset.UTC);

        return "Input time: " + getStringFromZdt(inputOffsetDateTime)
                + "\nGMT time: " + getStringFromZdt(utcDateTime);

    }

    @Command(aliases = "!tzd", description = "Convert from entered time, date and timezone to GMT/UTC", usage = "!tzd <time 12 hr or 24hr> <GMT timezone offset. If blank, defaults to GMT-5 (EST)>")
    public String onTimeDateConversion(String[] args) {
        /*
        String info = message.getContent().substring(5);

                    ZoneId est = ZoneId.of("America/New_York");
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                    LocalDateTime localDateAndTime = LocalDateTime.parse(info, formatter);
                    ZonedDateTime dateAndTimeEST = ZonedDateTime.of(localDateAndTime, est);

                    message.reply(
                            dateAndTimeEST.toLocalDateTime().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)
                                    + " GMT+1");
         */
        return "404. Too soon.";
    }

    private String getStringFromZdt(ZonedDateTime zdt) {

        return zdt.toString();
    }

    public ZoneId getOffset(TimeData data) {

        if(data.getOffset().equals(""))
            return ZoneId.of("GMT-5");

        if(data.getOffset().startsWith("GMT"))
            return ZoneId.of(data.getOffset());

        return ZoneId.of(data.getOffset());
    }

}
