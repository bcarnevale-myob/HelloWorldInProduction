package com.company;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;

public class MyHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange request) throws IOException {
        String response = "Hello Bianca";
        request.sendResponseHeaders(200, response.length());
        OutputStream outputStream = request.getResponseBody();
        outputStream.write(response.getBytes());
        outputStream.close();
    }
}

