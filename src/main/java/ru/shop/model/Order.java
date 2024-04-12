package ru.shop.model;

public record Order(
        String id,
        String customerId,
        String productId,
        long count,
        long amount
) {
    public Order {
        if (count <= 0) {
            throw new IllegalArgumentException("Order count must be greater than 0");
        }
    }
}
