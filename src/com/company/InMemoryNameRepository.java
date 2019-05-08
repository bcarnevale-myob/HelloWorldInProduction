package com.company;

import java.util.ArrayList;
import java.util.List;

public class InMemoryNameRepository implements NameRepository {

    private List<String> names;

    public InMemoryNameRepository(String initialName) {
        this.names = new ArrayList<>();
        names.add(initialName);
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
        for(int i = 1; i < names.size(); i++) {
            if(names.get(i).equals(name) && !(names.get(i).equals("Bianca"))) {
                names.remove(i);
            }
        }
    }
}
