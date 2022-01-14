package edu.tlabs.api.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

import edu.tlabs.api.model.Task;
import edu.tlabs.api.model.Tasks;

@Service
public class TrackerService {

	private static final Logger LOGGER = LoggerFactory.getLogger(TrackerService.class);

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private ObjectMapper mapper;

	@Value("${task.service}")
	private String taskUrl;

	@PostConstruct
	public void init() {
		LOGGER.info("Construction of Tracker Service Completed");
	}

	@HystrixCommand(fallbackMethod="saveTasksFallbackMethod", commandProperties= {
			@HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds", value = "2000"),
			@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "2"),
			@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "4000")
	})
	public String saveTasks(Tasks tasks, String jwt) {
		LOGGER.info("Tasks to be saved, JWT {}, {} ", tasks, jwt);
		String responseMessage = null;
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.setBearerAuth(jwt);
			headers.setContentType(MediaType.APPLICATION_JSON);

			String request = mapper.writeValueAsString(tasks);
			HttpEntity<String> entity = new HttpEntity<>(request, headers);

			ResponseEntity<String> response = restTemplate.postForEntity(taskUrl, entity, String.class);
			responseMessage = response.getBody();
		} catch (JsonProcessingException e) {
			LOGGER.error("Error parsing request body {}", e.getMessage());
		}
		return responseMessage;
	}

	public String saveTasksFallbackMethod(Tasks tasks, String jwt) {
		LOGGER.info("#########TasksServicedown ########");
		return "TaskService IS DOWN";
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<Task> findAllTasks(String jwt){
		HttpHeaders headers = new HttpHeaders();
		headers.setBearerAuth(jwt);
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> entity = new HttpEntity<>(headers);
		ResponseEntity<List> responseEntity = restTemplate.exchange(taskUrl, HttpMethod.GET, entity, List.class);
		return responseEntity.getBody();
	}
		
}
