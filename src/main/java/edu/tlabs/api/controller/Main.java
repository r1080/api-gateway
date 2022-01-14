package edu.tlabs.api.controller;


import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import edu.tlabs.api.model.Task;
import edu.tlabs.api.model.Tasks;

//TODO: to be deleted
public class Main {

	public static void main(String[] args) throws JsonProcessingException {
		Tasks tasks = new Tasks();
		
		List<Task> taskList = new ArrayList<>();
		
		Task taskOne = new Task();
		taskOne.setTask("TaskOne");
		taskOne.setTaskId(0);
		
		Task taskTwo = new Task();
		taskTwo.setTask("TaskTwo");
		taskOne.setTaskId(1);
		
		taskList.add(taskOne);
		taskList.add(taskTwo);
		tasks.setTasks(taskList);
		
		ObjectMapper mapper = new ObjectMapper();
		
		String json = mapper.writeValueAsString(tasks);
		
		System.out.println(json);
		
	}

}
