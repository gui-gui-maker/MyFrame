package com.fwxm.contract.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fwxm.contract.bean.ContractCustom;
import com.fwxm.contract.bean.ContractInfo;
import com.fwxm.contract.bean.ContractLog;
import com.fwxm.contract.dao.ContractCustomDao;
import com.fwxm.contract.dao.ContractInfoDao;
import com.fwxm.contract.dao.ContractLogDao;
import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.khnt.security.CurrentSessionUser;
import com.khnt.security.util.SecurityUtil;

import util.TS_Util;

@Service("contractInfoService")
public class ContractInfoService extends EntityManageImpl<ContractInfo, ContractInfoDao> {

	@Autowired
	private ContractInfoDao contractInfoDao;
	@Autowired
	private ContractCustomDao contractCustomDao;
	@Autowired
	private ContractLogDao contractLogDao;

	public void del(String ids) {
		String id = ids.replace(",", "','");
		String hql = "update ContractInfo c set c.data_status='99' where c.id in ('"+id+"')";
		contractInfoDao.createQuery(hql).executeUpdate();
		
	}
	
	/**
	 * 保存基本信息
	 * author pingZhou
	 * @param request
	 * @param entity
	 * @throws Exception
	 */
  public void saveBasic(HttpServletRequest request,ContractInfo entity) throws Exception {
		CurrentSessionUser user = SecurityUtil.getSecurityUser();
		if(entity.getId()==null){
			//记录日志
			contractInfoDao.save(entity);
			ContractLog log = new ContractLog();
			log.setContract_id(entity.getId());
			log.setEvent_name("起草");
			log.setOp_id(user.getId());
			log.setOp_name(user.getName());
			log.setOp_time(new Date());
			log.setRemark("起草合同信息");
			log.setStep("0");
			log.setIp(TS_Util.getIpAddress(request));
			contractLogDao.save(log);
		}
		contractInfoDao.save(entity);
	}
  	
  /**
   * 保存附件信息
   * author pingZhou
   * @param request
   * @param doc_ids
 * @param id 
   */
	public void saveFile(HttpServletRequest request, String doc_ids, String id) {
		
		ContractInfo info = contractInfoDao.get(id);
		if(info.getDoc_ids()==null){
			CurrentSessionUser user = SecurityUtil.getSecurityUser();
			ContractLog log = new ContractLog();
			log.setContract_id(info.getId());
			log.setEvent_name("添加附件");
			log.setOp_id(user.getId());
			log.setOp_name(user.getName());
			log.setOp_time(new Date());
			log.setRemark("添加上传附件");
			log.setStep(info.getAudit_step());
			log.setIp(TS_Util.getIpAddress(request));
			contractLogDao.save(log);
		}
		info.setDoc_ids(doc_ids);
		contractInfoDao.save(info);
		
	}
/**
 * 提交环节
 * author pingZhou
 * @param request
 * @param step
 * @param id
 * @param op_date 
 */
public void submbitStep(HttpServletRequest request, String step, String id, String op_date) {
	String []ids = id.split(",");
	for (int i = 0; i < ids.length; i++) {
		String idn = ids[i];
		ContractInfo info = contractInfoDao.get(idn);
		CurrentSessionUser user = SecurityUtil.getSecurityUser();
		ContractLog log = new ContractLog();
		log.setContract_id(info.getId());
		log.setEvent_name("提交");
		log.setOp_id(user.getId());
		log.setOp_name(user.getName());
		log.setOp_time(new Date());
		log.setRemark(getStepAudit(info.getAudit_step())+"提交"+getStepAudit(step));
		log.setStep(info.getAudit_step());
		log.setIp(TS_Util.getIpAddress(request));
		if("3".equals(step)){
			if(op_date!=null){
				try {
					info.setFile_date(new SimpleDateFormat("yyyy-MM-dd").parse(op_date));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else{
				info.setFile_date(new Date());
			}
			info.setFile_status("已归档");
		}
		info.setAudit_step(step);
		contractLogDao.save(log);
	}
	
}
	/**
	 * 获取环节说明
	 * author pingZhou
	 * @param step
	 * @return
	 */
	public String getStepAudit(String step){
		String stepName = "";
		if("0".equals(step)){
			stepName = "起草";
		}else if("1".equals(step)){
			stepName = "审批中";
		}else if("2".equals(step)){
			stepName = "审批完成";
		}else if("3".equals(step)){
			stepName = "已归档";
		}
		return stepName;
	}

	public List<Map<String, Object>> sametimeStatistic(HttpServletRequest request, String type) {
		
		List<Map<String, Object>> mapList = new ArrayList<Map<String,Object>>();
		
		if("contrast".equals(type)){
			//对比
			List<Object> list = contractInfoDao.getAllByYear();	
			
			for (int i = 0; i < list.size(); i++) {
				Map<String, Object> map = new HashMap<String, Object>();
				Object [] sums = (Object[]) list.get(i);
				//String sumCout = (sums[0]==null?"":sums[0].toString());
				String sumAmou = (sums[1]==null?"":sums[1].toString());
				String year = (sums[2]==null?"":sums[2].toString());
				map.put("all", sumAmou);
				map.put("year", year);
				//获取每月金额
				List<Object> mlist = contractInfoDao.getAllByMonth(year);
				for (int j = 0; j < mlist.size(); j++) {
					//String cout = (sums[0]==null?"":sums[0].toString());
					Object [] msums = (Object[]) mlist.get(j);
					String smou = (msums[1]==null?"":msums[1].toString());
					String month = (msums[2]==null?"":msums[2].toString());
					map.put("m_"+month, smou);
				}
				mapList.add(map);
			}
			
			
		}else{
			//占比
			List<Object> list = contractInfoDao.getAllByYear();	
			
			for (int i = 0; i < list.size(); i++) {
				Map<String, Object> map = new HashMap<String, Object>();
				Object [] sums = (Object[]) list.get(i);
				//String sumCout = (sums[0]==null?"":sums[0].toString());
				String sumAmou = (sums[1]==null?"":sums[1].toString());
				String year = (sums[2]==null?"":sums[2].toString());
				map.put("all", sumAmou);
				map.put("year", year);
				//获取每月金额
				List<Object> mlist = contractInfoDao.getProperByMonth(year,sumAmou);
				for (int j = 0; j < mlist.size(); j++) {
					//String cout = (sums[0]==null?"":sums[0].toString());
					Object [] msums = (Object[]) mlist.get(j);
					String smou = (msums[1]==null?"":msums[1].toString());
					String month = (msums[2]==null?"":msums[2].toString());
					map.put("m_"+month, smou);
				}
				mapList.add(map);
			}
			
			
			
		}
		
		
		return mapList;
	}

	public void paySure(HttpServletRequest request, String paySure, String id) {
		String []ids = id.split(",");
		for (int i = 0; i < ids.length; i++) {
			String idn = ids[i];
			ContractInfo info = contractInfoDao.get(idn);
			CurrentSessionUser user = SecurityUtil.getSecurityUser();
			ContractLog log = new ContractLog();
			log.setContract_id(info.getId());
			log.setEvent_name("核定收费情况");
			log.setOp_id(user.getId());
			log.setOp_name(user.getName());
			log.setOp_time(new Date());
			log.setRemark("核实确认收费情况");
			log.setStep(info.getAudit_step());
			log.setIp(TS_Util.getIpAddress(request));
			
			info.setPay_sure(paySure);
			contractInfoDao.save(info);
			contractLogDao.save(log);
		}
		
		
	}

	public void warnDate(HttpServletRequest request, String warnDate, String id) throws ParseException {
		String []ids = id.split(",");
		for (int i = 0; i < ids.length; i++) {
			String idn = ids[i];
			ContractInfo info = contractInfoDao.get(idn);
			Date warnDate1 = new SimpleDateFormat("yyyy-MM-dd").parse(warnDate);
			info.setEffect_date(warnDate1);
			contractInfoDao.save(info);
		}
		
	}

	/**
	 * 退回环节
	 * author pingZhou
	 * @param request
	 * @param step
	 * @param ids
	 * @param op_date
	 */
	public void backStep(HttpServletRequest request, String step, String id, String op_date) {
		String []ids = id.split(",");
		for (int i = 0; i < ids.length; i++) {
			String idn = ids[i];
			ContractInfo info = contractInfoDao.get(idn);
			CurrentSessionUser user = SecurityUtil.getSecurityUser();
			ContractLog log = new ContractLog();
			log.setContract_id(info.getId());
			log.setEvent_name("退回");
			log.setOp_id(user.getId());
			log.setOp_name(user.getName());
			log.setOp_time(new Date());
			log.setRemark(getStepAudit(info.getAudit_step())+"退回"+getStepAudit(step));
			log.setStep(info.getAudit_step());
			log.setIp(TS_Util.getIpAddress(request));
			if("2".equals(step)){
				info.setFile_date(null);
			}
			info.setFile_status("");
			info.setAudit_step(step);
			contractLogDao.save(log);
		}
		
		
	}

	/**
	 * 合同检索
	 * author pingZhou
	 * @param request
	 * @param name
	 * @param bh 
	 * @return 
	 */
	public HashMap<String, Object> contractResearch(HttpServletRequest request, String name,String date, String bh) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		int m = 0;
		int con = 0;
		int cus = 0;
		if(name!=null){
			List<ContractInfo> conList = contractInfoDao.getDataByName(name);
			map.put("conList1", conList);
			m = m + conList.size();
			con = con + conList.size();
			List<ContractCustom> cusList = contractCustomDao.getDataByName(name);
			map.put("cusList1", cusList);
			m = m + cusList.size();
			cus = cusList.size();
		}
		if(date!=null){
			List<ContractInfo> conList = contractInfoDao.getDataByDate(date);
			map.put("conList2", conList);
			m = m + conList.size();
			con = con + conList.size();
		}
		if(bh!=null){
			List<ContractInfo> conList = contractInfoDao.getDataByBh(bh);
			map.put("conList3", conList);
			m = m + conList.size();
			con = con + conList.size();
		}
		map.put("sumc", m);
		map.put("conc", con);
		map.put("cusc", cus);
		return map;
	}
	
}
