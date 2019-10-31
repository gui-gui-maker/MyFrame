package com.lsts.mobileapp.code.web;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.khnt.core.crud.web.SpringSupportAction;
import com.khnt.utils.StringUtil;
import com.lsts.mobileapp.code.bean.Dict;
import com.lsts.mobileapp.code.service.DictService;

@Controller
@RequestMapping("dictAction")
public class DictAction extends SpringSupportAction<Dict,DictService>{
	@Autowired
	DictService dictService;
	
	@RequestMapping(value="getDictData")
	@ResponseBody
	public HashMap<String, Object> getDictData(HttpServletRequest request) throws Exception{
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		String code = request.getParameter("code");
		String sql = request.getParameter("sql");
		
		try {
			List list = null;
			if(!StringUtil.isEmpty(code)){
				list = dictService.getDictByCode(code);
			}else if(!StringUtil.isEmpty(sql)){
				list = dictService.getDictBySql(sql);
			}
			System.out.println(list.size());
			map.put("data", list);
			map.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
			map.put("msg", e.getMessage());
		}
		return map;
	}
	
	@RequestMapping(value="getDeptTree")
	@ResponseBody
	public HashMap<String,Object> getDeptTree() throws Exception{
		HashMap<String, Object> map = new HashMap<String, Object>();
		String sql = "select t.id,t.pid,t.code, t.text from (select o.id as id, o.id as code, replace(o.org_code,'j','a')  as tcode, o.ORG_NAME as text, o.PARENT_ID as pid from sys_org o where o.id != '100049' union select t1.id as id,t1.id as code, replace(e.code,'j','a') as tcode, t1.NAME as text, t1.ORG_ID as pid from employee e,sys_user t1,EMPLOYEE_PERMISSIONS ep where e.id=t1.employee_id and e.id = ep.fk_employee_id and ep.is_audit_man = '0' and t1.status='1' and t1.id in (select s.user_id from sys_user_role s ,Sys_Role r where r.id=s.role_id and r.name='报告审核') union select t2.id as id, t2.id as code, replace(e2.code, 'j', 'a') as tcode, t2.NAME as text, p2.ORG_ID as pid from employee e2, sys_user t2, SYS_EMPLOYEE_POSITION p1, SYS_POSITION p2, EMPLOYEE_PERMISSIONS ep2 where e2.id = t2.employee_id and p1.position_id = p2.id and p1.employee_id = e2.id and (p2.org_id = '100091') and e2.id = ep2.fk_employee_id and ep2.is_audit_man = '0' and t2.status='1' and t2.id in (select s.user_id from sys_user_role s, Sys_Role r where r.id = s.role_id and r.name = '报告审核')) t start with t.id in ('100020','100021','100022','100023','100024','100026','100034','100035','100033','100036','100037','100062','100045','100066','100063','100065','100067','100090','100091') connect by t.pid = prior t.id ORDER BY T.TCODE";											
		List<Object[]> list = null;
		try {
			list = dictService.getDeptTree(sql);
			map.put("data", list);
			map.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
			map.put("msg", e.getMessage());
		}
		return map;

	}
}
