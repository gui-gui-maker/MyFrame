package com.lsts.humanresources.service;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.khnt.utils.DateUtil;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.khnt.base.Factory;
import com.khnt.base.SysParaInf;
import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.khnt.pub.fileupload.service.AttachmentManager;
import com.khnt.rbac.impl.bean.Employee;
import com.khnt.rbac.impl.manager.EmployeeManager;
import com.khnt.rbac.impl.manager.OrgManagerImpl;
import com.khnt.utils.StringUtil;
import com.lsts.employee.dao.EmployeesDao;
import com.lsts.employee.service.EmployeesService;
import com.lsts.humanresources.Tjy2Ywfwbgzqrb.bean.Tjy2Ywfwbgzqrb;
import com.lsts.humanresources.Tjy2Ywfwbgzqrb.dao.Tjy2YwfwbgzqrbDao;
import com.lsts.humanresources.Tjy2Ywfwbgzqrb.service.Tjy2YwfwbgzqrbManager;
import com.lsts.humanresources.bean.EmployeeBase;
import com.lsts.humanresources.bean.RsSummaryDTO;
import com.lsts.humanresources.bean.Tjy2Gjj;
import com.lsts.humanresources.bean.WorkExperience;
import com.lsts.humanresources.bean.WorktitleRecord;
import com.lsts.humanresources.dao.EmployeeBaseDao;
import com.lsts.humanresources.dao.Tjy2GjjDao;
import com.lsts.humanresources.dao.WorktitleRecordDao;

import net.sf.json.JSONArray;


@Service("employeeBase")
public class EmployeeBaseManager extends EntityManageImpl<EmployeeBase, EmployeeBaseDao> {
    @Autowired
    EmployeeBaseDao employeeBaseDao;
    @Autowired
    private EmployeesDao employeesDao;
    @Autowired
    private OrgManagerImpl orgManager;
    @Autowired
    private EmployeeManager employeeManager;
    @Autowired
    private EmployeesService employeesService;
    @Autowired
    private WorkExperienceManager workExperienceManager;
    @Autowired
    private WorktitleRecordDao worktitleRecordDao;
    @Autowired
    private AttachmentManager attachmentManager;
    @Autowired
    private Tjy2GjjDao tjy2GjjDao;
    @Autowired
    private Tjy2GjjManager tjy2GjjManager;
    @Autowired
    private Tjy2YwfwbgzqrbDao tjy2YwfwbgzqrbDao;
    @Autowired
    private Tjy2YwfwbgzqrbManager tjy2YwfwbgzqrbManager;


    public HashMap<String, Object> saveEmp(HttpServletRequest request, String status, String name_old,
                                           String org_id_old) {
        HashMap<String, Object> wrapper = new HashMap<String, Object>();
        try {
            String uploadFiles = request.getParameter("uploadFiles");
            String employee = request.getParameter("employee").toString();
            EmployeeBase entity = JSON.parseObject(employee, EmployeeBase.class);
  			/*String workExperience=request.getParameter("workExperience");
  			System.out.println(list);
  			WorkExperience entity1=JSON.parseObject(workExperience, WorkExperience.class);*/
            String empIdCard = entity.getEmpIdCard();
            String month = "";
            String day = "";
            String year = "";
            if (entity.getId() == null || entity.getId().equals("")) {
                entity.setIsCheck("0");
            } else {
  				/* entity.getId不为空时表明此时执行的是修改操作，则执行下面方法。
  				判断是否有修改关键字段的值。如果有关键字段的值被修改，则将字段modify_ident的值
  				设为1。
  				 */
                if (this.isModifiedKeyWordValue(entity)) {
                    this.tjy2YwfwbgzqrbManager.setModifyIdent(entity.getId());
                    this.tjy2GjjManager.setModifyIdent(entity.getId());
                }
            }
            if (empIdCard.length() >= 18) {
                year = empIdCard.substring(6, 10);
                month = empIdCard.substring(10, 12);
                day = empIdCard.substring(12, 14);
                entity.setEmpBirthday(year + "." + month + "." + day);
                /*entity.setEmpBirthday(month+"月"+day+"日");*/
            }
            if ("2".equals(entity.getEmpStatus())) {
                gz(request, entity);
                entity.setEmpStatus("3");
            } else if ("3".equals(entity.getEmpStatus())) {

            }
  		/*	else if ("5".equals(entity.getEmpStatus())){

  				//解聘
  				String id = entity.getId();
  				tjy2YwfwbgzqrbDao.deleteByEmployeeId(id);
  				tjy2GjjDao.deleteByEmployeeId(id);
  			}
  			*/
            else {
                if (status != null && !status.equals("")) {//从正式员工处直接增加员工则status值为“4”
                    entity.setEmpStatus(status);
                } else {
                    if (entity.getId() == null || entity.getId().equals(null) || entity.getId().equals("")) {
                        entity.setCreateDate(new Date());
                    }
                    entity.setEmpStatus("1");
                }
                if(StringUtil.isEmpty(entity.getId())) {
                    employeeBaseDao.save(entity);
                }
                gz(request, entity);
            }

            List<WorkExperience> list = new ArrayList<WorkExperience>(entity.getWorkExperience());
            entity.setWorkExperience(null);
            if (entity.getEmpPhone() != null && !entity.getEmpPhone().equals("")) {
                entity.setMobilePhone(entity.getEmpPhone());
            }

            employeeBaseDao.save(entity);
            //回写employee
            if (entity.getEmpStatus().equals("3") || entity.getEmpStatus().equals("4")) {
                Employee employee2 = new Employee();
                if (employeesDao.get(entity.getId()) != null) {
                    employee2 = employeesDao.get(entity.getId());
                }
                employee2.setName(entity.getEmpName());
                employee2.setGender(entity.getEmpSex());
                employee2.setIdNo(entity.getEmpIdCard());
                employee2.setOrg(orgManager.get(entity.getWorkDepartment()));
                //employee2.setEuserType(entity.getEmpPosition());
                employee2.setMobileTel(entity.getMobilePhone());
                employee2.setOfficeTel(entity.getOfficePhone());
                //employeesDao.save(employee2);
                if (employee2.getId() == null || employee2.getId().equals("")) {
                    String sql = "insert into EMPLOYEE(id,name,gender,id_no,org_id,euser_type,mobile_tel,office_tel," +
                            "second_pwd) VALUES(?,?,?,?,?,?,?,?,?)";
                    //employeeBaseDao.createSQLQuery(sql, entity.getId(), employee2.getName(),employee2.getGender(),
                    // employee2.getIdNo(),employee2.getOrg(),employee2.getEuserType(),employee2.getMobileTel(),
                    // employee2.getOfficeTel(),"2c34252f7011806681c72e723b043314").executeUpdate();
                    employeeBaseDao.createSQLQuery(sql, entity.getId(), employee2.getName(), employee2.getGender(),
                            employee2.getIdNo(), employee2.getOrg(), "", employee2.getMobileTel(),
                            employee2.getOfficeTel(), "2c34252f7011806681c72e723b043314").executeUpdate();
                } else {
                    employeesDao.save(employee2);
                }
                if (name_old != null && !name_old.equals("") && org_id_old != null && !org_id_old.equals("")) {
                    if (employeeBaseDao.checksysuser1(name_old, org_id_old)) {
                        //更新登录人员姓名和部门信息
                        String sql = "update sys_user set ORG_ID = ?,NAME=?,LAST_PWD_DATE=?  where NAME = ? and " +
                                "ORG_ID = ? and STATUS = '1'";
                        employeesDao.createSQLQuery(sql, entity.getWorkDepartment(), entity.getEmpName(), new Date(),
                                name_old, org_id_old).executeUpdate();
                    } else {
                        // 生成人员登录帐号信息
                        employeeManager.produceUser(entity.getId());
                    }
                } else {
                    // 生成人员登录帐号信息
                    employeeManager.produceUser(entity.getId());
                }
  			/*if(employeeBaseDao.checksysuser(entity.getId())){
  				//更新登录人员姓名和部门信息
  	  			String sql = "update sys_user set ORG_ID = ?,NAME=?,LAST_PWD_DATE=?  where EMPLOYEE_ID = ?";
  	  			employeesDao.createSQLQuery(sql, entity.getWorkDepartment(), entity.getEmpName(), new Date(), entity
  	  			.getId()).executeUpdate();
  			}else{
  				// 生成人员登录帐号信息
  	  			employeeManager.produceUser(entity.getId());
  			}*/
                //entity.setFkEmployee(employee2.getId());
  			/*if(employeesDao.getSecondPwd(employee2.getId()).equals(null)||employeesDao.getSecondPwd(employee2.getId
  			()).equals(""))
  			{
  			//设置初始独立密码
  			employeesService.initSecondPwd(employee2.getId());
  			}*/
                // 3、保存人员登录帐号信息
                //employeeManager.produceUser(entity.getId());

                // 4、更新登录人员姓名和部门信息
  			/*String sql = "update sys_user set ORG_ID = ?,NAME=?,LAST_PWD_DATE=?  where EMPLOYEE_ID = ?";
  			employeesDao.createSQLQuery(sql, entity.getWorkDepartment(), entity.getEmpName(), new Date(), entity.getId
  			()).executeUpdate();*/
            }


            workExperienceManager.delByEmpId(entity.getId());
            for (int i = 0; i < list.size(); i++) {
                WorkExperience work = list.get(i);
                work.setId("");
                System.out.println(work.getWorkPosition());
                work.setFkRlEmplpyeeId(entity.getId());
                workExperienceManager.save(work);
            }
            wrapper.put("id", entity.getId());
            wrapper.put("createDate", entity.getCreateDate());
            wrapper.put("success", true);
        } catch (Exception e) {
            log.error("保存信息：" + e.getMessage());
            wrapper.put("success", false);
            wrapper.put("message", "保存信息出错！");
            e.printStackTrace();
        }
        return wrapper;
    }

    //统计
    public Map<String, List> queryList() {
        Map<String, List> map = new HashMap<String, List>();
        List<Object[]> empMan = new ArrayList<Object[]>();
        List<Object[]> empWoman = new ArrayList<Object[]>();
        List<Object[]> empMating = new ArrayList<Object[]>();
        List<Object[]> empMatingNo = new ArrayList<Object[]>();
        String sqlMan = "select  work_department,count(1) my_count  from TJY2_RL_EMPLOYEE_BASE where emp_sex='1' and " +
                "emp_status='4'  group by work_department";
        String sqlWoman = "select  work_department,count(1) my_count  from TJY2_RL_EMPLOYEE_BASE where emp_sex='0' " +
                "and emp_status='4'  group by work_department";
        String sqlMating = "select  work_department,count(1) my_count  from TJY2_RL_EMPLOYEE_BASE where " +
                "emp_mating='1' and emp_status='4'  group by work_department";
        String sqlMatingNo = "select  work_department,count(1) my_count  from TJY2_RL_EMPLOYEE_BASE where " +
                "emp_mating='0' and emp_status='4'  group by work_department";
        empMan = employeeBaseDao.createSQLQuery(sqlMan).list();
        empWoman = employeeBaseDao.createSQLQuery(sqlWoman).list();
        empMating = employeeBaseDao.createSQLQuery(sqlMating).list();
        empMatingNo = employeeBaseDao.createSQLQuery(sqlMatingNo).list();
        map.put("empMan", empMan);
        map.put("empWoman", empWoman);
        map.put("empMating", empMating);
        map.put("empMatingNo", empMatingNo);
        return map;
    }

    //员工当前状态统计
    public Map<String, List> empCurrStatus() {
        Map<String, List> map = new HashMap<String, List>();
        List<Object[]> leave = new ArrayList<Object[]>();/*
  		List<Object[]> empWoman = new ArrayList<Object[]>();
  		List<Object[]> empMating = new ArrayList<Object[]>();
  		List<Object[]> empMatingNo L= new ArrayList<Object[]>();*/
        String sqlLeave = "select t.dep_id,count(1) my_count  from TJY2_RL_LEAVE t " +
                "where to_char(sysdate,'yyyy-mm-dd') between to_char(start_date,'yyyy-mm-dd') and to_char(end_date," +
                "'yyyy-mm-dd') " +
                "and t.apply_status='SPTG' group by t.dep_id";
    	/*String sqlWoman="select  work_department,count(1) my_count  from TJY2_RL_EMPLOYEE_BASE where emp_sex='0' and
    	 emp_status='4'  group by work_department";
    	String sqlMating="select  work_department,count(1) my_count  from TJY2_RL_EMPLOYEE_BASE where emp_mating='1'
    	and emp_status='4'  group by work_department";
    	String sqlMatingNo="select  work_department,count(1) my_count  from TJY2_RL_EMPLOYEE_BASE where emp_mating='0'
    	 and emp_status='4'  group by work_department";*/
        leave = employeeBaseDao.createSQLQuery(sqlLeave).list();
    	/*empWoman=employeeBaseDao.createSQLQuery(sqlWoman).list();
    	empMating=employeeBaseDao.createSQLQuery(sqlMating).list();
    	empMatingNo=employeeBaseDao.createSQLQuery(sqlMatingNo).list();*/
        map.put("leave", leave);
    	/*map.put("empWoman", empWoman);
    	map.put("empMating", empMating);
    	map.put("empMatingNo", empMatingNo);*/
        return map;
    }

    //学历、专业统计
    public List<?> edumajorCount(String condition) {
        List<?> list = employeeBaseDao.edumajorCount(condition);
        List<Object> list1 = new ArrayList<Object>();
        for (int i = 0; i < list.size(); i++) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            Object[] obj = (Object[]) list.get(i);
            map.put("name", obj[0]);
            map.put("num", obj[1]);
            map.put("per", obj[2] + "%");
            list1.add(map);
        }
        return list1;
    }

    public void deleteByEmp(String ids) {
        String arr[] = ids.split(",");
        for (int i = 0; i < arr.length; i++) {
            String hql = "delete from EmployeeBase  where FK_EMPLOYEE =? ";
            employeeBaseDao.createQuery(hql, arr[i]);
        }
    }

    public EmployeeBase getByEmpId(String id) {
        EmployeeBase employeeBase = new EmployeeBase();
        List<EmployeeBase> list = new ArrayList<EmployeeBase>();
        String hql = "from EmployeeBase  where FK_EMPLOYEE =? ";
        list = employeeBaseDao.createQuery(hql, id).list();
        if (list.size() > 0) {
            employeeBase = list.get(0);
        }
        return employeeBase;
    }

    public List<EmployeeBase> getByMessageId(String id) {
        EmployeeBase employeeBase = new EmployeeBase();
        List<EmployeeBase> list = new ArrayList<EmployeeBase>();
        String hql = "from EmployeeBase  where FK_MESSAGE_ID =? ";
        list = employeeBaseDao.createQuery(hql, id).list();
        return list;
    }

    //查询所有员工树形
    public List<Employee> getEmployeeTree(String orgId) {
        List<Employee> list = new ArrayList<Employee>();
        String hql = "from Employee where ORG_ID=? ";
        list = employeeBaseDao.createQuery(hql, orgId).list();
        return list;
    }

    //根据微信查询企业号
    public String getByWechat(String wechat) {
        List<EmployeeBase> list = new ArrayList<EmployeeBase>();
        EmployeeBase employeeBase = new EmployeeBase();
        //企业号
        String enterprise = "";
        String sql = "select * from TJY2_RL_EMPLOYEE_BASE where WECHAT=?";
        list = employeeBaseDao.createSQLQuery(sql, wechat).list();
        if (list.size() > 0) {
            employeeBase = list.get(0);
            enterprise = employeeBase.getEnterprise();
        }
        return enterprise;
    }

    //根据微信查询id
    public String getByWechatId(String weixin_id) {
        List<EmployeeBase> list = new ArrayList<EmployeeBase>();
        EmployeeBase employeeBase = new EmployeeBase();
        //企业号
        String enterprise = "";
        String sql = "select * from TJY2_RL_EMPLOYEE_BASE where WECHAT=?";
        list = employeeBaseDao.createSQLQuery(sql, weixin_id).list();
        if (list.size() > 0) {
            employeeBase = list.get(0);
            enterprise = employeeBase.getId();
        }
        return enterprise;
    }

    //根据手机号获取员工信息
    @SuppressWarnings("unchecked")
    public EmployeeBase getEmpByPhone(String phone) {
        List<EmployeeBase> list = new ArrayList<EmployeeBase>();
        EmployeeBase employeeBase = new EmployeeBase();
    	/*String sql="select * from TJY2_RL_EMPLOYEE_BASE where EMP_PHONE=?";
    	list=employeeBaseDao.createSQLQuery(sql, phone).list();*/
        String hql = "from EmployeeBase  where empPhone =? and (empStatus ='3' or empStatus ='4')";
        list = employeeBaseDao.createQuery(hql, phone).list();
        if (list.size() > 0) {
            employeeBase = list.get(0);
        }
        return employeeBase;
    }

    //微信端通过员工姓名获取员工基本信息
    @SuppressWarnings("unchecked")
    public List<EmployeeBase> getEmpOnWXByName(String name) {
        List<EmployeeBase> emplist = new ArrayList<EmployeeBase>();
        //EmployeeBase employeeBase=new EmployeeBase();
        String hql = "from EmployeeBase  where empPhone != null and empName =? ";
        emplist = employeeBaseDao.createQuery(hql, name).list();
        return emplist;
    }

    //微信端人力资源全院概况统计
    public List<RsSummaryDTO> weixinHRCount() throws Exception {
        List<RsSummaryDTO> list = new ArrayList<RsSummaryDTO>();
        list = employeeBaseDao.weixinHRCount();
        return list;
    }

    //根据员工姓名和状态查询员工
    public List<EmployeeBase> getEmpByNameAndStatus(String empName, String empStatus) {
        List<EmployeeBase> list = employeeBaseDao.queryEmployeesByNameAndStatus(empName, empStatus);
        return list;
    }

    //保存职务任免
    public HashMap<String, Object> saveWorkTitle(EmployeeBase employeeBase, WorktitleRecord worktitleRecord) {
        HashMap<String, Object> wrapper = new HashMap<String, Object>();
        try {
            WorktitleRecord worktitleRecord1 = worktitleRecordDao.getWorkTitle(worktitleRecord.getEmpId());
            worktitleRecord1.setStatus("0");
            worktitleRecordDao.save(worktitleRecord1);
        } catch (Exception e) {
            // TODO Auto-generated catch block
        }
        employeeBaseDao.save(employeeBase);
        worktitleRecordDao.save(worktitleRecord);
        if (StringUtil.isNotEmpty(worktitleRecord.getUploadFiles())) {
            String[] files = worktitleRecord.getUploadFiles().replaceAll("/^,/", "").split(",");
            for (String file : files) {
                if (StringUtil.isNotEmpty(file)) {
                    attachmentManager.setAttachmentBusinessId(file, worktitleRecord.getId());
                }
            }
        }
        wrapper.put("success", true);
        return wrapper;
    }

    /**
     * 保存导入
     *
     * @param file
     * @return
     * @throws ParseException
     */
    public String[] saveTaskData(String files) throws ParseException {
        JSONArray array = JSONArray.fromObject(files);
        String[] contents = new String[2];
        contents[0] = "0";
        contents[1] = "";
        String[] fileName = new String[array.length()];// 文件名
        String[] filePath = new String[array.length()];// 文件路径
        for (int i = 0; i < array.length(); i++) {
            String[] content = new String[2];
            fileName[i] = array.getJSONObject(i).getString("name");
            filePath[i] = array.getJSONObject(i).getString("path");
            content = saveDate(fileName[i], filePath[i]);
            contents[0] = Integer.toString(Integer.parseInt(contents[0]) + Integer.parseInt(content[0]));
            contents[1] = contents[1] + content[1];
        }
        return contents;
    }

    /**
     * 根据导入的文件名获取并解析数据
     *
     * @param file
     * @throws ParseException
     * @throws IOException
     */
    @SuppressWarnings("unused")
    public String[] saveDate(String fileName, String filePath) throws ParseException {
        String repData = "";
        int total = 0;//导入成功的数量
        String[] content = new String[2];
        fileName = this.getSystemFilePath() + File.separator + filePath;
        File taskfile = new File(fileName); // 创建文件对象
        HashMap<String, Object> UIDandNAME = new HashMap<String, Object>();//缓存使用人ID及姓名
        HashMap<String, Object> DIDandNAME = new HashMap<String, Object>();//缓存使用部门ID及名称
        Workbook taskWb = null;
        try {
            taskWb = WorkbookFactory.create(taskfile);
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Sheet taskSheet = taskWb.getSheetAt(0);//获得sheet
        for (int i = 0; i <= taskSheet.getLastRowNum(); i++) {
            Row row = taskSheet.getRow(i);//行
            if (row != null && !"null".equals(row)) {
                if (row.getCell(0) != null && !"null".equals(row.getCell(0))) {
                    System.out.println("姓名==" + row.getCell(1));
                    String name = row.getCell(1).toString();//姓名
                    String date1 = row.getCell(2).toString();//进院时间
                    String days = null;//天数
                    Date date = null;
                    if (!row.getCell(2).toString().isEmpty()) {
                        String date2 = date1 + ".01";
                        date = this.formatDate(date2);
                    }
                    if (!row.getCell(4).toString().isEmpty()) {
                        if (row.getCell(4).getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
                            String str = String.valueOf(row.getCell(4));
                            days = str.substring(0, str.indexOf("."));
                        } else if (row.getCell(4).getCellType() == HSSFCell.CELL_TYPE_STRING) {
                            days = row.getCell(4).getStringCellValue();
                        }
                    }
                    List<EmployeeBase> list = employeeBaseDao.queryEmployeesByName(name, "");
                    for (int j = 0; j < list.size(); j++) {
                        EmployeeBase employeeBase = list.get(j);
                        employeeBase.setSeniorityDate(date);
                        employeeBase.setLeaveDays(days);
                        employeeBaseDao.save(employeeBase);
                    }
                    try {
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        total = total + 1;
                    }
                }
            }
        }
        content[0] = Integer.toString(total);
        content[1] = repData;
        return content;

    }

    public String getSystemFilePath() {
        SysParaInf sp = Factory.getSysPara();
        String attachmentPath = sp.getProperty("attachmentPath");
        String attachmentPathType = sp.getProperty("attachmentPathType", "relative");
        if ("relative".equals(attachmentPathType)) {
            return Factory.getWebRoot() + File.separator + attachmentPath;
        }
        return attachmentPath;
    }

    /**
     * 格式化导入EXCEL文件中的日期
     *
     * @param strDate
     * @return
     * @throws ParseException
     */
    public Date formatDate(String strDate) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        if (strDate.indexOf(".") != -1) {
            strDate = strDate.replaceAll("\\.", "/");
        } else if (strDate.indexOf("—") != -1) {
            strDate = strDate.replaceAll("－", "/");
        } else if (strDate.indexOf("-") != -1) {
            strDate = strDate.replaceAll("-", "/");
        } else if (strDate.indexOf("/") != -1) {
            strDate = strDate.replaceAll("/", "/");
        }
        Date date = sdf.parse(strDate);
        return date;
    }

    /**
     * 根据系统时间自动更新可休年假的总天数
     */
    public void checkTotalDays() {
        try {
            System.out.println("------------系统定时更新可休年假总天数----开始--------");
            List<EmployeeBase> list = employeeBaseDao.getEmployeeBases();
            for (EmployeeBase employeeBase : list) {
                Date nowDate = new Date();
                String workAge = "0";//工龄
                String leaveDay = "0";//年假天数
                Date joinWorkDate = employeeBase.getJoinWorkDate();//参加工作时间
                Date intoWorkDate = employeeBase.getIntoWorkDate();//入院时间
                Date seniorityDate = employeeBase.getSeniorityDate();//计算工龄时间
                String extraDays = employeeBase.getExtraDays();//额外天数
                String empPosition = employeeBase.getEmpPosition();//员工身份 1:在编,2:聘用

                if (seniorityDate == null) {
                    if ("1".equals(empPosition)) {
                        if (joinWorkDate != null) {
                            //计算年假天数
                            if (nowDate.getYear() == joinWorkDate.getYear() && nowDate.getMonth() >= joinWorkDate.getMonth()) {
                                workAge = "0";
                            } else if (nowDate.getYear() > joinWorkDate.getYear()) {
                                if (nowDate.getMonth() >= joinWorkDate.getMonth()) {
                                    workAge = String.valueOf(nowDate.getYear() - joinWorkDate.getYear());
                                } else {
                                    workAge = String.valueOf(nowDate.getYear() - joinWorkDate.getYear() - 1);
                                }
                            }
                            if (Integer.valueOf(workAge) >= 0 && Integer.valueOf(workAge) < 1) {
                                leaveDay = "0";
                            } else if (Integer.valueOf(workAge) >= 1 && Integer.valueOf(workAge) < 10) {
                                leaveDay = "5";
                            } else if (Integer.valueOf(workAge) >= 10 && Integer.valueOf(workAge) < 20) {
                                leaveDay = "10";
                            } else if (Integer.valueOf(workAge) >= 20) {
                                leaveDay = "15";
                            }
                            if (extraDays != null) {
                                employeeBase.setTotalDays(String.valueOf(Integer.valueOf(leaveDay) + Integer.valueOf(extraDays)));
                            } else {
                                employeeBase.setExtraDays("0");
                                employeeBase.setTotalDays(String.valueOf(Integer.valueOf(leaveDay) + 0));
                            }
                            employeeBase.setSeniorityDate(joinWorkDate);
                            employeeBase.setLeaveDays(leaveDay);
                            employeeBaseDao.save(employeeBase);
                        }
                    } else if ("2".equals(empPosition)) {
                        if (intoWorkDate != null) {
                            //计算年假天数
                            if (nowDate.getYear() == intoWorkDate.getYear() && nowDate.getMonth() >= intoWorkDate.getMonth()) {
                                workAge = "0";
                            } else if (nowDate.getYear() > intoWorkDate.getYear()) {
                                if (nowDate.getMonth() >= intoWorkDate.getMonth()) {
                                    workAge = String.valueOf(nowDate.getYear() - intoWorkDate.getYear());
                                } else {
                                    workAge = String.valueOf(nowDate.getYear() - intoWorkDate.getYear() - 1);
                                }
                            }
                            if (Integer.valueOf(workAge) >= 0 && Integer.valueOf(workAge) < 1) {
                                leaveDay = "0";
                            } else if (Integer.valueOf(workAge) >= 1 && Integer.valueOf(workAge) < 10) {
                                leaveDay = "5";
                            } else if (Integer.valueOf(workAge) >= 10 && Integer.valueOf(workAge) < 20) {
                                leaveDay = "10";
                            } else if (Integer.valueOf(workAge) >= 20) {
                                leaveDay = "15";
                            }
                            if (extraDays != null) {
                                employeeBase.setTotalDays(String.valueOf(Integer.valueOf(leaveDay) + Integer.valueOf(extraDays)));
                            } else {
                                employeeBase.setExtraDays("0");
                                employeeBase.setTotalDays(String.valueOf(Integer.valueOf(leaveDay) + 0));
                            }
                            employeeBase.setSeniorityDate(intoWorkDate);
                            employeeBase.setLeaveDays(leaveDay);
                            employeeBaseDao.save(employeeBase);
                        }
                    }
                } else {
                    if ("1".equals(empPosition)) {
                        if (joinWorkDate != null) {
                            if (seniorityDate.getTime() != joinWorkDate.getTime()) {
                                seniorityDate = joinWorkDate;
                            }
                        }
                    } else if ("2".equals(empPosition)) {
                        if (intoWorkDate != null) {
                            if (seniorityDate.getTime() != intoWorkDate.getTime()) {
                                seniorityDate = intoWorkDate;
                            }
                        }
                    }
                    if (nowDate.getYear() == seniorityDate.getYear() && nowDate.getMonth() >= seniorityDate.getMonth()) {
                        workAge = "0";
                    } else if (nowDate.getYear() > seniorityDate.getYear()) {
                        if (nowDate.getMonth() >= seniorityDate.getMonth()) {
                            workAge = String.valueOf(nowDate.getYear() - seniorityDate.getYear());
                        } else {
                            workAge = String.valueOf(nowDate.getYear() - seniorityDate.getYear() - 1);
                        }
                    }
                    if (Integer.valueOf(workAge) >= 0 && Integer.valueOf(workAge) < 1) {
                        leaveDay = "0";
                    } else if (Integer.valueOf(workAge) >= 1 && Integer.valueOf(workAge) < 10) {
                        leaveDay = "5";
                    } else if (Integer.valueOf(workAge) >= 10 && Integer.valueOf(workAge) < 20) {
                        leaveDay = "10";
                    } else if (Integer.valueOf(workAge) >= 20) {
                        leaveDay = "15";
                    }
                    if (extraDays != null) {
                        employeeBase.setTotalDays(String.valueOf(Integer.valueOf(leaveDay) + Integer.valueOf(extraDays)));
                    } else {
                        employeeBase.setExtraDays("0");
                        employeeBase.setTotalDays(String.valueOf(Integer.valueOf(leaveDay) + 0));
                    }
                    employeeBase.setSeniorityDate(seniorityDate);
                    employeeBase.setLeaveDays(leaveDay);
                    employeeBaseDao.save(employeeBase);
                }
            }
            //employeeBaseDao.checkTotalDays();
            System.out.println("------------系统定时更新可休年假总天数----结束--------");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //获取员工职务信息
    public String getWorkTitle(String id) {
        String workTitle = "";
        List<String> list = employeeBaseDao.getWorkTitle(id);
        if (list != null && list.size() > 0) {
            workTitle = list.get(0);
        }
        return workTitle;
    }

    //新增 员工工资信息 员工公积金信息
    public void gz(HttpServletRequest request, EmployeeBase entity) {

        if (!tjy2YwfwbgzqrbDao.gzqr(entity.getId())) {
            // 没有员工的工资信息，则新增
            Tjy2Ywfwbgzqrb tjy2Ywfwbgzqrb = new Tjy2Ywfwbgzqrb();

            String initialEducation = entity.getInitialEducation();
            String initialDegree = entity.getInitial_degree();

            String education = getEduction(initialEducation, initialDegree, "gz");

            String empTitle = entity.getEmpTitle();
            String empTitlen = getEmpTitle(empTitle);

            // 姓名
            tjy2Ywfwbgzqrb.setName(entity.getEmpName());
            // 学历
            tjy2Ywfwbgzqrb.setEducation(education);
            // 岗位
            tjy2Ywfwbgzqrb.setJobs(entity.getPosition());
            // 职称
            tjy2Ywfwbgzqrb.setProfessional(empTitlen);
            // 员工id
            tjy2Ywfwbgzqrb.setNameId(entity.getId());
            // 部门
            tjy2Ywfwbgzqrb.setDepartment(entity.getWorkDepartmentName());
            // 部门id
            tjy2Ywfwbgzqrb.setDepartmentId(entity.getWorkDepartment());
            // 数据状态 1,在用 99,删除
            tjy2Ywfwbgzqrb.setData_status("1");
            // 本人是否确认 0，未确认 1，已确认
            tjy2Ywfwbgzqrb.setYesNo("0");
            tjy2Ywfwbgzqrb.setCreatTime(new Date());

            tjy2YwfwbgzqrbDao.save(tjy2Ywfwbgzqrb);
        } else {
            Tjy2Ywfwbgzqrb tjy2Ywfwbgzqrb = tjy2YwfwbgzqrbDao.getGzByEmp(entity.getId());
            if (tjy2Ywfwbgzqrb != null) {
                // 有员工的工资信息，则修改
                String initialEducation = entity.getInitialEducation();
                String initialDegree = entity.getInitial_degree();

                String education = getEduction(initialEducation, initialDegree, "gz");

                String empTitle = entity.getEmpTitle();
                String empTitlen = getEmpTitle(empTitle);


                // 姓名
                tjy2Ywfwbgzqrb.setName(entity.getEmpName());
                // 学历
                tjy2Ywfwbgzqrb.setEducation(education);
                // 岗位
                tjy2Ywfwbgzqrb.setJobs(entity.getPosition());
                // 职称
                tjy2Ywfwbgzqrb.setProfessional(empTitlen);
                // 员工id
                tjy2Ywfwbgzqrb.setNameId(entity.getId());
                // 部门
                tjy2Ywfwbgzqrb.setDepartment(entity.getWorkDepartmentName());
                // 部门id
                tjy2Ywfwbgzqrb.setDepartmentId(entity.getWorkDepartment());

                tjy2YwfwbgzqrbDao.save(tjy2Ywfwbgzqrb);
            }

        }

        if (!tjy2GjjDao.gJJ(entity.getId())) {

            // 没有员工的公积金信息，则新增
            Tjy2Gjj tjy2Gjj = new Tjy2Gjj();

            String initialEducation = entity.getInitialEducation();
            String initialDegree = entity.getInitial_degree();
            String education = getEduction(initialEducation, initialDegree, "gjj");

            // 姓名
            tjy2Gjj.setName(entity.getEmpName());
            // 学历
            tjy2Gjj.setXl(education);
            // 部门
            tjy2Gjj.setOrg(entity.getWorkDepartmentName());
            // 部门id
            tjy2Gjj.setOrgId(entity.getWorkDepartment());
            // 员工id
            tjy2Gjj.setNameId(entity.getId());
            // 进院时间
            tjy2Gjj.setJySj(entity.getIntoWorkDate());
            tjy2Gjj.setCjSj(new Date());
            // 数据状态
            tjy2Gjj.setData_status("1");
            // 本人确认
            tjy2Gjj.setBrqr("0");

            tjy2GjjDao.save(tjy2Gjj);
        } else {

            // 有员工的公积金信息，则修改
            Tjy2Gjj tjy2Gjj = tjy2GjjDao.getGjjByEmp(entity.getId());

            String initialEducation = entity.getInitialEducation();
            String initialDegree = entity.getInitial_degree();
            String education = getEduction(initialEducation, initialDegree, "gjj");

            // 姓名
            tjy2Gjj.setName(entity.getEmpName());
            // 学历
            tjy2Gjj.setXl(education);
            // 部门
            tjy2Gjj.setOrg(entity.getWorkDepartmentName());
            // 部门id
            tjy2Gjj.setOrgId(entity.getWorkDepartment());
            // 员工id
            tjy2Gjj.setNameId(entity.getId());
            // 进院时间
            tjy2Gjj.setJySj(entity.getIntoWorkDate());

            tjy2GjjDao.save(tjy2Gjj);
        }

    }
  	/*//新增 员工公积金信息
  	public void gjj(HttpServletRequest request,String id){
  		if(!tjy2GjjDao.gJJ(id)){
  			EmployeeBase entity = employeeBaseDao.get(id);
  			Tjy2Gjj tjy2Gjj = new Tjy2Gjj();

  			tjy2Gjj.setName(entity.getEmpName());
  			tjy2Gjj.setXl(entity.getInitial_degree());
  			tjy2Gjj.setOrg(entity.getWorkDepartmentName());
  			tjy2Gjj.setNameId(id);
  			tjy2Gjj.setJySj(entity.getFireDate());

  			tjy2GjjDao.save(tjy2Gjj);
  		}


  	}*/


    private String getEmpTitle(String empTitle) {
        String empTitlen = "";

        if (empTitle.contains("技术员")) {
            return getValue("lpryzc", "技术员");
        } else if (empTitle.contains("助理工程师")) {
            return getValue("lpryzc", "助理工程师");
        } else if (empTitle.equals("工程师")) {
            return getValue("lpryzc", "工程师");
        }
        return empTitlen;
    }

    public String getEduction(String initialEducation, String initialDegree, String type) {
        String education = "";

        if (initialEducation.contains("本科") || (initialDegree != null && initialDegree.contains("学士"))) {
            if (initialDegree != null) {
                String[] nn = initialDegree.replace("学士", ";学士;").split("学士");
                if (nn.length > 2) {
                    if ("gjj".equals(type)) {
                        return getValue("TJY2_XL", "本科双学位");
                    } else if ("gz".equals(type)) {
                        return getValue("lpryxl", "双学位大学本科");
                    }
                } else {
                    if ("gjj".equals(type)) {
                        return getValue("TJY2_XL", "本科");
                    } else if ("gz".equals(type)) {
                        return getValue("lpryxl", "大学本科");
                    }
                }
            } else {
                if ("gjj".equals(type)) {
                    return getValue("TJY2_XL", "本科");
                } else if ("gz".equals(type)) {
                    return getValue("lpryxl", "本科");
                }
            }
        } else if (initialEducation.contains("研究生") || initialEducation.contains("硕士")) {

            if ("gjj".equals(type)) {
                return getValue("TJY2_XL", "硕士（研究生）");

            } else if ("gz".equals(type)) {
                if (initialEducation.contains("研究生")) {
                    return getValue("lpryxl", "研究生");
                } else {
                    return getValue("lpryxl", "硕士");
                }

            }

        } else if (initialEducation.contains("博士")) {
            if ("gjj".equals(type)) {
                return getValue("TJY2_XL", "博士");
            } else if ("gz".equals(type)) {
                return getValue("lpryxl", "博士");
            }
        } else {
            if ("gjj".equals(type)) {
                return getValue("TJY2_XL", "大专及以下");
            } else if ("gz".equals(type)) {
                if (initialEducation.contains("大专") || initialEducation.contains("大学专科") || initialEducation.contains("专科")) {
                    return getValue("lpryxl", "大学专科");
                } else if (initialEducation.contains("中专")) {
                    return getValue("lpryxl", "中专");
                } else if (initialEducation.contains("高中")) {
                    return getValue("lpryxl", "高中");
                } else if (initialEducation.contains("初中")) {
                    return getValue("lpryxl", "初中");
                }

            }

        }


        return education;
    }


    /**
     * 获取码表值
     * author pingZhou
     *
     * @param tableCode 码表code
     * @param text      显示内容
     * @return
     */
    public String getValue(String tableCode, String text) {
        String sql = "select tv.value from PUB_CODE_TABLE t,PUB_CODE_TABLE_values tv where t.id = tv.code_table_id " +
                "and t.code = ? and tv.name =?";
        List<Object> list = employeeBaseDao.createSQLQuery(sql, tableCode, text).list();
        if (list.size() > 0) {
            return list.get(0).toString();
        }
        return "";
    }

    /**
     * 通过手机号获取员工
     *
     * @param empPhone
     * @return
     * @throws Exception
     */
    public List<EmployeeBase> checkInfoByPhone(String empPhone) throws Exception {
        List<EmployeeBase> list = new ArrayList<EmployeeBase>();
        list = employeeBaseDao.checkInfoByPhone(empPhone);
        return list;
    }

    /**
     * 通过省份证号获取员工
     *
     * @param empIdCard
     * @return
     * @throws Exception
     */
    public List<EmployeeBase> checkInfoByIdCard(String empIdCard) throws Exception {
        List<EmployeeBase> list = new ArrayList<EmployeeBase>();
        list = employeeBaseDao.checkInfoByIdCard(empIdCard);
        return list;
    }

    /**
     * 通过职称证书编号获取员工
     *
     * @param empTitleNum
     * @return
     * @throws Exception
     */
    public List<EmployeeBase> checkInfoByTitleNum(String empTitleNum) throws Exception {
        List<EmployeeBase> list = new ArrayList<EmployeeBase>();
        list = employeeBaseDao.checkInfoByTitleNum(empTitleNum);
        return list;
    }

    /**
     * 判断是否修改了关键字段的值如：进院时间、工作部门、工作职务、在职状态、职称、在职学历,
     * 只要修改了其中任意一项，就返回false。
     *
     * @param entity 在职员工信息
     * @return true or false
     */
    public boolean isModifiedKeyWordValue(EmployeeBase entity) {
        String id = entity.getId();
        Map<String, Object> map = this.employeeBaseDao.getKeyWordValue(id);

        // 判断进院时间是否修改
        String intoWorkDateOld;
        if (map.get("INTO_WORK_DATE")==null){
            intoWorkDateOld = "";
        } else {
            intoWorkDateOld = String.valueOf(DateUtil.format((Date)map.get("INTO_WORK_DATE"),"yyyy-MM-dd HH:mm:ss"));
        }
        String intoWorkDateNew = String.valueOf(DateUtil.format(entity.getIntoWorkDate(),"yyyy-MM-dd HH:mm:ss"));
        if (notTheSame(intoWorkDateNew, intoWorkDateOld)) {
            return true;
        }

        // 判断工作部门是否修改
        String workDepartmentOld = String.valueOf(map.get("WORK_DEPARTMENT"));
        String workDepartmentNew = entity.getWorkDepartment();
        if (notTheSame(workDepartmentNew, workDepartmentOld)) {
            return true;
        }

        // 判断工作职务是否修改
        String workTitleOld = String.valueOf(map.get("WORK_TITLE"));
        String workTitleNew = entity.getWorkTitle();
        if (notTheSame(workTitleNew, workTitleOld)) {
            return true;
        }

        // 判断在职状态是否修改
        String empStatusOld = String.valueOf(map.get("EMP_STATUS"));
        String empStatusNew = entity.getEmpStatus();
        if (notTheSame(empStatusNew, empStatusOld)) {
            return true;
        }

        // 判断职称是否修改
        String empTitleOld = String.valueOf(map.get("EMP_TITLE"));
        String empTitleNew = entity.getEmpTitle();
        if (notTheSame(empTitleNew, empTitleOld)) {
            return true;
        }

        // 判断初始学历是否修改
        String initialEducationOld = String.valueOf(map.get("INITIAL_EDUCATION"));
        String initialEducationNew = entity.getInitialEducation();
        if (notTheSame(initialEducationNew, initialEducationOld)) {
            return true;
        }

        //  判断初始学位是否修改
        String initialDegreeOld = String.valueOf(map.get("INITIAL_DEGREE"));
        String initialDegreeNew = entity.getInitial_degree();
        if (notTheSame(initialDegreeNew, initialDegreeOld)) {
            return true;
        }
        return false;
    }

    /**
     * 判断值是否相同，如果相同返回false，不同返回true
     *
     * @param var0 值1
     * @param var1 值2
     * @return true or false
     */
    private boolean notTheSame(String var0, String var1) {
        boolean b = false;
        if("null".equals(var0)) {
            var0 = null;
        }
        if("null".equals(var1)) {
            var1 = null;
        }
        if (StringUtil.isNotEmpty(var0) && StringUtil.isNotEmpty(var1)) {
            // 都不为空，则判断值是否一样
            if (!var1.equals(var0)) {
                b = true;
            }
        } else if ((StringUtil.isEmpty(var1) && StringUtil.isNotEmpty(var0)) || (StringUtil.isNotEmpty(var1) && StringUtil.isEmpty(var0))) {
            // 只有其中一个为空
            b = true;
        }
        return b;
    }
}