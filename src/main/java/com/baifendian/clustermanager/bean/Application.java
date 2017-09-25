package com.baifendian.clustermanager.bean;

/**
 * <li>集群application类
 * @author node
 *
 */

public class Application {

	private String id;
	
	private String user;
	
	private String name;
	
	private String queue;
	
	private String state ;
	
	private String finalStatus;
	
	private double progress;
	
	private String trackingUI;
	
	private String trackingUrl;
	
	private String diagnostics;
	
	private long clusterId;
	
	private String applicationType;
	
	private String applicationTags;
	
	private long startedTime;
	
	private long finishedTime;
	
	private long elapsedTime;
	
	private String  amContainerLogs;
	
	private String amHostHttpAddress;
	
	private long allocatedMB;
	
	private int allocatedVCores;
	
	private int runningContainers;
	
	private long memorySeconds;
	
	private long vcoreSeconds;
	
	private long preemptedResourceMB;
	
	private long preemptedResourceVCores;
	
	private long numNonAMContainerPreempted;
	
	private long numAMContainerPreempted;
	
	private String uberized;
	
	
	
	

	public String getUberized() {
		return uberized;
	}

	public void setUberized(String uberized) {
		this.uberized = uberized;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getFinalStatus() {
		return finalStatus;
	}

	public void setFinalStatus(String finalStatus) {
		this.finalStatus = finalStatus;
	}

	public String getTrackingUI() {
		return trackingUI;
	}

	public void setTrackingUI(String trackingUI) {
		this.trackingUI = trackingUI;
	}

	public String getTrackingUrl() {
		return trackingUrl;
	}

	public void setTrackingUrl(String trackingUrl) {
		this.trackingUrl = trackingUrl;
	}

	public String getQueue() {
		return queue;
	}

	public void setQueue(String queue) {
		this.queue = queue;
	}

	

	public double getProgress() {
		return progress;
	}

	public void setProgress(double progress) {
		this.progress = progress;
	}

	public String getApplicationTags() {
		return applicationTags;
	}

	public void setApplicationTags(String applicationTags) {
		this.applicationTags = applicationTags;
	}

	public long getAllocatedMB() {
		return allocatedMB;
	}

	public void setAllocatedMB(long allocatedMB) {
		this.allocatedMB = allocatedMB;
	}

	public int getAllocatedVCores() {
		return allocatedVCores;
	}

	public void setAllocatedVCores(int allocatedVCores) {
		this.allocatedVCores = allocatedVCores;
	}

	public int getRunningContainers() {
		return runningContainers;
	}

	public void setRunningContainers(int runningContainers) {
		this.runningContainers = runningContainers;
	}

	public long getMemorySeconds() {
		return memorySeconds;
	}

	public void setMemorySeconds(long memorySeconds) {
		this.memorySeconds = memorySeconds;
	}

	public long getVcoreSeconds() {
		return vcoreSeconds;
	}

	public void setVcoreSeconds(long vcoreSeconds) {
		this.vcoreSeconds = vcoreSeconds;
	}

	public long getPreemptedResourceMB() {
		return preemptedResourceMB;
	}

	public void setPreemptedResourceMB(long preemptedResourceMB) {
		this.preemptedResourceMB = preemptedResourceMB;
	}

	public long getPreemptedResourceVCores() {
		return preemptedResourceVCores;
	}

	public void setPreemptedResourceVCores(long preemptedResourceVCores) {
		this.preemptedResourceVCores = preemptedResourceVCores;
	}

	public long getNumNonAMContainerPreempted() {
		return numNonAMContainerPreempted;
	}

	public void setNumNonAMContainerPreempted(long numNonAMContainerPreempted) {
		this.numNonAMContainerPreempted = numNonAMContainerPreempted;
	}

	public long getNumAMContainerPreempted() {
		return numAMContainerPreempted;
	}

	public void setNumAMContainerPreempted(long numAMContainerPreempted) {
		this.numAMContainerPreempted = numAMContainerPreempted;
	}

	public String getDiagnostics() {
		return diagnostics;
	}

	public void setDiagnostics(String diagnostics) {
		this.diagnostics = diagnostics;
	}

	public long getClusterId() {
		return clusterId;
	}

	public void setClusterId(long clusterId) {
		this.clusterId = clusterId;
	}

	public String getApplicationType() {
		return applicationType;
	}

	public void setApplicationType(String applicationType) {
		this.applicationType = applicationType;
	}

	public long getStartedTime() {
		return startedTime;
	}

	public void setStartedTime(long startedTime) {
		this.startedTime = startedTime;
	}

	public long getFinishedTime() {
		return finishedTime;
	}

	public void setFinishedTime(long finishedTime) {
		this.finishedTime = finishedTime;
	}

	public long getElapsedTime() {
		return elapsedTime;
	}

	public void setElapsedTime(long elapsedTime) {
		this.elapsedTime = elapsedTime;
	}

	public String getAmContainerLogs() {
		return amContainerLogs;
	}

	public void setAmContainerLogs(String amContainerLogs) {
		this.amContainerLogs = amContainerLogs;
	}

	public String getAmHostHttpAddress() {
		return amHostHttpAddress;
	}

	public void setAmHostHttpAddress(String amHostHttpAddress) {
		this.amHostHttpAddress = amHostHttpAddress;
	}

	
	
	
	
}
