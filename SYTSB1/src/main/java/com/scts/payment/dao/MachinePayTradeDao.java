package com.scts.payment.dao;

import org.springframework.stereotype.Repository;

import com.khnt.core.crud.dao.impl.EntityDaoImpl;
import com.scts.payment.bean.MachinePayTrade;

/**
 * 智能一体机在线支付交易记录（支付宝和微信）数据处理对象
 * @ClassName MachinePayTrade
 * @JDK 1.7
 * @author GaoYa
 * @date 2018-07-06 下午02:35:00
 */
@Repository("machinePayTradeDao")
public class MachinePayTradeDao extends EntityDaoImpl<MachinePayTrade> {

	// 根据订单号、交易号获取交易记录
	public MachinePayTrade queryTrade(String order_no, String trade_no) {
		String hql = "from MachinePayTrade where order_no=? and trade_no=?";
		MachinePayTrade trade = (MachinePayTrade) this.createQuery(hql, order_no, trade_no).uniqueResult();
		return trade;
	}
}
