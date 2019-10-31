package com.lsts.report.dao;


import java.util.List;

import org.springframework.stereotype.Repository;

import com.khnt.core.crud.dao.impl.EntityDaoImpl;
import com.khnt.utils.StringUtil;
import com.lsts.report.bean.ReportCancel;

/**
 * 报告作废记录数据处理对象
 * @ClassName ReportCancelDao
 * @JDK 1.7
 * @author GaoYa
 * @date 2014-07-10 12:23:00
 */
@Repository("reportCancelDao")
public class ReportCancelDao extends EntityDaoImpl<ReportCancel> {

	/**
	 * 获取作废编号后三位序号
	 * @param sn_pre -- 作废编号前缀
	 * @return 
	 * @author GaoYa
	 * @date 2014-07-10 下午04:00:00
	 */
	@SuppressWarnings("unchecked")
	public String queryNextSn(String sn_pre){
		String nextNum = "";
		String sql = "select nvl(max(Substr(t.sn, length('" + sn_pre + "')+1)), '000') count_num" +
        " from TZSB_REPORT_CANCEL t" +
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
