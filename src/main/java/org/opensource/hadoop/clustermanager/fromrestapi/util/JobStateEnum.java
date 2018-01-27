package org.opensource.hadoop.clustermanager.fromrestapi.util;

public enum JobStateEnum {

	NEW("NEW"),
	INITED("INITED"),
	RUNNING("RUNNING"),
	SUCCEEDED("SUCCEEDED"),
	FAILED("FAILED"),
	KILL_WAIT("KILL_WAIT"),
	KILLED("KILLED"),
	ERROR("ERROR");
	
	private JobStateEnum(String state){
		this.state = state;
	}
	
	private String state;

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	
}
