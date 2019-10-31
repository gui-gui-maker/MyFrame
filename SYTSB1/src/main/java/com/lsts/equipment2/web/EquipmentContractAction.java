package com.lsts.equipment2.web;

import com.khnt.core.crud.web.SpringSupportAction;
import com.khnt.core.crud.web.support.JsonWrapper;
import com.khnt.security.CurrentSessionUser;
import com.khnt.security.util.SecurityUtil;
import com.khnt.utils.FileUtil;
import com.lsts.equipment2.bean.EquipmentBuy;
import com.lsts.equipment2.bean.EquipmentContract;
import com.lsts.equipment2.bean.EquipmentLoan;
import com.lsts.equipment2.service.EquipmentBuyService;
import com.lsts.equipment2.service.EquipmentContractManager;
import com.lsts.humanresources.bean.Contract;
import com.lsts.humanresources.bean.EmployeeBase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Controller
@RequestMapping("equipmentContractAction")
public class EquipmentContractAction extends SpringSupportAction<EquipmentContract, EquipmentContractManager> {

    @Autowired
    private EquipmentContractManager  equipmentContractManager;
    @Autowired
    private EquipmentBuyService equipmentBuyService;
    public String conId;
    /**
  	 * 保存合同基本信息
  	 *
  	 */
    @RequestMapping(value = "saveContract")
  	@ResponseBody
  	public HashMap<String, Object> saveContract(EquipmentContract equipmentContract,String buyApplyId,String renew,HttpServletRequest request)
  			throws Exception {
  		HashMap<String, Object> wrapper = new HashMap<String, Object>();
  		EquipmentBuy equipmentBuy = equipmentBuyService.get(buyApplyId);
  		EquipmentContract equipmentContract1 = equipmentContractManager.get(conId);
  		equipmentContract1.setSignedMan(equipmentContract.getSignedMan());
  		equipmentContract1.setContractType(equipmentContract.getContractType());
  		equipmentContract1.setContractStartDate(equipmentContract.getContractStartDate());
  		equipmentContract1.setContractStopDate(equipmentContract.getContractStopDate());
  		equipmentContract1.setFkEquipApplyId(buyApplyId);
		equipmentContract1.setCreateDate(new Date());
		equipmentBuy.setApplyStatus(equipmentBuyService.BUY_FLOW_YQHT);//改变申请单状态为已签订合同
  		try{
  			equipmentBuyService.save(equipmentBuy);
  			equipmentContractManager.save(equipmentContract1);
  		}catch(Exception e){
  			log.error("获取信息：" + e.getMessage());
  			wrapper.put("success", false);
  			wrapper.put("message", "获取信息出错！");
  			e.printStackTrace();	
  		}
  		return wrapper;
  	}
    /**
  	 * 查询合同
  	 *
  	 */
  	@RequestMapping(value = "detailContract")
  	@ResponseBody
  	public HashMap<String, Object> detailContract(String buyApplyId,HttpServletRequest request)
  			throws Exception {
  		HashMap<String, Object> wrapper = new HashMap<String, Object>();
  		try{
  			EquipmentContract equipmentContract=new EquipmentContract();
  			equipmentContract = equipmentContractManager.getByBuyApplyId(buyApplyId);
  			wrapper.put("con", equipmentContract);
  			wrapper.put("success", true);
  		}catch(Exception e){
  			log.error("获取信息：" + e.getMessage());
  			wrapper.put("success", false);
  			wrapper.put("message", "获取信息出错！");
  			e.printStackTrace();	
  		}
  		return wrapper;
  	}
  	/**
  	 * 保存上传文件
  	 *
  	 */
  	@ResponseBody
	@RequestMapping("saveFile")
	public HashMap<String, Object> saveFile(HttpServletRequest request,
			String id,String documentId) throws Exception {
		HashMap<String, Object> wrapper = new HashMap<String, Object>();
		request.setCharacterEncoding("UTF-8");
		String documentName = request.getParameter("documentName"); 
		try {
			EquipmentContract equipmentContract=new EquipmentContract();
			if(id!=null&&!id.equals(null)){
			if(!id.equals("")&&!id.equals(null)){
				equipmentContract=equipmentContractManager.get(id);
			}
			}
			equipmentContract.setDocumentId(documentId);
			equipmentContract.setDocumentName(documentName);
			equipmentContractManager.save(equipmentContract);
			conId=equipmentContract.getId();
			wrapper.put("success", true);
		} catch (Exception e) {
			wrapper.put("success", false);
		}
		return wrapper;
	}
  	/**
	 * 取得合同文档
	 * @param id
	 * @param response
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("getFile")
	public void getFile(String id,HttpServletResponse response) throws Exception {
		EquipmentContract file = equipmentContractManager.getFile(id);
		//下载文档
		FileUtil.download(response, file.getDocumentDoc(), file.getDocumentName(), "application/octet-stream");
	}
  	
}