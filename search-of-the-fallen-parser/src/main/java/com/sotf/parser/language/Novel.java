package com.sotf.parser.language;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by thad on 1/7/17.
 */
public class Novel {
    int number;
    List<Book> books;

    public void addBook(Book b) {
        this.books.add(b);
    }
    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public Novel(int number) {
        this(number, new ArrayList<>());
    }
    public Novel(int number, List<Book> books) {
        this.number = number;
        this.books = books;
    }
}
