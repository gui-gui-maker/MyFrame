package com.khnt.bpm.core.support;

/**
 * 默认需要执行的流程活动环节超时处理接口规范，这是直接继承于{@link com.khnt.bpm.core.support.LimitActionInf}
 * 接口的。
 * 
 * 其目的在于为扩展组件提供一个能够默认执行的超时监听接口，一旦超时发生即可直接响应，无需由流程设计时注册。
 * 实现类亦只需实现limitAction(Activity activity)方法即可。
 * 
 * 在实际处理中，定时任务管理器会自动在上下文环境寻找此接口的实现类实例，并执行响应方法.
 * 
 * @author hansel 2013-3-20
 * 
 */
public interface DefaultLimitActionInf extends LimitActionInf {
}
