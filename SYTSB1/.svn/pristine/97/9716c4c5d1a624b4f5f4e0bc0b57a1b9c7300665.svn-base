package com.lsts.archives.web;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.khnt.base.Factory;
import com.khnt.bpm.core.support.FinishFlowInf;
import com.khnt.bpm.ext.service.FlowExtManager;
import com.khnt.bpm.ext.support.FlowExtWorktaskParam;
import com.khnt.core.crud.web.SpringSupportAction;
import com.khnt.core.crud.web.support.JsonWrapper;
import com.khnt.rbac.impl.bean.Employee;
import com.khnt.rbac.impl.bean.User;
import com.khnt.security.CurrentSessionUser;
import com.khnt.security.util.SecurityUtil;
import com.khnt.utils.StringUtil;
import com.lsts.advicenote.service.MessageService;
import com.lsts.archives.bean.ArchivesBorrow;
import com.lsts.archives.bean.ArchivesDestroy;
import com.lsts.archives.bean.ArchivesFile;
import com.lsts.archives.bean.ArchivesPrint;
import com.lsts.archives.bean.ArchivesYijina;
import com.lsts.archives.dao.ArchivesBorrowDao;
import com.lsts.archives.dao.ArchivesYijinaDao;
import com.lsts.archives.service.ArchivesBorrowManager;
import com.lsts.archives.service.ArchivesDestroyManager;
import com.lsts.archives.service.ArchivesPrintManager;
import com.lsts.archives.service.ArchivesRzManager;
import com.lsts.archives.service.ArchivesYijinaManager;
import com.lsts.constant.Constant;
import com.lsts.employee.dao.EmployeesDao;
import com.lsts.finance.bean.MessageCheck2;
import com.lsts.finance.service.MessageCheckManager;
import com.lsts.humanresources.service.EmployeeBaseManager;
import com.lsts.log.service.SysLogService;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("archives/yijina")
public class ArchivesYijinaAction extends SpringSupportAction<ArchivesYijina, ArchivesYijinaManager> {
	@Autowired
	private EmployeeBaseManager employeeBaseManager;
	@Autowired
	private ArchivesYijinaManager archivesYijinaManager;
	@Autowired
	private ArchivesBorrowManager archivesBorrowManager;
	@Autowired
	private ArchivesBorrowDao archivesBorrowDao;
	@Autowired
	private ArchivesPrintManager archivesPrintManager;
	@Autowired
	private ArchivesYijinaDao archivesYijinaDao;
	@Autowired
	private ArchivesRzManager archivesRzManager;
	@Autowired
	private ArchivesDestroyManager archivesDestroyManager;
	@Autowired
	FlowExtManager flowExtManager;
	@Autowired
	private MessageService messageService;
	@Autowired
	private EmployeesDao employeesDao;
	@Autowired
	private SysLogService logService;

	@Autowired
	private MessageCheckManager messageCheckManager;

	Date date1 = new Date();

	/**
	 * 判断借阅权限
	 */
	@RequestMapping(value = "czqx")
	@ResponseBody
	public HashMap<String, Object> czqx(String userId) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		CurrentSessionUser curUser = SecurityUtil.getSecurityUser();
		User user = (User) curUser.getSysUser();
		Employee emp = (com.khnt.rbac.impl.bean.Employee) user.getEmployee();
		String uu = emp.getId();
		String userId1 = "402884c4477c9bac01477ffaa1a90080";
		String qx = archivesYijinaDao.getqx(userId1).toString();
		String a = "[" + "]";
		// long b=86400000*15;reportNumber
		Date date1 = new Date();
		long date = (long) date1.getTime();// 当前时间
		List<ArchivesBorrow> list = archivesYijinaManager.getTime(userId1);
		List<ArchivesFile> list1 = archivesYijinaManager.getb(userId1);// 获得报告编号
		// String z =
		// Pattern.compile("\\b([\\w\\W])\\b").matcher(list1.toString()
		// .substring(1,list1.toString().length()-1)).replaceAll("'$1'");
		if (list != null && list.size() > 0) {
			for (int k = 0; k < list.size(); k++) {
				long c = (long) list.get(k).getApplyTime().getTime();// 申请时间
				long d = (long) list.get(k).getRestoreTime().getTime();// 返还时间
				if (a.equals(qx)) {
					map.put("success", false);
					map.put("msg", "你还没有借阅权限,请先去申请！");
				} else {
					if (c < date && date < d) {
						if (list1 != null && list1.size() > 0) {
							for (int j = 0; j < list1.size(); j++) {
								String q = list1.get(j).getCertificateNumber();
								String w = list1.get(j).getChecker();
								map.put("reportNumber", q + "-" + w);
							}
						}
						map.put("success", true);
						map.put("msg", "欢迎进入阅览！");
						break;
					} else {
						map.put("success", false);
						// archivesYijinaDao.getgh(userId);
						map.put("msg", "借阅权限已到期，如还需查看请去申请！");

					}
				}

			}
		}
		return map;

	}

	/**
	 * 获取报告
	 */
	@RequestMapping(value = "getbg")
	@ResponseBody
	public HashMap<String, Object> getbg(String yzm) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		// 得到报告的id
		String reportNumberId = archivesYijinaDao.getbg(yzm).toString();
		String z = Pattern.compile("\\b([\\w\\W])\\b")
				.matcher(reportNumberId.toString().substring(1, reportNumberId.toString().length() - 1))
				.replaceAll("'$1'");
		// String[] reportNumberId=z.split("','");
		// List<ArchivesBorrow> list=new ArrayList<ArchivesBorrow>();
		// for (int i = 0; i < reportNumberId.length; i++) {
		// ArchivesBorrow archivesBorrow=new ArchivesBorrow();
		// archivesBorrow.setReportNumberId(reportNumberId[i]);
		// list.add(archivesBorrow);
		// }
		map.put("mss", z);

		return map;
	}

	/**
	 * 归还借阅权限
	 */
	@RequestMapping(value = "czqxgh")
	@ResponseBody
	public HashMap<String, Object> czqxgh(String yzm, String id) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		CurrentSessionUser curUser = SecurityUtil.getSecurityUser();
		User user = (User) curUser.getSysUser();
		Employee emp = (com.khnt.rbac.impl.bean.Employee) user.getEmployee();
		String uu = emp.getId();
		String userId1 = "402884c4477c9bac01477ffaa1a90080";
		archivesYijinaDao.getyzm(yzm);// 干掉验证码的权限
		// archivesYijinaDao.getgh(userId1);//干掉人员的权限
		// 设置返还报告时间
		ArchivesBorrow archivesBorrow = archivesBorrowManager.get(id);
		archivesBorrow.setFhbgsj(new Date());
		archivesBorrowManager.save(archivesBorrow);
		map.put("success", true);
		// map.put("msg", "已归还权限！");
		return map;
	}

	/**
	 * 打印审核意见添加
	 * 
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "savedy")
	@ResponseBody
	public HashMap<String, Object> savedy(HttpServletRequest request, @RequestBody ArchivesYijina archivesYijina)
			throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		CurrentSessionUser user = SecurityUtil.getSecurityUser();
		User uu = (User) user.getSysUser();
		Employee emp = (com.khnt.rbac.impl.bean.Employee) uu.getEmployee();
		// 得到状态
		String qx = archivesYijinaDao.getzt(archivesYijina.getFileId()).toString();
		String a = "[" + "SHZ" + "]";
		String b = "[" + "NO_PASS" + "]";
		String c = "[" + "PASS" + "]";
		if (a.equals(qx) || b.equals(qx) || c.equals(qx)) {// 判断在审核
			// 获取当前登录人信息
			archivesYijina.setAuditMan(user.getName());
			archivesYijina.setAuditTime(new java.sql.Date(date1.getTime()));
			archivesYijina.setAuditManId(user.getId());
			// 设置意见
			ArchivesPrint archivesPrint = archivesPrintManager.get(archivesYijina.getFileId());
			if (archivesYijina.getAuditStep().equals("部门负责人审核")) {

				archivesYijina.setAss("");// 签字id
				archivesYijina.setSeal("");// 盖章id
				archivesYijina.setReturnName("");// 退回环节名称
				archivesYijina.setStatus(ArchivesBorrowManager.DA_JYSQ_SHZ);
				archivesYijina.setBusinessName("打印申请");

				archivesPrint.setSqbmfzr(archivesYijina.getAuditOpinion());
				archivesPrint.setSqbmfzrName(user.getName());
				archivesPrint.setSqbmfzrTime(new java.sql.Date(date1.getTime()));
				archivesPrint.setSqbmfzrId(emp.getId());
			} else if (archivesYijina.getAuditStep().equals("服务部门负责人")) {

				archivesYijina.setAss("");// 签字id
				archivesYijina.setSeal("");// 盖章id
				archivesYijina.setReturnName("");// 退回环节名称
				archivesYijina.setStatus(ArchivesBorrowManager.DA_JYSQ_SHZ);
				archivesYijina.setBusinessName("打印申请");

				archivesPrint.setFwbmfzr(archivesYijina.getAuditOpinion());
				archivesPrint.setFwbmfzrName(user.getName());
				archivesPrint.setFwbmfzrTime(new java.sql.Date(date1.getTime()));
				archivesPrint.setFwbmfzrId(emp.getId());
			} else {

				archivesYijina.setAss("");// 签字id
				archivesYijina.setSeal("");// 盖章id
				archivesYijina.setReturnName("");// 退回环节名称
				archivesYijina.setStatus(ArchivesBorrowManager.DA_JYSQ_PASS);
				archivesYijina.setBusinessName("打印申请");

				// archivesPrint.setFwbnjbr(archivesYijina.getAuditOpinion());
				// archivesPrint.setFwbnjbrName(user.getName());
				// archivesPrint.setFwbnjbrTime(new
				// java.sql.Date(date1.getTime()));
				// archivesPrint.setFwbnjbrId(emp.getId());
			}
			archivesYijinaManager.save(archivesYijina);
			archivesPrintManager.save(archivesPrint);
			map.put("success", true);
			map.put("msg", "审核成功！");
		} else {
			map.put("success", false);
			map.put("msg", "审核失败！");
		}
		return map;

	}

	/**
	 * 不通过 打印审核意见添加
	 * 
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "savedy1")
	@ResponseBody
	public HashMap<String, Object> savedy1(HttpServletRequest request, @RequestBody ArchivesYijina archivesYijina)
			throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		CurrentSessionUser user = SecurityUtil.getSecurityUser();
		User uu = (User) user.getSysUser();
		Employee emp = (com.khnt.rbac.impl.bean.Employee) uu.getEmployee();
		// 得到状态
		String qx = archivesYijinaDao.getzt(archivesYijina.getFileId()).toString();
		String a = "[" + "SHZ" + "]";
		String b = "[" + "NO_PASS" + "]";
		String c = "[" + "PASS" + "]";
		String aa = "部门负责人审核";
		String bb = "服务部门负责人";
		String cc = "服务部门经办人";
		ArchivesPrint archivesPrint = archivesPrintManager.get(archivesYijina.getFileId());

		if (a.equals(qx) || b.equals(qx) || c.equals(qx)) {// 判断在审核
			if (aa.equals(archivesYijina.getAuditStep())) {

				archivesYijina.setAss("");// 签字id
				archivesYijina.setSeal("");// 盖章id
				archivesYijina.setReturnName(aa);// 退回环节名称
				archivesYijina.setStatus(ArchivesBorrowManager.DA_JYSQ_NO_PASS);
				archivesYijina.setBusinessName("打印申请");

				archivesPrint.setSqbmfzr(archivesYijina.getAuditOpinion());
				archivesPrint.setSqbmfzrName(user.getName());
				archivesPrint.setSqbmfzrTime(new java.sql.Date(date1.getTime()));
				archivesPrint.setSqbmfzrId(emp.getId());
			} else if (bb.equals(archivesYijina.getAuditStep())) {

				archivesYijina.setAss("");// 签字id
				archivesYijina.setSeal("");// 盖章id
				archivesYijina.setReturnName(bb);// 退回环节名称
				archivesYijina.setStatus(ArchivesBorrowManager.DA_JYSQ_NO_PASS);
				archivesYijina.setBusinessName("打印申请");

				archivesPrint.setFwbmfzr(archivesYijina.getAuditOpinion());
				archivesPrint.setFwbmfzrName(user.getName());
				archivesPrint.setFwbmfzrTime(new java.sql.Date(date1.getTime()));
				archivesPrint.setFwbmfzrId(emp.getId());
			} else if (cc.equals(archivesYijina.getAuditStep())) {

				archivesYijina.setAss("");// 签字id
				archivesYijina.setSeal("");// 盖章id
				archivesYijina.setReturnName(cc);// 退回环节名称
				archivesYijina.setStatus(ArchivesBorrowManager.DA_JYSQ_NO_PASS);
				archivesYijina.setBusinessName("打印申请");

				// archivesPrint.setFwbnjbr(archivesYijina.getAuditOpinion());
				// archivesPrint.setFwbnjbrName(user.getName());
				// archivesPrint.setFwbnjbrTime(new
				// java.sql.Date(date1.getTime()));
				// archivesPrint.setFwbnjbrId(emp.getId());
			}
			// 获取当前登录人信息
			archivesYijina.setAuditMan(user.getName());
			archivesYijina.setAuditTime(new java.sql.Date(date1.getTime()));
			archivesYijina.setAuditManId(user.getId());
			// 设置意见
			archivesPrintManager.save(archivesPrint);
			archivesYijinaManager.save(archivesYijina);
			map.put("success", true);
			map.put("msg", "审核成功！");
		} else {
			map.put("success", false);
			map.put("msg", "审核失败！");
		}
		return map;

	}

	/**
	 * 打印审批流程并改变状态
	 * 
	 * @param id
	 * @param flowId
	 * @param typeCode
	 * @param status
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "dadysh")
	@ResponseBody
	public HashMap<String, Object> dadysh(HttpServletRequest request, String areaFlag, String id, String typeCode,
			String status, String activityId, String yj) throws Exception {
		String inspection_jd_org_id = Factory.getSysPara().getProperty("INSPECTION_JD_ORG_ID");// 机电检验部门ID
		String inspection_cy_org_id = Factory.getSysPara().getProperty("INSPECTION_CY_ORG_ID");// 承压检验部门ID
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(FlowExtWorktaskParam.SERVICE_ID, id);
		map.put(FlowExtWorktaskParam.ACTIVITY_ID, activityId);
		map.put(FlowExtWorktaskParam.SERVICE_TITLE, "打印申请");
		map.put(FlowExtWorktaskParam.SERVICE_TYPE, typeCode);
		map.put(FlowExtWorktaskParam.IS_CURRENT_USER_TASK, true);

		ArchivesYijina archivesYijina = new ArchivesYijina();
		CurrentSessionUser user = SecurityUtil.getSecurityUser();
		User uu = (User) user.getSysUser();
		Employee emp = (com.khnt.rbac.impl.bean.Employee) uu.getEmployee();
		if (StringUtil.isEmpty(id)) {
			return JsonWrapper.failureWrapper("参数错误！");
		} else {
			// 审批流程
			if (StringUtil.isNotEmpty(activityId)) {
				if (areaFlag.equals("1")) {// 申请部门负责人
					archivesBorrowManager.doProcess(map);
					ArchivesPrint archivesPrint = archivesPrintManager.get(id);
					String applyUnitId = archivesPrint.getApplyUnitId();
					archivesPrint.setStatus(ArchivesBorrowManager.DA_JYSQ_SHZ);
					archivesPrint.setSqbmfzr(yj);
					archivesPrint.setSqbmfzrName(user.getName());
					archivesPrint.setSqbmfzrTime(new Date());
					archivesPrint.setSqbmfzrId(emp.getId());
					archivesPrintManager.save(archivesPrint);
					// 业务编号、操作动作、操作描述、操作用户编号、操作用户姓名、操作时间、操作IP
					logService.setLogs(id, "检验报告证书打印申请审核", "部门负责人审核。审核结果：通过",
							user != null ? user.getId() : "未获取到操作用户编号", user != null ? user.getName() : "未获取到操作用户姓名",
							new Date(), request != null ? request.getRemoteAddr() : "");
					archivesYijina.setFileId(id);
					archivesYijina.setAuditOpinion(yj);
					archivesYijina.setAuditMan(user.getName());
					archivesYijina.setAuditTime(new Date());
					archivesYijina.setAuditManId(emp.getId());
					archivesYijina.setAss("");// 签字id
					archivesYijina.setSeal("");// 盖章id
					archivesYijina.setReturnName("");// 退回环节名称
					archivesYijina.setAuditStep("申请部门负责人审核");
					archivesYijina.setAuditResult("通过");
					archivesYijina.setStatus(ArchivesBorrowManager.DA_JYSQ_SHZ);
					archivesYijina.setBusinessName("打印申请");
					archivesYijinaManager.save(archivesYijina);
					if (inspection_jd_org_id.indexOf(applyUnitId) != -1) {
						// 申请部门是机电部门时，向杨莉、邓雯雯、马竺君发送微信
						for (String emp_id : Factory.getSysPara().getProperty("YUANSHI_JD_SHENHE_EMP_ID1").split(",")) {
							// 获取电话
							String tel = employeesDao.getMobileTel(emp_id).toString();
							String destNumber = Pattern.compile("\\b([\\w\\W])\\b")
									.matcher(tel.toString().substring(1, tel.toString().length() - 1))
									.replaceAll("'$1'");
							System.out.println("电话号码为：" + destNumber);
							messageService.sendWxMsg(request, archivesPrint.getId(), Constant.INSPECTION_CORPID,
									Constant.INSPECTION_PWD,
									"您部门有【" + archivesPrint.getApplyUnit() + "】" + archivesPrint.getProposer()
											+ " 提交的“检验报告证书打印申请”（编号：" + archivesPrint.getIdentifier() + "）需要审核，请及时处理！",
									destNumber);
						}
					} else if (inspection_cy_org_id.indexOf(applyUnitId) != -1) {
						// 申请部门是承压部门时，向杨莉、秦云云发送微信
						for (String emp_id : Factory.getSysPara().getProperty("YUANSHI_CY_SHENHE_EMP_ID1").split(",")) {
							// 获取电话
							String tel = employeesDao.getMobileTel(emp_id).toString();
							String destNumber = Pattern.compile("\\b([\\w\\W])\\b")
									.matcher(tel.toString().substring(1, tel.toString().length() - 1))
									.replaceAll("'$1'");
							System.out.println("电话号码为：" + destNumber);
							messageService.sendWxMsg(request, archivesPrint.getId(), Constant.INSPECTION_CORPID,
									Constant.INSPECTION_PWD,
									"您部门有【" + archivesPrint.getApplyUnit() + "】" + archivesPrint.getProposer()
											+ " 提交的“检验报告证书打印申请”（编号：" + archivesPrint.getIdentifier() + "）需要审核，请及时处理！",
									destNumber);
						}
					}

				} else if (areaFlag.equals("2")) {// 服务部门负责人
					ArchivesPrint archivesPrint = archivesPrintManager.get(id);
					String applyUnitId = archivesPrint.getApplyUnitId();
					// 选分支
					JSONObject dataBus = new JSONObject();
					String orgid = archivesPrint.getApplyUnitId();
					if (orgid.equals("100020") || orgid.equals("100021") || orgid.equals("100022")
							|| orgid.equals("100023") || orgid.equals("100024") || orgid.equals("100063")) {
						dataBus.put("org", "1");
					} else {
						dataBus.put("org", "2");
					}
					map.put(FlowExtWorktaskParam.DATA_BUS, dataBus);
					archivesBorrowManager.doProcess(map);
					archivesPrint.setStatus(ArchivesBorrowManager.DA_JYSQ_SHZ);
					archivesPrint.setFwbmfzr(yj);
					archivesPrint.setFwbmfzrName(user.getName());
					archivesPrint.setFwbmfzrTime(new Date());
					archivesPrint.setFwbmfzrId(emp.getId());
					archivesPrintManager.save(archivesPrint);
					// 业务编号、操作动作、操作描述、操作用户编号、操作用户姓名、操作时间、操作IP
					logService.setLogs(id, "检验报告证书打印申请审核", "业务服务部部门负责人审核。审核结果：通过",
							user != null ? user.getId() : "未获取到操作用户编号", user != null ? user.getName() : "未获取到操作用户姓名",
							new Date(), request != null ? request.getRemoteAddr() : "");
					archivesYijina.setFileId(id);
					archivesYijina.setAuditOpinion(yj);
					archivesYijina.setAuditMan(user.getName());
					archivesYijina.setAuditTime(new Date());
					archivesYijina.setAuditManId(emp.getId());
					archivesYijina.setAss("");// 签字id
					archivesYijina.setSeal("");// 盖章id
					archivesYijina.setReturnName("");// 退回环节名称
					archivesYijina.setAuditStep("服务部门负责人审核");
					archivesYijina.setAuditResult("通过");
					archivesYijina.setStatus(ArchivesBorrowManager.DA_JYSQ_SHZ);
					archivesYijina.setBusinessName("打印申请");
					archivesYijinaManager.save(archivesYijina);

					if (inspection_jd_org_id.indexOf(applyUnitId) != -1) {
						// 申请部门是机电部门时，向邓雯雯、马竺君发送微信
						for (String emp_id : Factory.getSysPara().getProperty("YUANSHI_JD_SHENHE_EMP_ID2").split(",")) {
							// 获取电话
							String tel = employeesDao.getMobileTel(emp_id).toString();
							String destNumber = Pattern.compile("\\b([\\w\\W])\\b")
									.matcher(tel.toString().substring(1, tel.toString().length() - 1))
									.replaceAll("'$1'");
							System.out.println("电话号码为：" + destNumber);
							messageService.sendWxMsg(request, archivesPrint.getId(), Constant.INSPECTION_CORPID,
									Constant.INSPECTION_PWD,
									"您部门有【" + archivesPrint.getApplyUnit() + "】" + archivesPrint.getProposer()
											+ " 提交的“检验报告证书打印申请”（编号：" + archivesPrint.getIdentifier()
											+ "）需要服务部接收，请及时处理！",
									destNumber);
						}
					} else if (inspection_cy_org_id.indexOf(applyUnitId) != -1) {
						// 申请部门是承压部门时，向秦云云发送微信
						for (String emp_id : Factory.getSysPara().getProperty("YUANSHI_CY_SHENHE_EMP_ID2").split(",")) {
							// 获取电话
							String tel = employeesDao.getMobileTel(emp_id).toString();
							String destNumber = Pattern.compile("\\b([\\w\\W])\\b")
									.matcher(tel.toString().substring(1, tel.toString().length() - 1))
									.replaceAll("'$1'");
							System.out.println("电话号码为：" + destNumber);
							messageService.sendWxMsg(request, archivesPrint.getId(), Constant.INSPECTION_CORPID,
									Constant.INSPECTION_PWD,
									"您部门有【" + archivesPrint.getApplyUnit() + "】" + archivesPrint.getProposer()
											+ " 提交的“检验报告证书打印申请”（编号：" + archivesPrint.getIdentifier()
											+ "）需要服务部接收，请及时处理！",
									destNumber);
						}
					}
				} else if (areaFlag.equals("3") || areaFlag.equals("4")) {// 服务部门经办人
					archivesBorrowManager.doProcess(map);
					ArchivesPrint archivesPrint = archivesPrintManager.get(id);
					archivesPrint.setStatus(ArchivesBorrowManager.DA_JYSQ_PASS);
					archivesPrint.setFwbmjbr(yj);
					archivesPrint.setFwbmjbrName(user.getName());
					archivesPrint.setFwbmjbrTime(new Date());
					archivesPrint.setFwbmfzrId(emp.getId());
					archivesPrintManager.save(archivesPrint);
					// 业务编号、操作动作、操作描述、操作用户编号、操作用户姓名、操作时间、操作IP
					logService.setLogs(id, "检验报告证书打印申请审核", "业务服务部经办人审核。审核结果：通过",
							user != null ? user.getId() : "未获取到操作用户编号", user != null ? user.getName() : "未获取到操作用户姓名",
							new Date(), request != null ? request.getRemoteAddr() : "");
					archivesYijina.setFileId(id);
					archivesYijina.setAuditOpinion(yj);
					archivesYijina.setAuditMan(user.getName());
					archivesYijina.setAuditTime(new Date());
					archivesYijina.setAuditManId(emp.getId());
					archivesYijina.setAss("");// 签字id
					archivesYijina.setSeal("");// 盖章id
					archivesYijina.setReturnName("");// 退回环节名称
					archivesYijina.setAuditStep("服务部门经办人审批");
					archivesYijina.setAuditResult("通过");
					archivesYijina.setStatus(ArchivesBorrowManager.DA_JYSQ_PASS);
					archivesYijina.setBusinessName("打印申请");
					archivesYijinaManager.save(archivesYijina);
					String tel = employeesDao.getMobileTel(archivesPrint.getProposerId()).toString();
					String destNumber = Pattern.compile("\\b([\\w\\W])\\b")
							.matcher(tel.toString().substring(1, tel.toString().length() - 1)).replaceAll("'$1'");
					System.out.println("电话号码为：" + destNumber);
					messageService.sendWxMsg(request, archivesPrint.getId(), Constant.INSPECTION_CORPID,
							Constant.INSPECTION_PWD,
							"您提交的“检验报告证书打印申请”（编号：" + archivesPrint.getIdentifier() + "）业务服务部已确认，望知晓！", destNumber);
				} else {
					ArchivesPrint archivesPrint = archivesPrintManager.get(id);
					archivesPrint.setStatus(ArchivesBorrowManager.DA_JYSQ_NO_PASS);
					archivesPrintManager.save(archivesPrint);
					// 业务编号、操作动作、操作描述、操作用户编号、操作用户姓名、操作时间、操作IP
					logService.setLogs(id, "检验报告证书打印申请审核", "未知流程环节。审核结果：不通过",
							user != null ? user.getId() : "未获取到操作用户编号", user != null ? user.getName() : "未获取到操作用户姓名",
							new Date(), request != null ? request.getRemoteAddr() : "");
				}
			} else {
				return JsonWrapper.failureWrapper("流程ID为空！");
			}

			return JsonWrapper.successWrapper(id);
		}

	}

	/**
	 * 打印退回审批流程并改变状态
	 * 
	 * @param id
	 * @param flowId
	 * @param typeCode
	 * @param status
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "dadyth")
	@ResponseBody
	public HashMap<String, Object> dadyth(HttpServletRequest request, String areaFlag, String id, String typeCode,
			String status, String activityId, String processId, String yj) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> map1 = new HashMap<String, Object>();

		map.put(FlowExtWorktaskParam.SERVICE_ID, id);
		map.put(FlowExtWorktaskParam.ACTIVITY_ID, activityId);
		map.put(FlowExtWorktaskParam.SERVICE_TITLE, "打印申请");
		map.put(FlowExtWorktaskParam.SERVICE_TYPE, typeCode);
		map.put(FlowExtWorktaskParam.IS_CURRENT_USER_TASK, true);
		map1.put(FlowExtWorktaskParam.PROCESS_ID, processId);
		map1.put(FlowExtWorktaskParam.FINISH_TYPE, FinishFlowInf.FINISH_TYPE_TERMINATE);

		ArchivesYijina archivesYijina = new ArchivesYijina();
		CurrentSessionUser curUser = SecurityUtil.getSecurityUser();
		User user = (User) curUser.getSysUser();
		Employee emp = (com.khnt.rbac.impl.bean.Employee) user.getEmployee();
		if (StringUtil.isEmpty(id)) {
			return JsonWrapper.failureWrapper("参数错误！");
		} else {
			// 退回流程
			if (StringUtil.isNotEmpty(activityId)) {

				archivesBorrowManager.stop(map1);
				// archivesBorrowManager.doreturn(map);
				ArchivesPrint archivesPrint = archivesPrintManager.get(id);
				archivesPrint.setStatus(ArchivesBorrowManager.DA_JYSQ_NO_PASS);
				if (areaFlag.equals("1")) {
					archivesPrint.setSqbmfzr(yj);
					archivesPrint.setSqbmfzrName(user.getName());
					archivesPrint.setSqbmfzrTime(new Date());
					archivesPrint.setSqbmfzrId(emp.getId());
					archivesYijina.setReturnName("申请部门负责人审核");// 退回环节名称
					// 业务编号、操作动作、操作描述、操作用户编号、操作用户姓名、操作时间、操作IP
					logService.setLogs(id, "检验报告证书打印申请审核", "部门负责人审核。审核结果：不通过",
							user != null ? user.getId() : "未获取到操作用户编号", user != null ? user.getName() : "未获取到操作用户姓名",
							new Date(), request != null ? request.getRemoteAddr() : "");
				} else if (areaFlag.equals("2")) {
					archivesPrint.setFwbmfzr(yj);
					archivesPrint.setFwbmfzrName(user.getName());
					archivesPrint.setFwbmfzrTime(new Date());
					archivesPrint.setFwbmfzrId(emp.getId());
					archivesYijina.setReturnName("服务部门负责人审核");
					// 业务编号、操作动作、操作描述、操作用户编号、操作用户姓名、操作时间、操作IP
					logService.setLogs(id, "检验报告证书打印申请审核", "业务服务部部门负责人审核。审核结果：不通过",
							user != null ? user.getId() : "未获取到操作用户编号", user != null ? user.getName() : "未获取到操作用户姓名",
							new Date(), request != null ? request.getRemoteAddr() : "");
				} else {
					archivesPrint.setFwbmjbr(yj);
					archivesPrint.setFwbmjbrName(user.getName());
					archivesPrint.setFwbmjbrTime(new Date());
					archivesPrint.setFwbmfzrId(emp.getId());
					archivesYijina.setReturnName("服务部门经办人审批");
					// 业务编号、操作动作、操作描述、操作用户编号、操作用户姓名、操作时间、操作IP
					logService.setLogs(id, "检验报告证书打印申请审核", "业务服务部经办人审核。审核结果：不通过",
							user != null ? user.getId() : "未获取到操作用户编号", user != null ? user.getName() : "未获取到操作用户姓名",
							new Date(), request != null ? request.getRemoteAddr() : "");
				}
				archivesPrintManager.save(archivesPrint);

				archivesYijina.setFileId(id);
				archivesYijina.setAuditMan(user.getName());
				archivesYijina.setAuditTime(new Date());
				archivesYijina.setAuditManId(emp.getId());
				archivesYijina.setAss("");// 签字id
				archivesYijina.setSeal("");// 盖章id

				archivesYijina.setAuditStep("服务部门经办人审批");
				archivesYijina.setAuditResult("未通过");
				archivesYijina.setStatus(ArchivesBorrowManager.DA_JYSQ_PASS);
				archivesYijina.setBusinessName("打印申请");
				archivesYijinaManager.save(archivesYijina);
			} else {
				return JsonWrapper.failureWrapper("流程ID为空！");
			}
			return JsonWrapper.successWrapper(id);
		}

	}

	// public static void main(String[] args) throws ParseException{
	// long a=5*86400000;
	// long d=(int) new Date().getTime();
	// Calendar c=new GregorianCalendar();
	// Date b=new Date(a+d);
	// System.out.println(d);
	// System.out.println(a+d);
	// SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	// System.out.println(sdf.format(b));
	//
	// SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
	// Calendar c1 = Calendar.getInstance();
	// System.out.println("当前日期:"+sf.format(c1.getTime()));
	// c1.add(Calendar.DAY_OF_MONTH, 15);
	// System.out.println("增加一天后日期:"+sf.parse(sf.format(c1.getTime())));
	//
	// }
	/**
	 * 阅览验证码对比
	 */
	@RequestMapping(value = "savetime")
	@ResponseBody
	public HashMap<String, Object> savetime(String yzm) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		long d = new Date().getTime();
		MessageCheck2 nowid = messageCheckManager.messageid12(yzm);
		long endtim = nowid.getEndTime().getTime() + 59400000;
		String a = "enable";
		// 验证是否超时
		if (nowid != null && nowid.getStatus().equals(a) && d <= endtim) {
			nowid.setCheckTime(new Date());
			messageCheckManager.save(nowid);
			map.put("success", true);

		} else {// 干掉验证码
			archivesYijinaManager.getrq(nowid.getBusinessId());
			nowid.setStatus("disable");
			messageCheckManager.save(nowid);
			map.put("success", false);
			map.put("msg", "验证码已过期！");

		}
		return map;
	}

	/**
	 * 借阅审核意见添加
	 * 
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "saveshd")
	@ResponseBody
	public HashMap<String, Object> saveshd(HttpServletRequest request, @RequestBody ArchivesYijina archivesYijina,
			String areaFlag, String test, String ywid) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		CurrentSessionUser curUser = SecurityUtil.getSecurityUser();
		User user = (User) curUser.getSysUser();
		Employee emp = (com.khnt.rbac.impl.bean.Employee) user.getEmployee();
		if (test.equals("9") && areaFlag.equals("1")) {// 审核未通过
			archivesYijina.setFileId(ywid);
			archivesYijina.setAuditOpinion(archivesYijina.getAuditOpinion());
			archivesYijina.setAuditMan(user.getName());
			archivesYijina.setAuditTime(new Date());
			archivesYijina.setAuditManId(emp.getId());
			archivesYijina.setAss("");// 签字id
			archivesYijina.setSeal("");// 盖章id
			archivesYijina.setReturnName("部门负责人审核");// 退回环节名称
			archivesYijina.setAuditStep("部门负责人审核");// 审核步骤
			archivesYijina.setAuditResult("不通过");
			archivesYijina.setStatus(ArchivesBorrowManager.DA_JYSQ_NO_PASS);
			archivesYijina.setBusinessName("借阅申请");
			archivesYijinaManager.save(archivesYijina);
			// 设置部门负责人
			ArchivesBorrow archivesBorrow = archivesBorrowManager.get(ywid);
			archivesBorrow.setBmfzr(user.getName());// 部门负责人
			archivesBorrow.setBmfzrTime(new Date());
			archivesBorrowManager.save(archivesBorrow);
		} else if (test.equals("9") && areaFlag.equals("2")) {
			archivesYijina.setFileId(ywid);
			archivesYijina.setAuditOpinion(archivesYijina.getAuditOpinion());
			archivesYijina.setAuditMan(user.getName());
			archivesYijina.setAuditTime(new Date());
			archivesYijina.setAuditManId(emp.getId());
			archivesYijina.setAss("");// 签字id
			archivesYijina.setSeal("");// 盖章id
			archivesYijina.setReturnName("业务服务部审核");// 退回环节名称
			archivesYijina.setAuditStep("业务服务部审核");// 审核步骤
			archivesYijina.setAuditResult("不通过");
			archivesYijina.setStatus(ArchivesBorrowManager.DA_JYSQ_NO_PASS);
			archivesYijina.setBusinessName("借阅申请");
			archivesYijinaManager.save(archivesYijina);
			// 设置服务部门部门负责人
			ArchivesBorrow archivesBorrow = archivesBorrowManager.get(ywid);
			archivesBorrow.setYffwb(user.getName());// 业务服务部负责人
			archivesBorrow.setYffwbTime(new Date());
			archivesBorrowManager.save(archivesBorrow);
		} else if (test.equals("8") && areaFlag.equals("1")) {// 审核通过
			archivesYijina.setFileId(ywid);
			archivesYijina.setAuditOpinion(archivesYijina.getAuditOpinion());
			archivesYijina.setAuditMan(user.getName());
			archivesYijina.setAuditTime(new Date());
			archivesYijina.setAuditManId(emp.getId());
			archivesYijina.setAss("");// 签字id
			archivesYijina.setSeal("");// 盖章id
			archivesYijina.setReturnName("");// 退回环节名称
			archivesYijina.setAuditStep("部门负责人审核");// 审核步骤
			archivesYijina.setAuditResult("通过");
			archivesYijina.setStatus(ArchivesBorrowManager.DA_JYSQ_SHZ);
			archivesYijina.setBusinessName("借阅申请");
			archivesYijinaManager.save(archivesYijina);
		} else if (test.equals("8") && areaFlag.equals("2")) {
			archivesYijina.setFileId(ywid);
			archivesYijina.setAuditOpinion(archivesYijina.getAuditOpinion());
			archivesYijina.setAuditMan(user.getName());
			archivesYijina.setAuditTime(new Date());
			archivesYijina.setAuditManId(emp.getId());
			archivesYijina.setAss("");// 签字id
			archivesYijina.setSeal("");// 盖章id
			archivesYijina.setReturnName("");// 退回环节名称
			archivesYijina.setAuditStep("业务服务部审核");// 审核步骤
			archivesYijina.setAuditResult("通过");
			archivesYijina.setStatus(ArchivesBorrowManager.DA_JYSQ_PASS);
			archivesYijina.setBusinessName("借阅申请");
			archivesYijinaManager.save(archivesYijina);
		}
		// 获取当前登录人信息
		archivesYijina.setAuditMan(user.getName());
		archivesYijina.setAuditTime(new Date());
		archivesYijina.setAuditManId(user.getId());
		// 设置意见
		ArchivesBorrow archivesBorrow = archivesBorrowManager.get(archivesYijina.getFileId());
		if (archivesYijina.getAuditStep().equals("部门负责人审核")) {
			archivesBorrow.setBmfzryj(archivesYijina.getAuditOpinion());
		} else {
			archivesBorrow.setYffwbyj(archivesYijina.getAuditOpinion());
		}
		archivesYijinaManager.save(archivesYijina);
		archivesBorrowManager.save(archivesBorrow);
		map.put("success", true);
		return map;

	}

	/**
	 * 借阅审批流程并改变状态
	 * 
	 * @param id
	 * @param flowId
	 * @param typeCode
	 * @param status
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "datj")
	@ResponseBody
	public HashMap<String, Object> datj(HttpServletRequest request, String areaFlag, String id, String typeCode,
			String status, String activityId) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(FlowExtWorktaskParam.SERVICE_ID, id);
		map.put(FlowExtWorktaskParam.ACTIVITY_ID, activityId);
		map.put(FlowExtWorktaskParam.SERVICE_TITLE, "借阅申请");
		map.put(FlowExtWorktaskParam.SERVICE_TYPE, typeCode);
		map.put(FlowExtWorktaskParam.IS_CURRENT_USER_TASK, true);

		CurrentSessionUser user = SecurityUtil.getSecurityUser();
		if (StringUtil.isEmpty(id)) {
			return JsonWrapper.failureWrapper("参数错误！");
		} else {
			// 审批流程
			if (StringUtil.isNotEmpty(activityId)) {
				if (areaFlag.equals("1")) {// 部门负责人审核
					archivesBorrowManager.doProcess(map);
					ArchivesBorrow archivesBorrow = archivesBorrowManager.get(id);
					archivesBorrow.setStatus(ArchivesBorrowManager.DA_JYSQ_SHZ);
					archivesBorrow.setBmfzr(user.getName());// 部门负责人
					archivesBorrow.setBmfzrTime(new Date());
					archivesBorrowManager.save(archivesBorrow);
					archivesBorrowManager.setCheckLog(request, "部门负责人审核，审核结果：通过", id, user);
					// 向杨莉、袁鑫、赵颖发送微信
					for (String emp_id : Factory.getSysPara().getProperty("ARCHIVES_SHENHE_EMP_ID1").split(",")) {
						// 获取电话
						String tel = employeesDao.getMobileTel(emp_id).toString();
						String destNumber = Pattern.compile("\\b([\\w\\W])\\b")
								.matcher(tel.toString().substring(1, tel.toString().length() - 1)).replaceAll("'$1'");
						System.out.println("电话号码为：" + destNumber);
						messageService.sendWxMsg(request, archivesBorrow.getId(), Constant.INSPECTION_CORPID,
								Constant.INSPECTION_PWD,
								"您部门有【" + archivesBorrow.getApplyUnit() + "】" + archivesBorrow.getProposer()
										+ " 提交的“检验档案（借阅、查阅、复制）审批表”（编号：" + archivesBorrow.getIdentifier()
										+ "）需要审核，请及时处理！",
								destNumber);
					}
				} else if (areaFlag.equals("2")) {// 业务服务部审核
					archivesBorrowManager.doProcess(map);
					ArchivesBorrow archivesBorrow = archivesBorrowManager.get(id);
					archivesBorrow.setStatus(ArchivesBorrowManager.DA_JYSQ_PASS);
					archivesBorrow.setYffwb(user.getName());// 业务服务部负责人
					archivesBorrow.setYffwbTime(new Date());
					if (archivesBorrow.getApplyType().equals("3")) {// 判断是复制
						archivesBorrow.setFatalism("");
					} else {
						// 获取电话
						String s = archivesYijinaDao.getsjh(archivesBorrow.getProposerId()).toString();
						String Account = Pattern.compile("\\b([\\w\\W])\\b")
								.matcher(s.toString().substring(1, s.toString().length() - 1)).replaceAll("'$1'");
						// 生成验证码
						double number1 = Math.random() * 8 + 1;
						double number2 = Math.random() * 99999;
						String number = String.valueOf((int) number1 * 100000 + (int) number2);

						// 当前时间加申请天数得到返还日期
						int a = Integer.parseInt(archivesBorrow.getFatalism());
						SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
						Calendar c1 = Calendar.getInstance();
						System.out.println("当前日期:" + sf.format(c1.getTime()));
						c1.add(Calendar.DAY_OF_MONTH, a);
						System.out.println("增加a天后日期:" + sf.format(c1.getTime()));
						archivesBorrow.setRestoreTime(sf.parse(sf.format(c1.getTime())));//
						// archivesBorrow.setJfbgsj(new Date());
						archivesBorrow.setFkMsg(number);
						// 发送短信Account
						// messageservice.sendMoMsg(request,id,"您申请的档案："+archivesBorrow.getReportNumber()+
						// "，已审核通过。验证码为："+number+"，此验证码将在"+sf.format(c1.getTime())+
						// "过期，请合理安排时间进行查看！",Account);
						// //发送微信
						// messageservice.sendWxMsg(request, id,
						// "您申请的档案："+archivesBorrow.getReportNumber()+
						// "，已审核通过。验证码为："+number+"，此验证码将在"+sf.format(c1.getTime())+
						// "过期，请合理安排时间进行查看！", Account);
						messageCheckManager.sets(id, "档案借阅申请", Account, null, new Date(), c1.getTime(), null, "enable",
								request.getRemoteAddr(), number);
					}
					archivesBorrow.setStatus(ArchivesBorrowManager.DA_JYSQ_PASS);
					archivesBorrowManager.save(archivesBorrow);
					archivesBorrowManager.setCheckLog(request, "业务服务部审核，审核结果：通过", id, user);
					// 赋权（借阅）
					// archivesYijinaDao.setEmployee(archivesBorrow.getProposerId());
					// 保存到日志
					archivesRzManager.setJyrz("借阅申请", archivesBorrow.getIdentifier(), archivesBorrow.getApplyUnit(),
							archivesBorrow.getApplyUnitId(), user.getName(), user.getId(),
							archivesBorrow.getApplyTime(), archivesBorrow.getReportNumber(), new Date(), id, "审核流程通过");
					// 向袁鑫、赵颖发送微信
					for (String emp_id : Factory.getSysPara().getProperty("ARCHIVES_SHENHE_EMP_ID2").split(",")) {
						// 获取电话
						String tel = employeesDao.getMobileTel(emp_id).toString();
						String destNumber = Pattern.compile("\\b([\\w\\W])\\b")
								.matcher(tel.toString().substring(1, tel.toString().length() - 1)).replaceAll("'$1'");
						System.out.println("电话号码为：" + destNumber);
						messageService.sendWxMsg(request, archivesBorrow.getId(), Constant.INSPECTION_CORPID,
								Constant.INSPECTION_PWD,
								"【" + archivesBorrow.getApplyUnit() + "】" + archivesBorrow.getProposer()
										+ " 提交的“检验档案（借阅、查阅、复制）审批表”（编号：" + archivesBorrow.getIdentifier()
										+ "）已审核通过，请留意申请人前来领取！",
								destNumber);
					}
					// 向申请人发送微信
					String s1 = archivesYijinaDao.getsjh(archivesBorrow.getProposerId()).toString();
					String Account1 = Pattern.compile("\\b([\\w\\W])\\b")
							.matcher(s1.toString().substring(1, s1.toString().length() - 1)).replaceAll("'$1'");
					messageService.sendWxMsg(request, archivesBorrow.getId(), Constant.INSPECTION_CORPID,
							Constant.INSPECTION_PWD,
							"您申请的检验档案（借阅、查阅、复制）：" + archivesBorrow.getReportNumber() + "，已审核通过，望知晓！", Account1);
				} else {
					ArchivesBorrow archivesBorrow = archivesBorrowManager.get(id);
					archivesBorrow.setStatus(ArchivesBorrowManager.DA_JYSQ_NO_PASS);
					archivesBorrowManager.save(archivesBorrow);
				}
			} else {
				return JsonWrapper.failureWrapper("流程ID为空！");
			}

			return JsonWrapper.successWrapper(id);
		}

	}

	/**
	 * 借阅退回审批流程并改变状态
	 * 
	 * @param id
	 * @param flowId
	 * @param typeCode
	 * @param status
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "dath")
	@ResponseBody
	public HashMap<String, Object> dath(HttpServletRequest request, String areaFlag, String id, String typeCode,
			String status, String activityId, String processId) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> map1 = new HashMap<String, Object>();
		CurrentSessionUser user = SecurityUtil.getSecurityUser();
		map.put(FlowExtWorktaskParam.SERVICE_ID, id);
		map.put(FlowExtWorktaskParam.ACTIVITY_ID, activityId);
		map.put(FlowExtWorktaskParam.SERVICE_TITLE, "借阅申请");
		map.put(FlowExtWorktaskParam.SERVICE_TYPE, typeCode);
		map.put(FlowExtWorktaskParam.IS_CURRENT_USER_TASK, true);
		map1.put(FlowExtWorktaskParam.PROCESS_ID, processId);
		map1.put(FlowExtWorktaskParam.FINISH_TYPE, FinishFlowInf.FINISH_TYPE_TERMINATE);
		if (StringUtil.isEmpty(id)) {
			return JsonWrapper.failureWrapper("参数错误！");
		} else {
			// 退回流程
			if (StringUtil.isNotEmpty(activityId)) {
				archivesBorrowManager.stop(map1);
				// archivesBorrowManager.doreturn(map);
				ArchivesBorrow archivesBorrow = archivesBorrowManager.get(id);
				archivesBorrow.setStatus(ArchivesBorrowManager.DA_JYSQ_NO_PASS);
				archivesBorrowManager.save(archivesBorrow);
				if (areaFlag.equals("1")) {
					archivesBorrowManager.setCheckLog(request, "部门负责人审核，审核结果：不通过", id, user);
				} else if (areaFlag.equals("2")) {
					archivesBorrowManager.setCheckLog(request, "业务服务部审核，审核结果：不通过", id, user);
				}
			} else {
				return JsonWrapper.failureWrapper("流程ID为空！");
			}
			return JsonWrapper.successWrapper(id);
		}

	}

	/**
	 * 销毁审核意见添加
	 * 
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "savexh")
	@ResponseBody
	public HashMap<String, Object> savexh(HttpServletRequest request, ArchivesYijina archivesYijina) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		CurrentSessionUser user = SecurityUtil.getSecurityUser();
		// 获取当前登录人信息
		archivesYijina.setAuditMan(user.getName());
		archivesYijina.setAuditTime(new Date());
		archivesYijina.setAuditManId(user.getId());
		ArchivesDestroy archivesDestroy = archivesDestroyManager.get(archivesYijina.getFileId());
		if (archivesYijina.getAuditStep().equals("申请部门审核")) {

			archivesDestroy.setSqbm(archivesYijina.getAuditOpinion());
			archivesDestroy.setSqbmTime(new java.sql.Date(date1.getTime()));
		} else if (archivesYijina.getAuditStep().equals("科管部审核")) {

			archivesDestroy.setKgb(archivesYijina.getAuditOpinion());
			archivesDestroy.setKgbTime(new java.sql.Date(date1.getTime()));
		} else if (archivesYijina.getAuditStep().equals("质管部审核")) {

			archivesDestroy.setZgb(archivesYijina.getAuditOpinion());
			archivesDestroy.setZgbTime(new java.sql.Date(date1.getTime()));
		} else if (archivesYijina.getAuditStep().equals("分管院领导审核")) {

			archivesDestroy.setFgyld(archivesYijina.getAuditOpinion());
			archivesDestroy.setFgyldTime(new java.sql.Date(date1.getTime()));
		} else if (archivesYijina.getAuditStep().equals("技术负责人审核")) {

			archivesDestroy.setJsfzr(archivesYijina.getAuditOpinion());
			archivesDestroy.setJsfzrTime(new java.sql.Date(date1.getTime()));
		} else {

			archivesDestroy.setYz(archivesYijina.getAuditOpinion());
			archivesDestroy.setYzTime(new java.sql.Date(date1.getTime()));
		}
		archivesYijinaManager.save(archivesYijina);
		archivesDestroyManager.save(archivesDestroy);
		map.put("success", true);
		return map;

	}

	/**
	 * 销毁审批流程并改变状态
	 * 
	 * @param id
	 * @param flowId
	 * @param typeCode
	 * @param status
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "daxhtj")
	@ResponseBody
	public HashMap<String, Object> daxhtj(String areaFlag, String id, String typeCode, String status, String activityId)
			throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(FlowExtWorktaskParam.SERVICE_ID, id);
		map.put(FlowExtWorktaskParam.ACTIVITY_ID, activityId);
		map.put(FlowExtWorktaskParam.SERVICE_TITLE, "销毁申请");
		map.put(FlowExtWorktaskParam.SERVICE_TYPE, typeCode);
		map.put(FlowExtWorktaskParam.IS_CURRENT_USER_TASK, true);

		if (StringUtil.isEmpty(id)) {
			return JsonWrapper.failureWrapper("参数错误！");
		} else {
			// 审批流程
			if (StringUtil.isNotEmpty(activityId)) {
				// 申请部门。科管部。质管部。分管院领导。技术负责人。
				if (areaFlag.equals("1") || areaFlag.equals("2") || areaFlag.equals("3") || areaFlag.equals("4")
						|| areaFlag.equals("5")) {
					archivesBorrowManager.doProcess(map);
					ArchivesDestroy archivesDestroy = archivesDestroyManager.get(id);
					archivesDestroy.setStatus(ArchivesBorrowManager.DA_JYSQ_SHZ);
					archivesDestroyManager.save(archivesDestroy);
				} else if (areaFlag.equals("6")) {// 院长
					archivesBorrowManager.doProcess(map);
					ArchivesDestroy archivesDestroy = archivesDestroyManager.get(id);
					String z = archivesDestroy.getReportNumberId();
					// 销毁报告
					String[] reportNumberId = z.split(",");
					for (int i = 0; i < reportNumberId.length; i++) {
						archivesYijinaDao.setReportNumberId(reportNumberId[i]);
					}
					archivesDestroy.setStatus(ArchivesBorrowManager.DA_JYSQ_PASS);
					archivesDestroyManager.save(archivesDestroy);
				} else {
					ArchivesDestroy archivesDestroy = archivesDestroyManager.get(id);
					archivesDestroy.setStatus(ArchivesBorrowManager.DA_JYSQ_NO_PASS);
					archivesDestroyManager.save(archivesDestroy);

				}
			} else {
				return JsonWrapper.failureWrapper("流程ID为空！");
			}
			return JsonWrapper.successWrapper(id);
		}

	}

	/**
	 * 销毁退回审批流程并改变状态
	 * 
	 * @param id
	 * @param flowId
	 * @param typeCode
	 * @param status
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "daxhth")
	@ResponseBody
	public HashMap<String, Object> daxhth(String areaFlag, String id, String typeCode, String status, String activityId,
			String processId) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> map1 = new HashMap<String, Object>();

		map.put(FlowExtWorktaskParam.SERVICE_ID, id);
		map.put(FlowExtWorktaskParam.ACTIVITY_ID, activityId);
		map.put(FlowExtWorktaskParam.SERVICE_TITLE, "销毁申请");
		map.put(FlowExtWorktaskParam.SERVICE_TYPE, typeCode);
		map.put(FlowExtWorktaskParam.IS_CURRENT_USER_TASK, true);
		map1.put(FlowExtWorktaskParam.PROCESS_ID, processId);
		map1.put(FlowExtWorktaskParam.FINISH_TYPE, FinishFlowInf.FINISH_TYPE_TERMINATE);
		if (StringUtil.isEmpty(id)) {
			return JsonWrapper.failureWrapper("参数错误！");
		} else {
			// 退回流程
			if (StringUtil.isNotEmpty(activityId)) {
				// archivesBorrowManager.doreturn(map);
				archivesBorrowManager.stop(map1);
				ArchivesDestroy archivesDestroy = archivesDestroyManager.get(id);
				archivesDestroy.setStatus(ArchivesBorrowManager.DA_JYSQ_NO_PASS);
				archivesDestroyManager.save(archivesDestroy);
			} else {
				return JsonWrapper.failureWrapper("流程ID为空！");
			}
			return JsonWrapper.successWrapper(id);
		}

	}
}