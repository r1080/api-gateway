package edu.tlabs.api.model;

public class Task {

	private int taskId;

	private String task;

	private boolean completed;

	public int getTaskId() {
		return taskId;
	}

	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	public boolean isCompleted() {
		return completed;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}

	public Task(int taskId, String task, boolean completed) {
		this.taskId = taskId;
		this.task = task;
		this.completed = completed;
	}

	@Override
	public String toString() {
		return "Task [taskId=" + taskId + ", task=" + task + ", completed=" + completed + "]";
	}

	public Task() {

	}

}
