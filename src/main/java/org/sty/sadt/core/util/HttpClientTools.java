package org.sty.sadt.core.util;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class HttpClientTools {
	
	
	public static final Logger log = LoggerFactory.getLogger(HttpClientTools.class);

	/**
	 * 获取http请求内容
	 * @param url
	 * @return
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 */
	public static String httpClientGet(String url) throws ClientProtocolException, IOException {
		log.debug(url);
		HttpClient httpclient = new DefaultHttpClient();
		HttpGet httpget = new HttpGet(url);
		String rpString = "";
		
		HttpResponse response = httpclient.execute(httpget);
		HttpEntity entity = response.getEntity();
		if (entity != null) {
			rpString = EntityUtils.toString(entity);
			httpget.abort();
			// httpclient.getConnectionManager().shutdown();
		}
		
		
		return rpString;
	}
}
