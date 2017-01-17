package com.sotf.server.service;

import com.sotf.server.model.SearchResult;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.util.ClientUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

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

    private static SolrQuery singleNovelQuery(@NotNull String searchText, @NotNull Integer novel, Integer chapter, @NotNull Integer rows, @NotNull Integer start) {
        SolrQuery query = commonQuery(searchText, rows, start);

        StringBuilder sb = new StringBuilder();
        sb.append("novel:");
        sb.append(novel);
        sb.append(" AND chapter:");

        if(chapter != null) {
            sb.append(chapter);
        }
        else {
            sb.append("[0 TO *]");
        }

        query.set("fq", sb.toString());
        LOG.info("singleNovelQuery: " + query.toString());
        return query;
    }

    private static SolrQuery commonQuery(String searchText, Integer rows, Integer start){
        SolrQuery query = new SolrQuery();
        String escapedText = ClientUtils.escapeQueryChars(searchText);
        query.set("q", "text:\"" + escapedText + "\"");
        query.set("sort", "novel asc, sequence asc");
        query.set("fl", "text,chapter,book,novel,sequence,nextText,prevText");
        query.setRows(rows);
        query.setStart(start);

        return query;
    }

    private static String zeroToRange(int count){
        return "[0 TO " + count + "]";
    }

    private static String oneToString(int count) {
        return "[1 TO " + count + "]";
    }

    private static SolrQuery upToQuery(@NotNull String searchText, @NotNull Integer novel, Integer chapter, @NotNull Integer rows, @NotNull Integer start) {
        SolrQuery query = commonQuery(searchText, rows, start);

        StringBuilder sb = new StringBuilder();
        sb.append("novel:");
        sb.append(oneToString(novel));
        sb.append(" OR (novel:");
        sb.append(novel);
        sb.append(" AND chapter:");

        if(chapter != null) {
            sb.append(zeroToRange(chapter));
        }
        else {
            sb.append("[0 TO *]");
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

    public SolrServiceResponse upToSearch(String searchText, Integer novel, Integer chapter, Integer rows, Integer start){
        SolrQuery query = upToQuery(searchText, novel, chapter, rows, start);
        return doQuery(query);
    }

    public SolrServiceResponse search(String searchText, Integer novel, Integer chapter, Integer rows, Integer start) {
        SolrQuery query = singleNovelQuery(searchText, novel, chapter, rows, start);
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
