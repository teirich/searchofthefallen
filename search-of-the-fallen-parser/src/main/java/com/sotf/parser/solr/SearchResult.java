package com.sotf.parser.solr;

import com.sotf.parser.language.Book;
import com.sotf.parser.language.Chapter;
import com.sotf.parser.language.Novel;
import com.sotf.parser.language.Paragraph;
import org.apache.solr.client.solrj.beans.Field;
import org.apache.solr.common.SolrInputDocument;

/**
 * Created by thad on 12/10/16.
 */
public class SearchResult {

    @Field String prevText;
    @Field String text;
    @Field String nextText;
    @Field int chapter;
    @Field int book;
    @Field int novel;
    @Field int sequence;

    public SearchResult() {
    }

    public SearchResult(String prevText, String text, String nextText, int chapter, int book, int novel, int sequence) {
        this.prevText = prevText;
        this.text = text;
        this.nextText = nextText;
        this.chapter = chapter;
        this.book = book;
        this.novel = novel;
        this.sequence = sequence;
    }

    public String getPrevText() {
        return prevText;
    }

    public void setPrevText(String prevText) {
        this.prevText = prevText;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getNextText() {
        return nextText;
    }

    public void setNextText(String nextText) {
        this.nextText = nextText;
    }

    public int getChapter() {
        return chapter;
    }

    public void setChapter(int chapter) {
        this.chapter = chapter;
    }

    public int getBook() {
        return book;
    }

    public void setBook(int book) {
        this.book = book;
    }

    public int getNovel() {
        return novel;
    }

    public void setNovel(int novel) {
        this.novel = novel;
    }

    public int getSequence() {
        return sequence;
    }

    public void setSequence(int sequence) {
        this.sequence = sequence;
    }


    public SearchResult (Paragraph paragraph) {
        Chapter chapter = paragraph.getChapter();
        Book book = paragraph.getBook();
        Novel novel = paragraph.getNovel();

        Paragraph prev = paragraph.getPrevious();
        Paragraph next = paragraph.getNext();

        SolrInputDocument document = new SolrInputDocument();
        this.text = paragraph.getText();
        this.chapter = chapter.getNumber();
        this.book = book.getNumber();
        this.novel = novel.getNumber();
        this.sequence = paragraph.getSequence();

        if (prev != null) this.prevText = prev.getText();
        if (next != null) this.nextText =  next.getText();
    }
}
