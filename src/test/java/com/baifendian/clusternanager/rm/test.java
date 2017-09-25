package com.baifendian.clusternanager.rm;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.baifendian.clustermanager.fromrestapi.util.JsonToBeanTools;
import com.mysql.fabric.xmlrpc.base.Data;

public class test {

	public static void main(String[] args) throws Exception {
		System.out.println(new Data());
		File af = new File("E:/a.json");
		InputStream is = new FileInputStream("E:/a.json");  
        BufferedReader reader = new BufferedReader(  
                new InputStreamReader(is));  
        String ss = reader.readLine();
        System.out.println(ss);
        
		JsonToBeanTools.stringToApps(ss);
		//ss();
	}

	private static void ss() {
		String s="{\"conf\":{\"path\":\"hdfs\",\"property\":[{\"name\":\"dfs.https.server.keystore.resource\",\"value\":\"ssl-server.xml\",\"source\":[\"hdfs-default.xml\",\"hdfs:job.xml\"]},{\"name\":\"mapreduce.reduce.memory.mb\",\"value\":\"3072\",\"source\":[\"mapred-site.xml\",\"hdfs:job.xml\"]},{\"name\":\"mapreduce.client.output.filter\",\"value\":\"FAILED\",\"source\":[\"mapred-default.xml\",\"hdfs:job.xml\"]}]}}";
		
		JSONObject  jasonObject = JSONObject.fromObject(s);
		JSONArray jsonArray =jasonObject.getJSONObject("conf").getJSONArray("property");
		int length = jsonArray.size();  
		String name = "";  
		String value="";
		for(int i = 0; i < length; i++) {//遍历JSONArray  
		      
		    JSONObject oj = jsonArray.getJSONObject(i);  
		    name =oj.getString("name"); 
		    if(name !=null && name.trim().equals("mapreduce.reduce.memory.mb")){
		    	value=oj.getString("value");
		    	System.out.println(name+":"+value);
		    }
		}
		
	}

}
