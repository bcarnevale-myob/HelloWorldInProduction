package com.company;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.net.*;

public class WebApplicationTest {

    private HelloWorldServer helloWorldServer;

    @BeforeEach
    public void setUp() throws IOException {
        this.helloWorldServer = new HelloWorldServer(8080, "bianca");
        this.helloWorldServer.createServer();
    }

    @AfterEach
    public void tearDown() {
        this.helloWorldServer.stopServer();
    }

    @Test
    public void canCreateConnection() {
        assertDoesNotThrow(() -> {
            URL url = new URL("http://localhost:8080");
            URLConnection connection = url.openConnection();
            connection.connect();
        });
    }

    @Test
    public void rootEndpointAllowsGetRequests() throws IOException {
        URL url = new URL("http://localhost:8080/");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        int responseCode = connection.getResponseCode();
        assertEquals(200, responseCode);
    }

    @Test
    public void rootEndpointDoesNotAllowPostRequests() throws IOException {
        URL url = new URL("http://localhost:8080/");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        int responseCode = connection.getResponseCode();
        assertEquals(404, responseCode);
    }

    @Test
    public void nameEndPointAllowsPostRequest() throws IOException {
        URL url = new URL("http://localhost:8080/users/");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        int responseCode = connection.getResponseCode();
        assertEquals(201, responseCode);
    }

    @Test
    public void nameEndPointAllowsDeleteRequest() throws IOException {
        URL url = new URL("http://localhost:8080/users/");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("DELETE");
        int responseCode = connection.getResponseCode();
        assertEquals(202, responseCode);
    }

    @Test
    public void nameEndPointAllowsGetRequest() throws IOException {
        URL url = new URL("http://localhost:8080/users/");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        int responseCode = connection.getResponseCode();
        assertEquals(200, responseCode);
    }

    @Test
    public void nameEndPointAllowsPutRequest() throws IOException {
        URL url = new URL("http://localhost:8080/users/");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("PUT");
        int responseCode = connection.getResponseCode();
        assertEquals(204, responseCode);
    }

}
