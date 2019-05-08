package com.company;

import java.util.List;

public interface NameRepository {
    List<String> get();

    void add(String name);

    void remove(String name);

}
