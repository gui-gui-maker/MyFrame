package com.lsts.finance.dao;


import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.khnt.core.crud.dao.impl.EntityDaoImpl;
import com.khnt.utils.StringUtil;
import com.lsts.finance.bean.CwBankDTO;
import com.lsts.finance.bean.CwBankHistory;



/**
 * 银行转账历史记录数据处理对象
 * @ClassName CwBankHistoryDao
 * @JDK 1.7
 * @author GaoYa
 * @date 2015-11-16 下午02:48:00
 */
@Repository("cwBankHistoryDao")
public class CwBankHistoryDao extends EntityDaoImpl<CwBankHistory> {

	/**
	 * 根据银行转账记录id获取转账历史记录
	 * @param fk_cw_bank_id -- 银行转账记录id
	 * @return 
	 * @author GaoYa
	 * @date 2015-11-16 下午02:52:00
	 */
	@SuppressWarnings("unchecked")
	public CwBankDTO queryByCwBankId(String fk_cw_bank_id){
		CwBankDTO cwBankDTO = null;
    	try {
    		if (StringUtil.isNotEmpty(fk_cw_bank_id)) {
    			String hql = "select t.last_remainMoney,t.last_usedMoney,t.fk_cw_bank_id from TJY2_CW_BANK_HISTORY t where t.fk_cw_bank_id=? order by t.last_mdy_date desc";
    			List list = this.createSQLQuery(hql, fk_cw_bank_id).list();
    			if (!list.isEmpty()) {
    				for (int i = 0; i < list.size(); i++) {
    					Object[] objArr = list.toArray();
    					Object[] obj = (Object[]) objArr[i];
    					cwBankDTO = new CwBankDTO();
    					cwBankDTO.setRestMoney(BigDecimal.valueOf(Double.valueOf(obj[0].toString())));
    					cwBankDTO.setUsedMoney(BigDecimal.valueOf(Double.valueOf(obj[1].toString())));
    					cwBankDTO.setId(obj[2].toString());
    					break;
    				}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return cwBankDTO;
	}
}
