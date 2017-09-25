package com.baifendian.clustermanager.db.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.baifendian.clustermanager.bean.ResourceHistory;
import com.baifendian.clustermanager.db.BaseDB;
import com.baifendian.clustermanager.db.IResourceHistory;

/**
 * 用户资源使用历史db类
 * @author BFD_491
 *
 */
public class ResourceHistoryImpl extends BaseDB implements IResourceHistory {

	/**
	 * 根据用户名获取资源使用记录
	 * @param username
	 * @return
	 */
	public List<ResourceHistory> getRHByUserName(String username){
		String sql = "select * from resource_history where username=?";
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<ResourceHistory> rHistoryList = new ArrayList<ResourceHistory>();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, username);
			rs = pstmt.executeQuery();
			while(rs.next()){
				ResourceHistory rHistory = new ResourceHistory();
				rHistory.setId(rs.getLong("id"));
				rHistory.setUsername(rs.getString("username"));
				rHistory.setAppId(rs.getString("appId"));
				rHistory.setMb(rs.getLong("mb"));
				rHistory.setVcore(rs.getLong("vcore"));
				rHistory.setState(rs.getString("state"));
				rHistoryList.add(rHistory);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeResultSet(rs);
			closePreparedStatement(pstmt);
			closeConnection(conn);
		}
		
		return rHistoryList;
	}

	/**
	 * 根据字段查询资源使用历史
	 * @param params
	 * @return
	 */
	public List<ResourceHistory> queryRHByParams(Map<String, ?> params){
		
		StringBuilder sb = new StringBuilder();
		sb.append("select * from resource_history ");
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
		
		List<ResourceHistory> rhList = new ArrayList<ResourceHistory>();
		
		String sql = sb.toString();
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResourceHistory rh = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()){
				rh = new ResourceHistory();
				rh.setId(rs.getLong("id"));
				rh.setUsername(rs.getString("username"));
				rh.setAppId(rs.getString("appid"));
				rh.setMb(rs.getLong("mb"));
				rh.setVcore(rs.getLong("vcore"));
				rh.setState(rs.getString("state"));
				rhList.add(rh);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeResultSet(rs);
			closePreparedStatement(pstmt);
			closeConnection(conn);
		}
		
		return rhList;
		
		
	}

	public void saveResourceHistory(ResourceHistory rh) {
		if(rh == null)
			return ;
		
		String sql = "insert into resource_history(appid,mb,vcore,username,state)"
				+ "values(?,?,?,?,?)";
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, rh.getAppId());
			pstmt.setLong(2, rh.getMb());
			pstmt.setLong(3, rh.getVcore());
			pstmt.setString(4, rh.getUsername());
			pstmt.setString(5, rh.getState());
			
			pstmt.executeUpdate();
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeResultSet(rs);
			closePreparedStatement(pstmt);
			closeConnection(conn);
		}
		
	}
	
	

}
