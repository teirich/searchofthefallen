package com.sotf.server.model;

import org.apache.solr.client.solrj.beans.Field;
import org.apache.solr.common.SolrDocument;

/**
 * Created by thad on 12/10/16.
 */
public class SearchResult {

    @Field String prevText;
    @Field String text;
    @Field String nextText;
    @Field int chapter;
    @Field int book;
    @Field String volume;
    @Field int sequence;

    public SearchResult() {
    }

    public SearchResult(String prevText, String text, String nextText, int chapter, int book, String volume, int sequence) {
        this.prevText = prevText;
        this.text = text;
        this.nextText = nextText;
        this.chapter = chapter;
        this.book = book;
        this.volume = volume;
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

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public int getSequence() {
        return sequence;
    }

    public void setSequence(int sequence) {
        this.sequence = sequence;
    }

}
