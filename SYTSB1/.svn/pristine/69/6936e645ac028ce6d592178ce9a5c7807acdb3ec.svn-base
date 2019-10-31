package com.fwxm.contract.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fwxm.contract.bean.ContractInfo;
import com.fwxm.contract.service.ContractInfoService;
import com.khnt.core.crud.web.SpringSupportAction;
import com.khnt.pub.fileupload.bean.Attachment;
import com.khnt.pub.fileupload.service.AttachmentManager;
import com.khnt.utils.StringUtil;

@Controller
@RequestMapping("contractInfoAction")
public class ContractInfoAction extends SpringSupportAction<ContractInfo, ContractInfoService> {

	@Autowired
	private ContractInfoService contractInfoService;
	@Autowired
	private AttachmentManager attachmentManager; 

	@Override
	public HashMap<String, Object> delete(String ids) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			
			contractInfoService.del(ids);
			map.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
		}
		
		return map;
	}

	@Override
	public HashMap<String, Object> detail(HttpServletRequest request, String id) throws Exception {
		HashMap<String, Object> wrapper = new HashMap<String, Object>();
		try {
			ContractInfo order = contractInfoService.get(id);
			
			String signPicture = order.getDoc_ids();
			List<Object> list = new ArrayList<Object>();
			if(StringUtil.isNotEmpty(signPicture)){
				String [] attchNames = signPicture.split(",");
				for (int i = 0; i < attchNames.length; i++) {
					Attachment signPicturefile = attachmentManager.get(attchNames[i]);
					list.add(signPicturefile);
				}
				
			}
			wrapper.put("data", order);
			wrapper.put("fileList", list);
			wrapper.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			wrapper.put("success", false);
		}
		
		return wrapper;
	}
	
	/**
	 * 保存基本信息
	 * author pingZhou
	 * @param request
	 * @param entity 合同信息
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
    @RequestMapping("saveBasic")
	public HashMap<String, Object> saveBasic(HttpServletRequest request, ContractInfo entity) throws Exception {
		HashMap<String, Object> wrapper = new HashMap<String, Object>();
		try {
			contractInfoService.saveBasic(request, entity);
		wrapper.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			wrapper.put("success", false);
		}
		
		return wrapper;
	}

	/**
	 * 
	 * author pingZhou
	 * @param request
	 * @param doc_ids 文件id
	 * @param id 合同id
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
    @RequestMapping("saveFile")
	public HashMap<String, Object> saveFile(HttpServletRequest request,String doc_ids,String id) throws Exception {
		HashMap<String, Object> wrapper = new HashMap<String, Object>();
		try {
			contractInfoService.saveFile(request,doc_ids,id);
		wrapper.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			wrapper.put("success", false);
		}
		
		return wrapper;
	}
	

	/**
	 * 提交环节
	 * author pingZhou
	 * @param request
	 * @param step 下一环节
	 * @param ids 业务ids
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
    @RequestMapping("submbitStep")
	public HashMap<String, Object> submbitStep(HttpServletRequest request,String step,String ids,String op_date) throws Exception {
		HashMap<String, Object> wrapper = new HashMap<String, Object>();
		try {
			contractInfoService.submbitStep(request,step,ids,op_date);
		wrapper.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			wrapper.put("success", false);
		}
		
		return wrapper;
	}
	
	/**
	 * 同期统计
	 * author pingZhou
	 * @param request
	 * @param step
	 * @param ids
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
    @RequestMapping("sametimeStatistic")
	public ModelAndView sametimeStatistic(HttpServletRequest request,String type,String view) throws Exception {
		HashMap<String, Object> wrapper = new HashMap<String, Object>();
		try {
			List<Map<String, Object>> list = contractInfoService.sametimeStatistic(request,type);
			wrapper.put("list", list);
			wrapper.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			wrapper.put("success", false);
		}
		if(view==null&&"contrast".equals(type)){
			view = "app/fwxm/contract/statistic/sametime_statistics";
		}else if(view==null&&"bl".equals(type)){
			view = "app/fwxm/contract/statistic/proportion_statistics";
		}
		return new ModelAndView(view,wrapper);
	}
	
	/**
	 * 收费情况确认
	 * author pingZhou
	 * @param request
	 * @param paySure
	 * @param ids
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
    @RequestMapping("paySure")
	public HashMap<String, Object> paySure(HttpServletRequest request,String paySure,String ids) throws Exception {
		HashMap<String, Object> wrapper = new HashMap<String, Object>();
		try {
			contractInfoService.paySure(request,paySure,ids);
		wrapper.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			wrapper.put("success", false);
		}
		
		return wrapper;
	}
	/**
	 * 编辑提醒日期
	 * author pingZhou
	 * @param request
	 * @param paySure
	 * @param ids
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
    @RequestMapping("warnDate")
	public HashMap<String, Object> warnDate(HttpServletRequest request,String warnDate,String ids) throws Exception {
		HashMap<String, Object> wrapper = new HashMap<String, Object>();
		try {
			contractInfoService.warnDate(request,warnDate,ids);
		wrapper.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			wrapper.put("success", false);
		}
		
		return wrapper;
	}
	
	/**
	 * 退回环节
	 * author pingZhou
	 * @param request
	 * @param step 下一环节
	 * @param ids 业务ids
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
    @RequestMapping("backStep")
	public HashMap<String, Object> backStep(HttpServletRequest request,String step,String ids,String op_date) throws Exception {
		HashMap<String, Object> wrapper = new HashMap<String, Object>();
		try {
			contractInfoService.backStep(request,step,ids,op_date);
		wrapper.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			wrapper.put("success", false);
		}
		
		return wrapper;
	}
	
	/**
	 * 合同检索
	 * author pingZhou
	 * @param request
	 * @param name
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
    @RequestMapping("contractResearch")
	public  ModelAndView contractResearch(HttpServletRequest request,String name,String view,String date,String bh) throws Exception {
		HashMap<String, Object> wrapper = new HashMap<String, Object>();
		try {
			wrapper = contractInfoService.contractResearch(request,name,date,bh);
		wrapper.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			wrapper.put("success", false);
		}
		if(view==null){
			view = "app/fwxm/contract/weixin/w-contract-search-back";
		}
		return new ModelAndView(view,wrapper);
	}
	
}
