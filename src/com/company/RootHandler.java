package com.company;

import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;

public class RootHandler extends HelloWorldHandler {

    public RootHandler(NameRepository names) {
        super(names);
    }

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
        var dateTime = new CurrentDateTime();
        String response = "Hello Bianca " + filterNames() + " - the time on the server is "  + dateTime.getCurrentTime() + " on " + dateTime.getCurrentDate();
        request.sendResponseHeaders(200, response.length());
        OutputStream outputStream = request.getResponseBody();
        outputStream.write(response.getBytes());
        outputStream.close();
    }

    private void notFound(HttpExchange request) throws IOException {
        request.sendResponseHeaders(404,0);
    }

    private String filterNames() {
        String andNames = "";

        for(int i = 0; i < names.get().size(); i++) {
           andNames += "& " + names.get().get(i);
        }

        return andNames;
    }

}

