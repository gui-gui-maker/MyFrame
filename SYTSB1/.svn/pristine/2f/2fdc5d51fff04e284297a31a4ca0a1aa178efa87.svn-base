/**
 * 
 */
package com.khnt.oa.car.service;

import com.khnt.utils.DateUtil;

import java.util.Date;

/**
 * @author Administrator
 *
 */
public class TempSql {

	/**
	 * 
	 */
	
	private static String sql;
	public TempSql() {
		// TODO Auto-generated constructor stub
	}
	public static String getSql() {
		if(sql==null){
			setSql(DateUtil.getDateTime("MM", new Date()));
		}
		return sql;
	}
	public static void setSql(String month) {
		String sql = "select c.car_num, c.manager_room_name,c.buy_date,c.driver, c.oil_consumption_100 km100, nvl(min(app.start_km), 0) startKM,nvl(max(app.end_km), 0) endKM,nvl(sum(app.all_km), 0) allKM,nvl(sum(app.gasoline_v), 0) V, nvl(sum(app.gasoline_money), 0) y from oa_car_info c left join (select t.car_id,t.send_car_time,           t.start_km, "
				+ "           t.end_km,        t.all_km, "
				+ "         t.gasoline_v,        t.gasoline_money "
				+ "  from oa_car_apply t  where t.state = '已归还' and to_char(t.send_car_time,'mm')='"
				+ month
				+ "'  ) app  on app.car_id = c.id    group by c.car_num, c.buy_date,c.manager_room_name, c.driver, c.oil_consumption_100";
		
		TempSql.sql = sql;
	}

}
