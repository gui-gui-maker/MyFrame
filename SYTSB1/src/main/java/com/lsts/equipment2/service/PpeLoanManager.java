package com.lsts.equipment2.service;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.khnt.utils.StringUtil;
import com.lsts.equipment2.bean.EquipPpe;
import com.lsts.equipment2.bean.PpeLoan;
import com.lsts.equipment2.bean.PpeLoanSub;
import com.lsts.equipment2.dao.EquipPpeDao;
import com.lsts.equipment2.dao.PpeLoanDao;
import com.lsts.equipment2.dao.PpeLoanSubDao;


@Service("PpeLoanManager")
public class PpeLoanManager extends EntityManageImpl<PpeLoan,PpeLoanDao>{
    @Autowired
    PpeLoanDao ppeLoanDao;
    @Autowired
    PpeLoanSubDao ppeLoanSubDao;
    @Autowired
    EquipPpeDao equipPpeDao;
    /**
     * 保存借用信息及相关联资产信息
     * @param ppeLoan
     * @param ppeLoanSubs
     * @return
     */
    public HashMap<String, Object> saveAll(PpeLoan ppeLoan,List<PpeLoanSub> ppeLoanSubs){
    	String fkPpeId="";
		String ppeSelfNo="";
    	for(PpeLoanSub ppeLoanSub : ppeLoanSubs){
    		fkPpeId+=ppeLoanSub.getPpeFk()+",";
    		ppeSelfNo+=ppeLoanSub.getSelfNo()+",";
    	}
    	if(StringUtil.isNotEmpty(fkPpeId)){
    		fkPpeId=fkPpeId.trim().substring(0, fkPpeId.length()-1);
    	}
    	if(StringUtil.isNotEmpty(ppeSelfNo)){
    		ppeSelfNo=ppeSelfNo.trim().substring(0, ppeSelfNo.length()-1);
    	}
    	ppeLoan.setFkPpeId(fkPpeId);
    	ppeLoan.setPpeSelfNo(ppeSelfNo);
    	ppeLoanDao.save(ppeLoan);
    	String ppeLoanId = ppeLoan.getId();
    	try {
    		ppeLoanSubDao.recoverPpeInfo(ppeLoanId);//恢复判断资产表中的借用状态
			ppeLoanSubDao.deletePpeLoanSub(ppeLoanId);//删除子表中所有与此登记表相关联的资产
			System.out.println("ppeLoanSubs had been deleted successfully when modified ppeLoan!");
			for(int i=0;i<ppeLoanSubs.size();i++){
	    		PpeLoanSub ppeLoanSub = ppeLoanSubs.get(i);
	    		if(ppeLoan.getStatus().equals("YJC")){//当为已借出时反写资产信息表中资产状态为已借用
	    			ppeLoanSub.setStatus("1");//借用子表资产状态改为未归还
	    			EquipPpe equipPpe=equipPpeDao.get(ppeLoanSub.getPpeFk());
	    			equipPpe.setLoanStatus("1");
	    			equipPpeDao.save(equipPpe);
	    		}
	    		ppeLoanSub.setId(null);
	    		ppeLoanSub.setLoanFk(ppeLoanId);
	    		try {
					ppeLoanSubDao.save(ppeLoanSub);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    	}
    	} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
    }
    /**
     * 删除借用信息及相关联资产信息
     * @param ids
     * @throws Exception
     */
    public void delete(String id)throws Exception{
    	PpeLoan ppeLoan=ppeLoanDao.get(id);
  		ppeLoanDao.getHibernateTemplate().delete(ppeLoan);
  		ppeLoanSubDao.deletePpeLoanSub(id);
      }
    /**
     * 生成新的登记表编号
     * @param ppeLoan
     * @return
     */
    public String initNum(PpeLoan ppeLoan){
    	String identifier = ppeLoan.getIdentifier();
    	//新增保存时，生成新编号，修改功能不需要重新生成编号
		if(null==ppeLoan.getId() || "".equals(ppeLoan.getId())){
			Calendar a=Calendar.getInstance();
			int nowYear = a.get(Calendar.YEAR);
			List<PpeLoan> ppeLoanlist = ppeLoanDao.getTaskAllot();
	 		if(ppeLoanlist==null || ppeLoanlist.size()==0) {
	 			identifier = nowYear+"-"+"0001";
	 		} else {
	 			int[] identifierArray = new int[ppeLoanlist.size()];
	 			for (int i=0;i<ppeLoanlist.size();i++) {
	 				//将编号去掉“-”，转换成int型存入数组
	 				if(ppeLoanlist.get(i).getIdentifier()!=null && !"".equals(ppeLoanlist.get(i).getIdentifier())){
	 				identifierArray[i] = Integer.parseInt(ppeLoanlist.get(i).getIdentifier().replaceAll("-", ""));
	 				}
	 			}
	 			int max = identifierArray[0];
	 			//获取数组中最大的值
	 			for (int i : identifierArray) {
	 				max = max>i?max:i;
	 			}
	 			String identifier1 = String.valueOf(max+1);
	 			if(nowYear>Integer.parseInt(identifier1.substring(0, 4))) {
	 				identifier = nowYear+"-"+"0001";
	 	 		}else{
	 	 			//编号加1后重新组
	 	 			identifier = identifier1.substring(0, 4)+"-"+identifier1.substring(4, 8);
	 	 		}
	 		}
		}
		return identifier;
    }
}
