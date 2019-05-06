package com.company;

import com.sun.net.httpserver.HttpExchange;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
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

}
