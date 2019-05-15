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
        NameRepository names = new InMemoryNameRepository("bianca");
        NameHandler userHandler = new NameHandler(names);

        HttpExchange mockRequest = Mockito.mock(HttpExchange.class);

        when(mockRequest.getRequestMethod()).thenReturn("POST");
        when(mockRequest.getRequestURI()).thenReturn(new URI("/names/fiona"));

        userHandler.handle(mockRequest);

        verify(mockRequest).sendResponseHeaders(201, 0);
        verify(mockRequest).close();

        assertTrue(names.contains("Fiona"));

    }

    @Test
    public void PostRequestsAddTwoNamesToTheNameRepository() throws IOException, URISyntaxException {
        NameRepository names = new InMemoryNameRepository("bianca");
        NameHandler userHandler = new NameHandler(names);

        HttpExchange mockRequest1 = Mockito.mock(HttpExchange.class);

        when(mockRequest1.getRequestMethod()).thenReturn("POST");
        when(mockRequest1.getRequestURI()).thenReturn(new URI("/names/renae"));
        userHandler.handle(mockRequest1);

        verify(mockRequest1).sendResponseHeaders(201, 0);
        verify(mockRequest1).close();

        HttpExchange mockRequest2 = Mockito.mock(HttpExchange.class);

        when(mockRequest2.getRequestMethod()).thenReturn("POST");
        when(mockRequest2.getRequestURI()).thenReturn(new URI("/names/fiona"));

        userHandler.handle(mockRequest2);

        verify(mockRequest2).sendResponseHeaders(201, 0);
        verify(mockRequest2).close();

        assertTrue(names.contains("Renae"));
        assertTrue(names.contains("Fiona"));

    }

    @Test
    public void DeleteRequestsRemovesANameFromTheNameRepository() throws IOException, URISyntaxException {
        NameRepository names = new InMemoryNameRepository("bianca");
        NameHandler userHandler = new NameHandler(names);

        HttpExchange mockRequest1 = Mockito.mock(HttpExchange.class);

        when(mockRequest1.getRequestMethod()).thenReturn("POST");
        when(mockRequest1.getRequestURI()).thenReturn(new URI("/names/Renae"));
        userHandler.handle(mockRequest1);

        verify(mockRequest1).sendResponseHeaders(201, 0);
        verify(mockRequest1).close();

        HttpExchange mockRequest2 = Mockito.mock(HttpExchange.class);

        when(mockRequest2.getRequestMethod()).thenReturn("DELETE");
        when(mockRequest2.getRequestURI()).thenReturn(new URI("/names/renae"));

        userHandler.handle(mockRequest2);

        verify(mockRequest2).sendResponseHeaders(202, 0);
        verify(mockRequest2).close();

        assertFalse(names.contains("Renae"));

    }

    @Test
    public void GetRequestGetsAllNamesFromTheNameRepository() throws IOException {
        HelloWorldServer helloWorldServer = new HelloWorldServer(8080, "bianca");
        helloWorldServer.createServer();

        URL urlPost1 = new URL("http://localhost:8080/names/fiona");
        HttpURLConnection connection = (HttpURLConnection) urlPost1.openConnection();
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

        assertEquals(200, responseCode3);
        assertEquals("[Bianca, Fiona, Renae]", response);

        helloWorldServer.stopServer();
    }

    @Test
    public void PutRequestsUpdatesANameToTheNameRepository() throws IOException, URISyntaxException {
        NameRepository names = new InMemoryNameRepository("bianca");
        NameHandler userHandler = new NameHandler(names);

        HttpExchange mockRequest1 = Mockito.mock(HttpExchange.class);

        when(mockRequest1.getRequestMethod()).thenReturn("POST");
        when(mockRequest1.getRequestURI()).thenReturn(new URI("/names/fiona"));
        userHandler.handle(mockRequest1);

        verify(mockRequest1).sendResponseHeaders(201, 0);
        verify(mockRequest1).close();

        HttpExchange mockRequest2 = Mockito.mock(HttpExchange.class);

        when(mockRequest2.getRequestMethod()).thenReturn("PUT");
        when(mockRequest2.getRequestURI()).thenReturn(new URI("/names/fiona/renae"));

        userHandler.handle(mockRequest2);

        verify(mockRequest2).sendResponseHeaders(204, -1);
        verify(mockRequest2).close();

        assertTrue(names.contains("Renae"));
        assertFalse(names.contains("Fiona"));

    }

    @Test
    public void PostRequestsOnlyAllowsUniqueNamesToBeAddedToTheNameRepository() throws IOException, URISyntaxException {
        NameRepository names = new InMemoryNameRepository("bianca");
        NameHandler userHandler = new NameHandler(names);

        HttpExchange mockRequest1 = Mockito.mock(HttpExchange.class);

        when(mockRequest1.getRequestMethod()).thenReturn("POST");
        when(mockRequest1.getRequestURI()).thenReturn(new URI("/names/fiona"));
        userHandler.handle(mockRequest1);

        verify(mockRequest1).sendResponseHeaders(201, 0);
        verify(mockRequest1).close();

        int previousNameRepositorySize = names.size();

        HttpExchange mockRequest2 = Mockito.mock(HttpExchange.class);

        when(mockRequest2.getRequestMethod()).thenReturn("POST");
        when(mockRequest2.getRequestURI()).thenReturn(new URI("/names/fiona"));

        userHandler.handle(mockRequest2);

        verify(mockRequest2).sendResponseHeaders(201, 0);
        verify(mockRequest2).close();

        int currentNameRepositorySize = names.size();

        assertEquals(previousNameRepositorySize, currentNameRepositorySize);

    }

    @Test
    public void cannotRemoveInitialNameFromNameRepository() throws IOException, URISyntaxException {
        NameRepository names = new InMemoryNameRepository("bianca");
        NameHandler userHandler = new NameHandler(names);

        HttpExchange mockRequest = Mockito.mock(HttpExchange.class);

        when(mockRequest.getRequestMethod()).thenReturn("DELETE");
        when(mockRequest.getRequestURI()).thenReturn(new URI("/names/bianca"));

        userHandler.handle(mockRequest);

        verify(mockRequest).sendResponseHeaders(202, 0);
        verify(mockRequest).close();

        assertTrue(names.contains("Bianca"));
    }

}
