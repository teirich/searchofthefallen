package com.sotf.server.model;

/**
 * Created by thad on 12/10/16.
 */
public class Book {
    public Book(int bookNumber, String title, int startChapter, int endChapter) {
        this.bookNumber = bookNumber;
        this.startChapter = startChapter;
        this.endChapter = endChapter;
        this.title = title;
    }

    private final int bookNumber;
    private final int startChapter;
    private final int endChapter;
    private final String title;

    public int getBookNumber() {
        return bookNumber;
    }

    public String title() {
        return title;
    }

    public int getStartChapter() {
        return startChapter;
    }

    public int getEndChapter() {
        return endChapter;
    }

    public static final Book PROLOGUE = new Book(0, "Prologue", 0,0);

    public static final Book EPILOGUE = new Book(100, "Epilogue", 100, 100);
}
