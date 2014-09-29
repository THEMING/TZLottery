package com.xsc.datasource;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSourceFactory;


public class JdbcUtilities {
	private static DataSource mydatasource;
	JdbcUtilities(){
		
	}
	static{
		try {
			Properties prop = new Properties();
			InputStream is = JdbcUtilities.class.getClassLoader().getResourceAsStream("dbcpconifg.properties");
			prop.load(is);
			mydatasource =  BasicDataSourceFactory.createDataSource(prop);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ExceptionInInitializerError("数据源初始化错误");
		}
	}
	/**
	 * 获取数据库连接
	 * @return
	 * @throws SQLException
	 */
	public static Connection getConnection() throws SQLException{
		return mydatasource.getConnection();
	}
	/**
	 * 获取数据源
	 * @return
	 */
	public static DataSource getMydatasource() {
		return mydatasource;
	}
	/**
	 * 返回结果集
	 * @param conn
	 * @param sql
	 * @param objs
	 * @return
	 * @throws SQLException
	 */
	public static ResultSet getResultSet(Connection conn,String sql) throws SQLException{
		PreparedStatement ps=conn.prepareStatement(sql);
		return ps.executeQuery();
	}
	/**
	 * 返回更新的条数
	 * @param conn
	 * @param sql
	 *
	 * @return
	 * @throws SQLException
	 */
	public static int saveOrUpdateOrDelete(Connection conn,String sql) throws SQLException{
		PreparedStatement ps=conn.prepareStatement(sql);
		int num=ps.executeUpdate(sql);
		return num;
	}
	/**
	 * 关闭连接
	 * @param conn
	 * @param rs
	 */
	public static void closeSource(Connection conn,ResultSet rs){
		try {
			if(conn!=null && !conn.isClosed()){
				conn.close();
			}
			if(rs!=null && !rs.isClosed()){
				rs.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/*public static void main(String[] args) {
	        try {
				Connection conn= getConnection();
				System.out.println("===============conn:"+conn);
				String sql="select * from sm_queue where id=? and status=? and channel=? and retry=?";
				Object[] objs={1,1,1,1};
				ResultSet rs=getResultSet(conn, sql, objs);
				System.out.println("===============rs:"+rs);
				while(rs.next()){
					int id=rs.getInt("id");
					String content=rs.getString("content");
					String modile=rs.getString("modile");
					java.util.Date addTime=rs.getDate("add_time"); 
					int status=rs.getInt("status");
					int channel=rs.getInt("channel");
					int retry=rs.getInt("retry");
					java.util.Date sendTime=rs.getDate("send_time");
					System.out.println("===============id:"+id);
					System.out.println("===============content:"+content);
					System.out.println("===============modile:"+modile);
					System.out.println("===============addTime:"+addTime);
					System.out.println("===============status:"+status);
					System.out.println("===============channel:"+channel);
					System.out.println("===============retry:"+retry);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}*/
}