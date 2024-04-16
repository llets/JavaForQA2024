package ru.shop.service;




import ru.shop.exception.BadOrderCountException;
import ru.shop.model.Customer;
import ru.shop.model.Order;
import ru.shop.model.Product;
import ru.shop.repository.OrderRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class OrderService implements Service<Order> {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }


    public void add(Customer customer, Product product, long count) throws BadOrderCountException {
//    String id,
//    String customerId,
//    String productId,
//    long count,
//    long amount
        if (count <= 0) {
            throw new BadOrderCountException("Вы пытаетесь создать заказ с невалидным количеством товара!");
        } else {
            Order order = new Order(UUID.randomUUID().toString(), customer.id(), product.id(), count, count * product.cost());
            orderRepository.save(order);
        }
    }

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    public List<Order> findByCustomer(Customer customer) {
        List<Order> ordersResult = new ArrayList<>();
        for (Order order : orderRepository.findAll()) {
            if (order.customerId().equals(customer.id())) {
                ordersResult.add(order);
            }
        }
        return ordersResult;
    }

    public long getTotalCustomerAmount(Customer customer) {
        long res = 0;
        for (Order order : orderRepository.findAll()) {
            if (Objects.equals(order.customerId(), customer.id())) {
                res += order.amount();
            }
        }
        return res;
    }
}
