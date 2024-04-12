package ru.shop.repository;
import ru.shop.model.Order;
import ru.shop.interfaces.Repository;
import java.util.ArrayList;
import java.util.List;

public class OrderRepository implements Repository<Order> {
    public ArrayList<Order> orders = new ArrayList<>();

    @Override
    public void save(Order order) {
        orders.add(order);
    }

    @Override
    public List<Order> findAll() {
        return orders;
    }
}
