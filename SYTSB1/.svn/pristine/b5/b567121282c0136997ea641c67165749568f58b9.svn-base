package com.khnt.signseal.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.khnt.signseal.bean.SignSeal;
import com.khnt.signseal.dao.SignSealDao;
import com.khnt.utils.StringUtil;

@Service("signSealManager")
public class SignSealManager extends EntityManageImpl<SignSeal, SignSealDao> {
	@Autowired
	SignSealDao signSealDao;

	/**
	 * 获取签名列表
	 * 
	 * @Title: getListByIds
	 * @author bait
	 * @date 2013-3-25 下午5:13:52
	 * @param Ids
	 * @return List<SignSeal>
	 * @throws
	 */
	public List<SignSeal> getListByIds(String Ids) {
		List<SignSeal> list = new ArrayList<SignSeal>();
		// list=signSealDao.createQuery("from SignSeal a where a.id in ("+Ids+")").list();
		if (StringUtil.isNotEmpty(Ids)) {
			for (String id : Ids.split(",")) {
				if (signSealDao.get(id) != null)
					list.add(signSealDao.get(id));
			}

		}
		return list;
	}

	/**
	 * 更新业务ID到印章记录中
	 * 
	 * @Title: updateSignSeal
	 * @author bait
	 * @date 2013-3-26 下午7:33:46
	 * @param Ids
	 * @param businessId
	 *            void
	 * @throws
	 */
	public void updateSignSeal(String Ids, String businessId) {
		this.signSealDao.updateBusinessId(Ids.split(","), businessId);
	}

}
