package ru.shop.repository;
import ru.shop.model.Order;

import java.util.ArrayList;
import java.util.List;

public class OrderRepository implements Repository<Order> {
    final protected List<Order> orders = new ArrayList<>();

    @Override
    public void save(Order order) {
        orders.add(order);
    }

    @Override
    public List<Order> findAll() {
        return orders;
    }
}
