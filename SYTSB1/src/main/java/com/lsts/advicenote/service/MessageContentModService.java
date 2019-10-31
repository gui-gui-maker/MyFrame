package com.lsts.advicenote.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.khnt.core.exception.KhntException;
import com.lsts.advicenote.bean.MessageContentMod;
import com.lsts.advicenote.dao.MessageContentModDao;

@Service("messageContentModService")
public class MessageContentModService extends EntityManageImpl<MessageContentMod,MessageContentModDao> {

	@Autowired
	private MessageContentModDao contentModDao;

	@Override
	public void save(MessageContentMod entity) throws Exception {

			String moduleCode = entity.getModule_code();
			Boolean flag = contentModDao.checkCode(moduleCode,entity.getId());
			if(flag) {
				throw new KhntException("编号重复，请修改！");
			}else {
				super.save(entity);
			}
			
	}

	/**
	 * 删除配置信息
	 * author pingZhou
	 * @param ids
	 */
	public void del(String ids) {
		contentModDao.del(ids);
	}
	
	
	
}
