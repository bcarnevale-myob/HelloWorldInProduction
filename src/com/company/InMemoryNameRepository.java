package com.company;

import java.util.*;

public class InMemoryNameRepository implements NameRepository {

    private List<String> names;
    private String initialName;

    public InMemoryNameRepository(String initialName) {
        this.names = new ArrayList<>();
        this.initialName = initialName;
        names.add(this.initialName);
    }

    @Override
    public List<String> get() {
        return this.names;
    }

    @Override
    public void add(String name) {
        if(!names.contains(name)) {
            names.add(name);
        }
    }

    @Override
    public void remove(String name) {
        if(!name.equals(this.initialName)) {
            names.remove(name);
        }
    }
}
