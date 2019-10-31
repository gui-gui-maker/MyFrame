package com.guido.repository.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.guido.model.BaseList;
import com.guido.repository.BaseListRepository;

@Repository
public class BaseListRepositoryImpl implements BaseListRepository{
	@Autowired
    private JdbcTemplate primaryJdbcTemplate;
	
	@Override
	public int save(BaseList blist, JdbcTemplate jdbcTemplate) {
		if(jdbcTemplate == null){
            jdbcTemplate= primaryJdbcTemplate;
        }
        return jdbcTemplate.update(
        		"INSERT INTO Base_List(code,name,sflb,msql,cc,pc,pcode,llevel,kl,jhxz,jhlb,zylb,zklx,fkey) "
        		+ " values(?, ?, ?,?, ?, ?,?, ?, ?, ?,?, ?, ?)",
        		blist.getCode(),
        		blist.getName(),
        		blist.getSflb(),
        		blist.getMsql(),
        		blist.getCc(),
        		blist.getPc(),
        		blist.getPcode(),
        		blist.getLlevel(),
        		blist.getKl(),
        		blist.getJhxz(),
        		blist.getJhlb(),
        		blist.getZylb(),
        		blist.getZklx(),
        		blist.getFkey()
        		);
     
	}

	@Override
	public int update(BaseList blist, JdbcTemplate jdbcTemplate) {
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
	public List<BaseList> findALL(JdbcTemplate jdbcTemplate) {
		 if(jdbcTemplate==null){
	            jdbcTemplate= primaryJdbcTemplate;
	        }
       // return jdbcTemplate.query("SELECT * FROM users", new UserRowMapper());
       return jdbcTemplate.query("SELECT * FROM Base_List", new BeanPropertyRowMapper(BaseList.class));
	}

	@Override
	public BaseList findById(String code, JdbcTemplate jdbcTemplate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Object[]> findCatalog(String yxdh) {
		String sql = "SELECT DISTINCT code,name,sflb from (  "
				+"select * from t_mulu where ml in (select dyml from (SELECT * FROM vt_zydh WHERE yxdh= ?1 AND dyml<>'' AND xgbj>=0) t) union "
				+"select * from t_mulu where sflb='0' and ml in (select CONCAT(SUBSTRING(dyml,1,1),'0000') from (SELECT * FROM vt_zydh WHERE yxdh= ?1 AND dyml<>'' AND xgbj>=0) t) union "
				+"select * from t_mulu where sflb='0' and ml in (select CONCAT(SUBSTRING(dyml,1,2),'000') from (SELECT * FROM vt_zydh WHERE yxdh= ?1 AND dyml<>'' AND xgbj>=0) t) union "
				+"select * from t_mulu where sflb='0' and ml in (select CONCAT(SUBSTRING(dyml,1,3),'00') from (SELECT * FROM vt_zydh WHERE yxdh= ?1 AND dyml<>'' AND xgbj>=0) t) union "
				+"select * from t_mulu where sflb='0' and ml in (select CONCAT(SUBSTRING(dyml,1,4),'0') from (SELECT * FROM vt_zydh WHERE yxdh= ?1 AND dyml<>'' AND xgbj>=0) t) "
				+")ml order by ml asc";
		return primaryJdbcTemplate.query(sql, new BeanPropertyRowMapper(BaseList.class));
	}

}
