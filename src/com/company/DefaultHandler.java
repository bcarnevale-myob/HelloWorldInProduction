package com.company;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;

public class DefaultHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange request) throws IOException {
        switch(request.getRequestMethod()){
            case "GET":
                getHandler(request);
            case "POST":
                postHandler(request);
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

    private void postHandler(HttpExchange request) throws IOException {
        request.sendResponseHeaders(201, 0);

        if (request.getResponseCode() == HttpURLConnection.HTTP_CREATED) {
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    postConnection.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in .readLine()) != null) {
                response.append(inputLine);
            } in .close();
            System.out.println(response.toString());
        } else {
            System.out.println("POST NOT WORKED");
        }
    }

    private void notFound(HttpExchange request) throws IOException {
        request.sendResponseHeaders(404,0);
    }

}

