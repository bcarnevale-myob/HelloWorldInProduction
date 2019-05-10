package com.company;

import java.util.*;

public class InMemoryUserRepository implements UserRepository {

    private List<User> users;
    private String initialName;

    public InMemoryUserRepository(String initialName) {
        this.users = new ArrayList<>();
        this.initialName = initialName;
        User owner = new User((long) 1, initialName, true);
        users.add(owner);
    }

    @Override
    public List<User> get() {
        return this.users;
    }

    @Override
    public void add(String name) {
        User newUser = new User(name);
        if(newUser.isOwner) {
            users.add(newUser);
        }
    }

    @Override
    public void remove(String name) {
        if(!name.equals(this.initialName)) {
            users.remove(name);
        }
    }
}
