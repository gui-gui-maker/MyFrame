package com.guido.web;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.guido.model.BaseJh;
import com.guido.repository.impl.BaseJhRepositoryImpl;
import com.guido.util.dbf.DBFReader;
import com.guido.util.dbf.DBFReader.Field;

@Controller
@RequestMapping("baseJh")
public class BaseJhWeb {
	@Autowired
	BaseJhRepositoryImpl baseJhRepository;

	@ResponseBody
	@RequestMapping(value="uploadDbf")
	public HashMap<String, Object> uploadDbf() {
		HashMap<String, Object> wapper = new HashMap<>();
		DBFReader dbfreader_SH = null;
		try {
			//
			dbfreader_SH = new DBFReader(new File("C://Users//Guido//Desktop//xxx.DBF"));
			Map<String, Field> map = new HashMap<String, Field>();

			Field[] fields = dbfreader_SH.getFields();
			for (int i = 0; i < fields.length; i++) {
				map.put(fields[i].getName().toLowerCase(), fields[i]);
			}

			List<DBFReader.Record> list_sh = dbfreader_SH.loadRecords();
			List<BaseJh> jhs = new ArrayList<BaseJh>();
			int ind = 0;
			for (DBFReader.Record reader : list_sh) {
				ind++;
				BaseJh jh = new BaseJh();

				jh.setJhid(reader.getString(map.get("jhid")));
				jh.setYxdm(reader.getString(map.get("yxdm")));
				jh.setYxmc(reader.getString(map.get("yxmc")));
				jh.setYxdh(reader.getString(map.get("yxdh")));
				jh.setYxdhmc(reader.getString(map.get("yxdhmc")));
				jh.setZszymc(reader.getString(map.get("zszymc")));
				jh.setZylbdm(reader.getString(map.get("zylbdm")));
				jh.setBhzy(reader.getString(map.get("bhzy")));
				jh.setBhzygs(reader.getString(map.get("bhzygs")));
				jh.setCcdm(reader.getString(map.get("ccdm")));
				jh.setSbzydh(reader.getString(map.get("sbzydh")));
				jh.setXbzydh(reader.getString(map.get("xbzydh")));
				jh.setZkfx(reader.getString(map.get("zkfx")));
				jh.setXzdm(reader.getString(map.get("xzdm")));
				jh.setSfbz(reader.getString(map.get("sfbz")));
				jh.setBxdd(reader.getString(map.get("bxdd")));
				jh.setBxddssmc(reader.getString(map.get("bxddssmc")));
				jh.setBxdddjsmc(reader.getString(map.get("bxdddjsmc")));
				jh.setBxddqxmc(reader.getString(map.get("bxddqxmc")));
				jh.setBxddxxdz(reader.getString(map.get("bxddxxdz")));
				jh.setBxddbb(reader.getString(map.get("bxddbb")));
				jh.setSfks(reader.getString(map.get("sfks")));
				jh.setWyyz(reader.getString(map.get("wyyz")));
				jh.setKldm(reader.getString(map.get("kldm")));
				jh.setJhxzdm(reader.getString(map.get("jhxzdm")));
				jh.setJhlbdm(reader.getString(map.get("jhlbdm")));
				jh.setPcdm(reader.getString(map.get("pcdm")));
				jh.setZklxdm(reader.getString(map.get("zklxdm")));
				jh.setZklxmc(reader.getString(map.get("zklxmc")));
				jh.setKskmyq(reader.getString(map.get("kskmyq")));
				jh.setKskmyqzw(reader.getString(map.get("kskmyqzw")));
				jh.setXkkmbhzy(reader.getString(map.get("xkkmbhzy")));
				jh.setZybz(reader.getString(map.get("zybz")));
				jh.setDz1(reader.getString(map.get("dz1")));
				jh.setDz2(reader.getString(map.get("dz2")));
				jh.setDz3(reader.getString(map.get("dz3")));
				jh.setDz4(reader.getString(map.get("dz4")));
				jh.setDz5(reader.getString(map.get("dz5")));
				jh.setDz6(reader.getString(map.get("dz6")));
				//元数据没有此列
				jh.setDyml(reader.getString(map.get("dyml")));
				jh.setDz8(reader.getString(map.get("dz8")));
				jh.setDz9(reader.getString(map.get("dz9")));
				System.out.println(ind);
				jh.setZsjhs(reader.getInt(map.get("zsjhs")));

				jhs.add(jh);
				
			}
			baseJhRepository.batchCreateArticle(jhs);
			wapper.put("success", true);
			wapper.put("msg", "数据上传成功");
		} catch (Exception e) {
			e.printStackTrace();
			wapper.put("success", false);
			wapper.put("msg", e.getMessage());
		}finally {
			
			try {
				dbfreader_SH.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				dbfreader_SH = null;
			}
		}
		return wapper;
	}
	@PostMapping("jhAll")
	public HashMap<String,Object> jhAll(int size,int page) {
		HashMap<String,Object> map =new HashMap<String,Object>();
		//map.put("data",baseJhRepository.page(size,page));
		return map;
	}
}
