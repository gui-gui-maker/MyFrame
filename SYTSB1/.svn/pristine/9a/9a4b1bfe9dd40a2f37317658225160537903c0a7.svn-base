package com.lsts.advicenote.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.lsts.advicenote.bean.AdviceNote;
import com.lsts.advicenote.dao.AdviceNoteDao;

/**
 * 通知书业务逻辑对象
 * @ClassName AdviceNoteService
 * @JDK 1.7
 * @author GaoYa
 * @date 2014-03-24 下午03:43:00
 */
@Service("adviceNoteService")
public class AdviceNoteService extends
		EntityManageImpl<AdviceNote, AdviceNoteDao> {
	@Autowired
	private AdviceNoteDao adviceNoteDao;
	
	// 根据通知书ID查询附件ID
	public String queryAttachmentId(String id) {
		return adviceNoteDao.queryAttachmentId(id);
	}

	// 删除通知书信息
	public void delAdviceNote(String ids) throws Exception {
		adviceNoteDao.delAdviceNote(ids);
	}
}
