package edu.tlabs.api.config;

import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.client.RestTemplate;

@Configuration
@PropertySource("classpath:security.properties")
@EnableHystrixDashboard
public class ApiConfig {

	/*@Bean
	@LoadBalanced
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}*/
	
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	//HttpComponentsClientHttpRequestFactory for setting timeout
}
