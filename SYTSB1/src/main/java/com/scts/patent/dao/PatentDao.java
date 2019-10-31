package com.scts.patent.dao;

import org.springframework.stereotype.Repository;

import com.khnt.core.crud.dao.impl.EntityDaoImpl;
import com.scts.patent.bean.Patent;

/**
 * 论文管理数据处理对象
 * @ClassName PatentDao
 * @JDK 1.8
 * @author xcb
 * @date 2019-02-28 
 */
@Repository("patentDao")
public class PatentDao extends EntityDaoImpl<Patent> {


}
