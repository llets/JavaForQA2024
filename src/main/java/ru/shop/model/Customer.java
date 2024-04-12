package ru.shop.model;

public record Customer(
        String id,
        String name,
        String phone,
        long age
) {
    public Customer {
        if (name.length() == 1) {
            throw new IllegalArgumentException("Name must contain more than 1 letter");
        }
        // проверка телефона
//        for (int i = 0; i < phone.length(); i++) {
//            if (Character.isDigit(phone.charAt(i))) {
//                count++;
//            }
//        }
        int count = 0;
        for (int i = 0; i < phone.length(); i++) {
            if (Character.isDigit(phone.charAt(i))) {
                count++;
            }
        }
        if (count != 11) {
            throw new IllegalArgumentException("Phone number must have 11 digits");
        }
        if (age < 18 || age > 100) {
            throw new IllegalArgumentException("Age must be between 18 and 100");
        }
    }
}
