package com.company;

import java.util.List;

public interface UserRepository {
    List<User> get();

    void add(String name);

    void remove(String name);

}
