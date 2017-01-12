package com.sotf.parser.language;

/**
 * Created by thad on 1/7/17.
 */
public class Paragraph {
    public Paragraph getPrevious() {
        return previous;
    }

    public void setPrevious(Paragraph previous) {
        this.previous = previous;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Paragraph getNext() {
        return next;
    }

    public void setNext(Paragraph next) {
        this.next = next;
    }
    public Chapter getChapter() {
        return chapter;
    }

    public void setChapter(Chapter chapter) {
        this.chapter = chapter;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Volume getVolume() {
        return volume;
    }

    public void setVolume(Volume volume) {
        this.volume = volume;
    }

    public int getSequence() {
        return sequence;
    }

    public void setSequence(int sequence) {
        this.sequence = sequence;
    }

    public Paragraph(Paragraph previous, String text, Paragraph next, Chapter chapter, Book book, Volume volume, int sequence) {
        this.previous = previous;
        this.text = text;
        this.next = next;
        this.chapter = chapter;
        this.book = book;
        this.volume = volume;
        this.sequence = sequence;
    }

    Paragraph previous;
    String text;
    Paragraph next;
    Chapter chapter;
    Book book;
    Volume volume;
    int sequence;

}
