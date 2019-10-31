package com.edu.jdbc.repository.impl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.edu.bean.SourcePlan;
import com.edu.jdbc.repository.SourcePlanJDaoI;
@Repository
public class SourcePlanJDao implements SourcePlanJDaoI{
	
	@Autowired
    private JdbcTemplate primaryJdbcTemplate;

	public int batchCreateArticle(final List<SourcePlan> jhs) throws Exception{
	    String sql = "insert into BASE_LYJH   "
	    		+ "(id,jhid,	nf,	yxdm,	yxmc,	yxdh,	yxdhmc,	zszydm,	zszymc,	zylbdm,	zylbmc,	bhzy,	bhzygs,	ccdm,	ccmc,	sbzydh,	xbzydh,	zkfx,	xzdm,	xzmc,	sfbz,	bxdd,	bxddssmc,	bxdddjsmc,	bxddqxmc,	bxddxxdz,	bxddbb,	sfks,	wyyz,	yxbmdm,	yxbmmc,	kldm,	klmc,	jhxzdm,	jhxzmc,	jhlbdm,	jhlbmc,	syssdm,	syssmc,	pcdm,	pcmc,	zklxdm,	zklxmc,	kskmyq,	kskmyqzw,	xkkmbhzy,	skyq,	zybz,	dz1,	dz2,	dz3,	dz4,	dz5,	dz6,	dz7,	dz8,	dz9,	dz10,	zsjhs,	xfzy,zyxztj) "
	    		+ " values(?,?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?)" ; 

	    // spring jdbc 帮我们生成了批量插入的 sql 语句, 我们也可以直接使用批量的插入 sql 语句进行批量数据插入
	    // return jdbcTemplate.batchUpdate(new String[]{sql});
	    return primaryJdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
	        @Override
	        public void setValues(PreparedStatement preparedStatement, int i) throws SQLException {
	        	SourcePlan jh = jhs.get(i);
	        	preparedStatement.setString(1,UUID.randomUUID().toString());
	        	preparedStatement.setString(2,jh.getJhid());
	        	preparedStatement.setString(3,jh.getNf());
	        	preparedStatement.setString(4,jh.getYxdm());
	        	preparedStatement.setString(5,jh.getYxmc());
	        	preparedStatement.setString(6,jh.getYxdh());
	        	preparedStatement.setString(7,jh.getYxdhmc());
	        	preparedStatement.setString(8,jh.getZszydm());
	        	preparedStatement.setString(9,jh.getZszymc());
	        	preparedStatement.setString(10,jh.getZylbdm());
	        	preparedStatement.setString(11,jh.getZylbmc());
	        	preparedStatement.setString(12,jh.getBhzy());
	        	preparedStatement.setString(13,jh.getBhzygs());
	        	preparedStatement.setString(14,jh.getCcdm());
	        	preparedStatement.setString(15,jh.getCcmc());
	        	preparedStatement.setString(16,jh.getSbzydh());
	        	preparedStatement.setString(17,jh.getXbzydh());
	        	preparedStatement.setString(18,jh.getZkfx());
	        	preparedStatement.setString(19,jh.getXzdm());
	        	preparedStatement.setString(20,jh.getXzmc());
	        	preparedStatement.setString(21,jh.getSfbz());
	        	preparedStatement.setString(22,jh.getBxdd());
	        	preparedStatement.setString(23,jh.getBxddssmc());
	        	preparedStatement.setString(24,jh.getBxdddjsmc());
	        	preparedStatement.setString(25,jh.getBxddqxmc());
	        	preparedStatement.setString(26,jh.getBxddxxdz());
	        	preparedStatement.setString(27,jh.getBxddbb());
	        	preparedStatement.setString(28,jh.getSfks());
	        	preparedStatement.setString(29,jh.getWyyz());
	        	preparedStatement.setString(30,jh.getYxbmdm());
	        	preparedStatement.setString(31,jh.getYxbmmc());
	        	preparedStatement.setString(32,jh.getKldm());
	        	preparedStatement.setString(33,jh.getKlmc());
	        	preparedStatement.setString(34,jh.getJhxzdm());
	        	preparedStatement.setString(35,jh.getJhxzmc());
	        	preparedStatement.setString(36,jh.getJhlbdm());
	        	preparedStatement.setString(37,jh.getJhlbmc());
	        	preparedStatement.setString(38,jh.getSyssdm());
	        	preparedStatement.setString(39,jh.getSyssmc());
	        	preparedStatement.setString(40,jh.getPcdm());
	        	preparedStatement.setString(41,jh.getPcmc());
	        	preparedStatement.setString(42,jh.getZklxdm());
	        	preparedStatement.setString(43,jh.getZklxmc());
	        	preparedStatement.setString(44,jh.getKskmyq());
	        	preparedStatement.setString(45,jh.getKskmyqzw());
	        	preparedStatement.setString(46,jh.getXkkmbhzy());
	        	preparedStatement.setString(47,jh.getSkyq());
	        	preparedStatement.setString(48,jh.getZybz());
	        	preparedStatement.setString(49,jh.getDz1());
	        	preparedStatement.setString(50,jh.getDz2());
	        	preparedStatement.setString(51,jh.getDz3());
	        	preparedStatement.setString(52,jh.getDz4());
	        	preparedStatement.setString(53,jh.getDz5());
	        	preparedStatement.setString(54,jh.getDz6());
	        	preparedStatement.setString(55,jh.getDz7());
	        	preparedStatement.setString(56,jh.getDz8());
	        	preparedStatement.setString(57,jh.getDz9());
	        	preparedStatement.setString(58,jh.getDz10());
	        	preparedStatement.setInt(59,jh.getZsjhs());
	        	preparedStatement.setString(60,jh.getXfzy());
	        	preparedStatement.setString(61,jh.getZyxztj());
	        }

	        @Override
	        public int getBatchSize() {
	            return jhs.size();
	        }
	    }).length;
	}

}
