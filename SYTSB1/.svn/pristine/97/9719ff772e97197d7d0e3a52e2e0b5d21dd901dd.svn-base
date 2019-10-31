package com.lsts.finance.web;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
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

import util.TS_Util;

import com.fwxm.certificate.bean.Tjy2CertificateTrain;
import com.khnt.core.crud.web.SpringSupportAction;
import com.khnt.core.crud.web.support.JsonWrapper;
import com.khnt.security.CurrentSessionUser;
import com.khnt.security.util.SecurityUtil;
import com.khnt.utils.BeanUtils;
import com.khnt.utils.StringUtil;
import com.lsts.finance.bean.CwBankBackDetail;
import com.lsts.finance.bean.CwBankDTO;
import com.lsts.finance.bean.CwBankDetail;
import com.lsts.finance.bean.CwBankFefund;
import com.lsts.finance.bean.CwBankPayDetail;
import com.lsts.finance.dao.CwBankDetailDao;
import com.lsts.finance.service.CwBankDetailManager;
import com.lsts.finance.service.CwBankFefundManager;


/**
 *银行转账明细Action
 *
 * @author dxf
 *
 * @date 2015年10月16日
 */
@Controller
@RequestMapping("cw/bank")
public class CwBankDetailAction extends SpringSupportAction<CwBankDetail, CwBankDetailManager> {

    @Autowired
    private CwBankDetailManager  cwBankDetailManager;
    @Autowired
    private CwBankFefundManager  cwBankFefundManager;
    @Autowired
    private CwBankDetailDao  cwBankDetailDao;
    
    SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
    /**保存财务导入数据
	 * @param file
	 * @return
	 */
	@RequestMapping(value = "saveBankData")
	@ResponseBody
	public HashMap<String,Object> saveBankData(String files) {
		HashMap<String,Object> data=new HashMap<String,Object>();
		String[] contents = new String[2];
		try {
			contents = cwBankDetailManager.saveBankData(files,getCurrentUser());
			data.put("success", true);
			data.put("total",contents[0]);
	    	data.put("repData",contents[1]);
		} catch(Exception e) {
			data.put("success", false);
		}
    	return  data;
	}
	
	
	/**
	 * 根据银行转账ids获取银行转账信息
	 * @param ids -- 银行转账id字符串
	 * 
	 * @return 
	 * @author GaoYa
	 * @date 2015-11-18 上午10:55:00
	 */
	@RequestMapping(value = "queryBankInfos")
	@ResponseBody
	public HashMap<String, Object> queryBankInfos(HttpServletRequest request, double useMoney)
			throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		String ids = request.getParameter("ids");
		double temp = useMoney;
		try {
			//查询是否存在退款申请的转账。
			String []idsArr = ids.split(",");
			String fadeBack = "";
			for(String id : idsArr) {
				CwBankDetail cwBankDetail = cwBankDetailManager.get(id);
				List<CwBankFefund> list = cwBankFefundManager.queryBankFefund(request, id);
				if(list != null && list.size()>0) {
					String fefundNameTemp = "";
					for(int i=0;i<list.size();i++) {
						if(StringUtil.isEmpty(fefundNameTemp)) {
							fefundNameTemp = list.get(i).getFefundName();
						}else {
							if(!fefundNameTemp.contains(list.get(i).getFefundName())) {
								fefundNameTemp += ","+list.get(i).getFefundName();
							}
						}
					}
					if(StringUtil.isEmpty(fadeBack)) {
						fadeBack = cwBankDetail.getAccountName()+" 中有 <font color=red >"+fefundNameTemp;
					}else {
						fadeBack += ","+fefundNameTemp;
					}
				}
			}
			if(StringUtil.isNotEmpty(fadeBack)) {
				fadeBack += "</font> 的退款申请未处理，请联系相关人员处理后再选择！";
				map.put("msg", fadeBack);
				map.put("success", false);
			}else {
				List<CwBankDTO> cwBankDTOList = new ArrayList<CwBankDTO>(); 
				List<CwBankDetail> list = cwBankDetailManager.queryBankInfos(ids);
				if (!list.isEmpty()) {
					for(CwBankDetail cwBankDetail : list){
						CwBankDTO cwBankDTO = new CwBankDTO();
						BeanUtils.copyProperties(cwBankDTO, cwBankDetail);
						if(temp>cwBankDetail.getRestMoney().doubleValue()) {
							cwBankDTO.setUsedMoney(TS_Util.ratioTransform(cwBankDetail.getRestMoney().doubleValue()));
							temp = temp - cwBankDetail.getRestMoney().doubleValue();
						} else {
							cwBankDTO.setUsedMoney(TS_Util.ratioTransform(temp));
						}
							
						// 默认本次收费金额为银行转账的剩余金额
//						cwBankDTO.setUsedMoney(
//								TS_Util.ratioTransform(
//										cwBankDetail.getRestMoney().doubleValue()));
						cwBankDTO.setPay_remark("");
						cwBankDTOList.add(cwBankDTO);
					}
				}
				map.put("list", cwBankDTOList);
				map.put("success", true);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.debug(e.toString());
			return JsonWrapper.failureWrapperMsg("获取银行转账信息失败！");
		}
		return map;
	}
	
	/**根据银行转账id获取收费明细
	 * @param request
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "queryPayDetails")
	@ResponseBody
	public HashMap<String, Object> queryPayDetails(HttpServletRequest request, String id)
			throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			List<CwBankPayDetail> cwBankPayDetailList = new ArrayList<CwBankPayDetail>();
			List<CwBankBackDetail> cwBankBackDetailList = new ArrayList<CwBankBackDetail>();
			
			//获取收费明细
			List<Object> list = cwBankDetailDao.queryPayDetails(id);
			if (list != null && list.size() > 0) {
				for (int u = 0; u < list.size(); u++) {
					Object obj[] = null;
					CwBankPayDetail cwBankPayDetail = new CwBankPayDetail();
					obj = (Object[]) list.get(u);
					/*cwBankPayDetail.setId(nullToEmpty(obj[0]).toString());
					cwBankPayDetail.setPayTime(obj[1] == null ? null : sf.parse(nullToEmpty(obj[1]).toString()));
					cwBankPayDetail.setReceiveName(nullToEmpty(obj[2]).toString());
					cwBankPayDetail.setPayName(nullToEmpty(obj[3]).toString());
					cwBankPayDetail.setPayMoney(new BigDecimal(obj[4]==null ? "0":obj[4].toString()));
					cwBankPayDetail.setInvoiceNum(nullToEmpty(obj[5]).toString());*/
					cwBankPayDetail.setId(nullToEmpty(obj[0]).toString());
					cwBankPayDetail.setPayTime(obj[1] == null ? null : sf.parse(nullToEmpty(obj[1]).toString()));
					cwBankPayDetail.setPayName(nullToEmpty(obj[2]).toString());
					//cwBankPayDetail.setDoType(nullToEmpty(obj[3]).toString());
					cwBankPayDetail.setDoContent(nullToEmpty(obj[3]).toString());
					//cwBankPayDetail.setDoContent(nullToEmpty(obj[4]).toString());
					cwBankPayDetailList.add(cwBankPayDetail);
				}
			}
			//获取退款明细
			List<Object> backList = cwBankDetailDao.queryBackDetails(id);
			if (backList != null && backList.size() > 0) {
				for (int u = 0; u < backList.size(); u++) {
					Object obj[] = null;
					CwBankBackDetail cwBankBackDetail = new CwBankBackDetail();
					obj = (Object[]) backList.get(u);
					cwBankBackDetail.setId(nullToEmpty(obj[0]).toString());
					cwBankBackDetail.setOperatorTime(obj[1] == null ? null : sf.parse(nullToEmpty(obj[1]).toString()));
					cwBankBackDetail.setOperatorName(nullToEmpty(obj[2]).toString());
					cwBankBackDetail.setFefundMoney(new BigDecimal(obj[3]==null ? "0":obj[3].toString()));
					cwBankBackDetail.setUnitName(nullToEmpty(obj[4]).toString());
					cwBankBackDetail.setFefundReason(nullToEmpty(obj[5]).toString());
					cwBankBackDetailList.add(cwBankBackDetail);
				}
			}
			map.put("Rows", cwBankPayDetailList);
			map.put("BackRows", cwBankBackDetailList);
			map.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			log.debug(e.toString());
			return JsonWrapper.failureWrapperMsg("获取银行转账信息失败！");
		}
		return map;
	}
	
	private Object nullToEmpty(Object o) {
		if(o!=null){
			return o;
		} else {
			return "";
		}
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
		cwBankDetailManager.delete(ids);
		return JsonWrapper.successWrapper(ids);
	}
	/**
	 * 保存往来账
	 * @param request
	 * @param cwBankDetail
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "saveBank")
	@ResponseBody
	public HashMap<String, Object> saveBank(HttpServletRequest request, CwBankDetail cwBankDetail) throws Exception {
		try {
			if(StringUtil.isEmpty(cwBankDetail.getId())){
				cwBankDetailManager.save(cwBankDetail);
			}else{
				Map<String, String> oldMap = cwBankDetailManager.changeCom(request, cwBankDetail);
				Map<String, String> newMap = cwBankDetailDao.queryBankDetailById(cwBankDetail.getId());
				cwBankDetailManager.compareMapOfBean(cwBankDetail,oldMap, newMap, request);
			}
			//更新对方账户信息
			cwBankDetailManager.saveCusAccount(cwBankDetail);
		} catch (Exception e) {
			e.printStackTrace();
			return JsonWrapper.failureWrapperMsg("保存失败，请重试！");
		}
		return JsonWrapper.successWrapper(cwBankDetail);
	}
}