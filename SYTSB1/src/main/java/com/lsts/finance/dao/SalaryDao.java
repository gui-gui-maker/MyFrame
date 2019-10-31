package com.lsts.finance.dao;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.khnt.core.crud.dao.impl.EntityDaoImpl;
import com.lsts.finance.bean.Salary;




/**
 * 实体DAO，继承自泛型类EntityDaoImpl，同时声明泛型的运行时类为Demo。<br/>
 * 通过这样的继承方式，自动获得了对Demo实体的crud操作
 * 
 * 注解@Repository标识该类为持久化dao组件。
 */
@Repository("salaryDao")
public class SalaryDao extends EntityDaoImpl<Salary> {
	
	@SuppressWarnings("unchecked")
	public List<Salary> getCwBankDetailM() {
		String hql = "from CwBankDetail t where t.jyTime>add_months(sysdate,-1)";
		return createQuery(hql).list();
		}
	@SuppressWarnings("unchecked")
	public List<Salary> getimId(String ids) {
		String hql = "from Salary t where t.importId=?";
		return createQuery(hql,ids).list();
		}
	
	
//	public void saveBatch(List<Salary> datas) {
//		Session session = this.getSession();
//
//		for (Salary b : datas) {
//			session.save(b);
//		}
//
//		// 关键之处，每处理完一批，刷出session中的数据
//		session.flush();
//		session.clear();
//	}
//
//	@SuppressWarnings("unchecked")
//	public List<Object> getData(String str, int total) {
//		return createCriteria(Restrictions.like("str", str)).setMaxResults(total).list();
//	}
	/**
	 * 根据id获取工资信息
	 * @param id
	 * @return
	 */
	public Salary getSalary(String id) {
		Salary salary = new Salary();
		String sql = "select t.id," + 
				"       t.name," + 
				"       t.jbgz_gwgz," + 
				"       t.jbgz_xjgz," + 
				"       t.jbgz_blbt," + 
				"       t.jbgz_lt," + 
				"       t.jbgz_bfx," + 
				"       t.jbgz_xj," + 
				"       t.jxgz_jcjx_my," + 
				"       t.jxgz_jcjx_bf," + 
				"       t.jxgz_jdjx," + 
				"       t.jxgz_bt_zcbt," + 
				"       t.jxgz_bt_qt," + 
				"       t.jxgz_by," + 
				"       t.kkx_zynj_my," + 
				"       t.kkx_zynj_bk," + 
				"       t.kkx_ylbx_my," + 
				"       t.kkx_ylbx_bf," + 
				"       t.kkx_sy_my," + 
				"       t.kkx_sy_bf," + 
				"       t.kkx_gjj_my," + 
				"       t.kkx_gjj_bf," + 
				"       t.kkx_ghjf," + 
				"       t.kkx_sds," + 
				"       t.jxgz_xj," + 
				"       t.jbgz_dz," + 
				"       t.kkx_by," + 
				"       t.kkx_xj," + 
				"       t.fsalary," + 
				"       t.import_id," + 
				"       t.creater_id," + 
				"       t.creater_name," + 
				"       t.kkx_oldbx_my," + 
				"       t.kkx_oldbx_bf," + 
				"       t.fk_employeebase_id," + 
				"       t.telephone" + 
				"  from TJY2_CW_SALARY_NEW t" + 
				" where t.id = '"+id+"'";
		List list = this.createSQLQuery(sql).list();
		if (!list.isEmpty()) {
			for (int i = 0; i < list.size(); i++) {
				Object[] obj = (Object[]) list.get(0);
				salary.setId(obj[0]!=null?obj[0].toString():null);
				salary.setName(obj[1]!=null?obj[1].toString():null);
				salary.setJbgzGwgz(obj[2]!=null?new BigDecimal(obj[2].toString()).setScale(3,BigDecimal.ROUND_HALF_UP):null);
				salary.setJbgzXjgz(obj[3]!=null?new BigDecimal(obj[3].toString()).setScale(3,BigDecimal.ROUND_HALF_UP):null);
				salary.setJbgzBlbt(obj[4]!=null?new BigDecimal(obj[4].toString()).setScale(3,BigDecimal.ROUND_HALF_UP):null);
				salary.setJbgzLt(obj[5]!=null?new BigDecimal(obj[5].toString()).setScale(3,BigDecimal.ROUND_HALF_UP):null);
				salary.setJbgzBfx(obj[6]!=null?new BigDecimal(obj[6].toString()).setScale(3,BigDecimal.ROUND_HALF_UP):null);
				salary.setJbgzXj(obj[7]!=null?new BigDecimal(obj[7].toString()).setScale(3,BigDecimal.ROUND_HALF_UP):null);
				salary.setJxgzJcjxMy(obj[8]!=null?new BigDecimal(obj[8].toString()).setScale(3,BigDecimal.ROUND_HALF_UP):null);
				salary.setJxgzJcjxBf(obj[9]!=null?new BigDecimal(obj[9].toString()).setScale(3,BigDecimal.ROUND_HALF_UP):null);
				salary.setJxgzJdjx(obj[10]!=null?new BigDecimal(obj[10].toString()).setScale(3,BigDecimal.ROUND_HALF_UP):null);
				salary.setJxgzBtZcbt(obj[11]!=null?new BigDecimal(obj[11].toString()).setScale(3,BigDecimal.ROUND_HALF_UP):null);
				salary.setJxgzBtQt(obj[12]!=null?new BigDecimal(obj[12].toString()).setScale(3,BigDecimal.ROUND_HALF_UP):null);
				salary.setJxgzBy(obj[13]!=null?new BigDecimal(obj[13].toString()).setScale(2,BigDecimal.ROUND_HALF_UP):null);
				salary.setKkxZynjMy(obj[14]!=null?new BigDecimal(obj[14].toString()).setScale(3,BigDecimal.ROUND_HALF_UP):null);
				salary.setKkxZynjBk(obj[15]!=null?new BigDecimal(obj[15].toString()).setScale(3,BigDecimal.ROUND_HALF_UP):null);
				salary.setKkxYlbxMy(obj[16]!=null?new BigDecimal(obj[16].toString()).setScale(3,BigDecimal.ROUND_HALF_UP):null);
				salary.setKkxYlbxBf(obj[17]!=null?new BigDecimal(obj[17].toString()).setScale(3,BigDecimal.ROUND_HALF_UP):null);
				salary.setKkxSyMy(obj[18]!=null?new BigDecimal(obj[18].toString()).setScale(3,BigDecimal.ROUND_HALF_UP):null);
				salary.setKkxSyBf(obj[19]!=null?new BigDecimal(obj[19].toString()).setScale(3,BigDecimal.ROUND_HALF_UP):null);
				salary.setKkxGjjMy(obj[20]!=null?new BigDecimal(obj[20].toString()).setScale(3,BigDecimal.ROUND_HALF_UP):null);
				salary.setKkxGjjBf(obj[21]!=null?new BigDecimal(obj[21].toString()).setScale(3,BigDecimal.ROUND_HALF_UP):null);
				salary.setKkxGhjf(obj[22]!=null?new BigDecimal(obj[22].toString()).setScale(3,BigDecimal.ROUND_HALF_UP):null);
				salary.setKkxSds(obj[23]!=null?new BigDecimal(obj[23].toString()).setScale(3,BigDecimal.ROUND_HALF_UP):null);
				salary.setJxgzXj(obj[24]!=null?new BigDecimal(obj[24].toString()).setScale(3,BigDecimal.ROUND_HALF_UP):null);
				salary.setJbgzDz(obj[25]!=null?new BigDecimal(obj[25].toString()).setScale(3,BigDecimal.ROUND_HALF_UP):null);
				salary.setKkxBy(obj[26]!=null?new BigDecimal(obj[26].toString()).setScale(3,BigDecimal.ROUND_HALF_UP):null);
				salary.setKkxXj(obj[27]!=null?new BigDecimal(obj[27].toString()).setScale(3,BigDecimal.ROUND_HALF_UP):null);
				salary.setFsalary(obj[28]!=null?new BigDecimal(obj[28].toString()).setScale(3,BigDecimal.ROUND_HALF_UP):null);
				salary.setImportId(obj[29]!=null?obj[29].toString():null);
				salary.setCreaterId(obj[30]!=null?obj[30].toString():null);
				salary.setCreaterName(obj[31]!=null?obj[31].toString():null);
				salary.setKkxOldbxMy(obj[32]!=null?new BigDecimal(obj[32].toString()).setScale(3,BigDecimal.ROUND_HALF_UP):null);
				salary.setKkxOldbxBf(obj[33]!=null?new BigDecimal(obj[33].toString()).setScale(3,BigDecimal.ROUND_HALF_UP):null);
				salary.setFkEmployeeBaseId(obj[34]!=null?obj[34].toString():null);
				salary.setTelePhone(obj[35]!=null?obj[35].toString():null);
			}
		}
		return salary;
	}
}
