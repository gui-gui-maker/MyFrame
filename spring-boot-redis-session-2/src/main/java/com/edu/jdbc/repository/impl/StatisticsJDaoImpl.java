package com.edu.jdbc.repository.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.edu.bean.Analysis;
import com.edu.bean.CatalogJH;
import com.edu.bean.Statistics1;
import com.edu.jdbc.repository.StatisticsJDao;
import com.edu.util.StringUtil;

@Repository
public class StatisticsJDaoImpl implements StatisticsJDao{
	
	@Autowired
    private JdbcTemplate primaryJdbcTemplate;
	
    public List<Statistics1> findByParams(String yxdm) throws Exception{
		StringBuilder sql18 = 
				new StringBuilder("select l.yxdh,l.yxdm,l.yxmc,l.nf,l.pcdm,l.pcmc,l.kldm,l.klmc,j.jhs,l.lqs,l.ddx "+
						"from ( SELECT  yxdh,yxdm,yxmc,nf,pcdm,pcmc,kldm,klmc,COUNT(1) lqs,MIN(tdcj) ddx from all_lqk ");
		if(!StringUtils.isEmpty(yxdm)) {
			sql18.append(" where yxdm in ('"+yxdm.replaceAll(",", "','")+"')");
		}				
		sql18.append(" GROUP BY yxdh,yxdm,yxmc,nf,pcdm,pcmc,kldm,klmc) l "+ 
						"left join (SELECT yxdh,yxdm,nf,pcdm,pcmc,kldm,klmc,SUM(jhrs) jhs from all_jhk ");
		if(!StringUtils.isEmpty(yxdm)) {
			sql18.append(" where yxdm in ('"+yxdm.replaceAll(",", "','")+"')");
		}	
		sql18.append(" GROUP BY yxdh,yxdm,nf,pcdm,pcmc,kldm,klmc) j "+
						"on l.yxdh+l.yxdm+convert(varchar(2),l.nf)+l.pcdm+l.pcmc+l.kldm+l.klmc = j.yxdh+j.yxdm+convert(varchar(2),j.nf)+j.pcdm+j.pcmc+j.kldm+j.klmc ");

		System.out.println(sql18.toString());

        return primaryJdbcTemplate.query(sql18.toString(), new BeanPropertyRowMapper(Statistics1.class));
    }

	@Override
	public Set<String> findYxdm(String yxdms) throws Exception {
		String sql = "select yxdm from all_lqk where yxdm in ('"+yxdms.replaceAll(",", "','")+"') union select yxdm from all_jhk where yxdm in ('"+yxdms.replaceAll(",", "','")+"')";
		System.out.println(sql);
		List<String> list = primaryJdbcTemplate.query(sql, new MyRowMapper());
		
		return new HashSet<String>(list);
	}
	
	

	@Override
	public List<CatalogJH> queryJhsByCatalog(String catalogs) throws Exception {
		String sql = "select t.dyml ml,m.msg mlmc,sum(t.zsjhs) jhs from t_zydh t inner join t_mulu m on t.dyml = m.ml ";
		if(!StringUtil.isEmpty(catalogs)) {
			sql += "where t.dyml in ('"+catalogs.replace(",", "','")+"') ";
		}
		sql += " group by t.dyml,m.msg order by t.dyml";
		System.out.println(sql);
		List<CatalogJH> list = primaryJdbcTemplate.query(sql, new CatalogJHRowMapper());
		return list;
	}
	public class MyRowMapper implements RowMapper<String>{

	    @Override
	    public String mapRow(ResultSet resultSet, int i) throws SQLException {
//	        获取结果集中的数据
	        String yxdm = resultSet.getString("yxdm");
	        return yxdm;
	    }
	}
	public class CatalogJHRowMapper implements RowMapper<CatalogJH>{
		
		@Override
		public CatalogJH mapRow(ResultSet resultSet, int i) throws SQLException {
//	        获取结果集中的数据
			CatalogJH catalogJh = new CatalogJH();
			catalogJh.setMl(resultSet.getString("ml"));
			catalogJh.setMlmc(resultSet.getString("mlmc"));
			catalogJh.setJhs(resultSet.getInt("jhs"));
			return catalogJh;
		}
	}
}
