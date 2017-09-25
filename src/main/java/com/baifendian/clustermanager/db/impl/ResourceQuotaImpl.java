package com.baifendian.clustermanager.db.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.baifendian.clustermanager.bean.ResourceQuota;
import com.baifendian.clustermanager.db.BaseDB;
import com.baifendian.clustermanager.db.IResourceQuota;


/**
 * <li>功能描述：用户限额db类
 * @author node
 *
 */
public class ResourceQuotaImpl extends BaseDB implements IResourceQuota{

	/**
	 * 通过用户名查找用户资源配额
	 * @param username
	 * @return
	 */
	public ResourceQuota getQuotaByUserName(String username){
		String sql = "select * from resource_quota where username=?";
		Connection conn = null;
		try {
			Class.forName(pt.getValue("connection.driver_class"));
			conn = DriverManager.getConnection(
					pt.getValue("connection.url"),
					pt.getValue("connection.username"),
					pt.getValue("connection.password"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		PreparedStatement pstmt = null;
		ResourceQuota rQuota = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, username);
			rs = pstmt.executeQuery();
			while(rs.next()){
				rQuota = new ResourceQuota();
				rQuota.setId(rs.getLong("id"));
				rQuota.setUsername(rs.getString("username"));
				rQuota.setMemoryQuota(rs.getLong("memory_quota"));
				rQuota.setVcoreQuota(rs.getLong("vcore_quota"));
				rQuota.setMemoryUsed(rs.getLong("memory_used"));
				rQuota.setVcoreUsed(rs.getLong("vcore_used"));
				rQuota.setMemoryOver(rs.getLong("memory_over"));
				rQuota.setVcoreOver(rs.getLong("vcore_over"));
				rQuota.setCanSubmit(rs.getInt("can_submit"));
				rQuota.setMemoryTotal(rs.getLong("memory_total"));
				rQuota.setVcoreTotal(rs.getLong("vcore_total"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeResultSet(rs);
			closePreparedStatement(pstmt);
			closeConnection(conn);
		}
		
		return rQuota;
		
	}
	
	
	
	/**
	 * 修改用户资源配额
	 * @param rq
	 */
	public void updateResourceQuota(ResourceQuota rq){
		if(rq == null || rq.getId() == 0)
			return ;
		String sql = "update resource_quota set username=?,memory_quota=?,"
				+ "vcore_quota=?,memory_used=?,vcore_used=?,memory_over=?,"
				+ "vcore_over=?,can_submit=?,memory_total=?,vcore_total=? "
				+ "where id=?";
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, rq.getUsername());
			pstmt.setLong(2, rq.getMemoryQuota());
			pstmt.setLong(3, rq.getVcoreQuota());
			pstmt.setLong(4, rq.getMemoryUsed());
			pstmt.setLong(5, rq.getVcoreUsed());
			pstmt.setLong(6, rq.getMemoryOver());
			pstmt.setLong(7, rq.getVcoreOver());
			pstmt.setInt(8, rq.getCanSubmit());
			pstmt.setLong(9, rq.getMemoryTotal());
			pstmt.setLong(10, rq.getVcoreTotal());
			pstmt.setLong(11, rq.getId());
			pstmt.executeUpdate();
			
			
		}catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeResultSet(rs);
			closePreparedStatement(pstmt);
			closeConnection(conn);
		}
	}
	
	
	
	
	/**
	 * 根据字段查询用户资源配额
	 * @param params
	 * @return
	 */
	public List<ResourceQuota> queryRQByParams(Map<String, ?> params){
		
		StringBuilder sb = new StringBuilder();
		sb.append("select * from resource_quota ");
		if(params != null && params.size() > 0){
			sb.append(" where 1=1 ");
			for(Map.Entry<String, ?> entry : params.entrySet()){
				sb.append(" and ").append(entry.getKey()).append("=");
				if(entry.getValue() != null){
					if(entry.getValue() instanceof String){
						sb.append("'").append(entry.getValue()).append("' ");
					}else if(entry.getValue() instanceof Integer || entry.getValue() instanceof Long){
						sb.append(entry.getValue());
					}
				}
			}
		}
		sb.append(" order by id ");
		
		List<ResourceQuota> rqList = new ArrayList<ResourceQuota>();
		
		String sql = sb.toString();
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResourceQuota rQuota = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()){
				rQuota = new ResourceQuota();
				rQuota.setId(rs.getLong("id"));
				rQuota.setUsername(rs.getString("username"));
				rQuota.setMemoryQuota(rs.getLong("memory_quota"));
				rQuota.setVcoreQuota(rs.getLong("vcore_quota"));
				rQuota.setMemoryUsed(rs.getLong("memory_used"));
				rQuota.setVcoreUsed(rs.getLong("vcore_used"));
				rQuota.setMemoryOver(rs.getLong("memory_over"));
				rQuota.setVcoreOver(rs.getLong("vcore_over"));
				rQuota.setCanSubmit(rs.getInt("can_submit"));
				rQuota.setMemoryTotal(rs.getLong("memory_total"));
				rQuota.setVcoreTotal(rs.getLong("vcore_total"));
				rqList.add(rQuota);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeResultSet(rs);
			closePreparedStatement(pstmt);
			closeConnection(conn);
		}
		
		return rqList;
		
		
	}
	
	/**
	 * 新增用户资源配额
	 * @param rq
	 */
	public void saveResourceQuota(ResourceQuota rq){
		
		if(rq == null)
			return ;
		
		String sql = "insert into resource_quota(username,memory_quota,vcore_quota,memory_used,"
				+ "vcore_used,memory_over,vcore_over,can_submit,memory_total,vcore_total) "
				+ "values(?,?,?,?,?,?,?,?,?,?)";
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, rq.getUsername());
			pstmt.setLong(2, rq.getMemoryQuota());
			pstmt.setLong(3, rq.getVcoreQuota());
			pstmt.setLong(4, rq.getMemoryUsed());
			pstmt.setLong(5, rq.getVcoreUsed());
			pstmt.setLong(6, rq.getMemoryOver());
			pstmt.setLong(7, rq.getVcoreOver());
			pstmt.setInt(8, rq.getCanSubmit());
			pstmt.setLong(9, rq.getMemoryTotal());
			pstmt.setLong(10, rq.getVcoreTotal());
			
			pstmt.executeUpdate();
			
			
		}catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeResultSet(rs);
			closePreparedStatement(pstmt);
			closeConnection(conn);
		}
	}
	
	/**
	 * 删除用户资源配额记录
	 * @param id
	 */
	public void deleteResourceQuota(int id){
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		List<ResourceQuota> rqList = queryRQByParams(params);
		if(rqList != null && rqList.size() > 0){
			ResourceQuota rq = rqList.get(0);
			String username = rq.getUsername();
			//关联删除资源使用记录
			String sql = "delete from resource_history where username='"+username+"'";
			executeQuery(sql);
			//删除配额记录
			String sql1 = "delete from resource_quota where id="+id;
			executeQuery(sql1);
		}
		
	}
	
	public static void main(String[] args) {
		IResourceQuota resourceQuota = new ResourceQuotaImpl();
		resourceQuota.getQuotaByUserName("hadoop");
	}
	
	
	
}
