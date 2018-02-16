package org.reverendracing.tzbot.util;

public class TimeData {


    private String hour;
    private String minute;
    private String amPm;
    private String offset;

    public TimeData() {
    }

    public TimeData(String hour, String minute, String amPm) {
        this.hour = hour;
        this.minute = minute;
        this.amPm = amPm;
        this.offset = "";
    }

    public TimeData(String hour, String minute, String amPm, String offset) {
        this.hour = hour;
        this.minute = minute;
        this.amPm = amPm;
        this.offset = offset;
    }

    public String getHour() {
        return hour;
    }

    public String getMinute() {
        return minute;
    }

    public String getAmPm() {
        return amPm;
    }

    public String getOffset() {
        return offset;
    }
}
