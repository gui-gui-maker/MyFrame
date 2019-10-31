package com.khnt.rtbox.config.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.khnt.rtbox.config.bean.SpecialCharacter;
import com.khnt.rtbox.config.dao.SpecialCharacterDao;
@SuppressWarnings("unchecked")
@Service("specialCharacterService")
public class SpecialCharacterService extends EntityManageImpl<SpecialCharacter,SpecialCharacterDao>{
	@Autowired
	private SpecialCharacterDao specialCharacterDao;

	public List<SpecialCharacter> getAllData(){
		List<SpecialCharacter> characters = null;
		try{
			characters = this.specialCharacterDao.createQuery("from SpecialCharacter where 1=1").list();
			System.out.println(">>>>>>>>>>>>>>>>>:"+characters);
		}catch(Exception e){
			System.out.println(characters+"---------");
			e.printStackTrace();
		}
		return characters;
	}
}
