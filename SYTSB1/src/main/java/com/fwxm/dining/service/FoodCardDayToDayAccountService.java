package com.fwxm.dining.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fwxm.dining.bean.FoodCardDayToDayAccount;
import com.fwxm.dining.dao.FoodCardDayToDayAccountDao;
import com.khnt.core.crud.manager.impl.EntityManageImpl;


@Service("foodCardDayToDayAccountService")
public class FoodCardDayToDayAccountService extends EntityManageImpl<FoodCardDayToDayAccount, FoodCardDayToDayAccountDao>{
	@Autowired
	private FoodCardDayToDayAccountDao foodCardDayToDayAccountDao;
}
