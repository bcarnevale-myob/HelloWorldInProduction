package com.company;

import java.util.ArrayList;
import java.util.List;

public class InMemoryNameRepository implements NameRepository {

    private List<String> names;

    public InMemoryNameRepository() {
        this.names = new ArrayList<>();
    }

    @Override
    public List<String> get() {
        return this.names;
    }

    @Override
    public void add(String name) {
        this.names.add(name);
    }
}
