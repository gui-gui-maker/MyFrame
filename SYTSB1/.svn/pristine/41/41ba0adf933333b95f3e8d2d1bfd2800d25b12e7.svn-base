package com.scts.task.dao;


import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.khnt.core.crud.dao.impl.EntityDaoImpl;
import com.khnt.utils.StringUtil;
import com.scts.task.bean.FunctionTaskInfo;

/**
 * 检验和质量管理软件功能开发（修改）任务书数据处理对象
 * @ClassName FunctionTaskInfoDao
 * @JDK 1.7
 * @author GaoYa
 * @date 2016-11-22 15:53:00
 */
@Repository("functionTaskInfoDao")
public class FunctionTaskInfoDao extends EntityDaoImpl<FunctionTaskInfo> {
	
    

	@SuppressWarnings("unchecked")
	public List<FunctionTaskInfo> getInfos() {
		List<FunctionTaskInfo> list = new ArrayList<FunctionTaskInfo>();
		String hql = "from FunctionTaskInfo i where i.data_status != '99'";
		list = this.createQuery(hql).list();
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public List<FunctionTaskInfo> queryInfos(
			String info_id) {
		List<FunctionTaskInfo> list = new ArrayList<FunctionTaskInfo>();
		String hql = "from FunctionTaskInfo i where i.id=? and i.data_status != '99'";
		list = this.createQuery(hql, info_id).list();
		return list;
	}
	
	/**
	 * 获取任务书编号后三位序号
	 * @param sn_pre -- 任务书编号前缀
	 * @return 
	 * @author GaoYa
	 * @date 2016-11-24 10:53:00
	 */
	@SuppressWarnings("unchecked")
	public String queryNextSn(String sn_pre){
		String nextNum = "";
		String sql = "select nvl(max(Substr(t.sn, length('" + sn_pre + "')+1)), '000') count_num" +
        " from FUNCTION_TASK_INFO t" +
        " where t.sn like '" + sn_pre + "%'";
		List list = this.createSQLQuery(sql).list();
		if (!list.isEmpty()) {
			String curNum = list.get(0)+"";
			if (StringUtil.isNotEmpty(curNum)) {
				nextNum = getCountNum(Integer.parseInt(curNum)+1);
			}
		}
		return nextNum;
	}

	// 生成3位序号
	private String getCountNum(int count_num){
        String nextNum = "";
       if((0 < count_num) && (count_num < 10))
    	   nextNum = "00" + count_num;
       if((9 < count_num) && (count_num < 100))
    	   nextNum = "0" + count_num;
       else if(count_num > 99)
    	   nextNum = String.valueOf(count_num);
       return  nextNum;
   }
	
}
