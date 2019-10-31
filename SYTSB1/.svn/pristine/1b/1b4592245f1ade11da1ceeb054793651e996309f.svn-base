package com.fwxm.scientific.service;

import java.util.List;

import com.fwxm.scientific.bean.Tjy2ScientificCost;
import com.fwxm.scientific.dao.Tjy2ScientificCostDao;
import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.khnt.pub.fileupload.service.AttachmentManager;
import com.khnt.utils.StringUtil;
import com.lsts.employee.bean.EmployeeCert;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service("tjy2ScientificCost")
public class Tjy2ScientificCostManager extends EntityManageImpl<Tjy2ScientificCost,Tjy2ScientificCostDao>{
    @Autowired
    Tjy2ScientificCostDao tjy2ScientificCostDao;
	@Autowired
	private AttachmentManager attachmentManager;
    // 保存项目费用情况（含附件）
   	public void saveCost(Tjy2ScientificCost entity, String uploadFiles){
   		entity.setStatus("0");
   		tjy2ScientificCostDao.save(entity);
   		if(StringUtil.isNotEmpty(uploadFiles)){
   			String[] files = uploadFiles.replaceAll("/^,/", "").split(",");
   			for(String file : files){
   				if (StringUtil.isNotEmpty(file)) {
   					attachmentManager.setAttachmentBusinessId(file, entity.getId());
   				}
   			}
   		}
   	}
 // 根据基本信息ID查询持证情况
    public List<Tjy2ScientificCost> queryTjy2ScientificCostByBasicId(String id){
    	return tjy2ScientificCostDao.queryTjy2ScientificCostByBasicId(id);
    }
}
