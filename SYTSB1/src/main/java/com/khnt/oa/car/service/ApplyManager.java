package com.khnt.oa.car.service;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.SQLQuery;
import org.hibernate.transform.AliasToBeanResultTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.khnt.oa.car.bean.Apply;
import com.khnt.oa.car.bean.CarAllCost;
import com.khnt.oa.car.bean.CarConsume;
import com.khnt.oa.car.bean.CarInfo;
import com.khnt.oa.car.dao.ApplyDao;
import com.khnt.pub.worktask.bean.Worktask;
import com.khnt.pub.worktask.service.WorktaskManager;
import com.khnt.security.CurrentSessionUser;
import com.khnt.security.util.SecurityUtil;

/*******************************************************************************
 * <p>
 * 
 * </p>
 * 
 * @ClassName ApplyManager
 * @JDK 1.5
 * @author
 * @date
 */
@Service("applyManager")
@Transactional
public class ApplyManager extends EntityManageImpl<Apply, ApplyDao> {

	@Autowired
	ApplyDao applyDao;

	public boolean submit(String ids, String editor, String editorCode) throws Exception {
		try {
			if (!com.khnt.utils.StringUtil.isEmpty(ids)) {
				String[] idArr = ids.split(",");
				for (String id : idArr) {
					Apply app = this.get(id);
					app.setEditor(editor);
					app.setEditorCode(editorCode);
					app.setState("处理中");
					// 增加代办事项
					Worktask workTask = new Worktask("[用车审核]"+app.getUsedCarReason(), "carapply", app.getEditorCode(),
							app.getEditor(), editorCode, editor, WorktaskManager.HANDLER_TYPE_PERSON, null,
							app.getId(), "", WorktaskManager.STATUS_NO_PEND, new Date(),new Date(), null);
					workTask.setUrl("app/oa/car/handle_detail.jsp?id=" + app.getId());
					this.applyDao.save(workTask);
				}
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean sendCar(String ids, Apply apply, CurrentSessionUser user) {
		try {
			if (!com.khnt.utils.StringUtil.isEmpty(ids)) {
				Apply app = this.get(ids);
				app.setSendCarMan(user.getName());
				app.setSendCarManCode(user.getCode());
				app.setSendCarTime(new Date());
				app.setStartKm(apply.getStartKm());
				app.setStartTime(apply.getStartTime());
				app.setState("已派车");
				CarInfo carinfo = app.getCar();
				carinfo.setCarState("未交车");
				carinfo.setCarStateDate(new Date());
				this.applyDao.save(carinfo);
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	public boolean isUsed(String carid, Date start, Date end) throws Exception {
		List<Apply> list = this.applyDao.getApplyByUsedCar(carid);
		if (list.size() > 0) {
			for (Apply app : list) {
				if ((start.getTime() >= app.getStartTime().getTime() && start.getTime() <= app.getEndTime().getTime())
						|| (end.getTime() >= app.getStartTime().getTime() && end.getTime() <= app.getEndTime()
								.getTime())
						|| (start.getTime() < app.getStartTime().getTime() && end.getTime() > app.getEndTime()
								.getTime())) {

					return false;
				}
			}
		}
		return true;

	}

	public boolean end(String id, Apply apply) throws Exception {
		try {
			Apply app = this.get(id);
			app.setEndKm(apply.getEndKm());
			// app.setStartTime(apply.getStartTime());
			app.setEndTime(apply.getEndTime());
			// app.setOilConsumption100(apply.getOilConsumption100());
			app.setOilPrice(apply.getOilPrice());
			double l1 = Float.valueOf(app.getEndKm());
			double l2 = Float.valueOf(app.getStartKm());
			double res = l1 - l2;
			app.setAllKm(String.valueOf(res));
			double allKm = Float.valueOf(app.getAllKm());
			String k100 = app.getCar().getOilConsumption100();
			double Km100L = Float.valueOf(k100);

			Double result = (allKm / 100.00) * Km100L;

			DecimalFormat df = new DecimalFormat("###.####");
			DecimalFormat df2 = new DecimalFormat("###.##");
			app.setGasolineV(df2.format(result));

			double re = Float.valueOf(df.format(result)) * Float.valueOf(app.getOilPrice());
			app.setGasolineMoney(String.valueOf(df2.format(re)));

			/*app.setState("已归还");*/
			app.setState("归还确认中");
			app.setBackCarType("正常交车");
			app.setBackCarTypeDate(new Date());

			CarInfo carinfo = app.getCar();
			carinfo.setCarState("正常交车");
			carinfo.setCarStateDate(new Date());
			carinfo.setCarMileage(app.getEndKm());//设置车子行驶里程数
			this.applyDao.save(carinfo);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * 统计指定月份的车辆消耗情况
	 *
	 * @param month
	 * @return
	 */
	public List<CarConsume> countCarConsume(String month) throws Exception {
		if (month == null || "".equals(month)) {
			return null;
		}
		String sql = "select c.car_num, c.manager_room_name,c.change_num,c.buy_date,c.driver, c.oil_consumption_100 km100, nvl(min(app.start_km), 0) startKM,nvl(max(app.end_km), 0) endKM,nvl(sum(app.all_km), 0) allKM,nvl(sum(app.gasoline_v), 0) V, nvl(sum(app.gasoline_money), 0) y " +
				" from oa_car_info c left join (select t.car_id,t.send_car_time,           t.start_km, "+ 
				"           t.end_km,        t.all_km, "+ 
				"         t.gasoline_v,        t.gasoline_money "+ 
				"  from oa_car_apply t  where t.state = '已归还' and to_char(t.send_car_time,'mm')='"+ month+ "'  ) app  on app.car_id = c.id    group by c.car_num,c.change_num, c.buy_date,c.manager_room_name, c.driver, c.oil_consumption_100";

		SQLQuery query=(SQLQuery) applyDao.createSQLQuery(sql);
		
		query.addScalar("car_num",Hibernate.STRING);
		query.addScalar("manager_room_name",Hibernate.STRING);
		query.addScalar("change_num",Hibernate.STRING);
		query.addScalar("buy_date",Hibernate.DATE);
		query.addScalar("driver",Hibernate.STRING);
		query.addScalar("km100",Hibernate.STRING);
		query.addScalar("startKM",Hibernate.STRING);
		query.addScalar("endKM",Hibernate.STRING);
		query.addScalar("allKM",Hibernate.FLOAT);
		query.addScalar("V",Hibernate.FLOAT);
		query.addScalar("y",Hibernate.FLOAT);
		
		query.setResultTransformer(new AliasToBeanResultTransformer(CarConsume.class));
		List<CarConsume> lRegCount =query.list();
		return lRegCount;
	}
	
	/**
	 *  统计指定年份中某月的车辆产生的费用情况
	 *
	 * @param month
	 * @return
	 */
	public List<CarAllCost> countCarAllCost(String year,String month) throws Exception {
		if (month == null || "".equals(month)||year==null||"".equals(year)) {
			return null;
		}
		StringBuffer sql = new StringBuffer();
		sql.append("	select car.id carId,	");
		sql.append("	       car.org_code unitId,	");
		sql.append("	       car.car_num carNum,	");
		sql.append("	       car.manager_room_name manageDep,	");
		sql.append("	       car.buy_date buyDate,	");
		sql.append("	       car.car_brand carBrand,	");
		sql.append("	       nvl(car.car_mileage,0) carMil,	");
		sql.append("	       nvl(oil.本月行驶里程, 0) carMilMonth,	");
		sql.append("	       nvl(oil.油费, 0) carOilMonth,	");
		sql.append("	       car.oil_consumption_100 carOil100,	");
		sql.append("	       nvl(costs.过路过桥费, 0) carRoadFee,	");
		sql.append("	       nvl(costs.洗车费, 0) carWashFee,	");
		sql.append("	       nvl(costs.停车费, 0) carParkFee,	");
		sql.append("	       nvl(rep.维修, 0) carRepairFee,	");
		sql.append("	       nvl(costs.审车费, 0) carYearFee,	");
		sql.append("	       nvl(rep.保养, 0) carBeautiFee,	");
		sql.append("	       nvl(safe.保险费, 0) carBxFee	");
		sql.append("		");
		sql.append("	  from oa_car_info car	");
		sql.append("	  left join (select app.car_id, sum(app.gasoline_money) 油费,sum(app.all_km) 本月行驶里程	");
		sql.append("	               from oa_car_apply app	");
		sql.append("	              where app.state = '已归还'	");
		sql.append("	                and to_char(app.send_car_time, 'yyyy-mm') = '"+ year + "-" + month +"'	");
		sql.append("	              group by app.car_id) oil	");
		sql.append("	    on car.id = oil.car_id	");
		sql.append("	  left join (select r.car_id,	");
		sql.append("	                    sum(case	");
		sql.append("	                          when r.type = '1' then	");
		sql.append("	                           r.repair_money	");
		sql.append("	                        end) 维修,	");
		sql.append("	                    sum(case	");
		sql.append("	                          when r.type = '2' then	");
		sql.append("	                           r.repair_money	");
		sql.append("	                        end) 保养	");
		sql.append("	               from oa_car_repair r	");
		sql.append("	              where to_char(r.repair_time, 'yyyy-mm') = '"+ year + "-" + month +"'	");
		sql.append("	              group by r.car_id) rep	");
		sql.append("	    on car.id = rep.car_id	");
		sql.append("	  left join (select sa.car_id, sum(sa.safe_money) 保险费	");
		sql.append("	               from oa_car_safe sa	");
		sql.append("	              where to_char(sa.start_date, 'yyyy-mm') = '"+ year + "-" + month +"'	");
		sql.append("	              group by sa.car_id) safe	");
		sql.append("	    on car.id = safe.car_id	");
		sql.append("	  left join (select c.car_id,	");
		sql.append("	                    sum(case	");
		sql.append("	                          when c.cost_type = '1' then	");
		sql.append("	                           c.cost	");
		sql.append("	                        end) 过路过桥费,	");
		sql.append("	                    sum(case	");
		sql.append("	                          when c.cost_type = '2' then	");
		sql.append("	                           c.cost	");
		sql.append("	                        end) 洗车费,	");
		sql.append("	                    sum(case	");
		sql.append("	                          when c.cost_type = '3' then	");
		sql.append("	                           c.cost	");
		sql.append("	                        end) 停车费,	");
		sql.append("	                    sum(case	");
		sql.append("	                          when c.cost_type = '4' then	");
		sql.append("	                           c.cost	");
		sql.append("	                        end) 审车费	");
		sql.append("	               from oa_car_cost c	");
		sql.append("	              where to_char(c.write_time, 'yyyy-mm') = '"+ year + "-" + month +"'	");
		sql.append("	              group by c.car_id) costs	");
		sql.append("	    on car.id = costs.car_id	");
		sql.append("	    where car.org_code = '"+SecurityUtil.getSecurityUser().getUnit().getId()+"'	");
		sql.append("	    order by car.manager_room_code	");
		System.out.println("============sql="+sql);
		SQLQuery query=(SQLQuery) applyDao.createSQLQuery(sql.toString());
		
		query.addScalar("carId",Hibernate.STRING);
		query.addScalar("unitId",Hibernate.STRING);
		query.addScalar("carNum",Hibernate.STRING);
		query.addScalar("manageDep",Hibernate.STRING);
		query.addScalar("buyDate",Hibernate.DATE);
		query.addScalar("carBrand",Hibernate.STRING);
		query.addScalar("carMil",Hibernate.FLOAT);
		query.addScalar("carMilMonth",Hibernate.FLOAT);
		query.addScalar("carOilMonth",Hibernate.FLOAT);
		query.addScalar("carOil100",Hibernate.FLOAT);
		query.addScalar("carRoadFee",Hibernate.FLOAT);
		query.addScalar("carWashFee",Hibernate.FLOAT);
		query.addScalar("carParkFee",Hibernate.FLOAT);
		query.addScalar("carRepairFee",Hibernate.FLOAT);
		query.addScalar("carYearFee",Hibernate.FLOAT);
		query.addScalar("carBeautiFee",Hibernate.FLOAT);
		query.addScalar("carBxFee",Hibernate.FLOAT);
		query.setResultTransformer(new AliasToBeanResultTransformer(CarAllCost.class));
		List<CarAllCost> lRegCount =query.list();
		return lRegCount;
	}

	public List<Map<String, Object>> exportHis(String ids) {
		List<Map<String, Object>> result = applyDao.exportHis(ids);
		return result;
	}
	public boolean confirm(String id) throws Exception {
		try {
			CurrentSessionUser user=SecurityUtil.getSecurityUser();
			Apply app = this.get(id);
			app.setState("已归还");
			app.setConfirmTime(new Date());
			app.setConfirmor(user.getName());
			app.setConfirmorCode(user.getId());
			this.applyDao.save(app);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
