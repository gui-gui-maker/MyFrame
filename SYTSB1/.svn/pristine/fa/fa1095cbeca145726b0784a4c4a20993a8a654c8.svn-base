package com.scts.car.dao;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Repository;
import com.khnt.core.crud.dao.impl.EntityDaoImpl;
import com.khnt.utils.StringUtil;
import com.scts.car.bean.CarWb;

/**
 * <p>
 * 
 * </p>
 * 
 * @ClassName CarWbDao
 * @JDK 1.5
 * @author
 * @date
 */
@Repository("carWbDao")
public class CarWbDao extends EntityDaoImpl<CarWb> {
	/**
	 * 获取维保申请编号后三位
	 * 
	 * @param sn_pre
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public String queryNextSn(String sn_pre) {
		String nextNum = "";
		String sql = "select nvl(max(Substr(t.sn, length('" + sn_pre + "')+1)), '000') count_num"
				+ " from TJY2_CAR_WB t" + " where t.sn like '" + sn_pre + "%'";
		List list = this.createSQLQuery(sql).list();
		if (!list.isEmpty()) {
			String curNum = list.get(0) + "";
			if (StringUtil.isNotEmpty(curNum)) {
				nextNum = getCountNum(Integer.parseInt(curNum) + 1);
			}
		}
		return nextNum;
	}

	/**
	 * 生成编号后三位
	 * 
	 * @param count_num
	 * @return
	 */
	private String getCountNum(int count_num) {
		String nextNum = "";
		if ((0 < count_num) && (count_num < 10))
			nextNum = "00" + count_num;
		if ((9 < count_num) && (count_num < 100))
			nextNum = "0" + count_num;
		else if (count_num > 99)
			nextNum = String.valueOf(count_num);
		return nextNum;
	}

	/**
	 * 根据业务id获取任务参数信息
	 * 
	 * @param request
	 * @param id
	 * @return
	 */
	public List<?> getWorktaskParam(HttpServletRequest request, String id) {
		String sql = "select t.ID,t.TITLE from v_pub_worktask t where t.SERVICE_ID = '" + id + "' and t.STATUS='0'";
		List<?> list = this.createSQLQuery(sql).list();
		return list;
	}

	/**
	 * 获取下一步处理人信息
	 * 
	 * @param request
	 * @param carWb
	 * @return
	 */
	public List<?> getNextCheckerInfo(HttpServletRequest request, CarWb carWb) {
		String sql = "select e.name, e.mobile_tel from SYS_USER u, EMPLOYEE e where u.employee_id = e.id "
				+ " and u.status = '1' and u.id in (select v.HANDLER_ID from TJY2_CAR_WB t, v_pub_worktask v "
				+ " where t.id = v.service_id and v.status = '0' and t.id = '"+carWb.getId()+"') and e.org_id='100040'";
		List<?> list = this.createSQLQuery(sql).list();
		return list;
	}

	/**
	 * 根据业务id获取申请中的车辆信息
	 * 
	 * @param request
	 * @param id
	 * @return
	 */
	public List<?> getFkCarIdByIdSql(HttpServletRequest request, String id) {
		String sql = "select t.fk_car_id,t.car_unit,t.car_num,t.car_brand,t.engine_no,t.frame_no,t.car_mileage from TJY2_CAR_WB t where t.ID = '"
				+ id + "' and t.DATA_STATUS <> '99'";
		List<?> list = this.createSQLQuery(sql).list();
		return list;
	}

	/**
	 * 微信端获取我的申请的事项信息
	 * 
	 * @param userId
	 * @param dataStatus
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<CarWb> queryMyApplyList(String userId, String dataStatus) {
		List<CarWb> list = new ArrayList<CarWb>();
		String hql = "from CarWb t where t.createUserId = '" + userId + "' order by t.createDate desc";
		list = this.createQuery(hql).list();
		return list;
	}

	/**
	 * 微信端获取待办事项信息
	 * 
	 * @param userId
	 * @param dataStatus
	 * @return
	 */
	public List<?> queryPendingList(String userId, String dataStatus) {
		String sql = "select t.id from TJY2_CAR_WB t, v_pub_worktask v where t.id = v.service_id and v.status = '0' and v.handler_id = '"
				+ userId + "' order by t.createDate desc";
		List<?> list = this.createSQLQuery(sql).list();
		return list;
	}

	/**
	 * 微信端获取已办理的事项信息
	 * 
	 * @param userId
	 * @param dataStatus
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<CarWb> queryDealedList(String userId, String dataStatus) {
		List<CarWb> list = new ArrayList<CarWb>();
		String hql = "from CarWb t where t.fleetDealId = '" + userId + "' or t.officeDealId = '" + userId
				+ "' or t.sealUserId = '" + userId + "' order by order by t.createDate desc";
		list = this.createQuery(hql).list();
		return list;
	}
}