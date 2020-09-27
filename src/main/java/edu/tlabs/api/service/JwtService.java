package edu.tlabs.api.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import edu.tlabs.api.model.JwtResponse;
import edu.tlabs.api.model.User;

@Service
public class JwtService {

	private static final Logger LOGGER = LoggerFactory.getLogger(JwtService.class);

	@Autowired
	private RestTemplate restTemplate;

	@Value("${jwt.service.authenticate}")
	private String authenticationUrl;

	@HystrixCommand(fallbackMethod = "getJwtFallback")
	public String getJwt(User user) {

		LOGGER.info("Calling JWT Service For JWT Token!");
		ResponseEntity<JwtResponse> response = restTemplate.postForEntity(authenticationUrl, user, JwtResponse.class);
		LOGGER.info("Token: " + response.getBody().getJwt());

		return response.getBody().getJwt();

	}

	public String getJwtFallback(User user) {
		LOGGER.info("Retrieve JWT FallBack Method");
		return "JWT_FALLBACK_TOKEN";
	}

}
