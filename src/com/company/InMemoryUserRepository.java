package com.company;

import java.util.*;

public class InMemoryUserRepository implements UserRepository {

    private List<String> names;
    private String initialName;

    public InMemoryUserRepository(String initialName) {
        this.names = new ArrayList<>();
        this.initialName = initialName;
        names.add(this.initialName);
    }

    @Override
    public List<String> get() {
        return this.names;
    }

    @Override
    public void add(String username) {
        if(!names.contains(username)) {
            names.add(username);
        }
    }

    @Override
    public void remove(String username) {
        if(!username.equals(this.initialName)) {
            names.remove(username);
        }
    }
}
