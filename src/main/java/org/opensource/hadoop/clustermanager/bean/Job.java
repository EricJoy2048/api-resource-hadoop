package org.opensource.hadoop.clustermanager.bean;


/**
 * <li>功能描述：job实体类
 * <li>如果state是running那么该实体中的finishJob为null,runningJob为当前job
 * <li>如果state是success那么实体中的runningJob为null,finishJob为当前job
 * @author node
 *
 */
public class Job {
	
	public String jobId;
	
	public String state;
	
	public FinishJob finishJob;
	
	public RunningJob runningJob;

	public String getJobId() {
		return jobId;
	}

	public void setJobId(String jobId) {
		this.jobId = jobId;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public FinishJob getFinishJob() {
		return finishJob;
	}

	public void setFinishJob(FinishJob finishJob) {
		this.finishJob = finishJob;
	}

	public RunningJob getRunningJob() {
		return runningJob;
	}

	public void setRunningJob(RunningJob runningJob) {
		this.runningJob = runningJob;
	}

	
	
	
	
}
