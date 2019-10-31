package com.lsts.humanresources.service;

import java.util.ArrayList;
import java.util.List;

import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.lsts.humanresources.bean.WorkExperience;
import com.lsts.humanresources.dao.WorkExperienceDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service("WorkExperience")
public class WorkExperienceManager extends EntityManageImpl<WorkExperience,WorkExperienceDao>{
    @Autowired
    WorkExperienceDao WorkExperienceDao;
    public List<WorkExperience> getByEmpId(String id){
    	List<WorkExperience> list =new ArrayList<WorkExperience>();
    	String hql="from WorkExperience where FK_RL_EMPLPYEE_ID=?";
    	list=WorkExperienceDao.createQuery(hql, id).list();
    	return list;
    }
    public void delByEmpId(String id){
    	List<WorkExperience> list =new ArrayList<WorkExperience>();
    	String hql="delete from WorkExperience where FK_RL_EMPLPYEE_ID=?";
    	WorkExperienceDao.createQuery(hql, id).executeUpdate();
    }
}
