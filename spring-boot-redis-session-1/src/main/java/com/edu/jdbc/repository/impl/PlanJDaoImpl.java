package com.edu.jdbc.repository.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.edu.bean.Catalog;
import com.edu.bean.Plan;
import com.edu.bean.PlanParam;
import com.edu.jdbc.repository.PlanJDao;

@Repository
public class PlanJDaoImpl implements PlanJDao{
	@Autowired
    private JdbcTemplate primaryJdbcTemplate;
	@SuppressWarnings("unchecked")
	@Override
	public List<Plan> getPlanBySql(String sql) throws Exception {
		return primaryJdbcTemplate.query(sql, new BeanPropertyRowMapper(Plan.class));
	}
	

	@Override
	public List<PlanParam> findByYxdhAndDyml(String yxdh, String dyml) throws Exception {
		String sql = "SELECT	yxdh,yxbz,jhlbdm,yxmc,	\r\n" + 
				"CASE yxjblxdm \r\n" + 
				"	WHEN '2' THEN '(民办院校)'\r\n" + 
				"	WHEN '3' THEN '(独立院校)'\r\n" + 
				"	WHEN '4' THEN '(中外合作办学)'\r\n" + 
				"	WHEN '5' THEN '(内地与港澳台地区合作办学)'\r\n" + 
				"	ELSE '' \r\n" + 
				"	END yxjblxmc,zjhs,yxdz,yxdhmc,jhxzdm,\r\n" + 
				"case when pcdm in ('5','6','7','8','9') and kldm<>'C' and jffsyt>' ' then jffsyt else jffs end jffs\r\n" + 
				"FROM (SELECT * FROM vt_zydh WHERE yxdh = ? AND dyml= ? AND xgbj >= 0	) v";
		return primaryJdbcTemplate.query(sql,  new Object[] {yxdh,dyml},new BeanPropertyRowMapper(PlanParam.class));
	}


	@Override
	public List<PlanParam> queryMajors(String yxdh, String dyml) throws Exception {
		String sql = "SELECT xzdm,zszymc,zkfx,zklxdm,kldm,pcdm,zklxmc,jhlbdm,bxdd,bhzygs,wyyz,sfks,zybz,zylbdm,jhxzdm,sbzydh,xbzydh,zsjhs,sfbz \n"
				+ "FROM vt_zydh WHERE yxdh = ? AND xgbj >= 0 AND dyml = ? \n"
				+ "ORDER BY yxdh,jhxzdm,sbzydh ASC";
		return primaryJdbcTemplate.query(sql,  new Object[] {yxdh,dyml},new BeanPropertyRowMapper(PlanParam.class));
	}

}
