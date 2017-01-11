package com.sotf.server.controller;

import com.sotf.server.model.SearchResults;
import com.sotf.server.service.SolrService;
import org.glassfish.jersey.server.ResourceConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 * Created by thad on 12/10/16.
 */
@Path("search")
public class SearchController {

    @Inject SolrService solrService;

    private static final Logger LOG = LoggerFactory.getLogger(ResourceConfig.class);

    @GET
    @Path("test")
    @Produces(MediaType.TEXT_PLAIN)
    public String test(){
        return "hello";
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public SearchResults search(@QueryParam("text") String searchText, @QueryParam("volume") String volume,
                           @QueryParam("book") Integer book, @QueryParam("chapter") Integer chapter,
                           @QueryParam("rows") Integer rows, @QueryParam("start") Integer start) {
        LOG.info("In SearchController.search");
        if(searchText == null || volume == null) {
            throw new IllegalArgumentException("Missing required parameter");
        }

        if(rows == null || rows <= 0 || rows > 100) {
            rows = 10;
        }

        if(start == null || start <= 0){
            start = 0;
        }

        SolrService.SolrServiceResponse response = solrService.search(searchText, volume.toUpperCase(), book, chapter, rows, start);
        return new SearchResults(start, rows, response.getNumFound(), response.getSearchResults());
    }

    @GET
    @Path("upTo")
    @Produces(MediaType.APPLICATION_JSON)
    public SearchResults upTo(@QueryParam("text") String searchText, @QueryParam("volume") String volume,
                              @QueryParam("book") Integer book, @QueryParam("chapter") Integer chapter,
                              @QueryParam("rows") Integer rows, @QueryParam("start") Integer start){
        LOG.info("In SearchController.upTo");
        if(searchText == null || volume == null) {
            throw new IllegalArgumentException("Missing required parameter");
        }

        if(rows == null || rows <= 0 || rows > 100) {
            rows = 10;
        }

        if(start == null || start <= 0){
            start = 0;
        }

        SolrService.SolrServiceResponse response = solrService.upToSearch(searchText, volume.toUpperCase(), book, chapter, rows, start);
        return new SearchResults(start, rows, response.getNumFound(), response.getSearchResults());

    }
}
