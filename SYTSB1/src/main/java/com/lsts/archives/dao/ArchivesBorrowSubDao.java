package com.lsts.archives.dao;

import com.lsts.archives.bean.ArchivesBorrowSub;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository; 
import com.khnt.core.crud.dao.impl.EntityDaoImpl;
/**
 * <p>
 * 
 * </p>
 * 
 * @ClassName ArchivesBorrowSubDao
 * @JDK 1.5
 * @author 
 * @date  
 */
@Repository("archivesBorrowSubDao")
public class ArchivesBorrowSubDao extends EntityDaoImpl<ArchivesBorrowSub> {

	/**
	 * 根据主表外键ID删除子表数据
	 * @param qualityZssFk
	 * @throws Exception
	 */
	public void deleteSub(String fk_archives_borrow_id) throws Exception {
   		String sql="delete from TJY2_ARCHIVES_BORROW_SUB t where t.fk_archives_borrow_id=?";
   		this.createSQLQuery(sql, fk_archives_borrow_id).executeUpdate();
   	}
	/**
	 * 根据主表外键ID获取子表数据
	 * @param fkArchivesBorrowId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<ArchivesBorrowSub> getArchivesBorrowSubs(String fk_archives_borrow_id) {
		List<ArchivesBorrowSub> list = new ArrayList<ArchivesBorrowSub>();
		String hql = "from ArchivesBorrowSub t where t.fkArchivesBorrowId=?";
		list = this.createQuery(hql, fk_archives_borrow_id).list();
		return list;
	}
	/**
	 * 根据主表外键ID和归还状态获取子表数据
	 * @param fk_archives_borrow_id
	 * @param isBack
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<ArchivesBorrowSub> getArchivesBorrowSubs(String fk_archives_borrow_id,String isBack) {
		List<ArchivesBorrowSub> list = new ArrayList<ArchivesBorrowSub>();
		String hql = "from ArchivesBorrowSub t where t.fkArchivesBorrowId=? and t.isBack=?";
		list = this.createQuery(hql, fk_archives_borrow_id,isBack).list();
		return list;
	}
}