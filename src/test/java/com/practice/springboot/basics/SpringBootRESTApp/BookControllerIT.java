package com.practice.springboot.basics.SpringBootRESTApp;

import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookControllerIT {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	HttpHeaders headers = new HttpHeaders();

	TestRestTemplate restTemplate = new TestRestTemplate();

	@Test
	public void testGetBook() throws JSONException {
		HttpEntity<Object> entity = new HttpEntity<>(null, headers);
		
		ResponseEntity<String> response = restTemplate.exchange("http://localhost:8080/book/1", HttpMethod.GET, entity,
				String.class);

		String expected = "{\"id\":1,\"name\":\"Effective JAVA\",\"author\":\"Joshua\"}";

		logger.info("Response: " + response.getBody());
		logger.info("Expected: " + expected);

		JSONAssert.assertEquals(expected, response.getBody(), false);


	}

}
