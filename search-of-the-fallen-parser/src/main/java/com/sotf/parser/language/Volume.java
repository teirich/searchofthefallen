package com.sotf.parser.language;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by thad on 1/7/17.
 */
public class Volume {
    String name;
    List<Book> books;

    public void addBook(Book b) {
        this.books.add(b);
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public Volume(String name) {
        this(name, new ArrayList<>());
    }
    public Volume(String name, List<Book> books) {
        this.name = name;
        this.books = books;
    }
}
