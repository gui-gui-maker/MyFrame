package com.edu.web;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import com.edu.anno.AuthToken;
import com.edu.bean.Plan;
import com.edu.bean.PlanEditApply;
import com.edu.bean.User;
import com.edu.service.PlanEditApplyService;
import com.edu.service.PlanService;
import com.edu.service.impl.RedisService;
import com.edu.util.StringUtil;

import net.sf.json.JSONObject;




@Controller
@RequestMapping("planWeb")
public class PlanWeb {

	@Autowired
	private RedisService redisService;
	@Autowired
	private PlanService planService;
	@Autowired
	private PlanEditApplyService planEditApplyService;
	
	@InitBinder
    public void initBinder(WebDataBinder binder, WebRequest request) {
        //转换日期 注意这里的转化要和传进来的字符串的格式一直 如2015-9-9 就应该为yyyy-MM-dd
        DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));// CustomDateEditor为自定义日期编辑器
    }
	
	@RequestMapping(value="viewAllPlans")
	@AuthToken(hasRole = { "admin"})
	public String toJyJh() {
		
		return "PlanList";
	}
	@RequestMapping(value="viewUniversityPlans")
	@AuthToken(hasRole = { "user" },profile=true)
	public String viewUniversityPlans(HttpServletRequest request) {
		//院校代号
		User user = (User) redisService.get(request.getSession().getId());
		request.setAttribute("yxdm",user.getProfiles().iterator().next().getYxdm());
		return "PlanUniversityList";
	}
	@RequestMapping(value="viewPlanEditApplies")
	@AuthToken(hasRole = {"admin"})
	public String viewPlanEditApplies(HttpServletRequest request) {
		//院校代号
		return "PlanEditApplyCheck";
	}
	
	/**
	 * 获取分页数据（所有）
	 * @param page
	 * @param pageSize
	 * @param jyjh
	 * @return
	 */
	@RequestMapping(value="list")
	@ResponseBody
	@AuthToken
	public HashMap<String,Object> findByCondition (
			HttpServletRequest request,
			@RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "rows", defaultValue = "10") Integer pageSize,
            Plan temp) throws Exception {
		
		HashMap<String,Object> map =new HashMap<String,Object>();
		Sort sort = new Sort(Sort.Direction.DESC, "yxdm");
	    Pageable pageable = PageRequest.of(page-1, pageSize, sort);
		Page<Plan> rows;
		
		try {
			rows = planService.findByCondition(temp,pageable);
			map.put("total",rows.getTotalElements());
			map.put("rows",rows.getContent());
		} catch (Exception e) {
			e.printStackTrace();
			map.put("total",0);
			map.put("rows",null);
		}
		return map;
	}
	//展开根据计划id查询修改申请
	@RequestMapping(value="getPlanEditApply")
	@ResponseBody
	public HashMap<String,Object> getPlanEditApply (HttpServletRequest request,String id) throws Exception {
		
		HashMap<String,Object> map =new HashMap<String,Object>();
		List<PlanEditApply> apps = planEditApplyService.findByFid(id);
		map.put("total",apps.size());
		map.put("rows",apps);
		return map;
	}
	//查询所有（有院校申请）未处理完毕的计划
	@RequestMapping(value="getAllApplyPlan")
	@ResponseBody
	public HashMap<String,Object> getAllApplyPlan (HttpServletRequest request) throws Exception {
		
		HashMap<String,Object> map =new HashMap<String,Object>();
		List<Plan> apps = planService.findAllApplyPlan();
		map.put("total",apps.size());
		map.put("rows",apps);
		return map;
	}
	//保存已处理
	@RequestMapping(value="ckeckApply")
	@ResponseBody
	public HashMap<String,Object> ckeckApply(HttpServletRequest request,PlanEditApply apply) throws Exception {
		
		HashMap<String,Object> map =new HashMap<String,Object>();
		User user = (User) redisService.get(request.getSession().getId());
		apply.setStatus("1");
		apply.setCheckBy(user.getUsername());
		apply.setCheckTime(new Date());
		
		planEditApplyService.save(apply,user);
		map.put("success",true);
		return map;
	}

	@RequestMapping(value="save")
	@ResponseBody
	public HashMap<String,Object> save(HttpServletRequest request,Plan plan) throws Exception{
		
		HashMap<String,Object> map =new HashMap<String,Object>();
		try {
			User user = (User) redisService.get(request.getSession().getId());
			if(StringUtil.isEmpty(plan.getId())) {
				plan.setJhid(user.getUsername() + plan.getJhid());
			}
			plan.setXgbj(plan.getXgbj()+1);
			//院校代号
			plan.setLastUpdateBy(user.getUsername());
			plan.setLastUpdateTime(new Date());
			plan.setStatus("0");
			planService.save(plan);
			map.put("success",true);
			map.put("data", plan);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success",false);
			map.put("msg",e.getMessage());
		}
		return map;
	}
	@RequestMapping(value="editApply")
	@ResponseBody
	public HashMap<String,Object> editApply(HttpServletRequest request,String apply) throws Exception {
		HashMap<String,Object> map =new HashMap<String,Object>();
		User user = (User) redisService.get(request.getSession().getId());
		planEditApplyService.save(apply,user);
		map.put("success", true);
		return map;
	}
	
	/**
	 * 逻辑删除
	 * @param ids
	 * @return
	 */
	@RequestMapping(value="ldel")
	@ResponseBody
	@AuthToken
	public HashMap<String,Object> ldel(String ids) {
		HashMap<String,Object> map =new HashMap<String,Object>();
		try {
			
			planService.ldel(ids);
			map.put("success",true);
			map.put("data",ids);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("msg",e.getMessage());
			map.put("success",false);
		}
		return map;
	}
	
}
