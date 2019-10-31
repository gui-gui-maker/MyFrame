package com.lsts.device.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import com.khnt.base.Factory;
import com.khnt.core.crud.dao.impl.EntityDaoImpl;
import com.khnt.pub.codetable.bean.CodeTableValue;
import com.khnt.utils.StringUtil;
import com.lsts.IdFormat;
import com.lsts.device.bean.DeviceDTO;
import com.lsts.device.bean.DeviceDocument;

/**
 * 特种设备信息管理 dao
 * 
 * @author 肖慈边 2014-1-8
 */

@Repository("deviceDao")
public class DeviceDao extends EntityDaoImpl<DeviceDocument> {
	private static Connection conn = null; // 数据库连接
	private static PreparedStatement pstmt = null; // 数据库操作对象
	private static ResultSet rs = null; // 结果集
	
	/**
	 * 根据使用单位ID查询设备信息
	 * @param id
	 * @return 
	 * @author GaoYa
	 * @date 2014-05-09 下午01:35:00
	 */
	@SuppressWarnings("unchecked")
    public List<DeviceDocument> queryDevicesByComId(String fk_company_info_use_id) {
    	List<DeviceDocument> list = new ArrayList<DeviceDocument>();
    	try {
    		if (StringUtil.isNotEmpty(fk_company_info_use_id)) {
    			String hql = "from DeviceDocument d where d.fk_company_info_use_id=? and d.device_status='2' order by d.device_registration_code";
    			list = this.createQuery(hql, fk_company_info_use_id).list();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return list;
    }
	
	/**
	 * 根据使用单位ID查询电梯信息
	 * @param id
	 * @return 
	 * @author GaoYa
	 * @date 2014-05-09 下午01:35:00
	 */
	@SuppressWarnings("unchecked")
    public List<DeviceDocument> queryDtDevicesByComId(String fk_company_info_use_id) {
    	List<DeviceDocument> list = new ArrayList<DeviceDocument>();
    	try {
    		if (StringUtil.isNotEmpty(fk_company_info_use_id)) {
    			String hql = "from DeviceDocument d where d.fk_company_info_use_id=? and d.device_status='2' and substr(d.device_registration_code,0,1)='3' order by d.device_registration_code";
    			list = this.createQuery(hql, fk_company_info_use_id).list();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return list;
    }
	
	/**
	 * 根据使用单位ID、单位内部编号、设备类别代码查询设备信息
	 * @param fk_company_info_use_id -- 使用单位id
	 * @param internal_num -- 单位内部编号
	 * @param device_sort_code -- 设备类别代码
	 * 
	 * @return 
	 * @author GaoYa
	 * @date 2016-01-29 下午01:05:00
	 */
	@SuppressWarnings("unchecked")
    public List<String> queryByInternal_num(String fk_company_info_use_id, String internal_num, String device_sort_code) {
    	List<String> list = new ArrayList<String>();
    	try {
    		String hql = "select d.id from base_device_document d where d.device_status!='99'";
    		if (StringUtil.isNotEmpty(fk_company_info_use_id)) {
    			hql += " and d.fk_company_info_use_id='"+fk_company_info_use_id+"'";
			}
    		if (StringUtil.isNotEmpty(internal_num)) {
    			hql += " and d.internal_num='"+internal_num+"'";
			}
    		if (StringUtil.isNotEmpty(device_sort_code)) {
    			hql += " and d.device_sort_code='"+device_sort_code+"'";
			}
    		list = this.createSQLQuery(hql).list();
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return list;
    }
	
	/**
	 * 根据设备注册代码查询设备信息
	 * @param id
	 * @return 
	 * @author GaoYa
	 * @date 2014-05-09 下午01:35:00
	 */
	@SuppressWarnings("unchecked")
    public List<DeviceDocument> queryDeviceDocumentByDeviceCode(String device_code) {
    	List<DeviceDocument> list = new ArrayList<DeviceDocument>();
    	try {
    		if (StringUtil.isNotEmpty(device_code)) {
    			String hql = "from DeviceDocument d where d.device_status !='99' and d.device_registration_code=? and d.device_registration_code<>'/'"; 
    			//String hql = "from DeviceDocument d where d.device_status !='99' and d.device_registration_code=?";
    			list = this.createQuery(hql, device_code).list();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return list;
    }

	/**
	 * 设备预警
	 * 
	 * @return
	 * @author GaoYa
	 * @date 2014-04-22 上午11:00:00
	 */
	@SuppressWarnings("unchecked")
	public List<DeviceDTO> getDeviceWarnList(String device_area_code) {
		List<DeviceDTO> list = new ArrayList<DeviceDTO>();
		try {
			conn = getConn();
			String sql = "select t.id,t.device_name,t1.com_name,substr(t.device_sort_code,0,1) big_class,t5.regional_name,(t.inspect_next_date-sysdate) as waring"
					+ " from base_device_document t, base_company_info t1,base_administrative_divisions t5"
					+ " where t.FK_COMPANY_INFO_USE_ID = t1.id(+) and t.device_area_code = t5.regional_code"
					+ " and t.device_status != '99' and t.inspect_next_date - sysdate<=30";
			if (StringUtil.isNotEmpty(device_area_code)) {
				sql += " and t.device_area_code = '" + device_area_code + "'";
			}
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				DeviceDTO deviceDTO = new DeviceDTO();
				deviceDTO.setId(rs.getString(1));
				deviceDTO.setDevice_name(rs.getString(2));
				deviceDTO.setCom_name(rs.getString(3));
				deviceDTO.setDevice_type(rs.getString(4));
				deviceDTO.setDevice_area_name(rs.getString(5));
				deviceDTO.setWarn_days(rs.getInt(6));
				list.add(deviceDTO);
			}
			closeConn();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> queryInfo(String deviceIds) {
		List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
		deviceIds=IdFormat.formatIdStr(deviceIds);
		String sql = "select d.id,d.device_name,d.device_registration_code "
				+ " from base_device_document d where d.id in ( " 
				+ deviceIds
				+ " ) "
				+ " order by d.id desc ";
		
		List list = this.createSQLQuery(sql).list();
		if (!list.isEmpty()) {
			for (int i = 0; i < list.size(); i++) {
				Object[] objArr = list.toArray();
				Object[] obj = (Object[]) objArr[i];
				HashMap<String, Object> map = new HashMap<String, Object>();
				map.put("id", obj[0]);
				map.put("device_name", obj[1]);
				map.put("device_registration_code", obj[2]);
				mapList.add(map);
			}
		}
		return mapList;
	}
	
	
	@SuppressWarnings("unchecked")
	public String getDeviceTotal() {
		
		String sql = "select count(1) as totalCount from base_device_document t where t.device_sort_code='2610' and t.device_status<>'99'";
		
		SQLQuery query = getSession().createSQLQuery(sql);
		
		Object obj = query.uniqueResult();
		return obj == null ? "0" : obj.toString();


	}
	
	@SuppressWarnings("unchecked")
	public String getDeviceCheck() {
		
		String sql = "select count(1) as totalCount from base_device_document t where t.device_sort_code='2610' and t.is_cur_check='2'  and t.device_status<>'99'";
		
		SQLQuery query = getSession().createSQLQuery(sql);
		
		Object obj = query.uniqueResult();
		return obj == null ? "0" : obj.toString();


	}
	
	@SuppressWarnings("unchecked")
	public String getDeviceUnCheck() {
		
		String sql = "select count(1) as totalCount from base_device_document t where t.device_sort_code='2610' and t.is_cur_check='1'  and t.device_status<>'99'";
		
		SQLQuery query = getSession().createSQLQuery(sql);
		
		Object obj = query.uniqueResult();
		return obj == null ? "0" : obj.toString();


	}
	
	/**
	 * 
	 * @return 返回介质
	 */
	public List<CodeTableValue> getMedia() {
		String sql = "select id,name,value from PUB_CODE_TABLE_VALUES t where code_table_id=(select id from pub_code_table where code='BASE_PARA_CZJZ') ";
		SQLQuery query = getSession().createSQLQuery(sql);
		List list = query.list();
		List<CodeTableValue> listct = new ArrayList<CodeTableValue>();
		for (int i = 0; i < list.size(); i++) {
			CodeTableValue ctable = new CodeTableValue();
			Object obj = list.get(i);
			Object[] ob = (Object[]) obj;
			ctable.setId(String.valueOf(ob[0]));
			ctable.setName(String.valueOf(ob[1]));
			ctable.setValue(String.valueOf(ob[2]));
			listct.add(ctable);
		}
		return listct;
	}
	
	
	public String deviceCountByMedia(String mediaCode,
			Date startDate, Date endDate) throws Exception {
		String sql = "select count(1) from BASE_DEVICE_PRESSUREVESSELS t,base_device_document t1 "
				+ " where t.p_czjz is not null  and t1.id=t.fk_tsjc_device_document_id and t1.device_sort_code='2610'  and t1.device_status<>'99' and t.p_czjz =?";
		SQLQuery query = getSession().createSQLQuery(sql);
		query.setString(0, mediaCode);
		
		Object obj = query.uniqueResult();
		return obj == null ? "0" : obj.toString();
	}
	/**
	 * 业务报检时，记录该设备当前是否正在报检中
	 * 
	 * @param ids --
	 *            报检业务ID
	 * @return
	 */
	public void updateDeviceInfo(String device_id) {
		String sql = "update DeviceDocument set is_cur_check = '2' where id = ?";
		this.createQuery(sql, device_id).executeUpdate();
	}
	
	/**
	 * 获取设置注册代码后四位序号
	 * 
	 * @param device_type --
	 *            设备类别
	 * @param device_area --
	 *            设备所在区域代码
	 * @return
	 * @author GaoYa
	 * @date 2017-04-24 下午03:32:00
	 */
	@SuppressWarnings("unchecked")
	public synchronized String getRegistrationCode(String device_type,
			String device_area) {
		String return_num = "";
		try {
			SimpleDateFormat mat = new SimpleDateFormat("yyyyMM");
			String num = "";
			if(StringUtil.isNotEmpty(device_area)){
				num = device_type + device_area.substring(0, 6)
				+ mat.format(new Date());
			}else{
				num = device_type + mat.format(new Date());
			}
			
			String start_num = "0000";
			String big_class = device_type.substring(0, 1);
			if("4".equals(big_class) || "5".equals(big_class) || "6".equals(big_class) || "9".equals(big_class)){
				start_num = "6000";
			}
			String sql = "select nvl(max(t.device_registration_code),'"+start_num+"') from DeviceDocument t where t.device_registration_code like '%"
					+ num + "%'";
			
			List list = this.createQuery(sql).list();
			if (!list.isEmpty()) {
				String curNum = list.get(0) + "";
				if (StringUtil.isNotEmpty(curNum)) {
					return_num = curNum;
				}
			}
			if ("0000".equals(return_num) || "6000".equals(return_num)) {
				return getCountNum(num, Integer.parseInt(return_num) + 1);
			} else {
				// 取设备注册代码后4位（例如：0001，‘0001’代表序号）
				String suf = return_num.substring(return_num.length()-4);
				return getCountNum(num, (Integer.parseInt(suf) + 1));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return return_num;
	}
	
	// 生成四位自动编号
	private String getCountNum(String num, int count_num) throws Exception {
		String code_4 = "";
		if ((0 < count_num) && (count_num < 10))
			code_4 = num + "000" + (count_num);
		if ((9 < count_num) && (count_num < 100))
			code_4 = num + "00" + (count_num);
		if ((99 < count_num) && (count_num < 1000))
			code_4 = num + "0" + (count_num);
		else if (count_num > 999)
			code_4 = num + String.valueOf(count_num);
		return code_4;
	}

	// 获取数据库连接
	public Connection getConn() {
		try {
			conn = Factory.getDB().getConnetion();
		} catch (Exception e) {
			logger.error("获取数据库连接失败：" + e.getMessage());
			e.printStackTrace();
		}
		return conn;
	}

	// 关闭连接
	public void closeConn() {
		try {
			/*if (null != rs)
				rs.close();
			if (null != pstmt)
				pstmt.close();
			if (null != conn)
				conn.close();*/
			Factory.getDB().freeConnetion(conn);	// 释放连接
		} catch (Exception e) {
			logger.error("关闭数据库连接错误：" + e.getMessage());
			e.printStackTrace();
		}
	}
}
