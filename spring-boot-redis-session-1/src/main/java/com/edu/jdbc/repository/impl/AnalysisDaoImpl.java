package com.edu.jdbc.repository.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.edu.bean.Analysis;
import com.edu.jdbc.repository.AnalysisDao;
@Repository
public class AnalysisDaoImpl implements AnalysisDao{

	@Autowired
    private JdbcTemplate primaryJdbcTemplate;
	
	@Override
    public List<Analysis> findByParams(String yxdh,String kldm,String zydh,int Score) {
		StringBuilder sql = 
				new StringBuilder("select v.*,\n" +
						"       (select max(t.ranking)\n" + 
						"          from YX_WC_FS t\n" + 
						"         where t.nf = '2018'\n" + 
						"           and t.stream = v.kldm\n" + 
						"           and t.score >= v.zy_lqf) as zy_wc18,\n" + 
						"       (select count(1)\n" + 
						"          from Matriculate m\n" + 
						"         where m.kldm = v.kldm\n" + 
						"           and m.tdcj >= v.zy_lqf\n" + 
						"           and m.nf = '2018') lqs,\n" + 
						"       y.wc18 dd_wc,\n" + 
						"       y.ddx18,\n" + 
						"       y.jh18,\n" + 
						"       y.jh19,\n" + 
						"       y.jhc\n" + 
						"  from (select yxdm, yxdh, yxmc, zydh, zymc, kldm, pcdm, min(tdcj) zy_lqf\n" + 
						"          from Matriculate\n" + 
						"         where jhxz = '0'\n" + 
						"           and nf = '2018'\n" );
		if(!StringUtils.isEmpty(yxdh)) {
			sql.append("        and yxdh in ('"+yxdh.replaceAll(",", "','")+"')");
		} 
		if(!StringUtils.isEmpty(kldm)) {
			sql.append("        and kldm in ('"+kldm.replaceAll(",", "','")+"')");
		} 
		if(!StringUtils.isEmpty(zydh)) {
			sql.append("        and zydh in ('"+zydh.replaceAll(",", "','")+"')");
		} 
						
		sql.append("         group by yxdm, yxdh, yxmc, nf, zydh, zymc, kldm, pcdm\n" + 
						"         order by yxdm, yxdh, kldm, min(tdcj)) v,\n" + 
						"       YX_WC_JH y\n" + 
						" where v.yxdh = y.yxdh\n" + 
						"   and v.kldm = y.kldm\n" + 
						"   and v.pcdm = y.pcdm");
		System.out.println(sql.toString());

        return primaryJdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper(Analysis.class));
    }
}
