package com.lsts.inspection.web;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.khnt.core.crud.web.SpringSupportAction;
import com.khnt.core.crud.web.support.JsonWrapper;
import com.khnt.utils.DateUtil;
import com.khnt.utils.StringUtil;
import com.lsts.inspection.bean.InspectionInfo;
import com.lsts.inspection.bean.InspectionZZJDInfo;
import com.lsts.inspection.bean.InspectionZZJDInfoDTO;
import com.lsts.inspection.service.InspectionInfoService;
import com.lsts.inspection.service.InspectionZZJDInfoService;
import com.lsts.report.bean.Report;
import com.lsts.report.service.ReportService;

/**
 * 制造监督检验报检业务明细控制器
 * 
 * @ClassName InspectionZZJDAction
 * @JDK 1.6
 * @author GaoYa
 * @date 2015-01-13 下午05:20:00
 */
@Controller
@RequestMapping("inspection/zzjdinfo")
public class InspectionZZJDInfoAction extends
		SpringSupportAction<InspectionZZJDInfo, InspectionZZJDInfoService> {

	@Autowired
	private InspectionZZJDInfoService inspectionZZJDInfoService;
	@Autowired
	private InspectionInfoService inspectionInfoService;
	@Autowired
	private ReportService reportService;
	
	// 删除报检业务明细数据
	@RequestMapping(value = "del")
	@ResponseBody
	public HashMap<String, Object> del(HttpServletRequest request, String ids) throws Exception {
		inspectionZZJDInfoService.del(request, ids);
		return JsonWrapper.successWrapper(ids);
	}
	
	// 获取制造监检明细列表
	@RequestMapping(value = "getInfos")
	@ResponseBody
	public HashMap<String, Object> getInfos(HttpServletRequest request)
			throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		String ids = request.getParameter("ids");
		try {
			map.put("list", inspectionZZJDInfoService.queryInfos(ids));
			map.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			log.debug(e.toString());
			return JsonWrapper.failureWrapperMsg("读取制造监督检验明细信息失败！");
		}
		return map;
	}
	
	/**
	 * 报告导出excel
	 * 
	 * @param request
	 * @return
	 * @author GaoYa
	 * @date 2018-04-28 下午01:20:00
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "exportReport")
	public String exportReport(HttpServletRequest request) {
		try {
			String report_name = "";
			// 报告业务ID
			String info_ids = request.getParameter("info_ids"); 			
			List<InspectionZZJDInfoDTO> dto_list = new ArrayList<InspectionZZJDInfoDTO>();
			List<InspectionZZJDInfo> list = inspectionZZJDInfoService.exportReport(info_ids);
			if (!list.isEmpty()) {
				for (InspectionZZJDInfo inspectionZZJDInfo : list) {
					InspectionZZJDInfoDTO dto = new InspectionZZJDInfoDTO();
					BeanUtils.copyProperties(inspectionZZJDInfo, dto);
					
					InspectionInfo info = inspectionInfoService.get(inspectionZZJDInfo.getFk_inspection_info_id());
					dto.setReport_sn(info.getReport_sn());
					dto.setInspection_date_str(DateUtil.format(inspectionZZJDInfo.getInspection_date(), "yyyy-MM-dd"));
					if(StringUtil.isEmpty(report_name) && StringUtil.isNotEmpty(info.getReport_type())){
						Report report = reportService.get(info.getReport_type());
						report_name = report.getReport_name();
					}
					
					if(inspectionZZJDInfo.getAdvance_fees()==null){						
						dto.setAdvance_fees(info.getAdvance_fees());					
					}else{
						if(inspectionZZJDInfo.getAdvance_fees()==0){
							dto.setAdvance_fees(info.getAdvance_fees());
						}
					}
					dto_list.add(dto);
					// 按报告编号进行排序
					Collections.sort(dto_list, new MyComparetor());
				}
			}
			request.getSession().setAttribute("report_name", report_name); // 报告类型名称
			request.getSession().setAttribute("data", dto_list); // 收入记录
		} catch (Exception e) {
			e.printStackTrace();
			log.error("报告导出失败！");
		}
		return "app/query/export_zzjd_gl_info";
	}
	

	/**
	 * 比较器
	 * 
	 * @param request
	 * @return
	 * @author GaoYa
	 * @date 2018-04-28 下午01:44:00
	 */
	public class MyComparetor implements Comparator {
		public int compare(Object o1, Object o2) {
			InspectionZZJDInfoDTO p1 = (InspectionZZJDInfoDTO) o1;
			InspectionZZJDInfoDTO p2 = (InspectionZZJDInfoDTO) o2;
			// 按报告编号排序
			return (p1.getReport_sn().compareTo(p2.getReport_sn()));
		}
	}
}
