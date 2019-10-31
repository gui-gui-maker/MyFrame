/**
 * 
 */
package com.khnt.rtbox.tools;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.docx4j.docProps.variantTypes.Array;

import com.khnt.base.Factory;
import com.khnt.rtbox.config.bean.BaseConfig;
import com.khnt.utils.StringUtil;

/** 
 * @author 作者 Jack Rio
 * @JDK 1.6
 * @version 创建时间：2014年12月18日 上午10:57:26 
 * 类说明 
 */
/**
 * @author Jack
 *
 */
public class BaseUtil {


	public static Object dealContent(String content)
			throws UnsupportedEncodingException {
		byte[] b = null;
		if (Factory.getSysPara().getProperty("DEVELOP").equals("true")) {
			b = StringUtil.transformNull(content).getBytes("GB2312");
			return b;
		} else {
			return content;
		}
	}

	/**
	 * 获取所有基本信息配置数据
	 * author pingZhou
	 * @return
	 */
	public static List<BaseConfig> getConfigList() {
		Connection conn = Factory.getDB().getConnetion();
    	String sql="select t.id,t.device_type,t.report_type,t.name,t.code,t.remark,t.type,t.bind_type,t.data_sql,t.data_code,t.data_string,t.default_value from BASE_NAME_CONFIG t";
    	Statement queryStatement = null ;
		ResultSet executSet = null ;
		List<BaseConfig> list = new ArrayList<BaseConfig>(); 
		try{
			queryStatement=conn.createStatement();
			executSet=queryStatement.executeQuery(sql);
			while(executSet.next()){
				BaseConfig baseConfig = new BaseConfig();
				String id = executSet.getString("id"); 
				String name = executSet.getString("name"); 
				String type = executSet.getString("type"); 
				String code = executSet.getString("code");
				String remark = executSet.getString("remark"); 
				baseConfig.setId(id);
				baseConfig.setCode(code);
				baseConfig.setName(name);
				baseConfig.setRemark(remark);
				baseConfig.setType(type);
				baseConfig.setDeviceType(executSet.getString("device_type"));
				baseConfig.setReportType(executSet.getString("report_type"));
				baseConfig.setBindType(executSet.getString("bind_type"));
				baseConfig.setDataSql(executSet.getString("data_sql"));
				baseConfig.setDataCode(executSet.getString("data_code"));
				baseConfig.setDataString(executSet.getString("data_string"));
				baseConfig.setDefaultValue(executSet.getString("default_value"));
				
				list.add(baseConfig);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
    	return list;
	}
	
	
	public static List<BaseConfig> getConfigSqlList() {
		Connection conn = Factory.getDB().getConnetion();
    	String sql="select t.id,t.device_type,t.report_type,t.name,t.code,t.remark,t.type,t.bind_type,t.data_sql,t.data_code,t.data_string from BASE_NAME_CONFIG t"
    			+ " where t.bind_type='sql' and t.data_sql is not null";
    	Statement queryStatement = null ;
		ResultSet executSet = null ;
		List<BaseConfig> list = new ArrayList<BaseConfig>(); 
		try{
			queryStatement=conn.createStatement();
			executSet=queryStatement.executeQuery(sql);
			while(executSet.next()){
				BaseConfig baseConfig = new BaseConfig();
				String id = executSet.getString("id"); 
				String name = executSet.getString("name"); 
				String type = executSet.getString("type"); 
				String code = executSet.getString("code");
				String remark = executSet.getString("remark"); 
				baseConfig.setId(id);
				baseConfig.setCode(code);
				baseConfig.setName(name);
				baseConfig.setRemark(remark);
				baseConfig.setType(type);
				baseConfig.setDeviceType(executSet.getString("device_type"));
				baseConfig.setReportType(executSet.getString("report_type"));
				baseConfig.setBindType(executSet.getString("bind_type"));
				baseConfig.setDataSql(executSet.getString("data_sql"));
				baseConfig.setDataCode(executSet.getString("data_code"));
				baseConfig.setDataString(executSet.getString("data_string"));
				
				list.add(baseConfig);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
    	return list;
	}
	

	public static String getDataByCode(String code) {
		String data = "";
		Connection conn = Factory.getDB().getConnetion();
    	String sql="select tv.name,tv.value,tv.sort from PUB_CODE_TABLE t,pub_code_table_values tv "
    			+ "where tv.code_table_id = t.id and t.code='"+ code +"' "
    			+ "order by  to_number(decode(tv.sort,null,0,tv.sort)) ";
    	Statement queryStatement = null ;
		ResultSet executSet = null ;
		
		try {
			queryStatement=conn.createStatement();
			executSet=queryStatement.executeQuery(sql);
			while(executSet.next()){

				if(StringUtil.isEmpty(data)) {
					data = data +"[";
				}else {
					data = data +",";
				}
				
				String name = executSet.getString("name"); 
				String value = executSet.getString("value"); 
				
				data  = data + "{'id':'"+value+"','text':'"+name+"'}";


			}
			
			if(StringUtil.isNotEmpty(data)) {
				data = data +"]";
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
			return data;
	}	
	
	
}
