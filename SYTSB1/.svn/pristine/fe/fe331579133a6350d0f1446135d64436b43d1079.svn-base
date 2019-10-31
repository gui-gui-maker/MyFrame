package com.khnt.signseal.dao;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.khnt.core.crud.dao.impl.EntityDaoImpl;
import com.khnt.signseal.bean.SignSeal;

@Repository("signSealDao")
public class SignSealDao extends EntityDaoImpl<SignSeal> {

	public void updateBusinessId(String[] signId, String busId) {
		Query query = super
				.createQuery("update SignSeal set businessId=:busId where id in (:id)");
		query.setString("busId", busId);
		query.setParameterList("id", signId);
		query.executeUpdate();

	}
}
