package org.opensource.hadoop.clustermanager.fromrestapi.util;

public enum TaskType {
	
	M("m"),R("r");
	
	private String taskType;

	private TaskType(String taskType){
		this.taskType = taskType;
	}

	public String getTaskType() {
		return taskType;
	}

	public void setTaskType(String taskType) {
		this.taskType = taskType;
	}
	
	
}
