package com.company;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CurrentDateTime {

    private String formatCurrentDate() {
        DateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy");
        Date date = new Date();
        return dateFormat.format(date);
    }

    private String formatCurrentTime() {
        DateFormat timeFormat = new SimpleDateFormat("HH:mma");
        Date date = new Date();
        return timeFormat.format(date);
    }

    public String getCurrentDateAndTime() {
        return formatCurrentTime() + " on " + formatCurrentDate();
    }

}
