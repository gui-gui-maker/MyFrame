package com.lsts.qualitymanage.web;

import com.khnt.bpm.core.support.FinishFlowInf;
import com.khnt.bpm.ext.support.FlowExtWorktaskParam;
import com.khnt.core.crud.web.SpringSupportAction;
import com.khnt.core.crud.web.support.JsonWrapper;
import com.khnt.core.exception.KhntException;
import com.khnt.security.CurrentSessionUser;
import com.khnt.security.util.SecurityUtil;
import com.khnt.utils.StringUtil;
import com.lsts.qualitymanage.bean.QualityUpdateFile;
import com.lsts.qualitymanage.bean.QualityUpdateYijina;
import com.lsts.qualitymanage.dao.QualityUpdateYijinaDao;
import com.lsts.qualitymanage.service.QualityUpdateFileManager;
import com.lsts.qualitymanage.service.QualityUpdateYijinaManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;













import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;


@Controller
@RequestMapping("update/yijina")
public class QualityUpdateYijinaAction extends SpringSupportAction<QualityUpdateYijina, QualityUpdateYijinaManager> {

    @Autowired
    private QualityUpdateYijinaManager  qualityUpdateYijinaManager;
    @Autowired
    private QualityUpdateFileManager  qualityUpdateFileManager;
    @Autowired
    private QualityUpdateYijinaDao qualityUpdateYijinaDao;
    /**
   	 * 添加
   	 * @param response
        * @throws Exception 
   	 */
    @RequestMapping(value = "savesh")
   	@ResponseBody
    public HashMap<String, Object> savesh(HttpServletRequest request,QualityUpdateYijina qualityUpdateYijina) throws Exception {
    	HashMap<String, Object> map = new HashMap<String, Object>();
		CurrentSessionUser user = SecurityUtil.getSecurityUser();
		qualityUpdateYijina.setAuditMan(user.getName());
		qualityUpdateYijina.setAuditTime(new Date());
//		qualityUpdateYijina.setAuditStep(user.getName());
//		qualityUpdateYijina.setFileId(ids);
//		qualityUpdateYijina.setAuditOpinion(qualityUpdateFileManager);
		qualityUpdateYijinaManager.save(qualityUpdateYijina);
    	map.put("success", true);
		return map;

   	}
    
    
    /**审批流程并改变状态
   	 * @param id
   	 * @param flowId
   	 * @param typeCode
   	 * @param status
   	 * @return
   	 * @throws Exception
   	 */
   	@RequestMapping(value = "zltj")
   	@ResponseBody
   	public HashMap<String, Object> zltj(QualityUpdateYijina qualityUpdateYijina,String areaFlag,String id,
   			String typeCode, String status,String activityId) throws Exception {
   		Map<String, Object> map = new HashMap<String, Object>();
   		map.put(FlowExtWorktaskParam.SERVICE_ID, id);
   		map.put(FlowExtWorktaskParam.ACTIVITY_ID, activityId);
   		map.put(FlowExtWorktaskParam.SERVICE_TITLE, "修改申请");
   		map.put(FlowExtWorktaskParam.SERVICE_TYPE, typeCode);
   		map.put(FlowExtWorktaskParam.IS_CURRENT_USER_TASK, true);
   		
   		if (StringUtil.isEmpty(id)) {
   			return JsonWrapper.failureWrapper("参数错误！");
   		} else {
			CurrentSessionUser user = SecurityUtil.getSecurityUser();
   			// 审批流程
   			if (StringUtil.isNotEmpty(activityId)) {
   				if(areaFlag.equals("1") ){//申请单审核
   					qualityUpdateFileManager.doProcess(map);
   					QualityUpdateFile qualityUpdateFile=qualityUpdateFileManager.get(id);
   					qualityUpdateFile.setStatus(QualityUpdateFileManager.ZL_XGSQ_AUDIT);
   					qualityUpdateFile.setSh_man(user.getName());//设置审核人
					qualityUpdateFile.setSh_mandate(new Date());
   					qualityUpdateFileManager.save(qualityUpdateFile);
   					//保存修改单id
//   					String cc = qualityUpdateFile.getId();
//   					qualityUpdateYijina.setFileId(cc);
//   					qualityUpdateYijinaManager.save(qualityUpdateYijina);
   				}else if(areaFlag.equals("5")){//传递单审核
   					qualityUpdateFileManager.doProcess(map);
   					QualityUpdateFile qualityUpdateFile=qualityUpdateFileManager.get(id);
   					qualityUpdateFile.setStatus(QualityUpdateFileManager.ZL_XGSQ_SI);
   					qualityUpdateFileManager.save(qualityUpdateFile);
   					
//   					qualityUpdateYijina=qualityUpdateYijinaManager.get(id);
//   					qualityUpdateYijina.setAuditOpinion(QualityUpdateFileManager.ZL_XGSQ_SI);
//   					qualityUpdateYijinaManager.save(qualityUpdateYijina);
   					
   				}else if(areaFlag.equals("4")){//申请单批准
   					qualityUpdateFileManager.doProcess(map);
   					QualityUpdateFile qualityUpdateFile=qualityUpdateFileManager.get(id);
   					qualityUpdateFile.setStatus(QualityUpdateFileManager.ZL_XGSQ_SAN);
   					qualityUpdateFile.setPz_man(user.getName());
					qualityUpdateFile.setPz_mandate(new Date());
   					qualityUpdateFileManager.save(qualityUpdateFile);
   					
   				}else if(areaFlag.equals("3")){//传递单审批
   					
   					qualityUpdateFileManager.doProcess(map);
   					QualityUpdateFile qualityUpdateFile=qualityUpdateFileManager.get(id);
   					qualityUpdateFile.setStatus(QualityUpdateFileManager.ZL_XGSQ_PASS);
   			   		qualityUpdateFile.setPassTime(new Date());
   					qualityUpdateFileManager.save(qualityUpdateFile);
   					
   				}else{
   					QualityUpdateFile qualityUpdateFile=qualityUpdateFileManager.get(id);
   					qualityUpdateFile.setStatus(QualityUpdateFileManager.ZL_XGSQ_NO_PASS);
   					qualityUpdateFileManager.save(qualityUpdateFile);

   				}
   			} else {
   				return JsonWrapper.failureWrapper("流程ID为空！");
   			}

   			return JsonWrapper.successWrapper(id);
   		}

   	}

   	/**退回审批流程并改变状态
	 * @param id
	 * @param flowId
	 * @param typeCode
	 * @param status
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "xgth")
	@ResponseBody
	public HashMap<String, Object> xgth(QualityUpdateYijina qualityUpdateYijina,String areaFlag,String id,
			String typeCode, String status,String activityId,String processId) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> map1 = new HashMap<String, Object>();

		map.put(FlowExtWorktaskParam.SERVICE_ID, id);
		map.put(FlowExtWorktaskParam.ACTIVITY_ID, activityId);
		map.put(FlowExtWorktaskParam.SERVICE_TITLE, "修改流程");
		map.put(FlowExtWorktaskParam.SERVICE_TYPE, typeCode);
		map.put(FlowExtWorktaskParam.IS_CURRENT_USER_TASK, true);
		
		map1.put(FlowExtWorktaskParam.PROCESS_ID, processId);
		map1.put(FlowExtWorktaskParam.FINISH_TYPE,FinishFlowInf.FINISH_TYPE_TERMINATE);

		QualityUpdateFile qualityUpdateFile=qualityUpdateFileManager.get(id);
		qualityUpdateFile.setStatus(QualityUpdateFileManager.ZL_XGSQ_NO_PASS);
		qualityUpdateFileManager.save(qualityUpdateFile);
		
		if (StringUtil.isEmpty(id)) {
			return JsonWrapper.failureWrapper("参数错误！");
		} else {
			// 退回流程
			if (StringUtil.isNotEmpty(activityId)) {
				qualityUpdateFileManager.stop(map1);
				qualityUpdateYijinaManager.save(qualityUpdateYijina);
			} else {
				return JsonWrapper.failureWrapper("流程ID为空！");
			}
			return JsonWrapper.successWrapper(id);
		}

	}
}