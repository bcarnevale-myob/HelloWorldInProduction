package com.company;

public class MockDateTime implements DateTime {

    @Override
    public String getCurrentDate() {
        return "15 April 2019";
    }

    @Override
    public String getCurrentTime() {
        return "11:11am";
    }
}
