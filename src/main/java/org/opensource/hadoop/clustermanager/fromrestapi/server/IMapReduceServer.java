package org.opensource.hadoop.clustermanager.fromrestapi.server;

import java.util.List;
import java.util.Map;

import org.opensource.hadoop.clustermanager.bean.Job;
import org.opensource.hadoop.clustermanager.bean.Task;
import org.opensource.hadoop.clustermanager.fromrestapi.util.TaskStateType;

/**
 * 对MapReduce rest api的封装
 * @author BFD_491
 *
 */
public interface IMapReduceServer {

	/**
	 * 根据jobId获取作业map tasks信息
	 * <br/>该接口会先请求正MR Application Master从正在运行的作业中查找,
	 * <br/>请求失败后会从MR History Server中请求历史作业 
	 * @param jobId
	 * 		jobId
	 * @param tt
	 * 		task的状态类型，有
	 * @return
	 */
	public Map<String, List<Task>> getJobsTasksMap(String jobId , TaskStateType tst);
	
	/**
	 * 根据jobId获取作业reduce tasks信息
	 * <br/>该接口会先请求正MR Application Master从正在运行的作业中查找,
	 * <br/>请求失败后会从MR History Server中请求历史作业 
	 * @param jobId
	 * 		jobId
	 * @param tt
	 * 		task的状态类型，有
	 * @return
	 */
	public Map<String, List<Task>> getJobsTasksReduce(String jobId , TaskStateType tst);
	
	/**
	 * 根据app获取该app下的所有job,
	 * <br/>该接口会先请求正MR Application Master从正在运行的作业中查找,
	 * <br/>请求失败后会从MR History Server中请求历史作业 
	 * @param app
	 * 		application
	 * @return
	 */
	public Job getJob(String appId_or_jobId);
	
}
