package com.baifendian.clustermanager.fromrestapi.server;

import java.util.List;

import com.baifendian.clustermanager.bean.Application;
import com.baifendian.clustermanager.bean.CapacityScheduler;
import com.baifendian.clustermanager.bean.FairScheduler;
import com.baifendian.clustermanager.bean.FifoScheduler;
import com.baifendian.clustermanager.fromrestapi.util.ApplicationFinalState;
import com.baifendian.clustermanager.fromrestapi.util.SchedulerType;

/**
 * 通过yarn resourcemanager rest api封装成的RM接口
 * @author BFD_491
 *
 */
public interface IResourceManagerServer {
	
	
	/**
	 * 获取集群资源总的使用情况 
	 * @return
	 */
	public double getClusterResourceUsage();
	
	
	/**
	 * 根据用户名，开始时间，结束时间，jobUnCore查询作业列表
	 * @param username
	 * 		用户名
	 * @param startedTimeBegin
	 * 		作业开始时间之前的时间点  ms 不能为空
	 * @param finishedTimeEnd
	 * 		作业结束时间之后的时间点  ms 可以为空，当为空时不加该过滤条件 
	 * @param jobUnCore
	 * 		jobname的后缀unCore,由bd-os生成
	 * @return
	 */
	public List<Application> getAppList(String username 
			, Long startedTimeBegin 
			, Long finishedTimeEnd
			, String jobUnCore);
	
	
	/**
	 * 根据用户名查询application信息
	 * @param username
	 * 		提交作业的用户名
	 * @return
	 */
	public List<Application> getAppList(String username);
	
	/**
	 * 根据用户名和作业完成状态查询作业
	 * @param username
	 * 		提交作业的用户名
	 * @param aft
	 * 		作业的完成状态:UNDEFINED,SUCCEEDED,KILLED,FAILED
	 * @return
	 */
	public List<Application> getAppList(String username , ApplicationFinalState aft );
	
	/**
	 * 根据用户名，开始时间与结束时间查找作业 
	 * @param username
	 * 		用户名
	 * @param startedTimeEnd
	 * 		作业开始时间之前的时间点  ms
	 * @param finishedTimeEnd
	 * 		作业结束时间之后的时间点  ms
	 * @return
	 */
	public List<Application> getAppList(String username , Long startedTimeBegin , Long finishedTimeEnd );
	
	/**
	 * 获取资源历史列表
	 * @param limit
	 * 		查询前多少条记录,limit=-1是不限制条数
	 * @return
	 */
	public List<Application> getAppList(Long limit);

	/**
	 * 使用命令行的方式kill作业 
	 * @param appId
	 * @return
	 */
//	public boolean killApplicationsOne(String appId);
	
	
	/**
	 * 获取当前active的RM ip
	 * @return
	 */
	public String getActiveRMIP();
	
	/**
	 * 重置activeRMIP
	 */
	public void resetActiveRMIP();
	
	/**
	 * 获取application的Memory值
	 * @param job
	 * 		job信息
	 * @return
	 */
//	public long getMemoryNeed(Job job);
	
	/**
	 * 获取application的vCore值
	 * @param job
	 * 		job信息
	 * @return
	 */
//	public long getVcoreNeed(Job job);
	
	
	/**
	 * 获取当前集群的调度器类型
	 * @return
	 */
	public SchedulerType getSchedulerType();
	
	/**
	 * 获取fair调度器信息
	 * @return
	 */
	public FairScheduler getFairScheduler();
	
	/**
	 * 获取capacity调度器信息
	 * @return
	 */
	public CapacityScheduler getCapacityScheduler();
	
	/**
	 * 获取fifo调度器信息
	 * @return
	 */
	public FifoScheduler getFifoScheduler();
	
	
}
