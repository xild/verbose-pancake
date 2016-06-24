package com.github.xild.verbose.pancake.config;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.datatype.joda.JodaMapper;
import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;
import com.github.xild.verbose.pancake.resource.AddressResource;
import com.github.xild.verbose.pancake.resource.HealthCheckResource;

@Component
@ApplicationPath("/api")
@Profile(value = "!test")
public class JerseyConfiguration extends ResourceConfig {

    public JerseyConfiguration() {
    	JacksonJaxbJsonProvider provider = new JacksonJaxbJsonProvider();
    	provider.setMapper(new JodaMapper());
    	
    	register(provider);
    	packages("com.github.xild.verbose.pancake");
    	registerEndpoints();
    }
    
    private void registerEndpoints() {
    	register(AddressResource.class);
    	register(HealthCheckResource.class);
    }

}
