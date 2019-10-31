package com.fwxm.scientific.dao;


import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository; 

import com.fwxm.scientific.bean.Tjy2ScientificCost;
import com.fwxm.scientific.bean.Tjy2ScientificPaper;
import com.khnt.core.crud.dao.impl.EntityDaoImpl;
import com.khnt.utils.StringUtil;
/**
 * <p>
 * 
 * </p>
 * 
 * @ClassName Tjy2ScientificPaperDao
 * @JDK 1.5
 * @author 
 * @date  
 */
@Repository("tjy2ScientificPaperDao")
public class Tjy2ScientificPaperDao extends EntityDaoImpl<Tjy2ScientificPaper> {

	
	/**
	 * 根据基本信息ID查询论文情况
	 * @param id
	 * @return 
	 * @author GaoYa
	 * @date 2014-03-03 下午05:00:00
	 */
	@SuppressWarnings("unchecked")
    public List<Tjy2ScientificPaper> queryTjy2ScientificPaperByBasicId(String id) {
    	List<Tjy2ScientificPaper> list = new ArrayList<Tjy2ScientificPaper>();
    	try {
    		if (StringUtil.isNotEmpty(id)) {
    			String hql = "from Tjy2ScientificPaper  where fkTjy2ScientificId=?";
    			list = this.createQuery(hql, id).list();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return list;
    }
}