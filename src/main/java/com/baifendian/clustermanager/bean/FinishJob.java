package com.baifendian.clustermanager.bean;

/**
 * 已经运行完成了的作业job信息
 * @author BFD_491
 *
 */
public class FinishJob {
	private long submitTime                    ;
	private long startTime                     ;
	private long finishTime                    ;
	private String id                            ;
	private String name                          ;
	private String queue                         ;
	private String user                          ;
	private String state                         ;
	private int mapsTotal                     ;
	private int mapsCompleted                 ;
	private int reducesTotal                  ;
	private int reducesCompleted              ;
	private String uberized                      ;
	private String diagnostics                   ;
	private long avgMapTime                    ;
	private long avgReduceTime                 ;
	private long avgShuffleTime                ;
	private long avgMergeTime                  ;
	private int failedReduceAttempts          ;
	private int killedReduceAttempts          ;
	private int successfulReduceAttempts      ;
	private int failedMapAttempts             ;
	private int killedMapAttempts             ;
	private int successfulMapAttempts         ;
	public long getSubmitTime() {
		return submitTime;
	}
	public void setSubmitTime(long submitTime) {
		this.submitTime = submitTime;
	}
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
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getQueue() {
		return queue;
	}
	public void setQueue(String queue) {
		this.queue = queue;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public int getMapsTotal() {
		return mapsTotal;
	}
	public void setMapsTotal(int mapsTotal) {
		this.mapsTotal = mapsTotal;
	}
	public int getMapsCompleted() {
		return mapsCompleted;
	}
	public void setMapsCompleted(int mapsCompleted) {
		this.mapsCompleted = mapsCompleted;
	}
	public int getReducesTotal() {
		return reducesTotal;
	}
	public void setReducesTotal(int reducesTotal) {
		this.reducesTotal = reducesTotal;
	}
	public int getReducesCompleted() {
		return reducesCompleted;
	}
	public void setReducesCompleted(int reducesCompleted) {
		this.reducesCompleted = reducesCompleted;
	}
	public String getUberized() {
		return uberized;
	}
	public void setUberized(String uberized) {
		this.uberized = uberized;
	}
	public String getDiagnostics() {
		return diagnostics;
	}
	public void setDiagnostics(String diagnostics) {
		this.diagnostics = diagnostics;
	}
	public long getAvgMapTime() {
		return avgMapTime;
	}
	public void setAvgMapTime(long avgMapTime) {
		this.avgMapTime = avgMapTime;
	}
	public long getAvgReduceTime() {
		return avgReduceTime;
	}
	public void setAvgReduceTime(long avgReduceTime) {
		this.avgReduceTime = avgReduceTime;
	}
	public long getAvgShuffleTime() {
		return avgShuffleTime;
	}
	public void setAvgShuffleTime(long avgShuffleTime) {
		this.avgShuffleTime = avgShuffleTime;
	}
	public long getAvgMergeTime() {
		return avgMergeTime;
	}
	public void setAvgMergeTime(long avgMergeTime) {
		this.avgMergeTime = avgMergeTime;
	}
	public int getFailedReduceAttempts() {
		return failedReduceAttempts;
	}
	public void setFailedReduceAttempts(int failedReduceAttempts) {
		this.failedReduceAttempts = failedReduceAttempts;
	}
	public int getKilledReduceAttempts() {
		return killedReduceAttempts;
	}
	public void setKilledReduceAttempts(int killedReduceAttempts) {
		this.killedReduceAttempts = killedReduceAttempts;
	}
	public int getSuccessfulReduceAttempts() {
		return successfulReduceAttempts;
	}
	public void setSuccessfulReduceAttempts(int successfulReduceAttempts) {
		this.successfulReduceAttempts = successfulReduceAttempts;
	}
	public int getFailedMapAttempts() {
		return failedMapAttempts;
	}
	public void setFailedMapAttempts(int failedMapAttempts) {
		this.failedMapAttempts = failedMapAttempts;
	}
	public int getKilledMapAttempts() {
		return killedMapAttempts;
	}
	public void setKilledMapAttempts(int killedMapAttempts) {
		this.killedMapAttempts = killedMapAttempts;
	}
	public int getSuccessfulMapAttempts() {
		return successfulMapAttempts;
	}
	public void setSuccessfulMapAttempts(int successfulMapAttempts) {
		this.successfulMapAttempts = successfulMapAttempts;
	}
	
	
	
}
