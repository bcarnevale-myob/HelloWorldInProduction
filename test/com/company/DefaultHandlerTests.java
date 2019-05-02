package com.company;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DefaultHandlerTests {

    @Test
    public void aNameCanBeAddedToTheResponse() throws IOException {
        HelloWorldServer helloWorldServer = new HelloWorldServer(8080);

        helloWorldServer.createServer();

        URL urlPost = new URL("http://localhost:8080/names/fiona");
        HttpURLConnection connection = (HttpURLConnection) urlPost.openConnection();
        connection.setRequestMethod("POST");

        connection.setRequestMethod("GET");

        int responseCode = connection.getResponseCode();

        var bytes = connection.getInputStream().readAllBytes();
        var response = new String(bytes);

        var expectedRegex = Pattern.compile("Hello Bianca & Fiona - the time on the server is [0-9]{1,2}:[0-9]{2}(am|pm) on [0-3]?[0-9] \\w+ [0-9]{4}");

        var responseMatches = expectedRegex.matcher(response).matches();

        assertEquals(200, responseCode);
        assertTrue(responseMatches);
    }

}
