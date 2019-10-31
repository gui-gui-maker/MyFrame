package com.scts.payment.order.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.scts.payment.order.bean.MachinePayRequest;
import com.scts.payment.order.dao.MachinePayRequestDao;

/**
 * 智能一体机在线支付请求记录（支付宝和微信）业务逻辑对象
 * @ClassName MachinePayRequestService
 * @JDK 1.7
 * @author GaoYa
 * @date 2018-07-12 下午04:04:00
 */
@Service("machinePayRequestService")
public class MachinePayRequestService extends EntityManageImpl<MachinePayRequest, MachinePayRequestDao> {
	@Autowired
	private MachinePayRequestDao machinePayRequestDao;

}
