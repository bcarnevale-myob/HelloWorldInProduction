package com.company;

import com.sun.net.httpserver.HttpHandler;

public abstract class HelloWorldHandler implements HttpHandler {

    public HelloWorldHandler(UserRepository names) {
        this.names = names;
    }

    protected UserRepository names;

}
