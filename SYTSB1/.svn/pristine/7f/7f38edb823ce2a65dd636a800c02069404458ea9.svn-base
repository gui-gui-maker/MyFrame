package com.scts.car.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.khnt.core.crud.dao.impl.EntityDaoImpl;
import com.khnt.rbac.impl.bean.User;
import com.khnt.security.CurrentSessionUser;
import com.khnt.security.util.SecurityUtil;
import com.khnt.utils.StringUtil;
import com.scts.car.bean.CarApply;


/**
 * 公务用车申请数据处理对象
 * @ClassName CarApplyDao
 * @JDK 1.7
 * @author GaoYa
 * @date 2018-06-27 下午02:12:00
 */
@Repository("carApplyDao")
public class CarApplyDao extends EntityDaoImpl<CarApply> {

	/**
	 * 根据用车部门ID获取该部门当前派用车辆
	 * 
	 * @param use_dep_id
	 *            -- 用车部门ID
	 * @return
	 * @author GaoYa
	 * @date 2018-07-16 下午18:32:00
	 */
	@SuppressWarnings("unchecked")
	public List<CarApply> getCarApplyByUseDepId(String use_dep_id, String apply_id) {
		List<CarApply> list = new ArrayList<CarApply>();
		String hql = "from CarApply t where t.use_dep_id = ? and t.data_status !='6' and t.data_status !='99' and  t.data_status !='98'";
		if(StringUtil.isNotEmpty(apply_id)) {
			hql += "and t.id != '"+apply_id+"'";
		}
		list = this.createQuery(hql, use_dep_id).list();
		return list;
	}
	
	/**
	 * 根据申请部门ID获取该部门当前派用车辆
	 * 
	 * @param apply_dep_id
	 *            -- 申请部门ID
	 * @return
	 * @author GaoYa
	 * @date 2018-07-16 下午18:32:00
	 */
	@SuppressWarnings("unchecked")
	public List<CarApply> getCarApplyByApplyDepId(String apply_dep_id, String apply_id) {
		List<CarApply> list = new ArrayList<CarApply>();
		String hql = "from CarApply t where t.apply_dep_id = ? and t.data_status !='6' and t.data_status !='99' and t.data_status !='98'";
		if(StringUtil.isNotEmpty(apply_id)) {
			hql += "and t.id != '"+apply_id+"'";
		}
		list = this.createQuery(hql, apply_dep_id).list();
		return list;
	}
	
	/**
	 * 获取未收车的用车申请业务
	 * 
	 * @return
	 * @author GaoYa
	 * @date 2018-08-16 上午09:28:00
	 */
	@SuppressWarnings("unchecked")
	public List<CarApply> getNotReturnedOfCarApply() {
		List<CarApply> list = new ArrayList<CarApply>();
		String hql = "from CarApply t where t.data_status = '5'";
		list = this.createQuery(hql).list();
		return list;
	}
		
	/**
	 * 获取上一次该车收车时的公里止数
	 * 
	 * @param fk_car_id
	 *            -- 车辆ID
	 * @param apply_id
	 *            -- 申请单ID
	 * @return
	 * @author GaoYa
	 * @date 2018-06-29 上午11:25:00
	 */
	public String getEndKmByCarId(String fk_car_id, String apply_id) {
		String end_km = "";
		String sql = "select i.end_km from TZSB_CAR_APPLY i where i.fk_car_id = ? and i.id != ? and i.data_status != '99'  and i.end_km is not null order by to_number(i.end_km) desc";
		List list = this.createSQLQuery(sql, fk_car_id, apply_id).list();
		if (!list.isEmpty()) {
			if(list.get(0)!=null) {
				end_km = String.valueOf(list.get(0));
			}
		}
		return end_km;
	}
	
	/**
	 * 获取申请单编号后三位序号
	 * 
	 * @param sn_pre
	 *            -- 业务流水号前缀
	 * @return
	 * @author GaoYa
	 * @date 2018-06-28 上午10:58:00
	 */
	public String queryNextSn(String sn_pre) {
		String nextNum = "";
		String sql = "select nvl(max(Substr(t.apply_sn, length('" + sn_pre + "')+1)), '000') count_num"
				+ " from TZSB_CAR_APPLY t" + " where t.apply_sn like '" + sn_pre + "%'";
		List list = this.createSQLQuery(sql).list();
		if (!list.isEmpty()) {
			String curNum = list.get(0) + "";
			if (StringUtil.isNotEmpty(curNum)) {
				nextNum = getCountNum(Integer.parseInt(curNum) + 1);
			}
		}
		return nextNum;
	}

	// 生成3位序号
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
	 * 获取部门负责人信息（含手机号码）
	 * 
	 * @return
	 * @author GaoYa
	 * @date 2018-07-16 下午20:02:00
	 */
	public List<Map<String, Object>> queryDepLeaders() {
		List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
		String sql = "select u.org_id, e.mobile_tel from SYS_USER_ROLE t,sys_user u,employee e "
				+ " where t.role_id='402883a0533ff7790153406d4e1c2eaf' "
				+ " and t.user_id=u.id and u.employee_id=e.id ";
		List list = this.createSQLQuery(sql).list();
		if (!list.isEmpty()) {
			for (int i = 0; i < list.size(); i++) {
				Object[] objArr = list.toArray();
				Object[] obj = (Object[]) objArr[i];
				HashMap<String, Object> map = new HashMap<String, Object>();
				map.put("org_id", obj[0]);
				map.put("mobile_tel", obj[1]);
				mapList.add(map);
			}
		}
		return mapList;
	}
	
	/**
	 * 微信端获取代办事项信息
	 * @param car_apply_check_status
	 * @param userId
	 * @param orgId
	 * @return
	 */
	public List<CarApply> queryCheckList(String car_apply_check_status,String userId,String orgId) {
		CurrentSessionUser cur_user = SecurityUtil.getSecurityUser();
		User user = (User) cur_user.getSysUser();
		List<CarApply> list = new ArrayList<CarApply>();
		String hql = "from CarApply t where t.id is null order by t.apply_sn desc";
		if("0".equals(car_apply_check_status)) {
			//申请部门负责人审核
			hql = "from CarApply t where t.data_status = '"+car_apply_check_status+"' and t.apply_dep_id = '"+orgId+"' order by t.apply_sn desc";
			if(user.getName().equals("韩绍义")) {
				hql = "from CarApply t where t.data_status = '"+car_apply_check_status+"' and t.apply_dep_id = '100030' order by t.apply_sn desc";
			}else if(user.getName().equals("孙宇艺")) {
				hql = "from CarApply t where t.data_status = '"+car_apply_check_status+"' and t.apply_dep_id in ('100025','100059') order by t.apply_sn desc";
			}else if(user.getName().equals("李山桥")) {
				hql = "from CarApply t where t.data_status = '"+car_apply_check_status+"' and t.apply_dep_id = '100044' order by t.apply_sn desc";
			}
		}else if("1".equals(car_apply_check_status)) {
			//办公室负责人审核
			hql = "from CarApply t where t.data_status = '"+car_apply_check_status+"' or (t.data_status = '0' and t.apply_dep_id ='"+orgId+"') order by t.apply_sn desc";
		}else if("2".equals(car_apply_check_status)) {
			//分管领导审核
			hql = "from CarApply t where t.data_status = '"+car_apply_check_status+"' order by t.apply_sn desc";
		}else if("3".equals(car_apply_check_status)) {
			//车队负责人审核
			hql = "from CarApply t where t.data_status in ('3','4','5') order by t.apply_sn desc";
		}else if("4".equals(car_apply_check_status)) {
			//车队派车/收车
			hql = "from CarApply t where t.data_status in ('4','5') order by t.apply_sn desc";
		}
		list = this.createQuery(hql).list();
		return list;
	}
	/**
	 * 微信端获取我的申请的事项信息
	 * @param car_apply_check_status
	 * @param userId
	 * @param orgId
	 * @return
	 */
	public List<CarApply> queryWdsqList(String car_apply_check_status,String userId,String orgId) {
		List<CarApply> list = new ArrayList<CarApply>();
		String hql = "from CarApply t where t.apply_user_id = '"+userId+"' order by t.apply_sn desc";
		list = this.createQuery(hql).list();
		return list;
	}
	/**
	 * 微信端获取已办理的事项信息
	 * @param car_apply_check_status
	 * @param userId
	 * @param orgId
	 * @return
	 */
	public List<CarApply> queryYblList(String car_apply_check_status,String userId,String orgId) {
		List<CarApply> list = new ArrayList<CarApply>();
		String hql = "from CarApply t where t.dep_deal_id = '"+userId+"'" + 
				"    or t.leader_deal_id = '"+userId+"'" + 
				"    or t.fleet_deal_id = '"+userId+"'" + 
				"    or t.office_deal_id = '"+userId+"'" + 
				"    or t.send_deal_id = '"+userId+"'" + 
				"    or t.receive_deal_id = '"+userId+"'" + 
				"    order by t.apply_sn desc";
		list = this.createQuery(hql).list();
		return list;
	}
	/**
	 * 用户所拥有的角色
	 * @param peopleId
	 * @return
	 */
	public List<?> getUserRole(String user_id){
		String sql = "select t.name from SYS_ROLE t where t.id in (select r.role_id from sys_user_role r where r.user_id = ?)";
		List<?> list = this.createSQLQuery(sql,user_id).list();
		return list;
	}
}
