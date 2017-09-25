package com.baifendian.clustermanager.bean;


/**
 * <li>功能描述：资源使用历史记录类
 * @author node
 *
 */
public class ResourceHistory {

	private long id;
	
	//applicationId
	private String appId;
	
	//applicationName
	private String appName;
	
	//作业使用内存
	private long mb;
	
	//作业使用vcore
	private long vcore;
	
	//作业提交租户
	private String username;
	
	//作业状态
	private String state;
	
	//执行时长
	private long elapsedTime;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public long getMb() {
		return mb;
	}

	public void setMb(long mb) {
		this.mb = mb;
	}

	public long getVcore() {
		return vcore;
	}

	public void setVcore(long vcore) {
		this.vcore = vcore;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public long getElapsedTime() {
		return elapsedTime;
	}

	public void setElapsedTime(long elapsedTime) {
		this.elapsedTime = elapsedTime;
	}
}
