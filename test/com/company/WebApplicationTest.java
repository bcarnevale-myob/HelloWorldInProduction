package com.company;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

public class WebApplicationTest {

    @Test
    public void canCreateConnection() throws IOException {

        HelloWorldServer helloWorldServer = new HelloWorldServer(8080);
        helloWorldServer.createServer();

        assertDoesNotThrow(() -> {
            URL url = new URL("http://localhost:8080");
            URLConnection connection = url.openConnection();
            connection.connect();
        });

    }

}
