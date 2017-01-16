package com.sotf.parser.solr;

import com.sotf.parser.language.Book;
import com.sotf.parser.language.Chapter;
import com.sotf.parser.language.Paragraph;
import com.sotf.parser.language.Novel;
import org.apache.solr.common.SolrInputDocument;

/**
 * Created by thad on 1/8/17.
 */
public class ParagraphSolrDocumentBuilder {

    public SolrInputDocument build(Paragraph paragraph) {
        Chapter chapter = paragraph.getChapter();
        Book book = paragraph.getBook();
        Novel novel = paragraph.getNovel();

        Paragraph prev = paragraph.getPrevious();
        Paragraph next = paragraph.getNext();

        SolrInputDocument document = new SolrInputDocument();
        document.setField("text", paragraph.getText());
        document.setField("chapter", chapter.getNumber());//TODO: these should probably be numbers
        document.setField("book", book.getNumber());
        document.setField("novel", novel.getName());
        document.setField("sequence", paragraph.getSequence());

        if (prev != null) document.setField("prevText", prev.getText());
        if (next != null) document.setField("nextText", next.getText());

        return document;
    }
}
