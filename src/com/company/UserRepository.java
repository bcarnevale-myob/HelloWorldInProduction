package com.company;

import java.util.List;

public interface UserRepository {
    List<String> get();

    void add(String username);

    void remove(String username);

}
