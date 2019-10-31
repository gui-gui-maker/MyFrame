package com.lsts.finance.web;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.khnt.bpm.core.service.ActivityManager;
import com.khnt.bpm.ext.service.FlowExtManager;
import com.khnt.core.crud.web.SpringSupportAction;
import com.khnt.core.crud.web.support.JsonWrapper;
import com.khnt.core.exception.KhntException;
import com.khnt.security.CurrentSessionUser;
import com.khnt.security.util.SecurityUtil;
import com.lsts.finance.bean.CwInvoiceP;
import com.lsts.finance.dao.CwInvoicePDao;
import com.lsts.finance.service.CwInvoiceFManager;
import com.lsts.finance.service.CwInvoicePManager;


@Controller
@RequestMapping("cwInvoice/reg")
public class CwInvoicePAction extends SpringSupportAction<CwInvoiceP, CwInvoicePManager> {

	@Autowired
	private CwInvoicePManager cwInvoicePManager;
	
	@Autowired
	private CwInvoiceFManager cwInvoiceFManager;
	
	@Autowired
	private FlowExtManager flowExtManager;
		
	@Autowired
	private ActivityManager activityManager;
	@Autowired
	private CwInvoicePDao cwInvoicePDao;
	
	/**
     * 保存导入文件
     * @param files
     * @return
     */
    @RequestMapping(value = "saveTask")
	@ResponseBody
	public HashMap<String,Object> saveTask(HttpServletRequest request,String files) {
		HashMap<String,Object> data=new HashMap<String,Object>();
		String[] contents = new String[2];
		try {
			contents = cwInvoiceFManager.saveTaskData(request,files,getCurrentUser());
			data.put("success", true);
			data.put("total",contents[0]);
	    	data.put("repData",contents[1]);
		} catch(Exception e) {
			data.put("success", false);
		}
    	return  data;
	}
	
	
	/**提交单据
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "submit")
	@ResponseBody
	public HashMap<String, Object> submit(String id) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		if (id.isEmpty()){
			map.put("success", false);
			map.put("msg", "所选业务ID为空！");
		} else {
			CwInvoiceP cwInvoicep = cwInvoicePManager.get(id);
			if(cwInvoicep==null){
				map.put("success", false);
				map.put("msg", "数据获取失败！");
			} else {
				cwInvoicep.setStatus(CwInvoicePManager.CW_SUBMIT);//已提交
				cwInvoicePManager.save(cwInvoicep);
				
				map.put("success", true);
			}
		}
		return map;
	}
	
	
	
    /**
     * 保存发票信息
     * @throws Exception 
     */
	@RequestMapping(value = "saveFp")
	@ResponseBody
    public HashMap<String, Object> saveFp(HttpServletRequest request,CwInvoiceP cwInvoicep) throws KhntException{
		HashMap<String, Object> map = new HashMap<String, Object>();
		CurrentSessionUser user = SecurityUtil.getSecurityUser();
		int start = Integer.parseInt(cwInvoicep.getInvoiceStart());//发票开始号
  		int end = Integer.parseInt(cwInvoicep.getInvoiceEnd());//发票结束号
	
			cwInvoicePManager.saveFp(cwInvoicep, user);
//			StringBuffer invoiceCode = new StringBuffer() ;
//	  		int count = 0 ;
//	  		for (int i=start;i<=end;i++){
//	  			invoiceCode.append(start + count).append(",");
//	  			count++;
//	  		}
//			List b = cwInvoicePDao.getCode(invoiceCode.toString(),cwInvoicep.getInvoiceNum());
//	  		BigDecimal scount =  (BigDecimal) b.get(0);
//			
//			cwInvoicep.setRegistrantId(user.getId()); //登记人ID
//	  		cwInvoicep.setRegistrantName(user.getName());//登记人姓名
//			cwInvoicep.setRegistrantDate(new Date());//登记时间
//			try {
//				cwInvoicePManager.save(cwInvoicep);
//				if( scount.intValue() == 0){
//			  		for (int i=start;i<=end;i++) {
//			  					CwInvoiceF cwInvoiceF = new CwInvoiceF();
//			  					cwInvoiceF.setInvoiceCode(String.valueOf(i));//发票号
//			  					cwInvoiceF.setInvoiceType(cwInvoicep.getInvoiceType());
//					  	  		cwInvoiceF.setStatus("WSY");//未使用
//					  			cwInvoiceF.setCwInvoiceP(cwInvoicep);
//								cwInvoiceFManager.save(cwInvoiceF);
//			  		}
			  		map.put("success", true);
//				}else{
//					throw new KhntException("发票号已有重复,请重新输入");
//				}
//			} catch (Exception e) {
//				e.printStackTrace();
//			} 
		return map;
    }
	
	
	
	/**
	 * 删除
	 * */
	@RequestMapping(value = "del")
	@ResponseBody
	public HashMap<String, Object> delete(CwInvoiceP cwInvoicep) throws Exception {
		
		cwInvoicePManager.delete(cwInvoicep);
		return JsonWrapper.successWrapper();
	}
	  
}
