package com.guido.repository.impl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.guido.model.BaseJh;
import com.guido.repository.BaseJhRepository;
@Repository
public class BaseJhRepositoryImpl implements BaseJhRepository{
	
	@Autowired
    private JdbcTemplate primaryJdbcTemplate;

	public int batchCreateArticle(final List<BaseJh> jhs) {
	    String sql = "insert into BASE_JH   (jhid,yxdm,yxmc,yxdh,yxdhmc,sbzydh,zydm,zszymc,zkfx,bhzygs,zylbdm,ccdm,xzdm,sfbz,bxdd,sfks,wyyz,kldm,jhxzdm,pcdm,jhlbdm,zklxdm,zklxmc,kskmyq,kskmyqzw,xkkmbhzy,xbyq,zsjhs,zjhs,zybz,ytlb,yxbz,dz1,dz2,dz3,dz4,dz5,dz6,bbjhid,sfdy,dybz,dyml,cc,pc,yxdz,xbzydh,bhzy,bxddssmc,bxdddjsmc,bxddqxmc,bxddxxdz,bxddbb,xgbj,dz9,dz8,sddm,id)  values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)" ; 

	    // spring jdbc 帮我们生成了批量插入的 sql 语句, 我们也可以直接使用批量的插入 sql 语句进行批量数据插入
	    // return jdbcTemplate.batchUpdate(new String[]{sql});
	    return primaryJdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
	        @Override
	        public void setValues(PreparedStatement preparedStatement, int i) throws SQLException {
	        	BaseJh jh = jhs.get(i);
				 preparedStatement.setString(	1	,	jh.getJhid());
				 preparedStatement.setString(	2	,	jh.getYxdm());
				 preparedStatement.setString(	3	,	jh.getYxmc());
				 preparedStatement.setString(	4	,	jh.getYxdh());
				 preparedStatement.setString(	5	,	jh.getYxdhmc()   	);
				 preparedStatement.setString(	6	,	jh.getSbzydh()    	);
				 preparedStatement.setString(	7	,	jh.getZydm());
				 preparedStatement.setString(	8	,	jh.getZszymc()    	);
				 preparedStatement.setString(	9	,	jh.getZkfx()     	);
				 preparedStatement.setString(	10	,	jh.getBhzygs()   	);
				 preparedStatement.setString(	11	,	jh.getZylbdm()    	);
				 preparedStatement.setString(	12	,	jh.getCcdm()      	);
				 preparedStatement.setString(	13	,	jh.getXzdm()      	);
				 preparedStatement.setString(	14	,	jh.getSfbz()      	);
				 preparedStatement.setString(	15	,	jh.getBxdd()      	);
				 preparedStatement.setString(	16	,	jh.getSfks()      	);
				 preparedStatement.setString(	17	,	jh.getWyyz()      	);
				 preparedStatement.setString(	18	,	jh.getKldm()      	);
				 preparedStatement.setString(	19	,	jh.getJhxzdm()    	);
				 preparedStatement.setString(	20	,	jh.getPcdm()      	);
				 preparedStatement.setString(	21	,	jh.getJhlbdm()    	);
				 preparedStatement.setString(	22	,	jh.getZklxdm()    	);
				 preparedStatement.setString(	23	,	jh.getZklxmc()    	);
				 preparedStatement.setString(	24	,	jh.getKskmyq()    	);
				 preparedStatement.setString(	25	,	jh.getKskmyqzw()  	);
				 preparedStatement.setString(	26	,	jh.getXkkmbhzy()  	);
				 preparedStatement.setString(	27	,	jh.getXbyq()      	);
				 preparedStatement.setInt(	28	,	jh.getZsjhs()==null ? 0: jh.getZsjhs());
				 preparedStatement.setInt(	29	,	jh.getZjhs()==null ? 0: jh.getZjhs());
				 preparedStatement.setString(	30	,	jh.getZybz()     	);
				 preparedStatement.setString(	31	,	jh.getYtlb()      	);
				 preparedStatement.setString(	32	,	jh.getYxbz()    	);
				 preparedStatement.setString(	33	,	jh.getDz1()       	);
				 preparedStatement.setString(	34	,	jh.getDz2()       	);
				 preparedStatement.setString(	35	,	jh.getDz3()       	);
				 preparedStatement.setString(	36	,	jh.getDz4()       	);
				 preparedStatement.setString(	37	,	jh.getDz5()       	);
				 preparedStatement.setString(	38	,	jh.getDz6()       	);
				 preparedStatement.setString(	39	,	jh.getBbjhid()    	);
				 preparedStatement.setString(	40	,	jh.getSfdy()      	);
				 preparedStatement.setString(	41	,	jh.getDybz()      	);
				 preparedStatement.setString(	42	,	jh.getDyml()      	);
				 preparedStatement.setString(	43	,	jh.getCc()        	);
				 preparedStatement.setString(	44	,	jh.getPc()        	);
				 preparedStatement.setString(	45	,	jh.getYxdz()      	);
				 preparedStatement.setString(	46	,	jh.getXbzydh()    	);
				 preparedStatement.setString(	47	,	jh.getBhzy()      	);
				 preparedStatement.setString(	48	,	jh.getBxddssmc()  	);
				 preparedStatement.setString(	49	,	jh.getBxdddjsmc() 	);
				 preparedStatement.setString(	50	,	jh.getBxddqxmc()  	);
				 preparedStatement.setString(	51	,	jh.getBxddxxdz()  	);
				 preparedStatement.setString(	52	,	jh.getBxddbb()    	);
				 preparedStatement.setString(	53	,	jh.getXgbj()      	);
				 preparedStatement.setString(	54	,	jh.getDz9()       	);
				 preparedStatement.setString(	55	,	jh.getDz8()       	);
				 preparedStatement.setString(	56	,	jh.getSddm()      	);
				 preparedStatement.setString(	57	,	UUID.randomUUID().toString());

	        }

	        @Override
	        public int getBatchSize() {
	            return jhs.size();
	        }
	    }).length;
	}

}
