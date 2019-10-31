package com.edu.jdbc.repository.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.edu.bean.JhTzDetail;


@Repository
public class AddJHJDaoImpl {
	@Autowired
    private JdbcTemplate secondaryJdbcTemplate;
	//院校增加计划不对等
	public List<Map<String,Object>> lyNotEqualLq() {
		String sql = "select a.yxdh lyyxdh,a.lytzs,b.yxdh lqyxdh,b.lqtzs from \r\n" + 
				"(select yxdh,SUM(tzjhs) lytzs from v_g_ly group by yxdh having SUM(tzjhs) <> 0 ) a full join \r\n" + 
				"(select yxdh,SUM(tzs) lqtzs from v_lq group by yxdh having SUM(tzs)<>0) b \r\n" + 
				"on a.yxdh = b.yxdh where a.lytzs <> b.lqtzs  ";
		List<Map<String,Object>> list = secondaryJdbcTemplate.queryForList(sql);
		return list;
	}
	//计划仅来源增加或录取增加
	public List<Map<String,Object>> onlyAddLyOrLq() {
		String sql = " select a.yxdh lyyxdh,a.lytzs,b.yxdh lqyxdh,b.lqtzs from " +
				"(select yxdh,SUM(tzjhs) lytzs from v_g_ly group by yxdh having SUM(tzjhs) <> 0 ) a full join "+
				"(select yxdh,SUM(tzs) lqtzs from v_lq group by yxdh having SUM(tzs)<>0) b "+
				"on a.yxdh = b.yxdh where a.yxdh is null or b.yxdh is null ";
		List<Map<String,Object>> list = secondaryJdbcTemplate.queryForList(sql);
		return list;
	}
	//院校增加计划明细
	public List<Map<String,Object>> yxAddJhDetail(String id) {
		String sql = "select * from (\r\n" + 
				"	select yxdm,yxdh,yxmc,xbzydh zydh,zydhmc zymc,'' cc,ccmc,kldm,klmc,jhxzdm,jhxzmc,jhlbdm,jhlbmc,pcdm,pcmc,'' tddw,tzjhs,-1 xt from ly \r\n" + 
				"	union\r\n" + 
				"	select '' yxdm,yxdh,'' yxmc,zydh,'' zymc,'' cc,'' ccmc,kldm,'' klmc,jhxz,'' jhxzmc,'' jhlbdm,'' jhlbmc,pcdm,'' pcmc,tddw,t.jhzxs-t.jhrs tzjhs ,1 xt \r\n" + 
				"	from lq t where t.jhzxs-t.jhrs<>0\r\n" + 
				") t where t.yxdh = ? order by zydh ";
		List<Map<String,Object>> list = secondaryJdbcTemplate.queryForList(sql,id);
		return list;
	}
	//院校增加计划明细2
	public List<JhTzDetail> yxAddJhDetail() {
		String sql = "select ly.yxdh+ly.xbzydh yxzy,ly.yxdm,ly.yxdh,ly.yxmc,ly.xbzydh zydh,ly.zydhmc zymc,'' cc,ly.ccmc, \r\n" + 
				"b.kldm,ly.klmc,b.jhxzdm,ly.jhxzmc,b.jhlbdm,ly.jhlbmc,b.pcdm,ly.pcmc,'' tddw,convert(int,ly.tzjhs)*-1 tzjhs,-1 xt  \r\n" + 
				"from ly LEFT JOIN jhdm2lqdm as b  ON ly.pcdm=b.pcdm  \r\n" + 
				"AND ly.kldm=b.kldm AND ly.jhxzdm=b.jhxzdm and ly.jhlbdm = b.jhlbdm \r\n" + 
				"union \r\n" + 
				"select yxdh+zydh yxzy ,'' yxdm,yxdh,'' yxmc,zydh,'' zymc,'' cc,'' ccmc,kldm,'' klmc,jhxz,'' jhxzmc,'' jhlbdm,'' jhlbmc,pcdm,'' pcmc,tddw,jhzxs-jhrs tzjhs ,1 xt  \r\n" + 
				"from lq where jhzxs-jhrs<>0 ";
		List<JhTzDetail> list = secondaryJdbcTemplate.query(sql, new BeanPropertyRowMapper(JhTzDetail.class));
		return list;
	}
	
	
	public List<Map<String,Object>> queryAddJh() {
		String sql = " select a.pcdm,b.pcmc,a.kldm,a.jhxz,c.jhxzmc,a.cc,sum(a.jhzxs-a.jhrs) zjjh \r\n" + 
				"  from lq a left join lq_pcdm b on a.pcdm=b.pcdm left join lq_jhxzdm c on a.jhxz = c.jhxz  \r\n" + 
				"  group by  a.pcdm,b.pcmc,a.kldm,a.jhxz,c.jhxzmc,a.cc order by a.pcdm,a.kldm,a.jhxz";
		List<Map<String,Object>> list = secondaryJdbcTemplate.queryForList(sql);
		return list;
	}
	
	
	 

	public Object queryTotal() {
		String sql1 = "select SUM(convert(int,tzjhs)) from ly";
		String sql2 = "select SUM(jhzxs-jhrs) from lq";
		Object lyTotal = secondaryJdbcTemplate.queryForObject(sql1, Object.class);
		Object lqTotal = secondaryJdbcTemplate.queryForObject(sql2, Object.class);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("ly", lyTotal);
		map.put("lq", lqTotal);
		
		return map;
	}
}
