package com.lsts.archives.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.lsts.archives.bean.ArchivesBorrow;
import com.lsts.archives.bean.ArchivesFile;
import com.lsts.archives.bean.ArchivesYijina;
import com.lsts.archives.dao.ArchivesYijinaDao;
import com.lsts.report.bean.SysOrg;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service("archivesYijinaManager")
public class ArchivesYijinaManager extends EntityManageImpl<ArchivesYijina,ArchivesYijinaDao>{
    @Autowired
    ArchivesYijinaDao archivesYijinaDao;

	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    /**
  	 * 获取申请时间
     * @return 
     * @throws ParseException 
  	 * */
	@SuppressWarnings("rawtypes")
  	public List<ArchivesBorrow> getTime(String nameId) throws ParseException{
		List list=archivesYijinaDao.gettime(nameId);
    	List<ArchivesBorrow> archivesBorrowList=new ArrayList<ArchivesBorrow>();
    	if (list != null && list.size() > 0) {
    		for (int u = 0; u < list.size(); u++) {
    			Object ab[] = null;
    			try {
					
    				ab =  (Object[]) list.get(u);
				} catch (Exception e) {
					e.printStackTrace();
				}
	    		ArchivesBorrow archivesBorrow = new ArchivesBorrow();
	    		archivesBorrow.setProposer(ab[0].toString());
	    		archivesBorrow.setApplyTime(sdf.parse(ab[1].toString()));
	    		archivesBorrow.setRestoreTime(sdf.parse(ab[2].toString()));
	    		archivesBorrowList.add(archivesBorrow);
    		}
    	}
  		return archivesBorrowList;
  		
  	}
	 /**
  	 * 获取编号
     * @return 
     * @throws ParseException 
  	 * */
	@SuppressWarnings("rawtypes")
  	public List<ArchivesFile> getb(String nameId) throws Exception{
		String list=archivesYijinaDao.getReportNumber(nameId).toString();
    	String z = Pattern.compile("\\b([\\w\\W])\\b").matcher(list.toString()
    			.substring(1,list.toString().length()-1)).replaceAll("'$1'"); 
		String[] x=z.split("'-'");
		
		List<ArchivesFile> lists=new ArrayList<ArchivesFile>();
		for (int i = 0; i < x.length; i++) {
			ArchivesFile archivesFile =new ArchivesFile();
			archivesFile.setCertificateNumber(x[0]);
			archivesFile.setChecker(x[1]);
            System.out.println(x[i]);
            lists.add(archivesFile);
        }
		
		return lists;
	}
	/**
  	 * 获取编号
     * @return 
     * @throws ParseException 
  	 * */
  	public void getrq(String Id) throws Exception{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		archivesYijinaDao.getghrq(Id);
	}
	
}
