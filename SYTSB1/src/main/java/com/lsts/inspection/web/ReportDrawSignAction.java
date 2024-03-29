package com.lsts.inspection.web;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ContextLoader;

import com.khnt.core.crud.web.SpringSupportAction;
import com.khnt.core.crud.web.support.JsonWrapper;
import com.khnt.core.exception.KhntException;
import com.khnt.utils.StringUtil;
import com.lsts.inspection.bean.ReportDrawSign;
import com.lsts.inspection.service.ReportDrawSignService;
import com.scts.payment.service.PayInfoCache;
import com.scts.push.service.PushManager;

import net.sf.json.JSONObject;
import util.Base64Utils;

@Controller
@RequestMapping("reportDrawSignAction")
public class ReportDrawSignAction extends SpringSupportAction<ReportDrawSign,ReportDrawSignService>{

	@Autowired
	ReportDrawSignService reportDrawSignService;
	@Autowired
	PushManager pushManager;
	
	/**
     * 报告领取签字推送，增加电话，签字图片推送，
     * @param fkInspectionInfoId
     * @param pulldown_op
     * @param linkmode
     * @param report_sn
     * @param cid
     * @return
     * @throws Exception
     */
    @RequestMapping("pushSignature")
    @ResponseBody
    public HashMap<String, Object> pushSignature(HttpServletRequest request,String reportDrawSignId, 
    		String fkInspectionInfoId, String report_sn,String format_report_sn, String invoice_no,
    		String pulldown_op,String linkmode,String cid) throws Exception {
    	//推送时添加费时签名为默认签名guido 2018-12-20修改
    	HashMap<String, Object> warp = new HashMap<String, Object>();
        try{
        	Map<String, Object> map = new HashMap<String, Object>();
        	ReportDrawSign rds = null;
            if(StringUtil.isNotEmpty(reportDrawSignId)){
            	rds = reportDrawSignService.get(reportDrawSignId);
            	//rds.setPulldown_op(pulldown_op);
            	if(!StringUtil.isEmpty(linkmode)){
            		rds.setPulldown_tel(linkmode);
            	}
            	//重新推送没有传这个参数，不更新
            	if(!StringUtil.isEmpty(format_report_sn))
            	{
            		rds.setFormatReportSn(format_report_sn);
            	}
            	rds.setStatus("1");
            	reportDrawSignService.save(rds);
            	map.put("reportDrawSignId", rds.getId());
            	map.put("isRepeat", true);
            }else{
            	//根本报检业务数据表id查询是否已经签名推送
            	List<ReportDrawSign> rdss = reportDrawSignService.getByFkInspectionInfoId(fkInspectionInfoId);
            	//定义签名推送id
            	if (rdss.size() > 0) {
            		rds = rdss.get(0);
            		if(!StringUtil.isEmpty(invoice_no)){
                		rds.setInvoiceNo(invoice_no);
                	}
            		if(!StringUtil.isEmpty(linkmode)){
                		rds.setPulldown_tel(linkmode);
                	}
            		if(!StringUtil.isEmpty(format_report_sn))
                	{
                		rds.setFormatReportSn(format_report_sn);
                	}
            		//rds.setPulldown_op(pulldown_op);
            		rds.setStatus("1");
            		reportDrawSignService.save(rds);
            		//map.put("isRepeat", true);
            	} else {
            		rds = new ReportDrawSign();
            		rds.setFkInspectionInfoId(fkInspectionInfoId);
            		rds.setReportSn(report_sn);
            		rds.setFormatReportSn(format_report_sn);
            		rds.setInvoiceNo(invoice_no);
            		//rds.setPulldown_op(pulldown_op);
                	rds.setPulldown_tel(linkmode);
            		rds.setCretatdDate(new Date());
            		rds.setStatus("0");
            		reportDrawSignService.save(rds);
            	}
            	map.put("reportDrawSignId", rds.getId());
            }
            JSONObject obj = new JSONObject();
            obj.put("title", "推送测试");
            obj.put("userName", "");
            obj.put("state", 0);
            obj.put("pageUrl", "../../app/report/newSign.html");
            obj.put("pageId", "../../app/report/newSign.html");
            obj.put("toastMsg", "state 2 需要弹出的提示消息");
            obj.put("isDialog", false);
            obj.put("pageFunction", "recivemsg");
            obj.put("params", map);
            String title = "四川省特种设备检验研究院报告领取记录";
            String content = "四川省特检院检验报告（定检/验收） 1 份，请签字确认。";
            //Boolean flag = MsgUtil.pusMsgToSingle(appId, appKey, masterSecret, cid, obj.toString().replaceAll("\"", "\'"),title,content);
            //报告领取消息推送id和报告书编号保存到report_draw_sign表

            List<String> cids = new ArrayList<String>();
            cids.add(cid);
            pushManager.pusMsgToTargets(obj, title, content, "检验报告签字确认", cids);
            
            warp.put("success",true);
        	warp.put("data",rds);
        }catch(Exception e){
        	e.printStackTrace();
        	warp.put("success",false);
        	warp.put("msg",e.getMessage());
        }
        return warp;
    }

	@RequestMapping(value="detailAndGetImg")
	@ResponseBody
	public HashMap<String, Object> detailAndGetImg(HttpServletRequest request, String id) throws Exception {
		// TODO Auto-generated method stub
		HashMap<String, Object> map = new HashMap<String, Object>();
		try{
			//内存存储保存操作
			ServletContext context = ContextLoader.getCurrentWebApplicationContext().getServletContext();
			@SuppressWarnings("unchecked")
			ConcurrentHashMap<String, Object> draw = (ConcurrentHashMap<String, Object>)context.getAttribute("reportDraw");
			if(null == draw || null == draw.get(id))
			{
				map.put("success",false); 
				map.put("msg", "没有签名！"); 
				return map; 
			}
			else 
			{
				draw.remove(id);
			}
			map = super.detail(request, id);
			ReportDrawSign rds = (ReportDrawSign)map.get("data");
			String filePath = rds.getSignRelativeFile();
			if(!StringUtil.isEmpty(filePath)){
				//base64 string
				String rs = "";
				StringBuffer sb = new StringBuffer();
				// BufferedReader:从字符输入流中读取文本，缓冲各个字符，从而实现字符、数组和行的高效读取。
				BufferedReader bufReader = null;
				String fileType = filePath.substring(filePath.length()-3, filePath.length());
				if(fileType.equals("txt")){
					try {
						File file = new File(filePath);
						// FileReader:用来读取字符文件的便捷类。
						bufReader = new BufferedReader(new FileReader(file));
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
					filePath=filePath.replaceAll("\\\\", "\\/");
					String paths = request.getServletContext().getRealPath("/");
					String base64Pic = Base64Utils.ImageToBase64ByLocal(paths+filePath);
					map.put("image", base64Pic);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			map.put("success", false);
			map.put("msg", e.getMessage());
		}
		return map;
	}
    
    
}
