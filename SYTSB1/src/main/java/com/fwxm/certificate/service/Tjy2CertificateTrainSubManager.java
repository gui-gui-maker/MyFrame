package com.fwxm.certificate.service;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fwxm.certificate.bean.Tjy2CertificateTrain;
import com.fwxm.certificate.bean.Tjy2CertificateTrainSub;
import com.fwxm.certificate.dao.Tjy2CertificateTrainSubDao;
import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.khnt.utils.StringUtil;


@Service("tjy2CertificateTrainSub")
public class Tjy2CertificateTrainSubManager extends EntityManageImpl<Tjy2CertificateTrainSub,Tjy2CertificateTrainSubDao>{
    @Autowired
    Tjy2CertificateTrainSubDao tjy2CertificateTrainSubDao;
    /**
     * 保存关联信息
     * @param request
     * @param train
     * @return
     * @throws Exception
     */
    public HashMap<String, Object> saveTrainSub(HttpServletRequest request, Tjy2CertificateTrain train) throws Exception{
		String businessId=train.getId();
		String userIds=train.getUserId();
		if(StringUtil.isNotEmpty(businessId)&&StringUtil.isNotEmpty(userIds)){
			tjy2CertificateTrainSubDao.deleteTrainSub(businessId);
			String[] userIdstrs = userIds.split(",");
			for(String userId:userIdstrs){
				Tjy2CertificateTrainSub trainSub = new Tjy2CertificateTrainSub();
				trainSub.setFkCertificateTrainId(businessId);
				trainSub.setFkUserId(userId);
				tjy2CertificateTrainSubDao.save(trainSub);
			}
		}
    	return null;
    	
    }
}
