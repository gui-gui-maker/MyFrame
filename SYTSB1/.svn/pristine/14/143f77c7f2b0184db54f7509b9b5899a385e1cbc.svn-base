package com.fwxm.certificate.service;

import com.fwxm.certificate.bean.Tjy2CertificateTrain;
import com.fwxm.certificate.dao.Tjy2CertificateTrainDao;
import com.fwxm.certificate.dao.Tjy2CertificateTrainSubDao;
import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.khnt.utils.StringUtil;
import com.lsts.equipment2.bean.PpeLoan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("tjy2CertificateTrain")
public class Tjy2CertificateTrainManager extends EntityManageImpl<Tjy2CertificateTrain,Tjy2CertificateTrainDao>{
    @Autowired
    Tjy2CertificateTrainDao tjy2CertificateTrainDao;
    @Autowired
    Tjy2CertificateTrainSubDao tjy2CertificateTrainSubDao;
    /**
     * 删除借用信息及相关联资产信息
     * @param ids
     * @throws Exception
     */
    public void deleteTrain(String ids)throws Exception{
    	if(StringUtil.isNotEmpty(ids)){
    		String[] idstrs = ids.split(",");
    		for(String id:idstrs){
    			Tjy2CertificateTrain train = new Tjy2CertificateTrain();
    			tjy2CertificateTrainDao.deleteTrain(id);
            	tjy2CertificateTrainSubDao.deleteTrainSub(id);;
    		}
    	}
      }
}
