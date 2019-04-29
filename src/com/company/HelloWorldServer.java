package com.company;

import com.sun.net.httpserver.HttpServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.URL;

public class HelloWorldServer {

    private HttpServer server;
    private InetSocketAddress socket;
    private final int port;

    public HelloWorldServer(int port){
        this.port = port;
    }

    public void createServer() throws IOException {
        this.socket = new InetSocketAddress(port);
        this.server = HttpServer.create(socket, 0);
        this.server.createContext("/test", new MyHandler());
        this.server.setExecutor(null);
        this.server.start();
    }

    public void stopServer() {
        this.server.stop(0);
    }

}
