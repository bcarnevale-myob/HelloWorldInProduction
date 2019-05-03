package com.company;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class HelloWorldServer {

    private HttpServer server;
    private InetSocketAddress socket;
    private final int port;
    private NameRepository names;

    public HelloWorldServer(int port){
        this.port = port;
        this.names = new InMemoryNameRepository();
    }

    public void createServer() throws IOException {
        this.socket = new InetSocketAddress(port);
        this.server = HttpServer.create(socket, 0);
        this.server.createContext("/", new RootHandler(names));
        this.server.createContext("/names/", new NameHandler(names));
        this.server.setExecutor(null);
        this.server.start();
    }

    public void stopServer() {
        this.server.stop(0);
    }

}
