package com.khnt.rtbox.tools;

/**
 * @author ZQ
 * @version 2016年3月9日 上午10:12:30
 * JDBC连接数据库工具类
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.khnt.base.Factory;
import com.khnt.security.util.DESUtil;

public class DbUtil {
	private static String DRIVERNAME = "oracle.jdbc.driver.OracleDriver";
	private static String URL = Factory.getSysPara().getProperty("database.url");
	private static String USERNAME = DESUtil.getDecryptString(Factory.getSysPara().getProperty("database.user", "rtbox"));
	private static String PASSWORD = DESUtil.getDecryptString(Factory.getSysPara().getProperty("database.password", "oracle"));

	private static Connection conn = null;

	public static Connection getConnection() {
		try {
			if (null == conn || conn.isClosed()) {
				Class.forName(DRIVERNAME);
				conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}

	public static Connection getConnection(OrlDbOptions options) {
		try {
			if (null == conn || conn.isClosed()) {
				Class.forName(options.getDriverName());
				conn = DriverManager.getConnection(options.getUrl(), options.getUsername(), options.getPassword());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}

	public static void close(Connection conn) {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static void close(PreparedStatement pstmt) {
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static void close(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		System.out.println(DbUtil.getConnection());
	}
}
