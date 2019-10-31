package com.edu.util;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.edu.bean.Condition;
import com.edu.bean.Dict;

import net.sf.json.JSONArray;

@Component
public class UserDictUtil {

	public static JdbcTemplate primaryJdbcTemplate;
	
	@Autowired
	public void setPrimaryJdbcTemplate(JdbcTemplate primaryJdbcTemplate) {
		UserDictUtil.primaryJdbcTemplate = primaryJdbcTemplate;
	}


	public static String dict(Class<?> cls,String key,String value,String condition) throws NoSuchFieldException, SecurityException {
		String tableName = null;
		String kColumnName = null;
		String vColumnName = null;
		Table table = cls.getAnnotation(Table.class);
		if(table!=null) {
			tableName = table.name();
		}else {
			tableName = cls.getSimpleName();
		}
		Field kf = cls.getDeclaredField(key);
		Field vf = cls.getDeclaredField(value);
		Column kColumn = kf.getAnnotation(Column.class);
		if(kColumn!=null) {
			kColumnName = kColumn.name();
		}else {
			kColumnName = key;
		}
		Column vColumn = vf.getAnnotation(Column.class);
		
		if(vColumn!=null) {
			vColumnName = vColumn.name();
		}else {
			vColumnName = value;
		}
		StringBuilder sql = new StringBuilder("select "+kColumnName+" as id , "+vColumnName+" as text, nf from "+tableName);
		/*
		 * if(list.size()>0) { sql.append(" where 1=1 "); for (Condition c : list) {
		 * if(!"user".equals(c.getType())) { if("or".equals(c.getLogic())) { //和前面一个条件or
		 * }else { //and }
		 * sql.append(c.getLogic()+c.getField()+c.getRef()+c.getValue()); }
		 * 
		 * 
		 * } }
		 */
		if(null!=condition) {
			sql.append(" where ").append(condition);
		}
		System.out.println("dict -->>"+sql);
		List<Dict> dicts = UserDictUtil.primaryJdbcTemplate.query(sql.toString(), new DictRowMapper());
		System.out.println("dict size "+dicts.size());
		
		return JSONArray.fromObject(dicts).toString();
	}
	
	
	public static class DictRowMapper implements RowMapper<Dict>{

	    @Override
	    public Dict mapRow(ResultSet resultSet, int i) throws SQLException {
//	        获取结果集中的数据
	    	Dict dict = new Dict();
	    	dict.setKey(resultSet.getString("id"));
	    	dict.setValue(resultSet.getString("text"));
	    	dict.setVersion(resultSet.getString("nf"));
	        return dict;
	    }
	}
}
