package com.sotf.server.model;

import java.util.List;

/**
 * Created by thad on 1/10/17.
 */
public class SearchResults {
    private int start;
    private int rows;
    private long numFound;
    private List<SearchResult> searchResults;

    public SearchResults(int start, int rows, long numFound, List<SearchResult> searchResults) {
        this.start = start;
        this.rows = rows;
        this.numFound = numFound;
        this.searchResults = searchResults;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public List<SearchResult> getSearchResults() {
        return searchResults;
    }

    public void setSearchResults(List<SearchResult> searchResults) {
        this.searchResults = searchResults;
    }

    public long getNumFound() {
        return numFound;
    }

    public void setNumFound(long numFound) {
        this.numFound = numFound;
    }
}
