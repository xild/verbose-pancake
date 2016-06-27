package com.github.xild.verbose.pancake.config;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PreDestroy;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.xild.verbose.pancake.client.ZipCodeClient;

import feign.Feign;
import feign.Logger.Level;
import feign.Request.Options;
import feign.Retryer;
import feign.httpclient.ApacheHttpClient;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.slf4j.Slf4jLogger;

/**
 * @author Luis Vieira
 * @version 1.0
 */
@Configuration
public class FeignConfig {

	@Value("${http.client.maxConnection}")
	private int httpMaxConnection;

	@Value("${zipcode.host}")
	private String zipCodeHost;

	@Value("${zipcode.api.conn.timeout.millis}")
	private Integer zipCodeUpConnTimeout;

	@Value("${zipcode.api.read.timeout.millis}")
	private Integer zipCodeReadTimeout;

	private final List<PoolingHttpClientConnectionManager> pools = new ArrayList<>();

	@Bean
	public ZipCodeClient elasticSearchClient() {
		return config(Feign.builder()) //
				.options(new Options(zipCodeUpConnTimeout, zipCodeReadTimeout))//
				.logger(new Slf4jLogger(ZipCodeClient.class)) //
				.target(ZipCodeClient.class, zipCodeHost);
	}

	private Feign.Builder config(Feign.Builder builder) {
		PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
		cm.setMaxTotal(httpMaxConnection);

		pools.add(cm);

		CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(cm).build();

		return builder//
				.client(new ApacheHttpClient(httpClient)) //
				.logLevel(Level.FULL) //
				// disable retryer!
				.retryer(new Retryer.Default(0, 0, 0)) //
				.encoder(new JacksonEncoder())//
				.decoder(new JacksonDecoder());
	}

	@PreDestroy
	public void close() {
		pools.forEach(p -> p.close());
	}
}
