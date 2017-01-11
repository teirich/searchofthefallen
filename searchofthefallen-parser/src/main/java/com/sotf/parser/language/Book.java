package com.sotf.parser.language;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by thad on 1/7/17.
 */
public class Book {
    int number;
    List<Chapter> chapters;

    public Book(int number) {
        this(number, new ArrayList<>());
    }
    public Book(int number, List<Chapter> chapters) {
        this.number = number;
        this.chapters = chapters;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public List<Chapter> getChapters() {
        return chapters;
    }

    public void setChapters(List<Chapter> chapters) {
        this.chapters = chapters;
    }

    public void addChapter(Chapter chapter) {
        this.chapters.add(chapter);
    }

}
