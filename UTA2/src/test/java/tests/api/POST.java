package tests.api;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.EntityExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import com.aventstack.extentreports.Status;

import common.UTATestWatcher;
import common.Reports;
import tests.postbodies.SimplePostBody;

@ExtendWith(UTATestWatcher.class)
public class POST extends Reports
{
	private String baseURL = "http://httpbin.org";
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
	void POSTtest()
	{
		test = extent.createTest("POSTtest");
		
		var postBody = new SimplePostBody("123");		
		client = WebTestClient.bindToServer().baseUrl(baseURL).build();
		
		EntityExchangeResult<String> result = client.post()
				.uri("/post")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				.body(BodyInserters.fromValue(postBody))
				.exchange()
				.expectStatus()
				.is2xxSuccessful() //.isOk()
				.expectHeader()
				.contentType(MediaType.APPLICATION_JSON)
				.expectBody(String.class)
				.returnResult();
		
		System.out.println("Response Content-Type: " + result.getRequestHeaders().getContentType());
		System.out.println(result.getResponseBody());
		test.log(Status.PASS, "POST for http://httpbin.org");
	}
}
