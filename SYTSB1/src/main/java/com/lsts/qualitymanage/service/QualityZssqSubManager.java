package com.lsts.qualitymanage.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.lsts.qualitymanage.bean.QualityZssq;
import com.lsts.qualitymanage.bean.QualityZssqSub;
import com.lsts.qualitymanage.dao.QualityZssqSubDao;


@Service("QualityZssqSubManager")
public class QualityZssqSubManager extends EntityManageImpl<QualityZssqSub,QualityZssqSubDao>{
    @Autowired
    QualityZssqSubDao qualityZssqSubDao;
    /**
     * 获取列表
     * @param id
     * @return
     */
    public List<QualityZssqSub> getQualityZssqSubs(String id) {
    	List<QualityZssqSub> qualityZssqSubs = qualityZssqSubDao.getQualityZssqSubs(id);
		return qualityZssqSubs;
	}
    public boolean checkAllBack(String quality_zssq_fk){
    	boolean isAllBAck=true;
    	List<QualityZssqSub> qualityZssqSubs = qualityZssqSubDao.getQualityZssqSubs(quality_zssq_fk);
    	try {
			for(QualityZssqSub qualityZssqSub:qualityZssqSubs){
				String isback=qualityZssqSub.getIsBack();
				if(isback == null||isback.equals("0")){
					isAllBAck=false;
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return isAllBAck;
    }
}
