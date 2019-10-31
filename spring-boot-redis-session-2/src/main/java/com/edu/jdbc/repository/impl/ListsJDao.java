package com.edu.jdbc.repository.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.edu.bean.Catalog;
import com.edu.jdbc.repository.ListsJDaoI;

@Repository
public class ListsJDao implements ListsJDaoI{
	@Autowired
    private JdbcTemplate primaryJdbcTemplate;
	
	

	@Override
	public int update(Catalog blist, JdbcTemplate jdbcTemplate) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(String code, JdbcTemplate jdbcTemplate) {
		// TODO Auto-generated method stub
		return 0;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<Catalog> findALL(JdbcTemplate jdbcTemplate) {
		 if(jdbcTemplate==null){
	            jdbcTemplate= primaryJdbcTemplate;
	        }
       // return jdbcTemplate.query("SELECT * FROM users", new UserRowMapper());
       return jdbcTemplate.query("SELECT * FROM Base_List", new BeanPropertyRowMapper(Catalog.class));
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<Catalog> listAll(JdbcTemplate jdbcTemplate) {
		if(jdbcTemplate==null){
			jdbcTemplate= primaryJdbcTemplate;
		}
		// return jdbcTemplate.query("SELECT * FROM users", new UserRowMapper());
		return jdbcTemplate.query("select t.code,t.pcode, t.name, t.sflb\r\n" + 
				"          from BASE_LIST t\r\n" + 
				"         start with t.pcode = '00000'\r\n" + 
				"        connect by prior t.code = t.pcode\r\n" + 
				"   order by code", new BeanPropertyRowMapper(Catalog.class));
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<Catalog> listOneByYxdh(JdbcTemplate jdbcTemplate,String yxdh) {
		if(jdbcTemplate==null){
			jdbcTemplate= primaryJdbcTemplate;
		}
		// return jdbcTemplate.query("SELECT * FROM users", new UserRowMapper());
		return jdbcTemplate.query(
				"select t.code, t.pcode, t.name, t.sflb\n" +
						"  from (select *\n" + 
						"          from BASE_LIST\n" + 
						"         where sflb = '1'\n" + 
						"           and trim(code) in (select trim(dyml)\n" + 
						"                                from base_publish_plan\n" + 
						"                               WHERE yxdh = ?\n" + 
						"                                 AND dyml <> ' '\n" + 
						"                                 AND xgbj >= 0)\n" + 
						"        union\n" + 
						"        select *\n" + 
						"          from BASE_LIST\n" + 
						"         where sflb = '0'\n" + 
						"           and trim(code) in (select SUBSTR(dyml, 1, 1) || '0000'\n" + 
						"                                from base_publish_plan\n" + 
						"                               WHERE yxdh = ?\n" + 
						"                                 AND dyml <> ' '\n" + 
						"                                 AND xgbj >= 0)\n" + 
						"        union\n" + 
						"        select *\n" + 
						"          from BASE_LIST\n" + 
						"         where sflb = '0'\n" + 
						"           and trim(code) in (select SUBSTR(dyml, 1, 2) || '000'\n" + 
						"                                from base_publish_plan\n" + 
						"                               WHERE yxdh = ?\n" + 
						"                                 AND dyml <> ' '\n" + 
						"                                 AND xgbj >= 0)\n" + 
						"        union\n" + 
						"        select *\n" + 
						"          from BASE_LIST\n" + 
						"         where sflb = '0'\n" + 
						"           and trim(code) in (select SUBSTR(dyml, 1, 3) || '00'\n" + 
						"                                from base_publish_plan\n" + 
						"                               WHERE yxdh = ?\n" + 
						"                                 AND dyml <> ' '\n" + 
						"                                 AND xgbj >= 0)\n" + 
						"        union\n" + 
						"        select *\n" + 
						"          from BASE_LIST\n" + 
						"         where sflb = '0'\n" + 
						"           and trim(code) in (select SUBSTR(dyml, 1, 4) || '0'\n" + 
						"                                from base_publish_plan\n" + 
						"                               WHERE yxdh = ?\n" + 
						"                                 AND dyml <> ' '\n" + 
						"                                 AND xgbj >= 0)) t\n" + 
						" start with t.pcode = '00000'\n" + 
						" connect by prior t.code = t.pcode\n" + 
						" order by code", new Object[] {yxdh,yxdh,yxdh,yxdh,yxdh},new BeanPropertyRowMapper(Catalog.class));
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<Catalog> listOneByYxdm(JdbcTemplate jdbcTemplate,String yxdm) {
		if(jdbcTemplate==null){
			jdbcTemplate= primaryJdbcTemplate;
		}
		// return jdbcTemplate.query("SELECT * FROM users", new UserRowMapper());
		return jdbcTemplate.query(
"select t.code, t.pcode, t.name, t.sflb\n" +
"  from (select *\n" + 
"          from BASE_LIST\n" + 
"         where sflb = '1'\n" + 
"           and trim(code) in (select trim(dyml)\n" + 
"                                from base_publish_plan\n" + 
"                               WHERE yxdm = ?\n" + 
"                                 AND dyml <> ' '\n" + 
"                                 AND xgbj >= 0)\n" + 
"        union\n" + 
"        select *\n" + 
"          from BASE_LIST\n" + 
"         where sflb = '0'\n" + 
"           and trim(code) in (select SUBSTR(dyml, 1, 1) || '0000'\n" + 
"                                from base_publish_plan\n" + 
"                               WHERE yxdm = ?\n" + 
"                                 AND dyml <> ' '\n" + 
"                                 AND xgbj >= 0)\n" + 
"        union\n" + 
"        select *\n" + 
"          from BASE_LIST\n" + 
"         where sflb = '0'\n" + 
"           and trim(code) in (select SUBSTR(dyml, 1, 2) || '000'\n" + 
"                                from base_publish_plan\n" + 
"                               WHERE yxdm = ?\n" + 
"                                 AND dyml <> ' '\n" + 
"                                 AND xgbj >= 0)\n" + 
"        union\n" + 
"        select *\n" + 
"          from BASE_LIST\n" + 
"         where sflb = '0'\n" + 
"           and trim(code) in (select SUBSTR(dyml, 1, 3) || '00'\n" + 
"                                from base_publish_plan\n" + 
"                               WHERE yxdm = ?\n" + 
"                                 AND dyml <> ' '\n" + 
"                                 AND xgbj >= 0)\n" + 
"        union\n" + 
"        select *\n" + 
"          from BASE_LIST\n" + 
"         where sflb = '0'\n" + 
"           and trim(code) in (select SUBSTR(dyml, 1, 4) || '0'\n" + 
"                                from base_publish_plan\n" + 
"                               WHERE yxdm = ?\n" + 
"                                 AND dyml <> ' '\n" + 
"                                 AND xgbj >= 0)) t\n" + 
" start with t.pcode = '00000'\n" + 
"connect by prior t.code = t.pcode\n" + 
" order by code", new Object[] {yxdm,yxdm,yxdm,yxdm,yxdm},new BeanPropertyRowMapper(Catalog.class));
	}

	@Override
	public Catalog findById(String code, JdbcTemplate jdbcTemplate) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
