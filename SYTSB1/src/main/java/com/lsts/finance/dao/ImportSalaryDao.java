package com.lsts.finance.dao;

import java.util.List;

import org.springframework.stereotype.Repository; 

import com.khnt.core.crud.dao.impl.EntityDaoImpl;
import com.lsts.finance.bean.ImportSalary;
import com.lsts.finance.bean.Salary;
/**
 * <p>
 * 
 * </p>
 * 
 * @ClassName Tjy2ImportDataRecordsDao
 * @JDK 1.5
 * @author 
 * @date  
 */
@Repository("importSalaryDao")
public class ImportSalaryDao extends EntityDaoImpl<ImportSalary> {
	
	@SuppressWarnings("unchecked")
	public List<ImportSalary> getim(String imid) {
		String hql = "from ImportSalary t where t.id=?";
		return createQuery(hql,imid).list();
		}

	@SuppressWarnings("unchecked")
	public List<ImportSalary> getimId(String ids) {
		String hql = "from ImportSalary t where t.id=?";
		return createQuery(hql,ids).list();
		}
	

}