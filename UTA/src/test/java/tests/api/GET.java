package tests.api;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.EntityExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;

import common.BaseTestAPIUnit;
import pages.api.TimezonePage;

public class GET extends BaseTestAPIUnit
{
	private String baseURL = "http://worldtimeapi.org/api/timezone";
	
	@Test
	void GETwithPOJO1(TestInfo info)
	{
		printTestName(info);
		
		client = WebTestClient.bindToServer()
				.baseUrl(baseURL)
				.build();
		
		EntityExchangeResult<TimezonePage> result = client.get()
				.uri("/America/Los_Angeles")
				.exchange()
				.expectStatus()
				.isOk()
				.expectHeader()
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.expectBody(TimezonePage.class)
				.returnResult();
		
		TimezonePage response = result.getResponseBody();

		assertEquals("PDT", response.getAbbreviation());
		assertEquals("73.162.153.202", response.getClient_ip());		
	}
}
