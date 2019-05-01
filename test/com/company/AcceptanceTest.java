package com.company;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.*;

class AcceptanceTest {

    @Test
    public void helloWorldAcceptanceTest() throws IOException {

        HelloWorldServer helloWorldServer = new HelloWorldServer(8080);

        helloWorldServer.createServer();

        URL url = new URL("http://localhost:8080/");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        connection.setRequestMethod("GET");

        int responseCode = connection.getResponseCode();

        var bytes = connection.getInputStream().readAllBytes();
        var response = new String(bytes);

//        var doesMatch = Regex.Matches(Hello Bianca - the time on the server is [0-9]{1,2}:[0-9]{2}(am|pm) on [0-3]?[0-9] \w+ [0-9]{4})
        assertEquals(200, responseCode);
        assertEquals("Hello Bianca - the time on the server is [0-9]{1,2}:[0-9]{2}(am|pm) on [0-3]?[0-9] \\w+ [0-9]{4}", response);
    }
    

}