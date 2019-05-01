package com.company;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CurrentDateTime implements DateTime {
    @Override
    public String getCurrentDate() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        return dateFormat.format(date);
    }

    @Override
    public String getCurrentTime() {
        DateFormat timeFormat = new SimpleDateFormat("HH:mm");
        Date date = new Date();
        return timeFormat.format(date);
    }

}
