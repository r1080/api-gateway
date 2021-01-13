package edu.tlabs.api.model;

public enum Priority {

	HIGH("High"),MEDIUM("Medium"),LOW("Low");
	
	private String value;
	
	Priority(String value){
		this.value = value;
	}
	
	public String getValue(){
		return this.value;
	}
}
