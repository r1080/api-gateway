package edu.tlabs.api.model;

public enum PriorityTypeEnum {

	HIGH("High"),MEDIUM("Medium"),LOW("Low");
	
	private String value;
	
	PriorityTypeEnum(String value){
		this.value = value;
	}
	
	public String getValue(){
		return this.value;
	}
}
