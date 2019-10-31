package com.lsts.finance.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.lsts.finance.bean.CwBankDTO;
import com.lsts.finance.bean.CwBankHistory;
import com.lsts.finance.dao.CwBankHistoryDao;


/**
 * 银行转账历史记录业务逻辑对象
 * @ClassName CwBankHistoryManager
 * @JDK 1.7
 * @author GaoYa
 * @date 2015-11-16 下午03:17:00
 */
@Service("cwBankHistoryManager")
public class CwBankHistoryManager extends EntityManageImpl<CwBankHistory,CwBankHistoryDao>{
    @Autowired
    CwBankHistoryDao cwBankHistoryDao;

    /**
	 * 根据银行转账记录id获取转账历史记录
	 * @param fk_cw_bank_id -- 银行转账记录id
	 * @return 
	 * @author GaoYa
	 * @date 2015-11-16 下午02:52:00
	 */
	@SuppressWarnings("unchecked")
	public CwBankDTO queryByCwBankId(String fk_cw_bank_id){
		return cwBankHistoryDao.queryByCwBankId(fk_cw_bank_id);
	}
}
