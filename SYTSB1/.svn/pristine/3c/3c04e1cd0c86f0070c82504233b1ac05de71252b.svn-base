package com.lsts.equipment2.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.khnt.core.crud.web.SpringSupportAction;
import com.khnt.core.crud.web.support.JsonWrapper;
import com.khnt.security.CurrentSessionUser;
import com.lsts.equipment2.bean.EquipmentSupplier;
import com.lsts.equipment2.service.EquipmentSupplierManager;

/**
 * 供货商信息控制器
 * @ClassName BaseEquipmentSupplierAction
 * @JDK 1.7
 * @author GaoYa
 * @date 2014-02-18 上午10:29:00
 */
@Controller
@RequestMapping("equipment2SupplierAction")
public class EquipmentSupplierAction extends SpringSupportAction<EquipmentSupplier, EquipmentSupplierManager>{

	@Autowired
	private EquipmentSupplierManager baseEquipmentSupplierService;
	
	/**
	 * 保存
	 * 
	 * @param request
	 * @param baseEquipmentSupplier
	 * @throws Exception
	 */
	@RequestMapping(value = "save")
	@ResponseBody
	public HashMap<String, Object> save(HttpServletRequest request,@RequestBody EquipmentSupplier baseEquipmentSupplier) throws Exception {
		String status = request.getParameter("status");
		HashMap<String, Object> wrapper = new HashMap<String, Object>();
		try {
			CurrentSessionUser curUser = this.getCurrentUser(); // 获取当前用户登录信息
			if ("add".equals(status)) {
				baseEquipmentSupplier.setCreate_by(curUser.getName());
				baseEquipmentSupplier.setCreate_date(new Date());
			}else if ("modify".equals(status)) {
				baseEquipmentSupplier.setLast_modify_by(curUser.getName());
				baseEquipmentSupplier.setLast_modify_date(new Date());
			}
			baseEquipmentSupplierService.save(baseEquipmentSupplier);
			wrapper.put("baseEquipmentSupplier", baseEquipmentSupplier);
			wrapper.put("success", "保存成功！");
		} catch (Exception e) {
			e.printStackTrace();
			wrapper.put("msg","保存供货商信息失败，请重试！");
		}
		return wrapper;
	}
	
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
	public HashMap<String, Object> detail(HttpServletRequest request, String id) throws Exception {
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
		baseEquipmentSupplierService.delete(ids);
		return JsonWrapper.successWrapper(ids);
	}

	/**通过厂商名称或电话号码自动检索
	 * @param q
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "searchManufacturer")
	@ResponseBody
	public HashMap<String, Object> suggest(String q) throws Exception {
		String data = new String(q.getBytes("iso8859-1"),"UTF-8");
		List<EquipmentSupplier> list = baseEquipmentSupplierService.sugguest(data);
		ArrayList al = new ArrayList();
		for (EquipmentSupplier baseEquipment2Supplier : list) {
			HashMap hm = new HashMap();
			hm.put("id", baseEquipment2Supplier.getId());
			hm.put("supplier_name", baseEquipment2Supplier.getSupplier_name());
			hm.put("supplier_tel", baseEquipment2Supplier.getSupplier_tel());
			al.add(hm);
		}
        return JsonWrapper.successWrapper(al);
	}
}
