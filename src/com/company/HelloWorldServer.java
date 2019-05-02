package com.company;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class HelloWorldServer {

    private HttpServer server;
    private InetSocketAddress socket;
    private final int port;
    private NameRepository names;

    // create repository which stores names, inject it into handlers. The handlers will interact with the stored list via repositorys

    public HelloWorldServer(int port){
        this.port = port;
    }

    public void createServer() throws IOException {
        this.socket = new InetSocketAddress(port);
        this.server = HttpServer.create(socket, 0);
        this.server.createContext("/", new DefaultHandler());
        this.server.createContext("/names/", new NameHandler(names));
        this.server.setExecutor(null);
        this.server.start();
    }

    public void stopServer() {
        this.server.stop(0);
    }

}
