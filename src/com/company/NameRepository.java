package com.company;

import java.util.List;

public interface NameRepository extends List<String> {

    boolean add(String name);

    boolean remove(String name);

    String getInitialName();

}
