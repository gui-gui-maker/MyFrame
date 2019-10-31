package util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import com.khnt.base.Factory;

public class WeixinAppUtil {

	/**
	 * 根据微信应用编号获取微信应用响应地址
	 * 
	 * @param app_code -- 微信应用编号
	 * 
	 * @return
	 * @author GaoYa
	 * @date 2018-08-22 上午11:20:00
	 */
	public static Map<String, String> getUrls(String app_code) {
		try {
			String sql = "select t.app_index_url,t.app_deal_url from WEIXIN_APP_INFO t where t.app_code = '" + app_code
					+ "' and t.data_status = '0' ";

			// 获取connection
			Connection conn = Factory.getDB().getConnetion();
			Statement queryStatement = conn.createStatement();
			ResultSet executeQuery = queryStatement.executeQuery(sql);

			Map<String, String> map = new HashMap<String, String>();
			while (executeQuery.next()) {
				map.put("app_index_url", executeQuery.getString("app_index_url"));
				map.put("app_deal_url", executeQuery.getString("app_deal_url"));
			}

			Factory.getDB().freeConnetion(conn);
			return map;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
