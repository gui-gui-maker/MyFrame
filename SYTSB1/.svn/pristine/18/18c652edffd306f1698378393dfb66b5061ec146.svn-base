package com.lsts.report.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fwxm.contract.bean.ContractCustom;
import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.khnt.security.CurrentSessionUser;
import com.khnt.security.util.SecurityUtil;
import com.khnt.utils.StringUtil;
import com.lsts.inspection.bean.InspectionZZJDInfo;
import com.lsts.inspection.bean.ReportDrawSign;
import com.lsts.inspection.dao.ReportDrawSignDao;
import com.lsts.log.service.SysLogService;
import com.lsts.report.bean.ReportDraw;
import com.lsts.report.bean.ReportDrawDTO;
import com.lsts.report.dao.ReportDrawDao;
import com.sun.org.apache.xpath.internal.operations.And;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import util.Base64Utils;

/**
 * 报告领取记录业务逻辑对象
 * 
 * @ClassName ReportDrawService
 * @JDK 1.7
 * @author GaoYa
 * @date 2014-03-04 下午03:19:00
 */
@Service("reportDrawService")
public class ReportDrawService extends
		EntityManageImpl<ReportDraw, ReportDrawDao> {
	@Autowired
	private ReportDrawDao reportDrawDao;
	@Autowired
	private ReportDrawSignDao reportDrawSignDao;
	@Autowired
	private	SysLogService logService;

	// 获取报告领取记录
	public ReportDraw queryByInspectionInfoId(String inspectionInfoId) {
		return reportDrawDao.queryByInspectionInfoId(inspectionInfoId);
	}

	// 修改报告领取记录
	public HashMap<String, Object> modify(String id, ReportDrawDTO entity)
			throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();

		String sql = "update tzsb_report_draw set";
		boolean hasMdyCondition = false;
		if (StringUtil.isNotEmpty(entity.getPulldown_op())) {
			if (hasMdyCondition) {
				sql += " ,";
			} else {
				hasMdyCondition = true;
			}
			sql += " pulldown_op='" + entity.getPulldown_op() + "'";
		}
		if (StringUtil.isNotEmpty(entity.getLinkmode())) {
			if (hasMdyCondition) {
				sql += " ,";
			} else {
				hasMdyCondition = true;
			}
			sql += " linkmode='" + entity.getLinkmode() + "'";
		}
		if (StringUtil.isNotEmpty(entity.getReport_sn())) {
			if (hasMdyCondition) {
				sql += " ,";
			} else {
				hasMdyCondition = true;
			}
			sql += " report_sn='" + entity.getReport_sn() + "'";
		}

		if (hasMdyCondition) {
			sql += " , data_status='0'";
		}
		
		try {
			if (hasMdyCondition) {
				if (id.indexOf(",") != -1) {
					String temp[] = id.split(",");
					for (int i = 0; i < temp.length; i++) {
						reportDrawDao.createSQLQuery(
								sql + " where id='" + temp[i] + "'")
								.executeUpdate();
					}
				} else {
					reportDrawDao
							.createSQLQuery(sql + " where id='" + id + "'")
							.executeUpdate();
				}
				map.put("success", true);
			} else {
				map.put("success", false);
				map.put("msg", "修改的内容不能为空，请至少填写一项！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
			map.put("msg", "请求超时，请稍后再试！");
		}
		return map;
	}
	
	// 删除
	public void del(HttpServletRequest request, String id) {
		CurrentSessionUser user = SecurityUtil.getSecurityUser();
		try {
			ReportDraw reportDraw = reportDrawDao.get(id);
			reportDraw.setData_status("99");	// 已删除
			reportDraw.setMdy_user_id(user.getId());		// 最后修改人ID
			reportDraw.setMdy_user_name(user.getName());	// 最后修改人姓名
			reportDraw.setMdy_date(new Date());				// 最后修改时间
			// 删除领取记录表（tzsb_report_draw）
			//reportDrawDao.removeById(id);
			reportDrawDao.save(reportDraw);
			logService.setLogs(id, "报告领取", "删除领取记录", 
					user.getId(), 
					user.getName(), 
					new Date(), request.getRemoteAddr());
		} catch (Exception e) {
			e.printStackTrace();
			log.debug(e.toString());
		}
	}

	public ReportDraw getInspectionInfoByid(String id) {
	   return	reportDrawDao.getInspectionInfoByid(id);
	}

	public ReportDraw getByInfoId(String infoId) {
		String hql=" from ReportDraw r where r.inspectionInfo.id=? ";
		//String sql = "select r.* from tzsb_report_draw r where r.fk_inspection_info_id=? ";
		List list=reportDrawDao.createQuery(hql, infoId).list();
		ReportDraw draw=null;
		if(list.size()==0)
		{
			draw=new ReportDraw();
		}else
		{
			draw=(ReportDraw) list.get(0); 
		}
		return draw;
		
		
	}

	public String getInspectionBytableId(String id) {
		return reportDrawDao.getInspectionBytableId(id);
	}

	public void saveEvaluate(String ids, String evaluate) {
		ids = ids.replaceAll(",", "','");
		reportDrawDao.createSQLQuery("update tzsb_report_draw set evaluate = ? where id in ('"+ids+"')", evaluate).executeUpdate();
	}

	public HashMap<String, Object> getReportDrawSign(String id) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		String sql = "select t2.report_sn "+
					 "from tzsb_report_draw t1,tzsb_inspection_info t2 "+
					 "where t1.id = t2.fk_report_draw_id "+
					 "and t1.data_status != '99' "+
					 "and t1.id=? order by t2.report_sn";
		List<Object> list = reportDrawDao.createSQLQuery(sql, id).list();
		map.put("rows", list);
		//获取图片
		String rs = "";
		String filePath = reportDrawDao.get(id).getSign_file();
		
		if(StringUtil.isEmpty(filePath)){
			throw new Exception("未获取到签名图片！");
		}
		StringBuffer sb = new StringBuffer();
	    // BufferedReader:从字符输入流中读取文本，缓冲各个字符，从而实现字符、数组和行的高效读取。
		BufferedReader bufReader = null;
		try {
			File file = new File(filePath);
		    // FileReader:用来读取字符文件的便捷类。
			bufReader = new BufferedReader(new FileReader(file));
			// buf = new BufferedReader(new InputStreamReader(new
			// FileInputStream(file)));
			String temp = null;
			while ((temp = bufReader.readLine()) != null) {
				sb.append(temp);
		    }
			rs = sb.toString();
			map.put("image", rs);
		} catch (Exception e) {
		    e.getStackTrace();
		} finally {
		    if (bufReader != null ) {
		        try {
		            bufReader.close();
		        } catch (IOException e) {
		            e.getStackTrace();
		        }
		    }
		}
		return map;
	}
	
	public List<ReportDraw> getInfos(
			String com_name,String pulldown_op) {
		return reportDrawDao.getInfos(com_name,pulldown_op);
	}


	

	public String saveDrawQrcode(String ids, String report_sns) {
		String id = UUID.randomUUID().toString().replace("-", "");
		String sql = "insert into REPORT_DRAW_PREPARED_QRCODE (id,report_ids,report_sns) values (?,?,?)";
		reportDrawDao.createSQLQuery(sql, id,ids,report_sns).executeUpdate();
		return id;
	}
	public void saveDrawQrcodeImg(String qrcode, String imageFile) {
		String sql = "update REPORT_DRAW_PREPARED_QRCODE set img_path=? where id = ?";
		this.reportDrawDao.createSQLQuery(sql, imageFile,qrcode).executeUpdate();
	}
	public List<Object[]> getDrawQrcode(String qrcode) {
		String sql = "select * from REPORT_DRAW_PREPARED_QRCODE where id = ?";
		return reportDrawDao.createSQLQuery(sql, qrcode).list();
	}

	/**
	 * 通过领取人姓名获取领取人信息
	 * @param pulldown_op
	 * @return
	 */
	public List<ReportDraw> getInfosByName(
			String pulldown_op) {
		return reportDrawDao.getInfosByName(pulldown_op);
	}
	
	
	/**
	 * 获取报告领取前面图片
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, Object> getReportDrawSign1(String id) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		String sql = "select t2.report_sn "+
					 "from tzsb_report_draw t1,tzsb_inspection_info t2 "+
					 "where t1.id = t2.fk_report_draw_id "+
					 "and t1.data_status != '99' "+
					 "and t1.id=? order by t2.report_sn";
		List<Object> list = reportDrawDao.createSQLQuery(sql, id).list();
		map.put("rows", list);
		//获取图片
		String rs = "";
		String filePath = reportDrawDao.get(id).getSign_file();
		
		if(StringUtil.isEmpty(filePath)){
			throw new Exception("未获取到签名图片！");
		}
		StringBuffer sb = new StringBuffer();
	    // BufferedReader:从字符输入流中读取文本，缓冲各个字符，从而实现字符、数组和行的高效读取。
		BufferedReader bufReader = null;
		String fileType=filePath.substring(filePath.length()-3, filePath.length());
		if(fileType.equals("txt")){
			try {
				File file = new File(filePath);
			    // FileReader:用来读取字符文件的便捷类。
				bufReader = new BufferedReader(new FileReader(file));
				// buf = new BufferedReader(new InputStreamReader(new
				// FileInputStream(file)));
				String temp = null;
				while ((temp = bufReader.readLine()) != null) {
					sb.append(temp);
			    }
				rs = sb.toString();
				map.put("image", rs);
			} catch (Exception e) {
			    e.getStackTrace();
			} finally {
			    if (bufReader != null ) {
			        try {
			            bufReader.close();
			        } catch (IOException e) {
			            e.getStackTrace();
			        }
			    }
			}
		}else{
//			filePath=filePath.replaceAll("//", "\\");
//			filePath=filePath.replaceAll("\\\\", "\\/");
			map.put("image", filePath);
		}
		return map;
	}
	
	/**
	 * 获取报告领取前面图片
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, Object> getReportDrawSign2(String id) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		String sql = "select t2.report_sn "+
					 "from tzsb_report_draw t1,tzsb_inspection_info t2 "+
					 "where t1.id = t2.fk_report_draw_id "+
					 "and t1.data_status != '99' "+
					 "and t1.id=? order by t2.report_sn";
		List<Object> list = reportDrawDao.createSQLQuery(sql, id).list();
		map.put("rows", list);
		//获取图片
		String rs = "";
		String filePath = reportDrawDao.get(id).getSign_file();
		
		if(StringUtil.isEmpty(filePath)){
			throw new Exception("未获取到签名图片！");
		}
		StringBuffer sb = new StringBuffer();
	    // BufferedReader:从字符输入流中读取文本，缓冲各个字符，从而实现字符、数组和行的高效读取。
		BufferedReader bufReader = null;
		String fileType=filePath.substring(filePath.length()-3, filePath.length());
		if(fileType.equals("txt")){
			try {
				File file = new File(filePath);
			    // FileReader:用来读取字符文件的便捷类。
				bufReader = new BufferedReader(new FileReader(file));
				// buf = new BufferedReader(new InputStreamReader(new
				// FileInputStream(file)));
				String temp = null;
				while ((temp = bufReader.readLine()) != null) {
					sb.append(temp);
			    }
				rs = sb.toString();
				map.put("image", rs);
			} catch (Exception e) {
			    e.getStackTrace();
			} finally {
			    if (bufReader != null ) {
			        try {
			            bufReader.close();
			        } catch (IOException e) {
			            e.getStackTrace();
			        }
			    }
			}
		}else{
			filePath=filePath.replaceAll("//", "\\");
			String path=filePath.substring((filePath.lastIndexOf("\\")+1),filePath.length());
			String index = filePath.substring(0,filePath.lastIndexOf("\\"));
			String indexs = index.substring(index.lastIndexOf("\\")+1);
			filePath="upload\\report\\sign\\"+indexs+"\\"+path;
			map.put("image", filePath);
		}
		return map;
	}
	
	/**
	 * 获取报告领取前面图片
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, Object> getReportDrawSign3(HttpServletRequest request,String id) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		//获取图片
		String rs = "";
		ReportDraw rd=reportDrawDao.get(id);
		String inspectionInfoId=rd.getInspectionInfo().getId();
		//查询相关签名记录
		List<ReportDrawSign> rds=reportDrawSignDao.getReportDrawSignByInspectionInfo(inspectionInfoId);
		if(rds.isEmpty()){
			throw new Exception("未查到相关签名记录！");
		}
		ReportDrawSign rds1=(ReportDrawSign)rds.get(0);
		map.put("formatReportSn", rds1.getFormatReportSn());//报告号批量格式
		map.put("reportSn", rds1.getReportSn());//报告号完整格式
		String filePath = rds1.getSignRelativeFile();
		if(StringUtil.isEmpty(filePath)){
			throw new Exception("未获取到签名图片！");
		}
		StringBuffer sb = new StringBuffer();
	    // BufferedReader:从字符输入流中读取文本，缓冲各个字符，从而实现字符、数组和行的高效读取。
		BufferedReader bufReader = null;
		String fileType=filePath.substring(filePath.length()-3, filePath.length());
		if(fileType.equals("txt")){
			try {
				File file = new File(filePath);
			    // FileReader:用来读取字符文件的便捷类。
				bufReader = new BufferedReader(new FileReader(file));
				// buf = new BufferedReader(new InputStreamReader(new
				// FileInputStream(file)));
				String temp = null;
				while ((temp = bufReader.readLine()) != null) {
					sb.append(temp);
			    }
				rs = sb.toString();
				map.put("image", rs);
			} catch (Exception e) {
			    e.getStackTrace();
			} finally {
			    if (bufReader != null ) {
			        try {
			            bufReader.close();
			        } catch (IOException e) {
			            e.getStackTrace();
			        }
			    }
			}
		}else{
				try {
					filePath = filePath.replaceAll("\\\\", "\\/");
					String paths = request.getServletContext().getRealPath("/");
					String base64Pic = Base64Utils.ImageToBase64ByLocal(paths + filePath);
					map.put("image", base64Pic);
				} catch (Exception e) {
						e.getStackTrace();
				}
		}
		return map;
	}

}
