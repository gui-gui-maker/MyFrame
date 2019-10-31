package com.lsts.archives.service;

import java.util.Date;

import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.lsts.archives.bean.ArchivesRz;
import com.lsts.archives.dao.ArchivesRzDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("archivesRzManager")
public class ArchivesRzManager extends EntityManageImpl<ArchivesRz,ArchivesRzDao>{
    @Autowired
    ArchivesRzDao archivesRzDao;
    
    
    /**
     * 借阅申请日志
     * @param archivesType;//档案类型
     * @param identifier;//编号
     * @param applyUnit;//申请部门
     * @param applyUnitId;//申请部门id
     * @param proposer;//申请人
     * @param proposerId;//申请人id
     * @param applyTime;//申请时间
     * @param reportNumber;//报告号
     * @param recordTime;//记录时间
     * @param applicationId;//申请表id
     * */
    public void setJyrz(String archivesType,String identifier,String applyUnit,
    		String applyUnitId,String proposer,String proposerId,
    		Date applyTime,String reportNumber,Date recordTime,String applicationId,String procedures){
    	ArchivesRz archivesRz=new ArchivesRz();
    	
    	archivesRz.setArchivesType(archivesType);
    	archivesRz.setIdentifier(identifier);
    	archivesRz.setApplyUnit(applyUnit);
    	archivesRz.setApplyUnitId(applyUnitId);
    	archivesRz.setProposer(proposer);
    	archivesRz.setProposerId(proposerId);
    	archivesRz.setApplyTime(applyTime);
    	archivesRz.setReportNumber(reportNumber);
    	archivesRz.setRecordTime(recordTime);
    	archivesRz.setApplicationId(applicationId);
    	archivesRz.setProcedures(procedures);
    	archivesRzDao.save(archivesRz);
    }
}
