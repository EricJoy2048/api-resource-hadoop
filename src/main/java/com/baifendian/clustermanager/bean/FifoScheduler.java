package com.baifendian.clustermanager.bean;

/**
 * fifo调度器信息
 * @author BFD_491
 *
 */
public class FifoScheduler {

	private String type;
	
	private double capacity;
	
	private String qstate;
	
	private int minQueueMemoryCapacity;
	
	private int maxQueueMemoryCapacity;
	
	private int numNodes;
	
	private int usedNodeCapacity;
	
	private int availNodeCapacity;
	
	private int totalNodeCapacity;
	
	private int numContainers;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public double getCapacity() {
		return capacity;
	}

	public void setCapacity(double capacity) {
		this.capacity = capacity;
	}

	public String getQstate() {
		return qstate;
	}

	public void setQstate(String qstate) {
		this.qstate = qstate;
	}

	public int getMinQueueMemoryCapacity() {
		return minQueueMemoryCapacity;
	}

	public void setMinQueueMemoryCapacity(int minQueueMemoryCapacity) {
		this.minQueueMemoryCapacity = minQueueMemoryCapacity;
	}

	public int getMaxQueueMemoryCapacity() {
		return maxQueueMemoryCapacity;
	}

	public void setMaxQueueMemoryCapacity(int maxQueueMemoryCapacity) {
		this.maxQueueMemoryCapacity = maxQueueMemoryCapacity;
	}

	public int getNumNodes() {
		return numNodes;
	}

	public void setNumNodes(int numNodes) {
		this.numNodes = numNodes;
	}

	public int getUsedNodeCapacity() {
		return usedNodeCapacity;
	}

	public void setUsedNodeCapacity(int usedNodeCapacity) {
		this.usedNodeCapacity = usedNodeCapacity;
	}

	public int getAvailNodeCapacity() {
		return availNodeCapacity;
	}

	public void setAvailNodeCapacity(int availNodeCapacity) {
		this.availNodeCapacity = availNodeCapacity;
	}

	public int getTotalNodeCapacity() {
		return totalNodeCapacity;
	}

	public void setTotalNodeCapacity(int totalNodeCapacity) {
		this.totalNodeCapacity = totalNodeCapacity;
	}

	public int getNumContainers() {
		return numContainers;
	}

	public void setNumContainers(int numContainers) {
		this.numContainers = numContainers;
	}
	
	
	
}
