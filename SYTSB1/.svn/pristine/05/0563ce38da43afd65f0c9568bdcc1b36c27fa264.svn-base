package com.scts.payment.order.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.khnt.utils.DateUtil;
import com.khnt.utils.StringUtil;
import com.lsts.inspection.bean.InspectionInfo;
import com.lsts.inspection.dao.InspectionInfoDao;
import com.scts.payment.order.bean.MachinePayInfo;
import com.scts.payment.order.bean.MachinePayOrder;
import com.scts.payment.order.constant.Constant;
import com.scts.payment.order.dao.MachinePayInfoDao;

/**
 * 智能一体机缴费支付单明细业务逻辑对象
 * @ClassName MachinePayInfoService
 * @JDK 1.7
 * @author GaoYa
 * @date 2018-06-11 下午05:13:00
 */
@Service("machinePayInfoService")
public class MachinePayInfoService extends EntityManageImpl<MachinePayInfo, MachinePayInfoDao> {
	@Autowired
	private MachinePayInfoDao machinePayInfoDao;
	@Autowired
	private InspectionInfoDao inspectionInfoDao;

	// 添加支付明细信息
	public void addPayInfos(MachinePayOrder order) {
		try {
			String[] info_ids = order.getInfo_ids().split(",");
			for (int i = 0; i < info_ids.length; i++) {
				InspectionInfo inspectionInfo = inspectionInfoDao.get(info_ids[i]);
				if (null != inspectionInfo && StringUtil.isNotEmpty(inspectionInfo.getReport_sn())) {
					// 1、更新报告收费信息
					if(Constant.PAY_TYPE_ALIPAY.equals(order.getPay_type())) {
						inspectionInfo.setAdvance_type("3"); 		// 收费类型（0 正常收费 1 协议收费 2 免收 3 支付宝支付 4 微信支付）
					}else if(Constant.PAY_TYPE_WEIXIN.equals(order.getPay_type())) {
						inspectionInfo.setAdvance_type("4"); 		
					}
					inspectionInfoDao.save(inspectionInfo);
					
					// 2、保存支付记录
					MachinePayInfo payInfo = new MachinePayInfo();
					payInfo.setInfo_id(info_ids[i]);
					payInfo.setReport_sn(inspectionInfo.getReport_sn());
					payInfo.setPay_device_type(order.getPay_device_type());
					payInfo.setPhone_num(order.getPhone_num());
					payInfo.setCom_name(order.getCom_name());
					payInfo.setPay_money(inspectionInfo.getAdvance_fees());
					payInfo.setPay_type(StringUtil.isNotEmpty(order.getPay_type()) ? order.getPay_type()
							: Constant.PAY_TYPE_ALIPAY);
					payInfo.setPay_time(order.getPay_time());
					payInfo.setPay_status(StringUtil.isNotEmpty(order.getPay_status()) ? order.getPay_status()
							: Constant.PAY_STATUS_PAYED);
					payInfo.setMachine_num(order.getMachine_num());
					payInfo.setOp_ip(order.getOp_ip());
					payInfo.setMachinePayOrder(order);
					machinePayInfoDao.save(payInfo);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
