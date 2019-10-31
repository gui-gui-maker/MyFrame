package com.khnt.bpm.ext.support;

/**
 * 工作流引擎扩展之工作任务参数定义
 * 
 * @author hansel
 * 
 */
public interface FlowExtWorktaskParam extends FlowExtParam {

	/** 第一个环节任务处理方式 */
	public static final String IS_CURRENT_USER_TASK = "is_current_user_task";

	/** 是否发送工作任务 */
	public static final String IS_WORKTASK = "is_worktask";

	/** 工作任务URL参数 */
	public static final String WORKTASK_PARAMETER = "worktask_parameter";

	/** 是否发送系统消息 */
	public static final String IS_SEND_MESSAGE = "is_send_message";

	/** 是否发送短信 */
	public static final String IS_SEND_SMS = "is_send_sms";

	/** 任务紧急级别 */
	public static final String WORKTASK_LEVEL = "wroktask_level";
}
