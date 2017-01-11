package com.sotf.server.model;

import java.util.Arrays;
import java.util.List;

/**
 * Created by thad on 12/10/16.
 */
public class Volume {
    public String getTitle() {
        return title;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public int getReadingOrder() {
        return readingOrder;
    }

    public List<Book> getContents() {
        return contents;
    }

    private final String title;
    private final String abbreviation;
    private final int readingOrder;
    private final List<Book> contents;

    public Volume(String abbreviation, String title, int readingOrder, Book... contents) {
        this.title = title;
        this.abbreviation = abbreviation;
        this.readingOrder = readingOrder;
        this.contents = Arrays.asList(contents);
    }


}
