package common;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.web.reactive.server.WebTestClient;

@ExtendWith(APITestWatcher.class)
public class BaseTestAPIUnit extends BaseLogging
{
	protected WebTestClient client;
}
