package com.company;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;

public class RootHandlerTests {

    @Test
    public void aNameCanBeAddedToTheResponse() throws IOException {
        HelloWorldServer helloWorldServer = new HelloWorldServer(8080);

        helloWorldServer.createServer();

        URL urlPost = new URL("http://localhost:8080/names/fiona");
        HttpURLConnection connection = (HttpURLConnection) urlPost.openConnection();
        connection.setRequestMethod("POST");

        int responseCode = connection.getResponseCode();
        connection.disconnect();

        assertEquals(201, responseCode);

        URL urlPost2 = new URL("http://localhost:8080/");
        HttpURLConnection connection2 = (HttpURLConnection) urlPost2.openConnection();
        connection2.setRequestMethod("GET");

        int responseCode2 = connection2.getResponseCode();

        var bytes = connection2.getInputStream().readAllBytes();
        var response = new String(bytes);

        var expectedRegex = Pattern.compile("Hello Bianca and Fiona - the time on the server is [0-9]{1,2}:[0-9]{2}(am|pm) on [0-3]?[0-9] \\w+ [0-9]{4}");

        var responseMatches = expectedRegex.matcher(response).matches();
        assertEquals(200, responseCode2 );

        assertTrue(responseMatches, "\n" + response+ "\n" + expectedRegex.toString());

    }

    @Test
    public void TwoNamesCanBeAddedToTheResponse() throws IOException {
        HelloWorldServer helloWorldServer = new HelloWorldServer(8080);

        helloWorldServer.createServer();

        URL urlPost = new URL("http://localhost:8080/names/fiona");
        HttpURLConnection connection = (HttpURLConnection) urlPost.openConnection();
        connection.setRequestMethod("POST");

        int responseCode = connection.getResponseCode();
        connection.disconnect();

        assertEquals(201, responseCode);

        URL urlPost2 = new URL("http://localhost:8080/names/renae");
        HttpURLConnection connection2 = (HttpURLConnection) urlPost2.openConnection();
        connection2.setRequestMethod("POST");

        int responseCode2 = connection2.getResponseCode();
        connection2.disconnect();

        assertEquals(201, responseCode2);

        URL urlGet = new URL("http://localhost:8080/");
        HttpURLConnection connection3 = (HttpURLConnection) urlGet.openConnection();
        connection3.setRequestMethod("GET");

        int responseCode3 = connection3.getResponseCode();

        var bytes = connection3.getInputStream().readAllBytes();
        var response = new String(bytes);

        var expectedRegex = Pattern.compile("Hello Bianca, Fiona and Renae - the time on the server is [0-9]{1,2}:[0-9]{2}(am|pm) on [0-3]?[0-9] \\w+ [0-9]{4}");

        var responseMatches = expectedRegex.matcher(response).matches();
        assertEquals(200, responseCode3 );

        assertTrue(responseMatches, "\n" + response+ "\n" + expectedRegex.toString());

    }

}
// 2 tests: name added to response and name can be read from response
// string compare to the regex