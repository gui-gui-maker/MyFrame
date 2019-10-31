package com.lsts.employee.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.khnt.core.exception.KhntException;
import com.khnt.security.CurrentSessionUser;
import com.khnt.security.util.SecurityUtil;
import com.khnt.utils.StringUtil;
import com.lsts.advicenote.service.MessageService;
import com.lsts.advicenote.service.MsgLinkAuditService;
import com.lsts.constant.Constant;
import com.lsts.employee.bean.Allowancefo;
import com.lsts.employee.bean.Employeeallowance;
import com.lsts.employee.dao.AllowancefoDao;
import com.lsts.employee.dao.EmployeeallowanceDao;


import util.TS_Util;

@Service("employeeallowanceService")
public class EmployeeallowanceService extends EntityManageImpl<Employeeallowance, EmployeeallowanceDao> {
	@Autowired
	EmployeeallowanceDao employeeallowanceDao;
	@Autowired
	AllowancefoDao allowancefoDao;

	@Autowired
	private MessageService messageService;
	@Autowired
	private MsgLinkAuditService msgLinkAuditService;

	@Override
	public void save(Employeeallowance entity) throws Exception {

		if (entity.getId() == null||StringUtil.isEmpty(entity.getId())) {
			entity.setId(null);
			CurrentSessionUser user = SecurityUtil.getSecurityUser();
			entity.setData_status("1");
			entity.setFlow_step("0");
			entity.setEnter_op(user.getName());
			entity.setEnter_op_id(user.getId());
			entity.setEnter_time(new Date());
			

		}
		
		String[] ids = entity.getOther_applicants_id().split(",");
		List<String> n = new ArrayList<String>();
		// 遍历所有的申请人的ID，依次判断所属部门并存储到list里面
		for (String id : ids) {
			n.add(this.subAuditOp(id));
		}
		/*// 判断list里的所属部门的最低权限
		if (n.contains("narmal_person")) {
			entity.setRole_flag("narmal_person");
		} else if (n.contains("depart_manager")) {
			entity.setRole_flag("depart_manager");
		} else if (n.contains("personnel_manager")) {
			entity.setRole_flag("personnel_manager");
		} else if (n.contains("leader_manager")) {
			entity.setRole_flag("leader_manager");
		} else if (n.contains("dean_manager")) {
			entity.setRole_flag("dean_manager");
		}
		*/
		if (n.contains("leader_manager")) {
			//申请人中有分管领导
			entity.setRole_flag("leader_manager");
			entity.setRole_status("1");
		}else {
			entity.setRole_status("0");
		}
		
		employeeallowanceDao.save(entity);
		String month = new SimpleDateFormat("yyyy-MM").format(entity.getEnter_time());
		List<Allowancefo> allowancefos = entity.getAllowancefos();
		if(allowancefos!=null){
			for (int i = 0; i < allowancefos.size(); i++) {
				Allowancefo del = allowancefos.get(i);
				
				//查看本月申请的补助金额，用于限制300
				float hadApply = checkMonthLimit(del,month);
				if(del.getSubsidy_money()!=null) {
					float nowApply = Float.parseFloat(del.getSubsidy_money());
					if( ( hadApply + nowApply ) > 300) {
						throw new KhntException(del.getOther_applicants()+"本月申请的补助超过限额300，已申请的金额为："+hadApply+"元！");
					}
				}
				
				del.setEmployeeallowance(entity);
				allowancefoDao.save(del);
			}
		}
	}

	
	
	private float checkMonthLimit(Allowancefo del,String month) {
		float pre = 0;
		String id = del.getOther_applicants_id();
		String sql = "select sum(to_number(a.subsidy_money)) from TJY2_ALLOWANCE_FOR_OVERTIME t,tjy2_allowancefo a where a.fk_plan_id = t.id "
				+ "and to_char(t.enter_time,'yyyy-MM')=? and t.data_status <>'99' and a.other_applicants_id=? ";
		@SuppressWarnings("unchecked")
		List<Object> list = employeeallowanceDao.createSQLQuery(sql, month,id).list();
		if(list.size()>0&&list.get(0)!=null) {
			pre = Float.parseFloat(list.get(0).toString());
		}
		return pre;
		
	}



	public String subAuditOp(String applicantsId) {
		String name = "";
		boolean flag = true;
		int size = employeeallowanceDao
				.createSQLQuery("select u.id,u.name from sys_user u,sys_role r,sys_user_role ur "
						+ "where ur.user_id = u.id and ur.role_id = r.id and r.name = '院长'"
						+ " and u.id = ?", applicantsId)
				.list().size();
		if (size > 0) {
			flag = false;
			name = "dean_manager";
			return name;
		}
		if (flag) {
			int size2 = employeeallowanceDao
					.createSQLQuery("select u.id,u.name from sys_user u,sys_role r,sys_user_role ur "
							+ "where ur.user_id = u.id and ur.role_id = r.id and r.name = '院领导'"
							+ " and u.id = ?", applicantsId)
					.list().size();
			if (size2 > 0) {
				flag = false;
				name = "leader_manager";
				return name;
			}
		}
		if (flag) {
			int size3 = employeeallowanceDao
					.createSQLQuery("select u.id,u.name from sys_user u,sys_role r,sys_user_role ur  "
							+ "where ur.user_id = u.id and ur.role_id = r.id and r.name = '人事查看'"
							+ " and u.id = ?", applicantsId)
					.list().size();
			if (size3 > 0) {
				flag = false;
				name = "personnel_manager";
				return name;
			}
		}
		if (flag) {
			int size4 = employeeallowanceDao
					.createSQLQuery("select u.id,u.name from sys_user u,sys_role r,sys_user_role ur "
							+ "where ur.user_id = u.id and ur.role_id = r.id and r.name = '部门负责人'"
							+ " and u.id = ?", applicantsId)
					.list().size();
			if (size4 > 0) {
				flag = false;
				name = "depart_manager";
				return name;
			}
		}
		return "narmal_person";
	}

	public void del(String ids) throws Exception {
		String[] idss = ids.split(",");
		for (String id : idss) {
			employeeallowanceDao.createQuery("update Employeeallowance set data_status = '99' where id = ?", id)
					.executeUpdate();
		}
	}

	@SuppressWarnings("all")
	public void subOvertime(HttpServletRequest request, String ids, String step) throws Exception {

		boolean flag = true;
		CurrentSessionUser user = SecurityUtil.getSecurityUser();
		String[] idss = ids.split(",");
		for (int i = 0; i < idss.length; i++) {

			Employeeallowance reviews = employeeallowanceDao.get(idss[i]);
			String ip = TS_Util.getIpAddress(request);
			String conclusion = request.getParameter("conclusion");
			String dept_opinion = request.getParameter("dept_opinion");
			String dept_op_date = request.getParameter("dept_op_date");
			String leader_opinion = request.getParameter("leader_opinion");
			String leader_date = request.getParameter("leader_date");
			String subStatus = request.getParameter("sub_status");
			String op_date = request.getParameter("op_date");
			String remark = request.getParameter("remark");
			String next_op = request.getParameter("next_op");
			String next_op_id = request.getParameter("next_op_id");
			String op = "提交审核";

			if (next_op_id != null && StringUtil.isNotEmpty(next_op_id)) {
				// 流转操作人
				reviews.setHandle_id(next_op_id);
				reviews.setHandle_op(next_op);
				

				//调用消息发送
				HashMap<String, Object> map1 = new HashMap<String, Object>();
				map1.put("applyOp", reviews.getApplicants());
				//补助总金额
				map1.put("sumMoney", reviews.getSumMoney());
				
				if( reviews.getEnter_time()!=null) {
					map1.put("applyTime", new SimpleDateFormat("yyyy-MM-dd HH:mm").format(reviews.getEnter_time()));
				}else {
					map1.put("applyTime", "");
				}
				
				String datas = reviews.toString();//reviews.toJson().toString();
				
				StringBuffer sql = new StringBuffer();
			
				if ("1".equals(step)) {
					// 提交部长时，部长需要选人事
					sql.append(
							"select u.id,u.name from SYS_USER u,sys_role r,sys_user_role ur  where ur.user_id = u.id and ur.role_id = r.id ");
					sql.append("and r.name = '人力资源管理-请休假申请人事审核' ");
				} else if ("2".equals(step)) {
					// 部长提交时已经选择了人事了，选择院领导
					sql.append(
							"select u.id,u.name from SYS_USER u,sys_role r,sys_user_role ur  where ur.user_id = u.id and ur.role_id = r.id ");
					sql.append("and r.name = '院领导' ");
					//sql.append("and r.name = '院领导' and u.org_id = o.id and tol.fk_sys_org_id like '%"+dept_id+"%' and tol.fk_rl_emplpyee_id = u.employee_id");
				} else if ("3".equals(step)&&"1".equals(reviews.getRole_status())) {
					sql.append(
							"select u.id,u.name from SYS_USER u,sys_role r,sys_user_role ur  where ur.user_id = u.id and ur.role_id = r.id ");
					sql.append("and r.name = '院长' ");
				} 
				
				if("4".equals(step)&&!"1".equals(reviews.getRole_status())) {
					
				}else {
					if ("1".equals(step)||(("通过".equals(conclusion) || "1".equals(conclusion))&&!"5".equals(step))) {
						String nextStep = (Integer.parseInt(step)+1) +"";
						//配置审核内容
						String id = msgLinkAuditService.addNeedAudit("加班补助申请",reviews.getId(),"TJY2_ALLOWANCE_FOR_OVERTIME",null,datas,
								"employeeallowanceAction/subOvertime.do?ids="+reviews.getId()+"&step="+nextStep,
								sql.toString(),
								next_op_id,next_op);
						
						String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxb0f376eb09e64dd3&redirect_uri"
								+ "=http://kh.scsei.org.cn/msgLinkAuditAction/weixinUserInfo.do?id="+id
								+ "&response_type=code&scope=snsapi_base&state=STATE";
						HashMap<String, Object> map2 =  new HashMap<String, Object>();
						map2.put("url", url);
						//发送消息
						messageService.sendMassageByConfig(request, reviews.getId(), null,"", "overWorkAllowance", 
								next_op_id, map1, map2,"1",Constant.PEOPLE_CORPID,Constant.PEOPLE_PWD);
					}
				}
			}

			// 设置提交状态,来判断是否能够修改删除提交等操作，1未false 0 为true
			if ("0".equals(reviews.getSub_status()) || reviews.getSub_status() == null) {
				reviews.setSub_status("1");
			}

			if ("1".equals(step)) {
				op = "提交审核";
				reviews.setEnter_op(user.getName());
				reviews.setEnter_op_id(user.getId());
				reviews.setEnter_time(new Date());
				reviews.setFlow_step(step);
			} else if ("2".equals(step)) {
				op = "部长审核";
				reviews.setMinister_audit(user.getName());
				reviews.setMinister_audit_id(user.getId());
				reviews.setFlow_step(step);
				try {
					Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(op_date);
					reviews.setMinister_audit_time(date);
					reviews.setDept_op_date(date);
					// 如果不通过审核人意见取手动填写的，通过默认为通过，下面一样
					if ("通过".equals(conclusion) || "1".equals(conclusion)) {
						reviews.setMinister_audit_remark("拟同意");
					} else {
						if (remark != null && StringUtil.isNotEmpty(remark)) {
							reviews.setMinister_audit_remark("不同意" + remark);
						} else {
							reviews.setMinister_audit_remark("不同意");
						}

					}
				} catch (Exception e) {
				}
			} else if ("3".equals(step)) {
				op = "人事审核";
				reviews.setPersonnel_audit(user.getName());
				reviews.setPersonnel_audit_id(user.getId());
				reviews.setFlow_step(step);
				try {
					Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(op_date);
					reviews.setPersonnel_audit_time(date);
					String temp = conclusion;
					if ("通过".equals(conclusion) || "1".equals(conclusion)) {
						reviews.setPersonnel_audit_remark("拟同意");
					} else {
						op = "不同意";
						if (remark != null && StringUtil.isNotEmpty(remark)) {
							reviews.setPersonnel_audit_remark("不同意" + remark);
						} else {
							reviews.setPersonnel_audit_remark("不同意");
						}
					}
				} catch (Exception e) {
				}
			} else if ("4".equals(step)) {
				op = "院领导审核";
				reviews.setLeader_audit(user.getName());
				reviews.setLeader_audit_op(user.getId());
				
				if(!"1".equals(reviews.getRole_status())) {
					//没有分管领导则直接结束流程了
					reviews.setHandle_id(null);
					reviews.setHandle_op(null);
					reviews.setFlow_step("5");
				}else {
					reviews.setFlow_step(step);
				}
				
				try {
					Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(op_date);
					reviews.setLeader_audit_time(date);
					reviews.setLeader_date(date);
					if ("通过".equals(conclusion) || "1".equals(conclusion)) {
						reviews.setLeader_audit_remark("拟同意");
					} else {
						op = "不同意";
						if (remark != null && StringUtil.isNotEmpty(remark)) {
							reviews.setLeader_audit_remark("不同意" + remark);
						} else {
							reviews.setLeader_audit_remark("不同意");
						}

					}
				} catch (Exception e) {
				}
			} else if ("5".equals(step)) {
				op = "院长审核";
				reviews.setDean_audit(user.getName());
				reviews.setDean_id(user.getId());
				reviews.setFlow_step(step);
				try {
					reviews.setDean_audit_time(new Date());
					if ("通过".equals(conclusion) || "1".equals(conclusion)) {
						reviews.setDean_audit_remark("拟同意");
					} else {
						op = "不同意";
						if (remark != null && StringUtil.isNotEmpty(remark)) {
							reviews.setDean_audit_remark("不同意" + remark);
						} else {
							reviews.setDean_audit_remark("不同意");
						}

					}
				} catch (Exception e) {
				}
			} else {
				op = "审核通过";
				reviews.setFlow_step("5");
				try {
					Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(op_date);
				} catch (Exception e) {
				}
			}
			String op_conclusion = conclusion;
			if ("不通过".equals(conclusion) || "0".equals(conclusion)) {
				// 不通过则直接结束
				reviews.setFlow_step("6");
				op_conclusion = "不同意";
			} else {
				op_conclusion = "拟同意";
			}
			employeeallowanceDao.save(reviews);
		}
	}
}
