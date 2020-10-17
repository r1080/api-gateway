package edu.tlabs.api.model;

import java.util.Collections;
import java.util.List;

public class Tasks {

	private int count;
	private List<Task> data;

	public Tasks(List<Task> tasks) {
		this.count = tasks.size();
		this.data = tasks;
	}
	
	public Tasks(){
		
	}
	
	public void setCount(int count) {
		this.count = count;
	}

	public void setData(List<Task> data) {
		this.data = data;
	}

	public int getCount() {
		return count;
	}

	public List<Task> getData() {
		return Collections.unmodifiableList(data);
	}

	@Override
	public String toString() {
		return "Tasks [count=" + count + ", data=" + data + "]";
	}

}
