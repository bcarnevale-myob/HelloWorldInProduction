package com.company;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class HelloWorldServer {

    private HttpServer server;
    private final int port;

    public HelloWorldServer(int port){
        this.port = port;
    }

    public void start() throws IOException {
        this.server = HttpServer.create(new InetSocketAddress(port), 0);
    }
}
