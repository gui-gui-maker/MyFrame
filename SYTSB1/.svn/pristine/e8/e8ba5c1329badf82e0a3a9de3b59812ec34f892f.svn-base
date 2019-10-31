package com.khnt.rtbox.tools;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

import com.khnt.utils.StringUtil;

/**
 * @author ZQ
 * @version 2016年3月9日 上午10:12:30 JDBC连接数据库工具参数类
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrlDbOptions {
	private String driverName = "oracle.jdbc.driver.OracleDriver";
	@JsonProperty(value = "ip")
	private String IP = "127.0.0.1";
	private int port = 1521;
	private String dbName = "khdb";
	private String url;
	private String username;
	private String password;
	private String sql;
	private String id;
	private String oldIds;

	public OrlDbOptions() {
	}

	public OrlDbOptions(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public OrlDbOptions(String url, String username, String password) {
		this.url = url;
		this.username = username;
		this.password = username;
	}

	public OrlDbOptions(String driverName, String url, String username, String password) {
		this.driverName = driverName;
		this.url = url;
		this.username = username;
		this.password = username;
	}

	public String getDriverName() {
		return driverName;
	}

	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}

	public String getUrl() {
		if (StringUtil.isEmpty(this.url)) {
			StringBuilder sb = new StringBuilder();
			sb.append("jdbc:oracle:thin:@").append(this.IP).append(":").append(this.port).append(":").append(this.dbName);
			this.url = sb.toString();
		}
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getIP() {
		return IP;
	}

	public void setIP(String iP) {
		IP = iP;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getDbName() {
		return dbName;
	}

	public void setDbName(String dbName) {
		this.dbName = dbName;
	}

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOldIds() {
		return oldIds;
	}

	public void setOldIds(String oldIds) {
		this.oldIds = oldIds;
	}

}
