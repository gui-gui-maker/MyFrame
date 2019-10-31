package com.scts.discipline.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.khnt.base.Factory;
import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.khnt.core.exception.KhntException;
import com.khnt.security.CurrentSessionUser;
import com.khnt.security.util.SecurityUtil;
import com.khnt.utils.StringUtil;
import com.scts.discipline.PhoneSysUtil;
import com.scts.discipline.bean.DisciplineCall;
import com.scts.discipline.bean.DisciplinePlan;
import com.scts.discipline.dao.DisciplineCallDao;
import com.scts.discipline.dao.DisciplinePlanDao;
import com.scts.discipline.dao.DisciplineUserDao;

@Service("disciplineCallService")
public class DisciplineCallService extends EntityManageImpl<DisciplineCall, DisciplineCallDao> {

	@Autowired
	private DisciplineCallDao disciplineCallDao;
	@Autowired
	private DisciplineUserDao disciplineUserDao;
	@Autowired
	private DisciplinePlanDao disciplinePlanDao;

	public HashMap<String, Object> saveCall(DisciplineCall entity) {

		HashMap<String, Object> map = new HashMap<String, Object>();
		
		CurrentSessionUser user = SecurityUtil.getSecurityUser();
		String phone = disciplineUserDao.getPhoneByUser(user.getId());
		if(phone==null) {
			throw new KhntException("您没有分配分机号，请联系系统管理员！");
		}
		JSONObject jsons = getState(phone);
		
		if(jsons!=null&&jsons.get("state").toString().contains("通话中")) {
			throw new KhntException("分机占线中，请先挂机！");
		}
		
		
		entity.setCreate_date(new Date());
		entity.setCreate_op(user.getName());
		entity.setCreate_op_id(user.getId());
		entity.setPhone(phone);
		
		DisciplinePlan plan=disciplinePlanDao.get(entity.getBusiness_id());
		if(plan!=null){
			entity.setCreate_org_name(plan.getUnit());
		}
		
		
		
		HashMap<String, Object> callResult = PhoneSysUtil.call(entity.getPhone_number(), phone);
		String result = callResult.get("data").toString();
		if(StringUtil.isNotEmpty(result)) {
			JSONObject json = JSONObject.parseObject(result);
			map.put("result", json);
			if(json.containsKey("id")) {
				String id = json.getString("id");
				if(entity.getCall_type()==null||StringUtil.isEmpty(entity.getCall_type())) {
					entity.setCall_type("0");
				}
				
				entity.setIs_connect("1");
				entity.setCall_id(id);
			}else {
				throw new KhntException("呼叫失败！");
			}
			
		}
		
		disciplineCallDao.save(entity);
		map.put("call", "entity");
		map.put("id", entity.getId());
		
		return map;
		
	}

	public HashMap<String, Object> shutDownCall(String id) {
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		DisciplineCall entity = disciplineCallDao.get(id);
		String phone = entity.getPhone();
		 
		HashMap<String, Object> callResult = PhoneSysUtil.shutDown(phone);
		String result = callResult.get("data").toString();
		if(StringUtil.isNotEmpty(result)) {
			JSONObject json = JSONObject.parseObject(result);
			map.put("result", json);
			if(json.containsKey("ret")&&"1".equals(json.getString("ret"))) {
				String ret = json.getString("ret");
				
			}else {
				throw new KhntException("挂断失败！");
			}
			
		}
		map.put("entity",entity);
		return map;
	}

	public HashMap<String, Object> getJudgeGrade(String id) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		//延时50秒继续
		Thread.sleep(50000);
		/*
		 * {"cmd":"queryrec","count":1,
		 * "list":[{"callee":"913548199448","callee_addr":"",
		 * "callee_user":"","caller":"600","caller_addr":"",
		 * "caller_user":"","dtmf":"S1","id":"101422250-06-001",
		 * "rowNum":"1","score":"100","timefade":"12300",
		 * "timestart":"2018-4-23 10:14:22","timetalk":"12300",
		 * "url":"rec/2018/04/23/20180423101422-600-913548199448-001.wav"}],
		 * "page":1,"page_max":1,"ret":1}
		*/
		
		DisciplineCall entity = disciplineCallDao.get(id);
		String phone = entity.getPhone();
		 
		HashMap<String, Object> callResult = PhoneSysUtil.queryRec(entity.getCall_id(), null, null, null, null, null, null);
		String result = callResult.get("data").toString();
		//String down = callResult.get("down").toString();
		
		if(StringUtil.isNotEmpty(result)) {
			JSONObject json = JSONObject.parseObject(result);
			map.put("result", json);
			if(json.containsKey("ret")&&"1".equals(json.getString("ret"))) {
				if("1".equals(json.getString("count"))) {
					JSONArray attr = json.getJSONArray("list");
					JSONObject list1 = attr.getJSONObject(0);
					
					String timetalk = list1.getString("timetalk");
					String timefade = list1.getString("timefade");
					String dtmf = list1.getString("dtmf");
					//String url = list1.getString("url");
					/*if(StringUtil.isNotEmpty(down)) {
						JSONObject downJson = JSONObject.parseObject(down);
						if(downJson.containsKey("filePath")) {
							String url = downJson.getString("filePath");
							entity.setPath(url);
						}
					}*/
					String filePath = callResult.get("filePath")==null?"":callResult.get("filePath").toString();
					entity.setPath(filePath);
					entity.setCall_mins(timetalk);
					entity.setJudge_grade(dtmf);
				}
					
				
				
				
			}else {
				throw new KhntException("查询失败！");
			}
			
		}
		
		disciplineCallDao.save(entity);
		map.put("entity", entity);
		return map;
	}
	
	public HashMap<String, Object> saveCallOther(DisciplineCall entity) {

		String otherPhone = entity.getOther_phone();
		entity = disciplineCallDao.get(entity.getId());
		entity.setOther_phone(otherPhone);
		entity.setCall_type("2");
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		CurrentSessionUser user = SecurityUtil.getSecurityUser();
		String phone = Factory.getSysPara().getProperty("phones");
		String[] phones = phone.split(",");
		for (int i = 0; i < phones.length; i++) {
			if(!entity.getPhone().equals(phones[i])) {
				phone = phones[i];
			}
		}
		JSONObject jsons = getState(phone);
		
		if(jsons!=null&&jsons.get("state").toString().contains("通话中")) {
			throw new KhntException("其他分机占线中，不能完成转呼！");
		}

		
		HashMap<String, Object> callResult = PhoneSysUtil.callOther(entity.getOther_phone(), entity.getPhone());
		String result = callResult.get("data").toString();
		if(StringUtil.isNotEmpty(result)) {
			JSONObject json = JSONObject.parseObject(result);
			map.put("result", json);
			if(json.containsKey("id")) {
				String id = json.getString("id");
				if(entity.getCall_type()==null||StringUtil.isEmpty(entity.getCall_type())) {
					entity.setCall_type("0");
				}
				
				entity.setIs_connect("1");
				entity.setCall_id(id);
			}else {
				throw new KhntException("呼叫失败！");
			}
			
		}
		
		disciplineCallDao.save(entity);
		map.put("call", "entity");
		map.put("id", entity.getId());
		
		return map;
		
	}
	
	public JSONObject getState(String phone) {
		JSONObject json = null;

		HashMap<String, Object> callResult = PhoneSysUtil.state(phone);
		String result = callResult.get("data").toString();
		if(StringUtil.isNotEmpty(result)) {
			json = JSONObject.parseObject(result);
		}
		return json;
	}

	public Map<String, Object[]> queryComCall(String mkid, String mtid) {
		Map<String,Object[]> map =new HashMap<String,Object[]>();
		String sql = "select c.COM_TYPE,c.com_name,c.com_contact_man, c.com_tel "+
					 "from base_company_info c "+
					 "where c.id = ?";
		
		List<Object[]> list1 = disciplineCallDao.createSQLQuery(sql, mkid).list();
		List<Object[]> list2 = disciplineCallDao.createSQLQuery(sql, mtid).list();
		map.put("mk", list1.size()>0?list1.get(0):new Object[]{});
		map.put("mt", list2.size()>0?list2.get(0):new Object[]{});
		return map;
	}
	/**
	 * 保存呼入的记录
	 * @param beginTime
	 * @param endTime
	 */
	public void saveCallIn(){
//		CurrentSessionUser user = SecurityUtil.getSecurityUser();

		Calendar calendar = Calendar.getInstance();
	    calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) - 1);
	    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
		HashMap<String, Object> callResult = PhoneSysUtil.queryRecHr( "600", df.format(calendar.getTime()),df.format(new Date()), null, "呼入", null);
		HashMap<String, Object> callResult1 = PhoneSysUtil.queryRecHr( "601",  df.format(calendar.getTime()), df.format(new Date()), null, "呼入", null);
//		String result = callResult.get("data").toString();
//		String result601=callResult1.get("data").toString();
		
		
		String result=null;
		if(callResult.isEmpty()&&callResult.get("data")!=null){
			 result = callResult.get("data").toString();
		}
		String result601=null;
		if(callResult1.isEmpty()){
			result601=callResult1.get("data").toString();
		}
		if(StringUtil.isNotEmpty(result)){
			JSONObject json = JSONObject.parseObject(result);
			JSONArray attr = json.getJSONArray("list");
			if(attr!=null){
				for (int j = 0; j < attr.size(); j++) {
					JSONObject list = attr.getJSONObject(j);
					DisciplineCall bean=new DisciplineCall();
					bean.setPhone_number(list.getString("callee"));//呼叫号码
					bean.setCall_id(list.getString("id"));
					bean.setCall_mins(list.getString("timetalk"));
//					bean.setCreate_op(user.getName());
//					bean.setCreate_op_id(user.getId());
					bean.setCreate_date(new Date());
					bean.setCall_type("1");
					bean.setPhone("600");
					bean.setIs_connect("1");
					bean.setJudge_grade(list.getString("dtmf"));
					String filePath = callResult.get(list.getString("id")+"filePath")==null?"":callResult.get(list.getString("id")+"filePath").toString();
					bean.setPath(filePath);
					disciplineCallDao.save(bean);
				}
				}
			}
			
		if(StringUtil.isNotEmpty(result601)){
			JSONObject json = JSONObject.parseObject(result601);
			JSONArray attr = json.getJSONArray("list");
			if(attr!=null){
				for (int j = 0; j < attr.size(); j++) {
					JSONObject list = attr.getJSONObject(j);
					DisciplineCall bean=new DisciplineCall();
					bean.setPhone_number(list.getString("callee"));//呼叫号码
					bean.setCall_id(list.getString("id"));
					bean.setCall_mins(list.getString("timetalk"));
//					bean.setCreate_op(user.getName());
//					bean.setCreate_op_id(user.getId());
					bean.setCreate_date(new Date());
					bean.setCall_type("1");
					bean.setPhone("601");
					bean.setIs_connect("1");
					bean.setJudge_grade(list.getString("dtmf"));
					String filePath = callResult.get(list.getString("id")+"filePath")==null?"":callResult.get(list.getString("id")+"filePath").toString();
					bean.setPath(filePath);
					disciplineCallDao.save(bean);
				}
				}
			}
	}
}
