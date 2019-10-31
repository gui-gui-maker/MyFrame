package com.khnt.rtbox.config.dao;

import org.springframework.stereotype.Repository;

import com.khnt.core.crud.dao.impl.EntityDaoImpl;
import com.khnt.rtbox.config.bean.SpecialCharacter;

@Repository("specialCharacterDao")
public class SpecialCharacterDao extends EntityDaoImpl<SpecialCharacter> {

}
