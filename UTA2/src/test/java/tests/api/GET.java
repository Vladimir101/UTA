package tests.api;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.EntityExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.aventstack.extentreports.Status;

import common.UTATestWatcher;
import common.Reports;
import pages.api.TimezonePage;

@ExtendWith(UTATestWatcher.class)
public class GET extends Reports
{
	private String baseURL = "http://worldtimeapi.org/api/timezone";
	private WebTestClient client;
	
	@BeforeAll
	static void report()
	{
		Reports.reportSetUp();
 
	}
	
	@BeforeEach
	void setUp()
	{
		client = WebTestClient.bindToServer().baseUrl(baseURL).build();
	}
	
	@Test
	void GETwithPOJO1()
	{
		test = extent.createTest("GETwithPOJO1");
		
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
		
		assertEquals("PDTh", response.getAbbreviation());
		test.log(Status.PASS, "abbreviation: PDT");
		
		assertEquals("73.162.153.202", response.getClient_ip());
		test.log(Status.PASS, "client_ip: 73.162.153.202");
	}
}
