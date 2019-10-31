package com.fwxm.scientific.web;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fwxm.scientific.bean.TjDTO;
import com.fwxm.scientific.bean.Tjy2ScientificResearch;
import com.fwxm.scientific.dao.Tjy2ScientificResearchDao;
import com.fwxm.scientific.service.TjManager;
import com.khnt.core.crud.web.support.JsonWrapper;
import com.khnt.utils.DateUtil;
import com.khnt.utils.StringUtil;
import com.lsts.finance.bean.CwFytjDTO;
import com.lsts.finance.service.CwFytjManager;
import com.lsts.statistics.bean.DeviceCountDTO;


/**
 *财务统计Action
 *
 * @author dxf
 *
 * @date 2015年10月16日
 */
@Controller
@RequestMapping("tj")
public class TjAction {
	private Log log = LogFactory.getLog(getClass());
    @Autowired
    private TjManager  tjManager;
    
    @Autowired
    private Tjy2ScientificResearchDao scientificResearchDao; 
    
    SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	/**获取统计数据
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "getKyData")
	@ResponseBody
	public HashMap<String, Object> getKyData(HttpServletRequest request) throws Exception {
		HashMap<String, Object> wrapper = new HashMap<String, Object>();
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		String org_id = request.getParameter("org_id");
		String userName = request.getParameter("userName");
		try {
			if (StringUtil.isEmpty(startDate)) {
				startDate = DateUtil.getFirstDateStringOfYear("yyyy-MM-dd");
			}
			if (StringUtil.isEmpty(endDate)) {
				endDate = DateUtil.getDateTime("yyyy-MM-dd", new Date());
			}
			List<String> list=scientificResearchDao.createSQLQuery("select id from TJY2_SCIENTIFIC_RESEARCH where status not in('1','2','3','4')").list();
			List<Object> list1 = new ArrayList<Object>();
			HashMap<String, Object> wrappers = new HashMap<String, Object>();
			for (int i = 0; i < list.size(); i++) {
				List<Object> lists=scientificResearchDao.createSQLQuery("select MONEY1,MONEY2,MONEY3,MONEY4,COST_ITEM_ID1,COST_ITEM_ID2,COST_ITEM_ID3,COST_ITEM_ID4 from TJY2_FYBXD where FYBX_TYPE='2' and ( SUB_ITEM_ID1='"+list.get(i)+"' or SUB_ITEM_ID2='"+list.get(i)+"' or SUB_ITEM_ID3='"+list.get(i)+"' or SUB_ITEM_ID4='"+list.get(i)+"')").list();
				
				for (int j = 0; j < lists.size(); j++) {
					Object[] o1=(Object[])lists.get(j);
					wrappers.put("project_name", list.get(i));
					wrappers.put("MONEY1", o1[0]==null?0.0:o1[0]);
					wrappers.put("MONEY2", o1[1]==null?0.0:o1[1]);
					wrappers.put("MONEY3", o1[2]==null?0.0:o1[2]);
					wrappers.put("MONEY4", o1[3]==null?0.0:o1[3]);
					wrappers.put("COST_ITEM_ID1",o1[4]==null?"":o1[4].toString());
					wrappers.put("COST_ITEM_ID2", o1[5]==null?"":o1[5].toString());
					wrappers.put("COST_ITEM_ID3", o1[6]==null?"":o1[6].toString());
					wrappers.put("COST_ITEM_ID4", o1[7]==null?"":o1[7].toString());
					list1.add(wrappers);
				}
			}
			List<TjDTO> lis=tjManager.getKyData(startDate, endDate, org_id,userName);
			Double num=0.0;
			for (int i = 0; i < lis.size(); i++) {
				TjDTO tj=lis.get(i);
				for (int j = 0; j < list1.size(); j++) {
					HashMap<String, Object> wrapper1=(HashMap<String, Object>)list1.get(j);
					Tjy2ScientificResearch entity=scientificResearchDao.get(wrapper1.get("project_name").toString());
					if(tj.getXmmc().equals(entity.getProjectName())){
						num=num+(Double.parseDouble(wrapper1.get("MONEY1").toString()))+(Double.parseDouble(wrapper1.get("MONEY2").toString()))+(Double.parseDouble(wrapper1.get("MONEY3").toString()))+(Double.parseDouble(wrapper1.get("MONEY4").toString()));
						
					}
				}
				tj.setMoney(tj.getMoney()+num);;
				tj.setFybx(num);
				
			}
			wrapper.put("success", true);
			wrapper.put("data",lis );
			//wrapper.put("datas",tjManager.getFybx(tjManager.getFybxlb()));
		} catch (Exception e) {
			e.printStackTrace();
			return JsonWrapper.failureWrapperMsg("获取财务报账数据失败，请重试！");
		}
		return wrapper;
	}
	/**获取持证统计数据(部门)
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "getXmmc")
	@ResponseBody
	public HashMap<String, Object> getXmmc(HttpServletRequest request,String xmmc) throws Exception {
		HashMap<String, Object> wrapper = new HashMap<String, Object>();
		try {
			String hql="from Tjy2ScientificResearch where project_name='"+xmmc+"'";
			List<Tjy2ScientificResearch> list=scientificResearchDao.createQuery(hql).list();
			Tjy2ScientificResearch base=new Tjy2ScientificResearch();
			if(list.size()>0){
				base=list.get(0);
			}
			
			List<Object> list2 = new ArrayList<Object>();
			HashMap<String, Object> wrappers = new HashMap<String, Object>();
			
				List<Object> lists=scientificResearchDao.createSQLQuery("select MONEY1,MONEY2,MONEY3,MONEY4,COST_ITEM_ID1,COST_ITEM_ID2,COST_ITEM_ID3,COST_ITEM_ID4 from TJY2_FYBXD where FYBX_TYPE='2' and ( SUB_ITEM_ID1='"+base.getId()+"' or SUB_ITEM_ID2='"+base.getId()+"' or SUB_ITEM_ID3='"+base.getId()+"' or SUB_ITEM_ID4='"+base.getId()+"')").list();
				
				for (int j = 0; j < lists.size(); j++) {
					Object[] o1=(Object[])lists.get(j);
					wrappers.put("project_name", base.getProjectName());
					wrappers.put("MONEY1", o1[0]==null?0.0:o1[0]);
					wrappers.put("MONEY2", o1[1]==null?0.0:o1[1]);
					wrappers.put("MONEY3", o1[2]==null?0.0:o1[2]);
					wrappers.put("MONEY4", o1[3]==null?0.0:o1[3]);
					wrappers.put("COST_ITEM_ID1",o1[4]==null?"":o1[4].toString());
					wrappers.put("COST_ITEM_ID2", o1[5]==null?"":o1[5].toString());
					wrappers.put("COST_ITEM_ID3", o1[6]==null?"":o1[6].toString());
					wrappers.put("COST_ITEM_ID4", o1[7]==null?"":o1[7].toString());
					list2.add(wrappers);
				}
				List<TjDTO> lis=tjManager.getKyData(null, null, xmmc,"");
				TjDTO t=lis.get(0);
			
			wrapper.put("success", true);
			wrapper.put("data",base);
		} catch (Exception e) {
			e.printStackTrace();
			return JsonWrapper.failureWrapperMsg("获取财务报账数据失败，请重试！");
		}
		return wrapper;
	}
	/**获取持证统计数据(部门)
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "getCzData")
	@ResponseBody
	public HashMap<String, Object> getCzData(HttpServletRequest request) throws Exception {
		HashMap<String, Object> wrapper = new HashMap<String, Object>();
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		String org_id = request.getParameter("org_id");
		String unit = request.getParameter("unit");
		try {
			wrapper.put("success", true);
			wrapper.put("data", tjManager.getCzData(startDate, endDate, org_id,unit));
		} catch (Exception e) {
			e.printStackTrace();
			return JsonWrapper.failureWrapperMsg("获取财务报账数据失败，请重试！");
		}
		return wrapper;
	}
	/**
	 * 各部门人员业务统计表导出
	 * @param request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="exportCount")
	public String exportCountByUser(HttpServletRequest request){	
		try {
			String startDate = request.getParameter("startDate");
			String endDate = request.getParameter("endDate");
			String org_id = request.getParameter("org_id1");
			String org_name =  new String(request.getParameter("org_id").getBytes("ISO-8859-1"),"utf-8");
			String userName = "";
		
			if (StringUtil.isEmpty(startDate)) {
				startDate = DateUtil.getFirstDateStringOfYear("yyyy-MM-dd");
			}
			if (StringUtil.isEmpty(endDate)) {
				endDate = DateUtil.getDateTime("yyyy-MM-dd", new Date());
			}
			List<TjDTO> list = tjManager.getKyData(startDate, endDate, org_id,userName);	
			request.getSession().setAttribute("startDate", startDate);
			request.getSession().setAttribute("endDate", endDate);
			request.getSession().setAttribute("org_name", new String(org_name.getBytes("ISO-8859-1"),"utf-8"));
			request.getSession().setAttribute("data", list);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("各部门人员业务统计表导出失败！");
		}
		return "app/fwxm/scientific/tj_export";
	}
	/**
	 * 各部门业务统计表导出（部门）
	 * @param request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="exportBmCount")
	public String exportCountByBm(HttpServletRequest request){	
		try {
			String startDate = request.getParameter("startDate");
			String endDate = request.getParameter("endDate");
			String org_id = request.getParameter("org_id1");
			String org_name = request.getParameter("org_id");
			String unit = request.getParameter("unit");
			List<TjDTO> list = tjManager.getCzData(startDate, endDate, org_id,unit);	
			request.getSession().setAttribute("startDate", startDate);
			request.getSession().setAttribute("endDate", endDate);
			request.getSession().setAttribute("org_name", new String(org_name.getBytes("ISO-8859-1"),"utf-8"));
			request.getSession().setAttribute("data", list);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("各部门人员业务统计表导出失败！");
		}
		return "app/fwxm/certificate/tj_export";
	}
	
}