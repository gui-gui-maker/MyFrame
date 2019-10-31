package com.scts.payment.order.dao;

import org.springframework.stereotype.Repository;

import com.khnt.core.crud.dao.impl.EntityDaoImpl;
import com.scts.payment.order.bean.MachinePayRequest;

/**
 * 智能一体机在线支付请求记录（支付宝和微信）数据处理对象
 * @ClassName MachinePayRequestDao
 * @JDK 1.7
 * @author GaoYa
 * @date 2018-07-12 下午04:05:00
 */
@Repository("machinePayRequestDao")
public class MachinePayRequestDao extends EntityDaoImpl<MachinePayRequest> {

}
