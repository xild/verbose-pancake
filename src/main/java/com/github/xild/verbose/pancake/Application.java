package com.github.xild.verbose.pancake;

import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.util.thread.QueuedThreadPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.jetty.JettyEmbeddedServletContainerFactory;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

@SpringBootApplication()
public class Application extends SpringBootServletInitializer {
	private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);
	
    @Value("${jetty.port}")
    private Integer port;

    @Value("${jetty.minThreads}")
    private Integer minThreads;

    @Value("${jetty.MaxThreads}")
    private Integer maxThreads;

    @Value("${jetty.IdleTimeout}")
    private Integer idleTimeout;

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }

    @Bean
    public JettyEmbeddedServletContainerFactory jettyEmbedded() {
        final JettyEmbeddedServletContainerFactory jetty = new JettyEmbeddedServletContainerFactory(port);
        jetty.addServerCustomizers((Server server) -> {
            final QueuedThreadPool threadPool = server.getBean(QueuedThreadPool.class);
            threadPool.setMinThreads(minThreads);
            threadPool.setMaxThreads(maxThreads);
            threadPool.setIdleTimeout(idleTimeout);
        });
        return jetty;
    }

    public static void main(String[] args) {
        try {
            ClassLoader cl = Application.class.getClassLoader();
            String banner = IOUtils.toString(cl.getResourceAsStream("banner-verbose.txt"));
            LOGGER.info(banner);
            new Application().configure(
                    new SpringApplicationBuilder(Application.class)).run(args);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

}
