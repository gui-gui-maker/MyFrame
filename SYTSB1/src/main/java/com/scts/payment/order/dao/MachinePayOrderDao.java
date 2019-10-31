package com.scts.payment.order.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.khnt.core.crud.dao.impl.EntityDaoImpl;
import com.khnt.utils.StringUtil;
import com.scts.payment.order.bean.MachinePayOrder;
/**
 * 智能一体机缴费支付单数据处理对象
 * @ClassName MachinePayMainDao
 * @JDK 1.7
 * @author GaoYa
 * @date 2018-06-11 下午05:13:00
 */
@Repository("machinePayOrderDao")
public class MachinePayOrderDao extends EntityDaoImpl<MachinePayOrder> {

	// 根据支付单流水号（订单编号）获取支付单
	public MachinePayOrder queryOrderByOrderNo(String order_no) {
		MachinePayOrder order = new MachinePayOrder();
		String hql = "from MachinePayOrder where order_no=?";
		order = (MachinePayOrder) this.createQuery(hql, order_no).uniqueResult();
		return order;
	}
	
	// 根据支付状态获取支付订单
	@SuppressWarnings("unchecked")
	public List<MachinePayOrder> getOrders(String pay_status) {
		String hql = "from MachinePayOrder i where i.pay_status=?";
		List list = this.createQuery(hql, pay_status).list();
		if (!list.isEmpty()) {
			return list;
		}
		return null;
	}
	
	// 根据报告业务ID获取支付订单信息
	@SuppressWarnings("unchecked")
	public List<MachinePayOrder> queryOrderByInfoIds(String info_id, String pay_status) {
		List<MachinePayOrder> list = new ArrayList<MachinePayOrder>();
		String hql = "from MachinePayOrder i where i.info_ids like ? and i.data_status='0' ";
		if(StringUtil.isNotEmpty(pay_status)) {
			hql += " and i.pay_status='"+pay_status+"'";
		}
		hql += " order by i.pay_start_time asc";
		list = this.createQuery(hql, "%"+info_id+"%").list();
		return list;
	}
	
	/**
	 * 获取缴费单号后2位序号
	 * 
	 * @param order_no_pre --
	 *            缴费单号前缀
	 * @return
	 * @author GaoYa
	 * @date 2018-06-12 下午03:29:00
	 */
	public String queryNextOrderNo(String order_no_pre) {
		String nextNum = "";
		String sql = "select nvl(max(Substr(t.order_no, length('" + order_no_pre
				+ "')+1)), '00') count_num"
				+ " from machine_pay_order t" + " where t.order_no like '"
				+ order_no_pre + "%'";
		List list = this.createSQLQuery(sql).list();
		if (!list.isEmpty()) {
			String curNum = list.get(0) + "";
			if (StringUtil.isNotEmpty(curNum)) {
				nextNum = getCountNum2(Integer.parseInt(curNum) + 1);
			}
		}
		return nextNum;
	}
	
	// 生成2位序号
	private static String getCountNum2(int count_num) {
		String nextNum = "";
		if ((0 < count_num) && (count_num < 10))
			nextNum = "0" + count_num;
		else if (count_num > 10)
			nextNum = String.valueOf(count_num);
		return nextNum;
	}

	// 生成4位序号
	private static String getCountNum4(int count_num) {
		String nextNum = "";
		if ((0 < count_num) && (count_num < 10))
			nextNum = "000" + count_num;
		if ((9 < count_num) && (count_num < 100))
			nextNum = "00" + count_num;
		if ((99 < count_num) && (count_num < 1000))
			nextNum = "0" + count_num;
		else if (count_num > 999)
			nextNum = String.valueOf(count_num);
		return nextNum;
	}
}
