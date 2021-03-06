package com.company;

import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;

public class UserHandler extends HelloWorldHandler {

    public UserHandler(UserRepository users) {
        super(users);
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
        String response = String.valueOf(users.get());
        request.sendResponseHeaders(200, response.length());
        OutputStream outputStream = request.getResponseBody();
        outputStream.write(response.getBytes());
        outputStream.close();
    }

    private void postHandler(HttpExchange request) throws IOException {
        request.sendResponseHeaders(201, 0);

        users.add(nameFromPath(request));
    }

    private void putHandler(HttpExchange request) throws IOException {
        request.sendResponseHeaders(204, 0);

        String path = request.getRequestURI().getPath();
        String[] pathTokens = path.split("/");

        String oldName = pathTokens[pathTokens.length - 2];
        String newName = pathTokens[pathTokens.length - 1];

        users.remove(oldName);
        users.add(newName);

    }

    private void deleteHandler(HttpExchange request) throws IOException {
        request.sendResponseHeaders(202, 0);

        users.remove(nameFromPath(request));
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
