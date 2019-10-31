package com.lsts.equipment2.service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.lsts.equipment2.bean.EquipPpe;
import com.lsts.equipment2.bean.PpeLoan;
import com.lsts.equipment2.bean.PpeLoanSub;
import com.lsts.equipment2.dao.EquipPpeDao;
import com.lsts.equipment2.dao.PpeLoanSubDao;
import com.lsts.humanresources.bean.WorktitleRecord;


@Service("PpeLoanSubManager")
public class PpeLoanSubManager extends EntityManageImpl<PpeLoanSub,PpeLoanSubDao>{
    @Autowired
    PpeLoanSubDao ppeLoanSubDao;
    @Autowired
    EquipPpeDao equipPpeDao;
    /**
     * 根据登记表ID获取资产信息
     * @param loanFk
     * @return
     * @throws Exception
     */
   	public List<PpeLoanSub> getPpeLoanSubList(String loanFk) throws Exception {
   		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); 
   		List<?> list=ppeLoanSubDao.getPpeLoanSubList(loanFk);//数据库里面获取的数据
   		List<PpeLoanSub> ppeLoanSubs=new ArrayList<PpeLoanSub>();
   		
   		for(int j=0;j<list.size();j++){//循环获取资产信息,并add到PpeLoanSubs中
			Object[] obj = (Object[]) list.get(j);
			PpeLoanSub ppeLoanSub = new PpeLoanSub();
			ppeLoanSub.setId(obj[0]!=null?obj[0].toString():null);
			ppeLoanSub.setLoanFk(obj[1]!=null?obj[1].toString():null);
			ppeLoanSub.setPpeFk(obj[2]!=null?obj[2].toString():null);
			ppeLoanSub.setSelfNo(obj[3]!=null?obj[3].toString():null);
			ppeLoanSub.setAssetName(obj[4]!=null?obj[4].toString():null);
			ppeLoanSub.setSpaciModel(obj[5]!=null?obj[5].toString():null);
			ppeLoanSub.setSn(obj[6]!=null?obj[6].toString():null);
			ppeLoanSub.setNumbers(obj[7]!=null?new BigDecimal(obj[7].toString()):null);
			ppeLoanSub.setUnit(obj[8]!=null?obj[8].toString():null);
			ppeLoanSub.setRemark(obj[9]!=null?obj[9].toString():null);
			ppeLoanSub.setStatus(obj[10]!=null?obj[10].toString():null);
			ppeLoanSub.setBackDate(obj[11]!=null?format.parse(obj[11].toString()):null);
			ppeLoanSub.setPreBackDate(obj[12]!=null?format.parse(obj[12].toString()):null);
			ppeLoanSubs.add(ppeLoanSub);
		}
   		return ppeLoanSubs;
   	}
   	/**
   	 * 修改资产状态为已归还并保存
   	 * @param ppeLoanSubs
   	 * @return
   	 */
   	public void saveBack(PpeLoan ppeLoan){
   		List<PpeLoanSub> ppeLoanSubs = ppeLoan.getPpeLoanSubs();
   		for(PpeLoanSub ppeLoanSub:ppeLoanSubs){
   			//PpeLoanSub ppeLoanSub1 = ppeLoanSubDao.get(ppeLoanSub.getId());
   			
   			//归还时反写资产信息表中资产状态为未借用
   			EquipPpe equipPpe=equipPpeDao.get(ppeLoanSub.getPpeFk());
			equipPpe.setLoanStatus("0");
			equipPpeDao.save(equipPpe);
			
   			//ppeLoanSub1.setBackDate(ppeLoan.getBackDate());
   			ppeLoanSub.setStatus("2");//状态改为已归还
   			ppeLoanSubDao.save(ppeLoanSub);
   		}
   	}
   	/**
   	 * 检查此登记表资产是否全部归还
   	 * @param loanFk
   	 * @return
   	 */
   	public Boolean checkBackAll(String loanFk){
   		boolean allBack=true;
   		List<?> list=ppeLoanSubDao.checkBackAll(loanFk);
   		String str = list.toString();
   		if(str.contains("1")){
   			allBack=false;
   		}
		return allBack;
   	}
}
