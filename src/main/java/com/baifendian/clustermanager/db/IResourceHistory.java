package com.baifendian.clustermanager.db;

import java.util.List;
import java.util.Map;

import com.baifendian.clustermanager.bean.ResourceHistory;

public interface IResourceHistory {

	
	
	/**
	 * 根据用户名获取资源使用记录
	 * @param username
	 * @return
	 */
	public List<ResourceHistory> getRHByUserName(String username);
	
	
	/**
	 * 根据字段查询资源使用历史
	 * @param params
	 * @return
	 */
	public List<ResourceHistory> queryRHByParams(Map<String, ?> params);
	
	/**
	 * 添加用户资源使用记录
	 * @param rh
	 */
	public void saveResourceHistory(ResourceHistory rh);
	
}
