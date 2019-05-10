package com.company;

import com.sun.net.httpserver.HttpHandler;

public abstract class HelloWorldHandler implements HttpHandler {

    public HelloWorldHandler(UserRepository users) {
        this.users = users;
    }

    protected UserRepository users;

}
