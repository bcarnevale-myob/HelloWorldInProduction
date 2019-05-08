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
        String response = greeting();
        request.sendResponseHeaders(200, response.length());
        OutputStream outputStream = request.getResponseBody();
        outputStream.write(response.getBytes());
        outputStream.close();
    }

    private void notFound(HttpExchange request) throws IOException {
        request.sendResponseHeaders(404,0);
    }

    private String filterNames() {
        String myName = names.get().get(0);
        String andNames = "";

        for(int i = 0; i < names.get().size(); i++) {
            if(!(i == names.get().size() - 1) && !(names.get().get(i).equals("Bianca"))) {
                andNames += ", " + names.get().get(i);
            } else if((i == names.get().size() - 1) && !(names.get().get(i).equals("Bianca"))) {
                andNames += " and " + names.get().get(i);
            }
        }

        return myName + andNames;
    }

    private String greeting() {
        return "Hello " + filterNames() + " - the time on the server is "  + getCurrentDateAndTime();
    }

    private String getCurrentDateAndTime() {
        var dateTime = new CurrentDateTime();
        return dateTime.getCurrentTime() + " on " + dateTime.getCurrentDate();
    }

}

