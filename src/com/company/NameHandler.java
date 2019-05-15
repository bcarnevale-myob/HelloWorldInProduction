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
            switch (request.getRequestMethod()) {
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
        String response = names.toString();
        OutputStream outputStream = request.getResponseBody();
        request.sendResponseHeaders(200, response.length());
        outputStream.write(response.getBytes());
        outputStream.close();
    }

    private void postHandler(HttpExchange request) throws IOException {
        names.add(nameFromPath(request));
        request.sendResponseHeaders(201, 0);
    }

    private void putHandler(HttpExchange request) throws IOException {

        String path = request.getRequestURI().getPath();
        String[] pathTokens = path.split("/");

        request.sendResponseHeaders(204, -1);

        String oldName = pathTokens[pathTokens.length - 2];
        String newName = pathTokens[pathTokens.length - 1];

        names.remove(oldName);
        names.add(newName);

    }

    private void deleteHandler(HttpExchange request) throws IOException {
        names.remove(nameFromPath(request));
        request.sendResponseHeaders(202, 0);
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
