package com.company;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.regex.Pattern;

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

        var expectedRegex = Pattern.compile("Hello Bianca - the time on the server is [0-9]{1,2}:[0-9]{2}(am|pm) on [0-3]?[0-9] \\w+ [0-9]{4}");

        var responseMatches = expectedRegex.matcher(response).matches();

        assertEquals(200, responseCode);
        assertTrue(responseMatches);
    }

    @Test
    public void canAddANameToResponseWhenCallingTheAppropriateHttpRequest() throws IOException {
        HelloWorldServer helloWorldServer = new HelloWorldServer(8080);

        helloWorldServer.createServer();

        URL url = new URL("http://localhost:8080/");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        String POST_PARAMS = "Fiona";

        connection.setRequestMethod("POST");
        OutputStream os = connection.getOutputStream();
        os.write(POST_PARAMS.getBytes());
        os.flush();
        os.close();

        int responseCode = connection.getResponseCode();

        var bytes = connection.getInputStream().readAllBytes();
        var response = new String(bytes);

        var expectedRegex = Pattern.compile("Hello Bianca and Fiona - the time on the server is [0-9]{1,2}:[0-9]{2}(am|pm) on [0-3]?[0-9] \\w+ [0-9]{4}");

        var responseMatches = expectedRegex.matcher(response).matches();

        assertEquals(200, responseCode);
        assertTrue(responseMatches);
    }

}