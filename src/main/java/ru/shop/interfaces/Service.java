package ru.shop.interfaces;


import java.util.List;

public interface Service<T> {
    List<T> findAll();
}
