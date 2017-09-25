package test.com.baifendian.clustermanager.rm.server.impl.test;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.BeforeClass;
import org.junit.Test;

import com.baifendian.clustermanager.bean.Application;
import com.baifendian.clustermanager.bean.CapacityScheduler;
import com.baifendian.clustermanager.bean.FairScheduler;
import com.baifendian.clustermanager.bean.FifoScheduler;
import com.baifendian.clustermanager.bean.Job;
import com.baifendian.clustermanager.bean.Task;
import com.baifendian.clustermanager.fromrestapi.server.IMapReduceServer;
import com.baifendian.clustermanager.fromrestapi.server.IResourceManagerServer;
import com.baifendian.clustermanager.fromrestapi.server.impl.MapReduceServerImpl;
import com.baifendian.clustermanager.fromrestapi.server.impl.YarnRMServerImpl;
import com.baifendian.clustermanager.fromrestapi.util.SchedulerType;

public class MRHistoryServerTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Test
	public void testGetJobs(){
		IMapReduceServer is = new MapReduceServerImpl();
		Job job = is.getJob("job_1447740761011_10016");
		if(job != null){
			
			System.out.println(job.getJobId());
			System.out.println(job.getState());
		}
	}
	
	@Test
	public void testGetJobsTasksMap(){
		IMapReduceServer is = new MapReduceServerImpl();
		Map<String, List<Task>> ts = is.getJobsTasksMap("job_1447740761011_10016", null);
		if(ts != null && ts.size() > 0){
			Set<String> ks = ts.keySet();
			Iterator<String> its = ks.iterator();
			while(its.hasNext())
				System.out.println(its.next());
		}
	}
	
	@Test
	public void testGetApp(){
		IResourceManagerServer ir = new YarnRMServerImpl();
		IMapReduceServer is = new MapReduceServerImpl();
		Date date = new Date();
		long startedTimeBegin = date.getTime()-3600000*5;
		List<Application> appList = ir.getAppList("bfdhadoop", startedTimeBegin, null, "IDE_TASK_CFCB0A82-ADD9-6FDB-47A0-268A2187A566_20160726173010340");
		if(appList != null && appList.size() > 0){
			for(Application app : appList){
				System.out.println(app.getId());
				Job job = is.getJob(app.getId());
				if(job == null )return;
				System.out.println(job.getJobId());
				if("FINISHED".equals(job.getState())){
					System.out.println(job.getFinishJob().getName());
				}else{
					System.out.println(job.getRunningJob().getName());
				}
			}
		}
		
	}
	
	
	@Test
	public void testGetClusterResourceUsage(){
		IResourceManagerServer ir = new YarnRMServerImpl();
		System.out.println(ir.getClusterResourceUsage());
	}
	
	@Test
	public void testGetSchedulerType(){
		IResourceManagerServer ir = new YarnRMServerImpl();
		SchedulerType st = ir.getSchedulerType();
		System.out.println(st.getScheduler());
	}
	
	@Test
	public void testGetFairScheduler(){
		IResourceManagerServer ir = new YarnRMServerImpl();
		FairScheduler st = ir.getFairScheduler();
		if(st != null){
			System.out.println(st.getType());
			System.out.println(st.getFairRootQueue().getQueueName());
			System.out.println(st.getFairRootQueue().getClusterResources().getMemory());
		}
	}
	
	@Test
	public void testGetCapacityScheduler(){
		IResourceManagerServer ir = new YarnRMServerImpl();
		CapacityScheduler st = ir.getCapacityScheduler();
		if(st != null){
			System.out.println(st.getType());
			System.out.println(st.getCapacity());
		}
	}
	
	@Test
	public void testGetFifoScheduler(){
		IResourceManagerServer ir = new YarnRMServerImpl();
		FifoScheduler st = ir.getFifoScheduler();
		if(st != null){
			System.out.println(st.getType());
			System.out.println(st.getCapacity());
		}
	}
	
	

}
