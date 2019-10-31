package util;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.khnt.base.Factory;
import com.khnt.rbac.impl.bean.Org;
import com.khnt.rbac.impl.bean.User;
import com.khnt.security.CurrentSessionUser;
import com.khnt.security.util.SecurityUtil;
import com.khnt.utils.DateUtil;
import com.khnt.utils.StringUtil;

public class TS_Util {

	public static void main(String[] args) {
		try {
			
			double total = 0.00;
			double amount = 5.5;
			for(int i=0;i<10;i++){
				total = addAmount(total, amount);
			}
			
			String count = String.valueOf(Math.ceil(total));
			String count2 = String.valueOf(new BigDecimal(total).setScale(0, BigDecimal.ROUND_HALF_UP));
			System.out.println(count);
			System.out.println(count2);
			System.out.println(Math.round(total));
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	private static double addAmount(double amount1, double amount2){
		double r1,r2,m; 
		try{
			r1=String.valueOf(amount1).split(".")[1].length();
		}catch(Exception e){
			r1=0;
		} 
		try{
			r2=String.valueOf(amount2).split(".")[1].length();
		}catch(Exception e){
			r2=0;
		} 
		m=Math.pow(10,Math.max(r1,r2));
		return (amount1*m+amount2*m)/m;
	}
	public static String readFileString2(String filePath){

		StringBuffer str=new StringBuffer("");

		File file=new File(filePath);

		try {

			FileReader fr=new FileReader(file);

			int ch = 0;

			while((ch = fr.read())!=-1 )

			{

				System.out.print((char)ch+" ");
				str.append((char)ch+" ");
			}

			fr.close();

		} catch (IOException e) {

			// TODO Auto-generated catch block

			e.printStackTrace();

			System.out.println("File reader出错");

		}

		return str.toString();

	}
	// 获取表单号
	public synchronized static String getHallNumber() throws Exception {

		// 获取connection
		Connection conn = Factory.getDB().getConnetion();

		String number = "";
		Date date = new Date();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		String dateNowStr = sdf.format(date);

		String sql = "select '"
				+ dateNowStr
				+ "'|| nvl2(max(hall_no), lpad(to_number(substr(max(hall_no), 7, 11)) + 1, 5, 0),'00001') as con"
				+ " from tzsb_inspection_hall where hall_no > '" + dateNowStr
				+ "'||'00000' ";

		Statement queryStatement = conn.createStatement();
		ResultSet executeQuery = queryStatement.executeQuery(sql);

		while (executeQuery.next()) {

			number = executeQuery.getString("con");
		}

		Factory.getDB().freeConnetion(conn);
		return number;

	}

	// 获取业务报检表单号
	public synchronized static String getYWBJNumber() throws Exception {

		// 获取connection
		Connection conn = Factory.getDB().getConnetion();

		String number = "";
		Date date = new Date();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		String dateNowStr = sdf.format(date);

		String sql = "select '"
				+ dateNowStr
				+ "'|| nvl2(max(accept_no), lpad(to_number(substr(max(accept_no), 7, 11)) + 1, 5, 0),'00001') as con"
				+ " from tzsb_inspection where accept_no > '" + dateNowStr
				+ "'||'00000' ";

		Statement queryStatement = conn.createStatement();
		ResultSet executeQuery = queryStatement.executeQuery(sql);

		while (executeQuery.next()) {

			number = executeQuery.getString("con");
		}

		Factory.getDB().freeConnetion(conn);
		return number;

	}

	// 自动生成注册代码，规则设备类型+510100+yyyyMM+xxxx自增长
	public static synchronized String getRegistrationCode(String device_type,
			String device_area) throws Exception {
		// String district_code = Resources.getProperty("DISTRICT_CODE");

		// 获取connection
		Connection conn = Factory.getDB().getConnetion();

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

		// String num_sql = "select count(device_registration_code)+1 as
		// count_num from base_device_document where device_registration_code
		// like '%"+num+"%'";
		String num_sql = "select nvl(max(device_registration_code),'"+start_num+"') as count_num from base_device_document where device_registration_code like '%"
				+ num + "%'";
		Statement queryStatement = conn.createStatement();
		ResultSet executeQuery = queryStatement.executeQuery(num_sql);

		String return_num = "";
		while (executeQuery.next()) {

			return_num = executeQuery.getString("count_num");
		}
		Factory.getDB().freeConnetion(conn);
		
		if ("0000".equals(return_num) || "6000".equals(return_num)) {
			return getCountNum(num, Integer.parseInt(return_num) + 1);
		} else {
			// 取设备注册代码后4位（例如：0001，‘0001’代表序号）
			String suf = return_num.substring(return_num.length()-4);
			return getCountNum(num, (Integer.parseInt(suf) + 1));
		}
	}

	// 生成四位自动编号
	private static String getCountNum(String num, int count_num)
			throws Exception {
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

	// 获取业务流水号
	public synchronized static String getSn(int cont, Connection conn)
			throws Exception {

		String number = "";
		Date date = new Date();

		String sql = "select nvl2(max(sn), lpad(to_number(max(sn)) + "
				+ cont
				+ ", 6, 0),'000001') sn from tzsb_inspection_info where sn > '000000' ";

		Statement queryStatement = conn.createStatement();
		ResultSet executeQuery = queryStatement.executeQuery(sql);

		while (executeQuery.next()) {

			number = executeQuery.getString("sn");
		}

		Factory.getDB().freeConnetion(conn);
		return number;

	}

	// 获取制造监督报检明细业务流水号
	public synchronized static String getZZJDInfoSn(int cont, Connection conn)
			throws Exception {
		String number = "";
		String sql = "select nvl2(max(sn), lpad(to_number(max(sn)) + "
				+ cont
				+ ", 6, 0),'000001') sn from tzsb_inspection_zzjd_info where sn > '000000' ";
		Statement queryStatement = conn.createStatement();
		ResultSet executeQuery = queryStatement.executeQuery(sql);
		while (executeQuery.next()) {
			number = executeQuery.getString("sn");
		}
		Factory.getDB().freeConnetion(conn);
		return number;

	}
	
	// 获取制造监督报检业务流水号
	public synchronized static String getZZJDSn(int cont, Connection conn)
			throws Exception {
		String number = "";
		String sql = "select nvl2(max(sn), lpad(to_number(max(sn)) + "
				+ cont
				+ ", 6, 0),'000001') sn from tzsb_inspection_zzjd where sn > '000000' ";
		Statement queryStatement = conn.createStatement();
		ResultSet executeQuery = queryStatement.executeQuery(sql);
		while (executeQuery.next()) {
			number = executeQuery.getString("sn");
		}
		Factory.getDB().freeConnetion(conn);
		return number;

	}
	
	// 获取检验资料报送打印签收业务流水号
	public synchronized static String getReportPrintSn(int cont, Connection conn)
			throws Exception {
		String number = "";
		String sql = "select nvl2(max(sn), lpad(to_number(max(sn)) + "
				+ cont
				+ ", 6, 0),'000001') sn from TZSB_REPORT_PRINT where sn > '000000' ";
		Statement queryStatement = conn.createStatement();
		ResultSet executeQuery = queryStatement.executeQuery(sql);
		while (executeQuery.next()) {
			number = executeQuery.getString("sn");
		}
		Factory.getDB().freeConnetion(conn);
		return number;

	}

	// 获取检验资料报送打印签收明细业务流水号
	public synchronized static String getReportPrintRecordSn(int cont, Connection conn)
			throws Exception {
		String number = "";
		String sql = "select nvl2(max(sn), lpad(to_number(max(sn)) + "
				+ cont
				+ ", 6, 0),'000001') sn from TZSB_REPORT_PRINT_RECORD where sn > '000000' ";
		Statement queryStatement = conn.createStatement();
		ResultSet executeQuery = queryStatement.executeQuery(sql);
		while (executeQuery.next()) {
			number = executeQuery.getString("sn");
		}
		Factory.getDB().freeConnetion(conn);
		return number;

	}
	
	// 获取检验资料报送打印签收明细业务流水号
	public synchronized static String getReportTransferSn(int cont, Connection conn) throws Exception {
		String number = "";
		String sql = "select nvl2(max(sn), lpad(to_number(max(sn)) + " + cont
				+ ", 6, 0),'000001') sn from TZSB_REPORT_TRANSFER where sn > '000000' ";
		Statement queryStatement = conn.createStatement();
		ResultSet executeQuery = queryStatement.executeQuery(sql);
		while (executeQuery.next()) {
			number = executeQuery.getString("sn");
		}
		return number;

	}
	
	// 获取检验资料报送打印签收明细业务流水号
	public synchronized static String getReportTransferRecordSn(int cont, Connection conn) throws Exception {
		String number = "";
		String sql = "select nvl2(max(sn), lpad(to_number(max(sn)) + " + cont
				+ ", 6, 0),'000001') sn from TZSB_REPORT_TRANSFER_RECORD where sn > '000000' ";
		Statement queryStatement = conn.createStatement();
		ResultSet executeQuery = queryStatement.executeQuery(sql);
		while (executeQuery.next()) {
			number = executeQuery.getString("sn");
		}
		return number;

	}
	
	// 获取内审业务流水号
	public synchronized static String getReviewSn(int cont, Connection conn)
			throws Exception {

		String number = "";

		String sql = "select ('LSTS-'||TO_CHAR(SYSDATE,'YYYY')||'-'|| lpad(to_number(max(substr(servicenum,-6)))+1,6,'0')  )sn  from oa_reviewmanagement ";

		Statement queryStatement = conn.createStatement();
		ResultSet executeQuery = queryStatement.executeQuery(sql);

		while (executeQuery.next()) {

			number = executeQuery.getString("sn");
		}

		Factory.getDB().freeConnetion(conn);
		return number;

	}

	// 获取通知书业务流水号
	public synchronized static String getAdviceNoteSn(int cont, Connection conn)
			throws Exception {
		String number = "";
		String sql = "select ('LSTS-'||TO_CHAR(SYSDATE,'YYYY')||'-'|| lpad(to_number(max(substr(advicenote_sn,-6)))+1,6,'0')  )sn  from TZSB_ADVICENOTE ";
		Statement queryStatement = conn.createStatement();
		ResultSet executeQuery = queryStatement.executeQuery(sql);
		while (executeQuery.next()) {
			number = executeQuery.getString("sn");
		}
		Factory.getDB().freeConnetion(conn);
		return number;
	}

	public static String nullToString(Object obj) {
		if (obj == null || "null".equals(obj)) {
			return "";
		} else {
			return obj.toString();
		}
	}

	/**
	 * 获取当前年份
	 * 
	 * @return
	 * @author GaoYa
	 * @date 2014-05-13 下午01:28:00
	 */
	public static String getCurYear() {
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR); // 获取当前年份
		return String.valueOf(year);
	}
	
	/**
	 * 获取当前年份后两位数
	 * 
	 * @return
	 * @author GaoYa
	 * @date 2016-03-13 下午03:00:00
	 */
	public static String getCurYear2() {
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR); // 获取当前年份
		String yearStr = String.valueOf(year);
		String lastYear = yearStr.substring(2);
		return lastYear;
	}
	
	/**
	 * 获取当前年份
	 * 
	 * @return
	 * @author GaoYa
	 * @date 2015-11-14 下午05:40:00
	 */
	public static int getCurrentYear() {
		Calendar cal = Calendar.getInstance();
		return cal.get(Calendar.YEAR); // 获取当前年份
		 
	}

	/**
	 * 获取当前年月
	 * 
	 * @return
	 * @author GaoYa
	 * @date 2014-05-13 下午01:28:00
	 */
	public static String getCurYearMonth() {
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR); // 获取当前年份
		int month = cal.get(Calendar.MONTH) + 1; // 获取当前月份
		String monStr = "";
		if (month < 10) {
			monStr += "0";
		}
		monStr += month;
		return String.valueOf(year) + monStr;
	}
	
	/**
	 * 获取某月最后一天
	 * 
	 * @return
	 * @author GaoYa
	 * @date 2017-11-07 上午10:50:00
	 */
	public static String getLastDayOfMonth(int year, int month) {
		Calendar cal = Calendar.getInstance();
		// 设置年份
		cal.set(Calendar.YEAR, year);
		// 设置月份
		cal.set(Calendar.MONTH, month-1);
		// 获取某月最大天数
		int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		return String.valueOf(lastDay);
	}

	/**
	 * 将id的之间加上
	 * 
	 * @param id
	 * @param ch
	 *            id两边加的符号，用于区分字符串和数字等
	 * @return
	 */
	public static String formatIdStr(String id) {
		String chStr = "'";
		String idStr = "";
		String[] ids = id.split(",");
		for (int i = 0; i < ids.length; i++) {
			idStr += (i == 0 ? "" : ",") + chStr + ids[i] + chStr;
		}
		return idStr;
	}
	
	/**
     * 将一个 JavaBean 对象转化为一个  Map
     * @param bean 要转化的JavaBean 对象
     * @return 转化出来的  Map 对象
     * @throws IntrospectionException 如果分析类属性失败
     * @throws IllegalAccessException 如果实例化 JavaBean 失败
     * @throws InvocationTargetException 如果调用属性的 setter 方法失败
     * @author GaoYa
	 * @date 2015-10-26 下午05:26:00
     */
	@SuppressWarnings("unchecked")
    public static Map<String, Object> convertBeanToMap(Object bean)
            throws IntrospectionException, IllegalAccessException, InvocationTargetException {
        Class type = bean.getClass();
        Map<String, Object> returnMap = new HashMap<String, Object>();
        BeanInfo beanInfo = Introspector.getBeanInfo(type);

        PropertyDescriptor[] propertyDescriptors =  beanInfo.getPropertyDescriptors();
        for (int i = 0; i< propertyDescriptors.length; i++) {
            PropertyDescriptor descriptor = propertyDescriptors[i];
            String propertyName = descriptor.getName();
            if (!propertyName.equals("class")) {
                Method readMethod = descriptor.getReadMethod();
                Object result = readMethod.invoke(bean, new Object[0]);
                if (result != null) {
                    returnMap.put(propertyName, result);
                } else {
                    returnMap.put(propertyName, "");
                }
            }
        }
        return returnMap;
    }
	
	@SuppressWarnings("unchecked")
	public static Map<String,Object> beanToMap(Object obj) throws Exception {
		Map<String,Object> map = new HashMap<String,Object>();
		Field[] field =  obj.getClass().getDeclaredFields();
		for(Field fd : field) {
			if(fd.getName().equals("serialVersionUID"))
				continue ;
			try{
				System.out.println(changFisrtUpper(fd.getName()));
				System.out.println("get"+changFisrtUpper(fd.getName()));
			Method method = obj.getClass()
					.getMethod("get"+changFisrtUpper(fd.getName()), null);
			
			if(method!= null) {
				method.getName();
				Object obj1 = method.invoke(obj, null) ;
				if (fd.getName().equals("last_check_time")) {
					map.put(fd.getName().toUpperCase(), DateUtil.getDateTime("yyyy-MM-dd", (Date)obj1));
				}else{
					map.put(fd.getName().toUpperCase(), obj1);
				}
			}
			}catch(Exception e){
				e.printStackTrace();
				
			}
		}
		return map ;
	}
	
	@SuppressWarnings("unchecked")
	public static Map<String,Object> beanToMapLower(Object obj) throws Exception {
		Map<String,Object> map = new HashMap<String,Object>();
		Field[] field =  obj.getClass().getDeclaredFields();
		for(Field fd : field) {
			if(fd.getName().equals("serialVersionUID"))
				continue ;
			try{
				System.out.println(changFisrtUpper(fd.getName()));
				System.out.println("get"+changFisrtUpper(fd.getName()));
			Method method = obj.getClass()
					.getMethod("get"+changFisrtUpper(fd.getName()), null);
			
			if(method!= null) {
				method.getName();
				Object obj1 = method.invoke(obj, null) ;
				if (fd.getName().equals("last_check_time")) {
					map.put(fd.getName().toLowerCase(), DateUtil.getDateTime("yyyy-MM-dd", (Date)obj1));
				}else{
					map.put(fd.getName().toLowerCase(), obj1);
				}
			}
			}catch(Exception e){
				e.printStackTrace();
				
			}
		}
		return map ;
	}
	
	@SuppressWarnings("unchecked")
	public static Map<String,Object> convertBeanToMap2(Object obj) throws Exception {
		Map<String,Object> map = new HashMap<String,Object>();
		Field[] field =  obj.getClass().getDeclaredFields();
		for(Field fd : field) {
			if(fd.getName().equals("serialVersionUID"))
				continue ;
			try{
				System.out.println(changFisrtUpper(fd.getName()));
				System.out.println("get"+changFisrtUpper(fd.getName()));
			Method method = obj.getClass()
					.getMethod("get"+changFisrtUpper(fd.getName()), null);
			
			if(method!= null) {
				method.getName();
				Object obj1 = method.invoke(obj, null) ;
				if (fd.getName().equals("last_check_time")) {
					map.put(fd.getName(), DateUtil.getDateTime("yyyy-MM-dd", (Date)obj1));
				}else{
					map.put(fd.getName(), obj1);
				}
			}
			}catch(Exception e){
				e.printStackTrace();
				
			}
		}
		return map ;
	}
	
	private static String changFisrtUpper(String str){
		return str.substring(0, 1).toUpperCase()
				+ str.substring(1, str.length());
	}
	
	/** 
     * 保留2位小数 
     *  
     * @param money 
     *            金额 
     * @param scale 
     *            精度 
     * @return 
     */  
    public static String ratioTransform2(double money) {  
        DecimalFormat df = new DecimalFormat("0.00");  
        df.setRoundingMode(RoundingMode.HALF_UP);  
        return df.format(money);  
    }  
    
    /** 
     * 保留2位小数 
     *  
     * @param money 
     *            金额 
     * @param scale 
     *            精度 
     * @return 
     */  
    public static BigDecimal ratioTransform(double money) {  
        BigDecimal moneyDev = new BigDecimal(money).setScale(2,  
                RoundingMode.HALF_UP);  
        return moneyDev;  
    }  
    
    /**
	 * 去除数组里重复的字符串
	 * 
	 * @param res
	 * @author Mr.Dawn
	 * @return
	 */
	public static String distArrays(String[] res) {
		Set<String> set = new TreeSet<String>();
		for (int i = 0; i < res.length; i++) {
			set.add(res[i]);
		}
		res = (String[]) set.toArray(new String[0]);
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < res.length; i++) {
			sb.append(res[i]).append(",");
		}
		return sb.toString().substring(0, sb.toString().length() - 1);
	}
	
	public static int getRanddom(int count){
		int[] array = {0,1,2,3,4,5,6,7,8,9};
		Random rand = new Random();
		for (int i = 10; i > 1; i--) {
			int index = rand.nextInt(i);
			int tmp = array[index];
			array[index] = array[i - 1];
			array[i - 1] = tmp;
		}
		int result = 0;
		for(int i = 0; i < count; i++)
			result = result * 10 + array[i];
		System.out.println(result);
		return result;
	}
	
	/**
	 * 获取人员当前所在部门信息
	 * 
	 * @param curUser --
	 *            系统当前登录用户信息
	 * @return Org
	 * @author GaoYa
	 * @date 2016-09-21 下午06:40:00
	 */
	public static Org getCurOrg(CurrentSessionUser curUser){
		Org org = (Org) curUser.getDepartment();	// 获取当前岗位所在部门信息
		// 不存在岗位信息时，获取当前登录用户所在部门信息
		if(org == null){
			User user = (User) curUser.getSysUser();
			org = (Org) user.getOrg();
		}
		return org;
	}
	
	 /** 
	   * 获取用户真实IP地址，不使用request.getRemoteAddr();的原因是有可能用户使用了代理软件方式避免真实IP地址, 
	   * 
	   * 可是，如果通过了多级反向代理的话，X-Forwarded-For的值并不止一个，而是一串IP值，究竟哪个才是真正的用户端的真实IP呢？ 
	   * 答案是取X-Forwarded-For中第一个非unknown的有效IP字符串。 
	   * 
	   * 如：X-Forwarded-For：192.168.1.110, 192.168.1.120, 192.168.1.130, 
	   * 192.168.1.100 
	   * 
	   * 用户真实IP为： 192.168.1.110 
	   * 
	   * @param request 
	   * @return 
	   */ 
	  public static String getIpAddress(HttpServletRequest request) { 
	    String ip = request.getHeader("x-forwarded-for"); 
	    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
	      ip = request.getHeader("Proxy-Client-IP"); 
	    } 
	    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
	      ip = request.getHeader("WL-Proxy-Client-IP"); 
	    } 
	    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
	      ip = request.getHeader("HTTP_CLIENT_IP"); 
	    } 
	    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
	      ip = request.getHeader("HTTP_X_FORWARDED_FOR"); 
	    } 
	    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
	      ip = request.getRemoteAddr(); 
	    } 
	    return ip; 
	  } 
	  
	  public static String getInspDeviceCount()
				throws Exception {
			// 获取connection
			Connection conn = Factory.getDB().getConnetion();
			String number = "";

			String sql = "select count(*) cc,to_char(sysdate,'yyyy') years from (select t.fk_tsjc_device_document_id from TZSB_INSPECTION_INFO t"
					+ " where to_char(t.advance_time,'yyyy')=to_char(sysdate,'yyyy') and t.data_status<>'99'"
					+ " group by t.fk_tsjc_device_document_id )";

			Statement queryStatement = conn.createStatement();
			ResultSet executeQuery = queryStatement.executeQuery(sql);

			while (executeQuery.next()) {

				number = executeQuery.getString("cc");
				number = number +","+ executeQuery.getString("years");
			}

			Factory.getDB().freeConnetion(conn);
			return number;

		}
	  
	  public static JSONArray getInspNew()
				throws Exception {
			// 获取connection
		  JSONArray objsa = new JSONArray();
			Connection conn = Factory.getDB().getConnetion();
			String number = "";
			String sql = "select info.id,info.report_com_name,info.report_sn,ins.check_type,info.advance_time,info.flow_note_name,info.advance_fees,substr(device.device_sort_code,0,1) device_type "
					+ " from tzsb_inspection_info info, tzsb_inspection ins,"
					+ " base_device_document device where rownum <= 20 "
					+" and info.fk_tsjc_device_document_id = device.id(+)"
					+ " and info.fk_inspection_id = ins.id(+) "
					+ " and info.data_status <> '99'"
					+ " and device.device_status <> '99'"
					+ " order by info.advance_time desc ";

			Statement queryStatement = conn.createStatement();
			ResultSet executeQuery = queryStatement.executeQuery(sql);

			while (executeQuery.next()) {
				JSONObject obj = new JSONObject();
				obj.put("report_com_name", executeQuery.getString("report_com_name"));
				obj.put("report_sn", executeQuery.getString("report_sn"));
				obj.put("check_type", getValueByCode("BASE_CHECK_TYPE",executeQuery.getString("check_type")));
				obj.put("flow_note_name", executeQuery.getString("flow_note_name"));
				obj.put("advance_time", executeQuery.getString("advance_time"));
				objsa.add(obj);
			}

			Factory.getDB().freeConnetion(conn);
			return objsa;

		}
	  
	  public static String getValueByCode(String tcode, String value){
		  Connection conn = Factory.getDB().getConnetion();
			String name="";
			String hql = "select t.name from pub_code_table_values t,PUB_CODE_TABLE t1 "
					+ "where t.code_table_id=t1.id and t1.code='"+tcode+"' and t.value='"+value+"'";
			try {
				Statement queryStatement = conn.createStatement();
				ResultSet executeQuery = queryStatement.executeQuery(hql);
				while (executeQuery.next()) {
					name = executeQuery.getString("name");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			Factory.getDB().freeConnetion(conn);
			return name;
		}
	  
		public static JSONArray getOaAuditNum() {
			JSONArray json = new JSONArray();
			try {
				 CurrentSessionUser user = SecurityUtil.getSecurityUser();
				  String userId = user.getId();
				  Connection conn = Factory.getDB().getConnetion();
					Statement pstmt = conn.createStatement();
					
					
			//1、 报告书待处理
				String sql = "SELECT T.ACTIVITY_NAME,"
						      +" subStr(t.TITLE, 0, instr(t.TITLE, '-') - 1) as flowName,"
						      +  " count(t.id) as num,"
						      + " a.activity_id,"
						      +"   a.function,"
						      +"      b.fk_flow_index_id"
						      +" FROM V_PUB_WORKTASK       T,"
						      +"    TZSB_INSPECTION_INFO b,"
						      +"   TZSB_INSPECTION      T1,"
						      +"     flow_activity        a"
						      +"  where t.SERVICE_ID = b.id"
						      +"  and B.FK_INSPECTION_ID = T1.ID"
						      +"  and b.flow_note_id = a.activity_id"
						      +"  and a.id = t.ACTIVITY_ID"
						      +"  and T.STATUS = '0'"
						      +" and b.data_status <> '99'"
						      +" and t.HANDLER_ID = '"+userId+"'"
						      +"  group by t.ACTIVITY_NAME,"
						          +" a.function,"
						          +" a.activity_id,"
						          +"  b.fk_flow_index_id,"
						          +" subStr(t.TITLE, 0, instr(t.TITLE, '-') - 1)";
				ResultSet rs = pstmt.executeQuery(sql);
				Integer count1  =  0;
				while (rs.next()) {

					JSONObject obj = new JSONObject();
					String activity_id = rs.getString("activity_id");
					String function = rs.getString("function");
					String fk_flow_index_id = rs.getString("fk_flow_index_id");
					
					obj.put("lable", rs.getString("activity_name"));
					obj.put("remark", rs.getString("flowName"));
					obj.put("count", rs.getString("num"));
					obj.put("url", "app/tanker/tanker_enter_list.jsp?flow_num="+activity_id+"&function="+function+"&flowId="+fk_flow_index_id);
					
					json.add(obj);
					System.out.println(obj);
					//String value  = rs.getBigDecimal("num").toString();
					//count1  = Integer.parseInt(value);
				}
				
				
				//2、 重大任务
				
				String sql2 = "select count(id) counts from TJY2_BG_WEIGHTYTASK "
								+ "where status !='WXF' and main_duty_id = '"+userId+"'";
				
				rs = pstmt.executeQuery(sql2);
				Integer count2  =  0;
				
				while (rs.next()) {
					JSONObject obj = new JSONObject();
					String value  = rs.getBigDecimal("counts").toString();
					count2  = Integer.parseInt(value);
					if(count2>0){
						obj.put("lable", "重大任务");
						obj.put("remark", "");
						obj.put("count", rs.getString("counts"));
						obj.put("url", "app/office/office_rv_fb_list2.jsp");
						
						json.add(obj);
					}
				}
				
				//3、任务台账
				
				String sql3 = "select count(id) counts from TJY2_YWHBSGZ"
						+ " where status != 'WXF' and responsible_personid = '"
						+userId+"' order by creater_time desc,end_tim,start_time";
				
				rs = pstmt.executeQuery(sql3);
				Integer count3  =  0;
				
				while (rs.next()) {
					JSONObject obj = new JSONObject();
					String value  = rs.getBigDecimal("counts").toString();
					count3  = Integer.parseInt(value);
					if(count3>0){
						obj.put("lable", "任务台账");
						obj.put("remark", "");
						obj.put("count", rs.getString("counts"));
						obj.put("url", "app/office/ywhbsgz_fb_list2.jsp");
						
						json.add(obj);
					}
				}
				
				//4、	工作任务接收/反馈 
				
				String sql4 = "select count(id) counts,'工作任务' lable from TJY2_YWHBSGZ where status != 'WXF' and "
						+ " responsible_personid = '"+userId+"'  and status <> 'YWC'order by creater_time desc,end_tim,start_time'";
				
				rs = pstmt.executeQuery(sql4);
				Integer count4  =  0;
				
				while (rs.next()) {
					JSONObject obj = new JSONObject();
					String value  = rs.getBigDecimal("counts").toString();
					count4  = Integer.parseInt(value);
					if(count4>0){
						obj.put("lable", "工作任务接收/反馈 ");
						obj.put("remark", "");
						obj.put("count", rs.getString("counts"));
						obj.put("url", "app/office/ywhbsgz_fb_list.jsp");
						
						json.add(obj);
					}
				}
				
				//6、	任务书审核 
				Boolean role1 = false;
				String sqlr1 = "select count(r.id) from sys_user u,sys_role r,sys_user_role ur where"
						+ " u.id = ur.user_id and ur.role_id = r.id and r.name like '%质量管理-任务书审核%' and u.id = '"+userId+"'";
				rs = pstmt.executeQuery(sqlr1);
				while (rs.next()) {
					role1 = true;
				}
				if(role1){
					String sql5 = "select count(t.id) counts from TJY2_QUALTTY_ALLOT t where t.status = 'DSH'";
					
					rs = pstmt.executeQuery(sql5);
					Integer count5  =  0;
					
					while (rs.next()) {
						JSONObject obj = new JSONObject();
						String value  = rs.getBigDecimal("counts").toString();
						count5  = Integer.parseInt(value);
						if(count5>0){
							obj.put("lable", "任务书审核");
							obj.put("remark", "");
							obj.put("count", rs.getString("counts"));
							obj.put("url", "app/qualitymanage/task_notcheck_list.jsp");
							
							json.add(obj);
						}
					}
					
				}
				
				//5、	休假请假审核
				Boolean role2 = false;
				String sqlr2 = "select count(r.id) from sys_user u,sys_role r,sys_user_role ur where"
						+ " u.id = ur.user_id and ur.role_id = r.id and r.name like '%人力资源管理-请休假申请处理按钮%' and u.id = '"+userId+"'";
				rs = pstmt.executeQuery(sqlr2);
				while (rs.next()) {
					role2 = true;
				}
				User uu = (User)user.getSysUser();
				com.khnt.rbac.impl.bean.Employee e = (com.khnt.rbac.impl.bean.Employee)uu.getEmployee();
				String eId = e.getId();
				if(role2){
					String sql6 = "select count(t.id) from (select * from (select b.*,t.handler_id from TJY2_RL_LEAVE b, v_pub_worktask t" 
							+ " where b.id=t.SERVICE_ID   and  t.WORK_TYPE  like   '%TJY2_RL_LEAVE%' "
							+ " and t.STATUS='0' ) s where s.handler_id = '"+userId+"' union "
							+ " select b.*,people_id from TJY2_RL_LEAVE b where people_id = '"+eId+"' union "
							+ " select b.*,people_id from TJY2_RL_LEAVE b where APPLY_STATUS = 'SPZ')"
							+ "  t where t.APPLY_STATUS = 'SPZ' or t.APPLY_STATUS = 'YTJ'";
					
					rs = pstmt.executeQuery(sql6);
					Integer count6  =  0;
					
					while (rs.next()) {
						JSONObject obj = new JSONObject();
						String value  = rs.getBigDecimal("counts").toString();
						count6  = Integer.parseInt(value);
						if(count6>0){
							obj.put("lable", "休假请假审核");
							obj.put("remark", "");
							obj.put("count", rs.getString("counts"));
							obj.put("url", "app/humanresources/leave/office_leave_list.jsp");
							
							json.add(obj);
						}
					}
					
				}
				
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			return json;
	  }			
			
		/**
		 * 获取当天检验的设备台数
		 * author pingZhou
		 * @return
		 * @throws Exception
		 */
		public static String getInspDeviceCountDay()
				throws Exception {
			// 获取connection
			Connection conn = Factory.getDB().getConnetion();
			String number = "";

			String sql = "select count(*) cc, to_char(sysdate, 'yyyy-MM-dd') times "
					+ "from (select t.fk_tsjc_device_document_id from TZSB_INSPECTION_INFO t "
					+ "where to_char(t.advance_time, 'yyyy-MM-dd') = to_char(sysdate, 'yyyy-MM-dd') "
					+ " and t.data_status <> '99' group by t.fk_tsjc_device_document_id)";

			Statement queryStatement = conn.createStatement();
			ResultSet executeQuery = queryStatement.executeQuery(sql);

			while (executeQuery.next()) {

				number = executeQuery.getString("cc");
			}

			Factory.getDB().freeConnetion(conn);
			return number;

		}
		
		
		/**
		 * 获取当天任务数量
		 * author pingZhou
		 * @return
		 * @throws Exception
		 */
		public static String getTaskCount()
				throws Exception {
			// 获取connection
			Connection conn = Factory.getDB().getConnetion();
			String number = "";

			String sql = "select count(t.id) cc from TJY2_QUALTTY_ALLOT t where to_char(t.register_date, 'yyyy-MM-dd') = to_char(sysdate, 'yyyy-MM-dd')";

			Statement queryStatement = conn.createStatement();
			ResultSet executeQuery = queryStatement.executeQuery(sql);

			while (executeQuery.next()) {

				number = executeQuery.getString("cc");
			}

			Factory.getDB().freeConnetion(conn);
			return number;

		}
		/**
		 * 合并参检人员和项目负责人
		 * author pingZhou
		 * @return
		 * @throws Exception
		 */
		public static String mergeCheckOps(String item , String check_op){
			if(check_op==null || "".equals(check_op)){
				return item ;
			}
			String[] ops = check_op.split(",");
			String newop = "";
			for(String op : ops ){
				if(!item.equals(op)){
					newop += ","+op ;
				}
			}
			String r_str = null ;
			if(StringUtil.isEmpty(item)){
				r_str = newop.substring(1, newop.length());
			} else {
				r_str = item+newop;
			} 
			return r_str;
		}
		
		/**
		 * 验证电话号码是否是手机号码
		 * 
		 * @param phone --
		 *            电话号码
		 * @return boolean
		 * @author GaoYa
		 * @date 2017-10-31 下午03:25:00
		 */
		public static boolean checkMobile(String phone){
			String regex = "^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(17[013678])|(18[0,5-9]))\\d{8}$";
			if (phone.length() != 11) {
				return false;
			} else {
				Pattern p = Pattern.compile(regex);
				Matcher m = p.matcher(phone);
				return m.matches();
			}
		}

	/**
	 * 计算两个日期之间相差的天数
	 * 
	 * @param smdate
	 *            较小的时间
	 * @param bdate
	 *            较大的时间
	 * @return 相差天数
	 * @throws ParseException
	 */
	public static int daysBetween(Date smdate, Date bdate) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		smdate = sdf.parse(sdf.format(smdate));
		bdate = sdf.parse(sdf.format(bdate));
		Calendar cal = Calendar.getInstance();
		cal.setTime(smdate);
		long time1 = cal.getTimeInMillis();
		cal.setTime(bdate);
		long time2 = cal.getTimeInMillis();
		long between_days = (time2 - time1) / (1000 * 3600 * 24);

		return Integer.parseInt(String.valueOf(between_days));
	}

	/**
	 * 字符串的日期格式的计算
	 * @param smdate
	 *            较小的时间
	 * @param bdate
	 *            较大的时间
	 * @return 相差天数
	 * @throws ParseException
	 */
	public static int daysBetween(String smdate, String bdate) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		cal.setTime(sdf.parse(smdate));
		long time1 = cal.getTimeInMillis();
		cal.setTime(sdf.parse(bdate));
		long time2 = cal.getTimeInMillis();
		long between_days = (time2 - time1) / (1000 * 3600 * 24);

		return Integer.parseInt(String.valueOf(between_days));
	} 
	
	/**
	 * 获取 科室报检 受理编号
	 * @return
	 * @throws Exception
	 * @author dawn
	 */
	public synchronized static String getDPNumber() throws Exception {
		// 获取connection
		Connection conn = Factory.getDB().getConnetion();
		String number = "";
		/*Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		String dateNowStr = sdf.format(date);*/
		String sql = "select 'DP'||to_char(sysdate,'YYYYMM')|| nvl2(max(accept_no), lpad(to_number(substr(max(accept_no),-5,5)) + 1, 5, 0),'00001') as con"
				+ " from TZSB_INSPECTION where accept_no like 'DP%'" ;

		/*String sql = "select 'DP'||'"
				+ dateNowStr
				+ "'|| nvl2(max(hall_no), lpad(to_number(substr(max(hall_no), 9, 13)) + 1, 5, 0),'00001') as con"
				+ " from tzsb_inspection_hall where hall_no > 'DP" + dateNowStr
				+ "'||'00000' ";*/

		Statement queryStatement = conn.createStatement();
		ResultSet executeQuery = queryStatement.executeQuery(sql);

		while (executeQuery.next()) {

			number = executeQuery.getString("con");
		}

		Factory.getDB().freeConnetion(conn);
		return number;

	}
}
