package com.lsts.employee.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.khnt.core.crud.dao.impl.EntityDaoImpl;
import com.khnt.rbac.impl.bean.Employee;
import com.khnt.rbac.impl.bean.Org;
import com.khnt.rbac.impl.bean.User;

/**
 * 人员信息数据处理对象
 * @ClassName EmployeesDao
 * @JDK 1.7
 * @author GaoYa
 * @date 2014-03-03 下午03:19:00
 */
@Repository("employeesDao")
public class EmployeesDao extends EntityDaoImpl<Employee>{

	/**
	 * 删除人员信息
	 * @param ids
	 * @return 
	 * @author GaoYa
	 * @date 2014-02-18 上午11:24:00
	 */
    public void delete(String ids) {
        String arr[] = ids.split(",");
        for (int i = 0; i < arr.length; i++) {
            super.removeById(arr[i]);	//删除人员信息
            this.createQuery("delete from EmployeeCert c where c.employee.id=?", arr[i]).executeUpdate();	// 人员持证情况    	 
            this.createQuery("delete from User u where u.employee.id=?", arr[i]).executeUpdate();	// 系统用户
            this.createQuery("delete from EmployeePrinter p where p.employee.id=?", arr[i]).executeUpdate();	// 人员打印机设置  
        }
    }
    
    /**
	 * 根据身份证号查询用户信息
	 * @param idNo -- 身份证号
	 * @author GaoYa
	 * @date 2014-03-04 上午10:40:00
	 */
    public Employee queryEmployeeByIdNo(String idNo) {
        String hql = "from Employee e where e.idNo=?";
        return (Employee)this.createQuery(hql, idNo).uniqueResult();
    }
    
    /**
	 * 根据人员姓名和部门ID查询用户信息
	 * @param idNo -- 身份证号
	 * @author GaoYa
	 * @date 2014-03-04 上午10:40:00
	 */
    public Employee queryEmployee(String name, String org_id) {
        String hql = "from Employee e where e.name=? and e.org.id=?";
        return (Employee)this.createQuery(hql, name, org_id).uniqueResult();
    }
    
    /**
	 * 根据人员姓名查询用户信息
	 * @param name -- 姓名
	 * @author GaoYa
	 * @date 2014-03-04 上午10:40:00
	 */
    @SuppressWarnings("unchecked")
    public List<Employee> queryEmployees(String name) {
    	List<Employee> list = new ArrayList<Employee>();
        String hql = "from Employee e where e.name=?";
        list = this.createQuery(hql, name).list();
        return list;
    }
    
    /**
	 * 根据人员姓名和部门ID查询用户信息
	 * @param name -- 姓名
	 * @param org_id -- 部门ID
	 * 
	 * @author GaoYa
	 * @date 2014-03-04 上午10:40:00
	 */
    @SuppressWarnings("unchecked")
    public List<Employee> queryEmployees(String name, String org_id) {
    	List<Employee> list = new ArrayList<Employee>();
        String hql = "from Employee e where e.name=? and e.org.id=?";
        list = this.createQuery(hql, name, org_id).list();
        return list;
    }
    
    /**根据id查询用户信息
     * @param id
     * @return
     */
    public Employee queryEmployeeById(String id) {
        String hql = "from Employee e where e.id=?";
        return (Employee)this.createQuery(hql, id).uniqueResult();
    }
    
    /**
     * 根据userId查询Employee信息
     * @param userId
     * 
     * @return
     */
    @SuppressWarnings("unchecked")
    public Employee queryEmployeeByUser(String userId) {
    	String emp_id = "";
		String sql = "select u.EMPLOYEE_ID from SYS_USER u where u.id=?";
		List list = this.createSQLQuery(sql, userId).list(); 
		if(list.size()>0){
			emp_id = String.valueOf(list.get(0));
		}
        String hql = "from Employee e where e.id=?";
        return (Employee)this.createQuery(hql, emp_id).uniqueResult();
    }
    
    /**
     * 根据userId和userName查询Employee信息
     * @param userId
     * @param userName
     * 
     * @return
     */
    @SuppressWarnings("unchecked")
    public Employee queryEmployeeByUser(String userId, String userName) {
    	String emp_id = "";
		String sql = "select u.EMPLOYEE_ID from SYS_USER u where u.id=? and u.name=?";
		List list = this.createSQLQuery(sql, userId, userName).list(); 
		if(list.size()>0){
			emp_id = String.valueOf(list.get(0));
		}
        String hql = "from Employee e where e.id=?";
        return (Employee)this.createQuery(hql, emp_id).uniqueResult();
    }
    
    /**
     * 根据Employee ID查询SYS_USER ID信息
     * @param emp_id -- Employee ID
     * 
     * @return
     */
    public String queryUserIdByEmpId(String emp_id) {
    	String user_id = "";
		String sql = "select u.ID from SYS_USER u where u.EMPLOYEE_ID=?";
		List list = this.createSQLQuery(sql, emp_id).list(); 
		if(list.size()>0){
			user_id = String.valueOf(list.get(0));
		}
        return user_id;
    }
    
    /** 根据姓名或电话号码获取人员信息
	 * @param q
	 * @return
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
    public List<Employee> suggest(String q) {
    	System.out.println("电话或者名字："+q);
    	System.out.println(q+" 的数据类型为："+q.getClass().getSimpleName());
        String hql = "from Employee t where t.name like '%"+q+"%' or t.mobileTel like '"+q+"%'";
        List<Employee> list = createQuery(hql).list();
        return list;

    }
    /** 根据机构名称获取机构信息
	 * @param orgName
	 * @return
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
    public List<Org> getOrg(String orgName) {
        String hql = "from Org t where t.orgName like '%" +orgName+"%'"+" and t.status='used'";
        List<Org> list = createQuery(hql).list();
        return list;

    }
    
    /**
	 * 根据部门ID和姓名查询登录用户信息
	 * @param org_id -- 部门ID
	 * @param name -- 姓名
	 * @author GaoYa
	 * @date 2015-09-18 上午10:32:00
	 */
	@SuppressWarnings("unchecked")
	public String getUserID(String org_id,String name){
		String value="";
		String sql = "select u.id from SYS_USER u where u.org_id=? and u.name=?";
		List list = this.createSQLQuery(sql, org_id, name).list(); 
		if(list.size()>0){
			value = String.valueOf(list.get(0));
		}
		return value;
	}
	
	/**
	 * 根据部门ID和姓名查询登录用户信息
	 * @param org_id -- 部门ID
	 * @param name -- 姓名
	 * @author GaoYa
	 * @date 2015-09-18 上午10:32:00
	 */
	@SuppressWarnings("unchecked")
	public String getEmployee(String org_id,String name){
		String value="";
		String sql = "select u.id from employee u where u.org_id=? and u.name=?";
		List list = this.createSQLQuery(sql, org_id, name).list(); 
		if(list.size()>0){
			value = String.valueOf(list.get(0));
		}
		return value;
	}
	
	/**
	 * 根据emp_id查询用户手机号码
	 * @param emp_id -- Employee ID
	 * @return mobile -- 手机号码
	 * 
	 * @author GaoYa
	 * @date 2016-10-16 上午11:24:00
	 */
	@SuppressWarnings("unchecked")
	public String getMobile(String emp_id){
		String value="";
		String sql = "select u.MOBILE_TEL from employee u where u.id=?";
		List list = this.createSQLQuery(sql, emp_id).list(); 
		if(list.size()>0){
			value = String.valueOf(list.get(0));
		}
		return value;
	}
	
	/**
	 * 根据user_id查询用户手机号码
	 * @param user_id -- sys_user id
	 * @return mobile -- 手机号码
	 * 
	 * @author GaoYa
	 * @date 2017-08-21 下午17:46:00
	 */
	public String getMobileByUserId(String user_id){
		String value="";
		String sql = "select MOBILE_TEL from EMPLOYEE where Id in (select t.employee_id from SYS_USER t where t.id=?)";
		List list = this.createSQLQuery(sql, user_id).list(); 
		if(list.size()>0){
			value = String.valueOf(list.get(0));
		}
		return value;
	}
	
	/**
	 * 根据部门ID查询部门人员信息
	 * @param org_id -- 部门ID
	 * 
	 * @author GaoYa
	 * @date 2015-11-05 15:07:00
	 */
	@SuppressWarnings("unchecked")
	public Map<String, Object> getEmployees(String org_id){
		HashMap<String, Object> wrapper = new HashMap<String, Object>();
		String sql = "select e.id,e.name text from employee e where e.org_id=?";
		List list = this.createSQLQuery(sql, org_id).list(); 
		if(list.size()>0){
			for (int i = 0; i < list.size(); i++) {
				Object[] objArr = list.toArray();
				Object[] obj = (Object[]) objArr[i];
				wrapper.put("id", obj[0]);
				wrapper.put("text", obj[1]);
			}
		}
		return wrapper;
	}
	
	/**
	 * 根据姓名查询登录用户信息
	 * @param name -- 姓名
	 * @author GaoYa
	 * @date 2015-10-08 上午11:45:00
	 */
	@SuppressWarnings("unchecked")
	public String getUserID(String name){
		String value="";
		String sql = "select u.id from SYS_USER u where u.name=?";
		List list = this.createSQLQuery(sql, name).list(); 
		if(list.size()>0){
			value = String.valueOf(list.get(0));
		}
		return value;
	}
	
	/**
	 * 根据employee_id查询人员岗位信息
	 * @param employee_id -- 人员ID
	 * @author GaoYa
	 * @date 2016-09-19  下午14:57:00
	 */
	@SuppressWarnings("unchecked")
	public List<String> getPositionIDs(String employee_id){
		String sql = "select POSITION_ID from SYS_EMPLOYEE_POSITION where EMPLOYEE_ID=?";
		List<String> list = this.createSQLQuery(sql, employee_id).list(); 
		return list;
	}
	
	/**
	 * 根据角色姓名查询登录用户信息
	 * @param role_name -- 角色名称
	 * @param org_id -- 部门ID
	 * 
	 * @author GaoYa
	 * @date 2016-09-01 下午05:22:00
	 */
	@SuppressWarnings("unchecked")
	public String getEmpIDByRoleName(String role_name, String org_id){
		String value="";
		String sql = "select t3.employee_id from SYS_ROLE t,sys_user_role t2,sys_user t3 where t.id=t2.role_id and t2.user_id=t3.id and t.name=? and t3.org_id=?";
		List list = this.createSQLQuery(sql, role_name, org_id).list(); 
		if(list.size()>0){
			value = String.valueOf(list.get(0));
		}
		return value;
	}
	
	/**
	 * 根据ID获取人员财务独立密码
	 * @param id -- 人员ID
	 * @author GaoYa
	 * @date 2015-12-31 上午11:40:00
	 */
	@SuppressWarnings("unchecked")
	public String getSecondPwd(String id){
		String value="";
		String sql = "select u.SECOND_PWD from employee u where u.id=?";
		List list = this.createSQLQuery(sql, id).list(); 
		if(list.size()>0){
			value = String.valueOf(list.get(0));
		}
		return value;
	}
	
	/**
	 * 根据ID获取人员印章独立密码
	 * @param id -- 人员ID
	 * @author GaoYa
	 * @date 2015-12-31 上午11:40:00
	 */
	@SuppressWarnings("unchecked")
	public String getPrintPwd(String id){
		String value="";
		String sql = "select u.PRINT_PWD from employee u where u.id=?";
		List list = this.createSQLQuery(sql, id).list(); 
		if(list.size()>0){
			value = String.valueOf(list.get(0));
		}
		return value;
	}
	/**
	 * 根据userId查询登陆用户User
	 * @param userId
	 * @author GaoYa
	 * @date 2016-12-08 14:47:43
	 */
	@SuppressWarnings("unchecked")
	public User getUser(String userId){
		String hql = " from User where id='"+userId+"'";
		List<User> list = createQuery(hql).list();
		User user = new User();
		if(list.size()>0&&list!=null){
			user = list.get(0);
		}else{
			user = null;
		}
		return user;
	}
	/**根据员工ID查询员工手机号码
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Employee> getMobileTel(String emp_id) {
		String sql = "select MOBILE_TEL from EMPLOYEE where Id =?";
		List<Employee> list = createSQLQuery(sql,emp_id).list();
		return list;

	}
}
