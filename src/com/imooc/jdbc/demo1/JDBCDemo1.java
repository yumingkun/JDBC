package com.imooc.jdbc.demo1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



public class JDBCDemo1 {
	

	/**
	 * JDBC资源的释放
	 */
	public void demo2(){
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			// 1.加载驱动
//			DriverManager.registerDriver(new Driver());// 会导致驱动注册两次。
			Class.forName("com.mysql.jdbc.Driver"); 
			// 2.获得连接
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbcdemo", "root", "root");
			// 3.创建执行SQL语句的对象，并且执行SQL
			// 3.1创建执行sql的对象
			String sql = "select * from user";
			stmt = conn.createStatement();
			// 3.2执行sql
			rs = stmt.executeQuery(sql);
			while(rs.next()){
				int uid = rs.getInt("uid");
				String username = rs.getString("username");
				String password = rs.getString("password");
				String name = rs.getString("name");
				
				System.out.println(uid+"   "+username+"   "+password+"   "+name);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			// 4.释放资源
		    if (rs != null) {
		        try {
		            rs.close();
		        } catch (SQLException sqlEx) { // ignore 
		        	
		        }

		        rs = null;
		    }
		    
		    if(stmt != null){
		    	try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		    	stmt = null;
		    }
		    
		    if(conn != null){
		    	try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		    	conn = null;// 垃圾回收机制更早回收对象。
		    }
		}
	}


	/**
	 * JDBC的入门程序
	 */
	public void demo1(){
		
		try {
			// 1.加载驱动
//			DriverManager.registerDriver(new Driver());// 会导致驱动注册两次。
			Class.forName("com.mysql.jdbc.Driver"); 
			// 2.获得连接
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbctest", "root", "abc");
			// 3.创建执行SQL语句的对象，并且执行SQL
			// 3.1创建执行sql的对象
			String sql = "select * from user";
			Statement stmt = conn.createStatement();
			// 3.2执行sql
			ResultSet resultSet = stmt.executeQuery(sql);
			while(resultSet.next()){
				int uid = resultSet.getInt("uid");
				String username = resultSet.getString("username");
				String password = resultSet.getString("password");
				String name = resultSet.getString("name");
				
				System.out.println(uid+"   "+username+"   "+password+"   "+name);
			}
			// 4.释放资源
			resultSet.close();
			stmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
