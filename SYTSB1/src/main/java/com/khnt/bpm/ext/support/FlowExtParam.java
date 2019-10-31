package com.khnt.bpm.ext.support;

/**
 * <b>工作流扩展参数定义接口</p>
 * 
 * <p>
 * 这里定义了工作流扩展实现所定义的接口方法参数列表
 * </p>
 * 
 * @author hansel
 * 
 */
public interface FlowExtParam {

	/** 流程业务ID */
	public static final String SERVICE_ID = "service_id";
	/** 描述信息 */
	public static final String REMARK = "remark";
	/** 数据总线 */
	public static final String DATA_BUS = "data_bus";
	/** 流程模板ID */
	public static final String FLOW_DEFINITION_ID = "flow_definition_id";
	/** 业务标题 */
	public static final String SERVICE_TITLE = "serevice_title";
	/** 业务类别 */
	public static final String SERVICE_TYPE = "serevice_type";
	/** 环节ID */
	public static final String ACTIVITY_ID = "activity_id";
	/** 流程实例ID */
	public static final String PROCESS_ID = "process_id";
	/** 工作流用户 */
	public static final String BPM_USER = "bpm_user";
	/** 流程结束类型 */
	public static final String FINISH_TYPE = "finish_type";
	/** 流程名称 */
	public static final String PROCESS_NAME = "process_name";
	/** 环节提交后下一步环节集合 */
	public static final String RESULT_ACTIVITY_LIST = "result_activity_list";
	/** 
	 * 流程操作结果类型。所有的类型定义在com.khnt.bpm.core.support.FlowServiceResult类中 
	 */
	public static final String RESULT_TYPE = "result_type";
	
	/** 环节提交者参与的下一步环节 */
	public static final String BPM_USER_NEXT_ACTIVITY = "bpm_user_next_activity";
}
