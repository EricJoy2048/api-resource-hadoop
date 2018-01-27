package org.opensource.hadoop.clustermanager.fromrestapi.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.opensource.hadoop.clustermanager.bean.Application;
import org.opensource.hadoop.clustermanager.bean.CapacityScheduler;
import org.opensource.hadoop.clustermanager.bean.FairRootQueue;
import org.opensource.hadoop.clustermanager.bean.FairScheduler;
import org.opensource.hadoop.clustermanager.bean.FifoScheduler;
import org.opensource.hadoop.clustermanager.bean.FinishJob;
import org.opensource.hadoop.clustermanager.bean.Job;
import org.opensource.hadoop.clustermanager.bean.Resource;
import org.opensource.hadoop.clustermanager.bean.RunningJob;
import org.opensource.hadoop.clustermanager.bean.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonToBeanTools {

	public static final Logger log = LoggerFactory.getLogger(JsonToBeanTools.class);
	
	public static List<Application> stringToApps(String rpString){
		ObjectMapper om = new ObjectMapper();
		om.configure(JsonParser.Feature.ALLOW_NON_NUMERIC_NUMBERS, true);
		JsonNode document;
		List<Application> appList = new ArrayList<Application>();
		
		try {
			rpString = rpString.replace(":NaN", ":0").replace(": NaN", ":0").replace(" : NaN", ":0");
			//System.out.println(rpString);
			document = om.readTree(rpString);
			JsonNode appsNode = document.findPath("app");
			if(appsNode.isMissingNode())
				return null;
			
			if(appsNode.isArray()){
				Iterator<JsonNode> its = appsNode.elements();
				while (its.hasNext()) {
					JsonNode appNode = its.next();
					Application app = new Application();
					app.setId(appNode.findPath("id").asText().replaceAll("\"", ""));
					app.setUser(appNode.findPath("user").asText().replaceAll("\"", ""));
					app.setName(appNode.findPath("name").asText().replaceAll("\"", ""));
					app.setQueue(appNode.findPath("queue").asText().replaceAll("\"", ""));
					app.setState(appNode.findPath("state").asText().replaceAll("\"", ""));
					app.setFinalStatus(appNode.findPath("finalStatus").asText().replaceAll("\"", ""));
					app.setProgress(appNode.findPath("progress").asLong());
					app.setTrackingUI(appNode.findPath("trackingUI").asText().replaceAll("\"", ""));
					app.setTrackingUrl(appNode.findPath("trackingUrl").asText().replaceAll("\"", ""));
					app.setApplicationType(appNode.findPath("applicationType").asText().replaceAll("\"", ""));
					app.setStartedTime(appNode.findPath("startedTime").asLong());
					app.setFinishedTime(appNode.findPath("finishedTime").asLong());
					app.setElapsedTime(appNode.findPath("elapsedTime").asLong());
					app.setAmContainerLogs(appNode.findPath("amContainerLogs").asText().replaceAll("\"", ""));
					app.setAllocatedMB(appNode.findPath("allocatedMB").asLong());
					app.setAllocatedVCores(appNode.findPath("allocatedVCores").asInt());
					
					app.setRunningContainers(appNode.findPath("runningContainers").asInt());
					app.setMemorySeconds(appNode.findPath("memorySeconds").asInt());
					app.setVcoreSeconds(appNode.findPath("vcoreSeconds").asInt());
					app.setPreemptedResourceMB(appNode.findPath("preemptedResourceMB").asInt());
					app.setPreemptedResourceVCores(appNode.findPath("preemptedResourceVCores").asInt());
					
					app.setNumNonAMContainerPreempted(appNode.findPath("numNonAMContainerPreempted").asInt());
					app.setNumAMContainerPreempted(appNode.findPath("numAMContainerPreempted").asInt());
					
					appList.add(app);
					
				}
			}else if(!appsNode.isNull()) {
				JsonNode appNode = appsNode;
				Application app = new Application();
				app.setId(appNode.findPath("id").asText().replaceAll("\"", ""));
				app.setUser(appNode.findPath("user").asText().replaceAll("\"", ""));
				app.setName(appNode.findPath("name").asText().replaceAll("\"", ""));
				app.setQueue(appNode.findPath("queue").asText().replaceAll("\"", ""));
				app.setState(appNode.findPath("state").asText().replaceAll("\"", ""));
				app.setFinalStatus(appNode.findPath("finalStatus").asText().replaceAll("\"", ""));
				app.setProgress(appNode.findPath("progress").asLong());
				app.setTrackingUI(appNode.findPath("trackingUI").asText().replaceAll("\"", ""));
				app.setTrackingUrl(appNode.findPath("trackingUrl").asText().replaceAll("\"", ""));
				app.setApplicationType(appNode.findPath("applicationType").asText().replaceAll("\"", ""));
				app.setStartedTime(appNode.findPath("startedTime").asLong());
				app.setFinishedTime(appNode.findPath("finishedTime").asLong());
				app.setElapsedTime(appNode.findPath("elapsedTime").asLong());
				app.setAmContainerLogs(appNode.findPath("amContainerLogs").asText().replaceAll("\"", ""));
				app.setAllocatedMB(appNode.findPath("allocatedMB").asLong());
				app.setAllocatedVCores(appNode.findPath("allocatedVCores").asInt());
				
				app.setRunningContainers(appNode.findPath("runningContainers").asInt());
				app.setMemorySeconds(appNode.findPath("memorySeconds").asInt());
				app.setVcoreSeconds(appNode.findPath("vcoreSeconds").asInt());
				app.setPreemptedResourceMB(appNode.findPath("preemptedResourceMB").asInt());
				app.setPreemptedResourceVCores(appNode.findPath("preemptedResourceVCores").asInt());
				
				app.setNumNonAMContainerPreempted(appNode.findPath("numNonAMContainerPreempted").asInt());
				app.setNumAMContainerPreempted(appNode.findPath("numAMContainerPreempted").asInt());
				
				appList.add(app);
			}
		} catch (Exception e) {
			appList = null;
			e.printStackTrace();
		} 
		return appList;
	}
	
	public static Job stringToFinishJob(String rpString){
		ObjectMapper om = new ObjectMapper();
		om.configure(JsonParser.Feature.ALLOW_NON_NUMERIC_NUMBERS, true);
		JsonNode document;
		Job job = null;
		try {
			rpString = rpString.replace(":NaN", ":0").replace(": NaN", ":0").replace(" : NaN", ":0");
			job = new Job();
			document = om.readTree(rpString);
			FinishJob fjob = new FinishJob();
			JsonNode node = document.findPath("submitTime");
			fjob.setSubmitTime(node.asLong());
			node = document.findPath("startTime");
			fjob.setStartTime(node.asLong());
			node = document.findPath("finishTime");
			fjob.setFinishTime(node.asLong());
			node = document.findPath("id");
			fjob.setId(node.asText().replaceAll("\"", ""));
			node = document.findPath("name");
			fjob.setName(node.asText().replaceAll("\"", ""));
			node = document.findPath("queue");
			fjob.setQueue(node.asText().replaceAll("\"", ""));
			node = document.findPath("user");
			fjob.setUser(node.asText().replaceAll("\"", ""));
			node = document.findPath("state");
			fjob.setState(node.asText().replaceAll("\"", ""));
			node = document.findPath("mapsTotal");
			fjob.setMapsTotal(node.asInt());
			node = document.findPath("mapsCompleted");
			fjob.setMapsCompleted(node.asInt());
			node = document.findPath("reducesTotal");
			fjob.setReducesTotal(node.asInt());
			node = document.findPath("reducesCompleted");
			fjob.setReducesCompleted(node.asInt());
			node = document.findPath("uberized");
			fjob.setUberized(node.asText().replaceAll("\"", "").replaceAll("\"", ""));
			node = document.findPath("diagnostics");
			fjob.setDiagnostics(node.asText().replaceAll("\"", ""));
			node = document.findPath("avgMapTime");
			fjob.setAvgMapTime(node.asLong());
			node = document.findPath("avgReduceTime");
			fjob.setAvgReduceTime(node.asLong());
			node = document.findPath("avgShuffleTime");
			fjob.setAvgShuffleTime(node.asLong());
			
			node = document.findPath("avgMergeTime");
			fjob.setAvgMergeTime(node.asLong());
			node = document.findPath("failedReduceAttempts");
			fjob.setFailedMapAttempts(node.asInt());
			node = document.findPath("killedReduceAttempts");
			fjob.setKilledReduceAttempts(node.asInt());
			node = document.findPath("successfulReduceAttempts");
			fjob.setSuccessfulReduceAttempts(node.asInt());
			node = document.findPath("failedMapAttempts");
			fjob.setFailedMapAttempts(node.asInt());
			node = document.findPath("killedMapAttempts");
			fjob.setKilledMapAttempts(node.asInt());
			node = document.findPath("successfulMapAttempts");
			fjob.setSuccessfulMapAttempts(node.asInt());
			
			if((fjob.getId() == null || "".equals(fjob.getId())) 
					&& (fjob.getName() == null || "".equals(fjob.getName()))
					&& (fjob.getUser() == null || "".equals(fjob.getUser()))
					)
				return null;
			
			job.setJobId(fjob.getId());
			job.setState("FINISHED");
			job.setFinishJob(fjob);
			
			
			
			
		} catch (Exception e) {
			job = null;
			e.printStackTrace();
		} 
		return job;
	}
	
	
	public static List<Task> stringToTasks(String rpString){
		ObjectMapper om = new ObjectMapper();
		om.configure(JsonParser.Feature.ALLOW_NON_NUMERIC_NUMBERS, true);
		JsonNode document;
		List<Task> tasks = new ArrayList<Task>();
		
		try {
			rpString = rpString.replace(":NaN", ":0").replace(": NaN", ":0").replace(" : NaN", ":0");
			document = om.readTree(rpString);
			JsonNode node = document.findPath("task");
			if(node.isMissingNode())
				return null;
			if(node.isArray()){
				Iterator<JsonNode> its = node.elements();
				while (its.hasNext()) {
					JsonNode taskNode = its.next();
					Task task = new Task();
					task.setStartTime(taskNode.findPath("startTime").asLong());
					task.setFinishTime(taskNode.findPath("finishTime").asLong());
					task.setElapsedTime(taskNode.findPath("elapsedTime").asLong());
					task.setProgress(taskNode.findPath("progress").asDouble());
					task.setId(taskNode.findPath("id").asText());
					task.setState(taskNode.findPath("state").asText());
					task.setType(taskNode.findPath("type").asText());
					task.setSuccessfulAttempt(taskNode.findPath("successfulAttempt").asText());
					tasks.add(task);
				}
			}
			
			
		} catch (Exception e) {
			tasks = null;
			e.printStackTrace();
		} 
		
		return tasks;
	}

	public static Job stringToRunningJob(String rpString) {
		
		ObjectMapper om = new ObjectMapper();
		om.configure(JsonParser.Feature.ALLOW_NON_NUMERIC_NUMBERS, true);
		JsonNode document;
		Job job = null;
		try {
			rpString = rpString.replace(":NaN", ":0").replace(": NaN", ":0").replace(" : NaN", ":0");
			job = new Job();
			document = om.readTree(rpString);
			RunningJob fjob = new RunningJob();
			JsonNode node = document.findPath("startTime");
			fjob.setStartTime(node.asLong());
			node = document.findPath("finishTime");
			fjob.setFinishTime(node.asLong());
			node = document.findPath("elapsedTime");
			fjob.setElapsedTime(node.asLong());
			
			node = document.findPath("id");
			fjob.setId(node.asText().replaceAll("\"", ""));
			node = document.findPath("name");
			fjob.setName(node.asText().replaceAll("\"", ""));
			node = document.findPath("user");
			fjob.setUser(node.asText().replaceAll("\"", ""));
			node = document.findPath("state");
			fjob.setState(node.asText().replaceAll("\"", ""));
			node = document.findPath("mapsTotal");
			fjob.setMapsTotal(node.asInt());
			node = document.findPath("mapsCompleted");
			fjob.setMapsCompleted(node.asInt());
			node = document.findPath("reducesTotal");
			fjob.setReducesTotal(node.asInt());
			node = document.findPath("reducesCompleted");
			fjob.setReducesCompleted(node.asInt());
			
			node = document.findPath("mapProgress");
			fjob.setMapProgress(node.asDouble());
			node = document.findPath("reduceProgress");
			fjob.setReduceProgress(node.asDouble());
			
			node = document.findPath("mapsPending");
			fjob.setMapsPending(node.asInt());
			node = document.findPath("mapsRunning");
			fjob.setMapsRunning(node.asInt());
			node = document.findPath("reducesPending");
			fjob.setReduceProgress(node.asInt());
			node = document.findPath("reducesRunning");
			fjob.setReducesRunning(node.asInt());
			
			
			node = document.findPath("uberized");
			fjob.setUberized(node.asText().replaceAll("\"", "").replaceAll("\"", ""));
			node = document.findPath("diagnostics");
			fjob.setDiagnostics(node.asText().replaceAll("\"", ""));
			
			
			
			node = document.findPath("newReduceAttempts");
			fjob.setNewReduceAttempts(node.asInt());
			node = document.findPath("runningReduceAttempts");
			fjob.setRunningReduceAttempts(node.asInt());
			
			
			node = document.findPath("failedReduceAttempts");
			fjob.setFailedMapAttempts(node.asInt());
			node = document.findPath("killedReduceAttempts");
			fjob.setKilledReduceAttempts(node.asInt());
			node = document.findPath("successfulReduceAttempts");
			fjob.setSuccessfulReduceAttempts(node.asInt());
			
			node = document.findPath("newMapAttempts");
			fjob.setNewMapAttempts(node.asInt());
			node = document.findPath("runningMapAttempts");
			fjob.setRunningMapAttempts(node.asInt());
			
			node = document.findPath("failedMapAttempts");
			fjob.setFailedMapAttempts(node.asInt());
			node = document.findPath("killedMapAttempts");
			fjob.setKilledMapAttempts(node.asInt());
			node = document.findPath("successfulMapAttempts");
			fjob.setSuccessfulMapAttempts(node.asInt());
			
			if((fjob.getId() == null || "".equals(fjob.getId())) 
					&& (fjob.getName() == null || "".equals(fjob.getName()))
					&& (fjob.getUser() == null || "".equals(fjob.getUser()))
					)
				return null;
			
			job.setJobId(fjob.getId());
			job.setState("RUNNING");
			job.setRunningJob(fjob);
			
			
			
			
		} catch (Exception e) {
			job = null;
			e.printStackTrace();
		} 
		return job;
	}
	
	public static Object stringToScheduler(String rpString){
		ObjectMapper om = new ObjectMapper();
		om.configure(JsonParser.Feature.ALLOW_NON_NUMERIC_NUMBERS, true);
		JsonNode document;
		Object scheduler = null;
		try {
			rpString = rpString.replace(":NaN", ":0").replace(": NaN", ":0").replace(" : NaN", ":0");
			document = om.readTree(rpString);
			JsonNode node = document.findPath("schedulerInfo");
			if(node.isMissingNode())
				return null;
			String schedulerType = node.findValue("type").asText();
			if(schedulerType != null && schedulerType.equals(SchedulerType.FAIRSCHEDULER.getScheduler())){
				FairScheduler fairScheduler = new FairScheduler();
				fairScheduler.setType(SchedulerType.FAIRSCHEDULER.getScheduler());
				FairRootQueue rootQueue = new FairRootQueue();
				rootQueue.setMaxApps(node.findValue("maxApps").asInt());
				Resource minResources = new Resource();
				minResources.setMemory(node.findPath("minResources").findValue("memory").asInt());
				minResources.setvCores(node.findPath("minResources").findValue("vCores").asInt());
				rootQueue.setMinResources(minResources);
				
				Resource maxResources = new Resource();
				maxResources.setMemory(node.findPath("maxResources").findValue("memory").asInt());
				maxResources.setvCores(node.findPath("maxResources").findValue("vCores").asInt());
				rootQueue.setMaxResources(maxResources);
				
				Resource usedResources = new Resource();
				usedResources.setMemory(node.findPath("usedResources").findValue("memory").asInt());
				usedResources.setvCores(node.findPath("usedResources").findValue("vCores").asInt());
				rootQueue.setUsedResources(usedResources);
				
				Resource fairResources = new Resource();
				fairResources.setMemory(node.findPath("fairResources").findValue("memory").asInt());
				fairResources.setvCores(node.findPath("fairResources").findValue("vCores").asInt());
				rootQueue.setFairResources(fairResources);
				
				Resource clusterResources = new Resource();
				clusterResources.setMemory(node.findPath("clusterResources").findValue("memory").asInt());
				clusterResources.setvCores(node.findPath("clusterResources").findValue("vCores").asInt());
				rootQueue.setClusterResources(clusterResources);
				
				rootQueue.setQueueName(node.findValue("queueName").asText());
				rootQueue.setSchedulingPolicy(node.findValue("schedulingPolicy").asText());
				
				fairScheduler.setFairRootQueue(rootQueue);
				
				scheduler = fairScheduler;
				
			}else if(schedulerType != null && schedulerType.equals(SchedulerType.CAPACITYSCHEDULER.getScheduler())){
				CapacityScheduler capacityScheduler = new CapacityScheduler();
				capacityScheduler.setCapacity(node.findValue("capacity").asDouble());
				capacityScheduler.setType(schedulerType);
				capacityScheduler.setMaxCapacity(node.findValue("maxCapacity").asDouble());
				capacityScheduler.setQueueName(node.findValue("queueName").asText());
				capacityScheduler.setUsedCapacity(node.findValue("usedCapacity").asDouble());
				
				scheduler = capacityScheduler;
			}else if(schedulerType != null && schedulerType.equals(SchedulerType.FIFO.getScheduler())){
				FifoScheduler fifoScheduler = new FifoScheduler();
				fifoScheduler.setType(schedulerType);
				fifoScheduler.setAvailNodeCapacity(node.findValue("availNodeCapacity").asInt());
				fifoScheduler.setCapacity(node.findValue("capacity").asInt());
				fifoScheduler.setMaxQueueMemoryCapacity(node.findValue("maxQueueMemoryCapacity").asInt());
				fifoScheduler.setMinQueueMemoryCapacity(node.findValue("minQueueMemoryCapacity").asInt());
				fifoScheduler.setNumContainers(node.findValue("numContainers").asInt());
				
				fifoScheduler.setNumNodes(node.findValue("numNodes").asInt());
				fifoScheduler.setQstate(node.findValue("qstate").asText());
				fifoScheduler.setTotalNodeCapacity(node.findValue("totalNodeCapacity").asInt());
				fifoScheduler.setUsedNodeCapacity(node.findValue("usedNodeCapacity").asInt());
				
				scheduler = fifoScheduler;
			}
			
			
			
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
			
			
		
		return scheduler;
	}
}
