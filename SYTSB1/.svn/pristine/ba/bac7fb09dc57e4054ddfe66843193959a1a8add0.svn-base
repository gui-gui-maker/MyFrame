package com.lsts.office.service;


import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.khnt.core.exception.KhntException;
import com.khnt.security.CurrentSessionUser;
import com.lsts.office.bean.WeightyDep;
import com.lsts.office.dao.WeightyDepDao;
import com.lsts.office.dao.WeightyTaskDao;


@Service("WeightyDepManager")
public class WeightyDepManager extends EntityManageImpl<WeightyDep,WeightyDepDao>{
    @Autowired
    WeightyDepDao weightyDepDao;
    @Autowired
    WeightyTaskDao weightyTaskDao;
    /**
     * 保存信息 并遍历重要任务信息获得ID
     * @param weightyDep
     * @param ids
     */
    @SuppressWarnings({ "rawtypes" })
	public void saveWei(WeightyDep weightyDep,CurrentSessionUser user,String id) throws KhntException {
    	if(weightyDep.getPhDepId() == null || weightyDep.getPhDepId().equals("")){
	    	if(weightyDep.getPhDepName() == null || weightyDep.getPhDepName().equals("") ){
	    		throw new KhntException("配合部门为空,请重新选择");
	    	}
    	}else{
    		if(id==null){
    			id = weightyDep.getWeightyTask().getId();
    		}
    		//获取主表里是否存在配合部门,如存在责判断是否为重复
    		List list = weightyDepDao.ExamineDep(id, weightyDep.getPhDepName());
    		BigDecimal scount = (BigDecimal) list.get(0);
    		if(scount.intValue() <= 0){
    				weightyDepDao.save(weightyDep);
    		}else {
				throw new KhntException("部门已存在,请重新选择");
			}
    	}
    }
    /**
     * 删除
     * @param ids
     */
    public void delete(String ids , WeightyDep weightyDep ){
    	for(String id: ids.split(",")){
    		weightyDep = weightyDepDao.get(ids);
    		weightyDepDao.getDelete(id);;
    	}
    }
    
    public List<WeightyDep> getList(String onetompid) throws Exception{
    	return weightyDepDao.findBy("weightyTask.id", onetompid);
    }
    
    
}
