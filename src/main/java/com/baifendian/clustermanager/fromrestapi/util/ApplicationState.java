package com.baifendian.clustermanager.fromrestapi.util;

/**
 * application的状态信息
 * @author BFD_491
 *
 */
public enum ApplicationState {

	NEW("NEW"),
	NEW_SAVING("NEW_SAVING"),
	SUBMITTED("SUBMITTED"),
	ACCEPTED("ACCEPTED"),
	RUNNING("RUNNING"),
	FINISHED("FINISHED"),
	FAILED("FAILED"),
	KILLED("KILLED");
	
	private String state;
	
	private ApplicationState(String state){
		this.state = state;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

}
