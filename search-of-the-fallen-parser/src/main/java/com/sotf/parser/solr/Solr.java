package com.sotf.parser.solr;

import com.sotf.parser.language.Book;
import com.sotf.parser.language.Chapter;
import com.sotf.parser.language.Paragraph;
import com.sotf.parser.language.Novel;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.common.SolrInputDocument;

import java.io.IOException;

/**
 * Created by thad on 1/8/17.
 */
public class Solr {

    public void seed(String url, Novel novel) {
        SolrClient solr = new HttpSolrClient.Builder(url).build();

        try {
            for (Book book : novel.getBooks()) {
                for (Chapter chapter : book.getChapters()) {
                    for (Paragraph paragraph : chapter.getParagraphs()) {
                        SearchResult searchResult = new SearchResult(paragraph);
                        solr.addBean(searchResult);
                    }
                }
            }

            solr.commit();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SolrServerException e) {
            throw new RuntimeException(e);
        }
        finally {
            try {
                solr.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
