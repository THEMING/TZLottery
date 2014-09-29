package com.xsc.datasource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class JDBCLaShou {

	private String url = "jdbc:mysql://10.168.2.31:3306/thinklasho";
//	private String url = "jdbc:mysql://10.168.31.17:3306/caipiao";
	
	private String user = "lashoucaipiao";
//	private String user = "cp_test";
	
	private String password = "6+l0o{OpuMXcc;QL81J6pSX~O5EH";
//	private String password = "111111";
	
	public Connection getConnection() {
		Connection con =null;
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			con = DriverManager.getConnection(url, user, password);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return con;
	}
	
	public void closeConnection(Connection connection ) {
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<Object[]> getData(String stime, String etime) {
		String sql = "select * from caipiao_bills ";
		if (stime != null || etime != null) {
			sql += " where ";
			if (stime != null) {
				sql += " create_time >= '" + stime + "' ";
				if (etime != null) {
					sql += " and create_time <= '" + etime + "' ";
				}
			}else {
				if (etime != null) {
					sql += " create_time <= '" + etime + "' ";
				}
			}
		}
		String money;
		String u_money;
		String c_money;
		List<Object[]> list = new ArrayList<Object[]>();
		Connection connection = getConnection();
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				Object[] objects = new Object[3] ;
				money = resultSet.getString("money");
				objects[0] = money;
				u_money = resultSet.getString("u_money");
				objects[1] = u_money;
				c_money = resultSet.getString("c_money");
				objects[2] = c_money;
				list.add(objects);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return list;
	}
	public static void main(String[] args) {
		JDBCLaShou jdbcLaShou = new JDBCLaShou();
		List<Object[]> list = jdbcLaShou.getData(null, null);
		
		for (Object[] object : list) {
			System.out.println(object[0]);
		}
////		String sql = "select * from caipiao_bills";
//		String sql = "select * from business_order";
//		JDBCLaShou jdbcLaShou = new JDBCLaShou();
//		Connection con = jdbcLaShou.getConnection();
//		try {
//			Statement statement = con.createStatement();
////			ResultSet list = statement.executeQuery(sql);
//			ResultSet rs = statement.executeQuery(sql);
//			String money;
//			String u_money;
//			String c_money;
//			while(rs.next()) {
//				money = rs.getString("money");
//				u_money= rs.getString("u_money");
//				c_money= rs.getString("c_money");
//				System.out.println(money + " = " + u_money + " + " + c_money);
////				String money = rs.getString("amount");
////				System.out.println("money = " + money);
//			}
//
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		finally {
//			jdbcLaShou.closeConnection(con);
//		}
	}
}










