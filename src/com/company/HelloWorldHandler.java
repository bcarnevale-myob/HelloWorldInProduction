package com.company;

import com.sun.net.httpserver.HttpHandler;

public abstract class HelloWorldHandler implements HttpHandler {

    public HelloWorldHandler(NameRepository names) {
        this.names = names;
    }

    protected NameRepository names;

    //method to set namerepo + get

}
