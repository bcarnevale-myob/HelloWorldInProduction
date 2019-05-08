package com.company;

import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

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

    private String greeting() {
        var currentDateAndTime = new CurrentDateTime().getCurrentDateAndTime();
        return "Hello " + getNamesToGreet() + " - the time on the server is "  + currentDateAndTime;
    }

    public String getNamesToGreet() {
        List<String> allNames = names.get();
        int numberOfNames = allNames.size();
        String initialName = allNames.get(0);
        String andNames = "";

        for(int i = 0; i < numberOfNames; i++) {
            if(!(i == numberOfNames - 1) && !(allNames.get(i).equals(initialName))) {
                andNames += ", " + allNames.get(i);
            } else if((i == numberOfNames - 1) && !(allNames.get(i).equals(initialName))) {
                andNames += " and " + allNames.get(i);
            }
        }

        return initialName + andNames;
    }

}

