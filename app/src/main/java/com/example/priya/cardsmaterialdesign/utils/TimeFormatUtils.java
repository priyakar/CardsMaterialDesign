package com.example.priya.cardsmaterialdesign.utils;

public class TimeFormatUtils {

    public static String getFormattedTime(int hour, int minute) {

        StringBuilder stringBuffer = new StringBuilder();
        boolean isPm = false;
        if (hour >= 12) {
            isPm = true;
        }
        if (hour > 12) {
            hour = hour - 12;
        }
        stringBuffer.append(hour)
                .append(":");
        if (minute < 10) {
            stringBuffer.append("0");
        }
        stringBuffer.append(minute);
        stringBuffer.append(" ");
        if (isPm) {
            stringBuffer.append("PM");
        } else {
            stringBuffer.append("AM");
        }
        return stringBuffer.toString();
    }
}
