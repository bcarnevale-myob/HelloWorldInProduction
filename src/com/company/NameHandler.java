package com.company;

import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;

public class NameHandler extends HelloWorldHandler {

    public NameHandler(UserRepository names) {
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
        request.sendResponseHeaders(200, response.length());
        OutputStream outputStream = request.getResponseBody();
        outputStream.write(response.getBytes());
        outputStream.close();
    }

    private void postHandler(HttpExchange request) throws IOException {
        request.sendResponseHeaders(201, 0);

        names.add(nameFromPath(request));
    }

    private void putHandler(HttpExchange request) throws IOException {
        request.sendResponseHeaders(204, 0);

        String path = request.getRequestURI().getPath();
        String[] pathTokens = path.split("/");

        String oldName = pathTokens[pathTokens.length - 2];
        String newName = pathTokens[pathTokens.length - 1];

        names.remove(oldName);
        names.add(newName);

    }

    private void deleteHandler(HttpExchange request) throws IOException {
        request.sendResponseHeaders(202, 0);

        names.remove(nameFromPath(request));
    }

    private void notFound(HttpExchange request) throws IOException {
        request.sendResponseHeaders(404,0);
    }

    private String nameFromPath(HttpExchange request) {
        String path = request.getRequestURI().getPath();
        String[] pathTokens = path.split("/");
        return pathTokens[pathTokens.length - 1];
    }

}
