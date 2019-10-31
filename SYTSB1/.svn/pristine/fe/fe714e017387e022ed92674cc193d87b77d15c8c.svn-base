package com.lsts.archives.service;

import java.util.Date;

import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.lsts.archives.bean.ArchivesPrintDyrz;
import com.lsts.archives.dao.ArchivesPrintDyrzDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service("archivesPrintDyrzManager")
public class ArchivesPrintDyrzManager extends EntityManageImpl<ArchivesPrintDyrz,ArchivesPrintDyrzDao>{
    @Autowired
    ArchivesPrintDyrzDao archivesPrintDyrzDao;
    
    /**
     * 记录打印日志
     * @param documentId 报告编号
     * @param identifier 申请表id
     * @param printMan 打印人
     * @param printManId 打印人id
     * @param printTime 打印时间
     * @param applyReason 申请原因
     * @param applyUnit;//申请部门
     * @param applyUnitId;//申请部门id
     * @param proposer;//申请人
     * @param proposerId;//申请人id
     * @param applyTime;//申请时间
     * @param applicationId;//申请表id
     * */
    public void setDyrz(String documentId,String identifier,String printMan,
    		String printManId,Date printTime,String applyReason,String applyUnit,
    		String applyUnitId,String proposer,String proposerId,Date applyTime,
    		String applicationId){
    	ArchivesPrintDyrz archivesPrintDyrz=new ArchivesPrintDyrz();
    	
    	archivesPrintDyrz.setDocumentId(documentId);
    	archivesPrintDyrz.setIdentifier(identifier);
    	archivesPrintDyrz.setPrintMan(printMan);
    	archivesPrintDyrz.setPrintManId(printManId);
    	archivesPrintDyrz.setPrintTime(printTime);
    	archivesPrintDyrz.setApplyReason(applyReason);
    	archivesPrintDyrz.setApplyUnit(applyUnit);
    	archivesPrintDyrz.setApplyUnitId(applyUnitId);
    	archivesPrintDyrz.setProposer(proposer);
    	archivesPrintDyrz.setProposerId(proposerId);
    	archivesPrintDyrz.setApplyTime(applyTime);
    	archivesPrintDyrz.setApplicationId(applicationId);
    	
    	archivesPrintDyrzDao.save(archivesPrintDyrz);
    }
}
