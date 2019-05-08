package com.company;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;

class AcceptanceTest {

    private HelloWorldServer helloWorldServer;

    @BeforeEach
    public void setUp() throws IOException {
        this.helloWorldServer = new HelloWorldServer(8080);
        this.helloWorldServer.createServer();
    }

    @AfterEach
    public void tearDown() {
        this.helloWorldServer.stopServer();
    }

    @Test
    public void helloWorldAcceptanceTest() throws IOException {

        URL url = new URL("http://localhost:8080/");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        connection.setRequestMethod("GET");

        int responseCode = connection.getResponseCode();

        var bytes = connection.getInputStream().readAllBytes();
        var response = new String(bytes);

        var expectedRegex = Pattern.compile("Hello Bianca - the time on the server is [0-9]{1,2}:[0-9]{2}(am|pm) on [0-3]?[0-9] \\w+ [0-9]{4}");

        var responseMatches = expectedRegex.matcher(response).matches();

        assertEquals(200, responseCode);
        assertTrue(responseMatches, "\n" + response+ "\n" + expectedRegex.toString());

    }

}