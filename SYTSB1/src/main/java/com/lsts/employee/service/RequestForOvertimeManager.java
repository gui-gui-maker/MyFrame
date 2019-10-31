
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
import com.khnt.security.CurrentSessionUser;
import com.khnt.security.util.SecurityUtil;
import com.khnt.utils.StringUtil;
import com.lsts.advicenote.service.MessageService;
import com.lsts.advicenote.service.MsgLinkAuditService;
import com.lsts.constant.Constant;
import com.lsts.employee.bean.RequestForOvertime;
import com.lsts.employee.dao.RequestForOvertimeDao;


/**********************************************************
 * @author WSL
 * @date 创建时间：2017年12月19日 上午10:27:08
 * @version 2.0
 ***********************************************************/

@Service("requestForOvertimeManager")
public class RequestForOvertimeManager extends EntityManageImpl<RequestForOvertime, RequestForOvertimeDao> {

	@Autowired
	private RequestForOvertimeDao requestForOvertimeDao;
	@Autowired
	private MessageService messageService;
	@Autowired
	private MsgLinkAuditService msgLinkAuditService;

	/**
	 * 保存的时候同时保存申请人所在的部门类别,类别取所有申请人当中权限最低的
	 */
	@Override
	public void save(RequestForOvertime entity) throws Exception {
		if (entity.getId() == null||StringUtil.isEmpty(entity.getId())) {
			entity.setId(null);
			CurrentSessionUser user = SecurityUtil.getSecurityUser();
			entity.setData_status("1");
			entity.setFlow_step("0");
			entity.setEnter_op(user.getName());
			entity.setEnter_op_id(user.getId());
			entity.setEnter_time(new Date());
			entity.setSn(getAutoSn());

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
		
		//entity.setRole_status(this.setRoleStatus(n));
		requestForOvertimeDao.save(entity);
	}

	private String getAutoSn() {
		
		String df = new SimpleDateFormat("yyyyMMdd").format(new Date());
		String sn = df;
		List<Object> list =  requestForOvertimeDao.createSQLQuery("select max(sn) from  TJY2_REQUEST_FOR_OVERTIME where  data_status <> '99' and sn like '"+sn+"%'").list();
		if(list.size()==0||list.get(0)==null) {
			sn = sn + "001";
		}else {
			String max = list.get(0).toString().substring(8,11);
			int maxp = Integer.parseInt(max);
			int l = (maxp+"").length();
			String now = (maxp+1)+"";
			for (int i = 0; i < 3-l; i++) {
				now="0"+now;
			}
			sn = sn + now;
		}
		return sn;
	}

	/**
	 * 改变删除状态，逻辑删除
	 * 
	 * @param request
	 * @param ids
	 */
	public void deleteOvertime(HttpServletRequest request, String ids) {
		String[] idss = ids.split(",");
		for (String id : idss) {
			requestForOvertimeDao.createQuery("update RequestForOvertime set data_status = '99' where id = ?", id)
					.executeUpdate();
		}
	}

	/**
	 * 判断是否全是普通人员或者包含其他人员
	 * 
	 * @param n
	 * @return
	 */
	private String setRoleStatus(List<String> n) {
		if (n.contains("depart_manager")) {
			// 标记普通人员的流程，0是申请人全部为普通人员，不走院长流程，1是有其他人员，需要走院长流程
			return "1";
		} else if (n.contains("personnel_manager")) {
			return "1";
		} else if (n.contains("leader_manager")) {
			return "1";
		} else if (n.contains("dean_manager")) {
			return "1";
		} else if (n.contains("narmal_person")) {
			return "0";
		}
		return "0";
	}

	/**
	 * 判断当前申请人所属部门
	 * 
	 * @param orgId
	 * @param step
	 */
	public String subAuditOp(String otherIds) {
		String sqlFirst = "select u.id,u.name from sys_user u,sys_role r,sys_user_role ur  "
				+ "where ur.user_id = u.id and ur.role_id = r.id and r.name =";
		String sqlLast = " and u.id = ?";
		/*boolean flag = true;
		int size = requestForOvertimeDao.createSQLQuery(sqlFirst + "'院长'" + sqlLast, otherIds).list().size();
		if (size > 0) {
			flag = false;
			return "dean_manager";
		} else if (flag) {
		*/	
		
		int size2 = requestForOvertimeDao.createSQLQuery(sqlFirst + "'院领导'" + sqlLast, otherIds).list().size();
			if (size2 > 0) {
				//flag = false;
				return "leader_manager";
			}
			
		/*} else if (flag) {
			int size3 = requestForOvertimeDao.createSQLQuery(sqlFirst + "'人事查看'" + sqlLast, otherIds).list().size();
			if (size3 > 0) {
				flag = false;
				return "personnel_manager";
			}
		} else if (flag) {
			int size4 = requestForOvertimeDao.createSQLQuery(sqlFirst + "'部门负责人'" + sqlLast, otherIds).list().size();
			if (size4 > 0) {
				flag = false;
				return "depart_manager";
			}
		}*/
		return "narmal_person";
	}

	/**
	 * 审核流程
	 * 
	 * @param request
	 * @param ids
	 * @param step
	 * @throws Exception 
	 */
	@SuppressWarnings("all")
	public void subOvertime(HttpServletRequest request, String ids, String step) throws Exception {

		CurrentSessionUser user = SecurityUtil.getSecurityUser();
		String[] idss = ids.split(",");
		for (int i = 0; i < idss.length; i++) {

			RequestForOvertime reviews = requestForOvertimeDao.get(idss[i]);
			String conclusion = request.getParameter("conclusion");
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
						String id = msgLinkAuditService.addNeedAudit("加班申请",reviews.getId(),"TJY2_REQUEST_FOR_OVERTIME",null,datas,
								"requestForOvertimeAction/subOvertime.do?ids="+reviews.getId()+"&step="+nextStep,
								sql.toString(),
								next_op_id,next_op);
						
						String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxb0f376eb09e64dd3&redirect_uri"
								+ "=http://kh.scsei.org.cn/msgLinkAuditAction/weixinUserInfo.do?id="+id
								+ "&response_type=code&scope=snsapi_base&state=STATE";
						HashMap<String, Object> map2 =  new HashMap<String, Object>();
						map2.put("url", url);
						//发送消息
						messageService.sendMassageByConfig(request, reviews.getId(), null,"", "overWork", next_op_id, map1, map2,
								"1",Constant.PEOPLE_CORPID,Constant.PEOPLE_PWD);
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
					// 如果不通过审核人意见取手动填写的，通过默认为通过，下面一样
					if ("通过".equals(conclusion) || "1".equals(conclusion)) {
						reviews.setMinister_audit_remark("拟同意");
					} else {
						op = "不同意";
						if (remark != null && StringUtil.isNotEmpty(remark)) {
							reviews.setMinister_audit_remark("不同意：" + remark);
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
					if ("通过".equals(conclusion) || "1".equals(conclusion)) {
						reviews.setPersonnel_audit_remark("拟同意");
					} else {
						op = "不同意";
						if (remark != null && StringUtil.isNotEmpty(remark)) {
							reviews.setPersonnel_audit_remark("不同意：" + remark);
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
					if ("通过".equals(conclusion) || "1".equals(conclusion)) {
						reviews.setLeader_audit_remark("拟同意");
					} else {
						op = "不同意";
						if (remark != null && StringUtil.isNotEmpty(remark)) {
							reviews.setLeader_audit_remark("不同意：" + remark);
						} else {
							reviews.setLeader_audit_remark("不同意");
						}

					}
				} catch (Exception e) {
				}
				/*if (reviews.getRole_flag().equals("narmal_person") && reviews.getRole_status().equals("0")) {
					// 如果是普通人员且其他人员都是普通人员，直接结束不走院长流程，否则继续流程
					reviews.setFlow_step("5");
				} else {
					reviews.setFlow_step(step);
				}*/
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
							reviews.setDean_audit_remark("不同意：" + remark);
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
			
			
			//发送消息
			
			/*if(next_op_id!=null&&StringUtil.isEmpty(next_op_id)) {
				HashMap<String, Object> map1 = new HashMap<String, Object>();
				map1.put("applyOp", reviews.getApplicants());
				map1.put("enterOp", reviews.getEnter_op());
				messageService.sendMassageByConfig(request, reviews.getId(), null,next_op_id, "", "overWork", map1, null);
			}
			
			*/
			String op_conclusion = conclusion;
			if ("不通过".equals(conclusion) || "0".equals(conclusion)) {
				// 不通过则直接结束
				reviews.setFlow_step("6");
				op_conclusion = "不同意";
			} else {
				op_conclusion = "拟同意";
			}
			requestForOvertimeDao.save(reviews);
		}
	}
}
