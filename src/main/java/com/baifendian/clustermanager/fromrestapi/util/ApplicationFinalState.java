package com.baifendian.clustermanager.fromrestapi.util;

/**
 * 已完成application 的状态类型
 * @author BFD_491
 *
 */
public enum ApplicationFinalState {

	UNDEFINED("UNDEFINED"),SUCCEEDED("SUCCEEDED"),KILLED("KILLED"),FAILED("FAILED");
	
	private String finalState;
	
	private ApplicationFinalState(String finalState){
		this.finalState = finalState;
	}

	public String getFinalState() {
		return finalState;
	}

	public void setFinalState(String finalState) {
		this.finalState = finalState;
	}

	
	
	
}
