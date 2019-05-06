package com.company;

import com.sun.net.httpserver.HttpExchange;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class NameHandlerTests {

    @Test
    public void PostRequestsAddsNameToTheNameRepository() throws IOException, URISyntaxException {
        NameRepository names = new InMemoryNameRepository();

        NameHandler userHandler = new NameHandler(names);

        HttpExchange mockRequest = Mockito.mock(HttpExchange.class);

        when(mockRequest.getRequestMethod()).thenReturn("POST");
        when(mockRequest.getRequestURI()).thenReturn(new URI("/names/bianca"));

        userHandler.handle(mockRequest);

        verify(mockRequest).sendResponseHeaders(201, 0);
        verify(mockRequest).close();

        assertTrue(names.get().contains("Bianca"));

    }

    @Test
    public void PostRequestsAddTwoNamesToTheNameRepository() throws IOException, URISyntaxException {
        NameRepository names = new InMemoryNameRepository();

        NameHandler userHandler = new NameHandler(names);

        HttpExchange mockRequest1 = Mockito.mock(HttpExchange.class);

        when(mockRequest1.getRequestMethod()).thenReturn("POST");
        when(mockRequest1.getRequestURI()).thenReturn(new URI("/names/bianca"));
        userHandler.handle(mockRequest1);

        verify(mockRequest1).sendResponseHeaders(201, 0);
        verify(mockRequest1).close();

        HttpExchange mockRequest2 = Mockito.mock(HttpExchange.class);

        when(mockRequest2.getRequestMethod()).thenReturn("POST");
        when(mockRequest2.getRequestURI()).thenReturn(new URI("/names/fiona"));

        userHandler.handle(mockRequest2);

        verify(mockRequest2).sendResponseHeaders(201, 0);
        verify(mockRequest2).close();

        assertTrue(names.get().contains("Bianca"));
        assertTrue(names.get().contains("Fiona"));

    }

    @Test
    public void DeleteRequestsRemovesANameFromTheNameRepository() throws IOException, URISyntaxException {
        NameRepository names = new InMemoryNameRepository();

        NameHandler userHandler = new NameHandler(names);

        HttpExchange mockRequest1 = Mockito.mock(HttpExchange.class);

        when(mockRequest1.getRequestMethod()).thenReturn("POST");
        when(mockRequest1.getRequestURI()).thenReturn(new URI("/names/bianca"));
        userHandler.handle(mockRequest1);

        verify(mockRequest1).sendResponseHeaders(201, 0);
        verify(mockRequest1).close();

        HttpExchange mockRequest2 = Mockito.mock(HttpExchange.class);

        when(mockRequest2.getRequestMethod()).thenReturn("DELETE");
        when(mockRequest2.getRequestURI()).thenReturn(new URI("/names/bianca"));

        userHandler.handle(mockRequest2);

        verify(mockRequest2).sendResponseHeaders(200, 0);
        verify(mockRequest2).close();

        assertFalse(names.get().contains("Bianca"));

    }

    @Test
    public void GetRequestGetsAllNamesFromTheNameRepository() throws IOException {
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

        URL urlGet = new URL("http://localhost:8080/names/");
        HttpURLConnection connection3 = (HttpURLConnection) urlGet.openConnection();
        connection3.setRequestMethod("GET");

        int responseCode3 = connection3.getResponseCode();

        var bytes = connection3.getInputStream().readAllBytes();
        var response = new String(bytes);

        assertEquals(202, responseCode3 );

        assertEquals("[Fiona, Renae]", response);

    }

    @Test
    public void PutRequestsUpdatesANameToTheNameRepository() throws IOException, URISyntaxException {
        NameRepository names = new InMemoryNameRepository();

        NameHandler userHandler = new NameHandler(names);

        HttpExchange mockRequest1 = Mockito.mock(HttpExchange.class);

        when(mockRequest1.getRequestMethod()).thenReturn("POST");
        when(mockRequest1.getRequestURI()).thenReturn(new URI("/names/bianca"));
        userHandler.handle(mockRequest1);

        verify(mockRequest1).sendResponseHeaders(201, 0);
        verify(mockRequest1).close();

        HttpExchange mockRequest2 = Mockito.mock(HttpExchange.class);

        when(mockRequest2.getRequestMethod()).thenReturn("PUT");
        when(mockRequest2.getRequestURI()).thenReturn(new URI("/names/bianca/hello"));

        userHandler.handle(mockRequest2);

        verify(mockRequest2).sendResponseHeaders(204, 0);
        verify(mockRequest2).close();

        assertTrue(names.get().contains("Hello"));
        assertFalse(names.get().contains("Bianca"));

    }

}
