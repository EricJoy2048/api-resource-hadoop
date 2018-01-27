package org.opensource.hadoop.clustermanager.fromrestapi.server.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.opensource.hadoop.clustermanager.bean.Job;
import org.opensource.hadoop.clustermanager.bean.Task;
import org.opensource.hadoop.clustermanager.fromrestapi.server.IMapReduceServer;
import org.opensource.hadoop.clustermanager.fromrestapi.server.IResourceManagerServer;
import org.opensource.hadoop.clustermanager.fromrestapi.util.JsonToBeanTools;
import org.opensource.hadoop.clustermanager.fromrestapi.util.ResourceType;
import org.opensource.hadoop.clustermanager.fromrestapi.util.TaskStateType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sty.sadt.core.util.ClassLoaderUtil;
import org.sty.sadt.core.util.HttpClientTools;
import org.sty.sadt.core.util.PropertiesTools;

public class MapReduceServerImpl implements IMapReduceServer{
	
	public static final Logger log = LoggerFactory.getLogger(MapReduceServerImpl.class);

	public  PropertiesTools pt = new PropertiesTools(ClassLoaderUtil.getProperties("cluster.properties"));
	
	IResourceManagerServer resourceManager = new YarnRMServerImpl();

	

	public Map<String, List<Task>> getJobsTasksMap(String jobId,
			TaskStateType tst) {
		
		Map<String, List<Task>> mapTasksMap = new HashMap<String, List<Task>>();
		
		List<Task> tList = null;
		
		try{
			
			if(jobId == null || "".equals(jobId)){
				log.error("jobId 不能为空");
				return null;
			}
			
			String mapTasksUrl = getJobsTasksMapUrl(jobId,tst);
			String rpString = null;
			try{
				
				rpString = HttpClientTools.httpClientGet(mapTasksUrl);
				if(rpString != null && rpString.toUpperCase().contains("STANDBY")){
					log.error(rpString);
					resourceManager.resetActiveRMIP();
					mapTasksUrl = getJobsTasksMapUrl(jobId,tst);
					rpString = HttpClientTools.httpClientGet(mapTasksUrl);
				}
			}catch(Exception e){
				resourceManager.resetActiveRMIP();
				mapTasksUrl = getJobsTasksMapUrl(jobId,tst);
				rpString = HttpClientTools.httpClientGet(mapTasksUrl);
			}
			
			
			if(rpString != null && (rpString.toUpperCase().contains("REMOTEEXCEPTION") || rpString.toUpperCase().contains("<!DOCTYPE HTML") || rpString.toUpperCase().contains("TRY THE HISTORY SERVER"))){
				mapTasksUrl = this.getResourceUrl(ResourceType.YARN_HIS_TASKS_URL, jobId);
				mapTasksUrl += "?type=m";
				rpString = HttpClientTools.httpClientGet(mapTasksUrl);
				
			}
			List<Task> tasks = JsonToBeanTools.stringToTasks(rpString);
			if(tasks != null && tasks.size() > 0){
				for(Task ts : tasks){
					String state = ts.getState();
					if(tst == null ){
						if(mapTasksMap.get(state) == null){
							List<Task> list1 = new ArrayList<Task>();
							list1.add(ts);
							mapTasksMap.put(state, list1);
						}else{
							List<Task> list2 = mapTasksMap.get(state);
							list2.add(ts);
						}
					}else{
						if(tList == null)
							tList = new ArrayList<Task>();
						if(state != null && state.equals(tst.getTaskStatus()))
							tList.add(ts);
					}
					
				}
			}
			
			if(mapTasksMap.size() == 0 && tList != null && tList.size() > 0)
				mapTasksMap.put(tst.getTaskStatus(), tList);
		}catch(Exception e1){
			mapTasksMap = null;
			e1.printStackTrace();
		}
		return mapTasksMap;
	}
	
	private String getJobsTasksMapUrl(String jobId,
			TaskStateType tst){
		
		String mapTasksUrl = this.getResourceUrl(ResourceType.YARN_TASKS_URL, jobId);
		mapTasksUrl += "?type=m";
		return mapTasksUrl;
	}
	
	private String getgetJobsTasksReduceUrl(String jobId,
			TaskStateType tst){
		
		String mapTasksUrl = this.getResourceUrl(ResourceType.YARN_TASKS_URL, jobId);
		mapTasksUrl += "?type=r";
		return mapTasksUrl;
	}
	
	public Map<String, List<Task>> getJobsTasksReduce(String jobId,
			TaskStateType tst) {
		
		Map<String, List<Task>> mapTasksMap = new HashMap<String, List<Task>>();
		
		List<Task> tList = null;
		
		try{
			
			
			
			if(jobId == null || "".equals(jobId)){
				log.error("jobId 不能为空");
				return null;
			}
			
			String mapTasksUrl = getgetJobsTasksReduceUrl(jobId,tst);
			String rpString = null;
			try{
				
				rpString = HttpClientTools.httpClientGet(mapTasksUrl);
				if(rpString != null && rpString.toUpperCase().contains("STANDBY")){
					log.error(rpString);
					resourceManager.resetActiveRMIP();
					mapTasksUrl = getgetJobsTasksReduceUrl(jobId,tst);
					rpString = HttpClientTools.httpClientGet(mapTasksUrl);
				}
			}catch(Exception e){
				resourceManager.resetActiveRMIP();
				mapTasksUrl = getgetJobsTasksReduceUrl(jobId,tst);
				rpString = HttpClientTools.httpClientGet(mapTasksUrl);
			}
			
			
			if(rpString != null && (rpString.toUpperCase().contains("REMOTEEXCEPTION") || rpString.toUpperCase().contains("<!DOCTYPE HTML") || rpString.toUpperCase().contains("TRY THE HISTORY SERVER"))){
				mapTasksUrl = this.getResourceUrl(ResourceType.YARN_HIS_TASKS_URL, jobId);
				mapTasksUrl += "?type=r";
				rpString = HttpClientTools.httpClientGet(mapTasksUrl);
				
			}
			List<Task> tasks = JsonToBeanTools.stringToTasks(rpString);
			if(tasks != null && tasks.size() > 0){
				for(Task ts : tasks){
					String state = ts.getState();
					if(tst == null ){
						if(mapTasksMap.get(state) == null){
							List<Task> list1 = new ArrayList<Task>();
							list1.add(ts);
							mapTasksMap.put(state, list1);
						}else{
							List<Task> list2 = mapTasksMap.get(state);
							list2.add(ts);
						}
					}else{
						if(tList == null)
							tList = new ArrayList<Task>();
						if(state != null && state.equals(tst.getTaskStatus()))
							tList.add(ts);
					}
					
				}
			}
			
			if(mapTasksMap.size() == 0 && tList != null && tList.size() > 0)
				mapTasksMap.put(tst.getTaskStatus(), tList);
		}catch(Exception e1){
			mapTasksMap = null;
			e1.printStackTrace();
		}
		return mapTasksMap;
	}

	private String getJobUrl(String appId_or_jobId){
		String jobUrl = this.getResourceUrl(ResourceType.YARN_JOB_URL, appId_or_jobId);
		return jobUrl;
	}
	
	public Job getJob(String appId_or_jobId) {
		if(appId_or_jobId == null){
			log.error("appId_or_jobId 不能为空");
			return null;
		}
		Job job = null;
		
		try{
			
			String jobUrl = getJobUrl(appId_or_jobId);
			log.info("request url:"+jobUrl);
			
			String rpString = null;
			try{
				
				rpString = HttpClientTools.httpClientGet(jobUrl);
				if(rpString != null && rpString.toUpperCase().contains("STANDBY")){
					log.error(rpString);
					resourceManager.resetActiveRMIP();
					jobUrl = getJobUrl(appId_or_jobId);
					rpString = HttpClientTools.httpClientGet(jobUrl);
				}
			}catch(Exception e){
				resourceManager.resetActiveRMIP();
				jobUrl = getJobUrl(appId_or_jobId);
				rpString = HttpClientTools.httpClientGet(jobUrl);
			}
			
			if(rpString != null && (rpString.toUpperCase().contains("REMOTEEXCEPTION") || rpString.toUpperCase().contains("<!DOCTYPE") || rpString.toUpperCase().contains("TRY THE HISTORY SERVER"))){
				jobUrl = this.getResourceUrl(ResourceType.YARN_HIS_JOB_URL, appId_or_jobId);
				rpString = HttpClientTools.httpClientGet(jobUrl);
				job = JsonToBeanTools.stringToFinishJob(rpString);
			}else{
				job = JsonToBeanTools.stringToRunningJob(rpString);
			}
		}catch(Exception e1){
			e1.printStackTrace();
			return null;
		}
		return job;
	}

	
	/**
	 * 根据不同的请求类型构造资源url
	 * <li>yarn 历史作业信息请求地址 YARN_HIS_JOB_URL
	 * <li>yarn 历史作业中的tasks列表请求地址YARN_HIS_TASKS_URL
	 * <li>yarn 历史作业中的job的conf信息请求地址 YARN_HIS_JOB_CONF_URL 
	 * <li>yarn 正在运行的作业信息请求地址 YARN_HIS_JOB_URL
	 * <li>yarn 正在运行的作业中的tasks列表请求地址YARN_HIS_TASKS_URL
	 * <li>yarn 正在运行的作业中的job的conf信息请求地址 YARN_HIS_JOB_CONF_URL
	 * @param rt
	 * 		资源类型
	 * @param appId_or_jobId
	 * 		jobId或appId
	 * @return
	 */
	public String getResourceUrl(ResourceType rt , String appId_or_jobId) {
		String activeRMIP = resourceManager.getActiveRMIP();
		if(appId_or_jobId == null || "".equals(appId_or_jobId)){
			log.error("appId_or_jobId is null!");
			return null;
		}
		StringBuilder resourceUrl = new StringBuilder("http://");
		
		switch (rt) {
			
			case YARN_HIS_JOB_URL:
				resourceUrl.append(pt.getValue("jobhistory"))
							.append(":")
							.append(pt.getValue("jobhistory.port"))
							.append("/ws/v1/history/mapreduce/jobs/job")
							.append(appId_or_jobId.substring(appId_or_jobId.indexOf("_"), appId_or_jobId.length()));
				break;
				
			case YARN_HIS_TASKS_URL:
				resourceUrl.append(pt.getValue("jobhistory"))
							.append(":")
							.append(pt.getValue("jobhistory.port"))
							.append("/ws/v1/history/mapreduce/jobs/job")
							.append(appId_or_jobId.substring(appId_or_jobId.indexOf("_"), appId_or_jobId.length()))
							.append("/tasks");
				break;
			
			case YARN_HIS_JOB_CONF_URL:
				resourceUrl.append(pt.getValue("jobhistory"))
							.append(":")
							.append(pt.getValue("jobhistory.port"))
							.append("/ws/v1/history/mapreduce/jobs/job")
							.append(appId_or_jobId.substring(appId_or_jobId.indexOf("_"), appId_or_jobId.length()))
							.append("/conf");
				break;
				
			case YARN_JOB_URL:
				resourceUrl.append(activeRMIP)
							.append(":")
							.append(pt.getValue("rm.port"))
							.append("/proxy/application")
							.append(appId_or_jobId.substring(appId_or_jobId.indexOf("_"), appId_or_jobId.length()))
							.append("/ws/v1/mapreduce/jobs/job")
							.append(appId_or_jobId.substring(appId_or_jobId.indexOf("_"), appId_or_jobId.length()));
				break;
				
			case YARN_TASKS_URL:
				resourceUrl.append(activeRMIP)
							.append(":")
							.append(pt.getValue("rm.port"))
							.append("/proxy/application")
							.append(appId_or_jobId.substring(appId_or_jobId.indexOf("_"), appId_or_jobId.length()))
							.append("/ws/v1/mapreduce/jobs/job")
							.append(appId_or_jobId.substring(appId_or_jobId.indexOf("_"), appId_or_jobId.length()))
							.append("/tasks");
				break;	
				
			case YARN_JOB_CONF_URL:
				resourceUrl.append(activeRMIP)
							.append(":")
							.append(pt.getValue("rm.port"))
							.append("/proxy/application")
							.append(appId_or_jobId.substring(appId_or_jobId.indexOf("_"), appId_or_jobId.length()))
							.append("/ws/v1/mapreduce/jobs/job")
							.append(appId_or_jobId.substring(appId_or_jobId.indexOf("_"), appId_or_jobId.length()))
							.append("/conf");
				break;
				
			default:
				break;
		}
		return resourceUrl.toString();
	}
	
	
	
	
	/**
	 * 根据不同的请求类型构造资源url
	 * <li>yarn 历史作业中的单一task信息请求地址 YARN_HIS_TASK_URL
	 * <li>yarn 中正在运行的task的信息请求地址YARN_TASK_URL
	 * @param rt
	 * 		资源类型
	 * @param appId_or_jobId
	 * 		jobId或appId
	 * @param taskId
	 * 		taskId
	 * @return
	 */
	public String getResourceUrl(ResourceType rt , String appId_or_jobId , String taskId) {
		String activeRMIP = resourceManager.getActiveRMIP();
		if(appId_or_jobId == null || "".equals(appId_or_jobId) || taskId == null || "".equals(taskId)){
			log.error("appId_or_jobId or taskId is null");
			return null;
		}
		
		StringBuilder resourceUrl = new StringBuilder("http://");
		
		switch (rt) {
			
			case YARN_HIS_TASK_URL:
				resourceUrl.append(pt.getValue("jobhistory"))
							.append(":")
							.append(pt.getValue("jobhistory.port"))
							.append("/ws/v1/history/mapreduce/jobs/job")
							.append(appId_or_jobId.substring(appId_or_jobId.indexOf("_"), appId_or_jobId.length()))
							.append("/tasks/")
							.append(taskId);
				
				
				break;
			case YARN_TASK_URL:
				resourceUrl.append(activeRMIP)
							.append(":")
							.append(pt.getValue("rm.port"))
							.append("/proxy/application")
							.append(appId_or_jobId.substring(appId_or_jobId.indexOf("_"), appId_or_jobId.length()))
							.append("/ws/v1/mapreduce/jobs/job")
							.append(appId_or_jobId.substring(appId_or_jobId.indexOf("_"), appId_or_jobId.length()))
							.append("/tasks/")
							.append(taskId);
				break;	
				
			default:
				break;
		}
		return resourceUrl.toString();
	}
}
