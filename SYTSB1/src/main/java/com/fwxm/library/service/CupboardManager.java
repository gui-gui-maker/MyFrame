package com.fwxm.library.service;


import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.khnt.rbac.bean.User;
import com.khnt.security.CurrentSessionUser;
import com.khnt.security.util.SecurityUtil;
import com.khnt.utils.StringUtil;
import com.fwxm.library.bean.Cupboard;
import com.fwxm.library.dao.BookDao;
import com.fwxm.library.dao.CupboardDao;

/*******************************************************************************
 * <p>
 * 
 * </p>
 * 
 * @ClassName CupboardManager
 * @JDK 1.5
 * @author CODER_V3.0
 * @date 2018-10-23 10:57:26
 */
@Service("cupboardManager")
public class CupboardManager extends EntityManageImpl<Cupboard, CupboardDao> {
	@Autowired
	CupboardDao cupboardDao;
	
	@Autowired
	BookDao bookDao;
	
	@Override
	public void save(Cupboard cupboard) throws Exception{
		
		if(StringUtil.isEmpty(cupboard.getId())){
			//新增
			CurrentSessionUser su = SecurityUtil.getSecurityUser();
			User user = su.getSysUser();
			//记录录入人员
			cupboard.setCreateBy(user.getId());
			cupboard.setCreateTime(new Date());
			cupboardDao.save(cupboard);
		}else{
			super.save(cupboard);
		}
	}

	public void delete(String ids) throws Exception{
		User user = SecurityUtil.getSecurityUser().getSysUser();
		Date dateTime = new Date();
		@SuppressWarnings("unchecked")
		List<Cupboard> cupboards = bookDao.createQuery("from Cupboard where id in ('"+ids.replaceAll(",", "','")+"')").list();
		for (Cupboard cupboard : cupboards) {
			bookDao.createQuery("update Book set currentPosition = '',status='2',"
					+ "lastOpBy=?,lastOpTime=?,remark='删除书架号更新' where currentPosition=?",
					user.getId(),dateTime, cupboard.getQrcode()).executeUpdate();
			//删除书架
			cupboardDao.remove(cupboard);
		}
	}
	
}
