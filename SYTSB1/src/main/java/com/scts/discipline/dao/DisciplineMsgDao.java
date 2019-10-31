package com.scts.discipline.dao;

import org.springframework.stereotype.Repository;

import com.khnt.core.crud.dao.impl.EntityDaoImpl;
import com.scts.discipline.bean.DisciplineMsg;

@Repository("disciplineMsgDao")
public class DisciplineMsgDao extends EntityDaoImpl<DisciplineMsg> {

}
