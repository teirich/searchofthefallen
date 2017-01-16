package com.sotf.server.config;

import com.sotf.server.controller.InfoController;
import com.sotf.server.controller.SearchController;
import org.glassfish.jersey.server.ResourceConfig;

import javax.inject.Named;
import javax.ws.rs.ApplicationPath;

/**
 * Created by thad on 1/9/17.
 */
@Named
@ApplicationPath("/api")
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {
        register(SearchController.class);
        register(InfoController.class);
    }
}
