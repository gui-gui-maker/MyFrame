package com.lsts.inspection.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.khnt.core.crud.dao.impl.EntityDaoImpl;
import com.lsts.IdFormat;
import com.lsts.inspection.bean.InspectionZZJDInfo;

/**
 * 制造监督检验报检业务明细数据处理对象
 * 
 * @ClassName InspectionZZJDDao
 * @JDK 1.6
 * @author GaoYa
 * @date 2015-01-13 上午09:27:00
 */
@Repository("inspectionZZJDInfoDao")
public class InspectionZZJDInfoDao extends EntityDaoImpl<InspectionZZJDInfo> {

	@SuppressWarnings("unchecked")
	public List<InspectionZZJDInfo> getInspectionZZJDInfos(
			String inspection_zzjd_id) {
		List<InspectionZZJDInfo> list = new ArrayList<InspectionZZJDInfo>();
		String hql = "from InspectionZZJDInfo i where i.inspectionZZJD.id=? and i.data_status != '99'";
		list = this.createQuery(hql, inspection_zzjd_id).list();
		return list;
	}

	// 根据业务信息ID查询制造监督检验明细表数据
	@SuppressWarnings("unchecked")
	public InspectionZZJDInfo getByInfoId(String inspection_info_id) {
		String hql = "from InspectionZZJDInfo i where i.fk_inspection_info_id=? and i.data_status != '99'";
		InspectionZZJDInfo inspectionZZJDInfo = (InspectionZZJDInfo) this
				.createQuery(hql, inspection_info_id).uniqueResult();
		if (inspectionZZJDInfo != null) {
			return inspectionZZJDInfo;
		}
		return null;
	}

	/**
	 * 根据明细ID查询明细列表
	 * 
	 * @param ids
	 * @return
	 * @author GaoYa
	 * @date 2015-01-23 下午05:26:00
	 */
	@SuppressWarnings("unchecked")
	public List<InspectionZZJDInfo> queryInfos(String ids) throws Exception{
		List<InspectionZZJDInfo> infoList = new ArrayList<InspectionZZJDInfo>();
		ids = IdFormat.formatIdStr(ids);
		String sql = "select t.made_unit_name,t.made_license_level,t.made_license_code,t.made_date,t.device_name,t.device_no,t.device_code,"
				+ "t.device_pic_no,t.device_type_code,t.design_unit_name,t.design_license_code,t.design_date,t.safely_tech_standard,"
				+ "t.inspection_user_name1,t.inspection_user_name2"
				+ " from tzsb_inspection_zzjd_info t where t.id in ("
				+ ids
				+ ")";
		List list = this.createSQLQuery(sql).list();
		if (!list.isEmpty()) {
			for (int i = 0; i < list.size(); i++) {
				Object[] objArr = list.toArray();
				Object[] obj = (Object[]) objArr[i];
				InspectionZZJDInfo inspectionZZJDInfo = new InspectionZZJDInfo();
				inspectionZZJDInfo.setMade_unit_name(String.valueOf(obj[0]));
				inspectionZZJDInfo.setMade_license_level(String.valueOf(obj[1]));
				inspectionZZJDInfo.setMade_license_code(String.valueOf(obj[2]));
				inspectionZZJDInfo.setMade_date(String.valueOf(obj[3]));
				inspectionZZJDInfo.setDevice_name(String.valueOf(obj[4]));
				inspectionZZJDInfo.setDevice_no(String.valueOf(obj[5]));
				inspectionZZJDInfo.setDevice_code(String.valueOf(obj[6]));
				inspectionZZJDInfo.setDevice_pic_no(String.valueOf(obj[7]));
				inspectionZZJDInfo.setDevice_type_code(String.valueOf(obj[8]));
				inspectionZZJDInfo.setDesign_unit_name(String.valueOf(obj[9]));
				inspectionZZJDInfo.setDesign_license_code(String.valueOf(obj[10]));
				inspectionZZJDInfo.setDesign_date(String.valueOf(obj[11]));
				inspectionZZJDInfo.setSafely_tech_standard(String.valueOf(obj[12]));
				inspectionZZJDInfo.setInspection_user_name1(String.valueOf(obj[13]));
				inspectionZZJDInfo.setInspection_user_name2(String.valueOf(obj[14]));
				infoList.add(inspectionZZJDInfo);
			}
		}
		return infoList;
	}
	
	/**
	 * 借报告
	 * 
	 * @param info_ids
	 *            报告业务id
	 * @return
	 * @author GaoYa
	 * @date 2018-01-17 上午10:38:00
	 */
	@SuppressWarnings("unchecked")
	public List<InspectionZZJDInfo> exportReport(String info_ids) {
		List<InspectionZZJDInfo> list = new ArrayList<InspectionZZJDInfo>();
		info_ids = IdFormat.formatIdStr(info_ids);
		try {
			String sql = "from InspectionZZJDInfo t where t.fk_inspection_info_id in ("+info_ids+") ";
			list = this.createQuery(sql).list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}
