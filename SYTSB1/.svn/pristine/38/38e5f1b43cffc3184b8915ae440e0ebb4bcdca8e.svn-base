package com.lsts.relevant.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.lsts.relevant.bean.RelevantPeopleCert;
import com.lsts.relevant.dao.RelevantPeopleCertDao;

/**
 * 特种作业人员持证情况业务逻辑对象
 * @ClassName RelevantPeopleCertService
 * @JDK 1.6
 * @author GaoYa
 * @date 2014-02-13 下午04:55:00
 */
@Service("relevantPeopleCertService")
public class RelevantPeopleCertService extends EntityManageImpl<RelevantPeopleCert, RelevantPeopleCertDao> {

	@Autowired
	private RelevantPeopleCertDao relevantPeopleCertDao;

	@Override
	public void save(RelevantPeopleCert entity) throws Exception {
		super.save(entity);
	}
	
	// 根据基本信息ID查询持证情况
    public List<RelevantPeopleCert> queryRelevantPeopleCertByBasicId(String id){
    	return relevantPeopleCertDao.queryRelevantPeopleCertByBasicId(id);
    }
    
    // 删除持证情况
    public void delete(String ids) {
    	relevantPeopleCertDao.delete(ids);
    }
}
