package com.lsts.pact.dao;

import org.springframework.stereotype.Repository;

import com.khnt.core.crud.dao.impl.EntityDaoImpl;
import com.lsts.pact.bean.PactInfo;

@Repository("pactDao")
public class PactDao extends EntityDaoImpl<PactInfo> {

}
