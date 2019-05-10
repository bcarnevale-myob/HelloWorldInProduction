package com.company;

import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

public class RootHandler extends HelloWorldHandler {

    public RootHandler(UserRepository names) {
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

    private String greeting() {
        var currentDateAndTime = new CurrentDateTime().getCurrentDateAndTime();
        return "Hello " + getNamesToGreet() + " - the time on the server is "  + currentDateAndTime;
    }

    private String getNamesToGreet() {
        List<String> allNames = users.get();
        int numberOfNames = allNames.size();
        String initialName = allNames.get(0);
        String andNames = "";

        for(int i = 0; i < numberOfNames; i++) {
            if(!(i == numberOfNames - 1) && !(allNames.get(i).equals(initialName))) {
                andNames += ", " + capitalise(allNames.get(i));
            } else if((i == numberOfNames - 1) && !(allNames.get(i).equals(initialName))) {
                andNames += " and " + capitalise(allNames.get(i));
            }
        }

        return capitalise(initialName) + andNames;
    }

    private String capitalise(String word) {
        return word.substring(0,1).toUpperCase() + word.substring(1).toLowerCase();
    }

}

