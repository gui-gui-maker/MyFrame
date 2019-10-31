package com.khnt.oa.car.web;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.khnt.core.crud.web.SpringSupportAction;
import com.khnt.core.crud.web.support.JsonWrapper;
import com.khnt.oa.car.bean.Apply;
import com.khnt.oa.car.bean.CarAllCost;
import com.khnt.oa.car.bean.CarConsume;
import com.khnt.oa.car.service.ApplyManager;
import com.khnt.oa.car.service.TempSql;
import com.khnt.pub.worktask.service.WorktaskManager;
import com.khnt.security.CurrentSessionUser;
import com.khnt.security.util.SecurityUtil;
import com.khnt.utils.StringUtil;

/**
 * <p>
 * 类说明
 * </p>
 * 
 * @ClassName OrgAction
 * @JDK 1.5
 * @author
 * @date
 */
@Controller
@RequestMapping("oa/car/apply/")
public class ApplyAction extends SpringSupportAction<Apply, ApplyManager> {

	@Autowired
	private ApplyManager applyManager;

	@Autowired
	private WorktaskManager workTaskManager;

	@RequestMapping(value = "saveApply")
	@ResponseBody
	public HashMap<String, Object> saveApply(HttpServletRequest request, Apply apply) throws Exception {
		if (StringUtil.isEmpty(apply.getId())) {
			if("".equals(apply.getState())||apply.getState()==null){
				apply.setState("草稿");
				apply.setApplitorCode(this.getCurrentUser().getUserId());
				apply.setApplitorName(this.getCurrentUser().getName());
				apply.setApplitorTime(new Date());
			}else{
				apply.setState("未派车");
				apply.setApplitorTime(new Date());
			}
		}
		Date stratTime=apply.getStartTime();
		Date endTime=apply.getEndTime();
		String carid=apply.getCar().getId();
		if (this.applyManager.isUsed(carid, stratTime, endTime)){
			return super.save(request, apply);
		}
		else{
			return  JsonWrapper.failureWrapperMsg("该时间段内车辆被占用,请参照车辆使用情况选择时间!");
		}
		
	}

	@RequestMapping(value = "sendCar")
	@ResponseBody
	public HashMap<String, Object> sendCar(String id, Apply apply) throws Exception {
	   if (this.applyManager.sendCar(id, apply, this.getCurrentUser())) {
			return JsonWrapper.successWrapper();
		} else {
			return JsonWrapper.failureWrapper();
		}
	}

	@RequestMapping(value = "submit")
	@ResponseBody
	public HashMap<String, Object> submit(String ids, String editor, String editorCode) throws Exception {
		if (this.applyManager.submit(ids, editor, editorCode)) {
			return JsonWrapper.successWrapper();
		} else {
			return JsonWrapper.failureWrapper();
		}
	}

	@RequestMapping(value = "handle")
	@ResponseBody
	public HashMap<String, Object> handle(String id, String state, String remark) throws Exception {
		try{
			Apply app = applyManager.get(id);

			if ("同意".equals(state)) {
				app.setState("未派车");
			} else {
				app.setState("不同意");
			}
			app.setDestinationTime(new Date());
			CurrentSessionUser user=SecurityUtil.getSecurityUser();
			app.setDestinationMan(user.getName());
			app.setDestinationManId(user.getId());
			applyManager.save(app);
			// 修改代办
			workTaskManager.changeWorktaskStatus(app.getId(), user.getId(),WorktaskManager.STATUS_PROCESSED);
			return JsonWrapper.successWrapper(app);
		}
		catch(Exception e){
			return JsonWrapper.failureWrapperMsg("用车申请处理出错："+e.getMessage());
		}
	}

	@RequestMapping(value = "end")
	@ResponseBody
	public HashMap<String, Object> end(String id, Apply apply) throws Exception {
		if (this.applyManager.end(id, apply)) {
			return JsonWrapper.successWrapper();
		} else {
			return JsonWrapper.failureWrapper();
		}
	}

	@RequestMapping(value = "isUsed")
	@ResponseBody
	public HashMap<String, Object> isUsed(String carid, Date start, Date end) throws Exception {
		if (this.applyManager.isUsed(carid, start, end)) {
			return JsonWrapper.successWrapper();
		} else {
			return JsonWrapper.failureWrapper();
		}
	}

	@RequestMapping(value = "getSql")
	@ResponseBody
	public HashMap<String, Object> getSql(String month) throws Exception {
		TempSql.setSql(month);
		return JsonWrapper.successWrapper();
	}
	
	/**
	 * 统计指定月份的车辆消耗情况
	 *
	 * @param month 指定的月份
	 * @return
	 */
	@RequestMapping(value = "countCarConsume")
	@ResponseBody
	public HashMap<String, Object> countCarConsume(HttpServletRequest request, String month) {
		try {
			month=request.getParameter("month");
			List<CarConsume> result = applyManager.countCarConsume(month);
			return JsonWrapper.successWrapper(result);
		} catch (Exception e) {
			log.error(e.getClass().getName(), e);
			return JsonWrapper.failureWrapper();
		}
	}

	/**
	 * 统计指定年份中某月的车辆产生的费用情况
	 *
	 * @param month 指定的月份
	 * @return
	 */
	@RequestMapping(value = "countCarAllCost")
	@ResponseBody
	public HashMap<String, Object> countCarAllCost(HttpServletRequest request, String month) {
		HashMap<String, Object> wrapper = new HashMap<String, Object>();
		try {
			String year=request.getParameter("year");
			month=request.getParameter("month");
			List<CarAllCost> result = applyManager.countCarAllCost(year,month);
			wrapper.put("success", true);
			wrapper.put("Rows", result);
		} catch (Exception e) {
			log.error(e.getClass().getName(), e);
			wrapper.put("error", true);
		} finally {
			return wrapper;
		}
	}
	
	/**
	 * 导出的车辆使用记录
	 * @return
	 */
	@RequestMapping(value = "exportHis")
	@ResponseBody
	public ModelAndView exportHis(HttpServletRequest request, String ids) {
		
		Map<String, Object> wrapper = new HashMap<String, Object>();
		try {
			List<Map<String, Object>> list = applyManager.exportHis(ids);
			wrapper.put("result", list);
			wrapper.put("success", true);
		} catch (Exception e) {
			log.error(e.getClass().getName(), e);
			wrapper.put("error", true);
		} finally {
			return new ModelAndView("app/oa/car/car_history_excel",wrapper);
		}
	}
	@RequestMapping(value = "confirm")
	@ResponseBody
	public HashMap<String, Object> confirm(String id) throws Exception {
		if (this.applyManager.confirm(id)) {
			return JsonWrapper.successWrapper();
		} else {
			return JsonWrapper.failureWrapper();
		}
	}
}
