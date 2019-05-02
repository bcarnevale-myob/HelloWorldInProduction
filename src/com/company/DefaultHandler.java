package com.company;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;

public class DefaultHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange request) throws IOException {
        switch(request.getRequestMethod()){
            case "GET":
                getHandler(request);
            default:
                notFound(request);
        }
        request.close();

    }

    private void getHandler(HttpExchange request) throws IOException {
        CurrentDateTime dateTime = new CurrentDateTime();
        String response = "Hello Bianca - the time on the server is "  + dateTime.getCurrentTime() + " on " + dateTime.getCurrentDate();
        request.sendResponseHeaders(200, response.length());
        OutputStream outputStream = request.getResponseBody();
        outputStream.write(response.getBytes());
        outputStream.close();
    }

    private void notFound(HttpExchange request) throws IOException {
        request.sendResponseHeaders(404,0);
    }

}

