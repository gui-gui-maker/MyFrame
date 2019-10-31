package com.lsts.finance.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository; 

import com.khnt.core.crud.dao.impl.EntityDaoImpl;
import com.khnt.utils.StringUtil;
import com.lsts.finance.bean.CwInvoiceF;
import com.lsts.finance.bean.Fybxd;
import com.lsts.finance.bean.Salary;
import com.lsts.finance.bean.Tjy2Fcp;
/**
 * <p>
 * 
 * </p>
 * 
 * @ClassName Tjy2FcpDao
 * @JDK 1.5
 * @author 
 * @date  
 */
@Repository("Tjy2FcpDao")
public class Tjy2FcpDao extends EntityDaoImpl<Tjy2Fcp> {
	
	@SuppressWarnings("unchecked")
	public List<Tjy2Fcp> getids(String status) {
		List<Tjy2Fcp> list = new ArrayList<Tjy2Fcp>();
		try {
			if (StringUtil.isNotEmpty(status)) {
				String hql="from Tjy2Fcp t  where t.sysId=?";	
				list = this.createQuery(hql, status).list();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
}