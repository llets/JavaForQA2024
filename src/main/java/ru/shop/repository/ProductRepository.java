package ru.shop.repository;

import ru.shop.interfaces.Repository;
import ru.shop.model.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductRepository implements Repository<Product> {
    protected ArrayList<Product> products = new ArrayList<>();

    @Override
    public void save(Product product) {
        products.add(product);
    }

    @Override
    public List<Product> findAll() {
        return products;
    }
}
