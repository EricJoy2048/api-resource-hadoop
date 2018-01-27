package org.opensource.hadoop.clustermanager.fromrestapi.util;

public enum SchedulerType {

	FIFO("fifoScheduler"),CAPACITYSCHEDULER("capacityScheduler"),FAIRSCHEDULER("fairScheduler");
	
	
	private SchedulerType(String scheduler){
		this.scheduler = scheduler;
	}
	
	private String scheduler;

	public String getScheduler() {
		return scheduler;
	}

	public void setScheduler(String scheduler) {
		this.scheduler = scheduler;
	}
	
	
}
