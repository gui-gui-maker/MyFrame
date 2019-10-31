package com.lsts.equipment2.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.khnt.core.crud.web.SpringSupportAction;
import com.khnt.core.crud.web.support.JsonWrapper;
import com.khnt.security.CurrentSessionUser;
import com.khnt.security.util.SecurityUtil;
import com.lsts.equipment2.bean.EquipmentLoan;
import com.lsts.equipment2.bean.EquipmentUseRegister;
import com.lsts.equipment2.bean.InoutRecord;
import com.lsts.equipment2.service.EquipmentUseRegisterManager;
import com.lsts.finance.bean.Fybxd;
import com.lsts.finance.service.FybxdService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;




@Controller
@RequestMapping("equipmentUseRegisterAction")
public class EquipmentUseRegisterAction extends SpringSupportAction<EquipmentUseRegister, EquipmentUseRegisterManager> {

    @Autowired
    private EquipmentUseRegisterManager  useRegisterManager;
	
    @Override
    public HashMap<String, Object> save(HttpServletRequest request,EquipmentUseRegister equipmentUseRegister) throws Exception{
		CurrentSessionUser user = SecurityUtil.getSecurityUser();
		//equipmentUseRegister.setStatus(useRegisterManager.SBJD_WGH);
		equipmentUseRegister.setCreateId(user.getId());
		equipmentUseRegister.setCreateName(user.getName());
		equipmentUseRegister.setCreateTime(new Date());
		
    	return super.save(request,equipmentUseRegister);
    	
    }
    
    @RequestMapping(value="gh")
    @ResponseBody
    public HashMap<String, Object> gh(HttpServletRequest request) throws Exception{
    	String id= request.getParameter("id");
    	EquipmentUseRegister ghs = useRegisterManager.get(id);
    	ghs.setStatus(useRegisterManager.SBJD_YGH);
    	ghs.setReturnTime(new Date());
    	return super.save(request,ghs);
    }
    /**
	 * 通过设备ID查询设备所有使用登记记录
	 * equip_id
	 * @return
	 * @throws Exception
	 */
    @RequestMapping(value="searchUseRecord")
    @ResponseBody
    public HashMap<String, Object> searchUseRecord(HttpServletRequest request,String equip_id) throws Exception {
		HashMap<String, Object> wrapper = new HashMap<String, Object>();
		try {
			List<EquipmentUseRegister> useRecords = new ArrayList<EquipmentUseRegister>();
			useRecords = useRegisterManager.searchUseRecord(equip_id);
			if(useRecords!=null && useRecords.size()>0){
				wrapper.put("success", true);
				wrapper.put("Rows", useRecords);
			}
		} catch (Exception e) {
			e.printStackTrace();
			wrapper.put("error", true);
		}
		return wrapper;
	}
    
    
    
}