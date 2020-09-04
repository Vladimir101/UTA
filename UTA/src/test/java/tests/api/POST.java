package tests.api;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.EntityExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import common.BaseTestAPIUnit;
import tests.postbodies.SimplePostBody;

public class POST extends BaseTestAPIUnit
{
	private String baseURL = "http://httpbin.org";

	@Test
	void POSTtest(TestInfo info)
	{
		printTestName(info);
		var postBody = new SimplePostBody("123");
		
		client = WebTestClient.bindToServer().baseUrl(baseURL).build();
		
		EntityExchangeResult<String> result = client.post()
				  .uri("/post")
				  .accept(MediaType.APPLICATION_JSON)
				  .contentType(MediaType.APPLICATION_JSON)
				  .body(BodyInserters.fromValue(postBody))
				  .exchange()
				  .expectStatus()
				  .is2xxSuccessful()	//.isOk()
				  .expectHeader().contentType(MediaType.APPLICATION_JSON)
				  .expectBody(String.class)
				  .returnResult();
		
		System.out.println("Response Content-Type: " +
				result.getRequestHeaders().getContentType());
		System.out.println(result.getResponseBody());
	}
}
