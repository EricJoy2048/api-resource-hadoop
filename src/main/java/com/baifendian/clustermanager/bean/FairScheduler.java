package com.baifendian.clustermanager.bean;

/**
 * fair调度器信息类
 * @author BFD_491
 *
 */
public class FairScheduler {

	private String type;
	
	private FairRootQueue fairRootQueue;

	public FairRootQueue getFairRootQueue() {
		return fairRootQueue;
	}

	public void setFairRootQueue(FairRootQueue fairRootQueue) {
		this.fairRootQueue = fairRootQueue;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	
	
}
