package com.lsts.archives.service;

import com.khnt.core.crud.manager.impl.EntityManageImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lsts.archives.bean.ArchivesBorrowSub;
import com.lsts.archives.dao.ArchivesBorrowSubDao;


@Service("archivesBorrowSubManager")
public class ArchivesBorrowSubManager extends EntityManageImpl<ArchivesBorrowSub,ArchivesBorrowSubDao>{
    @Autowired
    ArchivesBorrowSubDao archivesBorrowSubDao;
    /**
     * 获取列表
     * @param id
     * @return
     */
    public List<ArchivesBorrowSub> getArchivesBorrowSubs(String id) {
    	List<ArchivesBorrowSub> archivesBorrowSubs = archivesBorrowSubDao.getArchivesBorrowSubs(id);
		return archivesBorrowSubs;
	}
    public boolean checkAllBack(String fk_archives_borrow_id){
    	boolean isAllBAck=true;
    	List<ArchivesBorrowSub> archivesBorrowSubs = archivesBorrowSubDao.getArchivesBorrowSubs(fk_archives_borrow_id);
    	try {
			for(ArchivesBorrowSub archivesBorrowSub:archivesBorrowSubs){
				String isBack=archivesBorrowSub.getIsBack();
				if(isBack == null||isBack.equals("0")){
					isAllBAck=false;
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return isAllBAck;
    }
    /**
     * 根据主表外键ID和归还状态获取子表数据
     * @param fk_archives_borrow_id
     * @param isBack
     * @return
     */
    public List<ArchivesBorrowSub> getArchivesBorrowSubs(String fk_archives_borrow_id,String isBack) {
    	List<ArchivesBorrowSub> archivesBorrowSubs = archivesBorrowSubDao.getArchivesBorrowSubs(fk_archives_borrow_id,isBack);
		return archivesBorrowSubs;
	}
}
