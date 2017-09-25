package com.baifendian.clustermanager.bean;

/**
 * <li>功能描述：资源限定类
 * @author node
 *
 */
public class ResourceQuota {

	
	private long id;
	
	//租户
	private String username;
	
	//内存限额
	private long memoryQuota;
	
	//vcore限额
	private long vcoreQuota;
	
	//已使用内存
	private long memoryUsed;
	
	//已使用vcore
	private long vcoreUsed;
	
	//剩余内存
	private long memoryOver;
	
	//剩余vcore
	private long vcoreOver;
	
	//是否可以提交作业
	private int canSubmit;
	
	//历史内存总额
	private long memoryTotal;
	
	//历史vcore总额
	private long vcoreTotal;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public long getMemoryQuota() {
		return memoryQuota;
	}

	public void setMemoryQuota(long memoryQuota) {
		this.memoryQuota = memoryQuota;
	}

	public long getVcoreQuota() {
		return vcoreQuota;
	}

	public void setVcoreQuota(long vcoreQuota) {
		this.vcoreQuota = vcoreQuota;
	}

	public long getMemoryUsed() {
		return memoryUsed;
	}

	public void setMemoryUsed(long memoryUsed) {
		this.memoryUsed = memoryUsed;
	}

	public long getVcoreUsed() {
		return vcoreUsed;
	}

	public void setVcoreUsed(long vcoreUsed) {
		this.vcoreUsed = vcoreUsed;
	}

	public long getMemoryOver() {
		return memoryOver;
	}

	public void setMemoryOver(long memoryOver) {
		this.memoryOver = memoryOver;
	}

	public long getVcoreOver() {
		return vcoreOver;
	}

	public void setVcoreOver(long vcoreOver) {
		this.vcoreOver = vcoreOver;
	}

	public int getCanSubmit() {
		return canSubmit;
	}

	public void setCanSubmit(int canSubmit) {
		this.canSubmit = canSubmit;
	}

	public long getMemoryTotal() {
		return memoryTotal;
	}

	public void setMemoryTotal(long memoryTotal) {
		this.memoryTotal = memoryTotal;
	}

	public long getVcoreTotal() {
		return vcoreTotal;
	}

	public void setVcoreTotal(long vcoreTotal) {
		this.vcoreTotal = vcoreTotal;
	}
	
	
	
}
