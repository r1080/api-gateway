package edu.tlabs.api.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.tlabs.api.aop.LogTime;
import edu.tlabs.api.model.Task;
import edu.tlabs.api.model.Tasks;
import edu.tlabs.api.service.JwtService;
import edu.tlabs.api.service.TrackerService;

@RestController
@RequestMapping("/tracker")
public class ApiController {

	@Autowired
	private TrackerService trackerService;

	@Autowired
	private JwtService jwtService;

	private static final Logger LOGGER = LoggerFactory.getLogger(ApiController.class);

	@GetMapping("/tasks")
	@LogTime
	@CrossOrigin
	public List<Task> testGateway() {

		String jwt = jwtService.authenticateAndGetJWTtoken();
		LOGGER.info("JWT Test {}", jwt);
		
		List<Task> taskList = trackerService.findAllTasks(jwt);

		return taskList;
	}

	@PostMapping("/tasks")
	public String saveTasks(@RequestBody Tasks tasks) {
		LOGGER.info("TaskList Received {}", tasks);
		String jwt = jwtService.authenticateAndGetJWTtoken();
		String response = trackerService.saveTasks(tasks, jwt);
		return response.concat("From API Gateway");
	}
}
