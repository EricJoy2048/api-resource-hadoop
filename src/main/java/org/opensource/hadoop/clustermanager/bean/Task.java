package org.opensource.hadoop.clustermanager.bean;

/**
 * map task信息
 * @author BFD_491
 *
 */
public class Task {

	//map task id
	private long startTime;
	
	private long finishTime;
	
	private long elapsedTime;
	
	private double progress;
	
	private String id;
	
	private String state;
	
	private String type;
	
	private String successfulAttempt;
	

	public long getStartTime() {
		return startTime;
	}

	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}

	public long getFinishTime() {
		return finishTime;
	}

	public void setFinishTime(long finishTime) {
		this.finishTime = finishTime;
	}

	public long getElapsedTime() {
		return elapsedTime;
	}

	public void setElapsedTime(long elapsedTime) {
		this.elapsedTime = elapsedTime;
	}

	

	

	public double getProgress() {
		return progress;
	}

	public void setProgress(double progress) {
		this.progress = progress;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSuccessfulAttempt() {
		return successfulAttempt;
	}

	public void setSuccessfulAttempt(String successfulAttempt) {
		this.successfulAttempt = successfulAttempt;
	}

	
	
}
