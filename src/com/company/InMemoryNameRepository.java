package com.company;

import java.util.*;

public class InMemoryNameRepository extends LinkedHashSet<String> implements NameRepository {

    private String yourName;

    public InMemoryNameRepository(String yourName) {
        this.yourName = capitalise(yourName);
        super.add(this.yourName);
    }

    @Override
    public boolean add(String name) {
        return super.add(capitalise(name));
    }

    @Override
    public boolean remove(String name) {
        if (this.yourName.equals(capitalise(name))) {
            return false;
        }
        return super.remove(capitalise(name));
    }

    @Override
    public String getYourName() {
        return this.yourName;
    }

    private String capitalise(String word) {
        return word.substring(0, 1).toUpperCase() + word.substring(1).toLowerCase();
    }
}
