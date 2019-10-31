package com.khnt.bpm.ext.service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * <b>工作流业务接口扩展规范<b> <br>
 * 此规范定义了业务使用流程引擎的几个方法：
 * </p>
 * <ol>
 * <li>启动流程</li>
 * <li>环节提交</li>
 * <li>环节退回</li>
 * <li>流程结束</li>
 * </ol>
 * <p>
 * 本规范中所有的接口方法均采用Map作为参数传递的载体，不同的实现者可自己定义Map中的具体参数
 * </p>
 * 
 * @author hansel
 * 
 */
public interface FlowExtManager {

	/**
	 * 工作流启动方法扩展，实现了启动后自动给待处理人员发送工作任务。
	 * 
	 * @param paramMap
	 *            参数集合映射表，工作流与业务之间通过map来实现参数传递。 具体实现者根据不同的应用场景定义自己的参数表规范。
	 * 
	 * @return 返回此次操作的结果信息
	 * 
	 * @throws Exception
	 */
	public Map<String, Object> startFlowProcess(Map<String, Object> paramMap) throws Exception;

	/**
	 * 批量启动流程实例。传递的参数为List，元素是Map，Map请参考startFlowProcess方法.
	 * 
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> startFlowProcessBatch(List<Map<String, Object>> paramMapList) throws Exception;

	/**
	 * 工作流活动环节提交任务,扩展实现自动发送工作任务给下一步处理人，同时将当前人员的工作任务状态修改为已处理
	 * 
	 * @throws Exception
	 */
	public Map<String, Object> submitActivity(Map<String, Object> paramMap) throws Exception;

	/**
	 * 批量提交环节任务，参数为list，元素为Map，Map请参考 submitActivity方法
	 * 
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> submitActivityBatch(List<Map<String, Object>> paramMapList) throws Exception;

	/**
	 * 工作流活动环节退回扩展，实现给退回节点处理人发送工作任务，同时修改退回人的任务状态为已处理。
	 * 
	 * @return 操作结果
	 * @throws Exception
	 */
	public Map<String, Object> returnedActivity(Map<String, Object> paramMap) throws Exception;

	/**
	 * 工作流活动环节撤回，实现流程参与人将自己已经提交的任务撤回并重新处理再提交，前提是下一步处理人未提交。
	 * 
	 * @throws Exception
	 */
	public void recallActivity(Map<String, Object> paramMap) throws Exception;

	/**
	 * 工作流实例处理完成。
	 * 
	 * @throws Exception
	 */
	public void finishProcess(Map<String, Object> paramMap) throws Exception;

	/**
	 * 批量处理完成工作流实例，参数为List集合，list元素为每个实例的map，map参数请参考finishProcess方法
	 * 
	 * @throws Exception
	 */
	public void finishProcessBatchs(List<Map<String, Object>> paramMapList) throws Exception;

	/**
	 * 暂挂流程环节
	 * 
	 * @param activityId
	 *            暂挂的环节ID
	 * @throws Exception
	 */
	public void suspendActivity(String activityId) throws Exception;

	/**
	 * 重启流程环节
	 * 
	 * @param activityId
	 *            重启的环节ID
	 * @throws Exception
	 */
	public void restartActivity(String activityId) throws Exception;

	/**
	 * 挂起流程，使流程被锁定，无法运转
	 * 
	 * @param procId
	 */
	public void suspendProcess(String procId) throws Exception;

	/**
	 * 解锁流程
	 * 
	 * @param procId
	 */
	public void restartProcess(String procId) throws Exception;

	/**
	 * 按业务ID清除业务的所有流程数据
	 * 
	 * @param serviceId
	 */
	public void clearServiceProcess(String serviceId) throws Exception;
}