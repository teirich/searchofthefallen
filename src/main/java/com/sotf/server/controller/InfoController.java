package com.sotf.server.controller;

import com.sotf.server.constants.Novels;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.json.JsonArray;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created by thad on 1/16/17.
 */
@Path("info")
public class InfoController {
    private static final Logger LOG = LoggerFactory.getLogger(InfoController.class);

    @GET
    @Path("novelStructure")
    @Produces(MediaType.APPLICATION_JSON)
    public String novelStructure(){
        return Novels.allToJson().toString();
    }

}
