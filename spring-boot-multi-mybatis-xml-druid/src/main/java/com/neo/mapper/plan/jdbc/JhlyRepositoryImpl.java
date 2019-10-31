package com.neo.mapper.plan.jdbc;

import com.neo.mapper.plan.JhlyMapper;
import com.neo.model.Jhly;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JhlyRepositoryImpl implements JhlyMapper {

	@Autowired
    private JdbcTemplate jdbcTemplate;

	@Override
	public List<Jhly> getAll() {
		return null;
	}

	@Override
	public Jhly getOne(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int addJhly(Jhly jhly) {
		return jdbcTemplate.update(
				"INSERT INTO jhly(jhid,\n" + 
				"		nf,\n" + 
				"		yxdm,\n" + 
				"		yxmc,\n" + 
				"		yxdh,\n" + 
				"		yxdhmc,\n" + 
				"		zszydm,\n" + 
				"		zszymc,\n" + 
				"		zylbdm,\n" + 
				"		zylbmc,\n" + 
				"		bhzy,\n" + 
				"		bhzygs,\n" + 
				"		ccdm,\n" + 
				"		ccmc,\n" + 
				"		sbzydh,\n" + 
				"		xbzydh,\n" + 
				"		zkfx,\n" + 
				"		xzdm,\n" + 
				"		xzmc,\n" + 
				"		sfbz,\n" + 
				"		bxdd,\n" + 
				"		bxddssmc,\n" + 
				"		bxdddjsmc,\n" + 
				"		bxddqxmc,\n" + 
				"		bxddxxdz,\n" + 
				"		bxddbb,\n" + 
				"		sfks,\n" + 
				"		wyyz,\n" + 
				"		yxbmdm,\n" + 
				"		yxbmmc,\n" + 
				"		kldm,\n" + 
				"		klmc,\n" + 
				"		jhxzdm,\n" + 
				"		jhxzmc,\n" + 
				"		jhlbdm,\n" + 
				"		jhlbmc,\n" + 
				"		syssdm,\n" + 
				"		syssmc,\n" + 
				"		pcdm,\n" + 
				"		pcmc,\n" + 
				"		zklxdm,\n" + 
				"		zklxmc,\n" + 
				"		kskmyq,\n" + 
				"		kskmyqzw,\n" + 
				"		xkkmbhzy,\n" + 
				"		skyq,\n" + 
				"		zybz,\n" + 
				"		dz1,\n" + 
				"		dz2,\n" + 
				"		dz3,\n" + 
				"		dz4,\n" + 
				"		dz5,\n" + 
				"		dz6,\n" + 
				"		dz7,\n" + 
				"		dz8,\n" + 
				"		dz9,\n" + 
				"		dz10,\n" + 
				"		zsjhs,\n" + 
				"		xfzy,\n" + 
				"		zyxztj) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
				jhly.getJhid(),jhly.getNf(),jhly.getYxdm(),jhly.getYxmc(),jhly.getYxdh(),jhly.getYxdhmc(),jhly.getZszydm(),jhly.getZszymc(),jhly.getZylbdm(),jhly.getZylbmc(),jhly.getBhzy() ,jhly.getBhzygs(),jhly.getCcdm(),jhly.getCcmc(),jhly.getSbzydh(),jhly.getXbzydh(),jhly.getZkfx(),jhly.getXzdm() ,jhly.getXzmc(),jhly.getSfbz(),jhly.getBxdd(),jhly.getBxddssmc(),jhly.getBxdddjsmc(),jhly.getBxddqxmc(),jhly.getBxddxxdz(),jhly.getBxddbb(),jhly.getSfks(),jhly.getWyyz(),jhly.getYxbmdm(),jhly.getYxbmmc(),jhly.getKldm(),jhly.getKlmc(),jhly.getJhxzdm(),jhly.getJhxzmc(),jhly.getJhlbdm(),jhly.getJhlbmc(),jhly.getSyssdm(),jhly.getSyssmc(),jhly.getPcdm(),jhly.getPcmc(),jhly.getZklxdm(),jhly.getZklxmc(),jhly.getKskmyq(),jhly.getKskmyqzw(),jhly.getXkkmbhzy(),jhly.getSkyq(),jhly.getZybz(),jhly.getDz1(),jhly.getDz2(),jhly.getDz3(),jhly.getDz4(),jhly.getDz5(),jhly.getDz6(),jhly.getDz7(),jhly.getDz8(),jhly.getDz9(),jhly.getDz10(),jhly.getZsjhs(),jhly.getXfzy(),jhly.getZyxztj());
		
	}

	@Override
	public int update(Jhly jhly) {
		return 0;
	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteByYxdm(String yxdm) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateYxdh() {
		// TODO Auto-generated method stub
		
	}

}
