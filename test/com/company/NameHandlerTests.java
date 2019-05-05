package com.company;

import com.sun.net.httpserver.HttpExchange;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

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

}
