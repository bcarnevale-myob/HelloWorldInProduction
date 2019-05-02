package com.company;

import com.sun.net.httpserver.HttpExchange;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class UserHandlerTests {

    @Test
    public void userEndpointDoesNotAllowsGetRequests() throws IOException {
        NameRepository names = new NameRepository();
        UserHandler userHandler = new UserHandler(names);

        HttpExchange mockRequest = Mockito.mock(HttpExchange.class);

        when(mockRequest.getRequestMethod()).thenReturn("GET");

        userHandler.handle(mockRequest);

        verify(mockRequest).sendResponseHeaders(404, 0);
        verify(mockRequest).close();

    }

    @Test
    public void userEndpointPostRequestsRespondsWithA201Response() throws IOException, URISyntaxException {
        NameRepository names = new NameRepository();
        UserHandler userHandler = new UserHandler(names);

        HttpExchange mockRequest = Mockito.mock(HttpExchange.class);

        when(mockRequest.getRequestMethod()).thenReturn("POST");
        when(mockRequest.getRequestURI()).thenReturn(new URI("/users/bianca"));

        userHandler.handle(mockRequest);

        verify(mockRequest).sendResponseHeaders(201, 0);
        verify(mockRequest).close();

    }

    @Test
    public void userEndpointPostRequestsAddsNameToTheNameRepository() throws IOException, URISyntaxException {
        NameRepository names = new NameRepository();

        UserHandler userHandler = new UserHandler(names);

        HttpExchange mockRequest = Mockito.mock(HttpExchange.class);

        when(mockRequest.getRequestMethod()).thenReturn("POST");
        when(mockRequest.getRequestURI()).thenReturn(new URI("/users/bianca"));

        userHandler.handle(mockRequest);

        verify(mockRequest).sendResponseHeaders(201, 0);
        verify(mockRequest).close();

        assertEquals("[bianca]", names.get().toString());

    }



}
