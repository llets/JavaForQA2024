package ru.shop.model;

public record Product(
        String id,
        String name,
        long cost,
        ProductType productType
) {
    public Product {
        if (name.length() <= 2) {
            throw new IllegalArgumentException("Product name must contain at least 2 characters");
        }
        if (cost <= 0) {
            throw new IllegalArgumentException("Product cost must be greater than zero");
        }
    }
}
