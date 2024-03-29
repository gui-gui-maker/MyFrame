package com.lsts.humanresources.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.khnt.base.Factory;
import com.khnt.core.crud.dao.impl.EntityDaoImpl;
import com.khnt.rbac.impl.bean.Employee;
import com.khnt.utils.StringUtil;
import com.lsts.humanresources.bean.EmployeeBase;
import com.lsts.humanresources.bean.RemindMessage;
import com.lsts.humanresources.bean.RsSummaryDTO;
import com.lsts.inspection.bean.FlowInfoDTO;

/**
 * <p>
 *
 * </p>
 *
 * @author
 * @ClassName Tjy2RlEmployeeBaseDao
 * @JDK 1.5
 * @date
 */
@Repository("employeeBaseDao")
public class EmployeeBaseDao extends EntityDaoImpl<EmployeeBase> {

    private static Connection conn = null;  // 数据库连接
    private static PreparedStatement pstmt = null; // 数据库操作对象
    private static ResultSet rs = null; // 结果集

    //根据员工ID查询此用户是否存在
    public Boolean checksysuser(String empId) {
        boolean isexist = false;
        String sql = "select * from SYS_USER where EMPLOYEE_ID=?";
        List<?> list = this.createSQLQuery(sql, empId).list();
        if (list != null && !list.isEmpty()) {
            isexist = true;
        }
        return isexist;
    }

    //根据员工姓名和部门ID查询此用户是否存在
    public Boolean checksysuser1(String name, String org_id) {
        boolean isexist = false;
        String sql = "select * from SYS_USER where NAME=? and ORG_ID=?";
        List<?> list = this.createSQLQuery(sql, name, org_id).list();
        if (list != null && !list.isEmpty()) {
            isexist = true;
        }
        return isexist;
    }

    /**
     * 根据员工姓名和状态查询员工
     *
     * @param empName
     * @param empStatus
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<EmployeeBase> queryEmployeesByNameAndStatus(String empName, String empStatus) {
        List<EmployeeBase> list = new ArrayList<EmployeeBase>();
        String hql = "from EmployeeBase e where e.empName=? and e.empStatus=?";
        list = this.createQuery(hql, empName, empStatus).list();
        return list;
    }

    /**
     * 根据员工姓名查询员工
     *
     * @param empName
     * @param empStatus
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<EmployeeBase> queryEmployeesByName(String empName, String empStatus) {
        String hql = "from EmployeeBase e where e.empName = '" + empName.replaceAll(" ", "") + "' and e.empStatus in " +
                "(3,4)";
        List<EmployeeBase> list = new ArrayList<EmployeeBase>();
        list = this.createQuery(hql).list();
        return list;
    }

    //学历、专业、性别、职称、职务统计
    public List<?> edumajorCount(String condition) {
        String sql = null;
        if (condition.equals("0")) {
            sql = "select education_class,numbers,round(100*ratio_to_report(numbers) over(),2) as per from(" +
                    "select education_class,count(education_class) numbers from " +
                    "(select " +
                    "CASE when education like '%博士%' THEN '博士' " +
                    "when education like '%硕士%' THEN '硕士' " +
                    "when education like '%本科%' THEN '本科' " +
                    "when education like '%专%' THEN '专科' " +
                    "when education like '%高中%' THEN '高中' " +
                    "when education like '%初中%' THEN '初中' " +
                    "when education like '%小学%' THEN '小学' " +
                    "else null " +
                    "end education_class " +
                    "from (select   nvl(t.mba_education,t.initial_education) education  from TJY2_RL_EMPLOYEE_BASE t"
                    + " where EMP_STATUS in (3,4))) " +
                    "group by education_class having education_class is not null)";
    		/*String sql = "select "+
					"count(case when education like '%博士%' THEN '博士' end) 博士,"+
					"count(case when education like '%硕士%' THEN '硕士' end) 硕士,"+
					"count(case when education like '%本科%' THEN '本科' end) 本科,"+
					"count(case when education like '%专%' THEN '专科' end) 本科,"+
					"count(case when education like '%高中%' THEN '高中' end) 高中,"+
					"count(case when education like '%初中%' THEN '初中' end) 初中,"+
					"count(case when education like '%小学%' THEN '小学' end) 小学"+
					"from (select   nvl(t.mba_education,t.initial_education) education  from TJY2_RL_EMPLOYEE_BASE t)
					";*/
        } else if (condition.equals("1")) {
            sql = "select major,numbers,round(100*ratio_to_report(numbers) over(),2) as per from ( " +
                    "select major,count(major) numbers " +
                    "from (select   nvl(t.MBA_MAJOR,t.INITIAL_MAJOR) major  from TJY2_RL_EMPLOYEE_BASE t"
                    + " where EMP_STATUS in (3,4)) " +
                    "group by major having major is not null) ";
        } else if (condition.equals("2")) {
            sql = "select EMP_SEX,numbers,round(100*ratio_to_report(numbers) over(),2) as per from( " +
                    "select EMP_SEX,count(EMP_SEX) numbers from " +
                    "(select case when EMP_SEX='0' then '女' " +
                    "when EMP_SEX='1' then '男' " +
                    "else null " +
                    "end EMP_SEX from TJY2_RL_EMPLOYEE_BASE where EMP_SEX is not null and EMP_STATUS in (3,4)) group " +
                    "by EMP_SEX)";
        } else if (condition.equals("3")) {
            sql = "select EMP_TITLE,numbers,round(100*ratio_to_report(numbers) over(),2) as per from( " +
                    "select EMP_TITLE,count(EMP_TITLE) numbers from " +
                    "(select EMP_TITLE from TJY2_RL_EMPLOYEE_BASE where EMP_TITLE is not null and  EMP_STATUS in (3," +
                    "4)) group by EMP_TITLE)";
        } else if (condition.equals("4")) {
            // sql = "select WORK_TITLE,numbers,round(100*ratio_to_report(numbers) over(),2) as per from( " +
            //         "select WORK_TITLE,count(WORK_TITLE) numbers from " +
            //         "(select WORK_TITLE from TJY2_RL_EMPLOYEE_BASE where WORK_TITLE is not null and  EMP_STATUS in " +
            //         "(3,4)) group by WORK_TITLE)";
            sql = "select WORK_TITLE, NUMBERS, round(100 * ratio_to_report(NUMBERS) over (), 2) as PER\n" +
                    "  from (\n" +
                    "         select WORK_TITLE, count(WORK_TITLE) NUMBERS\n" +
                    "           from (select regexp_substr(T.WORK_TITLE, '[^,]+', 1, ROWNUM) WORK_TITLE\n" +
                    "                   from (select regexp_replace(to_char(wm_concat(WORK_TITLE)), '、', ',') " +
                    "WORK_TITLE\n" +
                    "                           from TJY2_RL_EMPLOYEE_BASE\n" +
                    "                          where WORK_TITLE is not null\n" +
                    "                            and EMP_STATUS in (3, 4)) T\n" +
                    "                connect by ROWNUM <= length(regexp_replace(T.WORK_TITLE, '[^,]', null)) + 1)\n" +
                    "          group by WORK_TITLE)";
        } else if ("5".equals(condition)) {
            // 编制情况按在编/聘用进行划分
            sql = "select POS, NUMBERS, round(100 * ratio_to_report(NUMBERS) over (), 2) as PER\n" +
                    "  from (\n" +
                    "         select POS, count(POS) NUMBERS\n" +
                    "           from (\n" +
                    "                  select case\n" +
                    "                           when B.EMP_POSITION = '1' then '在编'\n" +
                    "                           when B.EMP_POSITION = '2' then '聘用'\n" +
                    "                           else '其它('||B.EMP_POSITION||')' end POS\n" +
                    "                    from TJY2_RL_EMPLOYEE_BASE B\n" +
                    "                   where B.EMP_STATUS in (3, 4)\n" +
                    "                )\n" +
                    "          group by POS)";
        } else if ("6".equals(condition)) {
            // 中干情况
            sql = "select WORK_TITLE, NUMBERS, round(100 * ratio_to_report(NUMBERS) over (), 2) as PER\n" +
                    "  from (\n" +
                    "         select WORK_TITLE, count(WORK_TITLE) NUMBERS\n" +
                    "           from (select regexp_substr(T.WORK_TITLE, '[^,]+', 1, ROWNUM) WORK_TITLE\n" +
                    "                   from (select regexp_replace(to_char(wm_concat(WORK_TITLE)), '、', ',') " +
                    "WORK_TITLE\n" +
                    "                           from TJY2_RL_EMPLOYEE_BASE\n" +
                    "                          where WORK_TITLE is not null\n" +
                    "                            and (instr(WORK_TITLE, '车队') = 0 and instr(WORK_TITLE, '委员') = 0 " +
                    "and\n" +
                    "                                 instr(WORK_TITLE, '享受') = 0\n" +
                    "                            and instr(WORK_TITLE, '主任助理') = 0 and instr(WORK_TITLE, '管道技术负责人') =" +
                    " 0)\n" +
                    "                            and EMP_STATUS in (3, 4)) T\n" +
                    "                connect by ROWNUM <= length(regexp_replace(T.WORK_TITLE, '[^,]', null)) + 1)\n" +
                    "          group by WORK_TITLE)";
        } else if ("7".equals(condition)) {
            // 年龄层次,按照20〜40，41〜60,60岁以上的进行划分
            sql = "select AGE, NUMBERS, round(100 * ratio_to_report(NUMBERS) over (), 2) as PER\n" +
                    "  from (\n" +
                    "         select AGE, count(AGE) NUMBERS\n" +
                    "           from (select case\n" +
                    "                          when BASE.AGE between 20 and 40 then '青年(20~40)'\n" +
                    "                          when BASE.AGE between 41 and 60 then '中年(41~60)'\n" +
                    "                          else '其它(' || to_char(AGE) || ')' end AGE\n" +
                    "                   from (\n" +
                    "                          select T.EMP_NAME,\n" +
                    "                                 T.EMP_ID_CARD,\n" +
                    "                                 to_char(sysdate, 'yyyy') - substr(EMP_ID_CARD, 7, 4) as AGE\n" +
                    "                            from TJY2_RL_EMPLOYEE_BASE T\n" +
                    "                           where T.EMP_STATUS in (3, 4)\n" +
                    "                             and T.EMP_ID_CARD is not null) BASE)\n" +
                    "          group by AGE) order by numbers";
        } else if ("8".equals(condition)) {
            // 学历情况
            sql = "select education_class,numbers,round(100*ratio_to_report(numbers) over(),2) as per from(" +
                    "select education_class,count(education_class) numbers from " +
                    "(select " +
                    "CASE when education like '%博士%' THEN '博士' " +
                    "when education like '%硕士%' THEN '硕士' " +
                    "when education like '%本科%' THEN '本科' " +
                    "when education like '%专%' THEN '专科' " +
                    "when education like '%高中%' THEN '高中' " +
                    "when education like '%初中%' THEN '初中' " +
                    "when education like '%小学%' THEN '小学' " +
                    "else null " +
                    "end education_class " +
                    "from (select   nvl(t.mba_education,t.initial_education) education  from TJY2_RL_EMPLOYEE_BASE t"
                    + " where EMP_STATUS in (3,4))) " +
                    "group by education_class having education_class is not null)";
        } else if("9".equals(condition)) {
            // 人员性质
            sql = "select POS, NUMBERS, round(100 * ratio_to_report(NUMBERS) over (), 2) as PER\n" +
                    "  from (\n" +
                    "         select POS, count(POS) NUMBERS\n" +
                    "           from (\n" +
                    "                  select case\n" +
                    "                           when B.POSITION in ('3', '2') then '后勤(含管理岗)'\n" +
                    "                           when B.POSITION in ('4', '6') then '检验员(含专业技术岗)'\n" +
                    "                           when B.POSITION = '1' then '工勤'\n" +
                    "                           when B.POSITION = '5' then '外借' end POS\n" +
                    "                    from TJY2_RL_EMPLOYEE_BASE B\n" +
                    "                   where B.EMP_STATUS in (3, 4)\n" +
                    "                     and B.POSITION is not null)\n" +
                    "          group by POS)";
        }

        List<?> list = this.createSQLQuery(sql).list();
        return list;
    }

    //微信端人力资源全院概况统计
    public List<RsSummaryDTO> weixinHRCount() throws Exception {
        List<RsSummaryDTO> list = new ArrayList<RsSummaryDTO>();
        try {
            conn = getConn();
            String sql = "SELECT "
                    + "COUNT(B.ID) AS total,"
                    + "COUNT(CASE WHEN B.EMP_SEX  IN ('1') THEN 'man' END) man,"
                    + "COUNT(CASE WHEN B.EMP_SEX IN ('0') THEN 'woman' END) woman,"
                    + "COUNT(CASE WHEN B.EMP_POSITION IN ('1') THEN 'regular' END) regular,"
                    + "COUNT(CASE WHEN B.INITIAL_EDUCATION like '%博士%' THEN 'PHD' END) phd,"
                    + "COUNT(CASE WHEN B.INITIAL_EDUCATION like '%研究生%' THEN 'postgraduate' END) postgraduate,"
                    + "COUNT(CASE WHEN B.INITIAL_EDUCATION like '%本科%' THEN 'undergraduate' END) undergraduate,"
                    + "COUNT(CASE WHEN B.EMP_POLITICAL like '%党员%' THEN ' member_CPC' END) member_CPC,"
                    + "COUNT(CASE WHEN B.EMP_POLITICAL like '%团员%' THEN 'member_CYL' END) member_CYL,"
                    + "COUNT(CASE WHEN B.EMP_PERMIT like '%高级工程师%' THEN 'senior_engineer' END) senior_engineer,"
                    + "COUNT(CASE WHEN B.EMP_PERMIT like '%高级工程师%' THEN 'P_senior_engineer' END) P_senior_engineer,"
                    + "COUNT(CASE WHEN B.EMP_PERMIT like '%工程师%' THEN 'enginer' END) enginer,"
                    + "COUNT(CASE WHEN B.EMP_PERMIT like '%检验师%' THEN 'examiner' END) examiner,"
                    + "COUNT(CASE WHEN B.EMP_PERMIT like '%承压类检验师%' THEN ' pressure_examiner' END) pressure_examiner,"
                    + "COUNT(CASE WHEN B.EMP_PERMIT like '%机电类检验师%' THEN 'ele_examiner' END) ele_examiner,"
                    + "COUNT(CASE WHEN B.EMP_PERMIT like '%无损检测Ⅲ级（高级）证%' THEN 'non_destructive' END) non_destructive,"
                    + "COUNT(CASE WHEN B.EMP_PERMIT like '%Ⅱ级（中级）证%' THEN 'second_class' END) second_class "
                    + "FROM TJY2_RL_EMPLOYEE_BASE B  ";
            System.out.println(sql.toString());
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                RsSummaryDTO rsSummaryDTO = new RsSummaryDTO();
                rsSummaryDTO.setTotal(rs.getString(1));
                rsSummaryDTO.setMan(rs.getString(2));
                rsSummaryDTO.setWoman(rs.getString(3));
                rsSummaryDTO.setRegular(rs.getString(4));
                rsSummaryDTO.setPhd(rs.getString(5));
                rsSummaryDTO.setPostgraduate(rs.getString(6));
                rsSummaryDTO.setUndergraduate(rs.getString(7));
                rsSummaryDTO.setMember_CPC(rs.getString(8));
                rsSummaryDTO.setMember_CYL(rs.getString(9));
                rsSummaryDTO.setSenior_engineer(rs.getString(10));
                rsSummaryDTO.setP_senior_engineer(rs.getString(11));
                rsSummaryDTO.setEnginer(rs.getString(12));
                rsSummaryDTO.setExaminer(rs.getString(13));
                rsSummaryDTO.setPressure_examiner(rs.getString(14));
                rsSummaryDTO.setEle_examiner(rs.getString(15));
                rsSummaryDTO.setNon_destructive(rs.getString(16));
                rsSummaryDTO.setSecond_class(rs.getString(17));
                list.add(rsSummaryDTO);
            }
            closeConn();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    //获取试用及在职员工数据
    @SuppressWarnings("unchecked")
    public List<EmployeeBase> getEmployeeBases() {
        List<EmployeeBase> list = new ArrayList<EmployeeBase>();
        String hql = "from EmployeeBase t where t.empStatus='3' or t.empStatus='4'";
        list = this.createQuery(hql).list();
        return list;
    }

    // 获取数据库连接
    public Connection getConn() {
        try {
            conn = Factory.getDB().getConnetion();
        } catch (Exception e) {
            logger.error("获取数据库连接失败：" + e.getMessage());
            e.printStackTrace();
        }
        return conn;
    }

    // 关闭连接
    public void closeConn() {
        try {
            Factory.getDB().freeConnetion(conn);    // 释放连接
        } catch (Exception e) {
            logger.error("关闭数据库连接错误：" + e.getMessage());
            e.printStackTrace();
        }
    }

    public List<EmployeeBase> queryEmployeesByNameAndDepart(String empName, String empStatus) {
        List<EmployeeBase> list = new ArrayList<EmployeeBase>();
        String hql = "from EmployeeBase e where e.empName=? and e.empStatus=? and e.workDepartment is not null order " +
                "by e.empPhone";
        list = this.createQuery(hql, empName, empStatus).list();
        return list;
    }

    /**
     * 根据系统时间自动更新可休年假的总天数
     */
    public void checkTotalDays() {
        List<EmployeeBase> list = new ArrayList<EmployeeBase>();
        try {
            //计算工龄时间不为空，额外天数不为空
            String hql = "from EmployeeBase  where seniorityDate is not null and extraDays is not null";
            list = this.createQuery(hql).list();
            if (list.size() > 0 && list != null) {
                for (int i = 0; i < list.size(); i++) {
                    String workAge = "0";//工龄
                    String leaveDay = "0";//年假天数
                    Date nowDate = new Date();//当前系统时间

                    EmployeeBase employeeBase = list.get(i);
                    Date seniorityDate = employeeBase.getSeniorityDate();
                    String extraDays = employeeBase.getExtraDays();//额外天数

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
                    employeeBase.setLeaveDays(leaveDay);
                    employeeBase.setTotalDays(String.valueOf(Integer.valueOf(leaveDay) + Integer.valueOf(extraDays)));
                    this.save(employeeBase);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取人员休假基本信息
     *
     * @return
     */
    public List<?> getLeaveBaseInfo(String org_id, String userName) {
        String sql = "select o.org_name, " +
                "t.emp_name, " +
                "t.id, " +
                "t.into_work_date, " +
                "floor(MONTHS_BETWEEN(sysdate, t.seniority_date) / 12) as work_age, " +
                "t.total_days " +
                "from tjy2_rl_employee_base t, sys_org o " +
                "where t.work_department = o.id(+) " +
                "and t.emp_status in ('3', '4') " +
                "and t.work_department != '100061' ";
        if (StringUtil.isNotEmpty(userName)) {
            sql += "and t.emp_name = '" + userName + "' ";
        }
        if (StringUtil.isNotEmpty(org_id) && !"all".equals(org_id)) {
            sql += " and t.work_department = '" + org_id + "' ";
        }
        sql += "order by o.orders, t.sort";
        List<?> list = this.createSQLQuery(sql).list();
        return list;
    }

    public List<String> getWorkTitle(String id) {
        String sql =
                "select t.work_title from TJY2_RL_EMPLOYEE_BASE t where t.emp_status in ('3','4') and t.id='" + id +
                        "'";
        List<String> list = this.createSQLQuery(sql).list();
        return list;
    }

    //根据员工姓名和手机号获取员工ID
    public String getEmpId(String name, String mobilePhone) {
        List<EmployeeBase> list = new ArrayList<EmployeeBase>();
        String hql = "from EmployeeBase t where t.empStatus in ('3','4','5','6') and t.empName='" + name + "' ";
        if (StringUtil.isNotEmpty(mobilePhone)) {
            hql += "and t.empPhone='" + mobilePhone + "' ";
        }
        list = this.createQuery(hql).list();
        return list.get(0).getId();
    }

    /**
     * 通过手机号获取员工
     *
     * @param empPhone
     * @return
     */
    public List<EmployeeBase> checkInfoByPhone(String empPhone) {
        List<EmployeeBase> list = new ArrayList<EmployeeBase>();
        String hql = "from EmployeeBase t where t.empStatus in ('3','4','5','6') and t.empPhone='" + empPhone + "' ";
        list = this.createQuery(hql).list();
        return list;
    }

    /**
     * 通过省份证号获取员工
     *
     * @param empIdCard
     * @return
     */
    public List<EmployeeBase> checkInfoByIdCard(String empIdCard) {
        List<EmployeeBase> list = new ArrayList<EmployeeBase>();
        String hql = "from EmployeeBase t where t.empStatus in ('3','4','5','6') and t.empIdCard='" + empIdCard + "' ";
        list = this.createQuery(hql).list();
        return list;
    }

    /**
     * 通过职称证书编号获取员工
     *
     * @param empTitleNum
     * @return
     */
    public List<EmployeeBase> checkInfoByTitleNum(String empTitleNum) {
        List<EmployeeBase> list = new ArrayList<EmployeeBase>();
        String hql =
                "from EmployeeBase t where t.empStatus in ('3','4','5','6') and t.empTitleNum='" + empTitleNum + "' ";
        list = this.createQuery(hql).list();
        return list;
    }

    /**
     * 根据用户id获取用户的角色名称
     *
     * @param user_id 员工id
     * @param unit_id 部门id
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<String> getEmployeeRole(String user_id, String unit_id) {
        String sql = "select SR.NAME" +
                "  from SYS_USER SU, SYS_USER_ROLE SUR, SYS_ROLE SR" +
                " where SU.ID = SUR.USER_ID" +
                "   AND SUR.ROLE_ID = SR.ID" +
                "   AND SU.ID = '" + user_id + "'";
        if (StringUtil.isNotEmpty(unit_id)) {
            sql = sql + "   AND SU.ORG_ID = '" + unit_id + "'";
        }
        List<String> list = this.createSQLQuery(sql).list();
        return list;
    }

    public Map<String,Object> getKeyWordValue(String id) {
        // language=Oracle
        String sql = "select b.INTO_WORK_DATE,b.WORK_DEPARTMENT,b.WORK_TITLE,b.EMP_STATUS,b.EMP_TITLE,b.MBA_EDUCATION,b.INITIAL_EDUCATION,b.INITIAL_DEGREE from TJY2_RL_EMPLOYEE_BASE b where b.id = ?";
        return (Map<String,Object>) this.createSQLQuery(sql,id).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).uniqueResult();
    }
}