package com.company;

import java.util.*;

public class InMemoryNameRepository implements NameRepository {

    private List<String> names;
    private String initialName;

    public InMemoryNameRepository(String initialName) {
        this.names = new ArrayList<>();
        this.initialName = capitalise(initialName);
        names.add(this.initialName);
    }

    @Override
    public List<String> get() {
        return this.names;
    }

    @Override
    public void add(String name) {
        if(!names.contains(name)) {
            names.add(capitalise(name));
        }
    }

    @Override
    public void remove(String name) {
        if(!name.equals(this.initialName)) {
            names.remove(name);
        }
    }

    private String capitalise(String word) {
        return word.substring(0,1).toUpperCase() + word.substring(1).toLowerCase();
    }
}
