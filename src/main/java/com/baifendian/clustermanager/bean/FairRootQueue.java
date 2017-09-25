package com.baifendian.clustermanager.bean;

/**
 * FairScheduler调度器的rootQueue信息类
 * @author BFD_491
 *
 */
public class FairRootQueue {

	private int maxApps;
	
	private Resource minResources;
	
	private Resource maxResources;
	
	private Resource usedResources;
	
	private Resource fairResources;
	
	private Resource clusterResources;
	
	private String queueName;
	
	private String schedulingPolicy;

	public int getMaxApps() {
		return maxApps;
	}

	public void setMaxApps(int maxApps) {
		this.maxApps = maxApps;
	}

	public Resource getMinResources() {
		return minResources;
	}

	public void setMinResources(Resource minResources) {
		this.minResources = minResources;
	}

	public Resource getMaxResources() {
		return maxResources;
	}

	public void setMaxResources(Resource maxResources) {
		this.maxResources = maxResources;
	}

	public Resource getUsedResources() {
		return usedResources;
	}

	public void setUsedResources(Resource usedResources) {
		this.usedResources = usedResources;
	}

	public Resource getFairResources() {
		return fairResources;
	}

	public void setFairResources(Resource fairResources) {
		this.fairResources = fairResources;
	}

	public Resource getClusterResources() {
		return clusterResources;
	}

	public void setClusterResources(Resource clusterResources) {
		this.clusterResources = clusterResources;
	}

	public String getQueueName() {
		return queueName;
	}

	public void setQueueName(String queueName) {
		this.queueName = queueName;
	}

	public String getSchedulingPolicy() {
		return schedulingPolicy;
	}

	public void setSchedulingPolicy(String schedulingPolicy) {
		this.schedulingPolicy = schedulingPolicy;
	}
	
	
}
