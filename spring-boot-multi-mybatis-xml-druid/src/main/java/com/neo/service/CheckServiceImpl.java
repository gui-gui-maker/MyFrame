package com.neo.service;

import java.io.File;
//import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.neo.dbf.DBFReader;
import com.neo.dbf.DBFReader.Field;
import com.neo.mapper.plan.FieldDescMapper;
import com.neo.mapper.plan.JhlyMapper;
import com.neo.model.Jhly;
import com.neo.model.Zydh;

@Service
@Transactional
public class CheckServiceImpl implements CheckService {

	@Autowired
	JhlyMapper jhlyMapper;
	@Autowired
	FieldDescMapper fieldDescMapper;

	@Override
	public String check(String yxdm) throws Exception {
		java.lang.reflect.Field[] fs = Jhly.class.getDeclaredFields();

		DBFReader dbfreader_SH = new DBFReader(new File("C://Users//Guido//Desktop//workspace//check-sys//ly5112.dbf"));
		// DBFReader dbfreader_SH = new DBFReader(new File("C://Users//Guido//Desktop//workspace//2019来源计划_dbf//lyjh_dl_dbf_203802201.dbf"));
		Map<String, Field> map = new HashMap<String, Field>();

		Field[] fields = dbfreader_SH.getFields();
		for (int i = 0; i < fields.length; i++) {
			// 1.检查是否是符合规范的数据
			boolean flag = false;
			for (java.lang.reflect.Field f : fs) {
				if (f.getName().equals(fields[i].getName().toLowerCase())) {
					flag = true;
				}
			}
			if (!flag) {
				throw new Exception("上传文件不正确！");
			}
			map.put(fields[i].getName().toLowerCase(), fields[i]);
		}
		//2.删除原表数据
		jhlyMapper.deleteByYxdm(yxdm);
		//3.添加院校数据
		List<DBFReader.Record> list_sh = dbfreader_SH.loadRecords();

		for (DBFReader.Record reader : list_sh) {

			Jhly jhly = new Jhly();

			jhly.setJhid(reader.getString(map.get("jhid")));
			jhly.setNf(reader.getString(map.get("nf")));
			jhly.setYxdm(reader.getString(map.get("yxdm")));
			jhly.setYxmc(reader.getString(map.get("yxmc")));
			jhly.setYxdh(reader.getString(map.get("yxdh")));
			jhly.setYxdhmc(reader.getString(map.get("yxdhmc")));
			jhly.setZszydm(reader.getString(map.get("zszydm")));
			jhly.setZszymc(reader.getString(map.get("zszymc")));
			jhly.setZylbdm(reader.getString(map.get("zylbdm")));
			jhly.setZylbmc(reader.getString(map.get("zylbmc")));
			jhly.setBhzy(reader.getString(map.get("bhzy")));
			jhly.setBhzygs(reader.getString(map.get("bhzygs")));
			jhly.setCcdm(reader.getString(map.get("ccdm")));
			jhly.setCcmc(reader.getString(map.get("ccmc")));
			jhly.setSbzydh(reader.getString(map.get("sbzydh")));
			jhly.setXbzydh(reader.getString(map.get("xbzydh")));
			jhly.setZkfx(reader.getString(map.get("zkfx")));
			jhly.setXzdm(reader.getString(map.get("xzdm")));
			jhly.setXzmc(reader.getString(map.get("xzmc")));
			jhly.setSfbz(reader.getString(map.get("sfbz")));
			jhly.setBxdd(reader.getString(map.get("bxdd")));
			jhly.setBxddssmc(reader.getString(map.get("bxddssmc")));
			jhly.setBxdddjsmc(reader.getString(map.get("bxdddjsmc")));
			jhly.setBxddqxmc(reader.getString(map.get("bxddqxmc")));
			jhly.setBxddxxdz(reader.getString(map.get("bxddxxdz")));
			jhly.setBxddbb(reader.getString(map.get("bxddbb")));
			jhly.setSfks(reader.getString(map.get("sfks")));
			jhly.setWyyz(reader.getString(map.get("wyyz")));
			jhly.setYxbmdm(reader.getString(map.get("yxbmdm")));
			jhly.setYxbmmc(reader.getString(map.get("yxbmmc")));
			jhly.setKldm(reader.getString(map.get("kldm")));
			jhly.setKlmc(reader.getString(map.get("klmc")));
			jhly.setJhxzdm(reader.getString(map.get("jhxzdm")));
			jhly.setJhxzmc(reader.getString(map.get("jhxzmc")));
			jhly.setJhlbdm(reader.getString(map.get("jhlbdm")));
			jhly.setJhlbmc(reader.getString(map.get("jhlbmc")));
			jhly.setSyssdm(reader.getString(map.get("syssdm")));
			jhly.setSyssmc(reader.getString(map.get("syssmc")));
			jhly.setPcdm(reader.getString(map.get("pcdm")));
			jhly.setPcmc(reader.getString(map.get("pcmc")));
			jhly.setZklxdm(reader.getString(map.get("zklxdm")));
			jhly.setZklxmc(reader.getString(map.get("zklxmc")));
			jhly.setKskmyq(reader.getString(map.get("kskmyq")));
			jhly.setKskmyqzw(reader.getString(map.get("kskmyqzw")));
			jhly.setXkkmbhzy(reader.getString(map.get("xkkmbhzy")));
			jhly.setSkyq(reader.getString(map.get("skyq")));
			jhly.setZybz(reader.getString(map.get("zybz")));
			jhly.setDz1(reader.getString(map.get("dz1")));
			jhly.setDz2(reader.getString(map.get("dz2")));
			jhly.setDz3(reader.getString(map.get("dz3")));
			jhly.setDz4(reader.getString(map.get("dz4")));
			jhly.setDz5(reader.getString(map.get("dz5")));
			jhly.setDz6(reader.getString(map.get("dz6")));
			jhly.setDz7(reader.getString(map.get("dz7")));
			jhly.setDz8(reader.getString(map.get("dz8")));
			jhly.setDz9(reader.getString(map.get("dz9")));
			jhly.setDz10(reader.getString(map.get("dz10")));
			jhly.setZsjhs(reader.getInt(map.get("zsjhs")));
			jhly.setXfzy(reader.getString(map.get("xfzy")));
			jhly.setZyxztj(reader.getString(map.get("zyxztj")));

			jhlyMapper.addJhly(jhly);
		}
		//4.处理数据
		//（1）新增院校
		jhlyMapper.updateYxdh();
		//(2)
		return null;
	}

}
