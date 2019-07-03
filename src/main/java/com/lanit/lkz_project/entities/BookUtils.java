package com.lanit.lkz_project.entities;

import java.util.ArrayList;
import java.util.List;

public class BookUtils {

    public static List<Book> build(int number) {
        List<Book> books = new ArrayList<>(number);
        int id = 0;
        String name;
        for (int i = 0; i < number; i++) {
            name = Integer.toString((id * 3) + 25);
            id++;
            books.add(new Book(id, name));
        }
        return books;
    }
}
