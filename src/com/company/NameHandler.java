package com.company;

import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;

public class NameHandler extends HelloWorldHandler {

    public NameHandler(NameRepository names) {
        super(names);
    }

    @Override
    public void handle(HttpExchange request) throws IOException {
        switch(request.getRequestMethod()){
            case "POST":
                postHandler(request);
            default:
                notFound(request);
        }
        request.close();
    }

    private void postHandler(HttpExchange request) throws IOException {
        request.sendResponseHeaders(201, 0);

        String path = request.getRequestURI().getPath();

        String[] pathTokens = path.split("/");

        String name = pathTokens[pathTokens.length - 1];

        names.add(name);
    }

    private void notFound(HttpExchange request) throws IOException {
        request.sendResponseHeaders(404,0);
    }

}
