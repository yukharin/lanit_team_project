package com.lanit.lkz_project.entities;

import java.util.ArrayList;
import java.util.List;

public class BookUtils {

    public static List<Customer> build(int number) {
        List<Customer> customers = new ArrayList<>(number);
        int id = 0;
        String name;
        String address;
        for (int i = 0; i < number; i++) {
            name = Integer.toString((id * 3) + 25);
            address = Integer.toString((id * 5) + 10);
            id++;
            customers.add(new Customer(id, name, address));
        }
        return customers;
    }
}
