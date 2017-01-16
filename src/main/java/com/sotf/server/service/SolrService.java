package com.sotf.server.service;

import com.sotf.server.constants.Novels;
import com.sotf.server.model.SearchResult;
import com.sotf.server.model.Novel;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by thad on 1/9/17.
 */
@Named
public class SolrService {
    private static final Logger LOG = LoggerFactory.getLogger(SolrService.class);

    @Inject SolrClient client;
    public String method() {
        LOG.info("In method");
        return "works";
    }

    private static SolrQuery singleNovelQuery(@NotNull String searchText, @NotNull String novel, Integer book, Integer chapter, @NotNull Integer rows, @NotNull Integer start) {
        SolrQuery query = commonQuery(searchText, rows, start);

        query.set("fq", "novel:" + novel + " ");

        if(book != null) {
            query.set("fq", "book:" + book);
        }

        if(chapter != null) {
            query.set("chapter", "chapter:" + chapter);
        }
        LOG.info("singleNovelQuery: " + query.toString());
        return query;
    }

    private static SolrQuery commonQuery(String searchText, Integer rows, Integer start){
        SolrQuery query = new SolrQuery();

        query.set("q", "text:" + searchText);
        query.set("sort", "sequence asc");
        query.set("fl", "text,chapter,book,novel,sequence,nextText,prevText");
        query.setRows(rows);
        query.setStart(start);

        return query;
    }

    private static String zeroToRange(int count){
        return "[0 TO " + count + "]";
    }

    private static String novelsBeforeString (String novel) {
        List<Novels> before = Novels.before(novel);
        return before.stream()
                .map(Novels::novel)
                .map(Novel::getAbbreviation)
                .map(String::toUpperCase)
                .collect(Collectors.joining(" , "));
    }

    private static SolrQuery upToQuery(@NotNull String searchText, @NotNull String novel, Integer book, Integer chapter, @NotNull Integer rows, @NotNull Integer start) {
        SolrQuery query = commonQuery(searchText, rows, start);
        String beforeString = novelsBeforeString(novel);

        StringBuilder sb = new StringBuilder();

        sb.append("novel:(");
        sb.append(beforeString);
        sb.append(") OR (novel:");
        sb.append(novel);

        if(book != null) {
            sb.append(" AND book:");
            sb.append(zeroToRange(book));
        }
        if(chapter != null) {
            sb.append(" AND chapter:");
            sb.append(zeroToRange(chapter));
        }
        sb.append(")");

        query.set("fq", sb.toString());
        LOG.info("upToQuery: " + query.toString());
        return query;
    }

    public class SolrServiceResponse {
        private final long numFound;
        private final List<SearchResult> searchResults;

        SolrServiceResponse(long numFound, List<SearchResult> searchResults){
            this.numFound = numFound;
            this.searchResults = Collections.unmodifiableList(searchResults);
        }
        public long getNumFound() {
            return numFound;
        }

        public List<SearchResult> getSearchResults() {
            return Collections.unmodifiableList(searchResults);
        }
    }

    public SolrServiceResponse upToSearch(String searchText, String novel, Integer book, Integer chapter, Integer rows, Integer start){
        SolrQuery query = upToQuery(searchText, novel, book, chapter, rows, start);
        return doQuery(query);
    }

    public SolrServiceResponse search(String searchText, String novel, Integer book, Integer chapter, Integer rows, Integer start) {
        SolrQuery query = singleNovelQuery(searchText, novel, book, chapter, rows, start);
        return doQuery(query);
    }

    private SolrServiceResponse doQuery(SolrQuery query) {
        try {
            QueryResponse response = client.query(query);
            long numFound = response.getResults().getNumFound();
            List<SearchResult> results = response.getBeans(SearchResult.class);
            return new SolrServiceResponse(numFound, results);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SolrServerException e) {
            throw new RuntimeException(e);
        }
    }
}
