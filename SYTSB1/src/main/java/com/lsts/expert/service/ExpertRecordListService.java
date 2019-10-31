package com.lsts.expert.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.lsts.expert.bean.ExpertRecordList;
import com.lsts.expert.dao.ExpertRecordListDao;

@Service("expertRecordListService")
public class ExpertRecordListService extends EntityManageImpl<ExpertRecordList, ExpertRecordListDao> {

	@Autowired
	private ExpertRecordListDao expertRecordListDao;
}
