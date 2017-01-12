package com.sotf.parser.language;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by thad on 1/7/17.
 */
public class Chapter {

    public void addParagraph(Paragraph p) {
        this.paragraphs.add(p);
    }
    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public List<Paragraph> getParagraphs() {
        return paragraphs;
    }

    public void setParagraphs(List<Paragraph> paragraphs) {
        this.paragraphs = paragraphs;
    }

    public Chapter(int number) {
        this(number, new ArrayList<>());
    }
    public Chapter(int number, List<Paragraph> paragraphs) {
        this.number = number;
        this.paragraphs = paragraphs;
    }

    int number;
    List<Paragraph> paragraphs;
}
