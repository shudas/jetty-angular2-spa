package com.shudas.example.jettyangular2spa.webapp;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;
import com.google.inject.servlet.ServletModule;
import com.shudas.example.jettyangular2spa.logging.LoggingFilter;
import com.sun.jersey.api.core.PackagesResourceConfig;
import com.sun.jersey.api.core.ResourceConfig;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.MessageBodyWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * Example Guice Server configuration. Creates an Injector, and binds it to
 * whatever Modules we want. In this case, we use an anonymous Module, but other
 * modules are welcome as well.
 */
public class GuiceConfig extends GuiceServletContextListener {
    private static final Logger log = LoggerFactory.getLogger(GuiceConfig.class);

    protected Injector getInjector() {
        return Guice.createInjector(
                mainModule
        );
    }

    ServletModule mainModule = new ServletModule() {
        @Override
        protected void configureServlets() {
            // Filters
            filter("/*").through(LoggingFilter.class);

            // bind the REST resources. Finds all Resource objects in the given packages.
            // Easier to do it this way than adding each resource explicitly.
            final ResourceConfig rc = new PackagesResourceConfig(
                    "com.shudas.example.jettyangular2spa.health.api",
                    "com.shudas.example.jettyangular2spa.auth.api");
            for (Class<?> resource : rc.getClasses()) {
                log.info(String.format("Binding resource: %s", resource.getName()));
                bind(resource);
            }
            // bind jackson converters for JAXB/JSON serialization
            bind(MessageBodyReader.class).to(JacksonJsonProvider.class);
            bind(MessageBodyWriter.class).to(JacksonJsonProvider.class);
            Map<String, String> initParams = new HashMap<String, String>();
            initParams.put("com.sun.jersey.api.json.POJOMappingFeature", "true");
            serve("/svc/*").with(GuiceContainer.class, initParams);
        }
    };
}