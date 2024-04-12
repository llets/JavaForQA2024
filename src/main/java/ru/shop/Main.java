package ru.shop;

import ru.shop.model.Customer;
import ru.shop.model.Product;
import ru.shop.model.ProductType;
import ru.shop.repository.CustomerRepository;
import ru.shop.repository.OrderRepository;
import ru.shop.repository.ProductRepository;
import ru.shop.service.CustomerService;
import ru.shop.service.OrderService;
import ru.shop.service.ProductService;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Main {
    public static void main(String[] args) {
//        System.out.println("Hello world!");

        //Создать сервисы и репозитории
        ProductRepository pRepo = new ProductRepository();
        CustomerRepository cRepo = new CustomerRepository();
        OrderRepository oRepo = new OrderRepository();

        ProductService productService = new ProductService(pRepo);
        CustomerService customerService = new CustomerService(cRepo);
        OrderService orderService = new OrderService(oRepo);

        // Добавить несколько заказчиков
        List<Customer> customers = new ArrayList<>();
        try {
            customers.add(new Customer(UUID.randomUUID().toString(), "Alex", "+7(917)917-75-75", 20));
            customers.add(new Customer(UUID.randomUUID().toString(), "Max", "+7(917)917-75-75", 25));
            customers.add(new Customer(UUID.randomUUID().toString(), "Andrew", "+7(917)917-75-75", 30));
        } catch (IllegalArgumentException e) {
            System.err.println("Unable to create Customer instance: " + e.getMessage());
        }

        for (Customer customer : customers) {
            customerService.save(customer);
        }

        // Добавить несколько продуктов
        List<Product> products = new ArrayList<>();
        try {
        products.add(new Product(UUID.randomUUID().toString(), "something1", 10, ProductType.GOOD));
        products.add(new Product(UUID.randomUUID().toString(), "something2", 20, ProductType.GOOD));
        products.add(new Product(UUID.randomUUID().toString(), "something3", 100, ProductType.SERVICE));
        products.add(new Product(UUID.randomUUID().toString(), "something4", 200, ProductType.SERVICE));
        } catch (IllegalArgumentException e) {
            System.err.println("Unable to create Product instance: " + e.getMessage());
        }

        for (Product product : products) {
            productService.save(product);
        }

        //  Добавить несколько Заказов.
        //  Одни из заказов должен быть с отрицательным количеством,
        //  получившуюся ошибку нужно обработать через try/catch (BadOrderCountException).
        //  Message ошибки вывести на консоль
        try {
            orderService.add(customers.getFirst(), products.getFirst(), 1);
            orderService.add(customers.get(1), products.get(3), 2);
            orderService.add(customers.get(2), products.get(2), 5);
            orderService.add(customers.get(1), products.get(3), -10);
        } catch (BadOrderCountException e) {
            System.out.println(e.getMessage());
        }

        // Вывести на консоль:
        //  * Количество заказчиков
        System.out.println("Количество заказчиков: " + customerService.findAll().size());
        System.out.println();
        //  * Количество товаров всего и в разрезе по типам
        System.out.println("Количество товаров всего: " + productService.findAll().size());
        for (ProductType item : ProductType.values()){
            System.out.println("Количество товаров типа " + item + " = " + productService.findByProductType(item).size());
        }
        System.out.println();
        //  * Количество товаров всего и в разрезе по заказчикам
        System.out.println("Количество товаров всего: " + productService.findAll().size());
        for (Customer customer : customers){
            System.out.println("Количество товаров заказчика " + customer.name() + " = " + orderService.findByCustomer(customer).size());
        }
        System.out.println();
        //  * Суммы для оплаты в разрезе по заказчикам
        for (Customer customer : customers){
            System.out.println("Сумма оплаты заказчика " + customer.name() + " = " + orderService.getTotalCustomerAmount(customer));
        }
    }
}