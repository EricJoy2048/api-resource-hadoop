package org.opensource.hadoop.clustermanager.fromrestapi.util;

public enum TaskStateType {

	SUCCEEDED("SUCCEEDED"),
	NEW("NEW"),
	SCHEDULED("SCHEDULED"),
	RUNNING("RUNNING"),
	FAILED("FAILED"),
	KILL_WAIT("KILL_WAIT"),
	KILLED("KILLED");
	
	private TaskStateType(String taskStatus){
		this.taskStatus = taskStatus;
	}
	
	private String taskStatus;

	public String getTaskStatus() {
		return taskStatus;
	}

	public void setTaskStatus(String taskStatus) {
		this.taskStatus = taskStatus;
	}
	
	
}
