package com.baifendian.clustermanager.db;

import java.util.List;
import java.util.Map;

import com.baifendian.clustermanager.bean.ResourceQuota;

public interface IResourceQuota {

	/**
	 * 通过用户名查找用户资源配额
	 * @param username
	 * @return
	 */
	public ResourceQuota getQuotaByUserName(String username);
	
	/**
	 * 修改用户资源配额
	 * @param rq
	 */
	public void updateResourceQuota(ResourceQuota rq);
	
	
	/**
	 * 根据字段查询用户资源配额
	 * @param params
	 * @return
	 */
	public List<ResourceQuota> queryRQByParams(Map<String, ?> params);
	
	
	/**
	 * 新增用户资源配额
	 * @param rq
	 */
	public void saveResourceQuota(ResourceQuota rq);
	
	
	/**
	 * 删除用户资源配额记录
	 * @param id
	 */
	public void deleteResourceQuota(int id);
	
}
