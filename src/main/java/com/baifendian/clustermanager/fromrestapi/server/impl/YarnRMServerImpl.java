package com.baifendian.clustermanager.fromrestapi.server.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sty.sadt.core.util.ClassLoaderUtil;
import org.sty.sadt.core.util.DataFormatTool;
import org.sty.sadt.core.util.HttpClientTools;
import org.sty.sadt.core.util.PropertiesTools;

import com.baifendian.clustermanager.bean.Application;
import com.baifendian.clustermanager.bean.CapacityScheduler;
import com.baifendian.clustermanager.bean.FairScheduler;
import com.baifendian.clustermanager.bean.FifoScheduler;
import com.baifendian.clustermanager.fromrestapi.server.IResourceManagerServer;
import com.baifendian.clustermanager.fromrestapi.util.ApplicationFinalState;
import com.baifendian.clustermanager.fromrestapi.util.JsonToBeanTools;
import com.baifendian.clustermanager.fromrestapi.util.ResourceManagerParam;
import com.baifendian.clustermanager.fromrestapi.util.ResourceType;
import com.baifendian.clustermanager.fromrestapi.util.SchedulerType;

/**
 * yarn resourcemanager 接口实现类
 * @author BFD_491
 *
 */
public class YarnRMServerImpl implements IResourceManagerServer{
	
	public static final Logger log = LoggerFactory.getLogger(YarnRMServerImpl.class);

	public  PropertiesTools pt = new PropertiesTools(ClassLoaderUtil.getProperties("cluster.properties"));
	
	
	public List<Application> getAppList(Long limit) {
		
		String url = getAppListUrl(limit);
		String rpString = null;
		List<Application> appList = null;
		try{
			rpString = HttpClientTools.httpClientGet(url);
			if(rpString != null && rpString.toUpperCase().contains("STANDBY")){
				log.error(rpString);
				resetActiveRMIP();
				url = getAppListUrl(limit);
				rpString = HttpClientTools.httpClientGet(url);
			}
		}catch(Exception e){
			resetActiveRMIP();
			url = getAppListUrl(limit);
			try {
				rpString = HttpClientTools.httpClientGet(url);
			} catch (Exception e1) {
				e1.printStackTrace();
				return null;
			} 
		}
		
		appList = JsonToBeanTools.stringToApps(rpString);
		if(appList == null)
			appList = new ArrayList<Application>();
		return appList;
	}
	
	
	private String getAppListUrl(Long limit){
		if(limit == null)
			limit = 10l;
		String url = getResourceUrl(ResourceType.YARN_APP_LIST_URL);
		
		if(limit != -1){
			url = url + "?limit="+limit;
		}
		
		return url;
	}

	public List<Application> getAppList(String username) {
		String url = getAppListUrl(username);
		String rpString = null;
		try{
			rpString = HttpClientTools.httpClientGet(url);
			if(rpString != null && rpString.toUpperCase().contains("STANDBY")){
				log.error(rpString);
				resetActiveRMIP();
				url = getAppListUrl(username);
				rpString = HttpClientTools.httpClientGet(url);
			}
		}
		catch (Exception e) {
			resetActiveRMIP();
			url = getAppListUrl(username);
			try {
				rpString = HttpClientTools.httpClientGet(url);
			} catch (Exception e1) {
				e1.printStackTrace();
				return null;
			}
		}
		
		
		List<Application> appList = JsonToBeanTools.stringToApps(rpString);
		if(appList == null)
			appList = new ArrayList<Application>();
		return appList;
	}

	private String getAppListUrl(String username){
		String url = getResourceUrl(ResourceType.YARN_APP_LIST_URL);
		url = url + "?user="+username;
		
		return url;
	}


	public List<Application> getAppList(String username, ApplicationFinalState aft) {
		if(aft == null){
			log.error("作业完成状态类型不能为空!");
			return null;
		}
		String url = getAppListUrl(username,aft);
		String rpString = null;
		try{
			rpString = HttpClientTools.httpClientGet(url);
			if(rpString != null && rpString.toUpperCase().contains("STANDBY")){
				log.error(rpString);
				resetActiveRMIP();
				url = getAppListUrl(username,aft);
				rpString = HttpClientTools.httpClientGet(url);
			}
		}
		catch (Exception e) {
			resetActiveRMIP();
			url = getAppListUrl(username,aft);
			try {
				rpString = HttpClientTools.httpClientGet(url);
			} catch (Exception e1) {
				e1.printStackTrace();
				return null;
			} 
		}
		
		List<Application> appList = JsonToBeanTools.stringToApps(rpString);
		if(appList == null)
			appList = new ArrayList<Application>();
		return appList;
	}

	private String getAppListUrl(String username, ApplicationFinalState aft){
		String url = getResourceUrl(ResourceType.YARN_APP_LIST_URL);
		url = url + "?user="+username+"&finalStatus="+aft.getFinalState();
		
		return url;
	}


	public List<Application> getAppList(String username, Long startedTimeBegin,
			Long finishedTimeEnd) {
		
//		if(username == null || "".equals(username)  || startedTimeBegin == null){
//			log.error("username 或 startedTimeBegin不能为空！");
//			return null;
//		}
		
		if(startedTimeBegin == null){
			log.error("startedTimeBegin不能为空！");
			return null;
		}
		
		
		String url = getAppListUrl(username,startedTimeBegin,finishedTimeEnd);
		String rpString = null;
		
		try{
			log.info("1----"+url);
			rpString = HttpClientTools.httpClientGet(url);
			log.info("1----"+rpString);
			if(rpString != null && rpString.toUpperCase().contains("STANDBY")){
				
				log.info("1 error ----"+rpString);
				log.info("RM主备切换了，切换到Active的RM");
				resetActiveRMIP();
				url = getAppListUrl(username,startedTimeBegin,finishedTimeEnd);
				log.info("2 ----"+url);
				rpString = HttpClientTools.httpClientGet(url);
				log.info("2 ----"+rpString);
			}
		}
		catch (Exception e) {
			log.info("3 Exception----"+e.getMessage());
			log.info("RM挂了，切换到Active的RM");
			resetActiveRMIP();
			url = getAppListUrl(username,startedTimeBegin,finishedTimeEnd);
			log.info("3 ----"+url);
			try {
				rpString = HttpClientTools.httpClientGet(url);
				log.info("3 ----"+rpString);
			} catch (Exception e1) {
				e1.printStackTrace();
				return null;
			} 
		}
		
		
		
		List<Application> appList = JsonToBeanTools.stringToApps(rpString);
		if(appList == null)
			appList = new ArrayList<Application>();
		return appList;
	}
	
	private String getAppListUrl(String username, Long startedTimeBegin,
			Long finishedTimeEnd){
		String url = getResourceUrl(ResourceType.YARN_APP_LIST_URL);
		
		if(username != null && !"".equals(username)){
			url = url + "?user="+username+"&startedTimeBegin="+startedTimeBegin;
		}else{
			url = url + "?startedTimeBegin="+startedTimeBegin;
		}
		
		if(finishedTimeEnd != null){
			url += "&finishedTimeEnd="+finishedTimeEnd;
		}
		return url;
	}
	
	public String getActiveRMIP() {
		if(ResourceManagerParam.activeRMIP == null){
			this.resetActiveRMIP();
		}
		return ResourceManagerParam.activeRMIP;
	}
	
	public void resetActiveRMIP(){
		String activeIP = pt.getValue("RM2");;
		String cluster_url = "http://"+activeIP+":"+pt.getValue("rm.port")+"/ws/v1/cluster/info";
		String str = null;
		try{
			str = HttpClientTools.httpClientGet(cluster_url);
			if(str.toUpperCase().contains("STANDBY")){
				activeIP = pt.getValue("RM1");
			}
		}catch(Exception e){
			activeIP = pt.getValue("RM1");
		}
		
		ResourceManagerParam.activeRMIP = activeIP;
	}
	
	
	
	
	public List<Application> getAppList(String username, Long startedTimeBegin,
			Long finishedTimeEnd, String jobUnCore) {
		
		log.info("username: "+username);
		log.info("startedTimeBegin: "+startedTimeBegin);
		log.info("finishedTimeEnd: "+finishedTimeEnd);
		log.info("jobUnCore: "+jobUnCore);
		List<Application> applications = new ArrayList<Application>();
		
		List<Application> appList = this.getAppList(username, startedTimeBegin, finishedTimeEnd);
		if(appList != null && appList.size() > 0){
			for(Application app : appList){
				if(app.getName().contains(jobUnCore)){
					applications.add(app);
				}
			}
		}
		
		return applications;
	}
	
	
	/**
	 * 根据不同的请求类型构造资源url
	 * <li>集群信息地址YARN_CLUSTER_INFO_URL
	 * <li>YARN application 列表请求地址YARN_APP_LIST_URL
	 * @param rt
	 * 		资源类型
	 * @return
	 */
	private String getResourceUrl(ResourceType rt) {
		String activeRMIP = this.getActiveRMIP();
		StringBuilder resourceUrl = new StringBuilder("http://");
		switch (rt) {
		case YARN_CLUSTER_INFO_URL:
			
			resourceUrl.append(activeRMIP)
						.append(":")
						.append(pt.getValue("rm.port"))
						.append("/ws/v1/cluster/info");
			break;

		case YARN_APP_LIST_URL:
			resourceUrl.append(activeRMIP)
						.append(":")
						.append(pt.getValue("rm.port"))
						.append("/ws/v1/cluster/apps");
			break;
			
		case YARN_CLUSTER_SCHEDULER_URL:
			resourceUrl.append(activeRMIP)
						.append(":")
						.append(pt.getValue("rm.port"))
						.append("/ws/v1/cluster/scheduler");
			break;
		
		default:
			break;
		}
		return resourceUrl.toString();
	}

	public double getClusterResourceUsage() {
		String url = this.getResourceUrl(ResourceType.YARN_CLUSTER_SCHEDULER_URL);
		String rpString;
		try {
			rpString = HttpClientTools.httpClientGet(url);
		} catch (Exception e) {
			e.printStackTrace();
			return 1;
		} 
		Object scheduler = JsonToBeanTools.stringToScheduler(rpString);
		if(scheduler instanceof FifoScheduler){
			return ((FifoScheduler) scheduler).getUsedNodeCapacity();
		}else if(scheduler instanceof CapacityScheduler){
			return ((CapacityScheduler) scheduler).getUsedCapacity();
		}else if(scheduler instanceof FairScheduler){
			int usedMemory = ((FairScheduler)scheduler).getFairRootQueue().getUsedResources().getMemory();
			int totalMemory = ((FairScheduler)scheduler).getFairRootQueue().getClusterResources().getMemory();
			
			return DataFormatTool.div(usedMemory+0.0, totalMemory+0.0, 2);
		}
		return 0.00;
	}

	public SchedulerType getSchedulerType() {
		String url = this.getResourceUrl(ResourceType.YARN_CLUSTER_SCHEDULER_URL);
		String rpString;
		try {
			rpString = HttpClientTools.httpClientGet(url);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} 
		Object scheduler = JsonToBeanTools.stringToScheduler(rpString);
		if(scheduler instanceof FifoScheduler){
			return SchedulerType.FIFO;
		}else if(scheduler instanceof CapacityScheduler){
			return SchedulerType.CAPACITYSCHEDULER;
		}else if(scheduler instanceof FairScheduler){
			return SchedulerType.FAIRSCHEDULER;
		}
		return null;
	}

	public FairScheduler getFairScheduler() {
		String url = this.getResourceUrl(ResourceType.YARN_CLUSTER_SCHEDULER_URL);
		String rpString;
		try {
			rpString = HttpClientTools.httpClientGet(url);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} 
		Object scheduler = JsonToBeanTools.stringToScheduler(rpString);
		if(scheduler instanceof FairScheduler){
			return (FairScheduler)scheduler;
		}
		return null;
	}

	public CapacityScheduler getCapacityScheduler() {
		String url = this.getResourceUrl(ResourceType.YARN_CLUSTER_SCHEDULER_URL);
		String rpString;
		try {
			rpString = HttpClientTools.httpClientGet(url);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} 
		Object scheduler = JsonToBeanTools.stringToScheduler(rpString);
		if(scheduler instanceof CapacityScheduler){
			return (CapacityScheduler)scheduler;
		}
		return null;
	}

	public FifoScheduler getFifoScheduler() {
		String url = this.getResourceUrl(ResourceType.YARN_CLUSTER_SCHEDULER_URL);
		String rpString;
		try {
			rpString = HttpClientTools.httpClientGet(url);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} 
		Object scheduler = JsonToBeanTools.stringToScheduler(rpString);
		if(scheduler instanceof FifoScheduler){
			return (FifoScheduler)scheduler;
		}
		return null;
	}



}
