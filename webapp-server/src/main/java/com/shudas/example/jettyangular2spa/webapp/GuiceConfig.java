package com.shudas.example.jettyangular2spa.webapp;

import com.google.inject.servlet.ServletModule;
import com.shudas.example.jettyangular2spa.auth.api.AuthResource;
import com.shudas.example.jettyangular2spa.health.api.HealthResource;
import com.shudas.example.jettyangular2spa.logging.LoggingFilter;

/**
 * Created by Shu on 4/1/2016.
 */
public class GuiceConfig extends ServletModule {
    @Override
    protected void configureServlets() {
        // Filters
        filter("/*").through(LoggingFilter.class);

        // Resources
        bind(AuthResource.class);
        bind(HealthResource.class);
    }
}
