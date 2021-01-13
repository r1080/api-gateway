package edu.tlabs.api.model;

import java.util.Date;

public class Task {
	
	private Integer taskId;
	private String taskName;
	private Priority priorityTypeEnum;
	private boolean isCompleted;
	private Date targetCompletionDate;
	private Date completionDate;
	
	public Task() {
	
	}
	
	public Integer getTaskId() {
		return taskId;
	}

	public void setTaskId(Integer taskId) {
		this.taskId = taskId;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public Priority getPriorityTypeEnum() {
		return priorityTypeEnum;
	}

	public void setPriorityTypeEnum(Priority priorityTypeEnum) {
		this.priorityTypeEnum = priorityTypeEnum;
	}

	public boolean isCompleted() {
		return isCompleted;
	}

	public void setCompleted(boolean isCompleted) {
		this.isCompleted = isCompleted;
	}

	public Date getTargetCompletionDate() {
		return targetCompletionDate;
	}

	public void setTargetCompletionDate(Date targetCompletionDate) {
		this.targetCompletionDate = targetCompletionDate;
	}

	public Date getCompletionDate() {
		return completionDate;
	}

	public void setCompletionDate(Date completionDate) {
		this.completionDate = completionDate;
	}

	@Override
	public String toString() {
		return "Task [id=" + taskId + ", taskName=" + taskName + ", priorityTypeEnum=" + priorityTypeEnum + ", isCompleted="
				+ isCompleted + ", targetCompletionDate=" + targetCompletionDate + ", completionDate=" + completionDate
				+ "]";
	}

	public Task(Integer taskId, String taskName, Priority priorityTypeEnum, boolean isCompleted,
			Date targetCompletionDate, Date completionDate) {
		this.taskId = taskId;
		this.taskName = taskName;
		this.priorityTypeEnum = priorityTypeEnum;
		this.isCompleted = isCompleted;
		this.targetCompletionDate = targetCompletionDate;
		this.completionDate = completionDate;
	}
	
	

}
