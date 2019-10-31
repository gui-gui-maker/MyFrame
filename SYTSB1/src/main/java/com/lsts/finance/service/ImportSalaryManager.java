package com.lsts.finance.service;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.lsts.finance.bean.ImportSalary;
import com.lsts.finance.dao.ImportSalaryDao;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service("importSalaryManager")
public class ImportSalaryManager extends EntityManageImpl<ImportSalary,ImportSalaryDao>{
    @Autowired
    ImportSalaryDao importSalaryDao;
    @Autowired
    SalaryManager    salaryManager;
    
    
    

	public void delete(String ids) throws ParseException {
		
		
		
	
			Session session = importSalaryDao.getHibernateTemplate().getSessionFactory()
					.getCurrentSession();
			for(String id:ids.split(",")){
				ImportSalary ywhbsgz = importSalaryDao.get(id);
				session.delete(ywhbsgz);
			
			}
			HashMap<String, Object> imid = salaryManager.deleteimid(ids);
		
	}




	public  String saveim(ImportSalary importSalary ,String month, String etime) {
		// TODO Auto-generated method stub
		importSalary.setImportTime(new Date());
		importSalary.setSalaryYear(etime);
		importSalary.setSalaryTmonth(month);
		importSalaryDao.save(importSalary);
		String imid = importSalary.getId();
		return imid;
		
	}




	public List<ImportSalary> getImsal(String id) {
		List<ImportSalary> message =	importSalaryDao.getimId(id);
		return message;
	}
}
