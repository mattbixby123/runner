package dev.mbixby.runner;


import dev.mbixby.runner.user.User;
import dev.mbixby.runner.user.UserHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import java.util.List;

@SpringBootApplication
public class Application {

	private static final Logger log = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	// Setting up the REST client and communicating that with spring
	@Bean
	UserHttpClient userHttpClient() {
		// populates user with placeholder data from the API url in the line below
		RestClient restClient = RestClient.create("https://jsonplaceholder.typicode.com/");
		HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(RestClientAdapter.create(restClient)).build();
		return factory.createClient(UserHttpClient.class);
	}

	@Bean
	CommandLineRunner runner(UserHttpClient client) {
		return args -> {

			List<User> users = client.findAll();
			System.out.println(users);

		};
	}
}
