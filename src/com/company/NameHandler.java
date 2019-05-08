package com.company;

import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;

public class NameHandler extends HelloWorldHandler {

    public NameHandler(NameRepository names) {
        super(names);
    }

    @Override
    public void handle(HttpExchange request) throws IOException {
        switch(request.getRequestMethod()){
            case "GET":
                getHandler(request);
                break;
            case "POST":
                postHandler(request);
                break;
            case "PUT":
                putHandler(request);
                break;
            case "DELETE":
                deleteHandler(request);
                break;
            default:
                notFound(request);
        }
        request.close();
    }

    private void getHandler(HttpExchange request) throws IOException {
        String response = String.valueOf(names.get());
        request.sendResponseHeaders(202, response.length());
        OutputStream outputStream = request.getResponseBody();
        outputStream.write(response.getBytes());
        outputStream.close();
    }

    private void postHandler(HttpExchange request) throws IOException {
        request.sendResponseHeaders(201, 0);

        String nameFromPath = nameFromPath(request);

        names.add(capitalise(nameFromPath));
    }

    private void putHandler(HttpExchange request) throws IOException {
        request.sendResponseHeaders(204, 0);

        String path = request.getRequestURI().getPath();
        String[] pathTokens = path.split("/");

        String nameToRemove = pathTokens[pathTokens.length - 2];
        String nameToReplace = pathTokens[pathTokens.length - 1];

        names.remove(capitalise(nameToRemove));
        names.add(capitalise(nameToReplace));

    }

    private void deleteHandler(HttpExchange request) throws IOException {
        request.sendResponseHeaders(200, 0);

        String nameFromPath = nameFromPath(request);

        names.remove(capitalise(nameFromPath));
    }

    private void notFound(HttpExchange request) throws IOException {
        request.sendResponseHeaders(404,0);
    }

    private String nameFromPath(HttpExchange request) {
        String path = request.getRequestURI().getPath();
        String[] pathTokens = path.split("/");
        return pathTokens[pathTokens.length - 1];
    }

    private String capitalise(String word) {
        return word.substring(0,1).toUpperCase() + word.substring(1).toLowerCase();
    }

}
