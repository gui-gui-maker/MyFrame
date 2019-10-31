/**
 * 
 */
package com.lsts.constant;

import java.util.HashMap;
import java.util.Map;

/** 
 * @author 作者 Jack Rio
 * @JDK 1.6
 * @version 创建时间：2014年12月18日 上午10:58:49 
 * 类说明 
 */
/**
 * @author Jack
 *
 */
public class ReportConstant {
	
	public static final boolean  DEVELOP = true ; // 开发阶段 因为eclipse中加载报告内容需要转码转换成二进制。
	
	public static final boolean REPORT_SN_BY_AUTO = false ; //
	
	public static final boolean REPORT_OPEN_EXCLUSIVE = false ; // 同一时刻只能一个人打开同一份报告书
	
	//报告显示阶段
	public static final boolean SHOW_REPORT_WITH_SIGN_PIC = false ; //如果为true，则查看报告的时候，报告人员签名处显示电子签名
	
	/**报检类型**/
	public static final String CT_BASE_CHECK_TYPE = "BASE_CHECK_TYPE";	// 报检类型
	/**设备类型**/
//	public static final String CT_BASE_DEVICE_TYPE = "device_classify";	// 设备类型
	/**收费情况**/
//    public static final String CT_CHARGE_SITUATION = "charge_situation";	// 收费情况
    
	/**报检*/
	public static final String FLOW_INSPECTION = "insing";
	/**报告录入*/
	public static final String FLOW_INPUT = "input";
	/**项目负责人校核*/
	public static final String FLOW_CONFIRM = "confirm";
	/**报告审批*/
	public static final String FLOW_AUDIT = "audit";
	/**报告签发*/
	public static final String FLOW_SIGN = "sign";
	/**原始资料审查*/
	public static final String FLOW_EXAMINE = "examine";
	/**打印报告*/
	public static final String FLOW_PRINT = "print";
	/**领取报告*/
	public static final String FLOW_DRAW = "draw";
	/**报告归档*/
	public static final String FLOW_FILE = "file";
	
	/**项目负责人校核自动提交*/
	public static final boolean AUTO_SUBMIT_COMFIRM = false ;
	/**报告审核自动提交*/
	public static final boolean AUTO_SUBMIT_AUDIT = false ;
	/**报告签发自动提交*/
	public static final boolean AUTO_SUBMIT_SIGN = false ;
	/**报告打印自动提交*/
	public static final boolean AUTO_SUBMIT_PRINT = false ;
	/**报告领取自动提交*/
	public static final boolean AUTO_SUBMIT_DRAW = false ;

	/**报告检验项目检验结果集合*/
	public static final Map<String, String> char_to_JgMap(){
		Map<String, String> map = new HashMap<String, String>();
		map.put("√", "符合");
		map.put("✔", "符合");
		map.put("符合", "符合");
		map.put("0", "资料确认符合");//数字0
		map.put("○", "资料确认符合");
		map.put("⭕", "资料确认符合");
		map.put("O", "资料确认符合");//大写字母o
		map.put("△", "资料确认符合");
		map.put("×", "不符合");
		map.put("✘", "不符合");
		map.put("不符合", "不符合");
		map.put("∕", "无此项");
		map.put("/", "无此项");
		map.put("—", "无此项");
		map.put("-", "无此项");
		map.put("——", "无此项");
		map.put("--", "无此项");
		map.put("无此项", "无此项");
	    return map;
	}

	/**报告检验项目检验结果之符合*/
	public static final String ITEM_S = "符合";
	/**报告检验项目检验结果之不符合*/
	public static final String ITEM_F = "不符合";
	
	/**报告检验项目检验结论集合*/
	public static final Map<String, String> char_to_JlMap(){
		Map<String, String> map = new HashMap<String, String>();
		map.put("√", "合格");
		map.put("✔", "合格");
		map.put("合格", "合格");
		map.put("×", "不合格");
		map.put("✘", "不合格");
		map.put("不合格", "不合格");
		map.put("∕", "无此项");
		map.put("/", "无此项");
		map.put("—", "无此项");
		map.put("-", "无此项");
		map.put("——", "无此项");
		map.put("--", "无此项");
		map.put("无此项", "无此项");
	    return map;
	}

	/**报告检验项目检验结论之符合*/
	public static final String ITEM_JL_S = "合格";
	/**报告检验项目检验结论之不符合*/
	public static final String ITEM_JL_F = "不合格";


	/**设备完整日期格式参数*/
	public static final String[] DEVICE_DATE_PARAM = { "certificate_date", "construction_finish_date",
			"construction_notify_date", "sbbgqk_bgrq1", "sbbgqk_bgrq2", "sbbgqk_bgrq3", "sbbgqk_bgrq4", "inspect_date",
			"inspect_next_date", "registration_date", "first_inspect_date", "register_fill_table_date",
			"register_signature_date" };
	
	/** 原始记录转换报告之系统参数 */
	public static final String[] REPORT_SYS_PARAMS = { "REPORT_INSPECTION_ORG", "REPORT_INSPECTION_ADDRESS",
			"REPORT_INSPECTION_POSTCODE", "REPORT_INSPECTION_PHONE", "REPORT_INSPECTION_EMAIL", "REPORT_INSPECTION_FAX",
			"REPORT_JGHZH" };
}
