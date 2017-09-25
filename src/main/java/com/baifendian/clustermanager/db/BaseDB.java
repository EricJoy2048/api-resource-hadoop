package com.baifendian.clustermanager.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.http.conn.ConnectTimeoutException;
import org.sty.sadt.core.util.ClassLoaderUtil;
import org.sty.sadt.core.util.PropertiesTools;


/**
 * <li>功能描述：基础DB类
 * @author node
 *
 */
public class BaseDB {
	
	public static PropertiesTools pt = new PropertiesTools(ClassLoaderUtil.getProperties("../conf/db.properties"));
	
	/**
	 * 获取数据库连接
	 * @return
	 */
	public Connection getConnection(){
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
		
		return conn;
		
	}
	
	/**
	 * 通用可直接执行sql
	 * @param sql
	 */
	public void executeQuery(String sql){
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt.executeUpdate();
			
		}catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeResultSet(rs);
			closePreparedStatement(pstmt);
			closeConnection(conn);
		}
	}
	
	public void closeConnection(Connection conn){
		if(conn != null){
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void closeResultSet(ResultSet rs){
		if(rs != null){
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void closePreparedStatement(PreparedStatement ps){
		if(ps != null){
			try {
				ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
	
}
