package com.lsts.humanresources.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.lsts.humanresources.bean.WorktitleRecord;
import com.lsts.humanresources.dao.WorktitleRecordDao;


@Service("WorktitleRecordManager")
public class WorktitleRecordManager extends EntityManageImpl<WorktitleRecord,WorktitleRecordDao>{
    @Autowired
    WorktitleRecordDao worktitleRecordDao;
  //通过员工ID查询员工职务记录
  	public List<WorktitleRecord> getWorkTitles(String empId) throws Exception {
  		return worktitleRecordDao.getWorkTitles(empId);
  	}
  	//通过员工ID查询员工当前职务
  	public WorktitleRecord getWorkTitle(String empId) throws Exception {
  		return worktitleRecordDao.getWorkTitle(empId);
  	}
}
