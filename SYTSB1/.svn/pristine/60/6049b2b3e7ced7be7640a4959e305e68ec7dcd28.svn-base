package com.lsts.equipment2.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.khnt.core.crud.web.SpringSupportAction;
import com.khnt.core.crud.web.support.JsonWrapper;
import com.khnt.security.CurrentSessionUser;
import com.khnt.utils.StringUtil;
import com.lsts.equipment2.bean.Equipment;
import com.lsts.equipment2.bean.EquipmentBuy;
import com.lsts.equipment2.bean.EquipmentBuyRelation;
import com.lsts.equipment2.service.EquipmentBuyRelationService;
import com.lsts.equipment2.service.EquipmentBuyService;
import com.lsts.equipment2.service.EquipmentManager;

/**
 * 设备申请关联控制器
 * 
 * @ClassName EquipmentBuyRelationAction
 * @JDK 1.7
 * @author GaoYa
 * @date 2014-02-18 下午03:31:00
 */
@Controller
@RequestMapping("equipmentBuyRelationAction")
public class EquipmentBuyRelationAction
		extends
		SpringSupportAction<EquipmentBuyRelation, EquipmentBuyRelationService> {
	@Autowired
	private EquipmentBuyRelationService baseEquipment2ApplyRelationService;
	@Autowired
	private EquipmentManager baseEquipmentService;
	@Autowired
	private EquipmentBuyService baseEquipmentApplyService;

	@RequestMapping(value = "getList")
	@ResponseBody
	public HashMap<String, Object> getList(HttpServletRequest request, String id) {
		HashMap<String, Object> wrapper = new HashMap<String, Object>();
		List<Map> datalist = new ArrayList<Map>();
		try {
			if (StringUtil.isNotEmpty(id)) {
				List<EquipmentBuyRelation> list = baseEquipment2ApplyRelationService
						.queryBaseEquipmentApplyRelationByApplyId(id);
				if (!list.isEmpty()) {
					for (EquipmentBuyRelation baseEquipmentApplyRelation : list) {
						/*Map map = baseEquipmentApplyRelation.to_Map();
						datalist.add(map);*/
					}
				}
				wrapper.put("success", true);
				wrapper.put("datalist", datalist);
			}
		} catch (Exception e) {
			e.printStackTrace();
			wrapper.put("error", true);
		}
		return wrapper;
	}

	/**
	 * 保存
	 * 
	 * @param request
	 * @param baseEquipmentApplyRelation
	 * @throws Exception
	 *//*
	@RequestMapping(value = "save")
	@ResponseBody
	public HashMap<String, Object> save(HttpServletRequest request,
			BaseEquipment2ApplyRelation baseEquipmentApplyRelation)
			throws Exception {
		String status = request.getParameter("status");
		try {
			CurrentSessionUser curUser = this.getCurrentUser(); // 获取当前用户登录信息
			if ("add".equals(status)) {
				baseEquipmentApplyRelation.setCreate_by(curUser.getName());
				baseEquipmentApplyRelation.setCreate_date(new Date());
			}
			baseEquipmentApplyRelation.setLast_modify_by(curUser.getName());
			baseEquipmentApplyRelation.setLast_modify_date(new Date());

			BaseEquipment2Apply baseEquipment2Apply = baseEquipmentApplyService
					.get(baseEquipmentApplyRelation.getBaseEquipment2Apply()
							.getId()); // 获取申请信息
			BaseEquipment2 baseEquipment = baseEquipmentApplyRelation
					.getBaseEquipment2(); // 获取设备信息
			if ("08".equals(baseEquipment.getEq_type())) { // 08：耗材
				if ("01".equals(baseEquipment2Apply.getApply_type())) {
					baseEquipmentApplyRelation.setNeed_return("0"); // 0：需要入库
					baseEquipmentApplyRelation.setReturn_count("0"); // 归还（入库）数量默认为0
				} else {
					baseEquipmentApplyRelation.setNeed_return("1"); // 1：不需要
				}
			} else {
				baseEquipmentApplyRelation.setNeed_return("0");
				baseEquipmentApplyRelation.setReturn_count("0");
			}
			baseEquipment.setLast_modify_by(curUser.getName());
			baseEquipment.setLast_modify_date(new Date());
			if (StringUtil.isEmpty(baseEquipment.getId())) {
				baseEquipment.setEq_status("01"); // 设备状态，码表：BASE_EQ_STATUS（01：在用
				// 02：待维修 03：待报废）
				baseEquipment.setIs_new("1"); // 是否是需要新购买（还未入库）的设备（0：否 1：是）
				if (!"08".equals(baseEquipment.getEq_type())) {
					baseEquipment.setEq_validate_count("1"); // 非耗材类设备，剩余数量默认为1
				}
				baseEquipment.setCreate_by(curUser.getName());
				baseEquipment.setCreate_date(new Date());
				baseEquipmentService.save(baseEquipment); // 1、新增设备信息
				baseEquipmentApplyRelation.setBaseEquipment2(baseEquipment);
			} else {
				if ("01".equals(baseEquipment2Apply.getApply_type())) {
					baseEquipmentService.updateBuyEquipment(baseEquipment); // 采购申请时，修改设备基本信息
				} else if ("02".equals(baseEquipment2Apply.getApply_type())
						|| "03".equals(baseEquipment2Apply.getApply_type())
						|| "05".equals(baseEquipment2Apply.getApply_type())) {
					baseEquipmentService.updateEquipment(baseEquipment); // 维修、借用、停用申请时，修改设备基本信息
				} else if ("04".equals(baseEquipment2Apply.getApply_type())) {
					baseEquipmentService.updateScrapEquipment(baseEquipment); // 报废申请时，修改设备基本信息
				}
			}
			if ("02".equals(baseEquipment2Apply.getApply_type())) { // 维修
				if (StringUtil.isEmpty(baseEquipmentApplyRelation
						.getApply_count())) {
					baseEquipmentApplyRelation.setApply_count("1"); // 维修数量默认为1
				}
			}
			baseEquipment2ApplyRelationService.save(baseEquipmentApplyRelation); // 2、保存设备申请关联记录
		} catch (Exception e) {
			e.printStackTrace();
			return JsonWrapper.failureWrapperMsg("提交设备申请失败，请重试！");
		}
		return JsonWrapper.successWrapper(baseEquipmentApplyRelation);
	}
*/
	/**
	 * 详情
	 * 
	 * @param request
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "detail")
	@ResponseBody
	public HashMap<String, Object> detail(HttpServletRequest request, String id)
			throws Exception {
		return super.detail(request, id);
	}

	/**
	 * 删除
	 * 
	 * @param ids
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "delete")
	@ResponseBody
	public HashMap<String, Object> delete(String ids) throws Exception {
		baseEquipment2ApplyRelationService.delete(ids);
		return JsonWrapper.successWrapper(ids);
	}
/*
	*//**
	 * 部分入库
	 * 
	 * @param request
	 * @param baseEquipment2Apply
	 * @throws Exception
	 *//*
	@RequestMapping(value = "inStock")
	@ResponseBody
	public HashMap<String, Object> inStock(HttpServletRequest request,
			BaseEquipment2ApplyRelation baseEquipmentApplyRelation)
			throws Exception {
		try {
			CurrentSessionUser curUser = this.getCurrentUser(); // 获取当前用户登录信息
			// 是否需要归还（入库），码表：BASE_EQ_NEED（0：需要 1：不需要）
			if ("0".equals(baseEquipmentApplyRelation.getNeed_return())) {
				BaseEquipment2 baseEquipment = baseEquipmentService
						.get(baseEquipmentApplyRelation.getBaseEquipment2()
								.getId());
				BaseEquipment2Apply baseEquipment2Apply = baseEquipmentApplyService
						.get(baseEquipmentApplyRelation.getBaseEquipment2Apply()
								.getId());
				// 申请数量
				int apply_count = StringUtil
						.isNotEmpty(baseEquipmentApplyRelation.getApply_count()) ? Integer
						.parseInt(baseEquipmentApplyRelation.getApply_count())
						: 0;
				// 本次归还数量
				int return_count = StringUtil
						.isNotEmpty(baseEquipmentApplyRelation
								.getReturn_count()) ? Integer
						.parseInt(baseEquipmentApplyRelation.getReturn_count())
						: 0;
				// 已归还数量
				int returnd_count = baseEquipment2ApplyRelationService
						.queryReturn_count(baseEquipmentApplyRelation.getId());
				if (returnd_count > 0) {
					if (return_count > (apply_count - returnd_count)) {
						return JsonWrapper.failureWrapperMsg("上次已部分归还（入库）（数量："
								+ returnd_count + "），本次最多归还（入库）数量："
								+ (apply_count - returnd_count) + "。");
					}
				} else {
					if (return_count > apply_count) {
						return JsonWrapper
								.failureWrapperMsg("归还（入库）数量不能大于申请数量，请检查！");
					}
				}

				if ("01".equals(baseEquipment2Apply.getApply_type())) { // 申请类型，码表：BASE_EQ_APPLY_TYPE（01：采购）
					if ("08".equals(baseEquipment.getEq_type())) {
						// 耗材类设备申请购买时，入库操作直接修改设备的可用数量、购买数量等
						int buy_count = StringUtil.isNotEmpty(baseEquipment
								.getEq_buy_count()) ? Integer
								.parseInt(baseEquipment.getEq_buy_count()) : 0;
						buy_count += return_count;
						baseEquipment
								.setEq_buy_count(String.valueOf(buy_count)); // 设备购买数量
						// baseEquipment.setEq_buy_price(eq_buy_price);
						baseEquipment.setEq_buy_date(new Date()); // 设备购买时间
						baseEquipmentService.inStock(baseEquipment,
								baseEquipmentApplyRelation.getReturn_count(),
								curUser.getName()); // 1、设备入库
					} else {
						int new_count = return_count;
						boolean needAdd = true;
						if ("1".equals(baseEquipment.getIs_new())) {
							// 新购买（系统暂无）的设备，提交采购申请时，系统默认新增一个设备，设备入库时，实则只入库（申请数量-1）个
							if (return_count == (apply_count - returnd_count)) {
								new_count--;
								baseEquipment.setIs_new("0"); // 待新购买的设备入库后，修改系统原默认新增的设备（相当于新购买的设备入库数量1个）
								baseEquipmentApplyRelation
										.setReturn_count(String
												.valueOf(apply_count));
							}
							if (returnd_count == (apply_count - 1)) {
								needAdd = false;
							}
						}
						if (needAdd) {
							// 非耗材类设备（检验设备、计量器具、办公设备、其他设备）申请购买时，入库操作直接新增设备
							for (int i = 0; i < new_count; i++) {
								baseEquipmentService.saveEquipment(
										baseEquipment, curUser);
							}
						}
					}
				} else {
					if ("03".equals(baseEquipment2Apply.getApply_type())) {	// 03：借用
						baseEquipment.setEq_return_date(new Date()); // 设备归还时间
					}
					baseEquipmentService.inStock(baseEquipment,
							baseEquipmentApplyRelation.getReturn_count(),
							curUser.getName()); // 1、设备入库
				}
				if (return_count > apply_count) {
					baseEquipmentApplyRelation.setReturn_count(String
							.valueOf(apply_count));
				}
				baseEquipmentApplyRelation.setReturn_count(String
						.valueOf(returnd_count + return_count));
				baseEquipmentApplyRelation.setLast_modify_by(curUser.getName());
				baseEquipmentApplyRelation.setLast_modify_date(new Date());
				baseEquipment2ApplyRelationService
						.save(baseEquipmentApplyRelation); // 2、保存设备申请关联信息

				boolean isStockAll = isStockAll(baseEquipment2Apply); // 判断是否已全部入库
				if (isStockAll) {
					baseEquipment2Apply.setApply_status("07"); // 申请状态，码表：BASE_EQ_APPLY_STATUS（07：已结束）
				} else {
					baseEquipment2Apply.setApply_status("05"); // 申请状态，码表：BASE_EQ_APPLY_STATUS（05：部分入库）
				}
				baseEquipment2Apply.setLast_modify_by(curUser.getName());
				baseEquipment2Apply.setLast_modify_date(new Date());
				baseEquipmentApplyService.updateApplyStatus(baseEquipment2Apply); // 3、更新申请状态
			} else {
				return JsonWrapper.failureWrapperMsg("该设备不需要归还（入库）！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return JsonWrapper.failureWrapperMsg("部分入库操作失败，请重试！");
		}
		return JsonWrapper.successWrapper(baseEquipmentApplyRelation);
	}*/

	/**
	 * 部分报废
	 * 
	 * @param request
	 * @param baseEquipment2Apply
	 * @throws Exception
	 *//*
	@RequestMapping(value = "scrap")
	@ResponseBody
	public HashMap<String, Object> scrap(HttpServletRequest request,
			BaseEquipment2ApplyRelation baseEquipmentApplyRelation)
			throws Exception {
		try {
			CurrentSessionUser curUser = this.getCurrentUser(); // 获取当前用户登录信息
			BaseEquipment2 baseEquipment = baseEquipmentService
					.get(baseEquipmentApplyRelation.getBaseEquipment2().getId());
			BaseEquipment2Apply baseEquipment2Apply = baseEquipmentApplyService
					.get(baseEquipmentApplyRelation.getBaseEquipment2Apply()
							.getId());
			// 申请数量
			int apply_count = StringUtil.isNotEmpty(baseEquipmentApplyRelation
					.getApply_count()) ? Integer
					.parseInt(baseEquipmentApplyRelation.getApply_count()) : 0;
			// 本次归还（入库\报废）数量
			int return_count = StringUtil.isNotEmpty(baseEquipmentApplyRelation
					.getReturn_count()) ? Integer
					.parseInt(baseEquipmentApplyRelation.getReturn_count()) : 0;
			// 已归还（入库\报废）数量
			int returnd_count = baseEquipment2ApplyRelationService
					.queryReturn_count(baseEquipmentApplyRelation.getId());
			if (returnd_count > 0) {
				if (return_count > (apply_count - returnd_count)) {
					return JsonWrapper.failureWrapperMsg("上次已部分归还（入库/报废）（数量："
							+ returnd_count + "），本次最多归还（入库/报废）数量："
							+ (apply_count - returnd_count) + "。");
				}
			} else {
				if (return_count > apply_count) {
					return JsonWrapper
							.failureWrapperMsg("归还（入库/报废）数量不能大于申请数量，请检查！");
				}
			}

			// 申请类型，码表：BASE_EQ_APPLY_TYPE（02：维修 04：报废）
			if ("02".equals(baseEquipment2Apply.getApply_type())
					|| "04".equals(baseEquipment2Apply.getApply_type())) {
				baseEquipment.setEq_status("03"); // 待报废
			}
			baseEquipment.setEq_buy_count("1");
			baseEquipment.setEq_validate_count("1");
			baseEquipment.setLast_modify_by(curUser.getName());
			baseEquipment.setLast_modify_date(new Date());
			baseEquipment.setEq_instock_date(new Date()); // 设备入库时间
			baseEquipment.setEq_use_status("01"); // 设备使用状态，码表：BASE_EQ_USE_STATUS（01：未借用/领用；02：已借用/领用）
			baseEquipmentService.save(baseEquipment);

			if (return_count > apply_count) {
				baseEquipmentApplyRelation.setReturn_count(String
						.valueOf(apply_count));
			}
			baseEquipmentApplyRelation.setReturn_count(String
					.valueOf(returnd_count + return_count));
			baseEquipmentApplyRelation.setLast_modify_by(curUser.getName());
			baseEquipmentApplyRelation.setLast_modify_date(new Date());
			baseEquipment2ApplyRelationService.save(baseEquipmentApplyRelation); // 2、保存设备申请关联信息

			boolean isStockAll = isStockAll(baseEquipment2Apply); // 判断是否已全部入库（报废）
			if (isStockAll) {
				baseEquipment2Apply.setApply_status("07"); // 申请状态，码表：BASE_EQ_APPLY_STATUS（07：已结束）
			} else {
				baseEquipment2Apply.setApply_status("08"); // 申请状态，码表：BASE_EQ_APPLY_STATUS（08：部分报废）
			}
			baseEquipment2Apply.setLast_modify_by(curUser.getName());
			baseEquipment2Apply.setLast_modify_date(new Date());
			baseEquipmentApplyService.updateApplyStatus(baseEquipment2Apply); // 3、更新申请状态
		} catch (Exception e) {
			e.printStackTrace();
			return JsonWrapper.failureWrapperMsg("部分报废操作失败，请重试！");
		}
		return JsonWrapper.successWrapper(baseEquipmentApplyRelation);
	}
*/
	/**
	 * 判断是否全部入库
	 * 
	 * @param id --
	 *            设备申请ID
	 * @return
	 */
	private boolean isStockAll(EquipmentBuy baseEquipment2Apply) {
		boolean isStockAll = true; // 是否全部入库
		if (StringUtil.isNotEmpty(baseEquipment2Apply.getId())) {
			List<EquipmentBuyRelation> list = baseEquipment2ApplyRelationService
					.queryBaseEquipmentApplyRelationByApplyId(baseEquipment2Apply
							.getId());
			if (!list.isEmpty()) {
				for (EquipmentBuyRelation baseEquipmentApplyRelation : list) {
					if ("0".equals(baseEquipmentApplyRelation.getNeed_return())) {
						int apply_count = StringUtil
								.isNotEmpty(baseEquipmentApplyRelation
										.getApply_count()) ? Integer
								.parseInt(baseEquipmentApplyRelation
										.getApply_count()) : 0;
						int return_count = StringUtil
								.isNotEmpty(baseEquipmentApplyRelation
										.getReturn_count()) ? Integer
								.parseInt(baseEquipmentApplyRelation
										.getReturn_count()) : 0;
						if (return_count != apply_count) {
							isStockAll = false;
						}
					}
				}
			}
		}
		return isStockAll;
	}
}
