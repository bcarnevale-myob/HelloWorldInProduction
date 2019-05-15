package com.company;

import java.util.*;

public class InMemoryNameRepository extends ArrayList<String> implements NameRepository {

    private String initialName;

    public InMemoryNameRepository(String initialName) {
        this.initialName = capitalise(initialName);
        super.add(this.initialName);
    }

    @Override
    public boolean add(String name) {
        String nameAsStored = capitalise(name);
        if(!super.contains(nameAsStored)) {
            super.add(nameAsStored);
            return true;
        }
        return false;
    }

    @Override
    public boolean remove(String name) {
        String nameAsStored = capitalise(name);
        if(!nameAsStored.equals(this.initialName)) {
            super.remove(nameAsStored);
            return true;
        }
        return false;
    }

    @Override
    public String getInitialName() {
        return this.initialName;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    private String capitalise(String word) {
        return word.substring(0,1).toUpperCase() + word.substring(1).toLowerCase();
    }
}
