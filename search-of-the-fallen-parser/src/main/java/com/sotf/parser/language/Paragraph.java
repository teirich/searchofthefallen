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

    public Novel getNovel() {
        return novel;
    }

    public void setNovel(Novel novel) {
        this.novel = novel;
    }

    public int getSequence() {
        return sequence;
    }

    public void setSequence(int sequence) {
        this.sequence = sequence;
    }

    public Paragraph(Paragraph previous, String text, Paragraph next, Chapter chapter, Book book, Novel novel, int sequence) {
        this.previous = previous;
        this.text = text;
        this.next = next;
        this.chapter = chapter;
        this.book = book;
        this.novel = novel;
        this.sequence = sequence;
    }

    Paragraph previous;
    String text;
    Paragraph next;
    Chapter chapter;
    Book book;
    Novel novel;
    int sequence;

}
