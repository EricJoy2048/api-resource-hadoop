package com.baifendian.clustermanager.fromrestapi.util;

/**
 * <li>功能描述：资源地址url
 * @author BFD_491
 *
 */
public enum ResourceType {
	
	/**yarn 集群信息地址 **/
	YARN_CLUSTER_INFO_URL,
	
	/**yarn 集群信息地址 **/
	YARN_CLUSTER_SCHEDULER_URL,

	/**YARN application 列表请求地址 **/
	YARN_APP_LIST_URL,
	
	/**yarn 历史作业信息请求地址 **/
	YARN_HIS_JOB_URL,
	
	/**yarn 历史作业中的tasks列表请求地址 **/
	YARN_HIS_TASKS_URL,
	
	/**yarn 历史作业中的单一task信息请求地址 **/
	YARN_HIS_TASK_URL,
	
	/**yarn 历史作业中的job的conf信息请求地址 **/
	YARN_HIS_JOB_CONF_URL,
	
	
	/**yarn 中正在运行的job信息请求地址**/
	YARN_JOB_URL,
	
	/**yarn 中正在运行的tasks列表请求地址**/
	YARN_TASKS_URL,
	
	/**yarn 中正在运行的task的信息请求地址**/
	YARN_TASK_URL,
	
	/**yarn 中正在运行的job的conf信息请求地址**/
	YARN_JOB_CONF_URL
	
	
	
	
}
