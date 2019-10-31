package com.lsts.constant;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.Map;

import com.khnt.utils.DateUtil;
import com.khnt.utils.StringUtil;
import com.lsts.common.dao.CodeTablesDao;
import com.lsts.inspection.bean.Inspection;
import com.lsts.inspection.bean.InspectionHall;
import com.lsts.inspection.bean.InspectionHallPara;
import com.scts.maintenance.bean.MaintenanceInfo;

/**
 * 系统常量
 * 
 * @author GaoYa
 * @date 2014-02-28 16:50:00
 */
public class Constant {

	/**
	 * 系统操作动作
	 */
	public static final String SYS_OP_ACTION_PRINT = "打印报告"; // 打印报告
	public static final String SYS_OP_ACTION_EXPORT_PDF = "报告导出PDF"; // 报告导出PDF
	public static final String SYS_OP_ACTION_PRINT_TAGS = "打印合格证"; // 打印合格证
	public static final String SYS_OP_ACTION_AUTO_ISSUE = "签字分配"; // 打印合格证

	/**
	 * 打印机类型
	 */
	public static final String PRINTER_TYPE_R = "1"; // 报告打印机
	public static final String PRINTER_TYPE_T = "2"; // 标签打印机

	/**
	 * 数据字典
	 */
	public static final String CT_BASE_LS_AREA = "BASE_LS_AREA"; // 乐山行政区划编码
	public static final String CT_BASE_CHECK_TYPE = "BASE_CHECK_TYPE"; // 报检类型
	public static final String CT_BASE_DEVICE_TYPE = "device_classify"; // 设备类型

	/**
	 * 报告书编号生成规则（生成规则：前缀[CO]-设备类别T[电梯：T、客运索道S]检验类别C[定期：D/监督：A]年份R[2014]序号XXX[00000]）
	 * 其中5位序号，从50001开始
	 */
	public static final String REPORT_SN_PRE = "CO"; // 前缀
	public static final String REPORT_SN_T = "T"; // 设备类别
	public static final String REPORT_SN_C = "C"; // 检验类别（定期）
	public static final String REPORT_SN_Y = "Y"; // 日期（年份年号）
	public static final String REPORT_SN_R = "R"; // 日期（年份年号）
	public static final String REPORT_SN_XXX = "XXX"; // 序号

	/**
	 * 报告书编号之设备类别编号
	 */
	public static final String REPORT_SN_T_T = "T"; // 设备类别（电梯）
	public static final String REPORT_SN_T_G = "G"; // 设备类别（锅炉）
	public static final String REPORT_SN_T_R = "R"; // 设备类别（压力容器）
	public static final String REPORT_SN_T_D = "D"; // 设备类别（压力管道）
	public static final String REPORT_SN_T_Q = "Q"; // 设备类别（起重机械）
	public static final String REPORT_SN_T_S = "S"; // 设备类别（客运索道）
	public static final String REPORT_SN_T_Y = "Y"; // 设备类别（游乐设施）
	public static final String REPORT_SN_T_N = "N"; // 设备类别（场（厂）内机动车）
	public static final String REPORT_SN_T_F = "F"; // 设备类别（安全阀）
	public static final String REPORT_SN_T_H = "H"; // 设备类别（安全附件及安全保护装置）
	public static final String REPORT_SN_T_B = "B"; // 设备类别（部件）
	public static final String REPORT_SN_T_Z = "Z"; // 设备类别（锅炉水处理）
	public static final String REPORT_SN_T_P = "P"; // 设备类别（气瓶）
	public static final String REPORT_SN_T_J = "J"; // 设备类别（储气井）
	public static final String REPORT_SN_T_QT = "Z"; // 设备类别（其他）
	public static final String REPORT_SN_T_CYGC = "CYGC"; // 设备类别（常压罐车）

	/**
	 * 报告书编号之检验类别编号
	 */
	public static final String REPORT_SN_C_D = "D"; // 检验类别（定期）
	public static final String REPORT_SN_C_A = "A"; // 检验类别（安改维监督）
	public static final String REPORT_SN_C_J = "J"; // 检验类别（制造监督）
	public static final String REPORT_SN_C_K = "K"; // 检验类别（进口设备）
	public static final String REPORT_SN_C_C = "C"; // 检验类别（出口设备）
	public static final String REPORT_SN_C_W = "W"; // 检验类别（委托）

	/**
	 * 系统设备类别
	 */
	public static final String DEVICE_TYPE_1 = "1"; // 锅炉
	public static final String DEVICE_TYPE_2 = "2"; // 压力容器
	public static final String DEVICE_TYPE_2_3 = "23"; // 气瓶
	public static final String DEVICE_TYPE_3 = "3"; // 电梯
	public static final String DEVICE_TYPE_3_3 = "3"; // 液压电梯
	public static final String DEVICE_TYPE_3_4 = "4"; // 杂物电梯
	public static final String DEVICE_TYPE_3_5 = "5"; // 自动扶梯
	public static final String DEVICE_TYPE_3_6 = "6"; // 自动人行道
	public static final String DEVICE_TYPE_4 = "4"; // 起重机械
	public static final String DEVICE_TYPE_5 = "5"; // 场（厂）内机动车辆
	public static final String DEVICE_TYPE_6 = "6"; // 大型游乐设施
	public static final String DEVICE_TYPE_7 = "7"; // 压力管道元件
	public static final String DEVICE_TYPE_8 = "8"; // 压力管道
	public static final String DEVICE_TYPE_9 = "9"; // 客运索道
	public static final String DEVICE_TYPE_B = "B"; // 部件
	public static final String DEVICE_TYPE_C = "C"; // 材料
	public static final String DEVICE_TYPE_F = "F"; // 安全附件及安全保护装置
	public static final String DEVICE_TYPE_Q = "Q"; // 安全阀
	public static final String DEVICE_TYPE_Q_1 = "1"; // 锅炉安全阀
	public static final String DEVICE_TYPE_Q_2 = "2"; // 压力容器安全阀
	public static final String DEVICE_TYPE_Q_3 = "3"; // 压力管道安全阀
	public static final String DEVICE_TYPE_Q_4 = "4"; // 其它设备安全阀
	public static final String DEVICE_TYPE_0 = "0";   // 其他

	/**
	 * 系统检验类别
	 */
	public static final String CHECK_TYPE_1 = "1"; // 制造监督检验
	public static final String CHECK_TYPE_2 = "2"; // 安改维监督检验
	public static final String CHECK_TYPE_3 = "3"; // 定期检验
	public static final String CHECK_TYPE_4 = "4"; // 委托检验
	public static final String CHECK_TYPE_5 = "5"; // 其他检验
	public static final String CHECK_TYPE_6 = "6"; // 起重首检
	public static final String CHECK_TYPE_7 = "7"; // 进口设备检验
	public static final String CHECK_TYPE_8 = "8"; // 出口设备检验
	
	/**
	 * 微信消息
	 */
	public static final String MESSAGE_CORPID = "kh.scsei.org.cn"; // 短信-账户名
	public static final String MESSAGE_PWD = "2774ab4e730554c8a0b097d610fefe16"; // 短信-密码
	public static final String INSPECTION_CORPID = "book.kh"; // 微信企业号应用-检验平台-账户名
	public static final String INSPECTION_PWD = "d2ed00acc5c236b2cfd93d36d959bd93"; // 微信企业号应用-检验平台-密码
	public static final String OFFICE_CORPID = "office.kh"; // 微信企业号应用-办公助手-账户名
	public static final String OFFICE_PWD = "203906a747e93883b2cde43af4e1efae"; // 微信企业号应用-办公助手-密码
	public static final String PEOPLE_CORPID = "people.kh"; // 微信企业号应用-人力资源-账户名
	public static final String PEOPLE_PWD = "16a61256d01a80cbecaaf38627dd6940"; // 微信企业号应用-人力资源-密码
    public static final String CHLQ_CORPID = "goods.kh"; //微信企业号应用-存货管理-账户名
    public static final String CHLQ_PWD = "389b3cc936a381c284b1b438749a4aa8"; //微信企业号应用-存货管理-密码
	public static final String ZDSX_CORPID="honest.kh";//微信企业号应用-纪检监察-账户名
	public static final String ZDSX_PWD="101031d60044468eec6726fffffc39b1";//微信企业号应用-纪检监察-密码
	
	/**
	 * 部门及人员信息
	 */
	public static final String CHECK_UNIT_100069 = "100069"; // 赴藏特检突击队
	public static final String CHECK_UNIT_100090 = "100090"; // 援疆特检突击队
	public static final String CHECK_UNIT_100091 = "100091"; // 九寨特检突击队
	
	// "402884c4477c9bac01477ff4f8f30066" 鄢霈然emp id
	// "402883a046cbb20a0146cbbe550c0034" 周青emp id 2016-11-15明子涵要求修改
	// "402884c4477c9bac01477fee5bf00042" 张甜甜emp id 2017-10-23张展彬要求修改
	public static final String XZ_REPORT_NOTIC_EMPID = "402884c4477c9bac01477fee5bf00042";
	// "402884c4477c9bac01477ff3f5600060" 李奇emp id 2017-12-12明子涵要求修改
	public static final String XJ_REPORT_NOTIC_EMPID = "402884c4477c9bac01477ff3f5600060";
	// "402884c4477c9bac01477ff543890068" 田寒emp id 2018-03-30明子涵要求修改
	public static final String JZ_REPORT_NOTIC_EMPID = "402884c4477c9bac01477ff543890068";
	
	/**
	 * 不符合报告文件编号
	 */
	public static final String REPORT_ERROR_FILENAME = "CTJC-019-B08"; // 不符合报告文件编号
	public static final String REPORT_ERROR_REMARK = "纠错报告启用老版本报告模板"; // 纠错报告启用老版本模板备注
	public static final String  REPORT_apply_REMARK = "旧版本报告模板启用"; //旧版本报告模板启用
	public static final String  REPORT_stope_REMARK = "旧版本报告模板停用"; //旧版本报告模板停用
	
	/**
	 * 批量设备ID
	 */
	public static final String DEFAULT_DEVICE_ID = "11111111111111111111111111111111"; // 批量设备默认ID
	
	/**
	 * 角色名称
	 */
	public static final String ROLE_NAME_BMZR = "部门主任"; 
	
	// 批量设备信息导入（Excel）工作表标题
	// 设备基础信息表标题
	public static final String[] DEVICE_TITLES = { "设备注册代码", "使用登记证号", "设备名称",
			"设备类别", "出厂编号", "使用单位", "安全管理员", "安全管理员联系电话", "设备使用地点", "设备所在地",
			"设备所在街道", "下次检验日期", "备注" };
	// 报告数据导入（Excel）工作表标题
	// 压力容器制造监督检验证书信息表标题
	public static final String[] YLRQ_ZZJD_INFO_TITLES = { "制造单位", "制造许可证级别",
			"制造许可证编号", "设备类别", "产品名称", "产品编号", "设备代码", "设计单位", "设计许可证编号",
			"产品图号", "设计日期", "制造日期", "有关安全技术监察规程", "备注、说明", "监检员1", "监检员2",
			"监检日期", "预收金额" };

	// 制造监督检验证书(出口压力容器)信息表标题
	public static final String[] CKYLRQ_ZZJD_INFO_TITLES = { "制造单位", "制造许可证级别", 
			"制造许可证编号", "产品名称", "产品编号", "设计单位", "设计许可证编号", "产品图号", 
			"设计日期", "制造日期", "有关安全技术监察规程", "备注、说明", "监检员1", "监检员2", "监检日期", "预收金额" };
	
	// 车用气瓶安装监督检验证书信息表标题
	// 2017-04-14 向同琼和质量部提出修改，去掉记录表内容，只保留证书页
		/*
		"气瓶文件审查", "文件审查日期", "安装资料审查", "安装资料审查日期", "气瓶外观检查", "气瓶外观检查日期",
		"安装质量检查", "安装质量检查日期", "泄漏试验确认", "泄漏试验日期", "安装竣工资料审查", "安装竣工资料审查日期",
		"对安装单位质量体系运转情况的评价", "记事栏", */
	public static final String[] QP_AZJD_INFO_TITLES = { "车辆识别代号", "安装单位名称",
			"安装许可证编号", "安装日期", "工作介质", "公称压力", "气瓶制造单位", "制造日期", "气瓶编号", "设备代码",
			"监检员1", "监检员2", "监检日期", "预收金额" };

	// 批量制造压力容器监督检验证书信息表标题
	public static final String[] PLYLRQ_ZZJD_INFO_TITLES = { "制造单位", "制造许可证级别",
			"制造许可证编号", "设备类别", "产品名称", "产品批号", "设备代码", "设计单位", "设计许可证编号",
			"产品图号", "设计日期", "制造日期", "有关安全技术监察规程", "备注、说明", "监捡所抽的产品编号",
			"本证书适用的产品编号", "监检员1", "监检员2", "监检日期", "预收金额" };

	// 压力容器改造与重大修理监督检验证书信息表标题
	public static final String[] YLRQ_GZYZDXL_INFO_TITLES = { "施工单位", "许可证编号", "施工类别", "使用单位", "设备使用地点", "设备类别",
			"使用登记证编号", "设备代码", "设备名称", "竣工日期", "产品图号", "有关安全技术监察规程",
			"改造与重大修理项目", "监检员1", "监检员2", "监检日期", "预收金额" };

	// 爆破片装置安全性能监督检验证书信息表标题
	public static final String[] ANFJ_BPPJJ_INFO_TITLES = { "制造单位", "制造许可证号",
			"产品名称", "产品型式型号", "产品批号", "产品数量", "产品标准", "制造日期", "监检员1", "监检员2",
			"监检日期", "材料牌号", "文件、材料、标记移植", "爆破试验及标定爆破压力", "产品质量证明书及外观、标志", "记事",
			"其他说明", "预收金额" };

	// 制造监督检验证书(压力容器封头、压力容器承压部件)信息表标题
	public static final String[] CYFT_ZZJJ_INFO_TITLES = { "制造单位", "设备类别", "产品名称", "产品编（批）号", 
			"型式规格", "材料", "数量", "来料加工", "部件图号", 
			 "制造日期", "有关安全技术监察规程", "备注", "监检员1", "监检员2", "监检日期", "预收金额" };

	// 气瓶制造监督检验证书信息表标题
	public static final String[] QP_ZZJJ_INFO_TITLES = { "制造单位", "许可证号",
			"设备类别", "产品名称", "产品型号", "产品批号", "产品数量", "产品编号", "产品标准", "制造日期",
			"本批产品编号中不包括", "监检标志标注部位", "监检员1", "监检员2", "监检日期", "预收金额" };
	
	// 安全阀安全性能监督检验证书信息表标题
	public static final String[] AQF_ZZJD_INFO_TITLES = { "制造单位", 
			"许可证编号", "设备类别", "产品名称", "产品型号", "产品适用温度", "产品适用介质", "出厂编号1",
			"出厂编号2", "产品数量", "有关安全技术监察规程", "监检员1", "监检员2",
			"监检日期", "预收金额" };
	
	// 压力管道元件监检证书（埋弧焊钢管）、（低温管件）信息表标题（低温管件的制造监检证书模板与埋弧焊钢管一样，所以共用同一个表头）
	public static final String[] YLGDYJ_MHHGG_ZZJD_INFO_TITLES = { "制造单位", 
			"制造许可证编号", "设备类别", "产品名称", "产品规格", "产品批号", "制造标准", "部件编号及管号",
			"产品数量", "产品制造日期", "有关安全技术监察规程", "监检员1", "监检员2",
			"监检日期", "预收金额" };
	
	// 压力管道元件监检证书（聚乙烯管）信息表标题
	public static final String[] YLGDYJ_JYXG_ZZJD_INFO_TITLES = { "制造单位", 
			"制造许可证编号", "设备类别", "产品名称", "产品规格", "产品批号", "制造标准", "部件编号及管号",
			"等级/型号", "产品数量", "产品制造日期", "有关安全技术监察规程", "监检员1", "监检员2",
			"监检日期", "预收金额" };
	
	// 金属常压罐体制造委托监督检验证书信息表标题
	public static final String[] YLRQ_JSCYGT_ZZWTJD_INFO_TITLES = { "制造单位", "许可证级别及编号", 
			"设备类别", "产品名称", "产品型号", "产品编号", "制造完成日期", "发动机编号",
			"车辆识别代码", "设计压力", "设计温度", "设计介质", "设计容积", "相关标准", "监检员1", "监检员2",
			"监检日期", "预收金额" };
	
	// 压力管道安装安全质量监督检验证书信息表标题
	public static final String[] YLGD_AZAQZLJD_INFO_TITLES = { "建设单位", "安装单位", 
			"设计单位", "工程名称", "设备类别", "管道名称", "管道编号", "管道材质", "管道等级", "管道规格",
			"管道长度", "设计压力", "设计温度", "输送介质", "安装竣工日期", "检验结论", "监检员1", "监检员2",
			"监检日期", "预收金额" };
	
	// 特种设备制造监检证书（锅炉）信息表标题
	public static final String[] GL_ZZJD_INFO_TITLES = { "制造单位", "制造许可证级别",
			"制造许可证编号", "设备类别", "设备品种（名称）", "产品型号", "产品编（批）号", "设备代码", 
			"产品总图图号", "制造日期", "说明", "监检员1", "监检员2",
			"监检日期", "预收金额" };
	
	// 特种设备制造监检证书（锅炉部件、组件）信息表标题
	public static final String[] GL_BJZJ_ZZJD_INFO_TITLES = { "制造单位",
			"制造许可证级别", "制造许可证编号", "设备类别", "设备品种（名称）", "产品编（批）号", "产品图号",
			"制造日期", "备注", "监检员1", "监检员2", "监检日期", "预收金额" };
	
	// 特种设备制造监督检验证书（出口锅炉及锅炉部件、组件）信息表标题
	public static final String[] CKGL_BJZJ_ZZJD_INFO_TITLES = { "制造单位", "制造许可级别", "制造许可证编号", "设备类别", "产品名称", "产品型号",
			"产品编（批）号", "产品图号", "制造日期", "备注", "有关安全技术监察规程", "监检员1", "监检员2", "监检日期", "预收金额" };

	// 锅炉设计文件鉴定审查报告信息表标题
	public static final String[] GL_SJWJJDSC_INFO_TITLES = { "鉴定人",
			"鉴定日期", "图号", "型号", "锅炉名称", "制造单位", "预收金额", "锅炉类别", "设计属性", "鉴定属性",
			"审查属性", "额定出力（t/h）或（MW）", "额定出口压力（MPa）", "锅炉给水（回水）温度（℃）",
			"额定出口温度（℃）", "设计燃料种类", "结构形式", "燃烧方式", "燃料低位发热量不低于（MJ/Kg）",
			"燃烧机型号", "稳定工况范围（%）", "设计热效率（%）", "总图编号/设计/审核/批准人员/批准日期/备注",
			"水冷（壁）系统图或本体图编号/设计/审核/批准人员/批准日期/备注", "对流管束编号/设计/审核/批准人员/批准日期/备注",
			"锅筒、汽水分离器、储水罐编号/设计/审核/批准人员/批准日期/备注", "过热器编号/设计/审核/批准人员/批准日期/备注",
			"减温器编号/设计/审核/批准人员/批准日期/备注", "省煤器编号/设计/审核/批准人员/批准日期/备注",
			"再热器编号/设计/审核/批准人员/批准日期/备注", "有机热载体锅炉系统图编号/设计/审核/批准人员/批准日期/备注",
			"强度计算汇总表编号/设计/审核/批准人员/批准日期/备注", "主给水管道编号/设计/审核/批准人员/批准日期/备注", 
			"主蒸汽管道编号/设计/审核/批准人员/批准日期/备注", "再热蒸汽冷段管道编号/设计/审核/批准人员/批准日期/备注", 
			"再热蒸汽热段管道编号/设计/审核/批准人员/批准日期/备注", 
			"制造许可级别", "制造许可证编号", "制造单位地址", "备注", "本体耗钢量（吨）", "是否自编号" };
	
	
	// 安全阀校验报告信息表标题
	public static final String[] AQF_JYBG_INFO_TITLES = { "是否自编号", "报告编号", "使用单位", "单位地址", "邮政编码", "阀门型号", "执行标准",
			"编号类型", "编号值", "联系人", "联系电话", "产品编号", "安装位置", "安全阀类型", "工作压力", "工作介质", "要求整定压力", "校验方式", "校验介质", "整定压力",
			"密封试验压力", "校验结果", "维护检修情况说明", "校验日期", "下次检验日期", "校验员", "预收金额" };

	// 超高压水晶釜委托检验报告信息表标题
	public static final String[] CGY_SJF_WTJYBG_INFO_TITLES = { "设备名称", "检验类别", "容器类别", "设备代码", "单位内编号", "使用登记证编号",
			"制造单位", "安装单位", "使用单位", "使用单位地址", "设备使用地点", "使用单位统一社会信用代码", "邮政编码", "安全管理人员", "联系电话", "设计使用年限（年）", "投入使用日期",
			"主体结构型式", "运行状态", "容积m³", "内径mm", "设计压力Mpa", "设计温度℃", "使用压力Mpa", "使用温度℃。", "工作介质", "检验依据", "问题及其处理",
			"安全状况等级评定（级）", "检验结论", "压力Mpa", "温度℃", "介质", "其他", "下次委托检验日期（年月）", "说明", "检验人员", "编制日期", "预收金额" };

	// 封头委托制造监督检验证书信息表标题
		public static final String[] FT_WTZZJD_INFO_TITLES = { "制造单位", "设备类别", "产品名称", "产品编（批）号", 
				"型式规格", "材料", "数量", "来料加工", "部件图号", 
				 "制造日期", "有关安全技术监察规程", "备注", "监检员1", "监检员2", "监检日期", "预收金额" };

	
	// 通用开票批量导入信息表标题
	public static final String[] PAYMENT_IMPORT_INFO_TITLES = { "受检单位", "开票单位", "报告编号", "发票号", "合同号", "检验部门", "收费方式",
			"金额", "数量", "开票日期" };

	// 修改金额默认审核人emp id
	// 孙宇艺：402883a04a055c63014a3342f02661d6
	// 雷兰：402884c4477c9bac01477ff874cd0076
	public static final String[] CHANGE_MONEY_CHK_EMP_ID = { "402883a04a055c63014a3342f02661d6",
			"402884c4477c9bac01477ff874cd0076" };

	// 软件功能任务书默认接收人emp id
	// 张展彬：402883a046d0c7990146d0c88c3b0001
	public static final String FUNCTION_TASK_RECEIVE_EMP_ID = "402883a046d0c7990146d0c88c3b0001";
	public static final String FUNCTION_TASK_RECEIVE_EMP_NAME = "张展彬";
	// 非业务服务部的自动报送人姓名
	public static final String REPORT_TRANSFER_USER_NAME = "曹鹏";

	// 机电检验部门ID（机电一到六部）
	public static final String[] INSPECTION_JD_ORG_ID = { "100020", "100021", "100022", "100023", "100024", "100063" };
	// 承压检验部门ID
	public static final String[] INSPECTION_CY_ORG_ID = { "100034", "100035", "100036", "100033", "100037", "100065",
			"100066", "100067" };

	// 机电类检验证书类型
	public static final String[] INSPECTION_JD_CERT_TYPES = { "TS", "DT", "DT-1", "QS", "QZ-1", "YS", "YL-1", "SS",
			"SD-1", "NS", "NC-1" };
	// 机电类检验证书类型（定检）
	public static final String[] INSPECTION_JD_DJ_CERT_TYPES = { "TS", "DT", "DT-1", "QS", "QZ-1", "YS", "YL-1", "SS",
			"SD-1", "NS", "NC-1" };
	// 机电类检验证书类型（监检，拥有监检证书的人可以进行定检）
	public static final String[] INSPECTION_JD_JJ_CERT_TYPES = { "TS", "DT", "QS", "YS", "SS", "NS" };
	// 承压类检验证书类型
	public static final String[] INSPECTION_CY_CERT_TYPES = { "GS", "RS", "DS", "GL-1", "GL-2", "RQ-1", "RQ-2", "QP",
			"QP-1", "QP-2", "GD", "GD-1", "GD-2" };
	// 承压类检验证书类型（定检）
	public static final String[] INSPECTION_CY_DJ_CERT_TYPES = { "GS", "RS", "DS", "GL-1", "RQ-1", "QP-1", "GD-1" };
	// 承压类检验证书类型（监检，拥有监检证书的人可以进行定检）
	public static final String[] INSPECTION_CY_JJ_CERT_TYPES = { "GS", "RS", "DS", "GL-2", "RQ-2", "QP-2", "GD-2" };

	// 省院在成都市内检验区域代码
	public static final String[] INSPECTION_DT_CD_AREA_CODES = { "510104", "510106", "510109", "510122", "510189" };

	// 电梯设备同步字段
	public static final String DT_UPDATE_COLUMNS = "COM_CODE,DEVICE_QR_CODE,INTERNAL_NUM,DEVICE_MODEL,FACTORY_CODE,SECURITY_OP,SECURITY_TEL,DEVICE_USE_PLACE,MAKE_DATE,MAINTAIN_UNIT_NAME,MAKE_UNITS_NAME,CONSTRUCTION_UNITS_NAME,CONSTRUCTION_LICENCE_NO,REGISTRATION_NUM";
	// 设备修改字段
	public static final String DEVICE_MODIFY_COLUMNS = "DEVICE_REGISTRATION_CODE,REGISTRATION_NUM,DEVICE_QR_CODE";
	
	// 罐车报告发送提醒所需检验项目参数
	// REPORT_SN：报告书编号、LADLE_CAR_NUMBER：车牌号、INTERNAL_NUM：单位内编号、SECURITY_TEL：安全管理员联系电话
	public static final String GC_REPORT_ITEM_NAMES = "REPORT_SN,LADLE_CAR_NUMBER,INTERNAL_NUM,SECURITY_TEL";
	
	// 用车申请审核人
	// 办公室负责人emp id
	public static final String CAR_APPLY_OFFICE_EMP_ID = "402883a0515e5d76015160c1d6573b92";
	// 分管院领导emp id
	public static final String CAR_APPLY_LEADER_EMP_ID = "402884c447802e51014780553e3b0012";
	// 车队负责人emp id
	public static final String CAR_APPLY_FLEET_EMP_ID = "402883a0515e5d76015160c576513bd5";
	// 车队派车长emp id
	public static final String CAR_APPLY_FLEET2_EMP_ID = "402883a0515e5d76015160c576513bd5,402883a0515e5d76015160c616013c04";
	// 财务部部长emp id
	public static final String CAR_APPLY_CW_EMP_ID = "402883a04a055c63014a3342f02661d6";
	// 科管部部长emp id
	public static final String CAR_APPLY_KG_EMP_ID = "402884c4477c9bac01477ffbef010088";
	// 司法鉴定中心部长emp id
	public static final String CAR_APPLY_SF_EMP_ID = "402883a0515e5d76015160cebd2d3c8e";
	
	/**
	 * 智能一体机系统参数 start...
	 */
	public static final String MACHINE_ACTION_DRAW = "报告领取"; 
	public static final String MACHINE_ACTION_DRAW_DESC = "保存领取单"; 
	public static final String MACHINE_ACTION_PRINT_REPORT = "打印报告"; // 打印报告
	public static final String MACHINE_ACTION_PRINT_TAG = "打印合格证"; // 打印合格证
	// 系统管理员 user id 用于自动提交流程
	public static final String SYS_ADMIN_USER_ID = "402884c4477c9bac01477fe0d188001b";
	
	// 智能一体机支付方式
	public static final String MACHINE_PAY_TYPE_ALIPAY_CODE = "alipay"; 	
	public static final String MACHINE_PAY_TYPE_WEIXIN_CODE = "weixin"; 
	public static final String MACHINE_PAY_TYPE_ALIPAY_TEXT = "支付宝"; 	
	public static final String MACHINE_PAY_TYPE_WEIXIN_TEXT = "微信"; 	
	
	
	/**
	 * 支付信息
	 */
	public static final String PAY_TITLE = "四川省特种设备检验研究院-特种设备检验费";	// 支付标题（支付描述）
	public static final String PAY_TYPE_ALIPAY = "alipay"; 	// 支付宝支付
	public static final String PAY_TYPE_WEIXIN = "weixin"; 	// 微信支付
	public static final String PAY_STATUS_PAYED = "payed"; 	// 支付状态（已支付）
	public static final String PAY_STATUS_UNPAY = "unpay"; 	// 支付状态（未支付）
	public static final String PAY_OVERTIME_30M = "30m";	// 支付超时时长
	
	// 支付宝支付
	public static final String ALIPAY_TRADE_PARTNER = "2088131485788775";		// PID（合作身份者ID，签约账号）	
	public static final String ALIPAY_TRADE_CODE_40004 = "40004"; 				// 交易状态（交易不存在）
	public static final String ALIPAY_TRADE_CODE_10000 = "10000"; 				// 交易状态（交易存在）
	public static final String ALIPAY_TRADE_SUCCESS = "TRADE_SUCCESS"; 			// 支付成功
	public static final String ALIPAY_TRADE_FINISHED = "TRADE_FINISHED"; 		// 支付完成
	public static final String ALIPAY_TRADE_WAIT_BUYER_PAY = "WAIT_BUYER_PAY"; 	// 等待支付
	public static final String ALIPAY_TRADE_TRADE_CLOSED = "TRADE_CLOSED";		// 交易关闭
	/**
	 * 智能一体机系统参数 end...
	 */
	
	public static final DecimalFormat defaultDecimalFormat = new DecimalFormat("0.00");// 格式化数字
	public static final String defaultDatePattern = "yyyy-MM-dd"; // 默认日期格式
	public static final String ymDatePattern = "yyyy-MM"; // 默认日期格式
	public static final String ymdDatePattern = "yyyyMMdd"; // 年月日日期格式
	public static final String hmsDatePattern = "yyyyMMddHHmmss"; // 带时分秒的日期格式
	public static final String ymdhmsDatePattern = "yyyy-MM-dd HH:mm:ss"; // 带时分秒的日期格式
	
	/**
	 * 微信应用消息
	 */
	public static final String WEIXIN_ERROR_URL = "app/weixininfo/app_info/weixin_error_page";
	public static final String WEIXIN_APP_MSG_UNDEFINED = "亲，未找到微信应用配置，请联系系统管理员！";	
	public static final String WEIXIN_APP_MSG_NOT_INDEX_URL = "亲，该微信应用未配置首页响应地址，请联系系统管理员！";	
	public static final String WEIXIN_APP_MSG_NOT_DEAL_URL = "亲，该微信应用未配置业务处理地址，请联系系统管理员！";	
	
	/**
	 * 历年年假计算周期
	 */
	public static final String TJY2_RL_ANNUAL_LEAVE_CYCLE_2018 = "2017-04-01,2018-03-02,2019-02-04";
	public static final String TJY2_RL_ANNUAL_LEAVE_CYCLE_2019 = "2018-03-02,2019-02-04,2020-01-23";
	public static final String TJY2_RL_ANNUAL_LEAVE_CYCLE_2020 = "2019-02-04,2020-01-23,2021-02-10";
	
	/**
	 * 报告领取打印内容
	 * 
	 * @param get_user_name --
	 *            领取人
	 * @param count --
	 *            报告份数
	 * @param report_use_unit --
	 *            报告书使用单位
	 * @param area_name --
	 *            安装区域
	 * @param report_sn --
	 *            报告书编号
	 * @param linkmode --
	 *            领取人联系电话
	 * @param op_user_name --
	 *            工作人员
	 * 
	 * @author GaoYa
	 * @date 2014-05-28 16:50:00
	 * @return
	 */
	public static String getReportDrawPrintContent(String get_user_name,
			int count, String report_use_unit, String area_name,
			String report_sn, String linkmode, String op_user_name) {
		String content = "<table width=\"100%\">";
		content += "<caption style=\"text-align:center\"><font size=\"4\"><b>四川省特种设备检验研究院<br>报告领取记录</b></font></caption>";
		content += "<tr><td>&nbsp;</td><td>&nbsp;</td></tr><tr><td>&nbsp;</td><td>&nbsp;</td></tr>";
		content += "<tr><td align=\"left\" colspan=\"2\" style=\"word-break:break-all; word-wrap:break-word;\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
		content += get_user_name + " 今领取省特检院检验报告（定检/验收）" + count + " 份，";
		content += "<tr><td align=\"left\" colspan=\"2\">报告使用单位："
				+ report_use_unit + "</td></tr>";
		content += "<tr><td align=\"left\" >安装区域：" + area_name
				+ "</td><td align=\"left\" >报告书编号：" + report_sn + "</td></tr>";
		content += "<tr><td align=\"left\" >领取人联系电话：" + linkmode
				+ "</td><td align=\"left\" >领取日期："
				+ DateUtil.getDateTime("yyyy年MM月dd日", new Date())
				+ "</td></tr>";
		content += "<tr><td align=\"left\" colspan=\"2\">工作人员：" + op_user_name
				+ "</td></tr>";
		content += "<tr><td>&nbsp;</td><td>&nbsp;</td></tr><tr><td>&nbsp;</td><td>&nbsp;</td></tr>";
		content += "<tr><td align=\"left\" colspan=\"2\">领取人（签字）确认：</td></tr>";
		return content;
	}

	/**
	 * 大厅报检打印回执单内容
	 * 
	 * @param inspectionHall --
	 *            报检信息
	 * @author GaoYa
	 * @date 2014-05-20 10:50:00
	 * @return
	 */
	public static String getInspectionHallPrintContent(
			InspectionHall inspectionHall) {
		String content = "<table width=\"100%\" >";
		content += "<caption style=\"text-align:center\"><font size=\"4\"><b>四川省特种设备检验研究院<br>大厅报检回执单</b></font></caption>";
		content += "<tr><td>&nbsp;</td></tr><tr><td>&nbsp;</td></tr>";
		content += "<tr><td align=\"left\" style=\"word-break:break-all; word-wrap:break-word;padding-left:40%;\">";
		content += "报检单号：" + inspectionHall.getHall_no() + "</td></tr>";
		content += "<tr><td align=\"left\" style=\"word-break:break-all; word-wrap:break-word;padding-left:40%;\">";
		content += "报检单位：" + inspectionHall.getCom_name() + "</td></tr>";
		content += "<tr><td align=\"left\" style=\"word-break:break-all; word-wrap:break-word;padding-left:40%;\">";
		content += "报检日期："
				+ DateUtil.getDateTime("yyyy年MM月dd日", inspectionHall
						.getReg_date()) + "</td></tr>";
		content += "<tr><td align=\"left\" style=\"word-break:break-all; word-wrap:break-word;padding-left:40%;\">";
		content += "报检类型：" + inspectionHall.getInspection_type() + "</td></tr>";
		content += "<tr><td align=\"left\" style=\"word-break:break-all; word-wrap:break-word;padding-left:40%;\">";
		content += "备&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注："
				+ (StringUtil.isNotEmpty(inspectionHall.getRemark()) ? inspectionHall
						.getRemark()
						: "") + "</td></tr>";
		content += "<tr><td align=\"left\" style=\"word-break:break-all; word-wrap:break-word;padding-left:40%;\">";
		content += "报检设备：</td></tr>";
		if (inspectionHall.getHallPara().size() > 0) {
			CodeTablesDao codeTablesDao = new CodeTablesDao();
			int index = 0;
			for (InspectionHallPara inspectionHallPara : inspectionHall
					.getHallPara()) {
				index++;
				content += "<tr><td align=\"left\" style=\"padding-left:43%;\">"
						+ (index)
						+ "、设备类型："
						+ codeTablesDao.queryValueByCode(CT_BASE_DEVICE_TYPE,
								inspectionHallPara.getDevice_type())
						+ "（数量："
						+ inspectionHallPara.getDevice_no() + "）</td></tr>";

			}
		}
		content += "<tr><td style=\"height: 300px;\"></td> </tr><tr><td style=\"border-bottom:1px dashed #000;width: 400px;\"></tr>";
		content += "<tr><td style=\"height: 20px;\"></td> </tr>";
		content += "<tr><td align=\"left\" style=\"word-break:break-all; word-wrap:break-word;padding-left:40%;\">";
		content += "报检单号："
				+ inspectionHall.getHall_no()
				+ "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  检验员签收： </td></tr>";
		content += "</table>";

		return content;
	}

	/**
	 * 安全阀报检打印回执单内容
	 * 
	 * @param inspection --
	 *            报检信息
	 * @return
	 */
	public static String getInspectionPrintContent(Inspection inspection,
			int count) {
		String content = "<table width=\"100%\">";
		content += "<caption style=\"text-align:center\"><font size=\"4\"><b>四川省特种设备检验研究院<br>安全阀报检回执单</b></font></caption>";
		content += "<tr><td>&nbsp;</td></tr><tr><td>&nbsp;</td></tr>";
		content += "<tr><td align=\"left\" style=\"word-break:break-all; word-wrap:break-word;padding-left:100px;\">";
		content += "编&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号："
				+ DateUtil.getDateTime(hmsDatePattern, inspection
						.getInspection_time() != null ? inspection
						.getInspection_time() : new Date()) + "</td></tr>";
		content += "<tr><td align=\"left\" style=\"word-break:break-all; word-wrap:break-word;padding-left:100px;\">";
		content += "报检单位：" + inspection.getCom_name() + "</td></tr>";
		content += "<tr><td align=\"left\" style=\"word-break:break-all; word-wrap:break-word;padding-left:100px;\">";
		content += "数&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;量："
				+ count + "</td></tr>";
		content += "<tr><td align=\"left\" style=\"word-break:break-all; word-wrap:break-word;padding-left:100px;\">";
		content += "备&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注："
				+ (StringUtil.isNotEmpty(inspection.getRemark()) ? inspection
						.getRemark() : "") + "</td></tr>";
		content += "<tr><td align=\"left\" style=\"word-break:break-all; word-wrap:break-word;padding-left:400px;\">";
		content += "送&nbsp;&nbsp;检&nbsp;&nbsp;人：</td></tr>";
		content += "<tr><td align=\"left\" style=\"word-break:break-all; word-wrap:break-word;padding-left:400px;\">";
		content += "报检日期：" + DateUtil.getDateTime("yyyy年MM月dd日", new Date())
				+ "</td></tr>";
		content += "</table>";
		return content;
	}

	/**
	 * 设备检验通知书打印内容
	 * 
	 * @param com_name --
	 *            单位名称
	 * @param device_type --
	 *            设备类型
	 * @param 数量 --
	 *            count
	 * @return
	 */
	public static String getDeviceCheckNoticePrintContent(String com_name,
			String device_type, int count) {
		String content = "<table width=\"100%\">";
		content += "<caption style=\"text-align:center\"><font size=\"4\"><b>特种设备定期检验通知书</b></font></caption>";
		content += "<tr><td>&nbsp;</td></tr><tr><td align=\"right\" style=\"padding-right:50px;\">乐特检：____________号</td></tr>";
		content += "<tr><td align=\"left\" ><u>" + com_name + "</u>：</td></tr>";
		content += "<tr><td align=\"left\" style=\"word-break:break-all; word-wrap:break-word;\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;你单位的&nbsp;";
		content += "<u>" + device_type + "</u>&nbsp;共计&nbsp;<u>" + count
				+ "</u>&nbsp;台，已超过检验有效期，根据中华人民共和国国务院《特种设备安全监察条例》的规定，";
		content += "特种设备的使用到期必须进行定期检验，超过检验期而未经检验的特种设备不得继续使用。";
		content += "<tr><td>&nbsp;</td></tr><tr><td>&nbsp;</td></tr><tr><td>&nbsp;</td></tr><tr><td>&nbsp;</td></tr>";
		content += "<tr><td align=\"right\" style=\"padding-right:50px;\">四川省特种设备检验研究院</td></tr>";
		content += "<tr><td align=\"right\" style=\"padding-right:100px;\">"
				+ DateUtil.getDateTime("yyyy年MM月dd日", new Date())
				+ "</td></tr></table>";
		content += "<tr><td>&nbsp;</td></tr><tr><td>&nbsp;</td></tr><tr><td>&nbsp;</td></tr>";
		content += "<tr><td>联系人：<br>电话：0833-2105093   2105112   2601936<br>地址：四川省成都市东风路北二巷4号</td></tr>";
		content += "</table>";
		return content;
	}

	/**
	 * 不符合报告通知发送内容（质量部）
	 * 
	 * @param report_error_sn --
	 *            不符合编号
	 * @param solve_end_date --
	 *            处理截止日期
	 * 
	 * @return
	 * @author GaoYa
	 * @date 2015-12-10 14:33:00
	 */
	public static String getReportErrorNoticeContent(String report_error_sn, String solve_end_date) {
		String content = "温馨提示：您有不符合报告（不符合编号："+report_error_sn+"），请在"+solve_end_date+"前处理完成！查看详情，请登录检验系统，进入【质量管理】-【不符合报告】中查看！\r\n（检验平台）";
		return content;
	}
	
	/**
	 * 不符合报告通知发送内容（业务服务部）
	 * 
	 * @param apply_date --
	 *            申请日期
	 * @param report_count --
	 *            报告份数
	 * @param report_com_name --
	 *            报告使用单位
	 * @return
	 * @author GaoYa
	 * @date 2018-05-03 09:49:00
	 */
	public static String getReportErrorNoticeContent2(String apply_date, int report_count, String report_com_name) {
		String content = "温馨提示：您于"+apply_date+"申请的"+report_com_name+report_count+"台纠错报告，现处于“待确认”未能完成，请尽快到pt.scsei.org.cn的【检验软件】-【不符合报告】中处理。\r\n【业务服务部】";
		return content;
	}
	
	/**
	 * 检验资料报送提醒发送内容
	 * 
	 * @param enter_user_name --
	 *            录入人
	 * @param com_name --
	 *            单位名称
	 * @param count --
	 *            报告数量
	 * @return
	 * @author GaoYa
	 * @date 2017-03-07 15:05:00
	 */
	public static String getReportPrintNoticeContent(String enter_user_name, String com_name, int count) {
		String content = enter_user_name+"同志，您提交的报送打印申请（"+com_name+"，共"+count+"份），原始记录还未报送哦，请及时报送！\r\n【业务服务部】";
		return content;
	}
	
	/**
	 * 任务书审核发送内容
	 * 
	 * @param quality_task_sn --
	 *            任务书编号
	 * @param item_date --
	 *            期望完成日期
	 * 
	 * @return
	 * @author Quinc
	 * @date 2016-09-07 10:45:33
	 */
	public static String getQualityTaskContent(String quality_task_sn, String item_date) {
		String content = "您有任务书需要审核（任务书编号："+quality_task_sn+"），请及时处理！查看详情，请登录检验系统，进入【质量管理】-【任务书审核】中查看！\r\n（检验平台）";
		return content;
	}
	
	/**
	 * 无损检测报告单独审核通知发送内容
	 * 
	 * @param report_sn --
	 *            报告编号
	 * 
	 * @return
	 * @author GaoYa
	 * @date 2015-12-10 14:33:00
	 */
	public static String getWsReportNoticeContent(String report_sn) {
		String content = "您有无损检测报告（报告编号："+report_sn+"），需要您审核！查看报告详情，请登录检验系统，进入【工作平台】-【无损检测审核】中查看！\r\n（检验平台）";
		return content;
	}
	
	/**
	 * 西藏报告提醒消息推送
	 * 
	 * @param report_sn --
	 *            报告编号
	 * 
	 * @return
	 * @author GaoYa
	 * @date 2015-12-10 14:33:00
	 */
	public static String getXzReportPayNoticeContent(String com_name, int count, String report_sn) {
		String content = com_name+"的报告"+report_sn+"等"+count+"份，已开票！查看详情，请登录检验系统，进入【收费管理】-【已收费】中查看！\r\n（检验平台）";
		return content;
	}
	
	/**
	 * 新疆报告提醒消息推送
	 * 
	 * @param report_sn --
	 *            报告编号
	 * 
	 * @return
	 * @author GaoYa
	 * @date 2017-12-12 10:05:00
	 */
	public static String getXjReportPayNoticeContent(String com_name, int count, String report_sn) {
		String content = com_name+"的报告"+report_sn+"等"+count+"份，已开票！查看详情，请登录检验系统，进入【收费管理】-【已收费】中查看！\r\n（检验平台）";
		return content;
	}
	
	/**
	 * 九寨报告提醒消息推送
	 * 
	 * @param report_sn --
	 *            报告编号
	 * 
	 * @return
	 * @author GaoYa
	 * @date 2018-03-29 13:52:00
	 */
	public static String getJzReportPayNoticeContent(String com_name, int count, String report_sn) {
		String content = com_name+"的报告"+report_sn+"等"+count+"份，已开票！查看详情，请登录检验系统，进入【收费管理】-【已收费】中查看！\r\n（检验平台）";
		return content;
	}
	
	/**
	 * 财务独立密码发送内容
	 * 
	 * @param second_pwd --
	 *            独立密码
	 * 
	 * @return
	 * @author GaoYa
	 * @date 2016-01-02 19:40:00
	 */
	public static String getSecondPwdNoticeContent(String second_pwd) {
		String content = "您财务管理的新独立密码为："+second_pwd+"，请妥善保管！亲，就是打死也不能告诉别人哦！\r\n（检验平台）";
		return content;
	}
	
	/**
	 * 印章独立密码发送内容
	 * 
	 * @param print_pwd --
	 *            独立密码
	 * 
	 * @return
	 * @author GaoYa
	 * @date 2016-09-01 10:40:00
	 */
	public static String getPrintPwdNoticeContent(String print_pwd) {
		String content = "您电子印章的新独立密码为："+print_pwd+"，请妥善保管！亲，就是打死也不能告诉别人哦！\r\n（检验平台）";
		return content;
	}
	
	/**
	 * 运维台账功能完成后反馈发送内容
	 * 
	 * @param info --
	 *            台账信息
	 * 
	 * @return
	 * @author GaoYa
	 * @date 2016-01-02 19:40:00
	 */
	public static String getMaintenanceNoticeContent(MaintenanceInfo info, String type, String user_name) {
		String content = "";
		if("1".equals(type)){
			content += info.getAdvance_user_name();
		}else{
			content += user_name;
		}
		content += "同志，你反馈的";	
		if(StringUtil.isNotEmpty(info.getPro_desc())){
			content = "“"+(info.getPro_desc().length()>128?info.getPro_desc().substring(0,128)+"......":info.getPro_desc())+"”";	
		}
		content += "已处理完毕。请登录检验软件平台，在首页的“平台运维日志”里查看详细。咨询电话：86607814张\r\n（信息中心）";
		return content;
	}
	
	/**
	 * 用车申请审核提醒内容（短信）
	 * 
	 * @param apply_user_name 
	 * 					-- 申请人
	 * @param apply_sn 
	 * 					-- 申请编号
	 * @return
	 * @author GaoYa
	 * @date 2018-07-16 19:54:00
	 */
	public static String getCarApplyDxTips(String apply_user_name, String apply_sn) {
		String content = "";
		if (StringUtil.isNotEmpty(apply_user_name) && StringUtil.isNotEmpty(apply_sn)) {
			content = "您有一条来自" + apply_user_name + "的用车申请待处理，编号为" + apply_sn + "，请及时处理！";
		}
		return content;
	}
	
	/**
	 * 用车时间到期未还车消息提醒内容（短信）
	 * 
	 * @param car_num 
	 * 					-- 车牌号
	 * @param use_end_date 
	 * 					-- 到期日期
	 * @return
	 * @author GaoYa
	 * @date 2018-08-16 10:43:00
	 */
	public static String getCarApplyReturnWxTips(String car_num, String use_end_date) {
		String content = "";
		if (StringUtil.isNotEmpty(car_num) && StringUtil.isNotEmpty(use_end_date)) {
			content = "温馨提示：为了便于车辆管理，借车（"+car_num+"/"+use_end_date+"）使用到期未归还的部门，请及时归还或办理续借手续。（车队 86607809）";
		}
		return content;
	}
	
	/**
	 * 不符合报告，责任部门负责人确认提醒内容（微信）
	 * 
	 * @param deal_user_name 
	 * 					-- 责任人（检验员）
	 * @param sn 
	 * 					-- 不符合编号
	 * @return
	 * @author GaoYa
	 * @date 2018-07-24 11:14:00
	 */
	public static String getReportErrorWxTips(String deal_user_name, String sn) {
		String content = "";
		if (StringUtil.isNotEmpty(deal_user_name) && StringUtil.isNotEmpty(sn)) {
			content = "您有一条来自" + deal_user_name + "的不符合报告待确认，编号为" + sn + "，请及时确认！【质量监督管理部】";
		}
		return content;
	}
	
	/**
	 * 用车申请审核提醒内容（微信）
	 * 
	 * @param apply_user_name 
	 * 					-- 申请人
	 * @param apply_sn 
	 * 					-- 申请编号
	 * @return
	 * @author GaoYa
	 * @date 2018-07-17 09:21:00
	 */
	public static String getCarApplyWxTips(String apply_user_name, String apply_sn, String apply_id,
			String apply_status) {
		String content = "";
		String check_url = "";
		if (StringUtil.isNotEmpty(apply_id) && StringUtil.isNotEmpty(apply_status)) {
			check_url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxb0f376eb09e64dd3"
					+ "&redirect_uri=http://kh.scsei.org.cn/car/apply/weixinUserInfo.do?" + "businessId=" + apply_id
					+ "&businessStatus=" + apply_status + "&response_type=code&scope=snsapi_base&state=STATE";
		}
		if (StringUtil.isNotEmpty(apply_user_name) && StringUtil.isNotEmpty(apply_sn)) {
			if (StringUtil.isNotEmpty(check_url)) {
				content = "<a href='" + check_url + "'>您有一条来自" + apply_user_name + "的用车申请待处理，编号为" + apply_sn
						+ "，请点击处理！</a>";
			} else {
				content = "您有一条来自" + apply_user_name + "的用车申请待处理，编号为" + apply_sn + "，请及时处理！";
			}
		}
		return content;
	}
	
	/**
	 * 运维台账功能论证后反馈发送内容
	 * 
	 * @param info --
	 *            台账信息
	 * 
	 * @return
	 * @author GaoYa
	 * @date 2017-08-04 15:08:00
	 */
	public static String getMaintenanceProveContent(MaintenanceInfo info) {
		String content = "";
		if(StringUtil.isNotEmpty(info.getPro_desc())){
			content = "“"+(info.getPro_desc().length()>128?info.getPro_desc().substring(0,128)+"......":info.getPro_desc())+"”";	
		}
		content =content+"功能已通过论证，";
		if("0".equals(info.getProve_type())){
			content += "进入开发修改环节，预计"+DateUtil.format(info.getExpect_finish_date(), defaultDatePattern)+"完成。感谢你对院信息化工作的支持。\r\n（信息中心）";
		}else{
			content += "因"+info.getProve_remark()+"原因延迟开发。感谢你对院信息化工作的支持。\r\n（信息中心）";
		}
		return content;
	}
	
	/**
	 * 报告出具提醒
	 * 
	 * @param fromUser --
	 *            提醒对象
	 * @param toUser --
	 *            被提醒对象
	 * @param report_name --
	 *            报告名称
	 * @param report_sn --
	 *            报告编号
	 * @param report_com_name --
	 *            使用单位
	 * 
	 * @return
	 * @author GaoYa
	 * @date 2016-08-04 16:14:00
	 */
	public static String getReportNoticeContent(String fromUser, String toUser, String report_name, String report_sn, String report_com_name) {
		String content = toUser + "同志，您的小伙伴" + fromUser + "正在检验软件里使用您的签名出具";
		if(StringUtil.isNotEmpty(report_name)){
			content += "" + report_name + "（报告编号：";
		}else{
			content += "检验报告（报告编号：";
		}
		if(StringUtil.isNotEmpty(report_sn)){
			content += report_sn + "，使用单位：";
		}else{
			content += "暂未生成，使用单位：";
		}
		if(StringUtil.isNotEmpty(report_com_name)){
			content += report_com_name + "）。";
		}else{
			content += "暂未录入）。";
		}
		content += "详情可登录检验软件查看。咨询电话：86607814张\r\n（信息中心）";
		return content;
	}
	
	/**
	 * 金额修改审核完成提醒发送内容
	 * 
	 * @param report_sn --
	 *            报告编号
	 * @param report_com_name --
	 *            使用单位
	 * @param check_emp_name --
	 *            审核人
	 * @param check_result --
	 *            审核结果
	 * 
	 * @return
	 * @author GaoYa
	 * @date 2016-11-18 11:37:00
	 */
	public static String getChangeMoneyCheckNoticeContent(String report_com_name, String report_sn, String check_emp_name, String check_result) {
		String content = "您提交的金额修改申请（使用单位："+report_com_name+"，编号："+report_sn+"）已由"+check_emp_name+check_result+"！查看详情，请登录检验系统，进入【收费管理】-【修改金额记录】中查看！\r\n（检验平台）";
		return content;
	}
	
	
	
	/**
	 * 金额修改申请提醒审核发送内容
	 * 
	 * @param create_emp_name --
	 *            申请人
	 * @param report_com_name --
	 *            使用单位           
	 * @param report_sn --
	 *            报告编号

	 * @return
	 * @author GaoYa
	 * @date 2016-11-17 17:26:00
	 */
	public static String getChangeMoneyNoticeContent(String create_emp_name, String report_com_name, String report_sn) {
		String content = create_emp_name+"提交了金额修改申请（使用单位："+report_com_name+"，编号："+report_sn+"）需要您审核！请登录检验系统，进入【收费管理】-【修改金额记录】中审核！后续开放微信回复审核功能，敬请期待！\r\n（检验平台）";
		return content;
	}
	
	/**
	 * 软件功能任务书下达后，提醒接收发送内容
	 * @param create_org_name --
	 *            任务下达部门名称
	 * @param create_user_name --
	 *            任务下达人        
	 * @param sn --
	 *            任务编号

	 * @return
	 * @author GaoYa
	 * @date 2016-11-30 15:20:00
	 */
	public static String getFuncTaskReceiveNoticeContent(String create_org_name, String create_user_name, String sn) {
		String content = create_org_name+create_user_name+"提交了软件功能开发/修改任务书（编号："+sn+"）需要您接收！查看详情，请登录检验系统，进入【质量管理】-【软件功能任务书】-【任务接收】中查看！\r\n（检验平台）";
		return content;
	}
	
	/**
	 * 任务退回时，提醒下达人
	 * @param op_user_name --
	 *            操作人    
	 * @param sn --
	 *            任务编号

	 * @return
	 * @author GaoYa
	 * @date 2016-12-15 16:20:00
	 */
	public static String getFuncTaskBackNoticeContent(String op_user_name, String sn) {
		String content = op_user_name+"退回了软件功能开发/修改任务书（编号："+sn+"）！查看详情，请登录检验系统，进入【质量管理】-【软件功能任务书】-【任务管理】中查看！\r\n（检验平台）";
		return content;
	}
	
	/**
	 * 罐车报告签发后发送消息提醒（一份报告发送一条）
	 * @param content_map -- 消息提醒内容集合

	 * @return
	 * @author GaoYa
	 * @date 2017-10-30 15:49:00
	 */
	public static String getGcNoticeContent(Map<String, Object> content_map) {
		String content = "贵单位";
		String car_num = content_map.get("LADLE_CAR_NUMBER")+"";
		if(StringUtil.isEmpty(car_num)){
			car_num = content_map.get("INTERNAL_NUM")+"";
		}else{
			if("null".equals(car_num)){
				car_num = content_map.get("INTERNAL_NUM")+"";
			}
		}
		
		if(!"null".equals(car_num)){
			content += "（"+ car_num + "）";
		}
		
		String report_sn = content_map.get("REPORT_SN")+"";
		
		if(StringUtil.isNotEmpty(car_num) && StringUtil.isNotEmpty(report_sn)){
			content += "的报告（"+ report_sn + "）已完成，进入领取/邮寄状态。";
		}else{
			content = "";
		}
		return content;
	}
	
	/**
	 * 罐车报告签发后发送消息提醒（同号码多份报告组合一条发送）
	 * @param content_map -- 消息提醒内容集合

	 * @return
	 * @author GaoYa
	 * @date 2018-03-20 11:21:00
	 */
	public static String getGcNoticeContent2(String car_numbers, String report_sns) {
		String content = "";
		if(StringUtil.isNotEmpty(car_numbers) && StringUtil.isNotEmpty(report_sns)){
			content += "贵单位（"+car_numbers+"）的报告（"+ report_sns + "）已完成，进入领取/邮寄状态。";
		}
		return content;
	}
}
