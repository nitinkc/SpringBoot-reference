package com.springHelloWorld.dao.pg;

import java.util.List;

public interface PostgresDao<T> {
    int save(T t);
    int update(T t);
    <U> T findById(U id);
    List<T> findByCriteria();//TODO: Find a way to pass criteria


    <U> int deleteById(U id);

    // ONLY FOR PRACTISE : Abomination
    int deleteAll();
    List<T> findAll();

}
