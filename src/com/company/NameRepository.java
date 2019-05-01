package com.company;

import java.util.ArrayList;
import java.util.List;

public class NameRepository {

    private List<String> names;

    public NameRepository() {
        this.names = new ArrayList<>();
    }

    public List<String> get() {
        return this.names;
    }

    public void add(String name) {
        this.names.add(name);
    }

}
