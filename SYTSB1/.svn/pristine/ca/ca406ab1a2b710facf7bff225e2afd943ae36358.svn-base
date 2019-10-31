package com.khnt.rtbox.config.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.khnt.rtbox.config.bean.RtPersonDir;
import com.khnt.rtbox.config.dao.RtPersonDirDao;

/*******************************************************************************
 * <p>
 * 
 * </p>
 * 
 * @ClassName RtPersonDirManager
 * @JDK 1.5
 * @author CODER_V3.0
 * @date 2016-03-22 09:51:04
 */
@Service("rtPersonDirManager")
public class RtPersonDirManager extends EntityManageImpl<RtPersonDir, RtPersonDirDao> {
	@Autowired
	RtPersonDirDao rtPersonDirDao;

	public RtPersonDir getByBusinessId(String fkBusinessId, String code) {
		@SuppressWarnings("unchecked")
		List<RtPersonDir> list = this.rtPersonDirDao.createQuery("from RtPersonDir where fkBusinessId=? and rtCode=?", fkBusinessId, code).list();
		if (list != null && !list.isEmpty()) {
			return list.get(0);
		}
		return null;
	}
}
