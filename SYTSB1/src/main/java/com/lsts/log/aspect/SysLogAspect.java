package com.lsts.log.aspect;

import java.lang.reflect.Method;
import java.util.Date;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;

import com.khnt.security.CurrentSessionUser;
import com.khnt.security.util.SecurityUtil;
import com.khnt.utils.StringUtil;
import com.lsts.constant.Constant;
import com.lsts.log.bean.SysLog;
import com.lsts.log.service.SysLogService;

/**
 * 系统操作日志切面
 * 
 * @ClassName SysLogAspect
 * @JDK 1.7
 * @author GaoYa
 * @date 2014-02-28 下午02:21:00
 */
@Aspect
public class SysLogAspect {
	private Logger logger = Logger.getLogger(this.getClass().getName());

	@Autowired
	private SysLogService sysLogService;

	/**
	 * 保存打印日志逻辑方法切入点
	 */
	@Pointcut("execution(* com.lsts.log.service.SysLogService.insert*(..))")
	public void insertLogService() {
	}

	@AfterThrowing(pointcut = "insertLogService()", throwing = "e")
	public void doAfterThrowing(JoinPoint jp, Throwable e) {
		System.out.println("出现异常:" + e.getMessage());
		System.out.println(e.getClass().getName());
		System.out.println("异常所在类:" + jp.getTarget().getClass().getName());
		System.out.println("" + jp.getSignature().getName()
				+ "方法 throw exception");
		// logger.error("错误！ error级别的！！！"+e.getMessage());
		logger.error("Oops===" + jp.getTarget().getClass().getName() + "中的"
				+ jp.getSignature().getName() + "方法抛出" + e.getClass().getName()
				+ "异常");
		System.out.println("参数:");
		if (jp.getArgs() != null && jp.getArgs().length > 0) {
			for (int i = 0; i < jp.getArgs().length; i++) {
				System.out.println(jp.getArgs()[i].toString());
				logger.error("参数：--" + jp.getArgs()[i].toString());
			}
		}
	}

	@Around(value = "insertLogService()")
	public void insertLogServiceCall(JoinPoint joinPoint) throws Throwable {
		System.out.println("开始保存打印报告日志...");
		// 获得当前登录用户
		CurrentSessionUser curUser = SecurityUtil.getSecurityUser();
		if (curUser == null || StringUtil.isEmpty(curUser.getId())) { // 当前无登录用户
			return;
		}

		// 判断参数
		if (joinPoint.getArgs() == null) { // 没有参数
			return;
		}

		// 获取方法名
		String methodName = joinPoint.getSignature().getName();
		// 获取操作内容
		String opContent = operateContent(joinPoint.getArgs(), methodName);

		SysLog sysLog = new SysLog();
		sysLog.setOp_user_id(curUser.getSysUser().getId()); // 操作用户编号
		sysLog.setOp_user_name(curUser.getSysUser().getName()); // 操作用户姓名
		sysLog.setOp_time(new Date()); // 操作时间
		sysLog.setOp_action(Constant.SYS_OP_ACTION_PRINT); // 操作动作
		sysLog.setOp_remark(opContent); // 操作内容描述
		sysLogService.save(sysLog); // 保存日志
		System.out.println("保存打印报告日志【日志ID：" + sysLog.getId() + "】完成！");
	}

	/**
	 * 获取操作内容 使用Java反射来获取被拦截方法（insert、update...）的参数值，将参数值拼接为操作内容
	 * 
	 * @param args --
	 *            参数数组
	 * @param methodName --
	 *            方法名
	 * @return
	 * @author GaoYa
	 * @date 2014-02-28 下午04:38:00
	 */
	private String operateContent(Object[] args, String methodName)
			throws Exception {
		if (args == null) {
			return null;
		}

		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append(methodName);
		String className = null;
		int index = 1;
		// 遍历参数对象
		for (Object object : args) {
			// 获取对象类型
			className = object.getClass().getName();
			className = className.substring(className.lastIndexOf(".") + 1);
			stringBuffer.append("[参数" + index + "，类型：" + className + "，值：");

			// 获取对象的所有方法
			Method[] methods = object.getClass().getDeclaredMethods();
			// 遍历方法，判断get方法
			for (Method method : methods) {
				String mName = method.getName();
				// 判断是不是get方法
				if (mName.indexOf("get") == -1) { // 不是get方法
					continue; // 不处理
				}

				Object rsValue = null;
				try {
					// 调用get方法，获取返回值
					rsValue = method.invoke(object);
					if (rsValue == null) { // 没有返回值
						continue;
					}
				} catch (Exception e) {
					continue;
				}
				stringBuffer.append("(" + mName + "：" + rsValue + ")");
			}
			stringBuffer.append("]");
			index++;
		}
		return stringBuffer.toString();
	}
}
