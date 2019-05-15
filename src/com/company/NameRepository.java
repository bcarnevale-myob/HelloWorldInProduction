package com.company;

import java.util.Collection;

public interface NameRepository extends Collection<String> {

    boolean add(String name);

    boolean remove(String name);

    String getYourName();

}