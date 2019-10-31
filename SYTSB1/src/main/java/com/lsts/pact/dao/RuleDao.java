package com.lsts.pact.dao;

import org.springframework.stereotype.Repository;

import com.khnt.core.crud.dao.impl.EntityDaoImpl;
import com.lsts.pact.bean.RuleInfo;

@Repository("ruleDao")
public class RuleDao extends EntityDaoImpl<RuleInfo> {

}
