package com.github.xild.verbose.pancake.searchZipCode;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;

import com.github.xild.verbose.pancake.Application;

@RunWith( SpringJUnit4ClassRunner.class )
@SpringApplicationConfiguration( classes = Application.class )
@WebAppConfiguration
@IntegrationTest("server.port:0") 
public class HealthCheckIntegrationTest {
	private static final Logger LOGGER = LoggerFactory.getLogger(HealthCheckIntegrationTest.class);
	
	private RestTemplate restTemplate = new TestRestTemplate();

	@Test
	public void health() {
		String entity = restTemplate.getForObject( "http://localhost:8082/api/healthcheck", String.class );
		LOGGER.info(entity);
		Assert.assertEquals("LIVE", entity);
	}
	
}
