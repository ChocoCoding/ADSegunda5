package org.example.repositories;

import java.util.List;

public interface Repositorio <T> {

    void create(T t);
    void remove(T t);
    void update(T t);
    List<T> findAll();
    T findById(int id);


}
