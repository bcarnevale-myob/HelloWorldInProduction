package com.company;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        HelloWorldServer helloWorldServer = new HelloWorldServer(8080, "bianca");
        helloWorldServer.createServer();
    }
}
