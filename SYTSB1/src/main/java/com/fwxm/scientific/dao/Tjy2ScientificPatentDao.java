package com.fwxm.scientific.dao;


import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository; 

import com.fwxm.scientific.bean.Tjy2ScientificPaper;
import com.fwxm.scientific.bean.Tjy2ScientificPatent;
import com.khnt.core.crud.dao.impl.EntityDaoImpl;
import com.khnt.utils.StringUtil;
/**
 * <p>
 * 
 * </p>
 * 
 * @ClassName Tjy2ScientificPatentDao
 * @JDK 1.5
 * @author 
 * @date  
 */
@Repository("tjy2ScientificPatentDao")
public class Tjy2ScientificPatentDao extends EntityDaoImpl<Tjy2ScientificPatent> {

	
	/**
	 * 根据基本信息ID查询专利情况
	 * @param id
	 * @return 
	 * @author GaoYa
	 * @date 2014-03-03 下午05:00:00
	 */
	@SuppressWarnings("unchecked")
    public List<Tjy2ScientificPatent> queryTjy2ScientificPatentByBasicId(String id) {
    	List<Tjy2ScientificPatent> list = new ArrayList<Tjy2ScientificPatent>();
    	try {
    		if (StringUtil.isNotEmpty(id)) {
    			String hql = "from Tjy2ScientificPatent  where fkTjy2ScientificId=?";
    			list = this.createQuery(hql, id).list();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return list;
    }
}