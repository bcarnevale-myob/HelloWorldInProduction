package com.company;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RequestHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange request) throws IOException {
        String currentTime = currentTime();
        String currentDate = currentDate();
        String response = "Hello Bianca - the time on the server is "  + currentTime + " on " + currentDate;
        request.sendResponseHeaders(200, response.length());
        OutputStream outputStream = request.getResponseBody();
        outputStream.write(response.getBytes());
        outputStream.close();
    }

    public String currentTime() {
        DateFormat timeFormat = new SimpleDateFormat("HH:mm");
        Date date = new Date();
        return timeFormat.format(date);
    }

    public String currentDate() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        return dateFormat.format(date);
    }

}

