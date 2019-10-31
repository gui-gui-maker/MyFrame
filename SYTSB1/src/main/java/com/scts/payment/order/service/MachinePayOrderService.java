package com.scts.payment.order.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khnt.base.Factory;
import com.khnt.base.SysParaInf;
import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.khnt.utils.DateUtil;
import com.khnt.utils.StringUtil;
import com.scts.payment.order.bean.MachinePayOrder;
import com.scts.payment.order.constant.Constant;
import com.scts.payment.order.dao.MachinePayOrderDao;

/**
 * 智能一体机缴费支付单业务逻辑对象
 * @ClassName MachinePayOrderService
 * @JDK 1.7
 * @author GaoYa
 * @date 2018-06-11 下午05:13:00
 */
@Service("machinePayOrderService")
public class MachinePayOrderService extends EntityManageImpl<MachinePayOrder, MachinePayOrderDao> {
	@Autowired
	private MachinePayOrderDao machinePayOrderDao;
	@Autowired
	private MachinePayInfoService machinePayInfoService;

	public MachinePayOrder queryOrderByOrderNo(String pay_sn) {
		return machinePayOrderDao.queryOrderByOrderNo(pay_sn);
	}
	
	// 根据报告业务ID获取支付订单信息
	public List<MachinePayOrder> queryOrderByInfoIds(String info_ids, String pay_status) {
		return machinePayOrderDao.queryOrderByInfoIds(info_ids, pay_status);
	}

	// 更新支付单信息
	public void updateOrder(MachinePayOrder order) {
		try {
			// 1、更新缴费支付订单信息
			machinePayOrderDao.save(order);			
			// 2、添加支付明细记录
			if (null != order && StringUtil.isNotEmpty(order.getInfo_ids())) {
				machinePayInfoService.addPayInfos(order);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.info("缴费支付单（"+order.getOrder_no()+"）更新失败：" + e.getMessage());
		}
	}
	
	// 获取缴费单号后2位序号
	public String queryNextOrderNo(String order_no_pre) {
		return machinePayOrderDao.queryNextOrderNo(order_no_pre);
	}
	
	/**
	 * 根据缴费支付单ID获取缴费人签名图片
	 * 
	 * @param id -- 缴费支付单ID
	 * 
	 * @throws Exception
	 */
	public HashMap<String, Object> getPaySign(String id) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		// 获取图片
		String rs = "";
		String filePath = machinePayOrderDao.get(id).getSign_file();

		if (StringUtil.isEmpty(filePath)) {
			throw new Exception("未获取到签名图片！");
		}
		StringBuffer sb = new StringBuffer();
		// BufferedReader:从字符输入流中读取文本，缓冲各个字符，从而实现字符、数组和行的高效读取。
		BufferedReader bufReader = null;
		try {
			File file = new File(filePath);
			// FileReader:用来读取字符文件的便捷类。
			bufReader = new BufferedReader(new FileReader(file));
			// buf = new BufferedReader(new InputStreamReader(new
			// FileInputStream(file)));
			String temp = null;
			while ((temp = bufReader.readLine()) != null) {
				sb.append(temp);
			}
			rs = sb.toString();
			map.put("image", rs);
		} catch (Exception e) {
			e.getStackTrace();
		} finally {
			if (bufReader != null) {
				try {
					bufReader.close();
				} catch (IOException e) {
					e.getStackTrace();
				}
			}
		}
		return map;
	}
	
	/**
	 * 根据缴费支付单ID获取缴费人签名图片
	 * 
	 * @param id -- 缴费支付单ID
	 * 
	 * @throws Exception
	 */
	public HashMap<String, Object> getPaySign2(String id) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		// 获取图片
		String signPath = "";
		String fileName = "";
		String filePath = machinePayOrderDao.get(id).getSign_file();

		if (StringUtil.isEmpty(filePath)) {
			throw new Exception("未获取到签名图片！");
		}else{
			fileName = filePath.substring(filePath.indexOf("sign"));
		}
		try {
			// 系统配置参数初始化
			SysParaInf sp = Factory.getSysPara();
			// 获取智能一体机编号
			String signImgPath = sp.getProperty("signImgPath");
			signPath = signImgPath + File.separator + fileName;
			map.put("imagePath", signPath);
		} catch (Exception e) {
			e.getStackTrace();
		}
		return map;
	}
	
	/**
	 * 智能一体机之超时未支付订单处理任务
	 * 
	 * 将所有智能一体机上超时未支付的订单进行作废处理
	 * 
	 * @param request
	 * 
	 * @return
	 * @author GaoYa
	 * @date 2018-07-11 下午04:10:00
	 */
	public void doCancelPayOrder() {
		try {
			// 1、获取智能一体机上预支付订单业务
			List<MachinePayOrder> orderList = machinePayOrderDao.getOrders(Constant.PAY_STATUS_UNPAY);
			for (MachinePayOrder machinePayOrder : orderList) {
				if (!"98".equals(machinePayOrder.getData_status())) {
					Date cur_time = DateUtil.convertStringToDate(Constant.ymdhmsDatePattern,
							DateUtil.getCurrentDateTime());
					Date pay_end_time = DateUtil.convertStringToDate(Constant.ymdhmsDatePattern,
							DateUtil.format(machinePayOrder.getPay_end_time(), Constant.ymdhmsDatePattern));
					if (pay_end_time.before(cur_time)) {
						machinePayOrder.setData_status("99");
						machinePayOrderDao.save(machinePayOrder);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 查询在线支付请求记录
	 * @param order_no
	 * @return
	 */
	public List<Map<String, Object>> queryMachinePayRequest(String order_no){
		String sql="select * from machine_pay_request where  order_no=? ";
		Query query=machinePayOrderDao.createSQLQuery(sql, order_no).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		List<Map<String, Object>> list=query.list();
		return list;
	}
}
